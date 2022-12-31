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
import com.example.cooker.model.Item
import com.example.cooker.model.User
import com.example.cooker.model.database.Repository
import com.example.cooker.other.managers.FirebaseManager
import com.example.cooker.other.register.AppSignIn
import com.example.cooker.view.fragments.SignupFragment
import com.example.cooker.viewModel.ItemsViewModel
import com.example.cooker.viewModel.ListsViewModel
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
import kotlin.concurrent.thread

class LoginActivity : AppCompatActivity() {

    private val firebase = FirebaseAuth.getInstance()
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var googleContent:  ActivityResultLauncher<Intent>

    private val usersViewModel: UsersViewModel by viewModels()
    private val listsViewModel: ListsViewModel by viewModels()
    private val itemsViewModel: ItemsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        readyDatabase()
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
        val intent = Intent(this, ListsActivity::class.java)
        if (lastSignin != -1L && System.currentTimeMillis()-lastSignin < 60000)
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
                if (it.signInMethods.isNullOrEmpty()) {
                    regToDatabase(googleSignInAccount)
                    regToFirebase(googleSignInAccount)
                }
                else
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


    /*-------------------------- Ready Database ---------------------------*/
    private fun readyDatabase() {
        ready_database.setOnClickListener {
            addUsers()
            addLists()
            addItems()
            openApp("cristiano.ronaldo@gmail.com")
        }
    }

    private fun addUsers() {
        usersViewModel.usersData.observe(this) {
            if (it.isEmpty())
                thread(start = true) {
                    Repository.getInstance(this).addUser(
                        User(
                            "cristiano.ronaldo@gmail.com",
                            "00000000",
                            "Cristiano Ronaldo",
                            "Master",
                            image = "https://i2-prod.mirror.co.uk/incoming/article28552428.ece/ALTERNATES/n615/0_Cristiano-Ronaldo-Piers-Morgan.jpg"
                        )
                    )
                    Repository.getInstance(this)
                        .addUser(
                            User(
                                "leo.messi@hotmail.com",
                                "00000000",
                                "Leonel Messi",
                                "beginner",
                                image = "https://img.olympicchannel.com/images/image/private/t_social_share_thumb/f_auto/primary/wfrhxc0kh2vvq77sonki"
                            )
                        )
                    Repository.getInstance(this)
                        .addUser(
                            User(
                                "muhamad.salah@email.com",
                                "00000000",
                                "Muhamad Salah",
                                "Master",
                                image = "https://library.sportingnews.com/styles/twitter_card_120x120/s3/2022-01/mohamed-salah-egypt-afcon-2022_w4flk9gdgimi1hizrpndo7v8i.jpg?itok=q_gvNBav"
                            )
                        )
                    Repository.getInstance(this)
                        .addUser(
                            User(
                                "karim.benzema@outlook.com",
                                "00000000",
                                "Karim Benzema",
                                "Master",
                                image = "https://pbs.twimg.com/profile_images/1401562839614210052/yo9BeNMD_400x400.jpg"
                            )
                        )
                    Repository.getInstance(this).addUser(
                        User(
                            "zlatan.ibra@gmail.com",
                            "00000000",
                            "zlatan ibrahimovich",
                            "beginner"
                        )
                    )
                    Repository.getInstance(this).addUser(
                        User(
                            "zenidine.zidane@outlook.com",
                            "00000000",
                            "zenidine zidane",
                            "Master"
                        )
                    )
                    Repository.getInstance(this)
                        .addUser(
                            User(
                                "ronaldinho@hotmail.com",
                                "00000000",
                                "ronaldinho",
                                "beginner"
                            )
                        )
                    Repository.getInstance(this)
                        .addUser(
                            User(
                                "ronaldo.brazil@hackeru.com",
                                "00000000",
                                "Ronaldo",
                                "beginner"
                            )
                        )
                    Repository.getInstance(this)
                        .addUser(User("rici.kaka@gmail.com", "00000000", "rici kaka", "beginner"))
                    Repository.getInstance(this)
                        .addUser(User("toni.krooz@walla.com", "00000000", "toni.krooz", "beginner"))
                    Repository.getInstance(this)
                        .addUser(
                            User(
                                "luka.modric@yahoo.com",
                                "00000000",
                                "Cristiano Ronaldo",
                                "beginner",
                                image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR-D9uwVek8hUI0i9_xUhHM5B32p2vx_6zSVw&usqp=CAU"
                            )
                        )
                    Repository.getInstance(this)
                        .addUser(
                            User(
                                "andreas.inesta@gmail.com",
                                "00000000",
                                "andreas inesta",
                                "beginner"
                            )
                        )
                    Repository.getInstance(this)
                        .addUser(
                            User(
                                "frank.rebiry@outlook.com",
                                "00000000",
                                "frank rebiry",
                                "Master"
                            )
                        )
                    Repository.getInstance(this).addUser(
                        User(
                            "muhamad.abotrika@hotmail.com",
                            "00000000",
                            "muhamad abotrika",
                            "Master"
                        )
                    )
                    Repository.getInstance(this)
                        .addUser(
                            User(
                                "kilyan.mbape@hackeru.com",
                                "00000000",
                                "kilyan mbape",
                                "beginner"
                            )
                        )
                    Repository.getInstance(this)
                        .addUser(User("halaand@gmail.com", "00000000", "halaand", "beginner"))
                    Repository.getInstance(this)
                        .addUser(
                            User(
                                "angel.dimaria@outlook.com",
                                "00000000",
                                "angel dimaria",
                                "beginner",
                                image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQv6NhXTlacJ1OAZf9XUsOIq0nld35vwzs2AA&usqp=CAU"
                            )
                        )
                    Repository.getInstance(this)
                        .addUser(User("hakim.zyech@yahoo.com", "00000000", "hakim zyech", "Master"))
                    Repository.getInstance(this)
                        .addUser(
                            User(
                                "ashrf.hakimi@hackeru.com",
                                "00000000",
                                "ashrf hakimi",
                                "Master"
                            )
                        )
                    Repository.getInstance(this)
                        .addUser(
                            User(
                                "yasin.bono@walla.com",
                                "00000000",
                                "yasin bono",
                                "Master",
                                image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT_wEufSNUkQUbAH4JTm8-bn2Yj8m_8XTrc0A&usqp=CAU"
                            )
                        )

                }
        }
    }

    private fun addLists() {
        listsViewModel.listsData.observe(this) {
            if (it.isEmpty())
                thread(start = true) {
                    Repository.getInstance(this).addList(
                        com.example.cooker.model.List(
                            "cristiano.ronaldo@gmail.com-Pasta",
                            "cristiano.ronaldo@gmail.com-Cristiano Ronaldo",
                            "Salad,FastFood,Home,",
                            participants = "leo.messi@hotmail.com-yasin.bono@walla.com-muhamad.salah@email.com-angel.dimaria@outlook.com-luka.modric@yahoo.com-andreas.inesta@gmail.com"
                        )
                    )
                    Repository.getInstance(this).addList(
                        com.example.cooker.model.List(
                            "leo.messi@hotmail.com-Spicy Chicken",
                            "leo.messi@hotmail.com-Lionel Messi",
                            "BBQ,Events,",
                            participants = "cristiano.ronaldo@gmail.com-leo.messi@hotmail.com-yasin.bono@walla.com-muhamad.salah@email.com-angel.dimaria@outlook.com"
                        )
                    )
                    Repository.getInstance(this).addList(
                        com.example.cooker.model.List(
                            "yasin.bono@walla.com-Kofta",
                            "yasin.bono@walla.com-yasin bono",
                            "BBQ,Home,",
                            participants = "cristiano.ronaldo@gmail.com-muhamad.salah@email.com-angel.dimaria@outlook.com"
                        )
                    )
                    Repository.getInstance(this).addList(
                        com.example.cooker.model.List(
                            "muhamad.salah@email.com-Falafel",
                            "muhamad.salah@email.com-muhamad salah",
                            "FastFood,Home,Events,",
                            participants = "cristiano.ronaldo@gmail.com-yasin.bono@walla.com"
                        )
                    )
                    Repository.getInstance(this).addList(
                        com.example.cooker.model.List(
                            "cristiano.ronaldo@gmail.com-Milk Shake",
                            "cristiano.ronaldo@gmail.com-Cristiano Ronaldo",
                            "Shakes,FastFood,Events,",
                            participants = "leo.messi@hotmail.com-yasin.bono@walla.com"
                        )
                    )
                    Repository.getInstance(this).addList(
                        com.example.cooker.model.List(
                            "angel.dimaria@outlook.com-Pizza",
                            "angel.dimaria@outlook.com-angel dimaria",
                            "FastFood,Home,Events,",
                            participants = "cristiano.ronaldo@gmail.com-leo.messi@hotmail.com-yasin.bono@walla.com"
                        )
                    )
                }
        }
    }

    private fun addItems() {
        itemsViewModel.itemsData.observe(this) {
            if (it.isEmpty())
                thread(start = true) {
                    Repository.getInstance(this).addItem(Item("cristiano.ronaldo@gmail.com-Pasta", "Eggs", info = "2 eggs,mix together"))
                    Repository.getInstance(this).addItem(Item("cristiano.ronaldo@gmail.com-Pasta", "Olive Oil", info = "2 big spoons,"))
                    Repository.getInstance(this).addItem(Item("cristiano.ronaldo@gmail.com-Pasta", "Salt", info = "3 grams,regular salt,"))

                    Repository.getInstance(this).addItem(Item("leo.messi@hotmail.com-Spicy Chicken", "Garlic Powder", info = "1 spoon,"))
                    Repository.getInstance(this).addItem(Item("leo.messi@hotmail.com-Spicy Chicken", "Paprika", info = "2 spoons,"))

                    Repository.getInstance(this).addItem(Item("yasin.bono@walla.com-Kofta", "Olive Oil", info = "5 grams,"))
                    Repository.getInstance(this).addItem(Item("yasin.bono@walla.com-Kofta", "Parsley", info = "brushed,200 grams,"))
                    Repository.getInstance(this).addItem(Item("yasin.bono@walla.com-Kofta", "Onion", info = "brushed,1 medium piece,"))

                    Repository.getInstance(this).addItem(Item("muhamad.salah@email.com-Falafel", "Hommos", info = "1 can,mix it,"))
                    Repository.getInstance(this).addItem(Item("muhamad.salah@email.com-Falafel", "Tohina", info = "1/2 can,mix with hommos,"))

                    Repository.getInstance(this).addItem(Item("cristiano.ronaldo@gmail.com-Milk Shake", "Mild", info = "250 ml,"))
                    Repository.getInstance(this).addItem(Item("cristiano.ronaldo@gmail.com-Milk Shake", "Choco", info = "100 gram,"))

                    Repository.getInstance(this).addItem(Item("angel.dimaria@outlook.com-Pizza", "Cheese", info = "500 gram,fixed brushed,"))
                    Repository.getInstance(this).addItem(Item("angel.dimaria@outlook.com-Pizza", "Dough", info = "large size,"))
                    Repository.getInstance(this).addItem(Item("angel.dimaria@outlook.com-Pizza", "Oil", info = "small pieces,"))

                }
        }
    }
}