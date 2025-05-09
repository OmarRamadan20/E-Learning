package com.example.data.api

import android.util.Log
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            Log.e("NetworkModule", message)
        }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }


    @Provides
    fun provideOkHttp(loggingInterceptor: HttpLoggingInterceptor)
    :OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }
    @Provides
    fun provideGson():GsonConverterFactory{
        return GsonConverterFactory.create()
    }
    @Provides
    fun provideRetrofit(okhttp:OkHttpClient,
                        gson:GsonConverterFactory):Retrofit{
        return Retrofit.Builder()
            .client(okhttp)
            .addConverterFactory(gson)
            .baseUrl("https://mansa2-7vcs6i13.b4a.run/api/v1/" )
            .build()

    }
    @Provides
    fun provideWebServices(retrofit:Retrofit): WebServices {
        return retrofit.create(WebServices::class.java)
    }

    @Provides
    fun provideGsson(): Gson = Gson()

}