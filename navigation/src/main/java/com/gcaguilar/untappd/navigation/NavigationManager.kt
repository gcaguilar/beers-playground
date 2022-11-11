package com.gcaguilar.untappd.navigation

import androidx.navigation.NamedNavArgument
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

val Default = object : NavigationCommand {

    override val arguments = emptyList<NamedNavArgument>()

    override val destination = ""

}

class NavigationManager @Inject constructor() {

    var commands = MutableStateFlow(Default)

    fun navigate(
        directions: NavigationCommand
    ) {
        commands.value = directions
    }
}