package com.example.visitenkarte

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.visitenkarte.helper.drawableResourceToBitmap
import com.example.visitenkarte.helper.toByteArray
import com.example.visitenkarte.model.Author
import com.example.visitenkarte.model.Book
import com.example.visitenkarte.model.Publisher
import com.example.visitenkarte.ui.theme.VisitenkarteTheme
import java.util.Date

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val booksList = mutableListOf(
                Book(
                    title = "Star Wars Dark Lord The Rise of Darth Vader",
                    publisher = Publisher.BENVELET,
                    numPages = 643,
                    originalReleaseDate = Date(),
                    frontCoverByteArray = drawableResourceToBitmap(LocalContext.current.resources,R.drawable.tmp_img).toByteArray(),
                    blurbText = "The Text from the Back Cover of The Book",
                    author = Author("James", "Luceno", "01-04-1947")
                ),
                Book(
                    title = "Star Wars Tarkin",
                    publisher = Publisher.BENVELET,
                    numPages = 643,
                    originalReleaseDate = Date(),
                    frontCoverByteArray = drawableResourceToBitmap(LocalContext.current.resources,R.drawable.tarkin).toByteArray(),
                    blurbText = "The Text from the Back Cover of The Book",
                    author = Author("James", "Luceno", "01-04-1947")
                ),
                Book(
                    title = "Star Wars Darth Plagueis",
                    publisher = Publisher.BENVELET,
                    numPages = 756,
                    originalReleaseDate = Date(),
                    frontCoverByteArray = drawableResourceToBitmap(LocalContext.current.resources,R.drawable.star_wars_darth_plagueis_taschenbuch_james_luceno).toByteArray(),
                    blurbText = "asdf",
                    author = Author("James", "Luceno", "01-04-1947")

                )
            )

            VisitenkarteTheme {
                Scaffold(
                    floatingActionButton = {
                        MyFAB()
                                           },
                    floatingActionButtonPosition = FabPosition.End
                )
                { it: PaddingValues ->
                    ListScreen(it,booksList)
                }
            }
        }
    }
}

@Composable
fun MyFAB(modifier: Modifier = Modifier) {
    var extended by remember { mutableStateOf(false)}
    val transition = updateTransition(targetState = extended, label = "FAB transition")

    val rotation = transition.animateFloat(
        label = "rotation",
        targetValueByState = {if (it) 45f else 0f}
    )
    val items = listOf(
        MiniFabItems(Icons.Filled.AccountCircle,"Account"),
        MiniFabItems(Icons.Filled.Edit,"Add Book"),
        MiniFabItems(Icons.Filled.Done,"Finished Books")
        )

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
            Column (horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(8.dp)){
                items.forEach { item ->
                    MiniFABs(item.icon, item.title)
                }
            }
        }
        LargeFloatingActionButton(
            onClick = { extended = !extended },
            shape = CircleShape,
        ) {
            Icon(
                Icons.Filled.Add,
                "Large FAB",
                modifier = Modifier.rotate(rotation.value)
            )
        }
    }
}

@Composable
fun MiniFABs(icon:ImageVector, title:String) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End)
    {
        Spacer(Modifier.weight(1f))
     Box(modifier = Modifier
         .border(2.dp,
             Color(0xffffefe5),
             RoundedCornerShape(8.dp)
         )
         .padding(4.dp)){
            Text(title)
        }
        FloatingActionButton(onClick = {}, modifier = Modifier.padding(3.dp)) {
            Icon(icon, contentDescription = null)
        }
    }


}

data class MiniFabItems(val icon: ImageVector, val title:String)

@Composable
fun ScreenContent(name: String, role: String, phoneNumber: String, smHandle: String, email: String, modifier:Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        WorkInfo(name, role)
        Spacer(modifier.height(250.dp))
        ContactInfo(phoneNumber,smHandle,email,modifier)
    }

}

@Composable
fun WorkInfo(name: String, role: String){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            name,
            fontSize = 45.sp,
            textAlign = TextAlign.Center
        )
        Text(
            role,
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )
    }
}
@Composable
fun ContactInfo(phoneNumber: String, smHandle: String ,email: String, modifier: Modifier){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Row{
            Icon(
                imageVector = Icons.Default.Call,
                contentDescription = null
            )
            Text(phoneNumber, fontSize = 25.sp)
        }
        Row {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null
            )
            Text(smHandle, fontSize = 25.sp)
        }
        Row {
            Icon(
                imageVector = Icons.Default.MailOutline,
                contentDescription = null
            )
            Text(email, fontSize = 25.sp)
        }
    }
}