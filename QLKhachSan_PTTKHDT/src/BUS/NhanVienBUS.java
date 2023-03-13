/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Duy
 */
public class NhanVienBUS {
    public static ArrayList<NhanVienDTO> dsnv;
    public Pattern pattien= Pattern.compile("^\\d{5,9}$");
    public Pattern patsoluong= Pattern.compile("^\\d{1,5}$");
    public Pattern patdienthoai= Pattern.compile("^0[0-9]{9}$");
    public Pattern patso= Pattern.compile("^[0-9]+$");
    public NhanVienBUS(){
        
    }
    public ArrayList<NhanVienDTO> docdsnv(){
        NhanVienDAO p=new NhanVienDAO();
        dsnv=p.getDanhsach();
        return dsnv;
    }
    public NhanVienDTO LayThongtin1NV(String manv){
        for (NhanVienDTO k : NhanVienBUS.dsnv){
            if (k.getMaNV().equals(manv)){
                return k;
            }
        }
        return null;
    }
    public void them(NhanVienDTO p) {
        NhanVienDAO data = new NhanVienDAO();
        data.them(p);
        int i = NhanVienBUS.dsnv.size();
        NhanVienBUS.dsnv.add(p);
        int size = NhanVienBUS.dsnv.size();
        if(size>i) JOptionPane.showMessageDialog(null, "Thêm thành công!!!");
    }
    public void sua(NhanVienDTO p) {
        NhanVienDAO data = new NhanVienDAO();
        data.sua(p);
        int i = 0;
        int tmp = 0;
        for(NhanVienDTO nv: NhanVienBUS.dsnv) {
            if(p.getMaNV().equals(nv.getMaNV())) {
                NhanVienBUS.dsnv.set(i, p);
                tmp=1;
                break;
            }
            i++;
        }
        if(tmp==1) JOptionPane.showMessageDialog(null, "Sửa thành công!!!");
    }
    public void xoa(NhanVienDTO p) {
        NhanVienDAO data = new NhanVienDAO();
        data.xoa(p);
        int i = 0;
        int size = NhanVienBUS.dsnv.size();
        for(NhanVienDTO nv: NhanVienBUS.dsnv) {
            if(p.getMaNV().equals(nv.getMaNV())) {
                NhanVienBUS.dsnv.remove(i);
                break;
            }
            i++;
        }
        int size1 = NhanVienBUS.dsnv.size();
        if(size1<size) JOptionPane.showMessageDialog(null, "Xóa thành công!!!");
    }
    public boolean Ktranv(String honv, String tennv, String sdt, String datetmp, String diachi, String tienluong) {
        if(honv.equals("")) {
            JOptionPane.showMessageDialog(null, "Họ nhân viên đang rỗng");
            return false;
        } 
        if(tennv.equals("")) {
            JOptionPane.showMessageDialog(null, "Tên nhân viên đang rỗng");
            return false;
        }
        if(sdt.equals("")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại nhân viên đang rỗng");
            return false;
        } 
        if(datetmp.equals("")) {
            JOptionPane.showMessageDialog(null, "Ngày sinh nhân viên đang rỗng");
            return false;
        } 
        if(diachi.equals("")) {
            JOptionPane.showMessageDialog(null, "Địa chỉ nhân viên đang rỗng");
            return false;
        }
        if(tienluong.equals("")) {
            JOptionPane.showMessageDialog(null, "Tiền lương nhân viên đang rỗng");
            return false;
        } 
        
        Matcher matsdt=patdienthoai.matcher(sdt);        
        if (matsdt.matches()==false){
            JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ(Sdt phải là 10 số và bắt đầu là số 0)");
            return false;
        }
        Matcher mattienluong=pattien.matcher(tienluong);        
        if (mattienluong.matches()==false){
            JOptionPane.showMessageDialog(null, "Tiền lương không hợp lệ(Tiền lương lớn hơn 10000)");
            return false;
        }
        return true;
    }
    
    public int laysldv() {
        NhanVienDAO nvdao = new NhanVienDAO();
        int d = nvdao.layslds();
        return d+1;
    }
    public ArrayList<NhanVienDTO> Timkiemten(String ten) {
        ArrayList<NhanVienDTO> dsnvtmp = new ArrayList<>();
        if(!ten.equals("")) {
            for(NhanVienDTO nv_dto: NhanVienBUS.dsnv) {
                String s=nv_dto.getHo()+" "+nv_dto.getTen();
                if(s.toLowerCase().contains(ten.toLowerCase())) {
                    dsnvtmp.add(nv_dto);
                }
            }
        } else {
            NhanVienDAO data = new NhanVienDAO();
            dsnvtmp = data.getDanhsach();
        }
        return dsnvtmp;
    }
    
    public ArrayList<NhanVienDTO> Timkiemsdt(String sdt) {
        ArrayList<NhanVienDTO> dsnvtmp = new ArrayList<>();
        Matcher matsdt=patso.matcher(sdt);   
        if(!sdt.equals("")) {
            if(matsdt.matches()==true) {
                for(NhanVienDTO nv_dto: NhanVienBUS.dsnv) {
                    if(nv_dto.getSdt().contains(sdt)) {
                        dsnvtmp.add(nv_dto);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Số điện chỉ là những số từ 0 đến 9!!!");
            }
        } else {
            NhanVienDAO data = new NhanVienDAO();
            dsnvtmp = data.getDanhsach();
        }
        return dsnvtmp;
    }
    
    public ArrayList<NhanVienDTO> Timkiemgioitinh(int gt) {
        ArrayList<NhanVienDTO> dsnvtmp = new ArrayList<>();
        if(gt==1) {
            for(NhanVienDTO nv_dto: NhanVienBUS.dsnv) {
                if(nv_dto.getGioitinh()==0) {
                    dsnvtmp.add(nv_dto);
                }
            }
        } else if(gt==2) {
            for(NhanVienDTO nv_dto: NhanVienBUS.dsnv) {
                if(nv_dto.getGioitinh()==1) {
                    dsnvtmp.add(nv_dto);
                }
            }
        } else {
            NhanVienDAO data = new NhanVienDAO();
            dsnvtmp = data.getDanhsach();
        }
        return dsnvtmp;
    }
    public ArrayList<NhanVienDTO> Timkiemluong(String luongtu, String luongden) {
        ArrayList<NhanVienDTO> dsnvtmp = new ArrayList<>();
        if(!luongtu.equals("") && !luongden.equals("")) {
            int tmp = Integer.parseInt(luongtu);
            int tmp1= Integer.parseInt(luongden);
            if(tmp <= tmp1) {
                for(NhanVienDTO nv_dto: NhanVienBUS.dsnv) {
                    if(tmp <= nv_dto.getTienLuong()&& nv_dto.getTienLuong()<= tmp1) {
                        dsnvtmp.add(nv_dto);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "số tiền phía sau phải nhập lớn hơn số tiền phía trước!!!");
            }
        } else if(!luongtu.equals("") && luongden.equals("")) {
            int tmp = Integer.parseInt(luongtu);
            for(NhanVienDTO nv_dto: NhanVienBUS.dsnv) {
                if(tmp <= nv_dto.getTienLuong()) {
                    dsnvtmp.add(nv_dto);
                }
            }
        } else if(luongtu.equals("") && !luongden.equals("")) {
            int tmp1= Integer.parseInt(luongden);
            for(NhanVienDTO nv_dto: NhanVienBUS.dsnv) {
                if(tmp1 >= nv_dto.getTienLuong()) {
                    dsnvtmp.add(nv_dto);
                }
            }
        } else {
            NhanVienDAO data = new NhanVienDAO();
            dsnvtmp = data.getDanhsach();
        }
        return dsnvtmp;
    }
    public boolean Ktraso(String luong){       
        Matcher mat=pattien.matcher(luong);        
        if (mat.matches()==false){
            return false;
        }
        return true;
    }
}
