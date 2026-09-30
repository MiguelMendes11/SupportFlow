package com.miguel.supportflow

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import kotlinx.serialization.Serializable

@Serializable
sealed interface ChamadosNavKey : NavKey {

    @Serializable
    data object Lista : ChamadosNavKey

    @Serializable
    data object Formulario : ChamadosNavKey

    @Serializable
    data class Detalhe(val chamadoId: Int) : ChamadosNavKey
}

@Composable
fun ChamadosNavGraph() {
    val backStack = rememberNavBackStack(ChamadosNavKey.Lista)
    val viewModel: ChamadosViewModel = viewModel()

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removerUltimo() },
        entryProvider = entryProvider {
            entry<ChamadosNavKey.Lista> {
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()

                ListaChamadosScreen(
                    uiState = uiState,
                    onChamadoClick = { chamado ->
                        backStack.add(ChamadosNavKey.Detalhe(chamado.id))
                    },
                    onNovoChamadoClick = {
                        backStack.add(ChamadosNavKey.Formulario)
                    },
                    onTentarNovamente = {
                        viewModel.recarregar()
                    }
                )
            }

            entry<ChamadosNavKey.Detalhe> { chave ->
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()

                DetalheChamadoScreen(
                    uiState = uiState,
                    chamadoId = chave.chamadoId,
                    onVoltarClick = { backStack.removerUltimo() },
                    onExcluirClick = { chamado ->
                        viewModel.excluir(chamado) {
                            backStack.voltarParaLista()
                        }
                    },
                    onTentarNovamente = {
                        viewModel.recarregar()
                    }
                )
            }

            entry<ChamadosNavKey.Formulario> {
                FormularioChamadoScreen(
                    onVoltarClick = { backStack.removerUltimo() },
                    onSalvar = { chamado ->
                        viewModel.salvar(chamado) {
                            backStack.voltarParaLista()
                        }
                    }
                )
            }
        }
    )
}

private fun MutableList<NavKey>.removerUltimo() {
    if (size > 1) {
        removeAt(lastIndex)
    }
}

private fun MutableList<NavKey>.voltarParaLista() {
    while (size > 1 && last() !is ChamadosNavKey.Lista) {
        removeAt(lastIndex)
    }
}
