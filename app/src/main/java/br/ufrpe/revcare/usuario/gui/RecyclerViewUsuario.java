package br.ufrpe.revcare.usuario.gui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;


import java.util.ArrayList;
import java.util.List;

import br.ufrpe.revcare.R;
import br.ufrpe.revcare.profissional.dominio.Profissional;
import br.ufrpe.revcare.profissional.persistencia.ProfissionalDAO;

public class RecyclerViewUsuario extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private ArrayList<String> mNomes = new ArrayList<>();
    private ArrayList<String> mCidade = new ArrayList<>();
    private ArrayList<String> mnota = new ArrayList<>();
    private List<Profissional> profissionais = null;
    private ArrayList<String> mTelefone = new ArrayList<>();
    private ArrayList<String> mDescricao = new ArrayList<>();
    private ArrayList<String> mEmail = new ArrayList<>();
    private ArrayList<Integer> mLikes = new ArrayList<Integer>();
    private ArrayList<Integer> mDeslikes = new ArrayList<Integer>();
    private ArrayList<String> mEstado = new ArrayList<>();






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_usuario);
        getSupportActionBar().hide();
        Spinner spinner = findViewById(R.id.spinner);
        Spinner spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.estados, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.cidadesPE, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);
        spinner.setOnItemSelectedListener(this);
        ImageButton atualizar = findViewById(R.id.btnAtt);
        atualizar.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View v) {
                finish();
                startActivity(getIntent());
             }
         }
        );
        initServicos();
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
             mCidade.add(profissionais.get(i).getCidade());
             mTelefone.add(profissionais.get(i).getTelefone());
             mEmail.add(profissionais.get(i).getEmail());
             mDescricao.add(profissionais.get(i).getDescricao());
             mLikes.add(dao.contarLikes(profissionais.get(i).getId()));
             mDeslikes.add(dao.contarDeslikes(profissionais.get(i).getId()));
             mEstado.add(profissionais.get(i).getEstado());

        }
        initRecyclerView();

    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.usuariorecylcer);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNomes, mCidade, mTelefone, mEmail, mDescricao, mLikes, mDeslikes, mEstado);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}
