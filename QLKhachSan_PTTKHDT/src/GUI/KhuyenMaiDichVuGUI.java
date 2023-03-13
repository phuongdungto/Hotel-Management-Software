/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.ChiTietKMDichVuBUS;
import BUS.DichVuBUS;
import BUS.KhuyenMaiDichVuBUS;
import BUS.Xulydulieu;
import BUS.iconBUS;
import DTO.ChiTietKMDichVuDTO;
import DTO.DichVuDTO;
import DTO.KhuyenMaiDichVuDTO;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import javax.swing.JButton;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author T P Dung
 */
public class KhuyenMaiDichVuGUI {
    protected JLabel lbTieude;
    protected JPanel pKMDichVu;
    protected JComboBox cbTimkiem;
    protected JTable tbdskmdv;
    protected JTextField txTenKMDV;
    protected DefaultTableModel model;
    protected JDateChooser daybd;
    protected JDateChooser daykt;
    protected JButton btThem;
    protected JButton btSua;
    protected JButton btXoa;
    protected JButton btTimkiem;
    protected JButton btCapnhat;
    protected JButton btXemchitiet;
    
    protected JTextField txTenKMDVcn;
    protected JDateChooser daybdcn;
    protected JDateChooser dayktcn;
    
    protected JButton btThemcn;
    protected JButton btSuacn;
    
    protected JFrame fThemct;
    protected JComboBox cbTinhtrang;
    protected JFrame fThem;
    protected JTextField txDv;
    protected JTextField txPtkm;
    protected JTable tbdsctkmdv;
    protected JTextField txDvct;
    protected DefaultTableModel model1;

    protected Color colorButton = new Color(36,36,36);
    protected JLabel txMakmdv;
    protected JLabel txTenkmdv;
    protected JLabel txNgaybd;
    protected JLabel txNgaykt;
    protected JLabel txTinhtrang;
    
    protected JLabel txMakmdvct;
    protected JLabel txTenkmdvct;
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
    int stt;
    iconBUS icon = new iconBUS();
    
    public JPanel KhoiTaoPanel(int width, int height){
        pKMDichVu=new JPanel();
        pKMDichVu.setLayout(null);
        pKMDichVu.setBounds(0, 0, width, height);
        pKMDichVu.setPreferredSize(new Dimension(width,height));
        pKMDichVu.setOpaque(true);
        if(DichVuBUS.dsdv==null){
            DichVuBUS dvbus= new DichVuBUS();
            dvbus.docdsdv();
        }
        init();
        return pKMDichVu;
    }
    public void init(){
        JLabel lbTimkiem = new JLabel();
        lbTimkiem.setBounds(20, 90, 100, 30);
        lbTimkiem.setText("Tìm Kiếm");
        lbTimkiem.setFont(new Font("Times New Roman",Font.BOLD,16));
        lbTimkiem.setForeground(Color.BLACK);
        lbTimkiem.setHorizontalAlignment(JLabel.LEFT);
        pKMDichVu.add(lbTimkiem);
        
        String sp[] = {"Chọn kiểm tìm kiếm","Theo tên KMDV","Tìm kiếm nâng cao"};
        
        cbTimkiem=new JComboBox(sp);
        cbTimkiem.setSelectedIndex(0);
        cbTimkiem.setFont(new Font("Arial",0,16));
        cbTimkiem.setBounds(120, 90, 270, 30);
        pKMDichVu.add(cbTimkiem);
        
        String sp1[] = {"Chọn tình trạng","Đang thực hiện","Chưa đến hạn","Đã hết hạn"};
        cbTinhtrang=new JComboBox(sp1);
        cbTinhtrang.setSelectedIndex(0);
        cbTinhtrang.setFont(new Font("Arial",0,16));
        cbTinhtrang.setBounds(400, 90, 150, 30);
        pKMDichVu.add(cbTinhtrang);
        
        JLabel lbNgayBD = new JLabel();
        lbNgayBD.setBounds(20, 210, 100, 30);
        lbNgayBD.setText("Ngày bắt đầu");
        lbNgayBD.setFont(new Font("Times New Roman",Font.BOLD,16));
        lbNgayBD.setForeground(Color.BLACK);
        lbNgayBD.setHorizontalAlignment(JLabel.LEFT);
        pKMDichVu.add(lbNgayBD);
        
        daybd= new JDateChooser();
        daybd.setBounds(120, 210, 150, 30);
        daybd.setDateFormatString("dd-MM-yyyy");
        JTextFieldDateEditor editor2;
        editor2 = (JTextFieldDateEditor) daybd.getDateEditor();
        editor2.setEditable(false);
        pKMDichVu.add(daybd);
        
        JLabel lbTenKMDV = new JLabel();
        lbTenKMDV.setBounds(20, 150, 100, 30);
        lbTenKMDV.setText("Tên KMDV");
        lbTenKMDV.setFont(new Font("Times New Roman",Font.BOLD,16));
        lbTenKMDV.setForeground(Color.BLACK);
        lbTenKMDV.setHorizontalAlignment(JLabel.LEFT);        
        pKMDichVu.add(lbTenKMDV);
        
        txTenKMDV = new JTextField();
        txTenKMDV.setBounds(120, 150, 420, 30);
        pKMDichVu.add(txTenKMDV);
        
        JLabel lbNgayKT = new JLabel();
        lbNgayKT.setBounds(290, 210, 100, 30);
        lbNgayKT.setText("Ngày kết thúc");
        lbNgayKT.setFont(new Font("Times New Roman",Font.BOLD,16));
        lbNgayKT.setForeground(Color.BLACK);
        lbNgayKT.setHorizontalAlignment(JLabel.LEFT);
        pKMDichVu.add(lbNgayKT);
        
        daykt= new JDateChooser();
        daykt.setBounds(390, 210, 150, 30);
        daykt.setDateFormatString("dd-MM-yyyy");
        JTextFieldDateEditor editor;
        editor = (JTextFieldDateEditor) daykt.getDateEditor();
        editor.setEditable(false);
        pKMDichVu.add(daykt);
        
        lbTieude=new JLabel();
        lbTieude.setText("Khuyến mãi dịch vụ");
        lbTieude.setFont(new Font("Times New Roman",Font.BOLD,26));
        lbTieude.setBounds(300, 10, 280, 30);
        lbTieude.setForeground(Color.BLACK);
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        pKMDichVu.add(lbTieude);
        
        ImageIcon iconthem = new ImageIcon(icon.iconthem);
        btThem = new JButton("Thêm",iconthem);
        btThem.setBounds(680, 210, 96, 35);
        btThem.setBackground(colorButton);
        btThem.setForeground(Color.WHITE);
        btThem.setBorderPainted(false);
        btThem.setFocusPainted(false);
        pKMDichVu.add(btThem);
        
        ImageIcon icontimkiem = new ImageIcon(icon.icontimkiem);
        btTimkiem = new JButton("Tìm kiếm",icontimkiem);
        btTimkiem.setBounds(560, 90, 120, 35);
        btTimkiem.setBackground(colorButton);
        btTimkiem.setForeground(Color.WHITE);
        btTimkiem.setBorderPainted(false);
        btTimkiem.setFocusPainted(false);
        pKMDichVu.add(btTimkiem);
        
        ImageIcon iconsua = new ImageIcon(icon.iconsua);
        btSua = new JButton("Sửa",iconsua);
        btSua.setBounds(790, 210, 95, 35);
        btSua.setBackground(colorButton);
        btSua.setForeground(Color.WHITE);
        btSua.setBorderPainted(false);
        btSua.setFocusPainted(false);
        pKMDichVu.add(btSua);
        
        ImageIcon iconxoa = new ImageIcon(icon.iconxoa);
        btXoa = new JButton("Xóa",iconxoa);
        btXoa.setBounds(900, 210, 95, 35);
        btXoa.setBackground(colorButton);
        btXoa.setForeground(Color.WHITE);
        btXoa.setBorderPainted(false);
        btXoa.setFocusPainted(false);
        pKMDichVu.add(btXoa);
        
        ImageIcon iconxemchitiet = new ImageIcon(icon.iconxem);
        btXemchitiet = new JButton("Xem chi tiết",iconxemchitiet);
        btXemchitiet.setBounds(760, 605, 180, 35);
        btXemchitiet.setBackground(colorButton);
        btXemchitiet.setForeground(Color.WHITE);
        btXemchitiet.setBorderPainted(false);
        btXemchitiet.setFocusPainted(false);
        pKMDichVu.add(btXemchitiet);
        
        ImageIcon iconcapnhat = new ImageIcon(icon.iconcapnhat);
        btCapnhat = new JButton("Cập nhật",iconcapnhat);
        btCapnhat.setBounds(50, 605, 140, 35);
        btCapnhat.setBackground(colorButton);
        btCapnhat.setForeground(Color.WHITE);
        btCapnhat.setBorderPainted(false);
        btCapnhat.setFocusPainted(false);
        pKMDichVu.add(btCapnhat);
        
        JPanel pnlchitietkm = new JPanel();
        pnlchitietkm.setLayout(null);
        pnlchitietkm.setBounds(710, 250, 280, 350);
        pnlchitietkm.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        pKMDichVu.add(pnlchitietkm);
        
        JLabel tieude= new JLabel();
        tieude.setText("THÔNG TIN KHUYẾN MÃI");
        tieude.setFont(new Font("Times New Roman",Font.BOLD,16));
        tieude.setBounds(0, 10, 280, 20);
        tieude.setForeground(Color.BLACK);
        tieude.setHorizontalAlignment(JLabel.CENTER);
        pnlchitietkm.add(tieude);
        
        JLabel lbMakmdv= new JLabel();
        lbMakmdv.setText("Mã KMDV");
        lbMakmdv.setFont(new Font("Times New Roman",Font.BOLD,16));
        lbMakmdv.setBounds(10, 40, 270, 20);
        lbMakmdv.setForeground(Color.BLACK);
        lbMakmdv.setHorizontalAlignment(JLabel.LEFT);
        pnlchitietkm.add(lbMakmdv);
        
        txMakmdv= new JLabel();
        txMakmdv.setText("");
        txMakmdv.setFont(new Font("Times New Roman",Font.BOLD,16));
        txMakmdv.setBounds(20, 70, 240, 20);
        txMakmdv.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

        txMakmdv.setHorizontalAlignment(JLabel.CENTER);
        pnlchitietkm.add(txMakmdv);
        
        JLabel lbTenkmdv= new JLabel();
        lbTenkmdv.setText("Tên KMDV");
        lbTenkmdv.setFont(new Font("Times New Roman",Font.BOLD,16));
        lbTenkmdv.setBounds(10, 100, 270, 20);
        lbTenkmdv.setForeground(Color.BLACK);
        lbTenkmdv.setHorizontalAlignment(JLabel.LEFT);
        pnlchitietkm.add(lbTenkmdv);
        
        txTenkmdv= new JLabel();
        txTenkmdv.setText("");
        txTenkmdv.setFont(new Font("Times New Roman",Font.BOLD,16));
        txTenkmdv.setBounds(20, 130, 240, 20);
        txTenkmdv.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
 
        txTenkmdv.setHorizontalAlignment(JLabel.CENTER);
        pnlchitietkm.add(txTenkmdv);
        
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
        
        txTenKMDV.setEditable(false);
        daybd.setEnabled(false);
        daykt.setEnabled(false);
        cbTinhtrang.setEnabled(false); 
        
        KhuyenMaiDichVuBUS kmdvbus=new KhuyenMaiDichVuBUS();
        if (KhuyenMaiDichVuBUS.dskmdv==null){
            kmdvbus.docdskmdv();
        }
        
        Khoitaoscroll();
        
        CacNutChucNang();
    }
    public JTable Danhsachkhuyenmaidichvu(ArrayList<KhuyenMaiDichVuDTO> arr){
//        tbdskmdv=new JTable();
//        tbdskmdv.getTableHeader().setBackground(new Color(3,169,244));
//        tbdskmdv.getTableHeader().setForeground(Color.WHITE);
//        tbdskmdv.getTableHeader().setFont(new Font("Times New Roman",0,18));
//        tbdskmdv.getTableHeader().setPreferredSize(new Dimension(0,30));
//        tbdskmdv.setFont(new Font("Times New Roman",0,18));
//        tbdskmdv.setForeground(Color.BLACK);

        tbdskmdv=new JTable();
        tbdskmdv.setRowHeight(25);
        tbdskmdv.getTableHeader().setBackground(colorButton);
        tbdskmdv.getTableHeader().setForeground(Color.WHITE);
        tbdskmdv.getTableHeader().setFont(new Font("Times New Roman",0,18));
        tbdskmdv.getTableHeader().setPreferredSize(new Dimension(0,30));
        tbdskmdv.setFont(new Font("Times New Roman",0,18));
        tbdskmdv.setForeground(Color.BLACK);
        
        model=new DefaultTableModel();
        Vector cottieude=new Vector();
        cottieude.add("Stt");
        cottieude.add("Mã KMDV");
        cottieude.add("Tên KMDV");
        cottieude.add("Ngày bắt đầu");
        cottieude.add("Ngày kết thúc");
        cottieude.add("Tình trạng");
        Xulydulieu xl = new Xulydulieu();
        if (model.getRowCount()==0){
            model=new DefaultTableModel(cottieude, 0);
        }
        int i=0;
        for (KhuyenMaiDichVuDTO k : arr){
            i++;
            Vector dong=new Vector();                
            dong.add(i);
            dong.add(k.getMaKMDV());
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
        tbdskmdv.setModel(model);
        tbdskmdv.getColumnModel().getColumn(0).setMaxWidth(50);
//        tbdskmdv.getColumnModel().getColumn(2).setMaxWidth(250);
//        tbdskmdv.getColumnModel().getColumn(4).setMinWidth(150);
//        tbdskmdv.getColumnModel().getColumn(5).setMinWidth(150);
        
        //--------------------Căn giữa trong bảng-----------------------//
        DefaultTableCellRenderer center=new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        tbdskmdv.setDefaultRenderer(tbdskmdv.getColumnClass(0), center);
        tbdskmdv.updateUI();
         tbdskmdv.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                int i=tbdskmdv.getSelectedRow();
                String s=tbdskmdv.getModel().getValueAt(i, 1).toString();
                String s1=tbdskmdv.getModel().getValueAt(i, 2).toString();
                String s2=tbdskmdv.getModel().getValueAt(i, 3).toString();
                String s3=tbdskmdv.getModel().getValueAt(i, 4).toString();
                String s4=tbdskmdv.getModel().getValueAt(i, 5).toString();
                txMakmdv.setText(s);
                txTenkmdv.setText(s1);
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
        return tbdskmdv;
        //--------------------------------------------------------------//
    }
    public void Khoitaoscroll(){
        tbdskmdv = Danhsachkhuyenmaidichvu(KhuyenMaiDichVuBUS.dskmdv);
        scrdskm=new JScrollPane(tbdskmdv);
        scrdskm.setViewportView(tbdskmdv);
        scrdskm.setBounds(0, 250, 700, 350); // Tùy mỗi người
        pKMDichVu.add(scrdskm);
    }
    public void JFrameChitiet(KhuyenMaiDichVuDTO p){
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
        
        JLabel lbtenDv= new JLabel();
        lbtenDv.setBounds(20, 50, 80, 30);
        lbtenDv.setText("Mã DV");
        lbtenDv.setFont(new Font("Times New Roman",Font.BOLD,13));
        lbtenDv.setForeground(Color.BLACK);
        lbtenDv.setHorizontalAlignment(JLabel.CENTER);
        fThemct.add(lbtenDv);
        
        txDvct=new JTextField();
        txDvct.setBounds(110, 50, 160, 30);
        txDvct.setEditable(false);
        txDvct.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        txDvct.setBackground(Color.WHITE);
        fThemct.add(txDvct);
        
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
        if(p.getTinhTrang()==1||p.getTinhTrang()==2){
            btThemct.setBounds(0,0,0,0);
        }else{
            btThemct.setBounds(20, 150, 96, 35);
        }
        fThemct.add(btThemct);
        
        ImageIcon iconsua = new ImageIcon(icon.iconsua);
        btSuact = new JButton("Sửa",iconsua);
        btSuact.setBounds(126, 150, 95, 35);
        btSuact.setBackground(colorButton);
        btSuact.setForeground(Color.WHITE);
        btSuact.setBorderPainted(false);
        btSuact.setFocusPainted(false);
        if(p.getTinhTrang()==1||p.getTinhTrang()==2){
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
        if(p.getTinhTrang()==1||p.getTinhTrang()==2){
            btXoact.setBounds(0,0,0,0);
        }else{
            btXoact.setBounds(231, 150, 95, 35);
        }
        fThemct.add(btXoact);
        
        model1= new DefaultTableModel();
        tbdsctkmdv=new JTable();
        
        tbdsctkmdv.getTableHeader().setBackground(colorButton);
        tbdsctkmdv.getTableHeader().setForeground(Color.WHITE);
        tbdsctkmdv.getTableHeader().setFont(new Font("Times New Roman",0,18));
        tbdsctkmdv.getTableHeader().setPreferredSize(new Dimension(0,30));
        tbdsctkmdv.setFont(new Font("Times New Roman",0,18));
        tbdsctkmdv.setForeground(Color.BLACK);
        
        JScrollPane scr=new JScrollPane();
        scr.setViewportView(tbdsctkmdv);
        scr.setBounds(10, 200, 500, 350); // Tùy mỗi người
        
        fThemct.add(scr);
        
        ChiTietKMDichVuBUS kmdvbus=new ChiTietKMDichVuBUS();
        if (ChiTietKMDichVuBUS.dsctkmdv==null){
            kmdvbus.docdsctkmdv();
        }
        Vector cottieude=new Vector();
        cottieude.add("Stt");
        cottieude.add("Mã DV");
        cottieude.add("Tên DV");
        cottieude.add("Phần trăm KM");
//        cottieude.add("Tình trạng");
        if (model1.getRowCount()==0){
            model1=new DefaultTableModel(cottieude, 0);
        }
        stt=0;
        for (ChiTietKMDichVuDTO k : ChiTietKMDichVuBUS.dsctkmdv){
            if(k.getMaKMDV().equals(p.getMaKMDV())){
                stt++;
                ChiTietKMDichVuBUS dv = new ChiTietKMDichVuBUS();
                Vector dong=new Vector();              
                dong.add(stt);
                dong.add(k.getMaDV());
                dong.add(dv.lay1dv(k.getMaDV()));
                dong.add(k.getPhanTramKM());
                model1.addRow(dong);
            }
        }
        tbdsctkmdv.setModel(model1);
        tbdsctkmdv.getColumnModel().getColumn(0).setMaxWidth(50);
//        tbdskmdv.getColumnModel().getColumn(2).setMaxWidth(250);
//        tbdskmdv.getColumnModel().getColumn(4).setMinWidth(150);
//        tbdskmdv.getColumnModel().getColumn(5).setMinWidth(150);
        
        //--------------------Căn giữa trong bảng-----------------------//
        DefaultTableCellRenderer center=new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        tbdsctkmdv.setDefaultRenderer(tbdsctkmdv    .getColumnClass(0), center);
        tbdsctkmdv.updateUI();
        //--------------------------------------------------------------//
       
        
        JLabel tieudekm= new JLabel();
        tieudekm.setText("THÔNG TIN KHUYẾN MÃI");
        tieudekm.setFont(new Font("Times New Roman",Font.BOLD,16));
        tieudekm.setBounds(0, 10, 280, 20);
        tieudekm.setForeground(Color.BLACK);
        tieudekm.setHorizontalAlignment(JLabel.CENTER);
        pnlchitietkm.add(tieudekm);
        
        JLabel lbMakmdv= new JLabel();
        lbMakmdv.setText("Mã KMDV");
        lbMakmdv.setFont(new Font("Times New Roman",Font.BOLD,16));
        lbMakmdv.setBounds(10, 40, 270, 20);
        lbMakmdv.setForeground(Color.BLACK);
        lbMakmdv.setHorizontalAlignment(JLabel.LEFT);
        pnlchitietkm.add(lbMakmdv);
        
        txMakmdvct= new JLabel();
        txMakmdvct.setText(p.getMaKMDV());
        txMakmdvct.setFont(new Font("Times New Roman",Font.BOLD,16));
        txMakmdvct.setBounds(20, 70, 220, 20);
        txMakmdvct.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
  
        txMakmdvct.setHorizontalAlignment(JLabel.CENTER);
        pnlchitietkm.add(txMakmdvct);
        
        JLabel lbTenkmdv= new JLabel();
        lbTenkmdv.setText("Tên KMDV");
        lbTenkmdv.setFont(new Font("Times New Roman",Font.BOLD,16));
        lbTenkmdv.setBounds(10, 100, 270, 20);
        lbTenkmdv.setForeground(Color.BLACK);
        lbTenkmdv.setHorizontalAlignment(JLabel.LEFT);
        pnlchitietkm.add(lbTenkmdv);
        
        txTenkmdvct= new JLabel();
        txTenkmdvct.setText(p.getTen());
        txTenkmdvct.setFont(new Font("Times New Roman",Font.BOLD,16));
        txTenkmdvct.setBounds(20, 130, 220, 20);
        txTenkmdvct.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
   
        txTenkmdvct.setHorizontalAlignment(JLabel.CENTER);
        pnlchitietkm.add(txTenkmdvct);
        
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
        }else{
            txTinhtrangct.setText("Đã hết hạn");
            txTinhtrangct.setForeground(Color.RED);
        }
        txTinhtrangct.setFont(new Font("Times New Roman",Font.BOLD,16));
        txTinhtrangct.setBounds(20, 310, 220, 20);
        txTinhtrangct.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        txTinhtrangct.setHorizontalAlignment(JLabel.CENTER);
        pnlchitietkm.add(txTinhtrangct);
        CacNutChucNangChiTiet();
    }
    public void JFrameChucnang(int x){
        fThem = new JFrame();
        fThem.setBounds(550, 100, 500, 360);
        fThem.setLayout(null);
       
        JLabel tieude= new JLabel();
        if(x==0){
            tieude.setText("Thêm khuyến mãi dịch vụ");
        }else{
            tieude.setText("Sửa khuyến mãi dịch vụ");
        }
        tieude.setFont(new Font("Times New Roman",Font.BOLD,23));
        tieude.setBounds(0, 0, 500, 30);
        tieude.setForeground(Color.WHITE);
        tieude.setHorizontalAlignment(JLabel.CENTER);
        tieude.setBackground(colorButton);
        tieude.setOpaque(true);
        fThem.add(tieude);
        
        JLabel lbTenKMDV = new JLabel();
        lbTenKMDV.setBounds(20, 110, 100, 30);
        lbTenKMDV.setText("Tên KMDV");
        lbTenKMDV.setFont(new Font("Times New Roman",Font.BOLD,16));
        lbTenKMDV.setForeground(Color.BLACK);
        lbTenKMDV.setHorizontalAlignment(JLabel.LEFT);
        fThem.add(lbTenKMDV);
        
        txTenKMDVcn = new JTextField();
        txTenKMDVcn.setBounds(130, 110, 300, 30);
        txTenKMDVcn.setFont(new Font("Times New Roman",Font.BOLD,16));
   
        fThem.add(txTenKMDVcn);
        
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
        daybdcn.setOpaque(true);
        JTextFieldDateEditor editor2;
        editor2 = (JTextFieldDateEditor) daybdcn.getDateEditor();
        editor2.setEditable(false);
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
        dayktcn.setOpaque(true);
        JTextFieldDateEditor editor;
        editor = (JTextFieldDateEditor) dayktcn.getDateEditor();
        editor.setEditable(false);
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
        tbdsctkmdv.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                int i=tbdsctkmdv.getSelectedRow();
                String s=tbdsctkmdv.getModel().getValueAt(i, 1).toString();
                String s1=tbdsctkmdv.getModel().getValueAt(i, 2).toString();
                String s2=tbdsctkmdv.getModel().getValueAt(i, 3).toString();
                txDvct.setText(s);
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
        btThemct.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                ChiTietKMDichVuDTO p = new ChiTietKMDichVuDTO();
                ChiTietKMDichVuBUS ctp = new ChiTietKMDichVuBUS();
                if(txPtkm.getText().equals("") || txDvct.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Điền đầy đủ thông tin");
                }else{
                    if(ctp.ktraphantram(txPtkm.getText())==1){
                        ChiTietKMDichVuBUS ctkmdv= new ChiTietKMDichVuBUS();
                        p.setMaDV(txDvct.getText());
                        p.setMaKMDV(txMakmdvct.getText());
                        p.setPhanTramKM(Integer.parseInt(txPtkm.getText()));
                        ChiTietKMDichVuBUS ct= new ChiTietKMDichVuBUS();
                        if(ctkmdv.ktratrung(p)==1){
                            Vector row=new Vector();
                            row.add(stt+1);
                            row.add(p.getMaDV());
                            row.add(ct.lay1dv(p.getMaDV()));
                            row.add(txPtkm.getText());
                            model1.addRow(row);
                            tbdsctkmdv.setModel(model1);
                        ctkmdv.them(p);
                        }else{
                            JOptionPane.showMessageDialog(null, "Dịch vụ đã tồn tại");
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
                ChiTietKMDichVuDTO p = new ChiTietKMDichVuDTO();
                ChiTietKMDichVuDTO p1 = new ChiTietKMDichVuDTO();
                if(txPtkm.getText().equals("") || txDvct.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Nhập đầy đủ thông tin");
                }else{ 
                    int i=tbdsctkmdv.getSelectedRow();
                    if(i != -1){
                        String s=tbdsctkmdv.getModel().getValueAt(i, 1).toString();
                        String s1=tbdsctkmdv.getModel().getValueAt(i, 2).toString();
                        String s2=tbdsctkmdv.getModel().getValueAt(i, 3).toString();
                        p1.setMaKMDV(txMakmdvct.getText());
                        p1.setMaDV(s);
                        p1.setPhanTramKM(Integer.parseInt(s2));
                        ChiTietKMDichVuBUS ctkmdv= new ChiTietKMDichVuBUS();
                        p.setMaDV(txDvct.getText());
                        p.setMaKMDV(txMakmdvct.getText());
                        p.setPhanTramKM(Integer.parseInt(txPtkm.getText()));
                        if(ctkmdv.sua(p1, p)==1){
                            tbdsctkmdv.getModel().setValueAt(p.getMaDV(), i, 1);
                            tbdsctkmdv.getModel().setValueAt(ctkmdv.lay1dv(p.getMaDV()), i, 2);
                            tbdsctkmdv.getModel().setValueAt(txPtkm.getText(), i, 3);
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
                ChiTietKMDichVuDTO p1 = new ChiTietKMDichVuDTO();
                int i=tbdsctkmdv.getSelectedRow();
                if(i != -1){
                    int input=JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa", "Xóa tài khoản", JOptionPane.YES_NO_OPTION);
                    if(input==0){
                        String s=tbdsctkmdv.getModel().getValueAt(i, 1).toString();
                        String s1=tbdsctkmdv.getModel().getValueAt(i, 2).toString();
                        String s2=tbdsctkmdv.getModel().getValueAt(i, 3).toString();
                        p1.setMaKMDV(txMakmdvct.getText());
                        p1.setMaDV(s);
                        p1.setPhanTramKM(Integer.parseInt(s2));
                        ChiTietKMDichVuBUS ctkmdv= new ChiTietKMDichVuBUS();
                        ctkmdv.xoa1chitiet(p1);
                        model1.removeRow(i);
                        tbdsctkmdv.setModel(model1);
                
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
        btXemp.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                BangChonDV();
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
    }
    public void CacNutChucNang(){
        String makmdv;
        btXemchitiet.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                KhuyenMaiDichVuDTO p = new KhuyenMaiDichVuDTO();
                int i=tbdskmdv.getSelectedRow();
                if(i!=-1)
                {
                    String s=tbdskmdv.getModel().getValueAt(i, 1).toString();
                    String s1=tbdskmdv.getModel().getValueAt(i, 2).toString();
                    String s2=tbdskmdv.getModel().getValueAt(i, 3).toString();
                    String s3=tbdskmdv.getModel().getValueAt(i, 4).toString();
                    String s4=tbdskmdv.getModel().getValueAt(i, 5).toString();
                    p.setMaKMDV(s);
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
                }
                else{
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
                int i=tbdskmdv.getSelectedRow();
                if(i!=-1){
                    JFrameChucnang(1);
                    String s=tbdskmdv.getModel().getValueAt(i, 1).toString();
                    String s1=tbdskmdv.getModel().getValueAt(i, 2).toString();
                    String s2=tbdskmdv.getModel().getValueAt(i, 3).toString();
                    String s3=tbdskmdv.getModel().getValueAt(i, 4).toString();
                    String s4=tbdskmdv.getModel().getValueAt(i, 5).toString();
                    txTenKMDVcn.setText(s1);
                    try {
                        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(s2);
                        Date date2 = new SimpleDateFormat("dd-MM-yyyy").parse(s3);
                        daybdcn.setDate(date);
                        dayktcn.setDate(date2);
                    fThem.setVisible(true);
                    } catch (ParseException ex) {
                        Logger.getLogger(KhuyenMaiDichVuGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Chọn khuyến mãi dịch vụ cần sửa");
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
                KhuyenMaiDichVuDTO p = new  KhuyenMaiDichVuDTO();
                KhuyenMaiDichVuBUS kmdv = new KhuyenMaiDichVuBUS();
                int i=tbdskmdv.getSelectedRow();
                if(i!=-1){
                    if(tbdskmdv.getModel().getValueAt(i, 5).toString().equals("Đang thực hiện")){
                        String s=tbdskmdv.getModel().getValueAt(i, 1).toString();
                        String s1=tbdskmdv.getModel().getValueAt(i, 2).toString();
                        String s2=tbdskmdv.getModel().getValueAt(i, 3).toString();
                        String s3=tbdskmdv.getModel().getValueAt(i, 4).toString();
                        String s4=tbdskmdv.getModel().getValueAt(i, 5).toString();
                        p.setMaKMDV(s);
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
                    }else{
                        JOptionPane.showMessageDialog(null, "Không thể xóa khuyến mãi đanh thực hiện");
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
                JTable tbl = Danhsachkhuyenmaidichvu(KhuyenMaiDichVuBUS.dskmdv);
                scrdskm1= new JScrollPane(tbl);
                scrdskm1.setBounds(0, 0, 700, 350);
                scrdskm.add(scrdskm1);
                scrdskm.repaint();
                scrdskm.revalidate();
                pKMDichVu.add(scrdskm);
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
                KhuyenMaiDichVuBUS kmdv= new KhuyenMaiDichVuBUS();
                JTable tbl = new JTable();
                if(cbTimkiem.getSelectedItem().equals("Theo tên KMDV")){
                    tbl = Danhsachkhuyenmaidichvu(kmdv.timkiemtheoten(txTenKMDV.getText()));
                    scrdskm.removeAll();
                    scrdskm1= new JScrollPane(tbl);
                    scrdskm1.setBounds(0, 0, 700, 350);
                    scrdskm.add(scrdskm1);
                    scrdskm.repaint();
                    scrdskm.revalidate();
                    pKMDichVu.add(scrdskm); 
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
                        tbl = Danhsachkhuyenmaidichvu(kmdv.timkiemnangcao(i,"",""));
                        scrdskm.removeAll();
                        scrdskm1= new JScrollPane(tbl);
                        scrdskm1.setBounds(0, 0, 700, 350);
                        scrdskm.add(scrdskm1);
                        scrdskm.repaint();
                        scrdskm.revalidate();
                        pKMDichVu.add(scrdskm); 
                    }else if(!cbTinhtrang.getSelectedItem().equals("Chọn tình trạng") && daybd.getDate()!=null && daykt.getDate()!=null){
                        System.out.println("2");
                        SimpleDateFormat date= new SimpleDateFormat("yyyy-MM-dd");
                        SimpleDateFormat date2= new SimpleDateFormat("yyyy-MM-dd");
                        tbl = Danhsachkhuyenmaidichvu(kmdv.timkiemnangcao(i,date2.format(daybd.getDate()),date2.format(daykt.getDate())));
                        scrdskm.removeAll();
                        scrdskm1= new JScrollPane(tbl);
                        scrdskm1.setBounds(0, 0, 700, 350);
                        scrdskm.add(scrdskm1);
                        scrdskm.repaint();
                        scrdskm.revalidate();
                        pKMDichVu.add(scrdskm);
                    }else if(i==0 && daybd.getDate()!=null && daykt.getDate()!=null){
                        System.out.println("3");
                        SimpleDateFormat date= new SimpleDateFormat("yyyy-MM-dd");
                        SimpleDateFormat date2= new SimpleDateFormat("yyyy-MM-dd");
                        tbl = Danhsachkhuyenmaidichvu(kmdv.timkiemnangcao(0,date2.format(daybd.getDate()),date2.format(daykt.getDate())));
                        scrdskm.removeAll();
                        scrdskm1= new JScrollPane(tbl);
                        scrdskm1.setBounds(0, 0, 700, 350);
                        scrdskm.add(scrdskm1);
                        scrdskm.repaint();
                        scrdskm.revalidate();
                        pKMDichVu.add(scrdskm);
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
            @Override
            public void itemStateChanged(ItemEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.                
                if (cbTimkiem.getSelectedItem().equals("Theo tên KMDV")){
                    txTenKMDV.setEditable(true);
                    daybd.setEnabled(false);
                    daykt.setEnabled(false);
                    cbTinhtrang.setEnabled(false);
                }
                else if(cbTimkiem.getSelectedItem().equals("Tìm kiếm nâng cao")){
                    txTenKMDV.setEditable(false);
                    daybd.setEnabled(true);
                    daykt.setEnabled(true);
                    cbTinhtrang.setEnabled(true);
                }else{
                    txTenKMDV.setEditable(false);
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
                KhuyenMaiDichVuDTO p = new  KhuyenMaiDichVuDTO();
                KhuyenMaiDichVuBUS kmdv = new KhuyenMaiDichVuBUS();
                Xulydulieu xldl= new Xulydulieu();
                p.setMaKMDV(kmdv.MaCuoiDS());
                SimpleDateFormat date= new SimpleDateFormat("yyy-MM-dd");
                SimpleDateFormat date2= new SimpleDateFormat("dd-MM-yyyy");
                if(txTenKMDVcn.getText().equals("") || daybdcn.getDate()==null || dayktcn.getDate()==null){
                    JOptionPane.showMessageDialog(null, "Nhập đầy đủ thông tin");
                }else{
                    if(xldl.TinhSoNgay(date2.format(daybdcn.getDate()),date2.format(dayktcn.getDate()))<0){
                        JOptionPane.showMessageDialog(null, "Ngày bắt đầu nhỏ hơn ngày kết thúc");
                    }
                    else if(xldl.TinhSoNgay(xldl.chuyendate(xldl.LayNgayThangNamHienTai()),date2.format(daybdcn.getDate()))<0){
                        JOptionPane.showMessageDialog(null, "Không thể tạo cho quá khứ");
                    }else{
                        p.setTen(txTenKMDVcn.getText());
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
                KhuyenMaiDichVuDTO p = new  KhuyenMaiDichVuDTO();
                int i=tbdskmdv.getSelectedRow();
                String s=tbdskmdv.getModel().getValueAt(i, 1).toString();
                String s1=tbdskmdv.getModel().getValueAt(i, 2).toString();
                KhuyenMaiDichVuBUS kmdv = new KhuyenMaiDichVuBUS();
                Xulydulieu xldl= new Xulydulieu();
                p.setMaKMDV(s);
                SimpleDateFormat date= new SimpleDateFormat("yyy-MM-dd");
                SimpleDateFormat date2= new SimpleDateFormat("dd-MM-yyyy");
                if(txTenKMDVcn.getText().equals("") || daybdcn.getDate()==null || dayktcn.getDate()==null){
                    JOptionPane.showMessageDialog(null, "Nhập đầy đủ thông tin");
                }else{
                    if(xldl.TinhSoNgay(date2.format(daybdcn.getDate()),date2.format(dayktcn.getDate()))<0){
                        JOptionPane.showMessageDialog(null, "Ngày bắt đầu nhỏ hơn ngày kết thúc");
                    }
                    else{
                        p.setTen(txTenKMDVcn.getText());
                        p.setNgayBD(date.format(daybdcn.getDate()));
                        p.setNgayKT(date.format(dayktcn.getDate()));
                        kmdv.sua(p);;
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
    public void BangChonDV(){
        JFrame fchon=new JFrame();
        fchon.setLayout(null);
        fchon.setBounds(0, 0, 550, 400);
        fchon.setPreferredSize(new Dimension(550,400));
        
        //---------------------------------Hiện giữa màn hình-----------------------------//
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        fchon.setLocation((int)d.getWidth()/2 - (int)fchon.getPreferredSize().getWidth()/2,
                      (int)d.getHeight()/2 - (int)fchon.getPreferredSize().getHeight()/2);
        //--------------------------------------------------------------------------------//
        
        JLabel lbTieude=new JLabel("Chọn dịch vụ");
        lbTieude.setFont(new Font("Times New Roman",Font.BOLD,25));
        lbTieude.setForeground(Color.BLACK);
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        lbTieude.setBounds(150, 20, 250, 25);
        
        fchon.add(lbTieude);
        
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
        cot.add("Mã dịch vụ");
        cot.add("Tên dịch vụ");
        cot.add("Đơn giá");
        if (md.getRowCount()==0){
            md=new DefaultTableModel(cot, 0);
        }
        int i=1;
        Xulydulieu xl = new Xulydulieu();
        for (DichVuDTO k1 : DichVuBUS.dsdv){
            Vector dong=new Vector();
            dong.add(i);
            dong.add(k1.getMaDV());
            dong.add(k1.getTenDV()); 
            dong.add(xl.ChuyenKieuTien(String.valueOf(k1.getDonGia()))+" VND"); 
            md.addRow(dong);
            i++;
        }
        tbp.setModel(md);
        tbp.getColumnModel().getColumn(0).setMinWidth(50);
//        tbp.getColumnModel().getColumn(1).setMinWidth(120);
//        tbp.getColumnModel().getColumn(2).setMinWidth(250);                
        
        //--------------------Căn giữa trong bảng-----------------------//
        DefaultTableCellRenderer center=new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        tbp.setDefaultRenderer(tbp.getColumnClass(0), center);
        tbp.updateUI();
        //--------------------------------------------------------------//
        
        JScrollPane sc=new JScrollPane();
        sc.setViewportView(tbp);
        sc.setBounds(10, 140, 520, 200);
        
        fchon.add(sc);
        
        tbp.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int i=tbp.getSelectedRow();
                String maq=tbp.getModel().getValueAt(i, 1).toString();
                txDvct.setText(maq);
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
//        
//    }
}
