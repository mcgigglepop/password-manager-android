package com.mcgigglepop.myprofilebox.ui.dashboard;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

import com.mcgigglepop.myprofilebox.R;
import com.mcgigglepop.myprofilebox.databinding.FragmentDashboardBinding;

import java.util.ArrayList;
import java.util.List;

import adapters.AccountAdapter;
import helpers.DBPasswordHelper;
import models.Account;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private ListView listView;
    private EditText searchInput;

    private DBPasswordHelper databaseHelper;

    private List<Account> accountList;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        databaseHelper = new DBPasswordHelper(requireContext());
        accountList = databaseHelper.getAllAccounts();

        listView = root.findViewById(R.id.list_view);
        initSearchWidgets(root);
        setupData();
        setUpList(root);
        setUpOnclickListener();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initSearchWidgets(View v) {
        searchInput = (EditText) v.findViewById(R.id.search_input);
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ArrayList<Account> filteredAccounts = new ArrayList<Account>();

                for(Account account: accountList)
                {
                    if(account.getTitle().toLowerCase().contains(charSequence.toString().toLowerCase()))
                    {
                        filteredAccounts.add(account);
                    }
                }
                AccountAdapter adapter = new AccountAdapter(getActivity(), 0, filteredAccounts);
                listView.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }
    private void setupData()
    {

    }
    private void setUpList(View v)
    {
        listView = (ListView) v.findViewById(R.id.list_view);

        AccountAdapter adapter = new AccountAdapter(getActivity(), 0, accountList);
        listView.setAdapter(adapter);
    }
    private void setUpOnclickListener()
    {

    }
}