package com.example.aston_contactsfragment.fragments.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aston_contactsfragment.R
import com.example.aston_contactsfragment.models.User

class UsersAdapter(private val users: MutableList<User>, private val onItemClick: (User) -> Unit) :
    RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    fun updateUser(updatedUser: User) {
        val index = users.indexOfFirst { it.id == updatedUser.id }
        if (index != -1) {
            users[index] = updatedUser
            notifyItemChanged(index)
        }
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val fullNameContact: TextView = itemView.findViewById(R.id.fullNameContact)
        private val phoneNumber: TextView = itemView.findViewById(R.id.phoneNumber)
        private val photoContact: ImageView = itemView.findViewById(R.id.photoContact)

        fun bind(user: User) {
            fullNameContact.text = "${user.lastName} ${user.firstName}"
            phoneNumber.text = user.phoneNumber
            Glide.with(itemView)
                .load(user.photoUrl)
                .error(R.drawable.error)
                .into(photoContact)

            itemView.setOnClickListener {
                onItemClick(user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return users.size
    }
}

