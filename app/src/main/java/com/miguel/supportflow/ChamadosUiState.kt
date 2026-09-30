package com.miguel.supportflow

sealed interface ChamadosUiState {

    data object Loading : ChamadosUiState

    data class Content(val chamados: List<Chamado>) : ChamadosUiState

    data object Empty : ChamadosUiState

    data object Error : ChamadosUiState
}
