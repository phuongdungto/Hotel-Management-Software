/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import BUS.NhanVienBUS;
import BUS.Xulydulieu;
import DTO.NhanVienDTO;
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
public class NhanVienGUI {
    protected JFrame fThem;
    protected JFrame fSua;
    protected JFrame fXemchitiet;
    protected JPanel pNhanVien;
    protected JPanel pBangdsnv;
    protected JPanel pTimkiem;
    protected JLabel lbTieude, lbTennhanvien, lbSodienthoai, lbTienluongtoithieu, lbTienluongtoida, lbGioitinh;
    protected JLabel lbTSManv, lbTSHonv, lbTSTennv, lbTSSdt, lbTSNgaysinh, lbTSGioitinh, lbTSDiachi, lbTSTienluong;
    protected JLabel lbXTTManv, lbXTTHonv, lbXTTTennv, lbXTTSdt, lbXTTNgaysinh, lbXTTGioitinh, lbXTTTienluong;
    protected JTable tbdsnv;
    protected JTextField txtTennhanvien, txtSodienthoai, txtTienluongtoithieu, txtTienluongtoida;
    protected JTextField txtTSHonv, txtTSTennv, txtTSManv, txtTSSdt, txtTSTienluong;
    protected JTextArea txtTSDiachi;
    protected JTextArea lbXTTDiachi;
    protected JComboBox cbxTimkiem, cbxGioitinh, cbxTSGioitinh;
    protected JButton btTimKiem,btXemChiTiet,btThem,btSua,btXoa,btCapNhat;
    protected JScrollPane sc, sc1;
    protected String DsTimkiem[] = { "Chọn kiểu cần tìm kiếm", "Tìm kiếm theo họ và tên", "Tìm kiếm theo số "
                + "điện thoại", "Tìm kiếm theo giới tính", "Tìm kiếm theo tiền lương"};
    protected String DsTSGioitinh[] = { "Nam", "Nữ" };
    protected String DsGioitinh[] = { "Tất cả", "Nam", "Nữ" };
    protected JTextFieldDateEditor editor;
    protected JDateChooser txtTSDate;
    protected Color colorButton = new Color(36,36,36);
    protected Color colorLaybel = new Color(238, 238, 238);
    public DefaultTableModel model;
    public JPanel KhoiTaoPanel(int width, int height) {
        pNhanVien = new JPanel();
        pNhanVien.setLayout(null);
        pNhanVien.setBounds(0, 0, width, height);
        pNhanVien.setPreferredSize(new Dimension(width, height));
        pNhanVien.setOpaque(true);
        
        lbTieude = new JLabel();
        lbTieude.setText("Nhân viên");
        lbTieude.setFont(new Font("Time New Roman", Font.BOLD, 26));
        lbTieude.setBounds(0, 0, 1000, 50);
        lbTieude.setForeground(Color.BLACK);
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        
        pNhanVien.add(lbTieude);
        
        pBangdsnv = Bangdsnv(1000, 450);
        pNhanVien.add(pBangdsnv);
        
        pTimkiem = Timkiem(1000,150);
        pNhanVien.add(pTimkiem);
        
        Chucnangcacnut();
        
        return pNhanVien;
    }
    public JPanel Bangdsnv(int width, int height) {
        pBangdsnv=new JPanel();
        pBangdsnv.setLayout(null);
        pBangdsnv.setBounds(0, 200, width, height);
        pBangdsnv.setPreferredSize(new Dimension(width,height));
        pBangdsnv.setOpaque(true);
        NhanVienBUS nvbus = new NhanVienBUS();
        if(NhanVienBUS.dsnv==null) {
            nvbus.docdsnv();
        }
        tbdsnv = Danhsachnhanvien(NhanVienBUS.dsnv);
        sc = new JScrollPane(tbdsnv);
        sc.setViewportView(tbdsnv);
        sc.setBounds(10, 20, 980, 370);
        pBangdsnv.add(sc);
        
        ImageIcon iconCapNhat = new ImageIcon("src/img/update.png");
        btCapNhat = new JButton("Cập nhật",iconCapNhat);
        btCapNhat.setBounds(20, 400, 160, 35);
        btCapNhat.setBackground(colorButton);
        btCapNhat.setForeground(Color.WHITE);
        btCapNhat.setBorderPainted(false);
        btCapNhat.setFocusPainted(false);
        pBangdsnv.add(btCapNhat);
        
        ImageIcon iconxemchitiet = new ImageIcon("src/img/eye.png");
        btXemChiTiet = new JButton("Xem chi tiết",iconxemchitiet);
        btXemChiTiet.setBounds(780, 400, 160, 35);
        btXemChiTiet.setBackground(colorButton);
        btXemChiTiet.setForeground(Color.WHITE);
        btXemChiTiet.setBorderPainted(false);
        btXemChiTiet.setFocusPainted(false);
        pBangdsnv.add(btXemChiTiet);
        return pBangdsnv;
    }
    public JTable Danhsachnhanvien(ArrayList<NhanVienDTO> dsnv) {
        tbdsnv = new JTable();
        model = new DefaultTableModel();
        tbdsnv.getTableHeader().setBackground(colorButton);
        tbdsnv.getTableHeader().setForeground(Color.WHITE);
        tbdsnv.getTableHeader().setFont(new Font("Time New Roman", 0, 18));
        tbdsnv.getTableHeader().setPreferredSize(new Dimension(0,30));
        tbdsnv.setFont(new Font("Times New Roman",0,18));
        tbdsnv.setForeground(Color.BLACK);
        tbdsnv.setRowHeight(25);
        
        Vector header = new Vector();
        header.add("Mã nhân viên");
        header.add("Họ và tên");
        header.add("Số điện thoại");
        header.add("Giới tính");
        header.add("Tiền lương");
        if(model.getRowCount()==0) 
            model = new DefaultTableModel(header, 0);
        for(NhanVienDTO nvdto: dsnv) {
            Vector row = new Vector();
            Xulydulieu xl = new Xulydulieu();
            row.add(nvdto.getMaNV());
            row.add(nvdto.getHo() + " " + nvdto.getTen());
            row.add(nvdto.getSdt());
            if(nvdto.getGioitinh()==0)
                row.add("Nam");
            else 
                row.add("Nữ");
            String tmp = String.valueOf(nvdto.getTienLuong());
            row.add(xl.ChuyenKieuTien(tmp) + " VND");
            model.addRow(row);
        }
        tbdsnv.setModel(model);
        tbdsnv.getColumnModel().getColumn(0).setMinWidth(150);
        tbdsnv.getColumnModel().getColumn(1).setMinWidth(300);
        tbdsnv.getColumnModel().getColumn(2).setMinWidth(150);
        tbdsnv.getColumnModel().getColumn(3).setMinWidth(100);
        tbdsnv.getColumnModel().getColumn(4).setMinWidth(200);
        
        DefaultTableCellRenderer center=new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        tbdsnv.setDefaultRenderer(tbdsnv.getColumnClass(0), center);
        tbdsnv.updateUI();
        tbdsnv.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                int i = tbdsnv.getSelectedRow();
                String mnv = tbdsnv.getModel().getValueAt(i, 0).toString();
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
        return tbdsnv;
    }
    public JPanel Timkiem(int width, int height) {
        pTimkiem=new JPanel();
        pTimkiem.setLayout(null);
        pTimkiem.setBounds(0, 50, width, height);
        pTimkiem.setPreferredSize(new Dimension(width,height));
        pTimkiem.setOpaque(true);
        
        lbTennhanvien = new JLabel("Theo họ tên: ");
        lbTennhanvien.setFont(new Font("Times New Roman",0,18));
        lbTennhanvien.setBounds(10, 5, 130, 20);
        lbTennhanvien.setOpaque(true);
        pTimkiem.add(lbTennhanvien);
        txtTennhanvien =new JTextField();
        txtTennhanvien.setBounds(150,2,370,25);
        txtTennhanvien.setFont(new Font("Arial",0,18));
        txtTennhanvien.setHorizontalAlignment(JTextField.CENTER);
        txtTennhanvien.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtTennhanvien.setEditable(false);
        txtTennhanvien.setOpaque(true);
        pTimkiem.add(txtTennhanvien);
        
        lbSodienthoai = new JLabel("Theo sdt: ");
        lbSodienthoai.setFont(new Font("Times New Roman",0,18));
        lbSodienthoai.setBounds(10, 45, 130, 20);
        lbSodienthoai.setOpaque(true);
        pTimkiem.add(lbSodienthoai);
        txtSodienthoai =new JTextField();
        txtSodienthoai.setBounds(150, 43, 370, 25);
        txtSodienthoai.setFont(new Font("Arial",0,18));
        txtSodienthoai.setHorizontalAlignment(JTextField.CENTER);
        txtSodienthoai.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtSodienthoai.setEditable(false);
        txtSodienthoai.setOpaque(true);
        pTimkiem.add(txtSodienthoai);
        
        lbGioitinh= new JLabel("Theo giới tính: ");
        lbGioitinh.setFont(new Font("Times New Roman",0,18));
        lbGioitinh.setBounds(10, 85, 130, 20);
        lbGioitinh.setOpaque(true);
        pTimkiem.add(lbGioitinh);
        cbxGioitinh = new JComboBox(DsGioitinh);
        cbxGioitinh.setBounds(150, 84, 150, 25);
        cbxGioitinh.setBackground(Color.WHITE);
        cbxGioitinh.setEnabled(false);
        pTimkiem.add(cbxGioitinh);
        
        lbTienluongtoithieu = new JLabel("Theo tiền lương: ");
        lbTienluongtoithieu.setFont(new Font("Times New Roman",0,18));
        lbTienluongtoithieu.setBounds(10, 125, 130, 20);
        lbTienluongtoithieu.setOpaque(true);
        pTimkiem.add(lbTienluongtoithieu);
        txtTienluongtoithieu =new JTextField();
        txtTienluongtoithieu.setBounds(150, 125, 150, 25);
        txtTienluongtoithieu.setFont(new Font("Arial",0,18));
        txtTienluongtoithieu.setHorizontalAlignment(JTextField.CENTER);
        txtTienluongtoithieu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtTienluongtoithieu.setEditable(false);
        txtTienluongtoithieu.setOpaque(true);
        pTimkiem.add(txtTienluongtoithieu);
        
        lbTienluongtoida = new JLabel("=>");
        lbTienluongtoida.setFont(new Font("Times New Roman",0,18));
        lbTienluongtoida.setBounds(325, 125, 30, 20);
        lbTienluongtoida.setOpaque(true);
        pTimkiem.add(lbTienluongtoida);
        txtTienluongtoida =new JTextField();
        txtTienluongtoida.setBounds(370, 125, 150, 25);
        txtTienluongtoida.setFont(new Font("Arial",0,18));
        txtTienluongtoida.setHorizontalAlignment(JTextField.CENTER);
        txtTienluongtoida.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtTienluongtoida.setEditable(false);
        txtTienluongtoida.setOpaque(true);
        pTimkiem.add(txtTienluongtoida);
        
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
                    txtTennhanvien.setEditable(false);
                    txtSodienthoai.setEditable(false);
                    cbxGioitinh.setEnabled(false);
                    txtTienluongtoithieu.setEditable(false);
                    txtTienluongtoida.setEditable(false);
                }
                if (cbxTimkiem.getSelectedItem().equals(DsTimkiem[1])){
                    txtTennhanvien.setEditable(true);
                    txtSodienthoai.setEditable(false);
                    cbxGioitinh.setEnabled(false);
                    txtTienluongtoithieu.setEditable(false);
                    txtTienluongtoida.setEditable(false);
                }
                if (cbxTimkiem.getSelectedItem().equals(DsTimkiem[2])){
                    txtTennhanvien.setEditable(false);
                    txtSodienthoai.setEditable(true);
                    cbxGioitinh.setEnabled(false);
                    txtTienluongtoithieu.setEditable(false);
                    txtTienluongtoida.setEditable(false);
                }
                if (cbxTimkiem.getSelectedItem().equals(DsTimkiem[3])){
                    txtTennhanvien.setEditable(false);
                    txtSodienthoai.setEditable(false);
                    cbxGioitinh.setEnabled(true);
                    txtTienluongtoithieu.setEditable(false);
                    txtTienluongtoida.setEditable(false);
                }
                if (cbxTimkiem.getSelectedItem().equals(DsTimkiem[4])){
                    txtTennhanvien.setEditable(false);
                    txtSodienthoai.setEditable(false);
                    cbxGioitinh.setEnabled(false);
                    txtTienluongtoithieu.setEditable(true);
                    txtTienluongtoida.setEditable(true);
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
                int i = tbdsnv.getSelectedRow();
                if(i<0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần sửa!!!");
                } else {
                    KhoiTaoJFrameSua();
                    NhanVienBUS nv_bus = new NhanVienBUS();
                    NhanVienDTO nv_dto = new NhanVienDTO();
                    String tmp = tbdsnv.getModel().getValueAt(i, 0).toString();
                    nv_dto = nv_bus.LayThongtin1NV(tmp);
                    txtTSManv.setText(nv_dto.getMaNV());
                    txtTSHonv.setText(nv_dto.getHo());
                    txtTSTennv.setText(nv_dto.getTen());
                    txtTSSdt.setText(nv_dto.getSdt());
                    try {
                        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(nv_dto.getNgaysinh());
                        txtTSDate.setDate(date);
                    } catch (ParseException ex) {
                        Logger.getLogger(NhanVienGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    int gioitinhtmp = nv_dto.getGioitinh();
                    if(gioitinhtmp==0) {
                        cbxTSGioitinh.setSelectedIndex(0);
                    } else {
                        cbxTSGioitinh.setSelectedIndex(1);
                    }
                    txtTSDiachi.setText(nv_dto.getDiachi());
                    txtTSTienluong.setText(String.valueOf(nv_dto.getTienLuong()));                    
                    
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
                int i = tbdsnv.getSelectedRow();
                if(i<0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần xóa!!!");
                } else {
                    NhanVienBUS nv_bus = new NhanVienBUS();
                    NhanVienDTO nv_dto = new NhanVienDTO();
                    String tmp = tbdsnv.getModel().getValueAt(i, 0).toString();
                    nv_dto = nv_bus.LayThongtin1NV(tmp);
                    txtTSManv = new JTextField();
                    txtTSManv.setText(nv_dto.getMaNV());
                    int input=JOptionPane.showConfirmDialog(pNhanVien, "Bạn có chắc muốn xóa", "Xóa nhân viên", JOptionPane.YES_NO_OPTION);
                    if(input==0) {
                        nv_bus.xoa(nv_dto);
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
                NhanVienBUS nvbus = new NhanVienBUS();
                if(NhanVienBUS.dsnv==null) {
                    nvbus.docdsnv();
                }
                JTable tbdsnvtmp = new JTable();
                tbdsnvtmp = Danhsachnhanvien(NhanVienBUS.dsnv);
                sc1 = new JScrollPane(tbdsnvtmp);
                sc1.setBounds(0, 0, 980, 370);
                sc.removeAll();
                sc.add(sc1);
                sc.repaint();
                sc.revalidate();
                pBangdsnv.add(sc);
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
                int i = tbdsnv.getSelectedRow();
                if(i<0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần xem chi tiết!!!");
                } else {
                    KhoiTaoJFrameXemchitiet();
                    NhanVienBUS nv_bus = new NhanVienBUS();
                    NhanVienDTO nv_dto = new NhanVienDTO();
                    Xulydulieu xl = new Xulydulieu();
                    String tmp = tbdsnv.getModel().getValueAt(i, 0).toString();
                    nv_dto = nv_bus.LayThongtin1NV(tmp);
                    try {
                        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(nv_dto.getNgaysinh());
                        SimpleDateFormat chuyendate = new SimpleDateFormat("dd-MM-yyyy");
                        lbXTTNgaysinh.setText(chuyendate.format(date));
                    } catch (ParseException ex) {
                        Logger.getLogger(NhanVienGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    lbXTTManv.setText(nv_dto.getMaNV()); 
                    lbXTTHonv.setText(nv_dto.getHo());
                    lbXTTTennv.setText(nv_dto.getTen());
                    lbXTTSdt.setText(nv_dto.getSdt());
                    int gioitinhtmp = nv_dto.getGioitinh();
                    if(gioitinhtmp==0) {
                        lbXTTGioitinh.setText("Nam");
                    } else {
                        lbXTTGioitinh.setText("Nữ");
                    }
                    lbXTTDiachi.setText(nv_dto.getDiachi());
                    String tienluongtmp = String.valueOf(nv_dto.getTienLuong());
                    lbXTTTienluong.setText(xl.ChuyenKieuTien(tienluongtmp) + " VND");
                    
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
                ArrayList<NhanVienDTO> dsnvtmp = new ArrayList<>();
                NhanVienBUS nv_bus = new NhanVienBUS();  
                if(cbxTimkiem.getSelectedItem().equals(DsTimkiem[0]))
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn kiểu tìm kiếm!!!");
                else {
                    if(cbxTimkiem.getSelectedItem().equals(DsTimkiem[1])){
                        dsnvtmp = nv_bus.Timkiemten(txtTennhanvien.getText());
                    }
                    if(cbxTimkiem.getSelectedItem().equals(DsTimkiem[2])){
                        dsnvtmp = nv_bus.Timkiemsdt(txtSodienthoai.getText());
                    }
                    if(cbxTimkiem.getSelectedItem().equals(DsTimkiem[3])){
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
                        dsnvtmp = nv_bus.Timkiemgioitinh(tmp);
                    }
                    if(cbxTimkiem.getSelectedItem().equals(DsTimkiem[4])){
                        if(nv_bus.Ktraso(txtTienluongtoithieu.getText())==true && nv_bus.Ktraso(txtTienluongtoida.getText())==true) {
                            dsnvtmp=nv_bus.Timkiemluong(txtTienluongtoithieu.getText(), txtTienluongtoida.getText());
                        } else {
                            JOptionPane.showMessageDialog(null, "Số tiền lương tìm kiếm không hợp lệ!!!");
                        }
                    }
                    if(dsnvtmp != null) {                      
                        JTable tbdsnvtmp = new JTable();
                        tbdsnvtmp = Danhsachnhanvien(dsnvtmp);
                        sc1 = new JScrollPane(tbdsnvtmp);
                        sc1.setBounds(0, 0, 980, 370);
                        sc.removeAll();
                        sc.add(sc1);
                        sc.repaint();
                        sc.revalidate();
                        pBangdsnv.add(sc);
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
        lbTieude.setText("Thêm nhân viên");
        lbTieude.setFont(new Font("Time New Roman", Font.BOLD, 20));
        lbTieude.setBounds(0, 15, 600, 25);
        lbTieude.setForeground(Color.BLACK);
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        fThem.add(lbTieude);
        
        lbTSHonv = new JLabel();
        lbTSHonv.setText("Họ nhân viên:");
        lbTSHonv.setBounds(55, 75, 120, 20);
        lbTSHonv.setFont(new Font("Times New Roman",0,20));
        lbTSHonv.setOpaque(true);
        fThem.add(lbTSHonv);
        txtTSHonv = new JTextField();
        txtTSHonv.setBounds(195,75,270,25);
        txtTSHonv.setFont(new Font("Arial",0,18));
        txtTSHonv.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
        txtTSHonv.setOpaque(true);
        fThem.add(txtTSHonv);
        
        lbTSTennv = new JLabel();
        lbTSTennv.setText("Tên nhân viên:");
        lbTSTennv.setBounds(55, 135, 120, 20);
        lbTSTennv.setFont(new Font("Times New Roman",0,20));
        lbTSTennv.setOpaque(true);
        fThem.add(lbTSTennv);
        txtTSTennv = new JTextField();
        txtTSTennv.setBounds(195,135,270,25);
        txtTSTennv.setFont(new Font("Arial",0,18));
        txtTSTennv.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
        txtTSTennv.setOpaque(true);
        fThem.add(txtTSTennv);
        
        lbTSSdt = new JLabel();
        lbTSSdt.setText("Số điện thoại:");
        lbTSSdt.setBounds(55, 195, 120, 23);
        lbTSSdt.setFont(new Font("Times New Roman",0,20));
        lbTSSdt.setOpaque(true);
        fThem.add(lbTSSdt);
        txtTSSdt = new JTextField();
        txtTSSdt.setBounds(195,195,270,25);
        txtTSSdt.setFont(new Font("Arial",0,18));
        txtTSSdt.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
        txtTSSdt.setOpaque(true);
        fThem.add(txtTSSdt);
        
        lbTSNgaysinh = new JLabel();
        lbTSNgaysinh.setText("Ngày sinh:");
        lbTSNgaysinh.setBounds(55, 255, 120, 20);
        lbTSNgaysinh.setFont(new Font("Times New Roman",0,20));
        lbTSNgaysinh.setOpaque(true);
        fThem.add(lbTSNgaysinh);
        txtTSDate = new JDateChooser();
        txtTSDate.setBounds(195, 255, 150, 25);
        txtTSDate.setDateFormatString("dd-MM-yyyy");
        txtTSDate.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
        editor = (JTextFieldDateEditor) txtTSDate.getDateEditor();
        editor.setEditable(false);
        editor.setBackground(Color.WHITE);
        editor.setFont(new Font("Arial",0,18));
        fThem.add(txtTSDate);
        
        lbTSGioitinh = new JLabel();
        lbTSGioitinh.setText("Giới tính:");
        lbTSGioitinh.setBounds(55, 315, 120, 20);
        lbTSGioitinh.setFont(new Font("Times New Roman",0,20));
        lbTSGioitinh.setOpaque(true);
        fThem.add(lbTSGioitinh);
        cbxTSGioitinh = new JComboBox(DsTSGioitinh);
        cbxTSGioitinh.setBounds(195, 315, 150, 25);
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
        lbTSDiachi.setBounds(55, 375, 120, 20);
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
        scTSDiachi.setBounds(195,375,270,75);
        fThem.add(scTSDiachi);
        
        lbTSTienluong = new JLabel();
        lbTSTienluong.setText("Tiền lương:");
        lbTSTienluong.setBounds(55, 485, 120, 23);
        lbTSTienluong.setFont(new Font("Times New Roman",0,20));
        lbTSTienluong.setOpaque(true);
        fThem.add(lbTSTienluong);
        txtTSTienluong = new JTextField();
        txtTSTienluong.setBounds(195,485,270,25);
        txtTSTienluong.setFont(new Font("Arial",0,18));
        txtTSTienluong.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
        txtTSTienluong.setOpaque(true);
        fThem.add(txtTSTienluong);
        
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
                NhanVienBUS nv_bus = new NhanVienBUS();
                NhanVienDTO nv_dto = new NhanVienDTO();
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
                if(nv_bus.Ktranv(txtTSHonv.getText(), txtTSTennv.getText(), txtTSSdt.getText(), 
                datetmp, txtTSDiachi.getText(), txtTSTienluong.getText())==true) {
                    
                        nv_dto.setMaNV(xl.TaoMaMoi("NV", nv_bus.laysldv()));
                        nv_dto.setHo(txtTSHonv.getText());
                        nv_dto.setTen(txtTSTennv.getText());
                        nv_dto.setSdt(txtTSSdt.getText());
                        nv_dto.setNgaysinh(date.format(txtTSDate.getDate()));
                        nv_dto.setGioitinh(tmp);
                        nv_dto.setDiachi(txtTSDiachi.getText());
                        nv_dto.setTienLuong(Integer.parseInt(txtTSTienluong.getText()));
                        nv_bus.them(nv_dto);
                        fThem.setVisible(false);
                        
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
        lbTieude.setText("Sửa nhân viên");
        lbTieude.setFont(new Font("Time New Roman", Font.BOLD, 20));
        lbTieude.setBounds(0, 15, 600, 25);
        lbTieude.setForeground(Color.BLACK);
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        fSua.add(lbTieude);
        
        lbTSManv = new JLabel();
        lbTSManv.setText("Mã nhân viên:");
        lbTSManv.setBounds(55, 75, 120, 20);
        lbTSManv.setFont(new Font("Times New Roman",0,20));
        lbTSManv.setOpaque(true);
        fSua.add(lbTSManv);
        txtTSManv = new JTextField();
        txtTSManv.setBounds(195,75,270,25);
        txtTSManv.setFont(new Font("Arial",0,18));
        txtTSManv.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
        txtTSManv.setOpaque(true);
        txtTSManv.setEnabled(false);
        fSua.add(txtTSManv);
        
        lbTSHonv = new JLabel();
        lbTSHonv.setText("Họ nhân viên:");
        lbTSHonv.setBounds(55, 135, 120, 20);
        lbTSHonv.setFont(new Font("Times New Roman",0,20));
        lbTSHonv.setOpaque(true);
        fSua.add(lbTSHonv);
        txtTSHonv = new JTextField();
        txtTSHonv.setBounds(195,135,270,25);
        txtTSHonv.setFont(new Font("Arial",0,18));
        txtTSHonv.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
        txtTSHonv.setOpaque(true);
        fSua.add(txtTSHonv);
        
        lbTSTennv = new JLabel();
        lbTSTennv.setText("Tên nhân viên:");
        lbTSTennv.setBounds(55, 195, 120, 20);
        lbTSTennv.setFont(new Font("Times New Roman",0,20));
        lbTSTennv.setOpaque(true);
        fSua.add(lbTSTennv);
        txtTSTennv = new JTextField();
        txtTSTennv.setBounds(195,195,270,25);
        txtTSTennv.setFont(new Font("Arial",0,18));
        txtTSTennv.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
        txtTSTennv.setOpaque(true);
        fSua.add(txtTSTennv);
        
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
        
        lbTSTienluong = new JLabel();
        lbTSTienluong.setText("Tiền lương:");
        lbTSTienluong.setBounds(55, 545, 120, 23);
        lbTSTienluong.setFont(new Font("Times New Roman",0,20));
        lbTSTienluong.setOpaque(true);
        fSua.add(lbTSTienluong);
        txtTSTienluong = new JTextField();
        txtTSTienluong.setBounds(195,545,270,25);
        txtTSTienluong.setFont(new Font("Arial",0,18));
        txtTSTienluong.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
        txtTSTienluong.setOpaque(true);
        fSua.add(txtTSTienluong);
        
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
                NhanVienBUS nv_bus = new NhanVienBUS();
                NhanVienDTO nv_dto = new NhanVienDTO();
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
                if(nv_bus.Ktranv(txtTSHonv.getText(), txtTSTennv.getText(), txtTSSdt.getText(), 
                datetmp, txtTSDiachi.getText(), txtTSTienluong.getText())==true) {
                    
                        nv_dto.setMaNV(txtTSManv.getText());
                        nv_dto.setHo(txtTSHonv.getText());
                        nv_dto.setTen(txtTSTennv.getText());
                        nv_dto.setSdt(txtTSSdt.getText());
                        nv_dto.setNgaysinh(date.format(txtTSDate.getDate()));
                        nv_dto.setGioitinh(tmp);
                        nv_dto.setDiachi(txtTSDiachi.getText());
                        nv_dto.setTienLuong(Integer.parseInt(txtTSTienluong.getText()));
                        nv_bus.sua(nv_dto);
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
        lbTieude.setText("Thông tin chi tiết nhân viên");
        lbTieude.setFont(new Font("Time New Roman", Font.BOLD, 20));
        lbTieude.setBounds(0, 15, 600, 25);
        lbTieude.setForeground(Color.BLACK);
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        fXemchitiet.add(lbTieude);
        
        lbTSManv = new JLabel();
        lbTSManv.setText("Mã nhân viên:");
        lbTSManv.setBounds(55, 75, 120, 20);
        lbTSManv.setFont(new Font("Times New Roman",0,20));
        lbTSManv.setOpaque(true);
        fXemchitiet.add(lbTSManv);
        lbXTTManv = new JLabel();
        lbXTTManv.setBounds(195,75,270,25);;
        lbXTTManv.setFont(new Font("Arial",0,18));
        lbXTTManv.setHorizontalAlignment(JTextField.CENTER);
        lbXTTManv.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,Color.BLACK));
        lbXTTManv.setOpaque(true);
        fXemchitiet.add(lbXTTManv);
        
        lbTSHonv = new JLabel();
        lbTSHonv.setText("Họ nhân viên:");
        lbTSHonv.setBounds(55, 135, 120, 20);
        lbTSHonv.setFont(new Font("Times New Roman",0,20));
        lbTSHonv.setOpaque(true);
        fXemchitiet.add(lbTSHonv);
        lbXTTHonv = new JLabel();
        lbXTTHonv.setBounds(195,135,270,25);
        lbXTTHonv.setFont(new Font("Arial",0,18));
        lbXTTHonv.setHorizontalAlignment(JTextField.CENTER);
        lbXTTHonv.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,Color.BLACK));
        lbXTTHonv.setOpaque(true);
        fXemchitiet.add(lbXTTHonv);
        
        lbTSTennv = new JLabel();
        lbTSTennv.setText("Tên nhân viên:");
        lbTSTennv.setBounds(55, 195, 120, 20);
        lbTSTennv.setFont(new Font("Times New Roman",0,20));
        lbTSTennv.setOpaque(true);
        fXemchitiet.add(lbTSTennv);
        lbXTTTennv = new JLabel();
        lbXTTTennv.setBounds(195,195,270,25);
        lbXTTTennv.setFont(new Font("Arial",0,18));
        lbXTTTennv.setHorizontalAlignment(JTextField.CENTER);
        lbXTTTennv.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,Color.BLACK));
        lbXTTTennv.setOpaque(true);
        fXemchitiet.add(lbXTTTennv);
        
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
        
        lbTSTienluong = new JLabel();
        lbTSTienluong.setText("Tiền lương:");
        lbTSTienluong.setBounds(55, 545, 120, 23);
        lbTSTienluong.setFont(new Font("Times New Roman",0,20));
        lbTSTienluong.setOpaque(true);
        fXemchitiet.add(lbTSTienluong);
        lbXTTTienluong = new JLabel();
        lbXTTTienluong.setBounds(195,545,270,25);
        lbXTTTienluong.setFont(new Font("Arial",0,18));
        lbXTTTienluong.setHorizontalAlignment(JTextField.CENTER);
        lbXTTTienluong.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,Color.BLACK));
        lbXTTTienluong.setOpaque(true);
        fXemchitiet.add(lbXTTTienluong);
        
        fXemchitiet.setVisible(true);
    }
}
