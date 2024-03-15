package com.base

class Frozen(val index: Int, override val name : String, override val price: Double, val info: String): AbstractMenu() {
    override fun displayinfo() {
        println("${index}.${name} || W${price} || ${info}")
    }
}


