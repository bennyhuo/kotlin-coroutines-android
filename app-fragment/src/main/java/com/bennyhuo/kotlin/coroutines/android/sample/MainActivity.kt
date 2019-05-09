package com.bennyhuo.kotlin.coroutines.android.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bennyhuo.kotlin.coroutines.android.mainscope.BasicScoped

class MainActivity : AppCompatActivity(), BasicScoped{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, MainFragment()).addToBackStack("Main").commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()

    }

}
