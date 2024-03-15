package com.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.time.TimeSource

class Counter(private val cart: Cart) {
    val currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
    fun checkOutDisplay() {
        val totalPrice: Int = cart.totalPrice().toInt()*1000 //double로 표시했다. 값을 맞춰주자
        cart.displayCart() // 코틀린에서는 입력칸의 힌트를 쓰는방법을 찾지못했다.
        println("총 결제 금액은 ${totalPrice}원 입니다.")
        println("위와 같이 주문 하시겠습니까?")
        println("1. 주문하기    2. 쇼핑 계속하기")
    }

    fun checkOut(money: Int) {
        val totalPrice: Int = cart.totalPrice().toInt()*1000 // 해당값이 반복되었다. 그러나, 함수밖에 선언할 경우(class안) 초기값인 0으로 기억한다. 차라리 Cart.kt의 totalPrice()의 식에서 해당부분을 추가하고 받는것이 맞다.
        if (money >= totalPrice) {
            println("결제 중 입니다.")
            val wait = CoroutineScope(Dispatchers.Default).launch {
                val delay = async(Dispatchers.IO) {
                    delay(3000)
                    "결제를 완료했습니다."
                }
                println("${delay.await()}  ${currentTime}")
            }
            runBlocking {
                wait.join() //Blocking을 하지않으면, 결제완료는 3초뒤에 뜨는게 맞으나, 반복문이 종료되어 초기화면이 나온다. 임의로 막아두자. 다른 방법은 없을까?
                cart.clear() //장바구니 비우기
            }
        }
        else {
            println("잔액이 부족합니다.")
        }
    }
}

//class Counter: Cart() {   다중상속이 불가능함.
//    fun checkOut(money: Int) {
//        val totalPrice = totalPrice()
//        println(totalPrice)
//    }
//}