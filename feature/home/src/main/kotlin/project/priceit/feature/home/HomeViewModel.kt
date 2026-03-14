package project.priceit.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import project.priceit.core.domain.usccase.LocationUseCase
import project.priceit.core.domain.usccase.RequestUseCase
import project.priceit.core.model.RequestEntity
import project.priceit.feature.home.model.HomeEffect
import project.priceit.feature.home.model.HomeEvent
import project.priceit.feature.home.model.HomeUiState
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val locationUseCase: LocationUseCase,
    private val requestUseCase: RequestUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _effect = Channel<HomeEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    private val _searchRadius: MutableStateFlow<Float> = MutableStateFlow(0.3f)
    val searchRadius = _searchRadius.asStateFlow()

    init{
        getRequestList()
    }

    fun observeLocation() {
        viewModelScope.launch {
            locationUseCase.getLocationFlow()
                .combine(searchRadius) { location, radius ->
                    location to radius
                }
                .collect { (location, radius) ->
                    locationUseCase.getNearMartForLocation(
                        latitude = location.latitude,
                        longitude = location.longitude,
                        radius = radius
                    ).onSuccess { list ->
                        _uiState.update { prev ->
                            prev.copy(
                                currentLocation = location,
                                nearbyMartEntities = list
                            )
                        }
                    }
                }
        }
    }

    fun getRequestList() = viewModelScope.launch{
        requestUseCase.getHomeRequestList()
            .onSuccess {
                _uiState.update { prev->
                    prev.copy(
                        currentRequestList = it.first,
                        recommentRequestList = it.second
                    )
                }
            }.onFailure {

            }
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.ShowRadiusDialog -> showRadiusDialog()
            HomeEvent.HideRadiusDialog -> hideRadiusDialog()

            is HomeEvent.ChangeRadiusTemp -> {
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

    //검색 반경을 업데이트
    fun updateSearchRadius(radius: Float) {
        _searchRadius.value = radius.coerceIn(0.1f, 0.5f)
    }
}
