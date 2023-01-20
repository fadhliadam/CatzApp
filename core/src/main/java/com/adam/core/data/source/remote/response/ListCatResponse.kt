package com.adam.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListCatResponseItem(

	@field:SerializedName("width")
	val width: Int,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("breeds")
	val breeds: List<BreedsResponseItem>,

	@field:SerializedName("height")
	val height: Int,
)