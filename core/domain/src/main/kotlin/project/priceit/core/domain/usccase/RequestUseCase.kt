package project.priceit.core.domain.usccase

import jakarta.inject.Inject
import project.priceit.core.domain.repo.MartRepository
import project.priceit.core.model.RequestEntity

// di를 위해 martRepository를 주입, 하드코딩 되어있음.
// 추후 api 연동 시 다른 repo를 주입받아 하드코딩을 수정해야 함.
class RequestUseCase @Inject constructor(
    private val martRepository: MartRepository,
) {
    suspend fun getHomeRequestList(): Result<Pair<List<RequestEntity>, List<RequestEntity>>> {
        return Result.success(getCurrentRequestList() to getRecommendedRequestList())
    }

    suspend fun getCurrentRequestList(): List<RequestEntity> {
        return listOf(
            RequestEntity(
                id = 0,
                title = "감자",
                location = "성동구",
                reward = 100,
                category = "채소"
            ),
            RequestEntity(
                id = 1,
                title = "사과",
                location = "성동구",
                reward = 200,
                category = "과일"
            ),
            RequestEntity(
                id = 1,
                title = "배",
                location = "성동구",
                reward = 200,
                category = "과일"
            ),
        )
    }

    suspend fun getRecommendedRequestList(): List<RequestEntity> {
        return listOf(
            RequestEntity(
                id = 0,
                title = "감자",
                location = "성동구",
                reward = 100,
                category = "채소"
            ),
            RequestEntity(
                id = 1,
                title = "사과",
                location = "성동구",
                reward = 200,
                category = "과일"
            ),
            RequestEntity(
                id = 1,
                title = "배",
                location = "성동구",
                reward = 200,
                category = "과일"
            ),
        )
    }
}

