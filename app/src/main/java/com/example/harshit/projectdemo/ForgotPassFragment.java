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

public class ForgotPassFragment extends Fragment {

    AutoCompleteTextView securityques;
    EditText securityans;
    TextView alertText;
    ArrayAdapter arrayAdapter;
    String[] questions=new String[]{"what is your favourite book?","what is your favourite game?","who is your favourite singer?",
                                    "who is your favourite actor?","who is your favourite actress?"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_forgot_pass, container, false);
        arrayAdapter=new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,questions);
        alertText=view.findViewById(R.id.alert);
        securityques=view.findViewById(R.id.securityquestion);
        securityques.setAdapter(arrayAdapter);
        securityques.setThreshold(0);
        securityans=view.findViewById(R.id.securityanswer);
        Button reset=view.findViewById(R.id.submit);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String securityques1=securityques.getText().toString();
                String securityans1=securityans.getText().toString();
                String securityques2=RegisterActivity.getInstance().getSecurityques();
                String securityans2=RegisterActivity.getInstance().getSecurityans();
                if(securityans1.isEmpty()||securityques1.isEmpty())
                {
                    alertText.setVisibility(View.VISIBLE);
                    alertText.setText("please fill the both fields");
                }
                else if(securityans1.equals(securityans2)&&securityques1.equals(securityques2))
                {
                    RegisterActivity.getInstance().setResetPassFrag();
                }
                else
                {
                    alertText.setVisibility(View.VISIBLE);
                    alertText.setText("you entered either wrong security question or wrong security answer");
                }
            }
        });
        return view;
    }

}
