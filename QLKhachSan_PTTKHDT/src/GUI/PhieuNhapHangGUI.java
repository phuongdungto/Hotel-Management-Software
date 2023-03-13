/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.ChiTietPhieuNhapBUS;
import BUS.HangHoaBUS;
import BUS.NhaCungCapBUS;
import BUS.NhanVienBUS;
import BUS.PhieuNhapHangBUS;
import BUS.Xulydulieu;
import DAO.ChiTietPhieuNhapDAO;
import DTO.ChiTietPhieuNhapDTO;
import DTO.HangHoaDTO;
import DTO.NhaCungCapDTO;
import DTO.NhanVienDTO;
import DTO.PhieuNhapHangDTO;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author camdu
 */
public class PhieuNhapHangGUI {
    protected JFrame frame_chitiet,frame_dsnhanvien,frame_dsnhacc;
    protected JPanel pPhieuNhapHang;
    protected JPanel pTimKiem;
    protected JPanel pBangDS_PNH;
    protected JTable tbdspnh;
    protected JTable tblCT_PNH;
    protected JTable tbnv;
    protected JTable tbncc;
    protected JLabel lbTieude,lbManhanvien,lbManhacungcap,lbNgayNhapKhoTu,lbNgayNhapKhoDen;
    protected JTextField txtManhanvien,txtManhacungcap;
    protected JComboBox cbxNhomHangHoa,cbxTimKiem;
    protected JButton btTimKiem,btXemChiTiet,btThem,btSua,btXoa,btCapNhat;
    protected DefaultTableModel model_dspnh;
    protected JTextFieldDateEditor editor1,editor2;
    protected JScrollPane sc,sc1,sc3,sc4;
    protected JDateChooser day,day1;
    protected Color colorButton = new Color(36,36,36);
    protected Color colorLaybel = new Color(238, 238, 238);
    Font font_lb = new Font("Times New Roman",0,18);
    Font font_Button = new Font("Times New Roman",0,17);
    Font font_lbThongtinPNH = new Font("Times New Roman",Font.BOLD,22);
    
    JTextFieldDateEditor editor;
    protected JDateChooser txt_CTNgayNhap;
    protected JLabel lbThongtinPNH,lbThongtinCTPNH,lb_CTMaPNH,lb_CTMaNV,lb_CTMaNCC,lb_CTNgayNhap,lb_CTTongTien,lbDSNhanVien,lb_CTmaHH,lb_HHSoLuong;
    Font font_lbCTThongtinPNH = new Font("Times New Roman",Font.BOLD,28);
    protected JTextField txt_CTMaPNH,txt_CTMaNV=null,txt_CTMaNCC,txt_CTTongTien,txt_CTMaHH,txt_HHSoLuong;
    protected JButton btThoat_CT,btThem_CT,btLuu_CT,bt_DanhsachNV,bt_DanhsachNCC,bt_DanhsachNV1,bt_DanhsachNCC1,bt_DanhsachHH,btThem_CT1,btXoa_CT1;
    Font font_CT = new Font("Times New Roman",Font.BOLD,20);
    Font font_CT_txt = new Font("Times New Roman",Font.BOLD,18);
    DefaultTableModel md ;
    public JPanel KhoiTaoPanel(int width, int height) {
        pPhieuNhapHang=new JPanel();
        pPhieuNhapHang.setLayout(null);
        pPhieuNhapHang.setBackground(Color.WHITE);
        pPhieuNhapHang.setBounds(0, 0, width, height);
        pPhieuNhapHang.setPreferredSize(new Dimension(width,height));
        pPhieuNhapHang.setOpaque(true);
        
        lbTieude=new JLabel();
        lbTieude.setOpaque(true);
        lbTieude.setText("Danh sách phiếu nhập hàng");
        lbTieude.setFont(font_lbCTThongtinPNH);
        lbTieude.setBounds(0, 0, 1000, 50);
        lbTieude.setForeground(Color.BLACK);
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        pTimKiem = TimKiem(1000, 160);
        pPhieuNhapHang.add(lbTieude);
        pPhieuNhapHang.add(pTimKiem);
        
        pBangDS_PNH = BangDSPNH(1000, 440);
        pPhieuNhapHang.add(pBangDS_PNH);
        return pPhieuNhapHang;
    }
    public JPanel TimKiem(int width, int height){
        pTimKiem=new JPanel();
        pTimKiem.setLayout(null);
        pTimKiem.setBounds(0, 50, width, height);
        pTimKiem.setPreferredSize(new Dimension(width,height));
        pTimKiem.setOpaque(true);
        //pTimKiem.setBackground(Color.red);
        //=========================== LB Mã nhân viên ============================//
        lbManhanvien = new JLabel("Mã nhân viên");
        lbManhanvien.setFont(new Font("Times New Roman",0,18));
        lbManhanvien.setBounds(10, 15, 140, 20);
        lbManhanvien.setOpaque(true);
       //lbTenHangHoa.setBackground(colorLaybel);
        pTimKiem.add(lbManhanvien);
         //=========================== TXT Mã nhân viên ============================//
        txtManhanvien = new JTextField();
        txtManhanvien.setBounds(150,12,290,25);
        txtManhanvien.setHorizontalAlignment(JTextField.CENTER);
        txtManhanvien.setEditable(false);
        txtManhanvien.setFont(font_CT_txt);
        pTimKiem.add(txtManhanvien);
         ImageIcon icon = new ImageIcon("src/img/select.png");
                bt_DanhsachNV1 = new JButton(icon);
                bt_DanhsachNV1.setBounds(0,0,0,0);
                bt_DanhsachNV1.setBackground(colorButton);
                bt_DanhsachNV1.setBorderPainted(false);
                bt_DanhsachNV1.setFocusPainted(false);
                pTimKiem.add(bt_DanhsachNV1);
                bt_DanhsachNV1.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                         BangChonDSNV();
                         BangChonDSNV_TimKiemPNH();
                    }
                     @Override
                    public void mouseEntered(MouseEvent arg0) {
                        bt_DanhsachNV1.setBackground(Color.DARK_GRAY);
                        bt_DanhsachNV1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    }
                    @Override
                    public void mouseExited(MouseEvent arg0) {
                       bt_DanhsachNV1.setBackground(colorButton);
                    }
                });
         //=========================== Mã nhà cung cấp ============================//
        lbManhacungcap= new JLabel("Mã nhà cung cấp");
        lbManhacungcap.setFont(new Font("Times New Roman",0,18));
        lbManhacungcap.setBounds(10, 55, 140, 20);
        lbManhacungcap.setOpaque(true);
        pTimKiem.add(lbManhacungcap);
        //=========================== TXT Mã nhà cung cấp ============================//
        txtManhacungcap = new JTextField();
        txtManhacungcap.setFont(font_CT_txt);
        txtManhacungcap.setHorizontalAlignment(JTextField.CENTER);
        txtManhacungcap.setEditable(false);
        txtManhacungcap.setBounds(150,53,290,25);
        pTimKiem.add(txtManhacungcap);
        
       bt_DanhsachNCC1= new JButton(icon);
                bt_DanhsachNCC1.setBounds(0,0,0,0);
                bt_DanhsachNCC1.setBackground(colorButton);
                bt_DanhsachNCC1.setBorderPainted(false);
                bt_DanhsachNCC1.setFocusPainted(false);
                pTimKiem.add(bt_DanhsachNCC1);
                bt_DanhsachNCC1.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                         BangChonDSNCC();
                         BangChonDSNCC_TimKiemPNH();
                    }
                     @Override
                    public void mouseEntered(MouseEvent arg0) {
                        bt_DanhsachNCC1.setBackground(Color.DARK_GRAY);
                        bt_DanhsachNCC1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    }
                    @Override
                    public void mouseExited(MouseEvent arg0) {
                       bt_DanhsachNCC1.setBackground(colorButton);
                    }
                });
        //=========================== Ngày nhập hàng hóa từ ============================//
        lbNgayNhapKhoTu= new JLabel("Ngày nhập từ");
        lbNgayNhapKhoTu.setFont(new Font("Times New Roman",0,18));
        lbNgayNhapKhoTu.setBounds(10, 97, 100, 20);
        lbNgayNhapKhoTu.setOpaque(true);
        pTimKiem.add(lbNgayNhapKhoTu);
        //===========================  Ngày nhập hàng hóa từ ============================//
        day = new JDateChooser();// khởi tạo lịch
        day.setBounds(120, 95, 130, 25);
        day.setFont(font_CT_txt);
        day.setDateFormatString("yyyy-MM-dd");// set định dạng cho lịch
        editor1 = (JTextFieldDateEditor) day.getDateEditor(); // lấy editor để định dạng không cho nhập lịch
        editor1.setEditable(false);// set n
        pTimKiem.add(day);
        //===========================  Ngày nhập hàng hóa đến ============================//
        lbNgayNhapKhoDen = new JLabel("Ngày nhập đến");
        lbNgayNhapKhoDen.setFont(new Font("Times New Roman",0,18));
        lbNgayNhapKhoDen.setBounds(280, 97, 115, 20);
        lbNgayNhapKhoDen.setOpaque(true);
        pTimKiem.add(lbNgayNhapKhoDen);
        //===========================  Ngày nhập hàng hóa đến ============================//
        day1 = new JDateChooser();
        day1.setBounds(400, 95, 130, 25);
        day1.setFont(font_CT_txt);
        day1.setDateFormatString("yyyy-MM-dd");
        editor2 = (JTextFieldDateEditor) day1.getDateEditor();
        editor2.setEditable(false);
        pTimKiem.add(day1);
        //===========================  CBX Tìm kiếm theo ============================//
        String DSTimKiem[] = { "Tìm kiếm theo", "Mã NCC", "Mã NV", "Ngày nhập"};
        cbxTimKiem = new JComboBox(DSTimKiem);
        cbxTimKiem.setBounds(570, 10, 140, 30);
        pTimKiem.add(cbxTimKiem);
        //==================== cbx Tìm kiếm theo =====================//
        
        cbxTimKiem.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                
                       if(cbxTimKiem.getSelectedItem().equals(DSTimKiem[0])){
                                        
                                    }
                       if(cbxTimKiem.getSelectedItem().equals(DSTimKiem[1])){
                                       TimKiemTheoMaNCC();
                                    }
                       if(cbxTimKiem.getSelectedItem().equals(DSTimKiem[2])){
                                       TimKiemTheoMaNV();
                                    }
                       if(cbxTimKiem.getSelectedItem().equals(DSTimKiem[3])){
                                       editor1.setEditable(true);
                                       editor2.setEditable(true);
                                    }           
                                         
                                }});
        
        //===========================  BT Tìm kiếm ============================//
        ImageIcon iconsearch = new ImageIcon("src/img/search.png");
        btTimKiem = new JButton("Tìm kiếm",iconsearch);
        btTimKiem.setBounds(570, 50, 140, 35);
        btTimKiem.setBackground(colorButton);
        btTimKiem.setForeground(Color.WHITE);
        btTimKiem.setFont(font_Button);
        btTimKiem.setBorderPainted(false);
        btTimKiem.setFocusPainted(false);
        pTimKiem.add(btTimKiem);
        
        btTimKiem.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent arg0) {
                PhieuNhapHangBUS pnhbus = new PhieuNhapHangBUS();
              if(cbxTimKiem.getSelectedItem().equals("Tìm kiếm theo")){
                  JOptionPane.showMessageDialog(null, "Vui lòng chọn kiểu tìm kiếm");
              }
              //======================= tìm kiếm theo mã ncc =====================================//
              
              if(cbxTimKiem.getSelectedItem().equals("Mã NCC")){
                  if(txtManhacungcap.getText().equals("")){
                  JOptionPane.showMessageDialog(null, "Vui lòng chọn Mã nhà cung cấp trong bảng danh sách"); 
                  }
                  else {
                       ArrayList<PhieuNhapHangDTO> dspnhtk_mancc = new ArrayList<>();
                                        dspnhtk_mancc =  pnhbus.Timkiemtheo_maNCC(txtManhacungcap.getText());
                                        if(dspnhtk_mancc==null){
                                            JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu");
                                             }
                                         sc.removeAll();
                                         JTable tbl11 =Table_DSPNH(dspnhtk_mancc);
                                         sc1 = new JScrollPane(tbl11);
                                            sc1.setBounds(0, 0, 980, 380);
                                            sc.add(sc1);
                                            sc.repaint();
                                            sc.revalidate();
                                            pBangDS_PNH.add(sc);
                  }
              }
               //======================= tìm kiếm theo mã nv =====================================//
              if(cbxTimKiem.getSelectedItem().equals("Mã NV")){
                  if(txtManhanvien.getText().equals("")){
                  JOptionPane.showMessageDialog(null, "Vui lòng chọn Mã nhân viên trong bảng danh sách"); 
                  }
                  else {
                       ArrayList<PhieuNhapHangDTO> dspnhtk_mannv = new ArrayList<>();
                                        dspnhtk_mannv =  pnhbus.Timkiemtheo_maNV(txtManhanvien.getText());
                                        if(dspnhtk_mannv==null){
                                            JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu");
                                             }
                                         sc.removeAll();
                                         JTable tbl11 =Table_DSPNH(dspnhtk_mannv);
                                         sc1 = new JScrollPane(tbl11);
                                            sc1.setBounds(0, 0, 980, 380);
                                            sc.add(sc1);
                                            sc.repaint();
                                            sc.revalidate();
                                            pBangDS_PNH.add(sc);
                  }
              }
              
               //======================= tìm kiếm theo ngày =====================================//
              if(cbxTimKiem.getSelectedItem().equals("Ngày nhập")){
                  if(day.getDate() == null || day1.getDate() == null){
                  JOptionPane.showMessageDialog(null, "Vui lòng chọn đầy đủ khoảng thời gian"); 
                  }
                  else {
                      
                        SimpleDateFormat date1 = new SimpleDateFormat("yyyy-MM-dd");
                        SimpleDateFormat date2 = new SimpleDateFormat("yyyy-MM-dd");
                        SimpleDateFormat date11 = new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat date22 = new SimpleDateFormat("dd-MM-yyyy");
                        String day11 = String.valueOf(date1.format(day.getDate()));
                        String day12 = String.valueOf(date2.format(day1.getDate()));
                        String day111 = String.valueOf(date11.format(day.getDate()));
                        String day122 = String.valueOf(date22.format(day1.getDate()));
                        Xulydulieu xldl = new Xulydulieu();
                        long tinhngay = xldl.TinhSoNgay(day111, day122);
                        if(tinhngay <0){
                            JOptionPane.showMessageDialog(null, "Khoảng thời gian không hợp lệ");
                        }
                        else {
                       ArrayList<PhieuNhapHangDTO> dspnhtk_ngay = new ArrayList<>();
                                        dspnhtk_ngay =  pnhbus.timkiemNgay(day11,day12);
                                         sc.removeAll();
                                         JTable tbl11 =Table_DSPNH(dspnhtk_ngay);
                                         sc1 = new JScrollPane(tbl11);
                                            sc1.setBounds(0, 0, 980, 380);
                                            sc.add(sc1);
                                            sc.repaint();
                                            sc.revalidate();
                                            pBangDS_PNH.add(sc);
                  }
                  }
              }
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
               // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
               // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btTimKiem.setBackground(Color.DARK_GRAY);
                btTimKiem.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
               btTimKiem.setBackground(colorButton);
            }
        
        });
        
        //===========================  BT Xem chi tiết ============================//
        ImageIcon iconxemchitiet = new ImageIcon("src/img/eye.png");
        btXemChiTiet = new JButton("Xem chi tiết",iconxemchitiet);
        btXemChiTiet.setBounds(535, 120, 160, 35);
        btXemChiTiet.setBackground(colorButton);
        btXemChiTiet.setFont(font_Button);
        btXemChiTiet.setForeground(Color.WHITE);
        btXemChiTiet.setBorderPainted(false);
        btXemChiTiet.setFocusPainted(false);
        pTimKiem.add(btXemChiTiet);
        
        btXemChiTiet.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent arg0) {
//             int i = tbdspnh.getSelectedRow();
//                if(i < 0){
//                    JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp cần xem thông tin trên bảng rồi hãy nhấn nút này !!!\n Thank you .");
//                }
//                else {
//                Xulydulieu xldl = new Xulydulieu();
//                Buttonxemchitiet();
//                PhieuNhapHangBUS pnh_BUS = new PhieuNhapHangBUS();
//                PhieuNhapHangDTO pnh_DTO = new PhieuNhapHangDTO();
//                String hung =tbdspnh.getModel().getValueAt(i, 1).toString();
//                pnh_DTO = pnh_BUS.LayThongtin1PNH(hung);
//                String ngaynhap = pnh_DTO.getNgayNhap();
//                 try {
//                    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(ngaynhap);
//                    txt_CTMaPNH.setText(pnh_DTO.getMaPNH());
//                    txt_CTMaNV.setText(pnh_DTO.getMaNV());
//                    txt_CTMaNCC.setText(pnh_DTO.getMaNCC());
//                    txt_CTNgayNhap.setDate(date);
//                    txt_CTTongTien.setText(xldl.ChuyenKieuTien(String.valueOf(pnh_DTO.getTongTien())));
//                 } catch (ParseException ex) {
//                     Logger.getLogger(PhieuNhapHangGUI.class.getName()).log(Level.SEVERE, null, ex);
//                 } }   
//==========================================================================================================================
                    int i = tbdspnh.getSelectedRow();
                        if(i < 0){
                            JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp cần xem thông tin trên bảng rồi hãy nhấn nút này !!!\n Thank you .");
                        }
                        else {                    
                            
                            PhieuNhapHangBUS pnh_BUS = new PhieuNhapHangBUS();
                            PhieuNhapHangDTO pnh_DTO = new PhieuNhapHangDTO();
                            String hung =tbdspnh.getModel().getValueAt(i, 1).toString();
                            pnh_DTO = pnh_BUS.LayThongtin1PNH(hung);
                            ButtonXemchitietCTPNH(pnh_DTO);
                        }
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
               // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btXemChiTiet.setBackground(Color.DARK_GRAY);
                btXemChiTiet.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
               btXemChiTiet.setBackground(colorButton);
            }
        
        });
        
        //===========================  BT Thêm ============================//
        ImageIcon iconthem = new ImageIcon("src/img/add.png");
        btThem = new JButton("Thêm",iconthem);
        btThem.setBounds(700, 120, 100, 35);
        btThem.setBackground(colorButton);
        btThem.setFont(font_Button);
        btThem.setForeground(Color.WHITE);
        btThem.setBorderPainted(false);
        btThem.setFocusPainted(false);
        pTimKiem.add(btThem);
        
        btThem.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent arg0) {
               Xulydulieu xldl = new Xulydulieu();
               PhieuNhapHangBUS pnh_Bus= new PhieuNhapHangBUS();
               int sl= pnh_Bus.LayMaCuoiDS();
               ButtonThemPNH();
               txt_CTMaPNH.setText(xldl.TaoMaMoi("PNH",sl+1));
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
               // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btThem.setBackground(Color.DARK_GRAY);
                btThem.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
               btThem.setBackground(colorButton);
            }
        
        });
        
        //===========================  BT Sửa ============================//
        ImageIcon iconsua = new ImageIcon("src/img/sua.png");
        btSua = new JButton("Sửa",iconsua);
        btSua.setBounds(805, 120, 90, 35);
        btSua.setFont(font_Button);
        btSua.setBackground(colorButton);
        btSua.setForeground(Color.WHITE);
        btSua.setBorderPainted(false);
        btSua.setFocusPainted(false);
        pTimKiem.add(btSua);
        
        btSua.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent arg0) {
               int i = tbdspnh.getSelectedRow();
                if(i < 0){
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp cần xem thông tin trên bảng rồi hãy nhấn nút này !!!\n Thank you .");
                }
                else {
                Xulydulieu xldl = new Xulydulieu();
              //  Buttonxemchitiet();
                PhieuNhapHangBUS pnh_BUS = new PhieuNhapHangBUS();
                PhieuNhapHangDTO pnh_DTO = new PhieuNhapHangDTO();
                String hung =tbdspnh.getModel().getValueAt(i, 1).toString();
                pnh_DTO = pnh_BUS.LayThongtin1PNH(hung);
                    ButtonSuaCTPNH(pnh_DTO);
                
                }   
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
               // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
               // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btSua.setBackground(Color.DARK_GRAY);
                btSua.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
               btSua.setBackground(colorButton);
            }
        
        });
        
        //===========================  BT Xóa ============================//
        ImageIcon iconxoa = new ImageIcon("src/img/Delete.png");
        btXoa = new JButton("Xóa",iconxoa);
        btXoa.setBounds(900, 120, 90, 35);
        btXoa.setBackground(colorButton);
        btXoa.setFont(font_Button);
        btXoa.setForeground(Color.WHITE);
        btXoa.setBorderPainted(false);
        btXoa.setFocusPainted(false);
        pTimKiem.add(btXoa);
        
        btXoa.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int removeIndex = tbdspnh.getSelectedRow();
                   if(removeIndex == -1){
                   JOptionPane.showMessageDialog(null, "Hãy chọn 1 dòng rồi ấn nút xóa !!!");
                    }
                   else if(PhieuNhapHangBUS.dspnh.size()==0){
                   JOptionPane.showMessageDialog(null, "Không có thông tin để xóa !!!");
                    }
                    else {
                       PhieuNhapHangDTO pnh = new PhieuNhapHangDTO();
                       PhieuNhapHangBUS pnh2 = new PhieuNhapHangBUS();
                       String hung = tbdspnh.getModel().getValueAt(removeIndex, 1).toString();
                       pnh = pnh2.LayThongtin1PNH(hung);
                       
                       int input=JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa", "Xóa phiếu nhập hàng", JOptionPane.YES_NO_OPTION);   
                       if(input==0){
                       if (pnh2.xoaPNH(pnh)==1){
                           JOptionPane.showMessageDialog(null, "Xóa thành công !!!");
                       }
                       else
                       JOptionPane.showMessageDialog(null, "Xóa thất bại !!!");
            } }
            } 

            @Override
            public void mousePressed(MouseEvent arg0) {
               // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
               // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btXoa.setBackground(Color.DARK_GRAY);
                btXoa.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
               btXoa.setBackground(colorButton);
            }
        
        });
        
        return pTimKiem;
    }
    public JPanel BangDSPNH(int width, int height) {
        PhieuNhapHangBUS pnh_BUS = new PhieuNhapHangBUS();
        pBangDS_PNH=new JPanel();
        pBangDS_PNH.setLayout(null);
        pBangDS_PNH.setBounds(0, 210, width, height);
        pBangDS_PNH.setPreferredSize(new Dimension(width,height));
        pBangDS_PNH.setOpaque(true);
        
        if(PhieuNhapHangBUS.dspnh ==null){
            pnh_BUS.docdspnh();
        }
        
        JTable tablehh = Table_DSPNH(PhieuNhapHangBUS.dspnh);
        sc = new JScrollPane(tablehh);
        sc.setBounds(10, 10, 980, 380);
        pBangDS_PNH.add(sc);
        //===========================  BT Cập nhật (Reset) ============================//
        ImageIcon iconCapNhat = new ImageIcon("src/img/update.png");
        btCapNhat = new JButton("Cập nhật",iconCapNhat);
        btCapNhat.setBounds(20, 400, 160, 35);
        btCapNhat.setBackground(colorButton);
        btCapNhat.setFont(font_Button);
        btCapNhat.setForeground(Color.WHITE);
        btCapNhat.setBorderPainted(false);
        btCapNhat.setFocusPainted(false);
        pBangDS_PNH.add(btCapNhat);
	
        btCapNhat.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent arg0) {
                txtManhacungcap.setText(null);
                txtManhanvien.setText(null);
                bt_DanhsachNCC1.setBounds(0,0,0,0);
                bt_DanhsachNV1.setBounds(0,0,0,0);
                cbxTimKiem.setSelectedIndex(0);
                day.setDate(null);
                day1.setDate(null);
                editor1.setEditable(false);
                editor2.setEditable(false);
                PhieuNhapHangBUS pnpBus = new PhieuNhapHangBUS();
                pnpBus.docdspnh();
                sc.removeAll();
                JTable tb =Table_DSPNH(PhieuNhapHangBUS.dspnh);
                sc1 = new JScrollPane(tb);
                sc1.setBounds(0, 0, 980, 380);
                sc.add(sc1);
                sc.repaint();
                sc.revalidate();
                pBangDS_PNH.add(sc);
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
              //  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
              //  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btCapNhat.setBackground(Color.DARK_GRAY);
                btCapNhat.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
               btCapNhat.setBackground(colorButton);
            }
        
        });
        
        return pBangDS_PNH;
    }
    public JTable Table_DSPNH(ArrayList<PhieuNhapHangDTO> dspnh){
        int stt =1;
        tbdspnh=new JTable();
        Xulydulieu xldl = new Xulydulieu();
        model_dspnh = new DefaultTableModel();
        tbdspnh.getTableHeader().setBackground(colorButton);
        tbdspnh.getTableHeader().setForeground(Color.WHITE);
        tbdspnh.getTableHeader().setFont(new Font("Times New Roman",0,18));
        tbdspnh.getTableHeader().setPreferredSize(new Dimension(0,30));
        tbdspnh.setFont(new Font("Times New Roman",0,18));
        tbdspnh.setForeground(Color.BLACK);
        tbdspnh.setRowHeight(25);
        String [] header={"STT","Mã phiếu nhập","Mã nhân viên","Mã NCC", "Ngày nhập","Tổng tiền"};
                if(model_dspnh.getRowCount()==0){
                   model_dspnh=new DefaultTableModel(header,0);
                }
           for(PhieuNhapHangDTO pnh : dspnh){
                Vector row=new Vector();
                        row.add(stt);
                        row.add(pnh.getMaPNH());
                        row.add(pnh.getMaNV());
                        row.add(pnh.getMaNCC());
                        row.add(pnh.getNgayNhap());
                        row.add(xldl.ChuyenKieuTien(String.valueOf(pnh.getTongTien())));
                        if(pnh.getTinhTrang()==1){
                        model_dspnh.addRow(row);
                        }
                        stt= stt + 1;
           }
        tbdspnh.setModel(model_dspnh);
        tbdspnh.getColumnModel().getColumn(0).setMaxWidth(50);
        //        //--------------------Căn giữa trong bảng-----------------------//
        DefaultTableCellRenderer center=new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        tbdspnh.setDefaultRenderer(tbdspnh.getColumnClass(0), center);
        tbdspnh.updateUI();
//        //--------------------------------------------------------------//
        return tbdspnh;
    }

    
    public void BangChonDSNCC(){
        JFrame fchon=new JFrame();
        fchon.setLayout(null);
        fchon.setBounds(0, 0, 600, 340);
        fchon.setPreferredSize(new Dimension(600,340));
        
        //---------------------------------Hiện giữa màn hình-----------------------------//
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        fchon.setLocation((int)d.getWidth()/2 - (int)fchon.getPreferredSize().getWidth()/2,
                      (int)d.getHeight()/2 - (int)fchon.getPreferredSize().getHeight()/2);
        //--------------------------------------------------------------------------------//
        
        JLabel lbTieude=new JLabel("Chọn nhà cung cấp");
        lbTieude.setFont(font_lbCTThongtinPNH);
        lbTieude.setForeground(Color.BLACK);
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        lbTieude.setOpaque(true);
        lbTieude.setBackground(colorButton);
        lbTieude.setForeground(Color.white);
        lbTieude.setBounds(0, 0, 600, 55);
        
        fchon.add(lbTieude);
        
        NhaCungCapBUS nccbus=new NhaCungCapBUS();
        if (NhaCungCapBUS.dsncc==null){
            nccbus.docdsncc();
        }
        tbncc=new JTable();
        tbncc.getTableHeader().setBackground(new Color(36,36,36));
        tbncc.getTableHeader().setForeground(Color.WHITE);
        tbncc.getTableHeader().setFont(font_lb);
        tbncc.getTableHeader().setPreferredSize(new Dimension(0,30));
        tbncc.setFont(font_lb);
        tbncc.setForeground(Color.BLACK);
        tbncc.setRowHeight(25);
        
        DefaultTableModel md=new DefaultTableModel();
        Vector cot=new Vector();
        cot.add("STT");
        cot.add("Mã NCC");
        cot.add("Tên NCC");
        cot.add("SĐT");
        if (md.getRowCount()==0){
            md=new DefaultTableModel(cot, 0);
        }
        int i=1;
        for (NhaCungCapDTO ncc : NhaCungCapBUS.dsncc){
            Vector dong=new Vector();
            dong.add(i);
            dong.add(ncc.getMaNCC());
            dong.add(ncc.getTenNCC());
            dong.add(ncc.getSdt());
            if(ncc.getTinhTrang()==1){
            md.addRow(dong);
            i++;}
        }
        tbncc.setModel(md);
        tbncc.getColumnModel().getColumn(0).setMinWidth(50);
        tbncc.getColumnModel().getColumn(1).setMinWidth(120);
        tbncc.getColumnModel().getColumn(2).setMinWidth(220);
        tbncc.getColumnModel().getColumn(3).setMinWidth(130);                
        
        //--------------------Căn giữa trong bảng-----------------------//
        DefaultTableCellRenderer center=new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        tbncc.setDefaultRenderer(tbncc.getColumnClass(0), center);
        tbncc.updateUI();
        //--------------------------------------------------------------//
        
        JScrollPane sc=new JScrollPane();
        sc.setViewportView(tbncc);
        sc.setBounds(35, 80, 520, 200);
        
         tbncc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                    fchon.setVisible(false);
            }
        });
        fchon.add(sc);
        fchon.setVisible(true);
    }
    
    
        public void BangChonDSNCC_SuaPNH(){
         tbncc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Xulydulieu xldl = new Xulydulieu();
                    int i = tbncc.getSelectedRow();
                    txt_CTMaNCC.setText(tbncc.getModel().getValueAt(i, 1).toString());
            }
        });
    }
        
        
    public void BangChonDSNCC_TimKiemPNH(){
         tbncc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Xulydulieu xldl = new Xulydulieu();
                    int i = tbncc.getSelectedRow();
                    txtManhacungcap.setText(tbncc.getModel().getValueAt(i, 1).toString());
            }
        });
    }
    
    public void BangChonDSNV(){
        JFrame fchon=new JFrame();
        fchon.setLayout(null);
        fchon.setBounds(0, 0, 600, 340);
        fchon.setPreferredSize(new Dimension(600,340));
        
        //---------------------------------Hiện giữa màn hình-----------------------------//
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        fchon.setLocation((int)d.getWidth()/2 - (int)fchon.getPreferredSize().getWidth()/2,
                      (int)d.getHeight()/2 - (int)fchon.getPreferredSize().getHeight()/2);
        //--------------------------------------------------------------------------------//
        
        JLabel lbTieude=new JLabel("Chọn nhân viên");
        lbTieude.setFont(font_lbCTThongtinPNH);
        lbTieude.setForeground(Color.BLACK);
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        lbTieude.setOpaque(true);
        lbTieude.setBackground(colorButton);
        lbTieude.setForeground(Color.white);
        lbTieude.setBounds(0, 0, 600, 55);
        
        fchon.add(lbTieude);
        
       NhanVienBUS nvbus=new NhanVienBUS();
        if (NhanVienBUS.dsnv==null){
            nvbus.docdsnv();
        }
        tbnv=new JTable();
        tbnv.getTableHeader().setBackground(new Color(36,36,36));
        tbnv.getTableHeader().setForeground(Color.WHITE);
        tbnv.getTableHeader().setFont(font_lb);
        tbnv.getTableHeader().setPreferredSize(new Dimension(0,30));
        tbnv.setFont(font_lb);
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
            if(k.getTinhTrang()==1){
            md.addRow(dong);
            i++;}
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
        sc.setBounds(35, 80, 520, 200);
        tbnv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fchon.setVisible(false);
            }
        });
        
        fchon.add(sc);
        fchon.setVisible(true);
    }
    
    
    public void BangChonDSNV_SuaPNH(){
        tbnv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Xulydulieu xldl = new Xulydulieu();
                int i = tbnv.getSelectedRow();
                txt_CTMaNV.setText(tbnv.getModel().getValueAt(i, 1).toString());
            }
        });
    }
    
    
    public void BangChonDSNV_TimKiemPNH(){
        tbnv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Xulydulieu xldl = new Xulydulieu();
                int i = tbnv.getSelectedRow();
                txtManhanvien.setText(tbnv.getModel().getValueAt(i, 1).toString());
            }
        });
    }
    public void BangChonDSHH(){
        JFrame fchon=new JFrame();
        fchon.setLayout(null);
        fchon.setBounds(0, 0, 600, 340);
        fchon.setPreferredSize(new Dimension(600,340));
        
        //---------------------------------Hiện giữa màn hình-----------------------------//
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        fchon.setLocation((int)d.getWidth()/2 - (int)fchon.getPreferredSize().getWidth()/2,
                      (int)d.getHeight()/2 - (int)fchon.getPreferredSize().getHeight()/2);
        //--------------------------------------------------------------------------------//
        
        JLabel lbTieude=new JLabel("Chọn hàng hóa");
        lbTieude.setFont(font_lbCTThongtinPNH);
        lbTieude.setForeground(Color.BLACK);
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        lbTieude.setOpaque(true);
        lbTieude.setBackground(colorButton);
        lbTieude.setForeground(Color.white);
        lbTieude.setBounds(0, 0, 600, 55);
        
        fchon.add(lbTieude);
        
       HangHoaBUS hhbus=new HangHoaBUS();
        if (HangHoaBUS.dshh==null){
            hhbus.docdshh();
        }
        JTable tbnv=new JTable();
        tbnv.getTableHeader().setBackground(new Color(36,36,36));
        tbnv.getTableHeader().setForeground(Color.WHITE);
        tbnv.getTableHeader().setFont(font_lb);
        tbnv.getTableHeader().setPreferredSize(new Dimension(0,30));
        tbnv.setFont(font_lb);
        tbnv.setForeground(Color.BLACK);
        tbnv.setRowHeight(25);
        
        DefaultTableModel md=new DefaultTableModel();
        Vector cot=new Vector();
        cot.add("STT");
        cot.add("Mã hàng hóa");
        cot.add("Tên hàng hóa");
        cot.add("Đơn giá");
        if (md.getRowCount()==0){
            md=new DefaultTableModel(cot, 0);
        }
        int i=1;
        for (HangHoaDTO k : HangHoaBUS.dshh){
            Vector dong=new Vector();
            dong.add(i);
            dong.add(k.getMaHang());
            dong.add(k.getTenHang());
            dong.add(k.getDonGia());
            if(k.getTinhTrang()==1){
            md.addRow(dong);
            i++;}
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
        sc.setBounds(35, 80, 520, 200);
        
        fchon.add(sc);
        
        tbnv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Xulydulieu xldl = new Xulydulieu();
                    int i = tbnv.getSelectedRow();
                    txt_CTMaHH.setText(tbnv.getModel().getValueAt(i, 1).toString());
                    txt_HHSoLuong.setEditable(true);
                    fchon.setVisible(false);
            }
        });
        
        fchon.setVisible(true);
    }
    
    public void ButtonXemchitietCTPNH(PhieuNhapHangDTO pnh){
        try {
            Xulydulieu xldl = new Xulydulieu();
            JFrame fchitiet_PNH=new JFrame();
            fchitiet_PNH.setLayout(null);
            fchitiet_PNH.setBounds(0, 0, 830, 650);
            fchitiet_PNH.setPreferredSize(new Dimension(830, 650));
            
            //---------------------------------Hiện giữa màn hình-----------------------------//
            Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
            fchitiet_PNH.setLocation((int)d.getWidth()/2 - (int)fchitiet_PNH.getPreferredSize().getWidth()/2,
                    (int)d.getHeight()/2 - (int)fchitiet_PNH.getPreferredSize().getHeight()/2);
            //--------------------------------------------------------------------------------//
            
            lbThongtinPNH=new JLabel("Thông tin phiếu nhập hàng");
            lbThongtinPNH.setFont(font_lbCTThongtinPNH);
            lbThongtinPNH.setForeground(Color.BLACK);
            lbThongtinPNH.setHorizontalAlignment(JLabel.CENTER);
            lbThongtinPNH.setOpaque(true);
            lbThongtinPNH.setBackground(colorButton);
            lbThongtinPNH.setForeground(Color.white);
            lbThongtinPNH.setBounds(0, 0, 830, 55);
            fchitiet_PNH.add(lbThongtinPNH);
            
            lb_CTMaPNH = new JLabel("Mã ");
            lb_CTMaPNH.setFont(font_CT);
            lb_CTMaPNH.setBounds(40, 70, 80, 30);
            fchitiet_PNH.add(lb_CTMaPNH);

            txt_CTMaPNH = new JTextField();
            txt_CTMaPNH.setText(pnh.getMaPNH());
            txt_CTMaPNH.setBounds(90, 70,120, 25);
            txt_CTMaPNH.setFont(font_CT_txt);
            txt_CTMaPNH.setForeground(Color.RED);
            txt_CTMaPNH.setHorizontalAlignment(JTextField.CENTER);
            txt_CTMaPNH.setBorder(BorderFactory.createLoweredBevelBorder());
            txt_CTMaPNH.setEditable(false);
            fchitiet_PNH.add(txt_CTMaPNH);
            
            lb_CTMaNV = new JLabel("Mã nhân viên ");
            lb_CTMaNV.setFont(font_CT);
            lb_CTMaNV.setOpaque(true);
            lb_CTMaNV.setBounds(20, 115, 130, 30);
            fchitiet_PNH.add(lb_CTMaNV);

            txt_CTMaNV = new JTextField();
            txt_CTMaNV.setText(pnh.getMaNV());
            txt_CTMaNV.setBounds(170, 115, 130, 25);
            txt_CTMaNV.setFont(font_CT_txt);
            txt_CTMaNV.setHorizontalAlignment(JTextField.CENTER);
            txt_CTMaNV.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorButton));
            txt_CTMaNV.setEditable(false);
            fchitiet_PNH.add(txt_CTMaNV);
            
//            ImageIcon icon = new ImageIcon("src/img/select.png");
//            bt_DanhsachNV = new JButton(icon);
//            bt_DanhsachNV.setBounds(310,113,60,28);
//            bt_DanhsachNV.setBackground(colorButton);
//            bt_DanhsachNV.setBorderPainted(false);
//            bt_DanhsachNV.setFocusPainted(false);
//            bt_DanhsachNV.addMouseListener(new java.awt.event.MouseAdapter() {
//                public void mouseClicked(java.awt.event.MouseEvent evt) {
//                     BangChonDSNV();
//                }
//                 @Override
//                public void mouseEntered(MouseEvent arg0) {
//                    bt_DanhsachNV.setBackground(Color.DARK_GRAY);
//                    bt_DanhsachNV.setCursor(new Cursor(Cursor.HAND_CURSOR));
//                }
//                @Override
//                public void mouseExited(MouseEvent arg0) {
//                   bt_DanhsachNV.setBackground(colorButton);
//                }
//            });
//            fchitiet_PNH.add(bt_DanhsachNV);

                lb_CTMaNCC = new JLabel("Mã nhà cung cấp ");
                lb_CTMaNCC.setFont(font_CT);
                lb_CTMaNCC.setOpaque(true);
                lb_CTMaNCC.setBounds(400, 115, 170, 30);
                fchitiet_PNH.add(lb_CTMaNCC);

                txt_CTMaNCC = new JTextField();
                txt_CTMaNCC.setText(pnh.getMaNCC());
                txt_CTMaNCC.setBounds(580, 113, 130, 25);
                txt_CTMaNCC.setFont(font_CT_txt);
                txt_CTMaNCC.setHorizontalAlignment(JTextField.CENTER);
                txt_CTMaNCC.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorButton));
                txt_CTMaNCC.setEditable(false);
                fchitiet_PNH.add(txt_CTMaNCC);

                //            bt_DanhsachNCC = new JButton(icon);
                //            bt_DanhsachNCC.setBounds(720,113,60,28);
                //            bt_DanhsachNCC.setBackground(colorButton);
                //            bt_DanhsachNCC.setBorderPainted(false);
                //            bt_DanhsachNCC.setFocusPainted(false);
                //            bt_DanhsachNCC.addMouseListener(new java.awt.event.MouseAdapter() {
                //                public void mouseClicked(java.awt.event.MouseEvent evt) {
                //                     BangChonDSNCC();
                //                }
                //                 @Override
                //                public void mouseEntered(MouseEvent arg0) {
                //                    bt_DanhsachNCC.setBackground(Color.DARK_GRAY);
                //                    bt_DanhsachNCC.setCursor(new Cursor(Cursor.HAND_CURSOR));
                //                }
                //                @Override
                //                public void mouseExited(MouseEvent arg0) {
                //                   bt_DanhsachNCC.setBackground(colorButton);
                //                }
                //            });
                //            fchitiet_PNH.add(bt_DanhsachNCC);

                lb_CTNgayNhap = new JLabel("Ngày nhập ");
                lb_CTNgayNhap.setFont(font_CT);
                lb_CTNgayNhap.setOpaque(true);
                lb_CTNgayNhap.setBounds(20, 160, 130, 30);
                fchitiet_PNH.add(lb_CTNgayNhap);

                txt_CTNgayNhap = new JDateChooser();
                String ngay123 =pnh.getNgayNhap();
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(ngay123);
                txt_CTNgayNhap.setDate(date);
                txt_CTNgayNhap.setDateFormatString("yyyy-MM-dd");
                txt_CTNgayNhap.setBounds(160, 160, 160, 30);
                txt_CTNgayNhap.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorButton));
                editor = (JTextFieldDateEditor) txt_CTNgayNhap.getDateEditor();
                editor.setEditable(false);
                txt_CTNgayNhap.setFont(font_CT_txt);
                fchitiet_PNH.add(txt_CTNgayNhap);

                lb_CTTongTien = new JLabel("Tổng tiền phiếu nhập");
                lb_CTTongTien.setFont(font_CT);
                lb_CTTongTien.setOpaque(true);
                lb_CTTongTien.setBounds(390, 160, 190, 30);
                fchitiet_PNH.add(lb_CTTongTien);
                
                txt_CTTongTien = new JTextField();
                txt_CTTongTien.setText(xldl.ChuyenKieuTien(String.valueOf(pnh.getTongTien())));
                txt_CTTongTien.setBounds(580, 161, 180, 25);
                txt_CTTongTien.setFont(font_CT_txt);
                txt_CTTongTien.setHorizontalAlignment(JTextField.CENTER);
                txt_CTTongTien.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorButton));
                txt_CTTongTien.setEditable(false);
                fchitiet_PNH.add(txt_CTTongTien);

                lbThongtinCTPNH=new JLabel("Thông tin chi tiết phiếu nhập hàng");
                lbThongtinCTPNH.setFont(font_lbCTThongtinPNH);
                lbThongtinCTPNH.setForeground(Color.BLACK);
                lbThongtinCTPNH.setHorizontalAlignment(JLabel.CENTER);
                lbThongtinCTPNH.setOpaque(true);
                lbThongtinCTPNH.setBackground(colorButton);
                lbThongtinCTPNH.setForeground(Color.white);
                lbThongtinCTPNH.setBounds(0, 200, 830, 55);
                fchitiet_PNH.add(lbThongtinCTPNH);
                    
//                    lb_CTmaHH = new JLabel("Mã hàng hóa ");
//                    lb_CTmaHH.setFont(font_CT);
//                    lb_CTmaHH.setOpaque(true);
//                    lb_CTmaHH.setBounds(20, 270, 130, 30);
//                    fchitiet_PNH.add(lb_CTmaHH);
//
//                    txt_CTMaHH = new JTextField();
//                    txt_CTMaHH.setText(pnh.getMaNV());
//                    txt_CTMaHH.setBounds(170, 270, 130, 25);
//                    txt_CTMaHH.setFont(font_CT_txt);
//                    txt_CTMaHH.setHorizontalAlignment(JTextField.CENTER);
//                    txt_CTMaHH.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorButton));
//                    txt_CTMaHH.setEditable(false);
//                    fchitiet_PNH.add(txt_CTMaHH);
                
                btThoat_CT = new JButton("Thoát");
                btThoat_CT.setBounds(340, 560, 120, 35);
                btThoat_CT.setFont(font_Button);
                btThoat_CT.setBackground(colorButton);
                btThoat_CT.setForeground(Color.WHITE);
                btThoat_CT.setBorderPainted(false);
                btThoat_CT.setFocusPainted(false);
                fchitiet_PNH.add(btThoat_CT);
                btThoat_CT.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        fchitiet_PNH.setVisible(false);
                    }
                    @Override
                    public void mouseEntered(MouseEvent arg0) {
                        btThoat_CT.setBackground(Color.DARK_GRAY);
                        btThoat_CT.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    }

                    @Override
                    public void mouseExited(MouseEvent arg0) {
                        btThoat_CT.setBackground(colorButton);
                    }
                });
                ChiTietPhieuNhapBUS ctpnhbus=new ChiTietPhieuNhapBUS();

                HangHoaBUS hhbus = new HangHoaBUS();
                if (HangHoaBUS.dshh==null){
                    hhbus.docdshh();
                }
                JTable tblCT_PNH=new JTable();
                tblCT_PNH.getTableHeader().setBackground(new Color(36,36,36));
                tblCT_PNH.getTableHeader().setForeground(Color.WHITE);
                tblCT_PNH.getTableHeader().setFont(font_lb);
                tblCT_PNH.getTableHeader().setPreferredSize(new Dimension(0,30));
                tblCT_PNH.setFont(font_lb);
                tblCT_PNH.setForeground(Color.BLACK);
                tblCT_PNH.setRowHeight(25);

                DefaultTableModel md=new DefaultTableModel();
                Vector cot=new Vector();
                cot.add("STT");
                cot.add("Mã hàng");
                cot.add("Tên sản phẩm");
                cot.add("Số lượng");
                cot.add("Thành tiền");
                if (md.getRowCount()==0){
                    md=new DefaultTableModel(cot, 0);
                }
                int i=1;
                ArrayList<ChiTietPhieuNhapDTO> dsctpnh_1 = ctpnhbus.docdsctpnh(pnh.getMaPNH());
                for (ChiTietPhieuNhapDTO ctpnh : dsctpnh_1){
                    Vector dong=new Vector();
                    dong.add(i);
                    dong.add(ctpnh.getMaHang());
                    HangHoaDTO hanghoa= hhbus.LayThongtin1HH(ctpnh.getMaHang());
                    dong.add(hanghoa.getTenHang());
                    dong.add(ctpnh.getSL());
                    dong.add(xldl.ChuyenKieuTien(String.valueOf(ctpnh.getThanhTien())));
                    md.addRow(dong);
                    i++;
                }
                tblCT_PNH.setModel(md);
                tblCT_PNH.getColumnModel().getColumn(0).setMinWidth(50);
                tblCT_PNH.getColumnModel().getColumn(1).setMinWidth(120);
                tblCT_PNH.getColumnModel().getColumn(2).setMinWidth(250);
                tblCT_PNH.getColumnModel().getColumn(3).setMinWidth(120);
                tblCT_PNH.getColumnModel().getColumn(4).setMinWidth(150);
                //--------------------Căn giữa trong bảng-----------------------//
                DefaultTableCellRenderer center=new DefaultTableCellRenderer();
                center.setHorizontalAlignment(JLabel.CENTER);
                tblCT_PNH.setDefaultRenderer(tblCT_PNH.getColumnClass(0), center);
                tblCT_PNH.updateUI();
                //--------------------------------------------------------------//

                JScrollPane sc=new JScrollPane();
                sc.setViewportView(tblCT_PNH);
               // sc.setBounds(60, 350, 690, 200);
                sc.setBounds(60, 270, 690, 270);

                fchitiet_PNH.add(sc);

                fchitiet_PNH.setVisible(true);
        } catch (ParseException ex) {
            Logger.getLogger(PhieuNhapHangGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ButtonSuaCTPNH(PhieuNhapHangDTO pnh){
        try {
            int dongia =0;
            Xulydulieu xldl = new Xulydulieu();
            JFrame fchitiet_PNH=new JFrame();
            fchitiet_PNH.setLayout(null);
            fchitiet_PNH.setBounds(0, 0, 830, 650);
            fchitiet_PNH.setPreferredSize(new Dimension(830, 650));
            
            //---------------------------------Hiện giữa màn hình-----------------------------//
            Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
            fchitiet_PNH.setLocation((int)d.getWidth()/2 - (int)fchitiet_PNH.getPreferredSize().getWidth()/2,
                    (int)d.getHeight()/2 - (int)fchitiet_PNH.getPreferredSize().getHeight()/2);
            //--------------------------------------------------------------------------------//
                ChiTietPhieuNhapBUS ctpnhbus=new ChiTietPhieuNhapBUS();
                HangHoaBUS hhbus = new HangHoaBUS();
                hhbus.docdshh();
                ctpnhbus.docalldsctpnh();
                ArrayList<ChiTietPhieuNhapDTO> dsctpnh_1 = ctpnhbus.docdsctpnh(pnh.getMaPNH());
                tblCT_PNH =tbl_DSCTPNH(dsctpnh_1);
            lbThongtinPNH=new JLabel("Thông tin phiếu nhập hàng");
            lbThongtinPNH.setFont(font_lbCTThongtinPNH);
            lbThongtinPNH.setForeground(Color.BLACK);
            lbThongtinPNH.setHorizontalAlignment(JLabel.CENTER);
            lbThongtinPNH.setOpaque(true);
            lbThongtinPNH.setBackground(colorButton);
            lbThongtinPNH.setForeground(Color.white);
            lbThongtinPNH.setBounds(0, 0, 830, 55);
            fchitiet_PNH.add(lbThongtinPNH);
            
            lb_CTMaPNH = new JLabel("Mã ");
            lb_CTMaPNH.setFont(font_CT);
            lb_CTMaPNH.setBounds(40, 70, 80, 30);
            fchitiet_PNH.add(lb_CTMaPNH);

            txt_CTMaPNH = new JTextField();
            txt_CTMaPNH.setText(pnh.getMaPNH());
            txt_CTMaPNH.setBounds(90, 70,120, 25);
            txt_CTMaPNH.setFont(font_CT_txt);
            txt_CTMaPNH.setForeground(Color.RED);
            txt_CTMaPNH.setHorizontalAlignment(JTextField.CENTER);
            txt_CTMaPNH.setBorder(BorderFactory.createLoweredBevelBorder());
            txt_CTMaPNH.setEditable(false);
            fchitiet_PNH.add(txt_CTMaPNH);
            
            lb_CTMaNV = new JLabel("Mã nhân viên ");
            lb_CTMaNV.setFont(font_CT);
            lb_CTMaNV.setOpaque(true);
            lb_CTMaNV.setBounds(20, 115, 130, 30);
            fchitiet_PNH.add(lb_CTMaNV);

            txt_CTMaNV = new JTextField();
            txt_CTMaNV.setText(pnh.getMaNV());
            txt_CTMaNV.setBounds(170, 115, 130, 25);
            txt_CTMaNV.setFont(font_CT_txt);
            txt_CTMaNV.setHorizontalAlignment(JTextField.CENTER);
            txt_CTMaNV.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorButton));
            txt_CTMaNV.setEditable(false);
            fchitiet_PNH.add(txt_CTMaNV);
            
            ImageIcon icon = new ImageIcon("src/img/select.png");
            bt_DanhsachNV = new JButton(icon);
            bt_DanhsachNV.setBounds(310,113,60,28);
            bt_DanhsachNV.setBackground(colorButton);
            bt_DanhsachNV.setBorderPainted(false);
            bt_DanhsachNV.setFocusPainted(false);
            bt_DanhsachNV.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                     BangChonDSNV();
                     BangChonDSNV_SuaPNH();
                }
                 @Override
                public void mouseEntered(MouseEvent arg0) {
                    bt_DanhsachNV.setBackground(Color.DARK_GRAY);
                    bt_DanhsachNV.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
                @Override
                public void mouseExited(MouseEvent arg0) {
                   bt_DanhsachNV.setBackground(colorButton);
                }
            });
            fchitiet_PNH.add(bt_DanhsachNV);

                lb_CTMaNCC = new JLabel("Mã nhà cung cấp ");
                lb_CTMaNCC.setFont(font_CT);
                lb_CTMaNCC.setOpaque(true);
                lb_CTMaNCC.setBounds(400, 115, 170, 30);
                fchitiet_PNH.add(lb_CTMaNCC);

                txt_CTMaNCC = new JTextField();
                txt_CTMaNCC.setText(pnh.getMaNCC());
                txt_CTMaNCC.setBounds(580, 113, 130, 25);
                txt_CTMaNCC.setFont(font_CT_txt);
                txt_CTMaNCC.setHorizontalAlignment(JTextField.CENTER);
                txt_CTMaNCC.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorButton));
                txt_CTMaNCC.setEditable(false);
                fchitiet_PNH.add(txt_CTMaNCC);
                
                
                            bt_DanhsachNCC = new JButton(icon);
                            bt_DanhsachNCC.setBounds(720,113,60,28);
                            bt_DanhsachNCC.setBackground(colorButton);
                            bt_DanhsachNCC.setBorderPainted(false);
                            bt_DanhsachNCC.setFocusPainted(false);
                            bt_DanhsachNCC.addMouseListener(new java.awt.event.MouseAdapter() {
                                public void mouseClicked(java.awt.event.MouseEvent evt) {
                                     BangChonDSNCC();
                                     BangChonDSNCC_SuaPNH();
                                }
                                 @Override
                                public void mouseEntered(MouseEvent arg0) {
                                    bt_DanhsachNCC.setBackground(Color.DARK_GRAY);
                                    bt_DanhsachNCC.setCursor(new Cursor(Cursor.HAND_CURSOR));
                                }
                                @Override
                                public void mouseExited(MouseEvent arg0) {
                                   bt_DanhsachNCC.setBackground(colorButton);
                                }
                            });
                            fchitiet_PNH.add(bt_DanhsachNCC);

                lb_CTNgayNhap = new JLabel("Ngày nhập ");
                lb_CTNgayNhap.setFont(font_CT);
                lb_CTNgayNhap.setOpaque(true);
                lb_CTNgayNhap.setBounds(20, 160, 130, 30);
                fchitiet_PNH.add(lb_CTNgayNhap);

                txt_CTNgayNhap = new JDateChooser();
                String ngay123 =pnh.getNgayNhap();
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(ngay123);
                txt_CTNgayNhap.setDate(date);
                txt_CTNgayNhap.setDateFormatString("yyyy-MM-dd");
                txt_CTNgayNhap.setBounds(160, 160, 160, 30);
                txt_CTNgayNhap.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorButton));
                editor = (JTextFieldDateEditor) txt_CTNgayNhap.getDateEditor();
                editor.setEditable(false);
                txt_CTNgayNhap.setFont(font_CT_txt);
                fchitiet_PNH.add(txt_CTNgayNhap);

                lb_CTTongTien = new JLabel("Tổng tiền phiếu nhập");
                lb_CTTongTien.setFont(font_CT);
                lb_CTTongTien.setOpaque(true);
                lb_CTTongTien.setBounds(390, 160, 190, 30);
                fchitiet_PNH.add(lb_CTTongTien);

                txt_CTTongTien = new JTextField();
                int tongtien = ctpnhbus.TongTienPNH(dsctpnh_1);
                txt_CTTongTien.setText(xldl.ChuyenKieuTien(String.valueOf(tongtien)));
                txt_CTTongTien.setBounds(580, 161, 180, 25);
                txt_CTTongTien.setFont(font_CT_txt);
                txt_CTTongTien.setHorizontalAlignment(JTextField.CENTER);
                txt_CTTongTien.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorButton));
                txt_CTTongTien.setEditable(false);
                fchitiet_PNH.add(txt_CTTongTien);

                lbThongtinCTPNH=new JLabel("Thông tin chi tiết phiếu nhập hàng");
                lbThongtinCTPNH.setFont(font_lbCTThongtinPNH);
                lbThongtinCTPNH.setForeground(Color.BLACK);
                lbThongtinCTPNH.setHorizontalAlignment(JLabel.CENTER);
                lbThongtinCTPNH.setOpaque(true);
                lbThongtinCTPNH.setBackground(colorButton);
                lbThongtinCTPNH.setForeground(Color.white);
                lbThongtinCTPNH.setBounds(0, 200, 830, 55);
                fchitiet_PNH.add(lbThongtinCTPNH);
                    
                    lb_CTmaHH = new JLabel("Mã hàng hóa ");
                    lb_CTmaHH.setFont(font_CT);
                    lb_CTmaHH.setOpaque(true);
                    lb_CTmaHH.setBounds(20, 270, 130, 30);
                    fchitiet_PNH.add(lb_CTmaHH);

                    txt_CTMaHH = new JTextField();
                    txt_CTMaHH.setBounds(170, 270, 130, 25);
                    txt_CTMaHH.setFont(font_CT_txt);
                    txt_CTMaHH.setHorizontalAlignment(JTextField.CENTER);
                    txt_CTMaHH.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorButton));
                    txt_CTMaHH.setEditable(false);
                    fchitiet_PNH.add(txt_CTMaHH);
                     
                    bt_DanhsachHH = new JButton(icon);
                            bt_DanhsachHH.setBounds(310,268,60,28);
                            bt_DanhsachHH.setBackground(colorButton);
                            bt_DanhsachHH.setBorderPainted(false);
                            bt_DanhsachHH.setFocusPainted(false);
                            bt_DanhsachHH.addMouseListener(new java.awt.event.MouseAdapter() {
                                public void mouseClicked(java.awt.event.MouseEvent evt) {
                                     BangChonDSHH();
                                }
                                 @Override
                                public void mouseEntered(MouseEvent arg0) {
                                    bt_DanhsachHH.setBackground(Color.DARK_GRAY);
                                    bt_DanhsachHH.setCursor(new Cursor(Cursor.HAND_CURSOR));
                                }
                                @Override
                                public void mouseExited(MouseEvent arg0) {
                                   bt_DanhsachHH.setBackground(colorButton);
                                }
                            });
                            fchitiet_PNH.add(bt_DanhsachHH);
                       
                            
                    lb_HHSoLuong = new JLabel("Số lượng ");
                    lb_HHSoLuong.setFont(font_CT);
                    lb_HHSoLuong.setOpaque(true);
                    lb_HHSoLuong.setBounds(20, 310, 130, 30);
                    fchitiet_PNH.add(lb_HHSoLuong);

                    txt_HHSoLuong = new JTextField();
                    txt_HHSoLuong.setBounds(170, 310, 130, 25);
                    txt_HHSoLuong.setFont(font_CT_txt);
                    txt_HHSoLuong.setHorizontalAlignment(JTextField.CENTER);
                    txt_HHSoLuong.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorButton));
                    txt_HHSoLuong.setEditable(false);
                    fchitiet_PNH.add(txt_HHSoLuong);
                    
  //============================= table ========================================================

                sc3=new JScrollPane();
                sc3.setViewportView(tblCT_PNH);
                sc3.setBounds(60, 350, 690, 200);
                fchitiet_PNH.add(sc3);
                    btThem_CT1 = new JButton("Thêm");
                    btThem_CT1.setBounds(490, 310, 120, 35);
                    btThem_CT1.setFont(font_Button);
                    btThem_CT1.setBackground(colorButton);
                    btThem_CT1.setForeground(Color.WHITE);
                    btThem_CT1.setBorderPainted(false);
                    btThem_CT1.setFocusPainted(false);
                    fchitiet_PNH.add(btThem_CT1);
                    btThem_CT1.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        ChiTietPhieuNhapDTO ctpnh = new ChiTietPhieuNhapDTO();
                        ChiTietPhieuNhapBUS ctpnh_bus = new ChiTietPhieuNhapBUS();
                        if(ChiTietPhieuNhapBUS.dsctpnh ==null){
                            ctpnh_bus.docalldsctpnh();
                        }
                        HangHoaDTO hh_dto = new HangHoaDTO();
                        HangHoaBUS hh_bus = new HangHoaBUS();
                        ctpnh.setMaPNH(txt_CTMaPNH.getText());
                        ctpnh.setMaHang(txt_CTMaHH.getText());
                        hh_dto = hh_bus.LayThongtin1HH(txt_CTMaHH.getText());
                        if(xldl.KtraSo(txt_HHSoLuong.getText())==true){
                        ctpnh.setSL(Integer.parseInt(txt_HHSoLuong.getText()));
                        ctpnh.setDonGia(hh_dto.getDonGia());
                        int SoLuong = Integer.parseInt(txt_HHSoLuong.getText());
                        int thanhtien = SoLuong*hh_dto.getDonGia();
                        ctpnh.setThanhTien(thanhtien);
                        ArrayList<ChiTietPhieuNhapDTO> dsctpnh_2 = ctpnhbus.docdsctpnh(pnh.getMaPNH());
                        if(ctpnh_bus.kiemtraHHTrung(dsctpnh_2, txt_CTMaHH.getText())==1){
                            int Soluong_bandau = ctpnh_bus.LaySLHH(dsctpnh_2,txt_CTMaHH.getText());
                            int Soluong_thaydoi = Soluong_bandau+SoLuong;
                            int Tien_bandau = ctpnh_bus.LayTienHH(dsctpnh_2, txt_CTMaHH.getText());
                            int Tien_thaydoi = thanhtien + Tien_bandau;
                            ctpnh_bus.sua(ctpnh.getMaPNH(), Soluong_thaydoi, txt_CTMaHH.getText(),Tien_thaydoi);
                            int sl_cu = hhbus.LaySLHH(txt_CTMaHH.getText());
                            int tongsoluong = sl_cu+Integer.parseInt(txt_HHSoLuong.getText());
                            hhbus.suaSLHangHoa(txt_CTMaHH.getText(), tongsoluong);
                            sc3.removeAll();
                            ArrayList<ChiTietPhieuNhapDTO> dsctpnh_1 = ctpnhbus.docdsctpnh(pnh.getMaPNH());
                            int tongtien123 = ctpnhbus.TongTienPNH(dsctpnh_1);
                            txt_CTTongTien.setText(xldl.ChuyenKieuTien(String.valueOf(tongtien123)));
                            tblCT_PNH =tbl_DSCTPNH(dsctpnh_1);
                            sc4 = new JScrollPane(tblCT_PNH);
                            sc4.setBounds(0, 0, 690, 200);
                            sc3.add(sc4);
                            sc3.repaint();
                            sc3.revalidate();
                            txt_CTMaHH.setText(null);
                            txt_HHSoLuong.setText(null);
                            fchitiet_PNH.add(sc3);
                            txt_CTMaHH.setText(null);
                            txt_HHSoLuong.setText(null);
                        
                        }
                        else{ 
                            if(ctpnh_bus.themCTPNH(ctpnh)==1){
                            JOptionPane.showMessageDialog(fchitiet_PNH, "Thêm thành công");
                                int sl_cu = hhbus.LaySLHH(ctpnh.getMaHang());
                                int tongsoluong = sl_cu+ctpnh.getSL();
                                hhbus.suaSLHangHoa(ctpnh.getMaHang(), tongsoluong);
                            ArrayList<ChiTietPhieuNhapDTO> dsctpnh_1 = ctpnhbus.docdsctpnh(pnh.getMaPNH());
                            int soTT = dsctpnh_1.size();
                            int tongtien123 = ctpnhbus.TongTienPNH(dsctpnh_1);
                            txt_CTTongTien.setText(xldl.ChuyenKieuTien(String.valueOf(tongtien123)));
                           Vector dong = new Vector();
                           dong.add(soTT);
                           dong.add(ctpnh.getMaHang());
                           dong.add(hh_bus.LayThongtin1HH(ctpnh.getMaHang()).getTenHang());
                           dong.add(ctpnh.getSL());
                           dong.add(ctpnh.getThanhTien());
                           md.addRow(dong);
                           tblCT_PNH.setModel(md);
                           txt_CTMaHH.setText(null);
                           txt_HHSoLuong.setText(null);
                        }
                        
                        else JOptionPane.showMessageDialog(fchitiet_PNH, "Thêm thất bại");
                        }
                        }
                    }
                    @Override
                    public void mouseEntered(MouseEvent arg0) {
                        btThem_CT1.setBackground(Color.DARK_GRAY);
                        btThem_CT1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    }

                    @Override
                    public void mouseExited(MouseEvent arg0) {
                        btThem_CT1.setBackground(colorButton);
                    }
                });
                    
                    btXoa_CT1 = new JButton("Xóa");
                    btXoa_CT1.setBounds(630, 310, 120, 35);
                    btXoa_CT1.setFont(font_Button);
                    btXoa_CT1.setBackground(colorButton);
                    btXoa_CT1.setForeground(Color.WHITE);
                    btXoa_CT1.setBorderPainted(false);
                    btXoa_CT1.setFocusPainted(false);
                    fchitiet_PNH.add(btXoa_CT1);
                    btXoa_CT1.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        int removeIndex = tblCT_PNH.getSelectedRow();
                                           if(removeIndex == -1){
                                           JOptionPane.showMessageDialog(fchitiet_PNH, "Hãy chọn 1 dòng rồi ấn nút xóa !!!");
                                            }
                                           else if(HangHoaBUS.dshh.size()==0){
                                           JOptionPane.showMessageDialog(fchitiet_PNH, "Không có thông tin để xóa !!!");
                                            }
                         else {
                       ChiTietPhieuNhapDTO hh = new ChiTietPhieuNhapDTO();
                       ChiTietPhieuNhapBUS hhbus = new ChiTietPhieuNhapBUS();
                       HangHoaBUS hhhBUS = new HangHoaBUS();
                       hhhBUS.docdshh();
                       if(ChiTietPhieuNhapBUS.dsctpnh == null){
                           hhbus.docalldsctpnh();
                       }
                       int soluonghh =Integer.parseInt(tblCT_PNH.getModel().getValueAt(removeIndex, 3).toString());
                       String mahh11 = tblCT_PNH.getModel().getValueAt(removeIndex, 1).toString();
                       int soluong_db= hhhBUS.LaySLHH(mahh11);
                       int so_luong_moi = soluong_db - soluonghh;
                       hhhBUS.suaSLHangHoa(mahh11, so_luong_moi);
                       String mapnh11 = txt_CTMaPNH.getText();
                       hh = hhbus.LayThongtin1CTPN(mapnh11,mahh11);
                       int input=JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa", "Xóa hàng hóa", JOptionPane.YES_NO_OPTION);   
                       if(input==0){
                       if (hhbus.XoaCTPNH(hh)==1){
                           JOptionPane.showMessageDialog(null, "Xóa thành công !!!");
                           ArrayList<ChiTietPhieuNhapDTO> dsctpnh_1 = ctpnhbus.docdsctpnh(pnh.getMaPNH());
                            int tongtien123 = ctpnhbus.TongTienPNH(dsctpnh_1);
                            txt_CTTongTien.setText(xldl.ChuyenKieuTien(String.valueOf(tongtien123)));
                           md.removeRow(removeIndex);
                           tblCT_PNH.setModel(md);
                       }
                       else
                       JOptionPane.showMessageDialog(null, "Xóa thất bại !!!");
            }}
                    }
                    @Override
                    public void mouseEntered(MouseEvent arg0) {
                        btXoa_CT1.setBackground(Color.DARK_GRAY);
                        btXoa_CT1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    }

                    @Override
                    public void mouseExited(MouseEvent arg0) {
                        btXoa_CT1.setBackground(colorButton);
                    }
                });
                    
                btThem_CT = new JButton("Hoàn tất");
                btThem_CT.setBounds(340, 560, 120, 35);
                btThem_CT.setFont(font_Button);
                btThem_CT.setBackground(colorButton);
                btThem_CT.setForeground(Color.WHITE);
                btThem_CT.setBorderPainted(false);
                btThem_CT.setFocusPainted(false);
                fchitiet_PNH.add(btThem_CT);
                btThem_CT.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        Xulydulieu xldl = new Xulydulieu();
                        PhieuNhapHangDTO pnhDTO = new PhieuNhapHangDTO();
                        PhieuNhapHangBUS pnhBUS = new PhieuNhapHangBUS();
                        pnhDTO.setMaPNH(txt_CTMaPNH.getText());
                        pnhDTO.setMaNV(txt_CTMaNV.getText());
                        pnhDTO.setMaNCC(txt_CTMaNCC.getText());
                        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                        String ngay = String.valueOf(date.format(txt_CTNgayNhap.getDate()));
                        pnhDTO.setNgayNhap(ngay);
                        pnhDTO.setTongTien(Integer.parseInt(xldl.Chuyentien1(txt_CTTongTien.getText())));
                        pnhDTO.setTinhTrang(1);
                        if(pnhBUS.suaPNH(pnhDTO)==1){
                            JOptionPane.showMessageDialog(null, "Cập nhật thông tin thành công");
                            fchitiet_PNH.setVisible(false);
                        }
                        else JOptionPane.showMessageDialog(null, "Cập nhật thông tin thất bại");
                    }
                    @Override
                    public void mouseEntered(MouseEvent arg0) {
                        btThem_CT.setBackground(Color.DARK_GRAY);
                        btThem_CT.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    }

                    @Override
                    public void mouseExited(MouseEvent arg0) {
                        btThem_CT.setBackground(colorButton);
                    }
                });
                    
                btThoat_CT = new JButton("Thoát");
                btThoat_CT.setBounds(0,0,0,0);
                btThoat_CT.setFont(font_Button);
                btThoat_CT.setBackground(colorButton);
                btThoat_CT.setForeground(Color.WHITE);
                btThoat_CT.setBorderPainted(false);
                btThoat_CT.setFocusPainted(false);
                fchitiet_PNH.add(btThoat_CT);
                btThoat_CT.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        fchitiet_PNH.setVisible(false);
                    }
                    @Override
                    public void mouseEntered(MouseEvent arg0) {
                        btThoat_CT.setBackground(Color.DARK_GRAY);
                        btThoat_CT.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    }

                    @Override
                    public void mouseExited(MouseEvent arg0) {
                        btThoat_CT.setBackground(colorButton);
                    }
                });

                
                //--------------------------------------------------------------//


                fchitiet_PNH.setVisible(true);
        } catch (ParseException ex) {
            Logger.getLogger(PhieuNhapHangGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public JTable tbl_DSCTPNH(ArrayList<ChiTietPhieuNhapDTO> pnh_hh){
        Xulydulieu xldl = new Xulydulieu();
        tblCT_PNH=new JTable();
                tblCT_PNH.getTableHeader().setBackground(new Color(36,36,36));
                tblCT_PNH.getTableHeader().setForeground(Color.WHITE);
                tblCT_PNH.getTableHeader().setFont(font_lb);
                tblCT_PNH.getTableHeader().setPreferredSize(new Dimension(0,30));
                tblCT_PNH.setFont(font_lb);
                tblCT_PNH.setForeground(Color.BLACK);
                tblCT_PNH.setRowHeight(25);
                ChiTietPhieuNhapBUS ctpnhbus=new ChiTietPhieuNhapBUS();
                HangHoaBUS hhbus = new HangHoaBUS();
                if (HangHoaBUS.dshh==null){
                    hhbus.docdshh();
                }
                md=new DefaultTableModel();
                Vector cot=new Vector();
                cot.add("STT");
                cot.add("Mã hàng");
                cot.add("Tên sản phẩm");
                cot.add("Số lượng");
                cot.add("Thành tiền");
                if (md.getRowCount()==0){
                    md=new DefaultTableModel(cot, 0);
                }
                int i=1;
                for (ChiTietPhieuNhapDTO ctpnh : pnh_hh){
                    Vector dong=new Vector();
                    dong.add(i);
                    dong.add(ctpnh.getMaHang());
                    HangHoaDTO hanghoa= hhbus.LayThongtin1HH(ctpnh.getMaHang());
                    dong.add(hanghoa.getTenHang());
                    dong.add(ctpnh.getSL());
                    dong.add(xldl.ChuyenKieuTien(String.valueOf(ctpnh.getThanhTien())));
                    md.addRow(dong);
                    i++;
                }
                tblCT_PNH.setModel(md);
                tblCT_PNH.getColumnModel().getColumn(0).setMinWidth(50);
                tblCT_PNH.getColumnModel().getColumn(1).setMinWidth(120);
                tblCT_PNH.getColumnModel().getColumn(2).setMinWidth(250);
                tblCT_PNH.getColumnModel().getColumn(3).setMinWidth(120);
                tblCT_PNH.getColumnModel().getColumn(4).setMinWidth(150);
                //--------------------Căn giữa trong bảng-----------------------//
                DefaultTableCellRenderer center=new DefaultTableCellRenderer();
                center.setHorizontalAlignment(JLabel.CENTER);
                tblCT_PNH.setDefaultRenderer(tblCT_PNH.getColumnClass(0), center);
                tblCT_PNH.updateUI();
                return tblCT_PNH;
    }
    
    public void ButtonThemPNH(){
        try { 
            frame_chitiet = new JFrame();
            frame_chitiet.setSize(600, 370);
            frame_chitiet.setLayout(null);
            frame_chitiet.setLocation(550, 220);
            
            lbThongtinPNH = new JLabel("Thông tin phiếu nhập hàng");
            lbThongtinPNH.setFont(font_lbCTThongtinPNH);
            lbThongtinPNH.setOpaque(true);
            lbThongtinPNH.setBackground(colorButton);
            lbThongtinPNH.setForeground(Color.WHITE);
            lbThongtinPNH.setBounds(0, 0, 600, 50);
            lbThongtinPNH.setHorizontalAlignment(JLabel.CENTER);
            frame_chitiet.add(lbThongtinPNH);
            
            lb_CTMaPNH = new JLabel("Mã ");
            lb_CTMaPNH.setFont(font_CT);
            lb_CTMaPNH.setBounds(40, 70, 80, 30);
            frame_chitiet.add(lb_CTMaPNH);
            
            txt_CTMaPNH = new JTextField();
            txt_CTMaPNH.setBounds(90, 70,120, 25);
            txt_CTMaPNH.setFont(font_CT_txt);
            txt_CTMaPNH.setForeground(Color.RED);
            txt_CTMaPNH.setHorizontalAlignment(JTextField.CENTER);
            txt_CTMaPNH.setBorder(BorderFactory.createLoweredBevelBorder());
            txt_CTMaPNH.setEditable(false);
            frame_chitiet.add(txt_CTMaPNH);
            
            lb_CTMaNV = new JLabel("Mã nhân viên ");
            lb_CTMaNV.setFont(font_CT);
            lb_CTMaNV.setOpaque(true);
            lb_CTMaNV.setBounds(40, 115, 170, 30);
            frame_chitiet.add(lb_CTMaNV);
            
            txt_CTMaNV = new JTextField();
            txt_CTMaNV.setBounds(230, 115, 180, 25);
            txt_CTMaNV.setFont(font_CT_txt);
            txt_CTMaNV.setHorizontalAlignment(JTextField.CENTER);
            txt_CTMaNV.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorButton));
            txt_CTMaNV.setEditable(false);
            frame_chitiet.add(txt_CTMaNV);
            
                ImageIcon icon = new ImageIcon("src/img/select.png");
                bt_DanhsachNV = new JButton(icon);
                bt_DanhsachNV.setBounds(420,113,60,28);
                bt_DanhsachNV.setBackground(colorButton);
                bt_DanhsachNV.setBorderPainted(false);
                bt_DanhsachNV.setFocusPainted(false);
                bt_DanhsachNV.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                         BangChonDSNV();
                         BangChonDSNV_SuaPNH();
                    }
                     @Override
                    public void mouseEntered(MouseEvent arg0) {
                        bt_DanhsachNV.setBackground(Color.DARK_GRAY);
                        bt_DanhsachNV.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    }
                    @Override
                    public void mouseExited(MouseEvent arg0) {
                       bt_DanhsachNV.setBackground(colorButton);
                    }
                });
                frame_chitiet.add(bt_DanhsachNV);
            
            
            lb_CTMaNCC = new JLabel("Mã nhà cung cấp ");
            lb_CTMaNCC.setFont(font_CT);
            lb_CTMaNCC.setOpaque(true);
            lb_CTMaNCC.setBounds(40, 160, 170, 30);
            frame_chitiet.add(lb_CTMaNCC);
            
            txt_CTMaNCC = new JTextField();
            txt_CTMaNCC.setBounds(230, 161, 180, 25);
            txt_CTMaNCC.setFont(font_CT_txt);
            txt_CTMaNCC.setHorizontalAlignment(JTextField.CENTER);
            txt_CTMaNCC.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorButton));
            txt_CTMaNCC.setEditable(false);
            frame_chitiet.add(txt_CTMaNCC);
            
                           bt_DanhsachNCC = new JButton(icon);
                           bt_DanhsachNCC.setBounds(420,159,60,28);
                           bt_DanhsachNCC.setBackground(colorButton);
                            bt_DanhsachNCC.setBorderPainted(false);
                            bt_DanhsachNCC.setFocusPainted(false);
                            bt_DanhsachNCC.addMouseListener(new java.awt.event.MouseAdapter() {
                                public void mouseClicked(java.awt.event.MouseEvent evt) {
                                     BangChonDSNCC();
                                     BangChonDSNCC_SuaPNH();
                                }
                                 @Override
                                public void mouseEntered(MouseEvent arg0) {
                                    bt_DanhsachNCC.setBackground(Color.DARK_GRAY);
                                    bt_DanhsachNCC.setCursor(new Cursor(Cursor.HAND_CURSOR));
                                }
                                @Override
                                public void mouseExited(MouseEvent arg0) {
                                   bt_DanhsachNCC.setBackground(colorButton);
                                }
                            });
                            frame_chitiet.add(bt_DanhsachNCC);
            
            lb_CTNgayNhap = new JLabel("Ngày nhập ");
            lb_CTNgayNhap.setFont(font_CT);
            lb_CTNgayNhap.setOpaque(true);
            lb_CTNgayNhap.setBounds(40, 205, 170, 30);
            frame_chitiet.add(lb_CTNgayNhap);
            
            txt_CTNgayNhap = new JDateChooser();
            txt_CTNgayNhap.setDateFormatString("yyyy-MM-dd");
            String ngay = String.valueOf(java.time.LocalDate.now());
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(ngay);
            txt_CTNgayNhap.setDate(date);
            txt_CTNgayNhap.setBounds(230, 206, 180, 30);
            txt_CTNgayNhap.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorButton));
            editor = (JTextFieldDateEditor) txt_CTNgayNhap.getDateEditor();
            editor.setEditable(false);
            txt_CTNgayNhap.setFont(font_CT_txt);
            frame_chitiet.add(txt_CTNgayNhap);
            
            lb_CTTongTien = new JLabel("Tổng tiền ");
            lb_CTTongTien.setFont(font_CT);
            lb_CTTongTien.setOpaque(true);
            lb_CTTongTien.setBounds(40, 250, 170, 30);
            frame_chitiet.add(lb_CTTongTien);
            
            txt_CTTongTien = new JTextField();
            txt_CTTongTien.setBounds(230, 251, 280, 25);
            txt_CTTongTien.setFont(font_CT_txt);
            txt_CTTongTien.setHorizontalAlignment(JTextField.CENTER);
            txt_CTTongTien.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorButton));
            txt_CTTongTien.setEditable(false);
            frame_chitiet.add(txt_CTTongTien);
            
            btThoat_CT = new JButton("Thoát");
            btThoat_CT.setBounds(0,0,0,0);
            btThoat_CT.setFont(font_Button);
            btThoat_CT.setBackground(colorButton);
            btThoat_CT.setForeground(Color.WHITE);
            btThoat_CT.setBorderPainted(false);
            btThoat_CT.setFocusPainted(false);
            frame_chitiet.add(btThoat_CT);
            
            txt_CTTongTien.setEditable(true);
            txt_CTMaNV.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colorButton));
            txt_CTMaNCC.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colorButton));
            txt_CTNgayNhap.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colorButton));
            txt_CTTongTien.setBounds(0, 0, 0, 0);
            lb_CTTongTien.setBounds(0, 0, 0, 0);
            btThem_CT = new JButton("Thêm");
            btThem_CT.setBounds(170, 270, 120, 35);
            btThem_CT.setFont(font_Button);
            btThem_CT.setBackground(colorButton);
            btThem_CT.setForeground(Color.WHITE);
            btThem_CT.setBorderPainted(false);
            btThem_CT.setFocusPainted(false);
            frame_chitiet.add(btThem_CT);
            btThoat_CT.setBounds(310, 270, 120, 35);
            btThem_CT.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    PhieuNhapHangDTO themPNH = new PhieuNhapHangDTO();
                    PhieuNhapHangBUS themPNHBUS = new  PhieuNhapHangBUS();
                    Xulydulieu xldl = new Xulydulieu();

                    themPNH.setMaPNH(txt_CTMaPNH.getText());
                    themPNH.setMaNV(txt_CTMaNV.getText());
                    themPNH.setMaNCC(txt_CTMaNCC.getText());
                    themPNH.setTongTien(0);
                    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                    
                    String ngay = String.valueOf(date.format(txt_CTNgayNhap.getDate()));
                    themPNH.setNgayNhap(ngay);
                    themPNH.setTinhTrang(1);
                    if(themPNHBUS.KiemtraDuLieu(themPNH)==1){
                    if(themPNHBUS.thempnh(themPNH)==1){
                        JOptionPane.showMessageDialog(frame_chitiet, "Thêm phiếu nhập hàng thành công");
                        frame_chitiet.setVisible(false);
                        ButtonSuaCTPNH(themPNH);
                        ThemChiTietPNH();
                    }
                    else JOptionPane.showMessageDialog(null, "Thêm phiếu nhập hàng thất bại");
                }
                }
                
                  @Override
                    public void mouseEntered(MouseEvent arg0) {
                                        btThem_CT.setBackground(Color.DARK_GRAY);
                                        btThem_CT.setCursor(new Cursor(Cursor.HAND_CURSOR));
                                    }

                    @Override
                    public void mouseExited(MouseEvent arg0) {
                                        btThem_CT.setBackground(colorButton);
                }});
            
            btThoat_CT.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    frame_chitiet.setVisible(false);
                }
                @Override
                public void mouseEntered(MouseEvent arg0) {
                    btThoat_CT.setBackground(Color.DARK_GRAY);
                    btThoat_CT.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
                
                @Override
                public void mouseExited(MouseEvent arg0) {
                    btThoat_CT.setBackground(colorButton);
                }
            });
            
            frame_chitiet.setVisible(true);
        } catch (ParseException ex) {
            Logger.getLogger(PhieuNhapHangGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void ThemChiTietPNH(){
        bt_DanhsachNCC.setBounds(0, 0, 0, 0);
        bt_DanhsachNV.setBounds(0, 0, 0, 0);
        editor = (JTextFieldDateEditor) txt_CTNgayNhap.getDateEditor();
        editor.setEditable(false);
    }
    public void TimKiemTheoMaNCC(){
       bt_DanhsachNCC1.setBounds(450,51,60,28);
    }
    public void TimKiemTheoMaNV(){
         bt_DanhsachNV1.setBounds(450,10,60,28);
    }
//    public static void main(String[] args) {
//        QuanLyGUI p=new QuanLyGUI("NV0001", "QL");
//    }
    }
