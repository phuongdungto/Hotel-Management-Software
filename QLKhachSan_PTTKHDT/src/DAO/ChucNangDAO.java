/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ChucNangDTO;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author minhd
 */
public class ChucNangDAO {
    public ChucNangDAO(){
        
    }
    public ArrayList<ChucNangDTO> getDanhsach(){
        KetnoiCSDL kn=new KetnoiCSDL();
        Connection con=kn.getKetnoi();
        ArrayList<ChucNangDTO> dscn=new ArrayList<>();
        String sql="select * from chuc_nang order by stt ASC";
        try{
            Statement smt=con.createStatement();
            ResultSet rs=smt.executeQuery(sql);
            while(rs.next()){
                ChucNangDTO cn=new ChucNangDTO();
                cn.setMaChucNang(rs.getString(1));
                cn.setTenChucNang(rs.getString(2));
                cn.setHinhanh(rs.getString(3));
                cn.setStt(Integer.parseInt(rs.getString(4)));
                dscn.add(cn);
            }
        }catch(Exception e){
            e.getStackTrace();
        }
        return dscn;
    }
//    public static void main(String[] args) {
//        ChucNangDAO p=new ChucNangDAO();
//    }
}
