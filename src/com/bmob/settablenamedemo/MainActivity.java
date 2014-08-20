package com.bmob.settablenamedemo;

import org.json.JSONArray;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.FindCallback;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	
	GameScore gameScore;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Bmob.initialize(this, "");
		showToast("请记得将你的AppId填写在MainActivity的BmobSDK初始化方法中");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_createData:
			createData();
			break;
		case R.id.btn_updateData:
			updateData();
			break;
		case R.id.btn_deleteData:
			deleteData();
			break;
		case R.id.btn_queryData:
			queryData();
			break;

		default:
			break;
		}
	}
	
	/**
	 * 创建数据
	 */
	public void createData(){
		gameScore = new GameScore();
		gameScore.setPlayerName("张三");
		gameScore.setScore(87);
		gameScore.setCheatMode(true);
		gameScore.save(this, new SaveListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				showToast("创建成功");
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				showToast("创建失败："+arg1);
			}
		});
	}
	
	/**
	 * 更新数据
	 */
	public void updateData(){
		gameScore.setScore(78);
		gameScore.update(this, new UpdateListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				showToast("更新成功");
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				showToast("更新失败");
			}
		});
	}
	
	/**
	 * 删除数据
	 */
	public void deleteData(){
		gameScore.delete(this, new DeleteListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				showToast("删除成功");
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				showToast("删除失败");
			}
		});
	}
	
	/**
	 * 查询数据
	 */
	public void queryData(){
		BmobQuery query = new BmobQuery("T_a_b");
		query.findObjects(this, new FindCallback() {
			
			@Override
			public void onSuccess(JSONArray arg0) {
				// TODO Auto-generated method stub
				showToast("查询成功:"+arg0.length());
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				showToast("查询失败:"+arg1);
			}
		});
	}
	
	private void showToast(String msg){
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
}
