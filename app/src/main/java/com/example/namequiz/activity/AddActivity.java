package com.example.namequiz.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.namequiz.R;
import com.example.namequiz.database.PersonDatabase;
import com.example.namequiz.helper.Helper;
import com.example.namequiz.model.Person;

import java.io.IOException;

public class AddActivity extends AppCompatActivity {

    ImageView imageView;
    Button captureBtn;
    Button imageBtn;
    Button addBtn;
    EditText editTextName;

    // Helper
    Helper helper = new Helper();
    PersonDatabase db;

    // Request codes
    private static final int CAMERA_CODE = 100;
    private static final int PERMISSION_CODE = 101;
    private static final int IMAGE_CODE = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        db = PersonDatabase.getInstance(this);

        // Find view
        editTextName = findViewById(R.id.editTextName);
        imageView = findViewById(R.id.imageView);
        captureBtn = findViewById(R.id.capBtn);
        imageBtn = findViewById(R.id.imageBtn);
        addBtn = findViewById(R.id.addBtn);

        // Add button click
        imageBtn.setOnClickListener(x -> chooseImage());
        addBtn.setOnClickListener(x -> addPerson());


        // Request for camera permission
        if (ContextCompat.checkSelfPermission(AddActivity.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(AddActivity.this, new String[]{
                    Manifest.permission.CAMERA
            }, CAMERA_CODE);
        }

        captureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_CODE && data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
            helper.addSelectedImg(bitmap);
        }else if(requestCode == IMAGE_CODE && resultCode == RESULT_OK){
            Uri imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                imageView.setImageBitmap(bitmap);
                helper.addSelectedImg(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    // Adds a new person to database
    public void addPerson() {
        // Verify data
        String name = editTextName.getText().toString();
        if (imageView.getDrawable() != null && !name.matches("")) {
               Person person = new Person(name, helper.getSelectedImg());

            try {
                db.personDAO().addPerson(person);

                imageView.setImageResource(0);
                editTextName.getText().clear();

                // Sends the user to database when button is clicked
                Intent i = new Intent(this, DatabaseActivity.class);

                startActivity(i);

                Toast.makeText(this, "Added " + name + " to the database", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();

                // Error message
                Toast.makeText(this, "Error... try again", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Error message
            Toast.makeText(this, "Input name and add a image", Toast.LENGTH_SHORT).show();
        }
    }

    public void chooseImage(){
        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};

            requestPermissions(permissions, PERMISSION_CODE);
        }else{
            chooseExistingImage();
        }
    }

    private void chooseExistingImage(){
        Intent i = new Intent(Intent.ACTION_PICK);
        i.setType("image/*");
        startActivityForResult(i, IMAGE_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMISSION_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                chooseExistingImage();
            }else{
                Toast.makeText(this, "Permission denied...", Toast.LENGTH_SHORT).show();
            }
        }
    }
}