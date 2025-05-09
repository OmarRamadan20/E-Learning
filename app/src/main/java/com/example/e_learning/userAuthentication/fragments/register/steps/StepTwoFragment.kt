package com.example.e_learning.userAuthentication.fragments.register.steps

import android.animation.Animator
import android.animation.ObjectAnimator
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController

import com.example.e_learning.R
import com.example.e_learning.databinding.FragmentStepTwoBinding
import com.example.e_learning.userAuthentication.fragments.register.RegisterFragmentViewModel
import com.shuhart.stepview.StepView
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar


@AndroidEntryPoint
class StepTwoFragment : Fragment() {


    private lateinit var binding: FragmentStepTwoBinding
    private val viewModel: RegisterFragmentViewModel by activityViewModels()

    private lateinit var stepView: StepView


    private var dob:String?=""
    private var sex: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentStepTwoBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()


        stepView = view.findViewById(R.id.stepView)

        val steps = listOf("Personal Info", "Additional Info", "Picture")
        binding.stepView.setSteps(steps)
        binding.stepView.go(1, true)


        binding.BODEdittext.setOnClickListener {
                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                val datePickerDialog = DatePickerDialog(
                    requireContext(),
                    { _, selectedYear, selectedMonth, selectedDay ->
                        val selectedDate = "${selectedDay}/${selectedMonth + 1}/${selectedYear}"
                        binding.BODEdittext.setText(selectedDate)
                        dob = "${year}-${(month + 1).toString()
                            .padStart(2, '0')}-${day.toString().padStart(2, '0')}"
                    },
                    year, month, day
                )

                datePickerDialog.show()
        }

        binding.genderEdittext.setOnClickListener {
            val options = arrayOf("male", "female")
            AlertDialog.Builder(requireContext())
                .setTitle("Select Gender")
                .setItems(options) { dialog, which ->
                    // Set the selected gender to the TextView
                    binding.genderEdittext.setText(options[which])
                    sex = options[which] // Store the selected gender
                }
                .show()
        }


        binding.nextButton.setOnClickListener {

            animateClickEffect(it)
            val mobileNumber = binding.phoneEdittext.text.toString()
            val city = binding.cityEdittext.text.toString()
            val gov=binding.govEdittext.text.toString()
            Log.e("Sex and Dob", "Sex: $sex, DOB: $dob ")

            val next= viewModel.updateStep2(dob?:"", sex?:"", mobileNumber, city,gov)
            Log.e("StepTwoFragment", "DOB: $dob, Sex: $sex, Mobile Number: $mobileNumber, City: $city")

            Log.e("Next","$next")
            if (next) {
                findNavController().navigate(R.id.action_stepTwoFragment_to_stepThreeFragment)
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