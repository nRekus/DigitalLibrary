package com.example.visitenkarte

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun MyFAB(modifier: Modifier = Modifier) {
    var extended by remember { mutableStateOf(false) }
    val transition = updateTransition(targetState = extended, label = "FAB transition")

    val rotation = transition.animateFloat(
        label = "rotation",
        targetValueByState = { if (it) 45f else 0f }
    )
    val items = remember {
        listOf(
            MiniFabItems(Icons.Filled.AccountCircle, "Account"),
            MiniFabItems(Icons.Filled.Edit, "Add Book"),
            MiniFabItems(Icons.Filled.Done, "Finished Books")
        )
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AnimatedVisibility(
            visible = extended,
            enter = fadeIn() + slideInVertically(initialOffsetY = { it }) + expandVertically(),
            exit = fadeOut() + slideOutVertically(targetOffsetY = { it }) + shrinkVertically()
        ) {
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items.forEach { item ->
                    key(item.title) {
                        MiniFABs(item.icon, item.title)
                    }
                }
            }
        }
        FloatingActionButton(
            onClick = { extended = !extended },
            shape = CircleShape,
            modifier = modifier.padding(4.dp)
        ) {
            Icon(
                Icons.Filled.Add,
                "Large FAB",
                modifier = Modifier.graphicsLayer { rotationZ = rotation.value}
            )
        }
    }
}

@Composable
fun MiniFABs(icon: ImageVector, title: String) {
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    )
    {
        Spacer(Modifier.weight(1f))
        Box(
            modifier = Modifier
//                .border(
//                    2.dp,
//                    Color(0xffffefe5),
//                    RoundedCornerShape(8.dp)
//                )
                .padding(4.dp)
        ) {
            Text(title)
        }
        FloatingActionButton(onClick = {}, modifier = Modifier.padding(4.dp)) {
            Icon(icon, contentDescription = null)
        }
    }


}
@Stable
data class MiniFabItems(val icon: ImageVector, val title: String)