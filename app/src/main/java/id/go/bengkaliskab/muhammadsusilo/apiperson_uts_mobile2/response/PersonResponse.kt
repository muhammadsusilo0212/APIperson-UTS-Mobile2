package id.go.bengkaliskab.muhammadsusilo.apiperson_uts_mobile2.response

import com.google.gson.annotations.SerializedName
import id.go.bengkaliskab.muhammadsusilo.apiperson_uts_mobile2.model.DataItem

data class PersonResponse(

	@field:SerializedName("total")
	val total: Int,

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("seed")
	val seed: Any,

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("locale")
	val locale: String,

	@field:SerializedName("status")
	val status: String
)


