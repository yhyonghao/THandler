package com.yh.threadandhandlertest;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by yh on 2017-12-24.
 */

public class ThreadAndHandler extends Activity {
    private String ImageUrl = "https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=4029737251,2559774786&fm=173&s=17963CC85E2AB64B0209CC010300C0C2&w=550&h=366&img.JPEG";
    private Button btn1;
    private ImageView imageView;
    private Handler mHandler= new Handler();
//    private Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            imageView.setImageBitmap((Bitmap) msg.obj);
//        }
//    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.button);
        imageView = findViewById(R.id.imageView);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downLoad();
            }
        });

    }

    private void downLoad() {

        new Thread(){
            @Override
            public void run() {
                super.run();
                byte[] data = Download.downLoad(ImageUrl);
                final Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(bitmap);
                    }
                });
            }
        }.start();
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
//                byte[] data = Download.downLoad(ImageUrl);
//                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
//                Message msg = Message.obtain();
//                msg.obj = bitmap;
//                mHandler.sendMessage(msg);
//            }
//        }.start();

    }

//    private void downLoad() {
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
//                byte[] data = Download.downLoad(ImageUrl);
//                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
//                Message msg = Message.obtain();
//                msg.obj = bitmap;
//                mHandler.sendMessage(msg);
//            }
//        }.start();
//
//    }



}
