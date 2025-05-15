package com.example.e_learning.home

import android.content.Context
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.e_learning.R

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        getToken(requireContext())
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    private fun getToken(context: Context): String? {
        val token = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        Log.e("token", token.getString("user_token", null).toString())
        return token.getString("user_token", null)

    }
}