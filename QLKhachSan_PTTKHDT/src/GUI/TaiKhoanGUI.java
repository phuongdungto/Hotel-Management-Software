/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.NhanVienBUS;
import BUS.QuyenTaiKhoanBUS;
import BUS.TaiKhoanBUS;
import BUS.Xulydulieu;
import BUS.iconBUS;
import DTO.NhanVienDTO;
import DTO.QuyenTaiKhoanDTO;
import DTO.TaiKhoanDTO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author minhd
 */
public class TaiKhoanGUI {
    protected String font="Times New Roman";
    protected JPanel pTaiKhoan;
    protected JLabel lbTieude;
    protected JTable tbdstk;
    protected DefaultTableModel model;
    protected JLabel lbMatKhauThongtin;
    protected JLabel lbMaTaiKhoanThongtin;
    protected JLabel lbMaNhanVienThongtin;
    protected JLabel lbMaQuyenThongtin;
    protected JLabel lbTenTaiKhoanThongtin;
    protected JButton btThem;
    protected JButton btSua;
    protected JButton btXoa;
    protected JButton btTimKiem;
    protected JButton btCapNhat;
    protected JComboBox cbKieuTK;
    protected JTextField txTKMaNV;
    protected JComboBox cbTKTenQuyen;
    protected JTextField txTKTenTaiKhoan;
    protected JScrollPane scr;    
    protected JFrame fThem;
    protected JFrame fSua;
    protected JTextField txThemSuaMaNV;
    protected JTextField txThemSuaMaQuyen;
    protected JTextField txThemSuaTenTaiKhoan;
    protected JTextField txThemSuaMatKhau;   
    protected JButton btChonNV;
    protected JButton btChonQuyenTK;
    public TaiKhoanGUI(){
        
    }
    public JPanel KhoiTaoPanel(int width, int height){
        pTaiKhoan=new JPanel();
        pTaiKhoan.setLayout(null);
        pTaiKhoan.setBounds(0, 0, width, height);
        pTaiKhoan.setPreferredSize(new Dimension(width,height));
        pTaiKhoan.setOpaque(true);
        
        lbTieude=new JLabel();
        lbTieude.setText("Tài khoản");
        lbTieude.setFont(new Font(font,Font.BOLD,26));
        lbTieude.setBounds(400, 20, 200, 50);
        lbTieude.setForeground(Color.BLACK);
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        
        pTaiKhoan.add(lbTieude);
        
        TaiKhoanBUS tkbus=new TaiKhoanBUS();
        if (TaiKhoanBUS.dstk==null){
            tkbus.docdstk();
        }
        JTable tb=new JTable();
        tb=BangTaiKhoan(TaiKhoanBUS.dstk);
        Danhsachtaikhoan(tb);
        formHienThongTin();
        CacNutChucNang();
        formTimKiem();
        ChucNangCacNut();
        
        return pTaiKhoan;
    }
    
    public JTable BangTaiKhoan(ArrayList<TaiKhoanDTO> ds){
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
        cottieude.add("Mã tài khoản");
        cottieude.add("Mã nhân viên");
        cottieude.add("Mã quyền");
        cottieude.add("Tên tài khoản");
        if (model.getRowCount()==0){
            model=new DefaultTableModel(cottieude, 0);
        }
        int i=0;
        for (TaiKhoanDTO k : ds){            
                i++;
                Vector dong=new Vector();
                dong.add(i);
                dong.add(k.getMaTK());
                dong.add(k.getMaNV());
                dong.add(k.getMaQuyen());
                dong.add(k.getUsername());
                model.addRow(dong);            
        }
        tbds.setModel(model);
        tbds.getColumnModel().getColumn(0).setMaxWidth(50);
        tbds.getColumnModel().getColumn(1).setMinWidth(110);
        tbds.getColumnModel().getColumn(2).setMinWidth(110);
        tbds.getColumnModel().getColumn(3).setMinWidth(110);
        tbds.getColumnModel().getColumn(4).setMinWidth(200);        
        
        //--------------------Căn giữa trong bảng-----------------------//
        DefaultTableCellRenderer center=new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        tbds.setDefaultRenderer(tbds.getColumnClass(0), center);
        tbds.updateUI();
        //--------------------------------------------------------------//
        
        return tbds;
    }
    
    public void Danhsachtaikhoan(JTable tb){                                        
        tbdstk=new JTable();
        tbdstk=tb;
        scr=new JScrollPane();
        scr.setViewportView(tbdstk);
        scr.setBounds(10, 250, 750, 350); // Tùy mỗi người
        
        pTaiKhoan.add(scr);    
        tbdstk.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int i=tbdstk.getSelectedRow();
                String matk=tbdstk.getModel().getValueAt(i, 1).toString();
                TaiKhoanBUS tkbus=new TaiKhoanBUS();
                TaiKhoanDTO tk=new TaiKhoanDTO();
                tk=tkbus.Lay1TaiKhoanTheoMaTK(matk);
                lbMaTaiKhoanThongtin.setText(tk.getMaTK());
                lbMaNhanVienThongtin.setText(tk.getMaNV());
                lbMaQuyenThongtin.setText(tk.getMaQuyen());
                lbTenTaiKhoanThongtin.setText(tk.getUsername());           
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
        JPanel pThongtin=new JPanel();
        pThongtin.setOpaque(true);
        pThongtin.setBounds(770, 250, 220, 350);
        pThongtin.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        pThongtin.setLayout(null);
        
        JLabel lbthongtin=new JLabel("Thông tin tài khoản");
        lbthongtin.setFont(new Font(font,Font.BOLD,20));
        lbthongtin.setForeground(Color.BLACK);
        lbthongtin.setBounds(20, 10, 180, 20);
        lbthongtin.setHorizontalAlignment(JLabel.CENTER);
        
        pThongtin.add(lbthongtin);
        
        JLabel lbMaTaiKhoan=new JLabel("Mã tài khoản:");
        lbMaTaiKhoan.setFont(new Font(font,0,18));
        lbMaTaiKhoan.setBounds(20, 50, 130, 20);
        lbMaTaiKhoan.setForeground(Color.BLACK);
        
        pThongtin.add(lbMaTaiKhoan);
        
        
        lbMaTaiKhoanThongtin=new JLabel();
        lbMaTaiKhoanThongtin.setForeground(Color.BLACK);
        lbMaTaiKhoanThongtin.setBounds(20, 80, 180, 20);
        lbMaTaiKhoanThongtin.setFont(new Font(font,Font.BOLD,18));
        lbMaTaiKhoanThongtin.setHorizontalAlignment(JLabel.CENTER);   
        lbMaTaiKhoanThongtin.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        
        pThongtin.add(lbMaTaiKhoanThongtin);
        
        JLabel lbMaNhanVien=new JLabel("Mã nhân viên:");
        lbMaNhanVien.setFont(new Font(font,0,18));
        lbMaNhanVien.setBounds(20, 110, 130, 20);
        lbMaNhanVien.setForeground(Color.BLACK);
        
        pThongtin.add(lbMaNhanVien);
        
        
        lbMaNhanVienThongtin=new JLabel();
        lbMaNhanVienThongtin.setForeground(Color.BLACK);
        lbMaNhanVienThongtin.setBounds(20, 140, 180, 20);
        lbMaNhanVienThongtin.setFont(new Font(font,Font.BOLD,18));
        lbMaNhanVienThongtin.setHorizontalAlignment(JLabel.CENTER);   
        lbMaNhanVienThongtin.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        
        pThongtin.add(lbMaNhanVienThongtin);
        
        JLabel lbMaQuyen=new JLabel("Mã quyền:");
        lbMaQuyen.setFont(new Font(font,0,18));
        lbMaQuyen.setBounds(20, 170, 130, 20);
        lbMaQuyen.setForeground(Color.BLACK);
        
        pThongtin.add(lbMaQuyen);
        
        
        lbMaQuyenThongtin=new JLabel();
        lbMaQuyenThongtin.setForeground(Color.BLACK);
        lbMaQuyenThongtin.setBounds(20, 200, 180, 20);
        lbMaQuyenThongtin.setFont(new Font(font,Font.BOLD,18));
        lbMaQuyenThongtin.setHorizontalAlignment(JLabel.CENTER);   
        lbMaQuyenThongtin.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        
        pThongtin.add(lbMaQuyenThongtin);
        
        JLabel lbTenTaiKhoan=new JLabel("Tên tài khoản:");
        lbTenTaiKhoan.setFont(new Font(font,0,18));
        lbTenTaiKhoan.setBounds(20, 230, 130, 20);
        lbTenTaiKhoan.setForeground(Color.BLACK);
        
        pThongtin.add(lbTenTaiKhoan);
        
        
        lbTenTaiKhoanThongtin=new JLabel();
        lbTenTaiKhoanThongtin.setForeground(Color.BLACK);
        lbTenTaiKhoanThongtin.setBounds(20, 260, 180, 20);
        lbTenTaiKhoanThongtin.setFont(new Font(font,Font.BOLD,18));
        lbTenTaiKhoanThongtin.setHorizontalAlignment(JLabel.CENTER);   
        lbTenTaiKhoanThongtin.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        
        pThongtin.add(lbTenTaiKhoanThongtin);                
        
        pTaiKhoan.add(pThongtin);
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
        
        pTaiKhoan.add(btThem);
        
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
        
        pTaiKhoan.add(btSua);
        
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
        
        pTaiKhoan.add(btXoa);
        
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
        
        pTaiKhoan.add(btCapNhat);
        
        ImageIcon iconTimkiem=new ImageIcon(ic.icontimkiem);
        btTimKiem=new JButton();
        btTimKiem.setText("Tìm kiếm");
        btTimKiem.setIcon(iconTimkiem);
        btTimKiem.setFont(new Font(font,0,18));
        btTimKiem.setBackground(new Color(36,36,36));
        btTimKiem.setForeground(Color.WHITE);
        btTimKiem.setFocusable(false);
        btTimKiem.setBorderPainted(false);
        btTimKiem.setBounds(380, 140, 150, 30);
        
        pTaiKhoan.add(btTimKiem);
    }
    
    public void formTimKiem(){
        JLabel lbTKMa=new JLabel("Nhập mã nhân viên:");
        lbTKMa.setFont(new Font(font,0,18));
        lbTKMa.setForeground(Color.BLACK);
        lbTKMa.setBounds(20, 90, 150, 20);            
        
        pTaiKhoan.add(lbTKMa);
        
        txTKMaNV=new JTextField();
        txTKMaNV.setFont(new Font(font,0,18));
        txTKMaNV.setForeground(Color.BLACK);
        txTKMaNV.setBounds(200, 90, 150, 25);
        txTKMaNV.setEnabled(false);
        
        pTaiKhoan.add(txTKMaNV);
        
        JLabel lbTKTenTaiKhoan=new JLabel("Nhập tên tài khoản:");
        lbTKTenTaiKhoan.setFont(new Font(font,0,18));
        lbTKTenTaiKhoan.setForeground(Color.BLACK);
        lbTKTenTaiKhoan.setBounds(20, 140, 150, 20);            
        
        pTaiKhoan.add(lbTKTenTaiKhoan);
        
        txTKTenTaiKhoan=new JTextField();
        txTKTenTaiKhoan.setFont(new Font(font,0,18));
        txTKTenTaiKhoan.setForeground(Color.BLACK);
        txTKTenTaiKhoan.setBounds(200, 140, 150, 25);
        txTKTenTaiKhoan.setEnabled(false);
        
        pTaiKhoan.add(txTKTenTaiKhoan);               
        
        String[] kieutk={"Chọn kiểu tìm kiếm","Tìm kiếm theo mã nhân viên", "Tìm kiếm theo tên tài khoản",
        "Tìm kiếm theo quyền tài khoản"};
        
        cbKieuTK=new JComboBox(kieutk);
        cbKieuTK.setSelectedIndex(0);
        cbKieuTK.setBounds(380, 90, 250, 25);
        cbKieuTK.setBackground(Color.WHITE);
        
        pTaiKhoan.add(cbKieuTK);
        
        QuyenTaiKhoanBUS tk=new QuyenTaiKhoanBUS();
        if (QuyenTaiKhoanBUS.dsqtk==null){
            tk.docdsqtk();
        }
        
        String[] tenquyen=new String[QuyenTaiKhoanBUS.dsqtk.size()+1];
        tenquyen[0]="Chọn tên quyền";
        int i=1;
        for (QuyenTaiKhoanDTO k : QuyenTaiKhoanBUS.dsqtk){
            tenquyen[i]=k.getTenQuyen();
            i++;
        }
        
        JLabel lbTKQuyenTaiKhoan=new JLabel("Quyền tài khoản:");
        lbTKQuyenTaiKhoan.setFont(new Font(font,0,18));
        lbTKQuyenTaiKhoan.setForeground(Color.BLACK);
        lbTKQuyenTaiKhoan.setBounds(20, 190, 150, 20);            
        
        pTaiKhoan.add(lbTKQuyenTaiKhoan);
        
        cbTKTenQuyen=new JComboBox(tenquyen);
        cbTKTenQuyen.setSelectedIndex(0);
        cbTKTenQuyen.setBounds(200, 190, 150, 25);
        cbTKTenQuyen.setBackground(Color.WHITE);
        cbTKTenQuyen.setEnabled(false);
        
        pTaiKhoan.add(cbTKTenQuyen);
                              
    }
    
    public void ChucNangCacNut(){                
        
        cbKieuTK.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent arg0) {
                int k=cbKieuTK.getSelectedIndex();
                if (k==0){
                    txTKMaNV.setEnabled(false);
                    txTKTenTaiKhoan.setEnabled(false);
                    cbTKTenQuyen.setEnabled(false);
                }
                if (k==1){
                    txTKMaNV.setEnabled(true);
                    txTKTenTaiKhoan.setEnabled(false);
                    cbTKTenQuyen.setEnabled(false);
                }
                if (k==2){
                    txTKMaNV.setEnabled(false);
                    txTKTenTaiKhoan.setEnabled(true);
                    cbTKTenQuyen.setEnabled(false);
                }
                if (k==3){
                    txTKMaNV.setEnabled(false);
                    txTKTenTaiKhoan.setEnabled(false);
                    cbTKTenQuyen.setEnabled(true);
                }
            }
        });
                
        btThem.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {  
                KhoiTaoJFrameThem(); 
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
                int i=tbdstk.getSelectedRow();
                if (i<0)
                    JOptionPane.showMessageDialog(pTaiKhoan, "Chưa chọn tài khoản cần sửa");
                else{                    
                    String matk=tbdstk.getModel().getValueAt(i, 1).toString();
                    String tentkcu=tbdstk.getModel().getValueAt(i, 4).toString();
                    KhoiTaoJFrameSua(matk,tentkcu);
                    TaiKhoanBUS tkbus=new TaiKhoanBUS();
                    TaiKhoanDTO tk=new TaiKhoanDTO();
                    tk=tkbus.Lay1TaiKhoanTheoMaTK(matk);
                    txThemSuaMaNV.setText(tk.getMaNV());
                    txThemSuaMaQuyen.setText(tk.getMaQuyen());
                    txThemSuaTenTaiKhoan.setText(tk.getUsername());
                    txThemSuaMatKhau.setText(tk.getPassword());
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
                int i=tbdstk.getSelectedRow();
                if (i<0)
                    JOptionPane.showMessageDialog(pTaiKhoan, "Chưa chọn tài khoản cần xóa");
                else{
                    int input=JOptionPane.showConfirmDialog(pTaiKhoan, "Bạn có chắc muốn xóa", "Xóa tài khoản", JOptionPane.YES_NO_OPTION);
                    if (input==0){
                        String matk=tbdstk.getModel().getValueAt(i, 1).toString();
                        TaiKhoanBUS tkbus=new TaiKhoanBUS();
                        if (tkbus.XoaTaiKhoan(matk)==1){
                            JOptionPane.showMessageDialog(pTaiKhoan, "Xóa thành công");
                            model.removeRow(i);
                            tbdstk.setModel(model);
                        }
                        else
                            JOptionPane.showMessageDialog(pTaiKhoan, "Xóa thất bại");
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
                if (k==0){
                    JOptionPane.showMessageDialog(pTaiKhoan, "Chưa chọn kiểu tìm kiếm");
                }
                if (k==1){
                    lbMaTaiKhoanThongtin.setText("");
                    lbMaNhanVienThongtin.setText("");
                    lbMaQuyenThongtin.setText("");
                    lbTenTaiKhoanThongtin.setText("");
                    pTaiKhoan.remove(scr);      
                    String manv = txTKMaNV.getText();
                    TaiKhoanBUS tkbus=new TaiKhoanBUS();
                    JTable tb=new JTable();
                    tb=BangTaiKhoan(tkbus.TimkiemtheoMaNV(manv));
                    Danhsachtaikhoan(tb);
                }
                if (k==2){
                    lbMaTaiKhoanThongtin.setText("");
                    lbMaNhanVienThongtin.setText("");
                    lbMaQuyenThongtin.setText("");
                    lbTenTaiKhoanThongtin.setText("");
                    pTaiKhoan.remove(scr);      
                    String tentk = txTKTenTaiKhoan.getText();
                    TaiKhoanBUS tkbus=new TaiKhoanBUS();
                    JTable tb=new JTable();
                    tb=BangTaiKhoan(tkbus.TimkiemtheoTenTaiKhoan(tentk));
                    Danhsachtaikhoan(tb);
                }
                if (k==3){
                    QuyenTaiKhoanBUS quyenbus=new QuyenTaiKhoanBUS();
                    lbMaTaiKhoanThongtin.setText("");
                    lbMaNhanVienThongtin.setText("");
                    lbMaQuyenThongtin.setText("");
                    lbTenTaiKhoanThongtin.setText("");
                    pTaiKhoan.remove(scr);      
                    String[] tenquyen = new String[1];
                    tenquyen[0]=(String) cbTKTenQuyen.getSelectedItem();
                    String maquyen=quyenbus.LayMaQuyenTaiKhoan(tenquyen[0]);
                    TaiKhoanBUS tkbus=new TaiKhoanBUS();
                    JTable tb=new JTable();
                    tb=BangTaiKhoan(tkbus.TimkiemtheoMaQuyenTaiKhoan(maquyen));
                    Danhsachtaikhoan(tb);
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
                lbMaTaiKhoanThongtin.setText("");
                lbMaNhanVienThongtin.setText("");
                lbMaQuyenThongtin.setText("");
                lbTenTaiKhoanThongtin.setText("");
                txTKMaNV.setText("");
                txTKTenTaiKhoan.setText("");
                cbTKTenQuyen.setSelectedIndex(0);
                txTKMaNV.setEnabled(false);
                txTKTenTaiKhoan.setEnabled(false);
                cbTKTenQuyen.setEnabled(false);
                pTaiKhoan.remove(scr);               
                JTable tb=new JTable();
                tb=BangTaiKhoan(TaiKhoanBUS.dstk);
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
    
    public void KhoiTaoJFrameThem(){
        fThem=new JFrame();
        fThem.setLayout(null);
        fThem.setBounds(0, 0, 500, 420);
        fThem.setPreferredSize(new Dimension(500,420));
   
//        fThemSua.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //---------------------------------Hiện giữa màn hình-----------------------------//
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        fThem.setLocation((int)d.getWidth()/2 - (int)fThem.getPreferredSize().getWidth()/2,
                      (int)d.getHeight()/2 - (int)fThem.getPreferredSize().getHeight()/2);
        //--------------------------------------------------------------------------------//
        
        JLabel lbTieude=new JLabel("Thêm tài khoản");
        lbTieude.setFont(new Font(font,Font.BOLD,25));        
        lbTieude.setBounds(0, 0, 500, 35);
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        lbTieude.setForeground(Color.WHITE);
        lbTieude.setBackground(new Color(36,36,36));
        lbTieude.setOpaque(true);
        
        fThem.add(lbTieude);
        
        JLabel lbManv=new JLabel("Mã nhân viên:");
        lbManv.setFont(new Font(font,0,20));
        lbManv.setForeground(Color.BLACK);
        lbManv.setBounds(50, 80, 150, 20);
       
        fThem.add(lbManv);
        
        txThemSuaMaNV=new JTextField();
        txThemSuaMaNV.setFont(new Font(font,0,20));
        txThemSuaMaNV.setForeground(Color.BLACK);
        txThemSuaMaNV.setBackground(Color.WHITE);
        txThemSuaMaNV.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        txThemSuaMaNV.setBounds(240, 80, 150, 25);
        txThemSuaMaNV.setEditable(false);
        
        fThem.add(txThemSuaMaNV);
        
        iconBUS ic=new iconBUS();
        ImageIcon iconChon=new ImageIcon(ic.iconchon);
        
        btChonNV=new JButton();
        btChonNV.setForeground(Color.WHITE);
        btChonNV.setBackground(new Color(36,36,36));        
        btChonNV.setIcon(iconChon);
        btChonNV.setBounds(400, 80, 35, 25);
        
        fThem.add(btChonNV);
        
        JLabel lbMaQuyentk=new JLabel("Mã quyền:");
        lbMaQuyentk.setFont(new Font(font,0,20));
        lbMaQuyentk.setForeground(Color.BLACK);
        lbMaQuyentk.setBounds(50, 135, 150, 20);
       
        fThem.add(lbMaQuyentk);
        
        txThemSuaMaQuyen=new JTextField();
        txThemSuaMaQuyen.setFont(new Font(font,0,20));
        txThemSuaMaQuyen.setForeground(Color.BLACK);
        txThemSuaMaQuyen.setBackground(Color.WHITE);
        txThemSuaMaQuyen.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        txThemSuaMaQuyen.setBounds(240, 135, 150, 25);
        txThemSuaMaQuyen.setEditable(false);
        
        fThem.add(txThemSuaMaQuyen);
        
        btChonQuyenTK=new JButton();
        btChonQuyenTK.setForeground(Color.WHITE);
        btChonQuyenTK.setBackground(new Color(36,36,36));        
        btChonQuyenTK.setIcon(iconChon);
        btChonQuyenTK.setBounds(400, 135, 35, 25);
        
        fThem.add(btChonQuyenTK);
        
        JLabel lbTenTK=new JLabel("Tên tài khoản:");
        lbTenTK.setFont(new Font(font,0,20));
        lbTenTK.setForeground(Color.BLACK);
        lbTenTK.setBounds(50, 190, 150, 20);
       
        fThem.add(lbTenTK);
        
        txThemSuaTenTaiKhoan=new JTextField();
        txThemSuaTenTaiKhoan.setFont(new Font(font,0,20));
        txThemSuaTenTaiKhoan.setForeground(Color.BLACK);
        txThemSuaTenTaiKhoan.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        txThemSuaTenTaiKhoan.setBounds(240, 190, 150, 25);
        
        fThem.add(txThemSuaTenTaiKhoan);
        
        JLabel lbMatKhau=new JLabel("Mật khẩu (mặc định):");
        lbMatKhau.setFont(new Font(font,0,20));
        lbMatKhau.setForeground(Color.BLACK);
        lbMatKhau.setBounds(50, 245, 180, 20);
       
        fThem.add(lbMatKhau);
        
        txThemSuaMatKhau=new JTextField("8Z4S9qgaA0kSefx97tc3qg==");
        txThemSuaMatKhau.setFont(new Font(font,0,20));
        txThemSuaMatKhau.setForeground(Color.BLACK);
        txThemSuaMatKhau.setBackground(Color.WHITE);
        txThemSuaMatKhau.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        txThemSuaMatKhau.setBounds(240, 245, 150, 25);
        txThemSuaMatKhau.setEditable(false);
        
        fThem.add(txThemSuaMatKhau);
        
        JButton btHoanThanh =new JButton("Hoàn thành");
        btHoanThanh.setFont(new Font(font,0,18));
        btHoanThanh.setForeground(Color.WHITE);
        btHoanThanh.setBounds(90, 310, 150, 25);
        btHoanThanh.setBackground(new Color(36,36,36));
        btHoanThanh.setFocusable(false);
        btHoanThanh.setBorderPainted(false);
        
        fThem.add(btHoanThanh);
        
        JButton btThoat =new JButton("Thoát");
        btThoat.setFont(new Font(font,0,18));
        btThoat.setForeground(Color.WHITE);
        btThoat.setBounds(260, 310, 150, 25);
        btThoat.setBackground(new Color(36,36,36));
        btThoat.setFocusable(false);
        btThoat.setBorderPainted(false);
        
        fThem.add(btThoat);
        
        btHoanThanh.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                TaiKhoanBUS tkbus=new TaiKhoanBUS();
                Xulydulieu dl=new Xulydulieu();
                String matk=dl.TaoMaMoi("TK", tkbus.DemSoluongTaiKhoan()+1);
                TaiKhoanDTO tk=new TaiKhoanDTO();
                tk.setMaTK(matk);
                tk.setMaNV(txThemSuaMaNV.getText());
                tk.setMaQuyen(txThemSuaMaQuyen.getText());
                tk.setUsername(txThemSuaTenTaiKhoan.getText());
                tk.setPassword(txThemSuaMatKhau.getText());
                tk.setTinhTrang(1);
                if (tkbus.KtradulieuTaiKhoan(tk)==1){
                    if (tkbus.KtraTenTaiKhoanKhiThem(tk)==1){
                        if (tkbus.ThemTaiKhoan(tk)==1){
                            JOptionPane.showMessageDialog(pTaiKhoan, "Thêm thành công"); 
                            fThem.setVisible(false);                        
                        }
                        else
                            JOptionPane.showMessageDialog(pTaiKhoan, "Thêm thất bại"); 
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
        
        btChonQuyenTK.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                BangChonQuyen();
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btChonQuyenTK.setBackground(Color.DARK_GRAY);
                btChonQuyenTK.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                btChonQuyenTK.setBackground(new Color(36,36,36));
            }
        });
        
        fThem.setVisible(true);
    }
    
    public void KhoiTaoJFrameSua(String matk,String tentkcu){
        fSua=new JFrame();
        fSua.setLayout(null);
        fSua.setBounds(0, 0, 500, 450);
        fSua.setPreferredSize(new Dimension(500,450));
//        fThemSua.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //---------------------------------Hiện giữa màn hình-----------------------------//
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        fSua.setLocation((int)d.getWidth()/2 - (int)fSua.getPreferredSize().getWidth()/2,
                      (int)d.getHeight()/2 - (int)fSua.getPreferredSize().getHeight()/2);
        //--------------------------------------------------------------------------------//
        
        JLabel lbTieude=new JLabel("Sửa tài khoản");
        lbTieude.setFont(new Font(font,Font.BOLD,25));        
        lbTieude.setBounds(0, 0, 500, 35);
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        lbTieude.setForeground(Color.WHITE);
        lbTieude.setBackground(new Color(36,36,36));
        lbTieude.setOpaque(true);
        
        fSua.add(lbTieude);
        
        JLabel lbMatk=new JLabel("Mã tài khoản:");
        lbMatk.setFont(new Font(font,0,20));
        lbMatk.setForeground(Color.BLACK);
        lbMatk.setBounds(50, 80, 150, 20);
       
        fSua.add(lbMatk);
        
        JLabel lbMatk1=new JLabel(matk);
        lbMatk1.setFont(new Font(font,0,20));
        lbMatk1.setForeground(Color.BLACK);
        lbMatk1.setBounds(240, 80, 150, 20);
       
        fSua.add(lbMatk1);
        
        JLabel lbManv=new JLabel("Mã nhân viên:");
        lbManv.setFont(new Font(font,0,20));
        lbManv.setForeground(Color.BLACK);
        lbManv.setBounds(50, 135, 150, 20);
       
        fSua.add(lbManv);
        
        txThemSuaMaNV=new JTextField();
        txThemSuaMaNV.setFont(new Font(font,0,20));
        txThemSuaMaNV.setForeground(Color.BLACK);
        txThemSuaMaNV.setBackground(Color.WHITE);
        txThemSuaMaNV.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        txThemSuaMaNV.setBounds(240, 135, 150, 25);
        txThemSuaMaNV.setEditable(false);
        
        fSua.add(txThemSuaMaNV);
        
        iconBUS ic=new iconBUS();
        ImageIcon iconChon=new ImageIcon(ic.iconchon);
        
        btChonNV=new JButton();
        btChonNV.setForeground(Color.WHITE);
        btChonNV.setBackground(new Color(36,36,36));        
        btChonNV.setIcon(iconChon);
        btChonNV.setBounds(400, 135, 35, 25);
        
        fSua.add(btChonNV);
        
        JLabel lbMaQuyentk=new JLabel("Mã quyền:");
        lbMaQuyentk.setFont(new Font(font,0,20));
        lbMaQuyentk.setForeground(Color.BLACK);
        lbMaQuyentk.setBounds(50, 190, 150, 20);
       
        fSua.add(lbMaQuyentk);
        
        txThemSuaMaQuyen=new JTextField();
        txThemSuaMaQuyen.setFont(new Font(font,0,20));
        txThemSuaMaQuyen.setForeground(Color.BLACK);
        txThemSuaMaQuyen.setBackground(Color.WHITE);
        txThemSuaMaQuyen.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        txThemSuaMaQuyen.setBounds(240, 190, 150, 25);
        txThemSuaMaQuyen.setEditable(false);
        
        fSua.add(txThemSuaMaQuyen);
        
        btChonQuyenTK=new JButton();
        btChonQuyenTK.setForeground(Color.WHITE);
        btChonQuyenTK.setBackground(new Color(36,36,36));        
        btChonQuyenTK.setIcon(iconChon);
        btChonQuyenTK.setBounds(400, 190, 35, 25);
        
        fSua.add(btChonQuyenTK);
        
        JLabel lbTenTK=new JLabel("Tên tài khoản:");
        lbTenTK.setFont(new Font(font,0,20));
        lbTenTK.setForeground(Color.BLACK);
        lbTenTK.setBounds(50, 245, 150, 20);
       
        fSua.add(lbTenTK);
        
        txThemSuaTenTaiKhoan=new JTextField();
        txThemSuaTenTaiKhoan.setFont(new Font(font,0,20));
        txThemSuaTenTaiKhoan.setForeground(Color.BLACK);
        txThemSuaTenTaiKhoan.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        txThemSuaTenTaiKhoan.setBounds(240, 245, 150, 25);
        
        fSua.add(txThemSuaTenTaiKhoan);
        
        JLabel lbMatKhau=new JLabel("Mật khẩu (mặc định):");
        lbMatKhau.setFont(new Font(font,0,20));
        lbMatKhau.setForeground(Color.BLACK);
        lbMatKhau.setBounds(50, 300, 180, 20);
       
        fSua.add(lbMatKhau);
        
        txThemSuaMatKhau=new JTextField();
        txThemSuaMatKhau.setFont(new Font(font,0,20));
        txThemSuaMatKhau.setForeground(Color.BLACK);
        txThemSuaMatKhau.setBackground(Color.WHITE);
        txThemSuaMatKhau.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        txThemSuaMatKhau.setBounds(240, 300, 150, 25);
        txThemSuaMatKhau.setEditable(false);
        
        fSua.add(txThemSuaMatKhau);
        
        ImageIcon icontailai=new ImageIcon(ic.icontailai);
        
        JButton btTailai=new JButton();
        btTailai.setForeground(Color.WHITE);
        btTailai.setBackground(new Color(36,36,36));        
        btTailai.setIcon(icontailai);
        btTailai.setBounds(400, 300, 35, 25);
        
        fSua.add(btTailai);
        
        JButton btHoanThanh =new JButton("Hoàn thành");
        btHoanThanh.setFont(new Font(font,0,18));
        btHoanThanh.setForeground(Color.WHITE);
        btHoanThanh.setBounds(90, 365, 150, 25);
        btHoanThanh.setBackground(new Color(36,36,36));
        btHoanThanh.setFocusable(false);
        btHoanThanh.setBorderPainted(false);
        
        fSua.add(btHoanThanh);
        
        JButton btThoat =new JButton("Thoát");
        btThoat.setFont(new Font(font,0,18));
        btThoat.setForeground(Color.WHITE);
        btThoat.setBounds(260, 365, 150, 25);
        btThoat.setBackground(new Color(36,36,36));
        btThoat.setFocusable(false);
        btThoat.setBorderPainted(false);
        
        fSua.add(btThoat);
        
        btHoanThanh.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                TaiKhoanBUS tkbus=new TaiKhoanBUS();
                TaiKhoanDTO tk=new TaiKhoanDTO();
                tk.setMaTK(matk);
                tk.setMaNV(txThemSuaMaNV.getText());
                tk.setMaQuyen(txThemSuaMaQuyen.getText());
                tk.setUsername(txThemSuaTenTaiKhoan.getText());
                tk.setPassword(txThemSuaMatKhau.getText());
                tk.setTinhTrang(1);
                System.out.println(tentkcu);
                System.out.println(tk.getUsername());
                if (tkbus.KtradulieuTaiKhoan(tk)==1){
                    if (tkbus.KtraTenTaiKhoanKhiSua(tentkcu, tk.getUsername())==1){
                        if (tkbus.SuaTaiKhoan(tk)==1){
                            JOptionPane.showMessageDialog(pTaiKhoan, "Sửa thành công");                            
                            fSua.setVisible(false);                        
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
        
        btTailai.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                txThemSuaMatKhau.setText("8Z4S9qgaA0kSefx97tc3qg==");
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btTailai.setBackground(Color.DARK_GRAY);
                btTailai.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                btTailai.setBackground(new Color(36,36,36));
            }
        });
        
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
        
        btChonQuyenTK.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                BangChonQuyen();
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btChonQuyenTK.setBackground(Color.DARK_GRAY);
                btChonQuyenTK.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                btChonQuyenTK.setBackground(new Color(36,36,36));
            }
        });
        
        fSua.setVisible(true);
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
                txThemSuaMaNV.setText(manv);
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
    
    public void BangChonQuyen(){
        JFrame fchon=new JFrame();
        fchon.setLayout(null);
        fchon.setBounds(0, 0, 550, 400);
        fchon.setPreferredSize(new Dimension(550,400));
        
        //---------------------------------Hiện giữa màn hình-----------------------------//
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        fchon.setLocation((int)d.getWidth()/2 - (int)fchon.getPreferredSize().getWidth()/2,
                      (int)d.getHeight()/2 - (int)fchon.getPreferredSize().getHeight()/2);
        //--------------------------------------------------------------------------------//
        
        JLabel lbTieude=new JLabel("Chọn quyền tài khoản");
        lbTieude.setFont(new Font(font,Font.BOLD,25));
        lbTieude.setForeground(Color.WHITE);
        lbTieude.setBackground(new Color(36,36,36));
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        lbTieude.setBounds(0, 0, 550, 35);
        lbTieude.setOpaque(true);
        
        fchon.add(lbTieude);
        
        QuyenTaiKhoanBUS qbus=new QuyenTaiKhoanBUS();
        if (QuyenTaiKhoanBUS.dsqtk==null){
            qbus.docdsqtk();
        }
        JTable tbq=new JTable();
        tbq.getTableHeader().setBackground(new Color(36,36,36));
        tbq.getTableHeader().setForeground(Color.WHITE);
        tbq.getTableHeader().setFont(new Font(font,0,18));
        tbq.getTableHeader().setPreferredSize(new Dimension(0,30));
        tbq.setFont(new Font(font,0,18));
        tbq.setForeground(Color.BLACK);
        tbq.setRowHeight(25);
        
        DefaultTableModel md=new DefaultTableModel();
        Vector cot=new Vector();
        cot.add("STT");
        cot.add("Mã quyền");
        cot.add("Tên quyền");
        if (md.getRowCount()==0){
            md=new DefaultTableModel(cot, 0);
        }
        int i=1;
        for (QuyenTaiKhoanDTO k : QuyenTaiKhoanBUS.dsqtk){
            Vector dong=new Vector();
            dong.add(i);
            dong.add(k.getMaQuyen());
            dong.add(k.getTenQuyen());           
            md.addRow(dong);
            i++;
        }
        tbq.setModel(md);
        tbq.getColumnModel().getColumn(0).setMinWidth(50);
        tbq.getColumnModel().getColumn(1).setMinWidth(120);
        tbq.getColumnModel().getColumn(2).setMinWidth(250);                
        
        //--------------------Căn giữa trong bảng-----------------------//
        DefaultTableCellRenderer center=new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        tbq.setDefaultRenderer(tbq.getColumnClass(0), center);
        tbq.updateUI();
        //--------------------------------------------------------------//
        
        JScrollPane sc=new JScrollPane();
        sc.setViewportView(tbq);
        sc.setBounds(10, 140, 520, 200);
        
        fchon.add(sc);
        
        tbq.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int i=tbq.getSelectedRow();
                String maq=tbq.getModel().getValueAt(i, 1).toString();
                txThemSuaMaQuyen.setText(maq);
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
//    }
}
