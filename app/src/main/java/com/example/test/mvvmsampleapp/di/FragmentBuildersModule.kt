package com.example.test.mvvmsampleapp.di

import com.example.test.mvvmsampleapp.view.ui.ProjectFragment
import com.example.test.mvvmsampleapp.view.ui.ProjectListFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    internal abstract fun contributeProjectFragment(): ProjectFragment

    @ContributesAndroidInjector
    internal abstract fun contributeProjectListFragment(): ProjectListFragment

}