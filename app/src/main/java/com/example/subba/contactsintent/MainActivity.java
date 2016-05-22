package com.example.subba.contactsintent;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button) findViewById(R.id.textView1)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,  ContactsContract.Contacts.CONTENT_URI);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (data != null) {
            Uri uri = data.getData();

            if (uri != null) {
                Cursor c = null;

                try {
                    c = getContentResolver().query(uri, null
                            // new String[] {
                            //ContactsContract.CommonDataKinds.Phone.NUMBER,
                            //ContactsContract.CommonDataKinds.Phone.TYPE},
                            , null, null, null);

                    if (c != null && c.moveToFirst()) {
                        String number = c.getString(0);
                        String type = c.getString(1);
                    }
                } finally {
                    if (c != null && !c.isClosed())
                        c.close();
                }
            }
        }
    }
}