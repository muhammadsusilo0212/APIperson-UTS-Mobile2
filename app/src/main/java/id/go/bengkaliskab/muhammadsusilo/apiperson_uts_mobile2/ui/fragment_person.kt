package id.go.bengkaliskab.muhammadsusilo.apiperson_uts_mobile2.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import id.go.bengkaliskab.muhammadsusilo.apiperson_uts_mobile2.DetailPersonActivity

import id.go.bengkaliskab.muhammadsusilo.apiperson_uts_mobile2.adapter.PersonAdapter
import id.go.bengkaliskab.muhammadsusilo.apiperson_uts_mobile2.databinding.FragmentPersonBinding
import id.go.bengkaliskab.muhammadsusilo.apiperson_uts_mobile2.model.DataItem
import id.go.bengkaliskab.muhammadsusilo.apiperson_uts_mobile2.response.PersonResponse
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

        _binding = FragmentPersonBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        // swipe refresh
        binding.swipeRefresh.setOnRefreshListener {
            getPersonList()
        }

        // recycler view
        val layoutManager =
            LinearLayoutManager(requireContext())

        binding.rvPerson.layoutManager =
            layoutManager

        val itemDecoration =
            DividerItemDecoration(
                requireContext(),
                layoutManager.orientation
            )

        binding.rvPerson.addItemDecoration(
            itemDecoration
        )

        // ambil data API
        getPersonList()
    }

    // retrofit API
    private fun getPersonList() {

        showLoading(true)

        val client =
            ApiConfig
                .getApiService()
                .getListPersons()

        client.enqueue(
            object : Callback<PersonResponse> {

                override fun onResponse(
                    call: Call<PersonResponse>,
                    response: Response<PersonResponse>
                ) {

                    showLoading(false)

                    if (response.isSuccessful) {

                        val responseBody =
                            response.body()

                        if (responseBody != null) {

                            setPersonData(
                                responseBody.data
                            )
                        }

                    } else {

                        Log.e(
                            TAG,
                            "onFailure: ${response.message()}"
                        )
                    }
                }

                override fun onFailure(
                    call: Call<PersonResponse>,
                    t: Throwable
                ) {

                    showLoading(false)

                    Log.e(
                        TAG,
                        "onFailure: ${t.message}"
                    )
                }
            }
        )
    }

    // set data recycler view
    private fun setPersonData(
        person: List<DataItem>
    ) {

        val adapter = PersonAdapter()

        adapter.submitList(person)

        binding.rvPerson.adapter = adapter

        adapter.setOnItemClickCallback(
            object : PersonAdapter.OnItemClickCallback {

                override fun onItemClicked(
                    data: DataItem
                ) {

                    val moveIntent =
                        Intent(
                            requireActivity(),
                            DetailPersonActivity::class.java
                        )

                    // person
                    moveIntent.putExtra(
                        "firstname",
                        data.firstname
                    )

                    moveIntent.putExtra(
                        "lastname",
                        data.lastname
                    )

                    moveIntent.putExtra(
                        "email",
                        data.email
                    )

                    moveIntent.putExtra(
                        "phone",
                        data.phone
                    )

                    moveIntent.putExtra(
                        "image",
                        data.image
                    )

                    // address
                    moveIntent.putExtra(
                        "street",
                        data.address?.street ?: "-"
                    )

                    moveIntent.putExtra(
                        "city",
                        data.address?.city ?: "-"
                    )

                    moveIntent.putExtra(
                        "country",
                        data.address?.country ?: "-"
                    )

                    moveIntent.putExtra(
                        "zipcode",
                        data.address?.zipcode ?: "-"
                    )

                    // latitude longitude
                    moveIntent.putExtra(
                        "latitude",
                        data.address?.latitude.toString()
                    )

                    moveIntent.putExtra(
                        "longitude",
                        data.address?.longitude.toString()
                    )
                    moveIntent.putExtra(
                        "gender",
                        data.gender
                    )

                    startActivity(moveIntent)
                }
            }
        )
    }

    // loading
    private fun showLoading(
        isLoading: Boolean
    ) {

        if (!binding.swipeRefresh.isRefreshing) {

            binding.progressBar.visibility =
                if (isLoading)
                    View.VISIBLE
                else
                    View.GONE
        }

        if (!isLoading) {

            binding.swipeRefresh.isRefreshing =
                false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}