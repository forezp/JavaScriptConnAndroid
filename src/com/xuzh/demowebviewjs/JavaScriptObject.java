package com.xuzh.demowebviewjs;

import org.json.JSONObject;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class JavaScriptObject {
    Context mContxt;
    
    
    
    public JavaScriptObject(Context mContxt) {
        this.mContxt = mContxt;
    }
    @JavascriptInterface
    public void invoke(String name ,String t,String callback) {
    	if(name.equals("testFunc")){
    		
    		User u=new User();
    		
    		Toast.makeText(mContxt, t,Toast.LENGTH_LONG).show();
    	}
        Toast.makeText(mContxt, name, Toast.LENGTH_LONG).show();
        
    }
    @JavascriptInterface
    public void fun2(String name) {
        Toast.makeText(mContxt, "调用fun2:" + name, Toast.LENGTH_SHORT).show();
    }
    
}
