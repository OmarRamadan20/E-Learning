package com.example.data.datasource

import com.example.data.contract.AuthDataSource
import com.example.data.api.datasource.AuthDataSourceImpl
import com.example.data.contract.LecturesDataSource
import com.example.data.datasource.LecturesDataSourceImpl
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

    @Binds
    abstract fun bindLecturesOnlineDataSource(
        impl: LecturesDataSourceImpl
    ): LecturesDataSource


}

