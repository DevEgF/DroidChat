package com.example.droidchat.data.mapper

import com.example.droidchat.data.network.model.CreateAccountRequest
import com.example.droidchat.domain.model.CreateAccount

fun CreateAccount.toCreateAccountRequest() =
    CreateAccountRequest(
        username = username,
        password = password,
        firstName = firstName,
        lastName = lastName,
        profilePictureId = profilePictureId
    )