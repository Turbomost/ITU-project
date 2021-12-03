package com.example.wis.ui.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wis.DataBaseHelper;
import com.example.wis.DeadlineModel;
import com.example.wis.ui.DeadlinesAdapter;
import com.example.wis.R;
import com.example.wis.databinding.FragmentListBinding;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    private com.example.wis.ui.list.ListViewModel listViewModel;
    private FragmentListBinding binding;

    View view;
    DataBaseHelper databaseHelper;
    RecyclerView rvDeadline;
    DeadlinesAdapter deadlinesAdapter;
    LinearLayoutManager layoutManager;
    List<DeadlineModel> deadlineList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
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










        /*
        final TextView textView = binding.textList;
        listViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        */
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}