package com.bangkit.myproject.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.myproject.R
import com.bangkit.myproject.data.source.local.entity.ArticleEntity
import com.bangkit.myproject.databinding.ItemArticlesBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.text.SimpleDateFormat
import kotlin.collections.ArrayList

class ArticlesAdapter : RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder>() {

    private var listArticles = ArrayList<ArticleEntity>()
    var onItemClick: ((ArticleEntity) -> Unit)? = null

    fun setArticles(articles: List<ArticleEntity>?) {
        if (articles == null) return
        this.listArticles.clear()
        this.listArticles.addAll(articles)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val itemArticlesBinding =
            ItemArticlesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(itemArticlesBinding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val articles = listArticles[position]
        holder.bind(articles)
    }

    override fun getItemCount(): Int {
        return listArticles.size
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    inner class ArticleViewHolder(private val binding: ItemArticlesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        fun bind(articles: ArticleEntity) {
            with(binding) {
                val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                val output = SimpleDateFormat("dd-MM-yyyy HH:mm")
                val date = format.parse(articles.created)
                val show = output.format(date)
                title.text = articles.title
                dateNews.text = show
                Glide.with(itemView.context).load(articles.images).apply(RequestOptions())
                    .error(R.drawable.placeholder).into(imageView)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listArticles[bindingAdapterPosition])
            }
        }
    }
}