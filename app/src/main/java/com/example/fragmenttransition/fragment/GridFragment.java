package com.example.fragmenttransition.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fragmenttransition.R;
import com.example.fragmenttransition.adapter.GridAdapter;

/**
 * A fragment for displaying a grid of images.
 */
public class GridFragment extends Fragment {
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_grid, container, false);
        return recyclerView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setAdapter(new GridAdapter(this));
        recyclerView.addItemDecoration(new BottomItemDecoration());
    }

}
