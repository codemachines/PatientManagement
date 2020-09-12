package com.example.jaydave_sanchitshah_comp304_lab_assign4;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import adapters.TestAdapter;
import models.Test;
import viewModel.TestviewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TestFragment extends Fragment {

    RecyclerView test_rv;

    Button btn_test_add;

    List<Test> testList = new ArrayList<Test>();

    private TestAdapter adapter;
    private TestviewModel mViewModel;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TestFragment newInstance(String param1, String param2) {
        TestFragment fragment = new TestFragment();
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
        View view= inflater.inflate(R.layout.fragment_test, container, false);

        mViewModel = ViewModelProviders.of(this).get(TestviewModel.class);

        test_rv=view.findViewById(R.id.test_rv);

        btn_test_add=view.findViewById(R.id.btn_test_add);

        // adapter = new TestAdapter();
        test_rv.setAdapter(adapter);
        test_rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        TestviewModel viewModel = ViewModelProviders.of(this).get(TestviewModel.class);

        viewModel.getAllPatient().observe(getActivity(), new Observer<List<Test>>() {
            @Override
            public void onChanged(List<Test> test) {
                testList.addAll(test);
                TestAdapter adapter = new TestAdapter(getActivity(), testList);
                //setting adapter to recyclerview
                test_rv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

        btn_test_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),AddTest.class);
                startActivity(intent);
            }
        });
        return view;
    }
}