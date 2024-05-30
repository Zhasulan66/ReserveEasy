package com.example.reserveeasy.presentation.viewmodel

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reserveeasy.domain.model.LoginResponse
import com.example.reserveeasy.domain.model.Resource
import com.example.reserveeasy.domain.model.Restaurant
import com.example.reserveeasy.domain.model.RestaurantResponse
import com.example.reserveeasy.domain.model.RestaurantsResponse
import com.example.reserveeasy.domain.model.User
import com.example.reserveeasy.domain.model.UserRequest
import com.example.reserveeasy.domain.repository.ReserveEasyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

val Context.dataStore by preferencesDataStore(name = "user_prefs")

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ReserveEasyRepository,
    private val application: Application
) : ViewModel() {

    private val userKey = stringPreferencesKey("user_key")

    // Function to save userId
    fun saveUserId(userId: String) {
        viewModelScope.launch {

            application.dataStore.edit { preferences ->
                preferences[userKey] = userId
            }
        }
    }

    // Function to retrieve userId
    fun readUserId(): Flow<String?> {
        return application.dataStore.data.map { preferences ->
            preferences[userKey]
        }
    }

    // Function to delete userId
    fun deleteUserId() {
        viewModelScope.launch {
            application.dataStore.edit { preferences ->
                preferences.remove(userKey)
            }
        }
    }

    private val _registrationState = MutableStateFlow<Resource<User>>(Resource.Initial)
    val registrationState: StateFlow<Resource<User>> = _registrationState.asStateFlow()

    fun registerUser(userRequest: UserRequest) {
        viewModelScope.launch {
            _registrationState.value = Resource.Loading
            try {
                val response = repository.registerUser(userRequest)
                _registrationState.value = Resource.Success(response)
            } catch (e: Exception) {
                _registrationState.value = Resource.Error(e)
            }
        }
    }

    fun registrationSuccess() {
        // This function called by the UI layer to signal a successful registration
        _registrationState.value = Resource.Initial
    }

    private val _loginState = MutableStateFlow<Resource<LoginResponse>>(Resource.Initial)
    val loginState: StateFlow<Resource<LoginResponse>> = _loginState.asStateFlow()

    fun loginUser(userRequest: UserRequest) {
        viewModelScope.launch {
            _loginState.value = Resource.Loading
            try {
                val loginResponse = repository.loginUser(userRequest)
                _loginState.value = Resource.Success(loginResponse)
            } catch (e: Exception) {
                _loginState.value = Resource.Error(e)
            }
        }
    }

    fun loginSuccess() {
        _loginState.value = Resource.Initial
    }

    //restaurant state
    private val _restaurantsState = MutableStateFlow<Resource<RestaurantsResponse>>(Resource.Initial)
    val restaurantsState: StateFlow<Resource<RestaurantsResponse>> = _restaurantsState.asStateFlow()

    fun fetchAllRestaurants() {
        viewModelScope.launch {
            _restaurantsState.value = Resource.Loading
            try {
                val response = repository.getAllRestaurants()
                _restaurantsState.value = Resource.Success(response)
            } catch (e: Exception) {
                _restaurantsState.value = Resource.Error(e)
            }
        }
    }

    //restaurant info state
    private val _restaurantInfoState = MutableStateFlow<Resource<RestaurantResponse>>(Resource.Initial)
    val restaurantInfoState: StateFlow<Resource<RestaurantResponse>> = _restaurantInfoState.asStateFlow()

    fun fetchRestaurantById(id: String) {
        viewModelScope.launch {
            _restaurantInfoState.value = Resource.Loading
            try {
                val response = repository.getRestaurantById(id)
                _restaurantInfoState.value = Resource.Success(response)
            } catch (e: Exception) {
                _restaurantInfoState.value = Resource.Error(e)
            }
        }
    }




}