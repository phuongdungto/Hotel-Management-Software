/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.ChiTietKMDichVuBUS;
import BUS.ChiTietKMPhongBUS;
import BUS.KhuyenMaiDichVuBUS;
import BUS.KhuyenMaiPhongBUS;
import BUS.LoaiPhongBUS;
import BUS.PhongBUS;
import BUS.Xulydulieu;
import BUS.iconBUS;
import DTO.ChiTietKMDichVuDTO;
import DTO.ChiTietKMPhongDTO;
import DTO.KhuyenMaiDichVuDTO;
import DTO.KhuyenMaiPhongDTO;
import DTO.LoaiPhongDTO;
import DTO.PhongDTO;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import javax.swing.JButton;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author T P Dung
 */
public class KhuyenMaiPhongGUI {
       protected JLabel lbTieude;
    protected JPanel pKMPhong;
    protected JComboBox cbTimkiem;
    protected JTable tbdskmp;
    protected JTextField txTenKMP;
    protected DefaultTableModel model;
    protected JDateChooser daybd;
    protected JDateChooser daykt;
    protected JButton btThem;
    protected JButton btSua;
    protected JButton btXoa;
    protected JButton btTimkiem;
    protected JButton btCapnhat;
    protected JButton btXemchitiet;
    
    protected JTextField txTenKMPcn;
    protected JDateChooser daybdcn;
    protected JDateChooser dayktcn;
    
    protected JButton btThemcn;
    protected JButton btSuacn;
    
    protected JFrame fThemct;
    protected JComboBox cbTinhtrang;
    protected JFrame fThem;
    protected JTextField txp;
    protected JTextField txPtkm;
    protected JTable tbdsctkmp;
    protected DefaultTableModel model1;

    protected Color colorButton = new Color(36,36,36);
    protected JLabel txMakmp;
    protected JLabel txTenkmp;
    protected JLabel txNgaybd;
    protected JLabel txNgaykt;
    protected JLabel txTinhtrang;
    
    protected JLabel txMakmpct;
    protected JLabel txTenkmpct;
    protected JLabel txNgaybdct;
    protected JLabel txNgayktct;
    protected JLabel txTinhtrangct;
    
    protected JButton btThemct;
    protected JButton btSuact;
    protected JButton btXoact;
    protected JButton btXemp;
    
    protected JButton btThemkm;
    protected JButton btSuakm;
    protected JScrollPane scrdskm;
    protected JScrollPane scrdskm1;
    protected JTextField txMaphong1;
    protected JComboBox cbLoaiphong;
    protected JTextField txMaphongct;
    protected JTable tbp;
    int stt;
    iconBUS icon = new iconBUS();
       
    protected JComboBox cbTendv;
    public JPanel KhoiTaoPanel(int width, int height){
        pKMPhong=new JPanel();
        pKMPhong.setLayout(null);
        pKMPhong.setBounds(0, 0, width, height);
        pKMPhong.setPreferredSize(new Dimension(width,height));
        pKMPhong.setOpaque(true);
        if(PhongBUS.dsp==null){
            PhongBUS dsp= new PhongBUS();
            dsp.docdsp();
        }
        init();
        return pKMPhong;
    }
    public void init(){
        JLabel lbTimkiem = new JLabel();
        lbTimkiem.setBounds(20, 90, 100, 30);
        lbTimkiem.setText("Tìm Kiếm");
        lbTimkiem.setFont(new Font("Times New Roman",Font.BOLD,16));
        lbTimkiem.setForeground(Color.BLACK);
        lbTimkiem.setHorizontalAlignment(JLabel.LEFT);
        pKMPhong.add(lbTimkiem);
        
        String sp[] = {"Chọn kiểm tìm kiếm","Theo tên KMP","Tìm kiếm nâng cao"};
        
        cbTimkiem=new JComboBox(sp);
        cbTimkiem.setSelectedIndex(0);
        cbTimkiem.setFont(new Font("Arial",0,16));
        cbTimkiem.setBounds(120, 90, 270, 30);
        pKMPhong.add(cbTimkiem);
        
        String sp1[] = {"Chọn tình trạng","Đang thực hiện","Chưa đến hạn","Đã hết hạn"};
        cbTinhtrang=new JComboBox(sp1);
        cbTinhtrang.setSelectedIndex(0);
        cbTinhtrang.setFont(new Font("Arial",0,16));
        cbTinhtrang.setBounds(400, 90, 150, 30);
        pKMPhong.add(cbTinhtrang);
        
        JLabel lbNgayBD = new JLabel();
        lbNgayBD.setBounds(20, 210, 100, 30);
        lbNgayBD.setText("Ngày bắt đầu");
        lbNgayBD.setFont(new Font("Times New Roman",Font.BOLD,16));
        lbNgayBD.setForeground(Color.BLACK);
        lbNgayBD.setHorizontalAlignment(JLabel.LEFT);
        pKMPhong.add(lbNgayBD);
        
        daybd= new JDateChooser();
        daybd.setBounds(120, 210, 150, 30);
        daybd.setDateFormatString("dd-MM-yyyy");
        JTextFieldDateEditor editor;
        editor = (JTextFieldDateEditor) daybd.getDateEditor();
        editor.setEditable(false);
        pKMPhong.add(daybd);
        
        JLabel lbTenKMDV = new JLabel();
        lbTenKMDV.setBounds(20, 150, 100, 30);
        lbTenKMDV.setText("Tên KMP");
        lbTenKMDV.setFont(new Font("Times New Roman",Font.BOLD,16));
        lbTenKMDV.setForeground(Color.BLACK);
        lbTenKMDV.setHorizontalAlignment(JLabel.LEFT);
        pKMPhong.add(lbTenKMDV);
        
        txTenKMP = new JTextField();
        txTenKMP.setBounds(120, 150, 420, 30);
        pKMPhong.add(txTenKMP);
        
        JLabel lbNgayKT = new JLabel();
        lbNgayKT.setBounds(290, 210, 100, 30);
        lbNgayKT.setText("Ngày kết thúc");
        lbNgayKT.setFont(new Font("Times New Roman",Font.BOLD,16));
        lbNgayKT.setForeground(Color.BLACK);
        lbNgayKT.setHorizontalAlignment(JLabel.LEFT);
        pKMPhong.add(lbNgayKT);
        daykt= new JDateChooser();
        daykt.setBounds(390, 210, 150, 30);
        daykt.setDateFormatString("dd-MM-yyyy");
        JTextFieldDateEditor editor2;
        editor2 = (JTextFieldDateEditor) daykt.getDateEditor();
        editor2.setEditable(false);
        pKMPhong.add(daykt);
        
        lbTieude=new JLabel();
        lbTieude.setText("Khuyến mãi phòng");
        lbTieude.setFont(new Font("Times New Roman",Font.BOLD,26));
        lbTieude.setBounds(300, 10, 280, 30);
        lbTieude.setForeground(Color.BLACK);
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        pKMPhong.add(lbTieude);
        
        ImageIcon iconthem = new ImageIcon(icon.iconthem);
        btThem = new JButton("Thêm",iconthem);
        btThem.setBounds(680, 210, 96, 35);
        btThem.setBackground(colorButton);
        btThem.setForeground(Color.WHITE);
        btThem.setBorderPainted(false);
        btThem.setFocusPainted(false);
        pKMPhong.add(btThem);
        
        ImageIcon icontimkiem = new ImageIcon(icon.icontimkiem);
        btTimkiem = new JButton("Tìm kiếm",icontimkiem);
        btTimkiem.setBounds(560, 90, 120, 35);
        btTimkiem.setBackground(colorButton);
        btTimkiem.setForeground(Color.WHITE);
        btTimkiem.setBorderPainted(false);
        btTimkiem.setFocusPainted(false);
        pKMPhong.add(btTimkiem);
        
        ImageIcon iconsua = new ImageIcon(icon.iconsua);
        btSua = new JButton("Sửa",iconsua);
        btSua.setBounds(790, 210, 95, 35);
        btSua.setBackground(colorButton);
        btSua.setForeground(Color.WHITE);
        btSua.setBorderPainted(false);
        btSua.setFocusPainted(false);
        pKMPhong.add(btSua);
        
        ImageIcon iconxoa = new ImageIcon(icon.iconxoa);
        btXoa = new JButton("Xóa",iconxoa);
        btXoa.setBounds(900, 210, 95, 35);
        btXoa.setBackground(colorButton);
        btXoa.setForeground(Color.WHITE);
        btXoa.setBorderPainted(false);
        btXoa.setFocusPainted(false);
        pKMPhong.add(btXoa);
        
        ImageIcon iconxemchitiet = new ImageIcon(icon.iconxem);
        btXemchitiet = new JButton("Xem chi tiết",iconxemchitiet);
        btXemchitiet.setBounds(760, 605, 180, 35);
        btXemchitiet.setBackground(colorButton);
        btXemchitiet.setForeground(Color.WHITE);
        btXemchitiet.setBorderPainted(false);
        btXemchitiet.setFocusPainted(false);
        pKMPhong.add(btXemchitiet);
        
        ImageIcon iconcapnhat = new ImageIcon(icon.iconcapnhat);
        btCapnhat = new JButton("Cập nhật",iconcapnhat);
        btCapnhat.setBounds(50, 605, 140, 35);
        btCapnhat.setBackground(colorButton);
        btCapnhat.setForeground(Color.WHITE);
        btCapnhat.setBorderPainted(false);
        btCapnhat.setFocusPainted(false);
        pKMPhong.add(btCapnhat);
        
        JPanel pnlchitietkm = new JPanel();
        pnlchitietkm.setLayout(null);
        pnlchitietkm.setBounds(710, 250, 280, 350);
        pnlchitietkm.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        pKMPhong.add(pnlchitietkm);
        
        JLabel tieude= new JLabel();
        tieude.setText("THÔNG TIN KHUYẾN MÃI");
        tieude.setFont(new Font("Times New Roman",Font.BOLD,16));
        tieude.setBounds(0, 10, 280, 20);
        tieude.setForeground(Color.BLACK);
        tieude.setHorizontalAlignment(JLabel.CENTER);
        pnlchitietkm.add(tieude);
        
        JLabel lbMakmdv= new JLabel();
        lbMakmdv.setText("Mã KMP");
        lbMakmdv.setFont(new Font("Times New Roman",Font.BOLD,16));
        lbMakmdv.setBounds(10, 40, 270, 20);
        lbMakmdv.setForeground(Color.BLACK);
        lbMakmdv.setHorizontalAlignment(JLabel.LEFT);
        pnlchitietkm.add(lbMakmdv);
        
        txMakmp= new JLabel();
        txMakmp.setText("");
        txMakmp.setFont(new Font("Times New Roman",Font.BOLD,16));
        txMakmp.setBounds(20, 70, 240, 20);
        txMakmp.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        
        txMakmp.setHorizontalAlignment(JLabel.CENTER);
        pnlchitietkm.add(txMakmp);
        
        JLabel lbTenkmdv= new JLabel();
        lbTenkmdv.setText("Tên KMP");
        lbTenkmdv.setFont(new Font("Times New Roman",Font.BOLD,16));
        lbTenkmdv.setBounds(10, 100, 270, 20);
        lbTenkmdv.setForeground(Color.BLACK);
        lbTenkmdv.setHorizontalAlignment(JLabel.LEFT);
        pnlchitietkm.add(lbTenkmdv);
        
        txTenkmp= new JLabel();
        txTenkmp.setText("");
        txTenkmp.setFont(new Font("Times New Roman",Font.BOLD,16));
        txTenkmp.setBounds(20, 130, 240, 20);
        txTenkmp.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        txTenkmp.setHorizontalAlignment(JLabel.CENTER);
        pnlchitietkm.add(txTenkmp);
        
        JLabel lbNgaybd= new JLabel();
        lbNgaybd.setText("Ngày bắt đầu");
        lbNgaybd.setFont(new Font("Times New Roman",Font.BOLD,16));
        lbNgaybd.setBounds(10, 160, 270, 20);
        lbNgaybd.setForeground(Color.BLACK);
        lbNgaybd.setHorizontalAlignment(JLabel.LEFT);
        pnlchitietkm.add(lbNgaybd);
        
        txNgaybd= new JLabel();
        txNgaybd.setText("");
        txNgaybd.setFont(new Font("Times New Roman",Font.BOLD,16));
        txNgaybd.setBounds(20, 190, 240, 20);
        txNgaybd.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        txNgaybd.setHorizontalAlignment(JLabel.CENTER);
        pnlchitietkm.add(txNgaybd);
        
        JLabel lbNgaykt= new JLabel();
        lbNgaykt.setText("Ngày kết thúc");
        lbNgaykt.setFont(new Font("Times New Roman",Font.BOLD,16));
        lbNgaykt.setBounds(10, 220, 270, 20);
        lbNgaykt.setForeground(Color.BLACK);
        lbNgaykt.setHorizontalAlignment(JLabel.LEFT);
        pnlchitietkm.add(lbNgaykt);
        
        txNgaykt= new JLabel();
        txNgaykt.setText("");
        txNgaykt.setFont(new Font("Times New Roman",Font.BOLD,16));
        txNgaykt.setBounds(20, 250, 240, 20);
        txNgaykt.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        txNgaykt.setHorizontalAlignment(JLabel.CENTER);
        pnlchitietkm.add(txNgaykt);
        
        JLabel lbTìnhtrang= new JLabel();
        lbTìnhtrang.setText("Tình trạng");
        lbTìnhtrang.setFont(new Font("Times New Roman",Font.BOLD,16));
        lbTìnhtrang.setBounds(10, 280, 270, 20);
        lbTìnhtrang.setForeground(Color.BLACK);
        lbTìnhtrang.setHorizontalAlignment(JLabel.LEFT);
        pnlchitietkm.add(lbTìnhtrang);
        
        txTinhtrang= new JLabel();
        txTinhtrang.setText("");
        txTinhtrang.setFont(new Font("Times New Roman",Font.BOLD,16));
        txTinhtrang.setBounds(20, 310, 240, 20);
        txTinhtrang.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        txTinhtrang.setHorizontalAlignment(JLabel.CENTER);
        pnlchitietkm.add(txTinhtrang);
        
        txTenKMP.setEditable(false);
        daybd.setEnabled(false);
        daykt.setEnabled(false);
        cbTinhtrang.setEnabled(false); 
        
        KhuyenMaiPhongBUS kmpbus=new KhuyenMaiPhongBUS();
        if (KhuyenMaiPhongBUS.dskmp==null){
            kmpbus.docdskmp();
        }
        
        Khoitaoscroll();
        
        CacNutChucNang();
    }
    public JTable Danhsachkhuyenmaiphong(ArrayList<KhuyenMaiPhongDTO> arr){
//        tbdskmdv=new JTable();
//        tbdskmdv.getTableHeader().setBackground(new Color(3,169,244));
//        tbdskmdv.getTableHeader().setForeground(Color.WHITE);
//        tbdskmdv.getTableHeader().setFont(new Font("Times New Roman",0,18));
//        tbdskmdv.getTableHeader().setPreferredSize(new Dimension(0,30));
//        tbdskmdv.setFont(new Font("Times New Roman",0,18));
//        tbdskmdv.setForeground(Color.BLACK);

        tbdskmp=new JTable();
        tbdskmp.setRowHeight(25);
        tbdskmp.getTableHeader().setBackground(colorButton);
        tbdskmp.getTableHeader().setForeground(Color.WHITE);
        tbdskmp.getTableHeader().setFont(new Font("Times New Roman",0,18));
        tbdskmp.getTableHeader().setPreferredSize(new Dimension(0,30));
        tbdskmp.setFont(new Font("Times New Roman",0,18));
        tbdskmp.setForeground(Color.BLACK);
        
        model=new DefaultTableModel();
        Vector cottieude=new Vector();
        cottieude.add("Stt");
        cottieude.add("Mã KM phòng");
        cottieude.add("Tên KM phòng");
        cottieude.add("Ngày bắt đầu");
        cottieude.add("Ngày kết thúc");
        cottieude.add("Tình trạng");
        Xulydulieu xl = new Xulydulieu();
        if (model.getRowCount()==0){
            model=new DefaultTableModel(cottieude, 0);
        }
        int i=0;
        for (KhuyenMaiPhongDTO k : arr){
            i++;
            Vector dong=new Vector();                
            dong.add(i);
            dong.add(k.getMaKMPhong());
            dong.add(k.getTen());
            dong.add(xl.chuyendate(k.getNgayBD()));
            dong.add(xl.chuyendate(k.getNgayKT()));
            if(k.getTinhTrang()==1)
                dong.add("Đang thực hiện");
            else if(k.getTinhTrang()==2){
                dong.add("Đã hết hạn");
            }else{
                dong.add("Chưa đến hạn");
            }
            model.addRow(dong);
        }
        tbdskmp.setModel(model);
        tbdskmp.getColumnModel().getColumn(0).setMaxWidth(50);
//        tbdskmdv.getColumnModel().getColumn(2).setMaxWidth(250);
//        tbdskmdv.getColumnModel().getColumn(4).setMinWidth(150);
//        tbdskmdv.getColumnModel().getColumn(5).setMinWidth(150);
        
        //--------------------Căn giữa trong bảng-----------------------//
        DefaultTableCellRenderer center=new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        tbdskmp.setDefaultRenderer(tbdskmp.getColumnClass(0), center);
        tbdskmp.updateUI();
         tbdskmp.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                int i=tbdskmp.getSelectedRow();
                String s=tbdskmp.getModel().getValueAt(i, 1).toString();
                String s1=tbdskmp.getModel().getValueAt(i, 2).toString();
                String s2=tbdskmp.getModel().getValueAt(i, 3).toString();
                String s3=tbdskmp.getModel().getValueAt(i, 4).toString();
                String s4=tbdskmp.getModel().getValueAt(i, 5).toString();
                txMakmp.setText(s);
                txTenkmp.setText(s1);
                txNgaybd.setText(s2);
                txNgaykt.setText(s3);
                if(s4.equals("Đang thực hiện"))
                {
                    txTinhtrang.setForeground(Color.GREEN);
                }else
                {
                    txTinhtrang.setForeground(Color.RED);
                }
                txTinhtrang.setText(s4);
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
        return tbdskmp;
        //--------------------------------------------------------------//
    }
    public void Khoitaoscroll(){
        tbdskmp = Danhsachkhuyenmaiphong(KhuyenMaiPhongBUS.dskmp);
        scrdskm=new JScrollPane(tbdskmp);
        scrdskm.setViewportView(tbdskmp);
        scrdskm.setBounds(0, 250, 700, 350); // Tùy mỗi người
        pKMPhong.add(scrdskm);
    }
    public void JFrameChitiet(KhuyenMaiPhongDTO p){
        System.out.println(p.getTinhTrang());
        fThemct = new JFrame();
        fThemct.setBounds(450, 100, 800, 600);
        fThemct.setLayout(null);
        fThemct.setBackground(Color.WHITE);
        
        JPanel pnlchitietkm = new JPanel();
        pnlchitietkm.setLayout(null);
        pnlchitietkm.setBounds(520, 200, 260, 350);
        pnlchitietkm.setBackground(Color.WHITE);
        pnlchitietkm.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        
        fThemct.add(pnlchitietkm);
        
        JLabel tieude= new JLabel();
        tieude.setText("Chi tiết khuyến mãi");
        tieude.setFont(new Font("Times New Roman",Font.BOLD,23));
        tieude.setBounds(0, 10, 800, 30);
        tieude.setForeground(Color.BLACK);
        tieude.setHorizontalAlignment(JLabel.CENTER);
        fThemct.add(tieude);
        
        JLabel lbptkm= new JLabel();
        lbptkm.setBounds(20, 100, 80, 30);
        lbptkm.setText("%KM");
        lbptkm.setFont(new Font("Times New Roman",Font.BOLD,13));
        lbptkm.setForeground(Color.BLACK);
        lbptkm.setHorizontalAlignment(JLabel.CENTER);
        fThemct.add(lbptkm);
        
        txPtkm= new JTextField();
        txPtkm.setBounds(110, 100, 80, 30);
        txPtkm.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        txPtkm.setBackground(Color.WHITE);
        fThemct.add(txPtkm);
        
        JLabel lbMaphong= new JLabel();
        lbMaphong.setBounds(20, 50, 80, 30);
        lbMaphong.setText("Mã phòng");
        lbMaphong.setFont(new Font("Times New Roman",Font.BOLD,13));
        lbMaphong.setForeground(Color.BLACK);
        lbMaphong.setHorizontalAlignment(JLabel.CENTER);
        fThemct.add(lbMaphong);
        
        txMaphongct=new JTextField();
        txMaphongct.setBounds(110, 50, 160, 30);
        txMaphongct.setEditable(false);
        txMaphongct.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        txMaphongct.setBackground(Color.WHITE);
        fThemct.add(txMaphongct);
        
        ImageIcon iconchon = new ImageIcon(icon.iconchon);
        btXemp= new JButton(iconchon);
        btXemp.setBounds(280, 50, 30, 30);
        btXemp.setBackground(colorButton);
        btXemp.setForeground(Color.WHITE);
        btXemp.setBorderPainted(false);
        btXemp.setFocusPainted(false);
        fThemct.add(btXemp);
        
        ImageIcon iconthem = new ImageIcon(icon.iconthem);
        btThemct = new JButton("Thêm",iconthem);
        btThemct.setBackground(colorButton);
        btThemct.setForeground(Color.WHITE);
        btThemct.setBorderPainted(false);
        btThemct.setFocusPainted(false);
        if(p.getTinhTrang()==1 || p.getTinhTrang()==2){
            btThemct.setBounds(0,0,0,0);
        }else{
            btThemct.setBounds(20, 150, 96, 35);
        }
        fThemct.add(btThemct);
        
        ImageIcon iconsua = new ImageIcon(icon.iconsua);
        btSuact = new JButton("Sửa",iconsua);
        btSuact.setBackground(colorButton);
        btSuact.setForeground(Color.WHITE);
        btSuact.setBorderPainted(false);
        btSuact.setFocusPainted(false);
        if(p.getTinhTrang()==1 || p.getTinhTrang()==2){
            btSuact.setBounds(0,0,0,0);
        }else{
            btSuact.setBounds(126, 150, 95, 35);
        }
        fThemct.add(btSuact);
        
        ImageIcon iconxoa = new ImageIcon(icon.iconxoa);
        btXoact = new JButton("Xóa",iconxoa);
        btXoact.setBackground(colorButton);
        btXoact.setForeground(Color.WHITE);
        btXoact.setBorderPainted(false);
        btXoact.setFocusPainted(false);
        if(p.getTinhTrang()==1 || p.getTinhTrang()==2){
            btXoact.setBounds(0,0,0,0);
        }else{
            btXoact.setBounds(231, 150, 95, 35);
        }
        fThemct.add(btXoact);
        
        model1= new DefaultTableModel();
        tbdsctkmp=new JTable();
        
        tbdsctkmp.getTableHeader().setBackground(colorButton);
        tbdsctkmp.getTableHeader().setForeground(Color.WHITE);
        tbdsctkmp.getTableHeader().setFont(new Font("Times New Roman",0,18));
        tbdsctkmp.getTableHeader().setPreferredSize(new Dimension(0,30));
        tbdsctkmp.setFont(new Font("Times New Roman",0,18));
        tbdsctkmp.setForeground(Color.BLACK);
        
        JScrollPane scr=new JScrollPane();
        scr.setViewportView(tbdsctkmp);
        scr.setBounds(10, 200, 500, 350); // Tùy mỗi người
        
        fThemct.add(scr);
        
        Vector cottieude=new Vector();
        cottieude.add("Stt");
        cottieude.add("Mã Phòng");
        cottieude.add("Loại");
        cottieude.add("Phần trăm KM");
//        cottieude.add("Tình trạng");
        if (model1.getRowCount()==0){
            model1=new DefaultTableModel(cottieude, 0);
        }
        stt=0;
        ChiTietKMPhongBUS dsp=new ChiTietKMPhongBUS();
        if(ChiTietKMPhongBUS.dsctkmp==null){
            dsp.docdsctkmp();
        }
        for (ChiTietKMPhongDTO k : ChiTietKMPhongBUS.dsctkmp){
            if(k.getMaKMPhong().equals(p.getMaKMPhong())){
                stt++;
                Vector dong=new Vector();              
                dong.add(stt);
                dong.add(k.getMaPhong());
                dong.add(dsp.lay1phong(k.getMaPhong()));
                dong.add(k.getPhanTramKM());
                model1.addRow(dong);
            }
        }
        tbdsctkmp.setModel(model1);
        tbdsctkmp.getColumnModel().getColumn(0).setMaxWidth(50);
        tbdsctkmp.setRowHeight(25);
//        tbdskmdv.getColumnModel().getColumn(2).setMaxWidth(250);
//        tbdskmdv.getColumnModel().getColumn(4).setMinWidth(150);
//        tbdskmdv.getColumnModel().getColumn(5).setMinWidth(150);
        
        //--------------------Căn giữa trong bảng-----------------------//
        DefaultTableCellRenderer center=new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        tbdsctkmp.setDefaultRenderer(tbdsctkmp    .getColumnClass(0), center);
        tbdsctkmp.updateUI();
        //--------------------------------------------------------------//
        
        JLabel tieudekm= new JLabel();
        tieudekm.setText("THÔNG TIN KHUYẾN MÃI");
        tieudekm.setFont(new Font("Times New Roman",Font.BOLD,16));
        tieudekm.setBounds(0, 10, 280, 20);
        tieudekm.setForeground(Color.BLACK);
        tieudekm.setHorizontalAlignment(JLabel.CENTER);
        pnlchitietkm.add(tieudekm);
        
        JLabel lbMakmp= new JLabel();
        lbMakmp.setText("Mã KMP");
        lbMakmp.setFont(new Font("Times New Roman",Font.BOLD,16));
        lbMakmp.setBounds(10, 40, 270, 20);
        lbMakmp.setForeground(Color.BLACK);
        lbMakmp.setHorizontalAlignment(JLabel.LEFT);
        pnlchitietkm.add(lbMakmp);
        
        txMakmpct= new JLabel();
        txMakmpct.setText(p.getMaKMPhong());
        txMakmpct.setFont(new Font("Times New Roman",Font.BOLD,16));
        txMakmpct.setBounds(20, 70, 220, 20);
        txMakmpct.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        txMakmpct.setHorizontalAlignment(JLabel.CENTER);
        pnlchitietkm.add(txMakmpct);
        
        JLabel lbTenkmp= new JLabel();
        lbTenkmp.setText("Tên KMP");
        lbTenkmp.setFont(new Font("Times New Roman",Font.BOLD,16));
        lbTenkmp.setBounds(10, 100, 270, 20);
        lbTenkmp.setForeground(Color.BLACK);
        lbTenkmp.setHorizontalAlignment(JLabel.LEFT);
        pnlchitietkm.add(lbTenkmp);
        
        txTenkmpct= new JLabel();
        txTenkmpct.setText(p.getTen());
        txTenkmpct.setFont(new Font("Times New Roman",Font.BOLD,16));
        txTenkmpct.setBounds(20, 130, 220, 20);
        txTenkmpct.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        txTenkmpct.setHorizontalAlignment(JLabel.CENTER);
        pnlchitietkm.add(txTenkmpct);
        
        JLabel lbNgaybd= new JLabel();
        lbNgaybd.setText("Ngày bắt đầu");
        lbNgaybd.setFont(new Font("Times New Roman",Font.BOLD,16));
        lbNgaybd.setBounds(10, 160, 220, 20);
        lbNgaybd.setForeground(Color.BLACK);
        lbNgaybd.setHorizontalAlignment(JLabel.LEFT);
        pnlchitietkm.add(lbNgaybd);
        
        txNgaybdct= new JLabel();
        txNgaybdct.setText(p.getNgayBD());
        txNgaybdct.setFont(new Font("Times New Roman",Font.BOLD,16));
        txNgaybdct.setBounds(20, 190, 220, 20);
        txNgaybdct.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        txNgaybdct.setHorizontalAlignment(JLabel.CENTER);
        pnlchitietkm.add(txNgaybdct);
        
        JLabel lbNgaykt= new JLabel();
        lbNgaykt.setText("Ngày kết thúc");
        lbNgaykt.setFont(new Font("Times New Roman",Font.BOLD,16));
        lbNgaykt.setBounds(10, 220, 270, 20);
        lbNgaykt.setForeground(Color.BLACK);
        lbNgaykt.setHorizontalAlignment(JLabel.LEFT);
        pnlchitietkm.add(lbNgaykt);
        
        txNgayktct= new JLabel();
        txNgayktct.setText(p.getNgayKT());
        txNgayktct.setFont(new Font("Times New Roman",Font.BOLD,16));
        txNgayktct.setBounds(20, 250, 220, 20);
        txNgayktct.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        txNgayktct.setHorizontalAlignment(JLabel.CENTER);
        pnlchitietkm.add(txNgayktct);
        
        JLabel lbTìnhtrang= new JLabel();
        lbTìnhtrang.setText("Tình trạng");
        lbTìnhtrang.setFont(new Font("Times New Roman",Font.BOLD,16));
        lbTìnhtrang.setBounds(10, 280, 270, 20);
        lbTìnhtrang.setForeground(Color.BLACK);
        lbTìnhtrang.setHorizontalAlignment(JLabel.LEFT);
        pnlchitietkm.add(lbTìnhtrang);
        
        txTinhtrangct= new JLabel();
        if(p.getTinhTrang()==1){
            txTinhtrangct.setText("Đang thực hiện");
            txTinhtrangct.setForeground(Color.GREEN);
        }else if(p.getTinhTrang()==3){
            txTinhtrangct.setText("Đã hết hạn");
            txTinhtrangct.setForeground(Color.RED);
        }else{
            txTinhtrangct.setText("Chưa đến hạn");
            txTinhtrangct.setForeground(Color.RED);
        }
        txTinhtrangct.setFont(new Font("Times New Roman",Font.BOLD,16));
        txTinhtrangct.setBounds(20, 310, 220, 20);
        txTinhtrangct.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        txTinhtrangct.setHorizontalAlignment(JLabel.CENTER);
        pnlchitietkm.add(txTinhtrangct);
        CacNutChucNangChiTiet();
    }
    public void BangChonPhong(){
        JFrame fchon=new JFrame();
        fchon.setLayout(null);
        fchon.setBounds(0, 0, 550, 400);
        fchon.setPreferredSize(new Dimension(550,400));
        
        //---------------------------------Hiện giữa màn hình-----------------------------//
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        fchon.setLocation((int)d.getWidth()/2 - (int)fchon.getPreferredSize().getWidth()/2,
                      (int)d.getHeight()/2 - (int)fchon.getPreferredSize().getHeight()/2);
        //--------------------------------------------------------------------------------//
        
        JLabel lbTieude=new JLabel("Chọn phòng");
        lbTieude.setFont(new Font("Times New Roman",Font.BOLD,25));
        lbTieude.setForeground(Color.BLACK);
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        lbTieude.setBounds(150, 20, 250, 25);
        
        String sp[] = {"Chọn tìm theo","Theo mã phòng","Theo loại phòng"};
        
        JComboBox cbTimkiem=new JComboBox(sp);
        cbTimkiem.setSelectedIndex(0);
        cbTimkiem.setFont(new Font("Arial",0,16));
        cbTimkiem.setBounds(100, 50, 160, 30);
        fchon.add(cbTimkiem);
        
        JLabel lbMaphong= new JLabel();
        lbMaphong.setBounds(10, 100, 80, 30);
        lbMaphong.setText("Mã phòng");
        lbMaphong.setFont(new Font("Times New Roman",Font.BOLD,13));
        lbMaphong.setForeground(Color.BLACK);
        lbMaphong.setHorizontalAlignment(JLabel.CENTER);
        fchon.add(lbMaphong);
        
        txMaphong1 = new JTextField();
        txMaphong1=new JTextField();
        txMaphong1.setBounds(100, 100, 160, 30);
        fchon.add(txMaphong1);
        
        LoaiPhongBUS lp = new LoaiPhongBUS();
        if(LoaiPhongBUS.dslp==null){
            lp.docdslp();
        }
        int size=LoaiPhongBUS.dslp.size()+1;
        String[] s = new String[size];
        s[0]="Loại phòng";
        int i=1;
        for(LoaiPhongDTO p: LoaiPhongBUS.dslp){
            s[i]=p.getMaLoai();
            i++;
        }
        cbLoaiphong=new JComboBox(s);
        cbLoaiphong.setSelectedIndex(0);
        cbLoaiphong.setFont(new Font("Arial",0,16));
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
        
        tbp=danhsachphong(PhongBUS.dsp);;
        JScrollPane sc=new JScrollPane();
        sc.setViewportView(tbp);
        sc.setBounds(10, 140, 520, 200);
        
        fchon.add(sc);
        txMaphong1.setEditable(false);
        cbLoaiphong.setEnabled(false);
        btTimkiem.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                PhongBUS p = new PhongBUS();
                if(cbTimkiem.getSelectedItem().equals("Theo mã phòng")){
                    sc.removeAll();
                    ArrayList<PhongDTO> dstk = new  ArrayList<>();
                    tbp = new JTable();
                    tbp=danhsachphong(p.timkiemtheoma(txMaphong1.getText()));
                    JScrollPane scrdsp1= new JScrollPane(tbp);
                    scrdsp1.setBounds(0, 0, 520, 200);
                    sc.add(scrdsp1);
                    sc.repaint();
                    sc.revalidate();
                    fchon.add(sc);
                }else if(cbTimkiem.getSelectedItem().equals("Theo loại phòng")){
                    sc.removeAll();
                    ArrayList<PhongDTO> dstk = new  ArrayList<>();
                    tbp = new JTable();
                    String s=(String) cbLoaiphong.getSelectedItem();
                    if(s.equals("Loại")){
                        s="";
                    }
                    tbp = danhsachphong(p.timkiemtheoloai(s));
                    JScrollPane scrdsp1= new JScrollPane(tbp);
                    scrdsp1.setBounds(0, 0, 520, 200);
                    sc.add(scrdsp1);
                    sc.repaint();
                    sc.revalidate();
                    fchon.add(sc);
                }else{
                    sc.removeAll();
                    ArrayList<PhongDTO> dstk = new  ArrayList<>();
                    tbp = new JTable();
                    tbp = danhsachphong(PhongBUS.dsp);
                    JScrollPane scrdsp1= new JScrollPane(tbp);
                    scrdsp1.setBounds(0, 0, 520, 200);
                    sc.add(scrdsp1);
                    sc.repaint();
                    sc.revalidate();
                    fchon.add(sc);
                }
            tbp.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent arg0) {
                    int i=tbp.getSelectedRow();
                    String maq=tbp.getModel().getValueAt(i, 1).toString();
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
                btTimkiem.setBackground(new Color(36,36,36));
            }
        });
        tbp.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int i=tbp.getSelectedRow();
                String maq=tbp.getModel().getValueAt(i, 1).toString();
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
                if (cbTimkiem.getSelectedItem().equals("Theo mã phòng")){
                    txMaphong1.setEditable(true);
                    cbLoaiphong.setEnabled(false);
                }
                else if(cbTimkiem.getSelectedItem().equals("Theo loại phòng")){
                    txMaphong1.setEditable(false);
                    cbLoaiphong.setEnabled(true);
                }else{
                    txMaphong1.setEditable(false);
                    cbLoaiphong.setEnabled(false);
                }
            }
        });
        fchon.setVisible(true);
    }
    public JTable danhsachphong(ArrayList<PhongDTO> arr){
        JTable tbp=new JTable();
        tbp.getTableHeader().setBackground(new Color(36,36,36));
        tbp.getTableHeader().setForeground(Color.WHITE);
        tbp.getTableHeader().setFont(new Font("Times New Roman",0,18));
        tbp.getTableHeader().setPreferredSize(new Dimension(0,30));
        tbp.setFont(new Font("Times New Roman",0,18));
        tbp.setForeground(Color.BLACK);
        tbp.setRowHeight(25);
        
        DefaultTableModel md=new DefaultTableModel();
        Vector cot=new Vector();
        cot.add("STT");
        cot.add("Mã phòng");
        cot.add("Loại");
        cot.add("SL người");
        cot.add("Đơn giá");
        if (md.getRowCount()==0){
            md=new DefaultTableModel(cot, 0);
        }
        int i=1;
        Xulydulieu xl = new Xulydulieu();
        for (PhongDTO k1 : arr){
        Vector dong=new Vector();
        dong.add(i);
        dong.add(k1.getMaPhong());
        dong.add(k1.getMaLoai()); 
        dong.add(k1.getSLNguoi());
        dong.add(xl.ChuyenKieuTien(String.valueOf(k1.getDonGia()))+" VND"); 
        md.addRow(dong);
        i++;
        }
        tbp.setModel(md);
        tbp.getColumnModel().getColumn(0).setMaxWidth(50);
        //tbp.getColumnModel().getColumn(0).setMinWidth(50);
//        tbp.getColumnModel().getColumn(1).setMinWidth(120);
//        tbp.getColumnModel().getColumn(2).setMinWidth(250);                
        //--------------------Căn giữa trong bảng-----------------------//
        DefaultTableCellRenderer center=new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        tbp.setDefaultRenderer(tbp.getColumnClass(0), center);
        tbp.updateUI();
        return tbp;
    }
    public void JFrameChucnang(int x){
        fThem = new JFrame();
        fThem.setBounds(550, 100, 500, 360);
        fThem.setLayout(null);
        fThem.setBackground(Color.WHITE);
       
        JLabel tieude= new JLabel();
        if(x==0){
            tieude.setText("Thêm khuyến mãi phòng");
        }else{
            tieude.setText("Sửa khuyến mãi phòng");
        }
        tieude.setFont(new Font("Times New Roman",Font.BOLD,23));
        tieude.setBounds(0, 10, 500, 30);
        tieude.setForeground(Color.BLACK);
        tieude.setHorizontalAlignment(JLabel.CENTER);
        tieude.setBackground(colorButton);
        fThem.add(tieude);
        
        JLabel lbTenKMP = new JLabel();
        lbTenKMP.setBounds(20, 110, 100, 30);
        lbTenKMP.setText("Tên KMP");
        lbTenKMP.setFont(new Font("Times New Roman",Font.BOLD,16));
        lbTenKMP.setForeground(Color.BLACK);
        lbTenKMP.setHorizontalAlignment(JLabel.LEFT);
        fThem.add(lbTenKMP);
        
        txTenKMPcn = new JTextField();
        txTenKMPcn.setBounds(130, 110, 300, 30);
        txTenKMPcn.setFont(new Font("Times New Roman",Font.BOLD,16));
        fThem.add(txTenKMPcn);
        
        JLabel lbNgayBD = new JLabel();
        lbNgayBD.setBounds(20, 160, 100, 30);
        lbNgayBD.setText("Ngày bắt đầu");
        lbNgayBD.setFont(new Font("Times New Roman",Font.BOLD,16));
        lbNgayBD.setForeground(Color.BLACK);
        lbNgayBD.setHorizontalAlignment(JLabel.LEFT);
        fThem.add(lbNgayBD);
        
        daybdcn= new JDateChooser();
        daybdcn.setBounds(130, 160, 300, 30);
        daybdcn.setDateFormatString("dd-MM-yyyy");
        daybdcn.setFont(new Font("Times New Roman",Font.BOLD,16));
        daybdcn.setBackground(Color.WHITE);
        JTextFieldDateEditor editor;
        editor = (JTextFieldDateEditor) daybdcn.getDateEditor();
        editor.setEditable(false);
        
        fThem.add(daybdcn);
        
        JLabel lbNgayKT = new JLabel();
        lbNgayKT.setBounds(20, 210, 100, 30);
        lbNgayKT.setText("Ngày kết thúc");
        lbNgayKT.setFont(new Font("Times New Roman",Font.BOLD,16));
        lbNgayKT.setForeground(Color.BLACK);
        lbNgayKT.setHorizontalAlignment(JLabel.LEFT);
        fThem.add(lbNgayKT);
        
        dayktcn= new JDateChooser();
        dayktcn.setBounds(130, 210, 300, 30);
        dayktcn.setDateFormatString("dd-MM-yyyy");
        dayktcn.setFont(new Font("Times New Roman",Font.BOLD,16));
        dayktcn.setBackground(Color.WHITE);
        JTextFieldDateEditor editor2;
        editor2 = (JTextFieldDateEditor) dayktcn.getDateEditor();
        editor2.setEditable(false);
        
        fThem.add(dayktcn);
       
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
    public void CacNutChucNangChiTiet(){
        tbdsctkmp.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                int i=tbdsctkmp.getSelectedRow();
                String s=tbdsctkmp.getModel().getValueAt(i, 1).toString();
                String s1=tbdsctkmp.getModel().getValueAt(i, 2).toString();
                String s2=tbdsctkmp.getModel().getValueAt(i, 3).toString();
                txMaphongct.setText(s);
                txPtkm.setText(s2);
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
                btXemp.setBackground(new Color(36,36,36));
            }
        });
        btThemct.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                ChiTietKMPhongDTO p = new ChiTietKMPhongDTO();
                ChiTietKMPhongBUS ctkmp= new ChiTietKMPhongBUS();
                if(txPtkm.getText().equals("") || txMaphongct.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Điền đầy đủ thông tin");
                }else{
                    if(ctkmp.ktraphantram(txPtkm.getText())==1){
                        p.setMaPhong(txMaphongct.getText());
                        p.setMaKMPhong(txMakmpct.getText());
                        p.setPhanTramKM(Integer.parseInt(txPtkm.getText()));
                        if(ctkmp.ktratrung(p)==1){
                            Vector row=new Vector();
                            row.add(stt+1);
                            row.add(p.getMaPhong());
                            row.add(ctkmp.lay1phong(p.getMaPhong()));
                            row.add(txPtkm.getText());
                            model1.addRow(row);
                            tbdsctkmp.setModel(model1);
                            ctkmp.them(p);
                        }else{
                            JOptionPane.showMessageDialog(null, "Phòng đã tồn tại");
                        }
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
                btThemct.setBackground(Color.DARK_GRAY);
                btThemct.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                btThemct.setBackground(new Color(36,36,36));
            }
        });
        btSuact.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                ChiTietKMPhongDTO p = new ChiTietKMPhongDTO();
                ChiTietKMPhongDTO p1 = new ChiTietKMPhongDTO();
                if(txPtkm.getText().equals("") || txMaphongct.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Nhập đầy đủ thông tin");
                }else{ 
                    int i=tbdsctkmp.getSelectedRow();
                    if(i != -1){
                        String s=tbdsctkmp.getModel().getValueAt(i, 1).toString();
                        String s1=tbdsctkmp.getModel().getValueAt(i, 2).toString();
                        String s2=tbdsctkmp.getModel().getValueAt(i, 3).toString();
                        p1.setMaKMPhong(txMakmpct.getText());
                        p1.setMaPhong(s);
                        p1.setPhanTramKM(Integer.parseInt(s2));
                        ChiTietKMPhongBUS ctkmp= new ChiTietKMPhongBUS();
                        p.setMaPhong(txMaphongct.getText());
                        p.setMaKMPhong(txMakmpct.getText());
                        p.setPhanTramKM(Integer.parseInt(txPtkm.getText()));
                        if(ctkmp.sua(p1, p)==1){
                            tbdsctkmp.getModel().setValueAt(p.getMaPhong(), i, 1);
                            tbdsctkmp.getModel().setValueAt(ctkmp.lay1phong(p.getMaPhong()), i, 2);
                            tbdsctkmp.getModel().setValueAt(txPtkm.getText(), i, 3);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Chọn đối tượng cần sửa");
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
                btSuact.setBackground(Color.DARK_GRAY);
                btSuact.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                btSuact.setBackground(new Color(36,36,36));
            }
        });
        btXoact.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                ChiTietKMPhongDTO p1 = new ChiTietKMPhongDTO();
                int i=tbdsctkmp.getSelectedRow();
                if(i != -1){
                    int input=JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa", "Xóa tài khoản", JOptionPane.YES_NO_OPTION);
                    if(input==0){
                        String s=tbdsctkmp.getModel().getValueAt(i, 1).toString();
                        String s1=tbdsctkmp.getModel().getValueAt(i, 2).toString();
                        String s2=tbdsctkmp.getModel().getValueAt(i, 3).toString();
                        p1.setMaKMPhong(txMakmpct.getText());
                        p1.setMaPhong(s);
                        p1.setPhanTramKM(Integer.parseInt(s2));
                        ChiTietKMPhongBUS ctkmdv= new ChiTietKMPhongBUS();
                        ctkmdv.xoa1chitiet(p1);
                        model1.removeRow(i);
                        tbdsctkmp.setModel(model1);
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
    }
    public void CacNutChucNang(){
        String makmdv;
        btXemchitiet.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                KhuyenMaiPhongDTO p = new KhuyenMaiPhongDTO();
                int i=tbdskmp.getSelectedRow();
                if(i!=-1)
                {
                    String s=tbdskmp.getModel().getValueAt(i, 1).toString();
                    String s1=tbdskmp.getModel().getValueAt(i, 2).toString();
                    String s2=tbdskmp.getModel().getValueAt(i, 3).toString();
                    String s3=tbdskmp.getModel().getValueAt(i, 4).toString();
                    String s4=tbdskmp.getModel().getValueAt(i, 5).toString();
                    p.setMaKMPhong(s);
                    p.setTen(s1);
                    p.setNgayBD(s2);
                    p.setNgayKT(s3);
                    if(s4.equals("Đã hết hạn")){
                        p.setTinhTrang(2);
                    }else if(s4.equals("Đang thực hiện")){
                        p.setTinhTrang(1);
                    }else{
                        p.setTinhTrang(3);
                    }
                    JFrameChitiet(p);
                    fThemct.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "Chọn khuyến mãi phòng cần xem chi tiết");
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
                btXemchitiet.setBackground(new Color(36,36,36));
            }
        });
        btThem.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                JFrameChucnang(0);
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
                KhuyenMaiDichVuDTO p = new  KhuyenMaiDichVuDTO();
                int i=tbdskmp.getSelectedRow();
                if(i!=-1){
                    JFrameChucnang(1);
                    String s=tbdskmp.getModel().getValueAt(i, 1).toString();
                    String s1=tbdskmp.getModel().getValueAt(i, 2).toString();
                    String s2=tbdskmp.getModel().getValueAt(i, 3).toString();
                    String s3=tbdskmp.getModel().getValueAt(i, 4).toString();
                    String s4=tbdskmp.getModel().getValueAt(i, 5).toString();
                    txTenKMPcn.setText(s1);
                    try {
                        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(s2);
                        Date date2 = new SimpleDateFormat("dd-MM-yyyy").parse(s3);
                        daybdcn.setDate(date);
                        dayktcn.setDate(date2);
                    fThem.setVisible(true);
                    } catch (Exception ex) {
                        ex.getStackTrace();
                    }
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
                KhuyenMaiPhongDTO p = new  KhuyenMaiPhongDTO();
                KhuyenMaiPhongBUS kmdv = new KhuyenMaiPhongBUS();
                int i=tbdskmp.getSelectedRow();
                if(i!=-1){
                    if(tbdskmp.getModel().getValueAt(i, 5).toString().equals("Đang thực hiện")){
                        String s=tbdskmp.getModel().getValueAt(i, 1).toString();
                        String s1=tbdskmp.getModel().getValueAt(i, 2).toString();
                        String s2=tbdskmp.getModel().getValueAt(i, 3).toString();
                        String s3=tbdskmp.getModel().getValueAt(i, 4).toString();
                        String s4=tbdskmp.getModel().getValueAt(i, 5).toString();
                        p.setMaKMPhong(s);
                        p.setTen(s1);
                        p.setNgayBD(s2);
                        p.setNgayKT(s3);
                        if(s4.equals("Đang thực hiện"))
                        {
                            p.setTinhTrang(1);
                        }else if(s4.equals("Đã hết hạn"))
                        {
                            p.setTinhTrang(2);
                        }else{
                            p.setTinhTrang(3);
                        }
                        kmdv.xoa(p);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Khuyến mãi đang thực hiện khổng thể xóa");
                    }
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
                JTable tbl = Danhsachkhuyenmaiphong(KhuyenMaiPhongBUS.dskmp);
                scrdskm1= new JScrollPane(tbl);
                scrdskm1.setBounds(0, 0, 700, 350);
                scrdskm.add(scrdskm1);
                scrdskm.repaint();
                scrdskm.revalidate();
                pKMPhong.add(scrdskm);
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
                KhuyenMaiPhongBUS kmp= new KhuyenMaiPhongBUS();
                JTable tbl = new JTable();
                if(cbTimkiem.getSelectedItem().equals("Theo tên KMP")){
                    tbl = Danhsachkhuyenmaiphong(kmp.timkiemtheoten(txTenKMP.getText()));
                    scrdskm.removeAll();
                    scrdskm1= new JScrollPane(tbl);
                    scrdskm1.setBounds(0, 0, 700, 350);
                    scrdskm.add(scrdskm1);
                    scrdskm.repaint();
                    scrdskm.revalidate();
                    pKMPhong.add(scrdskm);
                }else if(cbTimkiem.getSelectedItem().equals("Tìm kiếm nâng cao")){
                    String s2,s3;
                    int i=0;
                    if(cbTinhtrang.getSelectedItem().equals("Đang thực hiện")){
                        i=1;
                    }else if(cbTinhtrang.getSelectedItem().equals("Đã hết hạn")){
                        i=2;
                    }
                    else if(cbTinhtrang.getSelectedItem().equals("Chưa đến hạn")){
                        i=3;
                    }
                    System.out.println(i);
                    if(!cbTinhtrang.getSelectedItem().equals("Chọn tình trạng") && (daybd.getDate()==null && daykt.getDate()==null)){
                        System.out.println("1");
                        tbl = Danhsachkhuyenmaiphong(kmp.timkiemnangcao(i,"",""));
                        scrdskm.removeAll();
                        scrdskm1= new JScrollPane(tbl);
                        scrdskm1.setBounds(0, 0, 700, 350);
                        scrdskm.add(scrdskm1);
                        scrdskm.repaint();
                        scrdskm.revalidate();
                        pKMPhong.add(scrdskm); 
                    }else if(!cbTinhtrang.getSelectedItem().equals("Chọn tình trạng") && daybd.getDate()!=null && daykt.getDate()!=null){
                        System.out.println("2");
                        SimpleDateFormat date= new SimpleDateFormat("yyyy-MM-dd");
                        SimpleDateFormat date2= new SimpleDateFormat("yyyy-MM-dd");
                        tbl = Danhsachkhuyenmaiphong(kmp.timkiemnangcao(i,date2.format(daybd.getDate()),date2.format(daykt.getDate())));
                        scrdskm.removeAll();
                        scrdskm1= new JScrollPane(tbl);
                        scrdskm1.setBounds(0, 0, 700, 350);
                        scrdskm.add(scrdskm1);
                        scrdskm.repaint();
                        scrdskm.revalidate();
                        pKMPhong.add(scrdskm);
                    }else if(i==0 && daybd.getDate()!=null && daykt.getDate()!=null){
                        System.out.println("3");
                        SimpleDateFormat date= new SimpleDateFormat("yyyy-MM-dd");
                        SimpleDateFormat date2= new SimpleDateFormat("yyyy-MM-dd");
                        tbl = Danhsachkhuyenmaiphong(kmp.timkiemnangcao(0,date2.format(daybd.getDate()),date2.format(daykt.getDate())));
                        scrdskm.removeAll();
                        scrdskm1= new JScrollPane(tbl);
                        scrdskm1.setBounds(0, 0, 700, 350);
                        scrdskm.add(scrdskm1);
                        scrdskm.repaint();
                        scrdskm.revalidate();
                        pKMPhong.add(scrdskm);
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
                if (cbTimkiem.getSelectedItem().equals("Theo tên KMP")){
                    txTenKMP.setEditable(true);
                    daybd.setEnabled(false);
                    daykt.setEnabled(false);
                    cbTinhtrang.setEnabled(false);
                }
                else if(cbTimkiem.getSelectedItem().equals("Tìm kiếm nâng cao")){
                    txTenKMP.setEditable(false);
                    daybd.setEnabled(true);
                    daykt.setEnabled(true);
                    cbTinhtrang.setEnabled(true);
                }else{
                    txTenKMP.setEditable(false);
                    daybd.setEnabled(false);
                    daykt.setEnabled(false);
                    cbTinhtrang.setEnabled(false);
                }
            }
        });
    }
    public void CacNutChucNangJFrameChucNang(){
        btThemcn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                KhuyenMaiPhongDTO p = new  KhuyenMaiPhongDTO();
                KhuyenMaiPhongBUS kmdv = new KhuyenMaiPhongBUS();
                Xulydulieu xldl= new Xulydulieu();
                p.setMaKMPhong(kmdv.MaCuoiDS());
                SimpleDateFormat date= new SimpleDateFormat("yyy-MM-dd");
                SimpleDateFormat date2= new SimpleDateFormat("dd-MM-yyyy");
                if(txTenKMPcn.getText().equals("") || daybdcn.getDate()==null || dayktcn.getDate()==null){
                    JOptionPane.showMessageDialog(null, "Nhập đầy đủ thông tin");
                }else{
                    if(xldl.TinhSoNgay(date2.format(daybdcn.getDate()),date2.format(dayktcn.getDate()))<0){
                        JOptionPane.showMessageDialog(null, "Ngày bắt đầu nhỏ hơn ngày kết thúc");
                    }
                    else if(xldl.TinhSoNgay(xldl.chuyendate(xldl.LayNgayThangNamHienTai()),date2.format(daybdcn.getDate()))<0){
                        JOptionPane.showMessageDialog(null, "Không thể tạo cho quá khứ");
                    }else{
                        p.setTen(txTenKMPcn.getText());
                        p.setNgayBD(date.format(daybdcn.getDate()));
                        p.setNgayKT(date.format(dayktcn.getDate()));
                        kmdv.them(p);
                        fThem.setVisible(false);
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
                KhuyenMaiPhongDTO p = new  KhuyenMaiPhongDTO();
                int i=tbdskmp.getSelectedRow();
                String s=tbdskmp.getModel().getValueAt(i, 1).toString();
                String s1=tbdskmp.getModel().getValueAt(i, 2).toString();
                KhuyenMaiPhongBUS kmdv = new KhuyenMaiPhongBUS();
                Xulydulieu xldl= new Xulydulieu();
                p.setMaKMPhong(s);
                SimpleDateFormat date= new SimpleDateFormat("yyy-MM-dd");
                SimpleDateFormat date2= new SimpleDateFormat("dd-MM-yyyy");
                if(txTenKMPcn.getText().equals("") || daybdcn.getDate()==null || dayktcn.getDate()==null){
                    JOptionPane.showMessageDialog(null, "Nhập đầy đủ thông tin");
                }else{
                    if(xldl.TinhSoNgay(date2.format(daybdcn.getDate()),date2.format(dayktcn.getDate()))<0){
                        JOptionPane.showMessageDialog(null, "Ngày bắt đầu nhỏ hơn ngày kết thúc");
                    }
                    else{
                        p.setTen(txTenKMPcn.getText());
                        p.setNgayBD(date.format(daybdcn.getDate()));
                        p.setNgayKT(date.format(dayktcn.getDate()));
                        kmdv.sua(p);
                        fThem.setVisible(false);
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
                btSuacn.setBackground(Color.DARK_GRAY);
                btSuacn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                btSuacn.setBackground(new Color(36,36,36));
            }
        });
    }

}
