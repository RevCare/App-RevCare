package br.ufrpe.revcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import br.ufrpe.revcare.usuario.dominio.Usuario;

public class RecyclerViewUsuario extends AppCompatActivity {

    private ArrayList<String> mNomes = new ArrayList<>();
    private ArrayList<String> mDescricao = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_usuario);
        initImageBitmaps();
    }
    private void initImageBitmaps(){

        mDescricao.add("Descrição de teste.");
        mNomes.add("Teste");

        mDescricao.add("Descrição de teste.");
        mNomes.add("Teste");

        mDescricao.add("Descrição de teste.");
        mNomes.add("Teste");

        mDescricao.add("Descrição de teste.");
        mNomes.add("Teste");


        mDescricao.add("Descrição de teste.");
        mNomes.add("Teste");

        mDescricao.add("Descrição de teste.");
        mNomes.add("Teste");


        mDescricao.add("Descrição de teste.");
        mNomes.add("Teste");

        mDescricao.add("Descrição de teste.");
        mNomes.add("Teste");

        mDescricao.add("Descrição de teste.");
        mNomes.add("Teste");

        initRecyclerView();
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.usuariorecylcer);
        UsuarioAdapter adapter = new UsuarioAdapter(this, mNomes, mDescricao);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
