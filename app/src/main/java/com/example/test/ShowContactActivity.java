package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test.localdb.Contact;
import com.example.test.localdb.ContactViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ShowContactActivity extends AppCompatActivity {

    private static Contact contact;
    private static ContactViewModel contactViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contact);
        fillTextFields(getIntent());

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowContactActivity.this, EditContactActivity.class);
                intent.putExtra("id", contact.getId());
                intent.putExtra("name", contact.getName());
                intent.putExtra("number", contact.getNumber());
                intent.putExtra("image", contact.getImage());
                startActivity(intent);
            }
        });

        ImageView callButton = findViewById(R.id.button_call);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + contact.getNumber()));
                startActivity(intent);
            }
        });

        ImageView textButton = findViewById(R.id.button_text);
        textButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse("sms:" + contact.getNumber()));
                startActivity(sendIntent);
            }
        });

        byte[] avatarByteArray = contact.getImage();
        Bitmap bmp = null;
        if (avatarByteArray != null) {
            bmp = BitmapFactory.decodeByteArray(avatarByteArray, 0, contact.getImage().length);
            ImageView callButton2 = findViewById(R.id.button_avatar);
            callButton2.setImageBitmap(Bitmap.createScaledBitmap(bmp, 200/*callButton2.getWidth()*/, /*callButton2.getHeight()*/200, false));
        }
        else {
            Drawable d = ContextCompat.getDrawable(this, R.drawable.ic_launcher_foreground);
            ImageView callButton2 = findViewById(R.id.button_avatar);
            callButton2.setImageResource(R.drawable.ic_launcher_foreground);
            callButton2.setColorFilter(getResources().getColor(R.color.colorPrimary));
        }


        Button deleteButton = findViewById(R.id.button_delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactViewModel.deleteAll();
                goBack();
            }
        });
    }

    private void fillTextFields(Intent intent) {
        int clickedPosition = intent.getIntExtra("position", -1);
        contactViewModel = MainActivity.getmContactViewModel();
        contact = contactViewModel.getAllContacts().getValue().get(clickedPosition);
        TextView textViewName = findViewById(R.id.textView_name);
        TextView textViewNumber = findViewById(R.id.textView_number);
        textViewName.setText(contact.getName());
        textViewNumber.setText(contact.getNumber());
    }

    private void goBack() {
        this.finish();
    }
}