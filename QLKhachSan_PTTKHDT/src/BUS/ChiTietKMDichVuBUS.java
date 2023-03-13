/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ChiTietKMDichVuDAO;
import DTO.ChiTietKMDichVuDTO;
import DTO.DichVuDTO;
import DTO.KhuyenMaiDichVuDTO;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author T P Dung
 */
public class ChiTietKMDichVuBUS {
    public static ArrayList<ChiTietKMDichVuDTO> dsctkmdv;
    public Pattern patso= Pattern.compile("^[0-9]{1,3}$");
    public ChiTietKMDichVuBUS(){
        
    }
    public ArrayList<ChiTietKMDichVuDTO> docdsctkmdv(){
        ChiTietKMDichVuDAO p=new ChiTietKMDichVuDAO();
        dsctkmdv=p.getDanhSach();
        return dsctkmdv;
    }
    public void them(ChiTietKMDichVuDTO p){
        ChiTietKMDichVuDAO data = new ChiTietKMDichVuDAO();
        data.them(p);
        int i=ChiTietKMDichVuBUS.dsctkmdv.size();
        ChiTietKMDichVuBUS.dsctkmdv.add(p);
        int i1=ChiTietKMDichVuBUS.dsctkmdv.size();
        if (i1>i)
            JOptionPane.showMessageDialog(null, "Thêm thành công");
    }
    public int sua(ChiTietKMDichVuDTO p,ChiTietKMDichVuDTO p2){
        System.out.println(p.getMaKMDV()+" "+p.getMaDV()+" "+p.getPhanTramKM());
        System.out.println(p2.getMaKMDV()+" "+p2.getMaDV()+" "+p2.getPhanTramKM());
        ChiTietKMDichVuDAO data = new ChiTietKMDichVuDAO();
        if(p.getMaDV().equals(p2.getMaDV()) && p.getMaKMDV().equals(p2.getMaKMDV()) && p.getPhanTramKM()!= p2.getPhanTramKM()){
            int i=0;
            for(ChiTietKMDichVuDTO dv1 : ChiTietKMDichVuBUS.dsctkmdv){
                if(p.getMaDV().equals(dv1.getMaDV()) && p.getMaKMDV().equals(dv1.getMaKMDV())){
                    data.sua(p,p2);
                    ChiTietKMDichVuBUS.dsctkmdv.set(i, p2);
                    JOptionPane.showMessageDialog(null, "Sửa thành công");
                    System.out.println("th1");
                    return 1;
                }
                i++;
            }
        }else if(!p.getMaDV().equals(p2.getMaDV()) && p.getMaKMDV().equals(p2.getMaKMDV())){    
            if(ktratrung(p2)==1){
                int i=0;
                for(ChiTietKMDichVuDTO dv1 : ChiTietKMDichVuBUS.dsctkmdv){
                    if(p.getMaDV().equals(dv1.getMaDV()) && p.getMaKMDV().equals(dv1.getMaKMDV())){
                        data.sua(p,p2);
                        ChiTietKMDichVuBUS.dsctkmdv.set(i, p2);
                        JOptionPane.showMessageDialog(null, "Sửa thành công");
                        System.out.println("th2");
                        return 1;
                    }
                    i++;
                }
            }else{
                JOptionPane.showMessageDialog(null, "Dịch vụ đã tồn tại");
                return 0;
            }
        }else{
            JOptionPane.showMessageDialog(null, "Sửa thất bại");
            return 0;
        }
        return 0;
    }
    public void xoa1chitiet(ChiTietKMDichVuDTO p){
        
            ChiTietKMDichVuDAO data = new ChiTietKMDichVuDAO();
            data.xoa1chitiet(p);
            int k=ChiTietKMDichVuBUS.dsctkmdv.size();
            int i=0;
            for(ChiTietKMDichVuDTO dv1 : ChiTietKMDichVuBUS.dsctkmdv){
                if(p.getMaKMDV().equals(dv1.getMaKMDV()) && p.getMaDV().equals(dv1.getMaDV())){
                    ChiTietKMDichVuBUS.dsctkmdv.remove(i);
                    break;
                }
                i++;
            }
            int k1=ChiTietKMDichVuBUS.dsctkmdv.size();
            if (k1<k)
                JOptionPane.showMessageDialog(null, "Xóa thành công");
        
    }
    public void xoatatca(KhuyenMaiDichVuDTO p){
        ChiTietKMDichVuDAO data = new ChiTietKMDichVuDAO();
        data.xoatatcachitiet(p);
        int i=0;
        for(ChiTietKMDichVuDTO dv1 : ChiTietKMDichVuBUS.dsctkmdv){
            if(p.getMaKMDV().equals(dv1.getMaKMDV())){
                ChiTietKMDichVuBUS.dsctkmdv.remove(i);
            }
            i++;
        }
    }
    public String lay1dv(String s){
        DichVuBUS dv = new DichVuBUS();
        if(DichVuBUS.dsdv==null){
            dv.docdsdv();
        }
        for(DichVuDTO p: DichVuBUS.dsdv){
            if(s.equals(p.getMaDV())){
                return p.getTenDV();
            }
        }
        return null;
    }
    public DichVuDTO lay1dvtheoten(String s){
        DichVuBUS dv = new DichVuBUS();
        if(DichVuBUS.dsdv==null){
            dv.docdsdv();
        }
        for(DichVuDTO p: DichVuBUS.dsdv){
            if(s.equals(p.getTenDV())){
                return p;
            }
        }
        return null;
    }
    public int ktratrung(ChiTietKMDichVuDTO p){
        for(ChiTietKMDichVuDTO dv1 : ChiTietKMDichVuBUS.dsctkmdv){
            if(p.getMaKMDV().equals(dv1.getMaKMDV())){
                if(p.getMaDV().equals(dv1.getMaDV())){
                    return 0;
                }
            }
        }
        return 1;
    }
    public int ktraphantram(String s){
        Matcher m = patso.matcher(s);
        if(m.matches()==false || Integer.parseInt(s)>100){
            JOptionPane.showMessageDialog(null, "Chỉ được nhập số và nhỏ hơn 100");
            return 0;
        }
       return 1;
    }
    //=================== Duyên =========================//
        public ChiTietKMDichVuDTO LayTT1CTKMDVTuMaKMDV(String s){
        ChiTietKMDichVuBUS dv = new ChiTietKMDichVuBUS();
            dv.docdsctkmdv();
        for(ChiTietKMDichVuDTO p: ChiTietKMDichVuBUS.dsctkmdv){
            if(p.getMaKMDV().equals(s)){
                return p;
            }
        }
        return null;
    }

    public String LayMaKhuyenMaiDV(String madv){
        KhuyenMaiDichVuBUS kmdvbus=new KhuyenMaiDichVuBUS();
        ChiTietKMDichVuBUS ctkmdvbus=new ChiTietKMDichVuBUS();
        ArrayList<KhuyenMaiDichVuDTO> dskm=new ArrayList<>();
        String s="null";
        if (KhuyenMaiDichVuBUS.dskmdv==null){
            kmdvbus.docdskmdv();
        }
        if (ChiTietKMDichVuBUS.dsctkmdv==null){
            ctkmdvbus.docdsctkmdv();
        }
        for (KhuyenMaiDichVuDTO k : KhuyenMaiDichVuBUS.dskmdv){
            if (k.getTinhTrang()==1)
                dskm.add(k);
        }
        for (KhuyenMaiDichVuDTO k : dskm){
            for (ChiTietKMDichVuDTO k1 : ChiTietKMDichVuBUS.dsctkmdv){
                if (k.getMaKMDV().equals(k1.getMaKMDV()) && k1.getMaDV().equals(madv))
                    return k1.getMaKMDV();
            }
        }
        return s;
    }
}
