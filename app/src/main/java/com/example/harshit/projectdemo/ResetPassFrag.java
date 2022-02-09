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

public class ResetPassFrag extends Fragment {

    EditText confirmpassword;
    EditText newpassword;
    TextView alertText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_reset_pass, container, false);
        alertText=view.findViewById(R.id.alert);
        newpassword=view.findViewById(R.id.newpassword);
        confirmpassword=view.findViewById(R.id.confirmpassword);
        Button reset=view.findViewById(R.id.submit);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newpassword1=newpassword.getText().toString();
                String confirmpassword1=confirmpassword.getText().toString();
                if(newpassword1.isEmpty()||confirmpassword1.isEmpty())
                {
                    alertText.setVisibility(View.VISIBLE);
                    alertText.setText("please fill both fields");
                }
                else if(newpassword1.length()>=4)
                {
                    if(newpassword1.equals(confirmpassword1))
                {
                    RegisterActivity.getInstance().startSession();
                    RegisterActivity.getInstance().setPassword(newpassword1);
                    Intent intent=new Intent(RegisterActivity.getInstance(),MainActivity.class);
                    Toast.makeText(RegisterActivity.getContext(),"password successfully reset",Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
                else
                {
                    alertText.setVisibility(View.VISIBLE);
                    alertText.setText("you entered wrong password");
                }
                } else {
                    alertText.setVisibility(View.VISIBLE);
                    alertText.setText("password should have at least 4 characters");
                }
            }
        });
        return view;
    }

}
