package project.priceit.core.domain.usccase

import jakarta.inject.Inject
import project.priceit.core.domain.repo.AuthRepository

class AuthUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun login(email: String, password: String): Result<Boolean> {
        return authRepository.login(email, password)
    }
}