package com.example.proyecto.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;

import com.example.proyecto.R;
import com.example.proyecto.view_model.RegisterViewModel;

public class MainActivity2 extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword, editTextNombre;
    private Button buttonRegister;
    private RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextNombre = findViewById(R.id.editTextNombre);
        buttonRegister = findViewById(R.id.buttonRegister);

        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        buttonRegister.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            String nombre = editTextNombre.getText().toString().trim();

            registerViewModel.registerUser(email, password, nombre);
        });

        observeRegistrationStatus();
    }

    private void observeRegistrationStatus() {
        registerViewModel.getRegistrationStatus().observe(this, registrationSuccessful -> {
            if (registrationSuccessful) {
                Toast.makeText(MainActivity2.this, "Usuario registrado correctamente. Te enviamos un correo para que verifiques tu cuenta.", Toast.LENGTH_LONG).show();
                // Limpiar los EditText
                editTextEmail.setText("");
                editTextPassword.setText("");
                editTextNombre.setText("");
            } else {
                Toast.makeText(MainActivity2.this, "Error al registrar usuario", Toast.LENGTH_LONG).show();
            }
        });

        buttonRegister.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            String nombre = editTextNombre.getText().toString().trim();

            // Validar el formato del correo electrónico
            if (!isValidEmail(email)) {
                Toast.makeText(MainActivity2.this, "Por favor, introduce un correo electrónico válido", Toast.LENGTH_LONG).show();
                return; // Salir del método si el correo electrónico no es válido
            }

            registerViewModel.registerUser(email, password, nombre);
        });
    }

    // Método para validar el formato del correo electrónico
    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


}
