package thegreymanshow.vscanner;

/**
 * Created by akshay dangare on 4/6/2017.
 */

    import android.app.Activity;
    import android.app.ProgressDialog;
    import android.content.Context;
    import android.content.DialogInterface;
    import android.os.AsyncTask;
    import android.support.v7.app.AlertDialog;
    import android.util.Log;

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


public class BackgroundTask2 extends AsyncTask<String,Void,String> {

    String Register_url = "http://192.168.1.3/vcafe/confirm.php";
    Context context;
    Activity activity;
    AlertDialog.Builder builder;
    ProgressDialog progressDialog;
    String msg = "Payment Successful. Thank you for ordering through Vcafe. Enjoy your meal.";
    String msg2 = "Oops ! You don't have enough balance in your account. Please recharge now to continue using Vcafe.";

    public BackgroundTask2(Context context) {
        this.context = context;
        activity = (Activity) context;
    }

    @Override
    protected void onPreExecute() {
        builder = new AlertDialog.Builder(activity);
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Placing Order");
        progressDialog.setMessage("Please wait while your order is being processed");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {

        try {
                String rollno = params[0];
                String total = params[1];

                URL url = new URL(Register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                // send data to the server
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));


                String data = URLEncoder.encode("rollno", "UTF-8") + "=" + URLEncoder.encode(rollno, "UTF-8") + "&" +
                        URLEncoder.encode("total", "UTF-8") + "=" + URLEncoder.encode(total, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                // Capture the response from server
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String stringBuilder="";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder+=line;
                }
                httpURLConnection.disconnect();
                Thread.sleep(2000);
                String json_string = stringBuilder.toString().trim();
                Log.w("JSON STRING",json_string);
                return stringBuilder;

            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


    @Override
    protected void  onPostExecute(String json) {

        try {
            progressDialog.dismiss();
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("server_response");
            JSONObject JO = jsonArray.getJSONObject(0);
            String code1 = JO.getString("code");

            if (code1.equals("Reg_true")) {
                builder.setTitle("Success");
                builder.setMessage(msg);
                builder.setPositiveButton("EXIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        activity.finish();
                    }
                });

                AlertDialog alertDialog1 = builder.create();
                alertDialog1.show();


            } else if (code1.equals("Reg_false")) {
                builder.setTitle("Failed");
                builder.setMessage(msg2);
                builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        activity.finish();
                    }
                });
                AlertDialog alertDialog1 = builder.create();
                alertDialog1.show();

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}


