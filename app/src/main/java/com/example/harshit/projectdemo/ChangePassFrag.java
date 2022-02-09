package com.example.harshit.projectdemo;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ChangePassFrag extends Fragment {

    EditText oldpassword;
    EditText confirmpassword;
    EditText newpassword;
    TextView alertText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_change_pass, container, false);
        alertText=view.findViewById(R.id.alert);
        oldpassword=view.findViewById(R.id.oldpassword);
        newpassword=view.findViewById(R.id.newpassword);
        confirmpassword=view.findViewById(R.id.confirmpassword);
        Button reset=view.findViewById(R.id.submit);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String oldpassword1=oldpassword.getText().toString();
                String newpassword1=newpassword.getText().toString();
                String confirmpassword1=confirmpassword.getText().toString();
                String oldpassword2=RegisterActivity.getInstance().getPassword();
                if(oldpassword1.isEmpty() || newpassword1.isEmpty() || confirmpassword1.isEmpty())
                {
                    alertText.setVisibility(View.VISIBLE);
                    alertText.setText("please all the fields");
                }
                else if(oldpassword1.equals(oldpassword2))
                {
                    if(newpassword1.length()>=4){
                    if(newpassword1.equals(confirmpassword1))
                    {
                        RegisterActivity.getInstance().setPassword(newpassword1);
                        Toast.makeText(RegisterActivity.getContext(),"password successfully changed ",Toast.LENGTH_SHORT).show();
                        RegisterActivity.getInstance().finish();
                    }
                    else{
                        alertText.setVisibility(View.VISIBLE);
                        alertText.setText("you entered wrong password");
                    }
                    } else {
                        alertText.setVisibility(View.VISIBLE);
                        alertText.setText("password should have at least 4 characters");
                    }
                }
                else {
                    alertText.setVisibility(View.VISIBLE);
                    alertText.setText("you entered wrong old password");
                }
            }
        });

        return view;
    }

}
