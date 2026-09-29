package com.miguel.supportflow

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ChamadosViewModel : ViewModel() {

    private val repository: ChamadoRepository = ChamadoRepositoryMock()

    private val _chamados = MutableStateFlow(repository.getChamados())

    val chamados: StateFlow<List<Chamado>> = _chamados.asStateFlow()
}
