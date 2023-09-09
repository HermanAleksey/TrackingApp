package com.justparokq.feature.splash_screen.api

import androidx.lifecycle.viewModelScope
import com.justparokq.core.common.view_model.NavigationBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
) : NavigationBaseViewModel<SplashScreenNavRoute>() {

    fun onLaunchSplashScreen() {
        viewModelScope.launch {

        }
    }
}