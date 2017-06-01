package com.example.test.mvvmsampleapp.viewmodel;

import android.app.Application;

import com.example.test.mvvmsampleapp.service.model.Project;
import com.example.test.mvvmsampleapp.service.repository.ProjectRepository;

import java.util.List;

import io.reactivex.Single;

public class ProjectListViewModel {
    private final Single<List<Project>> projectListObservable;

    public ProjectListViewModel(Application application) {
        System.out.println("Getting project List ...");
        projectListObservable = ProjectRepository.getInstance().getProjectList("Google");
    }

    /**
     * Expose the Observable Projects query so the UI can observe it.
     */
    public Single<List<Project>> getProjectListObservable() {
        return projectListObservable;
    }
}
