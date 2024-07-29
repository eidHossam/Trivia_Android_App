package com.trivia.di

import com.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module //This tells dagger that he can use this object class to provide dependencies
@InstallIn(SingletonComponent::class)   //Defines the lifetime of the dependencies,
                                        //Singleton means that the dependency will live for the entire duration
                                        // of the app
object NetworkModule {

    @Provides   //Tells dagger that he can use this function to generate an OkHttpClient instance
    @Singleton  //Allows only one instance of the OkHttpClient to be created
    fun providesOkHTTPClient(): OkHttpClient
        = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build()


    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit
        = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): ApiService
        = retrofit.create(ApiService::class.java)

}