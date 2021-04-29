package com.example.courshop_app.ui.myCourses;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.courshop_app.R;

public class MyCoursesFragment extends Fragment {


    //public MyCoursesFragment() {
        // Required empty public constructor
    //}


    //@Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View root = inflater.inflate(R.layout.fragment_my_courses, container, false);

        return root;
    }
}