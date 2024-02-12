package com.example.androidhw02

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView

class CustomView (context: Context,
                  attrs: AttributeSet? = null,
) : LinearLayout(context, attrs){
    private var topString: TextView? = null
    private var bottomString: TextView? = null

    init {
        inflate(context, R.layout.custom_view, this)
        topString = findViewById(R.id.top_string)
        bottomString = findViewById(R.id.bottom_string)
    }


    fun setTopString(title: String) {
        this.topString!!.text = title
    }

    fun setBottomString(name: String) {
        this.bottomString!!.text = name
    }
}