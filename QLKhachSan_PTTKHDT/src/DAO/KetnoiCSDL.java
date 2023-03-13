/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;

/**
 *
 * @author minhd
 */
public class KetnoiCSDL {
    protected String user="root";
    protected String pass="0972155055";
    protected String localhost="jdbc:mysql://localhost:3306/khachsan_pttkhdt?characterEncoding=UTF-8";
    protected Connection con=null;
    public KetnoiCSDL(){
        
    }
    public Connection getKetnoi(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(localhost,user,pass);
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        return con;
    }
    public ResultSet getselect(String sql){
        Connection con=getKetnoi();
        try{
            Statement stm = con.createStatement();
            return stm.executeQuery(sql);
        }catch(Exception ex){
            ex.getStackTrace();
        }
        return null;
    }
    public void setchange(String sql){
        Connection con=getKetnoi();
        try{
            Statement stm= con.createStatement();
            stm.executeUpdate(sql);
        }catch(Exception ex){
            ex.getStackTrace();
        }
    }
//    public static void main(String[] args) {
//        KetnoiCSDL kn=new KetnoiCSDL();
//    }
}
