package project.priceit.core.network.datasource.auth

import project.priceit.core.network.model.ApiResponse
import project.priceit.core.network.model.response.LoginResponse

interface AuthDatasource {
    suspend fun login(email: String, password: String): ApiResponse<LoginResponse>
}