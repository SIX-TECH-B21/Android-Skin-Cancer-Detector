package com.bangkit.myproject.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetectionResponse(

	@field:SerializedName("detection")
	val detection: Detection,

	@field:SerializedName("responden")
	val responden: Responden,

	@field:SerializedName("created")
	val created: String,

	@field:SerializedName("id")
	val id: String
) : Parcelable

@Parcelize
data class HospitalsItem(

	@field:SerializedName("address")
	val address: String,

	@field:SerializedName("name")
	val name: String
) : Parcelable

@Parcelize
data class Responden(

	@field:SerializedName("sex")
	val sex: Boolean,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("age")
	val age: Int
) : Parcelable

@Parcelize
data class Detection(

	@field:SerializedName("symptoms")
	val symptoms: List<String>,

	@field:SerializedName("treatment")
	val treatment: List<String>,

	@field:SerializedName("images")
	val images: String,

	@field:SerializedName("doctors")
	val doctors: List<DoctorsItem>,

	@field:SerializedName("percentage")
	val percentage: String,

	@field:SerializedName("category")
	val category: String
) : Parcelable

@Parcelize
data class DoctorsItem(

	@field:SerializedName("hospitals")
	val hospitals: List<HospitalsItem>,

	@field:SerializedName("name")
	val name: String
) : Parcelable
