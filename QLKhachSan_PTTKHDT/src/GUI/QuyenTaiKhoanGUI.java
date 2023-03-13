/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.ChiTietQuyenBUS;
import BUS.ChucNangBUS;
import BUS.QuyenTaiKhoanBUS;
import BUS.iconBUS;
import DTO.ChiTietQuyenDTO;
import DTO.ChucNangDTO;
import DTO.QuyenTaiKhoanDTO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author minhd
 */
public class QuyenTaiKhoanGUI {
    protected String font="Times New Roman";   
    protected Font fontdaydu=new Font("Times New Roman",0,18);
    protected Font fontdayduBold=new Font("Times New Roman",Font.BOLD,18);
    protected JPanel pQuyenTaiKhoan;
    protected JPanel pThongtin;
    protected JLabel lbTieude;
    protected JTable tbdsqtk;
    protected DefaultTableModel model;
    protected JScrollPane scr;
    protected JLabel lbMaQuyenThongtin;
    protected JLabel lbTenQuyenThongtin;
    protected JLabel[] lbChucNangThongtin;
    protected JPanel pChucNangThongTin;
    protected JScrollPane scrChucnang;
    protected JButton btThem;
    protected JButton btSua;
    protected JButton btXoa;
    protected JButton btTimKiem;
    protected JButton btCapNhat;
    protected JTextField txTKTenQuyen;    
    protected JFrame fThem;
    protected JFrame fThemChiTiet;
    protected JFrame fSua;
    protected JTextField txThemSuaMaQuyen;
    protected JTextField txThemSuaTenQuyen;
    protected JLabel lbThemSuaChiTietQuyen;
    protected JButton btChonCN;
    protected JTable tbdsctqtk;
    protected DefaultTableModel modelct;
    protected JScrollPane scrct;
    public QuyenTaiKhoanGUI(){
        
    }
    public JPanel KhoiTaoPanel(int width, int height){
        pQuyenTaiKhoan=new JPanel();
        pQuyenTaiKhoan.setLayout(null);
        pQuyenTaiKhoan.setBounds(0, 0, width, height);
        pQuyenTaiKhoan.setPreferredSize(new Dimension(width,height));
        pQuyenTaiKhoan.setOpaque(true);
        
        lbTieude=new JLabel();
        lbTieude.setText("Quyền tài khoản");
        lbTieude.setFont(new Font(font,Font.BOLD,26));
        lbTieude.setBounds(400, 20, 200, 50);
        lbTieude.setForeground(Color.BLACK);
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        
        pQuyenTaiKhoan.add(lbTieude);
        
        QuyenTaiKhoanBUS qtkbus=new QuyenTaiKhoanBUS();
        if (QuyenTaiKhoanBUS.dsqtk==null){
            qtkbus.docdsqtk();
        }
        JTable tb=new JTable();
        tb=BangTaiKhoan(QuyenTaiKhoanBUS.dsqtk);
        Danhsachtaikhoan(tb);
        formHienThongTin();
        CacNutChucNang();
        formTimKiem();
        ChucNangCacNut();
        
        return pQuyenTaiKhoan;
    }
    
    public JTable BangTaiKhoan(ArrayList<QuyenTaiKhoanDTO> ds){
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
        cottieude.add("Mã quyền");
        cottieude.add("Tên quyền");
        if (model.getRowCount()==0){
            model=new DefaultTableModel(cottieude, 0);
        }
        int i=0;
        for (QuyenTaiKhoanDTO k : ds){            
                i++;
                Vector dong=new Vector();
                dong.add(i);
                dong.add(k.getMaQuyen());
                dong.add(k.getTenQuyen());
                model.addRow(dong);            
        }
        tbds.setModel(model);
        tbds.getColumnModel().getColumn(0).setMaxWidth(50);
        tbds.getColumnModel().getColumn(1).setMinWidth(80);
        tbds.getColumnModel().getColumn(2).setMinWidth(230);    
        
        //--------------------Căn giữa trong bảng-----------------------//
        DefaultTableCellRenderer center=new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        tbds.setDefaultRenderer(tbds.getColumnClass(0), center);
        tbds.updateUI();
        //--------------------------------------------------------------//
        
        return tbds;
    }
    
    public void Danhsachtaikhoan(JTable tb){                                        
        tbdsqtk=new JTable();
        tbdsqtk=tb;
        scr=new JScrollPane();
        scr.setViewportView(tbdsqtk);
        scr.setBounds(10, 250, 450, 350); // Tùy mỗi người
        scr.setPreferredSize(new Dimension(450,350));
        
        pQuyenTaiKhoan.add(scr);    
        tbdsqtk.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int vt=tbdsqtk.getSelectedRow();
                String maqtk=tbdsqtk.getModel().getValueAt(vt, 1).toString();
                QuyenTaiKhoanBUS qtkbus=new QuyenTaiKhoanBUS();
                QuyenTaiKhoanDTO qtk=new QuyenTaiKhoanDTO();
                qtk=qtkbus.LayQuyenTaiKhoanTheoMa(maqtk);
                lbMaQuyenThongtin.setText(qtk.getMaQuyen());
                lbTenQuyenThongtin.setText(qtk.getTenQuyen());
                ChucNangBUS cnbus=new ChucNangBUS();
                if (ChucNangBUS.dscn==null){
                    cnbus.docdscn();
                }
                ChiTietQuyenBUS ctqbus=new ChiTietQuyenBUS();
                if (ChiTietQuyenBUS.dsctq==null){
                    ctqbus.docdsctq();
                }
                ArrayList<ChiTietQuyenDTO> ctq1=new ArrayList<>();
                ctq1=ctqbus.LayChiTietQuyen(maqtk);
                if (ctq1.size()!=0){
                    ArrayList<ChucNangDTO> cn1=new ArrayList<>();

                    lbChucNangThongtin=new JLabel[ctq1.size()];
                    for (ChucNangDTO k : ChucNangBUS.dscn){
                        for (ChiTietQuyenDTO k1 : ctq1){
                            if (k.getMaChucNang().equals(k1.getMaChucNang()))
                                cn1.add(k);
                        }
                    }
                    int dodai=(cn1.size()*30);
                    pChucNangThongTin=new JPanel();
                    pChucNangThongTin.setLayout(new FlowLayout(1, 20, 0));
                    pChucNangThongTin.setBounds(0, 0, 250, dodai);
                    pChucNangThongTin.setPreferredSize(new Dimension(250,dodai));
                    pChucNangThongTin.setOpaque(true);


                    int i=0;
                    for (ChucNangDTO k : cn1){
                        lbChucNangThongtin[i]=new JLabel();
                        lbChucNangThongtin[i].setText(k.getTenChucNang());
                        lbChucNangThongtin[i].setBounds(0,0,250,30);
                        lbChucNangThongtin[i].setPreferredSize(new Dimension(250,30));
                        lbChucNangThongtin[i].setForeground(Color.BLACK);
                        lbChucNangThongtin[i].setFont(new Font("Times New Roman",Font.BOLD,18));
                        lbChucNangThongtin[i].setOpaque(true);
                        pChucNangThongTin.add(lbChucNangThongtin[i]);
                        i++;
                    }
                    scrChucnang.setViewportView(pChucNangThongTin);
                    scrChucnang.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    scrChucnang.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    scrChucnang.getVerticalScrollBar().setUnitIncrement(10);

                    pThongtin.add(scrChucnang);
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
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
            }
        });
    }
    
    public void formHienThongTin(){
        pThongtin=new JPanel();
        pThongtin.setOpaque(true);
        pThongtin.setBounds(470, 250, 520, 350);
        pThongtin.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        pThongtin.setLayout(null);
        
        JLabel lbthongtin=new JLabel("Thông tin quyền tài khoản");
        lbthongtin.setFont(new Font(font,Font.BOLD,20));
        lbthongtin.setForeground(Color.BLACK);
        lbthongtin.setBounds(135, 10, 250, 20);
        lbthongtin.setHorizontalAlignment(JLabel.CENTER);
        
        pThongtin.add(lbthongtin);
        
        JLabel lbMaQuyen=new JLabel("Mã quyền:");
        lbMaQuyen.setFont(new Font(font,0,18));
        lbMaQuyen.setBounds(20, 50, 130, 20);
        lbMaQuyen.setForeground(Color.BLACK);
        
        pThongtin.add(lbMaQuyen);
        
        
        lbMaQuyenThongtin=new JLabel();
        lbMaQuyenThongtin.setForeground(Color.BLACK);
        lbMaQuyenThongtin.setBounds(20, 80, 180, 20);
        lbMaQuyenThongtin.setFont(new Font(font,Font.BOLD,18));
        lbMaQuyenThongtin.setHorizontalAlignment(JLabel.CENTER);   
        lbMaQuyenThongtin.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        
        pThongtin.add(lbMaQuyenThongtin);
        
        JLabel lbTenQuyen=new JLabel("Tên quyền:");
        lbTenQuyen.setFont(new Font(font,0,18));
        lbTenQuyen.setBounds(20, 110, 130, 20);
        lbTenQuyen.setForeground(Color.BLACK);
        
        pThongtin.add(lbTenQuyen);
        
        
        lbTenQuyenThongtin=new JLabel();
        lbTenQuyenThongtin.setForeground(Color.BLACK);
        lbTenQuyenThongtin.setBounds(20, 140, 180, 20);
        lbTenQuyenThongtin.setFont(new Font(font,Font.BOLD,18));
        lbTenQuyenThongtin.setHorizontalAlignment(JLabel.CENTER);   
        lbTenQuyenThongtin.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        
        pThongtin.add(lbTenQuyenThongtin); 
        
        JLabel lbChucnang=new JLabel("Các chức năng");
        lbChucnang.setFont(new Font(font,0,18));
        lbChucnang.setBounds(320, 50, 130, 20);
        lbChucnang.setForeground(Color.BLACK);
        
        pThongtin.add(lbChucnang);      
        
        scrChucnang=new JScrollPane();
        scrChucnang.setBounds(250, 90, 250, 250);
        scrChucnang.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        
        pThongtin.add(scrChucnang);
                                               
        pQuyenTaiKhoan.add(pThongtin);
    }
    
    public void CacNutChucNang(){
        iconBUS ic=new iconBUS();
        
        ImageIcon iconThem=new ImageIcon(ic.iconthem);
        btThem=new JButton();
        btThem.setText("Thêm");
        btThem.setIcon(iconThem);
        btThem.setFont(new Font(font,0,18));
        btThem.setBackground(new Color(36,36,36));
        btThem.setForeground(Color.WHITE);
        btThem.setFocusable(false);
        btThem.setBorderPainted(false);
        btThem.setBounds(610, 210, 120, 30);
        
        pQuyenTaiKhoan.add(btThem);
        
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
        
        pQuyenTaiKhoan.add(btSua);
        
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
        
        pQuyenTaiKhoan.add(btXoa);
        
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
        
        pQuyenTaiKhoan.add(btCapNhat);
        
        ImageIcon iconTimkiem=new ImageIcon(ic.icontimkiem);
        btTimKiem=new JButton();
        btTimKiem.setText("Tìm kiếm");
        btTimKiem.setIcon(iconTimkiem);
        btTimKiem.setFont(new Font(font,0,18));
        btTimKiem.setBackground(new Color(36,36,36));
        btTimKiem.setForeground(Color.WHITE);
        btTimKiem.setFocusable(false);
        btTimKiem.setBorderPainted(false);
        btTimKiem.setBounds(600, 90, 150, 30);
        
        pQuyenTaiKhoan.add(btTimKiem);
    }
    
    public void formTimKiem(){
        JLabel lbTKMa=new JLabel("Nhập tên quyền:");
        lbTKMa.setFont(new Font(font,0,20));
        lbTKMa.setForeground(Color.BLACK);
        lbTKMa.setBounds(50, 90, 150, 20);            
        
        pQuyenTaiKhoan.add(lbTKMa);
        
        txTKTenQuyen=new JTextField();
        txTKTenQuyen.setFont(new Font(font,0,18));
        txTKTenQuyen.setForeground(Color.BLACK);
        txTKTenQuyen.setBounds(230, 90, 350, 25);
        txTKTenQuyen.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(36,36,36)));
        
        pQuyenTaiKhoan.add(txTKTenQuyen);
                
                              
    }
    
    public void ChucNangCacNut(){                                
                
        btThem.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {  
                KhoiTaoFrameThemQuyen(); 
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
                int i=tbdsqtk.getSelectedRow();
                if (i<0)
                    JOptionPane.showMessageDialog(pQuyenTaiKhoan, "Chưa chọn quyền tài khoản cần sửa");
                else{                    
                    String maqtk=tbdsqtk.getModel().getValueAt(i, 1).toString();
                    String tenqtk=tbdsqtk.getModel().getValueAt(i, 2).toString();
                    KhoiTaiFrameSuaQuyen(maqtk);       
                    txThemSuaMaQuyen.setText(maqtk);
                    txThemSuaTenQuyen.setText(tenqtk);
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
                int i=tbdsqtk.getSelectedRow();
                if (i<0)
                    JOptionPane.showMessageDialog(pQuyenTaiKhoan, "Chưa chọn quyền tài khoản cần xóa");
                else{
                    int input=JOptionPane.showConfirmDialog(pQuyenTaiKhoan, "Bạn có chắc muốn xóa", "Xóa tài khoản", JOptionPane.YES_NO_OPTION);
                    if (input==0){
                        String matk=tbdsqtk.getModel().getValueAt(i, 1).toString();
                        QuyenTaiKhoanBUS qtkbus=new QuyenTaiKhoanBUS();
                        if (qtkbus.XoaQuyenTaiKhoan(matk)==1){
                            JOptionPane.showMessageDialog(pQuyenTaiKhoan, "Xóa thành công");
                            model.removeRow(i);
                            tbdsqtk.setModel(model);
                        }
                        else
                            JOptionPane.showMessageDialog(pQuyenTaiKhoan, "Xóa thất bại");
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
                lbMaQuyenThongtin.setText("");
                lbTenQuyenThongtin.setText("");
                pQuyenTaiKhoan.remove(scr);
                QuyenTaiKhoanBUS qtkbus=new QuyenTaiKhoanBUS();
                String tenq=txTKTenQuyen.getText();
                JTable tb=new JTable();
                tb=BangTaiKhoan(qtkbus.TimkiemtheoTenQuyenTaiKhoan(tenq));
                Danhsachtaikhoan(tb);
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
                lbMaQuyenThongtin.setText("");
                lbTenQuyenThongtin.setText("");
                pQuyenTaiKhoan.remove(scr);               
                JTable tb=new JTable();
                tb=BangTaiKhoan(QuyenTaiKhoanBUS.dsqtk);
                Danhsachtaikhoan(tb);
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
        
    }
    
    public void KhoiTaoFrameThemQuyen(){
        fThem=new JFrame();
        fThem.setLayout(null);
        fThem.setBounds(0, 0, 500, 350);
        fThem.setPreferredSize(new Dimension(500,350));
   
//        fThem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //---------------------------------Hiện giữa màn hình-----------------------------//
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        fThem.setLocation((int)d.getWidth()/2 - (int)fThem.getPreferredSize().getWidth()/2,
                      (int)d.getHeight()/2 - (int)fThem.getPreferredSize().getHeight()/2);
        //--------------------------------------------------------------------------------//
        
        JLabel lbTieude=new JLabel("Thêm quyền tài khoản");
        lbTieude.setFont(new Font(font,Font.BOLD,25));     
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        lbTieude.setForeground(Color.WHITE);
        lbTieude.setBounds(0, 0, 500, 35);
        lbTieude.setBackground(new Color(36,36,36));
        lbTieude.setOpaque(true);
        
        fThem.add(lbTieude);
        
        JLabel lbMaquyen=new JLabel("Mã quyền:");
        lbMaquyen.setFont(new Font(font,0,20));
        lbMaquyen.setForeground(Color.BLACK);
        lbMaquyen.setBounds(50, 80, 150, 20);
       
        fThem.add(lbMaquyen);
        
        txThemSuaMaQuyen=new JTextField();
        txThemSuaMaQuyen.setFont(new Font(font,0,20));
        txThemSuaMaQuyen.setForeground(Color.BLACK);
        txThemSuaMaQuyen.setBackground(Color.WHITE);
        txThemSuaMaQuyen.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        txThemSuaMaQuyen.setBounds(180, 80, 220, 25);
        
        fThem.add(txThemSuaMaQuyen);        
        
        JLabel lbTenQuyen=new JLabel("Tên quyền:");
        lbTenQuyen.setFont(new Font(font,0,20));
        lbTenQuyen.setForeground(Color.BLACK);
        lbTenQuyen.setBounds(50, 135, 150, 20);
       
        fThem.add(lbTenQuyen);
        
        txThemSuaTenQuyen=new JTextField();
        txThemSuaTenQuyen.setFont(new Font(font,0,20));
        txThemSuaTenQuyen.setForeground(Color.BLACK);
        txThemSuaTenQuyen.setBackground(Color.WHITE);
        txThemSuaTenQuyen.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        txThemSuaTenQuyen.setBounds(180, 135, 220, 25);
        
        fThem.add(txThemSuaTenQuyen);
        
        JButton btHoanThanh =new JButton("Hoàn thành");
        btHoanThanh.setFont(new Font(font,0,18));
        btHoanThanh.setForeground(Color.WHITE);
        btHoanThanh.setBounds(85, 200, 150, 25);
        btHoanThanh.setBackground(new Color(36,36,36));
        btHoanThanh.setFocusable(false);
        btHoanThanh.setBorderPainted(false);
        
        fThem.add(btHoanThanh);
        
        JButton btThoat =new JButton("Thoát");
        btThoat.setFont(new Font(font,0,18));
        btThoat.setForeground(Color.WHITE);
        btThoat.setBounds(255, 200, 150, 25);
        btThoat.setBackground(new Color(36,36,36));
        btThoat.setFocusable(false);
        btThoat.setBorderPainted(false);
        
        fThem.add(btThoat);
        
        btHoanThanh.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                QuyenTaiKhoanBUS qtkbus=new QuyenTaiKhoanBUS();
                QuyenTaiKhoanDTO qtk=new QuyenTaiKhoanDTO();
                qtk.setMaQuyen(txThemSuaMaQuyen.getText());
                qtk.setTenQuyen(txThemSuaTenQuyen.getText());
                if (qtkbus.KtraDulieuQuyen(qtk)==1){
                    if (qtkbus.KtraMaQuyenKhiThem(qtk)==1){
                        qtkbus.ThemQuyenTaiKhoan(qtk);
                        JOptionPane.showMessageDialog(pQuyenTaiKhoan, "Chọn chức năng cho tài khoản");
                        fThem.setVisible(false);
                        KhoiTaoFrameThemChiTietQuyen(qtk.getMaQuyen());
                    }
                }
                else
                    JOptionPane.showMessageDialog(pQuyenTaiKhoan, "Thêm thất bại");
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
                fThem.setVisible(false);            
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
        
        fThem.setVisible(true);
    }
    
    public void KhoiTaoFrameThemChiTietQuyen(String s){
        fThemChiTiet=new JFrame();
        fThemChiTiet.setLayout(null);
        fThemChiTiet.setBounds(0, 0, 510, 550);
        fThemChiTiet.setPreferredSize(new Dimension(510,550));
   
//        fThemChiTiet.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //---------------------------------Hiện giữa màn hình-----------------------------//
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        fThemChiTiet.setLocation((int)d.getWidth()/2 - (int)fThemChiTiet.getPreferredSize().getWidth()/2,
                      (int)d.getHeight()/2 - (int)fThemChiTiet.getPreferredSize().getHeight()/2);
        //--------------------------------------------------------------------------------//
        
        JLabel lbTieude=new JLabel("Thêm chi tiết quyền tài khoản");
        lbTieude.setFont(new Font(font,Font.BOLD,25));     
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        lbTieude.setForeground(Color.WHITE);
        lbTieude.setBounds(0, 0, 510, 35);
        lbTieude.setBackground(new Color(36,36,36));
        lbTieude.setOpaque(true);
        
        fThemChiTiet.add(lbTieude);
        
        JLabel lbMaquyen=new JLabel("Mã quyền:");
        lbMaquyen.setFont(new Font(font,0,20));
        lbMaquyen.setForeground(Color.BLACK);
        lbMaquyen.setBounds(50, 80, 150, 20);
       
        fThemChiTiet.add(lbMaquyen);
        
        JLabel lbThongtinMaquyen=new JLabel(s);
        lbThongtinMaquyen.setFont(new Font(font,Font.BOLD,20));
        lbThongtinMaquyen.setForeground(Color.BLACK);
        lbThongtinMaquyen.setBounds(180, 80, 220, 20);
        
        fThemChiTiet.add(lbThongtinMaquyen);        
        
        JLabel lbTenQuyen=new JLabel("Chọn chức năng:");
        lbTenQuyen.setFont(new Font(font,0,20));
        lbTenQuyen.setForeground(Color.BLACK);
        lbTenQuyen.setBounds(50, 135, 150, 20);
       
        fThemChiTiet.add(lbTenQuyen);
        
        lbThemSuaChiTietQuyen=new JLabel();
        lbThemSuaChiTietQuyen.setFont(new Font(font,0,20));
        lbThemSuaChiTietQuyen.setForeground(Color.BLACK);
        lbThemSuaChiTietQuyen.setBackground(Color.WHITE);
        lbThemSuaChiTietQuyen.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        lbThemSuaChiTietQuyen.setBounds(220, 135, 150, 25);
        lbThemSuaChiTietQuyen.setOpaque(true);
        
        fThemChiTiet.add(lbThemSuaChiTietQuyen);
        
        iconBUS ic=new iconBUS();
        ImageIcon iconChon=new ImageIcon(ic.iconchon);
        
        btChonCN=new JButton();
        btChonCN.setForeground(Color.WHITE);
        btChonCN.setBackground(new Color(36,36,36));        
        btChonCN.setIcon(iconChon);
        btChonCN.setBounds(390, 135, 35, 25);
        
        fThemChiTiet.add(btChonCN);
        
        JButton btHoanThanh =new JButton("Hoàn thành");
        btHoanThanh.setFont(new Font(font,0,18));
        btHoanThanh.setForeground(Color.WHITE);
        btHoanThanh.setBounds(85, 450, 150, 25);
        btHoanThanh.setBackground(new Color(36,36,36));
        btHoanThanh.setFocusable(false);
        btHoanThanh.setBorderPainted(false);
        
        fThemChiTiet.add(btHoanThanh);
        
        JButton btThoat =new JButton("Thoát");
        btThoat.setFont(new Font(font,0,18));
        btThoat.setForeground(Color.WHITE);
        btThoat.setBounds(255, 450, 150, 25);
        btThoat.setBackground(new Color(36,36,36));
        btThoat.setFocusable(false);
        btThoat.setBorderPainted(false);
        
        fThemChiTiet.add(btThoat);
        
        ImageIcon iconthemct=new ImageIcon(ic.iconthem);
        
        JButton btThemct =new JButton("Thêm");
        btThemct.setFont(new Font(font,0,18));
        btThemct.setIcon(iconthemct);
        btThemct.setForeground(Color.WHITE);
        btThemct.setBounds(230, 190, 120, 25);
        btThemct.setBackground(new Color(36,36,36));
        btThemct.setFocusable(false);
        btThemct.setBorderPainted(false);
        
        fThemChiTiet.add(btThemct);
        
        ImageIcon iconxoact=new ImageIcon(ic.iconxoa);
        
        JButton btXoact =new JButton("Xóa");
        btXoact.setFont(new Font(font,0,18));
        btXoact.setForeground(Color.WHITE);
        btXoact.setIcon(iconxoact);
        btXoact.setBounds(360, 190, 120, 25);
        btXoact.setBackground(new Color(36,36,36));
        btXoact.setFocusable(false);
        btXoact.setBorderPainted(false);
        
        fThemChiTiet.add(btXoact);

        tbdsctqtk=new JTable();
        tbdsctqtk.getTableHeader().setBackground(new Color(36,36,36));
        tbdsctqtk.getTableHeader().setForeground(Color.WHITE);
        tbdsctqtk.getTableHeader().setFont(new Font(font,0,18));
        tbdsctqtk.getTableHeader().setPreferredSize(new Dimension(0,30));
        tbdsctqtk.setFont(new Font(font,0,18));
        tbdsctqtk.setForeground(Color.BLACK);
        tbdsctqtk.setRowHeight(25);
        
        modelct=new DefaultTableModel();
        Vector cot=new Vector();
        cot.add("Mã chức năng");
        cot.add("Tên chức năng");
        if (modelct.getRowCount()==0){
            modelct=new DefaultTableModel(cot, 0);
        }
        ChucNangBUS cnbus=new ChucNangBUS();
        if (ChucNangBUS.dscn==null){
            cnbus.docdscn();
        }
        ChiTietQuyenBUS ctqbus=new ChiTietQuyenBUS();
        if (ChiTietQuyenBUS.dsctq==null){
            ctqbus.docdsctq();
        }
        ArrayList<ChiTietQuyenDTO> ctq1=new ArrayList<>();
        ctq1=ctqbus.LayChiTietQuyen(s);
        ArrayList<ChucNangDTO> cn1=new ArrayList<>();
        
        for (ChucNangDTO k : ChucNangBUS.dscn){
            for (ChiTietQuyenDTO k1 : ctq1){
                if (k.getMaChucNang().equals(k1.getMaChucNang()))
                    cn1.add(k);
            }
        }
        int i=1;
        for (ChucNangDTO k : cn1){
            Vector dong=new Vector();
            dong.add(k.getMaChucNang());
            dong.add(k.getTenChucNang());
            modelct.addRow(dong);
        }
        tbdsctqtk.setModel(modelct);
        tbdsctqtk.getColumnModel().getColumn(0).setMinWidth(120);
        tbdsctqtk.getColumnModel().getColumn(1).setMinWidth(250);               
        
        //--------------------Căn giữa trong bảng-----------------------//
        DefaultTableCellRenderer center=new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        tbdsctqtk.setDefaultRenderer(tbdsctqtk.getColumnClass(0), center);
        tbdsctqtk.updateUI();
        //--------------------------------------------------------------//
        
        scrct=new JScrollPane();
        scrct.setViewportView(tbdsctqtk);
        scrct.setBounds(10, 230, 480, 200);
        
        fThemChiTiet.add(scrct);
        
        btThemct.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                ChiTietQuyenBUS ctbus=new ChiTietQuyenBUS();
                ChiTietQuyenDTO ct=new ChiTietQuyenDTO();
                ChucNangBUS cn=new ChucNangBUS();      
                if (ChucNangBUS.dscn==null)
                    cn.docdscn();
                if (ChiTietQuyenBUS.dsctq==null)
                    ctbus.docdsctq();
                ct.setMaQuyen(s);
                ct.setMaChucNang(lbThemSuaChiTietQuyen.getText());
                if (ctbus.KtraDuLieu(lbThemSuaChiTietQuyen.getText())==1){
                    if (ctbus.KtraTonTai(ct)==1){
                        ctbus.ThemChiTietQuyenTaiKhoan(ct);
                        Vector dong=new Vector();                    
                        dong.add(ct.getMaChucNang());
                        dong.add(cn.Lay1ChucNangTheoMa(ct.getMaChucNang()).getTenChucNang());
                        modelct.addRow(dong);
                        tbdsctqtk.setModel(modelct);
                    }
                    else
                        JOptionPane.showMessageDialog(pQuyenTaiKhoan, "Chức năng này đã tồn tại");
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
                ChiTietQuyenBUS ctbus=new ChiTietQuyenBUS();
                ChiTietQuyenDTO ct=new ChiTietQuyenDTO();
                if (ChiTietQuyenBUS.dsctq==null){
                    ctqbus.docdsctq();
                }
                int i=tbdsctqtk.getSelectedRow();
                if (i<0)
                    JOptionPane.showMessageDialog(pQuyenTaiKhoan, "Chưa chọn chức năng cần xóa");
                else{
                    String macn=tbdsctqtk.getModel().getValueAt(i, 0).toString();
                    ct.setMaQuyen(s);
                    ct.setMaChucNang(macn);
                    ctbus.XoaChiTietQuyenTaiKhoan(ct);
                    modelct.removeRow(i);
                    tbdsctqtk.setModel(modelct);
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
        
        btHoanThanh.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                JOptionPane.showMessageDialog(pQuyenTaiKhoan, "Hoàn thành");
                fThemChiTiet.setVisible(false);
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
                fThemChiTiet.setVisible(false);            
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
        
        btChonCN.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                BangChonChucNang();
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btChonCN.setBackground(Color.DARK_GRAY);
                btChonCN.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                btChonCN.setBackground(new Color(36,36,36));
            }
        });
        
        fThemChiTiet.setVisible(true);
    }
    
    public void KhoiTaiFrameSuaQuyen(String maqtkcu){
        fSua=new JFrame();
        fSua.setLayout(null);
        fSua.setBounds(0, 0, 500, 350);
        fSua.setPreferredSize(new Dimension(500,350));
   
//        fThem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //---------------------------------Hiện giữa màn hình-----------------------------//
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        fSua.setLocation((int)d.getWidth()/2 - (int)fSua.getPreferredSize().getWidth()/2,
                      (int)d.getHeight()/2 - (int)fSua.getPreferredSize().getHeight()/2);
        //--------------------------------------------------------------------------------//
        
        JLabel lbTieude=new JLabel("Sửa quyền tài khoản");
        lbTieude.setFont(new Font(font,Font.BOLD,25));     
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        lbTieude.setForeground(Color.WHITE);
        lbTieude.setBounds(0, 0, 500, 35);
        lbTieude.setBackground(new Color(36,36,36));
        lbTieude.setOpaque(true);
        
        fSua.add(lbTieude);
        
        JLabel lbMaquyen=new JLabel("Mã quyền:");
        lbMaquyen.setFont(new Font(font,0,20));
        lbMaquyen.setForeground(Color.BLACK);
        lbMaquyen.setBounds(50, 80, 150, 20);
       
        fSua.add(lbMaquyen);
        
        txThemSuaMaQuyen=new JTextField();
        txThemSuaMaQuyen.setFont(new Font(font,0,20));
        txThemSuaMaQuyen.setForeground(Color.BLACK);
        txThemSuaMaQuyen.setBackground(Color.WHITE);
        txThemSuaMaQuyen.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        txThemSuaMaQuyen.setBounds(180, 80, 220, 25);
        txThemSuaMaQuyen.setEditable(false);
        
        fSua.add(txThemSuaMaQuyen);        
        
        JLabel lbTenQuyen=new JLabel("Tên quyền:");
        lbTenQuyen.setFont(new Font(font,0,20));
        lbTenQuyen.setForeground(Color.BLACK);
        lbTenQuyen.setBounds(50, 135, 150, 20);
       
        fSua.add(lbTenQuyen);
        
        txThemSuaTenQuyen=new JTextField();
        txThemSuaTenQuyen.setFont(new Font(font,0,20));
        txThemSuaTenQuyen.setForeground(Color.BLACK);
        txThemSuaTenQuyen.setBackground(Color.WHITE);
        txThemSuaTenQuyen.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        txThemSuaTenQuyen.setBounds(180, 135, 220, 25);
        
        fSua.add(txThemSuaTenQuyen);
        
        JButton btHoanThanh =new JButton("Hoàn thành");
        btHoanThanh.setFont(new Font(font,0,18));
        btHoanThanh.setForeground(Color.WHITE);
        btHoanThanh.setBounds(85, 200, 150, 25);
        btHoanThanh.setBackground(new Color(36,36,36));
        btHoanThanh.setFocusable(false);
        btHoanThanh.setBorderPainted(false);
        
        fSua.add(btHoanThanh);
        
        JButton btThoat =new JButton("Thoát");
        btThoat.setFont(new Font(font,0,18));
        btThoat.setForeground(Color.WHITE);
        btThoat.setBounds(255, 200, 150, 25);
        btThoat.setBackground(new Color(36,36,36));
        btThoat.setFocusable(false);
        btThoat.setBorderPainted(false);
        
        fSua.add(btThoat);
        
        btHoanThanh.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                QuyenTaiKhoanBUS qtkbus=new QuyenTaiKhoanBUS();
                ChiTietQuyenBUS ctqbus=new ChiTietQuyenBUS();
                QuyenTaiKhoanDTO qtk=new QuyenTaiKhoanDTO();
                qtk.setMaQuyen(txThemSuaMaQuyen.getText());
                qtk.setTenQuyen(txThemSuaTenQuyen.getText());
                if (qtkbus.KtraDulieuQuyen(qtk)==1){
                    if (qtkbus.KtraMaQuyenKhiSua(maqtkcu,qtk.getMaQuyen())==1){
                        qtkbus.SuaQuyenTaiKhoan(qtk);
                        JOptionPane.showMessageDialog(pQuyenTaiKhoan, "Chọn chức năng cho tài khoản");
                        fSua.setVisible(false);
                        KhoiTaoFrameThemChiTietQuyen(qtk.getMaQuyen());
                    }
                }
                else
                    JOptionPane.showMessageDialog(pQuyenTaiKhoan, "Sửa thất bại");
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
                fSua.setVisible(false);            
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
        
        fSua.setVisible(true);
    }
    
    public void BangChonChucNang(){
        JFrame fchon=new JFrame();
        fchon.setLayout(null);
        fchon.setBounds(0, 0, 550, 400);
        fchon.setPreferredSize(new Dimension(550,400));
        
        //---------------------------------Hiện giữa màn hình-----------------------------//
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        fchon.setLocation((int)d.getWidth()/2 - (int)fchon.getPreferredSize().getWidth()/2,
                      (int)d.getHeight()/2 - (int)fchon.getPreferredSize().getHeight()/2);
        //--------------------------------------------------------------------------------//
        
        JLabel lbTieude=new JLabel("Chọn chức năng");
        lbTieude.setFont(new Font(font,Font.BOLD,25));
        lbTieude.setForeground(Color.WHITE);
        lbTieude.setBackground(new Color(36,36,36));
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        lbTieude.setBounds(0, 0, 550, 35);
        lbTieude.setOpaque(true);
        
        fchon.add(lbTieude);
        
        ChucNangBUS cnbus=new ChucNangBUS();
        if (ChucNangBUS.dscn==null){
            cnbus.docdscn();
        }
        JTable tbcn=new JTable();
        tbcn.getTableHeader().setBackground(new Color(36,36,36));
        tbcn.getTableHeader().setForeground(Color.WHITE);
        tbcn.getTableHeader().setFont(new Font(font,0,18));
        tbcn.getTableHeader().setPreferredSize(new Dimension(0,30));
        tbcn.setFont(new Font(font,0,18));
        tbcn.setForeground(Color.BLACK);
        tbcn.setRowHeight(25);
        
        DefaultTableModel md=new DefaultTableModel();
        Vector cot=new Vector();
        cot.add("STT");
        cot.add("Mã chức năng");
        cot.add("Tên chức năng");
        if (md.getRowCount()==0){
            md=new DefaultTableModel(cot, 0);
        }
        int i=1;
        for (ChucNangDTO k : ChucNangBUS.dscn){
            Vector dong=new Vector();
            dong.add(i);
            dong.add(k.getMaChucNang());
            dong.add(k.getTenChucNang());
            md.addRow(dong);
            i++;
        }
        tbcn.setModel(md);
        tbcn.getColumnModel().getColumn(0).setMinWidth(50);
        tbcn.getColumnModel().getColumn(1).setMinWidth(120);
        tbcn.getColumnModel().getColumn(2).setMinWidth(250);               
        
        //--------------------Căn giữa trong bảng-----------------------//
        DefaultTableCellRenderer center=new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        tbcn.setDefaultRenderer(tbcn.getColumnClass(0), center);
        tbcn.updateUI();
        //--------------------------------------------------------------//
        
        JScrollPane sc=new JScrollPane();
        sc.setViewportView(tbcn);
        sc.setBounds(10, 140, 520, 200);
        
        fchon.add(sc);
        
        tbcn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int i=tbcn.getSelectedRow();
                String macn=tbcn.getModel().getValueAt(i, 1).toString();
                lbThemSuaChiTietQuyen.setText(macn);
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
////          QuyenTaiKhoanGUI p1=new QuyenTaiKhoanGUI();
////          p1.KhoiTaoFrameThemQuyen();
////          p1.KhoiTaoFrameThemChiTietQuyen("TN");
////            p1.BangChonChucNang();
////            p1.KhoiTaiFrameSuaQuyen("QL");
//    }
}
