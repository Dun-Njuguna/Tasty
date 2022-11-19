package com.dunk.eats.domain.model

sealed class UIComponentType{
    object Dialog: UIComponentType()
    object SnackBar: UIComponentType()
    object Toast: UIComponentType()
    object None: UIComponentType()
}
