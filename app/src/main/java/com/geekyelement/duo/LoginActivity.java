package com.geekyelement.duo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private TextView editEmail;
    private TextView editPassword;
    private ProgressBar centerLoading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button loginButton = findViewById(R.id.login);
        editEmail = findViewById(R.id.username);
        editPassword = findViewById(R.id.password);
        centerLoading = findViewById(R.id.loading);

        String mEmail=editEmail.getText().toString().trim();
        String mPass = editPassword.getText().toString().trim();

        if(!mEmail.isEmpty() && !mPass.isEmpty()){
            loginButton.setEnabled(true);
        }
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                centerLoading.setVisibility(View.VISIBLE);

                if (!mEmail.isEmpty() && !mPass.isEmpty()) {
                    Log.d("TAG", "onClick: " + "Clicked");
                    Intent i = new Intent(LoginActivity.this,Home.class);
                    startActivity(i);
//                    Login(mEmail, mPass);

                } else if (!mEmail.isEmpty()) {
                    displayDialog("Please insert password");
                } else if (!mPass.isEmpty()) {
                    displayDialog("Please insert Email");
                } else {
                    displayDialog("Please insert Email and password");
                }
                centerLoading.setVisibility(View.GONE);
            }

        });
    }
    public final void displayDialog(String str){

        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(LoginActivity.this,R.style.AlertDialog);
        builder.setMessage(str);
        androidx.appcompat.app.AlertDialog alertDialog=builder.create();
        alertDialog.show();
        alertDialog.getWindow().getWindowStyle();
    }
//    private void Login(final String email, final String password) {
//
//        dialog = new ProgressDialog(LoginActivity.this,R.style.AlertDialog);
//
//        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        dialog.setMessage("Logging In....");
//        dialog.show();
//
//
//        Log.d(TAG, "onResponse: login clicked");
//        final RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
//
//        JSONObject postparams = null;
//        try {
//            postparams = new JSONObject();
//            postparams.put("username", email);
//            postparams.put("password", password);
//        } catch (JSONException e) {
//            Log.d(TAG, "Login: Error:" + e);
//            Toast.makeText(getApplicationContext(), "Please try again", Toast.LENGTH_LONG).show();
//            dialog.dismiss();
//            onClickLogin.setEnabled(true);
//        }
//
//        final JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.GET, urlGet, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//
//                        String pk ;
//                        String dID;
//                        try {
//                            Log.e("Testing", "Get execution");
//                            JSONObject c = new JSONObject(String.valueOf(response));
//                            Log.d(TAG, "onResponse: " + c);
//                            JSONObject a = c.getJSONObject("user");
//                            Log.d(TAG, "onResponse: user:" + a);
//                            typeOfUser = a.getString("role");
//                            if(typeOfUser.equals("4")){
//                                try{
//                                    JSONObject dist = c.getJSONObject("district");
//                                    district = dist.getString("district");
//
//                                }catch (Exception e){
//                                    Log.d(TAG, "onResponse: DistrictError" + e);
//                                    e.printStackTrace();
//                                }
//                            }
//                            Name = a.getString("name");
//                            String number="Not Available",email="Not Available",address="Not Available",image=null;
//                            try{
//                                number = a.getString("phone_number");
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            try{
//                                email = a.getString("email");
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            try{
//                                image = a.getString("image");
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            try{
//                                JSONObject state= a.getJSONObject("state");
//                                address = state.getString("state");
//                                Log.e("Testing", "State received is " + address);
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            pk = a.getString("id");
//                            dID = c.getString("id");
//
//                            Log.d(TAG, "onResponse: valuepk"+pk);
//                            SharedPreferences.Editor editor = getSharedPreferences("tokenFile", Context.MODE_PRIVATE).edit();
//                            editor.putString("role", typeOfUser);
//                            editor.putString("Name", Name);
//                            editor.putString("id", pk);
//                            editor.putString("dID", dID);
//                            editor.putString("PhoneNumber",number);
//                            editor.putString("Email",email);
//                            editor.putString("Address",address);
//                            editor.putString("Image",image);
//                            editor.putString("district",district);
//                            editor.apply();
//
//                            Intent intent = null;
//
//                            getRadius(radiusUrl);
//
//                            if (typeOfUser.equals("1")) {
//                                Toast.makeText(LoginActivity.this, "login for farmer", Toast.LENGTH_SHORT).show();
//                            }else if (typeOfUser.equals("2")) {
//                                intent = new Intent(LoginActivity.this, Home.class);
////                                new Home(typeOfUser);
//                                startActivity(intent);
//                                finish();
//                            }else if (typeOfUser.equals("3")) {
//                                Toast.makeText(LoginActivity.this, "login for block admin", Toast.LENGTH_SHORT).show();
//                            }else if (typeOfUser.equals("4")) {
//                                onClickLogin.setEnabled(false);
//                                onClickLogin.setClickable(false);
////                                intent.putExtra("district",district);
//                                intent = new Intent(LoginActivity.this, Home.class);
//                                startActivity(intent);
//                                finish();
//                            }else if (typeOfUser.equals("5")) {
//                                intent = new Intent(LoginActivity.this,Home.class);
//                                startActivity(intent);
//                                finish();
//                            }else if (typeOfUser.equals("6")) {
//                                Toast.makeText(LoginActivity.this, "login for super admin", Toast.LENGTH_SHORT).show();
//                            } else {
//                                dialog.dismiss();
//                                //Toast.makeText(getApplicationContext(), "Invalid User", Toast.LENGTH_SHORT).show();
//                                displayDialog("Incorrect Password or Email");
//                            }
//
//
//                        } catch (JSONException e) {
//                            Log.d(TAG, "onResponse: error in get catch block :" + e.getMessage());
//                            //Toast.makeText(getApplicationContext(), "Please try again", Toast.LENGTH_SHORT).show();
//                            dialog.dismiss();
//                            displayDialog("Please try again");
//                            onClickLogin.setEnabled(true);
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        if (error instanceof NoConnectionError)
//                            noInternetDialog();
//                        else if (error instanceof ClientError)
//                            displayDialog("Invalid User!");
//                        else {
//                            Log.d(TAG,"error.networkResponse.toString()" + error.networkResponse.toString());
//                            displayDialog("Something went wrong,please try again");
//                        }
//                        onClickLogin.setEnabled(true);
//                        dialog.dismiss();
//                        Log.d(TAG, "onErrorResponse: some error in get: " + error.getLocalizedMessage());
//                    }
//                }) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> headers = new HashMap<>();
//                Log.d(TAG, "getHeaders: ");
//                headers.put("Authorization", "Token " + token);
//                Log.d(TAG,token);
//                return headers;
//            }
//        };
//
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urlPost, postparams,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//
//                        //retrieve the token from server
//                        try {
//                            JSONObject jsonObject = new JSONObject(String.valueOf(response));
//                            token = jsonObject.getString("key");
//                            SharedPreferences.Editor editor = getSharedPreferences("tokenFile", Context.MODE_PRIVATE).edit();
//                            editor.putString("key", token);
//                            editor.apply();
//                            MyRequestQueue.add(jsonObjectRequest1);
//                            Log.d(TAG, "onResponse: key:" + token);
//                        } catch (JSONException e) {
//                            dialog.dismiss();
//                            onClickLogin.setEnabled(true);
//                            Log.d(TAG, "onResponse: error in post catch block: " + e);
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        if (error instanceof NoConnectionError)
//                            noInternetDialog();
//                        else if (error instanceof ClientError)
//                            displayDialog("Incorrect Password or Email");
//                        else
//                            displayDialog("Something went wrong,please try again");
//                        Log.d(TAG, "onErrorResponse: invalid user : " + error);
//                        dialog.dismiss();
//                        onClickLogin.setEnabled(true);
//                    }
//                });
//
//        MyRequestQueue.add(jsonObjectRequest);
//
//        jsonObjectRequest.setRetryPolicy(new RetryPolicy() {
//            @Override
//            public int getCurrentTimeout() {
//                return 50000;
//            }
//
//            @Override
//            public int getCurrentRetryCount() {
//                return 50000;
//            }
//
//            @Override
//            public void retry(VolleyError error) throws VolleyError {
//
//            }
//        });
//
//
//
//
//
//    }
}
