package thegreymanshow.vscanner;

/**
 * Created by akshay dangare on 4/4/2017.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


public class BackgroundTask extends AsyncTask<String,Order,String> {

    Context context;
    Activity activity;
    RadioButton chk;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Order> arrayList = new ArrayList<>();

    public BackgroundTask(Context context) {
        this.context = context;
        activity = (Activity) context;
    }

    @Override
    protected void onPreExecute() {
        recyclerView = (RecyclerView) activity.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.hasFixedSize();
        adapter = new RecyclerAdapter(arrayList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected String doInBackground(String... params) {

        try {
                String roll = params[0];
                roll = params[1];
                roll = params[2];
                roll = params[3];
                StringBuilder sb;
                Log.w("JSON STRING",roll);
                URL url = new URL("http://192.168.1.3/vcafe/script.php?roll="+roll+"");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                StringBuilder stringBuilder= new StringBuilder();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                 stringBuilder.append(line+"\n");
                }
                httpURLConnection.disconnect();

                String json_string = stringBuilder.toString().trim();

                Log.w("JSON STRING",json_string);

                JSONObject jsonObject = new JSONObject(json_string);
                JSONArray jsonArray = jsonObject.getJSONArray("server_response");
                int count=0;

                while(count<jsonArray.length())
                {
                    JSONObject jo = jsonArray.getJSONObject(count);
                    count++;
                    Order order = new Order(jo.getString("Name"),jo.getInt("Quantity"),jo.getDouble("Cost"));
                    //total = (int) (total+ jo.getDouble("Cost"));
                    publishProgress(order);
                }


            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Order... values) {

        arrayList.add(values[0]);
        adapter.notifyDataSetChanged();
    }


    @Override
    protected void  onPostExecute(String json) {



    }

}

