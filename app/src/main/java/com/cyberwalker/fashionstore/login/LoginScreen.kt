package com.cyberwalker.fashionstore.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cyberwalker.fashionstore.R
import com.cyberwalker.fashionstore.splash.SplashScreenActions
import com.cyberwalker.fashionstore.ui.theme.cardColorGreen
import com.cyberwalker.fashionstore.ui.theme.highlight

@Composable
fun LoginScreen(onAction: (actions: SplashScreenActions) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            modifier = Modifier
                .width(80.dp)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.splash_cta),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(30.dp))

        Login(onAction = onAction)
        Spacer(modifier = Modifier.height(20.dp))
        GoogleLogin(onAction = onAction)
        Spacer(modifier = Modifier.height(20.dp))
        FaceBookLogin(onAction = onAction)
        Spacer(modifier = Modifier.height(20.dp))
        GitHubLogin(onAction = onAction)

    }

}

@Composable
fun Login(
    viewModel: LoginViewModel = hiltViewModel(),
    onAction: (actions: SplashScreenActions) -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.weight(1f))
        Column(
            Modifier.weight(8f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val email = remember { mutableStateOf("") }
            var password by rememberSaveable { mutableStateOf("") }
            var passwordVisibility by remember { mutableStateOf(false) }

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email.value,
                singleLine = false,
                onValueChange = {
                    email.value = it
                },
                placeholder = { Text(text = "Email", color = highlight) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = highlight,
                    unfocusedBorderColor = cardColorGreen
                )
            )
            Spacer(modifier = Modifier.height(20.dp))


            val icon = if (passwordVisibility)
                painterResource(id = R.drawable.no_eye)
            else
                painterResource(id = R.drawable.eye)

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = password,
                onValueChange = {
                    password = it
                },
                label = { Text(text = "Password", color = highlight) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = highlight,
                    unfocusedBorderColor = cardColorGreen
                ),
                trailingIcon = {
                    IconButton(onClick = {
                        passwordVisibility = !passwordVisibility
                    }) {
                        Icon(
                            painter = icon,
                            contentDescription = "Visibility Icon"
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = if (passwordVisibility) VisualTransformation.None
                else PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(20.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    modifier = Modifier.weight(5f),
                    onClick = {
                        viewModel.loginWithEmail(email.value, password, onAction)
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
                )
                {
                    Text(text = "Login", color = Color.White)
                }
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}


@Composable
fun GoogleLogin(
    viewModel: LoginViewModel = hiltViewModel(),
    onAction: (actions: SplashScreenActions) -> Unit
) {
    Row(Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
//            viewModel.loginWithGoogle()
            },
            modifier = Modifier
                .weight(8f)
                .shadow(0.dp),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp,
                hoveredElevation = 0.dp,
                focusedElevation = 0.dp
            ),
            shape = RoundedCornerShape(28.dp),
            contentPadding = PaddingValues(15.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White,
                contentColor = Color.Red
            ),
            border = BorderStroke(1.dp, backgroundColor)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterStart)
                ) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.google),
                        modifier = Modifier
                            .size(38.dp),
                        contentDescription = "drawable_icons",
                        tint = Color.Unspecified
                    )
                }
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Google",
                    color = backgroundColor,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
//                fontFamily = FontFamily(
//                    Font(
//                        R.font.roboto_medium
//                    )
//                )
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun FaceBookLogin(
    viewModel: LoginViewModel = hiltViewModel(),
    onAction: (actions: SplashScreenActions) -> Unit
) {
    Row(Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
//            viewModel.loginWithGoogle()
            },
            modifier = Modifier
                .weight(8f)
                .shadow(0.dp),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp,
                hoveredElevation = 0.dp,
                focusedElevation = 0.dp
            ),
            shape = RoundedCornerShape(28.dp),
            contentPadding = PaddingValues(15.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White,
                contentColor = Color.Red
            ),
            border = BorderStroke(1.dp, backgroundColor)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterStart)
                ) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.facebook),
                        modifier = Modifier
                            .size(38.dp),
                        contentDescription = "drawable_icons",
                        tint = Color.Unspecified
                    )
                }
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Facebook",
                    color = backgroundColor,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
//                fontFamily = FontFamily(
//                    Font(
//                        R.font.roboto_medium
//                    )
//                )
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun GitHubLogin(
    viewModel: LoginViewModel = hiltViewModel(),
    onAction: (actions: SplashScreenActions) -> Unit
) {
    Row(Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
//            viewModel.loginWithGoogle()
            },
            modifier = Modifier
                .weight(8f)
                .shadow(0.dp),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp,
                hoveredElevation = 0.dp,
                focusedElevation = 0.dp
            ),
            shape = RoundedCornerShape(28.dp),
            contentPadding = PaddingValues(15.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White,
                contentColor = Color.Red
            ),
            border = BorderStroke(1.dp, backgroundColor)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterStart)
                ) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.github),
                        modifier = Modifier
                            .size(38.dp),
                        contentDescription = "drawable_icons",
                        tint = Color.Unspecified
                    )
                }
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Github",
                    color = backgroundColor,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
//                fontFamily = FontFamily(
//                    Font(
//                        R.font.roboto_medium
//                    )
//                )
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview
@Composable
fun GoogleLoginPrev() {
//    GoogleLogin()
}

//@Preview
//@Composable
//fun loginPreview() {
//    val viewModel: LoginViewModel = hiltViewModel()
////    Login(viewModel)
//}