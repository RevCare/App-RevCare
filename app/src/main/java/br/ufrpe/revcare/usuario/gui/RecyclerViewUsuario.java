package br.ufrpe.revcare.usuario.gui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import br.ufrpe.revcare.R;
import br.ufrpe.revcare.servico.gui.CadastroServicoActivity;

public class RecyclerViewUsuario extends AppCompatActivity {
    private ArrayList<String> mNomes = new ArrayList<>();
    private ArrayList<String> mDescricao = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_usuario);
        getSupportActionBar().hide();
        initServicos();
        Button btnAdicionar = findViewById(R.id.botaoMais);
        btnAdicionar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    startActivity(new Intent(RecyclerViewUsuario.this, CadastroServicoActivity.class));
            }

        });

    }

    private void initServicos(){
        mNomes.add("Levar ao médico");
        mDescricao.add("Preciso de um acompanhante para me levar ao médico no bairro de Casa amarela ás 15:00");

        mNomes.add("Passar a tarde");
        mDescricao.add("Preciso de um cuidador para ficar com a minha vó de 13:00 até as 18:00");
        mNomes.add("Passar a tarde");
        mDescricao.add("Preciso de um cuidador para ficar com a minha vó de 13:00 até as 18:00");
        mNomes.add("Passar a tarde");
        mDescricao.add("Preciso de um cuidador para ficar com a minha vó de 13:00 até as 18:00");

        initRecyclerView();

    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.usuariorecylcer);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNomes, mDescricao);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
