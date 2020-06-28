package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.test.localdb.Contact;

import java.io.ByteArrayOutputStream;

public class NewContactActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.name.REPLY";
    public static final String EXTRA_REPLY_NUMBER = "com.example.android.number.REPLY";
    public static final String EXTRA_REPLY_AVATAR = "com.example.android.avatar.REPLY";

    private EditText mEditContactViewName;
    private EditText mEditContactViewNumber;

    private byte[] gAvatar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);
        mEditContactViewName = findViewById(R.id.edit_name);
        mEditContactViewNumber = findViewById(R.id.edit_number);

        final ImageView avatar = findViewById(R.id.image_avatar);
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, 1);
                }
            }
        });


        final Button saveButton = findViewById(R.id.button_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditContactViewName.getText()) || TextUtils.isEmpty(mEditContactViewNumber.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String name = mEditContactViewName.getText().toString();
                    String number = mEditContactViewNumber.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, name);
                    replyIntent.putExtra(EXTRA_REPLY_NUMBER, number);
                    replyIntent.putExtra(EXTRA_REPLY_AVATAR, gAvatar);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
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