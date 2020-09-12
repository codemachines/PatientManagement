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
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import models.Patient;
import viewModel.PatientviewModel;

public class AddPatient extends AppCompatActivity {

    TextInputEditText patient_fname,patient_lname,patient_dept,patient_room,patient_add,patient_mobile,patient_disease;

    TextInputLayout layout_patient_fname,layout_patient_lname,layout_patient_dept,layout_patient_room,layout_patient_add,layout_patient_mobile,layout_patient_disease;

    Button btn_add_patient;

    String p_firstName,p_lastName,p_department,p_room,p_address,p_mobile,p_disease;

    public static final String MY_PREFS_NAME = "MyPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        layout_patient_fname=findViewById(R.id.layout_patient_fname);
        layout_patient_lname=findViewById(R.id.layout_patient_lname);
        layout_patient_dept=findViewById(R.id.layout_patient_dept);
        layout_patient_room=findViewById(R.id.layout_patient_room);
        layout_patient_add=findViewById(R.id.layout_patient_add);
        layout_patient_mobile=findViewById(R.id.layout_patient_mobile);
        layout_patient_disease=findViewById(R.id.layout_patient_disease);

        patient_fname=findViewById(R.id.patient_fname);
        patient_lname=findViewById(R.id.patient_lname);
        patient_dept=findViewById(R.id.patient_dept);
        patient_room=findViewById(R.id.patient_room);
        patient_add=findViewById(R.id.patient_add);
        patient_mobile=findViewById(R.id.patient_mobile);
        patient_disease=findViewById(R.id.patient_disease);

        btn_add_patient=findViewById(R.id.btn_add_patient);

        final PatientviewModel mViewModel = ViewModelProviders.of(AddPatient.this).get(PatientviewModel.class);
        final SharedPreferences preferences=getSharedPreferences(MY_PREFS_NAME,MODE_PRIVATE);

        mViewModel.getInsertResult().observe(AddPatient.this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer result) {
                if (result == 1) {
                    Toast.makeText(AddPatient.this, "Patient successfully saved", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(AddPatient.this, "Error saving user", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_add_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                p_firstName=patient_fname.getText().toString();
                p_lastName=patient_lname.getText().toString();
                p_department=patient_dept.getText().toString();
                p_room=patient_room.getText().toString();
                p_address=patient_add.getText().toString();
                p_mobile=patient_mobile.getText().toString();
                p_disease=patient_disease.getText().toString();

                if (p_firstName.isEmpty() ){
                    layout_patient_fname.setError("This Field is necessary");
                    layout_patient_fname.requestFocus();
                }else if (p_lastName.isEmpty()){
                    layout_patient_lname.setError("This Field is necessary");
                    layout_patient_fname.setErrorEnabled(false);
                    layout_patient_lname.requestFocus();
                }else if (p_department.isEmpty()){
                    layout_patient_lname.setErrorEnabled(false);
                    layout_patient_dept.setError("This Field is necessary");
                    layout_patient_dept.requestFocus();
                }else if (p_room.isEmpty()){
                    layout_patient_dept.setErrorEnabled(false);
                    layout_patient_room.setError("This Field is necessary");
                    layout_patient_room.requestFocus();
                }else{

                    int id=Integer.parseInt(preferences.getString("nurseId",""));
                    Patient patientData = new Patient();
                    patientData.setNurseId(id);
                    patientData.setAddress(p_address);
                    patientData.setPhone(p_mobile);
                    patientData.setFirstname(p_firstName);
                    patientData.setLastname(p_lastName);
                    patientData.setDepartment(p_department);
                    patientData.setRoom(p_room);
                    patientData.setDisease(p_disease);
                    mViewModel.insert(patientData);

                    Intent intent=new Intent(AddPatient.this,MainActivity.class);
                    startActivity(intent);
                    finish();

                }

            }
        });
    }
}