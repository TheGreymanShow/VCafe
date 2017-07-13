package thegreymanshow.vscanner;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;

import java.util.ArrayList;

public class Checkout extends AppCompatActivity {
    String rollno;
    String total;
    StringBuilder sb=null;
    Context context;
    Activity activity;
    RadioButton chk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        rollno = getIntent().getStringExtra("roll");

        BackgroundTask backgroundTask = new BackgroundTask(Checkout.this);
        backgroundTask.execute(rollno,rollno,rollno,rollno);

    }

    public void onClick(View view) {
        BackgroundTask2 backgroundTask2 = new BackgroundTask2(Checkout.this);
        backgroundTask2.execute(rollno,"50");


    }

    public void onClick2(View view){

    }

}
