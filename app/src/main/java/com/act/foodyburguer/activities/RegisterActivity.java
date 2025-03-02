package com.act.foodyburguer.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.act.foodyburguer.R;
import com.act.foodyburguer.model.MangerDB;

public class RegisterActivity extends AppCompatActivity {
    private Context context;
    private EditText etCorreo, etContraseña;
    private Button btnRegistrar;
    private MangerDB basedatosManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        begin();
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etCorreo.getText().toString().trim();
                String password = etContraseña.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    if (basedatosManger.registerUser(email, password)) {
                        Toast.makeText(RegisterActivity.this, "Usuario registrado", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Error al registrar", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private void begin(){
        this.context = this;
        this.btnRegistrar = findViewById(R.id.btnRegistrar);
        this.etCorreo = findViewById(R.id.etCorreo);
        this.etContraseña = findViewById(R.id.etContraseña);
        basedatosManger = new MangerDB(this);
    }
}