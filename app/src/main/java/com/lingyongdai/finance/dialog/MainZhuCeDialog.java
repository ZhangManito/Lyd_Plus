package com.lingyongdai.finance.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.lingyongdai.finance.R;

/**
 * reated by lei on 201609-20.
 */
public class MainZhuCeDialog extends Dialog implements OnClickListener {
        private MyDialogListener listener;
        private LinearLayout myDialog_View;
        private Context mContext;
        private ImageView toreg_close;
        private ImageView toreg_lingqu;
        private String img1s, img2s;
        public interface MyDialogListener{
            void quxiaoListener();
            void sureListener();
        }
        public MainZhuCeDialog(Context context, String img1, String img2, MyDialogListener Listener) {
            super(context, R.style.MyDialog);
            listener = Listener;
            mContext = context;
            img1s = img1;
            img2s = img2;
        }
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.first_come_dialog);
            //出去黑棱角
            getWindow().setBackgroundDrawable(new
                    BitmapDrawable());
            initViews();
            myDialog_View = (LinearLayout)findViewById(R.id.myDialog_View);
            setCanceledOnTouchOutside(false);
        }

        private void initViews(){
            toreg_close = (ImageView) findViewById(R.id.toreg_close);
            toreg_close.setOnClickListener(this);
            toreg_lingqu = (ImageView) findViewById(R.id.toreg_lingqu);
            toreg_lingqu.setOnClickListener(this);

            Glide.with(mContext)
                    .load(img2s)
                    .into(toreg_close);
            Glide.with(mContext)
                    .load(img1s)
                    .into(toreg_lingqu);
        }
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.toreg_close:
                    listener.quxiaoListener();
                    this.dismiss();
                    break;
                case R.id.toreg_lingqu:
                    listener.sureListener();
                    this.dismiss();
                    break;
            }
        }
}
