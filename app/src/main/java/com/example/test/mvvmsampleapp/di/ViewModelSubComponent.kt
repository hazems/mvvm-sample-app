package com.example.test.mvvmsampleapp.di

import com.example.test.mvvmsampleapp.viewmodel.ProjectListViewModel
import com.example.test.mvvmsampleapp.viewmodel.ProjectViewModel

import dagger.Subcomponent

/**
 * A sub component to create ViewModels. It is called by the
 * [com.example.test.mvvmsampleapp.viewmodel.ProjectViewModelFactory].
 */
@Subcomponent
interface ViewModelSubComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): ViewModelSubComponent
    }

    fun projectListViewModel(): ProjectListViewModel

    fun projectViewModel(): ProjectViewModel

}