package com.app.or.Activity.Main;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.or.Activity.Login.Login;
import com.app.or.Activity.Review.DetailActivity;
import com.app.or.Activity.Review.ReviewWriteActivity;
import com.app.or.Config.Universal;
import com.app.or.DTO.SimpleReview;
import com.app.or.R;
import com.app.or.Universal.ListViewAdapter;

import java.util.ArrayList;
import java.util.List;


public class StettingActivity extends AppCompatActivity {

    Button setting_back;
    ListView setting_view ;
    ArrayAdapter<String> adapter;

    boolean safe = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_setting);
        setting_back = findViewById(R.id.setting_back);
        setting_view = findViewById(R.id.setting_view);
        setting_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        List<String> list = new ArrayList<>();
        list.add("내가 쓴 글 보기");
        list.add("내가 좋아요한 글 보기");
        list.add("비밀번호 변경");
        list.add("닉네임 변경");
        list.add("회원 탈퇴");
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        setting_view.setAdapter(adapter);

        setting_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    Intent intent = new Intent(getApplicationContext(),ShowSettingActivity.class);
                    intent.putExtra("code",true);
                    startActivity(intent);
                }else if(i==1){
                    Intent intent = new Intent(getApplicationContext(),ShowSettingActivity.class);
                    intent.putExtra("code",false);
                    startActivity(intent);
                }else if(i ==2){
                    Intent intent = new Intent(getApplicationContext(),ChangePwActivity.class);
                    startActivity(intent);
                }else if(i==3) {
                    Intent intent = new Intent(getApplicationContext(),ChangeNicknameActivity.class);
                    startActivity(intent);

                }else if(i==4){
                    if(safe){
                        Toast.makeText(getApplicationContext(),"정말 탈퇴 하시나요? 탈퇴하여도 작성하신 글은 삭제되지 않습니다.",Toast.LENGTH_SHORT).show();
                        safe = false;
                    }else{
                        List<String> params = new ArrayList<>();
                        params.add("last");
                        String[] answer= Universal.NETWORK.Request("Login/Resign",params);
                        if(answer[0].equals("GoodByeThankYou")){
                            Universal.fileSystem.DeleteAccount();
                            Universal.security.init();
                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(getApplicationContext(),"오류가 발생했습니다, 관리자에게 문의 하세요",Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            }
        });
    }


}