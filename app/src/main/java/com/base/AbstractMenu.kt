package com.base

abstract class AbstractMenu {
    abstract val name: String // 해당 두 값을 Cart에 필수적으로 연결지어주기 위함
    abstract val price: Double
    abstract fun displayinfo()
}