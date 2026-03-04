package project.priceit.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import project.priceit.core.data.datasource.auth.AuthDatasource
import project.priceit.core.data.datasource.auth.FakeAuthDatasource
import project.priceit.core.data.datasource.auth.RetrofitAuthDatasource
import project.priceit.core.data.datasource.mart.FakeMartDataSource
import project.priceit.core.data.datasource.mart.MartDataSource
import project.priceit.core.data.datasource.mart.RetrofitMartDataSource
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DatasourceModule {
    @Binds
    @FakeDataSource
    abstract fun bindFakeAuthDatasource(
        fakeAuthDatasource: FakeAuthDatasource
    ): AuthDatasource

    @Binds
    @RealDataSource
    abstract fun bindAuthDatasource(
        retrofitAuthDatasource: RetrofitAuthDatasource
    ): AuthDatasource

    @Binds
    @FakeDataSource
    abstract fun bindFakeMartDatasource(
        fakeMartDatasource: FakeMartDataSource
    ): MartDataSource

     @Binds
     @RealDataSource
     abstract fun bindMartDatasource(
         retrofitMartDatasource: RetrofitMartDataSource
     ): MartDataSource

}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RealDataSource

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FakeDataSource