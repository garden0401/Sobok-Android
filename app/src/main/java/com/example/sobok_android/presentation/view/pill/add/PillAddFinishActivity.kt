package com.example.sobok_android.presentation.view.pill.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sobok_android.R
import com.example.sobok_android.databinding.ActivityPillAddBinding
import com.example.sobok_android.presentation.base.BindingActivity
import com.example.sobok_android.presentation.base.BindingFragment

class PillAddFinishActivity : BindingActivity<ActivityPillAddBinding>(R.layout.activity_pill_add_finish) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pill_add_finish)
    }
}