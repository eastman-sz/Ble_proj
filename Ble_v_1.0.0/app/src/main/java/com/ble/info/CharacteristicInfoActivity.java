package com.ble.info;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ListView;
import com.ble.base.BaseAppCompactActivitiy;
import com.ble.base.CommonTitleView;
import com.ble.empty.BaseEmptyLoadingView;
import com.ble.sz.gvd.proj.R;
import java.util.ArrayList;

public class CharacteristicInfoActivity extends BaseAppCompactActivitiy {

    private CommonTitleView commonTitleView = null;

    private ListView listView = null;
    private ArrayList<String> list = new ArrayList<String>();
    private CharateristicAdapter adapter = null;

    private String mAddress = null;
    private String serviceUuid = null;
    private ArrayList<String> characteristicUuids = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characteristic_info);

        initActivitys();
    }

    @Override
    protected void getIntentData() {
        mAddress = getIntent().getStringExtra("mAddress");
        serviceUuid = getIntent().getStringExtra("serviceUuid");
        characteristicUuids = getIntent().getStringArrayListExtra("characteristicUuids");
    }

    @Override
    protected void initTitle() {
        commonTitleView = (CommonTitleView)findViewById(R.id.common_title_view);
        commonTitleView.setLeftBtnBackgroud(null);
        commonTitleView.setLeftBtnText("返回");
        commonTitleView.setCenterTitleText(SampleGattAttributes.lookup(serviceUuid));
        commonTitleView.setOnTitleClickListener(new CommonTitleView.OnTitleClickListener() {
            @Override
            public void onLeftBtnClick() {
                onBackPressed();
            }
        });
    }

    @Override
    protected void initViews() {
        listView = (ListView)findViewById(R.id.listView);
        list.addAll(characteristicUuids);
        adapter = new CharateristicAdapter(context , list);
        adapter.setmAddress(mAddress);
        listView.setAdapter(adapter);

        addEmptyView();
    }

    private void addEmptyView(){
        ViewGroup viewGroup =  (ViewGroup) listView.getParent();

        BaseEmptyLoadingView emptyLoadingView = new BaseEmptyLoadingView(context);

        viewGroup.addView(emptyLoadingView);
        listView.setEmptyView(emptyLoadingView);
    }

    @Override
    protected ArrayList<String> addBroadCastAction() {
        ArrayList<String> list = new ArrayList<>();
        list.add("bledataReceived");
        return list;
    }

    @Override
    protected void onBroadCastReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)){
            return;
        }
        if (action.equals("bledataReceived")){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }
}
