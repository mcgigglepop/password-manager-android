package com.mcgigglepop.myprofilebox.ui.dashboard;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.mcgigglepop.myprofilebox.databinding.FragmentDashboardBinding;

import java.util.List;

import helpers.DBPasswordHelper;
import models.Account;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    DBPasswordHelper databaseHelper;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        databaseHelper = new DBPasswordHelper(requireContext());
        List<Account> accountList = databaseHelper.getAllAccounts();


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}