package com.miguel.supportflow

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.miguel.supportflow.databinding.ActivityDetalheBinding

class DetalheActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetalheBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetalheBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val chamado = lerChamado()
        if (chamado == null) {
            finish()
            return
        }

        exibir(chamado)

        binding.btnVoltar.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun lerChamado(): Chamado? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        intent.getSerializableExtra(EXTRA_CHAMADO, Chamado::class.java)
    } else {
        @Suppress("DEPRECATION")
        intent.getSerializableExtra(EXTRA_CHAMADO) as? Chamado
    }

    private fun exibir(chamado: Chamado) {
        binding.apply {
            tvChamadoTitulo.text = chamado.titulo
            tvCliente.text = chamado.cliente
            tvDescricao.text = chamado.descricao ?: getString(R.string.sem_descricao)

            tvPrioridade.text = chamado.prioridade.rotulo
            tvPrioridade.backgroundTintList = ContextCompat.getColorStateList(
                this@DetalheActivity,
                CoresChamado.corDaPrioridade(chamado.prioridade)
            )

            tvStatus.text = chamado.status.rotulo
            tvStatus.backgroundTintList = ContextCompat.getColorStateList(
                this@DetalheActivity,
                CoresChamado.corDoStatus(chamado.status)
            )
        }
    }

    companion object {
        const val EXTRA_CHAMADO = "chamado"
    }
}
