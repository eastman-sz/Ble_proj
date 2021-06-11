package com.ble.info;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import com.ble.base.CustomFontTextView;
import com.ble.base.IBaseAdapter;
import com.ble.base.ViewHolder;
import com.ble.sz.gvd.proj.R;
import java.util.List;
import java.util.UUID;
/**
 * Created by E on 2017/6/7.
 */
public class BleInfoAdapter extends IBaseAdapter<BleInfo> {

    public BleInfoAdapter(Context context, List<BleInfo> list){
        super(context , list , R.layout.ble_info_adapter_view);
    }

    @Override
    public void getConvertView(View convertView, List<BleInfo> list, int position) {
        CustomFontTextView nameTextView = ViewHolder.getView(convertView , R.id.name_textView);
        CustomFontTextView uuidTextView = ViewHolder.getView(convertView , R.id.uuid_textVie);
        ImageView nextStepImageView = ViewHolder.getView(convertView , R.id.next_step_imageView);

        BleInfo bleInfo = list.get(position);

        UUID serviceUuid =  bleInfo.getService().getUuid();
        boolean isCharEmpty = bleInfo.getCharacteristicList().isEmpty();


        nameTextView.setText(SampleGattAttributes.lookup(serviceUuid.toString()));
        uuidTextView.setText(serviceUuid.toString());
        nextStepImageView.setVisibility(isCharEmpty ? View.GONE : View.VISIBLE);

    }
}
