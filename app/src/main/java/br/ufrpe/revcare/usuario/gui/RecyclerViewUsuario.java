package br.ufrpe.revcare.usuario.gui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    private ArrayList<String> mLocalizacao = new ArrayList<>();
    private ArrayList<String> mnota = new ArrayList<>();
    private List<Profissional> profissionais = null;
    private ArrayList<String> mTelefone = new ArrayList<>();
    private ArrayList<String> mDescricao = new ArrayList<>();
    private ArrayList<String> mEmail = new ArrayList<>();
    private ArrayList<Integer> mLikes = new ArrayList<Integer>();




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
        profissionais = dao.getAllProfissional();

        for (int i = 0; i < profissionais.size(); i++) {
             mNomes.add(profissionais.get(i).getNome());
             mLocalizacao.add(profissionais.get(i).getCidade());
             mTelefone.add(profissionais.get(i).getTelefone());
             mEmail.add(profissionais.get(i).getEmail());
             mDescricao.add(profissionais.get(i).getDescricao());
             mLikes.add(dao.contarDelikes(profissionais.get(i).getId()));

        }
        initRecyclerView();

    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.usuariorecylcer);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNomes, mLocalizacao, mTelefone, mEmail, mDescricao, mLikes);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}
