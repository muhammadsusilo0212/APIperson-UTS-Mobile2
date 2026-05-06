package id.go.bengkaliskab.muhammadsusilo.apiperson_uts_mobile2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

// adapter
import id.go.bengkaliskab.muhammadsusilo.apiperson_uts_mobile2.adapter.PersonAdapter

// binding
import id.go.bengkaliskab.muhammadsusilo.apiperson_uts_mobile2.databinding.FragmentPersonBinding

// model
import id.go.bengkaliskab.muhammadsusilo.apiperson_uts_mobile2.model.DataItem

// response
import id.go.bengkaliskab.muhammadsusilo.apiperson_uts_mobile2.response.PersonResponse

// retrofit
import id.go.bengkaliskab.muhammadsusilo.apiperson_uts_mobile2.retrofit.ApiConfig

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class fragment_person : Fragment() {

    private var _binding: FragmentPersonBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val TAG = "PersonFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPersonBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // layout recycler view
        val layoutManager = LinearLayoutManager(requireContext())

        binding.rvPerson.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(
            requireContext(),
            layoutManager.orientation
        )

        binding.rvPerson.addItemDecoration(itemDecoration)

        // ambil data API
        getPersonList()
    }

    // mengambil data retrofit
    private fun getPersonList() {

        showLoading(true)

        val client = ApiConfig
            .getApiService()
            .getListPersons()

        client.enqueue(object : Callback<PersonResponse> {

            override fun onResponse(
                call: Call<PersonResponse>,
                response: Response<PersonResponse>
            ) {

                showLoading(false)

                if (response.isSuccessful) {

                    val responseBody = response.body()

                    if (responseBody != null) {

                        setPersonData(responseBody.data)
                    }

                } else {

                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(
                call: Call<PersonResponse>,
                t: Throwable
            ) {

                showLoading(false)

                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    // set data ke recycler view
    private fun setPersonData(person: List<DataItem>) {

        val adapter = PersonAdapter()

        adapter.submitList(person)

        binding.rvPerson.adapter = adapter
    }

    // loading progressbar
    private fun showLoading(isLoading: Boolean) {

        if (isLoading) {

            binding.progressBar.visibility = View.VISIBLE

        } else {

            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}