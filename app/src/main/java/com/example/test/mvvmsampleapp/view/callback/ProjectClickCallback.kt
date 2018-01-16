package com.example.test.mvvmsampleapp.view.callback

import com.example.test.mvvmsampleapp.service.model.Project

interface ProjectClickCallback {
    fun onClick(project: Project)
}
