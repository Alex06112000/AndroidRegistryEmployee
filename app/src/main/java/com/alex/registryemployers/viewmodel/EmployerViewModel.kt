package com.alex.registryemployers.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.alex.registryemployers.data.EmployerDatabase
import com.alex.registryemployers.repository.EmployerRepository
import com.alex.registryemployers.model.Employer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmployerViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Employer>>

    private val repository: EmployerRepository

    init {
        val employerDao = EmployerDatabase.getDatabase(application).employerDao()
        repository = EmployerRepository(employerDao)
        readAllData = repository.readallData
    }

    fun addEmployer(employer: Employer){
        viewModelScope.launch ( Dispatchers.IO ){
            repository.addEmployer(employer)
        }
    }

    fun updateEmployer(employer: Employer){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateEmployer(employer)
        }
    }

    fun deleteEmployer(employer: Employer){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteEmployer(employer)
        }
    }

    fun deleteAllEmployers(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllEmployers()
        }
    }

}