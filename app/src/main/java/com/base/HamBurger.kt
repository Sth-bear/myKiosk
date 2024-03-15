package com.base

class HamBurger(val index: Int, override val name: String, override val price: Double, val info: String): AbstractMenu() {
    override fun displayinfo() {
        println("${index}.${name} || W${price} || ${info}")
    }
}

//class HamBurger: AbstractMenu() {
//    override fun displayinfo(name: String, price: Double, info: String) {  너무 많은 선언되지 않은 변수라는 오류가 발생함. 위와같이 앞에서 필요한 값들을 선언하게 교체(이전 피드백에 있었던 내용 +)
//        println("${name} || W${price} || ${info}")
//    }
//}