    package com.example.jpveira.conversormoedas;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

    public class MainActivity extends AppCompatActivity implements  View.OnClickListener {
    Double value;
    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mViewHolder.editValue = (EditText) findViewById(R.id.edit_value);
        this.mViewHolder.textDolar = (TextView) findViewById(R.id.text_valor_dolar);
        this.mViewHolder.textEuro = (TextView) findViewById(R.id.text_valor_euro);
        this.mViewHolder.buttonCalculate = (Button) findViewById(R.id.calcular);
        this.mViewHolder.buttonCalculate.setOnClickListener(this);

    }
    private static class ViewHolder {
        EditText editValue;
        TextView textDolar;
        TextView textEuro;
        Button buttonCalculate;

    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.calcular){


            try {

                Boolean flag=new RetrieveFeedTask().isInternetAvailable(this);
                Log.e("Error:",flag.toString());
                if(flag==true)
                {
                    if(mViewHolder.editValue.getText().toString().equals(""))
                        mViewHolder.editValue.setText("1");
                    String result = new RetrieveFeedTask().execute(mViewHolder.editValue.getText().toString(), "BRL").get();
                    mViewHolder.textDolar.setText(result);
                }
                else
                {
                    AlertDialog.Builder alertBox = new AlertDialog.Builder(this);
                    alertBox.setTitle("Error");
                    alertBox.setMessage("Device is not connected to the internet!");
                    alertBox.setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) { }
                    });
                    alertBox.create();
                    alertBox.show();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


            value = Double.valueOf(this.mViewHolder.editValue.getText().toString());
            //this.mViewHolder.textDolar.setText(String.format("%.2f", value*3));
            //this.mViewHolder.textEuro.setText(String.format("%.2f", value*4));
        }
    }

    public boolean isInternetAvailable(Context mContext) {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
    class RetrieveFeedTask extends AsyncTask<String, String, String> {


        private Exception exception;
        private Context applicationContext;

        protected void onPreExecute() { }

        protected String doInBackground(String... args) {
            //Calling the web Api to get rates!
            try {
                String output=null;
                URL url = new URL("http://data.fixer.io/api/latest?access_key=e6c0975cbf36ea5cba5017846edc3095");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try
                {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();

                    if (stringBuilder.toString() != null)
                    {
                        try
                        {
                            JSONObject jobj=new JSONObject(stringBuilder.toString());
                            JSONObject rates= (JSONObject) jobj.get("rates");
                            output=rates.getString(args[1]);

                            Double x=Double.parseDouble(output);
                            Double convertedValue=x*(Integer.parseInt(args[0]));

                            output=Double.toString(convertedValue);
                        }
                        catch (Exception e)
                        {
                            Log.e("Exception Caught:",e.toString());
                        }

                    }
                    else
                    {
                        Log.e("Error:","The web API did not send any data");
                    }
                    return output;
                }
                finally
                {
                    urlConnection.disconnect();
                }
            }
            catch (Exception e)
            {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }


        }
        public boolean isInternetAvailable(Context mContext) {
            ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            return netInfo != null && netInfo.isConnectedOrConnecting();
        }

        protected void onPostExecute(String response) { }

    }