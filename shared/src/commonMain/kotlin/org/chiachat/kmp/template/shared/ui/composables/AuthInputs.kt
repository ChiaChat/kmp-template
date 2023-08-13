@file:OptIn(ExperimentalMaterial3Api::class)

package org.chiachat.kmp.template.shared.ui.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

internal object AuthInputs {
    private val textFieldModifier = Modifier.height(60.dp).width(300.dp)

    private fun isValidEmail(email: String): Boolean {
        return email.contains("@") && email.contains(".")
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 8
    }

    @Composable
    fun TextFieldLabel(label: String) {
        Text(label, color = MaterialTheme.colorScheme.onBackground)
    }

    @Composable
    fun Username(username: String, onUsernameChange: (String) -> Unit) {
        OutlinedTextField(
            label = { TextFieldLabel("Username") },
            value = username,
            onValueChange = onUsernameChange,
            modifier = textFieldModifier,
            colors = TextFieldDefaults.outlinedTextFieldColors()
        )
    }

    @Composable
    fun Email(email: String, onEmailChange: (String) -> Unit) {
        val error = !isValidEmail(email) && email.isNotBlank()

        if (error) {
            Text("Invalid email address", color = MaterialTheme.colorScheme.error)
            Spacer(Modifier.size(5.dp))
        }

        OutlinedTextField(
            label = { TextFieldLabel("Email") },
            value = email,
            onValueChange = onEmailChange,
            modifier = textFieldModifier,
            colors = TextFieldDefaults.outlinedTextFieldColors(),
            isError = error
        )
    }

    @Composable
    fun Password(password: String, onPasswordChange: (String) -> Unit) {
        val error = !isPasswordValid(password) && password.isNotBlank()
        if (error) {
            Text("Password must be at least 8 characters", color = MaterialTheme.colorScheme.error)
            Spacer(Modifier.size(5.dp))
        }
        OutlinedTextField(
            label = { TextFieldLabel("Password") },
            value = password,
            onValueChange = onPasswordChange,
            visualTransformation = PasswordVisualTransformation(),
            modifier = textFieldModifier,
            colors = TextFieldDefaults.outlinedTextFieldColors(),
            isError = error
        )
    }

    @Composable
    fun ConfirmPassword(
        password: String,
        confirmPassword: String,
        onConfirmPasswordChange: (String) -> Unit
    ) {
        val error =
            password.isNotBlank() && confirmPassword.isNotBlank() && password != confirmPassword
        if (error) {
            Text("Passwords do not match", color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.size(5.dp))
        }
        OutlinedTextField(
            label = { TextFieldLabel("Confirm Password") },
            value = confirmPassword,
            onValueChange = onConfirmPasswordChange,
            visualTransformation = PasswordVisualTransformation(),
            isError = error,
            modifier = textFieldModifier,
            colors = TextFieldDefaults.outlinedTextFieldColors(),
        )
    }
}
