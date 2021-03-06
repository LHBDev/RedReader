package org.quantumbadger.redreader.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import org.holoeverywhere.widget.FrameLayout;
import org.holoeverywhere.widget.LinearLayout;
import org.quantumbadger.redreader.R;
import org.quantumbadger.redreader.common.General;

public class LinkDetailsView extends FrameLayout {

	private final String title, subtitle;

	public LinkDetailsView(Context context, final String title, final String subtitle) {
		super(context);
		this.title = title;
		this.subtitle = subtitle;

		setClickable(true);

		final LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.HORIZONTAL);
		addView(layout);
		final int marginPx = General.dpToPixels(context, 8);

		layout.setGravity(Gravity.CENTER_VERTICAL);

		final TypedArray appearance = context.obtainStyledAttributes(new int[]{R.attr.rrIconGlobe});
		final ImageView globe = new ImageView(context);
		globe.setImageDrawable(appearance.getDrawable(0));
		layout.addView(globe);
		((LinearLayout.LayoutParams)globe.getLayoutParams()).setMargins(marginPx, marginPx, marginPx, marginPx);

		final LinearLayout textLayout = new LinearLayout(context);
		textLayout.setOrientation(LinearLayout.VERTICAL);
		layout.addView(textLayout);
		((LinearLayout.LayoutParams)textLayout.getLayoutParams()).setMargins(0, marginPx, marginPx, marginPx);

		{
			final TextView titleView = new TextView(context);
			titleView.setText(title);
			titleView.setTextSize(15f); // TODO scale with comment
			textLayout.addView(titleView);
		}

		if(subtitle != null && !title.equals(subtitle)) {
			final TextView subtitleView = new TextView(context);
			subtitleView.setText(subtitle);
			subtitleView.setTextSize(11f); // TODO scale with comment
			textLayout.addView(subtitleView);
		}

		final RectShape borderShape = new RectShape();
		final ShapeDrawable border = new ShapeDrawable(borderShape);
		border.getPaint().setColor(Color.rgb(128, 128, 128));
		border.getPaint().setStrokeWidth(1f);
		border.getPaint().setStyle(Paint.Style.STROKE);

		setBackground(border);


	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {

		switch(ev.getActionMasked()) {

			case MotionEvent.ACTION_DOWN:
				((ShapeDrawable)getBackground()).getPaint().setColor(Color.rgb(0x00, 0x99, 0xCC));
				((ShapeDrawable)getBackground()).getPaint().setStrokeWidth(2f);
				invalidate();
				break;

			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_CANCEL:
				((ShapeDrawable)getBackground()).getPaint().setColor(Color.rgb(128, 128, 128));
				((ShapeDrawable)getBackground()).getPaint().setStrokeWidth(1f);
				invalidate();
				break;
		}

		return super.onInterceptTouchEvent(ev);
	}
}
