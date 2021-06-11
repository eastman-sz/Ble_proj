package com.ble.sz.gvd.proj;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import com.ble.base.CustomFontTextView;
import com.ble.base.IBaseAdapter;
import com.ble.base.ViewHolder;
import com.sdk.ble.BleState;
import com.sdk.dev.DevInfo;
import java.util.List;
/**
 * Created by E on 2017/6/7.
 */
public class DevAdapter extends IBaseAdapter<DevInfo> {

    public DevAdapter(Context context , List<DevInfo> list){
        super(context , list , R.layout.dev_adapter_view);
    }

    @Override
    public void getConvertView(View convertView, List<DevInfo> list, int position) {
        CustomFontTextView devNameTextView = ViewHolder.getView(convertView , R.id.dev_name_textView);
        CustomFontTextView statusTipTextView = ViewHolder.getView(convertView , R.id.status_tip_textVie);
        ProgressBar progressBar = ViewHolder.getView(convertView , R.id.progress_bar);

        DevInfo devInfo = list.get(position);
        int state = devInfo.getState();

        devNameTextView.setText(devInfo.getName());

        if (state == BleState.STATE_CONNECTED.getState()){

            statusTipTextView.setText("已连接,点击可断开");
            progressBar.setVisibility(View.GONE);
            devNameTextView.setTextColor(context.getResources().getColor(R.color.c27));

        }else if (state == BleState.STATE_DISCONNECTED.getState()){

            statusTipTextView.setText("已断开,点击可连接");
            progressBar.setVisibility(View.GONE);
            devNameTextView.setTextColor(context.getResources().getColor(R.color.c12));

        }else if (state == BleState.STATE_CONNECTING.getState()){

            statusTipTextView.setText("正在连接中...");
            progressBar.setVisibility(View.VISIBLE);
            devNameTextView.setTextColor(context.getResources().getColor(R.color.c12));

        }else {
            devNameTextView.setTextColor(context.getResources().getColor(R.color.c12));
        }
    }
}
