package com.example.test.mvvmsampleapp.service.model

import java.util.*

class Project {
    var id: Long = 0
    var name: String? = null
    var full_name: String? = null
    var owner: User? = null
    var html_url: String? = null
    var description: String? = null
    var url: String? = null
    var created_at: Date? = null
    var updated_at: Date? = null
    var pushed_at: Date? = null
    var git_url: String? = null
    var ssh_url: String? = null
    var clone_url: String? = null
    var svn_url: String? = null
    var homepage: String? = null
    var stargazers_count: Int = 0
    var watchers_count: Int = 0
    var language: String? = null
    var has_issues: Boolean = false
    var has_downloads: Boolean = false
    var has_wiki: Boolean = false
    var has_pages: Boolean = false
    var forks_count: Int = 0
    var open_issues_count: Int = 0
    var forks: Int = 0
    var open_issues: Int = 0
    var watchers: Int = 0
    var default_branch: String? = null

    constructor() {}

    constructor(name: String) {
        this.name = name
    }
}
