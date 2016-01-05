package com.mangnae.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.mangnae.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by pjm on 2015-12-31.
 */
public class LoginActivity extends Activity implements View.OnClickListener {

    private EditText edittext_id;
    private EditText edittext_pw;
    private CheckBox checkbox_idsave;
    private Button button_login;
    private Button button_join;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.login_activity);

        edittext_id = (EditText)findViewById(R.id.edittext_id);
        edittext_pw = (EditText)findViewById(R.id.edittext_pw);
        checkbox_idsave = (CheckBox)findViewById(R.id.checkbox_idsave);
        button_login = (Button)findViewById(R.id.button_login);
        button_join = (Button)findViewById(R.id.button_join);

        button_login.setOnClickListener(this);
        button_join.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == button_login){

            String strId = "";
            String strPwd = "";
            if(edittext_id != null){
                strId = edittext_id.getText().toString();
            }
            if(edittext_pw != null){
                strPwd = edittext_pw.getText().toString();
            }

            new LoginAsyncTask().execute(strId, strPwd);

        }
        else if (v == button_join) {

        }
    }

    private class LoginAsyncTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            // TODO Auto-generated method stub
            Log.d("MangNae", "doInBackground ");
            try{
                String strId = "";
                String strPwd = "";

                strId = params[0];
                strPwd = params[1];
                String body = "";
                body = "id=" + strId + "&pwd=" + strPwd;
                Log.d("MangNae", "body : " + body);

                //				URL u = new URL("http://10.131.153.38:8080/ContactBook/login.jsp");
                URL u = new URL("http://192.168.42.125:8080/ContactBook/login.jsp");
                HttpURLConnection huc = (HttpURLConnection) u.openConnection();
                huc.setRequestMethod("POST");
                huc.setDoInput(true);
                huc.setDoOutput(true);
                huc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                OutputStream os = huc.getOutputStream();
                os.write( body.getBytes("euc-kr") );
                os.flush();

                os.close();
                BufferedReader br = new BufferedReader( new InputStreamReader( huc.getInputStream(), "EUC-KR" ), huc.getContentLength() );

                String buf;
                String page = "";

                while( ( buf = br.readLine() ) != null ) {

                    //					System.out.println( buf );
                    page += buf;

                }

                Log.d("MangNae", "page : " + page);

                if("".equals(page)){ // 연결 실패

                    Toast.makeText(getApplicationContext(), "네트워크 상태 불량", Toast.LENGTH_SHORT).show();

                }
                else {		//연결 성공
                    Message msg = new Message();
                    msg.obj = page;
                    responseHandler.sendMessage(msg);

                }

                // 스트림을 닫는다.

                br.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }

            return null;

        }

    }
    /**
     * 응답 처리 핸들러
     */
    private Handler responseHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            String res = (String)msg.obj;

            Log.d("MangNae", "responseHandler res : " + res);

            try {
                JSONObject jObj = new JSONObject(res);

                if("Y".equals(jObj.getString("IsRight"))){					//로그인 성공
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                }
                else {														//로그인 실패


                    AlertDialog.Builder ab = new AlertDialog.Builder(LoginActivity.this);
                    ab.setTitle("로그인 실패");
                    ab.setMessage("실패!!");
                    ab.setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    if(edittext_pw != null)
                                        edittext_pw.setText("");
                                }
                            });
                    ab.show();

                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        }
    };
}
