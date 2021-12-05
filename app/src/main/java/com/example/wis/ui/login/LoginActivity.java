/*
 * LoginActivity.java
 * Author     : xbella01
 *
 */
package com.example.wis.ui.login;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wis.Data.DataBaseHelper;
import com.example.wis.Data.SharedPref;
import com.example.wis.ui.main.MainActivity;
import com.example.wis.R;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText edtUsername;
    EditText edtPassword;
    DataBaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);

        databaseHelper = new DataBaseHelper(LoginActivity.this);

        if(Integer.valueOf(SharedPref.readSharedSetting(LoginActivity.this, "UserID", "-1"))!=-1){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("username", edtUsername.getText().toString());
            startActivity(intent);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isExist = databaseHelper.checkUserExist(LoginActivity.this,edtUsername.getText().toString(), edtPassword.getText().toString());

                if(isExist){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("username", edtUsername.getText().toString());
                    startActivity(intent);
                } else {
                    edtPassword.setText(null);
                    Toast.makeText(LoginActivity.this, "Login failed. Invalid username or password.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

}
