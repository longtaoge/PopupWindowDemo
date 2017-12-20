package com.michael.popupwindowdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends Activity
{
    
    private ImageButton ibOperationMore;
    
    List<Map<String, String>> moreList;
    
    private PopupWindow mPopWindow;// popupwindow
    
    private ListView lvPopupList;// popupwindow中的ListView
    
    private int NUM_OF_VISIBLE_LIST_ROWS = 3;// 指定popupwindow中Item的数量
    
    LinearLayout ll_head_bar;
    
    LinearLayout v1;
    
    int h;
    
    int[] location;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        iniData();
        v1 = (LinearLayout)findViewById(R.id.ll);
        
        h = v1.getHeight();
        
        iniPopupWindow();
        
        // popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, location[0]+v.getWidth(), location[1]);
        
        // 更多操作按钮
        
        ll_head_bar = (LinearLayout)findViewById(R.id.ll_head_bar);
        ibOperationMore = (ImageButton)findViewById(R.id.ib_operate_more);
        ibOperationMore.setOnClickListener(new OnClickListener()
        {
            
            @Override
            public void onClick(View v)
            {
                
                if (mPopWindow.isShowing())
                {
                    
                    mPopWindow.dismiss();// 关闭
                }
                else
                {
                    
                    // mPopWindow.showAsDropDown(ibOperationMore);// 显示
                    
                    // mPopWindow.showAtLocation(ibOperationMore, Gravity.BOTTOM, location[0], location[1]);
                    location = new int[2];
                    v1.getLocationOnScreen(location);
                    
                    mPopWindow.showAtLocation(v1, Gravity.RIGHT, location[0], location[1]);
                    
                    // 左边
                    
                    // mPopWindow.showAtLocation(v, Gravity.NO_GRAVITY, location[0]-mPopWindow.getWidth(), location[1]);
                    
                    // 右边
                    // mPopWindow.showAtLocation(v, Gravity.NO_GRAVITY, location[0]+v.getWidth(), location[1]);
                    
                    // pwMyPopWindow.showAtLocation(v, Gravity.NO_GRAVITY, location[0]-pwMyPopWindow.getWidth(),
                    // location[1]);
                }
                
            }
        });
    }
    
    private void iniData()
    {
        
        moreList = new ArrayList<Map<String, String>>();
        Map<String, String> map;
        map = new HashMap<String, String>();
        map.put("share_key", "复制");
        moreList.add(map);
        map = new HashMap<String, String>();
        map.put("share_key", "删除");
        moreList.add(map);
        map = new HashMap<String, String>();
        map.put("share_key", "修改");
        moreList.add(map);
    }
    
    private void iniPopupWindow()
    {
        
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.task_detail_popupwindow, null);
        lvPopupList = (ListView)layout.findViewById(R.id.lv_popup_list);
        mPopWindow = new PopupWindow(layout);
        mPopWindow.setFocusable(true);// 加上这个popupwindow中的ListView才可以接收点击事件
        
        lvPopupList.setAdapter(new SimpleAdapter(MainActivity.this, moreList, R.layout.list_item_popupwindow,
            new String[] {"share_key"}, new int[] {R.id.tv_list_item}));
        lvPopupList.setOnItemClickListener(new OnItemClickListener()
        {
            
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                
                Toast.makeText(MainActivity.this, moreList.get(position).get("share_key"), Toast.LENGTH_LONG).show();
            }
        });
        
        // 控制popupwindow的宽度和高度自适应
        lvPopupList.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        mPopWindow.setWidth(lvPopupList.getMeasuredWidth());
        // mPopWindow.setHeight(WindowManager.LayoutParams.MATCH_PARENT);ll
        mPopWindow.setHeight(WindowManager.LayoutParams.MATCH_PARENT);
        
        // 控制popupwindow点击屏幕其他地方消失
        mPopWindow.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.blue_bg));// 设置背景图片，不能在布局中设置，要通过代码来设置
        mPopWindow.setOutsideTouchable(true);// 触摸popupwindow外部，popupwindow消失。这个要求你的popupwindow要有背景图片才可以成功，如上
        
        // mPopWindow.setAnimationStyle(R.style.popwin_anim_style);
       // mPopWindow.setAnimationStyle(R.style.popwin_anim_style);
        
    }
    
}
