package com.example.test.mvvmsampleapp.di;

import com.example.test.mvvmsampleapp.view.ui.ProjectFragment;
import com.example.test.mvvmsampleapp.view.ui.ProjectListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract ProjectFragment contributeProjectFragment();

    @ContributesAndroidInjector
    abstract ProjectListFragment contributeProjectListFragment();
}
