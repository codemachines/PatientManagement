package com.example.jaydave_sanchitshah_comp304_lab_assign4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import adapters.PatientAdapter;
import models.Nurse;
import models.Patient;
import models.Test;
import viewModel.LoginviewModel;
import viewModel.TestviewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BaseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BaseFragment extends Fragment {

    TextView tv_greeting,tv_name,tv_size;

    Button btn_patient;

    LoginviewModel viewModel;

    TestviewModel tviewModel;


    public static final String MY_PREFS_NAME = "MyPrefs";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BaseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BaseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BaseFragment newInstance(String param1, String param2) {
        BaseFragment fragment = new BaseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_base, container, false);

        tv_greeting=view.findViewById(R.id.tv_greeting);
        tv_name=view.findViewById(R.id.tv_name);
        tv_size=view.findViewById(R.id.tv_size);

        btn_patient=view.findViewById(R.id.btn_patient);

        SharedPreferences preferences=getActivity().getSharedPreferences(MY_PREFS_NAME,getContext().MODE_PRIVATE);

        final int nurseid=Integer.parseInt(preferences.getString("nurseId",""));

        viewModel =  ViewModelProviders.of(this).get(LoginviewModel.class);

        viewModel.getInsertResult().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer result) {
                if (result == 1) {
                    Toast.makeText(getActivity(), "Test successfully saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Error saving user", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewModel.getGetAllData().observe(getActivity(), new Observer<List<Nurse>>() {

            @Override
            public void onChanged(List<Nurse> nurse) {
                for(Nurse data : nurse) {
                    if(data.getNurseId() == nurseid){
                        tv_name.setText(data.getName());
                    }
                }
            }
        });


        //Using Calander instance to display Greeting message to User/Nurse

        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay >= 0 && timeOfDay < 12){

            tv_greeting.setText("Good Morning,");
           // Toast.makeText(getActivity(), "Good Morning", Toast.LENGTH_SHORT).show();
        }else if(timeOfDay >= 12 && timeOfDay < 16){
            tv_greeting.setText("Good Afternoon,");
            //Toast.makeText(getActivity(), "Good Afternoon", Toast.LENGTH_SHORT).show();
        }else if(timeOfDay >= 16 && timeOfDay < 21){
            tv_greeting.setText("Good Evening,");
            //Toast.makeText(getActivity(), "Good Evening", Toast.LENGTH_SHORT).show();
        }else if(timeOfDay >= 21 && timeOfDay < 24){
            tv_greeting.setText("Good Night,");
            //Toast.makeText(getActivity(), "Good Night", Toast.LENGTH_SHORT).show();
        }

        btn_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getActivity(),AddPatient.class);
                startActivity(intent);
            }
        });
        return view;
    }

}