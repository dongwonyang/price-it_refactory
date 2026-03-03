package project.priceit.core.domain.repo

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Boolean>
}