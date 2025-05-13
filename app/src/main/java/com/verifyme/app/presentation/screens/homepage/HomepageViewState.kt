package com.verifyme.app.presentation.screens.homepage

data class LoginViewState(
    var storeInformation: StoreInformation? = null,
    var data: Boolean = false,
)


data class StoreInformation(
    var storeId : String,
    var totalAttempt : Int = 0,
    var totalVerified : Int = 0,
    var totalPaid : Double = 0.0,
    var totalPending : Double = 0.0,
)