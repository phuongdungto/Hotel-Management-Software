/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import BUS.DichVuBUS;
import BUS.Xulydulieu;
import DTO.DichVuDTO;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Duy
 */
public class DichVuGUI {
    protected JFrame fThem;
    protected JFrame fSua;
    protected JPanel pDichVu;
    protected JPanel pBangdsdv;
    protected JPanel pBangctdv;
    protected JPanel pTimkiem;
    protected JLabel lbTieude, lbTendichvu, lbgiatoithieu, lbgiatoida;
    protected JLabel lbMadv, lbTendv, lbDongia;
    protected JLabel lbtextmadv, lbtexttendv, lbtextdongia;
    protected JLabel lbTSMadv, lbTSTendv, lbTSDongia, lbTSTinhtrang;
    protected JTable tbdsdv;
    protected JTextField txtTendichvu, txtgiatoithieu, txtgiatoida;
    protected JTextField txtTSTendv, txtTSMadv, txtTSDongia, txtTSTinhtrang;
    protected JComboBox cbxTimkiem;
    protected JButton btTimKiem,btThem,btSua,btXoa,btCapNhat;
    protected JScrollPane sc, sc1;
    protected String DsTimkiem[] = { "Chọn kiểu cần tìm kiếm", "Tìm kiếm theo tên dịch vụ", "Tìm kiếm theo giá"};
    protected Color colorButton = new Color(36,36,36);
    protected Color colorLaybel = new Color(238, 238, 238);
    public DefaultTableModel model;
    public JPanel KhoiTaoPanel(int width, int height) {
        pDichVu = new JPanel();
        pDichVu.setLayout(null);
        pDichVu.setBounds(0, 0, width, height);
        pDichVu.setPreferredSize(new Dimension(width, height));
        pDichVu.setOpaque(true);
        
        lbTieude = new JLabel();
        lbTieude.setText("Dịch vụ");
        lbTieude.setFont(new Font("Time New Roman", Font.BOLD, 26));
        lbTieude.setBounds(0, 0, 1000, 50);
        lbTieude.setForeground(Color.BLACK);
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        
        pDichVu.add(lbTieude);
        
        pBangdsdv = Bangdsdv(1000, 450);
        pDichVu.add(pBangdsdv);
        
        pTimkiem = Timkiem(1000,150);
        pDichVu.add(pTimkiem);
        
        Chucnangcacnut();
        
        return pDichVu;
    }
    public JPanel Bangdsdv(int width, int height) {
        pBangdsdv=new JPanel();
        pBangdsdv.setLayout(null);
        pBangdsdv.setBounds(0, 200, width, height);
        pBangdsdv.setPreferredSize(new Dimension(width,height));
        pBangdsdv.setOpaque(true);
        DichVuBUS dvbus = new DichVuBUS();
        if(DichVuBUS.dsdv==null) {
            dvbus.docdsdv();
        }
        tbdsdv = Danhsachdichvu(DichVuBUS.dsdv);
        sc = new JScrollPane(tbdsdv);
        sc.setViewportView(tbdsdv);
        sc.setBounds(10, 20, 680, 370);
        pBangdsdv.add(sc);
        
        ImageIcon iconCapNhat = new ImageIcon("src/img/update.png");
        btCapNhat = new JButton("Cập nhật",iconCapNhat);
        btCapNhat.setBounds(20, 400, 160, 35);
        btCapNhat.setBackground(colorButton);
        btCapNhat.setForeground(Color.WHITE);
        btCapNhat.setBorderPainted(false);
        btCapNhat.setFocusPainted(false);
        pBangdsdv.add(btCapNhat);
        
        return pBangdsdv;
    }
    public JTable Danhsachdichvu(ArrayList<DichVuDTO> dsdv) {
        tbdsdv = new JTable();
        model = new DefaultTableModel();
        tbdsdv.getTableHeader().setBackground(colorButton);
        tbdsdv.getTableHeader().setForeground(Color.WHITE);
        tbdsdv.getTableHeader().setFont(new Font("Time New Roman", 0, 18));
        tbdsdv.getTableHeader().setPreferredSize(new Dimension(0,30));
        tbdsdv.setFont(new Font("Times New Roman",0,18));
        tbdsdv.setForeground(Color.BLACK);
        tbdsdv.setRowHeight(25);
        
        pBangctdv = new JPanel();
        pBangctdv.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        pBangctdv.setBounds(700, 20, 280, 370);
        pBangctdv.setLayout(null);
        pBangdsdv.add(pBangctdv);
        
        lbTieude = new JLabel();
        lbTieude.setText("Thông tin dịch vụ");
        lbTieude.setFont(new Font("Time New Roman", Font.BOLD, 20));
        lbTieude.setBounds(15, 15, 250, 25);
        lbTieude.setForeground(Color.BLACK);
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        pBangctdv.add(lbTieude);
        
        lbtextmadv = new JLabel();
        lbtextmadv.setText("Mã dịch vụ");
        lbtextmadv.setFont(new Font("Times New Roman",0,18));
        lbtextmadv.setBounds(15, 65, 150, 20);
        lbtextmadv.setOpaque(true);   
        pBangctdv.add(lbtextmadv);
        lbMadv = new JLabel();
        lbMadv.setBounds(15,105,250,25);
        lbMadv.setFont(new Font("Arial",0,18));
        lbMadv.setHorizontalAlignment(JTextField.CENTER);
        lbMadv.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,Color.BLACK));
        lbMadv.setOpaque(true);
        pBangctdv.add(lbMadv);
        
        lbtexttendv = new JLabel();
        lbtexttendv.setText("Tên dịch vụ");
        lbtexttendv.setFont(new Font("Times New Roman",0,18));
        lbtexttendv.setBounds(15, 145, 150, 20);
        lbtexttendv.setOpaque(true);   
        pBangctdv.add(lbtexttendv);
        lbTendv = new JLabel();
        lbTendv.setBounds(15,185,250,25);
        lbTendv.setFont(new Font("Arial",0,18));
        lbTendv.setHorizontalAlignment(JTextField.CENTER);
        lbTendv.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,Color.BLACK));
        lbTendv.setOpaque(true);
        pBangctdv.add(lbTendv);
        
        lbtextdongia = new JLabel();
        lbtextdongia.setText("Đơn giá");
        lbtextdongia.setFont(new Font("Times New Roman",0,18));
        lbtextdongia.setBounds(15, 225, 150, 20);
        lbtextdongia.setOpaque(true);   
        pBangctdv.add(lbtextdongia);
        lbDongia = new JLabel();
        lbDongia.setBounds(15,265,250,25);
        lbDongia.setFont(new Font("Arial",0,18));
        lbDongia.setHorizontalAlignment(JTextField.CENTER);
        lbDongia.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,Color.BLACK));
        lbDongia.setOpaque(true);
        pBangctdv.add(lbDongia);
             
        DichVuBUS dv = new DichVuBUS();
        Vector header = new Vector();
        header.add("Mã dịch vụ");
        header.add("Tên dịch vụ");
        header.add("Đơn giá");
        if(model.getRowCount()==0) 
            model = new DefaultTableModel(header, 0);
        for(DichVuDTO dvdto: dsdv) {
            Vector row = new Vector();
            Xulydulieu xl = new Xulydulieu();
            row.add(dvdto.getMaDV());
            row.add(dvdto.getTenDV());
            String tmp = String.valueOf(dvdto.getDonGia());
            row.add(xl.ChuyenKieuTien(tmp)+" VND");
            model.addRow(row);
        }
        tbdsdv.setModel(model);
        tbdsdv.getColumnModel().getColumn(0).setMinWidth(150);
        tbdsdv.getColumnModel().getColumn(1).setMinWidth(300);
        tbdsdv.getColumnModel().getColumn(2).setMinWidth(150);        
        DefaultTableCellRenderer center=new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        tbdsdv.setDefaultRenderer(tbdsdv.getColumnClass(0), center);
        tbdsdv.updateUI();
        tbdsdv.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                int i = tbdsdv.getSelectedRow();
                String mdv = tbdsdv.getModel().getValueAt(i, 0).toString();
                DichVuBUS dvbus = new DichVuBUS();
                DichVuDTO dvdto = new DichVuDTO();
                dvdto=dvbus.Lay1madichvu(mdv);
                lbMadv.setText(dvdto.getMaDV());
                lbTendv.setText(dvdto.getTenDV());
                String tmp = String.valueOf(dvdto.getDonGia());
                Xulydulieu xl = new Xulydulieu();
                lbDongia.setText(xl.ChuyenKieuTien(tmp) + " VND");
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
        return tbdsdv;
    }
    public JPanel Timkiem(int width, int height) {
        pTimkiem=new JPanel();
        pTimkiem.setLayout(null);
        pTimkiem.setBounds(0, 50, width, height);
        pTimkiem.setPreferredSize(new Dimension(width,height));
        pTimkiem.setOpaque(true);
        
        lbTendichvu = new JLabel("Theo tên dịch vụ: ");
        lbTendichvu.setFont(new Font("Times New Roman",0,18));
        lbTendichvu.setBounds(10, 5, 140, 20);
        lbTendichvu.setOpaque(true);
        pTimkiem.add(lbTendichvu);
        txtTendichvu =new JTextField();
        txtTendichvu.setBounds(150,2,370,25);
        txtTendichvu.setFont(new Font("Arial",0,18));
        txtTendichvu.setHorizontalAlignment(JTextField.CENTER);
        txtTendichvu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtTendichvu.setEditable(false);
        txtTendichvu.setOpaque(true);
        pTimkiem.add(txtTendichvu);
        
        lbgiatoithieu = new JLabel("Theo đơn giá: ");
        lbgiatoithieu.setFont(new Font("Times New Roman",0,18));
        lbgiatoithieu.setBounds(10, 45, 130, 20);
        lbgiatoithieu.setOpaque(true);
        pTimkiem.add(lbgiatoithieu);
        txtgiatoithieu =new JTextField();
        txtgiatoithieu.setBounds(150, 45, 150, 25);
        txtgiatoithieu.setFont(new Font("Arial",0,18));
        txtgiatoithieu.setHorizontalAlignment(JTextField.CENTER);
        txtgiatoithieu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtgiatoithieu.setEditable(false);
        txtgiatoithieu.setOpaque(true);
        pTimkiem.add(txtgiatoithieu);
        
        lbgiatoida = new JLabel("=>");
        lbgiatoida.setFont(new Font("Times New Roman",0,18));
        lbgiatoida.setBounds(325, 45, 30, 20);
        lbgiatoida.setOpaque(true);
        pTimkiem.add(lbgiatoida);
        txtgiatoida =new JTextField();
        txtgiatoida.setBounds(370, 45, 150, 25);
        txtgiatoida.setFont(new Font("Arial",0,18));
        txtgiatoida.setHorizontalAlignment(JTextField.CENTER);
        txtgiatoida.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtgiatoida.setEditable(false);
        txtgiatoida.setOpaque(true);
        pTimkiem.add(txtgiatoida);
        
        cbxTimkiem = new JComboBox(DsTimkiem);
        cbxTimkiem.setBounds(570, 10, 180, 30);
        pTimkiem.add(cbxTimkiem);
        
        cbxTimkiem.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent arg0) {
                if (cbxTimkiem.getSelectedItem().equals(DsTimkiem[0])){
                    txtTendichvu.setEditable(false);
                    txtgiatoithieu.setEditable(false);
                    txtgiatoida.setEditable(false);
                }
                if (cbxTimkiem.getSelectedItem().equals(DsTimkiem[1])){
                    txtTendichvu.setEditable(true);
                    txtgiatoithieu.setEditable(false);
                    txtgiatoida.setEditable(false);
                }
                if (cbxTimkiem.getSelectedItem().equals(DsTimkiem[2])){
                    txtTendichvu.setEditable(false);
                    txtgiatoithieu.setEditable(true);
                    txtgiatoida.setEditable(true);
                }
            }
        });
        
        ImageIcon iconsearch = new ImageIcon("src/img/search.png");
        btTimKiem = new JButton("Tìm kiếm",iconsearch);
        btTimKiem.setBounds(570, 50, 140, 35);
        btTimKiem.setBackground(colorButton);
        btTimKiem.setForeground(Color.WHITE);
        btTimKiem.setBorderPainted(false);
        btTimKiem.setFocusPainted(false);
        pTimkiem.add(btTimKiem);
        
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
                int i = tbdsdv.getSelectedRow();
                if(i<0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn dịch vụ cần sửa!!!");
                } else {
                    KhoiTaoJFrameSua();
                    DichVuBUS dv_bus = new DichVuBUS();
                    DichVuDTO dv_dto = new DichVuDTO();
                    String tmp = tbdsdv.getModel().getValueAt(i, 0).toString();
                    dv_dto = dv_bus.Lay1madichvu(tmp);
                    txtTSMadv.setText(dv_dto.getMaDV());
                    txtTSTendv.setText(dv_dto.getTenDV());
                    txtTSDongia.setText(String.valueOf(dv_dto.getDonGia()));
                    
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
                int i = tbdsdv.getSelectedRow();
                if(i<0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn dịch vụ cần xóa!!!");
                } else {
                    DichVuBUS dv_bus = new DichVuBUS();
                    DichVuDTO dv_dto = new DichVuDTO();
                    String tmp = tbdsdv.getModel().getValueAt(i, 0).toString();
                    dv_dto = dv_bus.Lay1madichvu(tmp);
                    txtTSMadv = new JTextField();
                    txtTSMadv.setText(dv_dto.getMaDV());
                    int input=JOptionPane.showConfirmDialog(pDichVu, "Bạn có chắc muốn xóa", "Xóa dịch vụ", JOptionPane.YES_NO_OPTION);
                    if(input==0) {
                        dv_bus.xoa(dv_dto);
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
                DichVuBUS dvbus = new DichVuBUS();
                if(DichVuBUS.dsdv==null) {
                    dvbus.docdsdv();
                }
                JTable tbdsdvtmp = new JTable();
                tbdsdvtmp = Danhsachdichvu(DichVuBUS.dsdv);
                sc1 = new JScrollPane(tbdsdvtmp);
                sc1.setBounds(0, 0, 680, 370);
                sc.removeAll();
                sc.add(sc1);
                sc.repaint();
                sc.revalidate();
                pBangdsdv.add(sc);
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
        
        btTimKiem.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                ArrayList<DichVuDTO> dsdvtmp = new ArrayList<>();
                DichVuBUS dv_bus = new DichVuBUS();  
                if(cbxTimkiem.getSelectedItem().equals(DsTimkiem[0]))
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn kiểu tìm kiếm!!!");
                else {
                    if(cbxTimkiem.getSelectedItem().equals(DsTimkiem[1])){
                        dsdvtmp=dv_bus.Timkiemten(txtTendichvu.getText());
                    }
                    if(cbxTimkiem.getSelectedItem().equals(DsTimkiem[2])){
                        if(dv_bus.Ktraso(txtgiatoithieu.getText())==true && dv_bus.Ktraso(txtgiatoida.getText())==true) {
                            dsdvtmp=dv_bus.Timkiemgia(txtgiatoithieu.getText(), txtgiatoida.getText());
                        } else {
                            JOptionPane.showMessageDialog(null, "Số tiền tìm kiếm không hợp lệ!!!");
                        }
                    }
                    if(dsdvtmp != null) {                      
                        JTable tbdsdvtmp = new JTable();
                        tbdsdvtmp = Danhsachdichvu(dsdvtmp);
                        sc1 = new JScrollPane(tbdsdvtmp);
                        sc1.setBounds(0, 0, 680, 370);
                        sc.removeAll();
                        sc.add(sc1);
                        sc.repaint();
                        sc.revalidate();
                        pBangdsdv.add(sc);
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
        fThem.setBounds(0,0,500,300);
        fThem.setPreferredSize(new Dimension(500,300));
        fThem.setLayout(null);
        fThem.setUndecorated(false);
        
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        fThem.setLocation((int)d.getWidth()/2 - (int)fThem.getPreferredSize().getWidth()/2,
                      (int)d.getHeight()/2 - (int)fThem.getPreferredSize().getHeight()/2);
        
        lbTieude = new JLabel();
        lbTieude.setText("Thêm dịch vụ");
        lbTieude.setFont(new Font("Time New Roman", Font.BOLD, 20));
        lbTieude.setBounds(0, 15, 500, 25);
        lbTieude.setForeground(Color.BLACK);
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        fThem.add(lbTieude);
        
        lbTSTendv = new JLabel();
        lbTSTendv.setText("Tên dịch vụ:");
        lbTSTendv.setBounds(35, 75, 120, 20);
        lbTSTendv.setFont(new Font("Times New Roman",0,20));
        lbTSTendv.setOpaque(true);
        fThem.add(lbTSTendv);
        txtTSTendv = new JTextField();
        txtTSTendv.setBounds(155,75,250,25);
        txtTSTendv.setFont(new Font("Arial",0,18));
        txtTSTendv.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
        txtTSTendv.setOpaque(true);
        fThem.add(txtTSTendv);
        
        lbTSDongia = new JLabel();
        lbTSDongia.setText("Đơn giá:");
        lbTSDongia.setBounds(35, 135, 120, 20);
        lbTSDongia.setFont(new Font("Times New Roman",0,20));
        lbTSDongia.setOpaque(true);
        fThem.add(lbTSDongia);
        txtTSDongia = new JTextField();
        txtTSDongia.setBounds(155,135,250,25);
        txtTSDongia.setFont(new Font("Arial",0,18));
        txtTSDongia.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
        txtTSDongia.setOpaque(true);
        fThem.add(txtTSDongia);
        
        JButton btHoanThanh = new JButton("Hoàn thành");
        btHoanThanh.setBounds(190, 195, 120, 35);
        btHoanThanh.setBackground(colorButton);
        btHoanThanh.setForeground(Color.WHITE);
        btHoanThanh.setBorderPainted(false);
        btHoanThanh.setFocusPainted(false);
        fThem.add(btHoanThanh);
        btHoanThanh.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                DichVuBUS dv_bus = new DichVuBUS();
                DichVuDTO dv_dto = new DichVuDTO();
                Xulydulieu xl = new Xulydulieu();
                if(dv_bus.Ktradv(txtTSTendv.getText(), txtTSDongia.getText())==true) {
                    dv_dto.setMaDV(xl.TaoMaMoi("DV", dv_bus.laysldv()));
                    dv_dto.setTenDV(txtTSTendv.getText());
                    dv_dto.setDonGia(Integer.parseInt(txtTSDongia.getText()));
                    dv_bus.them(dv_dto);
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
        fSua.setBounds(0,0,500,350);
        fSua.setPreferredSize(new Dimension(500,350));
        fSua.setLayout(null);
        fSua.setUndecorated(false);
        
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        fSua.setLocation((int)d.getWidth()/2 - (int)fSua.getPreferredSize().getWidth()/2,
                      (int)d.getHeight()/2 - (int)fSua.getPreferredSize().getHeight()/2);
        
        lbTieude = new JLabel();
        lbTieude.setText("Sửa dịch vụ");
        lbTieude.setFont(new Font("Time New Roman", Font.BOLD, 20));
        lbTieude.setBounds(0, 15, 500, 25);
        lbTieude.setForeground(Color.BLACK);
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        fSua.add(lbTieude);
        
        lbTSMadv = new JLabel();
        lbTSMadv.setText("Mã dịch vụ:");
        lbTSMadv.setBounds(35, 75, 120, 20);
        lbTSMadv.setFont(new Font("Times New Roman",0,20));
        lbTSMadv.setOpaque(true);
        fSua.add(lbTSMadv);
        txtTSMadv = new JTextField();
        txtTSMadv.setBounds(155,75,250,25);
        txtTSMadv.setFont(new Font("Arial",0,18));
        txtTSMadv.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
        txtTSMadv.setForeground(Color.BLACK);
        txtTSMadv.setBackground(Color.WHITE);
        txtTSMadv.setOpaque(true);
        txtTSMadv.setEditable(false);
        fSua.add(txtTSMadv);
        
        lbTSTendv = new JLabel();
        lbTSTendv.setText("Tên dịch vụ:");
        lbTSTendv.setBounds(35, 135, 120, 20);
        lbTSTendv.setFont(new Font("Times New Roman",0,20));
        lbTSTendv.setOpaque(true);
        fSua.add(lbTSTendv);
        txtTSTendv = new JTextField();
        txtTSTendv.setBounds(155,135,250,25);
        txtTSTendv.setFont(new Font("Arial",0,18));
        txtTSTendv.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
        txtTSTendv.setOpaque(true);
        fSua.add(txtTSTendv);
        
        lbTSDongia = new JLabel();
        lbTSDongia.setText("Đơn giá:");
        lbTSDongia.setBounds(35, 195, 120, 20);
        lbTSDongia.setFont(new Font("Times New Roman",0,20));
        lbTSDongia.setOpaque(true);
        fSua.add(lbTSDongia);
        txtTSDongia = new JTextField();
        txtTSDongia.setBounds(155,195,250,25);
        txtTSDongia.setFont(new Font("Arial",0,18));
        txtTSDongia.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
        txtTSDongia.setOpaque(true);
        fSua.add(txtTSDongia);
        
        JButton btHoanThanh = new JButton("Hoàn thành");
        btHoanThanh.setBounds(190, 255, 120, 35);
        btHoanThanh.setBackground(colorButton);
        btHoanThanh.setForeground(Color.WHITE);
        btHoanThanh.setBorderPainted(false);
        btHoanThanh.setFocusPainted(false);
        fSua.add(btHoanThanh);
        btHoanThanh.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                DichVuBUS dv_bus = new DichVuBUS();
                DichVuDTO dv_dto = new DichVuDTO();
                Xulydulieu xl = new Xulydulieu();
                if(dv_bus.Ktradv(txtTSTendv.getText(), txtTSDongia.getText())==true) {
                    dv_dto.setMaDV(txtTSMadv.getText());
                    dv_dto.setTenDV(txtTSTendv.getText());
                    dv_dto.setDonGia(Integer.parseInt(txtTSDongia.getText()));
                    dv_bus.sua(dv_dto);
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
}
