package com.edurda77.ClientGitHub.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProfileOfGitHub(
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatar: String
):Serializable
