package com.beta.app.redis;

import redis.clients.jedis.Jedis;

public class RedisClient {
    
    
    public static void main(String[] args) {
        int i = 100;
        @SuppressWarnings("resource")
        Jedis  jedis  =  new Jedis("127.0.0.1", 6379);
        Jedis  jedis1  =  new Jedis("127.0.0.1", 6379);
        jedis.auth("abc123456");
        jedis1.auth("abc123456");
        while(i-->0){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(jedis1.clientList());
        }
        
        
    }
}
