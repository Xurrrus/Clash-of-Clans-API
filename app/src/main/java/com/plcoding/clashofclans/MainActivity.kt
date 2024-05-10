package com.plcoding.clashofclans

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.plcoding.clashofclans.presentation.WarriorsScreen
import com.plcoding.clashofclans.presentation.WarriorsViewModel
import com.plcoding.clashofclans.ui.theme.ClashOfClansAppTheme

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClashOfClansAppTheme{
                val viewModel = hiltViewModel<WarriorsViewModel>()
                val state by viewModel.state.collectAsState()

                WarriorsScreen(
                    state = state,
                    onSelectWarrior = viewModel::selectWarrior,
                    onDismissWarriorDialog = viewModel::dismissWarriorDialog,
                    viewModel = viewModel

                )
            }


        }
    }
}