/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

/**
 *
 * @author minhd
 */
public class Xulydulieu {
    public Pattern pattien= Pattern.compile("^\\d{5,9}$");
    public Pattern patsoluong= Pattern.compile("^\\d$");
    public Pattern patdienthoai= Pattern.compile("^0[1-9]{9}$");    
    public Pattern patdongia= Pattern.compile("^\\d{5,9}$");
    public Xulydulieu(){
        
    }
    
    public String TaoMaMoi(String s,int n){
        String ma=String.valueOf(n);
        String s1=s;
        if (ma.length()==1)
            s1+="000"+ma;
        if (ma.length()==2)
            s1+="00"+ma;
        if (ma.length()==3)
            s1+="0"+ma;
        return s1;
    }
    
    public String ChuyenKieuTien(String s){
        String s1="";
        int d=0;
        for (int i=s.length()-1;i>=0;i--){
            d++;
            if (d%3==0 && i!=0){
                s1=","+s.substring(i,i+d)+s1;
                d=0;
            }
            if (i==0)
                s1=s.substring(i,i+d)+s1;
        }
        return s1;
    }
    
    public String chuyendate(String a){
        String s;
        s=a.substring(8)+"-"+a.substring(5,7)+"-"+a.substring(0,4);
        return s;
    }
    
    public long TinhSoNgay(String s,String s1){
        DateFormat smp=new SimpleDateFormat("dd-MM-yyyy");
        Date date1=null;
        Date date2=null;
        long getDaysDiff = 0;
        try{
        date1=smp.parse(s);
        date2=smp.parse(s1);
        long getDiff = date2.getTime() - date1.getTime();
        getDaysDiff = getDiff / (24 * 60 * 60 * 1000);       
        }catch(Exception e){
            e.printStackTrace();
        }
        return getDaysDiff;
    }
    
    public boolean KTraNamNhuan(int n){
        if ((n%4==0 && n%100!=0) || n%400==0)
            return true;
        return false;
    }
    
    public boolean KTraNgayThangNam(String s){
        String s1=s.substring(0,4);
        String s2=s.substring(5,7);
        String s3=s.substring(8);
        int ngay=Integer.parseInt(s3);
        int thang=Integer.parseInt(s2);
        int nam=Integer.parseInt(s1);
        switch(thang){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                if (ngay<=31)
                    return true;                
            case 4:
            case 6:
            case 9:
            case 11:
                if (ngay==31){
                    JOptionPane.showMessageDialog(null, "Tháng "+thang+" không có ngày 31");
                    return false;
                }
                break;
            case 2:
                if (KTraNamNhuan(nam)){
                    if (ngay>29){
                        JOptionPane.showMessageDialog(null, "Tháng 2 không có ngày 30 và 21");
                        return false;
                    }
                }                   
                else{
                    if (ngay>28){
                        JOptionPane.showMessageDialog(null, "Đây là năm không nhuận, không có ngày 29");
                        return false;
                    }
                }
                break;
        }
        return true;
    }    
    
    public String LayNgayThangNamHienTai(){
        LocalDate d=LocalDate.now();
        String day=String.valueOf(d.getDayOfMonth()),month=String.valueOf(d.getMonthValue()),year = String.valueOf(d.getYear());
        if (d.getYear()<10){            
            year="0"+String.valueOf(d.getYear());
        }
        if (d.getMonthValue()<10){            
            month="0"+String.valueOf(d.getMonthValue());            
        }
        if (d.getDayOfMonth()<10){
            day="0"+String.valueOf(d.getDayOfMonth());            
        }
        return year+"-"+month+"-"+day;
    }
    
    public String Chuyentien1(String s){
        String s2="";
        int d=0;
        for (int i=s.length()-1;i>=0;i--){
            d++;
            if (s.charAt(i)==','){
                s2=s.substring(i+1,i+d)+s2;
                d=0;
            }
            if (i==0)
                s2=s.substring(i,d)+s2;
        }
        return s2;
    }
    
    public String MaHoaMatKhau(String s){
        String myKey="4D";
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] key = myKey.getBytes("UTF-8");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(s.getBytes("UTF-8")));
      } catch (Exception e) {
            System.out.println(e.toString());
      }
      return null;
    }
    public String GiaiMaHoaMatKhau(String s){
        String myKey="4D";
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] key = myKey.getBytes("UTF-8");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(s)));
      } catch (Exception e) {
            System.out.println(e.toString());
      }
      return null;
    }    
    
    public Pattern so= Pattern.compile("^[0-9]{1,6}$");

    public boolean KtraSo(String s){
        Matcher mat1= so.matcher(s);
        if (mat1.matches()==false){
            JOptionPane.showMessageDialog(null, "Dữ liệu số lượng không hợp lệ");
            return false;
        }
        return true;
    }
    public boolean KtraDonGia(String s){
        Matcher mat1= patdongia.matcher(s);
        if (mat1.matches()==false){
            JOptionPane.showMessageDialog(null, "Dữ liệu đơn giá không hợp lệ");
            return false;
        }
        return true;
    }
    public boolean KtraSĐT(String s){
        Matcher mat1= patdienthoai.matcher(s);
        if (mat1.matches()==false){
            JOptionPane.showMessageDialog(null, "Dữ liệu số điện thoại không hợp lệ");
            return false;
        }
        return true;
    }
//    public static void main(String[] args) {
//        Xulydulieu p=new Xulydulieu();
//        System.out.println(p.GiaiMaHoaMatKhau("8Z4S9qgaA0kSefx97tc3qg=="));
//    }
}
