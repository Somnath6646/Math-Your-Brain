package com.wenull.mathyourbrain.repo

import com.wenull.mathyourbrain.data.local.AppDAO
import com.wenull.mathyourbrain.data.local.entities.User
import com.wenull.mathyourbrain.data.remote.AppService
import com.wenull.mathyourbrain.data.remote.AuthService
import com.wenull.mathyourbrain.models.requests.CreateGameRequest
import com.wenull.mathyourbrain.models.requests.SigninRequest
import com.wenull.mathyourbrain.models.requests.SignupRequest
import com.wenull.mathyourbrain.models.requests.UpdateGameRequest
import com.wenull.mathyourbrain.repo.base.BaseRepository
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Path

class AppRepository(private val authService: AuthService, private val appService: AppService, private val dao: AppDAO) : BaseRepository() {

    val users = dao.getUserFromDB()

    suspend fun signUp(@Body signUpRequest: SignupRequest) = safeApiCall {
        authService.signUp(signUpRequest)
    }
    suspend fun signIn(@Body signInRequest: SigninRequest) = safeApiCall {
        authService.signIn(signInRequest)
    }


    suspend fun saveUser(user: User){
        dao.insertUser(user)
    }

    suspend fun getUser(@Header("Authorization") accessToken: String,
                        @Path("userId") userId: String) = safeApiCall {
                            appService.getUser(accessToken, userId)
    }

    suspend fun getLeaderboard(@Header("Authorization") accessToken: String,
                        @Path("userId") userId: String) = safeApiCall {
                            appService.getLeaderboard(accessToken, userId)
    }

    suspend fun createGame(@Header("Authorization") accessToken: String,
                       @Path("userId") userId: String,
    createGameRequest: CreateGameRequest) = safeApiCall {
        appService.createGame(accessToken, userId, createGameRequest)
    }
    suspend fun updateGame( @Header("Authorization") accessToken: String,
                            @Path("userId") userId: String,
                            @Path("gameId") gameId: String,
                            @Body updateGameRequest: UpdateGameRequest
    ) = safeApiCall {
        appService.updateGame(accessToken, userId, gameId, updateGameRequest)
    }


    suspend fun getAllAvatars() = safeApiCall {
        appService.getAllAvatars()
    }

    suspend fun deleteAllUser(){
        dao.deleteAll()
    }

}