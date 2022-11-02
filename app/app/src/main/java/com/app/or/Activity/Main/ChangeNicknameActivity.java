package com.app.or.Activity.Main;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.or.Config.Universal;
import com.app.or.R;

import java.util.ArrayList;
import java.util.List;


public class ChangeNicknameActivity extends AppCompatActivity {

    Button change_nn;
    Button backward_review;
    TextInputEditText check_nickname;

    TextView say;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_changenickname);
        change_nn = findViewById(R.id.change_nn);
        check_nickname = findViewById(R.id.check_nickname);
        say = findViewById(R.id.say);
        backward_review = findViewById(R.id.backward_review);

        change_nn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_nn.setEnabled(false);
                List<String> t= new ArrayList<>();
                t.add(check_nickname.getText().toString());
                String[] get = Universal.NETWORK.Request("Login/SetNickName",t);
                if(get[0].equals("Exist")){
                    say.setText("존재하는 닉네임 입니다.");
                    change_nn.setEnabled(true);
                }else if(get[0].equals("Change")){
                    Toast.makeText(getApplicationContext(),"변경했습니다.",Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    change_nn.setEnabled(true);
                    Toast.makeText(getApplicationContext(),"오류가 발생 했습니다.",Toast.LENGTH_SHORT).show();
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