package com.example.e_learning.lectures

import com.example.domain.models.lectures.LectureItem
import com.example.e_learning.base.ViewMessage
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

open class LecturesContract {

    interface ViewModel{
        val viewState: StateFlow<ViewState>
        val event : SharedFlow<Event>

        fun doAction(action: Action)

    }


    sealed class ViewState{
        object Idle: ViewState()
        object Loading: ViewState()
        data class GetCourses(val lectures: List<LectureItem?>?) : ViewState()
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