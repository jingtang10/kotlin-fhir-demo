package com.jetbrains.kmpapp.data

import com.google.fhir.model.r5.Patient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

interface MuseumStorage {
    suspend fun saveObjects(newObjects: List<Patient>)

    fun getObjectById(objectId: String): Flow<Patient?>

    fun getObjects(): Flow<List<Patient>>
}

class InMemoryMuseumStorage : MuseumStorage {
    private val storedObjects = MutableStateFlow(emptyList<Patient>())

    override suspend fun saveObjects(newObjects: List<Patient>) {
        storedObjects.value = newObjects
    }

    override fun getObjectById(objectId: String): Flow<Patient?> {
        return storedObjects.map { objects ->
            objects.find { it.id == objectId }
        }
    }

    override fun getObjects(): Flow<List<Patient>> = storedObjects
}
