package com.miguel.supportflow

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.miguel.supportflow.ui.components.CabecalhoDetalhe
import com.miguel.supportflow.ui.components.RotuloSecao

@Composable
fun FormularioChamadoScreen(
    onVoltarClick: () -> Unit,
    onSalvar: (Chamado) -> Unit,
    modifier: Modifier = Modifier
) {
    var titulo by rememberSaveable { mutableStateOf("") }
    var cliente by rememberSaveable { mutableStateOf("") }
    var descricao by rememberSaveable { mutableStateOf("") }
    var prioridade by rememberSaveable { mutableStateOf(Prioridade.MEDIA) }
    var status by rememberSaveable { mutableStateOf(Status.PENDENTE) }

    val podeSalvar = titulo.isNotBlank() && cliente.isNotBlank()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(R.color.screen_background))
            .verticalScroll(rememberScrollState())
    ) {
        CabecalhoDetalhe(
            titulo = stringResource(R.string.novo_chamado),
            textoVoltar = stringResource(R.string.voltar),
            contentDescriptionVoltar = stringResource(R.string.cd_voltar),
            aoVoltar = onVoltarClick,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(R.color.surface_card)
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            border = BorderStroke(
                1.dp,
                colorResource(R.color.border_card)
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = titulo,
                    onValueChange = { titulo = it },
                    label = { Text(text = "Título") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = cliente,
                    onValueChange = { cliente = it },
                    label = { Text(text = stringResource(R.string.label_cliente)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(R.color.surface_card)
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            border = BorderStroke(
                1.dp,
                colorResource(R.color.border_card)
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                RotuloSecao(texto = stringResource(R.string.label_prioridade))
                Spacer(modifier = Modifier.height(12.dp))
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Prioridade.values().forEach { opcao ->
                        OpcaoChip(
                            texto = opcao.rotulo,
                            cor = colorResource(CoresChamado.corDaPrioridade(opcao)),
                            selecionado = prioridade == opcao,
                            onClick = { prioridade = opcao }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                RotuloSecao(texto = stringResource(R.string.label_status))
                Spacer(modifier = Modifier.height(12.dp))
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Status.values().forEach { opcao ->
                        OpcaoChip(
                            texto = opcao.rotulo,
                            cor = colorResource(CoresChamado.corDoStatus(opcao)),
                            selecionado = status == opcao,
                            onClick = { status = opcao }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(R.color.surface_card)
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            border = BorderStroke(
                1.dp,
                colorResource(R.color.border_card)
            )
        ) {
            OutlinedTextField(
                value = descricao,
                onValueChange = { descricao = it },
                label = { Text(text = stringResource(R.string.label_descricao)) },
                minLines = 3,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        if (!podeSalvar) {
            Text(
                text = "Informe título e cliente para salvar.",
                style = MaterialTheme.typography.bodySmall,
                color = colorResource(R.color.text_secondary),
                modifier = Modifier.padding(horizontal = 20.dp)
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
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.brand_blue),
                contentColor = colorResource(R.color.white),
                disabledContainerColor = colorResource(R.color.border_card),
                disabledContentColor = colorResource(R.color.text_secondary)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(48.dp)
        ) {
            Text(text = "Salvar chamado")
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun OpcaoChip(
    texto: String,
    cor: Color,
    selecionado: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val formato = RoundedCornerShape(10.dp)
    val fundo = if (selecionado) cor else Color.Transparent
    val borda = if (selecionado) cor else colorResource(R.color.border_card)
    val corDoTexto = if (selecionado) Color.White else colorResource(R.color.text_primary)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clip(formato)
            .background(fundo)
            .border(1.dp, borda, formato)
            .selectable(
                selected = selecionado,
                role = Role.RadioButton,
                onClick = onClick
            )
            .padding(horizontal = 12.dp, vertical = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .background(
                    color = if (selecionado) Color.White else cor,
                    shape = RoundedCornerShape(50)
                )
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = texto,
            style = MaterialTheme.typography.bodyLarge,
            color = corDoTexto,
            modifier = Modifier.weight(1f)
        )
    }
}
