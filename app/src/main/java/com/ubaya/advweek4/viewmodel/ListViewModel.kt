package com.ubaya.advweek4.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.advweek4.model.Student

class ListViewModel(application: Application):AndroidViewModel(application) {
    val studentsLD = MutableLiveData<List<Student>>()
    val loadingErrorLD = MutableLiveData<Boolean>()
    val loadingDoneLD = MutableLiveData<Boolean>()

    private val TAG = "volleyTag"
    private var queue:RequestQueue ?= null



    fun refresh(){
        loadingErrorLD.value = false
        loadingDoneLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        var url = "http://adv.jitusolution.com/student.php"

        val stringRequest = StringRequest(Request.Method.GET,url,
            { response ->
                val sType = object :TypeToken<List<Student>>(){}.type
                val result = Gson().fromJson<List<Student>>(response,sType)
                studentsLD.value = result

                loadingDoneLD.value = false
                Log.d("showvolley",response.toString())

            },
            {
                loadingErrorLD.value = true
                loadingDoneLD.value = false
                Log.d("showvolley",it.toString())

            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}