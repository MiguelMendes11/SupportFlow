package com.miguel.supportflow

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FormularioChamadoScreen(
    onVoltarClick: () -> Unit,
    onSalvar: (Chamado) -> Unit,
    modifier: Modifier = Modifier
) {
    var titulo by rememberSaveable { mutableStateOf("") }
    var cliente by rememberSaveable { mutableStateOf("") }
    var descricao by rememberSaveable { mutableStateOf("") }
    var prioridade by remember { mutableStateOf(Prioridade.MEDIA) }
    var status by remember { mutableStateOf(Status.PENDENTE) }

    val podeSalvar = titulo.isNotBlank() && cliente.isNotBlank()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Button(onClick = onVoltarClick) {
            Text(text = "Voltar")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Novo chamado",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = titulo,
            onValueChange = { titulo = it },
            label = { Text(text = "Título") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = cliente,
            onValueChange = { cliente = it },
            label = { Text(text = "Cliente") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Prioridade",
            style = MaterialTheme.typography.titleMedium
        )
        Prioridade.values().forEach { opcao ->
            OpcaoSelecionavel(
                rotulo = opcao.rotulo,
                selecionado = prioridade == opcao,
                onClick = { prioridade = opcao }
            )
        }
        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Status",
            style = MaterialTheme.typography.titleMedium
        )
        Status.values().forEach { opcao ->
            OpcaoSelecionavel(
                rotulo = opcao.rotulo,
                selecionado = status == opcao,
                onClick = { status = opcao }
            )
        }
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = descricao,
            onValueChange = { descricao = it },
            label = { Text(text = "Descrição") },
            minLines = 3,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))

        if (!podeSalvar) {
            Text(
                text = "Informe título e cliente para salvar.",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = {
                onSalvar(
                    Chamado(
                        titulo = titulo.trim(),
                        cliente = cliente.trim(),
                        prioridade = prioridade,
                        status = status,
                        descricao = descricao.trim().ifBlank { null }
                    )
                )
            },
            enabled = podeSalvar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Salvar chamado")
        }
    }
}

@Composable
private fun OpcaoSelecionavel(
    rotulo: String,
    selecionado: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        RadioButton(
            selected = selecionado,
            onClick = onClick
        )
        Text(
            text = rotulo,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
