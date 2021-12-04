package com.example.wis;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wis.ui.DeadlinesAdapter;

import java.util.ArrayList;
import java.util.List;

public class ExcerciseActivity extends AppCompatActivity {
    DataBaseHelper databaseHelper;
    List<ExcerciseViewModel> excerciseList = new ArrayList<ExcerciseViewModel>();
    ExcercisesAdapter excerciseAdapter;
    ExcerciseViewModel excercise = new ExcerciseViewModel();
    androidx.recyclerview.widget.RecyclerView RecyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercises_activity);
        RecyclerView = (androidx.recyclerview.widget.RecyclerView) findViewById(R.id.recyclerView);
        databaseHelper = new DataBaseHelper(this);

        excerciseList = excercise.displayData(this);
        excerciseAdapter = new ExcercisesAdapter(this, excerciseList);
        RecyclerView.setAdapter(excerciseAdapter);
        RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        getIncomingIntent();

    }
    private void getIncomingIntent(){
        String subject_name = getIntent().getStringExtra("subject_name");
        Toast.makeText(this, subject_name, Toast.LENGTH_SHORT).show();
    }
}
