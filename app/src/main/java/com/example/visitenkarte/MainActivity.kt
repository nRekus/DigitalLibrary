package com.example.visitenkarte

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.ui.platform.LocalContext
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
            //Dummy
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
                        floatingActionButton = { MyFAB() },
                        floatingActionButtonPosition = FabPosition.End
                    )
                    { it: PaddingValues ->

                        ListScreen(it, booksList)

                }
            }
        }
    }
}
