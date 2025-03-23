package com.anhndt.common.constants

sealed class Destination(val path: String) : BaseDestination(path) {
    data object Quotes : Destination(path = "quotes")
    data object QuoteDetail : Destination(path = "quote_detail"){
        const val author = "author"
        const val quote = "quote"
    }
    data object Favorite : Destination(path = "favorite")
    data object Profile : Destination(path = "profile")

}