/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.ChiTietQuyenBUS;
import BUS.ChucNangBUS;
import BUS.NhanVienBUS;
import BUS.TaiKhoanBUS;
import DTO.ChiTietQuyenDTO;
import DTO.ChucNangDTO;
import DTO.NhanVienDTO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author minhd
 */
public class QuanLyGUI {
    protected JFrame f;
    protected JPanel pThongtin;
    protected JPanel pChucnang;
    protected JPanel pNoidung; // Content
    
    protected JLabel lbHinhlogo;
    protected JLabel lbTieude;
    protected JLabel lbTenNV;
    protected JLabel lbSuaThongtin;
    protected JLabel lbDangXuat;
    
    protected JLabel[] lbChucnang;
    public QuanLyGUI(String manv,String maq){
        KhoitaoFrame(manv,maq);
    }
    public void KhoitaoFrame(String manv,String maq){
        f=new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setBounds(0,0,1300,750);
        f.setPreferredSize(new Dimension(1300,750));
        f.setLayout(new BorderLayout());
        f.setUndecorated(true);
        
        //---------------------------------Hiện giữa màn hình-----------------------------//
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        f.setLocation((int)d.getWidth()/2 - (int)f.getPreferredSize().getWidth()/2,
                      (int)d.getHeight()/2 - (int)f.getPreferredSize().getHeight()/2);
        //--------------------------------------------------------------------------------//
        
        pThongtin=new JPanel();
        pThongtin.setPreferredSize(new Dimension(0, 100));
        pThongtin.setBackground(new Color(18,18,18));
        pThongtin.setLayout(null);
        
        f.add(pThongtin, BorderLayout.NORTH);
        
        pChucnang=new JPanel();
        pChucnang.setPreferredSize(new Dimension(300, 0));
        pChucnang.setBackground(new Color(36,36,36));
        pChucnang.setLayout(new FlowLayout(1, 20, 0));
        
        f.add(pChucnang, BorderLayout.WEST);
        
        pNoidung=new JPanel();
        pNoidung.setBackground(Color.WHITE);
        pNoidung.setLayout(null);
        
        f.add(pNoidung, BorderLayout.CENTER);
        
        //-----------------Gọi hàm----------------//
        Thongtin(manv,maq);
        DanhSachCacChucNang(maq);
        //----------------------------------------//
        
        f.setVisible(true);
    }
    
    public void Thongtin(String manv,String maq){
        ImageIcon imgLogo=new ImageIcon("src/img/Logo.png");
        lbHinhlogo=new JLabel();
        lbHinhlogo.setIcon(imgLogo);
        lbHinhlogo.setBounds(80, 0, 125, 100);
        
        pThongtin.add(lbHinhlogo);
        
        lbTieude=new JLabel("QUẢN LÝ KHÁCH SẠN");
        lbTieude.setBounds(450, 25, 300, 50);
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        lbTieude.setFont(new Font("Times New Roman",Font.ITALIC,30));
        lbTieude.setForeground(Color.WHITE);
        
        pThongtin.add(lbTieude);
        
        NhanVienDTO nv=new NhanVienDTO();
        NhanVienBUS nvbus=new NhanVienBUS();
        if(NhanVienBUS.dsnv==null){
            NhanVienBUS nv1=new NhanVienBUS();
            nv1.docdsnv();
        }
        nv=nvbus.LayThongtin1NV(manv);
        
        lbTenNV=new JLabel();
        lbTenNV.setFont(new Font("Times New Roman",Font.BOLD,16));
        lbTenNV.setText("Xin chào, "+nv.getHo()+" "+nv.getTen());
        lbTenNV.setBounds(1000, 20, 250, 20);
        lbTenNV.setForeground(Color.WHITE);
        
        pThongtin.add(lbTenNV);
        
        lbSuaThongtin=new JLabel();
        lbSuaThongtin.setFont(new Font("Times New Roman",0,16));
        lbSuaThongtin.setText("Đổi mật khẩu");
        lbSuaThongtin.setForeground(Color.WHITE);
        lbSuaThongtin.setBounds(1030, 50, 100, 20);
        
        pThongtin.add(lbSuaThongtin);
        
        JLabel lb1=new JLabel("|");
        lb1.setFont(new Font("Times New Roman",0,16));
        lb1.setForeground(Color.WHITE);
        lb1.setBounds(1130, 50, 10, 20);
        
        pThongtin.add(lb1);
        
        lbDangXuat=new JLabel();
        lbDangXuat.setFont(new Font("Times New Roman",0,16));
        lbDangXuat.setText("Đăng xuất");
        lbDangXuat.setForeground(Color.WHITE);
        lbDangXuat.setBounds(1150, 50, 100, 20);
        
        pThongtin.add(lbDangXuat);
        
        ChucNangPhanThongTin(manv,maq);
    }
    
    public void DoiMatKhau(String manv,String maq){
        JFrame fDoiMK=new JFrame();
        fDoiMK.setLayout(null);
        fDoiMK.setBounds(0, 0, 350, 350);
        fDoiMK.setPreferredSize(new Dimension(350,350));
        
        //---------------------------------Hiện giữa màn hình-----------------------------//
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        fDoiMK.setLocation((int)d.getWidth()/2 - (int)fDoiMK.getPreferredSize().getWidth()/2,
                      (int)d.getHeight()/2 - (int)fDoiMK.getPreferredSize().getHeight()/2);
        //--------------------------------------------------------------------------------//
        
        JLabel lbDoiMK=new JLabel("Đổi mật khẩu");
        lbDoiMK.setForeground(Color.WHITE);
        lbDoiMK.setBackground(new Color(36,36,36));
        lbDoiMK.setBounds(0, 0, 350, 35);
        lbDoiMK.setHorizontalAlignment(JLabel.CENTER);
        lbDoiMK.setFont(new Font("Times New Roman",Font.BOLD,25));
        lbDoiMK.setOpaque(true);
        
        fDoiMK.add(lbDoiMK);
        
        JLabel lbMKCu=new JLabel("Mật khẩu cũ:");
        lbMKCu.setForeground(Color.BLACK);
        lbMKCu.setFont(new Font("Times New Roman",0,18));
        lbMKCu.setBounds(40, 65, 100, 20);
        
        fDoiMK.add(lbMKCu);
        
        JPasswordField txMKCu=new JPasswordField();
        txMKCu.setBackground(Color.WHITE);
        txMKCu.setForeground(Color.BLACK);
        txMKCu.setBounds(40, 95, 270, 25);
        txMKCu.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        txMKCu.setFont(new Font("Times New Roman",0,18));
        
        fDoiMK.add(txMKCu);
        
        JLabel lbMKMoi=new JLabel("Mật khẩu mới:");
        lbMKMoi.setForeground(Color.BLACK);
        lbMKMoi.setFont(new Font("Times New Roman",0,18));
        lbMKMoi.setBounds(40, 130, 120, 20);
        
        fDoiMK.add(lbMKMoi);
        
        JPasswordField txMKMoi=new JPasswordField();
        txMKMoi.setBackground(Color.WHITE);
        txMKMoi.setForeground(Color.BLACK);
        txMKMoi.setBounds(40, 160, 270, 25);
        txMKMoi.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        txMKMoi.setFont(new Font("Times New Roman",0,18));
        
        fDoiMK.add(txMKMoi);
        
        JLabel lbXacNhanMKMoi=new JLabel("Xác nhận lại mật khẩu:");
        lbXacNhanMKMoi.setForeground(Color.BLACK);
        lbXacNhanMKMoi.setFont(new Font("Times New Roman",0,18));
        lbXacNhanMKMoi.setBounds(40, 195, 200, 20);
        
        fDoiMK.add(lbXacNhanMKMoi);
        
        JPasswordField txXacNhanMKMoi=new JPasswordField();
        txXacNhanMKMoi.setBackground(Color.WHITE);
        txXacNhanMKMoi.setForeground(Color.BLACK);
        txXacNhanMKMoi.setBounds(40, 225, 270, 25);
        txXacNhanMKMoi.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        txXacNhanMKMoi.setFont(new Font("Times New Roman",0,18));
        
        fDoiMK.add(txXacNhanMKMoi);
        
        JButton btHoanThanh=new JButton("Hoàn thành");
        btHoanThanh.setBackground(new Color(36,36,36));
        btHoanThanh.setForeground(Color.WHITE);
        btHoanThanh.setFont(new Font("Times New Roman",0,18));
        btHoanThanh.setBounds(40, 270, 120, 25);
        btHoanThanh.setFocusable(false);
        btHoanThanh.setBorderPainted(false);
        
        fDoiMK.add(btHoanThanh);
        
        JButton btThoat=new JButton("Thoát");
        btThoat.setBackground(new Color(36,36,36));
        btThoat.setForeground(Color.WHITE);
        btThoat.setFont(new Font("Times New Roman",0,18));
        btThoat.setBounds(190, 270, 120, 25);
        btThoat.setFocusable(false);
        btThoat.setBorderPainted(false);
        
        fDoiMK.add(btThoat);
        
        btHoanThanh.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                char[] input1=txMKCu.getPassword();
                String mkcu=String.valueOf(input1);
                char[] input2=txMKMoi.getPassword();
                String mkmoi=String.valueOf(input2);
                char[] input3=txXacNhanMKMoi.getPassword();
                String xacnhanmkmoi=String.valueOf(input3);
                TaiKhoanBUS tkbus=new TaiKhoanBUS();
                if (TaiKhoanBUS.dstk==null){
                    tkbus.docdstk();
                }
                if (tkbus.KtraDoiMatKhau(mkcu, mkmoi, xacnhanmkmoi, manv, maq)==1){
                    if (tkbus.DoiMatKhau(mkmoi, manv, maq)==1){
                        JOptionPane.showMessageDialog(fDoiMK, "Đổi mật khẩu thành công, vui lòng đăng nhập lại");
                        fDoiMK.setVisible(false);
                        f.setVisible(false);
                        DangNhapGUI dn=new DangNhapGUI();                        
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
                fDoiMK.setVisible(false);
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
        
        fDoiMK.setVisible(true);
    }
    
    public void ChucNangPhanThongTin(String manv,String maq){
        lbSuaThongtin.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                DoiMatKhau(manv,maq);
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                lbSuaThongtin.setForeground(Color.CYAN);
                lbSuaThongtin.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                lbSuaThongtin.setForeground(Color.WHITE);
            }
        });
        
        
        lbDangXuat.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                DangNhapGUI dn=new DangNhapGUI();
                f.setVisible(false);
//                    System.exit(0);
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                lbDangXuat.setForeground(Color.red);
                lbDangXuat.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                lbDangXuat.setForeground(Color.WHITE);
            }
        });
    }
    
    public void DanhSachCacChucNang(String maq){
        ChucNangBUS cnbus=new ChucNangBUS();
        if (ChucNangBUS.dscn==null){
            cnbus.docdscn();
        }
        ChiTietQuyenBUS ctqbus=new ChiTietQuyenBUS();
        if (ChiTietQuyenBUS.dsctq==null){
            ctqbus.docdsctq();
        }
        ArrayList<ChiTietQuyenDTO> ctq1=new ArrayList<>();
        ctq1=ctqbus.LayChiTietQuyen(maq);
        ArrayList<ChucNangDTO> cn1=new ArrayList<>();
        
        lbChucnang=new JLabel[ctq1.size()];
        for (ChucNangDTO k : ChucNangBUS.dscn){
            for (ChiTietQuyenDTO k1 : ctq1){
                if (k.getMaChucNang().equals(k1.getMaChucNang()))
                    cn1.add(k);
            }
        }
        int dodai=(cn1.size()*75)+80;
        JPanel panel=new JPanel();
        panel.setLayout(new FlowLayout(1, 20, 0));
        panel.setBounds(0, 0, 300, dodai);
        panel.setPreferredSize(new Dimension(300,dodai));
        panel.setBackground(new Color(36,36,36));
        panel.setOpaque(true);

        
        int i=0;
        for (ChucNangDTO k : cn1){
            ImageIcon iconchucnang=new ImageIcon("src/img/"+k.getHinhanh());
            lbChucnang[i]=new JLabel();
            lbChucnang[i].setText(k.getTenChucNang());
            lbChucnang[i].setIcon(iconchucnang);
            lbChucnang[i].setIconTextGap(30);
            lbChucnang[i].setBounds(0,0,300,75);
            lbChucnang[i].setPreferredSize(new Dimension(300,75));
            lbChucnang[i].setForeground(Color.WHITE);
            lbChucnang[i].setBackground(new Color(36,36,36));
            lbChucnang[i].setFont(new Font("Times New Roman",0,18));
            lbChucnang[i].setOpaque(true);
            panel.add(lbChucnang[i]);
            i++;
        }
        JScrollPane scr=new JScrollPane(panel);
        scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scr.getVerticalScrollBar().setUnitIncrement(10);
        scr.setBounds(0, 0, 300, 750);
        scr.setPreferredSize(new Dimension(300,750));
        
        pChucnang.add(scr);
        
        ChucNangPhanDanhSachChucNang();
    }
            
    public void ChucNangPhanDanhSachChucNang(){
        for (JLabel lb1 : lbChucnang){
            lb1.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    ChonChucNang(lb1.getText());
                    for (JLabel lb2 : lbChucnang){
                        if (e.getSource()==lb2)
                            lb2.setForeground(Color.red);
                        else
                            lb2.setForeground(Color.WHITE);
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
                    lb1.setBackground(Color.DARK_GRAY);
                    lb1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent arg0) {
                    lb1.setBackground(new Color(36,36,36));
                }
            });
        }
    }
    
    public void ChonChucNang(String s){
        ChucNangDTO cn=new ChucNangDTO();
        ChucNangBUS cnbus=new ChucNangBUS();
        cn=cnbus.Lay1ChucNang(s);
        int k=cn.getStt();
        switch(k){
            case 1: // Quản lý phòng
                PhongGUI phong=new PhongGUI();
                JPanel phg=new JPanel();
                phg=phong.KhoiTaoPanel(1000,650);
                pNoidung.removeAll();
                pNoidung.add(phg);
                pNoidung.repaint();
                pNoidung.revalidate();
                break;
            case 2: // Quản lý phiếu đặt phòng
                PhieuDatPhongGUI p2=new PhieuDatPhongGUI();
                JPanel pdp=new JPanel();
                pdp=p2.KhoiTaoPanel(1000,650);
                pNoidung.removeAll();
                pNoidung.add(pdp);
                pNoidung.repaint();
                pNoidung.revalidate();
                break;
            case 3: // Quản lý hóa đơn
                HoaDonGUI hdgui=new HoaDonGUI();
                JPanel hd=new JPanel();
                hd=hdgui.KhoiTaoPanel(1000,650);
                pNoidung.removeAll();
                pNoidung.add(hd);
                pNoidung.repaint();
                pNoidung.revalidate();
                break;
            case 4: // Khuyến mãi dịch vụ (Dũng)
                KhuyenMaiDichVuGUI p1=new KhuyenMaiDichVuGUI();
                JPanel kmdv=new JPanel();
                kmdv=p1.KhoiTaoPanel(1000,650);
                pNoidung.removeAll();
                pNoidung.add(kmdv);
                pNoidung.repaint();
                pNoidung.revalidate();
                break;
            case 5: // Khuyến mãi phòng (Dũng)
                KhuyenMaiPhongGUI p5=new KhuyenMaiPhongGUI();
                JPanel kmp=new JPanel();
                kmdv=p5.KhoiTaoPanel(1000,650);
                pNoidung.removeAll();
                pNoidung.add(kmdv);
                pNoidung.repaint();
                pNoidung.revalidate();
                break;
            case 6: // Quản lý khách hàng (Duy)
                KhachHangGUI kh = new KhachHangGUI();
                JPanel kh1 = new JPanel();
                kh1 = kh.KhoiTaoPanel(1000, 650);
                pNoidung.removeAll();
                pNoidung.add(kh1);
                pNoidung.repaint();
                pNoidung.revalidate();
                break;
            case 7: // Quản lý nhân viên (Duy)
                NhanVienGUI nv = new NhanVienGUI();
                JPanel nv1 = new JPanel();
                nv1 = nv.KhoiTaoPanel(1000, 650);
                pNoidung.removeAll();
                pNoidung.add(nv1);
                pNoidung.repaint();
                pNoidung.revalidate();
                break;
            case 8: // Quản lý dịch vụ (Duy)
                DichVuGUI dv = new DichVuGUI();
                JPanel dv1 = new JPanel();
                dv1 = dv.KhoiTaoPanel(1000, 650);
                pNoidung.removeAll();
                pNoidung.add(dv1);
                pNoidung.repaint();
                pNoidung.revalidate();
                break;
            case 9: // Quản lý loại phòng (Dũng)
                LoaiPhongGUI p9=new LoaiPhongGUI();
                JPanel lp=new JPanel();
                kmdv=p9.KhoiTaoPanel(1000,650);
                pNoidung.removeAll();
                pNoidung.add(kmdv);
                pNoidung.repaint();
                pNoidung.revalidate();
                break;
            case 10: // Quản lý phiếu nhập hàng (Duyên)
                PhieuNhapHangGUI phieunhaphang=new PhieuNhapHangGUI();
                JPanel pnh=new JPanel();
                pnh=phieunhaphang.KhoiTaoPanel(1000,650);
                pNoidung.removeAll();
                pNoidung.add(pnh);
                pNoidung.repaint();
                pNoidung.revalidate();
                break;
            case 11: // Quản lý hàng hóa (Duyên)
                HangHoaGUI hanghoa=new HangHoaGUI();
                JPanel hh=new JPanel();
                hh=hanghoa.KhoiTaoPanel(1000,650);
                pNoidung.removeAll();
                pNoidung.add(hh);
                pNoidung.repaint();
                pNoidung.revalidate();
                break;
            case 12: // Quản lý nhà cung cấp (Duyên)
                NhaCungCapGUI nhacungcap=new NhaCungCapGUI();
                JPanel ncc=new JPanel();
                ncc=nhacungcap.KhoiTaoPanel(1000,650);
                pNoidung.removeAll();
                pNoidung.add(ncc);
                pNoidung.repaint();
                pNoidung.revalidate();
                break;
            case 13: // Quản lý tài khoản (Đức)
                TaiKhoanGUI tkgui=new TaiKhoanGUI();
                JPanel tk=new JPanel();
                tk=tkgui.KhoiTaoPanel(1000,650);
                pNoidung.removeAll();
                pNoidung.add(tk);
                pNoidung.repaint();
                pNoidung.revalidate();
                break;
            case 14: // Phân quyền tài khoản (Đức)
                QuyenTaiKhoanGUI qtkgui=new QuyenTaiKhoanGUI();
                JPanel qtk=new JPanel();
                qtk=qtkgui.KhoiTaoPanel(1000,650);
                pNoidung.removeAll();
                pNoidung.add(qtk);
                pNoidung.repaint();
                pNoidung.revalidate();
                break;
            case 15: // Báo cáo thống kê (Đức)
                ThongKeGUI thongkegui=new ThongKeGUI();
                JPanel thongke=new JPanel();
                thongke=thongkegui.KhoiTaoPanel(1000,650);
                pNoidung.removeAll();
                pNoidung.add(thongke);
                pNoidung.repaint();
                pNoidung.revalidate();
                break;
            default:
                break;
        }
    }
    
//    public static void main(String[] args) {
//        QuanLyGUI p=new QuanLyGUI("NV0001","QL");
//        
//    }
}
