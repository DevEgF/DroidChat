package com.example.droidchat.data.mapper

import com.example.droidchat.data.network.model.CreateAccountRequest
import com.example.droidchat.data.network.model.ImageResponse
import com.example.droidchat.domain.model.CreateAccount
import com.example.droidchat.domain.model.Image

fun CreateAccount.toCreateAccountRequest() =
    CreateAccountRequest(
        username = username,
        password = password,
        firstName = firstName,
        lastName = lastName,
        profilePictureId = profilePictureId
    )

fun ImageResponse.toImageDomain() =
    Image(
        id = id,
        name = name,
        type = type,
        url = url
    )

fun Image.toImageResponse() =
    ImageResponse(
        id = id,
        name = name,
        type = type,
        url = url
    )