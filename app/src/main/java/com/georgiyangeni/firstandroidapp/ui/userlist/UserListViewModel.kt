package com.georgiyangeni.firstandroidapp.ui.userlist

import androidx.lifecycle.viewModelScope
import com.georgiyangeni.firstandroidapp.BuildConfig
import com.georgiyangeni.firstandroidapp.data.network.Api
import com.georgiyangeni.firstandroidapp.data.network.MockApi
import com.georgiyangeni.firstandroidapp.ui.base.BaseViewModel
import com.georgiyangeni.firstandroidapp.entity.User
import com.georgiyangeni.firstandroidapp.interactor.UsersInteractor
import com.haroldadmin.cnradapter.NetworkResponse
import com.squareup.moshi.Moshi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val usersInteractor: UsersInteractor
) : BaseViewModel() {
    sealed class ViewState {
        object Loading : ViewState()
        data class Data(val userList: List<User>) : ViewState()
    }

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loading)
    val viewState: Flow<ViewState> get() = _viewState.asStateFlow()

    init {
        loadUsers()
    }

    private fun loadUsers() {
        viewModelScope.launch {
            _viewState.emit(ViewState.Loading)
            when (val response = usersInteractor.loadUsers()) {
                is NetworkResponse.Success -> {
                    _viewState.emit(ViewState.Data(response.body))
                }
                else -> {
                    // Something else...
                }
            }
        }
    }
}
