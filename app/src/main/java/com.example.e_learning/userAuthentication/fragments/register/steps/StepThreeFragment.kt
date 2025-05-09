package com.example.e_learning.userAuthentication.fragments.register.steps

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.e_learning.R
import com.example.e_learning.databinding.FragmentStepThreeBinding
import com.example.e_learning.userAuthentication.fragments.login.LoginFragment
import com.example.e_learning.userAuthentication.fragments.register.RegisterFragmentViewModel
import com.example.e_learning.userAuthentication.fragments.register.RegisterContract
import com.shuhart.stepview.StepView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream


@AndroidEntryPoint
class StepThreeFragment : Fragment() {

    private lateinit var binding: FragmentStepThreeBinding
    private val viewModel: RegisterFragmentViewModel by activityViewModels()



    private val imagePickerLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                binding.profileImageView.setImageURI(it)
                binding.dragSelect.isVisible = false
                val imagePart = createMultipartFromUri(requireContext(), it)
                if (imagePart != null) {
                    viewModel.updateStep3(imagePart)
                } else {
                    Toast.makeText(requireContext(), "Failed to process image", Toast.LENGTH_SHORT).show()
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentStepThreeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        // تحديد الخطوات باستخدام مصفوفة الخطوات
        val steps = listOf("Personal Info", "Additional Info", "Picture")
        binding.stepView.setSteps(steps)
        binding.stepView.go(2, true)


        observeLivedata()

        binding.profileImageView.setOnClickListener {
            imagePickerLauncher.launch("image/*")
        }

        binding.registerBtn.setOnClickListener {
            animateClickEffect(it)
            Log.e("StepThreeFragment", "Register button clicked")
            viewModel.doAction(RegisterContract.Action.Register)
        }


    }

    private fun initViews() {
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun observeLivedata() {
        lifecycleScope.launch{
            viewModel.viewState.collect { state ->
                when (state) {
                    is RegisterContract.ViewState.Loading -> {
                        showLoadingView()
                    }
                    is RegisterContract.ViewState.Success -> {
                        showSuccessView()

                        parentFragmentManager.beginTransaction().replace(R.id.fragment_container, LoginFragment()).commit()

                    }
                    is RegisterContract.ViewState.Error -> {
                        showErrorView(state.error)
                        viewModel.doAction(RegisterContract.Action.ResetState)

                    }

                    is RegisterContract.ViewState.Idle -> {
                    }

                    is RegisterContract.ViewState.NavigatingToLogin -> {
                        parentFragmentManager.beginTransaction().replace(R.id.fragment_container,
                            LoginFragment()).commit()
                    }
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.event.collect { event ->
                if (event is RegisterContract.Event.ShowMessage) {
                    Toast.makeText(requireContext(), event.viewMessage.message, Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    private fun showSuccessView() {
        binding.icNext.isVisible = false
        binding.progressBar.isVisible = true
    }

    private fun showErrorView(message: String) {
        binding.icNext.isVisible = true
        binding.progressBar.isVisible = false
        Toast.makeText(requireContext(),message,Toast.LENGTH_LONG).show()

    }


    private fun showLoadingView() {
        binding.icNext.isVisible = false
        binding.progressBar.isVisible = true
    }




    private fun createMultipartFromUri(context: Context, uri: Uri): MultipartBody.Part? {
        val inputStream = context.contentResolver.openInputStream(uri) ?: return null
        val file = File(context.cacheDir, "temp_image.jpg")
        FileOutputStream(file).use { output ->
            inputStream.copyTo(output)
        }
        val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData("profilePicture", file.name, requestBody)
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
        animatorSet.addListener(object :Animator.AnimatorListener {


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
