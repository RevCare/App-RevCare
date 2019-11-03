package br.ufrpe.revcare.servico.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import br.ufrpe.revcare.R;
import br.ufrpe.revcare.infra.gui.Validacao;
import br.ufrpe.revcare.servico.dominio.Servico;
import br.ufrpe.revcare.servico.negocio.ServicoService;
import br.ufrpe.revcare.usuario.gui.HomeUsuario;

public class CadastroServico extends AppCompatActivity implements  DatePickerDialog.OnDateSetListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_servico);
        getSupportActionBar().hide();

        Button btnAgendar = findViewById(R.id.btnAgendar);
        btnAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    cadastrar();
                    Toast.makeText(CadastroServico.this, "Serviço agendado com sucesso", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CadastroServico.this, HomeUsuario.class));


                }
                catch (Exception e){
                    Toast.makeText(CadastroServico.this, "Não foi possivel cadastrar o serviço", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.dataTextField).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
                validarData();
                if (validarData()){

                }
            }
        });

    }



    private void cadastrar() {
        EditText nomeServicoTxt = findViewById(R.id.nomeServicoTextField);
        EditText dataServicoTxt = findViewById(R.id.dataTextField);
        EditText horarioInicialTxt = findViewById(R.id.horarioInicialTextField);
        EditText horarioFinalTxt = findViewById(R.id.horarioFinalTextField);
        EditText descricaoTxt = findViewById(R.id.descricaoTextField);


        Validacao campos = new Validacao();
        if(campos.isValido(nomeServicoTxt, dataServicoTxt, horarioInicialTxt, horarioFinalTxt, descricaoTxt)){
            Servico servico = criarServico();
            ServicoService service = new ServicoService(getBaseContext());
            service.cadastrar(servico);

        }

    }

    private Servico criarServico() {

        EditText nomeServicoTxt = findViewById(R.id.nomeServicoTextField);
        EditText dataServicoTxt = findViewById(R.id.dataTextField);
        EditText horarioInicialTxt = findViewById(R.id.horarioInicialTextField);
        EditText horarioFinalTxt = findViewById(R.id.horarioFinalTextField);
        EditText descricaoTxt = findViewById(R.id.descricaoTextField);

        Servico result = new Servico();
        result.setNome(nomeServicoTxt.getText().toString().trim());
        result.setData(dataServicoTxt.getText().toString().trim());
        result.setHorarioInicial(horarioInicialTxt.getText().toString().trim());
        result.setHorarioFinal(horarioFinalTxt.getText().toString().trim());
        result.setDescricao(descricaoTxt.getText().toString().trim());

        return result;
    }

    private void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,this,
                Calendar.getInstance().get(Calendar.DATE),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.YEAR)
                );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = dayOfMonth + "/" + month + "/" + year;
        EditText dataServicoTxt = findViewById(R.id.dataTextField);
        dataServicoTxt.setText(date);

    }

    private boolean validarData() {
        EditText dataServicoTxt = findViewById(R.id.dataTextField);
        String data = dataServicoTxt.getText().toString();
        Calendar now = Calendar.getInstance();
        Calendar date = Calendar.getInstance();

        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        if (data.isEmpty()) {
            Toast.makeText(CadastroServico.this, "Data inválida", Toast.LENGTH_SHORT).show();
            return false;
        } else if (date.before(now)) {
            Toast.makeText(CadastroServico.this, "Data inválida", Toast.LENGTH_SHORT).show();

            return false;
        }
        return true;
    }
}
