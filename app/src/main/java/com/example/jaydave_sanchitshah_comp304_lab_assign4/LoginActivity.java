package com.example.jaydave_sanchitshah_comp304_lab_assign4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.NurseDao;
import data.NurseDatabase;
import models.Nurse;
import viewModel.LoginviewModel;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout layout_uname,layout_password;

    TextInputEditText uname,password;

    Button btn_login;

    TextView tvsignup;

    Nurse loginData;
    NurseDao nurseDao;
    NurseDatabase dataBase;

    Map<Integer, String> userDetails = new HashMap<>();
    public static final String MY_PREFS_NAME = "MyPrefs";
    SharedPreferences.Editor editor;

    String login_username,login_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        layout_uname=findViewById(R.id.layout_uname);
        layout_password=findViewById(R.id.layout_password);

        uname=findViewById(R.id.uname);
        password=findViewById(R.id.password);

        tvsignup=findViewById(R.id.tvsignup);

        btn_login=findViewById(R.id.btn_login);

        LoginviewModel viewModel = new ViewModelProvider(this).get(LoginviewModel.class);

        SharedPreferences preferences=getSharedPreferences(MY_PREFS_NAME,MODE_PRIVATE);

        editor = getApplicationContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();

        try {
            if (preferences.getString("login","").equals("yes"))
            {
                Intent i=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this, ""+ Log.e("Error:",e.getMessage()), Toast.LENGTH_SHORT).show();
        }

        viewModel.getGetAllData().observe(this, new Observer<List<Nurse>>() {
            @Override
            public void onChanged(List<Nurse> loginTables) {
                for(Nurse data : loginTables) {
                    userDetails.put(data.getNurseId(), data.getPassword());
                }
            }
        });



        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String login_id=uname.getText().toString();
                String login_pwd=password.getText().toString();
                if (login_id.isEmpty()){
                    layout_uname.setError("This field can't remain empty");
                    layout_uname.requestFocus();
                }else if (login_pwd.isEmpty()){
                    layout_password.setError("This field can't remain empty");
                    layout_password.requestFocus();
                    layout_uname.setErrorEnabled(false);
                }else {

                    layout_uname.setErrorEnabled(false);
                    layout_password.setErrorEnabled(false);

                        for(Integer key : userDetails.keySet()){
                            if(String.valueOf(key).equals(login_id) && userDetails.get(key).equals(login_pwd)){
                                editor.putString("login","yes");
                                editor.putString("nurseId",login_id);
                                editor.commit();

                                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                                Toast.makeText(LoginActivity.this, "Hello Nurse ID:"+login_id, Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                                break;
                            }else {
                                Toast.makeText(LoginActivity.this, "User ID/Password Incorrect", Toast.LENGTH_SHORT).show();
                            }
                        }


                    }
                }

        });



        tvsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(LoginActivity.this,NurseSignUp.class);
                startActivity(intent);
            }
        });


    }
}