package dev.abdullah.noteapp.feature_note.presentation.notes.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.abdullah.noteapp.feature_note.domin.util.NoteOrder
import dev.abdullah.noteapp.feature_note.domin.util.OrderType


@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    onOrderChange: (NoteOrder) -> Unit
) {

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Title",
                selected = noteOrder is NoteOrder.Title
            ) { onOrderChange(NoteOrder.Title(noteOrder.orderType)) }
            Spacer(Modifier.width(8.dp))

            DefaultRadioButton(
                text = "Date",
                selected = noteOrder is NoteOrder.Date
            ) { onOrderChange(NoteOrder.Date(noteOrder.orderType)) }
            Spacer(Modifier.width(8.dp))

            DefaultRadioButton(
                text = "Color",
                selected = noteOrder is NoteOrder.Color
            ) { onOrderChange(NoteOrder.Color(noteOrder.orderType)) }


        }
        Spacer(Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Ascending",
                selected = noteOrder.orderType is OrderType.Ascending
            ) {
                onOrderChange(noteOrder.copy(OrderType.Ascending))
            }
            Spacer(Modifier.width(8.dp))

            DefaultRadioButton(
                text = "Descending",
                selected = noteOrder.orderType is OrderType.Descending
            ) { onOrderChange(noteOrder.copy(OrderType.Descending)) }


        }


    }

}