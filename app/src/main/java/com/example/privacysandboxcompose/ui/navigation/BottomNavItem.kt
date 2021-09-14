package com.example.privacysandboxcompose.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(var title: String, var icon: ImageVector, var screen_route: String){
    object Legacy: BottomNavItem("Legacy",Icons.Filled.Person,"legacy")
    object RuntimeEnabled : BottomNavItem("Runtime Enabled", Icons.Filled.Warning,"re")
}

