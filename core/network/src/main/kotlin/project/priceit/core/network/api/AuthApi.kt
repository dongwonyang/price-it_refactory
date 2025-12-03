package project.priceit.core.network.api

import project.priceit.core.network.model.response.LoginResponse
import retrofit2.Response

interface AuthApi {
    suspend fun login(email: String, password: String): Response<LoginResponse>
}