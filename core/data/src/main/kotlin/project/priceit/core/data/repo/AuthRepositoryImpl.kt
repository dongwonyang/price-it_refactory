package project.priceit.core.data.repo

import project.priceit.core.data.mapper.toResult
import project.priceit.core.network.datasource.auth.AuthDatasource
import project.priceit.core.network.di.FakeDataSource
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    @param:FakeDataSource private val authDatasource: AuthDatasource
): AuthRepository {
    override suspend fun login(email: String, password: String): Result<Boolean> {
        return authDatasource.login(email, password).toResult(
            transform = {
                it.isSuccess
            }
        )
    }
}