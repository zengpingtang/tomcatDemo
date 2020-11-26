package com.tzp.tomcat.two;

import java.io.IOException;

public class StaticResourceProcessor {

    public void process(MyRequest request,MyResponse response){
        try {
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
