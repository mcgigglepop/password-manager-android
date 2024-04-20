package com.mcgigglepop.myprofilebox.ui.add_password;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mcgigglepop.myprofilebox.R;
import com.mcgigglepop.myprofilebox.databinding.FragmentAddPasswordBinding;
import com.uk.tastytoasty.TastyToasty;

import helpers.DBPasswordHelper;
import helpers.ValidationHelper;
import models.Account;

public class AddPasswordFragment extends Fragment {
    private TextInputLayout titleTextInputLayout;
    private TextInputLayout accountTypeTextInputLayout;
    private TextInputLayout usernameTextInputLayout;
    private TextInputLayout passwordTextInputLayout;

    private TextInputEditText titleTextInputEditText;
    private AutoCompleteTextView accountTypeAutoCompleteTextView;
    private TextInputEditText usernameTextInputEditText;
    private TextInputEditText passwordTextInputEditText;

    private ValidationHelper inputValidation;
    private DBPasswordHelper databaseHelper;
    private Account account;

    private FragmentAddPasswordBinding binding;

    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AddPasswordViewModel addPasswordViewModel =
                new ViewModelProvider(this).get(AddPasswordViewModel.class);

        binding = FragmentAddPasswordBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        initViews(root);
        initObjects();

        // save user data button
        final Button saveButton = binding.saveMaterialButton;
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // verify input fields and persist data to the SQLite Database
                verifyFromSQLite();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initViews(View view) {
        titleTextInputLayout = view.findViewById(R.id.titleTextInputLayout);
        accountTypeTextInputLayout = view.findViewById(R.id.accountTypeTextInputLayout);
        usernameTextInputLayout = view.findViewById(R.id.usernameTextInputLayout);
        passwordTextInputLayout = view.findViewById(R.id.passwordTextInputLayout);

        titleTextInputEditText = view.findViewById(R.id.titleTextInputEditText);
        accountTypeAutoCompleteTextView = view.findViewById(R.id.accountTypeAutoCompleteTextView);
        usernameTextInputEditText = view.findViewById(R.id.usernameTextInputEditText);
        passwordTextInputEditText = view.findViewById(R.id.passwordTextInputEditText);
    }

    private void initObjects() {
        inputValidation = new ValidationHelper(requireContext());
        databaseHelper = new DBPasswordHelper(requireContext());
        account = new Account();
    }

    private void verifyFromSQLite() {
        if (!inputValidation.isInputEditTextFilled(titleTextInputEditText, titleTextInputLayout, getString(R.string.error_message_title))) {
            return;
        }
        if (!inputValidation.isTextInputLayoutFilled(accountTypeAutoCompleteTextView, accountTypeTextInputLayout, getString(R.string.error_message_account_type))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(usernameTextInputEditText, usernameTextInputLayout, getString(R.string.error_message_username))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(passwordTextInputEditText, passwordTextInputLayout, getString(R.string.error_message_password))) {
            return;
        }

        account.setTitle(titleTextInputEditText.getText().toString().trim());
        account.setAccountType(accountTypeAutoCompleteTextView.getText().toString().trim());
        account.setUsername(usernameTextInputEditText.getText().toString().trim());
        account.setPassword(passwordTextInputEditText.getText().toString().trim());

        databaseHelper.addAccount(account);

        success();
    }

    private void success() {
        titleTextInputEditText.getText().clear();
        accountTypeAutoCompleteTextView.getText().clear();
        usernameTextInputEditText.getText().clear();
        passwordTextInputEditText.getText().clear();

        TastyToasty.success(getActivity(), "password added").show();

        root.requestFocus();
    }
}