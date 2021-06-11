package com.ble.info;

import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.ble.base.CustomFontTextView;
import com.ble.base.IBaseAdapter;
import com.ble.base.ViewHolder;
import com.ble.sz.gvd.proj.R;
import com.sdk.ble.GattCharacteristicHelper;
import java.util.List;
/**
 * Created by E on 2017/6/7.
 */
public class CharateristicAdapter extends IBaseAdapter<String> {

    private GattCharacteristicHelper characteristicHelper = GattCharacteristicHelper.getInstance();
    private String mAddress = null;

    public CharateristicAdapter(Context context , List<String> list){
        super(context , list , R.layout.characteristic_adapter_view);
    }

    @Override
    public void getConvertView(View convertView, List<String> list, int position) {
        CustomFontTextView nameTextView = ViewHolder.getView(convertView , R.id.name_textview);
        CustomFontTextView characteristic_uuid_textview = ViewHolder.getView(convertView , R.id.characteristic_uuid_textview);
        CustomFontTextView dataTextview = ViewHolder.getView(convertView , R.id.data_textview);

        String characteristicUUid = list.get(position);

        nameTextView.setText(SampleGattAttributes.lookup(characteristicUUid));

        characteristic_uuid_textview.setText(characteristicUUid);

        String data = characteristicHelper.getData(mAddress , characteristicUUid);

        dataTextview.setText(TextUtils.isEmpty(data) ? "" : "< " + data + " >");
    }

    public void setmAddress(String mAddress){
        this.mAddress = mAddress;
    }
}
