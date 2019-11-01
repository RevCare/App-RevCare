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

        mDescricao.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        mNomes.add("Havasu Falls");

        mDescricao.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mNomes.add("Trondheim");

        mDescricao.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mNomes.add("Portugal");

        mDescricao.add("https://i.redd.it/j6myfqglup501.jpg");
        mNomes.add("Rocky Mountain National Park");


        mDescricao.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mNomes.add("Mahahual");

        mDescricao.add("https://i.redd.it/k98uzl68eh501.jpg");
        mNomes.add("Frozen Lake");


        mDescricao.add("https://i.redd.it/glin0nwndo501.jpg");
        mNomes.add("White Sands Desert");

        mDescricao.add("https://i.redd.it/obx4zydshg601.jpg");
        mNomes.add("Austrailia");

        mDescricao.add("https://i.imgur.com/ZcLLrkY.jpg");
        mNomes.add("Washington");

        initRecyclerView();
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.usuariorecylcer);
        UsuarioAdapter adapter = new UsuarioAdapter(this, mNomes, mDescricao);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
