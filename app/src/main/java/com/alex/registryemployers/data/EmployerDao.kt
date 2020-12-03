package com.alex.registryemployers.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.alex.registryemployers.model.Employer


@Dao
interface EmployerDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEmployer(employer: Employer)

    @Update
    suspend fun updateEmployer(employer: Employer)

    @Delete
    suspend fun deleteEmployer(employer: Employer)

    @Query("DELETE FROM employer_table")
    suspend fun deleteAllEmployers()

    @Query("SELECT * FROM employer_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Employer>>
}