package com.tzp.tomcat.first;

import java.io.*;
import java.util.Date;

public class MyResponse {

    private OutputStream outputStream;
    private MyRequest myRequest;
    private static final int BUFFER_SIZE = 1024;

    String msg = "Http/1.1 200 OK\r\n"+
            "Server: Microsoft-IIS/4.0"+
            "Date: "+new Date()+
            "Content-Type: text/html\r\n"+
            "Last-Modified: "+new Date()+
            "Content-Length: 65\r\n"+
            "\r\n";

    public MyResponse(OutputStream outputStream){
        this.outputStream = outputStream;
    }

    public void sendStaticResource() throws IOException {
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fileInputStream = null;
        try {
            File file = new File(MyHttpServer.WEB_ROOT,myRequest.getUri());
            if(file.exists()){
                fileInputStream = new FileInputStream(file);
                int i = fileInputStream.read(bytes,0,BUFFER_SIZE);
                outputStream.write(msg.getBytes());
                while (i!=-1){
                    outputStream.write(bytes,0,BUFFER_SIZE);
                    i = fileInputStream.read(bytes,0,BUFFER_SIZE);
                }
            } else {
              String errorMsg = "Http/1.1 404 File Not Found\r\n"+
              "Content-Type: text/html\r\n"+
              "Content-Length: 23\r\n"+
              "\r\n"+
              "<h1>File Not Found</h1>";
              outputStream.write(errorMsg.getBytes());
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        } finally {
            if(fileInputStream!=null){
                fileInputStream.close();
            }
        }
    }

    public MyRequest getMyRequest() {
        return myRequest;
    }

    public void setMyRequest(MyRequest myRequest) {
        this.myRequest = myRequest;
    }
}
