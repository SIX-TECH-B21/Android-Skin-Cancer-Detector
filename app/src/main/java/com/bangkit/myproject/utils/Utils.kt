package com.bangkit.myproject.utils

import android.content.ContentResolver
import android.net.Uri
import android.provider.OpenableColumns

fun ContentResolver.getFileName(uriData: Uri): String {
    var name = ""
    val cursor = query(uriData, null, null, null, null)
    cursor?.use {
        it.moveToFirst()
        name = cursor.getString(it.getColumnIndex(OpenableColumns.DISPLAY_NAME))
    }
    return name
}