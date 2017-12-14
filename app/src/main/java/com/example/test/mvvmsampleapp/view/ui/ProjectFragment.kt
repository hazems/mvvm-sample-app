package com.example.test.mvvmsampleapp.view.ui

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
import com.example.test.mvvmsampleapp.databinding.FragmentProjectDetailsBinding
import com.example.test.mvvmsampleapp.di.Injectable
import com.example.test.mvvmsampleapp.viewmodel.ProjectViewModel
import javax.inject.Inject

/**
 * Fragment for a detailed info about a GitHub Repository.
 */
class ProjectFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentProjectDetailsBinding

    companion object {

        private val KEY_PROJECT_ID = "project_id"

        /** Creates project fragment for specific project ID  */
        fun newInstance(projectID: String?): ProjectFragment {
            val fragment = ProjectFragment()
            val args = Bundle()

            args.putString(KEY_PROJECT_ID, projectID)
            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate this data binding layout
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project_details, container, false)

        // Create and set the adapter for the RecyclerView.
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ProjectViewModel::class.java)

        viewModel.setProjectID(arguments?.getString(KEY_PROJECT_ID))

        binding.projectViewModel = viewModel
        binding.isLoading = true

        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: ProjectViewModel) {
        // Observe project data
        viewModel.observableProject.observe(this, Observer { project ->
            if (project != null) {
                binding.isLoading = false
                viewModel.setProject(project)
            }
        })
    }
}
