package project.priceit.core.domain

import jakarta.inject.Inject
import project.priceit.core.data.mapper.toResult
import project.priceit.core.data.repo.AuthRepository

class AuthUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun login(email: String, password: String): Result<Boolean> {
        return authRepository.login(email, password)
    }
}