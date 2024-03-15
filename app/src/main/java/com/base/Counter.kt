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
        val totalPrice: Int = cart.totalPrice().toInt()*1000
        cart.displayCart()
        println("총 결제 금액은 ${totalPrice}원 입니다.")
        println("위와 같이 주문 하시겠습니까?")
        println("1. 주문하기    2. 쇼핑 계속하기")
    }

    fun checkOut(money: Int) {
        val totalPrice: Int = cart.totalPrice().toInt()*1000
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
                wait.join()
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