package com.example.harshit.projectdemo;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignInFrag extends Fragment {

    EditText password;
    TextView alertText;
    TextView forgotpass;
    Intent intent;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_sign_in, container, false);
        password=view.findViewById(R.id.password);
        alertText=view.findViewById(R.id.alert);
        forgotpass=view.findViewById(R.id.forgot);
        Button submit=view.findViewById(R.id.submit);
        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterActivity.getInstance().setForgotPassFrag();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String password1=password.getText().toString();
                if(!password1.isEmpty())
                {
                    String password2 = RegisterActivity.getInstance().getPassword();
                    if(password1.equals(password2))
                    {
                        intent=new Intent(RegisterActivity.getContext(),MainActivity.class);
                        RegisterActivity.getInstance().startSession();
                        Toast.makeText(RegisterActivity.getContext(),"you have successfully logged in",Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                    else
                    {
                        alertText.setVisibility(View.VISIBLE);
                        alertText.setText("you entered wrong password");
                    }
                }
                else
                {
                    alertText.setVisibility(View.VISIBLE);
                    alertText.setText("please enter the password");
                }
            }
        });
        return view;
    }

}
