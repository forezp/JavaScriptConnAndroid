package com.xuzh.demowebviewjs;

import org.json.JSONObject;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    TextView mTextview;
    Button mBtn1;
    WebView mWebView;
    Context mContext;

    @SuppressLint({ "JavascriptInterface", "SetJavaScriptEnabled" })
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化
        initViews();

        //设置编码
        mWebView.getSettings().setDefaultTextEncodingName("utf-8");
        //支持js
        mWebView.getSettings().setJavaScriptEnabled(true);
        //设置背景颜色 透明
        mWebView.setBackgroundColor(Color.argb(0, 0, 0, 0));
        //设置本地调用对象及其接口
       // mWebView.addJavascriptInterface(new JavaScriptObject(mContext), "MfsJSBridge");
        mWebView.addJavascriptInterface(new Object(){
        	
        	@JavascriptInterface
            public void invoke(String name ,String t,String callback) {
            	if(name.equals("testFunc")){
            		
            		User u=new User();
            		
            		Toast.makeText(mContext, t,Toast.LENGTH_LONG).show();
            		
            		String strJson = "{\"code\":122, \"msg\":\"1231\", \"data\":null}";
            		//String strJson = JSONObject.toString();
            		mWebView.loadUrl("javascript:"+ callback +"('" + strJson + "')");
            	}
              //  Toast.makeText(mContext, name, Toast.LENGTH_LONG).show();
                
            }
        },"MfsJSBridge");
        //载入js
      //  mWebView.loadUrl("http://www.morefans.cc/MfsJSBridge/demo/1TestFunc.html");
         mWebView.loadUrl("file:///android_asset/1TestFunc.html");  //加载页面  
        //点击调用js中方法
        mBtn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mWebView.loadUrl("javascript:funFromjs()");
                Toast.makeText(mContext, "调用javascript:funFromjs()", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void initViews() {

        mBtn1 = (Button) findViewById(R.id.btn_1);

        mTextview = (TextView) findViewById(R.id.tv_view);
        mWebView = (WebView) findViewById(R.id.wv_view);

        mContext = getApplicationContext();
    }

}
