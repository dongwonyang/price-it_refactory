package project.priceit.core.network.datasource.auth

import project.priceit.core.network.api.AuthApi
import project.priceit.core.network.model.ApiResponse
import project.priceit.core.network.model.response.LoginResponse
import project.priceit.core.network.util.runRemote
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitAuthDatasource @Inject constructor(
   private val authApi: AuthApi
): AuthDatasource {
    override suspend fun login(email: String, password: String): ApiResponse<LoginResponse> =
        runRemote {
            authApi.login(email, password)
        }
}