package com.example.test.mvvmsampleapp.view.adapter

import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.test.mvvmsampleapp.R
import com.example.test.mvvmsampleapp.databinding.ProjectListItemBinding
import com.example.test.mvvmsampleapp.service.model.Project
import com.example.test.mvvmsampleapp.view.callback.ProjectClickCallback
import com.example.test.mvvmsampleapp.view.viewholders.ProjectViewHolder

/**
 * Adapter which shows a list of repositories.
 */
class ProjectAdapter(private val projectClickCallback: ProjectClickCallback?)
    : RecyclerView.Adapter<ProjectViewHolder>() {

    internal var projectList: List<Project>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val binding = DataBindingUtil
                .inflate<ProjectListItemBinding>(LayoutInflater.from(parent.context),
                        R.layout.project_list_item,
                        parent, false)

        binding.callback = projectClickCallback

        return ProjectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.binding.project = projectList!![position]
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return if (projectList == null) 0 else projectList!!.size
    }

    fun setProjectList(projectList: List<Project>) {
        if (this.projectList == null) {
            this.projectList = projectList
            notifyDataSetChanged()
        } else {
            calculateDiff(projectList)
        }
    }

    private fun calculateDiff(projectList: List<Project>) {
        val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int {
                return this@ProjectAdapter.projectList!!.size
            }

            override fun getNewListSize(): Int {
                return projectList.size
            }

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return this@ProjectAdapter.projectList!![oldItemPosition].id ==
                        projectList[newItemPosition].id
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val project = projectList[newItemPosition]
                val old = projectList[oldItemPosition]
                return project.id == old.id &&
                        project.git_url == old.git_url
            }
        })
        this.projectList = projectList
        result.dispatchUpdatesTo(this)
    }

}
