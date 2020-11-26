package com.tzp.tomcat.two;

import com.tzp.tomcat.first.MyHttpServer;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

public class MyResponse implements HttpServletResponse {

    private OutputStream outputStream;
    private MyRequest myRequest;
    private static final int BUFFER_SIZE = 1024;
    private PrintWriter writer;

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

    public void addCookie(Cookie cookie) {

    }

    public boolean containsHeader(String s) {
        return false;
    }

    public String encodeURL(String s) {
        return null;
    }

    public String encodeRedirectURL(String s) {
        return null;
    }

    public String encodeUrl(String s) {
        return null;
    }

    public String encodeRedirectUrl(String s) {
        return null;
    }

    public void sendError(int i, String s) throws IOException {

    }

    public void sendError(int i) throws IOException {

    }

    public void sendRedirect(String s) throws IOException {

    }

    public void setDateHeader(String s, long l) {

    }

    public void addDateHeader(String s, long l) {

    }

    public void setHeader(String s, String s1) {

    }

    public void addHeader(String s, String s1) {

    }

    public void setIntHeader(String s, int i) {

    }

    public void addIntHeader(String s, int i) {

    }

    public void setStatus(int i) {

    }

    public void setStatus(int i, String s) {

    }

    public int getStatus() {
        return 0;
    }

    public String getHeader(String s) {
        return null;
    }

    public Collection<String> getHeaders(String s) {
        return null;
    }

    public Collection<String> getHeaderNames() {
        return null;
    }

    public String getCharacterEncoding() {
        return null;
    }

    public String getContentType() {
        return null;
    }

    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    public PrintWriter getWriter() throws IOException {
        writer = new PrintWriter(outputStream,true);
        return writer;
    }

    public void setCharacterEncoding(String s) {

    }

    public void setContentLength(int i) {

    }

    public void setContentLengthLong(long l) {

    }

    public void setContentType(String s) {

    }

    public void setBufferSize(int i) {

    }

    public int getBufferSize() {
        return 0;
    }

    public void flushBuffer() throws IOException {

    }

    public void resetBuffer() {

    }

    public boolean isCommitted() {
        return false;
    }

    public void reset() {

    }

    public void setLocale(Locale locale) {

    }

    public Locale getLocale() {
        return null;
    }
}
