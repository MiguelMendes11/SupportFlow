package com.miguel.supportflow

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.miguel.supportflow.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val chamados = ChamadosMock.chamados
        binding.tvListaVazia.isVisible = chamados.isEmpty()

        binding.recyclerChamados.layoutManager = LinearLayoutManager(this)
        binding.recyclerChamados.adapter = ChamadoAdapter(chamados) { chamado ->
            val intent = Intent(this, DetalheActivity::class.java).apply {
                putExtra(DetalheActivity.EXTRA_CHAMADO, chamado)
            }
            startActivity(intent)
        }
    }
}
