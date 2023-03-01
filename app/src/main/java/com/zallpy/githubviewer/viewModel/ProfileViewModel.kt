package com.zallpy.githubviewer.viewModel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zallpy.githubviewer.model.Repository
import com.zallpy.githubviewer.network.EndPointPath
import com.zallpy.githubviewer.network.NetworkUtils
import com.zallpy.githubviewer.view.fragment.ProfileFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel(private val fragment: ProfileFragment) : ViewModel() {

    var liveDataRepository = MutableLiveData<List<Repository>>()

    fun getRepositories() {
        val retrofitBase = NetworkUtils.getRetrofitInstance()
        val endPointPath = retrofitBase.create(EndPointPath::class.java)
        val callback = endPointPath.getRepos(fragment.args.user.login)

        callback.enqueue(object : Callback<List<Repository>> {

            override fun onResponse(
                call: Call<List<Repository>>,
                response: Response<List<Repository>>
            ) {
                response.body()?.let {
                    liveDataRepository.value = it
                }
            }

            override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
                Toast.makeText(fragment.context, "Não funciona", Toast.LENGTH_LONG).show()
            }
        })
    }

    class ProfileViewModelProvider(
        private val fragment: ProfileFragment
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ProfileViewModel(fragment) as T
        }
    }
}