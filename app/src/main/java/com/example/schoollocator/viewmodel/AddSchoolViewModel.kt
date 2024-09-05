package com.example.schoollocator.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class AddSchoolViewModel: ViewModel() {

    private val _schoolName =  mutableStateOf("")  // school name
    val username: MutableState<String> = _schoolName

    private val _schoolAddress =  mutableStateOf("")  // school address
    val schoolAddress: MutableState<String> = _schoolAddress

    private val _schoolType =  mutableStateOf("")  // school type
    val schoolType: MutableState<String> = _schoolType

    private val _schoolAbout =  mutableStateOf("") // school about
    val schoolAbout: MutableState<String> = _schoolAbout

    private val _schoolEmail =  mutableStateOf("") // school email
    val schoolEmail: MutableState<String> = _schoolEmail


    //  This for adding the school
    //  TODO: Implement the addSchool function
    fun addSchool(){

    }

    fun  onSchoolNameChange(schoolName: String){
        _schoolName.value = schoolName
    }

    fun  onSchoolAddressChange(schoolAddress: String){
        _schoolAddress.value = schoolAddress
    }

    fun  onSchoolTypeChange(schoolType: String){
        _schoolType.value = schoolType
    }

    fun  onSchoolAboutChange(schoolAbout: String){
        _schoolAbout.value = schoolAbout
    }

    fun  onSchoolEmailChange(schoolEmail: String){
        _schoolEmail.value = schoolEmail
    }
}