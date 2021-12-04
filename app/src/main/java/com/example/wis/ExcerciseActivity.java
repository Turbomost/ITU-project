package com.example.wis;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wis.ui.DeadlinesAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExcerciseActivity extends AppCompatActivity {
    DataBaseHelper databaseHelper = new DataBaseHelper(this);
    List<ExcerciseViewModel> excerciseList = new ArrayList<ExcerciseViewModel>();
    ExcercisesAdapter excerciseAdapter;
    ExcerciseViewModel excercise = new ExcerciseViewModel();
    androidx.recyclerview.widget.RecyclerView RecyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercises_activity);
        String subject_name = getIncomingIntent();
        int subject_id = databaseHelper.getSubjectId(subject_name);
        RecyclerView = (androidx.recyclerview.widget.RecyclerView) findViewById(R.id.recyclerView);
        databaseHelper = new DataBaseHelper(this);
        SharedPref.saveSharedSetting(this, "SubjectID", Integer.toString(subject_id));
        excerciseList = excercise.displayData(this, subject_id);
        excerciseAdapter = new ExcercisesAdapter(this, excerciseList);
        RecyclerView.setAdapter(excerciseAdapter);
        RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        Collections.sort(excerciseList, ExcerciseViewModel.ExcerciseTimeComparator);
        excerciseAdapter.notifyDataSetChanged();

    }
    private String getIncomingIntent(){
        String subject_name = getIntent().getStringExtra("subject_name");
        //Toast.makeText(this, subject_name, Toast.LENGTH_SHORT).show();
        return subject_name;
    }
}
