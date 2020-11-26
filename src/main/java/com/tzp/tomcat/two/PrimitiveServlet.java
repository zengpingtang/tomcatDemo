package com.tzp.tomcat.two;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class PrimitiveServlet implements Servlet {
    String msg = "Http/1.1 200 OK\r\n"+
            "Server: Microsoft-IIS/4.0"+
            "Date: "+new Date()+
            "Content-Type: text/html\r\n"+
            "Last-Modified: "+new Date()+
            "Content-Length: 65\r\n"+
            "\r\n";

    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init");
    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("form service");
        PrintWriter out = servletResponse.getWriter();
        out.println(msg+"Hello. Roses are red.");
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {
        System.out.println("destroy");
    }
}
