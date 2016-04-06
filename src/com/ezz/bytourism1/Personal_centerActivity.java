package com.ezz.bytourism1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 37492 on 2016/4/4.
 */
public class Personal_centerActivity extends BaseActivity {
    TextView username,userid;
    RelativeLayout re,yep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_center);
        username = (TextView)findViewById(R.id.nameview);
        userid = (TextView)findViewById(R.id.idview);
        re = (RelativeLayout)findViewById(R.id.relative4);
        yep = (RelativeLayout)findViewById(R.id.yep);

       updateIn();

    }
    public void signout(View view){
        deletePreference();
        updateIn();
    }
    public void trunToLogin(View view){
        Intent intent = new Intent(Personal_centerActivity.this,LoginActivity.class);
        startActivity(intent);
    }
    public void turnToRegist(View view){
        Intent intent = new Intent(Personal_centerActivity.this,RegistActivity.class);
        startActivity(intent);
    }
    public void returnHome(View view){
        Intent intent = new Intent(Personal_centerActivity.this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);//must store the new intent unless getIntent() will return the old one
        Toast.makeText(Personal_centerActivity.this,"here is intent", Toast.LENGTH_LONG).show();
        updateIn();
    }
    public void updateIn(){
        if(!getPreference(USER_NAME).equals("")){
            Toast.makeText(Personal_centerActivity.this, getResources().getString(R.string.signin)+getPreference(USER_NAME), Toast.LENGTH_LONG).show();
            username.setText(getPreference(USER_NAME));
            userid.setText("365354"+getPreference(USER_ID));
            yep.setVisibility(View.GONE);
            re.setVisibility(View.VISIBLE);
        }
        else{
            Toast.makeText(Personal_centerActivity.this, getResources().getString(R.string.signout)+getPreference(USER_NAME), Toast.LENGTH_LONG).show();
            username.setText(getResources().getString(R.string.signout));
            userid.setText(getResources().getString(R.string.signout));
            yep.setVisibility(View.VISIBLE);
            re.setVisibility(View.GONE);
        }
    }


}
