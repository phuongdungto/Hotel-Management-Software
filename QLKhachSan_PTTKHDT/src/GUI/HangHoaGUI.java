/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.HangHoaBUS;
import BUS.Xulydulieu;
import DTO.HangHoaDTO;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author minhd
 */
public class HangHoaGUI {
    protected  JFrame frame_chitiet;
    protected JPanel pHangHoa;
    protected JPanel pTimKiem;
    protected JPanel pBangDS;
    protected JPanel pBangCT;
    protected JTable tbdshh;
    protected JScrollPane sc,sc1;
    protected JLabel lbTieude,lbTenHangHoa,lbNhomHangHoa,lbNgayNhapKhoTu,lbNgayNhapKhoDen;
    protected JLabel lb_ma,lb_ten,lb_soluong,lb_nhom,lb_dongia;
    protected JTextField txtTenHangHoa;
    protected JTextField txt_ma,txt_ten,txt_soluong,txt_nhom,txt_dongia;
    protected JComboBox cbxNhomHangHoa,cbxTimKiem;
    protected JButton btTimKiem,btXemChiTiet,btThem,btSua,btXoa,btCapNhat;
    protected DefaultTableModel model_dshh;
    Font font_lb = new Font("Times New Roman",0,18);
    Font font_tk = new Font("Times New Roman",0,23);
    Font font_txt = new Font("Times New Roman",Font.BOLD,18);
    Font font_Button = new Font("Times New Roman",0,17);
    Font font_lbThongtinHH = new Font("Times New Roman",Font.BOLD,24);
    Font font_lbTenHH= new Font("Times New Roman",Font.BOLD,22);
    protected Color colorButton = new Color(36,36,36);
    protected Color colorLaybel = new Color(238, 238, 238);
    protected Color colorLaybel1 = new Color(246, 246, 246);
    
    protected JButton btThoat_CT,btThemCT,btSuaCT;
    Font font_lbCTThongtinHH = new Font("Times New Roman",Font.BOLD,28);
    protected JLabel lbThongtinHH,lbMaHH,lbTenHH,lbSoluongHH,lbLoaiHH,lbDonGiaHH;
    protected JTextField txt_mahh,txt_tenhh,txt_soluonghh,txt_loaihh,txt_dongiaHH;
    Font font_CT = new Font("Times New Roman",Font.BOLD,20);
    Font font_CT_txt = new Font("Times New Roman",Font.BOLD,18);
    public HangHoaGUI(){
        
    }
    public JPanel KhoiTaoPanel(int width, int height) {
        pHangHoa=new JPanel();
        pHangHoa.setLayout(null);
        pHangHoa.setBackground(Color.WHITE);
        pHangHoa.setBounds(0, 0, width, height);
        pHangHoa.setPreferredSize(new Dimension(width,height));
        pHangHoa.setOpaque(true);
        
        lbTieude=new JLabel();
        lbTieude.setOpaque(true);
        lbTieude.setText("Danh sách hàng hóa");
        lbTieude.setFont(font_lbCTThongtinHH);
        lbTieude.setBounds(0, 0, 1000, 50);
        lbTieude.setForeground(Color.BLACK);
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        pTimKiem = TimKiem(1000, 110);
        pHangHoa.add(lbTieude);
        pHangHoa.add(pTimKiem);
        
        pBangDS = BangDSHangHoa(1000, 490);
        pHangHoa.add(pBangDS);
        return pHangHoa;
    }
     //====================================================  Jpanel các loại tìm kiếm ===============================================//
    public JPanel TimKiem(int width, int height){
        pTimKiem=new JPanel();
        pTimKiem.setLayout(null);
        pTimKiem.setBounds(0, 50, width, height);
        pTimKiem.setPreferredSize(new Dimension(width,height));
        pTimKiem.setOpaque(true);
        //pTimKiem.setBackground(Color.red);
        //=========================== LB Tên hàng hóa ============================//
        lbTenHangHoa = new JLabel("Tên hàng hóa");
        lbTenHangHoa.setFont(font_lbTenHH);
        lbTenHangHoa.setBounds(30, 25, 150, 35);
        lbTenHangHoa.setOpaque(true);
        pTimKiem.add(lbTenHangHoa);
         //=========================== TXT Tên hàng hóa ============================//
        txtTenHangHoa = new JTextField();
        txtTenHangHoa.setBounds(185,25,500,35);
        txtTenHangHoa.setFont(font_tk);
        txtTenHangHoa.setOpaque(true);
        txtTenHangHoa.setBackground(colorLaybel1);
        txtTenHangHoa.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, colorButton));
        pTimKiem.add(txtTenHangHoa);
//        //=========================== Ngày nhập hàng hóa từ ============================//
//        lbNgayNhapKhoTu= new JLabel("Ngày nhập từ");
//        lbNgayNhapKhoTu.setFont(font_lb);
//        lbNgayNhapKhoTu.setBounds(10, 55, 130, 20);
//        lbNgayNhapKhoTu.setOpaque(true);
//        pTimKiem.add(lbNgayNhapKhoTu);
//        //===========================  Ngày nhập hàng hóa từ ============================//
//        JDateChooser day = new JDateChooser();
//        day.setBounds(140, 53, 120, 25);
//        day.setDateFormatString("yyyy-MM-dd");
//        pTimKiem.add(day);
//        //===========================  Ngày nhập hàng hóa đến ============================//
//        lbNgayNhapKhoDen = new JLabel("Ngày nhập đến");
//        lbNgayNhapKhoDen.setFont(font_lb);
//        lbNgayNhapKhoDen.setBounds(280, 55, 140, 20);
//        lbNgayNhapKhoDen.setOpaque(true);
//        pTimKiem.add(lbNgayNhapKhoDen);
//        //===========================  Ngày nhập hàng hóa đến ============================//
//        JDateChooser day1 = new JDateChooser();
//        day1.setBounds(400, 53, 120, 25);
//        day1.setDateFormatString("yyyy-MM-dd");
//        pTimKiem.add(day1);
//        //===========================  CBX Tìm kiếm theo ============================//
//        String DSTimKiem[] = { "Tìm kiếm theo", "Tên", "Nhóm", "Ngày nhập"};
//        cbxTimKiem = new JComboBox(DSTimKiem);
//        cbxTimKiem.setOpaque(true);
//        cbxTimKiem.setBackground(Color.white);
//        cbxTimKiem.setBounds(570, 10, 140, 30);
//        pTimKiem.add(cbxTimKiem);
        //===========================  BT Tìm kiếm ============================//
        ImageIcon iconsearch = new ImageIcon("src/img/search.png");
        btTimKiem = new JButton("Tìm kiếm",iconsearch);
        btTimKiem.setBounds(700, 25, 140, 35);
        btTimKiem.setBackground(colorButton);
        btTimKiem.setFont(font_Button);
        btTimKiem.setForeground(Color.WHITE);
        btTimKiem.setBorderPainted(false);
        btTimKiem.setFocusPainted(false);
        pTimKiem.add(btTimKiem);
        
        btTimKiem.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent arg0) {
             HangHoaBUS hhBUS = new HangHoaBUS();
             if(hhBUS.KtradulieuTimKiem(txtTenHangHoa.getText())==1){
                 ArrayList<HangHoaDTO> ds = hhBUS.Timkiemtheo_TenHH(txtTenHangHoa.getText());
                    sc.removeAll();
                    JTable tb =Table_DSHH(ds);
                    sc1 = new JScrollPane(tb);
                    sc1.setBounds(0, 0, 680, 420);
                    sc.add(sc1);
                    sc.repaint();
                    sc.revalidate();
                    pBangDS.add(sc);
             }
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
                btTimKiem.setBackground(Color.DARK_GRAY);
                btTimKiem.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
               btTimKiem.setBackground(colorButton);
            }
        
        });
        
        //===========================  BT Thêm ============================//
        ImageIcon iconthem = new ImageIcon("src/img/add.png");
        btThem = new JButton("Thêm",iconthem);
        btThem.setBounds(700, 70, 100, 35);
        btThem.setFont(font_Button);
        btThem.setBackground(colorButton);
        btThem.setForeground(Color.WHITE);
        btThem.setBorderPainted(false);
        btThem.setFocusPainted(false);
        pTimKiem.add(btThem);
        
        btThem.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent arg0) {
                Xulydulieu xldl = new Xulydulieu();
                HangHoaBUS hh_Bus= new HangHoaBUS();
                int sl= hh_Bus.LayMaCuoiDS();
               Xemchitiet();
               TrangThaiTxt_Them();
               txt_mahh.setText(xldl.TaoMaMoi("HH",sl+1));
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
              //  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        btSua.setBounds(805, 70, 90, 35);
        btSua.setFont(font_Button);
        btSua.setBackground(colorButton);
        btSua.setForeground(Color.WHITE);
        btSua.setBorderPainted(false);
        btSua.setFocusPainted(false);
        pTimKiem.add(btSua);
        
        btSua.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent arg0) {
               int i = tbdshh.getSelectedRow();
                if(i <0){
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp cần thay đổi thông tin trên bảng rồi hãy nhấn nút này !!!\n Thank you .");
                }
                else {
                Xemchitiet();     
                HangHoaBUS hh_BUS = new HangHoaBUS();
                HangHoaDTO hh_DTO = new HangHoaDTO();
                String hung =tbdshh.getModel().getValueAt(i, 1).toString();
                hh_DTO = hh_BUS.LayThongtin1HH(hung);
                txt_mahh.setText(hh_DTO.getMaHang());
                txt_tenhh.setText(hh_DTO.getTenHang());
                txt_soluonghh.setText(String.valueOf(hh_DTO.getSL()));
                txt_loaihh.setText(hh_DTO.getLoaiHang());
                txt_dongiaHH.setText(String.valueOf(hh_DTO.getDonGia()));
                TrangThaiTxt_Sua();
            }
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
               // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
              //  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        btXoa.setBounds(900, 70, 90, 35);
        btXoa.setFont(font_Button);
        btXoa.setBackground(colorButton);
        btXoa.setForeground(Color.WHITE);
        btXoa.setBorderPainted(false);
        btXoa.setFocusPainted(false);
        pTimKiem.add(btXoa);
        
        btXoa.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent arg0) {
//                       if(txt_ma.getText().equals("")){
//                             JOptionPane.showMessageDialog(null, "Hãy chọn 1 dòng rồi ấn nút xóa !!!");
//                         }
//                       else if(HangHoaBUS.dshh.size()==0){
//                             JOptionPane.showMessageDialog(null, "Không có thông tin để xóa !!!");
//                         }
                        int removeIndex = tbdshh.getSelectedRow();
                                           if(removeIndex == -1){
                                           JOptionPane.showMessageDialog(null, "Hãy chọn 1 dòng rồi ấn nút xóa !!!");
                                            }
                                           else if(HangHoaBUS.dshh.size()==0){
                                           JOptionPane.showMessageDialog(null, "Không có thông tin để xóa !!!");
                                            }
                         else {
                       HangHoaDTO hh = new HangHoaDTO();
                       HangHoaBUS hhbus = new HangHoaBUS();
                       String hung = txt_ma.getText();
                       hh = hhbus.LayThongtin1HH(hung);
                       int input=JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa", "Xóa hàng hóa", JOptionPane.YES_NO_OPTION);   
                       if(input==0){
                       if (hhbus.xoaHanghoa(hh)==1){
                           JOptionPane.showMessageDialog(null, "Xóa thành công !!!");
                       }
                       else
                       JOptionPane.showMessageDialog(null, "Xóa thất bại !!!");
            }}
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
     //====================================================  Jpanel table + chi tiết của table ===============================================//
    public JPanel BangDSHangHoa(int width, int height) {
        HangHoaBUS hh_BUS = new HangHoaBUS();
        pBangDS=new JPanel();
        pBangDS.setLayout(null);
        pBangDS.setBounds(0, 160, width, height);
        pBangDS.setPreferredSize(new Dimension(width,height));
        pBangDS.setOpaque(true);
        //================  Hiện bảng ================//
        if(HangHoaBUS.dshh ==null){
            hh_BUS.docdshh();
        }    
        tbdshh = Table_DSHH(HangHoaBUS.dshh);
        sc = new JScrollPane(tbdshh);
        sc.setBounds(12, 10, 680, 420);
        pBangDS.add(sc);
        //================  Hiện chi tiết ================//
        pBangCT = BangCTHangHoa(295, 420);
        pBangCT.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colorButton));
        pBangDS.add(pBangCT);
        //===========================  BT Cập nhật (Reset) ============================//
        ImageIcon iconCapNhat = new ImageIcon("src/img/update.png");
        btCapNhat = new JButton("Cập nhật",iconCapNhat);
        btCapNhat.setBounds(20, 440, 160, 35);
        btCapNhat.setFont(font_Button);
        btCapNhat.setBackground(colorButton);
        btCapNhat.setForeground(Color.WHITE);
        btCapNhat.setBorderPainted(false);
        btCapNhat.setFocusPainted(false);
        pBangDS.add(btCapNhat);
        
        btCapNhat.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent arg0) {
              Reset();
                sc.removeAll();
                JTable tb =Table_DSHH(HangHoaBUS.dshh);
                sc1 = new JScrollPane(tb);
                sc1.setBounds(0, 0, 680, 420);
                sc.add(sc1);
                sc.repaint();
                sc.revalidate();
                pBangDS.add(sc);
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
                btCapNhat.setBackground(Color.DARK_GRAY);
                btCapNhat.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
               btCapNhat.setBackground(colorButton);
            }
        
        });

        
        return pBangDS;
    }
    //=========================================  Jpanel chi tiết hàng hóa  ===========================================//
     public JPanel BangCTHangHoa(int width, int height) {
        pBangCT=new JPanel();
        pBangCT.setLayout(null);
        pBangCT.setBounds(695, 10, width, height);
        pBangCT.setPreferredSize(new Dimension(width,height));
        pBangCT.setOpaque(true);
        
        JLabel lb_CTHH = new  JLabel("Thông tin hàng hóa");
            lb_CTHH.setBounds(15, 10, 270, 25);
            lb_CTHH.setHorizontalAlignment(JLabel.CENTER);
            lb_CTHH.setFont(font_lbThongtinHH);
            lb_CTHH.setFocusable(false);
            pBangCT.add(lb_CTHH);
            
        lb_ma = new  JLabel("Mã hàng hóa");
            lb_ma.setBounds(15,45, 200, 25);
            lb_ma.setFont(font_lb);
            lb_ma.setFocusable(false);
            pBangCT.add(lb_ma);
            
            txt_ma = new JTextField();
                txt_ma.setBounds(15, 70, 250, 25);
                txt_ma.setFont(font_txt);
                txt_ma.setHorizontalAlignment(JTextField.CENTER);
                txt_ma.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorButton));
                txt_ma.setEditable(false);
                pBangCT.add(txt_ma);
            
        lb_ten = new  JLabel("Tên hàng hóa ");
            lb_ten.setBounds(15, 105, 200, 25);
            lb_ten.setFont(font_lb);
            lb_ten.setFocusable(false);
            pBangCT.add(lb_ten);
            
            txt_ten = new JTextField();
                txt_ten.setBounds(15, 130, 250, 25);
                txt_ten.setFont(font_txt);
                txt_ten.setHorizontalAlignment(JTextField.CENTER);
                txt_ten.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorButton));
                txt_ten.setEditable(false);
                pBangCT.add(txt_ten);  
            
        lb_nhom = new  JLabel("Loại hàng hóa");
            lb_nhom.setBounds(15, 165, 200, 25);
            lb_nhom.setFont(font_lb);
            lb_nhom.setFocusable(false);
            pBangCT.add(lb_nhom);
            
            txt_nhom = new JTextField();
                txt_nhom.setBounds(15, 190, 250, 25);
                txt_nhom.setFont(font_txt);
                txt_nhom.setHorizontalAlignment(JTextField.CENTER);
                txt_nhom.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorButton));
                txt_nhom.setEditable(false);
                pBangCT.add(txt_nhom); 
        
        lb_soluong = new  JLabel("Số lượng hàng hóa");
            lb_soluong.setBounds(15, 225, 200, 25);
            lb_soluong.setFont(font_lb);
            lb_soluong.setFocusable(false);
            pBangCT.add(lb_soluong);
            
            txt_soluong = new JTextField();
                txt_soluong.setBounds(15, 250, 250, 25);
                txt_soluong.setFont(font_txt);
                txt_soluong.setHorizontalAlignment(JTextField.CENTER);
                txt_soluong.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorButton));
                txt_soluong.setEditable(false);
                pBangCT.add(txt_soluong); 
                
        lb_dongia = new  JLabel("Đơn giá sản phẩm");
            lb_dongia.setBounds(15, 285, 200, 25);
            lb_dongia.setFont(font_lb);
            lb_dongia.setFocusable(false);
            pBangCT.add(lb_dongia);
            
            txt_dongia = new JTextField();
                txt_dongia.setBounds(15, 310, 250, 25);
                txt_dongia.setFont(font_txt);
                txt_dongia.setHorizontalAlignment(JTextField.CENTER);
                txt_dongia.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorButton));
                txt_dongia.setEditable(false);
                pBangCT.add(txt_dongia); 
                
        return pBangCT;
    }
    //=========================================  JTable danh sách hàng hóa  ===========================================//
    public JTable Table_DSHH(ArrayList<HangHoaDTO> dshh){
        tbdshh=new JTable();
        int stt=1;
        model_dshh = new DefaultTableModel();
        tbdshh.getTableHeader().setBackground(colorButton);
        tbdshh.getTableHeader().setForeground(Color.WHITE);
        tbdshh.getTableHeader().setFont(new Font("Times New Roman",0,18));
        tbdshh.getTableHeader().setPreferredSize(new Dimension(0,30));
        tbdshh.setFont(new Font("Times New Roman",0,18));
        tbdshh.setForeground(Color.BLACK);
        tbdshh.setRowHeight(25);
        String [] header={"STT","Mã hàng hóa","Tên hàng hóa", "Số lượng"};
                if(model_dshh.getRowCount()==0){
                   model_dshh=new DefaultTableModel(header,0);
                }
        for(HangHoaDTO hh : dshh){
                Vector row=new Vector();
                        row.add(stt);
                        row.add(hh.getMaHang());
                        row.add(hh.getTenHang());
                        row.add(hh.getSL());
                        if(hh.getTinhTrang()==1){
                        model_dshh.addRow(row);
                        }
                        stt= stt + 1;
           }
        tbdshh.setModel(model_dshh);
        tbdshh.getColumnModel().getColumn(0).setMaxWidth(50);
        tbdshh.getColumnModel().getColumn(1).setMinWidth(150);
        tbdshh.getColumnModel().getColumn(2).setMinWidth(150);
        
        tbdshh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Xulydulieu xldl = new Xulydulieu();
                    int i = tbdshh.getSelectedRow();
                    
                HangHoaBUS hh_BUS = new HangHoaBUS();
                HangHoaDTO hh_DTO = new HangHoaDTO();
                String hung =tbdshh.getModel().getValueAt(i, 1).toString();
                hh_DTO = hh_BUS.LayThongtin1HH(hung);
                    txt_ma.setText(hh_DTO.getMaHang());
                    txt_ten.setText(hh_DTO.getTenHang());
                    txt_soluong.setText(String.valueOf(hh_DTO.getSL()));  
                    txt_nhom.setText(hh_DTO.getLoaiHang());
                    txt_dongia.setText(xldl.ChuyenKieuTien(String.valueOf(hh_DTO.getDonGia())));
            }
        });
        
        //        //--------------------Căn giữa trong bảng-----------------------//
        DefaultTableCellRenderer center=new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        tbdshh.setDefaultRenderer(tbdshh.getColumnClass(0), center);
        tbdshh.updateUI();
//        //--------------------------------------------------------------//
        return tbdshh;
    }
   
    public void Xemchitiet(){
        frame_chitiet = new JFrame();
        frame_chitiet.setSize(600, 420);
        frame_chitiet.setLayout(null);
        frame_chitiet.setLocation(550, 220);
        
        lbThongtinHH = new JLabel("Thông tin hàng hóa");
        lbThongtinHH.setFont(font_lbCTThongtinHH);
        lbThongtinHH.setOpaque(true);
        lbThongtinHH.setBackground(colorButton);
        lbThongtinHH.setForeground(Color.WHITE);
        lbThongtinHH.setBounds(0, 0, 600, 50);
        lbThongtinHH.setHorizontalAlignment(JLabel.CENTER);
        frame_chitiet.add(lbThongtinHH);
        
        lbMaHH = new JLabel("Mã ");
        lbMaHH.setFont(font_CT);
        lbMaHH.setBounds(40, 70, 80, 30);
        frame_chitiet.add(lbMaHH);
        
        txt_mahh = new JTextField();
        txt_mahh.setBounds(90, 70,120, 25);
        txt_mahh.setFont(font_CT_txt);
        txt_mahh.setForeground(Color.RED);
        txt_mahh.setHorizontalAlignment(JTextField.CENTER);
        txt_mahh.setBorder(BorderFactory.createLoweredBevelBorder());
        txt_mahh.setEditable(false);
        frame_chitiet.add(txt_mahh);
        
        lbTenHH = new JLabel("Tên hàng hóa ");
        lbTenHH.setFont(font_CT);
        lbTenHH.setOpaque(true);
        lbTenHH.setBounds(40, 130, 170, 30);
        frame_chitiet.add(lbTenHH);
        
        txt_tenhh = new JTextField();
        txt_tenhh.setBounds(230, 130, 280, 25);
        txt_tenhh.setFont(font_CT_txt);
        txt_tenhh.setHorizontalAlignment(JTextField.CENTER);
        txt_tenhh.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorButton));
        txt_tenhh.setEditable(false);
        frame_chitiet.add(txt_tenhh);
        
        lbLoaiHH = new JLabel("Loại hàng hóa ");
        lbLoaiHH.setFont(font_CT);
        lbLoaiHH.setOpaque(true);
        lbLoaiHH.setBounds(40, 180, 170, 30);
        frame_chitiet.add(lbLoaiHH);
        
        txt_loaihh = new JTextField();
        txt_loaihh.setBounds(230, 181, 280, 25);
        txt_loaihh.setFont(font_CT_txt);
        txt_loaihh.setHorizontalAlignment(JTextField.CENTER);
        txt_loaihh.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorButton));
        txt_loaihh.setEditable(false);
        frame_chitiet.add(txt_loaihh);
              
        lbSoluongHH = new JLabel("Số lượng hàng hóa ");
        lbSoluongHH.setFont(font_CT);
        lbSoluongHH.setOpaque(true);
        lbSoluongHH.setBounds(40, 230, 170, 30);
        frame_chitiet.add(lbSoluongHH);
        
        txt_soluonghh = new JTextField();
        txt_soluonghh.setBounds(230, 230, 280, 25);
        txt_soluonghh.setFont(font_CT_txt);
        txt_soluonghh.setHorizontalAlignment(JTextField.CENTER);
        txt_soluonghh.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorButton));
        txt_soluonghh.setEditable(false);
        frame_chitiet.add(txt_soluonghh);
        
        
        lbDonGiaHH = new JLabel("Đơn giá sản phẩm ");
        lbDonGiaHH.setFont(font_CT);
        lbDonGiaHH.setOpaque(true);
        lbDonGiaHH.setBounds(40, 280, 170, 30);
        frame_chitiet.add(lbDonGiaHH);
        
        txt_dongiaHH = new JTextField();
        txt_dongiaHH.setBounds(230, 281, 280, 25);
        txt_dongiaHH.setFont(font_CT_txt);
        txt_dongiaHH.setHorizontalAlignment(JTextField.CENTER);
        txt_dongiaHH.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorButton));
        txt_dongiaHH.setEditable(false);
        frame_chitiet.add(txt_dongiaHH);
        
        btThoat_CT = new JButton("Thoát");
        btThoat_CT.setBounds(250, 350, 120, 35);
        btThoat_CT.setFont(font_Button);
        btThoat_CT.setBackground(colorButton);
        btThoat_CT.setForeground(Color.WHITE);
        btThoat_CT.setBorderPainted(false);
        btThoat_CT.setFocusPainted(false);
        frame_chitiet.add(btThoat_CT);
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
    }

public void TrangThaiTxt_Them(){
    txt_soluonghh.setEditable(true);
    txt_tenhh.setEditable(true);
    txt_loaihh.setEditable(true);
    txt_dongiaHH.setEditable(true);
    txt_soluonghh.setBackground(Color.white);
    txt_tenhh.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colorButton));
    txt_loaihh.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colorButton));
    txt_soluonghh.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colorButton));
    txt_dongiaHH.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colorButton));
    
    btThemCT = new JButton("Thêm");
    btThemCT.setBounds(170, 330, 120, 35);
    btThemCT.setFont(font_Button);
    btThemCT.setBackground(colorButton);
    btThemCT.setForeground(Color.WHITE);
    btThemCT.setBorderPainted(false);
    btThemCT.setFocusPainted(false);
    frame_chitiet.add(btThemCT);
    btThemCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                  HangHoaDTO themHH = new HangHoaDTO();
                  HangHoaBUS themHHBUS = new HangHoaBUS();
                  Xulydulieu xldl = new Xulydulieu();
                  themHH.setMaHang(txt_mahh.getText());
                  themHH.setTenHang(txt_tenhh.getText());
                  themHH.setLoaiHang(txt_loaihh.getText());
                  themHH.setTinhTrang(1);
                  if (themHHBUS.KtraSluongDonGia(txt_soluonghh.getText(), txt_dongiaHH.getText())==1){
                      themHH.setSL(Integer.parseInt(txt_soluonghh.getText()));
                      themHH.setDonGia(Integer.parseInt(txt_dongiaHH.getText()));
                    if(themHHBUS.KtradulieuHangHoa(themHH)==1){
                        if(themHHBUS.themhanghoa(themHH)==1){
                            JOptionPane.showMessageDialog(frame_chitiet, "Thêm thành công");
                            frame_chitiet.setVisible(false);
                        }
                        else
                            JOptionPane.showMessageDialog(frame_chitiet, "Thêm thất bại");
                    }
                  }
            }
             @Override
            public void mouseEntered(MouseEvent arg0) {
                btThemCT.setBackground(Color.DARK_GRAY);
                btThemCT.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
               btThemCT.setBackground(colorButton);
            }
        });
    
    btThoat_CT.setBounds(310, 330, 120, 35);
}
public void TrangThaiTxt_Sua(){
    txt_soluonghh.setEditable(true);
    txt_tenhh.setEditable(true);
    txt_loaihh.setEditable(true);
    txt_dongiaHH.setEditable(true);
    txt_soluonghh.setBackground(Color.white);
    txt_tenhh.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colorButton));
    txt_loaihh.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colorButton));
    txt_soluonghh.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colorButton));
    txt_dongiaHH.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colorButton));

    btSuaCT = new JButton("Lưu");
    btSuaCT.setFont(font_Button);
    btSuaCT.setBackground(colorButton);
    btSuaCT.setForeground(Color.WHITE);
    btSuaCT.setBorderPainted(false);
    btSuaCT.setFocusPainted(false);
    btSuaCT.setBounds(170, 330, 120, 35);
    frame_chitiet.add(btSuaCT);
    btSuaCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                  HangHoaDTO suaHH = new HangHoaDTO();
                  HangHoaBUS suaHHBUS = new HangHoaBUS();
                  Xulydulieu xldl = new Xulydulieu();
                  suaHH.setMaHang(txt_mahh.getText());
                  suaHH.setTenHang(txt_tenhh.getText());
                  suaHH.setLoaiHang(txt_loaihh.getText());
                  suaHH.setTinhTrang(1);
                  if (suaHHBUS.KtraSluongDonGia(txt_soluonghh.getText(), txt_dongiaHH.getText())==1){
                      suaHH.setSL(Integer.parseInt(txt_soluonghh.getText()));
                      suaHH.setDonGia(Integer.parseInt(txt_dongiaHH.getText()));
                    if(suaHHBUS.KtradulieuHangHoa(suaHH)==1){
                        if(suaHHBUS.suaHangHoa(suaHH)==1){
                            JOptionPane.showMessageDialog(frame_chitiet, "Cập nhật thành công");
                            frame_chitiet.setVisible(false);
                        }
                        else
                            JOptionPane.showMessageDialog(frame_chitiet, "Cập nhật thất bại");
                    }
                  }
            }
             @Override
            public void mouseEntered(MouseEvent arg0) {
                btSuaCT.setBackground(Color.DARK_GRAY);
                btSuaCT.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
               btSuaCT.setBackground(colorButton);
            }
                  
            
        });
    
    btThoat_CT.setBounds(310, 330, 120, 35);
}
     public void  Reset(){
        txt_ma.setText(null);
        txt_ten.setText(null);
        txt_nhom.setText(null);
        txt_soluong.setText(null);
        txtTenHangHoa.setText(null);
        txt_dongia.setText(null);
    } 
   
//public static void main(String[] args) {
//        QuanLyGUI p=new QuanLyGUI("NV0001","QL");
//        
//    }
}
