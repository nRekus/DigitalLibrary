package com.example.visitenkarte

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
//import com.example.visitenkarte.data.local.database.LibraryDatabase
import com.example.visitenkarte.ui.ListScreen
import com.example.visitenkarte.ui.MyFAB
import com.example.visitenkarte.ui.theme.VisitenkarteTheme

class MainActivity : ComponentActivity() {
    //val db = LibraryDatabase()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VisitenkarteTheme {
                    Scaffold(
                        floatingActionButton = { MyFAB() },
                        floatingActionButtonPosition = FabPosition.End
                    )
                    { it: PaddingValues ->
                        ListScreen(it)
                }
            }
        }
    }
}
