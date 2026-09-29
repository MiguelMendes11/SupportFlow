package com.miguel.supportflow

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DetalheChamadoScreen(
    chamado: Chamado?,
    onVoltarClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(onClick = onVoltarClick) {
            Text(text = "Voltar")
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (chamado == null) {
            Text(
                text = "Chamado não encontrado",
                style = MaterialTheme.typography.bodyMedium
            )
        } else {
            Text(
                text = chamado.titulo,
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Cliente: ${chamado.cliente}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Prioridade: ${chamado.prioridade.rotulo}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Status: ${chamado.status.rotulo}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Descrição",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = chamado.descricao ?: "Sem descrição",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
