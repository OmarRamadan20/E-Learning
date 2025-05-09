package com.example.e_learning.userAuthentication.fragments.login

import com.example.domain.models.User

import com.example.e_learning.base.ViewMessage
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

class LoginContract {


    interface ViewModel{
        val viewState: StateFlow<ViewState>
        val event : SharedFlow<Event>

        fun doAction(action: Action)

    }

    sealed class ViewState{
        object Idle: ViewState()
        object Loading: ViewState()
        object NavigatingToRegister : ViewState()
        data class Success(val user: User, val token:String) : ViewState()
        data class Error(val message: String) : ViewState()

    }
    sealed class Action{

        data object NavigateToRegister: Action()
        data class NavigateToHome(val email:String,val password:String): Action()

    }
    sealed class Event(){
        data class ShowMessage(val viewMessage: ViewMessage): Event()


    }
}