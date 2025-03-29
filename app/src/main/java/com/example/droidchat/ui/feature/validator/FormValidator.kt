package com.example.droidchat.ui.feature.validator

interface FormValidator<FormState> {
    fun validate(formState: FormState): FormState
}