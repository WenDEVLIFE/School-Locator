package com.example.schoollocator.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MenuViewModel: ViewModel() {

    private val _imageUrl = MutableStateFlow<String?>(null)
    val imageUrl: StateFlow<String?> get() = _imageUrl

    // This is used to fetch the image url
    fun fetchImageUrl(username: String?) {
        viewModelScope.launch {

            val db = Firebase.firestore

            db.collection("Users").whereEqualTo("Username",username).get()
                .addOnSuccessListener { documents ->
                    if (documents.isEmpty) {
                        Log.d(TAG, "No such document")
                    } else {
                        for (document in documents) {
                            Log.d(TAG, "${document.id} => ${document.data}")
                            val url = document.data["Filepath"].toString()
                            var imagefile = document.data["Image"].toString()
                            val storageRef = FirebaseStorage.getInstance().reference.child("Profile/27538841-20ae-40cb-91a6-9f02771f2a58.png")
                            storageRef.downloadUrl.addOnSuccessListener { uri ->
                                _imageUrl.value = uri.toString()
                            }.addOnFailureListener {
                                _imageUrl.value = null
                            }
                        }
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents: ", exception)
                }

           // db.co
        }
    }
}