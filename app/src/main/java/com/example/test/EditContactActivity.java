package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.test.localdb.Contact;

import java.io.ByteArrayOutputStream;

public class EditContactActivity extends AppCompatActivity {
    private byte[] avatar;
    private String name;
    private String number;

    private byte[] gAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
        fillTextFields(getIntent());

        ImageView imageAvatar = findViewById(R.id.image_avatar);
        imageAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, 1);
                }
            }
        });
    }

    private void fillTextFields(Intent intent) {
        avatar = intent.getByteArrayExtra("image");
        name = intent.getStringExtra("name");
        number = intent.getStringExtra("number");
        ImageView imageAvatar = findViewById(R.id.image_avatar);
        byte[] avatarByteArray = avatar;
        Bitmap bmp = null;
        if (avatarByteArray != null) {
            bmp = BitmapFactory.decodeByteArray(avatarByteArray, 0, avatarByteArray.length);
            imageAvatar.setImageBitmap(Bitmap.createScaledBitmap(bmp, 200, 200, false));
        }

        EditText edit_name = findViewById(R.id.edit_name);
        edit_name.setText(name);

        EditText edit_number = findViewById(R.id.edit_number);
        edit_number.setText(number);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArrayAvatar = stream.toByteArray();
            gAvatar = byteArrayAvatar;

            ImageView avatar = findViewById(R.id.image_avatar);
            avatar.setImageBitmap(imageBitmap);
        }
    }
}