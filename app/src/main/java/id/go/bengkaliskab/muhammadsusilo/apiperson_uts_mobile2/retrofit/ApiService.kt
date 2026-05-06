package id.go.bengkaliskab.muhammadsusilo.apiperson_uts_mobile2.retrofit

import id.go.bengkaliskab.muhammadsusilo.apiperson_uts_mobile2.response.PersonResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    // mengambil 10 data person Indonesia gender male
    @GET("persons?_quantity=10&_locale=id_ID&_gender=male")

    fun getListPersons(): Call<PersonResponse>

}