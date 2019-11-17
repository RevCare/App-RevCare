package br.ufrpe.revcare.profissional.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import br.ufrpe.revcare.R;
import br.ufrpe.revcare.infra.gui.MainActivity;
import br.ufrpe.revcare.infra.gui.Validacao;
import br.ufrpe.revcare.profissional.dominio.Profissional;
import br.ufrpe.revcare.profissional.negocio.ProfissionalServices;
import br.ufrpe.revcare.profissional.persistencia.ProfissionalDAO;

public class CadastroProfissional extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText nNome;
    private EditText nDataNascimento;
    private EditText nDescricao;
    private EditText nCpf;
    private EditText nEmail;
    private EditText nTelefone;
    private EditText nCertificado;
    private EditText nSenha;
    private EditText nConfirmarSenha;
    private Spinner nCidade;
    private Spinner nEstado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cadastro_profissional);
        Button btnFinalizarCadastro = findViewById(R.id.botaoFinalizarCadastro);
        btnFinalizarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //finish();
                    cadastrar();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });
        Spinner spinner = findViewById(R.id.spinnerEstado);
        Spinner spinner2 = findViewById(R.id.spinnerCidade);
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
    private void cadastrar() throws Exception {
        if (validarCampos() && confirmaEmail()) {
            Profissional profissional = criarProfissional();
            ProfissionalServices services = new ProfissionalServices(getBaseContext());
            services.cadastrar(profissional);
            Toast.makeText(getApplicationContext(),"Profissional cadastrado com sucesso.", Toast.LENGTH_LONG).show();
            startActivity(new Intent(CadastroProfissional.this, MainActivity.class));
        }
    }

    private boolean validarCampos() {
        nNome = findViewById(R.id.nomeTextField);
        nDataNascimento = findViewById(R.id.dataNascimentoTextField);
        nCpf = findViewById(R.id.cpfTextField);
        nDescricao = findViewById(R.id.descricaoTextField);
        nTelefone = findViewById(R.id.telefoneTextField);
        nEmail = findViewById(R.id.emailTextField);
        nSenha = findViewById(R.id.caixaTxtSenhaLogin);
        nConfirmarSenha = findViewById(R.id.caixaConfirmaSenha);

        Validacao valido = new Validacao();
        boolean emailValido =
                valido.validarEmail(nEmail);
        boolean camposValidos =
                valido.isValido(nNome, nDataNascimento, nDescricao, nTelefone, nEmail, nSenha, nConfirmarSenha);
        boolean senhasValidas =
                valido.confirmarSenha(getApplicationContext(),nSenha.getText().toString(),nConfirmarSenha.getText().toString()) &&
                        valido.senhaCorreta(nSenha);
        boolean cpfValido=
                valido.isCPF(nCpf) && confirmaCpf();
        return emailValido && camposValidos && senhasValidas && cpfValido;
    }
    private  boolean confirmaEmail(){
        Profissional result = null;
        ProfissionalDAO dao = new ProfissionalDAO(this);
        EditText nEmail = findViewById(R.id.emailTextField);
        String email = nEmail.getText().toString().trim();
        result = dao.consultarEmail(email);
        if (result != null){
            nEmail.requestFocus();
            nEmail.setError("Preencha novamente o campo.");
            Toast.makeText(getApplicationContext(), "Não foi possível realizar o cadastro.", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
    private  boolean confirmaCpf(){
        Profissional result = null;
        ProfissionalDAO dao = new ProfissionalDAO(this);
        EditText nCpf = findViewById(R.id.cpfTextField);
        String cpf = nCpf.getText().toString().trim();
        result = dao.consultarCpf(cpf);
        if (result != null){
            nCpf.requestFocus();
            nCpf.setError("Preencha novamente o campo.");
            Toast.makeText(getApplicationContext(), "Não foi possível realizar o cadastro.", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private Profissional criarProfissional() {
        nNome = findViewById(R.id.nomeTextField);
        nDataNascimento = findViewById(R.id.dataNascimentoTextField);
        nCpf = findViewById(R.id.cpfTextField);
        nDescricao = findViewById(R.id.descricaoTextField);
        nTelefone = findViewById(R.id.telefoneTextField);
        nEmail = findViewById(R.id.emailTextField);
        nCertificado = findViewById(R.id.certificadoTextField);
        nSenha = findViewById(R.id.caixaTxtSenhaLogin);
        nConfirmarSenha = findViewById(R.id.caixaConfirmaSenha);
        nEstado = findViewById(R.id.spinnerEstado);
        nCidade = findViewById(R.id.spinnerCidade);



        Profissional result = new Profissional();
        result.setNome(nNome.getText().toString().trim());
        result.setCpf(nCpf.getText().toString().trim());
        result.setDescricao(nDescricao.getText().toString().trim());
        result.setTelefone(nTelefone.getText().toString().trim());
        result.setEmail(nEmail.getText().toString().trim());
        result.setCertificado(nCertificado.getText().toString().trim());
        result.setDataNascimento(nDataNascimento.getText().toString().trim());
        result.setSenha(nSenha.getText().toString().trim());
        result.setEstado(nEstado.getSelectedItem().toString().trim());
        result.setCidade(nCidade.getSelectedItem().toString().trim());
        return result;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
