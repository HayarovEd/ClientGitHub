package com.edurda77.ClientGitHub.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edurda77.ClientGitHub.R
import com.edurda77.ClientGitHub.databinding.ActivityReposGitHubBinding
import com.edurda77.ClientGitHub.domain.GitHubRepoUseCase
import com.edurda77.ClientGitHub.model.RepoGitHubModel
import com.edurda77.ClientGitHub.model.UserGitHubViewModel
import com.edurda77.ClientGitHub.model.UserModel
import com.edurda77.filmlibrary.ui.ReposAdapter
import com.edurda77.filmlibrary.ui.UsersAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers


class ReposGitHubActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReposGitHubBinding
    private val gitHubRepoUseCase: GitHubRepoUseCase by lazy { app.gitHubRepoUseCase }
    private val reposOfUser = emptyList<RepoGitHubModel>().toMutableList()
    private lateinit var adapter: ReposAdapter
    //val recyclerView = binding.reposRecyclerview

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityReposGitHubBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //val userProfile = binding.userRepo
        //val userAvatar = binding.userAvatar
        val arguments = intent.extras
        val user: UserModel

        if (arguments != null) {
            user = arguments.getSerializable(UserModel::class.java.simpleName) as UserModel
            setRecycledView(user)
        //val profile = gitHubRepoUseCase.getReposObservable(user.user)

        /*val profile = observable(user.user)
            profile.subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.computation())
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
*/

        }

    }

    private fun setRecycledView(user:UserModel) {
        val recyclerView: RecyclerView = binding.reposRecyclerview
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val stateClickListener: ReposAdapter.OnStateClickListener =
            object : ReposAdapter.OnStateClickListener {
                override fun onStateClick(currentRepo: RepoGitHubModel, position: Int) {

                }
            }
        adapter = ReposAdapter(reposOfUser, stateClickListener)
        recyclerView.adapter=adapter
        val userProfile = binding.userRepo
        val userAvatar = binding.userAvatar
        val userViewModel = UserGitHubViewModel(this)
        userViewModel.getData(user.user).observe(this
        ) { t ->
            reposOfUser.clear()
            t?.let {
                reposOfUser.addAll(it)
                 it.forEach {
                     userProfile.text = it.owner.login
                     Glide.with(this)
                         .load(it.owner.avatar)
                         .placeholder(R.drawable.ic_no_avatar)
                         .into(userAvatar)                }
            }
            adapter.notifyDataSetChanged()

        }
        //recyclerView.adapter = ReposAdapter(reposOfUser, stateClickListener)
    }
    private fun observable (user: String) = Observable.create<List<RepoGitHubModel>> { it
        it.onNext(gitHubRepoUseCase.getReposForGitHub(user))
    }
}