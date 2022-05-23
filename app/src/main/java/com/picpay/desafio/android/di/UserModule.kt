package com.picpay.desafio.android.di

import com.picpay.desafio.android.commons.constants.Constants.BASE_URL
import com.picpay.desafio.android.feature.user.repository.UserRepository
import com.picpay.desafio.android.feature.user.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UserModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideUserRepository(retrofit: Retrofit): UserRepository = UserRepositoryImpl(retrofit)

}
