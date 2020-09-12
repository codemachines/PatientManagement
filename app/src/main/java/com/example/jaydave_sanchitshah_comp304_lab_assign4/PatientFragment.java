package com.example.jaydave_sanchitshah_comp304_lab_assign4;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import adapters.PatientAdapter;
import models.Patient;
import viewModel.PatientviewModel;


public class PatientFragment extends Fragment {

    private Button btn_patient_add;

    private RecyclerView patient_rv;

    List<Patient> patientsList = new ArrayList<Patient>();

    PatientAdapter patientsAdapter;


    public PatientFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_patient, container, false);

        patient_rv =view.findViewById(R.id.patient_rv);

        btn_patient_add =view.findViewById(R.id.btn_patient_add);

        //rv_patient.setAdapter(patientsAdapter);
        //patientsList.clear();
        patient_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
//        patientsAdapter.notifyDataSetChanged();
       PatientviewModel viewModel = ViewModelProviders.of(this).get(PatientviewModel.class);


        viewModel.getAllPatient().observe(getActivity(), new Observer<List<Patient>>() {
            @Override
            public void onChanged(List<Patient> patients) {
                patientsList.addAll(patients);

                patientsAdapter = new PatientAdapter(PatientFragment.this.getActivity(), patientsList);
                //setting adapter to recyclerview
                patient_rv.setAdapter(patientsAdapter);
                //
                patientsAdapter.notifyDataSetChanged();
                //rv_patient.setAdapter(patientsAdapter);
            }
        });


        btn_patient_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getActivity(),AddPatient.class);
                startActivity(intent);
            }
        });



        return view;
    }
}