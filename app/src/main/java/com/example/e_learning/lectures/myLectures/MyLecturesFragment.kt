package com.example.e_learning.lectures.myLectures

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.e_learning.R
import com.example.e_learning.base.BaseFragment
import com.example.e_learning.databinding.FragmentMyLecturesBinding
import com.example.e_learning.lectures.LecturesContract
import com.example.e_learning.lectures.adapter.LectureAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyLecturesFragment : BaseFragment<FragmentMyLecturesBinding, MyLecturesViewModel>() {

    private val mViewModel: MyLecturesViewModel by viewModels()
    private lateinit var adapter: LectureAdapter

    override fun initViewModel(): MyLecturesViewModel {
        return mViewModel
    }
    override fun getLayoutId(): Int {
        return R.layout.fragment_my_lectures
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        // Get token and dispatch FetchLectures action
        val token = getToken(requireContext())
        if (!token.isNullOrEmpty()) {
            mViewModel.doAction(LecturesContract.Action.FetchLectures(token))
        } else {
            Toast.makeText(requireContext(), "Token is missing", Toast.LENGTH_SHORT).show()
            return
        }
    }

    // Collect UI state


    private fun navigateToCourseDetails(courseId: String) {
        val action = MyLecturesFragmentDirections
            .actionMyLecturesFragmentToCoursseDetailsFragment(courseId)
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
                    is LecturesContract.ViewState.GetCourses -> {
                        binding.progressBar.isVisible = false
                        adapter = LectureAdapter(state.lectures) { lecture ->
                            mViewModel.doAction(
                                LecturesContract.Action.NavigateToCourse(
                                    lecture.id
                                )
                            )
                        }
                        Log.e("success my lectures", "${state.lectures}")
                        binding.recyclerViewLectures.adapter = adapter
                    }

                    is LecturesContract.ViewState.Error -> {
                        binding.progressBar.isVisible = false
                        Toast.makeText(
                            requireContext(),
                            state.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    LecturesContract.ViewState.Idle -> {
                        binding.progressBar.isVisible = false
                    }

                    LecturesContract.ViewState.Loading -> {
                        binding.progressBar.isVisible = true
                    }

                    is LecturesContract.ViewState.NavigateToCourse -> {
                        navigateToCourseDetails(state.courseId?:"")
                    }
                }
            }
        }
        lifecycleScope.launch {
            viewModel.event.collect { event ->
                when (event) {
                    is LecturesContract.Event.ShowMessage -> {
                        Toast.makeText(requireContext(), event.viewMessage.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }
}
