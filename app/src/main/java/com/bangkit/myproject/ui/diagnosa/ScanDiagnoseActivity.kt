package com.bangkit.myproject.ui.diagnosa

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.bangkit.myproject.databinding.ActivityScanDiagnosaBinding
import com.bangkit.myproject.utils.getFileName
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.jvm.Throws

class ScanDiagnoseActivity : AppCompatActivity() {

    private val scanViewModel: ScanViewModel by viewModels()
    private lateinit var currentPhotoPath: String
    private lateinit var binding: ActivityScanDiagnosaBinding
    private var uri: Uri? = null
    private var author: String = "com.bangkit.myproject.fileprovider"

    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanDiagnosaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btnKembali.setOnClickListener {
            onBackPressed()
        }

        binding.takeCamera.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Select Image")
            builder.setMessage("Choose your option?")
            builder.setPositiveButton("Gallery") { dialog, _ ->
                dialog.dismiss()

                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                startActivityForResult(intent, 101)
            }

            builder.setNegativeButton("Camera") { dialog, _ ->
                dialog.dismiss()
                Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePicture ->
                    takePicture.resolveActivity(packageManager)?.also {
                        val permission =
                            ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                        if (permission != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(
                                this,
                                arrayOf(Manifest.permission.CAMERA),
                                1
                            )
                        } else {
                            val photoFile: File? = try {
                                createImageFile()
                            } catch (ex: IOException) {
                                null
                            }
                            photoFile?.also {
                                uri = FileProvider.getUriForFile(
                                    this,
                                    author,
                                    it
                                )

                                takePicture.putExtra(MediaStore.EXTRA_OUTPUT, uri)
                                startActivityForResult(takePicture, 202)
                            }
                        }
                    }
                }
            }

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

        scanViewModel.isLoading.observe(this, {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        scanViewModel.toastText.observe(this, {
            it.getContentIfNotHandled()?.let { toast ->
                Toast.makeText(this, toast, Toast.LENGTH_SHORT).show()
            }
        })

        scanViewModel.getIdStatus.observe(this, {
            scanViewModel.getResultDetection(it)
        })

        scanViewModel.listScan.observe(this, { data ->
            val moveToResult = Intent(this, ResultDiagnosaActivity::class.java)
            moveToResult.putExtra(ResultDiagnosaActivity.EXTRA_DIAGNOSE, data)
            startActivity(moveToResult)
        })

        binding.btnDiagnosa.setOnClickListener {
            postData()
        }
    }

    @SuppressLint("SimpleDateFormat")
    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            timeStamp,
            ".jpg",
            storageDir
        ).apply {
            currentPhotoPath = absolutePath
        }

    }

    @Suppress("DEPRECATION")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101 && resultCode == RESULT_OK && data != null) {
            uri = data.data
            Glide.with(this).load(uri).apply(RequestOptions()).into(binding.imgFromCamera)

        }
        if (requestCode == 202 && resultCode == RESULT_OK) {

            try {
                if (Build.VERSION.SDK_INT < 28) {
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
                    Glide.with(this).load(bitmap).apply(RequestOptions())
                        .into(binding.imgFromCamera)
                } else {
                    val source = uri?.let { ImageDecoder.createSource(contentResolver, it) }
                    val bitmap = source?.let { ImageDecoder.decodeBitmap(it) }
                    binding.imgFromCamera.setImageBitmap(bitmap)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    private fun postData() {
        if (uri == null) {
            Toast.makeText(this, "Gambar tidak ada", Toast.LENGTH_SHORT).show()
            return
        }

        val parcelFile = contentResolver.openFileDescriptor(uri!!, "r", null)
        val inputStream = FileInputStream(parcelFile?.fileDescriptor)
        val file = File(cacheDir, contentResolver.getFileName(uri!!))

        val outputStream = FileOutputStream(file)
        inputStream.copyTo(outputStream)

        val latitude = intent.getDoubleExtra(EXTRA_LATITUDE, 0.0)
        val logtitude = intent.getDoubleExtra(EXTRA_LONGITUDE, 0.0)


        val name = intent.getStringExtra(EXTRA_NAME)

        val age = intent.getStringExtra(EXTRA_AGE)?.toInt()
        val gender = intent.getStringExtra(EXTRA_GENDER).toBoolean()


        val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("ImagesFile", file.name, requestFile)
        val requestBodyGetName =
            name?.toRequestBody("multipart/form-data".toMediaTypeOrNull())

        scanViewModel.postDiagnose(body, latitude, logtitude,
            requestBodyGetName, age, gender)
    }

    companion object {
        const val EXTRA_LATITUDE = "extra_latitude"
        const val EXTRA_LONGITUDE = "extra_longitude"
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_AGE = "extra_age"
        const val EXTRA_GENDER = "extra_gender"
    }

}