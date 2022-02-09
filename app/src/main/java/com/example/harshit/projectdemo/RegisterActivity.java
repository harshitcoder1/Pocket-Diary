package com.example.harshit.projectdemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class RegisterActivity extends AppCompatActivity {

    static RegisterActivity registerActivity;
    SharedPreferences sp;
    SharedPreferences.Editor e;
    static final String USERDATA = "user_data";
    static final String USERNAME = "username";
    static final String PASSWORD = "password";
    static final String SECURITYQUES = "securityques";
    static final String SECURITYANS = "securityans";
    static final String SESSION="set_Session";
    static final String SESSIONKEY="session_status";
    static FragmentManager fm;
    static FragmentTransaction ft;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerActivity=this;
        fm=getSupportFragmentManager();
        setContentView(R.layout.activity_register);
        String password=getPassword();
        String checksession=getSession();
        intent=getIntent();
        String source=intent.getStringExtra("source");
        if(source != null && source.equals("mainActivity"))
        {
                setChangePassFrag();
        }
        else if(password != null)
        {
            if(checksession.equals("1"))
            {
                intent=new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
            }
            else
            {
                setSignInFrag();
            }
        }
        else
        {
            setRegistrationFragment();
        }
        //try{
        //}catch(Exception e){Toast.makeText(this,""+e,Toast.LENGTH_LONG).show();}
    }

    public void setUsername(String username) {
        sp = getSharedPreferences(USERDATA, MODE_PRIVATE);
        e = sp.edit();
        e.putString(USERNAME, username);
        e.commit();
    }

    public void setPassword(String password) {
        sp = getSharedPreferences(USERDATA, MODE_PRIVATE);
        e = sp.edit();
        e.putString(PASSWORD,password);
        e.commit();
    }
    public void setSecurityques(String securityques) {
        sp = getSharedPreferences(USERDATA, MODE_PRIVATE);
        e = sp.edit();
        e.putString(SECURITYQUES,securityques);
        e.commit();
    }
    public void setSecurityans(String securityans) {
        sp = getSharedPreferences(USERDATA, MODE_PRIVATE);
        e = sp.edit();
        e.putString(SECURITYANS,securityans);
        e.commit();
    }


    public String getPassword()
    {
        sp=getSharedPreferences(USERDATA,MODE_PRIVATE);
        return sp.getString(PASSWORD,null);
    }
    public String getSecurityques()
    {
        sp=getSharedPreferences(USERDATA,MODE_PRIVATE);
        return sp.getString(SECURITYQUES,null);
    }
    public String getSecurityans()
    {
        sp=getSharedPreferences(USERDATA,MODE_PRIVATE);
        return sp.getString(SECURITYANS,null);
    }
    public static RegisterActivity getInstance()
    {
        return registerActivity;
    }

    public static Context getContext()
    {
        return registerActivity;
    }

    public void setRegistrationFragment()
    {
        ft=fm.beginTransaction();
        ft.replace(R.id.root,new Registration());
        ft.commit();
    }

    public void setSignInFrag()
    {
        ft=fm.beginTransaction();
        ft.replace(R.id.root,new SignInFrag());
        ft.commit();
    }

    public void setForgotPassFrag()
    {
        ft=fm.beginTransaction();
        ft.replace(R.id.root,new ForgotPassFragment());
        ft.commit();
    }

    public void setResetPassFrag()
    {
        ft=fm.beginTransaction();
        ft.replace(R.id.root,new ResetPassFrag());
        ft.commit();
    }

    public void setChangePassFrag(){
        //MainActivity.getInstance().finish();
        ft=fm.beginTransaction();
        ft.replace(R.id.root,new ChangePassFrag(),"changefarg");
        ft.commit();
    }


    public void startSession()
    {
        sp=getSharedPreferences(SESSION,MODE_PRIVATE);
        e=sp.edit();
        e.putString(SESSIONKEY,"1");
        e.commit();
    }

    public void endSession()
    {
        sp=getSharedPreferences(SESSION,MODE_PRIVATE);
        e=sp.edit();
        e.putString(SESSIONKEY,"0");
        e.commit();
    }

    public String getSession()
    {
        sp=getSharedPreferences(SESSION,MODE_PRIVATE);
        return sp.getString(SESSIONKEY,null);
    }

}
