package com.example.pantallapersonalizada.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pantallapersonalizada.components.CustomInput

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    onRegisterClick: (
        fullName: String,
        email: String,
        phone: String,
        password: String
    ) -> Unit = { _, _, _, _ -> }
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "REGISTRARSE",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Nombre completo
        CustomInput(
            value = fullName,
            onValueChange = { fullName = it },
            placeholder = "Nombre completo",
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Correo electrónico
        CustomInput(
            value = email,
            onValueChange = { email = it },
            placeholder = "Correo electrónico",
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Teléfono
        CustomInput(
            value = phone,
            onValueChange = { phone = it },
            placeholder = "Teléfono",
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Contraseña
        CustomInput(
            value = password,
            onValueChange = {
                password = it
                if (confirmPassword.isNotEmpty()) passwordError = it != confirmPassword
            },
            placeholder = "Contraseña",
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodyLarge,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Confirmar contraseña
        CustomInput(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                passwordError = password != it
            },
            placeholder = "Confirmar contraseña",
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodyLarge,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (!passwordError && password.isNotEmpty()) {
                    onRegisterClick(fullName, email, phone, password)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !passwordError && password.isNotEmpty() && confirmPassword.isNotEmpty()
        ) {
            Text("Registrarse")
        }
    }


}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    MaterialTheme {
        RegisterScreen()
    }
}