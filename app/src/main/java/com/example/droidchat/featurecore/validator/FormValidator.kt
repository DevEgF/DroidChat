package com.example.droidchat.featurecore.validator

interface FormValidator<FormState> {
    fun validate(formState: FormState): FormState
}