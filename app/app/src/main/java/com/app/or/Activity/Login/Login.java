package com.app.or.Activity.Login;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.or.Activity.Main.MainActivity;
import com.app.or.Config.Universal;
import com.app.or.R;

public class Login extends AppCompatActivity {


    Button Login_bt;
    Button singUp_bt;

    TextView login_err;

    TextInputEditText input_id;
    TextInputEditText input_pw;
    TextView login_change_pw;
    int wrong = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_login);

        input_id = (TextInputEditText) findViewById(R.id.input_id);
        input_pw = (TextInputEditText) findViewById(R.id.input_pw);
        Login_bt = (Button) findViewById(R.id.Login_bt);
        singUp_bt = (Button) findViewById(R.id.singUp_bt);

        login_err = (TextView) findViewById(R.id.login_err);
        login_change_pw = (TextView) findViewById(R.id.login_change_pw);


        Login_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = Universal.NETWORK.Login(input_id.getText().toString(),input_pw.getText().toString());
                if(answer.equals("FALSE")){
                    wrong++;
                    login_err.setText("아이디 또는 비밀번호를 확인하세요 틀린 횟수 "+wrong+"/5");
                    login_change_pw.setVisibility(View.VISIBLE);
                }else if(answer.equals("TooManyWrong")){
                    login_err.setText("너무 많이 틀리셨습니다. 비밀번호를 변경 후 다시 시도하세요");
                    Login_bt.setBackground(getDrawable(R.drawable.login_button_disable));
                    Login_bt.setEnabled(false);
                    login_change_pw.setVisibility(View.VISIBLE);
                }else if(answer.equals("TRUE")){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        singUp_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SingUp.class);
                startActivityForResult(intent,100);
            }
        });

        login_change_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"터치 성공",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==100){
            if(resultCode==RESULT_OK){
                Universal.NETWORK.AutoLogin();
            }
        }
    }
}