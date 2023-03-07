package com.example.jpmc.nycschools.schools

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jpmc.nycschools.R
import com.example.jpmc.nycschools.data.Images
import com.example.jpmc.nycschools.data.School

class SchoolsListAdapter(
    private var onItemClicked: ((school: School) -> Unit)
) : RecyclerView.Adapter<ItemViewHolder>(){
    private val listOfSchools: MutableList<School> = mutableListOf()
    private val listOfImages = Images.listOfImages

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.school_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listOfSchools.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(listOfSchools[position], listOfImages, onItemClicked)
    }

    fun setItems(list: List<School>) {
        listOfSchools.addAll(list)
        notifyDataSetChanged()
    }
}

class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(school: School, listOfImages: List<Int>, onItemClicked: (school: School) -> Unit) {
        view.findViewById<TextView>(R.id.name).text = school.name
        view.findViewById<TextView>(R.id.phone_number).text = school.phoneNumber
        view.findViewById<TextView>(R.id.location).text = school.city
        view.findViewById<TextView>(R.id.email).text = if (school.email.isNullOrEmpty()) {
            view.context.getString(R.string.no_email)
        } else {
            school.email
        }
        view.setOnClickListener {
            onItemClicked(school)
        }
        val img = listOfImages.random()
        val imgView = view.findViewById<ImageView>(R.id.icon)
        Glide.with(view)
            .load(img)
            .circleCrop()
            .into(imgView)
    }
}