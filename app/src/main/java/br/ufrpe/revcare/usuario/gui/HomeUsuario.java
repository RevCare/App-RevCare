package br.ufrpe.revcare.usuario.gui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import br.ufrpe.revcare.R;
import br.ufrpe.revcare.infra.gui.MainActivity;
import br.ufrpe.revcare.usuario.dominio.Usuario;
import br.ufrpe.revcare.usuario.negocio.SessaoUsuario;
import br.ufrpe.revcare.usuario.negocio.UsuarioServices;

public class HomeUsuario extends AppCompatActivity {
    private TextView nome;
    private TextView cpf ;
    private TextView telefone;
    private TextView email;
    private ImageView mImage;
    private static String mCurrentPhotoPath;
    private static final int PERMISSION_REQUEST = 0;
    private static final int REQUEST_TAKE_PHOTO = 1;
    private static final int REQUEST_GALLERY = 2;
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    private Usuario usuario = SessaoUsuario.getUsuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_usuario);
        getSupportActionBar().hide();
        preencheTela(SessaoUsuario.getUsuario());

        Button btnTrocarFoto = findViewById(R.id.imagemUsuario);
        Button btnProcurarProfissional = findViewById(R.id.botao_procurar_usuario);
        btnProcurarProfissional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeUsuario.this, RecyclerViewUsuario.class));
            }
        });

        Button buttonSair = findViewById(R.id.buttonSairUsuario);
        buttonSair.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SessaoUsuario.reset();
                finish();
                startActivity(new Intent(HomeUsuario.this, MainActivity.class));
            }
        });

        btnTrocarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] opcoes = {"Tirar foto", "Escolher foto"};
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeUsuario.this);
                builder.setTitle("Alterar Foto");
                builder.setItems(opcoes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if ("Tirar foto".equals(opcoes[which])){
                            getPermissionsCamera();
                        }
                        else if ("Escolher foto".equals(opcoes[which])){
                            getPermissionsGaleria();
                        }
                    }
                });
                builder.show();
            }
        });
        }



    private void getPermissionsCamera() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
        else
            abrirCameraIntent();
    }

    private void getPermissionsGaleria() {
        int permissionCheckRead = ContextCompat.checkSelfPermission(HomeUsuario.this,
                Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permissionCheckRead != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(HomeUsuario.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST);
            } else {
                ActivityCompat.requestPermissions(HomeUsuario.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST);
            }
        } else {
            abrirGaleriaIntent();
        }
    }

    public void preencheTela(Usuario usuario) {
        nome = findViewById(R.id.nomeUsuarioHome);
        cpf = findViewById(R.id.cpfUsuarioHome);
        telefone = findViewById(R.id.telefoneUsuarioHome);
        email = findViewById(R.id.emailUsuarioHome);
        mImage = findViewById(R.id.image);

        nome.setText(usuario.getNome());
        cpf.setText(usuario.getCpf());
        telefone.setText(usuario.getTelefone());
        email.setText(usuario.getEmail());
        byte[] imagemEmBits = usuario.getFoto();
        if (usuario.getFoto() != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imagemEmBits, 0, imagemEmBits.length);
            mImage.setImageBitmap(bitmap);
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
            }
            catch(IOException ex){
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
                        mImage.setImageBitmap(bm1);
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
                        this.mImage.setImageBitmap(bitmap);
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
        usuario.setFoto(foto);
        UsuarioServices.alteraFotoUsuario(usuario);
    }


    private byte[] conveterImageViewToByte() {
        Bitmap bitmap = ((BitmapDrawable) mImage.getDrawable()).getBitmap();
        ByteArrayOutputStream saida = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, saida);
        return saida.toByteArray();
    }

    private Bitmap getThumbnailFromBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int max = Math.max(width, height);
        if (max>512) {
            int thumbWidth = Math.round((512f/max)* width);
            int thumbHeight = Math.round((512f/max)* height);
            Bitmap thumbnail = ThumbnailUtils.extractThumbnail(bitmap, thumbWidth , thumbHeight);
            bitmap.recycle();
            return thumbnail ;
        } else {
            return bitmap ;
        }
    }
 }