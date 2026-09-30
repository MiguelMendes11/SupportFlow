package com.miguel.supportflow

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.miguel.supportflow.data.ChamadoRepositoryImpl
import com.miguel.supportflow.data.SupportFlowDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ChamadosViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ChamadoRepository = ChamadoRepositoryImpl(
        SupportFlowDatabase.getInstance(application).chamadoDao()
    )

    private val tentativa = MutableStateFlow(0)

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<ChamadosUiState> = tentativa
        .flatMapLatest {
            repository.observeChamados()
                .map { chamados ->
                    if (chamados.isEmpty()) {
                        ChamadosUiState.Empty
                    } else {
                        ChamadosUiState.Content(chamados)
                    }
                }
                .onStart { emit(ChamadosUiState.Loading) }
                .catch { e ->
                    Log.w(TAG, "Falha ao carregar chamados", e)
                    emit(ChamadosUiState.Error)
                }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ChamadosUiState.Loading
        )

    val chamados: StateFlow<List<Chamado>> = uiState
        .map { estado -> (estado as? ChamadosUiState.Content)?.chamados ?: emptyList() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun recarregar() {
        tentativa.value++
    }

    fun salvar(chamado: Chamado, aoConcluir: () -> Unit = {}) {
        viewModelScope.launch {
            repository.salvar(chamado)
            aoConcluir()
        }
    }

    fun excluir(chamado: Chamado, aoConcluir: () -> Unit = {}) {
        viewModelScope.launch {
            repository.excluir(chamado)
            aoConcluir()
        }
    }

    private companion object {
        const val TAG = "ChamadosViewModel"
    }
}
