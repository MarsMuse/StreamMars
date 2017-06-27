package com.beta.app.generic;

import java.util.ArrayList;
import java.util.List;

public class GenericTest<T> {

    public  List<T> getGenericInfor(Class<T>  classInfo){
        
        List<T>  result  = new ArrayList<T>();
        
        try {
            T a  = classInfo.newInstance();
            
            result.add(a);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public static void main(String[] args) {
        GenericTest<UserEntity>   gt = new GenericTest<UserEntity>();
        
        List<UserEntity> ue = gt.getGenericInfor(UserEntity.class);
        UserEntity u = ue.get(0);
        u.setName("a");
        System.out.println(u.getName());
    }
}
