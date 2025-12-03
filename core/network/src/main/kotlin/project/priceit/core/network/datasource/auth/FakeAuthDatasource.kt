package project.priceit.core.network.datasource.auth

import project.priceit.core.network.model.ApiResponse
import project.priceit.core.network.model.response.LoginResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeAuthDatasource @Inject constructor(): AuthDatasource {
    override suspend fun login(email: String, password: String): ApiResponse<LoginResponse> {
        return ApiResponse.Success(LoginResponse(true))
    }
}