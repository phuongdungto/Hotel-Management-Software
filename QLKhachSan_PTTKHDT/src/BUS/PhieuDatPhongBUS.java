/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.PhieuDatPhongDAO;
import DTO.ChiTietPhieuDatPhongDTO;
import DTO.HoaDonDTO;
import DTO.PhieuDatPhongDTO;
import DTO.PhongDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author T P Dung
 */
public class PhieuDatPhongBUS {
    public static ArrayList<PhieuDatPhongDTO> dspdp;
    public PhieuDatPhongBUS(){
        
    }
    public ArrayList<PhieuDatPhongDTO> docdspdp(){
        PhieuDatPhongDAO data=new PhieuDatPhongDAO();
        Xulydulieu xldl = new Xulydulieu();
        dspdp=data.getDanhSach();
        return dspdp;
    }
    public PhieuDatPhongDTO lay1pdp(String s){
        for(PhieuDatPhongDTO p : PhieuDatPhongBUS.dspdp){
            if(p.getMaPDP().equals(s))
                return p;
        }
        return null;
    }
    public void them(PhieuDatPhongDTO p){
        PhieuDatPhongDAO data = new PhieuDatPhongDAO();
        Xulydulieu xl= new Xulydulieu();
        if(xl.TinhSoNgay(xl.chuyendate(xl.LayNgayThangNamHienTai()), xl.chuyendate(p.getNgayThue()))==0 && xl.TinhSoNgay(xl.chuyendate(xl.LayNgayThangNamHienTai()), xl.chuyendate(p.getNgayTra()))>=0){
            p.setTinhTrang(1);
        }else if(xl.TinhSoNgay(xl.chuyendate(xl.LayNgayThangNamHienTai()), xl.chuyendate(p.getNgayThue()))>0 && xl.TinhSoNgay(xl.chuyendate(xl.LayNgayThangNamHienTai()), xl.chuyendate(p.getNgayThue()))>0){
            p.setTinhTrang(3);
        }
        data.them(p);
        int i=PhieuDatPhongBUS.dspdp.size();
        PhieuDatPhongBUS.dspdp.add(p);
        int i1=PhieuDatPhongBUS.dspdp.size();
        if (i1>i)
            JOptionPane.showMessageDialog(null, "Thêm thành công");
    }
    public String MaCuoiDS(){
        PhieuDatPhongDAO data = new PhieuDatPhongDAO();
        int n=data.MaCuoiDS()+1;
        Xulydulieu xldl= new Xulydulieu();
        String s=xldl.TaoMaMoi("PDP", n);
        return s;
    }
    public String Ma1PDP(String mapdp){
        PhieuDatPhongDAO pdpDAO = new PhieuDatPhongDAO();
        String maPDP =pdpDAO.LayPDP(mapdp);
        return maPDP;
    }
    public void sua(PhieuDatPhongDTO p){
        PhieuDatPhongDAO data = new PhieuDatPhongDAO();
        int i=0;
        Xulydulieu xl= new Xulydulieu();
        if(xl.TinhSoNgay(xl.chuyendate(xl.LayNgayThangNamHienTai()), xl.chuyendate(p.getNgayThue()))==0 && xl.TinhSoNgay(xl.chuyendate(xl.LayNgayThangNamHienTai()), xl.chuyendate(p.getNgayTra()))>=0){
            p.setTinhTrang(1);
        }else if(xl.TinhSoNgay(xl.chuyendate(xl.LayNgayThangNamHienTai()), xl.chuyendate(p.getNgayThue()))>0 && xl.TinhSoNgay(xl.chuyendate(xl.LayNgayThangNamHienTai()), xl.chuyendate(p.getNgayThue()))>0){
            p.setTinhTrang(3);
        }
        for(PhieuDatPhongDTO p1 : PhieuDatPhongBUS.dspdp){
            if(p.getMaPDP().equals(p1.getMaPDP())){
                PhieuDatPhongBUS.dspdp.set(i, p);
                data.sua(p);
                break;
            }
            i++;
        }
    }
   public void xoa(PhieuDatPhongDTO p){
        int input=JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa", "Xóa tài khoản", JOptionPane.YES_NO_OPTION);
        if(input==0){
            if(p.getTinhTrang()==2 || p.getTinhTrang()==3){
                PhieuDatPhongDAO data = new PhieuDatPhongDAO();
                int k=PhieuDatPhongBUS.dspdp.size();
                int i=0;
                for(PhieuDatPhongDTO dv1 : PhieuDatPhongBUS.dspdp){
                    if(p.getMaPDP().equals(dv1.getMaPDP())){
                        PhieuDatPhongBUS.dspdp.remove(i);
                        HoaDonDTO hd = new HoaDonDTO();
                        HoaDonBUS dshd = new HoaDonBUS();
                        hd=dshd.Lay1HoaDonTheoPDP(p.getMaPDP());
                        dshd.XoahoadonbenPDP(hd.getMaHD());
                        data.xoa(p);
                        break;
                    }
                    i++;
                }
                if(p.getTinhTrang()==3){
                    PhongBUS dsp = new PhongBUS();
                    ChiTietPhieuDatPhongBUS ctpdp = new ChiTietPhieuDatPhongBUS();
                    for(ChiTietPhieuDatPhongDTO d : ChiTietPhieuDatPhongBUS.dsctpdp){
                        if(p.getMaPDP().equals(d.getMaPDP())){
                            PhongDTO x = new PhongDTO();
                            x=dsp.lay1p(d.getMaPhong());
                            ctpdp.capnhattrangthaiphong(x, 1);
                        }
                    }
                }
                int k1=PhieuDatPhongBUS.dspdp.size();
                if (k1<k)
                    JOptionPane.showMessageDialog(null, "Xóa thành công");
            }else{
                JOptionPane.showMessageDialog(null, "Khách đang nhận phòng không thể xóa");
            }
        }
    }
    public ArrayList<PhieuDatPhongDTO> timkiemtheokh(String s){
        ArrayList<PhieuDatPhongDTO> dstk = new  ArrayList<>();
        for(PhieuDatPhongDTO dv1 : PhieuDatPhongBUS.dspdp){
            if(dv1.getMaKH().toLowerCase().contains(s.toLowerCase())){
                //System.out.println(dv1.getTen());
                dstk.add(dv1);
            }
        }
        return dstk;
    }
    public ArrayList<PhieuDatPhongDTO> timkiemtheonv(String s){
        ArrayList<PhieuDatPhongDTO> dstk = new  ArrayList<>();
        for(PhieuDatPhongDTO dv1 : PhieuDatPhongBUS.dspdp){
            if(dv1.getMaNV().toLowerCase().contains(s.toLowerCase())){
                //System.out.println(dv1.getTen());
                dstk.add(dv1);
            }
        }
        return dstk;
    }
    public ArrayList<PhieuDatPhongDTO> timkiemnangcao(int tt,String nbd, String nkt){
        ArrayList<PhieuDatPhongDTO> dstimkiem = new  ArrayList<>();
        PhieuDatPhongDAO data = new PhieuDatPhongDAO();
        dstimkiem=data.Timkiemnangcao(tt, nbd, nkt);
        return dstimkiem;
    }
}
