package br.ufrpe.revcare.profissional.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import br.ufrpe.revcare.R;
import br.ufrpe.revcare.profissional.dominio.Profissional;

public class HomeProfissional extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_profissional);
        getSupportActionBar().hide();

    }
    public void PreencheTela(Profissional profissional){
        TextView nome = findViewById(R.id.nomeprofissional);

    }
}
