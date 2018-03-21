package com.example.laomao.kotlinplayer.widget;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.design.widget.BottomSheetBehavior;
import android.view.View;

/**
 * User: laomao
 * Date: 2016/10/14
 * Time: 13:38
 */

public class BottomSheetDialog extends android.support.design.widget.BottomSheetDialog {

	private BottomSheetBehavior behavior;

	public BottomSheetDialog(@NonNull Context context) {
		super(context);
	}

	public BottomSheetDialog(@NonNull Context context, @StyleRes int theme) {
		super(context, theme);
	}

	protected BottomSheetDialog(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
	}

	@Override
	public void setContentView(@LayoutRes int layoutResId) {
		this.setContentView(View.inflate(getContext(), layoutResId, null));
	}

	@Override
	public void setContentView(View view) {
		super.setContentView(view);
		View parent = (View) view.getParent();
		behavior = BottomSheetBehavior.from(parent);
		behavior.setHideable(false);
		behavior.setSkipCollapsed(true);
		//		behavior.setType(BottomSheetBehavior.STATE_EXPANDED);
	}

	/**
	 * 设置下滑收缩的最低高度
	 * @param height 高度
	 */
	public void setPeekHeight(int height) {
		behavior.setPeekHeight(height);
	}

	@Override
	public void show() {
		//  取消下滑消失
		try {
			getWindow().getDecorView().setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (behavior != null) {
			behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
		}
		super.show();
	}

	@Override
	public void dismiss() {
		//		if(behavior != null){
		//			behavior.setType(BottomSheetBehavior.STATE_HIDDEN);
		//		}
		super.dismiss();
	}
}
