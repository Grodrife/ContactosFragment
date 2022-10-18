package com.gabrielrf.contactosfragment

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contacto(val imagen : String, val name : String , val phonenumber : String, val email : String): Parcelable {
}