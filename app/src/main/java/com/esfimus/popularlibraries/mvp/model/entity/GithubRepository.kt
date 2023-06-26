package com.esfimus.popularlibraries.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubRepository(
    @Expose
    val allowForking: Boolean? = false,
    @Expose
    val archiveUrl: String? = "",
    @Expose
    val archived: Boolean? = false,
    @Expose
    val assigneesUrl: String? = "",
    @Expose
    val blobsUrl: String? = "",
    @Expose
    val branchesUrl: String? = "",
    @Expose
    val cloneUrl: String? = "",
    @Expose
    val collaboratorsUrl: String? = "",
    @Expose
    val commentsUrl: String? = "",
    @Expose
    val commitsUrl: String? = "",
    @Expose
    val compareUrl: String? = "",
    @Expose
    val contentsUrl: String? = "",
    @Expose
    val contributorsUrl: String? = "",
    @Expose
    val createdAt: String? = "",
    @Expose
    val defaultBranch: String? = "",
    @Expose
    val deploymentsUrl: String? = "",
    @Expose
    val description: String? = "",
    @Expose
    val disabled: Boolean? = false,
    @Expose
    val downloadsUrl: String? = "",
    @Expose
    val eventsUrl: String? = "",
    @Expose
    val fork: Boolean? = false,
    @Expose
    val forks: Int? = 0,
    @Expose
    val forksCount: Int? = 0,
    @Expose
    val forksUrl: String? = "",
    @Expose
    val fullName: String? = "",
    @Expose
    val gitCommitsUrl: String? = "",
    @Expose
    val gitRefsUrl: String? = "",
    @Expose
    val gitTagsUrl: String? = "",
    @Expose
    val gitUrl: String? = "",
    @Expose
    val hasDiscussions: Boolean? = false,
    @Expose
    val hasDownloads: Boolean? = false,
    @Expose
    val hasIssues: Boolean? = false,
    @Expose
    val hasPages: Boolean? = false,
    @Expose
    val hasProjects: Boolean? = false,
    @Expose
    val hasWiki: Boolean? = false,
    @Expose
    val homepage: String? = "",
    @Expose
    val hooksUrl: String? = "",
    @Expose
    val htmlUrl: String? = "",
    @Expose
    val id: Int? = 0,
    @Expose
    val isTemplate: Boolean? = false,
    @Expose
    val issueCommentUrl: String? = "",
    @Expose
    val issueEventsUrl: String? = "",
    @Expose
    val issuesUrl: String? = "",
    @Expose
    val keysUrl: String? = "",
    @Expose
    val labelsUrl: String? = "",
    @Expose
    val language: String? = "",
    @Expose
    val languagesUrl: String? = "",
    @Expose
    val license: Map<String, String> = mapOf(),
    @Expose
    val mergesUrl: String? = "",
    @Expose
    val milestonesUrl: String? = "",
    @Expose
    val mirrorUrl: String? = "",
    @Expose
    val name: String? = "",
    @Expose
    val nodeId: String? = "",
    @Expose
    val notificationsUrl: String? = "",
    @Expose
    val openIssues: Int? = 0,
    @Expose
    val openIssuesCount: Int? = 0,
    @Expose
    val owner: GithubUser = GithubUser(),
    @Expose
    val `private`: Boolean? = false,
    @Expose
    val pullsUrl: String? = "",
    @Expose
    val pushedAt: String? = "",
    @Expose
    val releasesUrl: String? = "",
    @Expose
    val size: Int? = 0,
    @Expose
    val sshUrl: String? = "",
    @Expose
    val stargazersCount: Int? = 0,
    @Expose
    val stargazersUrl: String? = "",
    @Expose
    val statusesUrl: String? = "",
    @Expose
    val subscribersUrl: String? = "",
    @Expose
    val subscriptionUrl: String? = "",
    @Expose
    val svnUrl: String? = "",
    @Expose
    val tagsUrl: String? = "",
    @Expose
    val teamsUrl: String? = "",
    @Expose
    val topics: List<String?>? = listOf(),
    @Expose
    val treesUrl: String? = "",
    @Expose
    val updatedAt: String? = "",
    @Expose
    val url: String? = "",
    @Expose
    val visibility: String? = "",
    @Expose
    val watchers: Int? = 0,
    @Expose
    val watchersCount: Int? = 0,
    @Expose
    val webCommitSignoffRequired: Boolean? = false
) : Parcelable