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

    var fullNameError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var phoneError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var confirmPasswordError by remember { mutableStateOf<String?>(null) }

    fun validate(): Boolean {
        var isValid = true

        if (fullName.isEmpty()) {
            fullNameError = "El nombre es obligatorio"
            isValid = false
        } else {
            fullNameError = null
        }

        if (email.isEmpty()) {
            emailError = "El correo es obligatorio"
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailError = "Correo no válido"
            isValid = false
        } else {
            emailError = null
        }

        if (phone.isEmpty()) {
            phoneError = "El teléfono es obligatorio"
            isValid = false
        } else if (phone.length < 8) {
            phoneError = "Teléfono demasiado corto"
            isValid = false
        } else {
            phoneError = null
        }

        if (password.isEmpty()) {
            passwordError = "La contraseña es obligatoria"
            isValid = false
        } else if (password.length < 6) {
            passwordError = "Mínimo 6 caracteres"
            isValid = false
        } else {
            passwordError = null
        }

        if (confirmPassword.isEmpty()) {
            confirmPasswordError = "Confirma tu contraseña"
            isValid = false
        } else if (confirmPassword != password) {
            confirmPasswordError = "Las contraseñas no coinciden"
            isValid = false
        } else {
            confirmPasswordError = null
        }

        return isValid
    }

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
            onValueChange = { 
                fullName = it
                if (fullNameError != null) fullNameError = null
            },
            placeholder = "Nombre completo",
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodyLarge,
            isError = fullNameError != null,
            errorMessage = fullNameError
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Correo electrónico
        CustomInput(
            value = email,
            onValueChange = { 
                email = it
                if (emailError != null) emailError = null
            },
            placeholder = "Correo electrónico",
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodyLarge,
            isError = emailError != null,
            errorMessage = emailError
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Teléfono
        CustomInput(
            value = phone,
            onValueChange = { 
                phone = it
                if (phoneError != null) phoneError = null
            },
            placeholder = "Teléfono",
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodyLarge,
            isError = phoneError != null,
            errorMessage = phoneError
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Contraseña
        CustomInput(
            value = password,
            onValueChange = {
                password = it
                if (passwordError != null) passwordError = null
            },
            placeholder = "Contraseña",
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodyLarge,
            visualTransformation = PasswordVisualTransformation(),
            isError = passwordError != null,
            errorMessage = passwordError
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Confirmar contraseña
        CustomInput(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                if (confirmPasswordError != null) confirmPasswordError = null
            },
            placeholder = "Confirmar contraseña",
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodyLarge,
            visualTransformation = PasswordVisualTransformation(),
            isError = confirmPasswordError != null,
            errorMessage = confirmPasswordError
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (validate()) {
                    onRegisterClick(fullName, email, phone, password)
                }
            },
            modifier = Modifier.fillMaxWidth()
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