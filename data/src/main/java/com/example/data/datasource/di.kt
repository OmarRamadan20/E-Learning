package com.route.data.datasource

import com.example.data.api.contract.AuthDataSource
import com.example.data.api.datasource.AuthDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class OnlineDataSourceModule{



    @Binds
    abstract fun bindRegisterOnlineDataSource(
        impl: AuthDataSourceImpl
    ): AuthDataSource

}

