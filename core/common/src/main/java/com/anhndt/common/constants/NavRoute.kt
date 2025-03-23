package com.anhndt.common.constants

sealed class NavRoute(val route: String) {
    data object MainRoute : NavRoute("main")
    data object HomeRoute : NavRoute("home")
    data object SettingRoute : NavRoute("setting")
    data object DetailRoute : NavRoute("detail")
}