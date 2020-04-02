package com.example.smart_agriculture_deloitte;


import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;




public class UserDetailsActivity extends Activity {


    TextView user_login_name, user_login_mail;
    Button edit_user_photo;
    ImageView user_login_photo;

    private static final int PICK_FROM_GALLERY = 2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdetails);


        user_login_photo = findViewById(R.id.user_login_photo);
        user_login_name = findViewById(R.id.user_login_name);
        user_login_mail = findViewById(R.id.user_login_mail);
        edit_user_photo = findViewById(R.id.edit_user_photo);


//        user_login_photo.setImageResource(R.mipmap.ic_launcher);
        user_login_name.setText(MainActivity.user_login_name_input);
        user_login_mail.setText(MainActivity.user_login_mail_input);

        edit_user_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                //******call android default gallery
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                //******code for crop image
                intent.putExtra("crop", "true");
                intent.putExtra("aspectX", 0);
                intent.putExtra("aspectY", 0);
                try {
                    intent.putExtra("return-data", true);
                    startActivityForResult(
                            Intent.createChooser(intent, "Complete action using"),
                            PICK_FROM_GALLERY);
                } catch (ActivityNotFoundException e) {
                }

            }
        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_FROM_GALLERY) {
                Bundle extras2 = data.getExtras();
                if (extras2 != null) {
                    Bitmap photo = extras2.getParcelable("image");
                    user_login_photo.setImageBitmap(photo);
                }
            }

            if (requestCode == 1) {
                Bundle extras = data.getExtras();
                if (extras != null) {
                    Bitmap photo = extras.getParcelable("data");
                    user_login_photo.setImageBitmap(photo);
                }
            }
        }


    }


}