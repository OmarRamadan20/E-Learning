package com.example.e_learning.userAuthentication.fragments.register

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.RegisterRequest
import com.example.domain.usecases.RegisterUseCase
import com.example.e_learning.base.BaseViewModel
import com.example.e_learning.base.ViewMessage
import com.example.domain.common.MyResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class RegisterFragmentViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : BaseViewModel(), RegisterContract.ViewModel {

    private val _viewState = MutableStateFlow<RegisterContract.ViewState>(RegisterContract.ViewState.Idle)
    override val viewState: StateFlow<RegisterContract.ViewState> get() = _viewState

    private val _event = MutableSharedFlow<RegisterContract.Event>(
        replay = 0,
        extraBufferCapacity = 1
    )
    override val event = _event.asSharedFlow()



    val firstNameLiveData = MutableLiveData<String?>()
    val firstNameErrorLiveData = MutableLiveData<String?>()

    val lastNameLiveData = MutableLiveData<String?>()
    val lastNameErrorLiveData = MutableLiveData<String?>()

    val emailLiveData = MutableLiveData<String?>()
    val emailErrorLiveData = MutableLiveData<String?>()

    val passwordLiveData = MutableLiveData<String?>()
    val passwordErrorLiveData = MutableLiveData<String?>()

    val passwordConfirmationLiveData = MutableLiveData<String?>()
    val passwordConfirmationErrorLiveData = MutableLiveData<String?>()

    val phoneNumberLiveData = MutableLiveData<String?>()
    val phoneNumberErrorLiveData = MutableLiveData<String?>()

    val dateLiveData = MutableLiveData<String?>()
    val dateErrorLiveData = MutableLiveData<String?>()

    val govLiveData = MutableLiveData<String?>()
    val govErrorLiveData = MutableLiveData<String?>()

    val cityLiveData = MutableLiveData<String?>()
    val cityErrorLiveData = MutableLiveData<String?>()

    val genderLiveData = MutableLiveData<String?>()
    val genderErrorLiveData = MutableLiveData<String?>()



    private val _form = MutableStateFlow(RegisterForm())


    fun updateStep1(firstName: String, lastName: String, email: String, password: String):Boolean {
        if(stepOneValidation()){
            _form.update { it.copy(firstName = firstName, lastName = lastName, email = email, password = password) }
            return true
        }
        else{
            return false
        }
    }
    private fun stepOneValidation(): Boolean {
        var isValid = true

        val firstName = firstNameLiveData.value
        if (firstName.isNullOrBlank()){
            isValid = false
            firstNameErrorLiveData.value="Please enter your first name"
        }
        if ((firstName?.length ?: 0) < 4){
            isValid = false
            firstNameErrorLiveData.value="Please enter a valid name that at least 4 characters"
        }
        else{
            firstNameErrorLiveData.value=null
        }
        val lastName = lastNameLiveData.value
        if (lastName.isNullOrBlank()){
            isValid = false
            lastNameErrorLiveData.value="Please enter your last name"
        }else if (lastName.length<4){
            isValid = false
            lastNameErrorLiveData.value="Please enter a valid name that at least 4 characters"
        }
        else{
            lastNameErrorLiveData.value=null
        }

        val email = emailLiveData.value
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        if (email.isNullOrBlank() || !email.matches(emailPattern.toRegex())){
            isValid = false
            emailErrorLiveData.value="Please enter a valid email address"
        }
        else{
            emailErrorLiveData.value=null
        }

        val password = passwordLiveData.value
//        val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{6,}$";
//        !password.matches(passwordPattern.toRegex())
        if (password.isNullOrBlank() || password.length<6){
            isValid = false
            passwordErrorLiveData.value="Please enter a valid password that contains at least: 6"
//            passwordErrorLiveData.value="Please enter a valid password that contains at least: " +
//                    "\n-1 uppercase letter " +
//                    "\n-1 lowercase letter " +
//                    "\n-1 digit " +
//                    "\n-1 special character " +
//                    "\nAnd at least 6 characters."
        }
        else{
            passwordErrorLiveData.value=null
        }

        val passwordConfirmation = passwordConfirmationLiveData.value
        if(passwordConfirmation.isNullOrBlank()){
            isValid = false
            passwordConfirmationErrorLiveData.value="Please enter your password confirmation"
        }
        else if (password != passwordConfirmation) {
            isValid = false
            passwordConfirmationErrorLiveData.value="Passwords do not match."
        }
        else{
            passwordConfirmationErrorLiveData.value=null
        }
        return isValid
    }



    fun updateStep2(dob: String, sex: String, mobileNumber: String, city: String, gov: String):Boolean {
        if(stepTwoValidation()){
            Log.e("RegisterFragmentViewModel", "updateStep2: success")

            _form.update { it.copy(DOB = dob, sex = sex, mobileNumber = mobileNumber, city = city, GOV = gov) }
            return true
        }
        else{
            Log.e("RegisterFragmentViewModel", "updateStep2: failed")
            return false
        }

    }
    private fun stepTwoValidation() :Boolean{

        var isValid = true

        val phone = phoneNumberLiveData.value
        if (phone.isNullOrBlank()){
            isValid = false
            phoneNumberErrorLiveData.value="Phone number can't be empty"
        }else if (phone.length!=11){
            isValid = false
            phoneNumberErrorLiveData.value="Phone number must be 11 digits"
        }
        else{
            phoneNumberErrorLiveData.value=null
        }


        val gov = govLiveData.value
        if (gov.isNullOrBlank()){
            isValid = false
            govErrorLiveData.value="Please enter your Government"
        }
        else{
            govErrorLiveData.value=null
        }

        val city = cityLiveData.value
        if (city.isNullOrBlank()){
            isValid = false
            cityErrorLiveData.value="Please enter your City"
        }
        else{
            cityErrorLiveData.value=null
        }
        val date=dateLiveData.value
        if (date.isNullOrBlank()){
            dateErrorLiveData.value = "Please enter your birth date"
        }
        else{
            dateErrorLiveData.value=null
        }
        val gender = genderLiveData.value
        if (gender.isNullOrBlank()){
            isValid = false
            genderErrorLiveData.value="Please enter your Gender"
        }
        else{
            genderErrorLiveData.value=null
        }

        return isValid
    }



    fun updateStep3(image: MultipartBody.Part) {
        _form.update { it.copy(profileImage = image) }
    }



    override fun doAction(action: RegisterContract.Action) {
        when (action) {
            is RegisterContract.Action.Register -> {
                    Log.d("RegisterFragmentViewModel", "valid inputs")
                    registerUser()
                }
            RegisterContract.Action.GotoLogin -> {
                viewModelScope.launch { _viewState.emit(RegisterContract.ViewState.NavigatingToLogin) }
            }

            RegisterContract.Action.ResetState ->
                _viewState.value = RegisterContract.ViewState.Idle

        }
    }

    private fun registerUser() {
        val formData = _form.value
        val request = RegisterRequest(
            email = formData.email,
            password = formData.password,
            firstName = formData.firstName,
            lastName = formData.lastName,
            dob = formData.DOB,
            sex = formData.sex,
            mobileNumber = formData.mobileNumber,
            city = formData.city,
            gov = formData.GOV,
            picUrl = formData.profileImage
        )

        viewModelScope.launch {
            _viewState.emit(RegisterContract.ViewState.Loading)

            when (val result = registerUseCase.invoke(request)) {
                is MyResult.Success -> {
                    Log.d("RegisterFragmentViewModel", "registerUser: ${result.data}")
                    _viewState.emit(RegisterContract.ViewState.Success(result.data.status))
                    _event.emit(RegisterContract.Event.ShowMessage(ViewMessage("Registration successful")))
                }

                is MyResult.ServerFail -> {
                    val serverMessage = result.serverError.message ?: "Something went wrong"
                    Log.e("RegisterViewModelServerFail", serverMessage)
                    _viewState.emit(RegisterContract.ViewState.Error(serverMessage))
                }

                is MyResult.Failure -> {
                    val errorMessage = result.exception.message ?: "An unexpected error occurred"
                    Log.e("RegisterViewModelException", errorMessage)
                    _viewState.emit(RegisterContract.ViewState.Error(errorMessage))
                }

                MyResult.Loading -> {
                    // Optional: Already handled above
                }
            }
        }
    }



}
