package com.cyberwalker.fashionstore.login


import android.app.Activity
import android.content.IntentSender
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.cyberwalker.fashionstore.splash.SplashScreenActions
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private const val TAG = "Login"
lateinit var auth: FirebaseAuth


//Google Auth
private var REQUEST_CODE_GOOGLE_SIGN_IN = 100
private lateinit var oneTapClient: SignInClient
private lateinit var signInRequest: BeginSignInRequest

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    fun loginWithEmail(
        email: String, password: String, onAction: (actions: SplashScreenActions) -> Unit
    ) {
        Log.d(TAG, "loginWithEmail: email: $email, password: $password")
        auth = FirebaseAuth.getInstance()


        auth.fetchSignInMethodsForEmail(email)
            .addOnSuccessListener { result ->
                val signInMethods = result.signInMethods!!
                if (signInMethods.isEmpty()) {
                    signup(email, password)
                } else {
                    login(email, password, onAction)
                }
            }
            .addOnFailureListener { exception ->
                Log.e(TAG, "Error getting sign in methods for user", exception)
            }
    }

    @Composable
    fun loginWithGoogle() {
        oneTapClient = Identity.getSignInClient(LocalContext.current)

        signInRequest = BeginSignInRequest.builder()
            .setPasswordRequestOptions(
                BeginSignInRequest.PasswordRequestOptions.builder()
                    .setSupported(true)
                    .build()
            )
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    // Your server's client ID, not your Android client ID.
                    .setServerClientId("getString(R.string.app_name)")
                    // Only show accounts previously used to sign in.
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            )
            // Automatically sign in when exactly one credential is retrieved.
            .setAutoSelectEnabled(false)
            .build()

        oneTapClient.beginSignIn(signInRequest)
            .addOnSuccessListener(Activity()) { result ->
                try {
                    Log.d(TAG, "googleSigIn: init try ${result}")


                    Log.d(TAG, "googleSigIn: finish try")
                } catch (e: IntentSender.SendIntentException) {
                    println("Google Sign --> Couldn't start One Tap UI: ${e.localizedMessage}")
                    Log.d(
                        TAG,
                        "googleSigIn: Google Sign --> Couldn't start One Tap UI: ${e.localizedMessage}"
                    )
                }
            }
            .addOnFailureListener(Activity()) { e ->
                Log.d(TAG, "googleSigIn: fail ${e.localizedMessage}")
                println("Google Sign --> No credential: ${e.localizedMessage}")
            }
    }

    private fun login(
        email: String,
        password: String,
        onAction: (actions: SplashScreenActions) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { request ->

            if (request.isSuccessful) {
                goAccount(onAction)
            } else {
                onAction(SplashScreenActions.LoadLogin)
            }
        }
    }


    private fun goAccount(onAction: (actions: SplashScreenActions) -> Unit) {
        onAction(SplashScreenActions.LoadLogin)
    }

    private fun signup(email: String, password: String) {
        try {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener() { request ->
                    if (request.isSuccessful) {
                        Log.d(TAG, "signup: registered")
                    } else {
                        Log.d(TAG, "signup: ${request.exception?.message}")
                    }


                }
        } catch (e: Exception) {
            RuntimeException("message: ${e.message}")
        }
    }

}