package com.example.test.mvvmsampleapp.view.ui

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.test.mvvmsampleapp.R
import com.example.test.mvvmsampleapp.databinding.FragmentProjectListBinding
import com.example.test.mvvmsampleapp.di.Injectable
import com.example.test.mvvmsampleapp.service.model.Project
import com.example.test.mvvmsampleapp.view.adapter.ProjectAdapter
import com.example.test.mvvmsampleapp.view.callback.ProjectClickCallback
import com.example.test.mvvmsampleapp.viewmodel.ProjectListViewModel

import javax.inject.Inject

/**
 * Fragment with a list of GitHub repositories.
 */
class ProjectListFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var projectAdapter: ProjectAdapter
    private lateinit var binding: FragmentProjectListBinding

    companion object {
        val TAG = "ProjectListFragment"
    }

    private val projectClickCallback = object : ProjectClickCallback {
        override fun onClick(project: Project) {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                (activity as MainActivity).show(project)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project_list, container, false)

        projectAdapter = ProjectAdapter(projectClickCallback)
        binding.projectList.adapter = projectAdapter
        binding.isLoading = true

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ProjectListViewModel::class.java)

        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: ProjectListViewModel) {
        // Update the list when the data changes
        viewModel.projectListObservable.observe(this, Observer { projects ->
            if (projects != null) {
                binding.isLoading = false
                projectAdapter.setProjectList(projects)
            }
        })
    }
}
