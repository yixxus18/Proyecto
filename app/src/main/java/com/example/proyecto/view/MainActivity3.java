package com.example.proyecto.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyecto.R;
import com.example.proyecto.view_model.LoginViewModel;
import com.example.proyecto.view_model.RegisterViewModel;

public class MainActivity3 extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private Button buttonLogin;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        observeLoginStatus();

        buttonLogin.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            if (!email.isEmpty() && !password.isEmpty()) {
                loginViewModel.loginUser(email, password, MainActivity3.this);
            } else {
                // Manejar el caso donde el correo o contraseña están vacíos
                Toast.makeText(MainActivity3.this, "Ingrese correo y contraseña", Toast.LENGTH_SHORT).show();
            }
        });

        // No es necesario llamar a loginUser con los valores guardados aquí,
        // ya que ahora se llama en el listener del botón de inicio de sesión.
    }

    private void observeLoginStatus() {
        loginViewModel.getLoginStatus().observe(this, loginSuccessful -> {
            if (loginSuccessful) {
                Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(MainActivity3.this, "Error al iniciar sesión, inténtalo de nuevo", Toast.LENGTH_LONG).show();
            }
        });
    }
}





