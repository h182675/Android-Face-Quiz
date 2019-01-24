package com.android.oblig.modules

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Person(
    @PrimaryKey var id:Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB) var picture: ByteArray?
    )
