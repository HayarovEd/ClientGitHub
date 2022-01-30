package com.edurda77.ClientGitHub.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.ClientGitHub.databinding.ActivityMainBinding
import com.edurda77.ClientGitHub.model.UserModel
import com.edurda77.filmlibrary.ui.UsersAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val userOfGitHub = emptyList<UserModel>().toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setRecycledView()
    }
    private fun setRecycledView() {
        val recyclerView: RecyclerView = binding.usersRecyclerview
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val users=initNotes()
        val stateClickListener: UsersAdapter.OnStateClickListener =
            object : UsersAdapter.OnStateClickListener {
                override fun onStateClick(currentUser: UserModel, position: Int) {
                    /*Thread {

                        //val iDMovie = goIDMovie.getReposForIDMovieSync(movie)
                        runOnUiThread {
                            val intent = Intent(this@NotesActivity, NoteActivity::class.java)
                            intent.putExtra(NoteMovie::class.java.simpleName, note)

                            startActivity(intent)
                        }
                    }.start()*/
                }
            }
        recyclerView.adapter = UsersAdapter(users,stateClickListener)
    }
    private fun initNotes(): List<UserModel>{
        userOfGitHub.add(UserModel("HayarovEd"))
        userOfGitHub.add(UserModel("kshalnov"))
        userOfGitHub.add(UserModel("robertBadamshin"))
        return userOfGitHub
    }
}