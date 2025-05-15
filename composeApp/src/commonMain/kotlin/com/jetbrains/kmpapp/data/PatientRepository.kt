package com.jetbrains.kmpapp.data

import com.google.fhir.model.r5.Patient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PatientRepository(
    private val patientApi: PatientApi,
    private val museumStorage: MuseumStorage,
) {
    private val scope = CoroutineScope(SupervisorJob())

    fun initialize() {
        scope.launch {
            refresh()
        }
    }

    suspend fun refresh() {
        museumStorage.saveObjects(patientApi.getData())
    }

    fun getObjects(): Flow<List<Patient>> = museumStorage.getObjects()

    fun getObjectById(objectId: String): Flow<Patient?> = museumStorage.getObjectById(objectId)
}
