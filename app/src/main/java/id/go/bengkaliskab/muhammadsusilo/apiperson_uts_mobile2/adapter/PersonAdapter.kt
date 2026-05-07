package id.go.bengkaliskab.muhammadsusilo.apiperson_uts_mobile2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide

import id.go.bengkaliskab.muhammadsusilo.apiperson_uts_mobile2.databinding.PersonItemBinding
import id.go.bengkaliskab.muhammadsusilo.apiperson_uts_mobile2.model.DataItem

class PersonAdapter :
    ListAdapter<DataItem, PersonAdapter.MyViewHolder>(DIFF_CALLBACK) {

    // click callback
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: DataItem)
    }

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

    // bind data ke item
    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {

        val person = getItem(position)

        holder.bind(person)

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(person)
        }
    }

    // view holder
    class MyViewHolder(
        val binding: PersonItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(person: DataItem) {

            // nama
            binding.txtName.text =
                "${person.firstname} ${person.lastname}"

            // email
            binding.txtEmail.text = person.email

            // phone
            binding.txtPhone.text = person.phone

            // image
            Glide.with(itemView.context)
                .load(person.image)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .into(binding.imgPerson)
        }
    }

    companion object {

        val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<DataItem>() {

                override fun areItemsTheSame(
                    oldItem: DataItem,
                    newItem: DataItem
                ): Boolean {

                    return oldItem == newItem
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