
package com.example.todolist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.databinding.ItemTaskBinding
import com.example.todolist.model.Task

class TaskListAdapter : ListAdapter<Task, TaskListAdapter.TaskListViewHolder>(DiffCallback()) {

    var listenerDelete: (Task) -> Unit = {}
    var listenerEdit: (Task) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTaskBinding.inflate(inflater, parent, false)
        return TaskListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TaskListViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Task) {
            binding.tvTitle.text = item.title
            binding.tvDate.text = "${item.date} ${item.hour}"
            binding.acimMore.setOnClickListener {
                showPopup(item)
            }
        }

        private fun showPopup(item: Task) {
            val acimMore = binding.acimMore
            val popupMenu = PopupMenu(acimMore.context, acimMore)
            popupMenu.menuInflater.inflate(R.menu.menu_task, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.label_edit -> listenerEdit(item)
                    R.id.label_delete -> listenerDelete(item)
                }
                return@setOnMenuItemClickListener true
            }
            popupMenu.show()
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task) =
        oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Task, newItem: Task) =
        oldItem.title == newItem.title
}