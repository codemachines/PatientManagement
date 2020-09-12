package com.example.jaydave_sanchitshah_comp304_lab_assign4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import models.Test;
import viewModel.TestviewModel;

public class AddTest extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    TextInputLayout layout_test_patient_id,layout_test_nurse_id,layout_bpl,layout_bph,layout_temp,layout_test_ph;

    TextInputEditText test_patient_id,test_nurse_id,test_bpl,test_bph,test_temp,test_ph;

    Button btn_new_test;

    public static final String MY_PREFS_NAME = "MyPrefs";

    private TestviewModel mViewModel;

    String patientid,nurseid,bpl,bph,temp,ph;

    String[] bloodGroup = { "A+", "A-", "B+", "B-", "AB+","AB-","O+","O-"};

    String selectedBg="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_test);

        Spinner spin =  findViewById(R.id.spinner);

        test_bpl=findViewById(R.id.test_bpl);
        test_bph=findViewById(R.id.test_bph);
        test_temp=findViewById(R.id.test_temp);
        test_ph=findViewById(R.id.test_ph);
        test_patient_id=findViewById(R.id.test_patient_id);

        final SharedPreferences preferences=getSharedPreferences(MY_PREFS_NAME,MODE_PRIVATE);

        btn_new_test=findViewById(R.id.btn_new_test);

        final TestviewModel mViewModel = ViewModelProviders.of(AddTest.this).get(TestviewModel.class);

        mViewModel.getInsertResult().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer result) {
                if (result == 1) {
                    Toast.makeText(AddTest.this, "Test data successfully saved", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(AddTest.this, "Error saving test data", Toast.LENGTH_SHORT).show();
                }
            }
        });

        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,bloodGroup);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

        btn_new_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int nurseid=Integer.parseInt(preferences.getString("nurseId",""));
                bpl=test_bpl.getText().toString();
                bph=test_bph.getText().toString();
                temp=test_temp.getText().toString();
                ph=test_ph.getText().toString();
                patientid=test_patient_id.getText().toString();

                if (bpl.isEmpty()){
                    layout_bpl.setError("This Field can't remain Empty");
                }else if (bph.isEmpty()){
                    layout_bph.setError("This Field can't remain Empty");
                }else if (temp.isEmpty()){
                    layout_temp.setError("This Field can't remain Empty");
                }else if (test_ph.equals(null)){
                    layout_test_ph.setError("This Field can't remain Empty");
                }else {

                    Test testData = new Test();

                    testData.setPatientId(Integer.parseInt(patientid));
                    testData.setNurseId(nurseid);
                    testData.setBPH(bph);
                    testData.setBPL(bpl);
                    testData.setTemperature(temp);
                    testData.setPH(ph);
                    testData.setBloodgroup(selectedBg);

                    mViewModel.insert(testData);

                    Intent intent=new Intent(AddTest.this,MainActivity.class);
                    startActivity(intent);

                    finish();

                }
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedBg=bloodGroup[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}