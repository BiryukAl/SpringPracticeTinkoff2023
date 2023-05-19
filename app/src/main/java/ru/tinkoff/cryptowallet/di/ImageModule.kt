package ru.tinkoff.cryptowallet.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import ru.tinkoff.cryptowallet.presentation.adapters.CryptoAdapter


@Module
@InstallIn(ActivityComponent::class)
class ImageModule {

    @Provides
    @ActivityScoped
    fun provideGlide(
        @ActivityContext
        context: Context,
    ): RequestManager {
        return Glide.with(context)
    }

    @Provides
    @ActivityScoped
    fun provideAdapter(glide: RequestManager): CryptoAdapter {
        return CryptoAdapter(glide)
    }
}
