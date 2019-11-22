package br.ufrpe.revcare.usuario.gui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.revcare.R;
import br.ufrpe.revcare.avaliacao.negocio.AvaliacaoServices;
import br.ufrpe.revcare.profissional.dominio.Profissional;
import br.ufrpe.revcare.profissional.persistencia.ProfissionalDAO;

public class RecyclerViewUsuario extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private ArrayList<String> mNomes = new ArrayList<>();
    private ArrayList<String> mCidade = new ArrayList<>();
    private ArrayList<String> mnota = new ArrayList<>();
    private List<Profissional> profissionaisRecomendados = new ArrayList<>();
    private List<Profissional> profissionais = new ArrayList<>();
    private ArrayList<String> mTelefone = new ArrayList<>();
    private ArrayList<String> mDescricao = new ArrayList<>();
    private ArrayList<String> mEmail = new ArrayList<>();
    private ArrayList<Integer> mLikes = new ArrayList<Integer>();
    private ArrayList<Integer> mDeslikes = new ArrayList<Integer>();
    private ArrayList<String> mEstado = new ArrayList<>();
    private ArrayList<Bitmap> mFotos = new ArrayList<>();






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_usuario);
        getSupportActionBar().hide();
        Button btnAllPro = findViewById(R.id.buttonAllPro);
        Button btnRecPro = findViewById(R.id.buttonRecommendedPro);
        initProfissionais();

        btnRecPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
            }
        });
        btnAllPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfissionalDAO dao = new ProfissionalDAO(getApplicationContext());
                zerarArrays();
                profissionais = dao.getAllProfissional();
                adicionaNoArray(dao,profissionais);
                initRecyclerView();
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();


    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private void initProfissionais() {
        ProfissionalDAO dao = new ProfissionalDAO(getApplicationContext());
        AvaliacaoServices services = new AvaliacaoServices(getApplicationContext());
        profissionaisRecomendados = services.getRecomendacao(getApplicationContext());
        if (profissionaisRecomendados.size() != 0) {
            adicionaNoArray(dao, profissionaisRecomendados);

        }else{
            Toast.makeText(getApplicationContext(), "Você não tem recomendações ainda.", Toast.LENGTH_SHORT).show();
            profissionais = dao.getAllProfissional();
            adicionaNoArray(dao, profissionais);
        }
        initRecyclerView();

    }

    private void adicionaNoArray(ProfissionalDAO dao, List<Profissional> profissionais) {
        for (int i = 0; i < profissionais.size(); i++) {
            mNomes.add(profissionais.get(i).getNome());
            mCidade.add(profissionais.get(i).getCidade());
            mTelefone.add(profissionais.get(i).getTelefone());
            mEmail.add(profissionais.get(i).getEmail());
            mDescricao.add(profissionais.get(i).getDescricao());
            mLikes.add(dao.contarLikes(profissionais.get(i).getId()));
            mDeslikes.add(dao.contarDeslikes(profissionais.get(i).getId()));
            mEstado.add(profissionais.get(i).getEstado());
            byte[] imagemEmBits = profissionais.get(i).getFoto();
            if (profissionais.get(i).getFoto() != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(imagemEmBits, 0, imagemEmBits.length);
                mFotos.add(bitmap);
            } else {
                mFotos.add(null);
            }

        }
    }
    private void zerarArrays(){
        mNomes = new ArrayList<>();
        mCidade = new ArrayList<>();
        mnota = new ArrayList<>();
        profissionaisRecomendados = new ArrayList<>();
        profissionais = new ArrayList<>();
        mTelefone = new ArrayList<>();
        mDescricao = new ArrayList<>();
        mEmail = new ArrayList<>();
        mLikes = new ArrayList<Integer>();
        mDeslikes = new ArrayList<Integer>();
        mEstado = new ArrayList<>();
        mFotos = new ArrayList<>();

    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.usuariorecylcer);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNomes, mCidade, mTelefone, mEmail, mDescricao, mLikes, mDeslikes, mEstado,mFotos);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

    }


}