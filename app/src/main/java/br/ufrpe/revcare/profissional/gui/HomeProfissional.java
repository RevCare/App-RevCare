package br.ufrpe.revcare.profissional.gui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
import java.util.List;

import br.ufrpe.revcare.R;
import br.ufrpe.revcare.infra.gui.MainActivity;
import br.ufrpe.revcare.profissional.dominio.Profissional;
import br.ufrpe.revcare.profissional.negocio.ProfissionalServices;
import br.ufrpe.revcare.profissional.negocio.SessaoProfissional;
import br.ufrpe.revcare.profissional.persistencia.ProfissionalDAO;
import br.ufrpe.revcare.usuario.gui.RecyclerViewAdapter;


import static android.os.Environment.getExternalStoragePublicDirectory;
// Funçoes de capturar foto e etc tiradas do aplicativo Trainee
public class HomeProfissional extends AppCompatActivity {
    private TextView nome;
    private TextView cpf;
    private TextView telefone;
    private TextView descricao;
    private TextView email;
    private ImageView mImagemCliente;
    private static String mCurrentPhotoPath;
    private static final int PERMISSION_REQUEST = 0;
    private static final int REQUEST_TAKE_PHOTO = 1;
    private static final int REQUEST_GALLERY = 2;
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    private Profissional profissional = SessaoProfissional.getProfissional();
    private Integer likes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_profissional);
        getSupportActionBar().hide();
        preencheTela(SessaoProfissional.getProfissional());
        ProfissionalServices services = new ProfissionalServices(getApplicationContext());
        likes = services.contarLikes(profissional.getId());
        Button mudarFoto = findViewById(R.id.imagemProfissional);
        Button buttonSair = findViewById(R.id.buttonSairProfissional);
        buttonSair.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SessaoProfissional.reset();
                finish();
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

        mudarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] opcoes = {"Tirar foto", "Escolher foto"};
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeProfissional.this);
                builder.setTitle("Alterar Foto");
                builder.setItems(opcoes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if ("Tirar foto".equals(opcoes[which])) {
                            getPermissionsCamera();
                        } else if ("Escolher foto".equals(opcoes[which])) {
                            getPermissionsGaleria();
                        }
                    }
                });
                builder.show();
            }
        });
    }

    public void preencheTela(Profissional profissional) {
        nome = findViewById(R.id.nomeprofissional);
        cpf = findViewById(R.id.cpfProfissionalHome);
        telefone = findViewById(R.id.telefoneProfissionalHome);
        descricao = findViewById(R.id.decricaoprofissional);
        email = findViewById(R.id.emailProfissionalHome);
        mImagemCliente = findViewById(R.id.image4);

        nome.setText(profissional.getNome());
        cpf.setText(profissional.getCpf());
        telefone.setText(profissional.getTelefone());
        email.setText(profissional.getEmail());
        descricao.setHint(profissional.getDescricao());
        byte[] imagemEmBits = profissional.getFoto();
        if (profissional.getFoto() != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imagemEmBits, 0, imagemEmBits.length);
            mImagemCliente.setImageBitmap(bitmap);
        }


    }

    public void atualizarPerfil() {
        Profissional profissional = SessaoProfissional.getProfissional();
        EditText descricao = findViewById(R.id.decricaoprofissional);
        if (descricao.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "A descrição não foi atualizada.", Toast.LENGTH_LONG).show();
        } else {
            profissional.setDescricao(descricao.getText().toString().trim());
            ProfissionalServices services = new ProfissionalServices(getApplicationContext());
            services.updateDescricaoProfissional(profissional);
            Toast.makeText(getApplicationContext(), "A descrição foi atualizada.", Toast.LENGTH_LONG).show();
            finish();
            startActivity(getIntent());

        }
    }


    private void getPermissionsCamera() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        } else
            abrirCameraIntent();
    }

    private void getPermissionsGaleria() {
        int permissionCheckRead = ContextCompat.checkSelfPermission(HomeProfissional.this,
                Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permissionCheckRead != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(HomeProfissional.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST);
            } else {
                ActivityCompat.requestPermissions(HomeProfissional.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST);
            }
        } else {
            abrirGaleriaIntent();
        }
    }


    private void abrirCameraIntent() {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                photoFile = File.createTempFile("PHOTOAPP", ".jpg", storageDir);
                mCurrentPhotoPath = "file:" + photoFile.getAbsolutePath();
            } catch (IOException ex) {
                Toast.makeText(getApplicationContext(), "Erro ao tirar a foto", Toast.LENGTH_SHORT).show();
            }

            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private void abrirGaleriaIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Selecione a foto"), REQUEST_GALLERY);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_IMAGE_CAPTURE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    abrirCameraIntent();
                } else {
                    Toast.makeText(this, "Erro: Permissão é necessária", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bm1 = BitmapFactory.decodeStream(getContentResolver().openInputStream(Uri.parse(mCurrentPhotoPath)));
                        bm1 = getThumbnailFromBitmap(bm1);
                        mImagemCliente.setImageBitmap(bm1);
                        salvarFoto();
                        Toast.makeText(this, "Imagem alterada com sucesso", Toast.LENGTH_SHORT).show();
                    } catch (FileNotFoundException fnex) {
                        Toast.makeText(getApplicationContext(), "Foto não encontrada", Toast.LENGTH_LONG).show();
                    }
                }
            case REQUEST_GALLERY:
                if (resultCode == RESULT_OK) {
                    try {
                        Uri imageUri = data.getData();
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                        bitmap = getThumbnailFromBitmap(bitmap);
                        this.mImagemCliente.setImageBitmap(bitmap);
                        salvarFoto();
                        Toast.makeText(this, "Imagem alterada com sucesso", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Não foi possível alterar a imagem", Toast.LENGTH_SHORT).show();

                    }
                }
        }
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
}



//    private void initFotoProfissional() {
//        initRecyclerView();
//    }
//
//    private void adicionaNoArray(ProfissionalDAO dao, List<Profissional> profissionais) {
//        for (int i = 0; i < profissionais.size(); i++) {
//            if (profissionais.get(i).getFoto() != null) {
//                Bitmap bitmap = BitmapFactory.decodeByteArray(imagemEmBits, 0, imagemEmBits.length);
//                mFotos.add(bitmap);
//            } else {
//                mFotos.add(null);
//            }
//
//        }
//    }
//
//    private void initRecyclerView(){
//        RecyclerView recyclerView = findViewById(R.id.usuariorecylcer);
//        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mFotos);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//    }
//}
