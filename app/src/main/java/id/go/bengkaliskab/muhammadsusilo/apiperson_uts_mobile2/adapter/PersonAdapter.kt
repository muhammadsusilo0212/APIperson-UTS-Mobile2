package id.go.bengkaliskab.muhammadsusilo.apiperson_uts_mobile2.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.go.bengkaliskab.muhammadsusilo.apiperson_uts_mobile2.helpers.Config

// glide image
import com.bumptech.glide.Glide

// binding
import id.go.bengkaliskab.muhammadsusilo.apiperson_uts_mobile2.databinding.PersonItemBinding

// model
import id.go.bengkaliskab.muhammadsusilo.apiperson_uts_mobile2.model.DataItem

class PersonAdapter :
    ListAdapter<DataItem, PersonAdapter.MyViewHolder>(DIFF_CALLBACK) {

    // membuat view holder
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {

        val binding = PersonItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return MyViewHolder(binding)
    }

    // menghubungkan data ke layout item
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val person = getItem(position)

        holder.bind(person)
    }

    // class view holder
    class MyViewHolder(
        val binding: PersonItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(person: DataItem) {

            // nama lengkap
            binding.txtName.text =
                "${person.firstname} ${person.lastname}"

            // email
            binding.txtEmail.text = person.email

            // phone
            binding.txtPhone.text = person.phone

            // url image dari API
            val url = person.image

            // cek url di logcat
            Log.d("IMAGE_URL", url)

            fun bind(person: DataItem) {

                // nama lengkap
                binding.txtName.text =
                    "${person.firstname} ${person.lastname}"

                // email
                binding.txtEmail.text = person.email

                // phone
                binding.txtPhone.text = person.phone

                // url image
                val url = Config.IMAGE_URL

                // load image dengan Glide
                    Glide.with(itemView.context)
                    .load(url)
                    .placeholder(android.R.drawable.ic_menu_gallery)
                    .error(android.R.drawable.stat_notify_error)
                    .centerCrop()
                    .into(binding.imgPerson)
            }
        }
    }

    companion object {

        val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<DataItem>() {

                override fun areItemsTheSame(
                    oldItem: DataItem,
                    newItem: DataItem
                ): Boolean {

                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: DataItem,
                    newItem: DataItem
                ): Boolean {

                    return oldItem == newItem
                }
            }
    }
}