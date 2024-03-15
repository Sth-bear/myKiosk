package com.base

class Drink(val index : Int, override val name : String, val price1: Double, val size1: String, val price2: Double, val size2: String, val info: String): AbstractMenu() {
    override val price: Double = price1
    override fun displayinfo() {
        println("${index}.${name} || ${info}|| ${size1} || W${price1}")
    }
}
