package cz.mtrakal.bscdemo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Note(val id: Int, val title: String) : Parcelable {
    override fun toString(): String {
        return "Note #$id: $title"
    }
}