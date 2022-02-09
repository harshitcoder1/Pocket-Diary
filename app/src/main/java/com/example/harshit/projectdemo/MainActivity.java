package com.example.harshit.projectdemo;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Calendar c;
    FragmentManager fm;
    FragmentTransaction ft;
    SharedPreferences sp;
    SharedPreferences.Editor e;
    static MainActivity mainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm=getSupportFragmentManager();
        setFragmentA();
        Button btn1=findViewById(R.id.dp1);
        setDate(btn1,1);
        Button btn2=findViewById(R.id.dp2);
        setDate(btn2,2);
        mainActivity=this;
        RegisterActivity.getInstance().finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        try{
       findFragment();}catch(Exception e){/*Toast.makeText(this," "+e,Toast.LENGTH_LONG).show();*/}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.optionmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if(id==R.id.m1)
        {
            Intent intent=new Intent(this,AddTransaction.class);
            intent.putExtra("source","mainActivity");
            startActivity(intent);
            //Toast.makeText(this,"Add Transactions are selected",Toast.LENGTH_LONG).show();
        }
        if(id==R.id.m2)
        {
           // Toast.makeText(this,"About us is selected",Toast.LENGTH_LONG).show();
            AlertDialog.Builder ab= new AlertDialog.Builder(this);
            ab.setTitle("ABOUT US");
            //ab.setIcon(R.drawable.ic_action_name3);
            ab.setMessage("Created by Harshit Agrawal");
            ab.show();
        }
        if(id==R.id.m3) {
            try {
                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.setType("text/email");
                sendIntent.putExtra(android.content.Intent.EXTRA_EMAIL,new String[] {"harshit.agrawal.developer@gmail.com"});
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Email subject");
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Body of feedback Email");
                startActivity(Intent.createChooser(sendIntent, "Email:"));
                //Toast.makeText(this, "feedback is selected", Toast.LENGTH_LONG).show();
            } catch (Exception e) {Toast.makeText(this," "+e,Toast.LENGTH_LONG).show();
            }
        }
        if(id==R.id.m4) {
            try {
                 Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.setType("text/email");
                sendIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {"harshit.agrawal.developer@gmail.com"});
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Email subject");
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Body of help Email");
                startActivity(Intent.createChooser(sendIntent, "Choose app for Email:"));
                //Toast.makeText(this, "help is selected", Toast.LENGTH_LONG).show();
            } catch (Exception e) {Toast.makeText(this," "+e,Toast.LENGTH_LONG).show();
            }
        }
        if(id==R.id.m5) {
            try {
                Intent i = new Intent();
                i.setAction(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:9971363691"));
                startActivity(i);
                //Toast.makeText(this, "Contact us is selected", Toast.LENGTH_LONG).show();
            } catch (Exception e) {Toast.makeText(this," "+e,Toast.LENGTH_LONG).show();
            }
        }
        if(id==R.id.m6){
            try{
                Intent shareAppIntent = new Intent(Intent.ACTION_SEND);
                shareAppIntent.setType("text/plain");
                String shareMsg = "Pocket Diary will help you in managing your expenses and income";
                shareAppIntent.putExtra(Intent.EXTRA_SUBJECT,"Share Room Finder");
                shareAppIntent.putExtra(Intent.EXTRA_TEXT,shareMsg);
                startActivity(Intent.createChooser(shareAppIntent,"Share Via"));
            } catch (Exception e) {Toast.makeText(this," "+e,Toast.LENGTH_LONG).show();
            }
        }
        if(id==R.id.m7){
            RegisterActivity.getInstance().endSession();
            Toast.makeText(this,"you have successfully logged out",Toast.LENGTH_SHORT).show();
            finish();
        }
       if(id==R.id.m8) {
            try {
               Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
               intent.putExtra("source","mainActivity");
               startActivity(intent);
               //RegisterActivity.getInstance().setChangePassFrag();
            } catch (Exception e) {
                Toast.makeText(this, " " + e, Toast.LENGTH_LONG).show();
            }
        }
            return super.onOptionsItemSelected(item);
        }


    public void fun(View view)
    {
        Button b1=findViewById(R.id.expense1);
        Button b2=findViewById(R.id.expense2);

        Button b3=findViewById(R.id.income1);
        Button b4=findViewById(R.id.income2);

        Button b5=findViewById(R.id.summary1);
        Button b6=findViewById(R.id.summary2);

        if(view.getId()==R.id.expense1)
        {
            b1.setVisibility(View.GONE);
            b2.setVisibility(View.VISIBLE);
            b3.setVisibility(View.VISIBLE);
            b4.setVisibility(View.GONE);
            b5.setVisibility(View.VISIBLE);
            b6.setVisibility(View.GONE);
            setFragmentA();
        }

        else if(view.getId()==R.id.income1)
        {
            b1.setVisibility(View.VISIBLE);
            b2.setVisibility(View.GONE);
            b3.setVisibility(View.GONE);
            b4.setVisibility(View.VISIBLE);
            b5.setVisibility(View.VISIBLE);
            b6.setVisibility(View.GONE);
            setFragmentB();
        }

        else if(view.getId()==R.id.summary1)
        {
            b1.setVisibility(View.VISIBLE);
            b2.setVisibility(View.GONE);
            b3.setVisibility(View.VISIBLE);
            b4.setVisibility(View.GONE);
            b5.setVisibility(View.GONE);
            b6.setVisibility(View.VISIBLE);
            setFragmentC();
        }

    }
    public void selectDate1(View view)
    {
        c = Calendar.getInstance();
        DatePickerDialog datePickerDialog=new DatePickerDialog(this,date1,c.get(Calendar.YEAR),c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    DatePickerDialog.OnDateSetListener date1=new DatePickerDialog.OnDateSetListener()
    {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            Button btn=(Button)findViewById(R.id.dp1);
            String date;
            if(i1<9 && i2<10)
            {
                date=i+"-0"+(i1+1)+"-0"+i2;
            }
            else if(i1<9)
            {
                date=i+"-0"+(i1+1)+"-"+i2;
            }
            else if(i2<10)
            {
                date=i+"-"+(i1+1)+"-0"+i2;
            }
            else
            {
                date=i+"-"+(i1+1)+"-"+i2;
            }
            btn.setText(date);
            saveStartDate(date,MainActivity.this);
            findFragment();
    }
    };
    public void selectDate2(View view)
    {
        c = Calendar.getInstance();
        DatePickerDialog datePickerDialog=new DatePickerDialog(this,date2,c.get(Calendar.YEAR),c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    DatePickerDialog.OnDateSetListener date2=new DatePickerDialog.OnDateSetListener()
    {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            Button btn=(Button)findViewById(R.id.dp2);
            String date;
            if(i1<9 && i2<10)
            {
                date=i+"-0"+(i1+1)+"-0"+i2;
            }
            else if(i1<9)
            {
                date=i+"-0"+(i1+1)+"-"+i2;
            }
            else if(i2<10)
            {
                date=i+"-"+(i1+1)+"-0"+i2;
            }
            else
            {
                date=i+"-"+(i1+1)+"-"+i2;
            }
            btn.setText(date);
            saveEndDate(date,MainActivity.this);
            findFragment();
        }
    };

    public void setFragmentA()
    {
        ft=fm.beginTransaction();
        ft.replace(R.id.l3,new FragA(),"fraga");
        ft.commit();
    }
    public void setFragmentB()
    {
        ft=fm.beginTransaction();
        ft.replace(R.id.l3,new FragB(),"fragb");
        ft.commit();
    }
    public void setFragmentC()
    {
        ft=fm.beginTransaction();
        ft.replace(R.id.l3,new FragC(),"fragc");
        ft.commit();
    }

    public void findFragment()
    {
        Fragment fragA=fm.findFragmentByTag("fraga");
        if(fragA != null && fragA.isVisible())
        {
            setFragmentA();
        }
        Fragment fragB=fm.findFragmentByTag("fragb");
        if(fragB != null && fragB.isVisible())
        {
            setFragmentB();
        }
        Fragment fragC=fm.findFragmentByTag("fragc");
        if(fragC != null && fragC.isVisible())
        {
            setFragmentC();
        }
    }

    public void setDate(Button btn,int i)
    {
        String startDate = getStartDate(this);
        String endDate = getEndDate(this);
        c = Calendar.getInstance();
        int dd=c.get(Calendar.DAY_OF_MONTH);
        int mm=c.get(Calendar.MONTH)+1;
        int yy=c.get(Calendar.YEAR);
        String date;
        if(i==1)
        {
            if(startDate != null)
            btn.setText(startDate);
            else{
            if(mm<10)
                date=yy+"-0"+mm+"-"+"01";
            else
                date=yy+"-"+mm+"-"+"01";
            btn.setText(date);
            saveStartDate(date,this);}
        }
        else if(i==2)
        {
            if(endDate != null)
                btn.setText(endDate);
            else{
            if(mm<10)
                date=yy+"-0"+mm+"-"+"30";
            else
                date=yy+"-"+mm+"-"+"30";
            btn.setText(date);
            saveEndDate(date,this);}
        }
        else if(i==3)
        {
            if(mm<10 && dd<10)
                date=yy+"-0"+mm+"-0"+dd;
            else if(dd<10)
                date=yy+"-"+mm+"-0"+dd;
            else if(mm<10)
                date=yy+"-0"+mm+"-"+dd;
            else
                date=yy+"-"+mm+"-"+dd;
            btn.setText(date);
        }
    }

    public static MainActivity getInstance()
    {
        return mainActivity;
    }

    public static Context getContext(){return mainActivity;}

    public void saveStartDate(String date, Context context)
    {
        sp=context.getSharedPreferences("share_date",MODE_PRIVATE);
        e=sp.edit();
        e.putString("start_date",date);
        e.commit();
    }
    public void saveEndDate(String date,Context context)
    {
        sp=context.getSharedPreferences("share_date",MODE_PRIVATE);
        e=sp.edit();
        e.putString("end_date",date);
        e.commit();
    }
    public String getStartDate(Context context)
    {
        sp=context.getSharedPreferences("share_date",MODE_PRIVATE);
        return sp.getString("start_date",null);
    }
    public String getEndDate(Context context)
    {
        sp=context.getSharedPreferences("share_date",MODE_PRIVATE);
        return sp.getString("end_date",null);
    }
}
