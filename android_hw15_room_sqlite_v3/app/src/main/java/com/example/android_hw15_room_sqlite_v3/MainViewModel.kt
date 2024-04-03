package com.example.android_hw15_room_sqlite_v3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.toCollection
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

//class MainViewModel (private val userDao: UserDao): ViewModel() {
class MainViewModel (private val dictDao: DictDao): ViewModel() {

    val allWords = this.dictDao.getAll()
    .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )

    fun onAddBtn(searchWord: String){

        viewModelScope.launch {
//            if(allWords.value.any{ it.word == searchWord }){
//            allWords.value.forEach() {
//                if (it.word == searchWord) {
//                    it.counter += 1
//                    dictDao.update(it)
//                } else {
//                    dictDao.insert(
//                        NewWord(
//                            word = "$searchWord",
//                            counter = 1
//                        )
//                    )
//                }
//            }
//            if(allWords.value.any{ it.word == searchWord }){
                allWords.value.forEach() {
                    if (it.word == searchWord) {
                        it.counter += 1
                        dictDao.update(it)
                    }
                }
                dictDao.insert(
                    NewWord(
                        word = "$searchWord",
                        counter = 1
                    )
                )
            }



//            userDao.insert(
//
//                NewUser(
//                    id = 1,
//                    firstName ="Name $size",
//                    lastName = "LastName $size",
//                    age = size
//                )
//            )
//        }
    }
//    fun onDeleteBtn(){
//        viewModelScope.launch {
//            allUsers.value.lastOrNull()?.let { userDao.delete(it) }
//        }
//
//    }
    fun onUpdateBtn() {
        viewModelScope.launch {
            allWords.value.lastOrNull()?.let {
                val word = it.copy(
                    counter = it.counter + 1
                )
                dictDao.update(word)
            }

        }
    }

}