/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import BUS.KhachHangBUS;
import BUS.Xulydulieu;
import DTO.KhachHangDTO;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Duy
 */
public class KhachHangGUI {
    protected JFrame fThem;
    protected JFrame fSua;
    protected JFrame fXemchitiet;
    protected JPanel pKhachHang;
    protected JPanel pBangdskh;
    protected JPanel pTimkiem;
    protected JLabel lbTieude, lbCmnd, lbTenkhachhang, lbSodienthoai, lbGioitinh;
    protected JLabel lbTSMakh, lbTSHokh, lbTSTenkh, lbTSSdt, lbTSNgaysinh, lbTSGioitinh, lbTSDiachi;
    protected JLabel lbXTTMakh, lbXTTHokh, lbXTTTenkh, lbXTTSdt, lbXTTNgaysinh, lbXTTGioitinh;
    protected JTable tbdskh;
    protected JTextField txtCmnd, txtTenkhachhang, txtSodienthoai;
    protected JTextField txtTSHokh, txtTSTenkh, txtTSMakh, txtTSSdt;
    protected JTextArea txtTSDiachi;
    protected JTextArea lbXTTDiachi;
    protected JComboBox cbxTimkiem, cbxGioitinh, cbxTSGioitinh;
    protected JButton btTimKiem,btXemChiTiet,btThem,btSua,btXoa,btCapNhat;
    protected JScrollPane sc, sc1;
    protected String DsGioitinh[] = { "Tất cả", "Nam", "Nữ" };
    protected String DsTimkiem[] = { "Chọn kiểu cần tìm kiếm", "Tìm kiếm theo chứng minh nhân dân", "Tìm kiếm theo họ và tên", "Tìm kiếm theo số "
                + "điện thoại", "Tìm kiếm theo giới tính"};
    protected String DsTSGioitinh[] = { "Nam", "Nữ" };
    protected JTextFieldDateEditor editor;
    protected JDateChooser txtTSDate;
    protected Color colorButton = new Color(36,36,36);
    protected Color colorLaybel = new Color(238, 238, 238);
    public DefaultTableModel model;
    public JPanel KhoiTaoPanel(int width, int height) {
        pKhachHang = new JPanel();
        pKhachHang.setLayout(null);
        pKhachHang.setBounds(0, 0, width, height);
        pKhachHang.setPreferredSize(new Dimension(width, height));
        pKhachHang.setOpaque(true);
        
        lbTieude = new JLabel();
        lbTieude.setText("Khách hàng");
        lbTieude.setFont(new Font("Time New Roman", Font.BOLD, 26));
        lbTieude.setBounds(0, 0, 1000, 50);
        lbTieude.setForeground(Color.BLACK);
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        
        pKhachHang.add(lbTieude);
        
        pBangdskh = Bangdskh(1000, 450);
        pKhachHang.add(pBangdskh);
        
        pTimkiem = Timkiem(1000,150);
        pKhachHang.add(pTimkiem);
        
        Chucnangcacnut();
        
        return pKhachHang;
    }
    public JPanel Bangdskh(int width, int height) {
        pBangdskh=new JPanel();
        pBangdskh.setLayout(null);
        pBangdskh.setBounds(0, 200, width, height);
        pBangdskh.setPreferredSize(new Dimension(width,height));
        pBangdskh.setOpaque(true);
        KhachHangBUS khbus = new KhachHangBUS();
        if(KhachHangBUS.dskh==null) {
            khbus.docdskh();
        }
        tbdskh = Danhsachkhachhang(KhachHangBUS.dskh);
        sc = new JScrollPane(tbdskh);
        sc.setViewportView(tbdskh);
        sc.setBounds(10, 20, 980, 370);
        pBangdskh.add(sc);
        
        ImageIcon iconCapNhat = new ImageIcon("src/img/update.png");
        btCapNhat = new JButton("Cập nhật",iconCapNhat);
        btCapNhat.setBounds(20, 400, 160, 35);
        btCapNhat.setBackground(colorButton);
        btCapNhat.setForeground(Color.WHITE);
        btCapNhat.setBorderPainted(false);
        btCapNhat.setFocusPainted(false);
        pBangdskh.add(btCapNhat);
        
        ImageIcon iconxemchitiet = new ImageIcon("src/img/eye.png");
        btXemChiTiet = new JButton("Xem chi tiết",iconxemchitiet);
        btXemChiTiet.setBounds(780, 400, 160, 35);
        btXemChiTiet.setBackground(colorButton);
        btXemChiTiet.setForeground(Color.WHITE);
        btXemChiTiet.setBorderPainted(false);
        btXemChiTiet.setFocusPainted(false);
        pBangdskh.add(btXemChiTiet);
        return pBangdskh;
    }
    public JTable Danhsachkhachhang(ArrayList<KhachHangDTO> dskh) {
        tbdskh = new JTable();
        model = new DefaultTableModel();
        tbdskh.getTableHeader().setBackground(colorButton);
        tbdskh.getTableHeader().setForeground(Color.WHITE);
        tbdskh.getTableHeader().setFont(new Font("Time New Roman", 0, 18));
        tbdskh.getTableHeader().setPreferredSize(new Dimension(0,30));
        tbdskh.setFont(new Font("Times New Roman",0,18));
        tbdskh.setForeground(Color.BLACK);
        tbdskh.setRowHeight(25);
        
        
        KhachHangBUS kh = new KhachHangBUS();
        Vector header = new Vector();
        header.add("Chứng minh nhân dân");
        header.add("Họ và tên");
        header.add("Số điện thoại");
        header.add("Giới tính");
        if(model.getRowCount()==0) 
            model = new DefaultTableModel(header, 0);
        for(KhachHangDTO khdto: dskh) {
            Vector row = new Vector();
            row.add(khdto.getMaKH());
            row.add(khdto.getHo() + " " + khdto.getTen());
            row.add(khdto.getSdt());
            if(khdto.getGioitinh()==0)
                row.add("Nam");
            else 
                row.add("Nữ");
            model.addRow(row);
        }
        tbdskh.setModel(model);
        tbdskh.getColumnModel().getColumn(0).setMinWidth(150);
        tbdskh.getColumnModel().getColumn(1).setMinWidth(300);
        tbdskh.getColumnModel().getColumn(2).setMinWidth(150);
        tbdskh.getColumnModel().getColumn(3).setMinWidth(100);
        
        DefaultTableCellRenderer center=new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        tbdskh.setDefaultRenderer(tbdskh.getColumnClass(0), center);
        tbdskh.updateUI();
        tbdskh.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                int i = tbdskh.getSelectedRow();
                String mnv = tbdskh.getModel().getValueAt(i, 0).toString();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        return tbdskh;
    }
    public JPanel Timkiem(int width, int height) {
        pTimkiem=new JPanel();
        pTimkiem.setLayout(null);
        pTimkiem.setBounds(0, 50, width, height);
        pTimkiem.setPreferredSize(new Dimension(width,height));
        pTimkiem.setOpaque(true);
        
        lbCmnd = new JLabel("Theo CMND: ");
        lbCmnd.setFont(new Font("Times New Roman",0,18));
        lbCmnd.setBounds(10, 5, 130, 20);
        lbCmnd.setOpaque(true);
        pTimkiem.add(lbCmnd);
        txtCmnd =new JTextField();
        txtCmnd.setBounds(150,2,370,25);
        txtCmnd.setFont(new Font("Arial",0,18));
        txtCmnd.setHorizontalAlignment(JTextField.CENTER);
        txtCmnd.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtCmnd.setEditable(false);
        txtCmnd.setOpaque(true);
        pTimkiem.add(txtCmnd);
        
        lbTenkhachhang = new JLabel("Theo họ và tên: ");
        lbTenkhachhang.setFont(new Font("Times New Roman",0,18));
        lbTenkhachhang.setBounds(10, 45, 130, 20);
        lbTenkhachhang.setOpaque(true);
        pTimkiem.add(lbTenkhachhang);
        txtTenkhachhang =new JTextField();
        txtTenkhachhang.setBounds(150, 43, 370, 25);
        txtTenkhachhang.setFont(new Font("Arial",0,18));
        txtTenkhachhang.setHorizontalAlignment(JTextField.CENTER);
        txtTenkhachhang.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtTenkhachhang.setEditable(false);
        txtTenkhachhang.setOpaque(true);
        pTimkiem.add(txtTenkhachhang);
               
        lbSodienthoai = new JLabel("Theo sdt: ");
        lbSodienthoai.setFont(new Font("Times New Roman",0,18));
        lbSodienthoai.setBounds(10, 85, 130, 20);
        lbSodienthoai.setOpaque(true);
        pTimkiem.add(lbSodienthoai);
        txtSodienthoai =new JTextField();
        txtSodienthoai.setBounds(150, 84, 370, 25);
        txtSodienthoai.setFont(new Font("Arial",0,18));
        txtSodienthoai.setHorizontalAlignment(JTextField.CENTER);
        txtSodienthoai.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtSodienthoai.setEditable(false);
        txtSodienthoai.setOpaque(true);
        pTimkiem.add(txtSodienthoai);
        
        lbGioitinh= new JLabel("Theo giới tính: ");
        lbGioitinh.setFont(new Font("Times New Roman",0,18));
        lbGioitinh.setBounds(10, 125, 130, 20);
        lbGioitinh.setOpaque(true);
        pTimkiem.add(lbGioitinh);
        cbxGioitinh = new JComboBox(DsGioitinh);
        cbxGioitinh.setBounds(150, 125, 150, 25);
        cbxGioitinh.setBackground(Color.WHITE);
        cbxGioitinh.setEnabled(false);
        pTimkiem.add(cbxGioitinh);
        
        cbxTimkiem = new JComboBox(DsTimkiem);
        cbxTimkiem.setBounds(570, 10, 180, 30);
        pTimkiem.add(cbxTimkiem);
        
        ImageIcon iconsearch = new ImageIcon("src/img/search.png");
        btTimKiem = new JButton("Tìm kiếm",iconsearch);
        btTimKiem.setBounds(570, 50, 140, 35);
        btTimKiem.setBackground(colorButton);
        btTimKiem.setForeground(Color.WHITE);
        btTimKiem.setBorderPainted(false);
        btTimKiem.setFocusPainted(false);
        pTimkiem.add(btTimKiem);
        
        cbxTimkiem.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent arg0) {
                if (cbxTimkiem.getSelectedItem().equals(DsTimkiem[0])){
                    txtCmnd.setEditable(false);
                    txtTenkhachhang.setEditable(false);
                    txtSodienthoai.setEditable(false);
                    cbxGioitinh.setEnabled(false);
                }
                if (cbxTimkiem.getSelectedItem().equals(DsTimkiem[1])){
                    txtCmnd.setEditable(true);
                    txtTenkhachhang.setEditable(false);
                    txtSodienthoai.setEditable(false);
                    cbxGioitinh.setEnabled(false);
                }
                if (cbxTimkiem.getSelectedItem().equals(DsTimkiem[2])){
                    txtCmnd.setEditable(false);
                    txtTenkhachhang.setEditable(true);
                    txtSodienthoai.setEditable(false);
                    cbxGioitinh.setEnabled(false);
                }
                if (cbxTimkiem.getSelectedItem().equals(DsTimkiem[3])){
                    txtCmnd.setEditable(false);
                    txtTenkhachhang.setEditable(false);
                    txtSodienthoai.setEditable(true);
                    cbxGioitinh.setEnabled(false);
                }
                if (cbxTimkiem.getSelectedItem().equals(DsTimkiem[4])){
                    txtCmnd.setEditable(false);
                    txtTenkhachhang.setEditable(false);
                    txtSodienthoai.setEditable(false);
                    cbxGioitinh.setEnabled(true);
                }
            }
        });
        
        ImageIcon iconthem = new ImageIcon("src/img/add.png");
        btThem = new JButton("Thêm",iconthem);
        btThem.setBounds(705, 110, 95, 35);
        btThem.setBackground(colorButton);
        btThem.setForeground(Color.WHITE);
        btThem.setBorderPainted(false);
        btThem.setFocusPainted(false);
        pTimkiem.add(btThem);
        
        ImageIcon iconsua = new ImageIcon("src/img/sua.png");
        btSua = new JButton("Sửa",iconsua);
        btSua.setBounds(805, 110, 90, 35);
        btSua.setBackground(colorButton);
        btSua.setForeground(Color.WHITE);
        btSua.setBorderPainted(false);
        btSua.setFocusPainted(false);
        pTimkiem.add(btSua);
        
        ImageIcon iconxoa = new ImageIcon("src/img/Delete.png");
        btXoa = new JButton("Xóa",iconxoa);
        btXoa.setBounds(900, 110, 90, 35);
        btXoa.setBackground(colorButton);
        btXoa.setForeground(Color.WHITE);
        btXoa.setBorderPainted(false);
        btXoa.setFocusPainted(false);
        pTimkiem.add(btXoa);
        
        return pTimkiem;
    }
    public void Chucnangcacnut() {
        btThem.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                KhoiTaoJFrameThem();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                btThem.setBackground(Color.DARK_GRAY);
                btThem.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                btThem.setBackground(new Color(36,36,36));
            }
        });
        btSua.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                int i = tbdskh.getSelectedRow();
                if(i<0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng cần sửa!!!");
                } else {
                    KhoiTaoJFrameSua();
                    KhachHangBUS kh_bus = new KhachHangBUS();
                    KhachHangDTO kh_dto = new KhachHangDTO();
                    String tmp = tbdskh.getModel().getValueAt(i, 0).toString();
                    kh_dto = kh_bus.LayThongtin1KH(tmp);
                    txtTSMakh.setText(kh_dto.getMaKH());
                    txtTSHokh.setText(kh_dto.getHo());
                    txtTSTenkh.setText(kh_dto.getTen());
                    txtTSSdt.setText(kh_dto.getSdt());
                        Date date;
                    try {
                        date = new SimpleDateFormat("yyyy-MM-dd").parse(kh_dto.getNgaysinh());
                        txtTSDate.setDate(date);
                    } catch (ParseException ex) {
                        Logger.getLogger(KhachHangGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }              
                    int gioitinhtmp = kh_dto.getGioitinh();
                    if(gioitinhtmp==0) {
                        cbxTSGioitinh.setSelectedIndex(0);
                    } else {
                        cbxTSGioitinh.setSelectedIndex(1);
                    }
                    txtTSDiachi.setText(kh_dto.getDiachi());                                   
                    
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                btSua.setBackground(Color.DARK_GRAY);
                btSua.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                btSua.setBackground(new Color(36,36,36));
            }
        });
        btXoa.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                int i = tbdskh.getSelectedRow();
                if(i<0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng cần xóa!!!");
                } else {
                    KhachHangBUS kh_bus = new KhachHangBUS();
                    KhachHangDTO kh_dto = new KhachHangDTO();
                    String tmp = tbdskh.getModel().getValueAt(i, 0).toString();
                    kh_dto = kh_bus.LayThongtin1KH(tmp);
                    txtTSMakh = new JTextField();
                    txtTSMakh.setText(kh_dto.getMaKH());
                    int input=JOptionPane.showConfirmDialog(pKhachHang, "Bạn có chắc muốn xóa", "Xóa khách hàng", JOptionPane.YES_NO_OPTION);
                    if(input==0) {
                        kh_bus.xoa(kh_dto);
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                btXoa.setBackground(Color.DARK_GRAY);
                btXoa.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                btXoa.setBackground(new Color(36,36,36));
            }
        });
        btCapNhat.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                KhachHangBUS khbus = new KhachHangBUS();
                if(KhachHangBUS.dskh==null) {
                    khbus.docdskh();
                }
                JTable tbdskhtmp = new JTable();
                tbdskhtmp = Danhsachkhachhang(KhachHangBUS.dskh);
                sc1 = new JScrollPane(tbdskhtmp);
                sc1.setBounds(0, 0, 980, 370);
                sc.removeAll();
                sc.add(sc1);
                sc.repaint();
                sc.revalidate();
                pBangdskh.add(sc);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                ///throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                btCapNhat.setBackground(Color.DARK_GRAY);
                btCapNhat.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                btCapNhat.setBackground(new Color(36,36,36));
            }
        });
        btXemChiTiet.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                int i = tbdskh.getSelectedRow();
                if(i<0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng cần xem chi tiết!!!");
                } else {
                    KhoiTaoJFrameXemchitiet();
                    KhachHangBUS kh_bus = new KhachHangBUS();
                    KhachHangDTO kh_dto = new KhachHangDTO();
                    Xulydulieu xl = new Xulydulieu();
                    String tmp = tbdskh.getModel().getValueAt(i, 0).toString();
                    kh_dto = kh_bus.LayThongtin1KH(tmp);
                    try {
                        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(kh_dto.getNgaysinh());
                        SimpleDateFormat chuyendate = new SimpleDateFormat("dd-MM-yyyy");
                        lbXTTNgaysinh.setText(chuyendate.format(date));
                    } catch (ParseException ex) {
                        Logger.getLogger(KhachHangGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    lbXTTMakh.setText(kh_dto.getMaKH()); 
                    lbXTTHokh.setText(kh_dto.getHo());
                    lbXTTTenkh.setText(kh_dto.getTen());
                    lbXTTSdt.setText(kh_dto.getSdt());
                    int gioitinhtmp = kh_dto.getGioitinh();
                    if(gioitinhtmp==0) {
                        lbXTTGioitinh.setText("Nam");
                    } else {
                        lbXTTGioitinh.setText("Nữ");
                    }
                    lbXTTDiachi.setText(kh_dto.getDiachi());
                    
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                btXemChiTiet.setBackground(Color.DARK_GRAY);
                btXemChiTiet.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                btXemChiTiet.setBackground(new Color(36,36,36));
            }
        });
        btTimKiem.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                ArrayList<KhachHangDTO> dskhtmp = new ArrayList<>();
                KhachHangBUS kh_bus = new KhachHangBUS();  
                if(cbxTimkiem.getSelectedItem().equals(DsTimkiem[0]))
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn kiểu tìm kiếm!!!");
                else {
                    if(cbxTimkiem.getSelectedItem().equals(DsTimkiem[1])){
                        dskhtmp = kh_bus.Timkiemcmnd(txtCmnd.getText());
                    }
                    if(cbxTimkiem.getSelectedItem().equals(DsTimkiem[2])){
                        dskhtmp = kh_bus.Timkiemten(txtTenkhachhang.getText());
                    }
                    if(cbxTimkiem.getSelectedItem().equals(DsTimkiem[3])){
                        dskhtmp = kh_bus.Timkiemsdt(txtSodienthoai.getText());
                    }
                    if(cbxTimkiem.getSelectedItem().equals(DsTimkiem[4])){
                        cbxGioitinh.addItemListener(new ItemListener() {
                            @Override
                            public void itemStateChanged(ItemEvent arg0) {

                            }
                        });
                        int tmp = 0;
                        if(cbxGioitinh.getSelectedItem().equals(DsGioitinh[0]))
                            tmp = 0;
                        else if(cbxGioitinh.getSelectedItem().equals(DsGioitinh[1]))
                            tmp = 1;
                        else
                            tmp = 2;
                        dskhtmp = kh_bus.Timkiemgioitinh(tmp);
                    }
                    if(dskhtmp != null) {                      
                        JTable tbdskhtmp = new JTable();
                        tbdskhtmp = Danhsachkhachhang(dskhtmp);
                        sc1 = new JScrollPane(tbdskhtmp);
                        sc1.setBounds(0, 0, 980, 370);
                        sc.removeAll();
                        sc.add(sc1);
                        sc.repaint();
                        sc.revalidate();
                        pBangdskh.add(sc);
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                btTimKiem.setBackground(Color.DARK_GRAY);
                btTimKiem.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                btTimKiem.setBackground(new Color(36,36,36));
            }
        });
    } 
    public void KhoiTaoJFrameThem() {
        fThem = new JFrame();
        fThem.setBounds(0,0,600,660);
        fThem.setPreferredSize(new Dimension(600,660));
        fThem.setLayout(null);
        fThem.setUndecorated(false);
        
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        fThem.setLocation((int)d.getWidth()/2 - (int)fThem.getPreferredSize().getWidth()/2,
                      (int)d.getHeight()/2 - (int)fThem.getPreferredSize().getHeight()/2);
        
        lbTieude = new JLabel();
        lbTieude.setText("Thêm khách hàng");
        lbTieude.setFont(new Font("Time New Roman", Font.BOLD, 20));
        lbTieude.setBounds(0, 15, 600, 25);
        lbTieude.setForeground(Color.BLACK);
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        fThem.add(lbTieude);
        
        lbTSMakh = new JLabel();
        lbTSMakh.setText("CMND:");
        lbTSMakh.setBounds(55, 75, 120, 20);
        lbTSMakh.setFont(new Font("Times New Roman",0,20));
        lbTSMakh.setOpaque(true);
        fThem.add(lbTSMakh);
        txtTSMakh = new JTextField();
        txtTSMakh.setBounds(195,75,270,25);
        txtTSMakh.setFont(new Font("Arial",0,18));
        txtTSMakh.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
        txtTSMakh.setOpaque(true);
        fThem.add(txtTSMakh);
        
        lbTSHokh = new JLabel();
        lbTSHokh.setText("Họ khách hàng:");
        lbTSHokh.setBounds(55, 135, 130, 20);
        lbTSHokh.setFont(new Font("Times New Roman",0,20));
        lbTSHokh.setOpaque(true);
        fThem.add(lbTSHokh);
        txtTSHokh = new JTextField();
        txtTSHokh.setBounds(195,135,270,25);
        txtTSHokh.setFont(new Font("Arial",0,18));
        txtTSHokh.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
        txtTSHokh.setOpaque(true);
        fThem.add(txtTSHokh);
        
        lbTSTenkh = new JLabel();
        lbTSTenkh.setText("Tên khách hàng:");
        lbTSTenkh.setBounds(55, 195, 135, 20);
        lbTSTenkh.setFont(new Font("Times New Roman",0,20));
        lbTSTenkh.setOpaque(true);
        fThem.add(lbTSTenkh);
        txtTSTenkh = new JTextField();
        txtTSTenkh.setBounds(195,195,270,25);
        txtTSTenkh.setFont(new Font("Arial",0,18));
        txtTSTenkh.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
        txtTSTenkh.setOpaque(true);
        fThem.add(txtTSTenkh);
        
        lbTSSdt = new JLabel();
        lbTSSdt.setText("Số điện thoại:");
        lbTSSdt.setBounds(55, 255, 120, 23);
        lbTSSdt.setFont(new Font("Times New Roman",0,20));
        lbTSSdt.setOpaque(true);
        fThem.add(lbTSSdt);
        txtTSSdt = new JTextField();
        txtTSSdt.setBounds(195,255,270,25);
        txtTSSdt.setFont(new Font("Arial",0,18));
        txtTSSdt.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
        txtTSSdt.setOpaque(true);
        fThem.add(txtTSSdt);
        
        lbTSNgaysinh = new JLabel();
        lbTSNgaysinh.setText("Ngày sinh:");
        lbTSNgaysinh.setBounds(55, 315, 120, 20);
        lbTSNgaysinh.setFont(new Font("Times New Roman",0,20));
        lbTSNgaysinh.setOpaque(true);
        fThem.add(lbTSNgaysinh);
        txtTSDate = new JDateChooser();
        txtTSDate.setBounds(195, 315, 150, 25);
        txtTSDate.setDateFormatString("dd-MM-yyyy");
        txtTSDate.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
        editor = (JTextFieldDateEditor) txtTSDate.getDateEditor();
        editor.setEditable(false);
        editor.setBackground(Color.WHITE);
        editor.setFont(new Font("Arial",0,18));
        fThem.add(txtTSDate);
        
        lbTSGioitinh = new JLabel();
        lbTSGioitinh.setText("Giới tính:");
        lbTSGioitinh.setBounds(55, 375, 120, 20);
        lbTSGioitinh.setFont(new Font("Times New Roman",0,20));
        lbTSGioitinh.setOpaque(true);
        fThem.add(lbTSGioitinh);
        cbxTSGioitinh = new JComboBox(DsTSGioitinh);
        cbxTSGioitinh.setBounds(195, 375, 150, 25);
        cbxTSGioitinh.setBackground(Color.WHITE);
        cbxTSGioitinh.setFont(new Font("Arial",0,18));
        fThem.add(cbxTSGioitinh);
        cbxTSGioitinh.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent arg0) {

            }
        });
        
        lbTSDiachi = new JLabel();
        lbTSDiachi.setText("Địa chỉ:");
        lbTSDiachi.setBounds(55, 435, 120, 20);
        lbTSDiachi.setFont(new Font("Times New Roman",0,20));
        lbTSDiachi.setOpaque(true);
        fThem.add(lbTSDiachi);
        txtTSDiachi = new JTextArea();
        txtTSDiachi.setFont(new Font("Arial",0,18));
        txtTSDiachi.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
        txtTSDiachi.setLineWrap(true);
        //txtTSDiachi.setWrapStyleWord(true);
        txtTSDiachi.setOpaque(true);
        JScrollPane scTSDiachi=new JScrollPane();
        scTSDiachi.setViewportView(txtTSDiachi);
        scTSDiachi.setBounds(195,435,270,75);
        fThem.add(scTSDiachi);
        
        JButton btHoanThanh = new JButton("Hoàn thành");
        btHoanThanh.setBounds(240, 545, 120, 35);
        btHoanThanh.setBackground(colorButton);
        btHoanThanh.setForeground(Color.WHITE);
        btHoanThanh.setBorderPainted(false);
        btHoanThanh.setFocusPainted(false);
        fThem.add(btHoanThanh);
        btHoanThanh.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                KhachHangBUS kh_bus = new KhachHangBUS();
                KhachHangDTO kh_dto = new KhachHangDTO();
                Xulydulieu xl = new Xulydulieu();
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                int tmp = 0;
                if(cbxTSGioitinh.getSelectedItem().equals(DsTSGioitinh[0]))
                    tmp = 0;
                else 
                    tmp = 1;
                String datetmp="";
                if(txtTSDate.getDate()==null) 
                    datetmp = "";
                else 
                    datetmp = date.format(txtTSDate.getDate());
                if(kh_bus.Ktranv(txtTSMakh.getText(), txtTSHokh.getText(), txtTSTenkh.getText(), txtTSSdt.getText(), 
                datetmp, txtTSDiachi.getText())==true) {
                    
                        if(kh_bus.ktratrung(txtTSMakh.getText())==true) {
                            kh_dto.setMaKH(txtTSMakh.getText());
                            kh_dto.setHo(txtTSHokh.getText());
                            kh_dto.setTen(txtTSTenkh.getText());
                            kh_dto.setSdt(txtTSSdt.getText());
                            kh_dto.setNgaysinh(date.format(txtTSDate.getDate()));
                            kh_dto.setGioitinh(tmp);
                            kh_dto.setDiachi(txtTSDiachi.getText());
                            kh_bus.them(kh_dto);
                            fThem.setVisible(false);
                        } else {
                            JOptionPane.showMessageDialog(null, "CMND đã có trong hệ thống");
                        }
                        
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                btHoanThanh.setBackground(Color.DARK_GRAY);
                btHoanThanh.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                btHoanThanh.setBackground(new Color(36,36,36));
            }
        });
        fThem.setVisible(true);
    }
    public void KhoiTaoJFrameSua() {
        fSua = new JFrame();
        fSua.setBounds(0,0,600,725);
        fSua.setPreferredSize(new Dimension(600,725));
        fSua.setLayout(null);
        fSua.setUndecorated(false);
        
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        fSua.setLocation((int)d.getWidth()/2 - (int)fSua.getPreferredSize().getWidth()/2,
                      (int)d.getHeight()/2 - (int)fSua.getPreferredSize().getHeight()/2);
        
        lbTieude = new JLabel();
        lbTieude.setText("Sửa khách hàng");
        lbTieude.setFont(new Font("Time New Roman", Font.BOLD, 20));
        lbTieude.setBounds(0, 15, 600, 25);
        lbTieude.setForeground(Color.BLACK);
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        fSua.add(lbTieude);
        
        lbTSMakh = new JLabel();
        lbTSMakh.setText("CMND:");
        lbTSMakh.setBounds(55, 75, 120, 20);
        lbTSMakh.setFont(new Font("Times New Roman",0,20));
        lbTSMakh.setOpaque(true);
        fSua.add(lbTSMakh);
        txtTSMakh = new JTextField();
        txtTSMakh.setBounds(195,75,270,25);
        txtTSMakh.setFont(new Font("Arial",0,18));
        txtTSMakh.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
        txtTSMakh.setOpaque(true);
        txtTSMakh.setEnabled(false);
        fSua.add(txtTSMakh);
        
        lbTSHokh = new JLabel();
        lbTSHokh.setText("Họ khách hàng:");
        lbTSHokh.setBounds(55, 135, 130, 20);
        lbTSHokh.setFont(new Font("Times New Roman",0,20));
        lbTSHokh.setOpaque(true);
        fSua.add(lbTSHokh);
        txtTSHokh = new JTextField();
        txtTSHokh.setBounds(195,135,270,25);
        txtTSHokh.setFont(new Font("Arial",0,18));
        txtTSHokh.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
        txtTSHokh.setOpaque(true);
        fSua.add(txtTSHokh);
        
        lbTSTenkh = new JLabel();
        lbTSTenkh.setText("Tên khách hàng:");
        lbTSTenkh.setBounds(55, 195, 135, 20);
        lbTSTenkh.setFont(new Font("Times New Roman",0,20));
        lbTSTenkh.setOpaque(true);
        fSua.add(lbTSTenkh);
        txtTSTenkh = new JTextField();
        txtTSTenkh.setBounds(195,195,270,25);
        txtTSTenkh.setFont(new Font("Arial",0,18));
        txtTSTenkh.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
        txtTSTenkh.setOpaque(true);
        fSua.add(txtTSTenkh);
        
        lbTSSdt = new JLabel();
        lbTSSdt.setText("Số điện thoại:");
        lbTSSdt.setBounds(55, 255, 120, 23);
        lbTSSdt.setFont(new Font("Times New Roman",0,20));
        lbTSSdt.setOpaque(true);
        fSua.add(lbTSSdt);
        txtTSSdt = new JTextField();
        txtTSSdt.setBounds(195,255,270,25);
        txtTSSdt.setFont(new Font("Arial",0,18));
        txtTSSdt.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
        txtTSSdt.setOpaque(true);
        fSua.add(txtTSSdt);
        
        lbTSNgaysinh = new JLabel();
        lbTSNgaysinh.setText("Ngày sinh:");
        lbTSNgaysinh.setBounds(55, 315, 120, 20);
        lbTSNgaysinh.setFont(new Font("Times New Roman",0,20));
        lbTSNgaysinh.setOpaque(true);
        fSua.add(lbTSNgaysinh);
        txtTSDate = new JDateChooser();
        txtTSDate.setBounds(195, 315, 150, 25);
        txtTSDate.setDateFormatString("dd-MM-yyyy");
        txtTSDate.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
        editor = (JTextFieldDateEditor) txtTSDate.getDateEditor();
        editor.setEditable(false);
        editor.setBackground(Color.WHITE);
        editor.setFont(new Font("Arial",0,18));
        fSua.add(txtTSDate);
        
        lbTSGioitinh = new JLabel();
        lbTSGioitinh.setText("Giới tính:");
        lbTSGioitinh.setBounds(55, 375, 120, 20);
        lbTSGioitinh.setFont(new Font("Times New Roman",0,20));
        lbTSGioitinh.setOpaque(true);
        fSua.add(lbTSGioitinh);
        cbxTSGioitinh = new JComboBox(DsTSGioitinh);
        cbxTSGioitinh.setBounds(195, 375, 150, 25);
        cbxTSGioitinh.setBackground(Color.WHITE);
        cbxTSGioitinh.setFont(new Font("Arial",0,18));
        fSua.add(cbxTSGioitinh);
        cbxTSGioitinh.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent arg0) {

            }
        });
        
        lbTSDiachi = new JLabel();
        lbTSDiachi.setText("Địa chỉ:");
        lbTSDiachi.setBounds(55, 435, 120, 20);
        lbTSDiachi.setFont(new Font("Times New Roman",0,20));
        lbTSDiachi.setOpaque(true);
        fSua.add(lbTSDiachi);
        txtTSDiachi = new JTextArea();
        txtTSDiachi.setFont(new Font("Arial",0,18));
        txtTSDiachi.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
        txtTSDiachi.setLineWrap(true);
        //txtTSDiachi.setWrapStyleWord(true);
        txtTSDiachi.setOpaque(true);
        JScrollPane scTSDiachi=new JScrollPane();
        scTSDiachi.setViewportView(txtTSDiachi);
        scTSDiachi.setBounds(195,435,270,75);
        fSua.add(scTSDiachi);
        
        JButton btHoanThanh = new JButton("Hoàn thành");
        btHoanThanh.setBounds(240, 605, 120, 35);
        btHoanThanh.setBackground(colorButton);
        btHoanThanh.setForeground(Color.WHITE);
        btHoanThanh.setBorderPainted(false);
        btHoanThanh.setFocusPainted(false);
        fSua.add(btHoanThanh);
        btHoanThanh.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                KhachHangBUS kh_bus = new KhachHangBUS();
                KhachHangDTO kh_dto = new KhachHangDTO();
                Xulydulieu xl = new Xulydulieu();
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                int tmp = 0;
                if(cbxTSGioitinh.getSelectedItem().equals(DsTSGioitinh[0]))
                    tmp = 0;
                else 
                    tmp = 1;
                String datetmp="";
                if(txtTSDate.getDate()==null) 
                    datetmp = "";
                else 
                    datetmp = date.format(txtTSDate.getDate());
                if(kh_bus.Ktranv(txtTSMakh.getText(), txtTSHokh.getText(), txtTSTenkh.getText(), txtTSSdt.getText(), 
                datetmp, txtTSDiachi.getText())==true) {
                    
                        kh_dto.setMaKH(txtTSMakh.getText());
                        kh_dto.setHo(txtTSHokh.getText());
                        kh_dto.setTen(txtTSTenkh.getText());
                        kh_dto.setSdt(txtTSSdt.getText());
                        kh_dto.setNgaysinh(date.format(txtTSDate.getDate()));
                        kh_dto.setGioitinh(tmp);
                        kh_dto.setDiachi(txtTSDiachi.getText());
                        kh_bus.sua(kh_dto);
                        fSua.setVisible(false);
                        
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                btHoanThanh.setBackground(Color.DARK_GRAY);
                btHoanThanh.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                btHoanThanh.setBackground(new Color(36,36,36));
            }
        });
        
        fSua.setVisible(true);
    }
    public void KhoiTaoJFrameXemchitiet() {
        fXemchitiet = new JFrame();
        fXemchitiet.setBounds(0,0,600,660);
        fXemchitiet.setPreferredSize(new Dimension(600,660));
        fXemchitiet.setLayout(null);
        fXemchitiet.setUndecorated(false);
        
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        fXemchitiet.setLocation((int)d.getWidth()/2 - (int)fXemchitiet.getPreferredSize().getWidth()/2,
                      (int)d.getHeight()/2 - (int)fXemchitiet.getPreferredSize().getHeight()/2);
        
        lbTieude = new JLabel();
        lbTieude.setText("Thông tin chi tiết khách hàng");
        lbTieude.setFont(new Font("Time New Roman", Font.BOLD, 20));
        lbTieude.setBounds(0, 15, 600, 25);
        lbTieude.setForeground(Color.BLACK);
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        fXemchitiet.add(lbTieude);
        
        lbTSMakh = new JLabel();
        lbTSMakh.setText("CMND:");
        lbTSMakh.setBounds(55, 75, 120, 20);
        lbTSMakh.setFont(new Font("Times New Roman",0,20));
        lbTSMakh.setOpaque(true);
        fXemchitiet.add(lbTSMakh);
        lbXTTMakh = new JLabel();
        lbXTTMakh.setBounds(195,75,270,25);;
        lbXTTMakh.setFont(new Font("Arial",0,18));
        lbXTTMakh.setHorizontalAlignment(JTextField.CENTER);
        lbXTTMakh.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,Color.BLACK));
        lbXTTMakh.setOpaque(true);
        fXemchitiet.add(lbXTTMakh);
        
        lbTSHokh = new JLabel();
        lbTSHokh.setText("Họ khách hàng:");
        lbTSHokh.setBounds(55, 135, 120, 20);
        lbTSHokh.setFont(new Font("Times New Roman",0,20));
        lbTSHokh.setOpaque(true);
        fXemchitiet.add(lbTSHokh);
        lbXTTHokh = new JLabel();
        lbXTTHokh.setBounds(195,135,270,25);
        lbXTTHokh.setFont(new Font("Arial",0,18));
        lbXTTHokh.setHorizontalAlignment(JTextField.CENTER);
        lbXTTHokh.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,Color.BLACK));
        lbXTTHokh.setOpaque(true);
        fXemchitiet.add(lbXTTHokh);
        
        lbTSTenkh = new JLabel();
        lbTSTenkh.setText("Tên khách hàng:");
        lbTSTenkh.setBounds(55, 195, 120, 20);
        lbTSTenkh.setFont(new Font("Times New Roman",0,20));
        lbTSTenkh.setOpaque(true);
        fXemchitiet.add(lbTSTenkh);
        lbXTTTenkh = new JLabel();
        lbXTTTenkh.setBounds(195,195,270,25);
        lbXTTTenkh.setFont(new Font("Arial",0,18));
        lbXTTTenkh.setHorizontalAlignment(JTextField.CENTER);
        lbXTTTenkh.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,Color.BLACK));
        lbXTTTenkh.setOpaque(true);
        fXemchitiet.add(lbXTTTenkh);
        
        lbTSSdt = new JLabel();
        lbTSSdt.setText("Số điện thoại:");
        lbTSSdt.setBounds(55, 255, 120, 23);
        lbTSSdt.setFont(new Font("Times New Roman",0,20));
        lbTSSdt.setOpaque(true);
        fXemchitiet.add(lbTSSdt);
        lbXTTSdt = new JLabel();
        lbXTTSdt.setBounds(195,255,270,25);
        lbXTTSdt.setFont(new Font("Arial",0,18));
        lbXTTSdt.setHorizontalAlignment(JTextField.CENTER);
        lbXTTSdt.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,Color.BLACK));
        lbXTTSdt.setOpaque(true);
        fXemchitiet.add(lbXTTSdt);
        
        lbTSNgaysinh = new JLabel();
        lbTSNgaysinh.setText("Ngày sinh:");
        lbTSNgaysinh.setBounds(55, 315, 120, 20);
        lbTSNgaysinh.setFont(new Font("Times New Roman",0,20));
        lbTSNgaysinh.setOpaque(true);
        fXemchitiet.add(lbTSNgaysinh);
        lbXTTNgaysinh = new JLabel();
        lbXTTNgaysinh.setBounds(195, 315, 270, 25);
        lbXTTNgaysinh.setFont(new Font("Arial",0,18));
        lbXTTNgaysinh.setHorizontalAlignment(JTextField.CENTER);
        lbXTTNgaysinh.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,Color.BLACK));
        lbXTTNgaysinh.setOpaque(true);
        fXemchitiet.add(lbXTTNgaysinh);
        
        lbTSGioitinh = new JLabel();
        lbTSGioitinh.setText("Giới tính:");
        lbTSGioitinh.setBounds(55, 375, 120, 20);
        lbTSGioitinh.setFont(new Font("Times New Roman",0,20));
        lbTSGioitinh.setOpaque(true);
        fXemchitiet.add(lbTSGioitinh);
        lbXTTGioitinh = new JLabel();
        lbXTTGioitinh.setBounds(195, 375, 270, 25);
        lbXTTGioitinh.setFont(new Font("Arial",0,18));
        lbXTTGioitinh.setHorizontalAlignment(JTextField.CENTER);
        lbXTTGioitinh.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,Color.BLACK));
        lbXTTGioitinh.setOpaque(true);
        fXemchitiet.add(lbXTTGioitinh);
        
        lbTSDiachi = new JLabel();
        lbTSDiachi.setText("Địa chỉ:");
        lbTSDiachi.setBounds(55, 435, 120, 20);
        lbTSDiachi.setFont(new Font("Times New Roman",0,20));
        lbTSDiachi.setOpaque(true);
        fXemchitiet.add(lbTSDiachi);
        lbXTTDiachi = new JTextArea();
        lbXTTDiachi.setFont(new Font("Arial",0,18));
        lbXTTDiachi.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0,Color.BLACK));
        lbXTTDiachi.setOpaque(true);
        lbXTTDiachi.setEditable(false);
        lbXTTDiachi.setLineWrap(true);
        //txtTSDiachi.setWrapStyleWord(true);
        lbXTTDiachi.setOpaque(true);
        lbXTTDiachi.setForeground(Color.BLACK);
        JScrollPane scXTTDiachi=new JScrollPane();
        scXTTDiachi.setViewportView(lbXTTDiachi);
        scXTTDiachi.setBounds(195,435,270,75);
        scXTTDiachi.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
        scXTTDiachi.setForeground(Color.BLACK);
        lbXTTDiachi.setBackground(new Color(238,238,238));
        fXemchitiet.add(scXTTDiachi);
        
        fXemchitiet.setVisible(true);
    }
}
