package com.example.e_learning.userAuthentication.fragments.login

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.e_learning.MainActivity
import com.example.e_learning.R
import com.example.e_learning.base.BaseFragment
import com.example.e_learning.databinding.FragmentLoginBinding
import com.example.e_learning.userAuthentication.fragments.register.steps.StepOneFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginFragmentViewModel>() {
    private val mViewModel : LoginFragmentViewModel by viewModels()

    override fun initViewModel(): LoginFragmentViewModel {
        return mViewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_login
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        hideKeyboard()
        initViews()
        observeViewModel()
        val text = "Don't have account? Sign Up"
        val spannable = SpannableString(text)
        spannable.setSpan(StyleSpan(Typeface.BOLD), 20, 27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.donTHaveAnAccountTv.text = spannable

        binding.donTHaveAnAccountTv.setOnClickListener {
            viewModel.doAction(LoginContract.Action.NavigateToRegister)
        }
        binding.loginBtn.setOnClickListener {
            animateClickEffect(it)
            viewModel.doAction(
                LoginContract.Action.NavigateToHome(
                    binding.emailEdittext.text.toString(), binding.passwordEdittext.text.toString()
                )
            )
        }
    }

    private fun observeViewModel() {
       lifecycleScope.launch {
           viewModel.viewState.collect{state->
               when(state){
                   is LoginContract.ViewState.Loading -> {
                   }
                   is LoginContract.ViewState.NavigatingToRegister -> {
                       navigateToRegister()
                   }
                   is LoginContract.ViewState.Error -> {
                       showErrorView(state.message)
                   }
                   is LoginContract.ViewState.Success -> {
                       showSuccessView()
                       val intent = Intent(requireContext(), MainActivity::class.java)
                       startActivity(intent)
                       requireActivity().finish()
                   }
                   LoginContract.ViewState.Idle -> {

                   }
               }
           }
       }
        lifecycleScope.launch {
            viewModel.event.collect { event ->
                when (event) {
                    is LoginContract.Event.ShowMessage -> {
                        Toast.makeText(requireContext(), event.viewMessage.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }


    private fun navigateToRegister() {
        findNavController().navigate(R.id.action_loginFragment_to_stepOneFragment)

    }


    private fun initViews() {
        binding.vm = mViewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }


    private fun showSuccessView() {
        binding.icNext.isVisible = false
        binding.progressBar.isVisible = true
    }

    private fun showErrorView(message: String) {
        binding.icNext.isVisible = true
        binding.progressBar.isVisible = false
//        binding.emailEdittext.error = message
//        binding.passwordEdittext.error = message
        Toast.makeText(requireContext(),message,Toast.LENGTH_LONG).show()

    }
    private fun showLoadingView() {
        binding.icNext.isVisible = false
        binding.progressBar.isVisible = true
    }



//    private fun hideKeyboard() {
//        view?.hideKeyboard(activity as AppCompatActivity?)
//    }

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
