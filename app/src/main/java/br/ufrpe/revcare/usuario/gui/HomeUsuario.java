package br.ufrpe.revcare.usuario.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.ufrpe.revcare.R;

public class HomeUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_usuario);
        getSupportActionBar().hide();

        Button btnMais = findViewById(R.id.botaoMais);
        btnMais.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


            }
        });




    }
}