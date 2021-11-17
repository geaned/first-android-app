package com.georgiyangeni.firstandroidapp.ui

import android.util.Log
import androidx.core.os.BuildCompat
import androidx.lifecycle.viewModelScope
import com.georgiyangeni.firstandroidapp.BuildConfig
import com.georgiyangeni.firstandroidapp.data.network.Api
import com.georgiyangeni.firstandroidapp.data.network.MockApi
import com.georgiyangeni.firstandroidapp.ui.base.BaseViewModel
import com.georgiyangeni.firstandroidapp.entity.User
import com.squareup.moshi.Moshi
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

class MainViewModel : BaseViewModel() {

    companion object {
        val LOG_TAG = "MyLogTag"
    }

    sealed class ViewState {
        object Loading : ViewState()
        data class Data(val userList: List<User>) : ViewState()
    }

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loading)
    val viewState: Flow<ViewState> get() = _viewState.asStateFlow()

    init {
        viewModelScope.launch {
            _viewState.emit(ViewState.Loading)
            val users = loadUsers()
            _viewState.emit(ViewState.Data(users))
        }
    }

    private suspend fun loadUsers(): List<User> {
        return withContext(Dispatchers.IO) {
            Timber.d("loadUsers()")
            // Thread.sleep(3000)  // for progress bar testing
            provideApi().getUsers().data
        }
    }

    private fun provideApi(): Api =
        if (BuildConfig.USE_MOCK_BACKEND_API) {
            MockApi()
        } else {
            Retrofit.Builder()
                .client(provideOkHttpClient())
                .baseUrl("https://reqres.in/api/")
                .addConverterFactory(MoshiConverterFactory.create(provideMoshi()))
                .build()
                .create(Api::class.java)
        }

//    private fun provideOkHttpClient(): OkHttpClient {
//        return OkHttpClient.Builder().build()
//    }

    private fun provideOkHttpClient(/*authRepository: AuthRepository*/): OkHttpClient =
        OkHttpClient
            .Builder()
//            .addInterceptor(AuthorizationInterceptor(AuthRepository(...)))
            .apply {
//                readTimeout(60, TimeUnit.SECONDS)
//                connectTimeout(60, TimeUnit.SECONDS)
//                addNetworkInterceptor(UserAgentInterceptor(userAgent))
//                addNetworkInterceptor(AuthorizationInterceptor(authRepository))
//                authenticator(FirstAndroidAppAuthenticator(authRepository))
                if (BuildConfig.DEBUG) {
                    addNetworkInterceptor(
                        HttpLoggingInterceptor { message ->
                            Timber.d(message)
                        }.setLevel(HttpLoggingInterceptor.Level.BODY)
                    )
//                    sslSocketFactory(
//                        clientCertificates.sslSocketFactory(),
//                        clientCertificates.trustManager
//                    )
                }
            }
            .build()

    private fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }
}