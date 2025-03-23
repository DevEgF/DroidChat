package com.example.droidchat.featurecore.validator

object EmailValidator {
    private const val EMAIL_REGEX = "^[A-Za-z](.*)([@])(.*)(\\.)(.+)"

    fun isValid(email: String): Boolean {
        return EMAIL_REGEX.toRegex().matches(email)
    }
}
