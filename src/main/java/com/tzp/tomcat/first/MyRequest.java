package com.tzp.tomcat.first;

import java.io.IOException;
import java.io.InputStream;

public class MyRequest {

    private InputStream inputStream;
    private String uri;

    public MyRequest(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void parse(){
        StringBuffer buffer = new StringBuffer(2048);
        int i;
        byte[] bytes = new byte[2048];
        try {
            i = inputStream.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            i = -1;
        } finally {
        }
        for(int j=0;j<i;j++){
            buffer.append((char) bytes[j]);
        }
        System.out.println(buffer.toString());
        uri = parseUri(buffer.toString());

    }

    private String parseUri(String content){
        int index1,index2;
        index1 = content.indexOf(" ");
        if(index1!=-1){
            index2 = content.indexOf(" ",index1+1);
            if(index2>index1){
                return content.substring(index1+1,index2);
            }
        }
        return null;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
