package id.go.bengkaliskab.muhammadsusilo.apiperson_uts_mobile2.ui

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import id.go.bengkaliskab.muhammadsusilo.apiperson_uts_mobile2.R
import id.go.bengkaliskab.muhammadsusilo.apiperson_uts_mobile2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1, // 🔥 Person
            R.string.tab_text_2  // 🔥 Profile
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 🔥 aktifkan view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 🔥 pasang adapter ke ViewPager
        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter

        // 🔥 hubungkan TabLayout dengan ViewPager
        val tabs: TabLayout = binding.tabs
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        // 🔥 optional: hilangkan shadow actionbar
        supportActionBar?.elevation = 0f
    }
}