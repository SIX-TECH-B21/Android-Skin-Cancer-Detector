package com.bangkit.myproject.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "diagnose")

@Parcelize
data class DiagnoseEntity(

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "age")
    var age: Int,

    @ColumnInfo(name = "sex")
    var sex: Boolean,

    @ColumnInfo(name = "result")
    var result: String,

    @ColumnInfo(name = "percentage")
    var percentage: String,

    @ColumnInfo(name = "image")
    var image: String

) : Parcelable
