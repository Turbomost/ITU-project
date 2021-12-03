package com.example.wis.ui.list;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wis.DeadlineViewModel;
import com.example.wis.ui.DeadlinesAdapter;
import com.example.wis.DataBaseHelper;
import com.example.wis.DeadlineModel;
import com.example.wis.SharedPref;
import com.example.wis.R;
import com.example.wis.databinding.FragmentListBinding;
import com.example.wis.ui.list.ListViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    private com.example.wis.ui.list.ListViewModel listViewModel;
    private FragmentListBinding binding;


    RecyclerView rvDeadline;
    LinearLayoutManager layoutManager;

    private ListViewModel ListViewModel;
    View view;
    DataBaseHelper databaseHelper;
    ArrayList<String> time,name,subject;
    DeadlinesAdapter deadlinesAdapter;
    androidx.recyclerview.widget.RecyclerView RecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        time = new ArrayList<>();
        name = new ArrayList<>();
        subject = new ArrayList<>();
        view = inflater.inflate(R.layout.fragment_list, container, false);
        RecyclerView = (RecyclerView) view.findViewById(R.id.rvDeadline);
        databaseHelper = new DataBaseHelper(getContext());

        displayData();
        deadlinesAdapter = new DeadlinesAdapter(getContext(),time,name,subject);
        RecyclerView.setAdapter(deadlinesAdapter);
        RecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        ListViewModel =
                new ViewModelProvider(this).get(ListViewModel.class);

        binding = FragmentListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        ListViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    void displayData(){
        Integer user_ID= Integer.valueOf((SharedPref.readSharedSetting(getContext(), "UserID", "-1")));
        Cursor cursor = databaseHelper.getUserDeadlines(user_ID);


        while (cursor.moveToNext()){
            time.add( cursor.getString(0));
            name.add (cursor.getString(1));
            subject.add( databaseHelper.getSubjectName(cursor.getInt(2)));


        }

    }






        /*
        listViewModel =
                new ViewModelProvider(this).get(com.example.wis.ui.list.ListViewModel.class);

        binding = FragmentListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        databaseHelper = new DataBaseHelper(root.getContext());
        deadlineList = databaseHelper.getAllDeadlines();
        view = inflater.inflate(R.layout.fragment_list, container, false);

        deadlinesAdapter = new DeadlinesAdapter(root.getContext(), deadlineList);
        rvDeadline = view.findViewById(R.id.rvDeadline);
        layoutManager = new LinearLayoutManager(root.getContext());
        rvDeadline.setLayoutManager(layoutManager);
        rvDeadline.setHasFixedSize(true);
        rvDeadline.setAdapter(deadlinesAdapter);

        return root;
        */







        /*
        final TextView textView = binding.textList;
        listViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        */

}