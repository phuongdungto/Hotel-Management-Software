/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.*;
import DTO.TaiKhoanDTO;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

/**
 *
 * @author minhd
 */
public class DangNhapGUI {
    protected JFrame f;
    protected JLabel lbHinhnen;
    protected JPanel pDangNhap;
    protected JLabel lbDangNhap;
    protected JLabel lbIconTaiKhoan;
    protected JLabel lbIconMatKhau;
    protected JTextField txTaiKhoan;
    protected JPasswordField txMatKhau;
    protected JButton btDangNhap;
    protected JLabel lbThoat;
    protected JButton btThoat;
    protected static String MaNV;
    public DangNhapGUI(){
        KhoitaoJFrame();
        
    }
    public void KhoitaoJFrame(){
        f=new JFrame();
        f.setBounds(0,0,920,613);
        f.setPreferredSize(new Dimension(920, 613));
        f.setLayout(null);
        f.setUndecorated(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //---------------------------------Hiện giữa màn hình-----------------------------//
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        f.setLocation((int)d.getWidth()/2 - (int)f.getPreferredSize().getWidth()/2,
                      (int)d.getHeight()/2 - (int)f.getPreferredSize().getHeight()/2);
        //--------------------------------------------------------------------------------//
        
        ImageIcon img=new ImageIcon("src/img/dangnhap.png");
        
        lbHinhnen=new JLabel();
        lbHinhnen.setBounds(0,0,920,613);
        lbHinhnen.setIcon(img);
        
        f.setShape(new RoundRectangle2D.Double(0, 0, 920, 613, 50, 50));
        f.add(lbHinhnen);
        
        formDangNhap();
        Doctatcadanhsach();
        ChucNangCacNut();
        
        f.setVisible(true);
    }
    
    public void formDangNhap(){
        pDangNhap=new JPanel();
        pDangNhap.setBounds(550,126,300,250);
        pDangNhap.setBackground(new Color(0,0,0,0.6f));
        pDangNhap.setLayout(null);
        
        lbHinhnen.add(pDangNhap);
        
        lbDangNhap=new JLabel("Đăng nhập");
        lbDangNhap.setFont(new Font("Times New Roman",Font.BOLD,25));
        lbDangNhap.setForeground(Color.WHITE);
        lbDangNhap.setBackground(new Color(0,0,0,0.1f));
        lbDangNhap.setBounds(80, 30, 150, 30);
        lbDangNhap.setHorizontalAlignment(JLabel.CENTER);
        lbDangNhap.setOpaque(true);
        
        pDangNhap.add(lbDangNhap);
        
        //----------------------Tài khoản-----------------------//
        ImageIcon imgDangNhap=new ImageIcon("src/img/taikhoan.png");
        lbIconTaiKhoan=new JLabel();
        lbIconTaiKhoan.setIcon(imgDangNhap);
        lbIconTaiKhoan.setBounds(30,80,24,24);
        
        pDangNhap.add(lbIconTaiKhoan);
        
        txTaiKhoan=new JTextField("Nhập tài khoản");
        txTaiKhoan.setBounds(70,80,180,20);
        txTaiKhoan.setFont(new Font("Times New Roman",0,18));
        txTaiKhoan.setBackground(new Color(0,0,0));
        txTaiKhoan.setForeground(Color.LIGHT_GRAY);
        txTaiKhoan.setCaretColor(Color.WHITE);                  // Màu con trỏ chuột
        txTaiKhoan.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        txTaiKhoan.setHorizontalAlignment(JTextField.CENTER);
        txTaiKhoan.setOpaque(true);
        
        pDangNhap.add(txTaiKhoan);                
        //------------------------------------------------------//
        
        
        //----------------------Mật Khẩu------------------------//
        ImageIcon imgMatKhau=new ImageIcon("src/img/matkhau.png");
        lbIconMatKhau=new JLabel();
        lbIconMatKhau.setIcon(imgMatKhau);
        lbIconMatKhau.setBounds(30, 130, 24, 24);
        
        pDangNhap.add(lbIconMatKhau);
        
        txMatKhau=new JPasswordField("Nhập mật khẩu");
        txMatKhau.setBounds(70, 130, 180, 20);
        txMatKhau.setFont(new Font("Times New Roman",0,18));
        txMatKhau.setForeground(Color.LIGHT_GRAY);
        txMatKhau.setBackground(new Color(0,0,0));
        txMatKhau.setCaretColor(Color.WHITE);
        txMatKhau.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        txMatKhau.setHorizontalAlignment(JTextField.CENTER);
        txMatKhau.setEchoChar((char) 0);
        txMatKhau.setOpaque(true);
        
        pDangNhap.add(txMatKhau);        
        //------------------------------------------------------//
        
        //----------------------Các nút chức năng------------------------//
        btDangNhap=new JButton("Đăng nhập");
        btDangNhap.setBounds(95, 190, 130, 20);
        btDangNhap.setFont(new Font("Times New Roman",0,18));
        btDangNhap.setForeground(Color.BLACK);
        btDangNhap.setBackground(Color.WHITE);
        btDangNhap.setFocusable(false);
        btDangNhap.setBorderPainted(false);
        btDangNhap.setOpaque(true);
        
        pDangNhap.add(btDangNhap);
        
        btThoat=new JButton("X");
        lbThoat=new JLabel("X");
        btThoat.setBounds(240,10,45,20);
        btThoat.setFont(new Font("Times New Roman",Font.BOLD,15));
        btThoat.setForeground(Color.BLACK);
        btThoat.setBackground(Color.WHITE);
        btThoat.setHorizontalAlignment(JLabel.CENTER);
        btThoat.setFocusable(false);
        btThoat.setBorderPainted(false);
        btThoat.setOpaque(true);
        
        pDangNhap.add(btThoat);
        //---------------------------------------------------------------//
        
    }
    public void ChucNangCacNut(){
        txTaiKhoan.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent arg0) {
                if (txTaiKhoan.getText().equals("Nhập tài khoản")){
                    txTaiKhoan.setText("");
                    txTaiKhoan.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent arg0) {
                if (txTaiKhoan.getText().equals("")){
                    txTaiKhoan.setText("Nhập tài khoản");
                    txTaiKhoan.setForeground(Color.LIGHT_GRAY);
                }
            }
            
        });
        
        txMatKhau.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent arg0) {
                char[] input=txMatKhau.getPassword();
                String s=String.valueOf(input);
                if (s.equals("Nhập mật khẩu")){
                    txMatKhau.setText("");
                    txMatKhau.setEchoChar('*');
                    txMatKhau.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent arg0) {
                char[] input=txMatKhau.getPassword();
                String s=String.valueOf(input);
                if (s.equals("")){
                    txMatKhau.setText("Nhập mật khẩu");
                    txMatKhau.setEchoChar((char) 0);
                    txMatKhau.setForeground(Color.LIGHT_GRAY);
                }
            }
        });
        
        btDangNhap.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent arg0) {
                String tentk=txTaiKhoan.getText();
                char[] input=txMatKhau.getPassword();
                String mk=String.valueOf(input);
                TaiKhoanBUS tkbus=new TaiKhoanBUS();
                if (tkbus.Ktradulieu(tentk, mk)==0)
                    JOptionPane.showMessageDialog(f, "Chưa nhập tên tài khoản hoặc mật khẩu");
                else{
                    TaiKhoanDTO tk=new TaiKhoanDTO();
                    tk=tkbus.Lay1TaiKhoan(tentk, mk);
                    if (tk!=null){
                        DangNhapGUI.MaNV=tk.getMaNV();
                        JOptionPane.showMessageDialog(f, "Đăng nhập thành công");
                        QuanLyGUI ql=new QuanLyGUI(DangNhapGUI.MaNV,tk.getMaQuyen());
                        f.setVisible(false);
                    }
                    else
                        JOptionPane.showMessageDialog(f, "Sai tên đăng nhập hoặc mật khẩu");
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
                btDangNhap.setBackground(Color.LIGHT_GRAY);
                btDangNhap.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                btDangNhap.setBackground(Color.WHITE);
            }
            
        });
        
        btThoat.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent arg0) {
                System.exit(0);
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btThoat.setForeground(Color.red);
                btThoat.setBackground(Color.LIGHT_GRAY);
                btThoat.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                btThoat.setForeground(Color.BLACK);   
                btThoat.setBackground(Color.WHITE);
            }
        
        });
    }
    public void Doctatcadanhsach(){
        TaiKhoanBUS tk=new TaiKhoanBUS();
        tk.docdstk();
        NhanVienBUS nv=new NhanVienBUS();
        nv.docdsnv();
        QuyenTaiKhoanBUS qtk=new QuyenTaiKhoanBUS();
        qtk.docdsqtk();
        ChucNangBUS cn=new ChucNangBUS();
        cn.docdscn();
        ChiTietQuyenBUS ctq=new ChiTietQuyenBUS();
        ctq.docdsctq();
        HangHoaBUS hh = new HangHoaBUS();
        hh.docdshh();
        NhaCungCapBUS ncc = new NhaCungCapBUS();
        ncc.docdsncc();
        PhieuNhapHangBUS pnh = new PhieuNhapHangBUS();
        pnh.docdspnh();
        ChiTietPhieuNhapBUS ctpnh = new ChiTietPhieuNhapBUS();
        ctpnh.docalldsctpnh();
        KhachHangBUS kh=new KhachHangBUS();
        kh.docdskh();
        DichVuBUS dv=new DichVuBUS();
        dv.docdsdv();
        KhuyenMaiDichVuBUS kmdv= new KhuyenMaiDichVuBUS();
        kmdv.docdskmdv();
        ChiTietKMDichVuBUS ctkmdv= new ChiTietKMDichVuBUS();
        ctkmdv.docdsctkmdv();
        KhuyenMaiPhongBUS kmp= new KhuyenMaiPhongBUS();
        kmp.docdskmp();
        ChiTietKMPhongBUS ctkmp = new ChiTietKMPhongBUS();
        ctkmp.docdsctkmp();
        LoaiPhongBUS lp = new LoaiPhongBUS();
        lp.docdslp();
        PhongBUS p = new PhongBUS();
        p.docdsp();
        HoaDonBUS hd=new HoaDonBUS();
        hd.docdshd();
        ChiTietHoaDonBUS cthd=new ChiTietHoaDonBUS();
        cthd.docdscthd();
        PhieuDatPhongBUS pdp=new PhieuDatPhongBUS();
        pdp.docdspdp();
        ChiTietPhieuDatPhongBUS ctpdp=new ChiTietPhieuDatPhongBUS();
        ctpdp.docdsctpdp();
    }
    public static void main(String[] args) {
        DangNhapGUI p=new DangNhapGUI();
    }
}
