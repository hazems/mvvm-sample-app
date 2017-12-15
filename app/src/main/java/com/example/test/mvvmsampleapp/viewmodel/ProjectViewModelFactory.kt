package com.example.test.mvvmsampleapp.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.v4.util.ArrayMap

import com.example.test.mvvmsampleapp.di.ViewModelSubComponent
import java.util.concurrent.Callable

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectViewModelFactory @Inject
constructor(viewModelSubComponent: ViewModelSubComponent) : ViewModelProvider.Factory {

    private val creators: ArrayMap<Class<*>, Callable<out ViewModel>> = ArrayMap()

    init {
        // View models cannot be injected directly because they won't be bound to the owner's view model scope.
        creators.put(ProjectViewModel::class.java,
                Callable<ViewModel> { viewModelSubComponent.projectViewModel() })
        creators.put(ProjectListViewModel::class.java,
                Callable<ViewModel> { viewModelSubComponent.projectListViewModel() })
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        var creator: Callable<out ViewModel>? = creators[modelClass]

        if (creator == null) {
            for ((key, value) in creators) {
                if (modelClass.isAssignableFrom(key)) {
                    creator = value
                    break
                }
            }
        }

        if (creator == null) {
            throw IllegalArgumentException("Unknown model class " + modelClass)
        }

        try {
            return creator.call() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
}