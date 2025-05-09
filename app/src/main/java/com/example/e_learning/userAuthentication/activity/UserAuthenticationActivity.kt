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

//        stepView = findViewById(R.id.step_view)
//
//        val navController = findNavController(R.id.fragment_container)
//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            when (destination.id) {
//                R.id.loginFragment -> stepView.visibility = View.GONE
//                R.id.stepOneFragment -> {
//                    stepView.visibility = View.VISIBLE
//                    stepView.go(0, true)
//                }
//                R.id.stepTwoFragment -> stepView.go(1, true)
//                R.id.stepThreeFragment -> stepView.go(2, true)
//                else -> stepView.visibility = View.GONE
//            }
//        }

    }
}
