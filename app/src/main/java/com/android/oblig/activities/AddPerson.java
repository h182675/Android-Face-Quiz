package com.android.oblig.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.android.oblig.R;
import com.android.oblig.modules.Person;
import com.android.oblig.modules.PersonUtil;

//Changed by Petter
import android.hardware.camera2.*;


import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddPerson extends AppCompatActivity {
/*
TODO
Consider moving permission check and request to mainmenu.java
 */


    private ImageView imageView;
    private EditText editText;

    private static final int PICK_IMAGE_REQUEST = 100;
    //Changed by Petter
    private static final int REQUEST_IMAGE_CAPTURE = 101;
    private static final int PERMISSION_REQUEST_CAMERA = 102;

    //Changed by Petter: checks if device has camera on runtime
    private boolean checkCameraHardware(Context context){
        if(context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            return true;
        } else {
            return false;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        //Changed by Petter
        checkCameraHardware(this);

        //Changed by Petter: checks if the app has permission to the camera
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    PERMISSION_REQUEST_CAMERA);

        }


        imageView = (ImageView) findViewById(R.id.imageView2);
        editText = (EditText) findViewById(R.id.editText2);
    }

    public void addPerson(View view){
        if(editText.getText().length() > 0) {
            Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            byte[] imageInByte = PersonUtil.INSTANCE.bitmapToByteArray(bitmap);


            Person newPerson = new Person(0, editText.getText().toString().toUpperCase(), imageInByte);
            MainMenu.db.personDao().insert(newPerson);

            editText.setText("");

            Toast.makeText(this, "Person added",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void openGallery(View view){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, PICK_IMAGE_REQUEST);
    }
    //Changed by Petter
    public void takePicture(View view){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePictureIntent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    //Changed by Petter: added switch case, either choose picture or take photo
    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        switch(reqCode) {
            case 100:
                if (resultCode == RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    imageView.setImageBitmap(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            break;
            case 101:
            if(reqCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                imageView.setImageBitmap(imageBitmap);
                }
                break;
            }
        }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults){
        switch(requestCode){
            case PERMISSION_REQUEST_CAMERA:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                }
            }
        }
    }
}

