package com.cyberwalker.fashionstore.profile

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.cyberwalker.fashionstore.dump.BottomNav
import com.cyberwalker.fashionstore.ui.theme.medium_18

@Composable
fun ProfileScreen(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    onAction: (actions: ProfileScreenActions) -> Unit,
    scrollState: ScrollState = rememberScrollState(),
    navController: NavHostController
) {

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomNav(navController = navController)
        }
    ) { innerPadding ->
        ProfileScreenContent(modifier = Modifier
            .padding(innerPadding)
            .scrollable(state = scrollState, orientation = Orientation.Vertical),
            onAction = onAction)
    }


}

@Composable
private fun ProfileScreenContent(
    modifier: Modifier,
    onAction: (actions: ProfileScreenActions) -> Unit,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 40.dp)
            .fillMaxHeight()
            .semantics { contentDescription = "Profile Screen" }
            .verticalScroll(rememberScrollState())
    ) {
        Text(text = "Profile", style = MaterialTheme.typography.medium_18)
    }
}

sealed class ProfileScreenActions {
    object LoadProfile : ProfileScreenActions()
}