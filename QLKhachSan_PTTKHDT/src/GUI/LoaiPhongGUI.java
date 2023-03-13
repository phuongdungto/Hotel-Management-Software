/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import BUS.ChiTietKMDichVuBUS;
import BUS.KhuyenMaiDichVuBUS;
import BUS.LoaiPhongBUS;
import BUS.Xulydulieu;
import BUS.iconBUS;
import DTO.ChiTietKMDichVuDTO;
import DTO.KhuyenMaiDichVuDTO;
import DTO.KhuyenMaiPhongDTO;
import DTO.LoaiPhongDTO;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author T P Dung
 */
public class LoaiPhongGUI {
    protected JLabel lbTieude;
    protected JPanel pnLoaiphong;
    protected JComboBox cbTimkiem;
    protected JTable tblp;
    protected JTextField txTenlp;
    protected DefaultTableModel model;
    protected JButton btThem;
    protected JButton btSua;
    protected JButton btXoa;
    protected JButton btTimkiem;
    protected JButton btCapnhat;
    protected JButton btXemchitiet;

    protected JButton btThemcn;
    protected JButton btSuacn;
    
    protected JFrame fSua;
    protected JFrame fThem;
    protected JTextField txMalp;
    
    protected Color colorButton = new Color(36,36,36);
    
    protected JLabel lbMalp1;
    protected JLabel lbTenlp1;
    protected JTextField txMaPhongcn;
    protected JTextField txTenPhongcn;
    protected JScrollPane scrdskm;
    protected JScrollPane scrdskm1;
    iconBUS icon = new iconBUS();
    
    public JPanel KhoiTaoPanel(int width, int height){
        pnLoaiphong=new JPanel();
        pnLoaiphong.setLayout(null);
        pnLoaiphong.setBounds(0, 0, width, height);
        pnLoaiphong.setPreferredSize(new Dimension(width,height));
        pnLoaiphong.setOpaque(true);
        LoaiPhongBUS dslp = new LoaiPhongBUS();
        if(LoaiPhongBUS.dslp== null){
            dslp.docdslp();
        }
        init();
        return pnLoaiphong;
    }
    public void init(){
        JLabel lbTimkiem = new JLabel();
        lbTimkiem.setBounds(20, 90, 100, 30);
        lbTimkiem.setText("Tìm Kiếm");
        lbTimkiem.setFont(new Font("Times New Roman",Font.BOLD,16));
        lbTimkiem.setForeground(Color.BLACK);
        lbTimkiem.setHorizontalAlignment(JLabel.LEFT);
        pnLoaiphong.add(lbTimkiem);
        
        String sp[] = {"Chọn kiểm tìm kiếm","Theo mã loại phòng","Theo tên loại phòng"};
        
        cbTimkiem=new JComboBox(sp);
        cbTimkiem.setSelectedIndex(0);
        cbTimkiem.setFont(new Font("Arial",0,16));
        cbTimkiem.setBounds(120, 90, 270, 30);
        pnLoaiphong.add(cbTimkiem);
        
        pnLoaiphong.add(cbTimkiem);
        
        JLabel lbTenlp9 = new JLabel();
        lbTenlp9.setBounds(20, 150, 100, 30);
        lbTenlp9.setText("Tên loại phòng");
        lbTenlp9.setFont(new Font("Times New Roman",Font.BOLD,16));
        lbTenlp9.setForeground(Color.BLACK);
        lbTenlp9.setHorizontalAlignment(JLabel.LEFT);
        pnLoaiphong.add(lbTenlp9);
        
        txTenlp = new JTextField();
        txTenlp.setBounds(120, 150, 420, 30);
        pnLoaiphong.add(txTenlp);
        
        JLabel lbMalp9 = new JLabel();
        lbMalp9.setBounds(20, 210, 100, 30);
        lbMalp9.setText("Mã loại phòng");
        lbMalp9.setFont(new Font("Times New Roman",Font.BOLD,16));
        lbMalp9.setForeground(Color.BLACK);
        lbMalp9.setHorizontalAlignment(JLabel.LEFT);
        pnLoaiphong.add(lbMalp9);
        
        txMalp = new JTextField();
        txMalp.setBounds(120, 210, 420, 30);
        pnLoaiphong.add(txMalp);
        
        lbTieude=new JLabel();
        lbTieude.setText("Loại phòng");
        lbTieude.setFont(new Font("Times New Roman",Font.BOLD,26));
        lbTieude.setBounds(300, 10, 280, 30);
        lbTieude.setForeground(Color.BLACK);
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        pnLoaiphong.add(lbTieude);
        
        ImageIcon iconthem = new ImageIcon(icon.iconthem);
        btThem = new JButton("Thêm",iconthem);
        btThem.setBounds(680, 210, 96, 35);
        btThem.setBackground(colorButton);
        btThem.setForeground(Color.WHITE);
        btThem.setBorderPainted(false);
        btThem.setFocusPainted(false);
        pnLoaiphong.add(btThem);
        
        ImageIcon icontimkiem = new ImageIcon(icon.icontimkiem);
        btTimkiem = new JButton("Tìm kiếm",icontimkiem);
        btTimkiem.setBounds(560, 90, 120, 35);
        btTimkiem.setBackground(colorButton);
        btTimkiem.setForeground(Color.WHITE);
        btTimkiem.setBorderPainted(false);
        btTimkiem.setFocusPainted(false);
        pnLoaiphong.add(btTimkiem);
        
        ImageIcon iconsua = new ImageIcon(icon.iconsua);
        btSua = new JButton("Sửa",iconsua);
        btSua.setBounds(790, 210, 95, 35);
        btSua.setBackground(colorButton);
        btSua.setForeground(Color.WHITE);
        btSua.setBorderPainted(false);
        btSua.setFocusPainted(false);
        pnLoaiphong.add(btSua);
        
        ImageIcon iconxoa = new ImageIcon(icon.iconxoa);
        btXoa = new JButton("Xóa",iconxoa);
        btXoa.setBounds(900, 210, 95, 35);
        btXoa.setBackground(colorButton);
        btXoa.setForeground(Color.WHITE);
        btXoa.setBorderPainted(false);
        btXoa.setFocusPainted(false);
        pnLoaiphong.add(btXoa);
        
        ImageIcon iconcapnhat = new ImageIcon(icon.iconcapnhat);
        btCapnhat = new JButton("Cập nhật",iconcapnhat);
        btCapnhat.setBounds(50, 605, 140, 35);
        btCapnhat.setBackground(colorButton);
        btCapnhat.setForeground(Color.WHITE);
        btCapnhat.setBorderPainted(false);
        btCapnhat.setFocusPainted(false);
        pnLoaiphong.add(btCapnhat);
        
        JPanel pnlchitietlp = new JPanel();
        pnlchitietlp.setLayout(null);
        pnlchitietlp.setBounds(710, 250, 280, 350);
        pnlchitietlp.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        pnLoaiphong.add(pnlchitietlp);
        
        JLabel tieude= new JLabel();
        tieude.setText("THÔNG TIN LOẠI PHÒNG");
        tieude.setFont(new Font("Times New Roman",Font.BOLD,16));
        tieude.setBounds(0, 10, 280, 20);
        tieude.setForeground(Color.BLACK);
        tieude.setHorizontalAlignment(JLabel.CENTER);
        pnlchitietlp.add(tieude);
        
        JLabel lbMakmdv= new JLabel();
        lbMakmdv.setText("Mã Loại Phòng");
        lbMakmdv.setFont(new Font("Times New Roman",Font.BOLD,16));
        lbMakmdv.setBounds(10, 40, 270, 20);
        lbMakmdv.setForeground(Color.BLACK);
        lbMakmdv.setHorizontalAlignment(JLabel.LEFT);
        pnlchitietlp.add(lbMakmdv);
        
        lbMalp1= new JLabel();
        lbMalp1.setText("");
        lbMalp1.setFont(new Font("Times New Roman",Font.BOLD,16));
        lbMalp1.setBounds(20, 70, 240, 20);
        lbMalp1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
   
        lbMalp1.setHorizontalAlignment(JLabel.CENTER);
        pnlchitietlp.add(lbMalp1);
        
        JLabel lbTenlp2= new JLabel();
        lbTenlp2.setText("Tên KMDV");
        lbTenlp2.setFont(new Font("Times New Roman",Font.BOLD,16));
        lbTenlp2.setBounds(10, 100, 270, 20);
        lbTenlp2.setForeground(Color.BLACK);
        lbTenlp2.setHorizontalAlignment(JLabel.LEFT);
        pnlchitietlp.add(lbTenlp2);
        
        lbTenlp1= new JLabel();
        lbTenlp1.setText("");
        lbTenlp1.setFont(new Font("Times New Roman",Font.BOLD,16));
        lbTenlp1.setBounds(20, 130, 240, 20);
        lbTenlp1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
    
        lbTenlp1.setHorizontalAlignment(JLabel.CENTER);
        pnlchitietlp.add(lbTenlp1);
        txMalp.setEditable(false);
        txTenlp.setEditable(false);
        Khoitaoscroll();  
        
        CacNutChucNang();
    }
    public void Khoitaoscroll(){
        tblp = Danhsachloaiphong(LoaiPhongBUS.dslp);
        scrdskm=new JScrollPane(tblp);
        scrdskm.setViewportView(tblp);
        scrdskm.setBounds(0, 250, 700, 350); // Tùy mỗi người
        pnLoaiphong.add(scrdskm);
    }
    public JTable Danhsachloaiphong(ArrayList<LoaiPhongDTO> arr){
//        tbdskmdv=new JTable();
//        tbdskmdv.getTableHeader().setBackground(new Color(3,169,244));
//        tbdskmdv.getTableHeader().setForeground(Color.WHITE);
//        tbdskmdv.getTableHeader().setFont(new Font("Times New Roman",0,18));
//        tbdskmdv.getTableHeader().setPreferredSize(new Dimension(0,30));
//        tbdskmdv.setFont(new Font("Times New Roman",0,18));
//        tbdskmdv.setForeground(Color.BLACK);
//        tbdskmdv.setRowHeight(25);
        
        tblp=new JTable();
        tblp.getTableHeader().setBackground(colorButton);
        tblp.getTableHeader().setForeground(Color.WHITE);
        tblp.getTableHeader().setFont(new Font("Times New Roman",0,18));
        tblp.getTableHeader().setPreferredSize(new Dimension(0,30));
        tblp.setFont(new Font("Times New Roman",0,18));
        tblp.setForeground(Color.BLACK);
        tblp.setRowHeight(25);
        
        LoaiPhongBUS lpbus=new LoaiPhongBUS();
        if (LoaiPhongBUS.dslp==null){
            lpbus.docdslp();
        }
        model=new DefaultTableModel();
        Vector cottieude=new Vector();
        cottieude.add("Stt");
        cottieude.add("Mã loại phòng");
        cottieude.add("Tên loại phòng");
        if (model.getRowCount()==0){
            model=new DefaultTableModel(cottieude, 0);
        }
        int i=0;
        for (LoaiPhongDTO k : arr){
            i++;
            Vector dong=new Vector();                
            dong.add(i);
            dong.add(k.getMaLoai());
            dong.add(k.getTenLoai());
            model.addRow(dong);
        }
        tblp.setModel(model);
        tblp.getColumnModel().getColumn(0).setMaxWidth(50);
//        tbdskmdv.getColumnModel().getColumn(2).setMaxWidth(250);
//        tbdskmdv.getColumnModel().getColumn(4).setMinWidth(150);
//        tbdskmdv.getColumnModel().getColumn(5).setMinWidth(150);
        
        //--------------------Căn giữa trong bảng-----------------------//
        DefaultTableCellRenderer center=new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        tblp.setDefaultRenderer(tblp    .getColumnClass(0), center);
        tblp.updateUI();
        tblp.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                int i=tblp.getSelectedRow();
                String s=tblp.getModel().getValueAt(i, 1).toString();
                String s1=tblp.getModel().getValueAt(i, 2).toString();
                lbMalp1.setText(s);
                lbTenlp1.setText(s1);
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
        return tblp;
        //--------------------------------------------------------------//
    }
    public void JFramechucnang(int x){
        fThem = new JFrame();
        fThem.setBounds(550, 100, 500, 360);
        fThem.setLayout(null);
        fThem.setBackground(Color.WHITE);
       
        JLabel tieude= new JLabel();
        if(x==0){
            tieude.setText("Thêm loại phòng");
        }else{
            tieude.setText("Sửa loại phòng");
        }
        tieude.setFont(new Font("Times New Roman",Font.BOLD,23));
        tieude.setBounds(0, 10, 500, 30);
        tieude.setForeground(Color.BLACK);
        tieude.setHorizontalAlignment(JLabel.CENTER);
        tieude.setBackground(colorButton);
        fThem.add(tieude);
        
        JLabel lbMaPhong = new JLabel();
        lbMaPhong.setBounds(20, 110, 100, 30);
        lbMaPhong.setText("Mã Phòng");
        lbMaPhong.setFont(new Font("Times New Roman",Font.BOLD,16));
        lbMaPhong.setForeground(Color.BLACK);
        lbMaPhong.setHorizontalAlignment(JLabel.LEFT);
        fThem.add(lbMaPhong);
        
        txMaPhongcn = new JTextField();
        txMaPhongcn.setBounds(130, 110, 300, 30);
        txMaPhongcn.setFont(new Font("Times New Roman",Font.BOLD,16));
       
        fThem.add(txMaPhongcn);
        
        JLabel lbTenPhong = new JLabel();
        lbTenPhong.setBounds(20, 160, 100, 30);
        lbTenPhong.setText("Tên Phòng");
        lbTenPhong.setFont(new Font("Times New Roman",Font.BOLD,16));
        lbTenPhong.setForeground(Color.BLACK);
        lbTenPhong.setHorizontalAlignment(JLabel.LEFT);
        fThem.add(lbTenPhong);
        
        txTenPhongcn = new JTextField();
        txTenPhongcn.setBounds(130, 160, 300, 30);
        txTenPhongcn.setFont(new Font("Times New Roman",Font.BOLD,16));
        
        fThem.add(txTenPhongcn);
       
        ImageIcon iconthem = new ImageIcon(icon.iconthem);
        btThemcn = new JButton("Thêm",iconthem);
        if(x==0){
            btThemcn.setBounds(210, 260, 96, 35);
        }else{
            btThemcn.setBounds(0, 0, 0, 0);
        }
        btThemcn.setBackground(colorButton);
        btThemcn.setForeground(Color.WHITE);
        btThemcn.setBorderPainted(false);
        btThemcn.setFocusPainted(false);
        fThem.add(btThemcn);
        
        ImageIcon iconsua = new ImageIcon(icon.iconsua);
        btSuacn = new JButton("Lưu",iconsua);
        if(x==0){
            btSuacn.setBounds(0, 0, 0, 0);
        }else{
            btSuacn.setBounds(210, 260, 96, 35);
        }
        btSuacn.setBackground(colorButton);
        btSuacn.setForeground(Color.WHITE);
        btSuacn.setBorderPainted(false);
        btSuacn.setFocusPainted(false);
        fThem.add(btSuacn);
        CacNutChucNangJFrameChucNang();
    }
    public void CacNutChucNangJFrameChucNang(){      
        btThemcn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                LoaiPhongDTO p = new  LoaiPhongDTO();
                LoaiPhongBUS lp = new LoaiPhongBUS();
                p.setMaLoai(txMaPhongcn.getText());
                p.setTenLoai(txTenPhongcn.getText());
                if(txMaPhongcn.getText().equals("") || txTenPhongcn.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Nhập đầy đủ thông tin");
                }
                else{
                    if(lp.kiemtratrung(p)==1){
                        lp.them(p);
                        fThem.setVisible(false);
                    }else{
                        JOptionPane.showMessageDialog(null, "Loại phòng đã tồn tại");
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
                btThemcn.setBackground(new Color(36,36,36));
            }
        
        });
        btSuacn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                LoaiPhongDTO p = new  LoaiPhongDTO();
                LoaiPhongDTO p1 = new  LoaiPhongDTO();
                LoaiPhongBUS kmdv = new LoaiPhongBUS();
                int i=tblp.getSelectedRow();
                if(i!=-1){
                    String s=tblp.getModel().getValueAt(i, 1).toString();
                    String s1=tblp.getModel().getValueAt(i, 2).toString();
                    p.setMaLoai(s);
                    p.setTenLoai(s1);
                    p1.setMaLoai(txMaPhongcn.getText());
                    p1.setTenLoai(txTenPhongcn.getText());
                    LoaiPhongBUS lp = new LoaiPhongBUS();  
                        lp.sua(p,p1);
                        fThem.setVisible(false);
                }else{
                     JOptionPane.showMessageDialog(null, "Chọn loại phòng cần sửa");
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
                btSuacn.setBackground(Color.DARK_GRAY);
                btSuacn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                btSuacn.setBackground(new Color(36,36,36));
            }
        });
    }
    public void CacNutChucNang(){
        btThem.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                JFramechucnang(0);
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
                btThem.setBackground(new Color(36,36,36));
            }
        });
        btSua.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                LoaiPhongDTO p = new  LoaiPhongDTO();
                int i=tblp.getSelectedRow();
                if(i!=-1){
                    JFramechucnang(1);
                    fThem.setVisible(true);
                    String s=tblp.getModel().getValueAt(i, 1).toString();
                    String s1=tblp.getModel().getValueAt(i, 2).toString();
                    txMaPhongcn.setText(s);
                    txTenPhongcn.setText(s1);
                }else{
                    JOptionPane.showMessageDialog(null, "Chọn khuyến mãi phòng cần sửa");
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
                btSua.setBackground(new Color(36,36,36));
            }
        });
        btXoa.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                System.out.println("hello");
                LoaiPhongDTO p = new LoaiPhongDTO();
                LoaiPhongBUS lp = new LoaiPhongBUS();
                int i=tblp.getSelectedRow();
                if(i!=-1){
                    String s=tblp.getModel().getValueAt(i, 1).toString();
                    String s1=tblp.getModel().getValueAt(i, 2).toString();
                    p.setMaLoai(s);
                    p.setTenLoai(s1);
                    lp.xoa(p);
                }else{
                    JOptionPane.showMessageDialog(null, "Chọn khuyến mãi dịch vụ cần xóa");
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
                scrdskm.removeAll();
                JTable tbl = Danhsachloaiphong(LoaiPhongBUS.dslp);
                scrdskm1= new JScrollPane(tbl);
                scrdskm1.setBounds(0, 0, 700, 350);
                scrdskm.add(scrdskm1);
                scrdskm.repaint();
                scrdskm.revalidate();
                pnLoaiphong.add(scrdskm);
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
                btCapnhat.setBackground(new Color(36,36,36));
            }
        });
        btTimkiem.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                LoaiPhongBUS dslp= new LoaiPhongBUS();
                if(cbTimkiem.getSelectedItem().equals("Theo mã loại phòng")){
                    scrdskm.removeAll();
                    JTable tbl = Danhsachloaiphong(dslp.timkiemtheoma(txMalp.getText()));
                    scrdskm1= new JScrollPane(tbl);
                    scrdskm1.setBounds(0, 0, 700, 350);
                    scrdskm.add(scrdskm1);
                    scrdskm.repaint();
                    scrdskm.revalidate();
                    pnLoaiphong.add(scrdskm);
                }else if(cbTimkiem.getSelectedItem().equals("Theo tên loại phòng")){
                    System.out.println("hello");
                    scrdskm.removeAll();
                    JTable tbl = Danhsachloaiphong(dslp.timkiemtheoten(txTenlp.getText()));
                    scrdskm1= new JScrollPane(tbl);
                    scrdskm1.setBounds(0, 0, 700, 350);
                    scrdskm.add(scrdskm1);
                    scrdskm.repaint();
                    scrdskm.revalidate();
                    pnLoaiphong.add(scrdskm);
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
                if (cbTimkiem.getSelectedItem().equals("Theo tên loại phòng")){
                    txMalp.setEditable(false);
                    txTenlp.setEditable(true);
                }
                else if(cbTimkiem.getSelectedItem().equals("Theo mã loại phòng")){
                    txMalp.setEditable(true);
                    txTenlp.setEditable(false);
                }else{
                    txMalp.setEditable(false);
                    txTenlp.setEditable(false);
                }
            }
        });
    }
}
