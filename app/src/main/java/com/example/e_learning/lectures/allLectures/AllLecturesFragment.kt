package com.example.e_learning.lectures.allLectures

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.e_learning.MainActivity
import com.example.e_learning.R
import com.example.e_learning.base.BaseFragment
import com.example.e_learning.databinding.FragmentAllLecturesBinding
import com.example.e_learning.lectures.allLectures.adapter.LectureAdapter
import com.example.e_learning.userAuthentication.fragments.login.LoginContract
import com.example.e_learning.userAuthentication.fragments.login.LoginFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllLecturesFragment : BaseFragment<FragmentAllLecturesBinding, AllLecturesViewModel>() {

    private val mViewModel: AllLecturesViewModel by viewModels()
    private lateinit var adapter: LectureAdapter

    override fun initViewModel(): AllLecturesViewModel {
        return mViewModel
    }
    override fun getLayoutId(): Int {
        return R.layout.fragment_all_lectures
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        // Get token and dispatch FetchLectures action
        val token = getToken(requireContext())
        if (!token.isNullOrEmpty()) {
            mViewModel.doAction(AllLecturesContract.Action.FetchLectures(token))
        } else {
            Toast.makeText(requireContext(), "Token is missing", Toast.LENGTH_SHORT).show()
            return
        }
    }

        // Collect UI state


    private fun navigateToCourseDetails(courseId: String) {
        val action = AllLecturesFragmentDirections
            .actionAllLecturesFragmentToCoursseDetailsFragment(courseId)
        findNavController().navigate(action)
    }

    private fun getToken(context: Context): String? {
        val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val token = prefs.getString("user_token", null)
        Log.e("token", token.toString())
        return token
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            mViewModel.viewState.collect { state ->
                when (state) {
                    is AllLecturesContract.ViewState.GetCourses -> {
                        binding.progressBar.isVisible = false
                        adapter = LectureAdapter(state.lectures) { lecture ->
                            mViewModel.doAction(
                                AllLecturesContract.Action.NavigateToCourse(
                                    lecture.id
                                )
                            )
                        }
                        binding.recyclerViewLectures.adapter = adapter
                    }

                    is AllLecturesContract.ViewState.Error -> {
                        binding.progressBar.isVisible = false
                        Toast.makeText(
                            requireContext(),
                            state.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    AllLecturesContract.ViewState.Idle -> {
                        binding.progressBar.isVisible = false
                    }

                    AllLecturesContract.ViewState.Loading -> {
                        binding.progressBar.isVisible = true
                    }

                    is AllLecturesContract.ViewState.NavigateToCourse -> {
                        navigateToCourseDetails(state.courseId?:"")
                    }
                }
            }
        }
        lifecycleScope.launch {
            viewModel.event.collect { event ->
                when (event) {
                    is AllLecturesContract.Event.ShowMessage -> {
                        Toast.makeText(requireContext(), event.viewMessage.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }
}
