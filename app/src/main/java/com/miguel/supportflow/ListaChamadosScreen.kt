package com.miguel.supportflow

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.miguel.supportflow.ui.components.CabecalhoSuporte
import com.miguel.supportflow.ui.components.ChipPrioridade
import com.miguel.supportflow.ui.components.ChipStatus
import com.miguel.supportflow.ui.components.EstadoVazio

@Composable
fun ListaChamadosScreen(
    chamados: List<Chamado>,
    onChamadoClick: (Chamado) -> Unit,
    onNovoChamadoClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(R.color.screen_background))
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            CabecalhoSuporte(
                titulo = stringResource(R.string.app_name),
                subtitulo = stringResource(R.string.subtitulo_lista),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))

            if (chamados.isEmpty()) {
                EstadoVazio(
                    titulo = stringResource(R.string.lista_vazia),
                    subtitulo = stringResource(R.string.lista_vazia_subtitulo),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(horizontal = 32.dp, vertical = 24.dp)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(top = 2.dp, bottom = 96.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(items = chamados, key = { it.id }) { chamado ->
                        CartaoChamado(
                            chamado = chamado,
                            onClick = { onChamadoClick(chamado) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        )
                    }
                }
            }
        }

        ExtendedFloatingActionButton(
            onClick = onNovoChamadoClick,
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_adicionar),
                    contentDescription = null
                )
            },
            text = { Text(text = stringResource(R.string.novo_chamado)) },
            containerColor = colorResource(R.color.brand_blue),
            contentColor = colorResource(R.color.white),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        )
    }
}

@Composable
private fun CartaoChamado(
    chamado: Chamado,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.surface_card)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = BorderStroke(1.dp, colorResource(R.color.border_card))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(colorResource(R.color.card_icon_tile_background)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_chamado),
                    contentDescription = null,
                    modifier = Modifier.size(22.dp),
                    colorFilter = ColorFilter.tint(colorResource(R.color.brand_blue_text))
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = chamado.titulo,
                    style = MaterialTheme.typography.titleMedium,
                    color = colorResource(R.color.text_heading),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = chamado.cliente,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(R.color.text_secondary)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    ChipPrioridade(prioridade = chamado.prioridade)
                    Spacer(modifier = Modifier.width(8.dp))
                    ChipStatus(status = chamado.status)
                }
            }
        }
    }
}
