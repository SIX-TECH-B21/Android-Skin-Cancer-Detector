package com.bangkit.myproject.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetectionResponseStatus(

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("status")
	val status: Int,

	@field:SerializedName("detectionId")
	val detectionId: String
)
