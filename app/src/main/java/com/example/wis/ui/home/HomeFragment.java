/*
 * HomeFragment.java
 * Author     : xzimme03
 * Fragment view for home page
 */
package com.example.wis.ui.home;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wis.Data.DataBaseHelper;
import com.example.wis.R;
import com.example.wis.Data.SharedPref;
import com.example.wis.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment{

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    View ww;
    DataBaseHelper DB;
    ArrayList<String> subject;
    CustomAdapter customAdapter;
    androidx.recyclerview.widget.RecyclerView RecyclerView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        ww = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView = (RecyclerView) ww.findViewById(R.id.recyclerView);
        DB = new DataBaseHelper(getContext());
        subject = new ArrayList<>();

        displayData();
        customAdapter = new CustomAdapter(getContext(),subject,RecyclerView);
        RecyclerView.setAdapter(customAdapter);
        RecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);


        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return ww;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    // Function to get data from database
    void displayData(){
        Integer user_ID= Integer.valueOf((SharedPref.readSharedSetting(getContext(), "UserID", "-1")));
        Cursor cursor = DB.getAllUserSubjects(user_ID);

            while (cursor.moveToNext()){

                subject.add(cursor.getString(1));

            }

    }

}