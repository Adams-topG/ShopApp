package com.example.shopapp.di

import android.content.Context
import androidx.room.Room
import com.example.shopapp.data.dao.FavouriteJeansDAO
import com.example.shopapp.data.db.AppDatabase
import com.example.shopapp.domain.retrofit.RetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class JeansFeatureModule {
    @Provides
    fun provideJeansApi(retrofit: Retrofit) =
        retrofit.create(RetrofitService::class.java)

    @Provides
    fun provideFavouriteRoom(appDatabase: AppDatabase): FavouriteJeansDAO {
        return appDatabase.favouriteDao()
    }
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "myApp"
        ).build()
    }
}