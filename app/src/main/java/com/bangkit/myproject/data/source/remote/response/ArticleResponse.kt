package com.bangkit.myproject.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ArticleResponse(

	@field:SerializedName("ArticleResponse")
	val articleResponse: List<ArticleResponseItem>
)

data class ArticleResponseItem(

	@field:SerializedName("images")
	val images: String,

	@field:SerializedName("created")
	val created: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("title")
	val title: String
)
