package com.cyberwalker.fashionstore.search

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.cyberwalker.fashionstore.dump.BottomNav
import com.cyberwalker.fashionstore.profile.ProfileScreenActions
import com.cyberwalker.fashionstore.ui.theme.medium_18


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    navController: NavHostController,
    onAction: (actions: SearchScreenActions) -> Unit,
    //searchViewModel: SearchViewModel = hiltViewModel()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    //val searchQuery by searchViewModel.searchQuery
    //val searchImages = searchViewModel.searchImages.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            SearchWidget(
                text = "Search",
                onTextChange = {
                    //searchViewModel.updateSearchQuery(query = it)
                },
                onSearchClicked = {
                    //searchViewModel.searchHeroes(query = it)
                },
                onCloseClicked = {

                }
            )
        },
        bottomBar = {
            BottomNav(navController = navController)
        },
        content = {
            //ListContent(items = searchImages)
        }
    )
}

//@Composable
//private fun SearchScreenContent(
//    modifier: Modifier,
//    onAction: (actions: SearchScreenActions) -> Unit,
//) {
//    Column(
//        modifier = modifier
//            .padding(horizontal = 40.dp)
//            .fillMaxHeight()
//            .semantics { contentDescription = "Search Screen" }
//            .verticalScroll(rememberScrollState())
//    ) {
//        Text(text = "Search", style = MaterialTheme.typography.medium_18)
//    }
//}

@Composable
fun SearchWidget(
    text: String,
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onCloseClicked: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .semantics {
                contentDescription = "Search Widget"
            },
        elevation = AppBarDefaults.TopAppBarElevation,
        color = Color.White
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .semantics {
                    contentDescription = "Text Field"
                },
            value = text,
            onValueChange = { onTextChange(it) },
            placeholder = {
                Text(
                    modifier = Modifier
                        .alpha(alpha = ContentAlpha.medium),
                    text = "Search here...",
                    color = Color.White
                )
            },
            textStyle = TextStyle(
                color = MaterialTheme.colors.primary
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier
                        .alpha(alpha = ContentAlpha.medium),
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = MaterialTheme.colors.primary
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    modifier = Modifier
                        .semantics {
                            contentDescription = " Close Button"
                        },
                    onClick = {
                        if (text.isNotEmpty()) {
                            onTextChange("")
                        } else {
                            onCloseClicked()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = " Close Icon",
                        tint = MaterialTheme.colors.primary
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedLabelColor = Color.Transparent,
                backgroundColor = Color.Transparent,
                cursorColor = MaterialTheme.colors.primary
            )
        )
    }
}

@Composable
@Preview
fun SearchWidgetPreview() {
    SearchWidget(text = "Search", onTextChange = {}, onSearchClicked = {}, onCloseClicked = {})
}

sealed class SearchScreenActions {
    object LoadSearch : SearchScreenActions()
}