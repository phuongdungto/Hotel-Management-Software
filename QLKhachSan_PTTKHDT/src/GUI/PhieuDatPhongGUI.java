/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.ChiTietKMPhongBUS;
import BUS.ChiTietPhieuDatPhongBUS;
import BUS.HoaDonBUS;
import BUS.KhachHangBUS;
import BUS.LoaiPhongBUS;
import BUS.PhieuDatPhongBUS;
import BUS.PhongBUS;
import BUS.Xulydulieu;
import BUS.iconBUS;
import DTO.ChiTietKMPhongDTO;
import DTO.ChiTietPhieuDatPhongDTO;
import DTO.HoaDonDTO;
import DTO.KhachHangDTO;
import DTO.LoaiPhongDTO;
import DTO.PhieuDatPhongDTO;
import DTO.PhongDTO;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Color;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author T P Dung
 */
public class PhieuDatPhongGUI {

    protected JLabel lbTieude;
    protected JPanel pnPDP;
    protected JComboBox cbTimkiem;
    protected JTable tbdspdp;
    protected DefaultTableModel model;
    protected JTextField txMaKH;
    protected JTextField txMaNV;
    protected JDateChooser dayLapPDPtu;
    protected JDateChooser dayLapPDPden;
    protected JComboBox cbTinhtrang;
    protected JButton btThem;
    protected JButton btSua;
    protected JButton btXoa;
    protected JButton btTimkiem;
    protected JButton btCapnhat;
    protected JButton btXemchitiet;

    protected JButton btXemp;
    protected JButton btXemkh;

    protected JTextField txMaKHcn;
    protected JTextField txMaNVcn;
    protected JDateChooser dayThuecn;
    protected JDateChooser dayTracn;
    protected JDateChooser dayLapPDPcn;
    protected JTextField txSLPhongcn;
    protected JTextField txTongtienthuecn;
    protected JTextField txTongtienKMcn;

    protected JTextField txMaPDPct;
    protected JTextField txTinhtrangct;
    protected JTextField txSLPhongct;
    protected JDateChooser dayThuect;
    protected JDateChooser dayTract;
    protected JTextField Tongtienthuect;
    protected JTextField Tongtienkmct;

    protected JButton btThemcn;
    protected JButton btSuacn;

    protected JButton btSuacnct;

    protected JFrame fThemct;
    protected JFrame fThem;
    protected JFrame fSua;
    protected JTable tbdsctpdp;
    protected DefaultTableModel model1;

    protected Color colorButton = new Color(36, 36, 36);

    protected JTextField txMaphongct;
    protected JTextField txMakmpct;
    protected JTextField txSlnguoict;
    protected JTextField txTongtienct;
    protected JTextField txTienkmct;

    protected JButton btThemct;
    protected JButton btCapnhatct;
    protected JButton btXoact;
    protected JButton btHoantat;
    protected JButton btThoat;

    protected JTextField txMaKH1;
    protected JComboBox cbLoaiphong;

    protected JScrollPane scrdspdp;
    protected JScrollPane scrdspdp1;

    protected JTable tbp;
    protected JTable tbkh;
    int stt;
    iconBUS icon = new iconBUS();

    protected JComboBox cbTendv;

    public JPanel KhoiTaoPanel(int width, int height) {
        pnPDP = new JPanel();
        pnPDP.setLayout(null);
        pnPDP.setBounds(0, 0, width, height);
        pnPDP.setPreferredSize(new Dimension(width, height));
        pnPDP.setOpaque(true);
        if (PhieuDatPhongBUS.dspdp == null) {
            PhieuDatPhongBUS pdp = new PhieuDatPhongBUS();
            pdp.docdspdp();
        }
        if (PhongBUS.dsp == null) {
            PhongBUS pdp = new PhongBUS();
            pdp.docdsp();
        }
        if (KhachHangBUS.dskh == null) {
            KhachHangBUS kh = new KhachHangBUS();
            kh.docdskh();
        }
        if (HoaDonBUS.dshd == null) {
            HoaDonBUS hd = new HoaDonBUS();
            hd.docdshd();
        }
        if (ChiTietPhieuDatPhongBUS.dsctpdp == null) {
            ChiTietPhieuDatPhongBUS cthd = new ChiTietPhieuDatPhongBUS();
            cthd.docdsctpdp();
        }
        init();
        return pnPDP;
    }

    public void init() {
        JLabel lbTimkiem = new JLabel();
        lbTimkiem.setBounds(20, 50, 100, 30);
        lbTimkiem.setText("Tìm Kiếm");
        lbTimkiem.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lbTimkiem.setForeground(Color.BLACK);
        lbTimkiem.setHorizontalAlignment(JLabel.LEFT);
        pnPDP.add(lbTimkiem);

        String sp[] = {"Chọn kiểm tìm kiếm", "Theo KH","Theo NV" ,"Tìm kiếm nâng cao"};

        cbTimkiem = new JComboBox(sp);
        cbTimkiem.setSelectedIndex(0);
        cbTimkiem.setFont(new Font("Arial", 0, 16));
        cbTimkiem.setBounds(120, 50, 270, 30);
        pnPDP.add(cbTimkiem);

        String sp1[] = {"Chọn tình trạng", "Đã nhận phòng", "Đã thanh toán", "Chưa nhận phòng"};
        cbTinhtrang = new JComboBox(sp1);
        cbTinhtrang.setSelectedIndex(0);
        cbTinhtrang.setFont(new Font("Arial", 0, 16));
        cbTinhtrang.setBounds(400, 50, 150, 30);
        pnPDP.add(cbTinhtrang);

        JLabel lbKH = new JLabel();
        lbKH.setBounds(20, 100, 100, 30);
        lbKH.setText("Mã KH:");
        lbKH.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lbKH.setForeground(Color.BLACK);
        lbKH.setHorizontalAlignment(JLabel.LEFT);
        pnPDP.add(lbKH);

        txMaKH = new JTextField();
        txMaKH.setBounds(120, 100, 150, 30);
        pnPDP.add(txMaKH);

        JLabel lbNV = new JLabel();
        lbNV.setBounds(330, 100, 70, 30);
        lbNV.setText("Mã NV:");
        lbNV.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lbNV.setForeground(Color.BLACK);
        lbNV.setHorizontalAlignment(JLabel.LEFT);
        pnPDP.add(lbNV);

        txMaNV = new JTextField();
        txMaNV.setBounds(400, 100, 150, 30);
        pnPDP.add(txMaNV);

        JLabel lbNgayLapPDPtu = new JLabel();
        lbNgayLapPDPtu.setBounds(20, 150, 130, 30);
        lbNgayLapPDPtu.setText("Ngày Lập từ:");
        lbNgayLapPDPtu.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lbNgayLapPDPtu.setForeground(Color.BLACK);
        lbNgayLapPDPtu.setHorizontalAlignment(JLabel.LEFT);
        pnPDP.add(lbNgayLapPDPtu);

        dayLapPDPtu = new JDateChooser();
        dayLapPDPtu.setBounds(140, 150, 150, 30);
        dayLapPDPtu.setDateFormatString("dd-MM-yyyy");
        JTextFieldDateEditor editor3;
        editor3 = (JTextFieldDateEditor) dayLapPDPtu.getDateEditor();
        editor3.setEditable(false);
        pnPDP.add(dayLapPDPtu);
        
        JLabel lbNgayLapPDPden = new JLabel();
        lbNgayLapPDPden.setBounds(330, 150, 130, 30);
        lbNgayLapPDPden.setText("Ngày Lập đến:");
        lbNgayLapPDPden.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lbNgayLapPDPden.setForeground(Color.BLACK);
        lbNgayLapPDPden.setHorizontalAlignment(JLabel.LEFT);
        pnPDP.add(lbNgayLapPDPden);

        dayLapPDPden = new JDateChooser();
        dayLapPDPden.setBounds(450, 150, 150, 30);
        dayLapPDPden.setDateFormatString("dd-MM-yyyy");
        JTextFieldDateEditor editor4;
        editor4 = (JTextFieldDateEditor) dayLapPDPden.getDateEditor();
        editor4.setEditable(false);
        pnPDP.add(dayLapPDPden);

        lbTieude = new JLabel();
        lbTieude.setText("Phiếu đặt phòng");
        lbTieude.setFont(new Font("Times New Roman", Font.BOLD, 26));
        lbTieude.setBounds(300, 10, 280, 30);
        lbTieude.setForeground(Color.BLACK);
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        pnPDP.add(lbTieude);

        ImageIcon iconthem = new ImageIcon(icon.iconthem);
        btThem = new JButton("Thêm", iconthem);
        btThem.setBounds(680, 210, 96, 35);
        btThem.setBackground(colorButton);
        btThem.setForeground(Color.WHITE);
        btThem.setBorderPainted(false);
        btThem.setFocusPainted(false);
        pnPDP.add(btThem);

        ImageIcon icontimkiem = new ImageIcon(icon.icontimkiem);
        btTimkiem = new JButton("Tìm kiếm", icontimkiem);
        btTimkiem.setBounds(560, 50, 120, 35);
        btTimkiem.setBackground(colorButton);
        btTimkiem.setForeground(Color.WHITE);
        btTimkiem.setBorderPainted(false);
        btTimkiem.setFocusPainted(false);
        pnPDP.add(btTimkiem);

        ImageIcon iconsua = new ImageIcon(icon.iconsua);
        btSua = new JButton("Sửa", iconsua);
        btSua.setBounds(790, 210, 95, 35);
        btSua.setBackground(colorButton);
        btSua.setForeground(Color.WHITE);
        btSua.setBorderPainted(false);
        btSua.setFocusPainted(false);
        pnPDP.add(btSua);

        ImageIcon iconxoa = new ImageIcon(icon.iconxoa);
        btXoa = new JButton("Xóa", iconxoa);
        btXoa.setBounds(900, 210, 95, 35);
        btXoa.setBackground(colorButton);
        btXoa.setForeground(Color.WHITE);
        btXoa.setBorderPainted(false);
        btXoa.setFocusPainted(false);
        pnPDP.add(btXoa);

        ImageIcon iconxemchitiet = new ImageIcon(icon.iconxem);
        btXemchitiet = new JButton("Xem chi tiết", iconxemchitiet);
        btXemchitiet.setBounds(480, 210, 180, 35);
        btXemchitiet.setBackground(colorButton);
        btXemchitiet.setForeground(Color.WHITE);
        btXemchitiet.setBorderPainted(false);
        btXemchitiet.setFocusPainted(false);
        pnPDP.add(btXemchitiet);

        ImageIcon iconcapnhat = new ImageIcon(icon.iconcapnhat);
        btCapnhat = new JButton("Cập nhật", iconcapnhat);
        btCapnhat.setBounds(50, 605, 140, 35);
        btCapnhat.setBackground(colorButton);
        btCapnhat.setForeground(Color.WHITE);
        btCapnhat.setBorderPainted(false);
        btCapnhat.setFocusPainted(false);
        pnPDP.add(btCapnhat);
        
        txMaKH.setEditable(false);
        txMaNV.setEditable(false);
        dayLapPDPtu.setEnabled(false);
        dayLapPDPden.setEnabled(false);
        cbTinhtrang.setEnabled(false);
        Khoitaoscroll();

        CacNutChucNang();
    }

    public JTable Danhsachphieudatphong(ArrayList<PhieuDatPhongDTO> arr) {
//        tbdskmdv=new JTable();
//        tbdskmdv.getTableHeader().setBackground(new Color(3,169,244));
//        tbdskmdv.getTableHeader().setForeground(Color.WHITE);
//        tbdskmdv.getTableHeader().setFont(new Font("Times New Roman",0,18));
//        tbdskmdv.getTableHeader().setPreferredSize(new Dimension(0,30));
//        tbdskmdv.setFont(new Font("Times New Roman",0,18));
//        tbdskmdv.setForeground(Color.BLACK);

        tbdspdp = new JTable();
        tbdspdp.setRowHeight(25);
        tbdspdp.getTableHeader().setBackground(colorButton);
        tbdspdp.getTableHeader().setForeground(Color.WHITE);
        tbdspdp.getTableHeader().setFont(new Font("Times New Roman", 0, 18));
        tbdspdp.getTableHeader().setPreferredSize(new Dimension(0, 30));
        tbdspdp.setFont(new Font("Times New Roman", 0, 18));
        tbdspdp.setForeground(Color.BLACK);

        model = new DefaultTableModel();
        Vector cottieude = new Vector();
        cottieude.add("Stt");
        cottieude.add("Mã PDP");
        cottieude.add("Mã KH");
        cottieude.add("Mã NV");
        cottieude.add("Ngày lập PDP");
        cottieude.add("Ngày thuê");
        cottieude.add("Ngày trả");
        cottieude.add("Tình trạng");
        Xulydulieu xl = new Xulydulieu();
        if (model.getRowCount() == 0) {
            model = new DefaultTableModel(cottieude, 0);
        }
        int i = 0;
        for (PhieuDatPhongDTO k : arr) {
            i++;
            Vector dong = new Vector();
            dong.add(i);
            dong.add(k.getMaPDP());
            dong.add(k.getMaKH());
            dong.add(k.getMaNV());
            dong.add(xl.chuyendate(k.getNgayLapPDP()));
            dong.add(xl.chuyendate(k.getNgayThue()));
            dong.add(xl.chuyendate(k.getNgayTra()));
            if (k.getTinhTrang() == 1) {
                dong.add("Đã nhận phòng");
            } else if (k.getTinhTrang() == 2) {
                dong.add("Đã thanh toán");
            } else {
                dong.add("Chưa nhận phòng");
            }
            model.addRow(dong);
        }
        tbdspdp.setModel(model);
        tbdspdp.getColumnModel().getColumn(0).setMaxWidth(50);
//        tbdskmdv.getColumnModel().getColumn(2).setMaxWidth(250);
//        tbdskmdv.getColumnModel().getColumn(4).setMinWidth(150);
//        tbdskmdv.getColumnModel().getColumn(5).setMinWidth(150);

        //--------------------Căn giữa trong bảng-----------------------//
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        tbdspdp.setDefaultRenderer(tbdspdp.getColumnClass(0), center);
        tbdspdp.updateUI();
        tbdspdp.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                int i = tbdspdp.getSelectedRow();
                String s = tbdspdp.getModel().getValueAt(i, 1).toString();
                String s1 = tbdspdp.getModel().getValueAt(i, 2).toString();
                String s2 = tbdspdp.getModel().getValueAt(i, 3).toString();
                String s3 = tbdspdp.getModel().getValueAt(i, 4).toString();
                String s4 = tbdspdp.getModel().getValueAt(i, 5).toString();
                String s5 = tbdspdp.getModel().getValueAt(i, 6).toString();

            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        return tbdspdp;
        //--------------------------------------------------------------//
    }

    public void Khoitaoscroll() {
        tbdspdp = Danhsachphieudatphong(PhieuDatPhongBUS.dspdp);
        scrdspdp = new JScrollPane(tbdspdp);
        scrdspdp.setViewportView(tbdspdp);
        scrdspdp.setBounds(10, 250, 990, 350); // Tùy mỗi người
        pnPDP.add(scrdspdp);
    }

    public void JFrameChitiet(PhieuDatPhongDTO p, int x) {
        System.out.println("Hello "+p.getTinhTrang());
        fThemct = new JFrame();
        fThemct.setBounds(450, 100, 830, 650);
        fThemct.setLayout(null);
        fThemct.setBackground(Color.WHITE);

        JPanel pnlchitietpdp = new JPanel();
        pnlchitietpdp.setLayout(null);
        pnlchitietpdp.setBounds(520, 200, 260, 350);
        pnlchitietpdp.setBackground(Color.WHITE);
        pnlchitietpdp.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

        //fThemct.add(pnlchitietpdp);
        JLabel tieude = new JLabel();
        tieude.setText("Phiếu đặt phòng");
        tieude.setFont(new Font("Times New Roman", Font.BOLD, 23));
        tieude.setBounds(0, 0, 830, 30);
        tieude.setForeground(Color.WHITE);
        tieude.setBackground(colorButton);
        tieude.setOpaque(true);
        tieude.setHorizontalAlignment(JLabel.CENTER);
        fThemct.add(tieude);

        JLabel tieude1 = new JLabel();
        tieude1.setText("Chi tiết phiếu đặt phòng");
        tieude1.setFont(new Font("Times New Roman", Font.BOLD, 23));
        tieude1.setBounds(0, 150, 830, 30);
        tieude1.setForeground(Color.WHITE);
        tieude1.setBackground(colorButton);
        tieude1.setOpaque(true);
        tieude1.setHorizontalAlignment(JLabel.CENTER);
        fThemct.add(tieude1);

        JLabel lbMapdp = new JLabel();
        lbMapdp.setText("Mã PDP");
        lbMapdp.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lbMapdp.setBounds(10, 40, 100, 20);
        lbMapdp.setForeground(Color.BLACK);
        lbMapdp.setHorizontalAlignment(JLabel.LEFT);
        fThemct.add(lbMapdp);

        txMaPDPct = new JTextField();
        txMaPDPct.setText(p.getMaPDP());
        txMaPDPct.setBounds(110, 40, 120, 25);
        txMaPDPct.setFont(new Font("Times New Roman", Font.BOLD, 16));
        txMaPDPct.setForeground(Color.RED);
        txMaPDPct.setHorizontalAlignment(JTextField.CENTER);
        txMaPDPct.setBorder(BorderFactory.createLoweredBevelBorder());
        txMaPDPct.setEditable(false);
        fThemct.add(txMaPDPct);

        JLabel lbslPhong = new JLabel();
        lbslPhong.setText("Số lượng phòng:");
        lbslPhong.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lbslPhong.setBounds(250, 40, 130, 20);
        lbslPhong.setForeground(Color.BLACK);
        lbslPhong.setHorizontalAlignment(JLabel.LEFT);
        fThemct.add(lbslPhong);

        txSLPhongct = new JTextField();
        txSLPhongct.setText(String.valueOf(p.getSLPhong()));
        txSLPhongct.setBounds(370, 40, 80, 25);
        txSLPhongct.setFont(new Font("Times New Roman", Font.BOLD, 16));
        txSLPhongct.setForeground(Color.RED);
        txSLPhongct.setHorizontalAlignment(JTextField.CENTER);
        txSLPhongct.setBorder(BorderFactory.createLoweredBevelBorder());
        fThemct.add(txSLPhongct);

        JLabel lbNgaythue = new JLabel();
        lbNgaythue.setText("Ngày thuê:");
        lbNgaythue.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lbNgaythue.setBounds(10, 100, 100, 20);
        lbNgaythue.setForeground(Color.BLACK);
        lbNgaythue.setHorizontalAlignment(JLabel.LEFT);
        fThemct.add(lbNgaythue);

        dayThuect = new JDateChooser();
        dayThuect.setBounds(110, 100, 100, 30);
        dayThuect.setDateFormatString("dd-MM-yyyy");
        dayThuect.setFont(new Font("Times New Roman", Font.BOLD, 16));
        dayThuect.setBackground(Color.WHITE);
        JTextFieldDateEditor editor;
        editor = (JTextFieldDateEditor) dayThuect.getDateEditor();
        editor.setEditable(false);
        fThemct.add(dayThuect);

        JLabel lbNgaytra = new JLabel();
        lbNgaytra.setText("Ngày trả:");
        lbNgaytra.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lbNgaytra.setBounds(250, 100, 100, 20);
        lbNgaytra.setForeground(Color.BLACK);
        lbNgaytra.setHorizontalAlignment(JLabel.LEFT);
        fThemct.add(lbNgaytra);

        dayTract = new JDateChooser();
        dayTract.setBounds(350, 100, 100, 30);
        dayTract.setDateFormatString("dd-MM-yyyy");
        dayTract.setFont(new Font("Times New Roman", Font.BOLD, 16));
        dayTract.setBackground(Color.WHITE);
        JTextFieldDateEditor editor2;
        editor2 = (JTextFieldDateEditor) dayTract.getDateEditor();
        editor2.setEditable(false);
        fThemct.add(dayTract);
        
        try {
            Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(p.getNgayThue());
            Date date3 = new SimpleDateFormat("yyyy-MM-dd").parse(p.getNgayTra());
            dayThuect.setDate(date2);
            dayTract.setDate(date3);
        } catch (Exception ex) {
            ex.getStackTrace();
        }

        JLabel lbTongtien = new JLabel();
        lbTongtien.setText("Tổng tiền thuê:");
        lbTongtien.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lbTongtien.setBounds(470, 40, 120, 20);
        lbTongtien.setForeground(Color.BLACK);
        lbTongtien.setHorizontalAlignment(JLabel.LEFT);
        fThemct.add(lbTongtien);
        
        Xulydulieu xldl = new Xulydulieu();
        
        txTongtienct = new JTextField();
        txTongtienct.setText(xldl.ChuyenKieuTien(String.valueOf(p.getTongTienThue()))+"VND");
        txTongtienct.setBounds(590, 40, 200, 25);
        txTongtienct.setFont(new Font("Times New Roman", Font.BOLD, 16));
        txTongtienct.setForeground(Color.RED);
        txTongtienct.setHorizontalAlignment(JTextField.CENTER);
        txTongtienct.setBorder(BorderFactory.createLoweredBevelBorder());
        txTongtienct.setEditable(false);
        fThemct.add(txTongtienct);

        JLabel lbTongtienkm = new JLabel();
        lbTongtienkm.setText("Tổng tiền KM:");
        lbTongtienkm.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lbTongtienkm.setBounds(470, 100, 120, 20);
        lbTongtienkm.setForeground(Color.BLACK);
        lbTongtienkm.setHorizontalAlignment(JLabel.LEFT);
        fThemct.add(lbTongtienkm);

        Tongtienkmct = new JTextField();
        Tongtienkmct.setText(xldl.ChuyenKieuTien(String.valueOf(p.getTongTienKM()))+"VND");
        Tongtienkmct.setBounds(590, 100, 200, 25);
        Tongtienkmct.setFont(new Font("Times New Roman", Font.BOLD, 16));
        Tongtienkmct.setForeground(Color.RED);
        Tongtienkmct.setHorizontalAlignment(JTextField.CENTER);
        Tongtienkmct.setBorder(BorderFactory.createLoweredBevelBorder());
        Tongtienkmct.setEditable(false);
        fThemct.add(Tongtienkmct);

        JLabel lbMaphongct = new JLabel();
        lbMaphongct.setBounds(20, 200, 100, 30);
        lbMaphongct.setText("Mã phòng:");
        lbMaphongct.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lbMaphongct.setForeground(Color.BLACK);
        lbMaphongct.setHorizontalAlignment(JLabel.CENTER);
        fThemct.add(lbMaphongct);

        txMaphongct = new JTextField();
        txMaphongct.setBounds(130, 200, 80, 30);
        txMaphongct.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        txMaphongct.setBackground(Color.WHITE);
        txMaphongct.setEditable(false);
        txMaphongct.setBackground(Color.WHITE);
        fThemct.add(txMaphongct);

        ImageIcon iconchon = new ImageIcon(icon.iconchon);
        btXemp = new JButton(iconchon);
        btXemp.setBackground(colorButton);
        btXemp.setForeground(Color.WHITE);
        btXemp.setBorderPainted(false);
        btXemp.setFocusPainted(false);
        fThemct.add(btXemp);

        ImageIcon iconthem = new ImageIcon(icon.iconthem);
        btThemct = new JButton("Thêm", iconthem);
        btThemct.setBackground(colorButton);
        btThemct.setForeground(Color.WHITE);
        btThemct.setBorderPainted(false);
        btThemct.setFocusPainted(false);
        if (p.getTinhTrang() == 2) {
            btThemct.setBounds(0, 0, 0, 0);
        } else {
            btThemct.setBounds(70, 260, 96, 35);
        }
        fThemct.add(btThemct);

//        ImageIcon iconsua = new ImageIcon(icon.iconsua);
//        btSuact = new JButton("Sửa",iconsua);
//        btSuact.setBackground(colorButton);
//        btSuact.setForeground(Color.WHITE);
//        btSuact.setBorderPainted(false);
//        btSuact.setFocusPainted(false);
//        if(p.getTinhTrang()==1 || p.getTinhTrang()==2){
//            btSuact.setBounds(0,0,0,0);
//        }else{
//            btSuact.setBounds(126, 150, 95, 35);
//        }
//        fThemct.add(btSuact);
        ImageIcon iconxoa = new ImageIcon(icon.iconxoa);
        btXoact = new JButton("Xóa", iconxoa);
        btXoact.setBackground(colorButton);
        btXoact.setForeground(Color.WHITE);
        btXoact.setBorderPainted(false);
        btXoact.setFocusPainted(false);
        if (p.getTinhTrang() == 2) {
            btXoact.setBounds(0, 0, 0, 0);
        } else {
            btXoact.setBounds(176, 260, 95, 35);
        }
        fThemct.add(btXoact);
        
        ImageIcon iconcapnhat = new ImageIcon(icon.iconcapnhat);
        btCapnhatct = new JButton("Cập nhật",iconcapnhat);
        btCapnhatct.setBackground(colorButton);
        btCapnhatct.setForeground(Color.WHITE);
        btCapnhatct.setBorderPainted(false);
        btCapnhatct.setFocusPainted(false);
        //btCapnhatct.setBounds(400, 190, 115, 35);
        fThemct.add(btCapnhatct);

        model1 = new DefaultTableModel();
        tbdsctpdp = new JTable();

        tbdsctpdp.getTableHeader().setBackground(colorButton);
        tbdsctpdp.getTableHeader().setForeground(Color.WHITE);
        tbdsctpdp.getTableHeader().setFont(new Font("Times New Roman", 0, 18));
        tbdsctpdp.getTableHeader().setPreferredSize(new Dimension(0, 30));
        tbdsctpdp.setFont(new Font("Times New Roman", 0, 18));
        tbdsctpdp.setForeground(Color.BLACK);

        JScrollPane scr = new JScrollPane();
        scr.setViewportView(tbdsctpdp);
        scr.setBounds(70, 300, 690, 250); // Tùy mỗi người

        fThemct.add(scr);
        stt = 0;
        Vector cottieude = new Vector();
        cottieude.add("Mã Phòng");
        cottieude.add("Mã KMP");
        cottieude.add("SL Người");
        cottieude.add("Đơn giá");
        cottieude.add("Tiền KM");
//        cottieude.add("Tình trạng");
        if (model1.getRowCount() == 0) {
            model1 = new DefaultTableModel(cottieude, 0);
        }
        ChiTietPhieuDatPhongBUS dsctpdp = new ChiTietPhieuDatPhongBUS();
        if (ChiTietPhieuDatPhongBUS.dsctpdp == null) {
            dsctpdp.docdsctpdp();
        }
        for (ChiTietPhieuDatPhongDTO k : ChiTietPhieuDatPhongBUS.dsctpdp) {
            if (k.getMaPDP().equals(p.getMaPDP())) {
                Vector dong = new Vector();
                dong.add(k.getMaPhong());
                dong.add(k.getMaKMPhong());
                dong.add(String.valueOf(k.getSLNguoi()));
                dong.add((String.valueOf(k.getDonGia())));
                dong.add(String.valueOf(k.getTienKM()));
                model1.addRow(dong);
            }
        }
        tbdsctpdp.setModel(model1);
        //tbdsctpdp.getColumnModel().getColumn(0).setMaxWidth(50);
        tbdsctpdp.setRowHeight(25);
//        tbdskmdv.getColumnModel().getColumn(2).setMaxWidth(250);
//        tbdskmdv.getColumnModel().getColumn(4).setMinWidth(150);
//        tbdskmdv.getColumnModel().getColumn(5).setMinWidth(150);

        //--------------------Căn giữa trong bảng-----------------------//
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        tbdsctpdp.setDefaultRenderer(tbdsctpdp.getColumnClass(0), center);
        tbdsctpdp.updateUI();
        //--------------------------------------------------------------//
        btHoantat = new JButton("Hoàn tất");
        btHoantat.setBackground(colorButton);
        btHoantat.setForeground(Color.WHITE);
        btHoantat.setBorderPainted(false);
        btHoantat.setFocusPainted(false);
        fThemct.add(btHoantat);
        
        btThoat = new JButton("Thoát");
        btThoat.setBackground(colorButton);
        btThoat.setForeground(Color.WHITE);
        btThoat.setBorderPainted(false);
        btThoat.setFocusPainted(false);
//        fThemct.add(btThoat);
        if(x!=0){
            txSLPhongct.setEditable(false); 
            //dayThuect.setEnabled(false);
            //dayTract.setEnabled(false);
            btCapnhatct.setBounds(0,0,0,0);
            btThemct.setBounds(0,0,0,0);
            btXoact.setBounds(0,0,0,0);
            tieude1.setBounds(0, 250, 800, 30);
            btXemp.setBounds(0,0,0,0);
            lbMaphongct.setBounds(0,0,0,0);
            txMaphongct.setBounds(0,0,0,0);
            btThoat.setBounds(350,570, 115, 35);
            btHoantat.setBounds(0,0,0,0);
            scr.setBounds(70, 300, 690, 300);
            
            JLabel lbKH = new JLabel();
            lbKH.setText("Khách hàng:");
            lbKH.setFont(new Font("Times New Roman", Font.BOLD, 16));
            lbKH.setBounds(10, 160, 100, 20);
            lbKH.setForeground(Color.BLACK);
            lbKH.setHorizontalAlignment(JLabel.LEFT);
            fThemct.add(lbKH);

            JTextField txkh = new JTextField();
            txkh.setText(p.getMaKH());
            txkh.setBounds(110, 160, 100, 25);
            txkh.setFont(new Font("Times New Roman", Font.BOLD, 16));
            txkh.setForeground(Color.RED);
            txkh.setHorizontalAlignment(JTextField.CENTER);
            txkh.setBorder(BorderFactory.createLoweredBevelBorder());
            txkh.setEditable(false);
            fThemct.add(txkh);
            
            JLabel lbNV = new JLabel();
            lbNV.setText("Nhân viên:");
            lbNV.setFont(new Font("Times New Roman", Font.BOLD, 16));
            lbNV.setBounds(240, 160, 100, 20);
            lbNV.setForeground(Color.BLACK);
            lbNV.setHorizontalAlignment(JLabel.LEFT);
            fThemct.add(lbNV);

            JTextField txNV = new JTextField();
            txNV.setText(p.getMaNV());
            txNV.setBounds(350, 160, 100, 25);
            txNV.setFont(new Font("Times New Roman", Font.BOLD, 16));
            txNV.setForeground(Color.RED);
            txNV.setHorizontalAlignment(JTextField.CENTER);
            txNV.setBorder(BorderFactory.createLoweredBevelBorder());
            txNV.setEditable(false);
            fThemct.add(txNV);
            
            JLabel lbNgaylap = new JLabel();
            lbNgaylap.setText("Ngày lập:");
            lbNgaylap.setFont(new Font("Times New Roman", Font.BOLD, 16));
            lbNgaylap.setBounds(470, 160, 100, 20);
            lbNgaylap.setForeground(Color.BLACK);
            lbNgaylap.setHorizontalAlignment(JLabel.LEFT);
            fThemct.add(lbNgaylap);
            JDateChooser dayLap = new JDateChooser();
            dayLap.setBounds(580, 160, 100, 25);
            dayLap.setDateFormatString("dd-MM-yyyy");
            dayLap.setFont(new Font("Times New Roman", Font.BOLD, 16));
            dayLap.setBackground(Color.WHITE);
            JTextFieldDateEditor editor3;
            editor3 = (JTextFieldDateEditor) dayLap.getDateEditor();
            editor3.setEditable(false);
            fThemct.add(dayLap);
            try {
                Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(p.getNgayLapPDP());
                dayLap.setDate(date2);
            } catch (Exception ex) {
                ex.getStackTrace();
            }
            
            JLabel lbTinhTrang = new JLabel();
            lbTinhTrang.setText("Tình trạng:");
            lbTinhTrang.setFont(new Font("Times New Roman", Font.BOLD, 16));
            lbTinhTrang.setBounds(470, 220, 100, 20);
            lbTinhTrang.setForeground(Color.BLACK);
            lbTinhTrang.setHorizontalAlignment(JLabel.LEFT);
            fThemct.add(lbTinhTrang);
            
            JTextField txTinhtrang = new JTextField();
            if(p.getTinhTrang()==1){
                txTinhtrang.setText("Đã nhận phòng");
                txTinhtrang.setForeground(Color.GREEN);
            }else if(p.getTinhTrang()==2){
                txTinhtrang.setText("Đã thanh toán");
                txTinhtrang.setForeground(Color.GREEN);
            }else{
                txTinhtrang.setText("Chưa nhận phòng");
                txTinhtrang.setForeground(Color.RED);
            }
            txTinhtrang.setBounds(580, 220, 200, 30);
            txTinhtrang.setFont(new Font("Times New Roman", Font.BOLD, 16));
            txTinhtrang.setHorizontalAlignment(JTextField.CENTER);
            txTinhtrang.setBorder(BorderFactory.createLoweredBevelBorder());
            txTinhtrang.setEditable(false);
            fThemct.add(txTinhtrang);
            
        }else{
            btHoantat.setBounds(350,570, 115, 35);
            btXemp.setBounds(220,200,30,30);
            //btThoat.setBounds(0,0,0,0);
            btCapnhatct.setBounds(400, 190, 115, 35);
            btThemct.setBounds(70, 260, 96, 35);
            btXoact.setBounds(176, 260, 95, 35);
            dayThuect.setEnabled(true);
            txSLPhongct.setEditable(true); 
            dayTract.setEnabled(true);
        }
        CacNutChucNangChiTiet();
    }

    public void BangChonPhong() {
        JFrame fchon = new JFrame();
        fchon.setLayout(null);
        fchon.setBounds(0, 0, 550, 400);
        fchon.setPreferredSize(new Dimension(550, 400));

        //---------------------------------Hiện giữa màn hình-----------------------------//
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        fchon.setLocation((int) d.getWidth() / 2 - (int) fchon.getPreferredSize().getWidth() / 2,
                (int) d.getHeight() / 2 - (int) fchon.getPreferredSize().getHeight() / 2);
        //--------------------------------------------------------------------------------//

        JLabel lbTieude = new JLabel("Chọn phòng");
        lbTieude.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lbTieude.setForeground(Color.BLACK);
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        lbTieude.setBounds(150, 20, 250, 25);

        String sp[] = {"Chọn tìm theo", "Theo mã phòng", "Theo loại phòng"};

        JComboBox cbTimkiem = new JComboBox(sp);
        cbTimkiem.setSelectedIndex(0);
        cbTimkiem.setFont(new Font("Arial", 0, 16));
        cbTimkiem.setBounds(100, 50, 160, 30);
        fchon.add(cbTimkiem);

        JLabel lbMaphong = new JLabel();
        lbMaphong.setBounds(10, 100, 80, 30);
        lbMaphong.setText("Mã phòng");
        lbMaphong.setFont(new Font("Times New Roman", Font.BOLD, 13));
        lbMaphong.setForeground(Color.BLACK);
        lbMaphong.setHorizontalAlignment(JLabel.CENTER);
        fchon.add(lbMaphong);

        txMaKH1 = new JTextField();
        txMaKH1 = new JTextField();
        txMaKH1.setBounds(100, 100, 160, 30);
        fchon.add(txMaKH1);

        LoaiPhongBUS lp = new LoaiPhongBUS();
        if (LoaiPhongBUS.dslp == null) {
            lp.docdslp();
        }
        int size = LoaiPhongBUS.dslp.size() + 1;
        String[] s = new String[size];
        s[0] = "Loại phòng";
        int i = 1;
        for (LoaiPhongDTO p : LoaiPhongBUS.dslp) {
            s[i] = p.getMaLoai();
            i++;
        }
        cbLoaiphong = new JComboBox(s);
        cbLoaiphong.setSelectedIndex(0);
        cbLoaiphong.setFont(new Font("Arial", 0, 16));
        cbLoaiphong.setBounds(270, 100, 100, 30);
        fchon.add(cbLoaiphong);

        ImageIcon icontimkiem = new ImageIcon(icon.icontimkiem);
        JButton btTimkiem = new JButton(icontimkiem);
        btTimkiem.setBounds(270, 50, 30, 30);
        btTimkiem.setBackground(colorButton);
        btTimkiem.setForeground(Color.WHITE);
        btTimkiem.setBorderPainted(false);
        btTimkiem.setFocusPainted(false);
        fchon.add(btTimkiem);

        fchon.add(lbTieude);

        tbp = danhsachphong(PhongBUS.dsp);;
        JScrollPane sc = new JScrollPane();
        sc.setViewportView(tbp);
        sc.setBounds(10, 140, 520, 200);

        fchon.add(sc);
        txMaKH1.setEditable(false);
        cbLoaiphong.setEnabled(false);
        btTimkiem.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                PhongBUS p = new PhongBUS();
                if (cbTimkiem.getSelectedItem().equals("Theo mã phòng")) {
                    sc.removeAll();
                    ArrayList<PhongDTO> dstk = new ArrayList<>();
                    tbp = new JTable();
                    tbp = danhsachphong(p.timkiemtheoma(txMaKH1.getText()));
                    JScrollPane scrdsp1 = new JScrollPane(tbp);
                    scrdsp1.setBounds(0, 0, 520, 200);
                    sc.add(scrdsp1);
                    sc.repaint();
                    sc.revalidate();
                    fchon.add(sc);
                } else if (cbTimkiem.getSelectedItem().equals("Theo loại phòng")) {
                    sc.removeAll();
                    ArrayList<PhongDTO> dstk = new ArrayList<>();
                    tbp = new JTable();
                    String s = (String) cbLoaiphong.getSelectedItem();
                    if (s.equals("Loại")) {
                        s = "";
                    }
                    tbp = danhsachphong(p.timkiemtheoloai(s));
                    JScrollPane scrdsp1 = new JScrollPane(tbp);
                    scrdsp1.setBounds(0, 0, 520, 200);
                    sc.add(scrdsp1);
                    sc.repaint();
                    sc.revalidate();
                    fchon.add(sc);
                } else {
                    sc.removeAll();
                    ArrayList<PhongDTO> dstk = new ArrayList<>();
                    tbp = new JTable();
                    tbp = danhsachphong(PhongBUS.dsp);
                    JScrollPane scrdsp1 = new JScrollPane(tbp);
                    scrdsp1.setBounds(0, 0, 520, 200);
                    sc.add(scrdsp1);
                    sc.repaint();
                    sc.revalidate();
                    fchon.add(sc);
                }
                tbp.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent arg0) {
                        int i = tbp.getSelectedRow();
                        String maq = tbp.getModel().getValueAt(i, 0).toString();
                        txMaphongct.setText(maq);
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
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btTimkiem.setBackground(Color.DARK_GRAY);
                btTimkiem.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                btTimkiem.setBackground(new Color(36, 36, 36));
            }
        });
        tbp.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int i = tbp.getSelectedRow();
                String maq = tbp.getModel().getValueAt(i, 0).toString();
                txMaphongct.setText(maq);
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
        cbTimkiem.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.                
                if (cbTimkiem.getSelectedItem().equals("Theo mã phòng")) {
                    txMaKH1.setEditable(true);
                    cbLoaiphong.setEnabled(false);
                } else if (cbTimkiem.getSelectedItem().equals("Theo loại phòng")) {
                    txMaKH1.setEditable(false);
                    cbLoaiphong.setEnabled(true);
                } else {
                    txMaKH1.setEditable(false);
                    cbLoaiphong.setEnabled(false);
                }
            }
        });
        fchon.setVisible(true);
    }

    public JTable danhsachphong(ArrayList<PhongDTO> arr) {
        JTable tbp = new JTable();
        tbp.getTableHeader().setBackground(new Color(36, 36, 36));
        tbp.getTableHeader().setForeground(Color.WHITE);
        tbp.getTableHeader().setFont(new Font("Times New Roman", 0, 18));
        tbp.getTableHeader().setPreferredSize(new Dimension(0, 30));
        tbp.setFont(new Font("Times New Roman", 0, 18));
        tbp.setForeground(Color.BLACK);
        tbp.setRowHeight(25);

        DefaultTableModel md = new DefaultTableModel();
        Vector cot = new Vector();
        cot.add("Mã phòng");
        cot.add("Loại");
        cot.add("SL người");
        cot.add("Đơn giá");
        if (md.getRowCount() == 0) {
            md = new DefaultTableModel(cot, 0);
        }
        Xulydulieu xl = new Xulydulieu();
        for (PhongDTO k1 : arr) {
            if(k1.getTinhTrang()==1){
                Vector dong = new Vector();
                dong.add(k1.getMaPhong());
                dong.add(k1.getMaLoai());
                dong.add(k1.getSLNguoi());
                dong.add(xl.ChuyenKieuTien(String.valueOf(k1.getDonGia())) + " VND");
                md.addRow(dong);
            }
        }
        tbp.setModel(md);
        //tbp.getColumnModel().getColumn(0).setMaxWidth(50);
        //tbp.getColumnModel().getColumn(0).setMinWidth(50);
//        tbp.getColumnModel().getColumn(1).setMinWidth(120);
//        tbp.getColumnModel().getColumn(2).setMinWidth(250);                
        //--------------------Căn giữa trong bảng-----------------------//
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        tbp.setDefaultRenderer(tbp.getColumnClass(0), center);
        tbp.updateUI();
        return tbp;
    }

    public JTable danhsachkhachhang(ArrayList<KhachHangDTO> arr) {
        JTable tbp = new JTable();
        tbp.getTableHeader().setBackground(new Color(36, 36, 36));
        tbp.getTableHeader().setForeground(Color.WHITE);
        tbp.getTableHeader().setFont(new Font("Times New Roman", 0, 18));
        tbp.getTableHeader().setPreferredSize(new Dimension(0, 30));
        tbp.setFont(new Font("Times New Roman", 0, 18));
        tbp.setForeground(Color.BLACK);
        tbp.setRowHeight(25);

        DefaultTableModel md = new DefaultTableModel();
        Vector cot = new Vector();
        cot.add("Mã KH");
        cot.add("Họ");
        cot.add("Tên");
        cot.add("NGày sinh");
        if (md.getRowCount() == 0) {
            md = new DefaultTableModel(cot, 0);
        }
        for (KhachHangDTO k1 : arr) {
            Vector dong = new Vector();
            dong.add(k1.getMaKH());
            dong.add(k1.getHo());
            dong.add(k1.getTen());
            dong.add(k1.getNgaysinh());
            md.addRow(dong);
        }
        tbp.setModel(md);
        //tbp.getColumnModel().getColumn(0).setMaxWidth(50);
        //tbp.getColumnModel().getColumn(0).setMinWidth(50);
//        tbp.getColumnModel().getColumn(1).setMinWidth(120);
//        tbp.getColumnModel().getColumn(2).setMinWidth(250);                
        //--------------------Căn giữa trong bảng-----------------------//
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        tbp.setDefaultRenderer(tbp.getColumnClass(0), center);
        tbp.updateUI();
        return tbp;
    }

    public void JFrameChucnang(int x) {
        fThem = new JFrame();
        fThem.setBounds(550, 100, 700, 430);
        fThem.setLayout(null);
        fThem.setBackground(Color.WHITE);

        JLabel tieude = new JLabel();
        if (x == 0) {
            tieude.setText("Thêm khuyến mãi phòng");
        } else {
            tieude.setText("Sửa khuyến mãi phòng");
        }
        tieude.setFont(new Font("Times New Roman", Font.BOLD, 23));
        tieude.setBounds(0, 0, 700, 30);
        tieude.setForeground(Color.WHITE);
        tieude.setBackground(colorButton);
        tieude.setOpaque(true);
        tieude.setHorizontalAlignment(JLabel.CENTER);
        tieude.setBackground(colorButton);
        fThem.add(tieude);

        JLabel lbMaKH = new JLabel();
        lbMaKH.setBounds(20, 60, 100, 30);
        lbMaKH.setText("Mã KH:");
        lbMaKH.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lbMaKH.setForeground(Color.BLACK);
        lbMaKH.setHorizontalAlignment(JLabel.LEFT);
        fThem.add(lbMaKH);

        txMaKHcn = new JTextField();
        txMaKHcn.setBounds(130, 60, 150, 30);
        txMaKHcn.setFont(new Font("Times New Roman", Font.BOLD, 16));
        txMaKHcn.setEditable(false);
        txMaKHcn.setBackground(Color.WHITE);
        fThem.add(txMaKHcn);

        ImageIcon iconxem = new ImageIcon(icon.iconchon);
        btXemkh = new JButton(iconxem);
        btXemkh.setBounds(290, 60, 30, 30);
        btXemkh.setBackground(colorButton);
        btXemkh.setForeground(Color.WHITE);
        btXemkh.setBorderPainted(false);
        btXemkh.setFocusPainted(false);
        fThem.add(btXemkh);

        JLabel lbMaNV = new JLabel();
        lbMaNV.setBounds(350, 60, 100, 30);
        lbMaNV.setText("Mã nhân viên");
        lbMaNV.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lbMaNV.setForeground(Color.BLACK);
        lbMaNV.setHorizontalAlignment(JLabel.LEFT);
        fThem.add(lbMaNV);

        txMaNVcn = new JTextField();
        txMaNVcn.setBounds(460, 60, 150, 30);
        txMaNVcn.setFont(new Font("Times New Roman", Font.BOLD, 16));
        txMaNVcn.setEditable(false);
        txMaNVcn.setBackground(Color.WHITE);
        fThem.add(txMaNVcn);

//        ImageIcon iconxem1 = new ImageIcon(icon.iconchon);
//        JButton btXemnv= new JButton(iconxem1);
//        btXemnv.setBounds(620, 60, 30, 30);
//        btXemnv.setBackground(colorButton);
//        btXemnv.setForeground(Color.WHITE);
//        btXemnv.setBorderPainted(false);
//        btXemnv.setFocusPainted(false);
//        fThem.add(btXemnv);
        JLabel lbNgaylappdp = new JLabel();
        lbNgaylappdp.setBounds(20, 110, 100, 30);
        lbNgaylappdp.setText("Ngày lập PDP");
        lbNgaylappdp.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lbNgaylappdp.setForeground(Color.BLACK);
        lbNgaylappdp.setHorizontalAlignment(JLabel.LEFT);
        fThem.add(lbNgaylappdp);

        dayLapPDPcn = new JDateChooser();
        dayLapPDPcn.setBounds(130, 110, 150, 30);
        dayLapPDPcn.setDateFormatString("dd-MM-yyyy");
        dayLapPDPcn.setFont(new Font("Times New Roman", Font.BOLD, 16));
        dayLapPDPcn.setBackground(Color.WHITE);
        JTextFieldDateEditor editor;
        editor = (JTextFieldDateEditor) dayLapPDPcn.getDateEditor();
        editor.setEditable(false);
        fThem.add(dayLapPDPcn);

        JLabel lbSlp = new JLabel();
        lbSlp.setBounds(350, 110, 100, 30);
        lbSlp.setText("SL phòng");
        lbSlp.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lbSlp.setForeground(Color.BLACK);
        lbSlp.setHorizontalAlignment(JLabel.LEFT);
        fThem.add(lbSlp);

        txSLPhongcn = new JTextField();
        txSLPhongcn.setBounds(460, 110, 150, 30);
        txSLPhongcn.setFont(new Font("Times New Roman", Font.BOLD, 16));
        fThem.add(txSLPhongcn);

        JLabel lbNgaythue = new JLabel();
        lbNgaythue.setBounds(20, 160, 100, 30);
        lbNgaythue.setText("Ngày thuê");
        lbNgaythue.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lbNgaythue.setForeground(Color.BLACK);
        lbNgaythue.setHorizontalAlignment(JLabel.LEFT);
        fThem.add(lbNgaythue);

        dayThuecn = new JDateChooser();
        dayThuecn.setBounds(130, 160, 150, 30);
        dayThuecn.setDateFormatString("dd-MM-yyyy");
        dayThuecn.setFont(new Font("Times New Roman", Font.BOLD, 16));
        dayThuecn.setBackground(Color.WHITE);
        JTextFieldDateEditor editor2;
        editor2 = (JTextFieldDateEditor) dayThuecn.getDateEditor();
        editor2.setEditable(false);
        fThem.add(dayThuecn);

        JLabel lbNgaytra = new JLabel();
        lbNgaytra.setBounds(350, 160, 100, 30);
        lbNgaytra.setText("Ngày trả");
        lbNgaytra.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lbNgaytra.setForeground(Color.BLACK);
        lbNgaythue.setHorizontalAlignment(JLabel.LEFT);
        fThem.add(lbNgaytra);

        dayTracn = new JDateChooser();
        dayTracn.setBounds(460, 160, 150, 30);
        dayTracn.setDateFormatString("dd-MM-yyyy");
        dayTracn.setFont(new Font("Times New Roman", Font.BOLD, 16));
        dayTracn.setBackground(Color.WHITE);
        JTextFieldDateEditor editor3;
        editor3 = (JTextFieldDateEditor) dayTracn.getDateEditor();
        editor3.setEditable(false);
        fThem.add(dayTracn);

//        JLabel lbTongtien = new JLabel();
//        lbTongtien.setBounds(20, 210, 150, 30);
//        lbTongtien.setText("Tổng tiền thuê");
//        lbTongtien.setFont(new Font("Times New Roman", Font.BOLD, 16));
//        lbTongtien.setForeground(Color.BLACK);
//        lbTongtien.setHorizontalAlignment(JLabel.LEFT);
//        fThem.add(lbTongtien);
//
//        txTongtienthuecn = new JTextField();
//        txTongtienthuecn.setBounds(180, 210, 250, 30);
//        txTongtienthuecn.setEditable(false);
//        txTongtienthuecn.setBackground(Color.WHITE);
//        txTongtienthuecn.setFont(new Font("Times New Roman", Font.BOLD, 16));
//        fThem.add(txTongtienthuecn);
//
//        JLabel lbTongtienkm = new JLabel();
//        lbTongtienkm.setBounds(20, 260, 150, 30);
//        lbTongtienkm.setText("Tổng tiền KM");
//        lbTongtienkm.setFont(new Font("Times New Roman", Font.BOLD, 16));
//        lbTongtienkm.setForeground(Color.BLACK);
//        lbTongtienkm.setHorizontalAlignment(JLabel.LEFT);
//        fThem.add(lbTongtienkm);
//
//        txTongtienKMcn = new JTextField();
//        txTongtienKMcn.setBounds(180, 260, 250, 30);
//        txTongtienKMcn.setEditable(false);
//        txTongtienKMcn.setBackground(Color.WHITE);
//        txTongtienKMcn.setFont(new Font("Times New Roman", Font.BOLD, 16));
//        fThem.add(txTongtienKMcn);

        ImageIcon iconthem = new ImageIcon(icon.iconthem);
        btThemcn = new JButton("Thêm", iconthem);
        if (x == 0) {
            btThemcn.setBounds(315, 320, 96, 35);
        } else {
            btThemcn.setBounds(0, 0, 0, 0);
        }
        btThemcn.setBackground(colorButton);
        btThemcn.setForeground(Color.WHITE);
        btThemcn.setBorderPainted(false);
        btThemcn.setFocusPainted(false);
        fThem.add(btThemcn);

        ImageIcon iconsua = new ImageIcon(icon.iconsua);
        btSuacn = new JButton("Lưu", iconsua);
        if (x == 0) {
            btSuacn.setBounds(0, 0, 0, 0);
        } else {
            btSuacn.setBounds(315, 320, 96, 35);
        }
        btSuacn.setBackground(colorButton);
        btSuacn.setForeground(Color.WHITE);
        btSuacn.setBorderPainted(false);
        btSuacn.setFocusPainted(false);
        fThem.add(btSuacn);
        CacNutChucNangJFrameChucNang();
    }

    public void BangChonKH() {
        JFrame fchon = new JFrame();
        fchon.setLayout(null);
        fchon.setBounds(0, 0, 550, 400);
        fchon.setPreferredSize(new Dimension(550, 400));

        //---------------------------------Hiện giữa màn hình-----------------------------//
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        fchon.setLocation((int) d.getWidth() / 2 - (int) fchon.getPreferredSize().getWidth() / 2,
                (int) d.getHeight() / 2 - (int) fchon.getPreferredSize().getHeight() / 2);
        //--------------------------------------------------------------------------------//

        JLabel lbTieude = new JLabel("Chọn khách hàng");
        lbTieude.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lbTieude.setForeground(Color.BLACK);
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        lbTieude.setBounds(150, 20, 250, 25);

        JLabel lbMaphong = new JLabel();
        lbMaphong.setBounds(10, 100, 150, 30);
        lbMaphong.setText("Mã khách hàng:");
        lbMaphong.setFont(new Font("Times New Roman", Font.BOLD, 13));
        lbMaphong.setForeground(Color.BLACK);
        lbMaphong.setHorizontalAlignment(JLabel.CENTER);
        fchon.add(lbMaphong);

        txMaKH1 = new JTextField();
        txMaKH1.setBounds(160, 100, 160, 30);
        fchon.add(txMaKH1);

        ImageIcon icontimkiem = new ImageIcon(icon.icontimkiem);
        JButton btTimkiem = new JButton(icontimkiem);
        btTimkiem.setBounds(330, 100, 30, 30);
        btTimkiem.setBackground(colorButton);
        btTimkiem.setForeground(Color.WHITE);
        btTimkiem.setBorderPainted(false);
        btTimkiem.setFocusPainted(false);
        fchon.add(btTimkiem);

        fchon.add(lbTieude);

        tbkh = danhsachkhachhang(KhachHangBUS.dskh);
        JScrollPane sc = new JScrollPane();
        sc.setViewportView(tbkh);
        sc.setBounds(10, 140, 520, 200);

        fchon.add(sc);
        btTimkiem.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                KhachHangBUS p = new KhachHangBUS();
                if (!txMaKH1.getText().equals("")) {
                    sc.removeAll();
                    ArrayList<PhongDTO> dstk = new ArrayList<>();
                    tbkh = new JTable();
                    tbkh = danhsachkhachhang(p.Timkiemcmnd(txMaKH1.getText()));
                    JScrollPane scrdsp1 = new JScrollPane(tbkh);
                    scrdsp1.setBounds(0, 0, 520, 200);
                    sc.add(scrdsp1);
                    sc.repaint();
                    sc.revalidate();
                    fchon.add(sc);
                } else {
                    sc.removeAll();
                    ArrayList<PhongDTO> dstk = new ArrayList<>();
                    tbkh = new JTable();
                    tbkh = danhsachkhachhang(KhachHangBUS.dskh);
                    JScrollPane scrdsp1 = new JScrollPane(tbkh);
                    scrdsp1.setBounds(0, 0, 520, 200);
                    sc.add(scrdsp1);
                    sc.repaint();
                    sc.revalidate();
                    fchon.add(sc);
                }
                tbkh.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent arg0) {
                        int i = tbkh.getSelectedRow();
                        String maq = tbkh.getModel().getValueAt(i, 0).toString();
                        txMaKHcn.setText(maq);
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
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btTimkiem.setBackground(Color.DARK_GRAY);
                btTimkiem.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                btTimkiem.setBackground(new Color(36, 36, 36));
            }
        });
        tbkh.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int i = tbkh.getSelectedRow();
                String maq = tbkh.getModel().getValueAt(i, 0).toString();
                txMaKHcn.setText(maq);
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

    public void CacNutChucNangChiTiet() {
        btXemp.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                BangChonPhong();
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btXemp.setBackground(Color.DARK_GRAY);
                btXemp.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                btXemp.setBackground(new Color(36, 36, 36));
            }
        });
        btCapnhatct.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                ChiTietPhieuDatPhongBUS ctpdp = new ChiTietPhieuDatPhongBUS();
                Xulydulieu xldl = new Xulydulieu();
                PhieuDatPhongBUS pdp = new PhieuDatPhongBUS();
                PhieuDatPhongDTO p = new PhieuDatPhongDTO();
                SimpleDateFormat date = new SimpleDateFormat("yyy-MM-dd");
                SimpleDateFormat date2 = new SimpleDateFormat("dd-MM-yyyy");
                p = pdp.lay1pdp(txMaPDPct.getText());
                if (xldl.TinhSoNgay(date2.format(dayThuect.getDate()), date2.format(dayTract.getDate())) < 0) {
                    JOptionPane.showMessageDialog(null, "Ngày bắt đầu nhỏ hơn ngày kết thúc");
                } else if ((p.getTinhTrang()==3 && xldl.TinhSoNgay(xldl.chuyendate(xldl.LayNgayThangNamHienTai()), date2.format(dayThuect.getDate())) >= 0)) {
                    if(!txSLPhongct.getText().equals("") && xldl.KtraSo(txSLPhongct.getText())==true){
                        int x = ctpdp.ktrasoluongphong(txMaPDPct.getText());
                        if(x>Integer.parseInt(txSLPhongct.getText())){
                            txSLPhongct.setText(String.valueOf(x));
                        }else{
                            p.setNgayThue(date.format(dayThuect.getDate()));
                            p.setNgayTra(date.format(dayTract.getDate()));
                            pdp.sua(p);
                            int tongtienthue = ctpdp.tinhtongtienthue(p);
                            int tongtienkm = ctpdp.tinhtongtienkm(p);
                            p.setTongTienThue(tongtienthue);
                            p.setTongTienKM(tongtienkm);
                            p.setSLPhong(Integer.parseInt(txSLPhongct.getText()));
                            pdp.sua(p);
                            //==============================Hóa đơn===================================
                            HoaDonBUS dshd = new HoaDonBUS();
                            HoaDonDTO hd = new HoaDonDTO();
                            hd = dshd.Lay1HoaDonTheoPDP(p.getMaPDP());
                            hd.setTongCong(tongtienthue + hd.getTongTienDV() + ((tongtienthue + hd.getTongTienDV()) * 10 / 100));
                            //hd.setTongTienKM(tongtienkm);
                            hd.setTongTienThue(tongtienthue);
                            hd.setTongPhaiTra(hd.getTongCong() - hd.getTongTienKM());
                            dshd.Suahoadon(hd);
                            txTongtienct.setText(xldl.ChuyenKieuTien(String.valueOf(p.getTongTienThue()))+"VND");
                            Tongtienkmct.setText(xldl.ChuyenKieuTien(String.valueOf(p.getTongTienKM()))+"VND");
                    
                        }
                    }else{
                    JOptionPane.showMessageDialog(null, "Dữ liệu phòng không hợp lệ");

                    }
                }else if(p.getTinhTrang()==1 && xldl.TinhSoNgay(xldl.chuyendate(xldl.LayNgayThangNamHienTai()), date2.format(dayTract.getDate())) >= 0){
                    if(!txSLPhongct.getText().equals("") && xldl.KtraSo(txSLPhongct.getText())==true){
                        int x = ctpdp.ktrasoluongphong(txMaPDPct.getText());
                        if(x>Integer.parseInt(txSLPhongct.getText())){
                            txSLPhongct.setText(String.valueOf(x));
                        }else{
                            //p.setNgayThue(date.format(dayThuect.getDate()));
                            p.setNgayTra(date.format(dayTract.getDate()));
                            pdp.sua(p);
                            int tongtienthue = ctpdp.tinhtongtienthue(p);
                            int tongtienkm = ctpdp.tinhtongtienkm(p);
                            p.setTongTienThue(tongtienthue);
                            p.setTongTienKM(tongtienkm);
                            p.setSLPhong(Integer.parseInt(txSLPhongct.getText()));
                            pdp.sua(p);
                            //==============================Hóa đơn===================================
                            HoaDonBUS dshd = new HoaDonBUS();
                            HoaDonDTO hd = new HoaDonDTO();
                            hd = dshd.Lay1HoaDonTheoPDP(p.getMaPDP());
                            hd.setTongCong(tongtienthue + hd.getTongTienDV() + ((tongtienthue + hd.getTongTienDV()) * 10 / 100));
                            //hd.setTongTienKM(tongtienkm);
                            hd.setTongTienThue(tongtienthue);
                            hd.setTongPhaiTra(hd.getTongCong() - hd.getTongTienKM());
                            dshd.Suahoadon(hd);
                            txTongtienct.setText(xldl.ChuyenKieuTien(String.valueOf(p.getTongTienThue()))+"VND");
                            Tongtienkmct.setText(xldl.ChuyenKieuTien(String.valueOf(p.getTongTienKM()))+"VND");
                    
                        }
                    }else{
                    JOptionPane.showMessageDialog(null, "Dữ liệu phòng không hợp lệ");

                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Không thể tạo cho quá khứ");
                }
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btCapnhatct.setBackground(Color.DARK_GRAY);
                btCapnhatct.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                btCapnhatct.setBackground(new Color(36, 36, 36));
            }
        });
        btThemct.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                ChiTietPhieuDatPhongDTO p = new ChiTietPhieuDatPhongDTO();
                ChiTietPhieuDatPhongBUS ctpdp = new ChiTietPhieuDatPhongBUS();
                Xulydulieu xldl = new Xulydulieu();
                //System.out.println(txSLPhongct.getText());
                if(!txSLPhongct.getText().equals("") && xldl.KtraSo(txSLPhongct.getText())==true){
                    if (txMaphongct.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Điền đầy đủ thông tin");
                        } else {
                            if(ctpdp.ktrasoluongphong(txMaPDPct.getText())<Integer.parseInt(txSLPhongct.getText())){
                                SimpleDateFormat date = new SimpleDateFormat("yyy-MM-dd");
                                SimpleDateFormat date2 = new SimpleDateFormat("dd-MM-yyyy");
                                ChiTietKMPhongBUS ctkmp = new ChiTietKMPhongBUS();
                                ChiTietKMPhongDTO x = new ChiTietKMPhongDTO();
                                PhongBUS dsp = new PhongBUS();
                                PhongDTO x2 = new PhongDTO();
                                x2 = dsp.lay1p(txMaphongct.getText());
                                x = ctkmp.lay1ctkm(txMaphongct.getText());
                                p.setMaPDP(txMaPDPct.getText());
                                p.setMaPhong(txMaphongct.getText());
                                if (x.getMaKMPhong()==null) {
                                    p.setMaKMPhong("null");
                                    p.setTienKM(0);
                                } else {
                                    p.setMaKMPhong(x.getMaKMPhong());
                                    int Tongtienkm = (int) ((x2.getDonGia() * x.getPhanTramKM() * xldl.TinhSoNgay(date2.format(dayThuect.getDate()), date2.format(dayTract.getDate()))) / 100);
                                    p.setTienKM(Tongtienkm);
                                }
                                p.setDonGia(x2.getDonGia());
                                p.setSLNguoi(x2.getSLNguoi());
                                if (ctpdp.ktratrung(p) == 1) {
                                    //===========chi tiet==================
                                    PhieuDatPhongBUS pdp = new PhieuDatPhongBUS();
                                    PhieuDatPhongDTO p1 = new PhieuDatPhongDTO();
                                    Vector row = new Vector();
                                    row.add(p.getMaPhong());
                                    row.add(p.getMaKMPhong());
                                    row.add(p.getSLNguoi());
                                    row.add(String.valueOf(p.getDonGia()));
                                    row.add(String.valueOf(p.getTienKM()));
                                    model1.addRow(row);
                                    tbdsctpdp.setModel(model1);
                                    ctpdp.them(p);
                                    stt++;
                                    ctpdp.capnhattrangthaiphong(x2,2);
                                    //===========phieu dat phong==================
                                    p1 = pdp.lay1pdp(txMaPDPct.getText());
                                    int tongtienthue = ctpdp.tinhtongtienthue(p1);
                                    int tongtienkm = ctpdp.tinhtongtienkm(p1);
                                    //System.out.println(tongtienkm);
                                    p1.setTongTienThue(tongtienthue);
                                    p1.setTongTienKM(tongtienkm);
                                    pdp.sua(p1);
                                    //==============================Hóa đơn===================================
                                    HoaDonBUS dshd = new HoaDonBUS();
                                    HoaDonDTO hd = new HoaDonDTO();
                                    hd = dshd.Lay1HoaDonTheoPDP(p.getMaPDP());
                                    hd.setTongCong(tongtienthue + hd.getTongTienDV() + ((tongtienthue + hd.getTongTienDV()) * 10 / 100));
                                    hd.setTongTienKM(hd.getTongTienKM()+p.getTienKM());
                                    hd.setTongTienThue(tongtienthue);
                                    hd.setTongPhaiTra(hd.getTongCong() - hd.getTongTienKM());
                                    dshd.Suahoadon(hd);
                                    txTongtienct.setText(xldl.ChuyenKieuTien(String.valueOf(p1.getTongTienThue()))+"VND");
                                    Tongtienkmct.setText(xldl.ChuyenKieuTien(String.valueOf(p1.getTongTienKM()))+"VND");
                                    //fThemct.setVisible(false);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Phòng đã tồn tại");
                                }
                        }else{
                                JOptionPane.showMessageDialog(null, "Số lượng phòng đã đủ");
                            }
                                
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Dữ liệu phòng không hợp lệ");
                    }
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btThemct.setBackground(Color.DARK_GRAY);
                btThemct.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                btThemct.setBackground(new Color(36, 36, 36));
            }
        });
        btXoact.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int i=tbdsctpdp.getSelectedRow();
                if(i != -1){
                    int input=JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa", "Xóa tài khoản", JOptionPane.YES_NO_OPTION);
                    if(input==0){
                        String s=tbdsctpdp.getModel().getValueAt(i, 0).toString();
                        String s1=tbdsctpdp.getModel().getValueAt(i, 1).toString();
                        String s2=tbdsctpdp.getModel().getValueAt(i, 2).toString();
                        String s3=tbdsctpdp.getModel().getValueAt(i, 3).toString();
                        String s4=tbdsctpdp.getModel().getValueAt(i, 4).toString();
                        ChiTietPhieuDatPhongDTO p = new ChiTietPhieuDatPhongDTO();
                        p.setMaPDP(txMaPDPct.getText());
                        p.setMaPhong(s);
                        p.setMaKMPhong(s1);
                        p.setSLNguoi(Integer.parseInt(s2));
                        p.setDonGia(Integer.parseInt(s3));
                        p.setTienKM(Integer.parseInt(s4));
                        ChiTietPhieuDatPhongBUS ctpdp= new ChiTietPhieuDatPhongBUS();
                        ctpdp.xoa1chitiet(p);
                        model1.removeRow(i);
                        tbdsctpdp.setModel(model1);
                        //===========phieu dat phong==================
                        Xulydulieu xldl = new Xulydulieu();
                        PhieuDatPhongBUS pdp = new PhieuDatPhongBUS();
                        PhieuDatPhongDTO p1 = new PhieuDatPhongDTO();
                        p1 = pdp.lay1pdp(txMaPDPct.getText());
                        int tongtienthue = ctpdp.tinhtongtienthue(p1);
                        int tongtienkm = ctpdp.tinhtongtienkm(p1);
                        //System.out.println(tongtienkm);
                        p1.setTongTienThue(tongtienthue);
                        p1.setTongTienKM(tongtienkm);
        //                txTongtienKMcn.setText(String.valueOf(tongtienkm));
        //                txTongtienthuecn.setText(String.valueOf(tongtienthue));
                        pdp.sua(p1);
                        //==============================Hóa đơn===================================
                        HoaDonBUS dshd = new HoaDonBUS();
                        HoaDonDTO hd = new HoaDonDTO();
                        hd = dshd.Lay1HoaDonTheoPDP(p1.getMaPDP());
                        //hd.setTongTienDV(0);
                        hd.setTongCong(tongtienthue + hd.getTongTienDV() + ((tongtienthue + hd.getTongTienDV()) * 10 / 100));
                        hd.setTongTienKM(hd.getTongTienKM()-p.getTienKM());
                        hd.setTongTienThue(tongtienthue);
                        hd.setTongPhaiTra(hd.getTongCong() - hd.getTongTienKM());
                        dshd.Suahoadon(hd);
                        txTongtienct.setText(xldl.ChuyenKieuTien(String.valueOf(p1.getTongTienThue()))+"VND");
                        Tongtienkmct.setText(xldl.ChuyenKieuTien(String.valueOf(p1.getTongTienKM()))+"VND");
                        //==============================Phong======================================
                        PhongDTO x= new PhongDTO();
                        PhongBUS dsp = new PhongBUS();
                        x=dsp.lay1p(s);
                        ctpdp.capnhattrangthaiphong(x, 1);
                        }
                }else{
                    JOptionPane.showMessageDialog(null, "Chọn đối tượng cần xóa");
                }
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        btHoantat.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                ChiTietPhieuDatPhongBUS ctpdp = new ChiTietPhieuDatPhongBUS();
                Xulydulieu xldl = new Xulydulieu();
                PhieuDatPhongBUS pdp = new PhieuDatPhongBUS();
                PhieuDatPhongDTO p = new PhieuDatPhongDTO();
                SimpleDateFormat date = new SimpleDateFormat("yyy-MM-dd");
                SimpleDateFormat date2 = new SimpleDateFormat("dd-MM-yyyy");
                p = pdp.lay1pdp(txMaPDPct.getText());
                int x = ctpdp.ktrasoluongphong(txMaPDPct.getText());
                if(x==Integer.parseInt(txSLPhongct.getText())){
                    if (xldl.TinhSoNgay(date2.format(dayThuect.getDate()), date2.format(dayTract.getDate())) < 0) {
                        JOptionPane.showMessageDialog(null, "Ngày bắt đầu nhỏ hơn ngày kết thúc");
                    } else if ((p.getTinhTrang()==3 && xldl.TinhSoNgay(xldl.chuyendate(xldl.LayNgayThangNamHienTai()), date2.format(dayThuect.getDate())) >= 0)) {
                        if(!txSLPhongct.getText().equals("") && xldl.KtraSo(txSLPhongct.getText())==true){
                            if(x>Integer.parseInt(txSLPhongct.getText())){
                                txSLPhongct.setText(String.valueOf(x));
                            }else{
                                p.setNgayThue(date.format(dayThuect.getDate()));
                                p.setNgayTra(date.format(dayTract.getDate()));
                                pdp.sua(p);
                                int tongtienthue = ctpdp.tinhtongtienthue(p);
                                int tongtienkm = ctpdp.tinhtongtienkm(p);
                                p.setTongTienThue(tongtienthue);
                                p.setSLPhong(Integer.parseInt(txSLPhongct.getText()));
                                p.setTongTienKM(tongtienkm);
                                pdp.sua(p);
                                //==============================Hóa đơn===================================
                                HoaDonBUS dshd = new HoaDonBUS();
                                HoaDonDTO hd = new HoaDonDTO();
                                hd = dshd.Lay1HoaDonTheoPDP(p.getMaPDP());
                                hd.setTongCong(tongtienthue + hd.getTongTienDV() + ((tongtienthue + hd.getTongTienDV()) * 10 / 100));
                                //hd.setTongTienKM(tongtienkm);
                                hd.setTongTienThue(tongtienthue);
                                hd.setTongPhaiTra(hd.getTongCong() - hd.getTongTienKM());
                                dshd.Suahoadon(hd);
                                txTongtienct.setText(xldl.ChuyenKieuTien(String.valueOf(p.getTongTienThue()))+"VND");
                                Tongtienkmct.setText(xldl.ChuyenKieuTien(String.valueOf(p.getTongTienKM()))+"VND");
                                JOptionPane.showMessageDialog(null, "Cập nhật thành công");
                                fThemct.setVisible(false);
                            }
                        }else{
                        JOptionPane.showMessageDialog(null, "Dữ liệu phòng không hợp lệ");

                        }
                    }else if(p.getTinhTrang()==1 && xldl.TinhSoNgay(xldl.chuyendate(xldl.LayNgayThangNamHienTai()), date2.format(dayTract.getDate())) >= 0){
                        if(!txSLPhongct.getText().equals("") && xldl.KtraSo(txSLPhongct.getText())==true){
                            if(x>Integer.parseInt(txSLPhongct.getText())){
                                txSLPhongct.setText(String.valueOf(x));
                            }else{
                                //p.setNgayThue(date.format(dayThuect.getDate()));
                                p.setNgayTra(date.format(dayTract.getDate()));
                                pdp.sua(p);
                                int tongtienthue = ctpdp.tinhtongtienthue(p);
                                int tongtienkm = ctpdp.tinhtongtienkm(p);
                                p.setTongTienThue(tongtienthue);
                                p.setTongTienKM(tongtienkm);
                                p.setSLPhong(Integer.parseInt(txSLPhongct.getText()));
                                pdp.sua(p);
                                //==============================Hóa đơn===================================
                                HoaDonBUS dshd = new HoaDonBUS();
                                HoaDonDTO hd = new HoaDonDTO();
                                hd = dshd.Lay1HoaDonTheoPDP(p.getMaPDP());
                                hd.setTongCong(tongtienthue + hd.getTongTienDV() + ((tongtienthue + hd.getTongTienDV()) * 10 / 100));
                                //hd.setTongTienKM(tongtienkm);
                                hd.setTongTienThue(tongtienthue);
                                hd.setTongPhaiTra(hd.getTongCong() - hd.getTongTienKM());
                                dshd.Suahoadon(hd);
                                txTongtienct.setText(xldl.ChuyenKieuTien(String.valueOf(p.getTongTienThue()))+"VND");
                                Tongtienkmct.setText(xldl.ChuyenKieuTien(String.valueOf(p.getTongTienKM()))+"VND");
                                JOptionPane.showMessageDialog(null, "Cập nhật thành công");
                                fThemct.setVisible(false);
                            }
                        }else{
                        JOptionPane.showMessageDialog(null, "Dữ liệu phòng không hợp lệ");

                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Không thể tạo cho quá khứ");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "số lượng phòng phải bằng chi tiết");
                }
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btHoantat.setBackground(Color.DARK_GRAY);
                btHoantat.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                btHoantat.setBackground(new Color(36, 36, 36));
            }
        });
    }

    public void CacNutChucNang() {
//        String makmdv;
        btTimkiem.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                PhieuDatPhongBUS pdp= new PhieuDatPhongBUS();
                if(cbTimkiem.getSelectedItem().equals("Theo KH")){
                    scrdspdp.removeAll();
                    JTable tbl = Danhsachphieudatphong(pdp.timkiemtheokh(txMaKH.getText()));
                    scrdspdp1 = new JScrollPane(tbl);
                    scrdspdp1.setBounds(0, 0, 990, 350);
                    scrdspdp.add(scrdspdp1);
                    scrdspdp.repaint();
                    scrdspdp.revalidate();
                    pnPDP.add(scrdspdp);
                    
//                    scrdspdp.removeAll();
//                    JTable tbl = Danhsachphieudatphong(PhieuDatPhongBUS.dspdp);
//                    scrdspdp1 = new JScrollPane(tbl);
//                    scrdspdp1.setBounds(0, 0, 990, 350);
//                    scrdspdp.add(scrdspdp1);
//                    scrdspdp.repaint();
//                    scrdspdp.revalidate();
//                    pnPDP.add(scrdspdp);
                }else if(cbTimkiem.getSelectedItem().equals("Theo NV")){
                    JTable tbl = Danhsachphieudatphong(pdp.timkiemtheonv(txMaNV.getText()));
                    scrdspdp.removeAll();
                    scrdspdp1= new JScrollPane(tbl);
                    scrdspdp1.setBounds(0, 0, 990, 350);
                    scrdspdp.add(scrdspdp1);
                    scrdspdp.repaint();
                    scrdspdp.revalidate();
                    pnPDP.add(scrdspdp);
                }
                else if(cbTimkiem.getSelectedItem().equals("Tìm kiếm nâng cao")){
                    String s2,s3;
                    int i=0;
                    if(cbTinhtrang.getSelectedItem().equals("Đã nhận phòng")){
                        i=1;
                    }else if(cbTinhtrang.getSelectedItem().equals("Đã thanh toán")){
                        i=2;
                    }
                    else if(cbTinhtrang.getSelectedItem().equals("Chưa nhận phòng")){
                        i=3;
                    }
                    //System.out.println(i);
                    if(!cbTinhtrang.getSelectedItem().equals("Chọn tình trạng") && (dayLapPDPtu.getDate()==null && dayLapPDPden.getDate()==null)){
                        JTable tbl = Danhsachphieudatphong(pdp.timkiemnangcao(i,"",""));
                        scrdspdp.removeAll();
                        scrdspdp1= new JScrollPane(tbl);
                        scrdspdp1.setBounds(0, 0, 990, 350);
                        scrdspdp.add(scrdspdp1);
                        scrdspdp.repaint();
                        scrdspdp.revalidate();
                        pnPDP.add(scrdspdp); 
                    }else if(!cbTinhtrang.getSelectedItem().equals("Chọn tình trạng") && dayLapPDPtu.getDate()!=null && dayLapPDPden.getDate()!=null){
                        //System.out.println("2");
                        SimpleDateFormat date= new SimpleDateFormat("yyyy-MM-dd");
                        SimpleDateFormat date2= new SimpleDateFormat("yyyy-MM-dd");
                        JTable tbl = Danhsachphieudatphong(pdp.timkiemnangcao(i,date2.format(dayLapPDPtu.getDate()),date2.format(dayLapPDPden.getDate())));
                        scrdspdp.removeAll();
                        scrdspdp1= new JScrollPane(tbl);
                        scrdspdp1.setBounds(0, 0, 990, 350);
                        scrdspdp.add(scrdspdp1);
                        scrdspdp.repaint();
                        scrdspdp.revalidate();
                        pnPDP.add(scrdspdp); 
                    }else if(i==0 && dayLapPDPtu.getDate()!=null && dayLapPDPden.getDate()!=null){
                        SimpleDateFormat date= new SimpleDateFormat("yyyy-MM-dd");
                        SimpleDateFormat date2= new SimpleDateFormat("yyyy-MM-dd");
                        JTable tbl = Danhsachphieudatphong(pdp.timkiemnangcao(0,date2.format(dayLapPDPtu.getDate()),date2.format(dayLapPDPden.getDate())));
                        scrdspdp.removeAll();
                        scrdspdp1= new JScrollPane(tbl);
                        scrdspdp1.setBounds(0, 0, 990, 350);
                        scrdspdp.add(scrdspdp1);
                        scrdspdp.repaint();
                        scrdspdp.revalidate();
                        pnPDP.add(scrdspdp); 
                    }else{
                        JOptionPane.showMessageDialog(null, "Tìm kiếm không hợp lý");
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btTimkiem.setBackground(Color.DARK_GRAY);
                btTimkiem.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                btTimkiem.setBackground(new Color(36,36,36));
            }
        });
        cbTimkiem.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.                
                if (cbTimkiem.getSelectedItem().equals("Theo KH")){
                    txMaKH.setEditable(true);
                    dayLapPDPtu.setEnabled(false);
                    dayLapPDPden.setEnabled(false);
                    cbTinhtrang.setEnabled(false);
                    txMaNV.setEditable(false);
                }
                else if(cbTimkiem.getSelectedItem().equals("Theo NV")){
                    txMaKH.setEditable(false);
                    txMaNV.setEditable(true);
                    dayLapPDPtu.setEnabled(false);
                    dayLapPDPden.setEnabled(false);
                    cbTinhtrang.setEnabled(false);
                }else if(cbTimkiem.getSelectedItem().equals("Tìm kiếm nâng cao")){
                    txMaKH.setEditable(false);
                    txMaNV.setEditable(false);
                    dayLapPDPtu.setEnabled(true);
                    dayLapPDPden.setEnabled(true);
                    cbTinhtrang.setEnabled(true);
                }else{
                    txMaKH.setEditable(false);
                    txMaNV.setEditable(false);
                    dayLapPDPtu.setEnabled(false);
                    dayLapPDPden.setEnabled(false);
                    cbTinhtrang.setEnabled(false);
                }
            }
        });
        btSua.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                PhieuDatPhongDTO p1 = new PhieuDatPhongDTO();
                PhieuDatPhongBUS ds = new PhieuDatPhongBUS();
                int i = tbdspdp.getSelectedRow();
                if (i != -1) {
                    String s = tbdspdp.getModel().getValueAt(i, 1).toString();
                    p1 = ds.lay1pdp(s);
                    if(p1.getTinhTrang()!=2){
                        JFrameChitiet(p1,0);
                        ChiTietPhieuDatPhongBUS ctpdp = new ChiTietPhieuDatPhongBUS();
                        Xulydulieu xldl = new Xulydulieu();
                        PhieuDatPhongBUS pdp = new PhieuDatPhongBUS();
                        PhieuDatPhongDTO p = new PhieuDatPhongDTO();
                        SimpleDateFormat date = new SimpleDateFormat("yyy-MM-dd");
                        SimpleDateFormat date2 = new SimpleDateFormat("dd-MM-yyyy");
                        p = pdp.lay1pdp(txMaPDPct.getText());
                        p.setNgayThue(date.format(dayThuect.getDate()));
                        p.setNgayTra(date.format(dayTract.getDate()));
                        int tongtienthue = ctpdp.tinhtongtienthue(p);
                        int tongtienkm = ctpdp.tinhtongtienkm(p);
                        p.setTongTienThue(tongtienthue);
                        p.setTongTienKM(tongtienkm);
        //                txTongtienKMcn.setText(String.valueOf(tongtienkm));
        //                txTongtienthuecn.setText(String.valueOf(tongtienthue));
                        pdp.sua(p);
                        //==============================Hóa đơn===================================
                        HoaDonBUS dshd = new HoaDonBUS();
                        HoaDonDTO hd = new HoaDonDTO();
                        hd = dshd.Lay1HoaDonTheoPDP(p.getMaPDP());
                        hd.setTongCong(tongtienthue + hd.getTongTienDV() + ((tongtienthue + hd.getTongTienDV()) * 10 / 100));
                        //hd.setTongTienKM(tongtienkm);
                        hd.setTongTienThue(tongtienthue);
                        hd.setTongPhaiTra(hd.getTongCong() - hd.getTongTienKM());
                        dshd.Suahoadon(hd);
                        txTongtienct.setText(xldl.ChuyenKieuTien(String.valueOf(p.getTongTienThue()))+"VND");
                        Tongtienkmct.setText(xldl.ChuyenKieuTien(String.valueOf(p.getTongTienKM()))+"VND");
                        fThemct.setVisible(true);
                    } else {
                        //System.out.println(p1.getTinhTrang());
                        JOptionPane.showMessageDialog(null, "Không thể sửa phiếu đặt phòng đã thanh toán");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Chọn phiếu đặt phòng cần sửa");
                }

            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btSua.setBackground(Color.DARK_GRAY);
                btSua.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                btSua.setBackground(new Color(36, 36, 36));
            }
        });
        btThem.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                JFrameChucnang(0);
                txMaNVcn.setText(DangNhapGUI.MaNV);
                Xulydulieu xl = new Xulydulieu();
                try {
                    String s = xl.chuyendate(xl.LayNgayThangNamHienTai());
                    Date date = new SimpleDateFormat("dd-MM-yyyy").parse(s);
                    dayLapPDPcn.setDate(date);
                    fThem.setVisible(true);
                } catch (Exception ex) {
                    ex.getStackTrace();
                }
                fThem.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btThem.setBackground(Color.DARK_GRAY);
                btThem.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                btThem.setBackground(new Color(36, 36, 36));
            }
        });
        btXemchitiet.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                PhieuDatPhongDTO p1 = new PhieuDatPhongDTO();
                PhieuDatPhongBUS ds = new PhieuDatPhongBUS();
                int i = tbdspdp.getSelectedRow();
                if (i != -1) {
                    String s = tbdspdp.getModel().getValueAt(i, 1).toString();
                    p1 = ds.lay1pdp(s);
                    JFrameChitiet(p1,1);
                    ChiTietPhieuDatPhongBUS ctpdp = new ChiTietPhieuDatPhongBUS();
                    Xulydulieu xldl = new Xulydulieu();
                    PhieuDatPhongBUS pdp = new PhieuDatPhongBUS();
                    PhieuDatPhongDTO p = new PhieuDatPhongDTO();
                    SimpleDateFormat date = new SimpleDateFormat("yyy-MM-dd");
                    SimpleDateFormat date2 = new SimpleDateFormat("dd-MM-yyyy");
                    p = pdp.lay1pdp(txMaPDPct.getText());
                    p.setNgayThue(date.format(dayThuect.getDate()));
                    p.setNgayTra(date.format(dayTract.getDate()));
                    int tongtienthue = ctpdp.tinhtongtienthue(p);
                    int tongtienkm = ctpdp.tinhtongtienkm(p);
                    p.setTongTienThue(tongtienthue);
                    p.setTongTienKM(tongtienkm);
    //                txTongtienKMcn.setText(String.valueOf(tongtienkm));
    //                txTongtienthuecn.setText(String.valueOf(tongtienthue));
                    //pdp.sua(p);
                    //==============================Hóa đơn===================================
                    HoaDonBUS dshd = new HoaDonBUS();
                    HoaDonDTO hd = new HoaDonDTO();
                    hd = dshd.Lay1HoaDonTheoPDP(p.getMaPDP());
                    hd.setTongCong(tongtienthue + hd.getTongTienDV() + ((tongtienthue + hd.getTongTienDV()) * 10 / 100));
                    //hd.setTongTienKM(tongtienkm);
                    hd.setTongTienThue(tongtienthue);
                    hd.setTongPhaiTra(hd.getTongCong() - hd.getTongTienKM());
                    //dshd.Suahoadon(hd);
                    txTongtienct.setText(xldl.ChuyenKieuTien(String.valueOf(p.getTongTienThue()))+"VND");
                    Tongtienkmct.setText(xldl.ChuyenKieuTien(String.valueOf(p.getTongTienKM()))+"VND");
                    fThemct.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Chọn phiếu đặt phòng cần xem chi tiết");
                }
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btXemchitiet.setBackground(Color.DARK_GRAY);
                btXemchitiet.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                btXemchitiet.setBackground(new Color(36, 36, 36));
            }
        });
        btXoa.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                PhieuDatPhongDTO p1 = new PhieuDatPhongDTO();
                PhieuDatPhongBUS ds = new PhieuDatPhongBUS();
                int i = tbdspdp.getSelectedRow();
                if (i != -1) {
                    String s = tbdspdp.getModel().getValueAt(i, 1).toString();
                    p1 = ds.lay1pdp(s);;
                    ds.xoa(p1);
                }else {
                    JOptionPane.showMessageDialog(null, "Chọn phiếu đặt phòng cần xóa");
                }

            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        btCapnhat.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                scrdspdp.removeAll();
                JTable tbl = Danhsachphieudatphong(PhieuDatPhongBUS.dspdp);
                scrdspdp1 = new JScrollPane(tbl);
                scrdspdp1.setBounds(0, 0, 990, 350);
                scrdspdp.add(scrdspdp1);
                scrdspdp.repaint();
                scrdspdp.revalidate();
                pnPDP.add(scrdspdp);
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btCapnhat.setBackground(Color.DARK_GRAY);
                btCapnhat.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                btCapnhat.setBackground(new Color(36, 36, 36));
            }
        });
    }
    
    public void CacNutChucNangJFrameChucNang() {
        btXemkh.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                BangChonKH();
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btXemkh.setBackground(Color.DARK_GRAY);
                btXemkh.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                btXemkh.setBackground(new Color(36, 36, 36));
            }
        });
//        btSuacnct.addMouseListener(new MouseListener() {
//            @Override
//            public void mouseClicked(MouseEvent arg0) {
//                PhieuDatPhongDTO p = new PhieuDatPhongDTO();
//                PhieuDatPhongBUS ds = new PhieuDatPhongBUS();
//                int i=tbdspdp.getSelectedRow();
//                if(i!=-1)
//                {
//                    String s=tbdspdp.getModel().getValueAt(i, 1).toString();
//                    p=ds.lay1pdp(s);
//                    JFrameChitiet(p);
//                    fThemct.setVisible(true);
//                }else{
//                    JOptionPane.showMessageDialog(null, "Chọn khuyến mãi phòng cần xem chi tiết");
//                }
//                
//            }
//
//            @Override
//            public void mousePressed(MouseEvent arg0) {
//                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent arg0) {
//                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//             @Override
//            public void mouseEntered(MouseEvent arg0) {
//                btSuacnct.setBackground(Color.DARK_GRAY);
//                btSuacnct.setCursor(new Cursor(Cursor.HAND_CURSOR));
//            }
//
//            @Override
//            public void mouseExited(MouseEvent arg0) {
//                btSuacnct.setBackground(new Color(36,36,36));
//            }
//        });
        btThemcn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                PhieuDatPhongDTO p = new PhieuDatPhongDTO();
                PhieuDatPhongBUS pdp = new PhieuDatPhongBUS();
                Xulydulieu xldl = new Xulydulieu();
                if (txSLPhongcn.getText().equals("") || txMaKHcn.getText().equals("") || dayLapPDPcn.getDate() == null || dayThuecn.getDate() == null || dayTracn.getDate() == null) {
                    JOptionPane.showMessageDialog(null, "Nhập đầy đủ thông tin");
                } else {
                    if(xldl.KtraSo(txSLPhongcn.getText())==true){
                        SimpleDateFormat date = new SimpleDateFormat("yyy-MM-dd");
                        SimpleDateFormat date2 = new SimpleDateFormat("dd-MM-yyyy");
                        if (xldl.TinhSoNgay(date2.format(dayThuecn.getDate()), date2.format(dayTracn.getDate())) < 0) {
                            JOptionPane.showMessageDialog(null, "Ngày bắt đầu nhỏ hơn ngày kết thúc");
                        } else if (xldl.TinhSoNgay(xldl.chuyendate(xldl.LayNgayThangNamHienTai()), date2.format(dayThuecn.getDate())) < 0) {
                            JOptionPane.showMessageDialog(null, "Không thể tạo cho quá khứ");
                        } else {
                            //==============================Phiếu đặt phòng===========================
                            p.setMaPDP(pdp.MaCuoiDS());
                            p.setMaKH(txMaKHcn.getText());
                            p.setMaNV(txMaNVcn.getText());
                            p.setSLPhong(Integer.parseInt(txSLPhongcn.getText()));
                            p.setNgayLapPDP(xldl.LayNgayThangNamHienTai());
                            p.setNgayThue(date.format(dayThuecn.getDate()));
                            p.setNgayTra(date.format(dayTracn.getDate()));
                            p.setTongTienThue(0);
                            p.setTongTienKM(0);
                            pdp.them(p);
                            //==============================Hóa đơn===================================
                            HoaDonBUS dshd = new HoaDonBUS();
                            HoaDonDTO hd = new HoaDonDTO();
                            hd.setMaHD(dshd.MaCuoiDS());
                            hd.setMaPDP(p.getMaPDP());
                            hd.setMaNV(DangNhapGUI.MaNV);
                            hd.setNgayLapHD(xldl.LayNgayThangNamHienTai());
                            hd.setThue(10);
                            hd.setTinhTrang(1);
                            hd.setTongCong(0);
                            hd.setTongPhaiTra(0);
                            hd.setTongTienDV(0);
                            hd.setTongTienKM(0);
                            hd.setTongTienThue(0);
                            HoaDonBUS h1 = new HoaDonBUS();
                            h1.them(hd);
                            fThem.setVisible(false);
                            JFrameChitiet(p,0);
                            fThemct.setVisible(true);

                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Nhập số lượng phòng không đúng dữ liệu");
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btThemcn.setBackground(Color.DARK_GRAY);
                btThemcn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                btThemcn.setBackground(new Color(36, 36, 36));
            }

        });
//        btSuacn.addMouseListener(new MouseListener() {
//            @Override
//            public void mouseClicked(MouseEvent arg0) {
//                KhuyenMaiPhongDTO p = new  KhuyenMaiPhongDTO();
//                int i=tbdskmp.getSelectedRow();
//                String s=tbdskmp.getModel().getValueAt(i, 1).toString();
//                String s1=tbdskmp.getModel().getValueAt(i, 2).toString();
//                KhuyenMaiPhongBUS kmdv = new KhuyenMaiPhongBUS();
//                Xulydulieu xldl= new Xulydulieu();
//                p.setMaKMPhong(s);
//                SimpleDateFormat date= new SimpleDateFormat("yyy-MM-dd");
//                SimpleDateFormat date2= new SimpleDateFormat("dd-MM-yyyy");
//                if(txTenKMPcn.getText().equals("") || daybdcn.getDate()==null || dayktcn.getDate()==null){
//                    JOptionPane.showMessageDialog(null, "Nhập đầy đủ thông tin");
//                }else{
//                    if(xldl.TinhSoNgay(date2.format(daybdcn.getDate()),date2.format(dayktcn.getDate()))<0){
//                        JOptionPane.showMessageDialog(null, "Ngày bắt đầu nhỏ hơn ngày kết thúc");
//                    }
//                    else if(xldl.TinhSoNgay(xldl.chuyendate(xldl.LayNgayThangNamHienTai()),date2.format(daybdcn.getDate()))<0){
//                        JOptionPane.showMessageDialog(null, "Không thể tạo cho quá khứ");
//                    }else{
//                        p.setTen(txTenKMPcn.getText());
//                        p.setNgayBD(date.format(daybdcn.getDate()));
//                        p.setNgayKT(date.format(dayktcn.getDate()));
//                        kmdv.sua(p);
//                        fThem.setVisible(false);
//                    }
//                }
//            }
//
//            @Override
//            public void mousePressed(MouseEvent arg0) {
//                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent arg0) {
//                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent arg0) {
//                btSuacn.setBackground(Color.DARK_GRAY);
//                btSuacn.setCursor(new Cursor(Cursor.HAND_CURSOR));
//            }
//
//            @Override
//            public void mouseExited(MouseEvent arg0) {
//                btSuacn.setBackground(new Color(36,36,36));
//            }
//        });
    }
//
//    public static void main(String[] args) {
//        QuanLyGUI p = new QuanLyGUI("NV0001", "QL");
//    }
}
