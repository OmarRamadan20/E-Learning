package com.example.e_learning.userAuthentication.fragments.register

import okhttp3.MultipartBody

data class RegisterForm(
    var email: String = "",
    var password: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var DOB: String = "",
    var sex: String = "",
    var mobileNumber: String = "",
    var city: String = "",
    var GOV: String = "",
    var profileImage: MultipartBody.Part?=null)
