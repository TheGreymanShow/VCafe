package thegreymanshow.vscanner;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

public class MainActivity extends AppCompatActivity {

    TextView rollno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rollno = (TextView) findViewById(R.id.rollno);

    }

    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.button2) {
            Intent intent = new Intent(getApplicationContext(), Scanner.class);
            startActivity(intent);
        }
    }
}
