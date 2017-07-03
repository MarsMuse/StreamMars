package com.beta.app.stream.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BioServer {

    private  static  int  DEFEAULT_PORT = 8111;
    
    private  static  ServerSocket  server  =  null;
    
    public synchronized  void  start(int port){
        if(server != null){
            return;
        }else{
            try {
                server  =  new ServerSocket(port);
                System.out.println("服务器已启动端口号为："+port);
                while(true){
                    Socket  socket  =   server.accept();
                    
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(server != null){
                    System.out.println("服务器关闭");
                    try {
                        server.close();
                    } catch (IOException e) {
                        
                        e.printStackTrace();
                    }
                    server = null;
                }
            }
        }
        
    }
}
