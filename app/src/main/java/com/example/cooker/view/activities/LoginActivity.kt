package com.example.cooker.view.activities

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.cooker.R
import com.example.cooker.model.User
import com.example.cooker.model.database.Repository
import com.example.cooker.other.managers.FirebaseManager
import com.example.cooker.other.managers.SharedPrefManager
import com.example.cooker.other.register.AppSignIn
import com.example.cooker.view.fragments.SignupFragment
import com.example.cooker.viewModel.UsersViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.login_activity.*
import kotlinx.android.synthetic.main.login_activity.login_bottom_menu1
import kotlinx.android.synthetic.main.login_activity.signup_bottom_menu1
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private val firebase = FirebaseAuth.getInstance()
    private lateinit var sharedPreferences: SharedPreferences
    private val usersViewModel: UsersViewModel by viewModels()
    private lateinit var googleContent:  ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        setBottomMenu()
        setGoogleContent()
        setSharedPref()
        lastSigning()
        signInButtons()
    }

    private fun setSharedPref() {
        sharedPreferences = getSharedPreferences(R.string.app_name.toString(), MODE_PRIVATE)
    }

    private fun setGoogleContent() {
        googleContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                content -> checkIntent(content)
        }
    }

    private fun lastSigning() {
        var lastSignin = sharedPreferences.getLong("LAST_LOGIN", -1)
        val intent = Intent(this, ItemsActivity::class.java)
        if (lastSignin != -1L && System.currentTimeMillis()-lastSignin < 60000) // 60000 ms = 60 sec
            startActivity(intent)
    }

    private fun signInButtons() {
        regular_signin.setOnClickListener { regularSignIn() }
        google_signin.setOnClickListener { googleSignIn() }
    }

    private fun regularSignIn() {
        val email = signin_email.text.toString()
        val password = signin_password.text.toString()
        usersViewModel.usersData.observe(this) {
            if (AppSignIn(this).checkUser(it, email, password)) {
                openApp(signin_email.text.toString())
                signin_email.setText("")
                signin_password.setText("")
            }
        }
    }

    private fun openApp(email: String) {
        var editor = sharedPreferences.edit()
        editor.putLong("LAST_LOGIN", System.currentTimeMillis()).apply()
        val intent = Intent(this, ListsActivity::class.java)
        intent.putExtra("userEmail", email)
        startActivity(intent)
    }

    /*-------------------------- FireBase ---------------------------*/

    private fun googleSignIn() {
        val googleOptions = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken(getString(R.string.default_web_client_id))
            .build()
        val client = GoogleSignIn.getClient(this, googleOptions).signInIntent
        googleContent.launch(client)
    }

    private fun checkIntent(content: ActivityResult?) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(content?.data)
        task.addOnSuccessListener{ checkUser(it) }
            .addOnFailureListener{ displayToast("Please Sign in regular") }
    }

    private fun checkUser(googleSignInAccount: GoogleSignInAccount) {
        firebase.fetchSignInMethodsForEmail(googleSignInAccount.email!!)
            .addOnSuccessListener {
                if (it.signInMethods.isNullOrEmpty()) { // if user is not exist in the firebase, register it in all the databases.
                    regToSharedPref(googleSignInAccount)
                    regToDatabase(googleSignInAccount)
                    regToFirebase(googleSignInAccount)
                }
                else // if user exist in firebase you can open the app.
                    openApp(googleSignInAccount.email.toString())
            }
            .addOnFailureListener { displayToast("Failed on Firebase") }
    }

    private fun regToFirebase(googleSignInAccount: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(googleSignInAccount.idToken, null)
        firebase.signInWithCredential(credential)
            .addOnSuccessListener {
                val user = User(googleSignInAccount.email.toString(), "Google Password", googleSignInAccount.givenName.toString(), "beginner")
                FirebaseManager.getInstance(this).addUser(user)
                openApp(googleSignInAccount.email.toString())
            }
            .addOnFailureListener { displayToast("try later") }
    }

    private fun regToSharedPref(googleSignInAccount: GoogleSignInAccount) {
        val user = User(googleSignInAccount.email.toString(), "Google Password", googleSignInAccount.givenName.toString(), "beginner")
        SharedPrefManager.getInstance(this).setUser(user)
    }

    private fun regToDatabase(googleSignInAccount: GoogleSignInAccount) {
        val name = googleSignInAccount.givenName.toString()
        val email = googleSignInAccount.email.toString()
        val context = this
        GlobalScope.launch { Repository.getInstance(context).addUser(User(email, "Google Password", name, "beginner")) }
    }

    private fun displayToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }


    /*-------------------------- Bottom Menu ---------------------------*/

    fun setBottomMenu() {
        signup_bottom_menu1.setTextColor(Color.parseColor("#000000"))
        login_bottom_menu1.setTextColor(Color.parseColor("#0835C5"))
        signup_bottom_menu1.setOnClickListener {
            signup_bottom_menu1.setTextColor(Color.parseColor("#0835C5"))
            login_bottom_menu1.setTextColor(Color.parseColor("#000000"))
            usersViewModel.usersData.observe(this) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.signup_fragment, SignupFragment(this, it)).commit()
            }
        }
    }

}