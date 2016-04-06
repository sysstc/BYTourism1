package com.ezz.bytourism1;

import java.util.List;

import com.ezz.bean.Collect;
import com.ezz.bean.Scenic;
import com.ezz.bean.User;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SightDetail extends BaseActivity{
	private ImageView sight_view;
	private TextView sight_describe;
	private EditText sight_my_message;
	private TextView sight_all_message;
	private String sight_name;
	private String sight_price;
	private int sight_id;
	private String sight_type;
	private Button back_btn;
	private TextView city_name;
	private ImageButton collectButton;
	
	private String userid ;
	private int type;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sight_detail);
		Bmob.initialize(SightDetail.this, "a1a4ff643e92be99bb8649e33589c596");
		Intent searchIntent = getIntent();
		
		sight_name = searchIntent.getStringExtra("sight_name");
		sight_type = searchIntent.getStringExtra("sight_type");
		sight_price = searchIntent.getStringExtra("sight_price");
		
		Toast.makeText(this, "name = "+sight_name+"type = "+sight_type+"price = "+sight_price, Toast.LENGTH_SHORT).show();
		showView();
		city_name = (TextView) findViewById(R.id.city_name);
		city_name.setText(sight_name);
		collectButton = (ImageButton) findViewById(R.id.collectButton);
		collectButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				if(getPreference(USER_NAME).equals("")){
					Toast.makeText(SightDetail.this, "请先登录，再收藏", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(SightDetail.this,LoginActivity.class);
					startActivity(intent);
				}
				else{
					String username = getPreference(USER_NAME);
					BmobQuery<User> user_query = new BmobQuery<User>();
					user_query.addWhereEqualTo("username",username);
					user_query.findObjects(SightDetail.this, new FindListener<User>() {
						@Override
						public void onError(int arg0, String arg1) {
							// TODO 自动生成的方法存根
							Log.i("80-fail",arg0+arg1);
						}
						@Override
						public void onSuccess(List<User> users) {
							// TODO 自动生成的方法存根
							Toast.makeText(SightDetail.this, "user = "+users.get(0), Toast.LENGTH_SHORT).show();
							//find userid list from UserTable and then find cid from Scenic through scenic_name
							userid = users.get(0).getId();
							Log.i("tag!!!","userid = "+ userid+" ");
							
							
							BmobQuery<Scenic> cid_query = new BmobQuery<Scenic>();
							cid_query.addWhereEqualTo("scenicname",sight_name);
							
							cid_query.findObjects(SightDetail.this, new FindListener<Scenic>() {
								@Override
								public void onError(int arg0, String arg1) {
									// TODO 自动生成的方法存根
								}
								@Override
								public void onSuccess(List<Scenic> scenic) {
									// TODO 自动生成的方法存根
									sight_id = scenic.get(0).getId();
									type = 5;
									Collect collect  = new Collect();
									collect.setUserid(userid);
									collect.setCid(sight_id);
									collect.setType(type);
									collect.save(SightDetail.this,new SaveListener() {
										
										@Override
										public void onSuccess() {
											// TODO 自动生成的方法存根
											Toast.makeText(SightDetail.this, "收藏成功！", Toast.LENGTH_SHORT).show();
										}
										@Override
										public void onFailure(int arg0, String arg1) {
											// TODO 自动生成的方法存根
											Log.i("115-fail",arg0+arg1);
										}
									});
								}
							});

						}
					});
					Toast.makeText(SightDetail.this, username, Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		sight_view = (ImageView) findViewById(R.id.sight_view);
		sight_describe = (TextView) findViewById(R.id.TextView);
		sight_my_message = (EditText) findViewById(R.id.sight_my_message);
		sight_all_message = (TextView) findViewById(R.id.sight_all_message);
		back_btn = (Button) findViewById(R.id.back_btn);
		back_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				finish();
			}
		});
		
	}
	Handler handler = new Handler(){
		@Override
		public void handleMessage(android.os.Message msg) {
			// TODO 自动生成的方法存根
			switch (msg.what) {
			case 1:
			    	Toast.makeText(SightDetail.this, msg.obj.toString()+"||||||||",Toast.LENGTH_SHORT).show();
					//sight_describe.setText(msg.obj+" ");
				break;

			default:
				break;
			}
			super.handleMessage(msg);
		}
	};
	public void showView(){
		BmobQuery<Scenic> query_scenic = new BmobQuery<Scenic>();
		query_scenic.addWhereEqualTo("scenicname",sight_name);
		query_scenic.findObjects(SightDetail.this, new FindListener<Scenic>() {
			@Override
			public void onSuccess(List<Scenic> scenics) {
				// TODO 自动生成的方法存根
				for(Scenic scenic:scenics){
					Message msg = new Message();
					msg.what = 1;
					msg.obj = scenic.getDescribe();
					handler.sendMessage(msg);
				}
			}
			@Override
			public void onError(int arg0, String arg1) {
				// TODO 自动生成的方法存根
				
			}
		});
	}
}
