package id.go.bengkaliskab.muhammadsusilo.apiperson_uts_mobile2.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.go.bengkaliskab.muhammadsusilo.apiperson_uts_mobile2.databinding.ActivityViewLocationBinding

class ViewLocationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewLocationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityViewLocationBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // ambil data intent
        val latitude = intent.getStringExtra("latitude") ?: "0"
        val longitude = intent.getStringExtra("longitude") ?: "0"

        // tampilkan data
        binding.tvLatitude.text = "Latitude : $latitude"
        binding.tvLongitude.text = "Longitude : $longitude"

        // tombol buka maps
        binding.btnOpenMaps.setOnClickListener {

            val gmmIntentUri =
                Uri.parse("geo:$latitude,$longitude?q=$latitude,$longitude")

            val mapIntent =
                Intent(Intent.ACTION_VIEW, gmmIntentUri)

            mapIntent.setPackage("com.google.android.apps.maps")

            startActivity(mapIntent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {

        finish()

        return true
    }
}