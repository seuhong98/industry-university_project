package com.app.or.Activity.Main;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.or.Config.Universal;
import com.app.or.R;

import java.util.ArrayList;
import java.util.List;


public class ChangePwActivity extends AppCompatActivity {

    Button change_pw;
    Button backward_review;
    TextInputEditText change_pw_input;
    TextInputEditText change_pw_input2;

    TextView say;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_changepw);
        change_pw = findViewById(R.id.change_pw);
        change_pw_input = findViewById(R.id.change_pw_input);
        change_pw_input2 = findViewById(R.id.change_pw_input2);
        say = findViewById(R.id.say);

        backward_review = findViewById(R.id.backward_review);

        change_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(change_pw_input.length() < 7){
                    say.setText("8자 이상 입력하세요");
                }else if(!change_pw_input.getText().toString().equals(change_pw_input2.getText().toString())){
                    say.setText("재입력하신 비밀번호가 맞지 않습니다.");
                }else{
                    change_pw.setEnabled(false);
                    List<String> param = new ArrayList<>();
                    param.add(change_pw_input.getText().toString());
                    String[] read = Universal.NETWORK.Request("Login/SetPw",param);
                    if(read[0].equals("Done")){
                        Universal.fileSystem.DeleteAccount();
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(),"오류가 발생했습니다.",Toast.LENGTH_SHORT).show();
                        change_pw.setEnabled(true);
                    }
                }
            }
        });

        backward_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}