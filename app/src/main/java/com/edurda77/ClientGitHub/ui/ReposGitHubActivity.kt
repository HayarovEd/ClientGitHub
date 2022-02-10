package com.edurda77.ClientGitHub.ui

import android.os.Bundle
import android.widget.Toast
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
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers


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
            val profile = gitHubRepoUseCase.getReposObservable(user.user)
            profile.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map { it }
                .subscribeBy(
                    onError = {
                        Toast.makeText(this, "Ошибка $it", Toast.LENGTH_SHORT).show()
                    },
                    onNext = {
                        it.forEach {
                            userProfile.text = it.owner.login
                            reposOfUser.add(it)
                            Glide.with(this)
                                .load(it.owner.avatar)
                                .placeholder(R.drawable.ic_no_avatar)
                                .into(userAvatar)
                        }
                    },
                    onComplete = {
                        Toast.makeText(this, "Все загружено", Toast.LENGTH_SHORT).show()
                    }
                )


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
        recyclerView.adapter = ReposAdapter(reposOfUser, stateClickListener)
    }
}