package com.wenull.mathyourbrain.data.remote


import com.wenull.mathyourbrain.models.requests.CreateGameRequest
import com.wenull.mathyourbrain.models.requests.UpdateGameRequest
import com.wenull.mathyourbrain.models.response.*
import retrofit2.Response
import retrofit2.http.*

interface AppService {



    @Headers("Accept: application/json")
    @GET("/api/misc/avatars/get")
    suspend fun getAllAvatars(): Response<GetAllAvatarsResponse>

    @Headers("Accept: application/json")
    @GET("/api/user/get/{userId}")
    suspend fun getUser(
        @Header("Authorization") accessToken: String,
        @Path("userId") userId: String): Response<GetUserDataResponse>


    @Headers("Accept: application/json")
    @POST("/api/game/create/{userId}")
    suspend fun createGame(
        @Header("Authorization") accessToken: String,
        @Path("userId") userId: String,
    @Body createGameRequest: CreateGameRequest): Response<CreateGameResponse>


    @Headers("Accept: application/json")
    @POST("/api/game/update/{userId}/{gameId}")
    suspend fun updateGame(
        @Header("Authorization") accessToken: String,
        @Path("userId") userId: String,
        @Path("gameId") gameId: String,
    @Body updateGameRequest: UpdateGameRequest): Response<UpdateGameResponse>

    @Headers("Accept: application/json")
    @GET("/api/user/leaderboard/{userId}")
    suspend fun getLeaderboard(
        @Header("Authorization") accessToken: String,
        @Path("userId") userId: String): Response<GetLeaderboardResponse>



}