package project.priceit.feature.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import project.priceit.feature.home.model.HomeEffect
import project.priceit.feature.home.model.HomeEvent
import project.priceit.feature.home.model.HomeUiState
import project.priceit.feature.home.model.test
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(test())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()


    private val _effect = Channel<HomeEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.ShowRadiusDialog -> showRadiusDialog()
            HomeEvent.HideRadiusDialog -> hideRadiusDialog()

            is HomeEvent.RadiusTempChanged -> {
                updateSearchRadius(event.radius)
                hideRadiusDialog()
            }
        }
    }

    //  반경 설정 다이얼로그를 표시
    fun showRadiusDialog() {
        _uiState.update { prev ->
            prev.copy(isRadiusDialogVisible = true)
        }

    }

    //  반경 설정 다이얼로그를 숨김
    fun hideRadiusDialog() {
        _uiState.update { prev ->
            prev.copy(isRadiusDialogVisible = false)
        }
    }

    /*
    검색 반경을 업데이트
    @param radius 새로운 검색 반경 (km 단위)
    */
    fun updateSearchRadius(radius: Float) {
        val limitedRadius = radius.coerceIn(0.1f, 0.5f) // 0.1~0.5km(100~500m)로 제한

        _uiState.update { currentState ->
            val newState = currentState.copy(searchRadius = limitedRadius)

            currentState.currentLocation?.let { location ->
//                        // 위치 정보가 있으면 새 반경으로 마트 검색 및 추천의뢰 다시 로드
//                        searchNearbyMarts(location.latitude, location.longitude, limitedRadius)
//                        loadLocationBasedRecommendedRequests(location.latitude, location.longitude)
            }

            newState
        }
    }
}
