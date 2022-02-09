package com.example.harshit.projectdemo;

import android.database.Cursor;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class FragC extends Fragment {
    int income1=0;
    int expenses1=0;
    int balance1=0;
    View  view;
    Cursor cursor;
    MyAdapter myAdapter;
@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    view=inflater.inflate(R.layout.fragment_frag_c, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            myAdapter = new MyAdapter(getActivity());
            cursor = myAdapter.getData1();
            while (cursor.moveToNext()) {
                expenses1 += cursor.getInt(2);
            }
            TextView expenses = (TextView) view.findViewById(R.id.totalexpenses);
            String expenses2=" "+expenses1+" ";
            expenses.setText(expenses2);
            cursor = myAdapter.getData2();
            while (cursor.moveToNext()) {
                income1 += cursor.getInt(2);
            }
            TextView income = (TextView) view.findViewById(R.id.totalincome);
            String income2=" "+income1+" ";
            income.setText(income2);
            TextView balance = (TextView) view.findViewById(R.id.balance);
            balance1 = income1 - expenses1;
            balance.setText(" "+balance1+" ");
        }catch (Exception e)
        {
            Toast.makeText(getActivity()," "+e,Toast.LENGTH_LONG).show();
        }
    }
}
