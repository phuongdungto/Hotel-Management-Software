/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.ChiTietHoaDonBUS;
import BUS.ChiTietKMDichVuBUS;
import BUS.DichVuBUS;
import BUS.HoaDonBUS;
import BUS.KhachHangBUS;
import BUS.LoaiPhongBUS;
import BUS.NhanVienBUS;
import BUS.PhieuDatPhongBUS;
import BUS.PhongBUS;
import BUS.Xulydulieu;
import DTO.ChiTietHoaDonDTO;
import DTO.ChiTietKMDichVuDTO;
import DTO.DichVuDTO;
import DTO.HoaDonDTO;
import DTO.KhachHangDTO;
import DTO.LoaiPhongDTO;
import DTO.NhanVienDTO;
import DTO.PhieuDatPhongDTO;
import DTO.PhongDTO;
import DTO.WrapLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;
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
public class PhongGUI {
    protected JFrame jf;
    protected  JPanel pPhong,pTimKiem,pJPanel_phong,timkiem;
    protected JLabel lbTieude,lbNhapTang,lbNhapGia1,lbNhapGia2;
    protected JButton btTimKiem,TrangTha1,TrangTha2,TrangTha3,TrangTha4,btThem,btSua,btXoa,btReset,bt_DanhsachDV,btThem_CT,btXoa_CT;
    protected JScrollPane sc,sc1,sc2,sc3,sc4;
    protected JTextField txt_TimKiemTheoTang,txt_TimKiemTheoGia1,txt_TimKiemTheoGia2, txt_LoaiP, txt_madv,txt_DonGia,txt_DonGia1;
    protected JComboBox cbxTimKiem,cbxTimKiemLP,cbxTinhTrang;
    protected Color colorButton = new Color(36,36,36);
    protected Color PhongCanDonDep = new Color(23, 95, 59);
    protected Color PhongCoNguoi = new Color(183,28,28);
    protected Color PhongDaDat = new Color(85,85,85);
    protected Color PhongTrong = new Color(36,36,36);
    protected JTable tbdsloaiphong,tbdsdichvu,tblCT_HoaDon;
    Font font_lb = new Font("Times New Roman",0,18);
    Font font_CT_txt = new Font("Times New Roman",Font.BOLD,18);
    Font font_tk = new Font("Times New Roman",0,23); 
    Font font_tk1 = new Font("Times New Roman",Font.BOLD,19);
    protected ArrayList<JPanel> pnlPhong = new ArrayList<>();
    Font font_lbPhong = new Font("Times New Roman",Font.BOLD,28);
    Font font_Button = new Font("Times New Roman",0,17);
    DefaultTableModel md ;
    ArrayList<ChiTietHoaDonDTO> DS_TamchuaCTHH;
    protected static int phongtrong=0,phongdadat=0,phongconguoithue=0,phongdangdondep=0;
    public PhongGUI(){
        
    }
    public JPanel KhoiTaoPanel(int width, int height) {
        PhongBUS p_BUS = new PhongBUS();
        p_BUS.docdsp();
        pPhong=new JPanel();
        pPhong.setLayout(null);
        pPhong.setBackground(Color.WHITE);
        pPhong.setBounds(0, 0, width, height);
        pPhong.setPreferredSize(new Dimension(width,height));
        pPhong.setOpaque(true);
        
        lbTieude=new JLabel();
        lbTieude.setOpaque(true);
        lbTieude.setText("Giao diện phòng");
        lbTieude.setFont(font_lbPhong);
        lbTieude.setBounds(0, 0, 1000, 50);
        lbTieude.setForeground(Color.BLACK);
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        pTimKiem = TimKiem(1000, 100);
        pPhong.add(lbTieude);
        pPhong.add(pTimKiem);
        sc = JPanel_Phong(1000, 500,PhongBUS.dsp);
        sc.setBounds(0, 150, 1000, 500);
        sc.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//Cấm scroll ngang
        sc.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);// scroll dọc
        sc.getVerticalScrollBar().setUnitIncrement(10);// tốc độ scroll
        pPhong.add(sc);


        return pPhong;
    }
     //====================================================  Jpanel các loại tìm kiếm ===============================================//
    public JPanel TimKiem(int width, int height){
        phongtrong=0;
        phongconguoithue=0;
        phongdangdondep=0;
        pTimKiem=new JPanel();
        pTimKiem.setLayout(null);
        pTimKiem.setBounds(0, 50, width, height);
        pTimKiem.setPreferredSize(new Dimension(width,height));
        pTimKiem.setOpaque(true);
        PhongBUS p_BUS = new PhongBUS();
        p_BUS.docdsp();
        for(PhongDTO p : PhongBUS.dsp){
            if(p.getTinhTrang()==1){
                phongtrong+=1;
            }
            if(p.getTinhTrang()==2){
                phongconguoithue+=1;
            }
            if(p.getTinhTrang()==3){
                phongdangdondep+=1;
            }
        }
        //===========================  CBX Tìm kiếm theo ============================//
        String DSTimKiem[] = { "Tìm kiếm theo", "Loại phòng", "Tầng", "Đơn giá"};
        cbxTimKiem = new JComboBox(DSTimKiem);
        cbxTimKiem.setBounds(20, 10, 140, 30);
        pTimKiem.add(cbxTimKiem);
        timkiem = new JPanel();
        timkiem.setBounds(170, 0, 600, 45);
        timkiem.setLayout(null);
        pTimKiem.add(timkiem);
        cbxTimKiem.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                
                       if(cbxTimKiem.getSelectedItem().equals(DSTimKiem[0])){
                                       timkiem.removeAll();
                                       timkiem.repaint();
                                       timkiem.revalidate();
                                    }
                       if(cbxTimKiem.getSelectedItem().equals(DSTimKiem[1])){
                                       TimKiemTheoLoaiP();
                                    }
                       if(cbxTimKiem.getSelectedItem().equals(DSTimKiem[2])){
                                       TimKiemTheoTang();
                                    }
                       if(cbxTimKiem.getSelectedItem().equals(DSTimKiem[3])){
                                       TimKiemTheoDonGia();
                                    }           
                                         
                                }});
        String phongtrong1 = String.valueOf(phongtrong);
        TrangTha1 = new JButton(phongtrong1);
        TrangTha1.setForeground(Color.WHITE);
        TrangTha1.setBounds(20, 60, 55, 35);
        TrangTha1.setBackground(PhongTrong);
        TrangTha1.setFont(font_tk1);
        TrangTha1.setForeground(Color.WHITE);
        TrangTha1.setBorderPainted(false);
        TrangTha1.setFocusPainted(false);
        pTimKiem.add(TrangTha1);
        TrangTha1.addMouseListener(new java.awt.event.MouseAdapter() {
            PhongBUS p_BUS = new PhongBUS();
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                  ArrayList<PhongDTO> dsptkp_theophong= new ArrayList<>(); 
                  dsptkp_theophong = p_BUS.timkiemtheophong(1);
                  
                     sc.removeAll();
                        sc1 = JPanel_Phong(1000, 500, dsptkp_theophong);
                        sc1.setBounds(0, 0, 1000, 500);
                        sc1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//Cấm scroll ngang
                        sc1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);// scroll dọc
                        sc1.getVerticalScrollBar().setUnitIncrement(10);// tốc độ scroll
                        sc.add(sc1);
                        sc.repaint();
                        sc.revalidate();
                        pPhong.add(sc);
            }
             @Override
            public void mouseEntered(MouseEvent arg0) {
                TrangTha1.setBackground(Color.DARK_GRAY);
                TrangTha1.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
               TrangTha1.setBackground(colorButton);
            }
        });
        
        JLabel lb_phongTrong = new JLabel("Phòng trống");
        lb_phongTrong.setFont(font_tk1);
        lb_phongTrong.setOpaque(true);
        lb_phongTrong.setBounds(80,62,110,30);
        lb_phongTrong.setOpaque(true);
        pTimKiem.add(lb_phongTrong);
        
//        String phongdadat1 = String.valueOf(phongdadat);
//        TrangTha2 = new JButton(phongdadat1);
//        TrangTha2.setForeground(Color.WHITE);
//        TrangTha2.setBounds(210, 60, 55, 35);
//        TrangTha2.setBackground(PhongDaDat);
//        TrangTha2.setFont(font_tk1);
//        TrangTha2.setForeground(Color.WHITE);
//        TrangTha2.setBorderPainted(false);
//        TrangTha2.setFocusPainted(false);
//        pTimKiem.add(TrangTha2);
//        
//        JLabel lb_phongdadat = new JLabel("Phòng đã đặt");
//        lb_phongdadat.setFont(font_tk1);
//        lb_phongdadat.setOpaque(true);
//        lb_phongdadat.setBounds(270,62,130,30);
//        lb_phongdadat.setOpaque(true);
//        pTimKiem.add(lb_phongdadat);
        
        String phongdangconguoi1 = String.valueOf(phongconguoithue);
        TrangTha3 = new JButton(phongdangconguoi1);
        TrangTha3.setForeground(Color.WHITE);
        TrangTha3.setBounds(220, 60, 55, 35);
        TrangTha3.setBackground(PhongCoNguoi);
        TrangTha3.setFont(font_tk1);
        TrangTha3.setForeground(Color.WHITE);
        TrangTha3.setBorderPainted(false);
        TrangTha3.setFocusPainted(false);
        pTimKiem.add(TrangTha3);
        TrangTha3.addMouseListener(new java.awt.event.MouseAdapter() {
            PhongBUS p_BUS = new PhongBUS();
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                  ArrayList<PhongDTO> dsptkp_theophong= new ArrayList<>(); 
                  dsptkp_theophong = p_BUS.timkiemtheophong(2);
                  
                     sc.removeAll();
                        sc1 = JPanel_Phong(1000, 500, dsptkp_theophong);
                        sc1.setBounds(0, 0, 1000, 500);
                        sc1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//Cấm scroll ngang
                        sc1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);// scroll dọc
                        sc1.getVerticalScrollBar().setUnitIncrement(10);// tốc độ scroll
                        sc.add(sc1);
                        sc.repaint();
                        sc.revalidate();
                        pPhong.add(sc);
            }
             @Override
            public void mouseEntered(MouseEvent arg0) {
                TrangTha3.setBackground(Color.DARK_GRAY);
                TrangTha3.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
               TrangTha3.setBackground(PhongCoNguoi);
            }
        });
        
        
        JLabel lb_phongdangconguoithue = new JLabel("Đang thuê");
        lb_phongdangconguoithue.setFont(font_tk1);
        lb_phongdangconguoithue.setOpaque(true);
        lb_phongdangconguoithue.setBounds(280,62,110,30);
        lb_phongdangconguoithue.setOpaque(true);
        pTimKiem.add(lb_phongdangconguoithue);
        
        String phongcandondep1 = String.valueOf(phongdangdondep);
        TrangTha4 = new JButton(phongcandondep1);
        TrangTha4.setForeground(Color.WHITE);
        TrangTha4.setBounds(405, 60, 55, 35);
        TrangTha4.setBackground(PhongCanDonDep);
        TrangTha4.setFont(font_tk1);
        TrangTha4.setForeground(Color.WHITE);
        TrangTha4.setBorderPainted(false);
        TrangTha4.setFocusPainted(false);
        pTimKiem.add(TrangTha4);
        TrangTha4.addMouseListener(new java.awt.event.MouseAdapter() {
            PhongBUS p_BUS = new PhongBUS();
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                  ArrayList<PhongDTO> dsptkp_theophong= new ArrayList<>(); 
                  dsptkp_theophong = p_BUS.timkiemtheophong(3);
                  
                     sc.removeAll();
                        sc1 = JPanel_Phong(1000, 500, dsptkp_theophong);
                        sc1.setBounds(0, 0, 1000, 500);
                        sc1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//Cấm scroll ngang
                        sc1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);// scroll dọc
                        sc1.getVerticalScrollBar().setUnitIncrement(10);// tốc độ scroll
                        sc.add(sc1);
                        sc.repaint();
                        sc.revalidate();
                        pPhong.add(sc);
            }
             @Override
            public void mouseEntered(MouseEvent arg0) {
                TrangTha4.setBackground(Color.DARK_GRAY);
                TrangTha4.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
               TrangTha4.setBackground(PhongCanDonDep);
            }
        });
        
        
        JLabel lb_phongdangcandondep = new JLabel("Đang dọn dẹp");
        lb_phongdangcandondep.setFont(font_tk1);
        lb_phongdangcandondep.setOpaque(true);
        lb_phongdangcandondep.setBounds(465,62,125,30);
        lb_phongdangcandondep.setOpaque(true);
        pTimKiem.add(lb_phongdangcandondep);
        
          //===========================  BT Thêm ============================//
        ImageIcon iconthem = new ImageIcon("src/img/add.png");
        btThem = new JButton("Thêm",iconthem);
        btThem.setBounds(780, 60, 140, 35);
        btThem.setBackground(colorButton);
        btThem.setFont(font_Button);
        btThem.setForeground(Color.WHITE);
        btThem.setBorderPainted(false);
        btThem.setFocusPainted(false);
        pTimKiem.add(btThem);
        btThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                  Frame_ThemP();
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
          //===========================  BT Reset ============================//
        ImageIcon iconreset = new ImageIcon("src/img/update.png");
        btReset = new JButton("Cập nhật",iconreset);
        btReset.setBounds(780, 7, 140, 35);
        btReset.setBackground(colorButton);
        btReset.setFont(font_Button);
        btReset.setForeground(Color.WHITE);
        btReset.setBorderPainted(false);
        btReset.setFocusPainted(false);
        pTimKiem.add(btReset);
        btReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                  timkiem.removeAll();
                  timkiem.repaint();
                  timkiem.revalidate();
                  cbxTimKiem.setSelectedIndex(0);
                  sc.removeAll();
                        sc1 = JPanel_Phong(1000, 500, PhongBUS.dsp);
                        sc1.setBounds(0, 0, 1000, 500);
                        sc1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//Cấm scroll ngang
                        sc1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);// scroll dọc
                        sc1.getVerticalScrollBar().setUnitIncrement(10);// tốc độ scroll
                        sc.add(sc1);
                        sc.repaint();
                        sc.revalidate();
                        
                        pPhong.add(sc);
            }
             @Override
            public void mouseEntered(MouseEvent arg0) {
                btReset.setBackground(Color.DARK_GRAY);
                btReset.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
               btReset.setBackground(colorButton);
            }
        });
         //===========================  BT Sửa ============================//
//        ImageIcon iconsua = new ImageIcon("src/img/sua.png");
//        btSua = new JButton("Sửa",iconsua);
//        btSua.setBounds(790, 60, 90, 35);
//        btSua.setFont(font_Button);
//        btSua.setBackground(colorButton);
//        btSua.setForeground(Color.WHITE);
//        btSua.setBorderPainted(false);
//        btSua.setFocusPainted(false);
//        pTimKiem.add(btSua);
        

        
        return pTimKiem;
    }
    
    public void TimKiemTheoLoaiP(){
        timkiem.removeAll();
//===========================  CBX Tìm kiếm theo loại phòng ============================//
        LoaiPhongBUS lpBus = new LoaiPhongBUS();
        lpBus.docdslp();
        String[] LoaiPhong= new String[LoaiPhongBUS.dslp.size()+1];
        LoaiPhong[0]="Chọn loại phòng";
        int i=1;
        for(LoaiPhongDTO lp : LoaiPhongBUS.dslp){
            LoaiPhong[i]=lp.getTenLoai();
            i++;
        }
        cbxTimKiemLP = new JComboBox(LoaiPhong);
        cbxTimKiemLP.setBounds(10, 10, 140, 30);
        timkiem.add(cbxTimKiemLP);
        timkiem.repaint();
        timkiem.revalidate();
        
//===========================  BT Tìm kiếm ============================//
        ImageIcon iconsearch = new ImageIcon("src/img/search.png");
        btTimKiem = new JButton("Tìm kiếm",iconsearch);
        btTimKiem.setBounds(170, 7, 140, 35);
        btTimKiem.setBackground(colorButton);
        btTimKiem.setFont(font_Button);
        btTimKiem.setForeground(Color.WHITE);
        btTimKiem.setBorderPainted(false);
        btTimKiem.setFocusPainted(false);
        timkiem.add(btTimKiem);
        btTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PhongBUS p_BUS = new PhongBUS();
                LoaiPhongBUS lp_BUS = new LoaiPhongBUS();
                lp_BUS.docdslp();
                 ArrayList<PhongDTO> dsptkp_theoLoai= new ArrayList<>();
                 if(cbxTimKiemLP.getSelectedIndex()==0){
                     JOptionPane.showMessageDialog(null, "Vui lòng chọn loại phòng");
                 }
                 else{
                 String loaiPhong = String.valueOf(cbxTimKiemLP.getSelectedItem());
                 String maloaiphong=null;
                        for(LoaiPhongDTO lpDTO : LoaiPhongBUS.dslp){
                            if(lpDTO.getTenLoai().equals(loaiPhong))
                                maloaiphong = lpDTO.getMaLoai();
                        }
                    dsptkp_theoLoai =  p_BUS.timkiemtheoloai(maloaiphong);
                    if(dsptkp_theoLoai==null){
                        JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu");
                         }
                     sc.removeAll();
                     
                        sc1 = JPanel_Phong(1000, 500, dsptkp_theoLoai);
                        sc1.setBounds(0, 0, 1000, 500);
                        sc.add(sc1);
                        sc.repaint();
                        sc.revalidate();
                        pPhong.add(sc);
            }}
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
    }
   public void TimKiemTheoTang(){
       timkiem.removeAll();
        lbNhapTang = new JLabel("Nhập tầng: ");
        lbNhapTang.setFont(font_tk);
        lbNhapTang.setOpaque(true);
        lbNhapTang.setBounds(10,10,105,30);
        lbNhapTang.setOpaque(true);
        timkiem.add(lbNhapTang);
        
        txt_TimKiemTheoTang = new JTextField();
        txt_TimKiemTheoTang.setBounds(117,10,70,30);
        txt_TimKiemTheoTang.setFont(font_tk);
        txt_TimKiemTheoTang.setOpaque(true);
        txt_TimKiemTheoTang.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, colorButton));
        timkiem.add(txt_TimKiemTheoTang);
        
 //===========================  BT Tìm kiếm ============================//
        ImageIcon iconsearch = new ImageIcon("src/img/search.png");
        btTimKiem = new JButton("Tìm kiếm",iconsearch);
        btTimKiem.setBounds(200, 7, 140, 35);
        btTimKiem.setBackground(colorButton);
        btTimKiem.setFont(font_Button);
        btTimKiem.setForeground(Color.WHITE);
        btTimKiem.setBorderPainted(false);
        btTimKiem.setFocusPainted(false);
        timkiem.add(btTimKiem);
        timkiem.repaint();
        timkiem.revalidate();
        
        btTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Xulydulieu xldl = new Xulydulieu();
                PhongBUS p_BUS = new PhongBUS();
                p_BUS.docdsp();
                 if(txt_TimKiemTheoTang.getText().equals("")){
                     JOptionPane.showMessageDialog(null, "Vui lòng nhập tầng để tìm kiếm");
                 }
                 else if(xldl.KtraSo(txt_TimKiemTheoTang.getText())==false){
                 }
                 else{
                  ArrayList<PhongDTO> dsptkp_theoTang= new ArrayList<>();
                 int tang = Integer.parseInt(txt_TimKiemTheoTang.getText());
                        dsptkp_theoTang = p_BUS.timkiemtheotang(tang);
                    if(dsptkp_theoTang==null){
                        JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu");
                         }
                     sc.removeAll();
                     
                        sc1 = JPanel_Phong(1000, 500, dsptkp_theoTang);
                        sc1.setBounds(0, 0, 1000, 500);
                        sc.add(sc1);
                        sc.repaint();
                        sc.revalidate();
                        pPhong.add(sc);
            }}
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
        
   }
   public void TimKiemTheoDonGia(){
       timkiem.removeAll();
       lbNhapGia1 = new JLabel("Giá từ: ");
        lbNhapGia1.setFont(font_tk);
        lbNhapGia1.setOpaque(true);
        lbNhapGia1.setBounds(10,10,70,30);
        lbNhapGia1.setOpaque(true);
        timkiem.add(lbNhapGia1);
        
        txt_TimKiemTheoGia1 = new JTextField();
        txt_TimKiemTheoGia1.setBounds(83,10,150,30);
        txt_TimKiemTheoGia1.setFont(font_tk);
        txt_TimKiemTheoGia1.setOpaque(true);
        txt_TimKiemTheoGia1.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, colorButton));
        timkiem.add(txt_TimKiemTheoGia1);
        
        lbNhapGia2 = new JLabel("đến ");
        lbNhapGia2.setFont(font_tk);
        lbNhapGia2.setOpaque(true);
        lbNhapGia2.setBounds(240,10,45,30);
        lbNhapGia2.setOpaque(true);
        timkiem.add(lbNhapGia2);
        
        txt_TimKiemTheoGia2 = new JTextField();
        txt_TimKiemTheoGia2.setBounds(290,10,150,30);
        txt_TimKiemTheoGia2.setFont(font_tk);
        txt_TimKiemTheoGia2.setOpaque(true);
        txt_TimKiemTheoGia2.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, colorButton));
        timkiem.add(txt_TimKiemTheoGia2);
        
         //===========================  BT Tìm kiếm ============================//
        ImageIcon iconsearch = new ImageIcon("src/img/search.png");
        btTimKiem = new JButton("Tìm kiếm",iconsearch);
        btTimKiem.setBounds(450, 7, 140, 35);
        btTimKiem.setBackground(colorButton);
        btTimKiem.setFont(font_Button);
        btTimKiem.setForeground(Color.WHITE);
        btTimKiem.setBorderPainted(false);
        btTimKiem.setFocusPainted(false);
        timkiem.add(btTimKiem);
        timkiem.repaint();
        timkiem.revalidate();
        btTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            Xulydulieu xldl = new Xulydulieu();
            PhongBUS p_BUS = new PhongBUS();
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(p_BUS.kiemtradulieu(txt_TimKiemTheoGia1.getText(), txt_TimKiemTheoGia2.getText())==1){
                  ArrayList<PhongDTO> dsptkp_theoDonGia= new ArrayList<>(); 
                  int s = Integer.parseInt(txt_TimKiemTheoGia1.getText());
                  int s1 = Integer.parseInt(txt_TimKiemTheoGia2.getText());
                  dsptkp_theoDonGia = p_BUS.timkiemtheodongia(s, s1);
                  
                     sc.removeAll();
                        sc1 = JPanel_Phong(1000, 500, dsptkp_theoDonGia);
                        sc1.setBounds(0, 0, 1000, 500);
                        sc1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//Cấm scroll ngang
                        sc1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);// scroll dọc
                        sc1.getVerticalScrollBar().setUnitIncrement(10);// tốc độ scroll
                        sc.add(sc1);
                        sc.repaint();
                        sc.revalidate();
                        pPhong.add(sc);
               }
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
        
   }
     //====================================================  Jpanel giao diện phòng ===============================================//
    public JScrollPane JPanel_Phong(int width, int height,ArrayList<PhongDTO> dsp){
        PhieuDatPhongBUS pdp_BUS = new PhieuDatPhongBUS();
        pdp_BUS.docdspdp();
        HoaDonBUS hdBus = new HoaDonBUS();
        hdBus.docdshd();
        ChiTietHoaDonBUS cthdbus = new ChiTietHoaDonBUS();
        cthdbus.docdscthd();
        PhongBUS p_BUS = new PhongBUS();
        pnlPhong.clear();
        pJPanel_phong = new JPanel();
        pJPanel_phong.removeAll();
        pJPanel_phong.setVisible(false);
        pJPanel_phong.setLayout(new WrapLayout(0,20,20));
        pJPanel_phong.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        for(PhongDTO p : dsp){
            if(p.getTinhTrang()!=0){
            JPanel_PhongGUI phong12 = new JPanel_PhongGUI();
            JPanel pn = phong12.CreaJPanel_Phong(p);
            pnlPhong.add(pn);
            pJPanel_phong.add(pn);
            pJPanel_phong.setVisible(true);
            
            pn.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent arg0) {
                if(p.getTinhTrang()==1){
                     Frame_ChiTietPhongTrong(p);
                }
                if(p.getTinhTrang()==2){
                    PhieuDatPhongDTO pdp_DTO = new PhieuDatPhongDTO();
                    String mapdp = pdp_BUS.Ma1PDP(p.getMaPhong());
                    pdp_DTO = pdp_BUS.lay1pdp(mapdp);
                    HoaDonDTO hd_DTO = new HoaDonDTO();
                    HoaDonBUS hd_BUS = new HoaDonBUS();
                    hd_DTO = hd_BUS.Lay1HoaDonTheoMaPDP(pdp_DTO.getMaPDP());
                    Frame_ChiTietPhongDangThue(p,pdp_DTO,hd_DTO);
                }
                if(p.getTinhTrang()==3){
                     Frame_ChiTietPhongTrong_TrangThaiDangDon(p);
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
                pn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
            }
        
        });
            
        }}
        JScrollPane srp = new JScrollPane();
        srp.setViewportView(pJPanel_phong);
       

        return srp;
    }
    public void Frame_ChiTietPhongTrong_TrangThaiDangDon(PhongDTO p){
        JFrame jf = new JFrame();
        jf.setSize(650, 380);
        jf.setLayout(null);
        jf.setLocation(500, 200);
        
        JLabel lb_TieuDeChiTietPhong = new JLabel("Chi tiết phòng");
        lb_TieuDeChiTietPhong.setFont(font_lbPhong);
        lb_TieuDeChiTietPhong.setOpaque(true);
        lb_TieuDeChiTietPhong.setBackground(PhongTrong);
        lb_TieuDeChiTietPhong.setBounds(0,0,650,50);
        lb_TieuDeChiTietPhong.setForeground(Color.WHITE);
        lb_TieuDeChiTietPhong.setHorizontalAlignment(JLabel.CENTER);
        jf.add(lb_TieuDeChiTietPhong);
        
        JLabel MaPhong = new JLabel("Mã phòng ");
        MaPhong.setFont(font_tk);
        MaPhong.setOpaque(true);
        MaPhong.setBounds(20,60,120,35);
        MaPhong.setHorizontalAlignment(JLabel.CENTER);
        jf.add(MaPhong);
        
        JTextField txt_CTma = new JTextField();
        txt_CTma.setText(p.getMaPhong());
        txt_CTma.setBounds(145, 65,140, 25);
        txt_CTma.setFont(font_CT_txt);
        txt_CTma.setForeground(Color.RED);
        txt_CTma.setHorizontalAlignment(JTextField.CENTER);
        txt_CTma.setBorder(BorderFactory.createLoweredBevelBorder());
        txt_CTma.setEditable(false);
        jf.add(txt_CTma);
        
        JLabel LoaiPhong = new JLabel("Loại phòng ");
        LoaiPhong.setFont(font_tk);
        LoaiPhong.setOpaque(true);
        LoaiPhong.setBounds(20,110,120,35);
        LoaiPhong.setHorizontalAlignment(JLabel.CENTER);
        jf.add(LoaiPhong);
        
        txt_LoaiP = new JTextField();
        LoaiPhongBUS lp_BUS = new LoaiPhongBUS();
        lp_BUS.docdslp();
        for(LoaiPhongDTO lp: LoaiPhongBUS.dslp){
            if(lp.getMaLoai().equals(p.getMaLoai()))
                txt_LoaiP.setText(lp.getTenLoai());
        }
        txt_LoaiP.setBounds(145, 114, 160, 25);
        txt_LoaiP.setFont(font_CT_txt);
        txt_LoaiP.setHorizontalAlignment(JTextField.CENTER);
        txt_LoaiP.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorButton));
        txt_LoaiP.setEditable(false);
        jf.add(txt_LoaiP);
        

        JLabel Tang= new JLabel("Tầng ");
        Tang.setFont(font_tk);
        Tang.setOpaque(true);
        Tang.setBounds(420,110,100,35);
        Tang.setHorizontalAlignment(JLabel.CENTER);
        jf.add(Tang);
        
        JTextField txt_Tang = new JTextField();
        txt_Tang.setText(String.valueOf(p.getTang()));
        txt_Tang.setBounds(530, 114, 70, 25);
        txt_Tang.setFont(font_CT_txt);
        txt_Tang.setHorizontalAlignment(JTextField.CENTER);
        txt_Tang.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorButton));
        txt_Tang.setEditable(false);
        jf.add(txt_Tang);
        
        JLabel SoNguoi = new JLabel("Số người ");
        SoNguoi.setFont(font_tk);
        SoNguoi.setOpaque(true);
        SoNguoi.setBounds(20,160,120,35);
        SoNguoi.setHorizontalAlignment(JLabel.CENTER);
        jf.add(SoNguoi);
        
        JTextField txt_SoNguoi = new JTextField();
        txt_SoNguoi.setText(String.valueOf(p.getSLNguoi()));
        txt_SoNguoi.setBounds(145, 165, 160, 25);
        txt_SoNguoi.setFont(font_CT_txt);
        txt_SoNguoi.setHorizontalAlignment(JTextField.CENTER);
        txt_SoNguoi.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorButton));
        txt_SoNguoi.setEditable(false);
        jf.add(txt_SoNguoi);
        
        JLabel Tien= new JLabel("Giá phòng ");
        Tien.setFont(font_tk);
        Tien.setOpaque(true);
        Tien.setBounds(340,160,120,35);
        Tien.setHorizontalAlignment(JLabel.CENTER);
        jf.add(Tien);
        
        Xulydulieu xldl = new Xulydulieu();
        txt_DonGia = new JTextField();
        txt_DonGia.setText(xldl.ChuyenKieuTien(String.valueOf(p.getDonGia())));
        txt_DonGia.setBounds(465, 165, 160, 25);
        txt_DonGia.setFont(font_CT_txt);
        txt_DonGia.setHorizontalAlignment(JTextField.CENTER);
        txt_DonGia.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorButton));
        txt_DonGia.setEditable(false);
        jf.add(txt_DonGia);
        
        JLabel TinhTrang= new JLabel("Tình trạng ");
        TinhTrang.setFont(font_tk);
        TinhTrang.setOpaque(true);
        TinhTrang.setBounds(20,210,120,35);
        TinhTrang.setHorizontalAlignment(JLabel.CENTER);
        jf.add(TinhTrang);
        
        String DSTinhTrang[] = { "Đang trống", "Đang dọn dẹp"};
                cbxTinhTrang = new JComboBox(DSTinhTrang);
                cbxTinhTrang.setBounds(145, 215, 160, 30);
                cbxTinhTrang.setEnabled(false);
                jf.add(cbxTinhTrang);
        if(p.getTinhTrang()==1){
            cbxTinhTrang.setSelectedIndex(0);
        }
        if(p.getTinhTrang()==3){
            cbxTinhTrang.setSelectedIndex(1);
        }
        

        JButton btThoat_CT = new JButton("Thoát");
        btThoat_CT.setBounds(460, 280, 120, 35);
        btThoat_CT.setFont(font_Button);
        btThoat_CT.setBackground(colorButton);
        btThoat_CT.setForeground(Color.WHITE);
        btThoat_CT.setBorderPainted(false);
        btThoat_CT.setFocusPainted(false);
        jf.add(btThoat_CT);
         btThoat_CT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                  jf.setVisible(false);
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
        
    //===========================  BT Xóa ============================//
        btXoa = new JButton("Xóa");
        btXoa.setBounds(150, 280, 120, 35);
        btXoa.setBackground(colorButton);
        btXoa.setFont(font_Button);
        btXoa.setForeground(Color.WHITE);
        btXoa.setBorderPainted(false);
        btXoa.setFocusPainted(false);
        jf.add(btXoa);
         btXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PhongBUS pbus = new PhongBUS();
                    int input=JOptionPane.showConfirmDialog(jf, "Bạn có chắc muốn xóa", "Xóa phòng", JOptionPane.YES_NO_OPTION);   
                       if(input==0){
                       if (pbus.xoaPhong(p)==1){
                           JOptionPane.showMessageDialog(jf, "Xóa thành công !!!");
                       }
                       else
                       JOptionPane.showMessageDialog(jf, "Xóa thất bại !!!");
            }}
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
        
        JButton btSua_CT = new JButton("Sửa");
        btSua_CT.setBounds(20, 280, 120, 35);
        btSua_CT.setFont(font_Button);
        btSua_CT.setBackground(colorButton);
        btSua_CT.setForeground(Color.WHITE);
        btSua_CT.setBorderPainted(false);
        btSua_CT.setFocusPainted(false);
        jf.add(btSua_CT);
        JButton btHoanTat_CT = new JButton("Hoàn tất");
         btSua_CT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btThoat_CT.setBounds(0, 0, 0, 0);
                 txt_DonGia.setEditable(true);
                 txt_LoaiP.setEditable(true);
                 txt_SoNguoi.setEditable(true);
                 txt_Tang.setEditable(true);
                 txt_DonGia.setText(String.valueOf(p.getDonGia()));
                 cbxTinhTrang.setEnabled(true);
                ImageIcon icon = new ImageIcon("src/img/select.png");
                JButton bt_DanhsachLP = new JButton(icon);
                bt_DanhsachLP.setBounds(310,110,80,30);
                bt_DanhsachLP.setBackground(colorButton);
                bt_DanhsachLP.setBorderPainted(false);
                bt_DanhsachLP.setFocusPainted(false);
                jf.add(bt_DanhsachLP);
                bt_DanhsachLP.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                         BangChonDSLP();
                    }
                     @Override
                    public void mouseEntered(MouseEvent arg0) {
                        bt_DanhsachLP.setBackground(Color.DARK_GRAY);
                        bt_DanhsachLP.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    }
                    @Override
                    public void mouseExited(MouseEvent arg0) {
                       bt_DanhsachLP.setBackground(colorButton);
                    }
                });
                 
                btHoanTat_CT.setBounds(460, 280, 120, 35);
                btHoanTat_CT.setFont(font_Button);
                btHoanTat_CT.setBackground(colorButton);
                btHoanTat_CT.setForeground(Color.WHITE);
                btHoanTat_CT.setBorderPainted(false);
                btHoanTat_CT.setFocusPainted(false);
                jf.add(btHoanTat_CT);
                btHoanTat_CT.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                Xulydulieu xldl = new Xulydulieu();
                PhongBUS pbus = new PhongBUS();
                PhongDTO pDTO = new PhongDTO();
                    LoaiPhongBUS loaiPhongBUS = new LoaiPhongBUS();
                    loaiPhongBUS.docdslp();
                    if(pbus.kiemtradulieuSua(txt_Tang.getText(), txt_SoNguoi.getText(), txt_DonGia.getText())==1){
                            for(LoaiPhongDTO lp : LoaiPhongBUS.dslp){
                             if(lp.getTenLoai().equals(txt_LoaiP.getText()));
                             pDTO.setMaLoai(lp.getMaLoai());
                             }
                             pDTO.setMaPhong(p.getMaPhong());
                             pDTO.setTang(Integer.parseInt(txt_Tang.getText()));
                             pDTO.setSLNguoi(Integer.parseInt(txt_SoNguoi.getText()));
                             pDTO.setDonGia(Integer.parseInt(txt_DonGia.getText()));
                             if(cbxTinhTrang.getSelectedIndex()==0){
                                 pDTO.setTinhTrang(1);
                             }
                             if(cbxTinhTrang.getSelectedIndex()==1){
                                 pDTO.setTinhTrang(3);
                             }
                       if(pbus.suaPhong(pDTO)==1){
                       JOptionPane.showMessageDialog(jf, "Cập nhật thành công");
                       jf.setVisible(false);
                       }
                       else JOptionPane.showMessageDialog(jf, "Cập nhật thất bại");
                    }
                }
            
                     @Override
                    public void mouseEntered(MouseEvent arg0) {
                        btHoanTat_CT.setBackground(Color.DARK_GRAY);
                        btHoanTat_CT.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    }
                    @Override
                    public void mouseExited(MouseEvent arg0) {
                       btHoanTat_CT.setBackground(colorButton);
                    }
                });
            }
             @Override
            public void mouseEntered(MouseEvent arg0) {
                btSua_CT.setBackground(Color.DARK_GRAY);
                btSua_CT.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
               btSua_CT.setBackground(colorButton);
            }
        });

        jf.setVisible(true);
    }
    
        public void Frame_ChiTietPhongTrong(PhongDTO p){
        JFrame jf = new JFrame();
        jf.setSize(650, 380);
        jf.setLayout(null);
        jf.setLocation(500, 200);
        
        JLabel lb_TieuDeChiTietPhong = new JLabel("Chi tiết phòng");
        lb_TieuDeChiTietPhong.setFont(font_lbPhong);
        lb_TieuDeChiTietPhong.setOpaque(true);
        lb_TieuDeChiTietPhong.setBackground(PhongTrong);
        lb_TieuDeChiTietPhong.setBounds(0,0,650,50);
        lb_TieuDeChiTietPhong.setForeground(Color.WHITE);
        lb_TieuDeChiTietPhong.setHorizontalAlignment(JLabel.CENTER);
        jf.add(lb_TieuDeChiTietPhong);
        
        JLabel MaPhong = new JLabel("Mã phòng ");
        MaPhong.setFont(font_tk);
        MaPhong.setOpaque(true);
        MaPhong.setBounds(20,60,120,35);
        MaPhong.setHorizontalAlignment(JLabel.CENTER);
        jf.add(MaPhong);
        
        JTextField txt_CTma = new JTextField();
        txt_CTma.setText(p.getMaPhong());
        txt_CTma.setBounds(145, 65,140, 25);
        txt_CTma.setFont(font_CT_txt);
        txt_CTma.setForeground(Color.RED);
        txt_CTma.setHorizontalAlignment(JTextField.CENTER);
        txt_CTma.setBorder(BorderFactory.createLoweredBevelBorder());
        txt_CTma.setEditable(false);
        jf.add(txt_CTma);
        
        JLabel LoaiPhong = new JLabel("Loại phòng ");
        LoaiPhong.setFont(font_tk);
        LoaiPhong.setOpaque(true);
        LoaiPhong.setBounds(20,110,120,35);
        LoaiPhong.setHorizontalAlignment(JLabel.CENTER);
        jf.add(LoaiPhong);
        
        txt_LoaiP = new JTextField();
        LoaiPhongBUS lp_BUS = new LoaiPhongBUS();
        lp_BUS.docdslp();
        for(LoaiPhongDTO lp: LoaiPhongBUS.dslp){
            if(lp.getMaLoai().equals(p.getMaLoai()))
                txt_LoaiP.setText(lp.getTenLoai());
        }
        txt_LoaiP.setBounds(145, 114, 160, 25);
        txt_LoaiP.setFont(font_CT_txt);
        txt_LoaiP.setHorizontalAlignment(JTextField.CENTER);
        txt_LoaiP.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorButton));
        txt_LoaiP.setEditable(false);
        jf.add(txt_LoaiP);
        

        JLabel Tang= new JLabel("Tầng ");
        Tang.setFont(font_tk);
        Tang.setOpaque(true);
        Tang.setBounds(420,110,120,35);
        Tang.setHorizontalAlignment(JLabel.CENTER);
        jf.add(Tang);
        
        JTextField txt_Tang = new JTextField();
        txt_Tang.setText(String.valueOf(p.getTang()));
        txt_Tang.setBounds(545, 114, 70, 25);
        txt_Tang.setFont(font_CT_txt);
        txt_Tang.setHorizontalAlignment(JTextField.CENTER);
        txt_Tang.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorButton));
        txt_Tang.setEditable(false);
        jf.add(txt_Tang);
        
        JLabel SoNguoi = new JLabel("Số người ");
        SoNguoi.setFont(font_tk);
        SoNguoi.setOpaque(true);
        SoNguoi.setBounds(20,160,120,35);
        SoNguoi.setHorizontalAlignment(JLabel.CENTER);
        jf.add(SoNguoi);
        
        JTextField txt_SoNguoi = new JTextField();
        txt_SoNguoi.setText(String.valueOf(p.getSLNguoi()));
        txt_SoNguoi.setBounds(145, 165, 160, 25);
        txt_SoNguoi.setFont(font_CT_txt);
        txt_SoNguoi.setHorizontalAlignment(JTextField.CENTER);
        txt_SoNguoi.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorButton));
        txt_SoNguoi.setEditable(false);
        jf.add(txt_SoNguoi);
        
        JLabel Tien= new JLabel("Giá phòng ");
        Tien.setFont(font_tk);
        Tien.setOpaque(true);
        Tien.setBounds(340,160,120,35);
        Tien.setHorizontalAlignment(JLabel.CENTER);
        jf.add(Tien);
        
        Xulydulieu xldl = new Xulydulieu();
        txt_DonGia1 = new JTextField();
        txt_DonGia1.setText(xldl.ChuyenKieuTien(String.valueOf(p.getDonGia())));
        txt_DonGia1.setBounds(465, 165, 160, 25);
        txt_DonGia1.setFont(font_CT_txt);
        txt_DonGia1.setHorizontalAlignment(JTextField.CENTER);
        txt_DonGia1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorButton));
        txt_DonGia1.setEditable(false);
        jf.add(txt_DonGia1);
        
        JLabel TinhTrang= new JLabel("Tình trạng ");
        TinhTrang.setFont(font_tk);
        TinhTrang.setOpaque(true);
        TinhTrang.setBounds(20,210,120,35);
        TinhTrang.setHorizontalAlignment(JLabel.CENTER);
        jf.add(TinhTrang);
        
        String DSTinhTrang[] = { "Đang trống", "Đang dọn dẹp"};
                cbxTinhTrang = new JComboBox(DSTinhTrang);
                cbxTinhTrang.setBounds(145, 215, 160, 30);
                cbxTinhTrang.setEnabled(false);
                jf.add(cbxTinhTrang);
        if(p.getTinhTrang()==1){
            cbxTinhTrang.setSelectedIndex(0);
        }
        if(p.getTinhTrang()==3){
            cbxTinhTrang.setSelectedIndex(1);
        }
        

        JButton btThoat_CT = new JButton("Thoát");
        btThoat_CT.setBounds(460, 280, 120, 35);
        btThoat_CT.setFont(font_Button);
        btThoat_CT.setBackground(colorButton);
        btThoat_CT.setForeground(Color.WHITE);
        btThoat_CT.setBorderPainted(false);
        btThoat_CT.setFocusPainted(false);
        jf.add(btThoat_CT);
         btThoat_CT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                  jf.setVisible(false);
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
        
    //===========================  BT Xóa ============================//
        btXoa = new JButton("Xóa");
        btXoa.setBounds(150, 280, 120, 35);
        btXoa.setBackground(colorButton);
        btXoa.setFont(font_Button);
        btXoa.setForeground(Color.WHITE);
        btXoa.setBorderPainted(false);
        btXoa.setFocusPainted(false);
        jf.add(btXoa);
        btXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PhongBUS pbus = new PhongBUS();
                    int input=JOptionPane.showConfirmDialog(jf, "Bạn có chắc muốn xóa", "Xóa phòng", JOptionPane.YES_NO_OPTION);   
                       if(input==0){
                       if (pbus.xoaPhong(p)==1){
                           JOptionPane.showMessageDialog(jf, "Xóa thành công !!!");
                       }
                       else
                       JOptionPane.showMessageDialog(jf, "Xóa thất bại !!!");
            }}
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
        JButton btSua_CT = new JButton("Sửa");
        btSua_CT.setBounds(20, 280, 120, 35);
        btSua_CT.setFont(font_Button);
        btSua_CT.setBackground(colorButton);
        btSua_CT.setForeground(Color.WHITE);
        btSua_CT.setBorderPainted(false);
        btSua_CT.setFocusPainted(false);
        jf.add(btSua_CT);
        
         btSua_CT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btThoat_CT.setBounds(0, 0, 0, 0);
                 txt_DonGia1.setEditable(true);
                 txt_LoaiP.setEditable(false);
                 txt_SoNguoi.setEditable(true);
                 txt_Tang.setEditable(true);
                 cbxTinhTrang.setEnabled(false);
                 txt_DonGia1.setText(String.valueOf(p.getDonGia()));
                ImageIcon icon = new ImageIcon("src/img/select.png");
                JButton bt_DanhsachLP = new JButton(icon);
                bt_DanhsachLP.setBounds(310,110,80,30);
                bt_DanhsachLP.setBackground(colorButton);
                bt_DanhsachLP.setBorderPainted(false);
                bt_DanhsachLP.setFocusPainted(false);
                jf.add(bt_DanhsachLP);
                
                JButton btHoanTat_CT = new JButton("Hoàn tất");
                btHoanTat_CT.setBounds(460, 280, 120, 35);
                btHoanTat_CT.setFont(font_Button);
                btHoanTat_CT.setBackground(colorButton);
                btHoanTat_CT.setForeground(Color.WHITE);
                btHoanTat_CT.setBorderPainted(false);
                btHoanTat_CT.setFocusPainted(false);
                jf.add(btHoanTat_CT);
                btHoanTat_CT.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                Xulydulieu xldl = new Xulydulieu();
                PhongBUS pbus = new PhongBUS();
                PhongDTO pDTO = new PhongDTO();
                    LoaiPhongBUS loaiPhongBUS = new LoaiPhongBUS();
                    loaiPhongBUS.docdslp();

                    if(pbus.kiemtradulieuSua(txt_Tang.getText(), txt_SoNguoi.getText(), txt_DonGia1.getText())==1){
                        
                                for(LoaiPhongDTO lp : LoaiPhongBUS.dslp){
                                if(lp.getTenLoai().equals(txt_LoaiP.getText()));
                                pDTO.setMaLoai(lp.getMaLoai());
                            }
                                pDTO.setMaPhong(p.getMaPhong());
                                pDTO.setTang(Integer.parseInt(txt_Tang.getText()));
                                pDTO.setSLNguoi(Integer.parseInt(txt_SoNguoi.getText()));
                                pDTO.setDonGia(Integer.parseInt(txt_DonGia1.getText()));
                                pDTO.setTinhTrang(1);
                       if(pbus.suaPhong(pDTO)==1){
                       JOptionPane.showMessageDialog(jf, "Cập nhật thành công");
                       jf.setVisible(false);
                       }
                       else JOptionPane.showMessageDialog(jf, "Cập nhật thất bại");
                    }
                }
            
                     @Override
                    public void mouseEntered(MouseEvent arg0) {
                        btHoanTat_CT.setBackground(Color.DARK_GRAY);
                        btHoanTat_CT.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    }
                    @Override
                    public void mouseExited(MouseEvent arg0) {
                       btHoanTat_CT.setBackground(colorButton);
                    }
                });
                
                bt_DanhsachLP.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                         BangChonDSLP();
                    }
                     @Override
                    public void mouseEntered(MouseEvent arg0) {
                        bt_DanhsachLP.setBackground(Color.DARK_GRAY);
                        bt_DanhsachLP.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    }
                    @Override
                    public void mouseExited(MouseEvent arg0) {
                       bt_DanhsachLP.setBackground(colorButton);
                    }
                });
                
            }
             @Override
            public void mouseEntered(MouseEvent arg0) {
                btSua_CT.setBackground(Color.DARK_GRAY);
                btSua_CT.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
               btSua_CT.setBackground(colorButton);
            }
        });

        jf.setVisible(true);
    }
    
        public void Frame_ChiTietPhongDangThue(PhongDTO p,PhieuDatPhongDTO pdp,HoaDonDTO hd){
                    jf = new JFrame();
                    jf.setSize(650, 800);
                    jf.setLayout(null);
                    jf.setLocation(500, 20);
        DS_TamchuaCTHH = new ArrayList<>();
            NhanVienBUS nv_BUS = new NhanVienBUS();
            NhanVienDTO nv_DTO = new NhanVienDTO();
            KhachHangBUS kh_BUS = new KhachHangBUS();
            KhachHangDTO kh_DTO = new KhachHangDTO();
            kh_BUS.docdskh();
            kh_DTO = kh_BUS.LayThongtin1KH(pdp.getMaKH());
            nv_DTO = nv_BUS.LayThongtin1NV(pdp.getMaNV());
            
                ChiTietHoaDonBUS cthhbus=new ChiTietHoaDonBUS();
                DichVuBUS dvbus = new DichVuBUS();
                dvbus.docdsdv();
                cthhbus.docdscthd();
                HoaDonBUS hhbus = new HoaDonBUS();
                hhbus.docdshd();
                ChiTietHoaDonBUS cthdbus = new ChiTietHoaDonBUS();
                cthdbus.docdscthd();
                ArrayList<ChiTietHoaDonDTO> dscthh_1 = cthhbus.LayCTHoaDonTheoMaHD(hd.getMaHD());
                tblCT_HoaDon =tbl_DSCTHOADON(dscthh_1);
                sc3 = new JScrollPane(tblCT_HoaDon);
                sc3.setBounds(17, 480, 600, 200);
                jf.add(sc3);
                

        JLabel lb_TieuDeChiTietPhong = new JLabel("Thông tin nhân viên");
        lb_TieuDeChiTietPhong.setFont(font_lbPhong);
        lb_TieuDeChiTietPhong.setOpaque(true);
        lb_TieuDeChiTietPhong.setBackground(PhongTrong);
        lb_TieuDeChiTietPhong.setBounds(0,0,650,50);
        lb_TieuDeChiTietPhong.setForeground(Color.WHITE);
        lb_TieuDeChiTietPhong.setHorizontalAlignment(JLabel.CENTER);
        jf.add(lb_TieuDeChiTietPhong);
        
        JLabel HoTenNV = new JLabel("Họ tên nhân viên lập phiếu ");
        HoTenNV.setFont(font_tk);
        HoTenNV.setOpaque(true);
        HoTenNV.setBounds(20,60,270,35);
        HoTenNV.setHorizontalAlignment(JLabel.CENTER);
        jf.add(HoTenNV);
        
        JTextField txt_CTHoTenNV = new JTextField();
        txt_CTHoTenNV.setText(nv_DTO.getHo()+" "+nv_DTO.getTen());
        txt_CTHoTenNV.setBounds(295, 60,300, 35);
        txt_CTHoTenNV.setFont(font_CT_txt);
        txt_CTHoTenNV.setForeground(Color.RED);
        txt_CTHoTenNV.setHorizontalAlignment(JTextField.CENTER);
        txt_CTHoTenNV.setBorder(BorderFactory.createLoweredBevelBorder());
        txt_CTHoTenNV.setEditable(false);
        jf.add(txt_CTHoTenNV);
        
        JLabel NgayLapPhieu = new JLabel("Ngày lập phiếu ");
        NgayLapPhieu.setFont(font_tk);
        NgayLapPhieu.setOpaque(true);
        NgayLapPhieu.setBounds(15,110,180,35);
        NgayLapPhieu.setHorizontalAlignment(JLabel.CENTER);
        jf.add(NgayLapPhieu);
        
        JTextField txt_ngaylapphieu = new JTextField();
        txt_ngaylapphieu.setText(pdp.getNgayLapPDP());
        txt_ngaylapphieu.setBounds(210, 112, 230, 30);
        txt_ngaylapphieu.setFont(font_CT_txt);
        txt_ngaylapphieu.setHorizontalAlignment(JTextField.CENTER);
        txt_ngaylapphieu.setBorder(BorderFactory.createLoweredBevelBorder());
        txt_ngaylapphieu.setEditable(false);
        jf.add(txt_ngaylapphieu);
        
        JLabel lb_TieuDeNhanVien = new JLabel("Thông tin khách hàng");
        lb_TieuDeNhanVien.setFont(font_lbPhong);
        lb_TieuDeNhanVien.setOpaque(true);
        lb_TieuDeNhanVien.setBackground(PhongTrong);
        lb_TieuDeNhanVien.setBounds(0,155,650,50);
        lb_TieuDeNhanVien.setForeground(Color.WHITE);
        lb_TieuDeNhanVien.setHorizontalAlignment(JLabel.CENTER);
        jf.add(lb_TieuDeNhanVien);
        
        
        JLabel HoTenKH = new JLabel("Họ tên khách hàng ");
        HoTenKH.setFont(font_tk);
        HoTenKH.setOpaque(true);
        HoTenKH.setBounds(15,215,200,35);
        HoTenKH.setHorizontalAlignment(JLabel.CENTER);
        jf.add(HoTenKH);
        
        JTextField txt_CTHoTenKH = new JTextField();
        txt_CTHoTenKH.setText(kh_DTO.getHo()+" "+kh_DTO.getTen());
        txt_CTHoTenKH.setBounds(225, 215,300, 30);
        txt_CTHoTenKH.setFont(font_CT_txt);
        txt_CTHoTenKH.setForeground(Color.RED);
        txt_CTHoTenKH.setHorizontalAlignment(JTextField.CENTER);
        txt_CTHoTenKH.setBorder(BorderFactory.createLoweredBevelBorder());
        txt_CTHoTenKH.setEditable(false);
        jf.add(txt_CTHoTenKH);
        
        JLabel SDT = new JLabel("Số điện thoại ");
        SDT.setFont(font_tk);
        SDT.setOpaque(true);
        SDT.setBounds(10,265,180,35);
        SDT.setHorizontalAlignment(JLabel.CENTER);
        jf.add(SDT);
        
        JTextField txt_sdtKH = new JTextField();
        txt_sdtKH.setText(kh_DTO.getSdt());
        txt_sdtKH.setBounds(225, 265, 300, 30);
        txt_sdtKH.setFont(font_CT_txt);
        txt_sdtKH.setHorizontalAlignment(JTextField.CENTER);
        txt_sdtKH.setBorder(BorderFactory.createLoweredBevelBorder());
        txt_sdtKH.setEditable(false);
        jf.add(txt_sdtKH);
        
        JLabel lb_TieuDeHoaDon = new JLabel("Chi tiết hóa đơn");
        lb_TieuDeHoaDon.setFont(font_lbPhong);
        lb_TieuDeHoaDon.setOpaque(true);
        lb_TieuDeHoaDon.setBackground(PhongTrong);
        lb_TieuDeHoaDon.setBounds(0,310,650,50);
        lb_TieuDeHoaDon.setForeground(Color.WHITE);
        lb_TieuDeHoaDon.setHorizontalAlignment(JLabel.CENTER);
        jf.add(lb_TieuDeHoaDon);
        
        JLabel NgayThue = new JLabel("Ngày thuê ");
        NgayThue.setFont(font_tk);
        NgayThue.setOpaque(true);
        NgayThue.setBounds(15,370,120,35);
        NgayThue.setHorizontalAlignment(JLabel.CENTER);
        jf.add(NgayThue);
        
        JTextField txt_NgayThue = new JTextField();
        txt_NgayThue.setText(pdp.getNgayThue());
        txt_NgayThue.setBounds(140, 373,130, 30);
        txt_NgayThue.setFont(font_CT_txt);
//        txt_NgayThue.setForeground(Color.RED);
        txt_NgayThue.setHorizontalAlignment(JTextField.CENTER);
        txt_NgayThue.setBorder(BorderFactory.createLoweredBevelBorder());
        txt_NgayThue.setEditable(false);
        jf.add(txt_NgayThue);
        
        JLabel NgayTra = new JLabel("Ngày trả ");
        NgayTra.setFont(font_tk);
        NgayTra.setOpaque(true);
        NgayTra.setBounds(320,370,100,35);
        NgayTra.setHorizontalAlignment(JLabel.CENTER);
        jf.add(NgayTra);
        
        JTextField txt_ngaytra = new JTextField();
        txt_ngaytra.setText(pdp.getNgayTra());
        txt_ngaytra.setBounds(430, 373, 130, 30);
        txt_ngaytra.setFont(font_CT_txt);
//        txt_ngaytra.setForeground(Color.RED);
        txt_ngaytra.setHorizontalAlignment(JTextField.CENTER);
        txt_ngaytra.setBorder(BorderFactory.createLoweredBevelBorder());
        txt_ngaytra.setEditable(false);
        jf.add(txt_ngaytra);
        
        JLabel MaP = new JLabel("Mã phòng ");
        MaP.setFont(font_tk);
        MaP.setOpaque(true);
        MaP.setBounds(20,410,100,35);
        MaP.setHorizontalAlignment(JLabel.CENTER);
        jf.add(MaP);
        
        JTextField txt_map = new JTextField();
        txt_map.setText(p.getMaPhong());
        txt_map.setBounds(140, 413, 130, 30);
        txt_map.setFont(font_CT_txt);
        txt_map.setForeground(Color.RED);
        txt_map.setHorizontalAlignment(JTextField.CENTER);
        txt_map.setBorder(BorderFactory.createLoweredBevelBorder());
        txt_map.setEditable(false);
        jf.add(txt_map);
        
        btThem_CT = new JButton("Thêm");
                    btThem_CT.setBounds(380, 445, 110, 30);
                    btThem_CT.setFont(font_Button);
                    btThem_CT.setBackground(colorButton);
                    btThem_CT.setForeground(Color.WHITE);
                    btThem_CT.setBorderPainted(false);
                    btThem_CT.setFocusPainted(false);
                    jf.add(btThem_CT);
                    btThem_CT.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        BangChonDSDV(hd,pdp);
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
                    
                    
        btXoa_CT = new JButton("Xóa");
                    btXoa_CT.setBounds(500, 445, 110, 30);
                    btXoa_CT.setFont(font_Button);
                    btXoa_CT.setBackground(colorButton);
                    btXoa_CT.setForeground(Color.WHITE);
                    btXoa_CT.setBorderPainted(false);
                    btXoa_CT.setFocusPainted(false);
                    jf.add(btXoa_CT);       
                    btXoa_CT.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                         int removeIndex = tblCT_HoaDon.getSelectedRow();
                         HoaDonBUS hd_BUs = new HoaDonBUS();
                         if(removeIndex < 0){
                             JOptionPane.showMessageDialog(jf, "Vui lòng chọn dịch vụ");
                         }
                         else {
                              String madv = tblCT_HoaDon.getModel().getValueAt(removeIndex, 0).toString();
                             int input=JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa", "Xóa phiếu nhập hàng", JOptionPane.YES_NO_OPTION);   
                            if(input==0){
                            hhbus.UpdateTien_SauXoa(hd.getMaHD(),madv);
                                    sc3.removeAll();
                                 ArrayList<ChiTietHoaDonDTO> dscthh_2 = cthhbus.LayCTHoaDonTheoMaHD(hd.getMaHD());
                                    tblCT_HoaDon =tbl_DSCTHOADON(dscthh_2);
                                    sc4 = new JScrollPane(tblCT_HoaDon);
                                    sc4.setBounds(0, 0, 600, 200);
                                    sc3.add(sc4);
                                    sc3.repaint();
                                    sc3.revalidate();
                                    jf.add(sc3);
                            }
                            else
                            JOptionPane.showMessageDialog(null, "Xóa thất bại !!!");
                         }
                    }
                     @Override
                    public void mouseEntered(MouseEvent arg0) {
                        btXoa_CT.setBackground(Color.DARK_GRAY);
                        btXoa_CT.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    }
                    @Override
                    public void mouseExited(MouseEvent arg0) {
                       btXoa_CT.setBackground(colorButton);
                    }
                });
                    
       
        JButton btThoat_CT = new JButton("Thoát");
        btThoat_CT.setBounds(260, 705, 120, 35);
        btThoat_CT.setFont(font_Button);
        btThoat_CT.setBackground(colorButton);
        btThoat_CT.setForeground(Color.WHITE);
        btThoat_CT.setBorderPainted(false);
        btThoat_CT.setFocusPainted(false);
        jf.add(btThoat_CT);
         btThoat_CT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                  jf.setVisible(false);
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


        jf.setVisible(true);
    } 
        public void Frame_ThemP(){
        JFrame jf = new JFrame();
        jf.setSize(650, 380);
        jf.setLayout(null);
        jf.setLocation(500, 200);
        
        JLabel lb_TieuDeChiTietPhong = new JLabel("Thêm phòng");
        lb_TieuDeChiTietPhong.setFont(font_lbPhong);
        lb_TieuDeChiTietPhong.setOpaque(true);
        lb_TieuDeChiTietPhong.setBackground(PhongTrong);
        lb_TieuDeChiTietPhong.setBounds(0,0,650,50);
        lb_TieuDeChiTietPhong.setForeground(Color.WHITE);
        lb_TieuDeChiTietPhong.setHorizontalAlignment(JLabel.CENTER);
        jf.add(lb_TieuDeChiTietPhong);
        
        JLabel MaPhong = new JLabel("Mã phòng ");
        MaPhong.setFont(font_tk);
        MaPhong.setOpaque(true);
        MaPhong.setBounds(20,60,120,35);
        MaPhong.setHorizontalAlignment(JLabel.CENTER);
        jf.add(MaPhong);
        
        JTextField txt_CTma = new JTextField();
        txt_CTma.setBounds(145, 65,140, 25);
        PhongBUS p_bus = new PhongBUS();
        int sl =p_bus.LayMaCuoiDS();
        Xulydulieu xldl = new Xulydulieu();
        txt_CTma.setText(xldl.TaoMaMoi("PHONG", sl+1));
        txt_CTma.setFont(font_CT_txt);
        txt_CTma.setForeground(Color.RED);
        txt_CTma.setHorizontalAlignment(JTextField.CENTER);
        txt_CTma.setBorder(BorderFactory.createLoweredBevelBorder());
        txt_CTma.setEditable(false);
        jf.add(txt_CTma);
        
        JLabel LoaiPhong = new JLabel("Loại phòng ");
        LoaiPhong.setFont(font_tk);
        LoaiPhong.setOpaque(true);
        LoaiPhong.setBounds(20,110,120,35);
        LoaiPhong.setHorizontalAlignment(JLabel.CENTER);
        jf.add(LoaiPhong);
        
        txt_LoaiP = new JTextField();
        LoaiPhongBUS lp_BUS = new LoaiPhongBUS();
        lp_BUS.docdslp();
        txt_LoaiP.setBounds(145, 114, 160, 25);
        txt_LoaiP.setFont(font_CT_txt);
        txt_LoaiP.setHorizontalAlignment(JTextField.CENTER);
        txt_LoaiP.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorButton));
        txt_LoaiP.setEditable(false);
        jf.add(txt_LoaiP);
        

        JLabel Tang= new JLabel("Tầng ");
        Tang.setFont(font_tk);
        Tang.setOpaque(true);
        Tang.setBounds(420,110,100,35);
        Tang.setHorizontalAlignment(JLabel.CENTER);
        jf.add(Tang);
        
        JTextField txt_Tang = new JTextField();
        txt_Tang.setBounds(530, 114, 70, 25);
        txt_Tang.setFont(font_CT_txt);
        txt_Tang.setHorizontalAlignment(JTextField.CENTER);
        txt_Tang.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorButton));
        txt_Tang.setEditable(true);
        jf.add(txt_Tang);
        
        JLabel SoNguoi = new JLabel("Số người ");
        SoNguoi.setFont(font_tk);
        SoNguoi.setOpaque(true);
        SoNguoi.setBounds(20,160,120,35);
        SoNguoi.setHorizontalAlignment(JLabel.CENTER);
        jf.add(SoNguoi);
        
        JTextField txt_SoNguoi = new JTextField();
        txt_SoNguoi.setBounds(145, 165, 160, 25);
        txt_SoNguoi.setFont(font_CT_txt);
        txt_SoNguoi.setHorizontalAlignment(JTextField.CENTER);
        txt_SoNguoi.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorButton));
        txt_SoNguoi.setEditable(true);
        jf.add(txt_SoNguoi);
        
        JLabel Tien= new JLabel("Giá phòng ");
        Tien.setFont(font_tk);
        Tien.setOpaque(true);
        Tien.setBounds(340,160,120,35);
        Tien.setHorizontalAlignment(JLabel.CENTER);
        jf.add(Tien);
        
        txt_DonGia = new JTextField();
        txt_DonGia.setBounds(465, 165, 160, 25);
        txt_DonGia.setFont(font_CT_txt);
        txt_DonGia.setHorizontalAlignment(JTextField.CENTER);
        txt_DonGia.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorButton));
        txt_DonGia.setEditable(true);
        jf.add(txt_DonGia);
        
        JLabel TinhTrang= new JLabel("Tình trạng ");
        TinhTrang.setFont(font_tk);
        TinhTrang.setOpaque(true);
        TinhTrang.setBounds(20,210,120,35);
        TinhTrang.setHorizontalAlignment(JLabel.CENTER);
        jf.add(TinhTrang);
        
        String DSTinhTrang[] = { "Đang trống", "Đang dọn dẹp"};
                cbxTinhTrang = new JComboBox(DSTinhTrang);
                cbxTinhTrang.setBounds(145, 215, 160, 30);
                cbxTinhTrang.setEnabled(true);
                jf.add(cbxTinhTrang);

      
        JButton btThem_CT = new JButton("Thêm");
        btThem_CT.setBounds(460, 280, 120, 35);
        btThem_CT.setFont(font_Button);
        btThem_CT.setBackground(colorButton);
        btThem_CT.setForeground(Color.WHITE);
        btThem_CT.setBorderPainted(false);
        btThem_CT.setFocusPainted(false);
        jf.add(btThem_CT);
         btThem_CT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Xulydulieu xldl = new Xulydulieu();
                PhongBUS pbus = new PhongBUS();
                PhongDTO pDTO = new PhongDTO();
                    LoaiPhongBUS loaiPhongBUS = new LoaiPhongBUS();
                    loaiPhongBUS.docdslp();
                    for(LoaiPhongDTO lp : LoaiPhongBUS.dslp){
                        if(lp.getTenLoai().equals(txt_LoaiP.getText()));
                        pDTO.setMaLoai(lp.getMaLoai());
                    }
                    if(pbus.kiemtradulieuThem(txt_Tang.getText(), txt_SoNguoi.getText(), txt_DonGia.getText())==1){
                            pDTO.setMaPhong(txt_CTma.getText());
                            pDTO.setTang(Integer.parseInt(txt_Tang.getText()));
                            pDTO.setSLNguoi(Integer.parseInt(txt_SoNguoi.getText()));
                            pDTO.setDonGia(Integer.parseInt(txt_DonGia.getText()));
                            if(cbxTinhTrang.getSelectedIndex()==0){
                                pDTO.setTinhTrang(1);
                            }
                            if(cbxTinhTrang.getSelectedIndex()==1){
                                pDTO.setTinhTrang(3);
                            }
                       if(pbus.themphong(pDTO)==1){
                       JOptionPane.showMessageDialog(jf, "Thêm thành công");
                       jf.setVisible(false);
                       }
                       else JOptionPane.showMessageDialog(jf, "Thêm thất bại");
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
            }
        });
        
                ImageIcon icon = new ImageIcon("src/img/select.png");
                JButton bt_DanhsachLP = new JButton(icon);
                bt_DanhsachLP.setBounds(310,110,80,30);
                bt_DanhsachLP.setBackground(colorButton);
                bt_DanhsachLP.setBorderPainted(false);
                bt_DanhsachLP.setFocusPainted(false);
                jf.add(bt_DanhsachLP);
                bt_DanhsachLP.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                         BangChonDSLP();
                    }
                     @Override
                    public void mouseEntered(MouseEvent arg0) {
                        bt_DanhsachLP.setBackground(Color.DARK_GRAY);
                        bt_DanhsachLP.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    }
                    @Override
                    public void mouseExited(MouseEvent arg0) {
                       bt_DanhsachLP.setBackground(colorButton);
                    }
                });

        jf.setVisible(true);
    }
        
     public JTable tbl_DSCTHOADON(ArrayList<ChiTietHoaDonDTO> pnh_hh){
        Xulydulieu xldl = new Xulydulieu();
        tblCT_HoaDon=new JTable();
                tblCT_HoaDon.getTableHeader().setBackground(new Color(36,36,36));
                tblCT_HoaDon.getTableHeader().setForeground(Color.WHITE);
                tblCT_HoaDon.getTableHeader().setFont(font_lb);
                tblCT_HoaDon.getTableHeader().setPreferredSize(new Dimension(0,30));
                tblCT_HoaDon.setFont(font_lb);
                tblCT_HoaDon.setForeground(Color.BLACK);
                tblCT_HoaDon.setRowHeight(25);
                ChiTietHoaDonBUS ctpnhbus=new ChiTietHoaDonBUS();
                DichVuBUS hhbus = new DichVuBUS();
                    hhbus.docdsdv();
                md=new DefaultTableModel();
                Vector cot=new Vector();
                cot.add("Mã dịch vụ");
                cot.add("Tên dịch vụ");
                cot.add("Số lượng");
                cot.add("Thành tiền");
                cot.add("Tiền KM");
                if (md.getRowCount()==0){
                    md=new DefaultTableModel(cot, 0);
                }
                for (ChiTietHoaDonDTO ctpnh : pnh_hh){
                    Vector dong=new Vector();
                    dong.add(ctpnh.getMaDV());
                    DichVuDTO dichvu= hhbus.Lay1madichvu(ctpnh.getMaDV());
                    dong.add(dichvu.getTenDV());
                    dong.add(ctpnh.getSL());
                    dong.add(xldl.ChuyenKieuTien(String.valueOf(ctpnh.getThanhTien())));
                    dong.add(ctpnh.getTienKM());
                    md.addRow(dong);
                }
                tblCT_HoaDon.setModel(md);
                tblCT_HoaDon.getColumnModel().getColumn(0).setMinWidth(100);
                tblCT_HoaDon.getColumnModel().getColumn(1).setMinWidth(120);
                tblCT_HoaDon.getColumnModel().getColumn(2).setMinWidth(100);
                tblCT_HoaDon.getColumnModel().getColumn(3).setMinWidth(150);
                tblCT_HoaDon.getColumnModel().getColumn(3).setMinWidth(130);
                //--------------------Căn giữa trong bảng-----------------------//
                DefaultTableCellRenderer center=new DefaultTableCellRenderer();
                center.setHorizontalAlignment(JLabel.CENTER);
                tblCT_HoaDon.setDefaultRenderer(tblCT_HoaDon.getColumnClass(0), center);
                tblCT_HoaDon.updateUI();
                return tblCT_HoaDon;
    }    
        
        public void BangChonDSLP(){
        JFrame fchon=new JFrame();
        fchon.setLayout(null);
        fchon.setBounds(0, 0, 600, 340);
        fchon.setPreferredSize(new Dimension(600,340));
        
        //---------------------------------Hiện giữa màn hình-----------------------------//
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        fchon.setLocation((int)d.getWidth()/2 - (int)fchon.getPreferredSize().getWidth()/2,
                      (int)d.getHeight()/2 - (int)fchon.getPreferredSize().getHeight()/2);
        //--------------------------------------------------------------------------------//
        
        JLabel lbTieude=new JLabel("Chọn loại phòng");
        lbTieude.setFont(font_lbPhong);
        lbTieude.setForeground(Color.BLACK);
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        lbTieude.setOpaque(true);
        lbTieude.setBackground(colorButton);
        lbTieude.setForeground(Color.white);
        lbTieude.setBounds(0, 0, 600, 55);
        
        fchon.add(lbTieude);
        
       LoaiPhongBUS nvbus=new LoaiPhongBUS();
            nvbus.docdslp();
        tbdsloaiphong=new JTable();
        tbdsloaiphong.getTableHeader().setBackground(new Color(36,36,36));
        tbdsloaiphong.getTableHeader().setForeground(Color.WHITE);
        tbdsloaiphong.getTableHeader().setFont(font_lb);
        tbdsloaiphong.getTableHeader().setPreferredSize(new Dimension(0,30));
        tbdsloaiphong.setFont(font_lb);
        tbdsloaiphong.setForeground(Color.BLACK);
        tbdsloaiphong.setRowHeight(25);
        
        DefaultTableModel md=new DefaultTableModel();
        Vector cot=new Vector();
        cot.add("Mã loại phòng");
        cot.add("Tên loại phòng");
        if (md.getRowCount()==0){
            md=new DefaultTableModel(cot, 0);
        }
        for (LoaiPhongDTO k : LoaiPhongBUS.dslp){
            Vector dong=new Vector();
            dong.add(k.getMaLoai());
            dong.add(k.getTenLoai());
            md.addRow(dong);
        }
        tbdsloaiphong.setModel(md);
        tbdsloaiphong.getColumnModel().getColumn(0).setMinWidth(50);
        tbdsloaiphong.getColumnModel().getColumn(1).setMinWidth(120);                
        
        //--------------------Căn giữa trong bảng-----------------------//
        DefaultTableCellRenderer center=new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        tbdsloaiphong.setDefaultRenderer(tbdsloaiphong.getColumnClass(0), center);
        tbdsloaiphong.updateUI();
        //--------------------------------------------------------------//
        
        JScrollPane sc=new JScrollPane();
        sc.setViewportView(tbdsloaiphong);
        sc.setBounds(35, 80, 520, 190);
        tbdsloaiphong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int removeIndex = tbdsloaiphong.getSelectedRow();
                fchon.setVisible(false);
                txt_LoaiP.setText(tbdsloaiphong.getModel().getValueAt(removeIndex, 1).toString());
            }
        });
        
        fchon.add(sc);
        fchon.setVisible(true);
    }
        public void BangChonDSDV(HoaDonDTO hd,PhieuDatPhongDTO pdp){
        JFrame fchon=new JFrame();
        fchon.setLayout(null);
        fchon.setBounds(0, 0, 600, 370);
        fchon.setPreferredSize(new Dimension(600,340));
        
        //---------------------------------Hiện giữa màn hình-----------------------------//
        fchon.setLocation(520, 300);
        //--------------------------------------------------------------------------------//
        
        JLabel lbTieude=new JLabel("Chọn dịch vụ");
        lbTieude.setFont(font_lbPhong);
        lbTieude.setForeground(Color.BLACK);
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        lbTieude.setOpaque(true);
        lbTieude.setBackground(colorButton);
        lbTieude.setForeground(Color.white);
        lbTieude.setBounds(0, 0, 600, 55);
        
        fchon.add(lbTieude);
        
       DichVuBUS dvbus=new DichVuBUS();
            dvbus.docdsdv();
        tbdsdichvu=new JTable();
        tbdsdichvu.getTableHeader().setBackground(new Color(36,36,36));
        tbdsdichvu.getTableHeader().setForeground(Color.WHITE);
        tbdsdichvu.getTableHeader().setFont(font_lb);
        tbdsdichvu.getTableHeader().setPreferredSize(new Dimension(0,30));
        tbdsdichvu.setFont(font_lb);
        tbdsdichvu.setForeground(Color.BLACK);
        tbdsdichvu.setRowHeight(25);
        
        DefaultTableModel md=new DefaultTableModel();
        Vector cot=new Vector();
        cot.add("Mã dịch vụ");
        cot.add("Tên dịch vụ");
        cot.add("Đơn giá");
        if (md.getRowCount()==0){
            md=new DefaultTableModel(cot, 0);
        }
        for (DichVuDTO k : DichVuBUS.dsdv){
            Vector dong=new Vector();
            dong.add(k.getMaDV());
            dong.add(k.getTenDV());
            dong.add(k.getDonGia());
            md.addRow(dong);
        }
        tbdsdichvu.setModel(md);
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
        btThem_CT = new JButton("Thêm");
                    btThem_CT.setBounds(240, 280, 110, 30);
                    btThem_CT.setFont(font_Button);
                    btThem_CT.setBackground(colorButton);
                    btThem_CT.setForeground(Color.WHITE);
                    btThem_CT.setBorderPainted(false);
                    btThem_CT.setFocusPainted(false);
                    fchon.add(btThem_CT);
                    btThem_CT.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                         ChiTietKMDichVuBUS ctkmdv = new ChiTietKMDichVuBUS();
                          ChiTietHoaDonBUS ctHDBUS = new ChiTietHoaDonBUS();
                         int removeIndex = tbdsdichvu.getSelectedRow();
                         if(removeIndex < 0){
                             JOptionPane.showMessageDialog(fchon, "Vui lòng chọn dịch vụ");
                         }
                         else {
                            String madv = tbdsdichvu.getModel().getValueAt(removeIndex, 0).toString();
                            ChiTietHoaDonDTO ctHD = new ChiTietHoaDonDTO();
                            ChiTietKMDichVuDTO ctkmdvDTO = new ChiTietKMDichVuDTO();
                            HoaDonBUS hdBUS = new HoaDonBUS();
                            ChiTietHoaDonBUS cthd_bus = new ChiTietHoaDonBUS();
                            ctHD.setMaHD(hd.getMaHD());

                            ctHD.setMaDV(madv);
                            ctHD.setMaKMDV(ctkmdv.LayMaKhuyenMaiDV(madv));
                            ctkmdvDTO = ctkmdv.LayTT1CTKMDVTuMaKMDV(ctkmdv.LayMaKhuyenMaiDV(madv));
                            int tien = Integer.parseInt(tbdsdichvu.getModel().getValueAt(removeIndex, 2).toString());
                            ctHD.setDonGia(tien);
                            ctHD.setSL(1);
                            int thanhtien = tien*1;
                            ctHD.setThanhTien(thanhtien);
                            int ptramKM = ctkmdvDTO.getPhanTramKM();
                            int tienKM = (thanhtien * ptramKM)/100;
                            ctHD.setTienKM(tienKM);

                            ArrayList<ChiTietHoaDonDTO> dscthd_2 = cthd_bus.LayCTHoaDonTheoMaHD(hd.getMaHD());
                            
                            if(ctHDBUS.kiemtraDVTrung(dscthd_2,madv)==1) {
                                ChiTietHoaDonDTO dt1 = new ChiTietHoaDonDTO();
                                dt1 = ctHDBUS.lay1CT(hd.getMaHD(),madv);
                                int soluong = dt1.getSL()+1;
                                ctHD.setSL(soluong);
                                int dongia = ctHD.getDonGia();
                                int thanhtien1 = soluong * dongia;
                                ctHD.setThanhTien(thanhtien1);
                                int ptramKm1 = ctkmdvDTO.getPhanTramKM();
                                int tienKM1 = (thanhtien1*ptramKm1)/100;
                                ctHD.setTienKM(tienKM1);
                                ctHDBUS.sua(ctHD);
                                
                                int tongtienthue = hd.getTongTienThue();
                                int tongtienDV = hd.getTongTienDV()+ctHD.getDonGia();
                                int tientongcong = tongtienthue + tongtienDV + ((tongtienthue + tongtienDV)*10)/100;
                                int tienkhuyenmai = hd.getTongTienKM()+tienKM;
                                int tongtienphaitra = tientongcong-tienkhuyenmai;
                                hd.setTongTienThue(tongtienthue);
                                hd.setTongTienDV(tongtienDV);
                                hd.setTongCong(tientongcong);
                                hd.setTongTienKM(tienkhuyenmai);
                                hd.setTongPhaiTra(tongtienphaitra);
                                hdBUS.suaSTienHoaDon(hd);
                                sc3.removeAll();
                                ArrayList<ChiTietHoaDonDTO> dscthd_1 = cthd_bus.LayCTHoaDonTheoMaHD(hd.getMaHD());
                                tblCT_HoaDon =tbl_DSCTHOADON(dscthd_1);
                                sc4 = new JScrollPane(tblCT_HoaDon);
                                sc4.setBounds(0, 0, 600, 200);
                                sc3.add(sc4);
                                sc3.repaint();
                                sc3.revalidate();
                                jf.add(sc3);
                            }
                            else {
                            if(ctHDBUS.them(ctHD)==1){
                                JOptionPane.showMessageDialog(fchon, "Thêm chi tiết hóa đơn thành công");
                                int tongtienthue = hd.getTongTienThue();
                                int tongtienDV = hd.getTongTienDV()+ctHD.getThanhTien();
                                int tientongcong = tongtienthue + tongtienDV + ((tongtienthue + tongtienDV)*10)/100;
                                int tienkhuyenmai = hd.getTongTienKM()+ctHD.getTienKM();
                                int tongtienphaitra = tientongcong-tienkhuyenmai;
                                hd.setTongTienThue(tongtienthue);
                                hd.setTongTienDV(tongtienDV);
                                hd.setTongCong(tientongcong);
                                hd.setTongTienKM(tienkhuyenmai);
                                hd.setTongPhaiTra(tongtienphaitra);
                                hdBUS.suaSTienHoaDon(hd);
                            }
                            else JOptionPane.showMessageDialog(fchon, "Thêm chi tiết hóa đơn thất bại");
                            }
                        dscthd_2 = cthd_bus.LayCTHoaDonTheoMaHD(hd.getMaHD());
                        sc3.removeAll();
                        tblCT_HoaDon =tbl_DSCTHOADON(dscthd_2);
                        sc4 = new JScrollPane(tblCT_HoaDon);
                        sc4.setBounds(0, 0, 600, 200);
                        sc3.add(sc4);
                        sc3.repaint();
                        sc3.revalidate();
                        jf.add(sc3);
                        fchon.setVisible(false);
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
                    }
                });
        fchon.setVisible(true);
    }
//    public static void main(String[] args) {
//        QuanLyGUI p=new QuanLyGUI("NV0001","QL");
//    }
}
