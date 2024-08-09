package com.dicoding.suittestapp.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.dicoding.suittestapp.databinding.ItemUserBinding
import com.dicoding.suittestapp.model.User


class UserAdapter(private val onUserSelected: (User) -> Unit) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val users = mutableListOf<User>()

    fun addUsers(newUsers: List<User>) {
        users.addAll(newUsers)
        notifyDataSetChanged()
    }

    fun clearUsers() {
        users.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.fullNameTextView.text = "${user.first_name} ${user.last_name}"
            binding.emailTextView.text = user.email
            Glide.with(binding.avatarImageView.context)
                .load(user.avatar)
                .into(binding.avatarImageView)

            itemView.setOnClickListener {
                onUserSelected(user)
            }
        }
    }
}