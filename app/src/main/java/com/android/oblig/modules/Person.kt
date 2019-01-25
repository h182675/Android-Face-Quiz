package com.android.oblig.modules

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity
data class Person(
    @PrimaryKey var id:Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "picture", typeAffinity = ColumnInfo.BLOB) var picture: ByteArray?
    )
