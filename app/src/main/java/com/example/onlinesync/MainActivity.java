package com.example.onlinesync;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.onlinesync.Server.MultipartUtility;
import com.example.onlinesync.Server.ServerConstants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> mylist = new ArrayList<String>();
    private InputStream is=null;
    private String result=null;
    private String line=null;
    boolean connected = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Toast.makeText(getApplicationContext(),"Test11111",Toast.LENGTH_LONG).show();
        OwnerDBAdapter ownerDBAdapter = new OwnerDBAdapter(getApplicationContext());
        Cursor cursor = ownerDBAdapter.getAllOwner();
//        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
//        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
//                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
//            //we are connected to a network
//            connected = true;
////            if(ownerList.size()!=0){
////                while (ownerDBAdapter.dbSyncCount()!=0){
////                    mylist.add(String.valueOf("1"));
////                    new Insertinfo().execute(mylist);
////                }
////            }
//
//        }
//        else
//            connected = false;

        ArrayList<OwnerListAd> ownerList=new ArrayList<OwnerListAd>();
        if (cursor.moveToFirst()) {
            do {
//                    RentDBAdapter rentDBAdapter = new RentDBAdapter(getApplicationContext());
//                    Cursor cursor1 = rentDBAdapter.getRent(cursor.getString(cursor.getColumnIndex(PropertyDBAdapter.KEY_ID)));
//                    String custname="";
//                    if (cursor1.moveToFirst()) {
//                        custname=  cursor1.getString(cursor1.getColumnIndex(RentDBAdapter.KEY_NAME));
//                    }
                Log.i("Name", cursor.getString(cursor.getColumnIndex(OwnerDBAdapter.OWNER_NAME)));
//                    Log.i("Type", cursor.getString(cursor.getColumnIndex(PropertyDBAdapter.KEY_Property_Type)));
//                    Log.i("ID", cursor.getString(cursor.getColumnIndex(PropertyDBAdapter.KEY_ID)));
//                    Log.i("Location", cursor.getString(cursor.getColumnIndex(PropertyDBAdapter.KEY_Property_Location)));
//                    Log.i("Remarks", cursor.getString(cursor.getColumnIndex(PropertyDBAdapter.KEY_Property_Remarks)));
//                    Log.i("Status", cursor.getString(cursor.getColumnIndex(PropertyDBAdapter.KEY_SYNCSTATUS)));
//                    Log.i("vacanr", cursor.getString(cursor.getColumnIndex(PropertyDBAdapter.KEY_PROPERTYVACANT)));
//                ownerList.add(new OwnerListAd("riiti"));
                ownerList.add(new OwnerListAd(cursor.getString(cursor.getColumnIndex(OwnerDBAdapter.OWNER_NAME))));
                String sync_data=cursor.getString(cursor.getColumnIndex(OwnerDBAdapter.SYCNEDDATA));
                if (sync_data.equals("0")) {
                    mylist.add(String.valueOf(cursor.getString(cursor.getColumnIndex(OwnerDBAdapter.OWNER_NAME))));
                    new Insertinfo().execute(mylist);
                    final boolean b = ownerDBAdapter.updateOwner(String.valueOf(cursor.getString(cursor.getColumnIndex(OwnerDBAdapter.OWNER_NAME))));
                }
//                OwnerIdArray.add(cursor.getString(cursor.getColumnIndex(OwnerDBAdapter.OWNER_ID)));
            } while (cursor.moveToNext());
        }

        OwnerListAdapter  adapter = new OwnerListAdapter(MainActivity.this, ownerList);
//	                    list.setAdapter( adapter );
        ListView proplist = (ListView) findViewById(R.id.owner_list);
        proplist.setAdapter(adapter);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListValue.class);
                //	            intent.putExtras(dataBundle);
//                startActivity(intent);
                startActivityForResult(intent, 2);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public class Insertinfo extends AsyncTask<ArrayList<String>, Void, String> {

        private ProgressDialog dialog = new ProgressDialog(MainActivity.this);

        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Please wait");
            this.dialog.show();
        }

        protected String doInBackground(ArrayList<String>... alldata) {

            ArrayList<String> passed = alldata[0]; //get passed arraylist
            String name = passed.get(0);
//            String batch_location = passed.get(1);
//            String batch_teacher = passed.get(2);
//            String batchtime = passed.get(3);
//            String batch_status = passed.get(4);
//            String maximumslot = passed.get(5);
//            // current time

            try {
                // Log.e(" setup Activity ", "  user detail to server " + " "+ SendName+" "+Sendmobile+" "+Checkgender);
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

//                nameValuePairs.add(new BasicNameValuePair("batch_name", batch_name ));
//                nameValuePairs.add(new BasicNameValuePair("batch_location", batch_location));
//                nameValuePairs.add(new BasicNameValuePair("batch_teacher",  batch_teacher));
//                nameValuePairs.add(new BasicNameValuePair("batchtime", batchtime));
//                nameValuePairs.add(new BasicNameValuePair("batch_status", batch_status));
                nameValuePairs.add(new BasicNameValuePair("name", name));
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://192.51.17.206/ds.accounts.mdi/api/loginphpfile.php?action=insert_name");
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs ,"UTF-8")); // UTF-8  support multi language
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
                is = entity.getContent();
                //     Log.e("pass 1", "connection success ");
            }
            catch(Exception e)
            {
                //  Log.e("Fail 1", e.toString());
                //  Log.d("setup Activity ","  fail 1  "+e.toString());
            }

            try
            {
                BufferedReader reader = new BufferedReader (new InputStreamReader(is,"iso-8859-1"),8);
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null)
                {
                    sb.append(line + "\n");
                }
                is.close();
                result = sb.toString();
                // Log.d("pass 2", "connection success " + result);
            }
            catch(Exception e)
            {
                //  Log.e("Fail 2", e.toString());
                //  Log.e("setup Activity  "," fail  2 "+ e.toString());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            //  Log.e(" setup acc ","  signup result  " + result);
            if (dialog.isShowing()) {
                dialog.dismiss();
            }

            try
            {

                JSONObject jObj = new JSONObject(result);
                String   myoutput = jObj.getString("batch_name");


//                Toast.makeText(getApplicationContext(),result.toString(),Toast.LENGTH_LONG).show();
//                JSONObject json_data = new JSONObject(result);
//               String code1=(json_data.getString("message"));
                Log.d("ritikk",result.toString());
//                if(myoutput.toString().equals("Save")){
//                    Intent i=new Intent(BatchFields.this,BatchList.class);
//                    startActivity(i);
//                }
                Toast.makeText(getApplicationContext(),myoutput,Toast.LENGTH_LONG).show();
//                Toast.makeText(getApplicationContext(),result.toString(),Toast.LENGTH_LONG).show();
//                JSONObject json_data = new JSONObject(result);
//               String code1=(json_data.getString("message"));
//                Log.i("query",result.toString());
//                Toast.makeText(getApplicationContext(),result.toString(),Toast.LENGTH_LONG).show();
//                //   Log.d("pass 3", "connection success " + result);
//                if(code==1)
//                {
//                    try {
////                        String Res_username =json_data.getString("Res_username");
////                        String Res_password =json_data.getString("Res_password");
////
////                        Intent nextScreen = new Intent(getApplicationContext(), HomeScreen.class);
////                        nextScreen.putExtra("username", Res_username );
////                        nextScreen.putExtra("password", Res_password);
////                        //Sending data to another Activity
////                        startActivity(nextScreen);
//
//                    }catch (Exception e){
//
//                    }
//                }


            }
            catch(Exception e)
            {
//                LoginError(" Network Problem , Please try again");
                //    Log.e("Fail 3 main result ", e.toString());
                // Log.d(" setup Activity "," fail 3 error - "+ result );
            }
        }

    }
}
