package id.go.bengkaliskab.muhammadsusilo.apiperson_uts_mobile2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import id.go.bengkaliskab.muhammadsusilo.apiperson_uts_mobile2.databinding.ActivityDetailPersonBinding

class DetailPersonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPersonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailPersonBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // intent
        val firstname = intent.getStringExtra("firstname")
        val lastname = intent.getStringExtra("lastname")
        val email = intent.getStringExtra("email")
        val phone = intent.getStringExtra("phone")
        val image = intent.getStringExtra("image")
        val street = intent.getStringExtra("street")
        val city = intent.getStringExtra("city")
        val country = intent.getStringExtra("country")
        val zipcode = intent.getStringExtra("zipcode")
        // latitude longitude
        val latitude = intent.getStringExtra("latitude")
        val longitude = intent.getStringExtra("longitude")
        // set text
        binding.tvName.text =
            "$firstname $lastname"

        binding.tvEmail.text = email

        binding.tvPhone.text = phone

        binding.tvStreet.text =
            "Street : $street"

        binding.tvCity.text =
            "City : $city"

        binding.tvCountry.text =
            "Country : $country"

        binding.tvZipcode.text =
            "Zipcode : $zipcode"
        // image
        Glide.with(this)
            .load(image)
            .centerCrop()
            .into(binding.imgPerson)

        // button lokasi
        binding.btnViewLocation.setOnClickListener {

            val intent =
                Intent(this, ViewLocationActivity::class.java)

            intent.putExtra(
                "latitude",
                latitude
            )

            intent.putExtra(
                "longitude",
                longitude
            )

            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {

        finish()

        return true
    }
}