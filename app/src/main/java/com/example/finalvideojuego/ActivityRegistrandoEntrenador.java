package com.example.finalvideojuego;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalvideojuego.Entities.Entrenador;
import com.example.finalvideojuego.Services.RegistrandoEntrenadorService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityRegistrandoEntrenador extends AppCompatActivity {

    Button btnGaleria;
    Uri imageUri;
    ImageView mPicture;
    String Imagen;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrando_entrenador);

        EditText etNombreEntrenador = findViewById(R.id.etNombreEntrenador);
        EditText etPuebloEntrenador = findViewById(R.id.etPuebloEntrenador);

        Button botonGuardar = findViewById(R.id.btnGuardarEntrenador);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://upn.lumenes.tk/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RegistrandoEntrenadorService service =retrofit.create(RegistrandoEntrenadorService.class);



        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Imagen = btnGaleria.getText().toString();
                String nombre= etNombreEntrenador.getText().toString();
                String pueblo = etPuebloEntrenador.getText().toString();

                Entrenador entrenador = new Entrenador(Imagen, nombre,pueblo);
                Call<Void> CreateEntrenador = service.CreateEntrenador(entrenador);


                CreateEntrenador.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Log.i("ERROR ACAA", String.valueOf(response.code()));
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });


            }
        });






        solicitarPermisos();

        Button btnCamara = findViewById(R.id.btnCamara);

        //Encode -> btnGaleria


        mPicture = findViewById(R.id.ivPicture);



        btnGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(intent, 15);
                if(ContextCompat.checkSelfPermission(ActivityRegistrandoEntrenador.this
                ,Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(ActivityRegistrandoEntrenador.this
                    ,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}
                    ,100);
                }else{
                    selectImage();
                }
            }
        });

        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 10);
            }
        });

    }

    private void selectImage() {

        mPicture.setImageBitmap(null);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Image")
        ,100);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull  String[] permissions, @NonNull  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 100 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            selectImage();
        }else {
            Toast.makeText(getApplicationContext()
            ,"Permiso denegado", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && resultCode == RESULT_OK)
        {
//            Bundle extras = data.getExtras();
//            Bitmap bitmap = (Bitmap) extras.get("data");
//            mPicture.setImageBitmap(bitmap);
            Uri uri  = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver()
                , uri );

                ByteArrayOutputStream stream= new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG ,100,stream);

                byte[] bytes = stream.toByteArray();
                //Obtengo base 64 convertido en string
                //imagenEntrenador = Base64.getEncoder().encodeToString(bytes,Base64.DEFAULT);
                    Imagen = Base64.encodeToString(bytes, Base64.DEFAULT);
                    textView.setText(Imagen);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(resultCode == RESULT_OK && requestCode == 15){
            imageUri = data.getData();
            mPicture.setImageURI(imageUri);
        }
    }


    private void solicitarPermisos() {
        if(checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[] {Manifest.permission.CAMERA}, 11);
        }
    }
}