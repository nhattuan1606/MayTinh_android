package com.example.myapplication.ui.length;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.myapplication.databinding.FragmentLengthBinding;

import androidx.fragment.app.Fragment;


public class LengthFragment extends Fragment {
    private FragmentLengthBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentLengthBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
