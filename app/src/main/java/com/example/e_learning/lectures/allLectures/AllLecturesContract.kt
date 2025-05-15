package com.example.e_learning.lectures.allLectures

import com.example.domain.models.lectures.Lecture
import com.example.e_learning.base.ViewMessage
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

open class AllLecturesContract {

    interface ViewModel{
        val viewState: StateFlow<ViewState>
        val event : SharedFlow<Event>

        fun doAction(action: AllLecturesContract.Action)

    }


    sealed class ViewState{
        object Idle: ViewState()
        object Loading: ViewState()
        data class GetCourses(val lectures: List<Lecture?>?) : ViewState()
        data class Error(val message: String) : ViewState()
        data class NavigateToCourse(val courseId:String?) : ViewState()

    }
    sealed class Action{

        data class NavigateToCourse(val courseId:String?) : Action()
        data class FetchLectures(val token: String?) : Action()

    }
    sealed class Event(){
        data class ShowMessage(val viewMessage: ViewMessage): Event()

    }

}