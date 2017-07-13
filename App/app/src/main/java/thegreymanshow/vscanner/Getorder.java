package thegreymanshow.vscanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

public class Getorder extends AppCompatActivity {

    TextView rollno;
    String roll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getorder);

        rollno = (TextView) findViewById(R.id.rollnum);
        Barcode barcode = getIntent().getParcelableExtra("barcode");
        roll = barcode.displayValue;
        rollno.setText("Roll no: "+roll);
    }

    public void onClick(View view){
        Intent intent = new Intent(getApplicationContext(),Checkout.class);
        intent.putExtra("roll",roll);
        startActivity(intent);
    }


}



