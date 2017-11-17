package com.example.android.stopwatch;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    Button start, pause, stop, lap;
    TextView textView, textView2, textView3, textView4;
    int milidetik=0, detik=0, menit=0, jam=0;
    ArrayAdapter<String> adapter ;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.tv_angka);
        textView2 = (TextView)findViewById(R.id.tv_angka2);
        textView3 = (TextView)findViewById(R.id.tv_angka3);
        textView4 = (TextView)findViewById(R.id.tv_angka4);
        start = (Button)findViewById(R.id.start);
        pause = (Button)findViewById(R.id.pause);
        stop = (Button)findViewById(R.id.stop);
        lap = (Button)findViewById(R.id.lap);
        ListView listView = (ListView)findViewById(R.id.list);
        String[] ListElements = new String[] {  };
        arrayList = new ArrayList<>(Arrays.asList(ListElements));
        adapter = new ArrayAdapter<String>(this, R.layout.list_view, R.id.tv_item,arrayList);
        listView.setAdapter(adapter);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.postDelayed(runnable, 100);
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacks(runnable);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacks(runnable);
                milidetik = 0;
                detik = 0;
                menit = 0;
                jam = 0;
                textView.setText(""+milidetik);
                textView2.setText(""+detik);
                textView3.setText(""+menit);
                textView4.setText(""+jam);
                arrayList.clear();
                adapter.notifyDataSetChanged();
            }
        });
        lap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newItem = textView.getText().toString();
                String newItem2 = textView2.getText().toString();
                String newItem3 = textView3.getText().toString();
                String newItem4 = textView4.getText().toString();
                String newItem5 = newItem4+":"+newItem3+":"+newItem2+":"+newItem;
                arrayList.add(newItem5);
                adapter.notifyDataSetChanged();
            }
        });

    }
    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            milidetik+=1;
            if(milidetik==10) {
                milidetik = 0;
                detik += 1;
                textView.setText(Integer.toString(milidetik));
                textView2.setText(Integer.toString(detik));
            }else if(detik==60){
                detik=0;
                menit+=1;
                textView.setText(Integer.toString(milidetik));
                textView2.setText(Integer.toString(detik));
                textView3.setText(Integer.toString(menit));
            }else if(menit==60){
                menit=0;
                jam+=1;
                textView.setText(Integer.toString(milidetik));
                textView2.setText(Integer.toString(detik));
                textView3.setText(Integer.toString(menit));
                textView4.setText(Integer.toString(jam));
            }
            else
            textView.setText(Integer.toString(milidetik));

            handler.postDelayed(this, 100);
        }
    };
}
