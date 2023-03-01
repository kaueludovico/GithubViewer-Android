package com.zallpy.githubviewer.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zallpy.githubviewer.databinding.ResItemRepositoryBinding
import com.zallpy.githubviewer.model.Repository

class RepositoryAdapter(
    private val listRepository: List<Repository>,
    private val onClick: (Repository) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RepositoryViewHolder(
            ResItemRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RepositoryViewHolder -> {
                holder.bind(listRepository[position], onClick)
            }
        }
    }

    override fun getItemCount(): Int {
        return listRepository.size
    }

    inner class RepositoryViewHolder(
        itemView: ResItemRepositoryBinding
    ) : RecyclerView.ViewHolder(itemView.root) {

        private val titleRepository = itemView.textViewTitle
        private val languageRepository = itemView.textViewLanguage

        fun bind(repository: Repository, onClick: (Repository) -> Unit) {
            titleRepository.text = repository.title
            languageRepository.text = repository.language

            itemView.setOnClickListener {
                onClick(repository)
            }
        }
    }
}