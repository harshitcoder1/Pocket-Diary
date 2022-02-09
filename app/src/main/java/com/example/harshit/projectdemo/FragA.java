package com.example.harshit.projectdemo;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FragA extends Fragment {

    Context context;
    View view;
    ListView listView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmenta, container, false);
        try {
            MyAdapter myAdapter = new MyAdapter(context);
            Cursor cursor = myAdapter.getData1();
            if (cursor == null || cursor.getCount() <= 0) {
                Toast.makeText(getActivity(), "you have no transaction in this range", Toast.LENGTH_LONG).show();

            } else {
                cursor.moveToFirst();
                String[] fieldsname = new String[]{myAdapter.ID, myAdapter.DATE, myAdapter.AMOUNT, myAdapter.DESCRIPTION};
                int[] views = new int[]{R.id.t1, R.id.t2, R.id.t3, R.id.t4};
                SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context, R.layout.my_layout, cursor, fieldsname, views, 0);
                listView = view.findViewById(R.id.listview1);
                listView.setAdapter(simpleCursorAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                                    @Override
                                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                                        TextView _id = (TextView) view.findViewById(R.id.t1);
                                                        TextView date = (TextView) view.findViewById(R.id.t2);
                                                        TextView description = (TextView) view.findViewById(R.id.t4);
                                                        TextView amount = (TextView) view.findViewById(R.id.t3);
                                                        String id = _id.getText().toString();
                                                        String transDate = date.getText().toString();
                                                        String desc = description.getText().toString();
                                                        String amount1 = amount.getText().toString();
                                                        Intent intent = new Intent(getActivity(), AddTransaction.class);
                                                        intent.putExtra("_id", id);
                                                        intent.putExtra("amount", amount1);
                                                        intent.putExtra("date", transDate);
                                                        intent.putExtra("description", desc);
                                                        intent.putExtra("source", "fragA");
                                                        startActivity(intent);
                                                    }
                                                }
                );
            }
        } catch (Exception e) {
            Toast.makeText(context, " " + e, Toast.LENGTH_LONG).show();
        }
        return view;
    }

}
