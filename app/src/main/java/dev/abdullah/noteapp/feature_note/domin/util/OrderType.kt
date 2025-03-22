package dev.abdullah.noteapp.feature_note.domin.util

sealed class OrderType {
    data object Ascending : OrderType()
    data object Descending : OrderType()
}