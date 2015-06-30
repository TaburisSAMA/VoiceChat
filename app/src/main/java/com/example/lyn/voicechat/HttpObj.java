package com.example.lyn.voicechat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.util.Log;

public class HttpObj
{
    private static final String THISFILE = "MAP_HTTPOBJ";
    
    private String mUsername;
    private String mPassword;
    private HttpResponse mResponse;
    
    
    public HttpObj(String name, String pwd)
    {
        mUsername = name;
        mPassword = pwd;
    }

    private boolean send(String url)
    {
        if (mUsername == null || mPassword == null)
        {
            Log.e(THISFILE, "username or password is null");
            return false;
        }
        Log.e(THISFILE, "name = " + mUsername + ", pwd = " + mPassword);

        HttpParams httpParameters = new BasicHttpParams();
        // Set the timeout in milliseconds until a connection is established.
        // The default value is zero, that means the timeout is not used.
//        int timeoutConnection = 3000;
//        HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
//        // Set the default socket timeout (SO_TIMEOUT)
//        // in milliseconds which is the timeout for waiting for data.
//        int timeoutSocket = 5000;
//        HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
//        DefaultHttpClient client = new DefaultHttpClient(httpParameters);
        DefaultHttpClient client = new DefaultHttpClient();

        client.getCredentialsProvider().setCredentials(new AuthScope(null, -1), new UsernamePasswordCredentials(mUsername, mPassword));
        HttpGet request = new HttpGet(url);

        try
        {
            mResponse = client.execute(request);
            // 判断连接成功
            if (mResponse.getStatusLine().getStatusCode() == 200)
            {
                Log.e(THISFILE, "连接成功");
                return true;
            }
            
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }
    
    private String receive()
    {
        String jsonString = "";
        InputStream inputStream;
        ByteArrayOutputStream outputStream;
        try
        {
            inputStream = mResponse.getEntity().getContent();
            outputStream = new ByteArrayOutputStream();
            int len = 0;
            byte[] data = new byte[1024]; // 10k
            
            while ((len = inputStream.read(data)) != -1)
            {
                outputStream.write(data, 0, len); // 把所有东西都写道缓冲区
            }
            
            jsonString = new String(outputStream.toByteArray()); // 然后一次性输出
        } 
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
        return jsonString;
    }
    
    public String getData(String url)
    {
        if (!send(url))
        {
            Log.e(THISFILE, "http send request faild");
            return null;
        }
            
        return receive();
    }

    // end..
}
