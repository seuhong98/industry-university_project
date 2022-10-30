package com.app.or.Activity.Login;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.or.Config.Universal;
import com.app.or.R;

import java.util.ArrayList;
import java.util.List;

public class SingUp extends AppCompatActivity {

    Button backward_signin;
    Button check_email;
    Button check_email_code_bt;
    Button check_nickname_bt;
    Button signup_bt;

    TextInputEditText signup_email;
    TextInputEditText check_email_code;
    TextInputEditText check_nickname;
    TextInputEditText signup_pw;
    TextInputEditText signup_pw_check;

    TextView check_email_txt;
    TextView check_nickname_txt;
    TextView use_uni;

    CheckBox agreeAll;

    CheckBox prime_agree;
    CheckBox security_agree;
    CheckBox marketing_agree;
    CheckBox age_agree;

    LinearLayout use_uni_box;

    boolean isCheckEmail = false;
    boolean isCheckNickname = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signin);
        backward_signin = (Button) findViewById(R.id.backward_signin);
        check_email = (Button) findViewById(R.id.check_email);
        check_email_code_bt = (Button) findViewById(R.id.check_email_code_bt);
        check_nickname_bt = (Button) findViewById(R.id.check_nickname_bt);
        signup_bt = (Button) findViewById(R.id.signup_bt);

        signup_email = (TextInputEditText) findViewById(R.id.signup_email);
        check_email_code = (TextInputEditText) findViewById(R.id.check_email_code);
        check_nickname = (TextInputEditText) findViewById(R.id.check_nickname);
        signup_pw = (TextInputEditText) findViewById(R.id.signup_pw);
        signup_pw_check = (TextInputEditText) findViewById(R.id.signup_pw_check);

        check_email_txt = (TextView) findViewById(R.id.check_email_txt);
        check_nickname_txt = (TextView) findViewById(R.id.check_nickname_txt);
        use_uni = (TextView) findViewById(R.id.use_uni);

        agreeAll = (CheckBox) findViewById(R.id.agreeAll);
        prime_agree = (CheckBox) findViewById(R.id.prime_agree);
        security_agree = (CheckBox) findViewById(R.id.security_agree);
        marketing_agree = (CheckBox) findViewById(R.id.marketing_agree);
        age_agree = (CheckBox) findViewById(R.id.age_agree);

        use_uni_box = (LinearLayout) findViewById(R.id.use_uni_box);
        //맵핑 완료


        /**
         * 뒤로가기 버튼
         */
        backward_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        /**
         * 이메일 입력
         */
        check_email.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                check_email.setEnabled(false);
                String email = signup_email.getText().toString();
                if(email.contains("@")){
                    Integer read = Universal.memory.getUniCode(email.split("@")[1]);
                    if(read != null){
                        List<String> parms = new ArrayList<>();
                        parms.add(email);
                        parms.add(read+"");
                        String[] answer = Universal.NETWORK.Request("signup/mail",parms);
                        if(answer != null && answer[0].equals("Success")){
                            use_uni_box.setVisibility(View.GONE);
                            check_email_code_bt.setBackground(getDrawable(R.drawable.check_code));
                            check_email_code_bt.setEnabled(true);
                            signup_email.setEnabled(false);
                            check_email.setEnabled(false);
                            check_email.setBackground(getDrawable(R.drawable.send_email_none));
                            use_uni.setText("인증번호를 입력해주세요");
                            use_uni.setTextColor(getColor(R.color.main_color));
                            use_uni_box.setVisibility(View.VISIBLE);
                        }else if(answer != null && answer[0].equals("Use_Email")){
                            use_uni.setText("이미 사용중인 이메일 입니다");
                            use_uni.setTextColor(getColor(R.color.warn_red));
                            use_uni_box.setVisibility(View.VISIBLE);
                            check_email.setEnabled(true);
                        }else{
                            use_uni.setText("이메일 인증에 오류가 발생 했습니다");
                            use_uni.setTextColor(getColor(R.color.warn_red));
                            use_uni_box.setVisibility(View.VISIBLE);
                            check_email.setEnabled(true);
                        }
                    }else {
                        use_uni.setText("대학교 이메일을 사용해주세요");
                        use_uni.setTextColor(getColor(R.color.warn_red));
                        use_uni_box.setVisibility(View.VISIBLE);
                        check_email.setEnabled(true);
                    }
                }else{
                    use_uni.setText("이메일을 입력해주세요");
                    use_uni.setTextColor(getColor(R.color.warn_red));
                    use_uni_box.setVisibility(View.VISIBLE);
                    check_email.setEnabled(true);
                }
            }
        });

        /**
         * 인증번호 보내기
         */
        check_email_code_bt.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                check_email_code_bt.setEnabled(false);
                String code = check_email_code.getText().toString();
                List<String> parms = new ArrayList<>();
                parms.add(code);
                String[] answer = Universal.NETWORK.Request("signup/mailResponse",parms);
                if(answer != null && answer[0].equals("TRUE")){
                    check_email_txt.setText("인증에 성공 했습니다.");
                    check_email_txt.setTextColor(getColor(R.color.main_color));
                    isCheckEmail= true;
                    check_email_code.setEnabled(false);
                    if(CheckAllWrite()){
                        signup_bt.setEnabled(true);
                        signup_bt.setBackground(getDrawable(R.drawable.signup_bt));
                    }else {
                        signup_bt.setEnabled(false);
                        signup_bt.setBackground(getDrawable(R.drawable.signup_bt_none));
                    }
                }else if(answer != null && answer[0].equals("FALSE")){
                    check_email_txt.setText("인증에 실패 했습니다.");
                    check_email_txt.setTextColor(getColor(R.color.warn_red));
                    isCheckEmail=false;
                    check_email_code_bt.setEnabled(true);
                }else{
                    check_email_txt.setText("오류가 발생 했습니다.");
                    check_email_txt.setTextColor(getColor(R.color.warn_red));
                    isCheckEmail=false;
                    check_email_code_bt.setEnabled(true);
                }
            }
        });

        /**
         * 닉네임 인증
         */
        check_nickname_bt.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                String nickname = check_nickname.getText().toString();
                List<String> parms = new ArrayList<>();
                parms.add(nickname);
                String[] answer = Universal.NETWORK.Request("signup/checkNickname",parms);
                if(answer != null && answer[0].equals("TRUE")){
                    check_nickname_txt.setText("사용 가능한 닉네임 입니다");
                    check_nickname_txt.setTextColor(getColor(R.color.main_color));
                    isCheckNickname = true;
                    if(CheckAllWrite()){
                        signup_bt.setEnabled(true);
                        signup_bt.setBackground(getDrawable(R.drawable.signup_bt));
                    }else {
                        signup_bt.setEnabled(false);
                        signup_bt.setBackground(getDrawable(R.drawable.signup_bt_none));
                    }
                }else if(answer != null && answer[0].equals("FALSE")){
                    check_nickname_txt.setText("사용 불가능한 닉네임 입니다");
                    check_nickname_txt.setTextColor(getColor(R.color.warn_red));
                    isCheckNickname = false;
                }else{
                    check_nickname_txt.setText("오류가 발생 했습니다.");
                    check_nickname_txt.setTextColor(getColor(R.color.warn_red));
                    isCheckNickname = false;
                }
            }
        });
        /**
         * 모두 동의 클릭 시
         */
        agreeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(agreeAll.isChecked()){
                    prime_agree.setChecked(true);
                    security_agree.setChecked(true);
                    marketing_agree.setChecked(true);
                    age_agree.setChecked(true);
                    //모든 약관 보여주기
                    if(CheckAllWrite()){
                        signup_bt.setEnabled(true);
                        signup_bt.setBackground(getDrawable(R.drawable.signup_bt));
                    }else {
                        signup_bt.setEnabled(false);
                        signup_bt.setBackground(getDrawable(R.drawable.signup_bt_none));
                    }
                }else{
                    prime_agree.setChecked(false);
                    security_agree.setChecked(false);
                    marketing_agree.setChecked(false);
                    age_agree.setChecked(false);
                }
            }
        });

        prime_agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!prime_agree.isChecked()){
                    agreeAll.setChecked(false);
                }else{
                    if(prime_agree.isChecked()&&security_agree.isChecked()&&marketing_agree.isChecked()&&age_agree.isChecked()){
                        agreeAll.setChecked(true);
                    }
                    if(CheckAllWrite()){
                        signup_bt.setEnabled(true);
                        signup_bt.setBackground(getDrawable(R.drawable.signup_bt));
                    }else {
                        signup_bt.setEnabled(false);
                        signup_bt.setBackground(getDrawable(R.drawable.signup_bt_none));
                    }
                    //약관 이동
                }
            }
        });
        security_agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!security_agree.isChecked()){
                    agreeAll.setChecked(false);
                }else{
                    if(prime_agree.isChecked()&&security_agree.isChecked()&&marketing_agree.isChecked()&&age_agree.isChecked()){
                        agreeAll.setChecked(true);
                    }
                    if(CheckAllWrite()){
                        signup_bt.setEnabled(true);
                        signup_bt.setBackground(getDrawable(R.drawable.signup_bt));
                    }else {
                        signup_bt.setEnabled(false);
                        signup_bt.setBackground(getDrawable(R.drawable.signup_bt_none));
                    }
                    //약관 이동
                }
            }
        });
        marketing_agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!marketing_agree.isChecked()){
                    agreeAll.setChecked(false);
                }else{
                    if(prime_agree.isChecked()&&security_agree.isChecked()&&marketing_agree.isChecked()&&age_agree.isChecked()){
                        agreeAll.setChecked(true);
                    }
                    //약관 이동
                }
            }
        });
        age_agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!age_agree.isChecked()){
                    agreeAll.setChecked(false);
                }else{
                    if(prime_agree.isChecked()&&security_agree.isChecked()&&marketing_agree.isChecked()&&age_agree.isChecked()){
                        agreeAll.setChecked(true);
                    }
                    if(CheckAllWrite()){
                        signup_bt.setEnabled(true);
                        signup_bt.setBackground(getDrawable(R.drawable.signup_bt));
                    }else {
                        signup_bt.setEnabled(false);
                        signup_bt.setBackground(getDrawable(R.drawable.signup_bt_none));
                    }
                    //약관 이동
                }
            }
        });



        signup_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup_bt.setEnabled(false);
                if(signup_pw.getText().toString().length()==0){
                    Toast.makeText(getApplicationContext(),"비밀번호를 입력해주세요",Toast.LENGTH_SHORT).show();
                    signup_bt.setEnabled(true);
                }else if(!signup_pw.getText().toString().equals(signup_pw_check.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "비밀번호가 맞지 않습니다", Toast.LENGTH_SHORT).show();
                    signup_bt.setEnabled(true);
                }else {
                    List<String> parms = new ArrayList<>();
                    parms.add(signup_pw.getText().toString());
                    parms.add(prime_agree.isChecked() ? "1" : "0");
                    parms.add(security_agree.isChecked() ? "1" : "0");
                    parms.add(marketing_agree.isChecked() ? "1" : "0");
                    parms.add(age_agree.isChecked() ? "1" : "0");
                    String[] answer = Universal.NETWORK.Request("signup/SingUp",parms);
                    if(answer[0].equals("True")){
                        Universal.fileSystem.SetAccount(signup_email.getText().toString(),signup_pw.getText().toString());
                        Intent retIntent = new Intent();
                        setResult(RESULT_OK,retIntent);
                        finish();
                    }else if(answer[0].equals("Err")){
                        Toast.makeText(getApplicationContext(),"회원 가입 중 오류가 발생했습니다",Toast.LENGTH_SHORT).show();
                        signup_bt.setEnabled(true);
                    }else{
                        finish();
                    }
                }
            }
        });


    }

    public boolean CheckAllWrite(){
        return isCheckEmail&&isCheckNickname&&(signup_pw.getText().toString().length()>0)&&(signup_pw.getText().toString().equals(signup_pw_check.getText().toString()))&&prime_agree.isChecked()&&security_agree.isChecked()&&age_agree.isChecked();
    }
}