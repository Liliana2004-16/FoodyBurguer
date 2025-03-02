package com.act.foodyburguer.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.act.foodyburguer.R;
import com.act.foodyburguer.model.MangerDB;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private Button btnIngresar;
    private EditText etCorreo, etContraseña;
    private TextView tvRegistrarse;
    private MangerDB basedatosManger;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        begin();

        tvRegistrarse.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etCorreo.getText().toString().trim();
                String password = etContraseña.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    if (basedatosManger.validateLogin(email, password)) {
                        Toast.makeText(MainActivity.this, "Login exitoso", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(MainActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private void begin(){
        this.context = this;
        this.btnIngresar = findViewById(R.id.btnIngresar);
        this.etCorreo = findViewById(R.id.etCorreo);
        this.etContraseña = findViewById(R.id.etContraseña);
        this.btnIngresar = findViewById(R.id.btnIngresar);
        this.tvRegistrarse = findViewById(R.id.tvRegistrarse);
        this.basedatosManger = new MangerDB(this);
    }
}
