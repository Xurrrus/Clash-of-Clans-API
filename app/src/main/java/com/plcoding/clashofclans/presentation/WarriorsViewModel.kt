package com.plcoding.clashofclans.presentation
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.clashofclans.domain.DetailedWarrior
import com.plcoding.clashofclans.domain.GetWarriorUseCase
import com.plcoding.clashofclans.domain.GetWarriorsUseCase
import com.plcoding.clashofclans.domain.SimpleWarrior
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


@HiltViewModel
class WarriorsViewModel @Inject constructor(
    private val getWarriorsUseCase: GetWarriorsUseCase,
    private val getWarriorUseCase: GetWarriorUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(WarriorsState())
    val state = _state.asStateFlow()
    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> get() = _searchText

    init {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            _state.update { it.copy(
                warriors = getWarriorsUseCase.execute(),
                isLoading = false
            ) }
        }
    }

    fun selectWarrior(id: String) {
        viewModelScope.launch {
            _state.update { it.copy(
                selectedWarrior = getWarriorUseCase.execute(id)
            ) }
        }
    }

    fun dismissWarriorDialog() {
        _state.update { it.copy(
            selectedWarrior = null
        ) }
    }
    fun onSearchTextChanged(text: String) {
        _searchText.value = text
    }


    fun searchWarriors(query: String) {
        val filteredWarriors = state.value.warriors.filter {
            it.nom.contains(query, ignoreCase = true)
        }

        _state.update { it.copy(
            warriors = filteredWarriors
        ) }
    }

    data class WarriorsState(
        val warriors: List<SimpleWarrior> = emptyList(),
        val isLoading: Boolean = false,
        val selectedWarrior: DetailedWarrior? = null
    )
}

