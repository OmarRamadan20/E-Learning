package com.example.e_learning.lectures.myLectures

import androidx.lifecycle.viewModelScope
import com.example.domain.common.MyResult
import com.example.domain.usecases.GetMyLecturesUseCase
import com.example.e_learning.base.BaseViewModel
import com.example.e_learning.lectures.LecturesContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyLecturesViewModel @Inject constructor(
    private val getMyLecturesUseCase: GetMyLecturesUseCase
) : BaseViewModel(), LecturesContract.ViewModel {

    private val _viewState = MutableStateFlow<LecturesContract.ViewState>(LecturesContract.ViewState.Idle)

    override val viewState: StateFlow<LecturesContract.ViewState>
        get() = _viewState

    private val _event = MutableSharedFlow<LecturesContract.Event>()

    override val event: SharedFlow<LecturesContract.Event>
        get() = _event



    override fun doAction(action: LecturesContract.Action) {
        viewModelScope.launch {
            when (action) {
                is LecturesContract.Action.NavigateToCourse -> {
                    _viewState.value = LecturesContract.ViewState.NavigateToCourse(action.courseId)
                }

                is LecturesContract.Action.FetchLectures -> {
                    fetchLectures(action.token)
                }
            }
        }
    }
    private fun fetchLectures(token:String?) {
        viewModelScope.launch {
            _viewState.value = LecturesContract.ViewState.Loading
            when(val result = getMyLecturesUseCase.getMyLectures(token?:"")){
                is MyResult.Failure -> {
                    _viewState.value = LecturesContract.ViewState.Error(result.exception.message?:"Unknown error")
                }
                MyResult.Loading -> {
                    _viewState.value = LecturesContract.ViewState.Loading
                }
                is MyResult.ServerFail -> {
                    _viewState.value = LecturesContract.ViewState.Error(result.serverError.message?:"Unknown error")
                }
                is MyResult.Success -> {
                    _viewState.value = LecturesContract.ViewState.GetCourses(result.data.lectures)
                }
            }

        }
    }
}