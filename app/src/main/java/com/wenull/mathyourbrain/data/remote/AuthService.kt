package com.wenull.mathyourbrain.data.remote

import com.wenull.mathyourbrain.models.requests.SigninRequest
import com.wenull.mathyourbrain.models.requests.SignupRequest
import com.wenull.mathyourbrain.models.response.SigninResponse
import com.wenull.mathyourbrain.models.response.SignupResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthService {

    @Headers("Accept: application/json")
    @POST("/api/auth/signup")
    suspend fun signUp(@Body signUpRequest: SignupRequest): Response<SignupResponse>

    @Headers("Accept: application/json")
    @POST("/api/auth/signin")
    suspend fun signIn(@Body signInRequest: SigninRequest): Response<SigninResponse>

}