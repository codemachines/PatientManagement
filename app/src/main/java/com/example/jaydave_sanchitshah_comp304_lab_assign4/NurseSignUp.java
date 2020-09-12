package com.example.jaydave_sanchitshah_comp304_lab_assign4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import models.Nurse;
import viewModel.LoginviewModel;

public class NurseSignUp extends AppCompatActivity {

    TextInputLayout layout_nurse_fname,layout_nurse_lname,layout_nurse_dept,layout_nurse_pwd,layout_nurse_cpwd,layout_nurse_id;

    TextInputEditText nurse_fname,nurse_lname,nurse_dept,nurse_pwd,nurse_cpwd,nurse_id;

    Button btn_signup;

    private LoginviewModel viewModel;

    Nurse loginData;

    String fname,lname,dept,password,cPassword;
    int nurseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_sign_up);

        //Binding TextINputLAyout
        layout_nurse_fname=findViewById(R.id.layout_nurse_fname);
        layout_nurse_lname=findViewById(R.id.layout_nurse_lname);
        layout_nurse_dept=findViewById(R.id.layout_nurse_dept);
        layout_nurse_pwd=findViewById(R.id.layout_nurse_pwd);
        layout_nurse_cpwd=findViewById(R.id.layout_nurse_cpwd);
        layout_nurse_id=findViewById(R.id.layout_nurse_id);

        //Binding TextInputEditText

        nurse_fname=findViewById(R.id.nurse_fname);
        nurse_lname=findViewById(R.id.nurse_lname);
        nurse_dept=findViewById(R.id.nurse_dept);
        nurse_pwd=findViewById(R.id.nurse_pwd);
        nurse_cpwd=findViewById(R.id.nurse_cpwd);
        nurse_id=findViewById(R.id.nurse_id);

        //Binding Buttons
        btn_signup=findViewById(R.id.btn_signup);

        viewModel = new ViewModelProvider(this).get(LoginviewModel.class);
        loginData = new Nurse();



        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                fname=nurse_fname.getText().toString();
                lname=nurse_lname.getText().toString();
                dept=nurse_dept.getText().toString();
                password=nurse_pwd.getText().toString();
                cPassword=nurse_cpwd.getText().toString();
                nurseId=Integer.parseInt(nurse_id.getText().toString());

                if (fname.isEmpty()){
                    layout_nurse_fname.setError("This Field can't remain Empty");
                }else if (lname.isEmpty()){
                    layout_nurse_lname.setError("This Field can't remain Empty");
                }else if (dept.isEmpty()){
                    layout_nurse_dept.setError("This Field can't remain Empty");
                }else if (password.isEmpty()){
                    layout_nurse_pwd.setError("This Field can't remain Empty");
                }else if (cPassword.isEmpty()){
                    layout_nurse_cpwd.setError("This Field can't remain Empty");
                }else if (nurse_id==null){
                    layout_nurse_id.setError("This Field can't remain Empty");
                }else if (!password.equals(cPassword)){
                    layout_nurse_cpwd.setError("Password Mismatch");
                    layout_nurse_cpwd.requestFocus();
                } else {
                    loginData.setNurseId(nurseId);
                    loginData.setPassword(password);
                    loginData.setFirstname(fname);
                    loginData.setLastname(lname);
                    loginData.setDepartment(dept);
                    viewModel.insert(loginData);

                    Intent intent=new Intent(NurseSignUp.this,LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}