package com.example.rxjavaflatmapexample.request

import com.example.rxjavaflatmapexample.models.Post
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface RequestApi {

    @GET("posts")
    fun getPosts(): Observable<List<Post>>

    @GET("posts/{id}")
    fun getPost(@Path("id") id: Int): Observable<Post>
}