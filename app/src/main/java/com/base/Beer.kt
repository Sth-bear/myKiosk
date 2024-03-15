package com.base

class Beer (val index: Int, override val name: String, override val price: Double, val taste: String, val info: String): AbstractMenu() { //name과 price는 부모가 필수로 가지게해둠. override를 통해 재선언
    override fun displayinfo() {
        println("${index}.${name} || W${price} || ${taste}|| ${info}")
    }
}