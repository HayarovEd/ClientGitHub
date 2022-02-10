package com.edurda77.ClientGitHub.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RepoGitHubModel(
    @SerializedName("owner")
    val owner: ProfileOfGitHub,
    @SerializedName("url")
    val urlRepo: String
) : Serializable
