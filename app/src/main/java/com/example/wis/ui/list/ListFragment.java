/* ListFragment.java
 * Author     : xbella01
 * Fragment of main activity that displays deadlines in recyclerView
 */

package com.example.wis.ui.list;

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

import com.example.wis.Models.DeadlineViewModel;
import com.example.wis.Data.DataBaseHelper;
import com.example.wis.R;
import com.example.wis.databinding.FragmentListBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListFragment extends Fragment {

    private com.example.wis.ui.list.ListViewModel listViewModel;
    private FragmentListBinding binding;

    private ListViewModel ListViewModel;
    View view;
    DataBaseHelper databaseHelper;
    DeadlinesAdapter deadlinesAdapter;
    DeadlineViewModel deadline = new DeadlineViewModel();
    List<DeadlineViewModel> deadlinelist = new ArrayList<DeadlineViewModel>();
    androidx.recyclerview.widget.RecyclerView RecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_list, container, false);
        // inicialization
        RecyclerView = (RecyclerView) view.findViewById(R.id.rvDeadline);
        databaseHelper = new DataBaseHelper(getContext());

        //fetching data
        deadlinelist = deadline.displayData(getContext());

        //using Adapter
        deadlinesAdapter = new DeadlinesAdapter(getContext(), deadlinelist);
        RecyclerView.setAdapter(deadlinesAdapter);
        RecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //sorting according to date
        Collections.sort(deadlinelist,DeadlineViewModel.DeadlineDateComparator);
        deadlinesAdapter.notifyDataSetChanged();

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

}