package com.miguel.supportflow

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

object ChamadosRoutes {
    const val LISTA = "lista"
    const val DETALHE = "detalhe/{chamadoId}"
    const val FORMULARIO = "formulario"
    const val ARG_CHAMADO_ID = "chamadoId"

    fun detalhe(chamadoId: Int): String = "detalhe/$chamadoId"
}

@Composable
fun ChamadosNavGraph() {
    val navController = rememberNavController()
    val viewModel: ChamadosViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = ChamadosRoutes.LISTA
    ) {
        composable(ChamadosRoutes.LISTA) {
            val chamados by viewModel.chamados.collectAsState()

            ListaChamadosScreen(
                chamados = chamados,
                onChamadoClick = { chamado ->
                    navController.navigate(ChamadosRoutes.detalhe(chamado.id))
                },
                onNovoChamadoClick = {
                    navController.navigate(ChamadosRoutes.FORMULARIO)
                }
            )
        }

        composable(
            route = ChamadosRoutes.DETALHE,
            arguments = listOf(
                navArgument(ChamadosRoutes.ARG_CHAMADO_ID) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val chamadoId =
                backStackEntry.arguments?.getInt(ChamadosRoutes.ARG_CHAMADO_ID)
            val chamados by viewModel.chamados.collectAsState()

            DetalheChamadoScreen(
                chamado = chamados.find { it.id == chamadoId },
                onVoltarClick = { navController.popBackStack() }
            )
        }

        composable(ChamadosRoutes.FORMULARIO) {
            FormularioChamadoScreen(
                onVoltarClick = { navController.popBackStack() },
                onSalvar = { chamado ->
                    viewModel.salvar(chamado) {
                        navController.popBackStack(
                            route = ChamadosRoutes.LISTA,
                            inclusive = false
                        )
                    }
                }
            )
        }
    }
}
