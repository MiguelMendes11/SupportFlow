package com.miguel.supportflow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.miguel.supportflow.databinding.ItemChamadoBinding

class ChamadoAdapter(
    private val chamados: List<Chamado>,
    private val onItemClick: (Chamado) -> Unit
) : RecyclerView.Adapter<ChamadoAdapter.ChamadoViewHolder>() {

    inner class ChamadoViewHolder(val binding: ItemChamadoBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChamadoViewHolder {
        val binding = ItemChamadoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ChamadoViewHolder(binding)
    }

    override fun getItemCount(): Int = chamados.size

    override fun onBindViewHolder(holder: ChamadoViewHolder, position: Int) {
        val chamado = chamados[position]
        val contexto = holder.binding.root.context

        holder.binding.apply {
            tvItemTitulo.text = chamado.titulo
            tvItemCliente.text = chamado.cliente

            tvItemPrioridade.text = chamado.prioridade.rotulo
            tvItemPrioridade.backgroundTintList = ContextCompat.getColorStateList(
                contexto,
                CoresChamado.corDaPrioridade(chamado.prioridade)
            )

            tvItemStatus.text = chamado.status.rotulo
            tvItemStatus.backgroundTintList = ContextCompat.getColorStateList(
                contexto,
                CoresChamado.corDoStatus(chamado.status)
            )

            root.setOnClickListener { onItemClick(chamado) }
        }
    }
}
