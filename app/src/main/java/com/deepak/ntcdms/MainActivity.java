package com.deepak.ntcdms;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText usernameET;
    EditText passwordET;
    public static String user;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        firebaseAuth = FirebaseAuth.getInstance();
        usernameET = (EditText) findViewById(R.id.editText1);
        passwordET = (EditText) findViewById(R.id.editText2);
        //passwordET.setTransformationMethod(new AsteriskPasswordTransformationMethod());

    }

    public void SignIn(View view) {

        String username = usernameET.getText().toString();
        String password = passwordET.getText().toString();
        user = username;
        firebaseAuth.signInWithEmailAndPassword(username, password)
                    .addOnCompleteListener(this, new OnCompleteListener<com.google.firebase.auth.AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<com.google.firebase.auth.AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                                Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(i);

                            }
                            else
                                Toast.makeText(getApplicationContext(), "Invalid Login", Toast.LENGTH_SHORT).show();
                        }
                    });

    }
    public void passwordforget(View view){
        Intent i = new Intent(this , ForgotPasswordActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }
}