
package com.edurda77.ClientGitHub.model

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edurda77.ClientGitHub.model.Utility.isInternetAvailable

class UserGitHubViewModel(
    private val context: Context,

) : ViewModel() {
    private var listData = MutableLiveData<List<RepoGitHubModel>>()


    fun getData(userName: String) : MutableLiveData<List<RepoGitHubModel>>{
        val retrofitGitHubUseCaseImpl=RetrofitGitHubUseCaseImpl()
        if(context.isInternetAvailable()) {
            listData = retrofitGitHubUseCaseImpl.getMutableLiveData(context,userName)
        }
        return listData
    }

}
