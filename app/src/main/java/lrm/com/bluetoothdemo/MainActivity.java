package lrm.com.bluetoothdemo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import lrm.com.bluetoothdemo.activity.BaseActivity;
import lrm.com.bluetoothdemo.activity.TestActivity;

public class MainActivity extends BaseActivity {
    private Button btn_test;

    @NonNull
    @Override
    protected String getActionBarTitle() {
        return "MainActivity";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        btn_test = (Button) findViewById(R.id.btn_test);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initListenter() {
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TestActivity.class);
                startActivity(intent);
            }
        });
    }
}
