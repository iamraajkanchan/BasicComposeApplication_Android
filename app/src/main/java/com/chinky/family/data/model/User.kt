package com.chinky.family.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserTable")
data class User(@PrimaryKey val id: Int, val name: String, val email: String, val phone: String) :
    Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    )

    override fun describeContents(): Int = 0

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(email)
        parcel.writeString(phone)
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User = User(parcel)

        override fun newArray(size: Int): Array<User?> = arrayOfNulls(size)
    }
}
