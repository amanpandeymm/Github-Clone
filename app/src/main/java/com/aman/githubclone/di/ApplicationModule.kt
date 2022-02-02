package com.aman.githubclone.di

import com.aman.githubclone.BuildConfig
import com.aman.githubclone.networking.ApiService
import com.aman.githubclone.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Named("github")
    fun provideGithubBaseUrl() = Constants.BASE_URL_GITHUB

    @Provides
    @Named("apiGithub")
    fun provideApiGithubBaseUrl() = Constants.BASE_URL_API_GITHUB

    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    @Provides
    @Named("githubRetrofit")
    fun provideGithubRetrofit(okHttpClient: OkHttpClient, @Named("github") BASE_URL: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Named("apiGithubRetrofit")
    fun provideApiGithubRetrofit(okHttpClient: OkHttpClient, @Named("apiGithub") API_BASE_URL: String): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(API_BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Named("githubRetrofitApiService")
    fun provideGithubApiService(@Named("githubRetrofit") retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Named("apiGithubRetrofitApiService")
    fun provideApiGithubApiService(@Named("apiGithubRetrofit") retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)
}