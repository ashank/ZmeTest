package com.funhotel.kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.async

class Main2Activity : AppCompatActivity() {

    var a: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*setContentView(R.layout.activity_main2)*/

        verticalLayout {
            padding = 20
            var view = textView {
                textSize = 20.0f
                text = "sadasdas"
                onClick { toast("sdsdsd") }
                lparams {
                    width = 890
                    height = 90
                    leftMargin = 20
                }
            }

            var et = editText {
                hint = "moren"
                onClick {}
                lparams {}
            }
        }


        async {
            uiThread {
                toast("sdsdsd")

            }

            uiThread {
                toast("1214u2oi4uiou4")
            }
        }


    }
}

