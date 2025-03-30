package com.example.droidchat.data.network.datasource

import com.example.droidchat.domain.model.exceptions.NetworkException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.statement.bodyAsText

suspend fun <T> safeApiCaller(block: suspend () -> T): T {
    return try {
        block()
    } catch (e: ClientRequestException){
        val errorMessage = e.response.bodyAsText()
        throw NetworkException.ApiException(errorMessage, e.response.status.value)
    } catch (e: Exception) {
        throw NetworkException.UnknownNetworkException(e)
    }
}