package project.priceit.core.data.repo

import project.priceit.core.data.mapper.toResult
import project.priceit.core.data.datasource.auth.AuthDatasource
import project.priceit.core.data.di.FakeDataSource
import project.priceit.core.domain.repo.AuthRepository
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