package com.example.cooker.view.activities

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.example.cooker.R
import com.example.cooker.model.List
import com.example.cooker.model.User
import com.example.cooker.model.database.Repository
import com.example.cooker.other.adapters.ListsAdapter
import com.example.cooker.other.managers.ImagesManager
import com.example.cooker.other.service.ItemService
import com.example.cooker.view.fragments.FilterFragment
import com.example.cooker.view.fragments.NewListFragment
import com.example.cooker.viewModel.ListsViewModel
import com.example.cooker.viewModel.UsersViewModel
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.lists_activity.*
import kotlinx.android.synthetic.main.menu_header.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import kotlin.concurrent.thread

class ListsActivity : AppCompatActivity() {

    private val listsViewModel: ListsViewModel by viewModels()
    private val usersViewModel: UsersViewModel by viewModels()
    lateinit var toggle: ActionBarDrawerToggle
    private val adapter = ListsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lists_activity)
        val userEmail = intent.extras!!.getString("userEmail")
        var filterListsStr = intent.getStringExtra("filter")
        if (filterListsStr == null)
            filterListsStr = ""
        setUser(userEmail!!, filterListsStr!!)
    }

    private fun setUser(userEmail: String, filterListsStr: String) {
        usersViewModel.usersData.observe(this) {
            for (user in it)
                if (user.email == userEmail) {
                    listsRecyclerView(user, filterListsStr)
                    cameraPermission(user)
                    addNewList(user)
                    searchBar(user)
                    filterBar(user)
                    Handler().postDelayed({setMenuBar(user)}, 500)
                }
        }
    }

    private fun addNewList(user: User) {
        val newListFragment = NewListFragment(user, this)
        add_list.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.new_list_fragment, newListFragment).commit()
        }
    }

    private fun addUserImage() {
        if (allowResult) {
            if (allowCamera)
                cameraAlert(this)
            else
                userImageAlert(this)
        }
        else
            Toast.makeText(this, "Only Owner Allow To Edit This Field.", Toast.LENGTH_SHORT).show()
    }


    //-------------------- lists RecyclerView --------------------//
    private fun listsRecyclerView(user: User, filterListsStr: String) {
        listsRecyclerView?.adapter = adapter
        if (filterListsStr == "")
            listsViewModel.listsData.observe(this) { adapter.setList(setUserLists(it, user), user, this) }
        else
            showFilteredLists(user, filterListsStr)
    }

    private fun setUserLists(allLists: kotlin.collections.List<List>, user: User): MutableList<List> {
        var userLists = mutableListOf<List>()
        for (list in allLists) {
            var flag = 0
            for (userList in userLists)
                if (userList.name == list.name)
                    flag++
            if (list.owner.split("-")[0] == user.email && flag == 0)
                userLists.add(list)
            else if (list.participants!!.isNotEmpty())
                    if (list.participants!!.contains(user.email) && flag == 0)
                        userLists.add(list)
        }
        return userLists
    }


    //-------------------- Search Bar --------------------//
    private fun searchBar(user: User) {
        search.clearFocus()
        search.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                filterSearchLists(newText, user)
                return true
            }

        })
    }

    private fun filterSearchLists(newText: String?, user: User) {
        val filteredLists = mutableListOf<List>()
        var userLists = mutableListOf<List>()
        listsViewModel.listsData.observe(this) { userLists = setUserLists(it, user) }
        for (list in userLists)
            if (list.name.split('-')[1].toLowerCase().contains(newText!!))
                filteredLists.add(list)
        if (filteredLists.isEmpty())
            Toast.makeText(this, "No Lists Found", Toast.LENGTH_SHORT).show()
        adapter.setList(filteredLists, user, this)

    }


    //-------------------- Filter Bar --------------------//
    private fun filterBar(user: User) {
        var userLists = mutableListOf<List>()
        listsViewModel.listsData.observe(this) {
            userLists = setUserLists(it, user)
        }
        filter.setOnClickListener {
            val filterFragment = FilterFragment(user, userLists)
            supportFragmentManager.beginTransaction().replace(R.id.filter_fragment, filterFragment).commit()
        }
    }

    private fun showFilteredLists(user: User, filterListsStr: String) {
        val filterListsArr = filterListsStr.split(',')
        val filteredLists = mutableListOf<List>()
        var userLists = mutableListOf<List>()
        listsViewModel.listsData.observe(this) {
            userLists = setUserLists(it, user)
            for (list in userLists)
                if (filterListsArr.contains(list.name.split('-')[1]))
                    filteredLists.add(list)
            adapter.setList(filteredLists, user, this)
        }
    }


    //-------------------- menu methods --------------------//
    private fun setMenuBar(user: User) {
        val drawer: DrawerLayout = findViewById(R.id.lists_drawer_layout)
        val menu: NavigationView = findViewById(R.id.menu)
        val header: View = menu.getHeaderView(0)

        toggle = ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val userName = header.findViewById<TextView>(R.id.menu_user_name)
        val userType = header.findViewById<TextView>(R.id.menu_user_type)
        val userEmail = header.findViewById<TextView>(R.id.menu_user_email)
        val userImage = header.findViewById<de.hdodenhof.circleimageview.CircleImageView>(R.id.menu_user_image)

        userName.text = user.name
        userEmail.text = user.email
        userType.text = user.type

        if (user.image!!.isNotEmpty())
            if (user.image!!.contains("https://"))
                Glide.with(this).load(user.image).into(menu_user_image)
            else
                menu_user_image.setImageURI(Uri.parse(user.image))

        userImage.setOnClickListener { addUserImage() }

        menu.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.users -> users()
                R.id.about -> about()
                R.id.logout -> logout()
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun users() {
        val intent = Intent(this, UsersActivity::class.java)
        startActivity(intent)    }

    private fun about() {
        val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
    }

    private fun logout() {
        val serviceIntent = Intent(this, ItemService::class.java)
        ContextCompat.startForegroundService(this, serviceIntent)
        this.finish()
    }


    /* ---------------- Camera Images Update ---------------- */
    private var allowCamera = false
    private var allowResult = false
    private var currentActivity = this
    private var currentUser: User? = null
    private val userContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val uri = result.data?.data
        menu_user_image.setImageURI(uri)
        ImagesManager.userImageFromGallery(uri!!, this, currentUser!!)
    }

    private fun cameraPermission(user: User) {
        currentUser = user
        if (ActivityCompat.checkSelfPermission(currentActivity, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)  {
            ActivityCompat.requestPermissions(currentActivity, arrayOf(android.Manifest.permission.CAMERA), 111)
            allowResult = true
        }
        else {
            allowCamera = true
            allowResult = true
        }
    }

    private fun cameraAlert(context: Context) {
        usersViewModel.viewModelScope.launch(Dispatchers.Main) {
            val alertBuilder = AlertDialog.Builder(context)
            alertBuilder.setTitle("Change Image")
            alertBuilder.setMessage("Select Image Source:  ")
            alertBuilder.setNeutralButton("Cancel") { dialogInterface: DialogInterface, i: Int -> }
            alertBuilder.setNegativeButton("Camera") { dialogInterface: DialogInterface, i: Int ->
                var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, 101)
            }
            alertBuilder.setPositiveButton("Gallery") { dialogInterface: DialogInterface, i: Int ->
                usersViewModel.viewModelScope.launch(Dispatchers.IO) {
                    ImagesManager.galleryImage(userContent)
                }
            }
            alertBuilder.show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101) {
            var pic = data?.getParcelableExtra<Bitmap>("data")
            val bytes = ByteArrayOutputStream()
            if (pic != null) {
                pic!!.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
                val path = MediaStore.Images.Media.insertImage(contentResolver, pic, "val", null)
                val uri = Uri.parse(path)
                menu_user_image.setImageURI(uri)
                thread(start = true) { Repository.getInstance(this).updateUserImage(currentUser!!, uri.toString()) }
            }
        }
    }

    private fun userImageAlert(context: Context) {
        usersViewModel.viewModelScope.launch(Dispatchers.Main) {
            val alertBuilder = AlertDialog.Builder(context)
            alertBuilder.setTitle("Change Image")
            alertBuilder.setMessage("Select Image Source:  ")
            alertBuilder.setNeutralButton("Cancel") { dialogInterface: DialogInterface, i: Int -> }
            alertBuilder.setPositiveButton("Gallery") { dialogInterface: DialogInterface, i: Int ->
                usersViewModel.viewModelScope.launch(Dispatchers.IO) {
                    ImagesManager.galleryImage(userContent)
                }
            }
            alertBuilder.show()
        }
    }
}