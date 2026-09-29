package com.miguel.supportflow

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ListaChamadosScreen(
    chamados: List<Chamado>,
    onChamadoClick: (Chamado) -> Unit,
    onNovoChamadoClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Chamados",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onNovoChamadoClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Novo chamado")
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (chamados.isEmpty()) {
            Text(
                text = "Nenhum chamado encontrado",
                style = MaterialTheme.typography.bodyMedium
            )
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(items = chamados, key = { it.id }) { chamado ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onChamadoClick(chamado) }
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = chamado.titulo,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = chamado.cliente,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "${chamado.prioridade.rotulo} - ${chamado.status.rotulo}",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }
        }
    }
}
