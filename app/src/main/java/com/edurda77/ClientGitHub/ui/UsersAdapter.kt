package com.edurda77.filmlibrary.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.ClientGitHub.ui.model.UserModel

class UsersAdapter (private val list: List<UserModel>,
                   private val onClickListener: OnStateClickListener) :
    RecyclerView.Adapter<UsersHolder>()
{
    interface OnStateClickListener {
        fun onStateClick(currentUser: UserModel, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersHolder {
        val inflater = LayoutInflater.from(parent.context)
        return UsersHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: UsersHolder, position: Int) {


        val currentUser: UserModel = list[position]
        holder.bind(currentUser)

        holder.itemView.setOnClickListener {
            onClickListener.onStateClick(currentUser, position)
        }
    }

    override fun getItemCount(): Int = list.size
}