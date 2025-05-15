package com.jetbrains.kmpapp.data

import com.google.fhir.model.r5.Patient
import io.ktor.utils.io.CancellationException
import kotlinx.serialization.json.Json
import kotlin_fhir_demo.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi

interface PatientApi {
    suspend fun getData(): List<Patient>
}

val serializationJson = Json {
    ignoreUnknownKeys = true
    explicitNulls = false
    prettyPrint = true
}

class LocalPatientApi : PatientApi {
    @OptIn(ExperimentalResourceApi::class)
    override suspend fun getData(): List<Patient> {
        return try {
            val json: String = Res.readBytes("files/list.json").decodeToString()
            return serializationJson.decodeFromString<List<Patient>>(json)
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()

            emptyList()
        }
    }
}
