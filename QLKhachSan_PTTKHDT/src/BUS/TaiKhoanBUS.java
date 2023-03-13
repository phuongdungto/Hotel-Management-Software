/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.TaiKhoanDAO;
import DTO.TaiKhoanDTO;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author minhd
 */
public class TaiKhoanBUS {
    public static ArrayList<TaiKhoanDTO> dstk;
    public Pattern patuser= Pattern.compile("^[a-zA-Z0-9]{4,20}$");
    public TaiKhoanBUS(){
        
    }
    public ArrayList<TaiKhoanDTO> docdstk(){
        TaiKhoanDAO p=new TaiKhoanDAO();
        dstk=p.getDanhSach();
        return dstk;
    }
    public int Ktradulieu(String tentk,String mk){
        if (tentk.equals("Nhập tên tài khoản") || mk.equals("Nhập mật khẩu"))
            return 0;
        return 1;
    }
    public TaiKhoanDTO Lay1TaiKhoan(String tentk,String mk){
        Xulydulieu dl=new Xulydulieu();
        String matkhau=dl.MaHoaMatKhau(mk);
        for (TaiKhoanDTO k : TaiKhoanBUS.dstk){
            if (k.getUsername().equals(tentk) && k.getPassword().equals(matkhau) && k.getTinhTrang()==1){
                return k;
            }
        }
        return null;
    }
    public TaiKhoanDTO Lay1TaiKhoanTheoMaTK(String matk){
        for (TaiKhoanDTO k : TaiKhoanBUS.dstk){
            if (k.getMaTK().equals(matk)){
                return k;
            }
        }
        return null;
    }
    public ArrayList<TaiKhoanDTO> TimkiemtheoMaNV(String s){
        ArrayList<TaiKhoanDTO> ds=new ArrayList<>();
        for (TaiKhoanDTO k : TaiKhoanBUS.dstk){
            if (k.getMaNV().toLowerCase().contains(s.toLowerCase())){
                ds.add(k);         
            }
        }
        return ds;
    }
    public ArrayList<TaiKhoanDTO> TimkiemtheoTenTaiKhoan(String s){
        ArrayList<TaiKhoanDTO> ds=new ArrayList<>();
        for (TaiKhoanDTO k : TaiKhoanBUS.dstk){
            if (k.getUsername().toLowerCase().contains(s.toLowerCase()))
                ds.add(k);         
        }
        return ds;
    }
    public ArrayList<TaiKhoanDTO> TimkiemtheoMaQuyenTaiKhoan(String s){
        ArrayList<TaiKhoanDTO> ds=new ArrayList<>();
        for (TaiKhoanDTO k : TaiKhoanBUS.dstk){
            if (k.getMaQuyen().contains(s))
                ds.add(k);         
        }
        return ds;
    }
    public int KtradulieuTaiKhoan(TaiKhoanDTO tk){
        if (tk.getMaNV().equals("")){
            JOptionPane.showMessageDialog(null, "Chưa nhập mã nhân viên"); 
            return 0;
        }
        if (tk.getMaQuyen().equals("")){
            JOptionPane.showMessageDialog(null, "Chưa nhập mã quyền"); 
            return 0;
        }
        if (tk.getUsername().equals("")){
            JOptionPane.showMessageDialog(null, "Chưa nhập tên tài khoản"); 
            return 0;
        }
        if (KtraTenTaikhoan(tk.getUsername())==0){
            JOptionPane.showMessageDialog(null, "Tên tài khoản từ 4 đến 32 ký tự (Chỉ có ký tự thường,"
                    + "ký tự in hoa,số)");
            return 0;
        }
        return 1;
    }
    public int KtraTenTaiKhoanKhiThem(TaiKhoanDTO tk){
        ArrayList<TaiKhoanDTO> ds=new ArrayList<>();
        TaiKhoanDAO tkdao=new TaiKhoanDAO();
        ds=tkdao.LayToanBoDanhSach();
        for (TaiKhoanDTO k : ds){
            if (k.getUsername().equals(tk.getUsername())){
                JOptionPane.showMessageDialog(null, "Tên tài khoản đã tồn tại");
                return 0;
            }
            if (k.getMaNV().equals(tk.getMaNV()) && k.getMaQuyen().equals(tk.getMaQuyen())){
                JOptionPane.showMessageDialog(null, "Nhân viên đã có tài khoản với quyền này");
                return 0;
            }
        }
        return 1;
    }
    public int KtraTenTaiKhoanKhiSua(String tentkcu, String tentkmoi){
        ArrayList<TaiKhoanDTO> ds=new ArrayList<>();
        TaiKhoanDAO tkdao=new TaiKhoanDAO();
        ds=tkdao.LayToanBoDanhSach();
        for (TaiKhoanDTO k : ds){
            if (k.getUsername().equals(tentkmoi) && !tentkcu.equals(tentkmoi)){
                JOptionPane.showMessageDialog(null, "Tên tài khoản đã tồn tại");
                return 0;
            }
        }
        return 1;
    }
    public int ThemTaiKhoan(TaiKhoanDTO tk){
        TaiKhoanDAO p=new TaiKhoanDAO();
        p.ThemTaiKhoan(tk);        
        int sl=TaiKhoanBUS.dstk.size();
        TaiKhoanBUS.dstk.add(tk);
        int sl1=TaiKhoanBUS.dstk.size();
        if (sl1>sl)
            return 1;
        return 0;
        
    }
    public int SuaTaiKhoan(TaiKhoanDTO tk){
        TaiKhoanDAO p=new TaiKhoanDAO();
        p.SuaTaiKhoan(tk);        
        int i=0;
        for (TaiKhoanDTO k : TaiKhoanBUS.dstk){
            if (k.getMaTK().equals(tk.getMaTK())){
                TaiKhoanBUS.dstk.set(i,tk);
                break;
            }
            i++;
        }
        return 1;
    }
    public int XoaTaiKhoan(String matk){
        TaiKhoanDAO p=new TaiKhoanDAO();
        p.XoaTaiKhoan(matk);
        int sl=TaiKhoanBUS.dstk.size();
        int i=0;
        for (TaiKhoanDTO k : TaiKhoanBUS.dstk){
            if (k.getMaTK().equals(matk)){
                TaiKhoanBUS.dstk.remove(i);
                break;
            }
            i++;
        }
        int sl1=TaiKhoanBUS.dstk.size();
        if (sl1<sl)
            return 1;
        return 0;
    }
    public int DemSoluongTaiKhoan(){
        TaiKhoanDAO tk=new TaiKhoanDAO();
        return tk.DemSoLuongTaiKhoan();
    }
    public int KtraTenTaikhoan(String s){
        Matcher mat1= patuser.matcher(s);
        if (mat1.matches()==false){            
            return 0;
        }
        return 1;
    }
    public TaiKhoanDTO LayThongTinTaiKhoanTheoMaNVMaQuyen(String manv,String maq){
        for (TaiKhoanDTO k : TaiKhoanBUS.dstk){
            if (k.getMaNV().equals(manv) && k.getMaQuyen().equals(maq))
                return k;
        }
        return null;
    }
    
    public int KtraDoiMatKhau(String mkcu, String mkmoi, String xacnhanmkmoi,String manv, String maq){
        TaiKhoanDTO tk=new TaiKhoanDTO();
        tk=LayThongTinTaiKhoanTheoMaNVMaQuyen(manv, maq);
        Xulydulieu dl=new Xulydulieu();
        if (mkcu.equals("")){
            JOptionPane.showMessageDialog(null, "Chưa nhập mật khẩu cũ");
            return 0;
        }
        if (mkmoi.equals("")){
            JOptionPane.showMessageDialog(null, "Chưa nhập mật khẩu mới");
            return 0;
        }
        if (xacnhanmkmoi.equals("")){
            JOptionPane.showMessageDialog(null, "Chưa nhập xác nhận mật khẩu mới");
            return 0;
        }
        if (!dl.GiaiMaHoaMatKhau(tk.getPassword()).equals(mkcu)){
            JOptionPane.showMessageDialog(null, "Mật khẩu cũ không đúng");
            return 0;
        }
        if (mkcu.equals(mkmoi)){
            JOptionPane.showMessageDialog(null, "Mật khẩu mới giống với mật khẩu cũ");
            return 0;
        }
        if (!mkmoi.equals(xacnhanmkmoi)){
            JOptionPane.showMessageDialog(null, "Xác nhận mật khẩu mới không đúng");
            return 0;
        }
        return 1;
    }
    public int DoiMatKhau(String mkmoi,String manv,String maq){
        TaiKhoanDTO tk=new TaiKhoanDTO();
        tk=LayThongTinTaiKhoanTheoMaNVMaQuyen(manv, maq);
        Xulydulieu dl=new Xulydulieu();
        TaiKhoanDAO tkdao=new TaiKhoanDAO();
        tkdao.DoiMatKhau(tk.getMaTK(), dl.MaHoaMatKhau(mkmoi));
        tk.setPassword(dl.MaHoaMatKhau(mkmoi));
        int i=0;
        for (TaiKhoanDTO k : TaiKhoanBUS.dstk){
            if (k.getMaTK().equals(tk.getMaTK()))
                TaiKhoanBUS.dstk.set(i, tk);
            i++;
        }
        return 1;
    }

//    public static void main(String[] args) {
//        TaiKhoanBUS p=new TaiKhoanBUS();
////        p.docdstk();
//        TaiKhoanDTO tk=new TaiKhoanDTO();
////        tk.setUsername("admin");
//        System.out.println(p.KtraTenTaiKhoanKhiSua("camduyen12","camduyen12"));
//    }
}
