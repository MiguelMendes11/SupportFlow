package com.miguel.supportflow

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.miguel.supportflow.data.ChamadoRepositoryImpl
import com.miguel.supportflow.data.SupportFlowDatabase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ChamadosViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ChamadoRepository = ChamadoRepositoryImpl(
        SupportFlowDatabase.getInstance(application).chamadoDao()
    )

    val chamados: StateFlow<List<Chamado>> = repository.observeChamados()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun salvar(chamado: Chamado, aoConcluir: () -> Unit = {}) {
        viewModelScope.launch {
            repository.salvar(chamado)
            aoConcluir()
        }
    }
}
