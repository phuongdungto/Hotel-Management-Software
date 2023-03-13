/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ChiTietHoaDonDAO;
import DAO.HoaDonDAO;
import DAO.PhieuDatPhongDAO;
import DAO.PhongDAO;
import DTO.ChiTietHoaDonDTO;
import DTO.ChiTietKMDichVuDTO;
import DTO.ChiTietPhieuDatPhongDTO;
import DTO.DichVuDTO;
import DTO.HoaDonDTO;
import DTO.KhuyenMaiDichVuDTO;
import DTO.PhieuDatPhongDTO;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author minhd
 */
public class HoaDonBUS {
    public static ArrayList<HoaDonDTO> dshd;
    public HoaDonBUS(){
        
    }
    public ArrayList<HoaDonDTO> docdshd(){
        HoaDonDAO p=new HoaDonDAO();
        dshd=p.getDanhSach();
        return dshd;
    }
    public HoaDonDTO Lay1HoaDonTheoMaHD(String mahd){
        for (HoaDonDTO k : HoaDonBUS.dshd){
            if (k.getMaHD().equals(mahd)){
                return k;
            }
        }
        return null;
    }
    public ArrayList<HoaDonDTO> TimKiemTheoMaNV(String manv){
        ArrayList<HoaDonDTO> ds=new ArrayList<>();
        for (HoaDonDTO k : HoaDonBUS.dshd){
            if (k.getMaNV().equals(manv))
                ds.add(k);
        }
        return ds;
    }
    public ArrayList<HoaDonDTO> TimKiemTheoNgay(String tungay,String denngay){
        ArrayList<HoaDonDTO> ds=new ArrayList<>();
        HoaDonDAO p=new HoaDonDAO();
        ds=p.TimKiemTheoNgay(tungay, denngay);
        return ds;
    }
    public ArrayList<HoaDonDTO> TimKiemTheoTinhTrang(int n){
        ArrayList<HoaDonDTO> ds=new ArrayList<>();
        for (HoaDonDTO k : HoaDonBUS.dshd){
            if (k.getTinhTrang()==n)
                ds.add(k);
        }
        return ds;
    }
    public int KtraTimKiemTheoNgay(String tungay, String denngay){
        Xulydulieu dl=new Xulydulieu();
        String s=dl.chuyendate(tungay);
        String s1=dl.chuyendate(denngay);
        if (dl.TinhSoNgay(s, s1)<0){
            JOptionPane.showMessageDialog(null, "Đến ngày lớn hơn từ ngày");
            return 0;
        }
        return 1;
    }
    public int XoaHoaDon(String mahd){
        HoaDonDAO p=new HoaDonDAO();   
        PhieuDatPhongDAO pdpdao=new PhieuDatPhongDAO();
        PhieuDatPhongBUS pdpbus=new PhieuDatPhongBUS();
        HoaDonDTO hd=new HoaDonDTO();
        hd=Lay1HoaDonTheoMaHD(mahd);
        if (hd.getTinhTrang()==1){
            JOptionPane.showMessageDialog(null, "Không xóa được hóa đơn chưa thanh toán");
            return 0;
        }
        p.XoaHoaDon(mahd);
        pdpdao.XoaPhieuDatPhongKhiXoaHoaDon(hd.getMaPDP());
        int sl=HoaDonBUS.dshd.size();
        int i=0;
        for (HoaDonDTO k : HoaDonBUS.dshd){
            if (k.getMaHD().equals(mahd)){
                HoaDonBUS.dshd.remove(i);
                break;
            }
            i++;
        }
        if (PhieuDatPhongBUS.dspdp==null){
            pdpbus.docdspdp();
        }
        i=0;
        for (PhieuDatPhongDTO k : PhieuDatPhongBUS.dspdp){
            if (k.getMaPDP().equals(hd.getMaPDP())){
                PhieuDatPhongBUS.dspdp.remove(i);
                break;
            }
            i++;
        }
        int sl1=HoaDonBUS.dshd.size();
        if (sl1<sl)
            return 1;
        return 0;
    }
    public HoaDonDTO TinhTienHoaDon(HoaDonDTO hd, PhieuDatPhongDTO pdp){
        Xulydulieu dl=new Xulydulieu();
        long songaythucte=dl.TinhSoNgay(dl.chuyendate(pdp.getNgayThue()), dl.chuyendate(dl.LayNgayThangNamHienTai()));
        ChiTietPhieuDatPhongBUS ctpdpbus=new ChiTietPhieuDatPhongBUS();
        if (ChiTietPhieuDatPhongBUS.dsctpdp==null){
            ctpdpbus.docdsctpdp();
        }
        
        ArrayList<ChiTietPhieuDatPhongDTO> ds1=new ArrayList<>(); //Tính tổng tiền thuê phòng và tiền KM phòng
        ds1=ctpdpbus.LaydsctpdpTheoMaPDP(hd.getMaPDP());
        int tongtienthuephong=0,tongtienkm=0;
        for (ChiTietPhieuDatPhongDTO k : ds1){
            tongtienthuephong+=k.getDonGia();
            tongtienkm+=k.getTienKM();
        }
        tongtienthuephong=tongtienthuephong*(int)songaythucte;
        tongtienkm=tongtienkm*(int)songaythucte;
        
        ChiTietHoaDonBUS cthdbus=new ChiTietHoaDonBUS(); // Tính tổng tiền KM DV
        if (ChiTietHoaDonBUS.dscthd==null){
            cthdbus.docdscthd();
        }
        ArrayList<ChiTietHoaDonDTO> ds2 =new ArrayList<>();
        ds2=cthdbus.LayCTHoaDonTheoMaHD(hd.getMaHD());
        if (ChiTietHoaDonBUS.dscthd!=null){
            for (ChiTietHoaDonDTO k : ds2){
                tongtienkm+=k.getTienKM();
            }
        }
        int tongcong=tongtienthuephong+hd.getTongTienDV()+((tongtienthuephong+hd.getTongTienDV())*10)/100;
        int tongphaitra=tongcong-tongtienkm;
        HoaDonDTO hd1=new HoaDonDTO();
        hd1.setMaHD(hd.getMaHD());
        hd1.setMaPDP(hd.getMaPDP());
        hd1.setMaNV(hd.getMaNV());
        hd1.setNgayLapHD(dl.LayNgayThangNamHienTai());
        hd1.setTongTienThue(tongtienthuephong);
        hd1.setTongTienDV(hd.getTongTienDV());
        hd1.setThue(hd.getThue());
        hd1.setTongCong(tongcong);
        hd1.setTongTienKM(tongtienkm);
        hd1.setTongPhaiTra(tongphaitra);
        hd1.setTinhTrang(1);
        return hd1;
    }
    public void ThanhToanHoaDon(HoaDonDTO hd){
        Xulydulieu dl=new Xulydulieu();
        PhongDAO pdao=new PhongDAO();
        PhieuDatPhongDTO pdp=new PhieuDatPhongDTO();
        PhieuDatPhongDAO pdpdao=new PhieuDatPhongDAO();
        PhieuDatPhongBUS pdpbus=new PhieuDatPhongBUS();
        HoaDonDAO p=new HoaDonDAO();
        pdp=pdpbus.lay1pdp(hd.getMaPDP());
        pdp.setTongTienThue(hd.getTongTienThue());
        pdp.setNgayTra(dl.LayNgayThangNamHienTai());
        long songaythucte=dl.TinhSoNgay(dl.chuyendate(pdp.getNgayThue()), dl.chuyendate(dl.LayNgayThangNamHienTai()));
        ChiTietPhieuDatPhongBUS ctpdpbus=new ChiTietPhieuDatPhongBUS();
        if (ChiTietPhieuDatPhongBUS.dsctpdp==null){
            ctpdpbus.docdsctpdp();
        }
        
        ArrayList<ChiTietPhieuDatPhongDTO> ds1=new ArrayList<>(); //Tính tiền KM phòng
        ds1=ctpdpbus.LaydsctpdpTheoMaPDP(hd.getMaPDP());
        int tongtienkm=0;
        for (ChiTietPhieuDatPhongDTO k : ds1){
            tongtienkm+=k.getTienKM();
            pdao.ThayDoiTinhTrangPhongSauKhiThanhToan(k.getMaPhong());
        }
        tongtienkm=tongtienkm*(int)songaythucte;
        pdp.setTongTienKM(tongtienkm);
        p.CapNhapHoaDonKhiThanhToan(hd);
        pdpdao.CapNhapPDPKhiThanhToan(pdp);
        hd.setTinhTrang(2);
        pdp.setTinhTrang(2);
        int i=0;
        for (HoaDonDTO k : HoaDonBUS.dshd){
            if (k.getMaHD().equals(hd.getMaHD())){
                HoaDonBUS.dshd.set(i, hd);
                break;
            }
            i++;
        }
        i=0;
        for (PhieuDatPhongDTO k : PhieuDatPhongBUS.dspdp){
            if (k.getMaPDP().equals(pdp.getMaPDP())){
                PhieuDatPhongBUS.dspdp.set(i, pdp);
                break;
            }
            i++;
        }
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
    public Pattern so= Pattern.compile("^[0-9]{1,3}$");

    public boolean KtraSo(String s){
        Matcher mat1= so.matcher(s);
        if (mat1.matches()==false){
            JOptionPane.showMessageDialog(null, "Dữ liệu số lượng không hợp lệ");
            return false;
        }
        return true;
    }
    
    public int KtraThemChiTietHoaDon(String madv,String sl){
        if (madv.equals("")){
            JOptionPane.showMessageDialog(null, "Chưa chọn dịch vụ");
            return 0;
        }
        if (sl.equals("")){
            JOptionPane.showMessageDialog(null, "Chưa nhập số lượng");
            return 0;
        }
        if (KtraSo(sl)==false){            
            return 0;
        }
        return 1;
    }
    public int LayPhanTramKM(String makmdv,String madv){
        for (ChiTietKMDichVuDTO k1 : ChiTietKMDichVuBUS.dsctkmdv){
            if (k1.getMaKMDV().equals(makmdv) && k1.getMaDV().equals(madv))
                return k1.getPhanTramKM();                
        }
        return 0;
    }    
    
    public void TinhTienKhiThemChiTiet(String mahd,String madv,String makm,String sl){
        HoaDonDAO hddao=new HoaDonDAO();
        HoaDonDTO hd=new HoaDonDTO();
        hd=Lay1HoaDonTheoMaHD(mahd);
        ChiTietHoaDonDAO cthddao=new ChiTietHoaDonDAO();
        ChiTietHoaDonDTO cthd=new ChiTietHoaDonDTO();
        int sl1=Integer.parseInt(sl);
        int sldv=0,kt=0;
        for (ChiTietHoaDonDTO k : ChiTietHoaDonBUS.dscthd){
            if (k.getMaHD().equals(hd.getMaHD()) && k.getMaDV().equals(madv)){
                cthd=k;
                sldv=k.getSL()+sl1;
                kt=1;
                break;
            }
        }
        if (kt==1){
            int sl2=cthd.getSL();
            cthd.setSL(sldv);
            cthd.setThanhTien(cthd.getDonGia()*sldv);
            int phantram=LayPhanTramKM(cthd.getMaKMDV(), cthd.getMaDV());
            cthd.setTienKM((cthd.getThanhTien()*phantram)/100);
            cthddao.SuaChiTietHoaDon(cthd);
            int i=0;
            for (ChiTietHoaDonDTO k : ChiTietHoaDonBUS.dscthd){
                if (k.getMaHD().equals(cthd.getMaHD()) && k.getMaDV().equals(cthd.getMaDV())){
                    ChiTietHoaDonBUS.dscthd.set(i, cthd);
                    break;
                }
                i++;
            }
            hd.setTongTienDV((hd.getTongTienDV()+cthd.getThanhTien())-(cthd.getDonGia()*sl2));
            int tongcong=hd.getTongTienThue()+hd.getTongTienDV()+((hd.getTongTienThue()+hd.getTongTienDV())*10)/100;
            hd.setTongCong(tongcong);
            hd.setTongTienKM(hd.getTongTienKM()+cthd.getTienKM());
            hd.setTongPhaiTra(hd.getTongCong()-hd.getTongTienKM());
            i=0;
            for (HoaDonDTO k : HoaDonBUS.dshd){
                if (k.getMaHD().equals(hd.getMaHD())){
                    HoaDonBUS.dshd.set(i, hd);
                    break;
                }
                i++;
            }
            hddao.CapNhapHoaDonKhiThanhToan(hd);
        }
        else{
            ChiTietHoaDonDTO cthd1=new ChiTietHoaDonDTO();
            DichVuBUS dvbus=new DichVuBUS();
            DichVuDTO dv=new DichVuDTO();
            dv=dvbus.Lay1madichvu(madv);
            cthd1.setMaHD(mahd);
            cthd1.setMaDV(madv);
            cthd1.setMaKMDV(makm);
            cthd1.setDonGia(dv.getDonGia());
            cthd1.setSL(sl1);
            cthd1.setThanhTien(dv.getDonGia()*sl1);
            int phantram=LayPhanTramKM(cthd1.getMaKMDV(), cthd1.getMaDV());
            cthd1.setTienKM((cthd1.getThanhTien()*phantram)/100);
            cthddao.ThemChiTietHoaDon(cthd1);
            ChiTietHoaDonBUS.dscthd.add(cthd1);
            hd.setTongTienDV(hd.getTongTienDV()+cthd1.getThanhTien());
            int tongcong=hd.getTongTienThue()+hd.getTongTienDV()+((hd.getTongTienThue()+hd.getTongTienDV())*10)/100;
            hd.setTongCong(tongcong);
            hd.setTongTienKM(hd.getTongTienKM()+cthd1.getTienKM());
            hd.setTongPhaiTra(hd.getTongCong()-hd.getTongTienKM());
            int i=0;
            for (HoaDonDTO k : HoaDonBUS.dshd){
                if (k.getMaHD().equals(hd.getMaHD())){
                    HoaDonBUS.dshd.set(i, hd);
                    break;
                }
                i++;
            }
            hddao.CapNhapHoaDonKhiThanhToan(hd);
        }
    }
    
    public void TinhTienKhiXoaChiTiet(String mahd,String madv){
        HoaDonDAO hddao=new HoaDonDAO();
        ChiTietHoaDonDAO cthddao=new ChiTietHoaDonDAO();
        ChiTietHoaDonDTO cthd=new ChiTietHoaDonDTO();
        HoaDonDTO hd=new HoaDonDTO();
        hd=Lay1HoaDonTheoMaHD(mahd);
        for (ChiTietHoaDonDTO k : ChiTietHoaDonBUS.dscthd){
            if (k.getMaHD().equals(mahd) && k.getMaDV().equals(madv)){
                cthd=k;
                break;
            }
        }
        hd.setTongTienDV(hd.getTongTienDV()-cthd.getThanhTien());
        int tongcong=hd.getTongTienThue()+hd.getTongTienDV()+((hd.getTongTienThue()+hd.getTongTienDV())*10)/100;
        hd.setTongCong(tongcong);
        hd.setTongTienKM(hd.getTongTienKM()-cthd.getTienKM());
        hd.setTongPhaiTra(hd.getTongCong()-hd.getTongTienKM());
        int i=0;
        for (ChiTietHoaDonDTO k : ChiTietHoaDonBUS.dscthd){
            if (k.getMaHD().equals(mahd) && k.getMaDV().equals(madv)){
                ChiTietHoaDonBUS.dscthd.remove(i);
                break;
            }
            i++;
        }
        hddao.CapNhapHoaDonKhiThanhToan(hd);        
        cthddao.XoaChiTietHoaDon(mahd, madv);
        i=0;
        for (HoaDonDTO k : HoaDonBUS.dshd){
            if (k.getMaHD().equals(hd.getMaHD())){
                HoaDonBUS.dshd.set(i, hd);
                break;
            }
            i++;
        }
    }
    public ArrayList<HoaDonDTO> docdshdTKngay(String s){
        ArrayList<HoaDonDTO> dshdtkn = new ArrayList<>();
        HoaDonDAO p=new HoaDonDAO();
        dshdtkn=p.getDanhSachTKngay(s);
        return dshdtkn;
    }
    public ArrayList<HoaDonDTO> docdshdTKthang(String m, String y){
        ArrayList<HoaDonDTO> dshdtkt = new ArrayList<>();
        HoaDonDAO p=new HoaDonDAO();
        dshdtkt=p.getDanhSachTKthang(m, y);
        return dshdtkt;
    }
    public ArrayList<HoaDonDTO> docdshdTKnam(String y){
        ArrayList<HoaDonDTO> dshdtkn = new ArrayList<>();
        HoaDonDAO p=new HoaDonDAO();
        dshdtkn=p.getDanhSachTKnam(y);
        return dshdtkn;
    }
    public ArrayList<HoaDonDTO> docdshdTKquy(int q, String y){
        ArrayList<HoaDonDTO> dshdtkq = new ArrayList<>();
        HoaDonDAO p=new HoaDonDAO();
        dshdtkq=p.getDanhSachTKquy(q, y);
        return dshdtkq;
    }
    public ArrayList<HoaDonDTO> docdshdTKkhoangthoigian(String d1, String d2){
        ArrayList<HoaDonDTO> dshdtkktg = new ArrayList<>();
        HoaDonDAO p=new HoaDonDAO();
        dshdtkktg=p.getDanhSachTKkhoangthoigian(d1, d2);
        return dshdtkktg;
    }
     //==================== Duyên ===================//
     public HoaDonDTO Lay1HoaDonTheoMaPDP(String mapdp){
        for (HoaDonDTO k : HoaDonBUS.dshd){
            if (k.getMaPDP().equals(mapdp)){
                return k;
            }
        }
        return null;
    }
     
     //--------------------- Sửa 1 SL hàng hóa ------------------------------//
      public void suaSTienHoaDon(HoaDonDTO hd){
        HoaDonDAO hhDAO = new HoaDonDAO();
        hhDAO.UpdateTienHD(hd);
        int i=0;
        for (HoaDonDTO k : HoaDonBUS.dshd){
            if (k.getMaHD().equals(hd.getMaHD())){
                HoaDonBUS.dshd.set(i, hd);
                break;
            }
            i++;
        }
    }     
      
      public void UpdateTien_SauXoa(String mahd,String madv){
        HoaDonDAO hddao=new HoaDonDAO();
        ChiTietHoaDonDAO cthddao=new ChiTietHoaDonDAO();
        ChiTietHoaDonDTO cthd=new ChiTietHoaDonDTO();
        HoaDonDTO hd=new HoaDonDTO();
        hd=Lay1HoaDonTheoMaHD(mahd);
        for (ChiTietHoaDonDTO k : ChiTietHoaDonBUS.dscthd){
            if (k.getMaHD().equals(mahd) && k.getMaDV().equals(madv)){
                cthd=k;
                break;
            }
        }
        hd.setTongTienDV(hd.getTongTienDV()-cthd.getThanhTien());
        int tongcong=hd.getTongTienThue()+hd.getTongTienDV()+((hd.getTongTienThue()+hd.getTongTienDV())*10)/100;
        hd.setTongCong(tongcong);
        hd.setTongTienKM(hd.getTongTienKM()-cthd.getTienKM());
        hd.setTongPhaiTra(hd.getTongCong()-hd.getTongTienKM());
        int i=0;
        for (ChiTietHoaDonDTO k : ChiTietHoaDonBUS.dscthd){
            if (k.getMaHD().equals(mahd) && k.getMaDV().equals(madv)){
                ChiTietHoaDonBUS.dscthd.remove(i);
                break;
            }
            i++;
        }
        hddao.UpdateTienHD(hd);
        cthddao.xoaCTHD(mahd, madv);
        i=0;
        for (HoaDonDTO k : HoaDonBUS.dshd){
            if (k.getMaHD().equals(hd.getMaHD())){
                HoaDonBUS.dshd.set(i, hd);
                break;
            }
            i++;
        }
    }
      public void them(HoaDonDTO p){
        HoaDonDAO data = new HoaDonDAO();
        Xulydulieu xl= new Xulydulieu();
        data.them(p);
        int i=HoaDonBUS.dshd.size();
        HoaDonBUS.dshd.add(p);
        int i1=HoaDonBUS.dshd.size();
        if (i1>i)
            JOptionPane.showMessageDialog(null, "Thêm thành công");
    }
    public String MaCuoiDS(){
        HoaDonDAO data = new HoaDonDAO();
        int n=data.MaCuoiDS()+1;
        Xulydulieu xldl= new Xulydulieu();
        String s=xldl.TaoMaMoi("HD", n);
        return s;
    }
    public HoaDonDTO Lay1HoaDonTheoPDP(String mapdp){
        for (HoaDonDTO k : HoaDonBUS.dshd){
            if (k.getMaPDP().equals(mapdp)){
                return k;
            }
        }
        return null;
    }
    public void Suahoadon(HoaDonDTO p){
        int i=0;
        HoaDonDAO data = new HoaDonDAO();
        for (HoaDonDTO k : HoaDonBUS.dshd){
            if (k.getMaHD().equals(p.getMaHD())){
                HoaDonBUS.dshd.set(i, p);
                data.SuaHoaDon(p);
                break;
            }
            i++;
        }
    }
    public void XoahoadonbenPDP(String mahd){
        HoaDonDAO p=new HoaDonDAO();
        p.XoaHoaDon(mahd);
    }
}
