package com.example.e_learning.userAuthentication.fragments.register.steps

import android.animation.Animator
import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.e_learning.R
import com.example.e_learning.databinding.FragmentStepOneBinding
import com.example.e_learning.userAuthentication.fragments.register.RegisterFragmentViewModel
import com.shuhart.stepview.StepView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StepOneFragment : Fragment() {

    private lateinit var binding: FragmentStepOneBinding
    private val viewModel: RegisterFragmentViewModel by activityViewModels()
    private lateinit var stepView: StepView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentStepOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initViews()


        // تحديد الخطوات باستخدام مصفوفة الخطوات
        val steps = listOf("Personal Info", "Additional Info", "Picture")
        binding.stepView.setSteps(steps)
        binding.stepView.go(0, true)



        binding.nextButton.setOnClickListener {
            animateClickEffect(it)
            val firstName = binding.firstNameEdittext.text.toString()
            val lastName = binding.lastNameEdittext.text.toString()
            val email = binding.emailEdittext.text.toString()
            val password = binding.passwordEdittext.text.toString()


           val next= viewModel.updateStep1(firstName,lastName,email, password)
            Log.d("StepOneFragment", "firstName: $firstName," +
                    " lastName: $lastName, email: $email, password: $password")

            if (next) {
                findNavController().navigate(R.id.action_stepOneFragment_to_stepTwoFragment)
            }
        }
    }
    private fun initViews() {
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

    }
    private fun animateClickEffect(view: View) {
        // Scale down animation (simulating button click)
        val scaleDownX = ObjectAnimator.ofFloat(view, "scaleX", 0.9f)
        val scaleDownY = ObjectAnimator.ofFloat(view, "scaleY", 0.9f)

        // Scale up animation (reset to original size)
        val scaleUpX = ObjectAnimator.ofFloat(view, "scaleX", 1f)
        val scaleUpY = ObjectAnimator.ofFloat(view, "scaleY", 1f)

        // Play both scale down and scale up animations together
        val animatorSet = android.animation.AnimatorSet()
        animatorSet.playTogether(scaleDownX, scaleDownY)

        animatorSet.duration = 100 // Duration for scale down animation
        animatorSet.start()

        // After scale down animation, start scale up animation
        animatorSet.addListener(object : android.animation.Animator.AnimatorListener {


            override fun onAnimationStart(animation: Animator) {
            }

            override fun onAnimationEnd(animation: Animator) {
                // Reset to original size after 100 milliseconds
                val resetAnimatorSet = android.animation.AnimatorSet()
                resetAnimatorSet.playTogether(scaleUpX, scaleUpY)
                resetAnimatorSet.duration = 100 // Duration for scale up animation
                resetAnimatorSet.start()
            }

            override fun onAnimationCancel(animation: Animator) {
            }

            override fun onAnimationRepeat(animation: Animator) {
                val resetAnimatorSet = android.animation.AnimatorSet()
                resetAnimatorSet.playTogether(scaleUpX, scaleUpY)
                resetAnimatorSet.duration = 100 // Duration for scale up animation
                resetAnimatorSet.start()
            }
        })
    }



}