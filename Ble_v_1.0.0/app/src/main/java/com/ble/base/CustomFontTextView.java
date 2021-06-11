package com.ble.base;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * 自定义字体的TextView。
 * @author E
 */
public class CustomFontTextView extends AppCompatTextView {

	public CustomFontTextView(Context context) {
		super(context);
		
		init();
	}

	public CustomFontTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		init();
	}

	public CustomFontTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		init();
	}
	
	private void init() {
		if (!isInEditMode()) {
//			final Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), getContext().getString(R.string.font_fzlting));
//			final Typeface typeface = TypefaceHelper.getInstance().getTypeface();
//			setTypeface(typeface);
		}
	}

}
