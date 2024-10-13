package com.example.zencar_test.utils

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale


class DateTransformation() : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return dateFilter(text)
    }
}

fun dateFilter(text: AnnotatedString): TransformedText {

    val trimmed = if (text.text.length >= 8) text.text.substring(0..7) else text.text
    var out = ""
    for (i in trimmed.indices) {
        out += trimmed[i]
        if (i % 2 == 1 && i < 4) out += "."
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 1) return offset
            if (offset <= 3) return offset +1
            if (offset <= 8) return offset +2
            return 10
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <=2) return offset
            if (offset <=5) return offset -1
            if (offset <=10) return offset -2
            return 8
        }
    }

    return TransformedText(AnnotatedString(out), numberOffsetTranslator)
}



fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("ddMMyyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}


fun formatDate(inputDate: String): String {
    if (inputDate.isEmpty()) {
        return ""
    }
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val input = DateTimeFormatter.ofPattern("ddMMyyyy")
        val output = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val date = LocalDate.parse(inputDate, input)
        date.format(output)
    } else {
        val input = SimpleDateFormat("ddMMyyyy", Locale.getDefault())
        val output = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val date = input.parse(inputDate) ?: return ""
        output.format(date)
    }
}

fun formatDateAndTime(inputDate: String): String {
    if (inputDate.isEmpty()) {
        return ""
    }
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val input = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")
        val output = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val date = LocalDate.parse(inputDate, input)
        date.format(output)
    } else {
        val input = SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault())
        val output = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val date = input.parse(inputDate) ?: return ""
        output.format(date)
    }
}


fun formatLocalDateInDate(inputDate: LocalDateTime): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val outputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")
        inputDate.format(outputFormatter)
    } else {
        val output = SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault())
        output.format(Date())
    }
}