package com.example.visitenkarte

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
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
                    floatingActionButton = {MyFAB(Modifier.fillMaxSize())},
                    floatingActionButtonPosition = FabPosition.EndOverlay
                )
                { it: PaddingValues ->
                    ListScreen(it,booksList)
                }

                //BookCoverCard(modifier = Modifier)

//                    ScreenContent(
//                        name = "John Doe",
//                        role = "Data Scientist and Computer Vision Specialist",
//                        "+49165409633",
//                        smHandle = "@SmartDev",
//                        email = "john.doe@gmail.com",
//                        modifier = Modifier
//                            .background(Color.Cyan)
//                    )
            }
        }
    }
}

@Composable
fun MyFAB(modifier: Modifier = Modifier) {
    var visible =  remember { mutableStateOf(true)}
    val density = LocalDensity.current
    LargeFloatingActionButton(
        onClick = {
            AnimatedVisibility(visible=visible,
                enter = slideInVertically { -30 },
                exit = slideOutVertically { 30 }) { }
        },
        shape = CircleShape,
    ) { Icon(Icons.Filled.Add, "Large FAB") }
}

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