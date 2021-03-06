package com.edurda77.ClientGitHub.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.ClientGitHub.databinding.ActivityMainBinding
import com.edurda77.ClientGitHub.model.UserModel
import com.edurda77.filmlibrary.ui.UsersAdapter
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val users: List<UserModel> by inject()
    //private val users: List<UserModel> by lazy { app.getUsers() }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //app.di.inject(this)
        setRecycledView()
    }
    private fun setRecycledView() {
        val recyclerView: RecyclerView = binding.usersRecyclerview
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val stateClickListener: UsersAdapter.OnStateClickListener =
            object : UsersAdapter.OnStateClickListener {
                override fun onStateClick(currentUser: UserModel, position: Int) {
                    Thread {

                        runOnUiThread {
                            val intent = Intent(this@MainActivity, ReposGitHubActivity::class.java)
                            intent.putExtra(UserModel::class.java.simpleName, currentUser)
                            startActivity(intent)
                        }
                    }.start()
                }
            }
        recyclerView.adapter = UsersAdapter(users,stateClickListener)
    }

}