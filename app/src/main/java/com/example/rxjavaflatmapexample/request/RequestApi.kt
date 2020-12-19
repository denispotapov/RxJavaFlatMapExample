package com.example.rxjavaflatmapexample.request

import com.example.rxjavaflatmapexample.models.Comment
import com.example.rxjavaflatmapexample.models.Post
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface RequestApi {

    @GET("posts")
    fun getPosts(): Observable<List<Post>>

    @GET("posts/{id}/comments")
    fun getComments(@Path("id") id: Int): Observable<List<Comment>>
}