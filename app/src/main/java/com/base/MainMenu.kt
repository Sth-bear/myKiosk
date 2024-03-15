package com.base

class MainMenu(private val display: AbstractMenu) {
    fun mainMenu() {
        display.displayinfo()
    }
}