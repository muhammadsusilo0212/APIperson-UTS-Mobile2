package id.go.bengkaliskab.muhammadsusilo.apiperson_uts_mobile2.model

import com.google.gson.annotations.SerializedName

data class DataItem(

    @field:SerializedName("birthday")
    val birthday: String,

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("firstname")
    val firstname: String,

    @field:SerializedName("website")
    val website: String,

    // nullable agar tidak crash
    @field:SerializedName("address")
    val address: Address?,

    @field:SerializedName("gender")
    val gender: String,

    @field:SerializedName("phone")
    val phone: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("lastname")
    val lastname: String
)

data class Address(

    @field:SerializedName("zipcode")
    val zipcode: String?,

    @field:SerializedName("country")
    val country: String?,

    @field:SerializedName("country_code")
    val countryCode: String?,

    @field:SerializedName("streetName")
    val streetName: String?,

    @field:SerializedName("city")
    val city: String?,

    @field:SerializedName("street")
    val street: String?,

    @field:SerializedName("latitude")
    val latitude: Any?,

    @field:SerializedName("buildingNumber")
    val buildingNumber: String?,

    @field:SerializedName("id")
    val id: Int?,

    @field:SerializedName("longitude")
    val longitude: Any?
)