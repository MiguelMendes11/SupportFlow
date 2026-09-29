package com.miguel.supportflow.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.miguel.supportflow.CoresChamado
import com.miguel.supportflow.Prioridade
import com.miguel.supportflow.R
import com.miguel.supportflow.Status

@Composable
fun CabecalhoSuporte(
    titulo: String,
    subtitulo: String?,
    modifier: Modifier = Modifier
) {
    CabecalhoFundo(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            LogoTile()
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = titulo,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
                if (subtitulo != null) {
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = subtitulo,
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 13.sp,
                        color = colorResource(R.color.text_on_navy_muted)
                    )
                }
            }
        }
    }
}

@Composable
fun CabecalhoDetalhe(
    titulo: String,
    textoVoltar: String,
    contentDescriptionVoltar: String,
    aoVoltar: () -> Unit,
    modifier: Modifier = Modifier
) {
    CabecalhoFundo(modifier = modifier, paddingTop = 8.dp) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .clickable(
                        onClickLabel = contentDescriptionVoltar,
                        role = Role.Button,
                        onClick = aoVoltar
                    )
                    .heightIn(min = 48.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_voltar),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    colorFilter = ColorFilter.tint(Color.White)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = textoVoltar,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = titulo,
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )
        }
    }
}

@Composable
fun ChipBadge(
    texto: String,
    corDeFundo: Color,
    modifier: Modifier = Modifier,
    estilo: TextStyle = MaterialTheme.typography.labelMedium,
    paddingHorizontal: Dp = 10.dp,
    paddingVertical: Dp = 4.dp
) {
    Box(
        modifier = modifier
            .background(corDeFundo, RoundedCornerShape(50))
            .padding(horizontal = paddingHorizontal, vertical = paddingVertical)
    ) {
        Text(
            text = texto,
            style = estilo,
            color = Color.White
        )
    }
}

@Composable
fun ChipPrioridade(
    prioridade: Prioridade,
    modifier: Modifier = Modifier,
    estilo: TextStyle = MaterialTheme.typography.labelMedium,
    paddingHorizontal: Dp = 10.dp,
    paddingVertical: Dp = 4.dp
) {
    ChipBadge(
        texto = prioridade.rotulo,
        corDeFundo = colorResource(CoresChamado.corDaPrioridade(prioridade)),
        modifier = modifier,
        estilo = estilo,
        paddingHorizontal = paddingHorizontal,
        paddingVertical = paddingVertical
    )
}

@Composable
fun ChipStatus(
    status: Status,
    modifier: Modifier = Modifier,
    estilo: TextStyle = MaterialTheme.typography.labelMedium,
    paddingHorizontal: Dp = 10.dp,
    paddingVertical: Dp = 4.dp
) {
    ChipBadge(
        texto = status.rotulo,
        corDeFundo = colorResource(CoresChamado.corDoStatus(status)),
        modifier = modifier,
        estilo = estilo,
        paddingHorizontal = paddingHorizontal,
        paddingVertical = paddingVertical
    )
}

@Composable
fun RotuloSecao(
    texto: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = texto.uppercase(),
        style = MaterialTheme.typography.bodySmall,
        color = colorResource(R.color.text_secondary),
        letterSpacing = 0.06.sp,
        modifier = modifier
    )
}

@Composable
fun EstadoVazio(
    titulo: String,
    subtitulo: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(72.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(colorResource(R.color.card_icon_tile_background)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.ic_chamado),
                contentDescription = null,
                modifier = Modifier.size(34.dp),
                colorFilter = ColorFilter.tint(colorResource(R.color.brand_blue_text))
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = titulo,
            style = MaterialTheme.typography.titleMedium,
            color = colorResource(R.color.text_heading),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = subtitulo,
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(R.color.text_secondary),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun CabecalhoFundo(
    modifier: Modifier = Modifier,
    paddingTop: Dp = 16.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    val formatoCabecalho = RoundedCornerShape(
        bottomStart = 20.dp,
        bottomEnd = 20.dp
    )
    val corInicial = colorResource(R.color.brand_navy)
    val corFinal = colorResource(R.color.brand_navy_light)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(formatoCabecalho)
            .drawBehind {
                drawRect(
                    brush = Brush.linearGradient(
                        colors = listOf(corInicial, corFinal),
                        start = Offset.Zero,
                        end = Offset(size.width, size.height)
                    )
                )
            }
            .padding(PaddingValues(start = 16.dp, end = 16.dp, top = paddingTop, bottom = 16.dp)),
        content = content
    )
}

@Composable
private fun LogoTile() {
    Box(
        modifier = Modifier
            .size(36.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(colorResource(R.color.logo_tile_background))
            .padding(6.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.ic_chamado),
            contentDescription = stringResource(R.string.cd_logo),
            modifier = Modifier.fillMaxSize(),
            colorFilter = ColorFilter.tint(Color.White)
        )
    }
}
