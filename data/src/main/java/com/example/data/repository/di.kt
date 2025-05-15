package com.example.data.repository

import com.example.data.api.repository.AuthRepositoryImpl
import com.example.data.repository.LecturesRepositoryImpl
import com.example.domain.contract.AuthRepository
import com.example.domain.contract.LecturesRepository
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


    @Binds
    abstract fun bindLecturesRepo(
        impl: LecturesRepositoryImpl
    ): LecturesRepository


}