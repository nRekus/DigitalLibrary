package com.example.visitenkarte

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.visitenkarte.ui.AddBookScreen
//import androidx.navigation3.runtime.entryProvider
//import androidx.navigation3.ui.NavDisplay
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
            val backStack = remember { mutableStateListOf<Any>() }
//            NavDisplay(
//                backStack = backStack,
//                onBack = {
//                    backStack.removeLastOrNull()
//                },
//                entryProvider = entryProvider {
//                    entry<Screen.Home>{
//
//                    }
//                }
//
//            )
            VisitenkarteTheme {
//                    Scaffold(
//                        floatingActionButton = { MyFAB() },
//                        floatingActionButtonPosition = FabPosition.End
//                    )
//                    { it: PaddingValues ->
//                        ListScreen(it)
//                    }
                    AddBookScreen(Modifier)
            }
        }
    }
}
