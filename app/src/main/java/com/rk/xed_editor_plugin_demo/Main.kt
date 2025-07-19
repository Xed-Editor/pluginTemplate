package com.rk.xed_editor_plugin_demo

import android.graphics.drawable.PaintDrawable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Icon
import com.rk.components.compose.preferences.base.PreferenceGroup
import com.rk.components.compose.preferences.base.PreferenceLayout
import com.rk.controlpanel.ControlItem
import com.rk.extension.ExtensionAPI
import com.rk.extension.Hooks
import com.rk.extension.SettingsScreen
import com.rk.xededitor.MainActivity.MainActivity
import com.rk.xededitor.ui.components.SettingsToggle
import com.rk.xededitor.ui.screens.settings.feature_toggles.Feature
import com.rk.xededitor.Editor.Editor
import android.widget.Toast
import com.rk.xededitor.settings.Settings
import com.rk.xededitor.ui.components.SimpleEditText
import com.rk.xededitor.ui.components.rememberMutableState

class Main : ExtensionAPI() {
    override fun onPluginLoaded() {
        // Add a button to the editor's toolbar
        val button = ControlItem(
            name = "AI",
            icon = Icons.Default.Build,
            onClick = {
                val apiKey = Settings.get("api_key", "")
                if (apiKey.isEmpty()) {
                    Toast.makeText(MainActivity.context, "Please set your API key in the settings", Toast.LENGTH_SHORT).show()
                    return@ControlItem
                }

                // a toast message
                Toast.makeText(MainActivity.context, "AI functionality triggered", Toast.LENGTH_SHORT).show()

                val editor = Editor.getEditor()
                val selectedText = editor.selectedText
                if (selectedText.isNotEmpty()) {
                    val reversedText = selectedText.reversed()
                    editor.replaceSelection(reversedText)
                }
            }
        )
        Hooks.add_control_item(button)
    }

    override fun onAppCreated() {
       
    }

    override fun onAppLaunched() {
       
    }

    override fun onAppPaused() {
       
    }

    override fun onAppResumed() {
       
    }

    override fun onAppDestroyed() {
       
    }

    override fun onLowMemory() {
       
    }

    override fun getSettingsScreen(): SettingsScreen {
        return SettingsScreen(
            "AI Plugin Settings",
            "Configure the AI plugin",
            Icons.Default.Build
        ) {
            val apiKey by rememberMutableState(
                key = "api_key",
                default = ""
            )
            SimpleEditText(
                value = apiKey,
                onValueChange = { Settings.put("api_key", it) },
                label = "API Key",
                placeholder = "Enter your API key here"
            )
        }
    }
}