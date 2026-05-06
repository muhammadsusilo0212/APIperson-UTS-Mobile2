package id.go.bengkaliskab.muhammadsusilo.apiperson_uts_mobile2.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import id.go.bengkaliskab.muhammadsusilo.apiperson_uts_mobile2.fragment_person
import id.go.bengkaliskab.muhammadsusilo.apiperson_uts_mobile2.fragment_profile

class SectionsPagerAdapter(activity: AppCompatActivity) :
    FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {

        var fragment: Fragment? = null

        when (position) {

            0 -> fragment = fragment_person()   // 🔥 DIUBAH dari HomeFragment

            1 -> fragment = fragment_profile()  // tetap sama
        }

        return fragment as Fragment
    }

    override fun getItemCount(): Int {
        return 2   // 🔥 jumlah tab
    }
}