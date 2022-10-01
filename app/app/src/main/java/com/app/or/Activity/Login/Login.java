package com.app.or.Activity.Login;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.or.Activity.Main.MainActivity;
import com.app.or.Config.Universal;
import com.app.or.R;
import com.app.or.Universal.HttpsHelper;

public class Login extends AppCompatActivity {


    Button Login_Submit;
    Button Login_SingUp;

    TextInputEditText Login_id;
    EditText Login_Password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_login);

        //맵핑 과정
        Login_id = (TextInputEditText) findViewById(R.id.Login_id);
        Login_Password = (EditText) findViewById(R.id.Login_Password);
        Login_Submit = (Button) findViewById(R.id.Login_Submit);
        Login_SingUp = (Button) findViewById(R.id.Login_SingUp);
        //테스트용
        Button Test = (Button) findViewById(R.id.Test);
        //여기서부터 기본 로직 생성

        Login_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo 로그인 요청을 하는 동안 페이지 동작 1.로딩중이라는것 보여주기 2.결과 보여주기
                String answer = Universal.httpsHelper.Login(Login_id.getText().toString(),Login_Password.getText().toString());
                if(answer.equals("True")){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }else{

                    AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(Login.this);
                    myAlertBuilder.setPositiveButton("Ok",new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog,int which){
                            // OK 버튼을 눌렸을 경우
                            Toast.makeText(getApplicationContext(),"Pressed OK",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                    if(answer.equals("ERR")){
                        myAlertBuilder.setTitle("로그인 실패");
                        myAlertBuilder.setMessage("로그인 중 오류가 발생 했습니다.");
                        myAlertBuilder.show();
                    }else if(answer.equals("FALSE")){
                        myAlertBuilder.setTitle("로그인 실패");
                        myAlertBuilder.setMessage("아이디나 비밀번호가 틀렸습니다.");
                        myAlertBuilder.show();
                    }else if(answer.equals("TooManyWrong")){
                        myAlertBuilder.setTitle("로그인 실패");
                        myAlertBuilder.setMessage("너무 많이 실패 하셨습니다. 잠시 후 시도해주세요.");
                        myAlertBuilder.show();
                    }else{
                        myAlertBuilder.setTitle("로그인 실패");
                        myAlertBuilder.setMessage("알 수 없는 오류가 발생 했습니다. 고객센터에 문의 해주세요");
                        myAlertBuilder.show();
                    }
                }
            }
        });

        //todo 회원가입 페이지로 이동하는 로직 추가 해야함


    }
}