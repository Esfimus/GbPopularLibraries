package com.esfimus.popularlibraries.mvp.model.entity


import com.google.gson.annotations.Expose

data class GithubRepository(
    @Expose
    val allowForking: Boolean?,
    @Expose
    val archiveUrl: String?,
    @Expose
    val archived: Boolean?,
    @Expose
    val assigneesUrl: String?,
    @Expose
    val blobsUrl: String?,
    @Expose
    val branchesUrl: String?,
    @Expose
    val cloneUrl: String?,
    @Expose
    val collaboratorsUrl: String?,
    @Expose
    val commentsUrl: String?,
    @Expose
    val commitsUrl: String?,
    @Expose
    val compareUrl: String?,
    @Expose
    val contentsUrl: String?,
    @Expose
    val contributorsUrl: String?,
    @Expose
    val createdAt: String?,
    @Expose
    val defaultBranch: String?,
    @Expose
    val deploymentsUrl: String?,
    @Expose
    val description: Any?,
    @Expose
    val disabled: Boolean?,
    @Expose
    val downloadsUrl: String?,
    @Expose
    val eventsUrl: String?,
    @Expose
    val fork: Boolean?,
    @Expose
    val forks: Int?,
    @Expose
    val forksCount: Int?,
    @Expose
    val forksUrl: String?,
    @Expose
    val fullName: String?,
    @Expose
    val gitCommitsUrl: String?,
    @Expose
    val gitRefsUrl: String?,
    @Expose
    val gitTagsUrl: String?,
    @Expose
    val gitUrl: String?,
    @Expose
    val hasDiscussions: Boolean?,
    @Expose
    val hasDownloads: Boolean?,
    @Expose
    val hasIssues: Boolean?,
    @Expose
    val hasPages: Boolean?,
    @Expose
    val hasProjects: Boolean?,
    @Expose
    val hasWiki: Boolean?,
    @Expose
    val homepage: Any?,
    @Expose
    val hooksUrl: String?,
    @Expose
    val htmlUrl: String?,
    @Expose
    val id: Int?,
    @Expose
    val isTemplate: Boolean?,
    @Expose
    val issueCommentUrl: String?,
    @Expose
    val issueEventsUrl: String?,
    @Expose
    val issuesUrl: String?,
    @Expose
    val keysUrl: String?,
    @Expose
    val labelsUrl: String?,
    @Expose
    val language: String?,
    @Expose
    val languagesUrl: String?,
    @Expose
    val license: Any?,
    @Expose
    val mergesUrl: String?,
    @Expose
    val milestonesUrl: String?,
    @Expose
    val mirrorUrl: Any?,
    @Expose
    val name: String?,
    @Expose
    val nodeId: String?,
    @Expose
    val notificationsUrl: String?,
    @Expose
    val openIssues: Int?,
    @Expose
    val openIssuesCount: Int?,
    @Expose
    val owner: GithubUser?,
    @Expose
    val `private`: Boolean?,
    @Expose
    val pullsUrl: String?,
    @Expose
    val pushedAt: String?,
    @Expose
    val releasesUrl: String?,
    @Expose
    val size: Int?,
    @Expose
    val sshUrl: String?,
    @Expose
    val stargazersCount: Int?,
    @Expose
    val stargazersUrl: String?,
    @Expose
    val statusesUrl: String?,
    @Expose
    val subscribersUrl: String?,
    @Expose
    val subscriptionUrl: String?,
    @Expose
    val svnUrl: String?,
    @Expose
    val tagsUrl: String?,
    @Expose
    val teamsUrl: String?,
    @Expose
    val topics: List<Any?>?,
    @Expose
    val treesUrl: String?,
    @Expose
    val updatedAt: String?,
    @Expose
    val url: String?,
    @Expose
    val visibility: String?,
    @Expose
    val watchers: Int?,
    @Expose
    val watchersCount: Int?,
    @Expose
    val webCommitSignoffRequired: Boolean?
)