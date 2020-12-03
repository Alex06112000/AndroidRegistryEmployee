package com.alex.registryemployers.repository

import androidx.lifecycle.LiveData
import com.alex.registryemployers.data.EmployerDao
import com.alex.registryemployers.model.Employer

class EmployerRepository (private val employerDao: EmployerDao) {

    val readallData: LiveData<List<Employer>> = employerDao.readAllData()

    suspend fun addEmployer(employer: Employer){
        employerDao.addEmployer(employer)
    }

    suspend fun updateEmployer(employer: Employer){
        employerDao.updateEmployer(employer)
    }

    suspend fun deleteEmployer(employer: Employer){
        employerDao.deleteEmployer(employer)
    }

    suspend fun deleteAllEmployers(){
        employerDao.deleteAllEmployers()
    }

}