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

//init {   양동원님 -> 출력할 때 칸수를 맞추는 방법. init은 접두사처럼 맨앞에 붙어서 나오는것을 활용, 제일 긴 길이를 매번 구하고, 그 칸수만큼 공백을 뒤에 추가하는것.
//    foodsNumberOfTypes = menuList.size
//    menuList.forEach { it ->
//        if (longNameLength < it.length) longNameLength = it.length
//    }
//}
//override fun print() {
//    println("[ Burgers MENU ]")
//    menuList.forEachIndexed { index, burger ->
//        var printBurger = "${index + 1}. $burger"
//
//        while(printBurger.length <= longNameLength+3) printBurger += " "
//        println("${printBurger}| W ${burgersPrice[burger]} | ${burgersDescription[burger]}")
//    }
//    println("0. 뒤로가기      | 뒤로가기")
//}