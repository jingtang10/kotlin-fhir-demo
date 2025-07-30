package com.google.fhir.model.demo.demo

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.google.fhir.model.demo.demo.ui.theme.AppTheme
import com.google.fhir.model.r5.Address
import com.google.fhir.model.r5.HumanName
import kotlinx.serialization.Serializable
import org.jetbrains.compose.ui.tooling.preview.Preview

@Serializable object PatientListDestination

@Serializable data class PatientDetailDestination(val id: String)

@Composable
@Preview
fun App(patientViewModel: PatientViewModel = viewModel { PatientViewModel() }) {
  AppTheme {
    Surface {
      val navController: NavHostController = rememberNavController()
      NavHost(navController = navController, startDestination = PatientListDestination) {
        composable<PatientListDestination> {
          PatientList(
            viewModel = patientViewModel,
            navigateToDetails = { patient ->
              navController.navigate(PatientDetailDestination(patient.id!!))
            },
          )
        }
        composable<PatientDetailDestination> { backStackEntry ->
          PatientDetails(
            viewModel = patientViewModel,
            id = backStackEntry.toRoute<PatientDetailDestination>().id,
            onBackClick = { navController.popBackStack() },
          )
        }
      }
    }
  }
}

val HumanName?.displayInApp: String
  get() = this?.given?.plus(family)?.map { it?.value }?.joinToString(separator = " ") ?: ""

val List<HumanName?>?.humanNames: String
  get() = this?.joinToString(separator = ", ") { it.displayInApp } ?: ""

val Address?.displayInApp: String
  get() =
    this?.line
      ?.asSequence()
      ?.plus(city)
      ?.plus(state)
      ?.plus(postalCode)
      ?.plus(country)
      ?.map { it?.value }
      ?.joinToString(separator = "\n") ?: " "

val List<Address?>?.addresses: String
  get() = this?.joinToString(separator = ", ") { it.displayInApp } ?: ""
