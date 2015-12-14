package com.zhaoxuan.combowidget.embed;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhaoxuan.combowidget.R;
import com.zhaoxuan.combowidget.flotage.NoDataTip;


/**
 * 自定义控件 NoDataTips
 * Created by lizhaoxuan on 15/12/4.
 */
public class NoDataTips extends LinearLayout implements IEmbedView{
    private TextView textView;
    private ImageView imageView;

    private NoDataTipListener listener;

    public NoDataTips(Context context) {
        super(context);
        init(context, null);
    }

    public NoDataTips(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public NoDataTips(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.widget_no_data_tip, this, true);
        textView = (TextView) findViewById(R.id.textView);
        imageView = (ImageView) findViewById(R.id.imageView);

        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.imageOnClick();
                }
            }
        });

        if (attrs == null) {
            return;
        } else {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.NoDataTips);
            CharSequence keyStr = a.getText(R.styleable.NoDataTips_android_text);
            if (keyStr != null) {
                textView.setText(keyStr);
            }

            a.recycle();
        }

    }

    public String getText() {
        return textView.getText().toString();
    }

    public NoDataTips setText(String tip) {
        textView.setText(tip);
        return this;
    }

    public NoDataTips setListener(NoDataTipListener listener) {
        this.listener = listener;
        return this;
    }

    @Override
    public void show(){
        this.setVisibility(VISIBLE);
    }
    @Override
    public void hide(){
        this.setVisibility(GONE);
    }

    public interface NoDataTipListener {
        void imageOnClick();
    }
}
