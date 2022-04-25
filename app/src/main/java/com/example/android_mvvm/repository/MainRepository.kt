package com.example.android_mvvm.repository

import android.telecom.Call
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android_mvvm.model.Post
import com.example.android_mvvm.network.RetrofitHttp
import retrofit2.Callback
import retrofit2.Response

object MainRepository {

    fun apiPostList(): LiveData<ArrayList<Post>> {
        val allPosts = MutableLiveData<ArrayList<Post>>()
        RetrofitHttp.postService.listPost().enqueue(object : Callback<ArrayList<Post>> {
            override fun onResponse(call: retrofit2.Call<ArrayList<Post>>, response: Response<ArrayList<Post>>) {
                allPosts.value = response.body()
            }

            override fun onFailure(call: retrofit2.Call<ArrayList<Post>>, t: Throwable) {
                allPosts.value = null
            }
        })
        return allPosts
    }

    fun apiPostDelete(post: Post): LiveData<Post> {
        val deletedPost = MutableLiveData<Post>()
        RetrofitHttp.postService.deletePost(post.id).enqueue(object : Callback<Post> {
            override fun onResponse(call: retrofit2.Call<Post>, response: Response<Post>) {
                deletedPost.value = response.body()
            }

            override fun onFailure(call: retrofit2.Call<Post>, t: Throwable) {
                deletedPost.value = null
            }
        })
        return deletedPost
    }

}