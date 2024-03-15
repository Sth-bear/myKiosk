package com.example.mykiosk

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import java.util.Date

class MainActivity : AppCompatActivity() {
    lateinit var tab1 : Hambuger
    lateinit var tab2 : Chicken
    lateinit var tab3 : Drink
    lateinit var tab4 : Side


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)

        tab1 = Hambuger()
        tab2 = Chicken()
        tab3 = Drink()
        tab4 = Side()

        fun replaceView(tab:Fragment) {
            var selectedFragment: Fragment? = null
            selectedFragment = tab
            selectedFragment?.let {
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout, it).commit()
            }
        }


        supportFragmentManager.beginTransaction().add(R.id.frameLayout, tab1).commit()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position) {
                    0 -> {
                        replaceView(tab1)
                    }
                    1 -> {
                        replaceView(tab2)
                    }
                    2 -> {
                        replaceView(tab3)
                    }
                    3 -> {
                        replaceView(tab4)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

        //리사이클러뷰 테스트

        val testImg = resources.getDrawable(R.drawable.candrink)
        val testImg2 = resources.getDrawable(R.drawable.cheesestick)

        val list = ArrayList<BasketData>()
        list.add(BasketData("aa",testImg,true))
        list.add(BasketData("ac",testImg2,false))

        val adapter = BasketAdapter(list)
        val recyclerView: RecyclerView = findViewById(R.id.rv_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter



    }
}

