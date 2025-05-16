package com.example.e_learning.userAuthentication.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.e_learning.R
import com.shuhart.stepview.StepView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UserAuthenticationActivity : AppCompatActivity() {

    private lateinit var stepView: StepView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_authentication)
    }
}
