package com.base

open class Cart {
    private val items = mutableListOf<AbstractMenu>()

    fun addItem(item: AbstractMenu) {
        items.add(item)
        println("${item.name}가 카트에 추가 되었습니다.")
    }
    fun displayCart() {
        println("[Cart]")
        for((index, item) in items.withIndex()) {
            println("${index + 1}. ${item.name} || W${item.price}")
        }
    }

    fun isNotEmpty(): Boolean {
        return items.isNotEmpty()
    }

    fun totalPrice() : Double {
        var total: Double = 0.0
        for(item in items) {
            total += item.price
        }
        return total
    }

    fun clear() {
        items.clear()
    }
    fun check(): Int {
        return items.size
    }
}