package com.example.e_learning.userAuthentication.fragments.register

import com.example.e_learning.base.ViewMessage
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

class RegisterContract {

    interface ViewModel{
        val viewState: StateFlow<ViewState>
        val event: SharedFlow<Event>

        fun doAction(action: Action)

    }

    sealed class ViewState{
        object Loading: ViewState()
        data class Success(val message: String) : ViewState()
        data class Error(val error: String) : ViewState()
        object NavigatingToLogin: ViewState()
        object Idle: ViewState()
    }

    sealed class Action{
        data object Register : Action()
        data object GotoLogin: Action()
        data object ResetState : Action()
    }


    sealed class Event(){
        data class ShowMessage(val viewMessage: ViewMessage): Event()
    }
}