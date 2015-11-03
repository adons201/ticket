package com.hardboy.tobuyamovieticket;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
/**
 * Created by Vladimir on 30.10.2015.
 */
public class request_net {
    Context context;

    String message="";

    public request_net(Context context){
        this.context = context;
    }

    public void get_Token_now(String login, String pass){
        new get_Token().execute(login,pass);
    }

    public void get_Token_complete(boolean result){

    }

    public class get_Token extends AsyncTask<String, Void, String> {



        @Override
        protected void onPreExecute(){
            Log.i("my_application", "onPreExecute");
        }

        protected String doInBackground(String... txt) {
            // Загрузка данных из сети
            HttpParams httpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, 500);
            HttpConnectionParams.setSoTimeout(httpParams, 500);
            HttpParams p = new BasicHttpParams();
            p.setParameter("Content-Type", "application/json");
            HttpClient httpclient = new DefaultHttpClient(p);

            String url = "http://birbrover.gq/myfilms.json";
            Log.i("my_application", "URL - " + url);

            HttpGet httppost = new HttpGet(url);
            try {
                HttpResponse responseGet = httpclient.execute(httppost);
                HttpEntity resEntityGet = responseGet.getEntity();
                String str = "";
                if (resEntityGet != null) {
                    str = str + EntityUtils.toString(resEntityGet, "UTF8");
                    message=str;
                    Log.i("MyApp", "Bonus str - "+str);
                    return str;
                }
                else
                {
                    Log.i("BookMrkt","#REGERR: err_reg - " + str);
                    return null;
                }
            }  catch (Exception e) {
                Log.i("my_application", "#ERR_NET:"+e.getMessage());
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String input) {
            boolean result = false;

            get_Token_complete(result);
        }

    }
}
