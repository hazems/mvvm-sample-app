package com.example.test.mvvmsampleapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.databinding.ObservableField
import android.util.Log

import com.example.test.mvvmsampleapp.service.model.Project
import com.example.test.mvvmsampleapp.service.repository.ProjectRepository

import javax.inject.Inject

class ProjectViewModel @Inject
constructor(projectRepository: ProjectRepository,
            application: Application) : AndroidViewModel(application) {

    val observableProject: LiveData<Project>
    private var projectID: MutableLiveData<String> = MutableLiveData()

    var project = ObservableField<Project>()

    companion object {

        private val TAG = ProjectViewModel::class.java.name
        private val ABSENT = MutableLiveData<Project>()

        init {
            ABSENT.value = null
        }
    }

    init {
        observableProject = Transformations.switchMap(projectID) { input ->

            if (input.isEmpty()) {
                Log.i(TAG, "ProjectViewModel projectID is absent!!!")
                return@switchMap ABSENT
            }

            Log.i(TAG, "ProjectViewModel projectID is " + projectID.value)
            projectRepository.getProjectDetails("Google", projectID.value)
        }
    }

    fun setProject(project: Project) {
        this.project.set(project)
    }

    fun setProjectID(projectID: String?) {
        this.projectID.value = projectID
    }
}
