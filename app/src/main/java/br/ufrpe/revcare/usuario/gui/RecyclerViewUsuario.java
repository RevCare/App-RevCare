package br.ufrpe.revcare.usuario.gui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


import java.util.ArrayList;
import java.util.List;

import br.ufrpe.revcare.R;
import br.ufrpe.revcare.profissional.dominio.Profissional;
import br.ufrpe.revcare.profissional.persistencia.ProfissionalDAO;

public class RecyclerViewUsuario extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private ArrayList<String> mNomes = new ArrayList<>();
    private ArrayList<String> mlocalizacao = new ArrayList<>();
    private ArrayList<String> mnota = new ArrayList<>();
    private List<Profissional> profissionais = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_usuario);
        getSupportActionBar().hide();
        initServicos();
        Spinner spinner = findViewById(R.id.spinner);
        Spinner spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.estados, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.cidadesPE, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter2);
        spinner.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private void initServicos(){
        ProfissionalDAO dao = new ProfissionalDAO(getApplicationContext());
        profissionais = dao.getAllProfissionalById();
        for (int i = 0; i < profissionais.size(); i++) {
             mNomes.add(profissionais.get(i).getNome());
             mlocalizacao.add(profissionais.get(i).getDescricao());
        }
        initRecyclerView();

    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.usuariorecylcer);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNomes, mlocalizacao);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
