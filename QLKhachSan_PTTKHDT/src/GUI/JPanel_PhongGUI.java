/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import DTO.PhongDTO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author camdu
 */
public class JPanel_PhongGUI {


    private Color PhongCanDonDep = new Color(23, 95, 59);
    private Color PhongCoNguoi = new Color(183,28,28);
    private Color PhongDaDat = new Color(85,85,85);
    protected Color PhongTrong = new Color(36,36,36);
    Font font_lb= new Font("Times New Roman",Font.BOLD,20);
    public JPanel CreaJPanel_Phong(PhongDTO p) {
        JPanel pn_phongchitiet = new JPanel();
        JLabel txt_lp,txt_mp;
        JLabel maphong = new JLabel();
        maphong.setFont(font_lb);
        pn_phongchitiet.removeAll();
        pn_phongchitiet.setLayout(null);
            pn_phongchitiet.setVisible(false);
            if(p.getTinhTrang()==1){
            pn_phongchitiet.setBackground(PhongTrong);
            maphong.setOpaque(true);
            maphong.setBackground(PhongTrong);
            }
            if(p.getTinhTrang()==2){
            pn_phongchitiet.setBackground(PhongCoNguoi);
            maphong.setOpaque(true);
            maphong.setBackground(PhongCoNguoi);
            }
            if(p.getTinhTrang()==3){
            pn_phongchitiet.setBackground(PhongCanDonDep);
            maphong.setOpaque(true);
            maphong.setBackground(PhongCanDonDep);
            }
            pn_phongchitiet.setPreferredSize(new Dimension(170,150));
            ImageIcon icon = new ImageIcon("src/img/bed_80px.png");
            JLabel img = new JLabel();
            img.setIcon(icon);
            img.setBounds(46, 10, 80, 55);
            pn_phongchitiet.add(img);
          
            
            maphong.setText(p.getMaPhong());
            
            maphong.setForeground(Color.WHITE);
            maphong.setHorizontalAlignment(JLabel.CENTER);
            maphong.setBounds(0, 75, 170, 20);
            pn_phongchitiet.add(maphong);
            
            pn_phongchitiet.repaint();
            pn_phongchitiet.setVisible(true);
        return pn_phongchitiet;
    }

}
