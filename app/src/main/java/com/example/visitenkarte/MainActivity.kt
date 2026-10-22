package com.example.visitenkarte

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.visitenkarte.model.Author
import com.example.visitenkarte.model.Book
import com.example.visitenkarte.model.Publisher
import com.example.visitenkarte.ui.AddBookScreen
import com.example.visitenkarte.ui.BookCoverCard
import com.example.visitenkarte.ui.ListScreen
import com.example.visitenkarte.ui.MyFAB
import com.example.visitenkarte.ui.UserScreen
import com.example.visitenkarte.ui.theme.VisitenkarteTheme
import com.example.visitenkarte.viewmodels.BookListViewModel
import java.util.Date

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VisitenkarteTheme {
                val backStack = rememberNavBackStack(Screen.Home)
                Scaffold(
                    floatingActionButton = { MyFAB(backStack = backStack) },
                    floatingActionButtonPosition = FabPosition.End
                )
                { it: PaddingValues ->
                    MainNav(it,backStack = backStack)
                }
            }
        }
    }
}

@Composable
fun MainNav(paddingValues: PaddingValues, modifier: Modifier = Modifier, backStack: NavBackStack<NavKey>) {

    NavDisplay(
        modifier = modifier.fillMaxSize(),
        backStack = backStack,
        onBack = {backStack.removeLastOrNull()},
        entryProvider = entryProvider {
            entry<Screen.Home> {
                ListScreen(paddingValues, backStack = backStack)
            }
            entry<Screen.AddBook>{
                AddBookScreen()
            }
            entry<Screen.Account> {
                UserScreen(backStack = backStack)
            }
            entry<Screen.Details>{
                BookCoverCard(
                    onCloseFunction = { backStack.removeLastOrNull(); },
                    isbn = it.isbn
                )
            }

        }
    )
}

