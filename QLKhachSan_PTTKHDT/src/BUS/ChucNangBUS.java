/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ChucNangDAO;
import DTO.ChucNangDTO;
import java.util.ArrayList;

/**
 *
 * @author minhd
 */
public class ChucNangBUS {
    public static ArrayList<ChucNangDTO> dscn;
    public ChucNangBUS(){
        
    }
    public ArrayList<ChucNangDTO> docdscn(){
        ChucNangDAO p=new ChucNangDAO();
        dscn=p.getDanhsach();
        return dscn;
    }
    public ChucNangDTO Lay1ChucNang(String s){
        for (ChucNangDTO k : ChucNangBUS.dscn){
            if (k.getTenChucNang().equals(s))
                return k;
        }
        return null;
    }
    public ChucNangDTO Lay1ChucNangTheoMa(String s){
        for (ChucNangDTO k : ChucNangBUS.dscn){
            if (k.getMaChucNang().equals(s))
                return k;
        }
        return null;
    }
}
