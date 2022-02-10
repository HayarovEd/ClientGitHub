package com.edurda77.filmlibrary.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.ClientGitHub.model.RepoGitHubModel
import com.edurda77.ClientGitHub.model.UserModel

class ReposAdapter (private val list: List<RepoGitHubModel>,
                    private val onClickListener: OnStateClickListener) :
    RecyclerView.Adapter<ReposHolder>()
{
    interface OnStateClickListener {
        fun onStateClick(currentRepo: RepoGitHubModel, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ReposHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ReposHolder, position: Int) {


        val currentRepo: RepoGitHubModel = list[position]
        holder.bind(currentRepo)

        holder.itemView.setOnClickListener {
            onClickListener.onStateClick(currentRepo, position)
        }
    }

    override fun getItemCount(): Int = list.size
}