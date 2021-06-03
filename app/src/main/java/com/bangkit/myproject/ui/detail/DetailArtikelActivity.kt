package com.bangkit.myproject.ui.detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.bangkit.myproject.R
import com.bangkit.myproject.data.source.local.entity.ArticleEntity
import com.bangkit.myproject.databinding.ActivityDetailArtikelBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.text.SimpleDateFormat

class DetailArtikelActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var binding: ActivityDetailArtikelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArtikelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val detailArticle = intent.getParcelableExtra<ArticleEntity>(EXTRA_DATA)

        showDetailArticle(detailArticle)
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    @SuppressLint("SimpleDateFormat")
    private fun showDetailArticle(articleEntity: ArticleEntity?) {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val output = SimpleDateFormat("dd-MM-yyyy HH:mm")
        val date = format.parse(articleEntity?.created)
        val show = output.format(date)

        articleEntity.let {
            Glide.with(this).load(articleEntity?.images).apply(RequestOptions())
                .error(R.drawable.no_image).into(binding.imgArticle)
            binding.title.text = articleEntity?.title
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                binding.content.text =
                    Html.fromHtml(articleEntity?.description, Html.FROM_HTML_MODE_LEGACY)
            }
            binding.dateTimeArticle.text = show
        }
    }
}