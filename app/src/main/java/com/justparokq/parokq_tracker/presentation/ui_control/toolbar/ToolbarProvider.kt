package com.justparokq.parokq_tracker.presentation.ui_control.toolbar

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.justparokq.core.common.navigation.NavDependenciesProvider
import com.justparokq.core.design_system.R
import com.justparokq.core.design_system.dialog.DialogController
import com.justparokq.core.design_system.toolbar.ToolbarProvider

class ToolbarProviderImpl(
    private val viewModel: ToolbarViewModel,
    private val dialogController: DialogController,
) : ToolbarProvider() {

    @Composable
    override fun Display() {
        val navDependencies = ((LocalContext.current as? Activity) as? NavDependenciesProvider)
            ?.provideDependencies(ToolbarNavDependencies::class.java)

        LaunchedEffect(key1 = Unit) {
            viewModel.onScreenEntered()
        }

        LaunchedEffect(key1 = viewModel.navigationEvent.collectAsState(null)) {
            viewModel.navigationEvent.collect { event ->
                event?.let { navRoute ->
                    navDependencies?.let {
                        navRoute.tryNavigate(it)
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .background(color = MaterialTheme.colors.background)
                .padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.ic_app_icon
                ),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
        }
    }
}