package com.tzp.tomcat.two;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MyHttpServer1 {

    private boolean shutdown = false;

    public static String WEB_ROOT = System.getProperty("user.dir")+ File.separator+"webroot";

    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    public static void main(String[] args) {
        MyHttpServer1 myHttpServer = new MyHttpServer1();
        myHttpServer.await();
    }

    public void await(){
        ServerSocket serverSocket = null;
        int port = 8080;
        try {
            serverSocket = new ServerSocket(port,1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        while(!shutdown){
            Socket socket = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                socket = serverSocket.accept();
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
                MyRequest request = new MyRequest(inputStream);
                request.parse();
                MyResponse response = new MyResponse(outputStream);
                response.setMyRequest(request);
                if(request.getUri().startsWith("/servlet/")){
                    ServletProcessor1 servletProcessor1 = new ServletProcessor1();
                    servletProcessor1.process(request,response);
                }else{
                    StaticResourceProcessor staticResourceProcessor = new StaticResourceProcessor();
                    staticResourceProcessor.process(request,response);
                }
                socket.close();
                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            } finally {
            }
        }
    }

}
