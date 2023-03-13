/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.ChiTietHoaDonBUS;
import BUS.ChiTietPhieuDatPhongBUS;
import BUS.DichVuBUS;
import BUS.HoaDonBUS;
import BUS.NhanVienBUS;
import BUS.PhieuDatPhongBUS;
import BUS.Xulydulieu;
import BUS.iconBUS;
import DTO.ChiTietHoaDonDTO;
import DTO.ChiTietPhieuDatPhongDTO;
import DTO.DichVuDTO;
import DTO.HoaDonDTO;
import DTO.NhanVienDTO;
import DTO.PhieuDatPhongDTO;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author minhd
 */
public class HoaDonGUI {
    protected String font="Times New Roman";
    protected JPanel pHoaDon;
    protected JLabel lbTieude;
    protected JTable tbdshd,tbct;
    protected DefaultTableModel model,mdct;
    protected JScrollPane scr,scct; 
    protected JButton btThem;
    protected JButton btSua;
    protected JButton btXoa;
    protected JButton btTimKiem;
    protected JButton btCapNhat;
    protected JButton btXemChiTiet;
    protected JTextField txTKMaNV;
    protected JDateChooser tungay,denngay;
    protected JTextFieldDateEditor edittu,editden;
    protected JComboBox cbKieuTK,cbTinhTrang;
    protected JButton btChonNV,btChonDV;
    protected HoaDonDTO hd1;
    protected JLabel lbdichvu1,lbTongTienDV1,lbkm;
    protected JTextField lbSL1;
    public HoaDonGUI(){
        
    }
    public JPanel KhoiTaoPanel(int width, int height){
        pHoaDon=new JPanel();
        pHoaDon.setLayout(null);
        pHoaDon.setBounds(0, 0, width, height);
        pHoaDon.setPreferredSize(new Dimension(width,height));
        pHoaDon.setOpaque(true);
        
        lbTieude=new JLabel();
        lbTieude.setText("Hóa đơn");
        lbTieude.setFont(new Font(font,Font.BOLD,26));
        lbTieude.setBounds(400, 20, 200, 50);
        lbTieude.setForeground(Color.BLACK);
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        
        pHoaDon.add(lbTieude);
        
        HoaDonBUS hdbus=new HoaDonBUS();
        if (HoaDonBUS.dshd==null){
            hdbus.docdshd();
        }
        JTable tb=new JTable();
        tb=BangHoaDon(HoaDonBUS.dshd);
        Danhsachhoadon(tb);
        CacNutChucNang();
        formTimkiem();
        ChucNangCacNut();
        
        return pHoaDon;
    }
    
    public JTable BangHoaDon(ArrayList<HoaDonDTO> ds){
        JTable tbds=new JTable();
        tbds.getTableHeader().setBackground(new Color(36,36,36));
        tbds.getTableHeader().setForeground(Color.WHITE);
        tbds.getTableHeader().setFont(new Font(font,0,18));
        tbds.getTableHeader().setPreferredSize(new Dimension(0,30));
        tbds.setFont(new Font(font,0,18));
        tbds.setForeground(Color.BLACK);
        tbds.setRowHeight(25);
                
        model=new DefaultTableModel();
        Vector cottieude=new Vector();
        cottieude.add("STT");
        cottieude.add("Mã hóa đơn");
        cottieude.add("Mã PDP");
        cottieude.add("Mã nhân viên");
        cottieude.add("Ngày lập hóa đơn");
        cottieude.add("Tổng tiền thanh toán");
        cottieude.add("Tình trạng");
        if (model.getRowCount()==0){
            model=new DefaultTableModel(cottieude, 0);
        }
        Xulydulieu dl=new Xulydulieu();
        int i=0;
        for (HoaDonDTO k : ds){            
                i++;
                Vector dong=new Vector();
                dong.add(i);
                dong.add(k.getMaHD());
                dong.add(k.getMaPDP());
                dong.add(k.getMaNV());
                dong.add(dl.chuyendate(k.getNgayLapHD()));
                dong.add(dl.ChuyenKieuTien(String.valueOf(k.getTongPhaiTra())));
                if (k.getTinhTrang()==1)
                    dong.add("Chưa thanh toán");
                else
                    dong.add("Đã thanh toán");
                model.addRow(dong);            
        }
        tbds.setModel(model);
        tbds.getColumnModel().getColumn(0).setMaxWidth(50);
        tbds.getColumnModel().getColumn(1).setMinWidth(100);
        tbds.getColumnModel().getColumn(2).setMinWidth(100);
        tbds.getColumnModel().getColumn(3).setMinWidth(100);
        tbds.getColumnModel().getColumn(4).setMinWidth(150); 
        tbds.getColumnModel().getColumn(5).setMinWidth(150); 
        tbds.getColumnModel().getColumn(6).setMinWidth(150); 
        
        //--------------------Căn giữa trong bảng-----------------------//
        DefaultTableCellRenderer center=new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        tbds.setDefaultRenderer(tbds.getColumnClass(0), center);
        tbds.updateUI();
        //--------------------------------------------------------------//
        
        return tbds;
    }
    
    public void Danhsachhoadon(JTable tb){                                        
        tbdshd=new JTable();
        tbdshd=tb;
        scr=new JScrollPane();
        scr.setViewportView(tbdshd);
        scr.setBounds(10, 250, 980, 350); // Tùy mỗi người
        
        pHoaDon.add(scr);    
        tbdshd.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int i=tbdshd.getSelectedRow();
                String matk=tbdshd.getModel().getValueAt(i, 1).toString();
                HoaDonBUS hdbus=new HoaDonBUS();
                HoaDonDTO hd=new HoaDonDTO();        
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
            }
        });
    }
    
    public void CacNutChucNang(){
        iconBUS ic=new iconBUS();
        
        ImageIcon iconThem=new ImageIcon(ic.iconthanhtoan);
        btThem=new JButton();
        btThem.setText("Thanh toán");
        btThem.setIcon(iconThem);
        btThem.setFont(new Font(font,0,18));
        btThem.setBackground(new Color(36,36,36));
        btThem.setForeground(Color.WHITE);
        btThem.setFocusable(false);
        btThem.setBorderPainted(false);
        btThem.setBounds(580, 210, 150, 30);
        
        pHoaDon.add(btThem);
        
        ImageIcon iconSua=new ImageIcon(ic.iconsua);
        btSua=new JButton();
        btSua.setText("Sửa");
        btSua.setIcon(iconSua);
        btSua.setFont(new Font(font,0,18));
        btSua.setBackground(new Color(36,36,36));
        btSua.setForeground(Color.WHITE);
        btSua.setFocusable(false);
        btSua.setBorderPainted(false);
        btSua.setBounds(740, 210, 120, 30);
        
        pHoaDon.add(btSua);
        
        ImageIcon iconXoa=new ImageIcon(ic.iconxoa);
        btXoa=new JButton();
        btXoa.setText("Xóa");
        btXoa.setIcon(iconXoa);
        btXoa.setFont(new Font(font,0,18));
        btXoa.setBackground(new Color(36,36,36));
        btXoa.setForeground(Color.WHITE);
        btXoa.setFocusable(false);
        btXoa.setBorderPainted(false);
        btXoa.setBounds(870, 210, 120, 30);
        
        pHoaDon.add(btXoa);
        
        ImageIcon iconCapnhap=new ImageIcon(ic.iconcapnhat);
        btCapNhat=new JButton();
        btCapNhat.setText("Cập nhật");
        btCapNhat.setIcon(iconCapnhap);
        btCapNhat.setFont(new Font(font,0,18));
        btCapNhat.setBackground(new Color(36,36,36));
        btCapNhat.setForeground(Color.WHITE);
        btCapNhat.setFocusable(false);
        btCapNhat.setBorderPainted(false);
        btCapNhat.setBounds(10, 610, 150, 30);
        
        pHoaDon.add(btCapNhat);
        
        ImageIcon iconTimkiem=new ImageIcon(ic.icontimkiem);
        btTimKiem=new JButton();
        btTimKiem.setText("Tìm kiếm");
        btTimKiem.setIcon(iconTimkiem);
        btTimKiem.setFont(new Font(font,0,18));
        btTimKiem.setBackground(new Color(36,36,36));
        btTimKiem.setForeground(Color.WHITE);
        btTimKiem.setFocusable(false);
        btTimKiem.setBorderPainted(false);
        btTimKiem.setBounds(580, 130, 150, 30);
        
        pHoaDon.add(btTimKiem);

        ImageIcon iconxemchitiet = new ImageIcon(ic.iconxem);
        btXemChiTiet = new JButton("Xem chi tiết",iconxemchitiet);
        btXemChiTiet.setBounds(410, 210, 160, 30);
        btXemChiTiet.setBackground(new Color(36,36,36));
        btXemChiTiet.setFont(new Font(font,0,18));
        btXemChiTiet.setForeground(Color.WHITE);
        btXemChiTiet.setBorderPainted(false);
        btXemChiTiet.setFocusPainted(false);
        
        pHoaDon.add(btXemChiTiet);
                
    }
    
    public void formTimkiem(){
        JLabel lbTKMa=new JLabel("Nhập mã nhân viên:");
        lbTKMa.setFont(new Font(font,0,18));
        lbTKMa.setForeground(Color.BLACK);
        lbTKMa.setBounds(20, 90, 150, 20);            
        
        pHoaDon.add(lbTKMa);
        
        txTKMaNV=new JTextField();
        txTKMaNV.setFont(new Font(font,0,18));
        txTKMaNV.setForeground(Color.BLACK);
        txTKMaNV.setBounds(200, 90, 150, 25);
        txTKMaNV.setEditable(false);
        txTKMaNV.setForeground(Color.BLACK);
        txTKMaNV.setBackground(Color.WHITE);        
        
        pHoaDon.add(txTKMaNV);
        
        iconBUS ic=new iconBUS();
        ImageIcon iconChon=new ImageIcon(ic.iconchon);
        
        btChonNV=new JButton();
        btChonNV.setForeground(Color.WHITE);
        btChonNV.setBackground(new Color(36,36,36));        
        btChonNV.setIcon(iconChon);
        btChonNV.setBounds(370, 90, 35, 25);
        
//        pHoaDon.add(btChonNV);
        
        JLabel lbNgayLapHD=new JLabel("Ngày lập hóa đơn:");
        lbNgayLapHD.setFont(new Font(font,0,18));
        lbNgayLapHD.setForeground(Color.BLACK);
        lbNgayLapHD.setBounds(20, 140, 150, 20);            
        
        pHoaDon.add(lbNgayLapHD);
        
        tungay=new JDateChooser();
        tungay.setBounds(200, 140, 150, 25);
        tungay.setFont(new Font(font,0,18));
        tungay.setDateFormatString("dd-MM-yyyy");
        edittu = (JTextFieldDateEditor) tungay.getDateEditor();
        edittu.setEditable(false);
        
        pHoaDon.add(tungay);
        
        JLabel lbden=new JLabel("-");
        lbden.setFont(new Font(font,0,18));
        lbden.setForeground(Color.BLACK);
        lbden.setBounds(370, 140, 20, 20);  
        
        pHoaDon.add(lbden);
        
        denngay=new JDateChooser();
        denngay.setBounds(410, 140, 150, 25);
        denngay.setFont(new Font(font,0,18));
        denngay.setDateFormatString("dd-MM-yyyy");
        editden = (JTextFieldDateEditor) denngay.getDateEditor();
        editden.setEditable(false);
        
        pHoaDon.add(denngay);
        
        String[] kieutk={"Chọn kiểu tìm kiếm","Tìm kiếm theo mã nhân viên", "Tìm kiếm theo ngày lập hóa đơn",
        "Tìm kiếm theo tình trạng"};
        
        cbKieuTK=new JComboBox(kieutk);
        cbKieuTK.setSelectedIndex(0);
        cbKieuTK.setBounds(580, 90, 250, 25);
        cbKieuTK.setBackground(Color.WHITE);
        
        pHoaDon.add(cbKieuTK);
        
        JLabel lbTinhTrang=new JLabel("Tình trạng:");
        lbTinhTrang.setFont(new Font(font,0,18));
        lbTinhTrang.setForeground(Color.BLACK);
        lbTinhTrang.setBounds(20, 190, 150, 20);  
        
        pHoaDon.add(lbTinhTrang);
        
        String[] tinhtrang={"Chọn tình trạng","Chưa thanh toán", "Đã thanh toán"};
        
        cbTinhTrang=new JComboBox(tinhtrang);
        cbTinhTrang.setSelectedIndex(0);
        cbTinhTrang.setBounds(200, 190, 150, 25);
        cbTinhTrang.setBackground(Color.WHITE);
        cbTinhTrang.setEnabled(false);
        
        pHoaDon.add(cbTinhTrang);
        
        btChonNV.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                BangChonNhanVien();
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btChonNV.setBackground(Color.DARK_GRAY);
                btChonNV.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                btChonNV.setBackground(new Color(36,36,36));
            }
        });
    }
    
    public void ChucNangCacNut(){                
        
        cbKieuTK.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent arg0) {
                int k=cbKieuTK.getSelectedIndex();
                if (k==0){
                    txTKMaNV.setEnabled(false);
                    pHoaDon.remove(btChonNV);
                    edittu.setEditable(false);
                    editden.setEditable(false);
                    cbTinhTrang.setEnabled(false);
                }
                if (k==1){
                    txTKMaNV.setEditable(false);
                    pHoaDon.add(btChonNV);
                    edittu.setEditable(false);
                    editden.setEditable(false);
                    cbTinhTrang.setEnabled(false);
                }
                if (k==2){
                    txTKMaNV.setEnabled(false);
                    pHoaDon.remove(btChonNV);
                    edittu.setEditable(true);
                    editden.setEditable(true);
                    cbTinhTrang.setEnabled(false);
                }
                if (k==3){
                    txTKMaNV.setEnabled(false);
                    pHoaDon.remove(btChonNV);
                    edittu.setEditable(false);
                    editden.setEditable(false);
                    cbTinhTrang.setEnabled(true);
                }
            }
        });
                
        btThem.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {  
                int i=tbdshd.getSelectedRow();
                if (i<0)
                    JOptionPane.showMessageDialog(pHoaDon, "Chưa chọn hóa đơn cần thanh toán");
                else{
                    String mahd=tbdshd.getModel().getValueAt(i, 1).toString();
                    HoaDonBUS hdbus=new HoaDonBUS();
                    PhieuDatPhongBUS pdpbus=new PhieuDatPhongBUS();
                    HoaDonDTO hd=new HoaDonDTO();
                    PhieuDatPhongDTO pdp=new PhieuDatPhongDTO();                    
                    hd=hdbus.Lay1HoaDonTheoMaHD(mahd);
                    pdp=pdpbus.lay1pdp(hd.getMaPDP());
                    if (hd.getTinhTrang()==2)
                        JOptionPane.showMessageDialog(pHoaDon, "Hóa đơn đã thanh toán");
                    else{
                        if (pdp.getTinhTrang()==3)
                            JOptionPane.showMessageDialog(pHoaDon, "Khách chưa nhận phòng nên không thể thanh toán hóa đơn");
                        else
                            Thanhtoanhoadon(mahd);
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btThem.setBackground(Color.DARK_GRAY);
                btThem.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                btThem.setBackground(new Color(36,36,36));
            }
        });
        
        btSua.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {        
                int i=tbdshd.getSelectedRow();
                if (i<0)
                    JOptionPane.showMessageDialog(pHoaDon, "Chưa chọn hóa đơn cần sửa");
                else{                    
                    String mahd=tbdshd.getModel().getValueAt(i, 1).toString();
                    HoaDonBUS hdbus=new HoaDonBUS();
                    HoaDonDTO hd=new HoaDonDTO();
                    hd=hdbus.Lay1HoaDonTheoMaHD(mahd);
                    if (hd.getTinhTrang()==1)
                        SuaThongTinHoaDon(mahd);
                    else
                        JOptionPane.showMessageDialog(pHoaDon, "Không cho phép sửa hóa đơn đã thanh toán");
                }
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btSua.setBackground(Color.DARK_GRAY);
                btSua.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                btSua.setBackground(new Color(36,36,36));
            }
        });
        
        btXoa.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {   
                int i=tbdshd.getSelectedRow();
                if (i<0)
                    JOptionPane.showMessageDialog(pHoaDon, "Chưa chọn hóa đơn cần xóa");
                else{
                    int input=JOptionPane.showConfirmDialog(pHoaDon, "Bạn có chắc muốn xóa", "Xóa hóa đơn", JOptionPane.YES_NO_OPTION);
                    if (input==0){
                        String mahd=tbdshd.getModel().getValueAt(i, 1).toString();
                        HoaDonBUS hdbus=new HoaDonBUS();
                        if (hdbus.XoaHoaDon(mahd)==1){
                            JOptionPane.showMessageDialog(pHoaDon, "Xóa thành công");
                            model.removeRow(i);
                            tbdshd.setModel(model);
                        }
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btXoa.setBackground(Color.DARK_GRAY);
                btXoa.setCursor(new Cursor(Cursor.HAND_CURSOR));                
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                btXoa.setBackground(new Color(36,36,36));
            }
        });
        
        btTimKiem.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {   
                int k=cbKieuTK.getSelectedIndex();
                HoaDonBUS hdbus=new HoaDonBUS();
                ArrayList<HoaDonDTO> ds=new ArrayList<>();
                if (k==0){
                    JOptionPane.showMessageDialog(pHoaDon, "Chưa chọn kiểu tìm kiếm");
                }
                if (k==1){
                    if (txTKMaNV.getText().equals(""))
                        JOptionPane.showMessageDialog(pHoaDon, "Chưa nhập mã nhân viên");
                    else{
                        pHoaDon.remove(scr);    
                        ds=hdbus.TimKiemTheoMaNV(txTKMaNV.getText());
                        JTable tb=new JTable();
                        tb=BangHoaDon(ds);
                        Danhsachhoadon(tb);
                    }
                }
                if (k==2){
                    if (tungay.getDate()==null || denngay.getDate()==null)
                        JOptionPane.showMessageDialog(pHoaDon, "Chưa chọn thời gian lập hóa đơn");
                    else{
                        SimpleDateFormat date1 = new SimpleDateFormat("yyyy-MM-dd");
                        String day1 = String.valueOf(date1.format(tungay.getDate()));
                        String day2 = String.valueOf(date1.format(denngay.getDate()));
                        if (hdbus.KtraTimKiemTheoNgay(day1, day2)==1){
                            pHoaDon.remove(scr);  
                            ds=hdbus.TimKiemTheoNgay(day1,day2);
                            JTable tb=new JTable();
                            tb=BangHoaDon(ds);
                            Danhsachhoadon(tb);
                        }
                    }
                }
                if (k==3){
                    int k1=cbTinhTrang.getSelectedIndex();
                    if (k1==0)
                        JOptionPane.showMessageDialog(pHoaDon, "Chưa chọn tình trạng");
                    else{
                        pHoaDon.remove(scr);  
                        ds=hdbus.TimKiemTheoTinhTrang(k1);
                        JTable tb=new JTable();
                        tb=BangHoaDon(ds);
                        Danhsachhoadon(tb);
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btTimKiem.setBackground(Color.DARK_GRAY);
                btTimKiem.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                btTimKiem.setBackground(new Color(36,36,36));
            }
        });
        
        btCapNhat.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {   
                pHoaDon.remove(scr);               
                JTable tb=new JTable();
                tb=BangHoaDon(HoaDonBUS.dshd);
                Danhsachhoadon(tb);
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btCapNhat.setBackground(Color.DARK_GRAY);
                btCapNhat.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                btCapNhat.setBackground(new Color(36,36,36));
            }
        });  
        
        btXemChiTiet.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {   
                int i=tbdshd.getSelectedRow();
                if (i<0){
                    JOptionPane.showMessageDialog(pHoaDon, "Chưa chọn hóa đơn cần xem chi tiết");
                }
                else{
                    String mahd1=tbdshd.getModel().getValueAt(i, 1).toString();
                    XemChiTiet(mahd1);
                }
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btXemChiTiet.setBackground(Color.DARK_GRAY);
                btXemChiTiet.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                btXemChiTiet.setBackground(new Color(36,36,36));
            }
        });  
        
    }
    
    public void BangChonNhanVien(){
        JFrame fchon=new JFrame();
        fchon.setLayout(null);
        fchon.setBounds(0, 0, 550, 400);
        fchon.setPreferredSize(new Dimension(550,400));
        
        //---------------------------------Hiện giữa màn hình-----------------------------//
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        fchon.setLocation((int)d.getWidth()/2 - (int)fchon.getPreferredSize().getWidth()/2,
                      (int)d.getHeight()/2 - (int)fchon.getPreferredSize().getHeight()/2);
        //--------------------------------------------------------------------------------//
        
        JLabel lbTieude=new JLabel("Chọn nhân viên");
        lbTieude.setFont(new Font(font,Font.BOLD,25));
        lbTieude.setForeground(Color.WHITE);
        lbTieude.setBackground(new Color(36,36,36));
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        lbTieude.setBounds(0, 0, 550, 35);
        lbTieude.setOpaque(true);
        
        fchon.add(lbTieude);
        
        NhanVienBUS nvbus=new NhanVienBUS();
        if (NhanVienBUS.dsnv==null){
            nvbus.docdsnv();
        }
        JTable tbnv=new JTable();
        tbnv.getTableHeader().setBackground(new Color(36,36,36));
        tbnv.getTableHeader().setForeground(Color.WHITE);
        tbnv.getTableHeader().setFont(new Font(font,0,18));
        tbnv.getTableHeader().setPreferredSize(new Dimension(0,30));
        tbnv.setFont(new Font(font,0,18));
        tbnv.setForeground(Color.BLACK);
        tbnv.setRowHeight(25);
        
        DefaultTableModel md=new DefaultTableModel();
        Vector cot=new Vector();
        cot.add("STT");
        cot.add("Mã nhân viên");
        cot.add("Họ Tên");
        cot.add("Giới tính");
        if (md.getRowCount()==0){
            md=new DefaultTableModel(cot, 0);
        }
        int i=1;
        for (NhanVienDTO k : NhanVienBUS.dsnv){
            Vector dong=new Vector();
            dong.add(i);
            dong.add(k.getMaNV());
            dong.add(k.getHo()+" "+k.getTen());
            if (k.getGioitinh()==0)
                dong.add("Nam");
            else
                dong.add("Nữ");
            md.addRow(dong);
            i++;
        }
        tbnv.setModel(md);
        tbnv.getColumnModel().getColumn(0).setMinWidth(50);
        tbnv.getColumnModel().getColumn(1).setMinWidth(120);
        tbnv.getColumnModel().getColumn(2).setMinWidth(250);
        tbnv.getColumnModel().getColumn(3).setMinWidth(100);                
        
        //--------------------Căn giữa trong bảng-----------------------//
        DefaultTableCellRenderer center=new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        tbnv.setDefaultRenderer(tbnv.getColumnClass(0), center);
        tbnv.updateUI();
        //--------------------------------------------------------------//
        
        JScrollPane sc=new JScrollPane();
        sc.setViewportView(tbnv);
        sc.setBounds(10, 140, 520, 200);
        
        fchon.add(sc);
        
        tbnv.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int i=tbnv.getSelectedRow();
                String manv=tbnv.getModel().getValueAt(i, 1).toString();
                txTKMaNV.setText(manv);
                fchon.setVisible(false);
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
            }
        });
        
        fchon.setVisible(true);
    }
    
    public void XemChiTiet(String mahd){
        JFrame fxem=new JFrame();
        fxem.setLayout(null);
        fxem.setBounds(0, 0, 720, 670);
        fxem.setPreferredSize(new Dimension(720,670));
//        fxem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //---------------------------------Hiện giữa màn hình-----------------------------//
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        fxem.setLocation((int)d.getWidth()/2 - (int)fxem.getPreferredSize().getWidth()/2,
                      (int)d.getHeight()/2 - (int)fxem.getPreferredSize().getHeight()/2);
        //--------------------------------------------------------------------------------//
        
        JLabel lbXem=new JLabel("Xem chi tiết hóa đơn");
        lbXem.setFont(new Font(font,Font.BOLD,25));
        lbXem.setForeground(Color.WHITE);
        lbXem.setBackground(new Color(36,36,36));
        lbXem.setHorizontalAlignment(JLabel.CENTER);
        lbXem.setBounds(0, 0, 720, 35);
        lbXem.setOpaque(true);
        
        fxem.add(lbXem);
        
        HoaDonDTO hd=new HoaDonDTO();
        HoaDonBUS hdbus=new HoaDonBUS();
        Xulydulieu dl=new Xulydulieu();
        if (HoaDonBUS.dshd==null){
            hdbus.docdshd();
        }
        hd=hdbus.Lay1HoaDonTheoMaHD(mahd);
        
        JLabel lbMaHD=new JLabel("Mã hóa đơn: ");
        lbMaHD.setForeground(Color.BLACK);
        lbMaHD.setBounds(40, 60, 110, 20);
        lbMaHD.setFont(new Font(font,0,18));
        
        fxem.add(lbMaHD);
        
        JLabel lbMaHD1=new JLabel(mahd);
        lbMaHD1.setForeground(Color.BLACK);
        lbMaHD1.setBounds(170, 60, 80, 20);
        lbMaHD1.setFont(new Font(font,Font.BOLD,18));
        lbMaHD1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        lbMaHD1.setHorizontalAlignment(JLabel.CENTER);
        
        fxem.add(lbMaHD1);
        
        JLabel lbMaPDP=new JLabel("Mã phiếu đặt phòng: ");
        lbMaPDP.setForeground(Color.BLACK);
        lbMaPDP.setBounds(280, 60, 160, 20);
        lbMaPDP.setFont(new Font(font,0,18));
        
        fxem.add(lbMaPDP);
        
        JLabel lbMaPDP1=new JLabel(hd.getMaPDP());
        lbMaPDP1.setForeground(Color.BLACK);
        lbMaPDP1.setBounds(450, 60, 110, 20);
        lbMaPDP1.setFont(new Font(font,Font.BOLD,18));
        lbMaPDP1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        lbMaPDP1.setHorizontalAlignment(JLabel.CENTER);
        
        fxem.add(lbMaPDP1);
        
        JLabel lbMaNV=new JLabel("Mã nhân viên: ");
        lbMaNV.setForeground(Color.BLACK);
        lbMaNV.setBounds(40, 100, 120, 20);
        lbMaNV.setFont(new Font(font,0,18));
        
        fxem.add(lbMaNV);
        
        JLabel lbMaNV1=new JLabel(hd.getMaNV());
        lbMaNV1.setForeground(Color.BLACK);
        lbMaNV1.setBounds(170, 100, 80, 20);
        lbMaNV1.setFont(new Font(font,Font.BOLD,18));
        lbMaNV1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        lbMaNV1.setHorizontalAlignment(JLabel.CENTER);
        
        fxem.add(lbMaNV1);
        
        JLabel lbNgayLap=new JLabel("Ngày lập hóa đơn: ");
        lbNgayLap.setForeground(Color.BLACK);
        lbNgayLap.setBounds(280, 100, 160, 20);
        lbNgayLap.setFont(new Font(font,0,18));
        
        fxem.add(lbNgayLap);
        
        JLabel lbNgayLap1=new JLabel(dl.chuyendate(hd.getNgayLapHD()));
        lbNgayLap1.setForeground(Color.BLACK);
        lbNgayLap1.setBounds(450, 100, 110, 20);
        lbNgayLap1.setFont(new Font(font,Font.BOLD,18));
        lbNgayLap1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        lbNgayLap1.setHorizontalAlignment(JLabel.CENTER);
        
        fxem.add(lbNgayLap1);
        
        JLabel lbTongTienThue=new JLabel("Tổng tiền thuê: ");
        lbTongTienThue.setForeground(Color.BLACK);
        lbTongTienThue.setBounds(40, 140, 160, 20);
        lbTongTienThue.setFont(new Font(font,0,18));
        
        fxem.add(lbTongTienThue);
        
        JLabel lbTongTienThue1=new JLabel(dl.ChuyenKieuTien(String.valueOf(hd.getTongTienThue())));
        lbTongTienThue1.setForeground(Color.BLACK);
        lbTongTienThue1.setBounds(230, 140, 140, 20);
        lbTongTienThue1.setFont(new Font(font,Font.BOLD,18));
        
        fxem.add(lbTongTienThue1);
        
        JLabel lbThue=new JLabel("Thuế: ");
        lbThue.setForeground(Color.BLACK);
        lbThue.setBounds(390, 140, 60, 20);
        lbThue.setFont(new Font(font,0,18));
        
        fxem.add(lbThue);
        
        JLabel lbThue1=new JLabel(dl.ChuyenKieuTien(String.valueOf(hd.getThue()))+"%");
        lbThue1.setForeground(Color.BLACK);
        lbThue1.setBounds(440, 140, 50, 20);
        lbThue1.setFont(new Font(font,Font.BOLD,18));
        
        fxem.add(lbThue1);
        
        JLabel lbTongTienDV=new JLabel("Tổng tiền dịch vụ: ");
        lbTongTienDV.setForeground(Color.BLACK);
        lbTongTienDV.setBounds(40, 180, 160, 20);
        lbTongTienDV.setFont(new Font(font,0,18));
        
        fxem.add(lbTongTienDV);
        
        JLabel lbTongTienDV1=new JLabel(dl.ChuyenKieuTien(String.valueOf(hd.getTongTienDV())));
        lbTongTienDV1.setForeground(Color.BLACK);
        lbTongTienDV1.setBounds(230, 180, 140, 20);
        lbTongTienDV1.setFont(new Font(font,Font.BOLD,18));
        
        fxem.add(lbTongTienDV1);
        
        JLabel lbTongCong=new JLabel("Tổng cộng: ");
        lbTongCong.setForeground(Color.BLACK);
        lbTongCong.setBounds(40, 220, 160, 20);
        lbTongCong.setFont(new Font(font,0,18));
        
        fxem.add(lbTongCong);
        
        JLabel lbTongCong1=new JLabel(dl.ChuyenKieuTien(String.valueOf(hd.getTongCong())));
        lbTongCong1.setForeground(Color.BLACK);
        lbTongCong1.setBounds(230, 220, 140, 20);
        lbTongCong1.setFont(new Font(font,Font.BOLD,18));
        
        fxem.add(lbTongCong1);
        
        JLabel lbTongTienKM=new JLabel("Tổng tiền KM: ");
        lbTongTienKM.setForeground(Color.BLACK);
        lbTongTienKM.setBounds(40, 260, 160, 20);
        lbTongTienKM.setFont(new Font(font,0,18));
        
        fxem.add(lbTongTienKM);
        
        JLabel lbTongTienKM1=new JLabel(dl.ChuyenKieuTien(String.valueOf(hd.getTongTienKM())));
        lbTongTienKM1.setForeground(Color.BLACK);
        lbTongTienKM1.setBounds(230, 260, 140, 20);
        lbTongTienKM1.setFont(new Font(font,Font.BOLD,18));
        
        fxem.add(lbTongTienKM1);
        
        JLabel lbTongTienPhaiTra=new JLabel("Tổng tiền thanh toán: ");
        lbTongTienPhaiTra.setForeground(Color.BLACK);
        lbTongTienPhaiTra.setBounds(40, 300, 180, 20);
        lbTongTienPhaiTra.setFont(new Font(font,0,18));
        
        fxem.add(lbTongTienPhaiTra);
        
        JLabel lbTongTienPhaiTra1=new JLabel(dl.ChuyenKieuTien(String.valueOf(hd.getTongPhaiTra())));
        lbTongTienPhaiTra1.setForeground(Color.BLACK);
        lbTongTienPhaiTra1.setBounds(230, 300, 140, 20);
        lbTongTienPhaiTra1.setFont(new Font(font,Font.BOLD,18));
      
        fxem.add(lbTongTienPhaiTra1);
        
        JLabel lbTinhTrang=new JLabel("Tình trạng: ");
        lbTinhTrang.setForeground(Color.BLACK);
        lbTinhTrang.setBounds(390, 180, 180, 20);
        lbTinhTrang.setFont(new Font(font,0,18));
        
        fxem.add(lbTinhTrang);
        
        JLabel lbTinhTrang1=new JLabel();
        lbTinhTrang1.setForeground(Color.BLACK);
        lbTinhTrang1.setBounds(390, 220, 160, 20);
        lbTinhTrang1.setFont(new Font(font,Font.BOLD,18));
        lbTinhTrang1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        lbTinhTrang1.setHorizontalAlignment(JLabel.CENTER);
        if (hd.getTinhTrang()==1){
            lbTinhTrang1.setText("Chưa thanh toán");
            lbTinhTrang1.setForeground(Color.red);
        }
        else{
            lbTinhTrang1.setText("Đã thanh toán");
            lbTinhTrang1.setForeground(Color.GREEN);
        }
      
        fxem.add(lbTinhTrang1);
        
        ChiTietHoaDonBUS cthdbus=new ChiTietHoaDonBUS();
        if (ChiTietHoaDonBUS.dscthd==null){
            cthdbus.docdscthd();
        }
        ArrayList<ChiTietHoaDonDTO> ds =new ArrayList<>();
        ds=cthdbus.LayCTHoaDonTheoMaHD(mahd);
        
        JTable tbct=new JTable();
        tbct.getTableHeader().setBackground(new Color(36,36,36));
        tbct.getTableHeader().setForeground(Color.WHITE);
        tbct.getTableHeader().setFont(new Font(font,0,18));
        tbct.getTableHeader().setPreferredSize(new Dimension(0,30));
        tbct.setFont(new Font(font,0,18));
        tbct.setForeground(Color.BLACK);
        tbct.setRowHeight(25);
        
        DefaultTableModel md=new DefaultTableModel();
        Vector cot=new Vector();
        cot.add("STT");
        cot.add("Mã dịch vụ");
        cot.add("Mã KM dịch vụ");
        cot.add("Đơn giá");
        cot.add("Số lượng");
        cot.add("Thành tiền");
        cot.add("Tiền KM");
        if (md.getRowCount()==0){
            md=new DefaultTableModel(cot, 0);
        }
        int i=1;
        for (ChiTietHoaDonDTO k : ds){
            Vector dong=new Vector();
            dong.add(i);
            dong.add(k.getMaDV());
            dong.add(k.getMaKMDV());
            dong.add(k.getDonGia());
            dong.add(k.getSL());
            dong.add(k.getThanhTien());
            dong.add(k.getTienKM());
            md.addRow(dong);
            i++;
        }
        tbct.setModel(md);
        tbct.getColumnModel().getColumn(0).setMinWidth(50);
        tbct.getColumnModel().getColumn(1).setMinWidth(100);
        tbct.getColumnModel().getColumn(2).setMinWidth(130);
        tbct.getColumnModel().getColumn(3).setMinWidth(100);
        tbct.getColumnModel().getColumn(4).setMinWidth(80);
        tbct.getColumnModel().getColumn(5).setMinWidth(100);
        tbct.getColumnModel().getColumn(6).setMinWidth(100);
        
        //--------------------Căn giữa trong bảng-----------------------//
        DefaultTableCellRenderer center=new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        tbct.setDefaultRenderer(tbct.getColumnClass(0), center);
        tbct.updateUI();
        //--------------------------------------------------------------//
        
        JScrollPane sc=new JScrollPane();
        sc.setViewportView(tbct);
        sc.setBounds(10, 350, 680, 240);
        
        fxem.add(sc);
        
        JButton btThoat =new JButton("Thoát");
        btThoat.setFont(new Font(font,0,18));
        btThoat.setForeground(Color.WHITE);
        btThoat.setBounds(285, 600, 150, 25);
        btThoat.setBackground(new Color(36,36,36));
        btThoat.setFocusable(false);
        btThoat.setBorderPainted(false);
        
        fxem.add(btThoat);
        
        btThoat.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                fxem.setVisible(false);            
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btThoat.setBackground(Color.DARK_GRAY);
                btThoat.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                btThoat.setBackground(new Color(36,36,36));
            }
        });
        
        fxem.setVisible(true);
    }
    
    public void Thanhtoanhoadon(String mahd){
        JFrame fthanhtoan=new JFrame();
        fthanhtoan.setLayout(null);
        fthanhtoan.setBounds(0, 0, 1230, 670);
        fthanhtoan.setPreferredSize(new Dimension(1230,670));        
        
        //---------------------------------Hiện giữa màn hình-----------------------------//
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        fthanhtoan.setLocation((int)d.getWidth()/2 - (int)fthanhtoan.getPreferredSize().getWidth()/2,
                      (int)d.getHeight()/2 - (int)fthanhtoan.getPreferredSize().getHeight()/2);
        //--------------------------------------------------------------------------------//
        
        JLabel lbThanhToan=new JLabel("Thanh toán hóa đơn");
        lbThanhToan.setFont(new Font(font,Font.BOLD,25));
        lbThanhToan.setForeground(Color.WHITE);
        lbThanhToan.setBackground(new Color(36,36,36));
        lbThanhToan.setHorizontalAlignment(JLabel.CENTER);
        lbThanhToan.setBounds(0, 0, 1230, 35);
        lbThanhToan.setOpaque(true);
        
        fthanhtoan.add(lbThanhToan);
        
        HoaDonDTO hd=new HoaDonDTO();
        HoaDonBUS hdbus=new HoaDonBUS();
        Xulydulieu dl=new Xulydulieu();
        if (HoaDonBUS.dshd==null){
            hdbus.docdshd();
        }
        hd=hdbus.Lay1HoaDonTheoMaHD(mahd);
        ChiTietHoaDonBUS cthdbus=new ChiTietHoaDonBUS();
        if (ChiTietHoaDonBUS.dscthd==null){
            cthdbus.docdscthd();
        }
        ArrayList<ChiTietHoaDonDTO> ds =new ArrayList<>();
        ds=cthdbus.LayCTHoaDonTheoMaHD(mahd);
        PhieuDatPhongBUS pdpbus=new PhieuDatPhongBUS();
        ChiTietPhieuDatPhongBUS ctpdpbus=new ChiTietPhieuDatPhongBUS();
        if (PhieuDatPhongBUS.dspdp==null){
            pdpbus.docdspdp();
        }
        if (ChiTietPhieuDatPhongBUS.dsctpdp==null){
            ctpdpbus.docdsctpdp();
        }
        PhieuDatPhongDTO pdp=new PhieuDatPhongDTO();
        pdp=pdpbus.lay1pdp(hd.getMaPDP());
        ArrayList<ChiTietPhieuDatPhongDTO> ds1=new ArrayList<>();
        ds1=ctpdpbus.LaydsctpdpTheoMaPDP(hd.getMaPDP());
        
        hd1=new HoaDonDTO();
        hd1=hdbus.TinhTienHoaDon(hd, pdp);
        hd1.setMaNV(DangNhapGUI.MaNV);
        
        JLabel lbMaHD=new JLabel("Mã hóa đơn: ");
        lbMaHD.setForeground(Color.BLACK);
        lbMaHD.setBounds(40, 60, 110, 20);
        lbMaHD.setFont(new Font(font,0,18));
        
        fthanhtoan.add(lbMaHD);
        
        JLabel lbMaHD1=new JLabel(hd1.getMaHD());
        lbMaHD1.setForeground(Color.BLACK);
        lbMaHD1.setBounds(170, 60, 80, 20);
        lbMaHD1.setFont(new Font(font,Font.BOLD,18));
        lbMaHD1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        lbMaHD1.setHorizontalAlignment(JLabel.CENTER);
        
        fthanhtoan.add(lbMaHD1);
        
        JLabel lbMaPDP=new JLabel("Mã phiếu đặt phòng: ");
        lbMaPDP.setForeground(Color.BLACK);
        lbMaPDP.setBounds(280, 60, 160, 20);
        lbMaPDP.setFont(new Font(font,0,18));
        
        fthanhtoan.add(lbMaPDP);
        
        JLabel lbMaPDP1=new JLabel(hd1.getMaPDP());
        lbMaPDP1.setForeground(Color.BLACK);
        lbMaPDP1.setBounds(450, 60, 110, 20);
        lbMaPDP1.setFont(new Font(font,Font.BOLD,18));
        lbMaPDP1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        lbMaPDP1.setHorizontalAlignment(JLabel.CENTER);
        
        fthanhtoan.add(lbMaPDP1);
        
        JLabel lbMaNV=new JLabel("Mã nhân viên: ");
        lbMaNV.setForeground(Color.BLACK);
        lbMaNV.setBounds(40, 100, 120, 20);
        lbMaNV.setFont(new Font(font,0,18));
        
        fthanhtoan.add(lbMaNV);
        
        JLabel lbMaNV1=new JLabel(hd1.getMaNV());
        lbMaNV1.setForeground(Color.BLACK);
        lbMaNV1.setBounds(170, 100, 80, 20);
        lbMaNV1.setFont(new Font(font,Font.BOLD,18));
        lbMaNV1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        lbMaNV1.setHorizontalAlignment(JLabel.CENTER);
        
        fthanhtoan.add(lbMaNV1);
        
        JLabel lbNgayLap=new JLabel("Ngày lập hóa đơn: ");
        lbNgayLap.setForeground(Color.BLACK);
        lbNgayLap.setBounds(280, 100, 160, 20);
        lbNgayLap.setFont(new Font(font,0,18));
        
        fthanhtoan.add(lbNgayLap);
        
        JLabel lbNgayLap1=new JLabel(dl.chuyendate(hd1.getNgayLapHD()));
        lbNgayLap1.setForeground(Color.BLACK);
        lbNgayLap1.setBounds(450, 100, 110, 20);
        lbNgayLap1.setFont(new Font(font,Font.BOLD,18));
        lbNgayLap1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        lbNgayLap1.setHorizontalAlignment(JLabel.CENTER);
        
        fthanhtoan.add(lbNgayLap1);
        
        JLabel lbTongTienThue=new JLabel("Tổng tiền thuê: ");
        lbTongTienThue.setForeground(Color.BLACK);
        lbTongTienThue.setBounds(40, 140, 160, 20);
        lbTongTienThue.setFont(new Font(font,0,18));
        
        fthanhtoan.add(lbTongTienThue);
        
        JLabel lbTongTienThue1=new JLabel(dl.ChuyenKieuTien(String.valueOf(hd1.getTongTienThue())));
        lbTongTienThue1.setForeground(Color.BLACK);
        lbTongTienThue1.setBounds(230, 140, 140, 20);
        lbTongTienThue1.setFont(new Font(font,Font.BOLD,18));
        
        fthanhtoan.add(lbTongTienThue1);
        
        JLabel lbThue=new JLabel("Thuế: ");
        lbThue.setForeground(Color.BLACK);
        lbThue.setBounds(390, 140, 60, 20);
        lbThue.setFont(new Font(font,0,18));
        
        fthanhtoan.add(lbThue);
        
        JLabel lbThue1=new JLabel(dl.ChuyenKieuTien(String.valueOf(hd1.getThue()))+"%");
        lbThue1.setForeground(Color.BLACK);
        lbThue1.setBounds(440, 140, 50, 20);
        lbThue1.setFont(new Font(font,Font.BOLD,18));
        
        fthanhtoan.add(lbThue1);
        
        JLabel lbTongTienDV=new JLabel("Tổng tiền dịch vụ: ");
        lbTongTienDV.setForeground(Color.BLACK);
        lbTongTienDV.setBounds(40, 180, 160, 20);
        lbTongTienDV.setFont(new Font(font,0,18));
        
        fthanhtoan.add(lbTongTienDV);
        
        JLabel lbTongTienDV1=new JLabel(dl.ChuyenKieuTien(String.valueOf(hd1.getTongTienDV())));
        lbTongTienDV1.setForeground(Color.BLACK);
        lbTongTienDV1.setBounds(230, 180, 140, 20);
        lbTongTienDV1.setFont(new Font(font,Font.BOLD,18));
        
        fthanhtoan.add(lbTongTienDV1);
        
        JLabel lbTongCong=new JLabel("Tổng cộng: ");
        lbTongCong.setForeground(Color.BLACK);
        lbTongCong.setBounds(40, 220, 160, 20);
        lbTongCong.setFont(new Font(font,0,18));
        
        fthanhtoan.add(lbTongCong);
        
        JLabel lbTongCong1=new JLabel(dl.ChuyenKieuTien(String.valueOf(hd1.getTongCong())));
        lbTongCong1.setForeground(Color.BLACK);
        lbTongCong1.setBounds(230, 220, 140, 20);
        lbTongCong1.setFont(new Font(font,Font.BOLD,18));
        
        fthanhtoan.add(lbTongCong1);
        
        JLabel lbTongTienKM=new JLabel("Tổng tiền KM: ");
        lbTongTienKM.setForeground(Color.BLACK);
        lbTongTienKM.setBounds(40, 260, 160, 20);
        lbTongTienKM.setFont(new Font(font,0,18));
        
        fthanhtoan.add(lbTongTienKM);
        
        JLabel lbTongTienKM1=new JLabel(dl.ChuyenKieuTien(String.valueOf(hd1.getTongTienKM())));
        lbTongTienKM1.setForeground(Color.BLACK);
        lbTongTienKM1.setBounds(230, 260, 140, 20);
        lbTongTienKM1.setFont(new Font(font,Font.BOLD,18));
        
        fthanhtoan.add(lbTongTienKM1);
        
        JLabel lbTongTienPhaiTra=new JLabel("Tổng tiền thanh toán: ");
        lbTongTienPhaiTra.setForeground(Color.BLACK);
        lbTongTienPhaiTra.setBounds(40, 300, 180, 20);
        lbTongTienPhaiTra.setFont(new Font(font,0,18));
        
        fthanhtoan.add(lbTongTienPhaiTra);
        
        JLabel lbTongTienPhaiTra1=new JLabel(dl.ChuyenKieuTien(String.valueOf(hd1.getTongPhaiTra())));
        lbTongTienPhaiTra1.setForeground(Color.BLACK);
        lbTongTienPhaiTra1.setBounds(230, 300, 140, 20);
        lbTongTienPhaiTra1.setFont(new Font(font,Font.BOLD,18));
      
        fthanhtoan.add(lbTongTienPhaiTra1);
        
        JLabel lbTinhTrang=new JLabel("Tình trạng: ");
        lbTinhTrang.setForeground(Color.BLACK);
        lbTinhTrang.setBounds(390, 180, 180, 20);
        lbTinhTrang.setFont(new Font(font,0,18));
        
        fthanhtoan.add(lbTinhTrang);
        
        JLabel lbTinhTrang1=new JLabel();
        lbTinhTrang1.setForeground(Color.BLACK);
        lbTinhTrang1.setBounds(390, 220, 160, 20);
        lbTinhTrang1.setFont(new Font(font,Font.BOLD,18));
        lbTinhTrang1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        lbTinhTrang1.setHorizontalAlignment(JLabel.CENTER);
        if (hd1.getTinhTrang()==1){
            lbTinhTrang1.setText("Chưa thanh toán");
            lbTinhTrang1.setForeground(Color.red);
        }
        else{
            lbTinhTrang1.setText("Đã thanh toán");
            lbTinhTrang1.setForeground(Color.GREEN);
        }
      
        fthanhtoan.add(lbTinhTrang1);               
        
        JTable tbct=new JTable();
        tbct.getTableHeader().setBackground(new Color(36,36,36));
        tbct.getTableHeader().setForeground(Color.WHITE);
        tbct.getTableHeader().setFont(new Font(font,0,18));
        tbct.getTableHeader().setPreferredSize(new Dimension(0,30));
        tbct.setFont(new Font(font,0,18));
        tbct.setForeground(Color.BLACK);
        tbct.setRowHeight(25);
        
        DefaultTableModel md=new DefaultTableModel();
        Vector cot=new Vector();
        cot.add("STT");
        cot.add("Mã dịch vụ");
        cot.add("Mã KM dịch vụ");
        cot.add("Đơn giá");
        cot.add("Số lượng");
        cot.add("Thành tiền");
        cot.add("Tiền KM");
        if (md.getRowCount()==0){
            md=new DefaultTableModel(cot, 0);
        }
        int i=1;
        for (ChiTietHoaDonDTO k : ds){
            Vector dong=new Vector();
            dong.add(i);
            dong.add(k.getMaDV());
            dong.add(k.getMaKMDV());
            dong.add(k.getDonGia());
            dong.add(k.getSL());
            dong.add(k.getThanhTien());
            dong.add(k.getTienKM());
            md.addRow(dong);
            i++;
        }
        tbct.setModel(md);
        tbct.getColumnModel().getColumn(0).setMinWidth(50);
        tbct.getColumnModel().getColumn(1).setMinWidth(100);
        tbct.getColumnModel().getColumn(2).setMinWidth(130);
        tbct.getColumnModel().getColumn(3).setMinWidth(100);
        tbct.getColumnModel().getColumn(4).setMinWidth(80);
        tbct.getColumnModel().getColumn(5).setMinWidth(100);
        tbct.getColumnModel().getColumn(6).setMinWidth(100);
        
        //--------------------Căn giữa trong bảng-----------------------//
        DefaultTableCellRenderer center=new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        tbct.setDefaultRenderer(tbct.getColumnClass(0), center);
        tbct.updateUI();
        //--------------------------------------------------------------//
        
        JScrollPane sc=new JScrollPane();
        sc.setViewportView(tbct);
        sc.setBounds(10, 350, 680, 240);
        
        fthanhtoan.add(sc);
        
        JLabel lb1=new JLabel();
        lb1.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK));
        lb1.setBounds(620, 60, 20, 280);
        
        fthanhtoan.add(lb1);
                
        JLabel lbMaPDP2=new JLabel("Các phòng đã đặt: ");
        lbMaPDP2.setForeground(Color.BLACK);
        lbMaPDP2.setBounds(650, 60, 150, 20);
        lbMaPDP2.setFont(new Font(font,0,18));
        
        fthanhtoan.add(lbMaPDP2);
        
        JTable tbctpdp=new JTable();
        tbctpdp.getTableHeader().setBackground(new Color(36,36,36));
        tbctpdp.getTableHeader().setForeground(Color.WHITE);
        tbctpdp.getTableHeader().setFont(new Font(font,0,18));
        tbctpdp.getTableHeader().setPreferredSize(new Dimension(0,30));
        tbctpdp.setFont(new Font(font,0,18));
        tbctpdp.setForeground(Color.BLACK);
        tbctpdp.setRowHeight(25);
               
        DefaultTableModel md1=new DefaultTableModel();
        Vector cot1=new Vector();
        cot1.add("STT");
        cot1.add("Mã phòng");
        cot1.add("Mã KM phòng");
        cot1.add("SL người");
        cot1.add("Đơn giá");
        cot1.add("Tiền KM");
        if (md1.getRowCount()==0){
            md1=new DefaultTableModel(cot1, 0);
        }
        int i1=1;
        for (ChiTietPhieuDatPhongDTO k : ds1){
            Vector dong=new Vector();
            dong.add(i1);
            dong.add(k.getMaPhong());
            dong.add(k.getMaKMPhong());
            dong.add(k.getSLNguoi());
            dong.add(k.getDonGia());
            dong.add(k.getTienKM());
            md1.addRow(dong);
            i1++;
        }
        tbctpdp.setModel(md1);
        tbctpdp.getColumnModel().getColumn(0).setMinWidth(50);
        tbctpdp.getColumnModel().getColumn(1).setMinWidth(100);
        tbctpdp.getColumnModel().getColumn(2).setMinWidth(130);
        tbctpdp.getColumnModel().getColumn(3).setMinWidth(100);
        tbctpdp.getColumnModel().getColumn(4).setMinWidth(80);
        tbctpdp.getColumnModel().getColumn(5).setMinWidth(100);
        
        //--------------------Căn giữa trong bảng-----------------------//
        DefaultTableCellRenderer center1=new DefaultTableCellRenderer();
        center1.setHorizontalAlignment(JLabel.CENTER);
        tbctpdp.setDefaultRenderer(tbctpdp.getColumnClass(0), center);
        tbctpdp.updateUI();
        //--------------------------------------------------------------//
        
        JScrollPane sc1=new JScrollPane();
        sc1.setViewportView(tbctpdp);
        sc1.setBounds(630, 100, 570, 200);
        
        fthanhtoan.add(sc1);
                
        JLabel lbNgayThue=new JLabel("Ngày thuê: ");
        lbNgayThue.setForeground(Color.BLACK);
        lbNgayThue.setBounds(720, 330, 110, 20);
        lbNgayThue.setFont(new Font(font,0,18));
        
        fthanhtoan.add(lbNgayThue);
        
        JLabel lbNgayThue1=new JLabel(dl.chuyendate(pdp.getNgayThue()));
        lbNgayThue1.setForeground(Color.BLACK);
        lbNgayThue1.setBounds(820, 330, 110, 20);
        lbNgayThue1.setFont(new Font(font,Font.BOLD,18));
        lbNgayThue1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        lbNgayThue1.setHorizontalAlignment(JLabel.CENTER);
        
        fthanhtoan.add(lbNgayThue1);
        
        JLabel lbNgayTra=new JLabel("Ngày trả: ");
        lbNgayTra.setForeground(Color.BLACK);
        lbNgayTra.setBounds(960, 330, 110, 20);
        lbNgayTra.setFont(new Font(font,0,18));
        
        fthanhtoan.add(lbNgayTra);
        
        JLabel lbNgayTra1=new JLabel(dl.chuyendate(pdp.getNgayTra()));
        lbNgayTra1.setForeground(Color.BLACK);
        lbNgayTra1.setBounds(1050, 330, 110, 20);
        lbNgayTra1.setFont(new Font(font,Font.BOLD,18));
        lbNgayTra1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        lbNgayTra1.setHorizontalAlignment(JLabel.CENTER);
        
        fthanhtoan.add(lbNgayTra1);
        
        JLabel lbTongNgayThuePDP=new JLabel("Tổng số ngày thuê theo PĐP: ");
        lbTongNgayThuePDP.setForeground(Color.BLACK);
        lbTongNgayThuePDP.setBounds(720, 370, 250, 20);
        lbTongNgayThuePDP.setFont(new Font(font,0,18));
        
        fthanhtoan.add(lbTongNgayThuePDP);
        
        JLabel lbTongNgayThuePDP1=new JLabel(String.valueOf(dl.TinhSoNgay(dl.chuyendate(pdp.getNgayThue()), dl.chuyendate(pdp.getNgayTra()))));
        lbTongNgayThuePDP1.setForeground(Color.BLACK);
        lbTongNgayThuePDP1.setBounds(950, 370, 100, 20);
        lbTongNgayThuePDP1.setFont(new Font(font,Font.BOLD,18));
        
        fthanhtoan.add(lbTongNgayThuePDP1);                
        
        JLabel lbTienThuePDP=new JLabel("Tiền thuê theo PĐP: ");
        lbTienThuePDP.setForeground(Color.BLACK);
        lbTienThuePDP.setBounds(720, 400, 200, 20);
        lbTienThuePDP.setFont(new Font(font,0,18));
        
        fthanhtoan.add(lbTienThuePDP);
        
        JLabel lbTienThuePDP1=new JLabel(dl.ChuyenKieuTien(String.valueOf(pdp.getTongTienThue())));
        lbTienThuePDP1.setForeground(Color.BLACK);
        lbTienThuePDP1.setBounds(880, 400, 120, 20);
        lbTienThuePDP1.setFont(new Font(font,Font.BOLD,18));
        
        fthanhtoan.add(lbTienThuePDP1);
        
        JLabel lbTienKMPDP=new JLabel("Tiền KM theo PĐP: ");
        lbTienKMPDP.setForeground(Color.BLACK);
        lbTienKMPDP.setBounds(720, 430, 200, 20);
        lbTienKMPDP.setFont(new Font(font,0,18));
        
        fthanhtoan.add(lbTienKMPDP);
        
        JLabel lbTienKMPDP1=new JLabel(dl.ChuyenKieuTien(String.valueOf(pdp.getTongTienKM())));
        lbTienKMPDP1.setForeground(Color.BLACK);
        lbTienKMPDP1.setBounds(880, 430, 120, 20);
        lbTienKMPDP1.setFont(new Font(font,Font.BOLD,18));
        
        fthanhtoan.add(lbTienKMPDP1);
        
        JLabel lbTongNgayThueThucTe=new JLabel("Tổng số ngày thuê thực tế: ");
        lbTongNgayThueThucTe.setForeground(Color.BLACK);
        lbTongNgayThueThucTe.setBounds(720, 470, 250, 20);
        lbTongNgayThueThucTe.setFont(new Font(font,0,18));
        
        fthanhtoan.add(lbTongNgayThueThucTe);
        
        JLabel lbTongNgayThueThucTe1=new JLabel(String.valueOf(dl.TinhSoNgay(dl.chuyendate(pdp.getNgayThue()), dl.chuyendate(dl.LayNgayThangNamHienTai()))));
        lbTongNgayThueThucTe1.setForeground(Color.BLACK);
        lbTongNgayThueThucTe1.setBounds(950, 470, 100, 20);
        lbTongNgayThueThucTe1.setFont(new Font(font,Font.BOLD,18));
        
        fthanhtoan.add(lbTongNgayThueThucTe1);
        
        JLabel lbTienThueThucTe=new JLabel("Tiền thuê thực tế: ");
        lbTienThueThucTe.setForeground(Color.BLACK);
        lbTienThueThucTe.setBounds(720, 500, 200, 20);
        lbTienThueThucTe.setFont(new Font(font,0,18));
        
        fthanhtoan.add(lbTienThueThucTe);
        
        JLabel lbTienThueThucTe1=new JLabel(dl.ChuyenKieuTien(String.valueOf(hd1.getTongTienThue())));
        lbTienThueThucTe1.setForeground(Color.BLACK);
        lbTienThueThucTe1.setBounds(880, 500, 120, 20);
        lbTienThueThucTe1.setFont(new Font(font,Font.BOLD,18));
        
        fthanhtoan.add(lbTienThueThucTe1);
        
        JLabel lbTienKMThucTe=new JLabel("Tiền KM thực tế: ");
        lbTienKMThucTe.setForeground(Color.BLACK);
        lbTienKMThucTe.setBounds(720, 530, 200, 20);
        lbTienKMThucTe.setFont(new Font(font,0,18));
        
        fthanhtoan.add(lbTienKMThucTe);
        
        JLabel lbTienKMThucTe1=new JLabel(dl.ChuyenKieuTien(String.valueOf(hd1.getTongTienKM())));
        lbTienKMThucTe1.setForeground(Color.BLACK);
        lbTienKMThucTe1.setBounds(880, 530, 120, 20);
        lbTienKMThucTe1.setFont(new Font(font,Font.BOLD,18));
        
        fthanhtoan.add(lbTienKMThucTe1);
        
        JButton btThanhToan =new JButton("Thanh toán");
        btThanhToan.setFont(new Font(font,0,18));
        btThanhToan.setForeground(Color.WHITE);
        btThanhToan.setBounds(400, 600, 200, 25);
        btThanhToan.setBackground(new Color(36,36,36));
        btThanhToan.setFocusable(false);
        btThanhToan.setBorderPainted(false);
        
        fthanhtoan.add(btThanhToan);  
        
        JButton btThoat =new JButton("Thoát");
        btThoat.setFont(new Font(font,0,18));
        btThoat.setForeground(Color.WHITE);
        btThoat.setBounds(630, 600, 200, 25);
        btThoat.setBackground(new Color(36,36,36));
        btThoat.setFocusable(false);
        btThoat.setBorderPainted(false);
        
        fthanhtoan.add(btThoat);      
        
        btThanhToan.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int input=JOptionPane.showConfirmDialog(fthanhtoan, "Bạn có chắc muốn thanh toán", "Thanh toán hóa đơn", JOptionPane.YES_NO_OPTION);
                if (input==0){
                    HoaDonBUS hdbus=new HoaDonBUS();
                    hdbus.ThanhToanHoaDon(hd1);
                    JOptionPane.showMessageDialog(fthanhtoan, "Thanh toán thành công");
                    fthanhtoan.setVisible(false);
                }                
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btThanhToan.setBackground(Color.DARK_GRAY);
                btThanhToan.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                btThanhToan.setBackground(new Color(36,36,36));
            }
        });
        
        btThoat.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                fthanhtoan.setVisible(false);            
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btThoat.setBackground(Color.DARK_GRAY);
                btThoat.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                btThoat.setBackground(new Color(36,36,36));
            }
        });
        
//        fthanhtoan.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fthanhtoan.setVisible(true);
    }
    
    public JScrollPane BangChiTietHoaDon(ArrayList<ChiTietHoaDonDTO> ds){
        tbct=new JTable();
        tbct.getTableHeader().setBackground(new Color(36,36,36));
        tbct.getTableHeader().setForeground(Color.WHITE);
        tbct.getTableHeader().setFont(new Font(font,0,18));
        tbct.getTableHeader().setPreferredSize(new Dimension(0,30));
        tbct.setFont(new Font(font,0,18));
        tbct.setForeground(Color.BLACK);
        tbct.setRowHeight(25);
        
        mdct=new DefaultTableModel();
        Vector cot=new Vector();
        cot.add("Mã dịch vụ");
        cot.add("Mã KM dịch vụ");
        cot.add("Đơn giá");
        cot.add("Số lượng");
        cot.add("Thành tiền");
        cot.add("Tiền KM");
        if (mdct.getRowCount()==0){
            mdct=new DefaultTableModel(cot, 0);
        }
        for (ChiTietHoaDonDTO k : ds){
            Vector dong=new Vector();
            dong.add(k.getMaDV());
            dong.add(k.getMaKMDV());
            dong.add(k.getDonGia());
            dong.add(k.getSL());
            dong.add(k.getThanhTien());
            dong.add(k.getTienKM());
            mdct.addRow(dong);
        }
        tbct.setModel(mdct);
        tbct.getColumnModel().getColumn(0).setMinWidth(100);
        tbct.getColumnModel().getColumn(1).setMinWidth(130);
        tbct.getColumnModel().getColumn(2).setMinWidth(100);
        tbct.getColumnModel().getColumn(3).setMinWidth(80);
        tbct.getColumnModel().getColumn(4).setMinWidth(100);
        tbct.getColumnModel().getColumn(5).setMinWidth(100);
        
        //--------------------Căn giữa trong bảng-----------------------//
        DefaultTableCellRenderer center=new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        tbct.setDefaultRenderer(tbct.getColumnClass(0), center);
        tbct.updateUI();
        //--------------------------------------------------------------//
        
        JScrollPane scct1=new JScrollPane();
        scct1.setViewportView(tbct);
        scct1.setBounds(15, 380, 680, 240);
        
        return scct1;
    }
    
    public void SuaThongTinHoaDon(String mahd){
        JFrame fsua=new JFrame();
        fsua.setLayout(null);
        fsua.setBounds(0, 0, 720, 710);
        fsua.setPreferredSize(new Dimension(720,710));        
        
        //---------------------------------Hiện giữa màn hình-----------------------------//
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        fsua.setLocation((int)d.getWidth()/2 - (int)fsua.getPreferredSize().getWidth()/2,
                      (int)d.getHeight()/2 - (int)fsua.getPreferredSize().getHeight()/2);
        //--------------------------------------------------------------------------------//
        
        JLabel lbSua=new JLabel("Cập nhật thông tin hóa đơn");
        lbSua.setFont(new Font(font,Font.BOLD,25));
        lbSua.setForeground(Color.WHITE);
        lbSua.setBackground(new Color(36,36,36));
        lbSua.setHorizontalAlignment(JLabel.CENTER);
        lbSua.setBounds(0, 0, 720, 35);
        lbSua.setOpaque(true);
        
        fsua.add(lbSua);
        
        Xulydulieu dl=new Xulydulieu();
        HoaDonBUS hdbus=new HoaDonBUS();       
        if (HoaDonBUS.dshd==null){
            hdbus.docdshd();
        }
        
        hd1=new HoaDonDTO();
        hd1=hdbus.Lay1HoaDonTheoMaHD(mahd);
        
        JLabel lbMaHD=new JLabel("Mã hóa đơn: ");
        lbMaHD.setForeground(Color.BLACK);
        lbMaHD.setBounds(40, 60, 110, 20);
        lbMaHD.setFont(new Font(font,0,18));
        
        fsua.add(lbMaHD);
        
        JLabel lbMaHD1=new JLabel(hd1.getMaHD());
        lbMaHD1.setForeground(Color.BLACK);
        lbMaHD1.setBounds(170, 60, 80, 20);
        lbMaHD1.setFont(new Font(font,Font.BOLD,18));
        lbMaHD1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        lbMaHD1.setHorizontalAlignment(JLabel.CENTER);
        
        fsua.add(lbMaHD1);
        
        JLabel lbMaPDP=new JLabel("Mã phiếu đặt phòng: ");
        lbMaPDP.setForeground(Color.BLACK);
        lbMaPDP.setBounds(280, 60, 160, 20);
        lbMaPDP.setFont(new Font(font,0,18));
        
        fsua.add(lbMaPDP);
        
        JLabel lbMaPDP1=new JLabel(hd1.getMaPDP());
        lbMaPDP1.setForeground(Color.BLACK);
        lbMaPDP1.setBounds(450, 60, 110, 20);
        lbMaPDP1.setFont(new Font(font,Font.BOLD,18));
        lbMaPDP1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        lbMaPDP1.setHorizontalAlignment(JLabel.CENTER);
        
        fsua.add(lbMaPDP1);
        
        JLabel lbMaNV=new JLabel("Mã nhân viên: ");
        lbMaNV.setForeground(Color.BLACK);
        lbMaNV.setBounds(40, 100, 120, 20);
        lbMaNV.setFont(new Font(font,0,18));
        
        fsua.add(lbMaNV);
        
        JLabel lbMaNV1=new JLabel(hd1.getMaNV());
        lbMaNV1.setForeground(Color.BLACK);
        lbMaNV1.setBounds(170, 100, 80, 20);
        lbMaNV1.setFont(new Font(font,Font.BOLD,18));
        lbMaNV1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        lbMaNV1.setHorizontalAlignment(JLabel.CENTER);
        
        fsua.add(lbMaNV1);
        
        JLabel lbNgayLap=new JLabel("Ngày lập hóa đơn: ");
        lbNgayLap.setForeground(Color.BLACK);
        lbNgayLap.setBounds(280, 100, 160, 20);
        lbNgayLap.setFont(new Font(font,0,18));
        
        fsua.add(lbNgayLap);
        
        JLabel lbNgayLap1=new JLabel(dl.chuyendate(hd1.getNgayLapHD()));
        lbNgayLap1.setForeground(Color.BLACK);
        lbNgayLap1.setBounds(450, 100, 110, 20);
        lbNgayLap1.setFont(new Font(font,Font.BOLD,18));
        lbNgayLap1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        lbNgayLap1.setHorizontalAlignment(JLabel.CENTER);
        
        fsua.add(lbNgayLap1);
        
        JLabel lbTongTienDV=new JLabel("Tổng tiền dịch vụ: ");
        lbTongTienDV.setForeground(Color.BLACK);
        lbTongTienDV.setBounds(40, 140, 160, 20);
        lbTongTienDV.setFont(new Font(font,0,18));
        
        fsua.add(lbTongTienDV);
        
        lbTongTienDV1=new JLabel(dl.ChuyenKieuTien(String.valueOf(hd1.getTongTienDV())));
        lbTongTienDV1.setForeground(Color.BLACK);
        lbTongTienDV1.setBounds(230, 140, 140, 20);
        lbTongTienDV1.setFont(new Font(font,Font.BOLD,18));
        
        fsua.add(lbTongTienDV1);
        
        JLabel lbSuaChiTiet=new JLabel("Chi tiết hóa đơn");
        lbSuaChiTiet.setFont(new Font(font,Font.BOLD,25));
        lbSuaChiTiet.setForeground(Color.WHITE);
        lbSuaChiTiet.setBackground(new Color(36,36,36));
        lbSuaChiTiet.setHorizontalAlignment(JLabel.CENTER);
        lbSuaChiTiet.setBounds(0, 180, 720, 35);
        lbSuaChiTiet.setOpaque(true);
        
        fsua.add(lbSuaChiTiet);
        
        JLabel lbdichvu=new JLabel("Chọn dịch vụ: ");
        lbdichvu.setForeground(Color.BLACK);
        lbdichvu.setBounds(40, 235, 160, 20);
        lbdichvu.setFont(new Font(font,0,18));
        
        fsua.add(lbdichvu);
        
        lbdichvu1=new JLabel();
        lbdichvu1.setForeground(Color.BLACK);
        lbdichvu1.setBounds(180, 235, 140, 25);
        lbdichvu1.setFont(new Font(font,Font.BOLD,18));
        lbdichvu1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        lbdichvu1.setBackground(Color.WHITE);
        lbdichvu1.setHorizontalAlignment(JLabel.CENTER);
        lbdichvu1.setOpaque(true);
        
        fsua.add(lbdichvu1);
        
        JLabel lbMaKM=new JLabel("Mã khuyến mãi: ");
        lbMaKM.setForeground(Color.BLACK);
        lbMaKM.setBounds(40, 280, 160, 20);
        lbMaKM.setFont(new Font(font,0,18));
        
        fsua.add(lbMaKM);
        
        lbkm=new JLabel();
        lbkm.setForeground(Color.BLACK);
        lbkm.setBounds(180, 280, 140, 25);
        lbkm.setFont(new Font(font,Font.BOLD,18));
        lbkm.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        lbkm.setBackground(Color.WHITE);
        lbkm.setHorizontalAlignment(JLabel.CENTER);
        lbkm.setOpaque(true);
        
        fsua.add(lbkm);
        
        JLabel lbSL=new JLabel("Số lượng: ");
        lbSL.setForeground(Color.BLACK);
        lbSL.setBounds(40, 330, 160, 20);
        lbSL.setFont(new Font(font,0,18));
        
        fsua.add(lbSL);
        
        lbSL1=new JTextField();
        lbSL1.setForeground(Color.BLACK);
        lbSL1.setBounds(180, 330, 140, 25);
        lbSL1.setFont(new Font(font,Font.BOLD,18));
        lbSL1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        lbSL1.setBackground(Color.WHITE);
        lbSL1.setHorizontalAlignment(JLabel.CENTER);
        
        fsua.add(lbSL1);
        
        iconBUS ic=new iconBUS();
        ImageIcon iconChon=new ImageIcon(ic.iconchon);
        
        btChonDV=new JButton();
        btChonDV.setForeground(Color.WHITE);
        btChonDV.setBackground(new Color(36,36,36));        
        btChonDV.setIcon(iconChon);
        btChonDV.setBounds(340, 235, 35, 25);
        btChonDV.setFocusable(false);
        btChonDV.setBorderPainted(false);
        
        fsua.add(btChonDV);
        
        ImageIcon iconThem=new ImageIcon(ic.iconthem);
        JButton btThemct=new JButton();
        btThemct.setText("Thêm");
        btThemct.setIcon(iconThem);
        btThemct.setFont(new Font(font,0,18));
        btThemct.setBackground(new Color(36,36,36));
        btThemct.setForeground(Color.WHITE);
        btThemct.setFocusable(false);
        btThemct.setBorderPainted(false);
        btThemct.setBounds(420, 340, 120, 30);
        
        fsua.add(btThemct);
        
        ImageIcon iconSua=new ImageIcon(ic.iconxoa);
        JButton btXoact=new JButton();
        btXoact.setText("Xóa");
        btXoact.setIcon(iconSua);
        btXoact.setFont(new Font(font,0,18));
        btXoact.setBackground(new Color(36,36,36));
        btXoact.setForeground(Color.WHITE);
        btXoact.setFocusable(false);
        btXoact.setBorderPainted(false);
        btXoact.setBounds(560, 340, 120, 30);
        
        fsua.add(btXoact);                
        
        ChiTietHoaDonBUS cthdbus=new ChiTietHoaDonBUS();
        if (ChiTietHoaDonBUS.dscthd==null){
            cthdbus.docdscthd();
        }
        ArrayList<ChiTietHoaDonDTO> ds =new ArrayList<>();
        ds=cthdbus.LayCTHoaDonTheoMaHD(mahd);
        
        scct=new JScrollPane();
        scct=BangChiTietHoaDon(ds);
        
        fsua.add(scct);
        
        btChonDV.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                BangChonDSDV();
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btChonDV.setBackground(Color.DARK_GRAY);
                btChonDV.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                btChonDV.setBackground(new Color(36,36,36));
            }
        });
        btThemct.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                String madv=lbdichvu1.getText();
                String makm=lbkm.getText();
                String sl=lbSL1.getText();
                HoaDonBUS hdbus=new HoaDonBUS();
                Xulydulieu dl=new Xulydulieu();
                if (hdbus.KtraThemChiTietHoaDon(madv, sl)==1){
                    hdbus.TinhTienKhiThemChiTiet(mahd, madv, makm, sl);
                    HoaDonDTO hd3=new HoaDonDTO();
                    hd3=hdbus.Lay1HoaDonTheoMaHD(mahd);
                    lbTongTienDV1.setText(dl.ChuyenKieuTien(String.valueOf(hd3.getTongTienDV())));
                    fsua.remove(scct);
                    ArrayList<ChiTietHoaDonDTO> ds =new ArrayList<>();
                    ds=cthdbus.LayCTHoaDonTheoMaHD(mahd);

                    scct=new JScrollPane();
                    scct=BangChiTietHoaDon(ds);

                    fsua.add(scct);
                }
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btThemct.setBackground(Color.DARK_GRAY);
                btThemct.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                btThemct.setBackground(new Color(36,36,36));
            }
        });
        btXoact.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int i=tbct.getSelectedRow();
                if (i<0){
                    JOptionPane.showMessageDialog(fsua, "Chưa chọn dịch vụ cần xóa");                   
                }
                else{
                    String madv=tbct.getModel().getValueAt(i, 0).toString();
                    int input=JOptionPane.showConfirmDialog(fsua, "Bạn có chắc muốn xóa", "Xóa chi tiết hóa đơn", JOptionPane.YES_NO_OPTION);
                    if (input==0){
                        hdbus.TinhTienKhiXoaChiTiet(mahd, madv);
                        HoaDonDTO hd3=new HoaDonDTO();
                        hd3=hdbus.Lay1HoaDonTheoMaHD(mahd);
                        lbTongTienDV1.setText(dl.ChuyenKieuTien(String.valueOf(hd3.getTongTienDV())));
                        fsua.remove(scct);
                        ArrayList<ChiTietHoaDonDTO> ds =new ArrayList<>();
                        ds=cthdbus.LayCTHoaDonTheoMaHD(mahd);

                        scct=new JScrollPane();
                        scct=BangChiTietHoaDon(ds);

                        fsua.add(scct);
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btXoact.setBackground(Color.DARK_GRAY);
                btXoact.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                btXoact.setBackground(new Color(36,36,36));
            }
        });
        
        JButton btHoanThanh =new JButton("Hoàn thành");
        btHoanThanh.setFont(new Font(font,0,18));
        btHoanThanh.setForeground(Color.WHITE);
        btHoanThanh.setBounds(195, 630, 150, 25);
        btHoanThanh.setBackground(new Color(36,36,36));
        btHoanThanh.setFocusable(false);
        btHoanThanh.setBorderPainted(false);
        
        fsua.add(btHoanThanh);
        
        JButton btThoat =new JButton("Thoát");
        btThoat.setFont(new Font(font,0,18));
        btThoat.setForeground(Color.WHITE);
        btThoat.setBounds(375, 630, 150, 25);
        btThoat.setBackground(new Color(36,36,36));
        btThoat.setFocusable(false);
        btThoat.setBorderPainted(false);
        
        fsua.add(btThoat);
        
        btHoanThanh.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                JOptionPane.showMessageDialog(fsua, "Cập nhật thành công");
                fsua.setVisible(false);
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btHoanThanh.setBackground(Color.DARK_GRAY);
                btHoanThanh.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                btHoanThanh.setBackground(new Color(36,36,36));
            }
        });
        
        btThoat.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                fsua.setVisible(false);            
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btThoat.setBackground(Color.DARK_GRAY);
                btThoat.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                btThoat.setBackground(new Color(36,36,36));
            }
        });
        
//        fsua.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fsua.setVisible(true);
    }
    
    public void BangChonDSDV(){
        JFrame fchon=new JFrame();
        fchon.setLayout(null);
        fchon.setBounds(0, 0, 600, 370);
        fchon.setPreferredSize(new Dimension(600,340));
        
        //---------------------------------Hiện giữa màn hình-----------------------------//
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        fchon.setLocation((int)d.getWidth()/2 - (int)fchon.getPreferredSize().getWidth()/2,
                      (int)d.getHeight()/2 - (int)fchon.getPreferredSize().getHeight()/2);
        //--------------------------------------------------------------------------------//
        
        JLabel lbTieude1=new JLabel("Chọn dịch vụ");
        lbTieude1.setFont(new Font(font,Font.BOLD,25));
        lbTieude1.setForeground(Color.BLACK);
        lbTieude1.setHorizontalAlignment(JLabel.CENTER);
        lbTieude1.setOpaque(true);
        lbTieude1.setBackground(new Color(36,36,36));
        lbTieude1.setForeground(Color.white);
        lbTieude1.setBounds(0, 0, 600, 35);
        
        fchon.add(lbTieude1);
        
        DichVuBUS dvbus=new DichVuBUS();
        if (DichVuBUS.dsdv==null){
            dvbus.docdsdv();
        }
        JTable tbdsdichvu=new JTable();
        tbdsdichvu.getTableHeader().setBackground(new Color(36,36,36));
        tbdsdichvu.getTableHeader().setForeground(Color.WHITE);
        tbdsdichvu.getTableHeader().setFont(new Font(font,0,18));
        tbdsdichvu.getTableHeader().setPreferredSize(new Dimension(0,30));
        tbdsdichvu.setFont(new Font(font,0,18));
        tbdsdichvu.setForeground(Color.BLACK);
        tbdsdichvu.setRowHeight(25);
        
        DefaultTableModel md2=new DefaultTableModel();
        Vector cot=new Vector();
        cot.add("Mã dịch vụ");
        cot.add("Tên dịch vụ");
        cot.add("Đơn giá");
        if (md2.getRowCount()==0){
            md2=new DefaultTableModel(cot, 0);
        }
        for (DichVuDTO k : DichVuBUS.dsdv){
            Vector dong=new Vector();
            dong.add(k.getMaDV());
            dong.add(k.getTenDV());
            dong.add(k.getDonGia());
            md2.addRow(dong);
        }
        tbdsdichvu.setModel(md2);
        tbdsdichvu.getColumnModel().getColumn(0).setMinWidth(50);
        tbdsdichvu.getColumnModel().getColumn(1).setMinWidth(120);
        tbdsdichvu.getColumnModel().getColumn(2).setMinWidth(120); 
        
        //--------------------Căn giữa trong bảng-----------------------//
        DefaultTableCellRenderer center=new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        tbdsdichvu.setDefaultRenderer(tbdsdichvu.getColumnClass(0), center);
        tbdsdichvu.updateUI();
        //--------------------------------------------------------------//
        
        JScrollPane sc=new JScrollPane();
        sc.setViewportView(tbdsdichvu);
        sc.setBounds(35, 80, 520, 190);
        fchon.add(sc);
        
        tbdsdichvu.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                HoaDonBUS hdbus=new HoaDonBUS();
                int i=tbdsdichvu.getSelectedRow();
                String madv=tbdsdichvu.getModel().getValueAt(i, 0).toString();
                lbdichvu1.setText(madv);
                lbkm.setText(hdbus.LayMaKhuyenMaiDV(madv));                
                fchon.setVisible(false);
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
            }
        });
        
        fchon.setVisible(true);
    }
    
    
    
//    public static void main(String[] args) {
//        QuanLyGUI p=new QuanLyGUI("NV0001","QL");
////        HoaDonGUI p1=new HoaDonGUI();
////        p1.SuaThongTinHoaDon("HD0001");
//    }
}
