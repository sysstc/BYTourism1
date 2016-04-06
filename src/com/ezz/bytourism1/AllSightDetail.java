package com.ezz.bytourism1;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.PrivateCredentialPermission;

import org.json.JSONArray;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindCallback;
import cn.bmob.v3.listener.FindListener;

import com.a.a.a.Tempest;
import com.ezz.bean.City;
import com.ezz.bean.Scenic;
import com.ezz.bean.Scenicroute;

import android.R.integer;
import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.Time;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class AllSightDetail extends Activity implements OnClickListener,OnItemClickListener{
	private Button btn_back;
	private AutoCompleteTextView edit_Search1;
	private List<String> city_list;
 	private ArrayAdapter<String> adapter;
 	private ListView city_listview;
 	public SimpleAdapter sadapter;
 	private List<Map<String,Object>> dataList;
 	private Button find_btn;
 	private String city_name;
 	private int time;
 	private static final ThreadLocal re = new ThreadLocal();
 	int a = 0;
 	@Override
 	protected void onCreate(Bundle savedInstanceState) {
 		// TODO 自动生成的方法存根
 		super.onCreate(savedInstanceState);
 		
 		setContentView(R.layout.allsightdetail);
 		Bmob.initialize(AllSightDetail.this, "a1a4ff643e92be99bb8649e33589c596");
 		Intent searchIntent = getIntent();
 		city_name = searchIntent.getStringExtra("city_name");
 		edit_Search1 = (AutoCompleteTextView) findViewById(R.id.edit_Search1);
 		edit_Search1.setText(city_name);
 		find_btn = (Button) findViewById(R.id.find_btn);
 		btn_back = (Button) findViewById(R.id.btn_back);
 		
 		city_listview = (ListView) findViewById(R.id.city_listview);
 		city_list = new ArrayList<String>();
 	

 		city_list.add("北京");
 		city_list.add("上海");
 		city_list.add("厦门");
 		city_list.add("广州"); 
 		city_list.add("云南");
 		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, city_list);
 		edit_Search1.setAdapter(adapter);
 		
 		find_btn.setOnClickListener(this);
 		
 		//搜索框输入时，单击回车
 		/*		edit_Search1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				// TODO 自动生成的方法存根
				if(actionId==EditorInfo.IME_ACTION_SEND||(event!=null&&event.getKeyCode()==KeyEvent.KEYCODE_ENTER)){
					Toast.makeText(AllSightDetail.this, "开始搜索"+city_name, Toast.LENGTH_SHORT).show();
					getData();
					return true;
				}
				return false;
			}

			private void query() {
				// TODO 自动生成的方法存根
				
			}
			
		});*/
 		btn_back.setOnClickListener(new OnClickListener() {  
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根     
				Intent intent = new Intent(AllSightDetail.this,MainActivity.class);
				startActivity(intent);
			}
		});
 		dataList = new ArrayList<Map<String, Object>>();
		getData();
		/*sadapter = new SimpleAdapter(AllSightDetail.this, dataList, R.layout.listview_item,
 				new String[]{"sight_view","sight_name","sight_type","sight_price","sight_avggrade"} ,
 				new int[]{R.id.sight_view,R.id.sight_name,R.id.sight_type,R.id.sight_price,R.id.sight_avggrade});
		*/ 
		sadapter = new MySimpleAdapter(AllSightDetail.this, dataList, R.layout.listview_item,
 				new String[]{"sight_view","sight_name","sight_type","sight_price","sight_avggrade"} ,
 				new int[]{R.id.sight_view,R.id.sight_name,R.id.sight_type,R.id.sight_price,R.id.sight_avggrade});
		
		 city_listview.setAdapter(sadapter);
		 city_listview.setOnItemClickListener(this);
 	}
 	Handler handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO 自动生成的方法存根
				Object scienc;
				switch (msg.what) {
				case 1:
					 scienc=msg.obj;
					 Map<String, Object> a = (Map<String, Object>)scienc;
					 dataList.add(a);		 
  				     sadapter.notifyDataSetChanged();
				//	 Toast.makeText(AllSightDetail.this, time+" "+dataList.size()+"   "+msg.toString(), Toast.LENGTH_SHORT).show();
					 break;
				case 2:
					scienc=msg.obj;
					 Map<String, Object> b = (Map<String, Object>)scienc;
					 dataList.add(b);
					 sadapter.notifyDataSetChanged();
				//	Toast.makeText(AllSightDetail.this, time+" "+dataList.size()+"   "+msg.toString(), Toast.LENGTH_SHORT).show();

					break;
				case 3:
					scienc=msg.obj;
					 Map<String, Object> c = (Map<String, Object>)scienc;
					 dataList.add(c);
					 sadapter.notifyDataSetChanged();
		//			Toast.makeText(AllSightDetail.this, time+" "+dataList.size()+"   "+msg.toString(), Toast.LENGTH_SHORT).show();
					//Log.i("tag3",msg.toString());
				
					break;
				case 4:
					scienc=msg.obj;
					 Map<String, Object> d = (Map<String, Object>)scienc;
					 dataList.add(d);
			//		Toast.makeText(AllSightDetail.this, time+" "+dataList.size()+"   "+msg.toString(), Toast.LENGTH_SHORT).show();
					//Log.i("tag4",msg.toString());
					 sadapter.notifyDataSetChanged();
					break;
				case 5:
					scienc=msg.obj;
					 Map<String, Object> e = (Map<String, Object>)scienc;
					 dataList.add(e);
					 sadapter.notifyDataSetChanged();
				//	Toast.makeText(AllSightDetail.this, time+" "+dataList.size()+"   "+msg.toString(), Toast.LENGTH_SHORT).show();
					//Log.i("tag5",msg.toString());
					
					
					break;
				case 0:
					/*Toast.makeText(AllSightDetail.this,"ending!", Toast.LENGTH_SHORT).show();
					Toast.makeText(AllSightDetail.this, "datalist = "+dataList.size(), Toast.LENGTH_SHORT).show();*/
					break;
				}
				super.handleMessage(msg);
			}
		};
	private List<Map<String, Object>> getData(){
		dataList = new ArrayList<Map<String, Object>>();
		BmobQuery<City> query_city = new BmobQuery<City>();
		
		city_name = edit_Search1.getText().toString();
	//	Toast.makeText(AllSightDetail.this, "------"+city_name, Toast.LENGTH_SHORT).show();
		
		query_city.addWhereEqualTo("cityname", city_name);
		
		query_city.findObjects(this, new FindListener<City>() {

			@Override
			public void onError(int arg0, String arg1) {
				// TODO 自动生成的方法存根
		//		akeText(AllSightDetail.this,"fail136"+ arg0+" "+arg1,Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onSuccess(List<City> citysList) {
				// TODO 自动生成的方法存根

				
				Map<String,Object> city_map = new HashMap<String,Object>();
				
				int cityid = citysList.get(0).getId();//city表中的id
				BmobQuery<Scenicroute> query_sroute = new BmobQuery<Scenicroute>();
				query_sroute.addWhereEqualTo("cityid", cityid);
		//		Toast.makeText(AllSightDetail.this,cityid+"====>187",Toast.LENGTH_SHORT).show();
				
				query_sroute.findObjects(AllSightDetail.this, new FindListener<Scenicroute>() {
					
					@Override
					public void onSuccess(List<Scenicroute> sroutes) {
						// TODO 自动生成的方法存根
						int l =sroutes.size();
						for(int i = 0;i<l;i++){//找到在Scenicroute表下面的路线
							time=0;
							Scenicroute sroute = sroutes.get(0);
							
				//			Toast.makeText(AllSightDetail.this,sroutes.size()+"===>196",Toast.LENGTH_SHORT).show();
							
							if(sroute.getOne()!=0){
								time++;
								BmobQuery<Scenic> query_scenic = new BmobQuery<Scenic>();
								query_scenic.addWhereEqualTo("id", sroute.getOne());
								
		//						Toast.makeText(AllSightDetail.this,sroute.getOne()+"===>201",Toast.LENGTH_SHORT).show();
								
								query_scenic.findObjects(AllSightDetail.this, new FindListener<Scenic>() {
									@Override
									public void onError(int arg0, String arg1) {
										// TODO 自动生成的方法存根
			//							Toast.makeText(AllSightDetail.this,"fail!!"+arg0+arg1, Toast.LENGTH_SHORT).show();
									}
									@Override
									public void onSuccess(List<Scenic> ascenic) {
										// TODO 自动生成的方法存根
										Map<String,Object> city_map = new HashMap<String,Object>();
										city_map.put("sight_view", ascenic.get(0).getScenicview());
										city_map.put("sight_name",ascenic.get(0).getScenicname());
										city_map.put("sight_type",ascenic.get(0).getScenictype());
										city_map.put("sight_price",ascenic.get(0).getScenicprice());
										city_map.put("sight_avggrade",ascenic.get(0).getAvggrade());
										
										Message msg = new Message();
										msg.what = 1;
										msg.obj = city_map;
										handler.sendMessage(msg);
										
				//						Toast.makeText(AllSightDetail.this,city_map.get("sight_name")+"",Toast.LENGTH_SHORT).show();
									}
								});
								
							}
							if(sroute.getTwo()!=0){
								time++;
								BmobQuery<Scenic> query_scenic = new BmobQuery<Scenic>();
								query_scenic.addWhereEqualTo("id", sroute.getTwo());
								query_scenic.findObjects(AllSightDetail.this, new FindListener<Scenic>() {
									@Override
									public void onError(int arg0, String arg1) {
										// TODO 自动生成的方法存根
										Log.i("fail!!!!!!!!!183", arg0+" "+arg1);
									}
									@Override
									public void onSuccess(List<Scenic> ascenic) {
										// TODO 自动生成的方法存根
										Map<String,Object> city_map = new HashMap<String,Object>();
										city_map.put("sight_view", ascenic.get(0).getScenicview());
										city_map.put("sight_name",ascenic.get(0).getScenicname());
										city_map.put("sight_type",ascenic.get(0).getScenictype());
										city_map.put("sight_price",ascenic.get(0).getScenicprice());
										city_map.put("sight_avggrade",ascenic.get(0).getAvggrade());
										
										Message msg = new Message();
										msg.what = 2;
										msg.obj = city_map;
										handler.sendMessage(msg);
										
			//							Toast.makeText(AllSightDetail.this,city_map.get("sight_name")+"",Toast.LENGTH_SHORT).show();
										}
								});
								
							}
							if(sroute.getThree()!=0){
								time++;
								BmobQuery< Scenic> query_scenic = new BmobQuery<Scenic>();
								query_scenic.addWhereEqualTo("id",sroute.getThree());
			//					Toast.makeText(AllSightDetail.this, sroute.getThree()+"", Toast.LENGTH_SHORT).show();
								query_scenic.findObjects(AllSightDetail.this, new FindListener<Scenic>() {
									@Override
									public void onError(int arg0, String arg1) {
										// TODO 自动生成的方法存根
										Log.i("tag!!!!!!", "209 "+arg0+" "+arg1);
									}
									public void onSuccess(List<Scenic> scenic) {
										// TODO 自动生成的方法存根
						//				Toast.makeText(AllSightDetail.this, scenic.size()+"", Toast.LENGTH_SHORT).show();
										Map<String,Object> city_map = new HashMap<String,Object>();
										city_map.put("sight_view", scenic.get(0).getScenicview());
										city_map.put("sight_name", scenic.get(0).getScenicname());
										city_map.put("sight_type", scenic.get(0).getScenictype());
										city_map.put("sight_price", scenic.get(0).getScenicprice());
										city_map.put("sight_avggrade", scenic.get(0).getAvggrade());
										
										Message msg = new Message();
										msg.what = 3;
										msg.obj = city_map;
										handler.sendMessage(msg);
										
				//						Toast.makeText(AllSightDetail.this,city_map.get("sight_name")+"",Toast.LENGTH_SHORT).show();
										}
								
								});
								
							}
							if(sroute.getFour()!=0){
								time++;
								BmobQuery<Scenic> query_scenic = new BmobQuery<Scenic>();
								query_scenic.addWhereEqualTo("id", sroute.getFour());
								query_scenic.findObjects(AllSightDetail.this, new FindListener<Scenic>() {
									@Override
									public void onError(int arg0, String arg1) {
										// TODO 自动生成的方法存根
										Log.i("fail!!!!!!!!!236", arg0+" "+arg1);
									}
									@Override
									public void onSuccess(List<Scenic> ascenic) {
										// TODO 自动生成的方法存根
										Map<String,Object> city_map = new HashMap<String,Object>();
										city_map.put("sight_view", ascenic.get(0).getScenicview());
										city_map.put("sight_name", ascenic.get(0).getScenicname());
										city_map.put("sight_type", ascenic.get(0).getScenictype());
										city_map.put("sight_price", ascenic.get(0).getScenicprice());
										city_map.put("sight_avggrade", ascenic.get(0).getAvggrade());
										
										
										Message msg = new Message();
										msg.what = 4;
										msg.obj = city_map;
										handler.sendMessage(msg);
										
			//							Toast.makeText(AllSightDetail.this,city_map.get("sight_name")+"",Toast.LENGTH_SHORT).show();
										}
								});
								
							}
							if(sroute.getFive()!=0){
								time++;
								BmobQuery<Scenic> query_scenic = new BmobQuery<Scenic>();
								query_scenic.addWhereEqualTo("id", sroute.getFive());
								query_scenic.findObjects(AllSightDetail.this, new FindListener<Scenic>() {
									@Override
									public void onError(int arg0, String arg1) {
										// TODO 自动生成的方法存根
										Log.i("fail!!!!!!!!!262", arg0+" "+arg1);
									}
									@Override
									public void onSuccess(List<Scenic> ascenic) {
										// TODO 自动生成的方法存根
										Map<String,Object> city_map = new HashMap<String,Object>();
										city_map.put("sight_view", ascenic.get(0).getScenicview());
										city_map.put("sight_name",ascenic.get(0).getScenicname());
										city_map.put("sight_type",ascenic.get(0).getScenictype());
										city_map.put("sight_price",ascenic.get(0).getScenicprice());
										city_map.put("sight_avggrade",ascenic.get(0).getAvggrade());
										
										Message msg = new Message();
										msg.what = 5;
										msg.obj = city_map;
										handler.sendMessage(msg);
									
		//								Toast.makeText(AllSightDetail.this,city_map.get("sight_name")+"",Toast.LENGTH_SHORT).show();
									}
								});
								
							}
						
						}
						
					}
					@Override
					public void onError(int arg0, String arg1) {
						// TODO 自动生成的方法存根
						Log.i("fail!!!!!!!!!284", arg0+" "+arg1);
					}
				});
			}
			
		});		
		
		return dataList;
	}
	
	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		dataList = new ArrayList<Map<String, Object>>();
		getData();
		sadapter = new SimpleAdapter(AllSightDetail.this, dataList, R.layout.listview_item,
 				new String[]{"sight_view","sight_name","sight_type","sight_price","sight_avggrade"} ,
 				new int[]{R.id.sight_view,R.id.sight_name,R.id.sight_type,R.id.sight_price,R.id.sight_avggrade});
		 
		sadapter = new MySimpleAdapter(AllSightDetail.this, dataList, R.layout.listview_item,
 				new String[]{"sight_view","sight_name","sight_type","sight_price","sight_avggrade"} ,
 				new int[]{R.id.sight_view,R.id.sight_name,R.id.sight_type,R.id.sight_price,R.id.sight_avggrade});
		
		 city_listview.setAdapter(sadapter);
		 city_listview.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO 自动生成的方法存根
		Map<String, Object> text= (Map<String, Object>)city_listview.getItemAtPosition(position);
		Toast.makeText(this, "position = "+position+" text="+text.get("sight_name")+"",Toast.LENGTH_LONG).show();
		Intent intent = new Intent(AllSightDetail.this,SightDetail.class);
		
		intent.putExtra("sight_name", text.get("sight_name").toString());
		intent.putExtra("sight_type", text.get("sight_type").toString());
		intent.putExtra("sight_price", text.get("sight_price").toString());
		startActivity(intent);
	}
}

