package br.ufrpe.revcare.profissional.gui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.ufrpe.revcare.R;
import br.ufrpe.revcare.infra.gui.MainActivity;
import br.ufrpe.revcare.profissional.dominio.Profissional;
import br.ufrpe.revcare.profissional.negocio.ProfissionalServices;
import br.ufrpe.revcare.profissional.negocio.SessaoProfissional;
import br.ufrpe.revcare.profissional.persistencia.ProfissionalDAO;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class HomeProfissional extends AppCompatActivity {
    private static final int PERMISSION_REQUEST = 0;
    private static final int REQUEST_GALLERY = 2;
    private static final int REQUEST_CAPTURE = 1;
    private static final String[] PERMISSIONS = {android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.INTERNET};

    private Profissional profissional = SessaoProfissional.getProfissional();
    private ImageView mImagemCliente;
    private Integer likes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_profissional);
        getSupportActionBar().hide();
        preencheTela(SessaoProfissional.getProfissional());
        ProfissionalDAO dao = new ProfissionalDAO(getApplicationContext());
        likes = dao.contarLikes(profissional.getId());
        Toast.makeText(getApplicationContext(),likes.toString() + " Like(s)", Toast.LENGTH_LONG).show();
        ImageButton mudarFoto = findViewById(R.id.imagemProfissional);
        Button buttonSair = findViewById(R.id.buttonSairProfissional);
        ActivityCompat.requestPermissions(this, PERMISSIONS, 112);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_GALLERY);
            }
        }
        mudarFoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getPermissionsGaleria();
                takePhoto();
            }
        });

        buttonSair.setOnClickListener(new View.OnClickListener() {

          @Override
          public void onClick(View v) {
              SessaoProfissional.reset();
              startActivity(new Intent(HomeProfissional.this, MainActivity.class));
          }
      });
        Button btnAtualizarPerfil = findViewById(R.id.botaoAtualizarPerfilProfissional);
        btnAtualizarPerfil.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                atualizarPerfil();

            }
            });
    }
    public void preencheTela(Profissional profissional){
        TextView nome = findViewById(R.id.nomeprofissional);
        TextView cpf = findViewById(R.id.cpfProfissionalHome);
        TextView telefone = findViewById(R.id.telefoneProfissionalHome);
        TextView descricao = findViewById(R.id.decricaoprofissional);
        TextView email = findViewById(R.id.emailProfissionalHome);

        nome.setText(profissional.getNome());
        cpf.setText(profissional.getCpf());
        telefone.setText(profissional.getTelefone());
        email.setText(profissional.getEmail());
        descricao.setHint(profissional.getDescricao());
    }
    public void atualizarPerfil(){
        Profissional profissional = SessaoProfissional.getProfissional();
        EditText descricao = findViewById(R.id.decricaoprofissional);
        if (descricao.getText().toString().trim().equals("")){
            Toast.makeText(getApplicationContext(), "A descrição não foi atualizada.", Toast.LENGTH_LONG).show();
        }else{
            profissional.setDescricao(descricao.getText().toString().trim());
            ProfissionalDAO dao = new ProfissionalDAO(getApplicationContext());
            dao.updateDescricaoProfissional(profissional);
            Toast.makeText(getApplicationContext(), "A descrição foi atualizada.", Toast.LENGTH_LONG).show();
            finish();
            startActivity(getIntent());

        }
    }

    private void takePhoto() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            photoFile = createPhotoFile();
            if (photoFile != null) {
                Uri photoUri = FileProvider.getUriForFile(getApplicationContext(), "br.ufrpe.revcare.provider", photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(intent, REQUEST_GALLERY);
            }
        }
    }

    private File createPhotoFile() {

        String name = new SimpleDateFormat("ddMMyyy_HHmmss").format(new Date());
        File storageDir = getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = null;
        try {
            image = File.createTempFile(name, ".jpg", storageDir);
        } catch (IOException e) {
            Log.d("My Tag", "Error " + e.toString());
        }

        return image;

    }

    private void getPermissionsGaleria() {
        int permissionCheckRead = ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permissionCheckRead != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST);
            }
        } else {
            abrirGaleriaIntent();
        }
    }

    private void abrirGaleriaIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Selecione a foto"), REQUEST_GALLERY);
    }

    private void salvarFoto() {
        byte[] foto = conveterImageViewToByte();
        profissional.setFoto(foto);
        ProfissionalServices.alteraFotoProfissional(profissional);
    }

    private byte[] conveterImageViewToByte() {
        Bitmap bitmap = ((BitmapDrawable) mImagemCliente.getDrawable()).getBitmap();
        ByteArrayOutputStream saida = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, saida);
        return saida.toByteArray();
    }

    private Bitmap getThumbnailFromBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int max = Math.max(width, height);
        if (max > 512) {
            int thumbWidth = Math.round((512f / max) * width);
            int thumbHeight = Math.round((512f / max) * height);
            Bitmap thumbnail = ThumbnailUtils.extractThumbnail(bitmap, thumbWidth, thumbHeight);
            bitmap.recycle();
            return thumbnail;
        } else {
            return bitmap;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_GALLERY:
                Toast.makeText(this, "e ai", Toast.LENGTH_SHORT).show();
                if (resultCode == RESULT_OK) {
                    try {
                        Uri img = data.getData();
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(img));
                        bitmap = getThumbnailFromBitmap(bitmap);
                        mImagemCliente.setImageBitmap(bitmap);
                        salvarFoto();
                        Toast.makeText(this, "Foto Alterada Com sucesso", Toast.LENGTH_SHORT).show();
                        break;
                    } catch (FileNotFoundException e) {

                        Toast.makeText(this, "Foto não encontrada", Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            case REQUEST_CAPTURE:
                if (resultCode == RESULT_OK && requestCode == 1) {
                    Uri img = data.getData();
                    Bitmap bitmap = BitmapFactory.decodeFile(String.valueOf(img));
                    mImagemCliente.setImageBitmap(bitmap);
                }
                break;
            default:
                break;
        }

    }

}
