package com.google.fhir.model.demo.demo

import com.google.fhir.model.r5.Patient
import com.google.fhir.model.r5.configureR5
import kotlin_fhir_demo.composeapp.generated.resources.Res
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

private val json = Json {
  ignoreUnknownKeys = true
  configureR5()
}

class PatientViewModel {
  private val _patients = MutableStateFlow<List<Patient>>(emptyList())
  val patients: StateFlow<List<Patient>> = _patients.asStateFlow()

  init {
    CoroutineScope(Dispatchers.Main).launch {
      val jsonString = Res.readBytes("files/list.json").decodeToString()
      _patients.update { json.decodeFromString(jsonString) }
    }
  }
}
