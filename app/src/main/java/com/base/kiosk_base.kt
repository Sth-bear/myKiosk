package com.base


import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.IllegalArgumentException
private val allmenus: ArrayList<AbstractMenu> by lazy { allMenus() } //저번 피드백과 이번 학습으로 넣긴했으나, 아직 어떤값에 들어가는지 잘 모르겠음.  lateinit var 또한 마찬가지. 함수밖에서 전역에 선언되는 값에만 쓸 수 있는것으로 이해중 매번 초기화 하는 값을 절약한다만 이해
lateinit var counter: Counter // lateinit은 초기화를 미뤄서 사용가능하게 해준다.
fun main() {
    val cart = Cart()
    counter = Counter(cart) //그래도 최초 초기화는 진행해야한다. 주의. cart보다 위에있으면 자료값을 제대로 못받아옴. 조사필요
    var select : Int = 0
    val cartcheck = GlobalScope.launch {
        while(true) {
            delay(10000) //너무 자주나와서 기존 2배로 변경
            println("현재 주문 대기수 : ${cart.check()}")
        }
    }

    while(true) {
        val category = arrayListOf("Burgers       ", "Forzen Custard", "Drinks        ", "Beer          ")
        val information = arrayListOf("앵거스 비프 통살을 다져만든 버거", "매장에서 신선하게 만드는 아이스크림", "매장에서 직접 만드는 음료", "뉴욕 브루클린 브루어리에서 양조한 맥주")
        println("[SHAKESHACK MENU]")
        var max = category.size
        for (i in 0 until max) {
            println("${i+1}.${category[i]} || ${information[i]}")
        }
        if(cart.isNotEmpty()) { //장바구니에 있는 경우에만 등장하게 처리.(자동 예외처리)
            println("5.Pay            || 결제하기")
            max = category.size + 1 //입력값 예외도 변경
        }
        println("0.Exit           || 프로그램 종료")
        try {
            val input = readln()!!.toInt()
            if (input > max){
                println("올바른 수를 입력해주세요")
                continue
            }
            select = input
        } catch (e: IllegalArgumentException) {
            println("숫자를 입력하세요")
            continue
        }
        when(select){
            0 -> {
                println("프로그램을 종료합니다.")
                break
            }
            1 -> mainLoop(menuCategory = "Hamburger", categoryName = "Burger", menu = {it is HamBurger}, cart = cart)
            2 -> mainLoop(menuCategory = "Frozen", categoryName = "Frozen Custard", menu = {it is Frozen}, cart = cart)
            3 -> mainLoop(menuCategory = "Drink", categoryName = "Drinks", menu = {it is Drink}, cart = cart)
            4 -> mainLoop(menuCategory = "Beer", categoryName = "Beer", menu = {it is Beer}, cart = cart)
            5 -> if (cart.isNotEmpty()) {
                while (true) {
                    counter.checkOutDisplay()
                    try {
                        val input = readln()!!.toInt()
                        if (input == 2) {
                            println("취소합니다.")
                            break
                        }
                        if (input > 2) {
                            println("올바른 값을 입력해주세요")
                            continue
                        }
                    } catch (e: IllegalArgumentException) {
                        println("올바른 숫자를 입력해주세요")
                        continue
                    }
                    println("금액을 넣어주세요")
                    try {
                        val input = readln()!!.toInt()
                        counter.checkOut(money = input)
                        break

                    } catch (e: IllegalArgumentException) {
                        println("올바른 금액을 입력해주세요")
                        continue
                    }
                }

            }
            else -> throw IllegalArgumentException("Nope") // 찾아본 결과 throw 사용시 종료되기에, 이를 원치않으면 반드시 뒤에 catch가 필요하다고 함. 추가적인 조사 필요. 단, 이 경우 오류가 날 경우를 직접 선언하고, 해당 코드를 내가 정한것.
        }
    }
}

//fun firstDisplay() { //첫 화면 표시
//    val category = arrayListOf("Burgers       ", "Forzen Custard", "Drinks        ", "Beer          ")
//    val information = arrayListOf("앵거스 비프 통살을 다져만든 버거", "매장에서 신선하게 만드는 아이스크림", "매장에서 직접 만드는 음료", "뉴욕 브루클린 브루어리에서 양조한 맥주")
//    println("[SHAKESHACK MENU]")
//    for (i in 0 until category.size) {
//        println("${i+1}.${category[i]} || ${information[i]}")
//    }
//    println("0.Exit           || 프로그램 종료")
//}

fun showMenu(menuType: String) {
    for (item in allmenus) {
        when (menuType) {
            "Hamburger" -> {
                if (item is HamBurger) {
                    MainMenu(item).mainMenu()
                }
            }
            "Frozen" -> {
                if (item is Frozen) {
                    MainMenu(item).mainMenu()
                }
            }
            "Drink" -> {
                if (item is Drink) {
                    MainMenu(item).mainMenu()
                }
            }
            "Beer" -> {
                if (item is Beer) {
                    MainMenu(item).mainMenu()
                }
            }
        }
    }
}

fun allMenus(): ArrayList<AbstractMenu> { //해당 방법을 위해 각 클래스 파일구조 형식 변경 -> 참조. Hamburger
    val menus = ArrayList<AbstractMenu>()

    val hamburger= arrayListOf<AbstractMenu>(//모든 이름의 가시성을 위해 동일한 여백을 부여해봄. 해당 방식말고, 함수로 처리할 수 있는지 추가 공부 필요(카트에 담기는 값 기준)
        HamBurger(index = 1, name = "ShackBurger           ", price = 6.9, info = "토마토, 양상추, 쉑소스가 토핑된 치즈버거"),
        HamBurger(index = 2, name = "SmokeShack            ", price = 8.9, info = "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"),
        HamBurger(index = 3, name = "ShroomBurger          ", price = 9.4, info = "몬스터 치즈와 체다 치즈로 속을 채운 베지테리안 버거"),
        HamBurger(index = 4, name = "CheeseBurger          ", price = 6.9, info = "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"),
        HamBurger(index = 5, name = "Hamburger             ", price = 5.4, info = "비프패티를 기반으로 야채가 들어간 기본버거")
    )
    val frozen = arrayListOf<AbstractMenu>(
        Frozen(index = 1, name ="Classic Shakes        ", 6.2, "쫀득하고 진한 커스터드가 들어간 클래식 쉐이크"),
        Frozen(index = 2, name ="Floats                ", 6.2, "부드러운 바닐라 커스터드와 톡톡 터지는 탄산이 만나 탄생한 색다른 음료"),
        Frozen(index = 3, name ="Shake of the Week     ", 6.5, "특별한 커스터드 플레이버"),
        Frozen(index = 4, name ="Red Bean Shake        ", 6.5, "신선한 커스터드와 함께 우유와 레드빈이 블렌딩 된 시즈널 쉐이크")
    )
    val drink = arrayListOf<AbstractMenu>(
        Drink(index = 1,name = "Shack-made Lemonade   ",price1 = 3.9,size1= "Regular", price2 = 4.5, size2 = "Large", info = "매장에서 직접 만드는 상큼한 레몬에이드"),
        Drink(index = 2,name = "Fresh Brewed Iced Tea ",price1 = 3.4,size1= "Regular", price2 = 3.9, size2 = "Large", info = "직접 유기농 홍차를 우려낸 아이스티"),
        Drink(index = 3,name = "Fifty/Fifty           ",price1 = 3.5,size1= "Regular", price2 = 4.4, size2 = "Large", info = "레몬에이드와 아이스티의 만남"),
        Drink(index = 4,name = "Fountain Soda         ",price1 = 2.7,size1= "Regular", price2 = 3.3, size2 = "Large", info = "코카콜라, 코카콜라 제로, 스프라이트, 환타 오렌지, 환타 그래이프")
    )
    val beer = arrayListOf<AbstractMenu>(
        Beer(index = 1, name = "ShockMeister Ale     ", price = 9.8, taste = "Bottle", info = "쉐이크쉑 버거를 위해 뉴욕 브루클린에서 특별히 양조한 에일 맥주"),
        Beer(index = 2, name = "Magpie Brewing Co.   ", price = 6.8, taste = "Pale Ale, Draft", info = "")
    )

    menus.addAll(hamburger)
    menus.addAll(frozen)
    menus.addAll(drink)
    menus.addAll(beer)
    return menus
}

fun addCart(menuCategory: String, itemIndex:Int, cart:Cart) {
    val filteredMenus = when (menuCategory) {
        "Hamburger" -> allmenus.filterIsInstance<HamBurger>() //위 처럼 정리한 경우 접근할려면 함수명.filterIsInstance<클래스명>이 필요했음.
        "Frozen" -> allmenus.filterIsInstance<Frozen>()
        "Drink" -> allmenus.filterIsInstance<Drink>()
        "Beer" -> allmenus.filterIsInstance<Beer>()
        else -> emptyList()
    }
    if (itemIndex in 1..filteredMenus.size) { //위의 클래스의 길이안의 값만 정상입력값.
        val selectedItem = filteredMenus[itemIndex - 1]
        cart.addItem(selectedItem)
    }
}

fun mainLoop(menuCategory: String,categoryName:String,menu:(AbstractMenu) -> Boolean, cart: Cart) { //여러 루프를 한번에 처리. menu값을 접근하기 위한 방법. 무작정 따라한 것. 조사 필요
    while (true) {
        println("[$categoryName]")
        showMenu(menuCategory)
        println("0.Back  ||  돌아가기")
        try {
            val input = readLine()!!.toInt()
            if (input == 0) {
                println("메인메뉴로 돌아갑니다")
                break
            } else if (input in 1..allmenus.filter(menu).size) {
                addCart(menuCategory = menuCategory, itemIndex = input, cart = cart)
                cart.displayCart()
            } else {
                println("올바른 수를 입력해주세요")
            }
        } catch (e: NumberFormatException) {
            println("숫자를 입력하세요")
        }
    }
}

//fun hamburgerDisplay() {  위의 allMenus를 쓰기위해 변경함.
//    val name = arrayListOf("1.ShackBurger ", "2.SmokeShack  ","3.ShroomBurger","4.CheeseBurger","5.Hamburger   ")
//    val price = arrayListOf(6.9, 8.9, 9.4, 6.9, 5.4)
//    val info = arrayListOf("토마토, 양상추, 쉑소스가 토핑된 치즈버거", "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거", "몬스터 치즈와 체다 치즈로 속을 채운 베지테리안 버거", "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거", "비프패티를 기반으로 야채가 들어간 기본버거")
//    for (i in 0 until name.size){
//        mainmenu.mainMenu(name= name[i],price = price[i],info = info[i],HamBurger())
//    }
//    println("0.Back         || 뒤로가기")
//}










