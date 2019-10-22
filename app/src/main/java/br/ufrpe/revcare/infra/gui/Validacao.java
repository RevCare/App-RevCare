package br.ufrpe.revcare.infra.gui;

import android.widget.EditText;

public class Validacao {

    public boolean isCampoValido(EditText editText) {
        boolean result = true;
        if (editText.getText().toString().trim().length()==0) {
            editText.requestFocus();
            editText.setError("Preencha o Campo.");
            result = false;
        }
        return result;
    }

    public boolean isValido(EditText... editTexts) {
        boolean result = true;
        for (EditText editText : editTexts) {
            if (!isCampoValido(editText)) {
                result = false;
                break;
            }
        }
        return result;
    }



    public boolean validacao(EditText nNome, EditText nDataNascimento, EditText nCpf, EditText nEndereco,
                             EditText nTelefone, EditText nEmail, EditText nSenha, EditText nConfirmarSenha) {


        boolean teveCampoVazio = false;
        String txtNome = nNome.getText().toString().trim();
        String txtDataNascimento = nDataNascimento.getText().toString().trim();
        String txtCpf = nCpf.getText().toString().trim();
        String txtEndereco = nEndereco.getText().toString().trim();
        String txxTelefone = nTelefone.getText().toString().trim();
        String txtEmail = nEmail.getText().toString().trim();
        String txtSenha = nSenha.getText().toString().trim();
        String txtConfirmarSenha = nConfirmarSenha.getText().toString().trim();


        if (txtNome.equals("")) {
            nNome.requestFocus();
            nNome.setError("Preencha o Campo.");
            teveCampoVazio = true;

        }

        if (txtDataNascimento.equals("")) {
            nDataNascimento.requestFocus();
            nDataNascimento.setError("Preencha o Campo.");
            teveCampoVazio = true;

        }
        if (txtEndereco.equals("")) {
            nEndereco.requestFocus();
            nEndereco.setError("Preencha o Campo.");
            teveCampoVazio = true;

        }
        if ((txtCpf.equals("00000000000") ||
                txtCpf.equals("11111111111") ||
                txtCpf.equals("22222222222") || txtCpf.equals("33333333333") ||
                txtCpf.equals("44444444444") || txtCpf.equals("55555555555") ||
                txtCpf.equals("66666666666") || txtCpf.equals("77777777777") ||
                txtCpf.equals("88888888888") || txtCpf.equals("99999999999") ||
                (txtCpf.length() != 11))) {
            nCpf.requestFocus();
            teveCampoVazio = true;
            nCpf.setError("CPF inválido.");
        }
        if (txxTelefone.equals("")) {
            nTelefone.requestFocus();
            nTelefone.setError("Preencha o Campo.");
            teveCampoVazio = true;

        }
        if (txtEmail.equals("")) {
            nEmail.requestFocus();
            nEmail.setError("Preencha o Campo.");
            teveCampoVazio = true;

        }
        if (txtSenha.equals("")) {
            nSenha.requestFocus();
            nSenha.setError("Preencha o Campo.");
            teveCampoVazio = true;

        }
        if (txtConfirmarSenha.equals("")) {
            nConfirmarSenha.requestFocus();
            nConfirmarSenha.setError("Preencha o Campo.");
            teveCampoVazio = true;

        }
        if (!teveCampoVazio) {

            if (!(txtSenha.equals(txtConfirmarSenha))) {
                nSenha.requestFocus();
                nSenha.setError("As senhas devem ser iguais.");
                nConfirmarSenha.setError("As senhas devem ser iguais.");

            } else {
                return true;
            }}

            if (!teveCampoVazio) {
                return true;
            }

            return false;



    }

}
