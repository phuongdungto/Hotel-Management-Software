/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;
import DAO.DichVuDAO;
import DTO.DichVuDTO;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
/**
 *
 * @author Duy
 */
public class DichVuBUS {
    public static ArrayList<DichVuDTO> dsdv;
    public Pattern pattien= Pattern.compile("^\\d{5,9}$");
    public Pattern patsoluong= Pattern.compile("^\\d{1,5}$");
    public Pattern patso= Pattern.compile("^[0-9]+$");

    public DichVuBUS() {
        
    }
    public ArrayList<DichVuDTO> docdsdv() {
        DichVuDAO data = new DichVuDAO();
        dsdv = data.getDanhsach();
        return dsdv;
    }
    public void them(DichVuDTO p) {
        DichVuDAO data = new DichVuDAO();
        data.them(p);
        int i = DichVuBUS.dsdv.size();
        DichVuBUS.dsdv.add(p);
        int size = DichVuBUS.dsdv.size();
        if(size > i) JOptionPane.showMessageDialog(null, "Thêm thành công!!!");
    }
    public void sua(DichVuDTO p) {
        DichVuDAO data = new DichVuDAO();
        data.sua(p);
        int i = 0;
        int tmp = 0;
        for(DichVuDTO dv: DichVuBUS.dsdv) {
            if(p.getMaDV().equals(dv.getMaDV())) {
                DichVuBUS.dsdv.set(i, p);
                tmp=1;
                break;
            }
            i++;
        }
        if(tmp==1) JOptionPane.showMessageDialog(null, "Sửa thành công!!!");
    }
    public void xoa(DichVuDTO p) {
        DichVuDAO data = new DichVuDAO();
        data.xoa(p);
        int i = 0;
        int size = DichVuBUS.dsdv.size();
        for(DichVuDTO dv: DichVuBUS.dsdv) {
            if(p.getMaDV().equals(dv.getMaDV())) {
                DichVuBUS.dsdv.remove(i);
                break;
            }
            i++;
        }
        int size1 = DichVuBUS.dsdv.size();
        if(size1<size) JOptionPane.showMessageDialog(null, "Xóa thành công!!!");
    }
    public DichVuDTO Lay1madichvu(String mdv){
        for(DichVuDTO dvdto: DichVuBUS.dsdv) {
            if(dvdto.getMaDV().equals(mdv)) {
                return dvdto;
            }
        }
        return null;
    }
    public boolean Ktradv(String tendv, String Dongia) {
        if(tendv.equals("")) {
            JOptionPane.showMessageDialog(null, "Tên dịch vụ đang rỗng");
            return false;
        } 
        if(Dongia.equals("")) {
            JOptionPane.showMessageDialog(null, "Đơn giá dịch vụ đang rỗng");
            return false;
        }
        Matcher mat=pattien.matcher(Dongia);        
        if (mat.matches()==false){
            JOptionPane.showMessageDialog(null, "Số tiền không hợp lệ(Giá phải lớn hơn 10000)");
            return false;
        }
        return true;
    }
    
    public int laysldv() {
        DichVuDAO dvdao = new DichVuDAO();
        int d = dvdao.layslds();
        return d+1;
    }
    
    public ArrayList<DichVuDTO> Timkiemten(String ten) {
        ArrayList<DichVuDTO> dsdvtmp = new ArrayList<>();
        if(!ten.equals("")) {
            for(DichVuDTO dv_dto: DichVuBUS.dsdv) {
                if(dv_dto.getTenDV().toLowerCase().contains(ten.toLowerCase())) {
                    dsdvtmp.add(dv_dto);
                }
            }
        } else {
            DichVuDAO data = new DichVuDAO();
            dsdvtmp = data.getDanhsach();
        }
        return dsdvtmp;
    }
    public ArrayList<DichVuDTO> Timkiemgia(String giatu, String giaden) {
        ArrayList<DichVuDTO> dsdvtmp = new ArrayList<>();
        if(!giatu.equals("") && !giaden.equals("")) {
            int tmp = Integer.parseInt(giatu);
            int tmp1= Integer.parseInt(giaden);
            if(tmp <= tmp1) {
                for(DichVuDTO dv_dto: DichVuBUS.dsdv) {
                    if(tmp <= dv_dto.getDonGia() && dv_dto.getDonGia() <= tmp1) {
                        dsdvtmp.add(dv_dto);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "số tiền phía sau phải nhập lớn hơn số tiền phía trước!!!");
            }
        } else if(!giatu.equals("") && giaden.equals("")) {
            int tmp = Integer.parseInt(giatu);
            for(DichVuDTO dv_dto: DichVuBUS.dsdv) {
                if(tmp <= dv_dto.getDonGia()) {
                    dsdvtmp.add(dv_dto);
                }
            }
        } else if(giatu.equals("") && !giaden.equals("")) {
            int tmp1= Integer.parseInt(giaden);
            for(DichVuDTO dv_dto: DichVuBUS.dsdv) {
                if(tmp1 >= dv_dto.getDonGia()) {
                    dsdvtmp.add(dv_dto);
                }
            }
        } else {
            DichVuDAO data = new DichVuDAO();
            dsdvtmp = data.getDanhsach();
        }
        return dsdvtmp;
    }
    public boolean Ktraso(String dongia){       
        Matcher mat=pattien.matcher(dongia);        
        if (mat.matches()==false){
            return false;
        }
        return true;
    }
}
