package br.ufrpe.revcare.infra.gui;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.ufrpe.revcare.R;
import br.ufrpe.revcare.servico.dominio.Servico;
import br.ufrpe.revcare.servico.negocio.ServicoService;
import br.ufrpe.revcare.servico.persistencia.ServicoDAO;

import androidx.annotation.Nullable;


public class ServicoActivity {


    public class ServicoActivity extends AppCompatActivity {

        public static final String TITULO_APPBAR = "Servicos Cadastrados";



        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_servico);
            setTitle(TITULO_APPBAR);


        }

    }

}
