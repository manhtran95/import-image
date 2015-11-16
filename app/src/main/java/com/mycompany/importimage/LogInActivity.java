package com.mycompany.importimage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;


public class LogInActivity extends AppCompatActivity {

    private EditText password;
    private Button btnSubmit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        setLayoutItems();
        addListenerOnButton();
    }

    // set some layout items' attribute
    private void setLayoutItems() {
        setBackgroundForPassword();
        setColorForCursor();
    }

    private void setBackgroundForPassword() {
        password = (EditText) findViewById(R.id.txtPassword);
        password.setBackgroundResource(R.drawable.edit_text_style);
    }

    private void setColorForCursor() {
        Field f = null;
        try {
            f = TextView.class.getDeclaredField("mCursorDrawableRes");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        f.setAccessible(true);
        try {
            f.set(password, R.drawable.color_cursor);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void addListenerOnButton() {

        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String passwordString = password.getText().toString();

                // password is correct
                if (passwordString.equals("1234")) {
                    Intent intent = new Intent(LogInActivity.this, ImageListActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LogInActivity.this, "Wrong Password!",
                            Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}
