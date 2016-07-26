package com.lc.documentdemo;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.InjectView;
import butterknife.OnItemClick;
/**
 * Author: lc
 * Email: rekirt@163.com
 * Date: 7/26/16
 */
public class HomeActivity extends BaseActivity {

    @InjectView(R.id.lv_demo_list)
    ListView lv_demo_list;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_home;
    }

    @OnItemClick(R.id.lv_demo_list)
    public void itemClick(AdapterView<?> parent, View view, int position, long id){
        switch (position){
            case 0:
                openActivity(PDFActivity.class);
                break;
            case 1:
                openActivity(OfficeActivity.class);
                break;
        }
    }

    @Override
    protected void initData() {
        super.initData();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        adapter.add("PDF");
        adapter.add("Office");
        lv_demo_list.setAdapter(adapter);
    }
}
