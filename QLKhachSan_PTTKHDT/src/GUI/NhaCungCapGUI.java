/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.HangHoaBUS;
import BUS.NhaCungCapBUS;
import BUS.Xulydulieu;
import DTO.HangHoaDTO;
import DTO.NhaCungCapDTO;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author minhd
 */
public class NhaCungCapGUI {
    protected JFrame frame_chitiet;
    protected JScrollPane sc,sc1;
    protected JPanel pNhaCungCap;
    protected JPanel pTimKiem;
    protected JPanel pBangDS;
    protected JPanel pBangCT;
    protected JTable tbdsncc;
    protected JLabel lbTieude,lbTenNhaCC;
    protected JLabel lb_ma,lb_ten,lb_diachi,lb_sdt;
    protected JTextField txtTenNhacungcap,txt_kiemtra = null;
    protected JTextField txt_ma,txt_ten,txt_diachi,txt_sdt;
    protected JComboBox cbxNhomHangHoa,cbxTimKiem;
    protected JButton btTimKiem,btXemChiTiet,btThem,btSua,btXoa,btCapNhat;
    protected DefaultTableModel model_dsncc;
    Font font_lb = new Font("Times New Roman",0,18);
    Font font_Button = new Font("Times New Roman",0,17);
    Font font_lbThongtinNCC = new Font("Times New Roman",Font.BOLD,28);
    protected Color colorButton = new Color(36,36,36);
    protected Color colorLaybel = new Color(238, 238, 238);
    protected Color colorLaybel1 = new Color(246, 246, 246);
    Font font_lbTenHH= new Font("Times New Roman",Font.BOLD,22);
    Font font_tk = new Font("Times New Roman",0,23); 
    
    protected JLabel lbThongtinNCC,lb_CTten,lb_CTma,lb_CTsdt,lb_CTdiachi;
    Font font_lbCTThongtinHH = new Font("Times New Roman",Font.BOLD,28);
    protected JTextField txt_CTma = null,txt_CTten,txt_CTsdt;
    protected JButton btThoat_CT,btThem_CT,btSua_CT;
    protected JTextArea txt_CTdiachi;
    Font font_CT = new Font("Times New Roman",Font.BOLD,20);
    Font font_CT_txt = new Font("Times New Roman",Font.BOLD,18);
    public NhaCungCapGUI(){
        
    }
    public JPanel KhoiTaoPanel(int width, int height) {
        pNhaCungCap=new JPanel();
        pNhaCungCap.setLayout(null);
        pNhaCungCap.setBackground(Color.WHITE);
        pNhaCungCap.setBounds(0, 0, width, height);
        pNhaCungCap.setPreferredSize(new Dimension(width,height));
        pNhaCungCap.setOpaque(true);
        
        lbTieude=new JLabel();
        lbTieude.setOpaque(true);
        lbTieude.setText("Danh sách nhà cung cấp");
        lbTieude.setFont(font_lbCTThongtinHH);
        lbTieude.setBounds(0, 0, 1000, 50);
        lbTieude.setForeground(Color.BLACK);
        lbTieude.setHorizontalAlignment(JLabel.CENTER);
        pTimKiem = TimKiem(1000, 115);
        pNhaCungCap.add(lbTieude);
        pNhaCungCap.add(pTimKiem);
        
        pBangDS = BangDSNhaCungCap(1000, 485);
        pNhaCungCap.add(pBangDS);
        return pNhaCungCap;
    }
     //====================================================  Jpanel các loại tìm kiếm ===============================================//
    public JPanel TimKiem(int width, int height){
        pTimKiem=new JPanel();
        pTimKiem.setLayout(null);
        pTimKiem.setBounds(0, 50, width, height);
        pTimKiem.setPreferredSize(new Dimension(width,height));
        pTimKiem.setOpaque(true);
        //pTimKiem.setBackground(Color.red);
        //=========================== LB Tên hàng hóa ============================//
        lbTenNhaCC = new JLabel("Tên nhà cung cấp");
        lbTenNhaCC.setFont(font_lbTenHH);
        lbTenNhaCC.setBounds(30, 25, 180, 35);
        lbTenNhaCC.setOpaque(true);
        pTimKiem.add(lbTenNhaCC);
         //=========================== TXT Tên hàng hóa ============================//
        txtTenNhacungcap = new JTextField();
        txtTenNhacungcap.setBounds(220,25,450,35);
        txtTenNhacungcap.setFont(font_tk);
        txtTenNhacungcap.setOpaque(true);
        txtTenNhacungcap.setBackground(colorLaybel1);
        txtTenNhacungcap.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, colorButton));
        pTimKiem.add(txtTenNhacungcap);
        
        
        //===========================  BT Tìm kiếm ============================//
        ImageIcon iconsearch = new ImageIcon("src/img/search.png");
        btTimKiem = new JButton("Tìm kiếm",iconsearch);
        btTimKiem.setBounds(700, 25, 140, 35);
        btTimKiem.setBackground(colorButton);
        btTimKiem.setFont(font_Button);
        btTimKiem.setForeground(Color.WHITE);
        btTimKiem.setBorderPainted(false);
        btTimKiem.setFocusPainted(false);
        pTimKiem.add(btTimKiem);
        
        btTimKiem.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent arg0) {
                NhaCungCapBUS nccBUS = new NhaCungCapBUS();
                if(nccBUS.KtradulieuTimKiem(txtTenNhacungcap.getText())==1){
                   ArrayList<NhaCungCapDTO> ds = nccBUS.Timkiemtheo_TenHH(txtTenNhacungcap.getText());
                      sc.removeAll();
                      JTable tb =Table_DSNCC(ds);
                      sc1 = new JScrollPane(tb);
                      sc1.setBounds(0, 0, 980, 420);
                      sc.add(sc1);
                      sc.repaint();
                      sc.revalidate();
                      pBangDS.add(sc);
               }
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
               // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
               // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btTimKiem.setBackground(Color.DARK_GRAY);
                btTimKiem.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
               btTimKiem.setBackground(colorButton);
            }
        
        });
        
        //===========================  BT Xem chi tiết ============================//
        ImageIcon iconxemchitiet = new ImageIcon("src/img/eye.png");
        btXemChiTiet = new JButton("Xem chi tiết",iconxemchitiet);
        btXemChiTiet.setBounds(535, 75, 160, 35);
        btXemChiTiet.setBackground(colorButton);
        btXemChiTiet.setForeground(Color.WHITE);
        btXemChiTiet.setFont(font_Button);
        btXemChiTiet.setBorderPainted(false);
        btXemChiTiet.setFocusPainted(false);
        pTimKiem.add(btXemChiTiet);
        
        btXemChiTiet.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int i = tbdsncc.getSelectedRow();
                if(i < 0){
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp cần xem thông tin trên bảng rồi hãy nhấn nút này !!!\n Thank you .");
                }
                else {
                Buttonxemchitiet();
                NhaCungCapBUS ncc_BUS = new NhaCungCapBUS();
                NhaCungCapDTO ncc_DTO = new NhaCungCapDTO();
                String hung =tbdsncc.getModel().getValueAt(i, 1).toString();
                ncc_DTO = ncc_BUS.LayThongtin1NCC(hung);
                txt_CTma.setText(ncc_DTO.getMaNCC());
                txt_CTten.setText(ncc_DTO.getTenNCC());
                txt_CTsdt.setText(ncc_DTO.getSdt());
                txt_CTdiachi.setText(ncc_DTO.getDiaChi());
            }
            }
            @Override
            public void mousePressed(MouseEvent arg0) {
                
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btXemChiTiet.setBackground(Color.DARK_GRAY);
                btXemChiTiet.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
               btXemChiTiet.setBackground(colorButton);
            }
        
        });
        
        //===========================  BT Thêm ============================//
        ImageIcon iconthem = new ImageIcon("src/img/add.png");
        btThem = new JButton("Thêm",iconthem);
        btThem.setBounds(700, 75, 100, 35);
        btThem.setFont(font_Button);
        btThem.setBackground(colorButton);
        btThem.setForeground(Color.WHITE);
        btThem.setBorderPainted(false);
        btThem.setFocusPainted(false);
        pTimKiem.add(btThem);
        
        btThem.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent arg0) {
                Xulydulieu xldl = new Xulydulieu();
                NhaCungCapBUS ncc_Bus= new NhaCungCapBUS();
                int sl= ncc_Bus.LayMaCuoiDS();
               Buttonxemchitiet();
               TrangThaiTxt_Them();
               txt_CTma.setText(xldl.TaoMaMoi("NCC",sl+1));
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btThem.setBackground(Color.DARK_GRAY);
                btThem.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
               btThem.setBackground(colorButton);
            }
        
        });
        //===========================  BT Sửa ============================//
        ImageIcon iconsua = new ImageIcon("src/img/sua.png");
        btSua = new JButton("Sửa",iconsua);
        btSua.setBounds(805, 75, 90, 35);
        btSua.setFont(font_Button);
        btSua.setBackground(colorButton);
        btSua.setForeground(Color.WHITE);
        btSua.setBorderPainted(false);
        btSua.setFocusPainted(false);
        pTimKiem.add(btSua);
        
        btSua.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent arg0) {
              int i = tbdsncc.getSelectedRow();
                if(i < 0){
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp cần xem thông tin trên bảng rồi hãy nhấn nút này !!!\n Thank you .");
                }
                else {
                Buttonxemchitiet();
                TrangThaiTxt_Sua();
                NhaCungCapBUS ncc_BUS = new NhaCungCapBUS();
                NhaCungCapDTO ncc_DTO = new NhaCungCapDTO();
                String hung =tbdsncc.getModel().getValueAt(i, 1).toString();
                ncc_DTO = ncc_BUS.LayThongtin1NCC(hung);
                txt_CTma.setText(ncc_DTO.getMaNCC());
                txt_CTten.setText(ncc_DTO.getTenNCC());
                txt_CTsdt.setText(ncc_DTO.getSdt());
                txt_CTdiachi.setText(ncc_DTO.getDiaChi());
            }
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
               // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
              //  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btSua.setBackground(Color.DARK_GRAY);
                btSua.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
               btSua.setBackground(colorButton);
            }
        
        });
        //===========================  BT Xóa ============================//
        ImageIcon iconxoa = new ImageIcon("src/img/Delete.png");
        btXoa = new JButton("Xóa",iconxoa);
        btXoa.setBounds(900, 75, 90, 35);
        btXoa.setFont(font_Button);
        btXoa.setBackground(colorButton);
        btXoa.setForeground(Color.WHITE);
        btXoa.setBorderPainted(false);
        btXoa.setFocusPainted(false);
        pTimKiem.add(btXoa);
        
        btXoa.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent arg0) {
               int removeIndex = tbdsncc.getSelectedRow();
                   if(removeIndex == -1){
                   JOptionPane.showMessageDialog(null, "Hãy chọn 1 dòng rồi ấn nút xóa !!!");
                    }
                   else if(NhaCungCapBUS.dsncc.size()==0){
                   JOptionPane.showMessageDialog(null, "Không có thông tin để xóa !!!");
                    }
                    else {
                       NhaCungCapDTO ncc = new NhaCungCapDTO();
                       NhaCungCapBUS ncc2 = new NhaCungCapBUS();
                       String hung = tbdsncc.getModel().getValueAt(removeIndex, 1).toString();
                       ncc = ncc2.LayThongtin1NCC(hung);
                       int input=JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa", "Xóa nhà cung cấp", JOptionPane.YES_NO_OPTION);   
                       if(input==0){
                       if (ncc2.xoaNCC(ncc)==1){
                           JOptionPane.showMessageDialog(null, "Xóa thành công !!!");
                       }
                       else
                       JOptionPane.showMessageDialog(null, "Xóa thất bại !!!");
            }}
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
               btXoa.setBackground(colorButton);
            }
        
        });
        
        return pTimKiem;
    }
     //====================================================  Jpanel table + chi tiết của table ===============================================//
    public JPanel BangDSNhaCungCap(int width, int height) {
        NhaCungCapBUS ncc_BUS = new NhaCungCapBUS();
        pBangDS=new JPanel();
        pBangDS.setLayout(null);
        pBangDS.setBounds(0, 165, width, height);
        pBangDS.setPreferredSize(new Dimension(width,height));
        pBangDS.setOpaque(true);
        //================  Hiện bảng ================//
        if(NhaCungCapBUS.dsncc ==null){
            ncc_BUS.docdsncc();
        }    
        JTable tablencc = Table_DSNCC(NhaCungCapBUS.dsncc);
        sc = new JScrollPane(tablencc);
        sc.setBounds(10, 10, 980, 420);
        pBangDS.add(sc);

        //===========================  BT Cập nhật (Reset) ============================//
        ImageIcon iconCapNhat = new ImageIcon("src/img/update.png");
        btCapNhat = new JButton("Cập nhật",iconCapNhat);
        btCapNhat.setBounds(20, 440, 160, 35);
        btCapNhat.setFont(font_Button);
        btCapNhat.setBackground(colorButton);
        btCapNhat.setForeground(Color.WHITE);
        btCapNhat.setBorderPainted(false);
        btCapNhat.setFocusPainted(false);
        pBangDS.add(btCapNhat);
        
        btCapNhat.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent arg0) {
              sc.removeAll();
              txtTenNhacungcap.setText(null);
                JTable tb =Table_DSNCC(NhaCungCapBUS.dsncc);
                sc1 = new JScrollPane(tb);
                sc1.setBounds(0, 0, 980, 420);
                sc.add(sc1);
                sc.repaint();
                sc.revalidate();
                pBangDS.add(sc);
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btCapNhat.setBackground(Color.DARK_GRAY);
                btCapNhat.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
               btCapNhat.setBackground(colorButton);
            }
        
        });
        
        
        return pBangDS;
    }
    
    //=========================================  JTable danh sách hàng hóa  ===========================================//
    public JTable Table_DSNCC(ArrayList<NhaCungCapDTO> dsncc){
        tbdsncc=new JTable();
        int stt=1;
        model_dsncc = new DefaultTableModel();
        tbdsncc.getTableHeader().setBackground(colorButton);
        tbdsncc.getTableHeader().setForeground(Color.WHITE);
        tbdsncc.getTableHeader().setFont(new Font("Times New Roman",0,18));
        tbdsncc.getTableHeader().setPreferredSize(new Dimension(0,30));
        tbdsncc.setFont(new Font("Times New Roman",0,18));
        tbdsncc.setForeground(Color.BLACK);
        tbdsncc.setRowHeight(25);
        String [] header={"STT","Mã NCC","Tên NCC", "SĐT", "Địa chỉ"};
                if(model_dsncc.getRowCount()==0){
                   model_dsncc=new DefaultTableModel(header,0);
                }
        for(NhaCungCapDTO ncc : dsncc){
                Vector row=new Vector();
                        row.add(stt);
                        row.add(ncc.getMaNCC());
                        row.add(ncc.getTenNCC());
                        row.add(ncc.getSdt());
                        row.add(ncc.getDiaChi());
                        if(ncc.getTinhTrang()==1){
                        model_dsncc.addRow(row);
                        }
                        stt= stt + 1;
           }
        tbdsncc.setModel(model_dsncc);
        tbdsncc.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tbdsncc.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbdsncc.getColumnModel().getColumn(1).setPreferredWidth(150);
        tbdsncc.getColumnModel().getColumn(2).setPreferredWidth(200);
        tbdsncc.getColumnModel().getColumn(3).setPreferredWidth(170);
        tbdsncc.getColumnModel().getColumn(4).setPreferredWidth(407);
        

        //        //--------------------Căn giữa trong bảng-----------------------//
        DefaultTableCellRenderer center=new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        tbdsncc.setDefaultRenderer(tbdsncc.getColumnClass(0), center);
        tbdsncc.updateUI();
//        //--------------------------------------------------------------//
        return tbdsncc;
    }
   
    public void Buttonxemchitiet(){
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        txt_CTdiachi = new javax.swing.JTextArea();
        frame_chitiet = new JFrame();
        frame_chitiet.setSize(600, 420);
        frame_chitiet.setLayout(null);
        frame_chitiet.setLocation(550, 220);
        
        lbThongtinNCC = new JLabel("Thông tin nhà cung cấp");
        lbThongtinNCC.setFont(font_lbThongtinNCC);
        lbThongtinNCC.setOpaque(true);
        lbThongtinNCC.setBackground(colorButton);
        lbThongtinNCC.setForeground(Color.WHITE);
        lbThongtinNCC.setBounds(0, 0, 600, 50);
        lbThongtinNCC.setHorizontalAlignment(JLabel.CENTER);
        frame_chitiet.add(lbThongtinNCC);
        
        lb_CTma = new JLabel("Mã ");
        lb_CTma.setFont(font_CT);
        lb_CTma.setBounds(40, 70, 80, 30);
        frame_chitiet.add(lb_CTma);
        
        txt_CTma = new JTextField();
        txt_CTma.setBounds(90, 70,120, 25);
        txt_CTma.setFont(font_CT_txt);
        txt_CTma.setForeground(Color.RED);
        txt_CTma.setHorizontalAlignment(JTextField.CENTER);
        txt_CTma.setBorder(BorderFactory.createLoweredBevelBorder());
        txt_CTma.setEditable(false);
        frame_chitiet.add(txt_CTma);
        
        lbTenNhaCC = new JLabel("Tên nhà cung cấp ");
        lbTenNhaCC.setFont(font_CT);
        lbTenNhaCC.setOpaque(true);
        lbTenNhaCC.setBounds(40, 115, 170, 30);
        frame_chitiet.add(lbTenNhaCC);
        
        txt_CTten = new JTextField();
        txt_CTten.setBounds(230, 115, 280, 25);
        txt_CTten.setFont(font_CT_txt);
        txt_CTten.setHorizontalAlignment(JTextField.CENTER);
        txt_CTten.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorButton));
        txt_CTten.setEditable(false);
        frame_chitiet.add(txt_CTten);
        
        lb_CTsdt = new JLabel("SĐT nhà cung cấp ");
        lb_CTsdt.setFont(font_CT);
        lb_CTsdt.setOpaque(true);
        lb_CTsdt.setBounds(40, 160, 170, 30);
        frame_chitiet.add(lb_CTsdt);
        
        txt_CTsdt = new JTextField();
        txt_CTsdt.setBounds(230, 161, 280, 25);
        txt_CTsdt.setFont(font_CT_txt);
        txt_CTsdt.setHorizontalAlignment(JTextField.CENTER);
        txt_CTsdt.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorButton));
        txt_CTsdt.setEditable(false);
        frame_chitiet.add(txt_CTsdt);
        
        lb_CTdiachi = new JLabel("Địa chỉ ");
        lb_CTdiachi.setFont(font_CT);
        lb_CTdiachi.setOpaque(true);
        lb_CTdiachi.setBounds(40, 205, 170, 30);
        frame_chitiet.add(lb_CTdiachi);
        
        txt_CTdiachi.setFont(font_CT_txt);
        txt_CTdiachi.setOpaque(true);
        txt_CTdiachi.setBackground(colorLaybel);
        txt_CTdiachi.setEditable(false);
        txt_CTdiachi.setLineWrap(true); //thiết lập tự động xuống dòng => Thanh cuộn ngang sẽ không bao giờ xuất hiện nhưng từ bị ngắt.
        txt_CTdiachi.setWrapStyleWord(true);//thiết lập khi hết dòng sẽ ngắt từ, không phải ngắt ký tự như chỉ dùng setLineWrap(true);
        jScrollPane1.setViewportView(txt_CTdiachi);
        jScrollPane1.setBounds(230, 206, 290, 100);
        frame_chitiet.add(jScrollPane1);
        
        btThoat_CT = new JButton("Thoát");
        btThoat_CT.setBounds(250, 330, 120, 35);
        btThoat_CT.setFont(font_Button);
        btThoat_CT.setBackground(colorButton);
        btThoat_CT.setForeground(Color.WHITE);
        btThoat_CT.setBorderPainted(false);
        btThoat_CT.setFocusPainted(false);
        frame_chitiet.add(btThoat_CT);
        btThoat_CT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                  frame_chitiet.setVisible(false);
            }
             @Override
            public void mouseEntered(MouseEvent arg0) {
                btThoat_CT.setBackground(Color.DARK_GRAY);
                btThoat_CT.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
               btThoat_CT.setBackground(colorButton);
            }
        });
        
        frame_chitiet.setVisible(true);
    }
   
public void TrangThaiTxt_Them(){
    txt_CTten.setEditable(true);
    txt_CTsdt.setEditable(true);
    txt_CTdiachi.setBackground(Color.white);
    txt_CTdiachi.setEditable(true);
    txt_CTten.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colorButton));
    txt_CTsdt.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colorButton));
    
    btThem_CT = new JButton("Thêm");
    btThem_CT.setBounds(170, 330, 120, 35);
    btThem_CT.setFont(font_Button);
    btThem_CT.setBackground(colorButton);
    btThem_CT.setForeground(Color.WHITE);
    btThem_CT.setBorderPainted(false);
    btThem_CT.setFocusPainted(false);
    frame_chitiet.add(btThem_CT);
    btThem_CT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                  NhaCungCapDTO themNCC = new NhaCungCapDTO();
                   NhaCungCapBUS themNCCBUS = new  NhaCungCapBUS();
                  Xulydulieu xldl = new Xulydulieu();
                  themNCC.setMaNCC(txt_CTma.getText());
                  themNCC.setTenNCC(txt_CTten.getText());
                  themNCC.setDiaChi(txt_CTdiachi.getText());
                  themNCC.setTinhTrang(1);
                  if(txt_CTsdt.getText().equals("")){
                      JOptionPane.showMessageDialog(frame_chitiet, "Chưa nhập số điện thoại");
                  }
                  else {
                  if(xldl.KtraSĐT(txt_CTsdt.getText())==true){
                      themNCC.setSdt(txt_CTsdt.getText());
                  if(themNCCBUS.KtradulieuNCC(themNCC)==1){
                      if(themNCCBUS.themNCC(themNCC)==1){
                          JOptionPane.showMessageDialog(frame_chitiet, "Thêm thành công");
                          frame_chitiet.setVisible(false);
                      }
                      else
                          JOptionPane.showMessageDialog(frame_chitiet, "Thêm thất bại");
                  }
            }}} 
             @Override
            public void mouseEntered(MouseEvent arg0) {
                btThem_CT.setBackground(Color.DARK_GRAY);
                btThem_CT.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
               btThem_CT.setBackground(colorButton);
            }
        });
    btThoat_CT.setBounds(310, 330, 120, 35);
}
    
public void TrangThaiTxt_Sua(){
    txt_CTten.setEditable(true);
    txt_CTsdt.setEditable(true);
    txt_CTdiachi.setBackground(Color.white);
    txt_CTdiachi.setEditable(true);
    txt_CTten.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colorButton));
    txt_CTsdt.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colorButton));
    
    btSua_CT = new JButton("Lưu");
    btSua_CT.setBounds(170, 330, 120, 35);
    btSua_CT.setFont(font_Button);
    btSua_CT.setBackground(colorButton);
    btSua_CT.setForeground(Color.WHITE);
    btSua_CT.setBorderPainted(false);
    btSua_CT.setFocusPainted(false);
    frame_chitiet.add(btSua_CT);
    btSua_CT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                  NhaCungCapDTO suaNCC = new NhaCungCapDTO();
                   NhaCungCapBUS suaNCCBUS = new  NhaCungCapBUS();
                  Xulydulieu xldl = new Xulydulieu();
                  suaNCC.setMaNCC(txt_CTma.getText());
                  suaNCC.setTenNCC(txt_CTten.getText());
                  suaNCC.setDiaChi(txt_CTdiachi.getText());
                  suaNCC.setTinhTrang(1);
                  if(txt_CTsdt.getText().equals("")){
                      JOptionPane.showMessageDialog(frame_chitiet, "Chưa nhập số điện thoại");
                  }
                  else {
                  if(xldl.KtraSĐT(txt_CTsdt.getText())==true){
                      suaNCC.setSdt(txt_CTsdt.getText());
                  if(suaNCCBUS.KtradulieuNCC(suaNCC)==1){
                      if(suaNCCBUS.suaNCC(suaNCC)==1){
                          JOptionPane.showMessageDialog(frame_chitiet, "Cập nhật thành công");
                          frame_chitiet.setVisible(false);
                      }
                      else
                          JOptionPane.showMessageDialog(frame_chitiet, "Cập nhật thất bại");
                  }
            }}} 
             @Override
            public void mouseEntered(MouseEvent arg0) {
                btSua_CT.setBackground(Color.DARK_GRAY);
                btSua_CT.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
               btSua_CT.setBackground(colorButton);
            }
        });
    btThoat_CT.setBounds(310, 330, 120, 35);
}

}
