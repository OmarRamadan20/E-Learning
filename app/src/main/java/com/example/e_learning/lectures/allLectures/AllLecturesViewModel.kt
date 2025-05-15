package com.example.e_learning.lectures.allLectures

import androidx.lifecycle.viewModelScope
import com.example.domain.common.MyResult
import com.example.domain.usecases.GetAllLecturesUseCase
import com.example.e_learning.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllLecturesViewModel @Inject constructor(
    private val getAllLecturesUseCase: GetAllLecturesUseCase
) : BaseViewModel(),AllLecturesContract.ViewModel {

    private val _viewState = MutableStateFlow<AllLecturesContract.ViewState>(AllLecturesContract.ViewState.Idle)

    override val viewState: StateFlow<AllLecturesContract.ViewState>
        get() = _viewState

    private val _event = MutableSharedFlow<AllLecturesContract.Event>()

    override val event: SharedFlow<AllLecturesContract.Event>
        get() = _event



    override fun doAction(action: AllLecturesContract.Action) {
        viewModelScope.launch {
            when (action) {
                is AllLecturesContract.Action.NavigateToCourse -> {
                    _viewState.value = AllLecturesContract.ViewState.NavigateToCourse(action.courseId)
                }

                is AllLecturesContract.Action.FetchLectures -> {
                    fetchLectures(action.token)
                }
            }
        }
    }
    private fun fetchLectures(token:String?) {
        viewModelScope.launch {
            _viewState.value = AllLecturesContract.ViewState.Loading
            when(val result = getAllLecturesUseCase.getAllLectures(token?:"")){
                is MyResult.Failure -> {
                    _viewState.value = AllLecturesContract.ViewState.Error(result.exception.message?:"Unknown error")
                }
                MyResult.Loading -> {
                    _viewState.value = AllLecturesContract.ViewState.Loading
                }
                is MyResult.ServerFail -> {
                    _viewState.value = AllLecturesContract.ViewState.Error(result.serverError.message?:"Unknown error")
                }
                is MyResult.Success -> {
                    _viewState.value = AllLecturesContract.ViewState.GetCourses(result.data.data)
                }
            }

        }
    }
}