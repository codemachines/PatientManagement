package com.example.jaydave_sanchitshah_comp304_lab_assign4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import models.Test;
import viewModel.TestviewModel;

public class UpdateTest extends AppCompatActivity {

    TextInputLayout layout_update_bpl,layout_update_bph,layout_update_ph,layout_update_temp,layout_update_bloodgroup;

    TextInputEditText update_bpl,update_bph,update_ph,update_temp,update_bloodgroup;

    TextView tv_testid,tv_nurseid,tv_patientid;



    int patientId,testId,nurseid;

    TestviewModel viewModel;

    public static final String MY_PREFS_NAME = "MyPrefs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_test);

        layout_update_bpl=findViewById(R.id.layout_update_bpl);
        layout_update_bph=findViewById(R.id.layout_update_bph);
        layout_update_ph=findViewById(R.id.layout_update_ph);
        layout_update_temp=findViewById(R.id.layout_update_temp);
        layout_update_bloodgroup=findViewById(R.id.layout_update_bloodgroup);

        update_bpl=findViewById(R.id.update_bpl);
        update_bph=findViewById(R.id.update_bph);
        update_ph=findViewById(R.id.update_ph);
        update_temp=findViewById(R.id.update_temp);
        update_bloodgroup=findViewById(R.id.update_bloodgroup);

        tv_testid=findViewById(R.id.tv_testid);
        tv_nurseid=findViewById(R.id.tv_nurseid);
        tv_patientid=findViewById(R.id.tv_patientid);



        SharedPreferences preferences=getSharedPreferences(MY_PREFS_NAME,MODE_PRIVATE);

        nurseid=Integer.parseInt(preferences.getString("nurseId",""));

        Intent intent = getIntent();
        patientId=intent.getIntExtra("patient_id",111);
        testId=intent.getIntExtra("test_id",111);

        tv_nurseid.setText(""+nurseid);
        tv_patientid.setText(""+patientId);

        viewModel =  ViewModelProviders.of(this).get(TestviewModel.class);

        viewModel.getInsertResult().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer result) {
                if (result == 1) {
                    Toast.makeText(getApplication(), "Test successfully saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplication(), "Error saving user", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewModel.getAllPatient().observe(this, new Observer<List<Test>>() {

            @Override
            public void onChanged(List<Test> tests) {
                for(Test data : tests) {
                    if(data.getTestId() == testId){
                        tv_testid.setText(""+data.getTestId());
                        update_bpl.setText(data.getBPL());
                        update_bph.setText(data.getBPH());
                        update_temp.setText(data.getTemperature());
                        update_ph.setText(data.getPH());
                        update_bloodgroup.setText(data.getBloodgroup());
                    }
                }
            }
        });



    }
}