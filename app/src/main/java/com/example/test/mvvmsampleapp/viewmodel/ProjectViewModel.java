package com.example.test.mvvmsampleapp.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.example.test.mvvmsampleapp.service.model.Project;
import com.example.test.mvvmsampleapp.service.repository.ProjectRepository;

import io.reactivex.Single;

public class ProjectViewModel {
    private final Single<Project> projectObservable;
    private final String projectID;

    public ObservableField<Project> project = new ObservableField<>();

    public ProjectViewModel(@NonNull Application application, final String projectID) {
        System.out.println("Getting project details ...");
        this.projectID = projectID;
        projectObservable = ProjectRepository.getInstance().getProjectDetails("Google", projectID);
    }

    public Single<Project> getObservableProject() {
        return projectObservable;
    }

    public void setProject(Project project) {
        this.project.set(project);
    }
}
