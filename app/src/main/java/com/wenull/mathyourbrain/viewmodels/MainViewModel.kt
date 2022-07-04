package com.wenull.mathyourbrain.viewmodels;

import android.os.CountDownTimer
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.qrate.android.models.utils.DataState
import com.qrate.android.utils.Indicator
import com.wenull.mathyourbrain.data.local.entities.User

import com.wenull.mathyourbrain.data.prefrences.UserPreferences;
import com.wenull.mathyourbrain.models.requests.CreateGameRequest
import com.wenull.mathyourbrain.models.requests.SigninRequest
import com.wenull.mathyourbrain.models.requests.SignupRequest
import com.wenull.mathyourbrain.models.requests.UpdateGameRequest
import com.wenull.mathyourbrain.models.response.*
import com.wenull.mathyourbrain.repo.AppRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
        private val savedStateHandle: SavedStateHandle,
        private val preferences: UserPreferences,
        private val repository: AppRepository
) : ViewModel() {

        val users: LiveData<List<User>> = repository.users
        val accessToken:LiveData<String?> = preferences.accessToken.asLiveData()
        val debugToast= MutableLiveData<Indicator<String>>()
        val toast = MutableLiveData<Indicator<String>>()

        val isLoading: MutableState<Boolean> =mutableStateOf(false)

        fun saveUserDetails(token: String, user: User){
                viewModelScope.launch {
                        preferences.saveAccessToken(accessToken = token)
                        repository.saveUser(user)
                }
        }


        fun signup(signupRequest: SignupRequest, task: Task<SignupResponse?>){
                viewModelScope.launch {
                        repository.signUp(signupRequest).onEach {
                                invokeTask(it, task)
                        }.launchIn(viewModelScope)
                }
        }

        private fun <T>invokeTask(dataState: DataState<T>, task: Task<T>){
                when(dataState){
                        is DataState.Loading -> {
                                isLoading.value = true
                                task.whileLoading()
                        }
                        is DataState.Success -> {
                                isLoading.value = false
                                dataState.data?.let { it1 -> task.onSucess(it1) }
                        }
                        is DataState.Error -> {
                                isLoading.value = false
                                task.onError(dataState.error)
                        }
                }
        }


        fun signin(signinRequest: SigninRequest, task: Task<SigninResponse?>){
                viewModelScope.launch {
                        repository.signIn(signinRequest).onEach {
                                when(it){
                                        is DataState.Loading -> {
                                                isLoading.value = true
                                                task.whileLoading()
                                        }
                                        is DataState.Success -> {
                                                isLoading.value = false
                                                it.data?.let { it1 -> task.onSucess(it1) }
                                        }
                                        is DataState.Error -> {
                                                isLoading.value = false
                                                task.onError(it.error)
                                        }
                                }
                        }.launchIn(viewModelScope)
                }
        }


        fun getUser(user: User){
                viewModelScope.launch {
                        repository.getUser("Bearer ${ accessToken.value!! }", user.id).onEach {
                                when(it){
                                        is DataState.Loading -> {
                                                isLoading.value = true
                                        }
                                        is DataState.Success -> {
                                                isLoading.value = false

                                                it.data?.let { it1 -> repository.saveUser(it1.user) }

                                        }
                                        is DataState.Error -> {
                                                isLoading.value = false
                                        }
                                }
                        }.launchIn(viewModelScope)
                }
        }

        val leaderboardResponse = mutableStateOf<GetLeaderboardResponse?>(null)

        fun getLeaderboard(user: User){
                viewModelScope.launch {
                        repository.getLeaderboard("Bearer ${ accessToken.value!! }", user.id).onEach {
                                when(it){
                                        is DataState.Loading -> {
                                                isLoading.value = true
                                        }
                                        is DataState.Success -> {
                                                isLoading.value = false
                                                leaderboardResponse.value = it.data
                                        }
                                        is DataState.Error -> {
                                                isLoading.value = false
                                        }
                                }
                        }.launchIn(viewModelScope)
                }
        }



        val createGameResponse : MutableState<CreateGameResponse?> = mutableStateOf(null)

        fun createGame(user: User){
                viewModelScope.launch {
                        repository.createGame("Bearer ${ accessToken.value!! }", user.id, CreateGameRequest(user.rating, 20, 20, 200)).onEach {
                                when(it){
                                        is DataState.Loading -> {
                                                isLoading.value = true
                                        }
                                        is DataState.Success -> {
                                                isLoading.value = false
                                                createGameResponse.value = it.data
                                                startTimer(1000L*200)
                                        }
                                        is DataState.Error -> {
                                                isLoading.value = false
                                        }
                                }
                        }.launchIn(viewModelScope)
                }
        }

       fun updateGame( userId: String, gameId: String, correctQuestions: List<String>,completedTime: Double, scoredPoints: Int, resigned: Boolean, rating: Int,task: Task<UpdateGameResponse>,){
               val updateGameRequest = UpdateGameRequest(Math.round(completedTime).toInt(), correctQuestions, rating, resigned, scoredPoints)
               viewModelScope.launch {
                       repository.updateGame("Bearer ${ accessToken.value!! }", userId, gameId, updateGameRequest).onEach {
                               when(it){
                                       is DataState.Loading -> {
                                               isLoading.value = true
                                               task.whileLoading()
                                       }
                                       is DataState.Success -> {
                                               isLoading.value = false
                                               it.data?.let { it1 -> task.onSucess(it1) }
                                               if(users.value!=null) {
                                                       getUser(users.value!!.get(0))
                                               }
                                       }
                                       is DataState.Error -> {
                                               isLoading.value = false

                                       }
                               }
                       }.launchIn(viewModelScope)
               }
       }


        fun getAllAvatars(task: Task<GetAllAvatarsResponse>){
                viewModelScope.launch {
                        repository.getAllAvatars().onEach {
                                when(it){
                                        is DataState.Loading -> {
                                                isLoading.value = true
                                               task.whileLoading()
                                        }
                                        is DataState.Success -> {
                                                isLoading.value = false
                                                it.data?.let { it1 -> task.onSucess(it1) }
                                        }
                                        is DataState.Error -> {

                                                isLoading.value = false
                                        }
                                }
                        }.launchIn(viewModelScope)
                }
        }

        val currentTime= MutableLiveData<Indicator<Long>>()
        val value = mutableStateOf(1F)
        val totalTime = mutableStateOf(1L)
        lateinit var countDownTimer: CountDownTimer

        fun startTimer(_totalTime:Long){

                totalTime.value = _totalTime
                currentTime.value = Indicator(_totalTime)


                countDownTimer = object : CountDownTimer(totalTime.value, 1L) {
                        override fun onTick(millisUntilFinished: Long) {
                                currentTime.value = Indicator(millisUntilFinished)
                                value.value = currentTime.value!!.peekContent() / totalTime.value.toFloat()
                        }

                        override fun onFinish() {
                                currentTime.value = Indicator(0L)
                        }

                }
                countDownTimer.start()

        }

        fun stopTimer(){


                countDownTimer.cancel()

        }


}

interface Task<T>{
        fun whileLoading()
        fun onSucess(data: T)
        fun onError(error: String)
}


