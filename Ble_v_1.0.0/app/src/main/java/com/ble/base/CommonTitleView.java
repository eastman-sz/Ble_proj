package com.ble.base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ble.sz.gvd.proj.R;
import com.utils.lib.ss.info.DeviceInfo;
/**
 * 标题栏。
 * @author E
 */
public class CommonTitleView extends RelativeLayout {

	private Context context = null;
	private TextView topTileTextView = null;
	private RelativeLayout titleLayout = null;
	private RelativeLayout leftLayout = null;
	private RelativeLayout rightLayout = null;
	
	private CustomFontTextView leftTextView = null;
	private ImageView leftRedotImageView = null;
	private CustomFontTextView centerTitleTextView = null;
	private CustomFontTextView rightTextView = null;
	private ImageView rightRedotImageView = null;
	
	private TextView divider_line_textview = null;
	
	private ProgressBar progressBar = null;
	
	private int drawable_Padding = 0;

	private int titleBarHeight = 0;
	
	public CommonTitleView(Context context) {
		super(context);
		this.context = context;
		init();
	}

	public CommonTitleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();
	}

	public CommonTitleView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		init();
	}
	
	private void init(){
		initViews();
		initListeners();
	}
	
	private void initViews(){
		LayoutInflater.from(context).inflate(R.layout.common_title_view, this);
		topTileTextView = (TextView) findViewById(R.id.top_title_view);
		topTileTextView.setVisibility(View.VISIBLE);
		titleLayout = (RelativeLayout) findViewById(R.id.title_layout);
		leftLayout = (RelativeLayout) findViewById(R.id.left_layout);
		rightLayout = (RelativeLayout) findViewById(R.id.right_layout);
		leftTextView = (CustomFontTextView) findViewById(R.id.commom_left_textview);
		leftRedotImageView = (ImageView) findViewById(R.id.left_red_dot_imgview);
		centerTitleTextView = (CustomFontTextView) findViewById(R.id.center_title_textview);
		rightTextView = (CustomFontTextView) findViewById(R.id.commom_right_textview);
		rightRedotImageView = (ImageView) findViewById(R.id.right_red_dot_imgview);
		progressBar = (ProgressBar) findViewById(R.id.common_bar); 
		
		divider_line_textview = (TextView) findViewById(R.id.divider_line);

        int statusBarHeight = 0;

//        int statusBarHeight = DeviceInfo.getStatusBarHeight(context);
//		if (0 != statusBarHeight) {
//			ViewGroup.LayoutParams params = topTileTextView.getLayoutParams();
//			params.height = statusBarHeight;
//			topTileTextView.setLayoutParams(params);
//		}
		
		drawable_Padding = DeviceInfo.dip2px(context, 5);

        titleBarHeight = DeviceInfo.dip2px(context , 48) + statusBarHeight;
	}

	public void initStatusBarHeight(boolean showStatusBarHeight){
        if (showStatusBarHeight){
            int statusBarHeight = DeviceInfo.getStatusBarHeight(context);
            if (0 != statusBarHeight) {
                ViewGroup.LayoutParams params = topTileTextView.getLayoutParams();
                params.height = statusBarHeight;
                topTileTextView.setLayoutParams(params);
            }
            titleBarHeight = DeviceInfo.dip2px(context , 48) + statusBarHeight;
        }
    }
	
	private void initListeners(){
		leftLayout.setOnClickListener(onClickListener);
		rightLayout.setOnClickListener(onClickListener);
	}
	
	public void setLeftBtnText(String text){
		leftTextView.setCompoundDrawablePadding(drawable_Padding);
		leftTextView.setText(text);
	}
	
	public void setLeftBtnText(int resid){
		leftTextView.setCompoundDrawablePadding(drawable_Padding);
		leftTextView.setText(resid);
	}
	
	public void setLeftBtnTextColor(int color){
		leftTextView.setTextColor(color);
	}

	public void setCenterTitleText(String text){
		centerTitleTextView.setText(text);
	}
	
	public void setCenterTitleText(int resid){
		centerTitleTextView.setText(resid);
	}
	
	public void setCenterTitleTextColor(int color){
		centerTitleTextView.setTextColor(color);
	}
	
	public void setRightBtnText(String text){
		rightTextView.setText(text);
	}
	
	public void setRightBtnText(int resid){
		rightTextView.setText(resid);
	}
	
	public void setRightBtnTextColor(int color){
		rightTextView.setTextColor(color);
	}
	
	public void setLeftBtnVisibility(int visibility){
		leftLayout.setVisibility(visibility);
	}
	
	public void setRightBtnVisibility(int visibility){
		rightLayout.setVisibility(visibility);
	}
	
	public void setLeftRedotVisibility(int visibility){
		leftRedotImageView.setVisibility(visibility);
	}	

	public void setRightRedotVisibility(int visibility){
		rightRedotImageView.setVisibility(visibility);
	}
	
	public void setRightBtnEnabled(boolean enabled){
		rightLayout.setEnabled(enabled);
	}
	
	public void setLeftBtnEnabled(boolean enabled){
		leftLayout.setEnabled(enabled);
	}
	
	public void setProgressBarVisibility(int visibility){
		progressBar.setVisibility(visibility);
	}
	
	public TextView getRightBtn() {
		return rightTextView;
	}
	
	public void setLeftBtnBackgroud(int resid){
		Drawable drawable = getResources().getDrawable(resid);
		if (null == drawable) {
			return;
		}
		drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
		leftTextView.setCompoundDrawables(drawable, null, null, null);
	}
	
	public void setLeftBtnBackgroud(Drawable drawable){
		leftTextView.setCompoundDrawables(drawable, null, null, null);
	}
	
	public void setRightBtnBackgroud(int resid){
		Drawable drawable = getResources().getDrawable(resid);
		if (null == drawable) {
			return;
		}
		drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
		rightTextView.setCompoundDrawables(null, null, drawable, null);
	}
	
	public void setRightBtnBackgroud(Drawable drawable){
		rightTextView.setCompoundDrawables(null, null, drawable, null);
	}
	
	public void setTitleBackgroundResource(int resid){
		titleLayout.setBackgroundResource(resid);
		topTileTextView.setBackgroundResource(resid);
	}
	
	public void setTitleBackgroundColor(int color){
		titleLayout.setBackgroundColor(color);
		topTileTextView.setBackgroundColor(color);
	}
	
	public void setProgress(int progress) {
		if (View.VISIBLE != progressBar.getVisibility()) {
			progressBar.setVisibility(View.VISIBLE);
		}
		progressBar.setProgress(progress);
		if (100 == progress) {
			progressBar.setVisibility(View.GONE);
		}
	}
	
	public void setMaxProgress(int max) {
		if (View.VISIBLE != progressBar.getVisibility()) {
			progressBar.setVisibility(View.VISIBLE);
		}
		progressBar.setMax(max);
	}

    public int getTitleBarHeight() {
        return titleBarHeight;
    }

    public void setDividerLineVisibility(int visibility){
		divider_line_textview.setVisibility(visibility);
	}
	
	OnClickListener onClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.left_layout:
				if (null != onTitleClickListener) {
					onTitleClickListener.onLeftBtnClick();
				}
				break;
			case R.id.right_layout:
				if (null != onTitleClickListener) {
					onTitleClickListener.onRightBtnClick();
				}
				break;
			default:
				break;
			}
		}
	};
	
	public OnTitleClickListener onTitleClickListener = null;
	
	public void setOnTitleClickListener(OnTitleClickListener onTitleClickListener) {
		this.onTitleClickListener = onTitleClickListener;
	}

	public static abstract class OnTitleClickListener{
		public void onLeftBtnClick(){};
		public void onRightBtnClick(){};
	}
}
