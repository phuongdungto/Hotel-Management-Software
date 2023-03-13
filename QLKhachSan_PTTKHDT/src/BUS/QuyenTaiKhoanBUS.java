/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ChiTietQuyenDAO;
import DAO.QuyenTaiKhoanDAO;
import DTO.QuyenTaiKhoanDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author minhd
 */
public class QuyenTaiKhoanBUS {
    public static ArrayList<QuyenTaiKhoanDTO> dsqtk;
    public QuyenTaiKhoanBUS(){
        
    }
    public ArrayList<QuyenTaiKhoanDTO> docdsqtk(){
        QuyenTaiKhoanDAO p=new QuyenTaiKhoanDAO();
        dsqtk=p.getDanhsach();
        return dsqtk;
    }
    public String LayMaQuyenTaiKhoan(String s){
        for (QuyenTaiKhoanDTO k : QuyenTaiKhoanBUS.dsqtk){
            if (k.getTenQuyen().equals(s))
               return k.getMaQuyen();
        }
        return null;
    }
    public QuyenTaiKhoanDTO LayQuyenTaiKhoanTheoMa(String maqtk){
        for (QuyenTaiKhoanDTO k : QuyenTaiKhoanBUS.dsqtk){
            if (k.getMaQuyen().equals(maqtk))
               return k;
        }
        return null;
    }    
    public int ThemQuyenTaiKhoan(QuyenTaiKhoanDTO qtk){
        QuyenTaiKhoanDAO p=new QuyenTaiKhoanDAO();
        p.ThemQuyenTaiKhoan(qtk);
        int sl=QuyenTaiKhoanBUS.dsqtk.size();
        QuyenTaiKhoanBUS.dsqtk.add(qtk);
        int sl1=QuyenTaiKhoanBUS.dsqtk.size();
        if (sl1>sl)
            return 1;
        return 0;
    }
    public int SuaQuyenTaiKhoan(QuyenTaiKhoanDTO qtk){
        QuyenTaiKhoanDAO p=new QuyenTaiKhoanDAO();
        p.SuaQuyenTaiKhoan(qtk);
        ChiTietQuyenDAO p1=new ChiTietQuyenDAO();
        int i=0;
        for (QuyenTaiKhoanDTO k : QuyenTaiKhoanBUS.dsqtk){
            if (k.getMaQuyen().equals(qtk.getMaQuyen())){
                QuyenTaiKhoanBUS.dsqtk.set(i, qtk);
                break;
            }
            i++;
        }
        return 1;
    }
    public int XoaQuyenTaiKhoan(String maq){
        QuyenTaiKhoanDAO p=new QuyenTaiKhoanDAO();
        p.XoaQuyenTaiKhoan(maq);
        int sl=QuyenTaiKhoanBUS.dsqtk.size();
        int i=0;
        for (QuyenTaiKhoanDTO k : QuyenTaiKhoanBUS.dsqtk){
            if (k.getMaQuyen().equals(maq)){
                QuyenTaiKhoanBUS.dsqtk.remove(i);
                break;
            }
            i++;
        }
        int sl1=QuyenTaiKhoanBUS.dsqtk.size();
        if (sl1<sl)
            return 1;
        return 0;
    }
    public ArrayList<QuyenTaiKhoanDTO> TimkiemtheoTenQuyenTaiKhoan(String s){
        ArrayList<QuyenTaiKhoanDTO> ds=new ArrayList<>();
        for (QuyenTaiKhoanDTO k : QuyenTaiKhoanBUS.dsqtk){
            if (k.getTenQuyen().toLowerCase().contains(s.toLowerCase()))
                ds.add(k);         
        }
        return ds;
    }
    public int KtraDulieuQuyen(QuyenTaiKhoanDTO qtk){
        if (qtk.getMaQuyen().equals("")){
            JOptionPane.showMessageDialog(null, "Chưa nhập mã quyền");
            return 0;
        }
        if (qtk.getTenQuyen().equals("")){
            JOptionPane.showMessageDialog(null, "Chưa nhập tên quyền");
            return 0;
        }        
        return 1;
    }
    public int KtraMaQuyenKhiThem(QuyenTaiKhoanDTO qtk){
        ArrayList<QuyenTaiKhoanDTO> ds=new ArrayList<>();
        QuyenTaiKhoanDAO qtkdao=new QuyenTaiKhoanDAO();
        ds=qtkdao.LayToanBoQuyenTaiKhoan();
        for (QuyenTaiKhoanDTO k : ds){
            if (k.getMaQuyen().equals(qtk.getMaQuyen())){
                JOptionPane.showMessageDialog(null, "Mã quyền đã tồn tại");
            return 0;
            }
        }
        return 1;
    }
    public int KtraMaQuyenKhiSua(String qtkcu, String qtkmoi){
        ArrayList<QuyenTaiKhoanDTO> ds=new ArrayList<>();
        QuyenTaiKhoanDAO qtkdao=new QuyenTaiKhoanDAO();
        ds=qtkdao.LayToanBoQuyenTaiKhoan();
        for (QuyenTaiKhoanDTO k : ds){
            if (k.getMaQuyen().equals(qtkmoi) && qtkcu.equals(qtkmoi)==false){
                JOptionPane.showMessageDialog(null, "Mã quyền đã tồn tại");
            return 0;
            }
        }
        return 1;
    }
}
