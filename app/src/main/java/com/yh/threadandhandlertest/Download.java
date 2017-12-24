package com.yh.threadandhandlertest;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by lenovo on 2016/11/25.
 */
public class Download {
    public static byte[] downLoad(String url){
        byte[] data=null;
        OkHttpClient ok=new OkHttpClient();//创建一个 OkHttpClient 类的对象 该对象是使用 OkHttp 的入口
        Request request=new Request.Builder().url(url).build();//接着要创建的是表示 HTTP 请求的 Request 对象。通过 Request.Builder 这个构建帮助类可以快速的创建出 Request 对象。这里指定了 Request 的 url 为 http://www.baidu.com。
        Call call=ok.newCall(request);//接着通过 OkHttpClient 的 newCall 方法来从 Request 对象中创建一个 Call 对象，
        try {
            Response response=call.execute();//所得到的结果是表示 HTTP 响应的 Response 对象。通过 Response 对象中的不同方法可以访问响应的不同内容。如 headers 方法来获取 HTTP 头，body 方法来获取到表示响应主体内容的 ResponseBody 对象。
            if(response.isSuccessful()){
                data=response.body().bytes();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
