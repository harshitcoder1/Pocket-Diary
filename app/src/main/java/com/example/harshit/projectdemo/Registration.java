package com.example.harshit.projectdemo;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Registration extends Fragment {

    EditText username;
    EditText password;
    EditText confirmpassword;
    AutoCompleteTextView securityques;
    EditText securityans;
    TextView alertText;
    ArrayAdapter arrayAdapter;
    String[] questions=new String[]{"what is your favourite book?","what is your favourite game?","who is your favourite singer?",
                                    "who is your favourite actor?","who is your favourite actress?"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_registration, container, false);
        arrayAdapter=new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,questions);
        username=view.findViewById(R.id.username);
        password=view.findViewById(R.id.password);
        confirmpassword=view.findViewById(R.id.confirmpassword);
        securityques=view.findViewById(R.id.securityquestion);
        securityques.setAdapter(arrayAdapter);
        securityques.setThreshold(0);
        securityans=view.findViewById(R.id.securityanswer);
        alertText=view.findViewById(R.id.alert);
        Button submit=view.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                    String username1=username.getText().toString();
                String password1=password.getText().toString();
                String confirmpassword1=confirmpassword.getText().toString();
                String securityques1=securityques.getText().toString();
                String securityans1=securityans.getText().toString();

                if(username1.isEmpty() || password1.isEmpty() || confirmpassword1.isEmpty() || securityans1.isEmpty() || securityques1.isEmpty()) {
                    alertText.setVisibility(View.VISIBLE);
                    alertText.setText("please fill all the fields");}
                else {
                    if(password1.length()>=4){
                    if (password1.equals(confirmpassword1)) {
                        RegisterActivity.getInstance().setUsername(username1);
                        RegisterActivity.getInstance().setPassword(password1);
                        RegisterActivity.getInstance().setSecurityques(securityques1);
                        RegisterActivity.getInstance().setSecurityans(securityans1);
                        RegisterActivity.getInstance().endSession();
                        RegisterActivity.getInstance().setSignInFrag();
                        Toast.makeText(RegisterActivity.getContext(),"you have successfully signed up",Toast.LENGTH_SHORT).show();
                        }
                     else {
                        alertText.setVisibility(View.VISIBLE);
                        alertText.setText("you entered wrong password");
                    }} else {
                        alertText.setVisibility(View.VISIBLE);
                        alertText.setText("password should have atleast 4 numbers");
                    }
                }
        }});
        return view;
    }
}
