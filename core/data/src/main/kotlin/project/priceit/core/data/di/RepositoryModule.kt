package project.priceit.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import project.priceit.core.domain.repo.AuthRepository
import project.priceit.core.data.repo.AuthRepositoryImpl
import project.priceit.core.data.repo.LocationRepositoryImpl
import project.priceit.core.domain.repo.MartRepository
import project.priceit.core.data.repo.MartRepositoryImpl
import project.priceit.core.domain.repo.LocationRepository

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {
    @Binds
    abstract fun bindAuthRepository(
        authRepository: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    abstract fun bindMartRepository(
        martRepository: MartRepositoryImpl
    ): MartRepository

    @Binds
    abstract fun bindLocationRepository(
        locationRepository: LocationRepositoryImpl
    ): LocationRepository
}