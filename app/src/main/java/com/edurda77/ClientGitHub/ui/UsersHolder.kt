package com.edurda77.filmlibrary.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.ClientGitHub.R
import com.edurda77.ClientGitHub.ui.model.UserModel

class UsersHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_user, parent, false)) {

    private var nameUser: TextView? = null

    init {
        nameUser = itemView.findViewById(R.id.user_view)
    }

    @SuppressLint("SetTextI18n")
    fun bind(currentUser: UserModel) {
        nameUser?.text = currentUser.user
    }
}


