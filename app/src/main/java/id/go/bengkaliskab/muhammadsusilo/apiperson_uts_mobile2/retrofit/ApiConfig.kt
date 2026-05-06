package id.go.bengkaliskab.muhammadsusilo.apiperson_uts_mobile2.retrofit

// import Config
import id.go.bengkaliskab.muhammadsusilo.apiperson_uts_mobile2.helpers.Config

// okhttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

// retrofit
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {

    companion object {

        fun getApiService(): ApiService {

            // menampilkan log response API di logcat
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.BODY
                )

            // client okhttp
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            // retrofit builder
            val retrofit = Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            // return api service
            return retrofit.create(ApiService::class.java)
        }
    }
}