package com.ubaya.advweek4.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ubaya.advweek4.model.Student

class ListViewModel:ViewModel() {
    val studentsLD = MutableLiveData<List<Student>>()
    val loadingErrorLD = MutableLiveData<Boolean>()
    val loadingDoneLD = MutableLiveData<Boolean>()

    fun refresh(){
        val student1 = Student("90-7925985","Fan","2018/05/12","6352415878","http://dummyimage.com/100x75.png/cc0000/ffffff")
        val student2 = Student("97-0754206","Cleavland","2003/10/24","7407651838","http://dummyimage.com/100x75.png/dddddd/000000")
        val student3 = Student("42-9394225","Jessika","2016/02/15","5707658776","http://dummyimage.com/100x75.png/5fa2dd/ffffff")

        studentsLD.value = arrayListOf<Student>(student1,student2,student3)
        loadingErrorLD.value = false
        loadingDoneLD.value = true
    }
}