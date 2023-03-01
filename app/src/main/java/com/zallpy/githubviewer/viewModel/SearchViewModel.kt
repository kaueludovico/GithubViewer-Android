package com.zallpy.githubviewer.viewModel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zallpy.githubviewer.databinding.FragmentSearchBinding
import com.zallpy.githubviewer.model.User
import com.zallpy.githubviewer.network.EndPointPath
import com.zallpy.githubviewer.network.NetworkUtils
import com.zallpy.githubviewer.view.fragment.SearchFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel (
    private val fragment: SearchFragment,
    private val binding: FragmentSearchBinding) : ViewModel() {

    val liveDataUser = MutableLiveData<User>()

    fun getUser() {
        val retrofitBase = NetworkUtils.getRetrofitInstance()
        val endPointPath = retrofitBase.create(EndPointPath::class.java)
        val callback = endPointPath.getUsers(binding.editTextSearch.text.toString())

        callback.enqueue(object: Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(fragment.context, "Não funciona", Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<User>, response: Response<User>) {
                response.body()?.let {
                    liveDataUser.value = it
                }
            }
        })
    }

    class SearchViewModelProvider (
        private val fragment: SearchFragment,
        private val binding: FragmentSearchBinding
        ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SearchViewModel(fragment, binding) as T
        }
    }
}