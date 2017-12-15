package com.example.test.mvvmsampleapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

import com.example.test.mvvmsampleapp.service.model.Project
import com.example.test.mvvmsampleapp.service.repository.ProjectRepository

import javax.inject.Inject

class ProjectListViewModel
@Inject constructor(projectRepository: ProjectRepository,
                    application: Application) : AndroidViewModel(application) {

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    val projectListObservable: LiveData<List<Project>> =
            projectRepository.getProjectList("Google")

}
