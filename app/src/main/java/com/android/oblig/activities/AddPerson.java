package com.android.oblig.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.android.oblig.R;
import com.android.oblig.modules.Person;
import com.android.oblig.modules.PersonUtil;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddPerson extends AppCompatActivity {

    private ImageView imageView;
    private EditText editText;
    private static final int PICK_IMAGE_REQUEST = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        imageView = (ImageView) findViewById(R.id.imageView2);
        editText = (EditText) findViewById(R.id.editText2);

    }

    public void addPerson(View view){
        if(editText.getText().length() > 0) {
            Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 30, baos);
            byte[] imageInByte = baos.toByteArray();

            Person newPerson = new Person(0, editText.getText().toString(), imageInByte);
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

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

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
    }
}
