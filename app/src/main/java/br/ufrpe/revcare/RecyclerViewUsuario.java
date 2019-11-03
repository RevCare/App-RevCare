package br.ufrpe.revcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import br.ufrpe.revcare.R;

public class RecyclerViewUsuario extends AppCompatActivity {
    private ArrayList<String> mNomes = new ArrayList<>();
    private ArrayList<String> mDescricao = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_usuario);
        getSupportActionBar().hide();
        initServicos();

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
