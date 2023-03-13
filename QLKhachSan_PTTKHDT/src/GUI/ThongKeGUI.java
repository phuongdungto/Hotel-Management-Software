/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import BUS.Xulydulieu;
import BUS.HoaDonBUS;
import DTO.HoaDonDTO;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.awt.Color;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;



/**
 *
 * @author Duy
 */
public class ThongKeGUI {
    protected JPanel pThongKe;
    protected JPanel pBangdstk;
    protected JPanel pBangctdstk;
    protected JLabel lbTieude, lbtongtien, lbtongtien1, lbtongtien2, lbtongtien3, lbtongtien4, lbtongtien5, lbngay, lbt;
    protected JButton btExcel1,btExcel2,btExcel3,btExcel4,btExcel5;
    protected JScrollPane sc1,sc2,sc3,sc4,sc5;
    protected JComboBox cbxQuy;
    protected Color colorButton = new Color(36,36,36);
    protected Color colorLaybel = new Color(238, 238, 238);
    protected JTextFieldDateEditor editor;
    protected JDateChooser txtDateTKngay, txtDateTKthang, txtDateTKnam, txtDateTKquy, txtDateTKkhoangthoigian1, txtDateTKkhoangthoigian2;
    public DefaultTableModel model;
    public JPanel KhoiTaoPanel(int width, int height) {
        pThongKe = new JPanel(null);
        pThongKe.setLayout(null);
        pThongKe.setBounds(0, 0, width, height);
        pThongKe.setPreferredSize(new Dimension(width, height));
        pThongKe.setOpaque(true);
        lbTieude = new JLabel();
        lbTieude.setText("Thống kê");
        lbTieude.setFont(new Font("Time New Roman", Font.BOLD, 26));
        lbTieude.setBounds(0, 0, 1000, 50);
        lbTieude.setForeground(Color.BLACK);
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        pThongKe.add(lbTieude);
                
        JTabbedPane tab = new JTabbedPane();
        tab.setBounds(10, 50, width-20, height-60);
        pThongKe.add(tab);
        JPanel ngay = Theongay(width, height);
        ngay.setBounds(0, 0, 900, 400);
        
        JPanel thang = Theothang(width, height);
        thang.setBounds(0, 0, 900, 400);
        
        JPanel nam = Theonam(width, height);
        nam.setBounds(0, 0, 900, 400);
        
        JPanel quy = Theoquy(width, height);
        quy.setBounds(0, 0, 900, 400);
        
        JPanel khoangthoigian = Theokhoangthoigian(width, height);
        khoangthoigian.setBounds(0, 0, 900, 400);
        
        tab.addTab("Theo ngày", ngay);
        tab.addTab("Theo tháng", thang);
        tab.addTab("Theo năm", nam);
        tab.addTab("Theo quý", quy);
        tab.addTab("Theo khoảng thời gian", khoangthoigian);
        
        return pThongKe;
    }

    public JTable Danhsachthongke(ArrayList<HoaDonDTO> dshd) {
        
        JTable tbdsTK=new JTable();
        model = new DefaultTableModel();
        tbdsTK.getTableHeader().setBackground(colorButton);
        tbdsTK.getTableHeader().setForeground(Color.WHITE);
        tbdsTK.getTableHeader().setFont(new Font("Time New Roman", 0, 18));
        tbdsTK.getTableHeader().setPreferredSize(new Dimension(0,30));
        tbdsTK.setFont(new Font("Times New Roman",0,18));
        tbdsTK.setForeground(Color.BLACK);
        tbdsTK.setRowHeight(25);
        
        
        
        HoaDonBUS hd = new HoaDonBUS();
        Vector header = new Vector();
        header.add("Mã hóa đơn");
        header.add("Mã phiếu đặt phòng");
        header.add("Mã nhân viên");
        header.add("Ngày lập hóa đơn");
        header.add("Tổng tiền");
        if(model.getRowCount()==0) 
            model = new DefaultTableModel(header, 0);
        for(HoaDonDTO hddto: dshd) {
            Vector row = new Vector();
            Xulydulieu xl = new Xulydulieu();
            row.add(hddto.getMaHD());
            row.add(hddto.getMaPDP());
            row.add(hddto.getMaNV());
            row.add(hddto.getNgayLapHD());
            String tmp = String.valueOf(hddto.getTongPhaiTra());
            row.add(xl.ChuyenKieuTien(tmp)+" VND");
            model.addRow(row);
        }
        tbdsTK.setModel(model);
        tbdsTK.getColumnModel().getColumn(0).setMinWidth(100);
        tbdsTK.getColumnModel().getColumn(1).setMinWidth(200);
        tbdsTK.getColumnModel().getColumn(2).setMinWidth(150); 
        tbdsTK.getColumnModel().getColumn(3).setMinWidth(250);
        tbdsTK.getColumnModel().getColumn(4).setMinWidth(250);
        DefaultTableCellRenderer center=new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        tbdsTK.setDefaultRenderer(tbdsTK.getColumnClass(0), center);
        tbdsTK.updateUI();
        
        return tbdsTK;
    }
  
    public JPanel Theongay(int width, int height)  {
        
        JPanel pTKngay=new JPanel();
        JPanel p0=new JPanel();
        pTKngay.setLayout(null);
        pTKngay.setBounds(0, 0, width, height);
        pTKngay.setPreferredSize(new Dimension(width,height));
        pTKngay.setBackground(new Color(238,238,238));
        pTKngay.setOpaque(true);
        
        lbngay = new JLabel();
        lbngay.setText("Thống kê theo ngày:");
        lbngay.setBounds(10, 30, 175, 20);
        lbngay.setFont(new Font("Times New Roman",0,20));
        lbngay.setOpaque(true);
        pTKngay.add(lbngay);
        txtDateTKngay = new JDateChooser();
        txtDateTKngay.setBounds(190, 30, 150, 25);
        txtDateTKngay.setDateFormatString("dd-MM-yyyy");
        txtDateTKngay.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
        editor = (JTextFieldDateEditor) txtDateTKngay.getDateEditor();
        editor.setEditable(false);
        editor.setBackground(Color.WHITE);
        editor.setFont(new Font("Arial",0,18));
        pTKngay.add(txtDateTKngay);
        ImageIcon iconsearch = new ImageIcon("src/img/search.png");
        JButton btThongKengay = new JButton("Thống kê",iconsearch);
        btThongKengay.setBounds(350, 30, 150,30);
        btThongKengay.setBackground(colorButton);
        btThongKengay.setForeground(Color.WHITE);
        btThongKengay.setBorderPainted(false);
        btThongKengay.setFocusPainted(false);
        pTKngay.add(btThongKengay);
  
        
        ImageIcon iconexcel = new ImageIcon("src/img/excel.png");
        btExcel1 = new JButton("Xuất excel", iconexcel);
        btExcel1.setBounds(800, 30, 150, 35);
        btExcel1.setBackground(colorButton);
        btExcel1.setForeground(Color.WHITE);
        btExcel1.setBorderPainted(false);
        btExcel1.setFocusPainted(false);
        pTKngay.add(btExcel1);
        
        ArrayList<HoaDonDTO> dshdtmp1 = new ArrayList<>();
        JTable tb1 = Danhsachthongke(dshdtmp1);
        JScrollPane sc= new JScrollPane(tb1);
        sc.setBounds(0, 100, 980, 370);
        sc.setViewportView(tb1);
        pTKngay.add(sc);
        
        
        pBangctdstk = new JPanel();
        pBangctdstk.setBounds(450, 500, 480, 50);
        pBangctdstk.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
        pBangctdstk.setLayout(null);
        pTKngay.add(pBangctdstk);
        
        lbtongtien = new JLabel("Tổng doanh thu:");
        lbtongtien.setFont(new Font("Arial",0,18));
        lbtongtien.setBounds(0, 0, 480, 35);
        lbtongtien.setForeground(Color.BLACK);
        lbtongtien.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK));
        pBangctdstk.add(lbtongtien);
        lbtongtien1 = new JLabel("0 VND");
        lbtongtien1.setFont(new Font("Arial",0,18));
        lbtongtien1.setHorizontalAlignment(JTextField.CENTER);
        lbtongtien1.setBounds(0, 0, 480, 35);
        lbtongtien1.setForeground(Color.BLACK);
        pBangctdstk.add(lbtongtien1);
        
        
        btThongKengay.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                ArrayList<HoaDonDTO> dshdtmp = new ArrayList<>();
                String datetmp="";
                if(txtDateTKngay.getDate()==null) 
                    datetmp = "";
                else 
                    datetmp = date.format(txtDateTKngay.getDate());
                if(!datetmp.equals("")) {
                    
                    HoaDonBUS hd_bus = new HoaDonBUS();
                    HoaDonDTO hd_dto = new HoaDonDTO();
                    dshdtmp=hd_bus.docdshdTKngay(datetmp);
                    JTable tb1 = Danhsachthongke(dshdtmp);
                    sc.removeAll();
                    sc1= new JScrollPane(tb1);
                    sc1.setBounds(0, 0, 980, 370);
                    sc.add(sc1);
                    sc.repaint();
                    sc.revalidate();
                    long tongtien=0;
                    for(HoaDonDTO dto: dshdtmp) {
                        tongtien+=dto.getTongPhaiTra();
                    }
                    String tmp = String.valueOf(tongtien);
                    Xulydulieu xl = new Xulydulieu();
                    lbtongtien1.setText(xl.ChuyenKieuTien(tmp) + " VND");
                } else {
                    JOptionPane.showMessageDialog(null, "Chọn ngày-tháng-năm để thống kê");
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
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        btExcel1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                try {
                    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                    ArrayList<HoaDonDTO> dshdtmp = new ArrayList<>();
                    String datetmp="";
                    if(txtDateTKngay.getDate()==null) 
                        datetmp = "";
                    else 
                        datetmp = date.format(txtDateTKngay.getDate());
                    if(!datetmp.equals("")) {
                        HoaDonBUS hd_bus = new HoaDonBUS();
                        HoaDonDTO hd_dto = new HoaDonDTO();
                        dshdtmp=hd_bus.docdshdTKngay(datetmp);
                        XSSFWorkbook wb=new XSSFWorkbook();
                        XSSFSheet sheet=wb.createSheet("Sheet");

                        XSSFRow row=null;
                        Cell cell=null;

                        row=sheet.createRow(0);
                        row.setHeight((short) 500);
                        cell=row.createCell(0,CellType.STRING);
                        cell.setCellValue("STT");
                        cell=row.createCell(1,CellType.STRING);
                        cell.setCellValue("Mã hóa đơn");
                        cell=row.createCell(2,CellType.STRING);
                        cell.setCellValue("Mã phiếu đặt phòng");
                        cell=row.createCell(3,CellType.STRING);
                        cell.setCellValue("Mã nhân viên");
                        cell=row.createCell(4,CellType.STRING);
                        cell.setCellValue("Ngày lập hóa đơn");
                        cell=row.createCell(5,CellType.STRING);
                        cell.setCellValue("Tổng tiền phải trả");
                        cell=row.createCell(6,CellType.STRING);

                        int doanhthu=0;
                        for (int i=0;i<dshdtmp.size();i++){
                            HoaDonDTO hd = dshdtmp.get(i);
                            row=sheet.createRow(1+i);
                            row.setHeight((short) 400);
                            row.createCell(0).setCellValue(i+1);
                            row.createCell(1).setCellValue(hd.getMaHD());
                            row.createCell(2).setCellValue(hd.getMaPDP());
                            row.createCell(3).setCellValue(hd.getMaNV());
                            row.createCell(4).setCellValue(hd.getNgayLapHD());
                            row.createCell(5).setCellValue(hd.getTongPhaiTra());
                            doanhthu+=hd.getTongPhaiTra();
                        }
                        row=sheet.createRow(dshdtmp.size()+2);
                        row.setHeight((short) 400);
                        cell=row.createCell(4,CellType.STRING);
                        cell.setCellValue("Doanh thu ngày: ");
                        Xulydulieu xl = new Xulydulieu();
                        row.createCell(5).setCellValue(xl.ChuyenKieuTien(String.valueOf(doanhthu)));

                        File f = new File("D:\\thongkengay.xls");
                        FileOutputStream out= new FileOutputStream(f);
                        wb.write(out);
                        out.flush();
                        out.close();
                        JOptionPane.showMessageDialog(null, "Xuất Excel thành công");
                    } else {
                        JOptionPane.showMessageDialog(null, "Chọn ngày-tháng-năm để xuất excel");
                    }
                } catch(Exception ex) {
                    ex.printStackTrace();
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
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        
        return pTKngay;
    }
    
    public JPanel Theothang(int width, int height) {
        
        JPanel pTKthang=new JPanel();
        pTKthang.setLayout(null);
        pTKthang.setBounds(0, 75, width, height);
        pTKthang.setPreferredSize(new Dimension(width,height));
        pTKthang.setBackground(new Color(238,238,238));
        pTKthang.setOpaque(true);
        
        lbngay = new JLabel();
        lbngay.setText("Thống kê theo tháng:");
        lbngay.setBounds(10, 30, 175, 20);
        lbngay.setFont(new Font("Times New Roman",0,20));
        lbngay.setOpaque(true);
        pTKthang.add(lbngay);
        txtDateTKthang = new JDateChooser();
        txtDateTKthang.setBounds(190, 30, 150, 25);
        txtDateTKthang.setDateFormatString("MM-yyyy");
        txtDateTKthang.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
        editor = (JTextFieldDateEditor) txtDateTKthang.getDateEditor();
        editor.setEditable(false);
        editor.setBackground(Color.WHITE);
        editor.setFont(new Font("Arial",0,18));
        pTKthang.add(txtDateTKthang);
        ImageIcon iconsearch = new ImageIcon("src/img/search.png");
        JButton btThongKethang = new JButton("Thống kê",iconsearch);
        btThongKethang.setBounds(350, 30, 150,30);
        btThongKethang.setBackground(colorButton);
        btThongKethang.setForeground(Color.WHITE);
        btThongKethang.setBorderPainted(false);
        btThongKethang.setFocusPainted(false);
        pTKthang.add(btThongKethang);
  
        
        ImageIcon iconexcel = new ImageIcon("src/img/excel.png");
        btExcel2 = new JButton("Xuất excel", iconexcel);
        btExcel2.setBounds(800, 30, 150, 35);
        btExcel2.setBackground(colorButton);
        btExcel2.setForeground(Color.WHITE);
        btExcel2.setBorderPainted(false);
        btExcel2.setFocusPainted(false);
        pTKthang.add(btExcel2);
        
        ArrayList<HoaDonDTO> dshdtmp1 = new ArrayList<>();
        JTable tb1 = Danhsachthongke(dshdtmp1);
        JScrollPane sc= new JScrollPane(tb1);
        sc.setBounds(0, 100, 980, 370);
        sc.setViewportView(tb1);
        pTKthang.add(sc);
        
        
        pBangctdstk = new JPanel();
        pBangctdstk.setBounds(450, 500, 480, 50);
        pBangctdstk.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
        pBangctdstk.setLayout(null);
        pTKthang.add(pBangctdstk);
        
        lbtongtien = new JLabel("Tổng doanh thu:");
        lbtongtien.setFont(new Font("Arial",0,18));
        lbtongtien.setBounds(0, 0, 480, 35);
        lbtongtien.setForeground(Color.BLACK);
        lbtongtien.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK));
        pBangctdstk.add(lbtongtien);
        lbtongtien2 = new JLabel("0 VND");
        lbtongtien2.setFont(new Font("Arial",0,18));
        lbtongtien2.setHorizontalAlignment(JTextField.CENTER);
        lbtongtien2.setBounds(0, 0, 480, 35);
        lbtongtien2.setForeground(Color.BLACK);
        pBangctdstk.add(lbtongtien2);
        
        btThongKethang.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                SimpleDateFormat dateyear = new SimpleDateFormat("yyyy");
                SimpleDateFormat datemonth = new SimpleDateFormat("MM");
                ArrayList<HoaDonDTO> dshdtmp = new ArrayList<>();
                String datetmpyear="";
                String datetmpmonth="";
                if(txtDateTKthang.getDate()==null) {
                    datetmpyear = "";
                    datetmpmonth = "";
                }
                else {
                    datetmpyear = dateyear.format(txtDateTKthang.getDate());
                    datetmpmonth = datemonth.format(txtDateTKthang.getDate());
                }
                if(!datetmpyear.equals("") && !datetmpmonth.equals("")) {
                    
                    HoaDonBUS hd_bus = new HoaDonBUS();
                    HoaDonDTO hd_dto = new HoaDonDTO();
                    dshdtmp=hd_bus.docdshdTKthang(datetmpmonth, datetmpyear);
                    JTable tb1 = Danhsachthongke(dshdtmp);
                    sc.removeAll();
                    sc2 = new JScrollPane(tb1);
                    sc2.setBounds(0, 0, 980, 440);
                    sc.add(sc2);
                    sc.repaint();
                    sc.revalidate();
                    long tongtien=0;
                    for(HoaDonDTO dto: dshdtmp) {
                        tongtien+=dto.getTongPhaiTra();
                    }
                    String tmp = String.valueOf(tongtien);
                    Xulydulieu xl = new Xulydulieu();
                    lbtongtien2.setText(xl.ChuyenKieuTien(tmp) + " VND");
                } else {
                    JOptionPane.showMessageDialog(null, "Chọn tháng-năm để thống kê");
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
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        btExcel2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                try {
                    SimpleDateFormat dateyear = new SimpleDateFormat("yyyy");
                    SimpleDateFormat datemonth = new SimpleDateFormat("MM");
                    ArrayList<HoaDonDTO> dshdtmp = new ArrayList<>();
                    String datetmpyear="";
                    String datetmpmonth="";
                    if(txtDateTKthang.getDate()==null) {
                        datetmpyear = "";
                        datetmpmonth = "";
                    }
                    else {
                        datetmpyear = dateyear.format(txtDateTKthang.getDate());
                        datetmpmonth = datemonth.format(txtDateTKthang.getDate());
                    }
                    if(!datetmpyear.equals("") && !datetmpmonth.equals("")) {
                        HoaDonBUS hd_bus = new HoaDonBUS();
                        HoaDonDTO hd_dto = new HoaDonDTO();
                        dshdtmp=hd_bus.docdshdTKthang(datetmpmonth, datetmpyear);
                        XSSFWorkbook wb=new XSSFWorkbook();
                        XSSFSheet sheet=wb.createSheet("Sheet1");

                        XSSFRow row=null;
                        Cell cell=null;

                        row=sheet.createRow(0);
                        row.setHeight((short) 500);
                        cell=row.createCell(0,CellType.STRING);
                        cell.setCellValue("STT");
                        cell=row.createCell(1,CellType.STRING);
                        cell.setCellValue("Mã hóa đơn");
                        cell=row.createCell(2,CellType.STRING);
                        cell.setCellValue("Mã phiếu đặt phòng");
                        cell=row.createCell(3,CellType.STRING);
                        cell.setCellValue("Mã nhân viên");
                        cell=row.createCell(4,CellType.STRING);
                        cell.setCellValue("Ngày lập hóa đơn");
                        cell=row.createCell(5,CellType.STRING);
                        cell.setCellValue("Tổng tiền phải trả");
                        cell=row.createCell(6,CellType.STRING);

                        int doanhthu=0;
                        for (int i=0;i<dshdtmp.size();i++){
                            HoaDonDTO hd = dshdtmp.get(i);
                            row=sheet.createRow(1+i);
                            row.setHeight((short) 400);
                            row.createCell(0).setCellValue(i+1);
                            row.createCell(1).setCellValue(hd.getMaHD());
                            row.createCell(2).setCellValue(hd.getMaPDP());
                            row.createCell(3).setCellValue(hd.getMaNV());
                            row.createCell(4).setCellValue(hd.getNgayLapHD());
                            row.createCell(5).setCellValue(hd.getTongPhaiTra());
                            doanhthu+=hd.getTongPhaiTra();
                        }
                        row=sheet.createRow(dshdtmp.size()+2);
                        row.setHeight((short) 400);
                        cell=row.createCell(4,CellType.STRING);
                        cell.setCellValue("Doanh thu tháng: ");
                        Xulydulieu xl = new Xulydulieu();
                        row.createCell(5).setCellValue(xl.ChuyenKieuTien(String.valueOf(doanhthu)));

                        File f = new File("D:\\thongkethang.xls");
                        FileOutputStream out= new FileOutputStream(f);
                        wb.write(out);
                        out.flush();
                        out.close();
                        JOptionPane.showMessageDialog(null, "Xuất Excel thành công");
                    } else {
                        JOptionPane.showMessageDialog(null, "Chọn tháng-năm để thống kê");
                    }
                } catch(Exception ex) {
                    ex.printStackTrace();
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
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        return pTKthang;
    }
    
    public JPanel Theonam(int width, int height) {
        
        JPanel pTKnam=new JPanel();
        pTKnam.setLayout(null);
        pTKnam.setBounds(0, 75, width, height);
        pTKnam.setPreferredSize(new Dimension(width,height));
        pTKnam.setBackground(new Color(238,238,238));
        pTKnam.setOpaque(true);
        
        lbngay = new JLabel();
        lbngay.setText("Thống kê theo năm:");
        lbngay.setBounds(10, 30, 175, 20);
        lbngay.setFont(new Font("Times New Roman",0,20));
        lbngay.setOpaque(true);
        pTKnam.add(lbngay);
        txtDateTKnam = new JDateChooser();
        txtDateTKnam.setBounds(190, 30, 150, 25);
        txtDateTKnam.setDateFormatString("yyyy");
        txtDateTKnam.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
        editor = (JTextFieldDateEditor) txtDateTKnam.getDateEditor();
        editor.setEditable(false);
        editor.setBackground(Color.WHITE);
        editor.setFont(new Font("Arial",0,18));
        pTKnam.add(txtDateTKnam);
        ImageIcon iconsearch = new ImageIcon("src/img/search.png");
        JButton btThongKenam = new JButton("Thống kê",iconsearch);
        btThongKenam.setBounds(350, 30, 150,30);
        btThongKenam.setBackground(colorButton);
        btThongKenam.setForeground(Color.WHITE);
        btThongKenam.setBorderPainted(false);
        btThongKenam.setFocusPainted(false);
        pTKnam.add(btThongKenam);
  
        
        ImageIcon iconexcel = new ImageIcon("src/img/excel.png");
        btExcel3 = new JButton("Xuất excel", iconexcel);
        btExcel3.setBounds(800, 30, 150, 35);
        btExcel3.setBackground(colorButton);
        btExcel3.setForeground(Color.WHITE);
        btExcel3.setBorderPainted(false);
        btExcel3.setFocusPainted(false);
        pTKnam.add(btExcel3);
        
        ArrayList<HoaDonDTO> dshdtmp1 = new ArrayList<>();
        JTable tb1 = Danhsachthongke(dshdtmp1);
        JScrollPane sc= new JScrollPane(tb1);
        sc.setBounds(0, 100, 980, 370);
        sc.setViewportView(tb1);
        pTKnam.add(sc);
        
        
        pBangctdstk = new JPanel();
        pBangctdstk.setBounds(450, 500, 480, 50);
        pBangctdstk.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
        pBangctdstk.setLayout(null);
        pTKnam.add(pBangctdstk);
        
        lbtongtien = new JLabel("Tổng doanh thu:");
        lbtongtien.setFont(new Font("Arial",0,18));
        lbtongtien.setBounds(0, 0, 480, 35);
        lbtongtien.setForeground(Color.BLACK);
        lbtongtien.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK));
        pBangctdstk.add(lbtongtien);
        lbtongtien3 = new JLabel("0 VND");
        lbtongtien3.setFont(new Font("Arial",0,18));
        lbtongtien3.setHorizontalAlignment(JTextField.CENTER);
        lbtongtien3.setBounds(0, 0, 480, 35);
        lbtongtien3.setForeground(Color.BLACK);
        pBangctdstk.add(lbtongtien3);
        
        btThongKenam.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                SimpleDateFormat dateyear = new SimpleDateFormat("yyyy");
                ArrayList<HoaDonDTO> dshdtmp = new ArrayList<>();
                String datetmpyear="";
                if(txtDateTKnam.getDate()==null) {
                    datetmpyear = "";
                }
                else {
                    datetmpyear = dateyear.format(txtDateTKnam.getDate());
                }
                if(!datetmpyear.equals("") ) {
                    
                    HoaDonBUS hd_bus = new HoaDonBUS();
                    HoaDonDTO hd_dto = new HoaDonDTO();
                    dshdtmp=hd_bus.docdshdTKnam(datetmpyear);
                    JTable tb1 = Danhsachthongke(dshdtmp);
                    sc.removeAll();
                    sc3 = new JScrollPane(tb1);
                    sc3.setBounds(0, 0, 980, 440);
                    sc.add(sc3);
                    sc.repaint();
                    sc.revalidate();
                    long tongtien=0;
                    for(HoaDonDTO dto: dshdtmp) {
                        tongtien+=dto.getTongPhaiTra();
                    }
                    String tmp = String.valueOf(tongtien);
                    Xulydulieu xl = new Xulydulieu();
                    lbtongtien3.setText(xl.ChuyenKieuTien(tmp) + " VND");
                } else {
                    JOptionPane.showMessageDialog(null, "Chọn năm để thống kê");
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
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        btExcel3.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                try {
                    SimpleDateFormat dateyear = new SimpleDateFormat("yyyy");
                    ArrayList<HoaDonDTO> dshdtmp = new ArrayList<>();
                    String datetmpyear="";
                    if(txtDateTKnam.getDate()==null) {
                        datetmpyear = "";
                    }
                    else {
                        datetmpyear = dateyear.format(txtDateTKnam.getDate());
                    }
                    if(!datetmpyear.equals("")) {
                        HoaDonBUS hd_bus = new HoaDonBUS();
                        HoaDonDTO hd_dto = new HoaDonDTO();
                        dshdtmp=hd_bus.docdshdTKnam(datetmpyear);
                        XSSFWorkbook wb=new XSSFWorkbook();
                        XSSFSheet sheet=wb.createSheet("Sheet2");

                        XSSFRow row=null;
                        Cell cell=null;

                        row=sheet.createRow(0);
                        row.setHeight((short) 500);
                        cell=row.createCell(0,CellType.STRING);
                        cell.setCellValue("STT");
                        cell=row.createCell(1,CellType.STRING);
                        cell.setCellValue("Mã hóa đơn");
                        cell=row.createCell(2,CellType.STRING);
                        cell.setCellValue("Mã phiếu đặt phòng");
                        cell=row.createCell(3,CellType.STRING);
                        cell.setCellValue("Mã nhân viên");
                        cell=row.createCell(4,CellType.STRING);
                        cell.setCellValue("Ngày lập hóa đơn");
                        cell=row.createCell(5,CellType.STRING);
                        cell.setCellValue("Tổng tiền phải trả");
                        cell=row.createCell(6,CellType.STRING);

                        int doanhthu=0;
                        for (int i=0;i<dshdtmp.size();i++){
                            HoaDonDTO hd = dshdtmp.get(i);
                            row=sheet.createRow(1+i);
                            row.setHeight((short) 400);
                            row.createCell(0).setCellValue(i+1);
                            row.createCell(1).setCellValue(hd.getMaHD());
                            row.createCell(2).setCellValue(hd.getMaPDP());
                            row.createCell(3).setCellValue(hd.getMaNV());
                            row.createCell(4).setCellValue(hd.getNgayLapHD());
                            row.createCell(5).setCellValue(hd.getTongPhaiTra());
                            doanhthu+=hd.getTongPhaiTra();
                        }
                        row=sheet.createRow(dshdtmp.size()+2);
                        row.setHeight((short) 400);
                        cell=row.createCell(4,CellType.STRING);
                        cell.setCellValue("Doanh thu năm: ");
                        Xulydulieu xl = new Xulydulieu();
                        row.createCell(5).setCellValue(xl.ChuyenKieuTien(String.valueOf(doanhthu)));

                        File f = new File("D:\\thongkenam.xls");
                        FileOutputStream out= new FileOutputStream(f);
                        wb.write(out);
                        out.flush();
                        out.close();
                        JOptionPane.showMessageDialog(null, "Xuất Excel thành công");
                    } else {
                        JOptionPane.showMessageDialog(null, "Chọn tháng-năm để thống kê");
                    }
                } catch(Exception ex) {
                    ex.printStackTrace();
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
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        return pTKnam;
    }
    
    public JPanel Theoquy(int width, int height) {
        
        JPanel pTKquy=new JPanel();
        pTKquy.setLayout(null);
        pTKquy.setBounds(0, 75, width, height);
        pTKquy.setPreferredSize(new Dimension(width,height));
        pTKquy.setBackground(new Color(238,238,238));
        pTKquy.setOpaque(true);
        
        lbngay = new JLabel();
        lbngay.setText("Thống kê theo quý:");
        lbngay.setBounds(10, 30, 175, 20);
        lbngay.setFont(new Font("Times New Roman",0,20));
        lbngay.setOpaque(true);
        pTKquy.add(lbngay);
        String DsQuy[] = { "Tất cả", "Quý 1", "Quý 2", "Quý 3", "Quý 4" };
        cbxQuy = new JComboBox(DsQuy);
        cbxQuy.setBounds(190, 30, 150, 25);
        cbxQuy.setBackground(Color.WHITE);
        cbxQuy.setFont(new Font("Arial",0,18));
        pTKquy.add(cbxQuy);
        cbxQuy.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            
            }
        });
        
        lbt = new JLabel();
        lbt.setText("&&");
        lbt.setBounds(345, 30, 30,20);
        lbt.setFont(new Font("Times New Roman",0,20));
        lbt.setOpaque(true);
        pTKquy.add(lbt);
        txtDateTKquy = new JDateChooser();
        txtDateTKquy.setBounds(385, 30, 150,25);
        txtDateTKquy.setDateFormatString("yyyy");
        txtDateTKquy.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
        editor = (JTextFieldDateEditor) txtDateTKquy.getDateEditor();
        editor.setEditable(false);
        editor.setBackground(Color.WHITE);
        editor.setFont(new Font("Arial",0,18));
        pTKquy.add(txtDateTKquy);
        
        ImageIcon iconsearch = new ImageIcon("src/img/search.png");
        JButton btThongKequy = new JButton("Thống kê",iconsearch);
        btThongKequy.setBounds(560, 30, 150,30);
        btThongKequy.setBackground(colorButton);
        btThongKequy.setForeground(Color.WHITE);
        btThongKequy.setBorderPainted(false);
        btThongKequy.setFocusPainted(false);
        pTKquy.add(btThongKequy);
  
        
        ImageIcon iconexcel = new ImageIcon("src/img/excel.png");
        btExcel4 = new JButton("Xuất excel", iconexcel);
        btExcel4.setBounds(800, 30, 150, 35);
        btExcel4.setBackground(colorButton);
        btExcel4.setForeground(Color.WHITE);
        btExcel4.setBorderPainted(false);
        btExcel4.setFocusPainted(false);
        pTKquy.add(btExcel4);
        
        ArrayList<HoaDonDTO> dshdtmp1 = new ArrayList<>();
        JTable tb1 = Danhsachthongke(dshdtmp1);
        JScrollPane sc= new JScrollPane(tb1);
        sc.setBounds(0, 100, 980, 370);
        sc.setViewportView(tb1);
        pTKquy.add(sc);
        
        
        pBangctdstk = new JPanel();
        pBangctdstk.setBounds(450, 500, 480, 50);
        pBangctdstk.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
        pBangctdstk.setLayout(null);
        pTKquy.add(pBangctdstk);
        
        lbtongtien = new JLabel("Tổng doanh thu:");
        lbtongtien.setFont(new Font("Arial",0,18));
        lbtongtien.setBounds(0, 0, 480, 35);
        lbtongtien.setForeground(Color.BLACK);
        lbtongtien.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK));
        pBangctdstk.add(lbtongtien);
        lbtongtien4 = new JLabel("0 VND");
        lbtongtien4.setFont(new Font("Arial",0,18));
        lbtongtien4.setHorizontalAlignment(JTextField.CENTER);
        lbtongtien4.setBounds(0, 0, 480, 35);
        lbtongtien4.setForeground(Color.BLACK);
        pBangctdstk.add(lbtongtien4);
        
        btThongKequy.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                SimpleDateFormat dateyear = new SimpleDateFormat("yyyy");
                ArrayList<HoaDonDTO> dshdtmp = new ArrayList<>();
                String datetmpyear="";
                if(txtDateTKquy.getDate()==null) {
                    datetmpyear = "";
                }
                else {
                    datetmpyear = dateyear.format(txtDateTKquy.getDate());
                }
                cbxQuy.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                    }
                });
                int tmp = 0;
                if(cbxQuy.getSelectedItem().equals(DsQuy[0]))
                    tmp = 0;
                else if(cbxQuy.getSelectedItem().equals(DsQuy[1]))
                    tmp = 1;
                else if(cbxQuy.getSelectedItem().equals(DsQuy[2]))
                    tmp = 2;
                else if(cbxQuy.getSelectedItem().equals(DsQuy[3]))
                    tmp = 3;
                else
                    tmp = 4;
                if(!datetmpyear.equals("") ) {
                    
                    HoaDonBUS hd_bus = new HoaDonBUS();
                    HoaDonDTO hd_dto = new HoaDonDTO();
                    dshdtmp=hd_bus.docdshdTKquy(tmp, datetmpyear);
                    JTable tb1 = Danhsachthongke(dshdtmp);
                    sc.removeAll();
                    sc3 = new JScrollPane(tb1);
                    sc3.setBounds(0, 0, 980, 440);
                    sc.add(sc3);
                    sc.repaint();
                    sc.revalidate();
                    long tongtien=0;
                    for(HoaDonDTO dto: dshdtmp) {
                        tongtien+=dto.getTongPhaiTra();
                    }
                    String tmp1 = String.valueOf(tongtien);
                    Xulydulieu xl = new Xulydulieu();
                    lbtongtien4.setText(xl.ChuyenKieuTien(tmp1) + " VND");
                } else {
                    JOptionPane.showMessageDialog(null, "Chọn đầy đủ quý và năm để thống kê");
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
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        btExcel4.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                try {
                    SimpleDateFormat dateyear = new SimpleDateFormat("yyyy");
                    ArrayList<HoaDonDTO> dshdtmp = new ArrayList<>();
                    String datetmpyear="";
                    if(txtDateTKquy.getDate()==null) {
                        datetmpyear = "";
                    }
                    else {
                        datetmpyear = dateyear.format(txtDateTKquy.getDate());
                    }
                    cbxQuy.addItemListener(new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                        }
                    });
                    int tmp = 0;
                    if(cbxQuy.getSelectedItem().equals(DsQuy[0]))
                        tmp = 0;
                    else if(cbxQuy.getSelectedItem().equals(DsQuy[1]))
                        tmp = 1;
                    else if(cbxQuy.getSelectedItem().equals(DsQuy[2]))
                        tmp = 2;
                    else if(cbxQuy.getSelectedItem().equals(DsQuy[3]))
                        tmp = 3;
                    else
                        tmp = 4;
                    if(!datetmpyear.equals("")) {
                        HoaDonBUS hd_bus = new HoaDonBUS();
                        HoaDonDTO hd_dto = new HoaDonDTO();
                        dshdtmp=hd_bus.docdshdTKquy(tmp, datetmpyear);
                        XSSFWorkbook wb=new XSSFWorkbook();
                        XSSFSheet sheet=wb.createSheet("Sheet3");

                        XSSFRow row=null;
                        Cell cell=null;

                        row=sheet.createRow(0);
                        row.setHeight((short) 500);
                        cell=row.createCell(0,CellType.STRING);
                        cell.setCellValue("STT");
                        cell=row.createCell(1,CellType.STRING);
                        cell.setCellValue("Mã hóa đơn");
                        cell=row.createCell(2,CellType.STRING);
                        cell.setCellValue("Mã phiếu đặt phòng");
                        cell=row.createCell(3,CellType.STRING);
                        cell.setCellValue("Mã nhân viên");
                        cell=row.createCell(4,CellType.STRING);
                        cell.setCellValue("Ngày lập hóa đơn");
                        cell=row.createCell(5,CellType.STRING);
                        cell.setCellValue("Tổng tiền phải trả");
                        cell=row.createCell(6,CellType.STRING);

                        int doanhthu=0;
                        for (int i=0;i<dshdtmp.size();i++){
                            HoaDonDTO hd = dshdtmp.get(i);
                            row=sheet.createRow(1+i);
                            row.setHeight((short) 400);
                            row.createCell(0).setCellValue(i+1);
                            row.createCell(1).setCellValue(hd.getMaHD());
                            row.createCell(2).setCellValue(hd.getMaPDP());
                            row.createCell(3).setCellValue(hd.getMaNV());
                            row.createCell(4).setCellValue(hd.getNgayLapHD());
                            row.createCell(5).setCellValue(hd.getTongPhaiTra());
                            doanhthu+=hd.getTongPhaiTra();
                        }
                        row=sheet.createRow(dshdtmp.size()+2);
                        row.setHeight((short) 400);
                        cell=row.createCell(4,CellType.STRING);
                        cell.setCellValue("Doanh thu quý: ");
                        Xulydulieu xl = new Xulydulieu();
                        row.createCell(5).setCellValue(xl.ChuyenKieuTien(String.valueOf(doanhthu)));

                        File f = new File("D:\\thongkequy.xls");
                        FileOutputStream out= new FileOutputStream(f);
                        wb.write(out);
                        out.flush();
                        out.close();
                        JOptionPane.showMessageDialog(null, "Xuất Excel thành công");
                    } else {
                        JOptionPane.showMessageDialog(null, "Chọn tháng-năm để thống kê");
                    }
                } catch(Exception ex) {
                    ex.printStackTrace();
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
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        return pTKquy;
    }
    
    public JPanel Theokhoangthoigian(int width, int height) {
        
        JPanel pTKkhoangthoigian=new JPanel();
        pTKkhoangthoigian.setLayout(null);
        pTKkhoangthoigian.setBounds(0, 75, width, height);
        pTKkhoangthoigian.setPreferredSize(new Dimension(width,height));
        pTKkhoangthoigian.setBackground(new Color(238,238,238));
        pTKkhoangthoigian.setOpaque(true);
        
        lbngay = new JLabel();
        lbngay.setText("Khoảng thời gian:");
        lbngay.setBounds(10, 30, 175, 20);
        lbngay.setFont(new Font("Times New Roman",0,20));
        lbngay.setOpaque(true);
        pTKkhoangthoigian.add(lbngay);
        txtDateTKkhoangthoigian1 = new JDateChooser();
        txtDateTKkhoangthoigian1.setBounds(190, 30, 150, 25);
        txtDateTKkhoangthoigian1.setDateFormatString("dd-MM-yyyy");
        txtDateTKkhoangthoigian1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
        editor = (JTextFieldDateEditor) txtDateTKkhoangthoigian1.getDateEditor();
        editor.setEditable(false);
        editor.setBackground(Color.WHITE);
        editor.setFont(new Font("Arial",0,18));
        pTKkhoangthoigian.add(txtDateTKkhoangthoigian1);
        
        lbt = new JLabel();
        lbt.setText("=>");
        lbt.setBounds(350, 30, 30,20);
        lbt.setFont(new Font("Times New Roman",0,20));
        lbt.setOpaque(true);
        pTKkhoangthoigian.add(lbt);
        txtDateTKkhoangthoigian2 = new JDateChooser();
        txtDateTKkhoangthoigian2.setBounds(385, 30, 150,25);
        txtDateTKkhoangthoigian2.setDateFormatString("dd-MM-yyyy");
        txtDateTKkhoangthoigian2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
        editor = (JTextFieldDateEditor) txtDateTKkhoangthoigian2.getDateEditor();
        editor.setEditable(false);
        editor.setBackground(Color.WHITE);
        editor.setFont(new Font("Arial",0,18));
        pTKkhoangthoigian.add(txtDateTKkhoangthoigian2);
        
        ImageIcon iconsearch = new ImageIcon("src/img/search.png");
        JButton btThongKektg = new JButton("Thống kê",iconsearch);
        btThongKektg.setBounds(560, 30, 150,30);
        btThongKektg.setBackground(colorButton);
        btThongKektg.setForeground(Color.WHITE);
        btThongKektg.setBorderPainted(false);
        btThongKektg.setFocusPainted(false);
        pTKkhoangthoigian.add(btThongKektg);
  
        
        ImageIcon iconexcel = new ImageIcon("src/img/excel.png");
        btExcel5 = new JButton("Xuất excel", iconexcel);
        btExcel5.setBounds(800, 30, 150, 35);
        btExcel5.setBackground(colorButton);
        btExcel5.setForeground(Color.WHITE);
        btExcel5.setBorderPainted(false);
        btExcel5.setFocusPainted(false);
        pTKkhoangthoigian.add(btExcel5);
        
        ArrayList<HoaDonDTO> dshdtmp1 = new ArrayList<>();
        JTable tb1 = Danhsachthongke(dshdtmp1);
        JScrollPane sc= new JScrollPane(tb1);
        sc.setBounds(0, 100, 980, 370);
        sc.setViewportView(tb1);
        pTKkhoangthoigian.add(sc);
        
        
        pBangctdstk = new JPanel();
        pBangctdstk.setBounds(450, 500, 480, 50);
        pBangctdstk.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
        pBangctdstk.setLayout(null);
        pTKkhoangthoigian.add(pBangctdstk);
        
        lbtongtien = new JLabel("Tổng doanh thu:");
        lbtongtien.setFont(new Font("Arial",0,18));
        lbtongtien.setBounds(0, 0, 480, 35);
        lbtongtien.setForeground(Color.BLACK);
        lbtongtien.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK));
        pBangctdstk.add(lbtongtien);
        lbtongtien5 = new JLabel("0 VND");
        lbtongtien5.setFont(new Font("Arial",0,18));
        lbtongtien5.setHorizontalAlignment(JTextField.CENTER);
        lbtongtien5.setBounds(0, 0, 480, 35);
        lbtongtien5.setForeground(Color.BLACK);
        pBangctdstk.add(lbtongtien5);
        
        btThongKektg.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                SimpleDateFormat datetu = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat dateden = new SimpleDateFormat("yyyy-MM-dd");
                ArrayList<HoaDonDTO> dshdtmp = new ArrayList<>();
                String datetmptu="";
                String datetmpden="";
                int input = 9;
                if(txtDateTKkhoangthoigian1.getDate()==null || txtDateTKkhoangthoigian2.getDate()==null) {
                    datetmptu = "";
                    datetmpden="";
                }
                else {
                    datetmptu = datetu.format(txtDateTKkhoangthoigian1.getDate());
                    datetmpden = dateden.format(txtDateTKkhoangthoigian2.getDate());
                    Date date1;
                    Date date2;
                    try {
                        date1 = datetu.parse(datetmptu);
                        date2 = dateden.parse(datetmpden);
                        input = date1.compareTo(date2);
                    } catch (ParseException ex) {
                        Logger.getLogger(ThongKeGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                
                
                if(!datetmptu.equals("") && !datetmpden.equals("")) {
                    
                    if(input<=0) {
                        HoaDonBUS hd_bus = new HoaDonBUS();
                        HoaDonDTO hd_dto = new HoaDonDTO();
                        dshdtmp=hd_bus.docdshdTKkhoangthoigian(datetmptu, datetmpden);
                        JTable tb1 = Danhsachthongke(dshdtmp);
                        sc.removeAll();
                        sc5 = new JScrollPane(tb1);
                        sc5.setBounds(0, 0, 980, 440);
                        sc.add(sc5);
                        sc.repaint();
                        sc.revalidate();
                        long tongtien=0;
                        for(HoaDonDTO dto: dshdtmp) {
                            tongtien+=dto.getTongPhaiTra();
                        }
                        String tmp1 = String.valueOf(tongtien);
                        Xulydulieu xl = new Xulydulieu();
                        lbtongtien5.setText(xl.ChuyenKieuTien(tmp1) + " VND");
                    } else {
                        JOptionPane.showMessageDialog(null, "ngày-tháng-năm bắt đầu phải nhỏ hơn ngày-tháng-năm kết thúc");
                    }
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Chọn đầy đủ ngày-tháng-năm bắt đầu và kết thúc để thống kê");
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
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        btExcel5.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                try {
                    SimpleDateFormat datetu = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat dateden = new SimpleDateFormat("yyyy-MM-dd");
                    ArrayList<HoaDonDTO> dshdtmp = new ArrayList<>();
                    String datetmptu="";
                    String datetmpden="";
                    int input = 9;
                    if(txtDateTKkhoangthoigian1.getDate()==null || txtDateTKkhoangthoigian2.getDate()==null) {
                        datetmptu = "";
                        datetmpden="";
                    }
                    else {
                        datetmptu = datetu.format(txtDateTKkhoangthoigian1.getDate());
                        datetmpden = dateden.format(txtDateTKkhoangthoigian2.getDate());
                        Date date1;
                        Date date2;
                        try {
                            date1 = datetu.parse(datetmptu);
                            date2 = dateden.parse(datetmpden);
                            input = date1.compareTo(date2);
                        } catch (ParseException ex) {
                            Logger.getLogger(ThongKeGUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }



                    if(!datetmptu.equals("") && !datetmpden.equals("")) {
                    
                        if(input<=0) {
                            HoaDonBUS hd_bus = new HoaDonBUS();
                            HoaDonDTO hd_dto = new HoaDonDTO();
                            dshdtmp=hd_bus.docdshdTKkhoangthoigian(datetmptu, datetmpden);
                            XSSFWorkbook wb=new XSSFWorkbook();
                            XSSFSheet sheet=wb.createSheet("Sheet4");

                            XSSFRow row=null;
                            Cell cell=null;

                            row=sheet.createRow(0);
                            row.setHeight((short) 500);
                            cell=row.createCell(0,CellType.STRING);
                            cell.setCellValue("STT");
                            cell=row.createCell(1,CellType.STRING);
                            cell.setCellValue("Mã hóa đơn");
                            cell=row.createCell(2,CellType.STRING);
                            cell.setCellValue("Mã phiếu đặt phòng");
                            cell=row.createCell(3,CellType.STRING);
                            cell.setCellValue("Mã nhân viên");
                            cell=row.createCell(4,CellType.STRING);
                            cell.setCellValue("Ngày lập hóa đơn");
                            cell=row.createCell(5,CellType.STRING);
                            cell.setCellValue("Tổng tiền phải trả");
                            cell=row.createCell(6,CellType.STRING);

                            int doanhthu=0;
                            for (int i=0;i<dshdtmp.size();i++){
                                HoaDonDTO hd = dshdtmp.get(i);
                                row=sheet.createRow(1+i);
                                row.setHeight((short) 400);
                                row.createCell(0).setCellValue(i+1);
                                row.createCell(1).setCellValue(hd.getMaHD());
                                row.createCell(2).setCellValue(hd.getMaPDP());
                                row.createCell(3).setCellValue(hd.getMaNV());
                                row.createCell(4).setCellValue(hd.getNgayLapHD());
                                row.createCell(5).setCellValue(hd.getTongPhaiTra());
                                doanhthu+=hd.getTongPhaiTra();
                            }
                            row=sheet.createRow(dshdtmp.size()+2);
                            row.setHeight((short) 400);
                            cell=row.createCell(4,CellType.STRING);
                            cell.setCellValue("Doanh thu: ");
                            Xulydulieu xl = new Xulydulieu();
                            row.createCell(5).setCellValue(xl.ChuyenKieuTien(String.valueOf(doanhthu)));

                            File f = new File("D:\\thongkekhoangthoigian.xls");
                            FileOutputStream out= new FileOutputStream(f);
                            wb.write(out);
                            out.flush();
                            out.close();
                            JOptionPane.showMessageDialog(null, "Xuất Excel thành công");
                        } else {
                            JOptionPane.showMessageDialog(null, "ngày-tháng-năm bắt đầu phải nhỏ hơn ngày-tháng-năm kết thúc");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Chọn tháng-năm để thống kê");
                    }
                } catch(Exception ex) {
                    ex.printStackTrace();
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
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        return pTKkhoangthoigian;
    }
}
