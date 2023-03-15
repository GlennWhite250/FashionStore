/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cyberwalker.fashionstore.dump

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.cyberwalker.fashionstore.R
import com.cyberwalker.fashionstore.navigation.Screen
import com.cyberwalker.fashionstore.ui.theme.bottomNavbg
import com.cyberwalker.fashionstore.ui.theme.highlight

@Composable
fun BottomNav(navController: NavController, isDark: Boolean = isSystemInDarkTheme()) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search,
        BottomNavItem.Liked,
        BottomNavItem.Profile,
    )
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.bottomNavbg,
        contentColor = highlight,
    ) {
        val context = LocalContext.current
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                selectedContentColor = highlight,
                unselectedContentColor = Color.Black.copy(0.4f),
                alwaysShowLabel = false,
                selected = currentRoute == item.screen_route,
                onClick = {
                    if (currentRoute != item.screen_route) {
                        navController.navigate(item.screen_route)
                   }
                    //throw RuntimeException("Test Crash") // Force a crash
                }
            )
        }
    }
}

@Composable
private fun Content(title: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5
        )
    }
}

sealed class BottomNavItem(var title: String, var icon: Int, var screen_route: String) {

    object Home : BottomNavItem("Home", R.drawable.home, Screen.Home.route)
    object Search : BottomNavItem("Search", R.drawable.search, "Search")
    object Liked : BottomNavItem("Liked", R.drawable.liked, "Liked")
    object Profile : BottomNavItem("Profile", R.drawable.profile, "Profile")
}