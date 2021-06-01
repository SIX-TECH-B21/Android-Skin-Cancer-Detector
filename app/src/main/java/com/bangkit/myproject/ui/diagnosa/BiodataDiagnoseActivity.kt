package com.bangkit.myproject.ui.diagnosa

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.RadioButton
import androidx.core.content.ContextCompat
import com.bangkit.myproject.R
import com.bangkit.myproject.databinding.ActivityBiodataDiagnosaBinding


class BiodataDiagnoseActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_LAT = "extra_lat"
        const val EXTRA_LONG = "extra_long"
    }

    private var nameValid = false
    private var age = false
    private var gender = false

    private lateinit var binding: ActivityBiodataDiagnosaBinding

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBiodataDiagnosaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.title_bar_diagnosa)

        binding.btnKembali.setOnClickListener {
            onBackPressed()
        }
        val lat = intent.getDoubleExtra(EXTRA_LAT, 0.0)
        val longitude = intent.getDoubleExtra(EXTRA_LONG, 0.0)

        validateButton()

        binding.edTextName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validateName()
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        binding.edTextUmur.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validateAge()
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        binding.btnLanjut.setOnClickListener {
            val gender = when {
                binding.rdLakiLaki.isChecked -> false
                binding.rdPerempuan.isChecked -> true
                else -> null
            }

            val intent = Intent(this, ScanDiagnoseActivity::class.java)
            intent.putExtra(ScanDiagnoseActivity.EXTRA_LATITUDE, lat)
            intent.putExtra(ScanDiagnoseActivity.EXTRA_LONGITUDE, longitude)
            intent.putExtra(ScanDiagnoseActivity.EXTRA_NAME, binding.edTextName.text.toString())
            intent.putExtra(ScanDiagnoseActivity.EXTRA_AGE, binding.edTextUmur.text.toString())
            intent.putExtra(ScanDiagnoseActivity.EXTRA_GENDER, gender.toString())

            startActivity(intent)

        }

    }

    private fun validateAge() {
        val input = binding.edTextUmur
        when {
            input.length() > 2 -> {
                age = false
                showAlertMaxLength(true)
            }
            input.length() < 1 -> {
                age = false
                showAlertNotNull(true)
            }
            else -> {
                age = true
                showAlertMaxLength(false)
            }
        }
        validateButton()
    }

    private fun showAlertNotNull(state: Boolean) {
        binding.textLayoutUmur.error = if (state) "Umur wajib di isi!" else null
    }

    private fun showAlertMaxLength(state: Boolean) {
        binding.textLayoutUmur.error = if (state) getString(R.string.age_max) else null
    }

    private fun validateName() {
        val input = binding.edTextName.text.toString()
        if (input.length < 3) {
            nameValid = false
            showNameMinimalAlert(true)
        } else {
            nameValid = true
            showNameMinimalAlert(false)
        }
        validateButton()
    }

    private fun validateButton() {
        if (nameValid && age && gender) {
            binding.btnLanjut.isEnabled = true
            binding.btnLanjut.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.colorPrimary
                )
            )
        } else {
            binding.btnLanjut.isEnabled = false
            binding.btnLanjut.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
        }
    }

    private fun showNameMinimalAlert(isNotValid: Boolean) {
        binding.textLayout.error = if (isNotValid) getString(R.string.name_minimal) else null
    }

    fun onClickRadio(view: View) {

        if (view is RadioButton) {
            val checked = view.isChecked

            when (view.id) {
                R.id.rd_laki_laki -> {
                    gender = checked
                }
                R.id.rd_perempuan -> {
                    gender = checked
                }
            }
        }
        validateButton()
    }

}