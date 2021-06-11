package com.ble.empty;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import com.ble.base.BaseRelativeLayout;
import com.ble.base.CustomFontTextView;
import com.ble.sz.gvd.proj.R;
/**
 * Created by E on 2017/6/7.
 */
public class BaseEmptyLoadingView extends BaseRelativeLayout {

    private CustomFontTextView emptyTextView = null;

    public BaseEmptyLoadingView(Context context) {
        super(context);

        init();
    }

    public BaseEmptyLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public BaseEmptyLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    @Override
    protected void initViews() {
        LayoutInflater.from(context).inflate(R.layout.base_empty_loading_view , this);

        emptyTextView = (CustomFontTextView)findViewById(R.id.empty_textView);

    }

    public void setEmptyText(String text){
        emptyTextView.setText(text);
    }

}
