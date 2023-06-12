package com.example.imgurapidemo.utils

import com.example.imgurapidemo.utils.Const.DETAIL_SCREEN
import com.example.imgurapidemo.utils.Const.HOME_SCREEN

sealed  class Route(val route: String) {
    object Home: Route(HOME_SCREEN)
    object Detail: Route(DETAIL_SCREEN)
}