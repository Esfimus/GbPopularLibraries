package com.esfimus.popularlibraries.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(
    @Expose
    val avatar_url: String?,
    @Expose
    val events_url: String?,
    @Expose
    val followers_url: String?,
    @Expose
    val following_url: String?,
    @Expose
    val gists_url: String?,
    @Expose
    val gravatar_id: String?,
    @Expose
    val html_url: String?,
    @Expose
    val id: String?,
    @Expose
    val login: String?,
    @Expose
    val node_id: String?,
    @Expose
    val organizations_url: String?,
    @Expose
    val received_events_url: String?,
    @Expose
    val repos_url: String?,
    @Expose
    val site_admin: Boolean?,
    @Expose
    val starred_url: String?,
    @Expose
    val subscriptions_url: String?,
    @Expose
    val type: String?,
    @Expose
    val url: String?
) : Parcelable