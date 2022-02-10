package com.edurda77.filmlibrary.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.ClientGitHub.R
import com.edurda77.ClientGitHub.model.RepoGitHubModel
import com.edurda77.ClientGitHub.model.UserModel

class ReposHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_repo, parent, false)) {

    private var nameRepo: TextView? = null


    init {
        nameRepo = itemView.findViewById(R.id.repo)
    }

    @SuppressLint("SetTextI18n")
    fun bind(currentRepo: RepoGitHubModel) {
        nameRepo?.text = currentRepo.urlRepo
    }
}


