package com.example.rahmatantravel;

import static androidx.core.content.ContentProviderCompat.requireContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import com.example.projectpostapi.api.UserPostRequest;
import com.example.rahmatantravel.api.RetrofitClient;
import com.example.rahmatantravel.api.UserPostResponse;
import com.example.rahmatantravel.databinding.FragmentLoginBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class LoginActivityRevisi2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_revisi2);

        CardView myButton = findViewById(R.id.loginButton);
        EditText usernameEditText = findViewById(R.id.username);
        EditText passwordEditText = findViewById(R.id.password);
        TextView belumPunyaAkun = findViewById(R.id.belumPunyaAkun);

        belumPunyaAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivityRevisi2.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        myButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            createPost(username, password);

            System.out.println("Request sent. Value " + username);
            System.out.println("Request sent. Value " + password);

        });

    }

    private void showDialogSuccess() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_login_success);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button button = dialog.findViewById(R.id.button);

        button.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            dialog.dismiss();
        });

        dialog.show();
    }
    private void showDialogFail() {
        Dialog dialog = new Dialog(this);
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