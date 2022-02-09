package com.example.harshit.projectdemo;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Calendar;

public class AddTransaction extends AppCompatActivity  {

    Calendar c;
    Button transactionDate;
    EditText setamount;
    EditText setdesc;
    RadioButton expenses;
    RadioButton income;
    Intent intent;
    String _id;
    MyAdapter myAdapter;
    String source;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_transaction);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        myAdapter=new MyAdapter(this);
        transactionDate=(Button)findViewById(R.id.b2);
        setamount=(EditText)findViewById(R.id.et1);
        setdesc=(EditText)findViewById(R.id.et2);
        expenses=(RadioButton)findViewById(R.id.rb1);
        income=(RadioButton)findViewById(R.id.rb2);
        intent=this.getIntent();
        if(intent != null)
        {
            source = intent.getStringExtra("source");
            if (source.equals("fragA") || source.equals("fragB"))
            {
                _id = intent.getStringExtra("_id");
                id=Integer.parseInt(_id);
                String amount = intent.getStringExtra("amount");
                String date = intent.getStringExtra("date");
                String desc = intent.getStringExtra("description");
                transactionDate.setText(date);
                setamount.setText(amount);
                setdesc.setText(desc);
                if(source.equals("fragA"))
                expenses.setChecked(true);
                else
                income.setChecked(true);
                ab.setTitle("existing Transaction");
            }
            else
            {
                MainActivity.getInstance().setDate(transactionDate, 3);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.addtransactionsmenus,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onPrepareOptionsMenu(Menu menu)
    {
        MenuItem delete = menu.findItem(R.id.deletetrans);
        if(source.equals("mainActivity"))
        {
            delete.setVisible(false);
        }
        else
        {
            delete.setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int itemId = item.getItemId();
        if(itemId == R.id.addtrans)
        {
            String date1=transactionDate.getText().toString();
            String desc=setdesc.getText().toString();
            String amount2=setamount.getText().toString();
            try{
                if(amount2.isEmpty())
                {
                    Toast.makeText(this, " please enter the amount ", Toast.LENGTH_LONG).show();
                }
                else
                {
                    int amount1= Integer.parseInt(amount2);
                    if(amount1>10000000)
                    {
                        Toast.makeText(this," the amount should be less than 10000000 ",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        if(expenses.isChecked())
                        {
                            if(source.equals("mainActivity"))
                                myAdapter.insertData1(date1,amount1,desc);
                            else if(source.equals("fragA"))
                                myAdapter.updateData1(id,date1,amount1,desc);
                            else if(source.equals("fragB"))
                            {
                                myAdapter.insertData1(date1,amount1,desc);
                                myAdapter.deleteData2(id);
                            }
                            finish();
                        }
                        else if(income.isChecked()) {
                            if (source.equals("mainActivity"))
                                myAdapter.insertData2(date1, amount1, desc);
                            else if (source.equals("fragB"))
                                myAdapter.updateData2(id, date1, amount1, desc);
                            else if (source.equals("fragA"))
                            {
                                myAdapter.insertData2(date1,amount1,desc);
                                myAdapter.deleteData1(id);
                            }
                            finish();
                        }
                        else
                        {
                            Toast.makeText(this," please mention the type of transaction ",Toast.LENGTH_LONG).show();
                        }
                    }
                }}catch (Exception e)
            {
                Toast.makeText(this," "+e,Toast.LENGTH_LONG).show();
            }
        }
        if(itemId == R.id.deletetrans)
        {
            AlertDialog.Builder ab = new AlertDialog.Builder(this);
            ab.setTitle("ALERT");
            ab.setIcon(R.drawable.ic_action_name3);
            ab.setMessage("Do you really want delete ?");
            ab.setNeutralButton("CANCEL",null);
            ab.setPositiveButton("YES", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    try{
                        if(expenses.isChecked())
                        {
                            myAdapter.deleteData1(id);
                            finish();
                        }
                        else if(income.isChecked())
                        {
                            myAdapter.deleteData2(id);
                            finish();
                        }
                        else
                            Toast.makeText(AddTransaction.this,"please mention the type of transaction ",Toast.LENGTH_LONG).show();
                    }catch (Exception e){Toast.makeText(AddTransaction.this," "+e,Toast.LENGTH_LONG).show();}
                }
                });
            ab.setCancelable(false);
            ab.show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void selectDate3(View view)
    {
        c = Calendar.getInstance();
        DatePickerDialog datePickerDialog=new DatePickerDialog(this,date3,c.get(Calendar.YEAR),c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    DatePickerDialog.OnDateSetListener date3=new DatePickerDialog.OnDateSetListener()
    {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            Button btn=(Button)findViewById(R.id.b2);
            if(i1<9 && i2<10)
            {
                String date=i+"-0"+(i1+1)+"-0"+i2;
                btn.setText(date);
            }
            else if(i1<9)
            {
                String date=i+"-0"+(i1+1)+"-"+i2;
                btn.setText(date);
            }
            else if(i2<10)
            {
                String date=i+"-"+(i1+1)+"-0"+i2;
                btn.setText(date);
            }
            else
            {
                String date=i+"-"+(i1+1)+"-"+i2;
                btn.setText(date);
            }
        }
    };

}

/**/