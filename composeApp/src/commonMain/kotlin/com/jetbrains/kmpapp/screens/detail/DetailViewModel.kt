package com.jetbrains.kmpapp.screens.detail

import androidx.lifecycle.ViewModel
import com.google.fhir.model.r5.Patient
import com.jetbrains.kmpapp.data.PatientRepository
import kotlinx.coroutines.flow.Flow

class DetailViewModel(private val patientRepository: PatientRepository) : ViewModel() {
    fun getObject(objectId: String): Flow<Patient?> =
        patientRepository.getObjectById(objectId)
}
