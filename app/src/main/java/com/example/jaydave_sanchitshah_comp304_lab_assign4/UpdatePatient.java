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

import models.Patient;
import viewModel.PatientviewModel;

public class UpdatePatient extends AppCompatActivity {

    TextView tv_patient,tv_patient_id,tv_nurseId;

    TextInputLayout layout_update_dept,layout_update_room,layout_update_add,layout_update_mobile,layout_update_disease;

    TextInputEditText patient_update_dept,patient_update_room,update_add,update_mobile,update_disease;

    Button btn_update_patient,btn_test;

    public static final String MY_PREFS_NAME = "MyPrefs";



    int patientId;

    PatientviewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_patient);

        tv_nurseId=findViewById(R.id.tv_nurseId);
        tv_patient=findViewById(R.id.tv_patient);
        tv_patient_id=findViewById(R.id.tv_patient_id);

        patient_update_room=findViewById(R.id.patient_update_room);
        patient_update_dept=findViewById(R.id.patient_update_dept);
        update_add=findViewById(R.id.update_add);
        update_mobile=findViewById(R.id.update_mobile);
        update_disease=findViewById(R.id.update_disease);



        viewModel =  ViewModelProviders.of(this).get(PatientviewModel.class);

        btn_update_patient=findViewById(R.id.btn_update_patient);
        Intent intent = getIntent();
        patientId=intent.getIntExtra("patient_id",111);
        String patientName=getIntent().getStringExtra("patient_name");
        final String patientfName=getIntent().getStringExtra("patient_fname");
        final String patientlName=getIntent().getStringExtra("patient_lname");

        final SharedPreferences preferences=getSharedPreferences(MY_PREFS_NAME,MODE_PRIVATE);

        final int id=Integer.parseInt(preferences.getString("nurseId",""));

        //tv_nurseId.setText(""+id);
        tv_patient_id.setText(""+patientId);
        tv_patient.setText(patientName);

        viewModel.getInsertResult().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer result) {
                if (result == 1) {

                    Toast.makeText(getApplication(), "Patient successfully saved", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplication(), "Error saving user", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewModel.getAllPatient().observe(this, new Observer<List<Patient>>() {
            @Override
            public void onChanged(List<Patient> patients) {
                for(Patient data : patients) {
                    if(data.getPatientId() == patientId){
                        tv_nurseId.setText(""+data.getNurseId());
                        patient_update_dept.setText(data.getDepartment());
                        patient_update_room.setText(data.getRoom());
                        update_add.setText(data.getAddress());
                        update_mobile.setText(data.getPhone());
                        update_disease.setText(data.getDisease());
                    }
                }
            }
        });

        btn_update_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String patient_add=update_add.getText().toString();
                String patient_mobile=update_mobile.getText().toString();
                String patient_room=patient_update_room.getText().toString();
                String patient_dept=patient_update_dept.getText().toString();
                String patient_disease=update_disease.getText().toString();

                if ( patient_room.isEmpty() || patient_dept.isEmpty()){
                    Toast.makeText(UpdatePatient.this, "Any field should not remain empty", Toast.LENGTH_SHORT).show();
                }else {

                    String department = patient_update_dept.getText().toString();

                    String room = patient_update_room.getText().toString();
                    //String mobile=patient_update_mobile.getText().toString();
                    //String address=patient_update_add.getText().toString();
                    Patient patientData = new Patient();
                    patientData.setFirstname(patientfName);
                    patientData.setLastname(patientlName);
                    patientData.setDepartment(department);
                    patientData.setPatientId(patientId);
                    patientData.setNurseId(id);
                    patientData.setRoom(room);
                    patientData.setPhone(patient_add);
                    patientData.setAddress(patient_mobile);
                    patientData.setDisease(patient_disease);
                    viewModel.update(patientData);

                    Intent intent1=new Intent(UpdatePatient.this,MainActivity.class);
                    startActivity(intent1);

                    finish();

                }

            }
        });
    }
}