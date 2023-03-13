/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;
import DAO.KhachHangDAO;
import DTO.KhachHangDTO;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
/**
 *
 * @author Duy
 */
public class KhachHangBUS {
    public static ArrayList<KhachHangDTO> dskh;
    public Pattern pattien= Pattern.compile("^\\d{5,9}$");
    public Pattern patsoluong= Pattern.compile("^\\d{1,5}$");
    public Pattern patdienthoai= Pattern.compile("^0[0-9]{9}$");
    public Pattern patso= Pattern.compile("^[0-9]+$");
    public KhachHangBUS(){
        
    }
    public ArrayList<KhachHangDTO> docdskh(){
        KhachHangDAO p=new KhachHangDAO();
        dskh=p.getDanhsach();
        return dskh;
    }
    public void them(KhachHangDTO p) {
        KhachHangDAO data = new KhachHangDAO();
        data.them(p);
        int i = KhachHangBUS.dskh.size();
        KhachHangBUS.dskh.add(p);
        int size = KhachHangBUS.dskh.size();
        if(size>i) JOptionPane.showMessageDialog(null, "Thêm thành công!!!");
    }
    public void sua(KhachHangDTO p) {
        KhachHangDAO data = new KhachHangDAO();
        data.sua(p);
        int i = 0;
        int tmp = 0;
        for(KhachHangDTO kh: KhachHangBUS.dskh) {
            if(p.getMaKH().equals(kh.getMaKH())) {
                KhachHangBUS.dskh.set(i, p);
                tmp=1;
                break;
            }
            i++;
        }
        if(tmp==1) JOptionPane.showMessageDialog(null, "Sửa thành công!!!");
    }
    public void xoa(KhachHangDTO p) {
        KhachHangDAO data = new KhachHangDAO();
        data.xoa(p);
        int i = 0;
        int size = KhachHangBUS.dskh.size();
        for(KhachHangDTO kh: KhachHangBUS.dskh) {
            if(p.getMaKH().equals(kh.getMaKH())) {
                KhachHangBUS.dskh.remove(i);
                break;
            }
            i++;
        }
        int size1 = KhachHangBUS.dskh.size();
        if(size1<size) JOptionPane.showMessageDialog(null, "Xóa thành công!!!");
    }
    public int laysldv() {
        KhachHangDAO khdao = new KhachHangDAO();
        int d = khdao.layslds();
        return d+1;
    }
    public KhachHangDTO LayThongtin1KH(String makh){
        for (KhachHangDTO k : KhachHangBUS.dskh){
            if (k.getMaKH().equals(makh)){
                return k;
            }
        }
        return null;
    }
    public boolean Ktranv(String cmnd, String hokh, String tenkh, String sdt, String datetmp, String diachi) {
        if(cmnd.equals("")) {
            JOptionPane.showMessageDialog(null, "Chứng minh nhân dân khách hàng đang rỗng");
            return false;
        } 
        if(hokh.equals("")) {
            JOptionPane.showMessageDialog(null, "Họ khách hàng đang rỗng");
            return false;
        } 
        if(tenkh.equals("")) {
            JOptionPane.showMessageDialog(null, "Tên khách hàng đang rỗng");
            return false;
        }
        if(sdt.equals("")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại khách hàng đang rỗng");
            return false;
        } 
        if(datetmp.equals("")) {
            JOptionPane.showMessageDialog(null, "Ngày sinh khách hàng đang rỗng");
            return false;
        } 
        if(diachi.equals("")) {
            JOptionPane.showMessageDialog(null, "Địa chỉ khách hàng đang rỗng");
            return false;
        }
        Matcher matso=patso.matcher(cmnd);
        if((cmnd.length()==9 || cmnd.length()==12) && matso.matches()==true) {
        } else {
            JOptionPane.showMessageDialog(null, "CMND phải là số và độ dài là 9 số hoặc 12 số");
            return false;
        }
        Matcher matsdt=patdienthoai.matcher(sdt);        
        if (matsdt.matches()==false){
            JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ(Sdt phải là 10 số và bắt đầu là số 0)");
            return false;
        }
        return true;
    }
    public boolean ktratrung(String s) {
        KhachHangDAO data = new KhachHangDAO();
        ArrayList<KhachHangDTO> dskhtmp = new ArrayList<>();
        dskhtmp=data.getDanhsachktra();
        for(KhachHangDTO kh_dto: dskhtmp) {
            if(kh_dto.getMaKH().equals(s)) {
                return false;
            }
        }
        return true;
    }
    public ArrayList<KhachHangDTO> Timkiemcmnd(String cmnd) {
        ArrayList<KhachHangDTO> dskhtmp = new ArrayList<>();
        Matcher matso=patso.matcher(cmnd);
        if(!cmnd.equals("")) {
            if(matso.matches()==true) {
                for(KhachHangDTO kh_dto: KhachHangBUS.dskh) {
                    if(kh_dto.getMaKH().contains(cmnd)) {
                        dskhtmp.add(kh_dto);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "CMND phải là số");
            }
        } else {
            KhachHangDAO data = new KhachHangDAO();
            dskhtmp = data.getDanhsach();
        }
        return dskhtmp;
    }
    
    public ArrayList<KhachHangDTO> Timkiemten(String ten) {
        ArrayList<KhachHangDTO> dskhtmp = new ArrayList<>();
        if(!ten.equals("")) {
            for(KhachHangDTO kh_dto: KhachHangBUS.dskh) {
                String s=kh_dto.getHo()+" "+kh_dto.getTen();
                if(s.toLowerCase().contains(ten.toLowerCase())) {
                    dskhtmp.add(kh_dto);
                }
            }
        } else {
            KhachHangDAO data = new KhachHangDAO();
            dskhtmp = data.getDanhsach();
        }
        return dskhtmp;
    }
    
    public ArrayList<KhachHangDTO> Timkiemsdt(String sdt) {
        ArrayList<KhachHangDTO> dskhtmp = new ArrayList<>();
        Matcher matsdt=patso.matcher(sdt);   
        if(!sdt.equals("")) {
            if(matsdt.matches()==true) {
                for(KhachHangDTO kh_dto: KhachHangBUS.dskh) {
                    if(kh_dto.getSdt().contains(sdt)) {
                        dskhtmp.add(kh_dto);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Số điện chỉ là những số từ 0 đến 9!!!");
            }
        } else {
            KhachHangDAO data = new KhachHangDAO();
            dskhtmp = data.getDanhsach();
        }
        return dskhtmp;
    }
    
    public ArrayList<KhachHangDTO> Timkiemgioitinh(int gt) {
        ArrayList<KhachHangDTO> dskhtmp = new ArrayList<>();
        if(gt==1) {
            for(KhachHangDTO kh_dto: KhachHangBUS.dskh) {
                if(kh_dto.getGioitinh()==0) {
                    dskhtmp.add(kh_dto);
                }
            }
        } else if(gt==2) {
            for(KhachHangDTO kh_dto: KhachHangBUS.dskh) {
                if(kh_dto.getGioitinh()==1) {
                    dskhtmp.add(kh_dto);
                }
            }
        } else {
            KhachHangDAO data = new KhachHangDAO();
            dskhtmp = data.getDanhsach();
        }
        return dskhtmp;
    }
    
}
