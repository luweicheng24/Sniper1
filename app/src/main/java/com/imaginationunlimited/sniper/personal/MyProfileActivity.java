package com.imaginationunlimited.sniper.personal;

import android.app.AlertDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.imaginationunlimited.sniper.R;
import com.imaginationunlimited.sniper.base.BaseActivity;

public class MyProfileActivity extends BaseActivity implements View.OnClickListener {

    private Switch switch_smart;
    private View rl_aboutus;

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_aboutus:
                Intent intent = new Intent(this, AboutUsActivity.class);
                startActivity(intent);
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

}
