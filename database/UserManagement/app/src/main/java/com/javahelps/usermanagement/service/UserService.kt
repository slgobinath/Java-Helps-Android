package com.javahelps.usermanagement.service

import com.javahelps.usermanagement.service.Model.User
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

interface UserService {

    @POST("users")
    fun create(@Body user: User): Observable<User>

    @GET("users/{username}")
    fun read(@Path("username") username: String): Observable<User>

    @PUT("users/{username}")
    fun update(@Path("username") username: String, @Body user: User): Observable<User>

    @DELETE("users/{username}")
    fun delete(@Path("username") username: String): Observable<Response<Void>>
}