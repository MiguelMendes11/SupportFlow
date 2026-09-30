package com.miguel.supportflow

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.miguel.supportflow.ui.components.CabecalhoDetalhe
import com.miguel.supportflow.ui.components.Carregando
import com.miguel.supportflow.ui.components.ChipPrioridade
import com.miguel.supportflow.ui.components.ChipStatus
import com.miguel.supportflow.ui.components.EstadoErro
import com.miguel.supportflow.ui.components.RotuloSecao

@Composable
fun DetalheChamadoScreen(
    uiState: ChamadosUiState,
    chamadoId: Int,
    onVoltarClick: () -> Unit,
    onTentarNovamente: () -> Unit,
    modifier: Modifier = Modifier
) {
    val chamado = (uiState as? ChamadosUiState.Content)
        ?.chamados
        ?.find { it.id == chamadoId }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(R.color.screen_background))
            .verticalScroll(rememberScrollState())
    ) {
        CabecalhoDetalhe(
            titulo = stringResource(R.string.detalhe_titulo),
            textoVoltar = stringResource(R.string.voltar),
            contentDescriptionVoltar = stringResource(R.string.cd_voltar),
            aoVoltar = onVoltarClick,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        if (uiState is ChamadosUiState.Loading) {
            Carregando(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 40.dp)
            )
        } else if (uiState is ChamadosUiState.Error) {
            EstadoErro(
                mensagem = stringResource(R.string.erro_carregamento),
                aoTentarNovamente = onTentarNovamente,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 24.dp)
            )
        } else if (chamado == null) {
            Text(
                text = stringResource(R.string.detalhe_nao_encontrado),
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(R.color.text_secondary),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 40.dp)
            )
        } else {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(R.color.surface_card)
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                border = BorderStroke(1.dp, colorResource(R.color.border_card))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = chamado.titulo,
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 18.sp,
                        color = colorResource(R.color.text_heading)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Divisor()
                    Spacer(modifier = Modifier.height(16.dp))

                    RotuloSecao(texto = stringResource(R.string.label_cliente))
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = chamado.cliente,
                        style = MaterialTheme.typography.bodyLarge,
                        color = colorResource(R.color.text_primary)
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    RotuloSecao(texto = stringResource(R.string.label_descricao))
                    Spacer(modifier = Modifier.height(6.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = colorResource(R.color.field_group_background),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .padding(start = 12.dp, end = 12.dp, top = 10.dp, bottom = 10.dp)
                    ) {
                        Text(
                            text = chamado.descricao ?: stringResource(R.string.sem_descricao),
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 15.sp,
                            lineHeight = 22.sp,
                            color = colorResource(R.color.text_secondary)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Divisor()
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.weight(1f)) {
                            RotuloSecao(texto = stringResource(R.string.label_prioridade))
                            Spacer(modifier = Modifier.height(6.dp))
                            ChipPrioridade(
                                prioridade = chamado.prioridade,
                                estilo = MaterialTheme.typography.labelLarge,
                                paddingHorizontal = 12.dp,
                                paddingVertical = 6.dp
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            RotuloSecao(texto = stringResource(R.string.label_status))
                            Spacer(modifier = Modifier.height(6.dp))
                            ChipStatus(
                                status = chamado.status,
                                estilo = MaterialTheme.typography.labelLarge,
                                paddingHorizontal = 12.dp,
                                paddingVertical = 6.dp
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun Divisor() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(colorResource(R.color.border_card))
    )
}
