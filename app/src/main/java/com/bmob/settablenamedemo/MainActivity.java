package com.bmob.settablenamedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class MainActivity extends AppCompatActivity {

    GameScore gameScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this, "");
        showToast("请记得将你的AppId填写在MainActivity的BmobSDK初始化方法中");
    }

    /**
     * 创建数据
     */
    public void onCreateData(View v){
        gameScore = new GameScore();
        gameScore.setPlayerName("张三");
        gameScore.setScore(87);
        gameScore.setCheatMode(true);
        gameScore.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e != null) {
                    showToast("创建失败："+e.getMessage());
                } else {
                    showToast("创建成功");
                }
            }
        });
    }

    /**
     * 更新数据
     */
    public void onUpdateData(View v){
        gameScore.setScore(78);
        gameScore.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e != null) {
                    showToast("更新失败："+e.getMessage());
                } else {
                    showToast("更新成功");
                }
            }
        });
    }

    /**
     * 删除数据
     */
    public void onDeleteData(View v){
        gameScore.delete("gameScore的objectId", new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e != null) {
                    showToast("删除失败："+e.getMessage());
                } else {
                    showToast("删除成功");
                }
            }
        });
    }

    /**
     * 查询数据
     */
    public void onQueryData(View v){
        BmobQuery<GameScore> query = new BmobQuery<>("T_a_b");
        query.findObjects(new FindListener<GameScore>() {
            @Override
            public void done(List<GameScore> list, BmobException e) {
                if (e != null) {
                    showToast("查询失败:"+e.getMessage());
                } else {
                    showToast("查询成功:"+list.size());
                }
            }
        });
    }

    private void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}