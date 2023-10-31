package com.example.rahmatantravel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaCodec;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;



public class LoginActivityRevisi extends AppCompatActivity {

    private TextView txt_login;
    private LinearLayout btnUseEmail;
    private LinearLayout btnuseGoogle;
    private FirebaseAuth apa;
    private static final int status_sign_in = 123;

    private GoogleApiClient client;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_revisi);

        txt_login = findViewById(R.id.txt_login);

        btnUseEmail = findViewById(R.id.btnUseEmail);

        btnuseGoogle = findViewById(R.id.btnUseGoogle);

        apa = FirebaseAuth.getInstance();

        GoogleSignInAccount satu;
        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivityRevisi.this, LoginActivityRevisi2.class);
                startActivity(intent);
            }
        });



        GoogleSignInOptions gee = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.idsignin))
                .requestEmail()
                .build();
        client = new GoogleApiClient.Builder(this).enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
            @Override
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                Toast.makeText(getApplicationContext(),"Koneksi ke google gagal",Toast.LENGTH_SHORT).show();
            }
        }).addApi(Auth.GOOGLE_SIGN_IN_API,gee)
                .build();

        btnuseGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logindenganakungoogle();
            }
        });

        // Memulai proses login dengan Google
//        private void signInWithGoogle(satu) {
//            String googleIdToken = satu.getIdToken(); // Dapatkan Google ID Token
//            AuthCredential credential = GoogleAuthProvider.getCredential(googleIdToken, null);
//            mAuth.signInWithCredential(credential)
//                    .addOnCompleteListener(this, task -> {
//                        if (task.isSuccessful()) {
//                            // Login berhasil
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            // Lakukan tindakan setelah login berhasil
//                        } else {
//                            // Login gagal
//                            // Tindakan penanganan kesalahan
//                        }
//                    });
//        }
        btnUseEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivityRevisi.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void logindenganakungoogle() {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(client);
        startActivityForResult(intent,status_sign_in);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == status_sign_in) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()){
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthdengangoogle(account);
            } else {
                Toast.makeText(getApplicationContext(),"Koneksi ke firebase gagal",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthdengangoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
        apa.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = apa.getCurrentUser();
                    tampildialog();
                }
            }
        });
    }

    private void tampildialog(){
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
}