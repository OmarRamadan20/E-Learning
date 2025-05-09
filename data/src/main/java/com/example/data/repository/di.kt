package com.route.data.repositories

import com.example.data.api.repository.AuthRepositoryImpl
import com.example.domain.contract.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoriesModule{

    @Binds
    abstract fun bindAuthRepo(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository



}