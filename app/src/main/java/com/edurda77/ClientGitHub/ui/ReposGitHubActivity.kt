package com.edurda77.ClientGitHub.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edurda77.ClientGitHub.R
import com.edurda77.ClientGitHub.databinding.ActivityReposGitHubBinding
import com.edurda77.ClientGitHub.domain.GitHubRepoUseCase
import com.edurda77.ClientGitHub.model.RepoGitHubModel
import com.edurda77.ClientGitHub.model.UserModel
import com.edurda77.filmlibrary.ui.ReposAdapter


class ReposGitHubActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReposGitHubBinding
    private val gitHubRepoUseCase: GitHubRepoUseCase by lazy { app.gitHubRepoUseCase }
    private val reposOfUser = emptyList<RepoGitHubModel>().toMutableList()

    //val recyclerView = binding.reposRecyclerview

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityReposGitHubBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val userProfile = binding.userRepo
        val userAvatar = binding.userAvatar
        val arguments = intent.extras
        val user: UserModel

        if (arguments != null) {
            user = arguments.getSerializable(UserModel::class.java.simpleName) as UserModel
            Thread {
                val profile = gitHubRepoUseCase.getReposForGitHub(user.user)
                runOnUiThread {
                    profile?.forEach {
                        userProfile.text = it.owner.login
                        reposOfUser.add(it)
                        Glide.with(this)
                            .load(it.owner.avatar)
                            .placeholder(R.drawable.ic_no_avatar)
                            .into(userAvatar)
                    }
                }
            }.start()

        }
        setRecycledView()
    }
    private fun setRecycledView() {
        val recyclerView: RecyclerView = binding.reposRecyclerview
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val stateClickListener: ReposAdapter.OnStateClickListener =
            object : ReposAdapter.OnStateClickListener {
                override fun onStateClick(currentRepo: RepoGitHubModel, position: Int) {
                  /*  val intent = Intent(Intent.ACTION_VIEW)
                    val url = currentRepo.urlRepo
                    val uri = Uri.parse(url)
                    intent.data = uri
                    startActivity(this, intent)*/
                }
            }
        recyclerView.adapter = ReposAdapter(reposOfUser,stateClickListener)
    }
}