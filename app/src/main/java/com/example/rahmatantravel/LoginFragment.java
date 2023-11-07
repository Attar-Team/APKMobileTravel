package com.example.rahmatantravel;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.rahmatantravel.api.UserPostRequest;
import com.example.rahmatantravel.api.RetrofitClient;
import com.example.rahmatantravel.api.UserPostResponse;
import com.example.rahmatantravel.databinding.FragmentLoginBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        Button myButton = binding.loginButton;
        EditText usernameEditText = binding.username;
        EditText passwordEditText = binding.password;

        myButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            createPost(username, password);

            System.out.println("Request sent. Value " + username);
            System.out.println("Request sent. Value " + password);

        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void showDialogSuccess() {
        Dialog dialog = new Dialog(requireContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_login_success);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button button = dialog.findViewById(R.id.button);

        button.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), MainActivity.class);
            startActivity(intent);
            dialog.dismiss();
        });

        dialog.show();
    }

    private void showDialogFail() {
        Dialog dialog = new Dialog(requireContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_login_fail);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button button = dialog.findViewById(R.id.btnRepeat);

        button.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    private void createPost(String email, String password) {
        RetrofitClient.INSTANCE.getInstance().post(new UserPostRequest(email, password)).enqueue(new Callback<UserPostResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserPostResponse> call, @NonNull Response<UserPostResponse> response) {
                if (response.isSuccessful()) {
                    UserPostResponse userPostResponse = response.body();
                    if (userPostResponse != null && userPostResponse.getStatus().equals("success")) {

                        System.out.println("Request successful. Response: " + email);
                        System.out.println("Request successful. Response: " + password);
                        System.out.println("Request successful. Response: " + userPostResponse.getResponse());

                        showDialogSuccess();

                    } else {
                        System.out.println("Unexpected response: " + userPostResponse);
                        showDialogFail();
                    }
                } else {
                    System.out.println("Server error. Status code: " + response.code());
                    showDialogFail();
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserPostResponse> call, @NonNull Throwable t) {
                System.out.println("Request failed: " + t.getMessage());
                showDialogFail();
            }
        });
    }

}
