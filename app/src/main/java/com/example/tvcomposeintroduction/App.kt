/*
 * Copyright 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.tvcomposeintroduction

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tvcomposeintroduction.data.Movie
import com.example.tvcomposeintroduction.ui.components.SideNavigationDrawer
import com.example.tvcomposeintroduction.ui.screens.DetailsError
import com.example.tvcomposeintroduction.ui.screens.DetailsScreen

@Composable
fun App() {
    val navController = rememberNavController()
    val showDetails: (Movie) -> Unit = {
        navController.navigate("/movie/${it.id}")
    }

    NavHost(navController = navController, startDestination = "/") {
        composable("/") {
            SideNavigationDrawer()
        }
        composable(
            route = "/movie/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.LongType
            })
        ) {
            if (it.arguments?.getLong("id") == null) {
                throw DetailsError.NoIdSpecified
            }
            DetailsScreen()
        }
    }
}