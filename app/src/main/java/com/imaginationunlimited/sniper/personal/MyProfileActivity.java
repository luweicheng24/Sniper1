package com.imaginationunlimited.sniper.personal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.imaginationunlimited.sniper.R;
import com.imaginationunlimited.sniper.base.BaseActivity;
import com.imaginationunlimited.sniper.model.UserInfoFromService;

public class MyProfileActivity extends BaseActivity implements View.OnClickListener {

    private InputMethodManager imm;
    private Switch switch_smart;
    private View rl_aboutus;
    private View iv_job,iv_school;
    private TextView tv_job,tv_school;
    private EditText et_job,et_school;
    private String et_result_job,et_result_school;



    @Override
    protected void afterViewFound() {

    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_my_profile);
    }

    @Override
    public void setUpPresenter() {
//        ListView_overScrollFooter
    }

    @Override
    public void findViews() {
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        switch_smart = findViewById(R.id.switch_smart);
        switch_smart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(MyProfileActivity.this, "开启开关1", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MyProfileActivity.this, "关闭开关1", Toast.LENGTH_SHORT).show();
                }
            }
        });
        rl_aboutus = findViewById(R.id.rl_aboutus);
        rl_aboutus.setOnClickListener(this);
        iv_job = findViewById(R.id.iv_job);
        iv_job.setOnClickListener(this);
        tv_job = findViewById(R.id.tv_job);
        et_job=findViewById(R.id.et_job);
        iv_school = findViewById(R.id.iv_school);
        iv_school.setOnClickListener(this);
        tv_school =findViewById(R.id.tv_school);
        et_school = findViewById(R.id.et_school);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_aboutus:
                Intent intent = new Intent(this, AboutUsActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_job:
                //从服务器上拿到用户信息填充进去
//                UserInfoFromService.getInstance().getUser()
                String content = tv_job.getText().toString();
                tv_job.setVisibility(View.INVISIBLE);
                et_job.setVisibility(View.VISIBLE);
                et_job.setText(content);
                et_job.setSelection(et_job.getText().length());
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                break;
            case R.id.iv_school:
                String content2 =  tv_school.getText().toString();
                tv_school.setVisibility(View.INVISIBLE);
                et_school.setVisibility(View.VISIBLE);
                et_school.setText(content2);
                et_school.setSelection(et_school.getText().length());
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                break;
        }
    }

    public void addPhotoAndVid() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View view = LayoutInflater.from(this).inflate(R.layout.dialog_edit_photos, null);
        View cancel = findViewById(R.id.tv_cancel);
        View delete = findViewById(R.id.tv_delete);
        View select = findViewById(R.id.tv_select);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        //监听


//
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN){

            if(getCurrentFocus()!=null && getCurrentFocus().getWindowToken()!=null){
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                et_result_job = et_job.getText().toString();
                et_job.setVisibility(View.INVISIBLE);
                tv_job.setText(et_result_job);
                tv_job.setVisibility(View.VISIBLE);
                //网络请求上传修改后的

                et_result_school = et_school.getText().toString();
                et_school.setVisibility(View.INVISIBLE);
                tv_school.setText(et_result_school);
                tv_school.setVisibility(View.VISIBLE);
                //wangluoshangchuan
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}
