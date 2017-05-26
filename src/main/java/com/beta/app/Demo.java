package com.beta.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.beta.prop.proxy.ProxyDemo;

public class Demo  extends ProxyDemo{
    Demo() {
        super();
    }

    public void test(){
        
    }
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection conn = null;
        try {
            conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/workflow","Beta","abc123456");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Statement st = null;
        try {
            st = conn.createStatement();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ResultSet rs = null;
        try {
            rs  =  st.executeQuery("select * from act_ge_property");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            while(rs.next()){
                System.out.println(rs.getRow());
                System.out.print(rs.getString(1) + " | ");
                System.out.print(rs.getString(2) + " | ");
                System.out.println(rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

}
