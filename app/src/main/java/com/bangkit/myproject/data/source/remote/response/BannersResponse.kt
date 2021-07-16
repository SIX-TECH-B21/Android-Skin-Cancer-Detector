package com.bangkit.myproject.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class BannersResponse(

	@field:SerializedName("BannersResponse")
	val bannersResponse: List<BannersResponseItem>
)

data class BannersResponseItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("imageStorageName")
	val imageStorageName: Any? = null,

	@field:SerializedName("imageFile")
	val imageFile: Any? = null,

	@field:SerializedName("id")
	val id: String? = null
)
