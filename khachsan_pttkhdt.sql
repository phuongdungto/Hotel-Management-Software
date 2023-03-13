-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 19, 2021 at 06:35 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `khachsan_pttkhdt`
--

-- --------------------------------------------------------

--
-- Table structure for table `chi_tiet_hoa_don`
--

CREATE TABLE `chi_tiet_hoa_don` (
  `MaHD` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `MaDV` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `MaKMDV` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `DonGia` int(10) UNSIGNED NOT NULL,
  `SL` tinyint(2) UNSIGNED NOT NULL,
  `ThanhTien` int(10) UNSIGNED NOT NULL,
  `TienKM` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `chi_tiet_hoa_don`
--

INSERT INTO `chi_tiet_hoa_don` (`MaHD`, `MaDV`, `MaKMDV`, `DonGia`, `SL`, `ThanhTien`, `TienKM`) VALUES
('HD0001', 'DV0001', 'KMDV0001', 100000, 3, 300000, 6000),
('HD0001', 'DV0003', 'null', 200000, 2, 400000, 0);

-- --------------------------------------------------------

--
-- Table structure for table `chi_tiet_km_dich_vu`
--

CREATE TABLE `chi_tiet_km_dich_vu` (
  `MaKMDV` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `MaDV` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `%KM` tinyint(2) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `chi_tiet_km_dich_vu`
--

INSERT INTO `chi_tiet_km_dich_vu` (`MaKMDV`, `MaDV`, `%KM`) VALUES
('KMDV0001', 'DV0001', 2),
('KMDV0001', 'DV0002', 4);

-- --------------------------------------------------------

--
-- Table structure for table `chi_tiet_km_phong`
--

CREATE TABLE `chi_tiet_km_phong` (
  `MaKMPhong` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `MaPhong` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `%KM` tinyint(2) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- --------------------------------------------------------

--
-- Table structure for table `chi_tiet_phieu_dat_phong`
--

CREATE TABLE `chi_tiet_phieu_dat_phong` (
  `MaPDP` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `MaPhong` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `MaKMPhong` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `SLNguoi` tinyint(2) UNSIGNED NOT NULL,
  `DonGia` int(10) UNSIGNED NOT NULL,
  `TienKM` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- --------------------------------------------------------

--
-- Table structure for table `chi_tiet_phieu_nhap`
--

CREATE TABLE `chi_tiet_phieu_nhap` (
  `MaPNH` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `MaHang` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `DonGia` int(10) UNSIGNED NOT NULL,
  `SL` int(10) UNSIGNED NOT NULL,
  `ThanhTien` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `chi_tiet_phieu_nhap`
--

INSERT INTO `chi_tiet_phieu_nhap` (`MaPNH`, `MaHang`, `DonGia`, `SL`, `ThanhTien`) VALUES
('PNH0001', 'HH0001', 0, 10, 0);

-- --------------------------------------------------------

--
-- Table structure for table `chi_tiet_quyen`
--

CREATE TABLE `chi_tiet_quyen` (
  `MaQuyen` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `MaChucNang` varchar(10) COLLATE utf8mb4_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `chi_tiet_quyen`
--

INSERT INTO `chi_tiet_quyen` (`MaQuyen`, `MaChucNang`) VALUES
('QL', 'QLBCTK'),
('QL', 'QLDV'),
('QL', 'QLHD'),
('QL', 'QLHH'),
('QL', 'QLKH'),
('QL', 'QLKMDV'),
('QL', 'QLKMP'),
('QL', 'QLLP'),
('QL', 'QLNCC'),
('QL', 'QLNV'),
('QL', 'QLPDP'),
('QL', 'QLPHONG'),
('QL', 'QLPNH'),
('QL', 'QLQTK'),
('QL', 'QLTK'),
('TKH', 'QLNCC'),
('TKH', 'QLPNH'),
('TKH', 'QLHH'),
('LT', 'QLDV'),
('LT', 'QLKH'),
('LT', 'QLPHONG'),
('LT', 'QLKMDV'),
('LT', 'QLKMP'),
('LT', 'QLPDP'),
('LT', 'QLLP'),
('TN', 'QLHD'),
('TN', 'QLBCTK');

-- --------------------------------------------------------

--
-- Table structure for table `chuc_nang`
--

CREATE TABLE `chuc_nang` (
  `MaChucNang` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `TenChucNang` varchar(256) COLLATE utf8mb4_bin NOT NULL,
  `hinhanh` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `stt` tinyint(3) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `chuc_nang`
--

INSERT INTO `chuc_nang` (`MaChucNang`, `TenChucNang`, `hinhanh`, `stt`) VALUES
('QLBCTK', 'Báo cáo thống kê', 'iconthongke.png', 15),
('QLDV', 'Quản lý dịch vụ', 'icondichvu.png', 8),
('QLHD', 'Quản lý hóa đơn', 'iconhoadon.png', 3),
('QLHH', 'Quản lý hàng hóa', 'iconhanghoa.png', 11),
('QLKH', 'Quản lý khách hàng', 'iconkhachhang.png', 6),
('QLKMDV', 'Khuyến mãi dịch vụ', 'iconkmdv.png', 4),
('QLKMP', 'Khuyến mãi phòng', 'iconkmphong.png', 5),
('QLLP', 'Quản lý loại phòng', 'iconloaiphong.png', 9),
('QLNCC', 'Quản lý nhà cung cấp', 'iconnhacungcap.png', 12),
('QLNV', 'Quản lý nhân viên', 'iconnhanvien.png', 7),
('QLPDP', 'Quản lý phiếu đặt phòng', 'iconpdp.png', 2),
('QLPHONG', 'Quản lý phòng', 'iconphong.png', 1),
('QLPNH', 'Quản lý phiếu nhập hàng', 'iconphieunhaphang.png', 10),
('QLQTK', 'Phân quyền tài khoản', 'iconquyentaikhoan.png', 14),
('QLTK', 'Quản lý tài khoản', 'icontaikhoan.png', 13);

-- --------------------------------------------------------

--
-- Table structure for table `dich_vu`
--

CREATE TABLE `dich_vu` (
  `MaDV` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `TenDV` varchar(256) COLLATE utf8mb4_bin NOT NULL,
  `DonGia` int(10) UNSIGNED NOT NULL,
  `TinhTrang` tinyint(3) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `dich_vu`
--

INSERT INTO `dich_vu` (`MaDV`, `TenDV`, `DonGia`, `TinhTrang`) VALUES
('DV0001', 'abc', 100000, 1),
('DV0002', 'xyz', 200000, 1),
('DV0003', '123456', 200000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `hang_hoa`
--

CREATE TABLE `hang_hoa` (
  `MaHang` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `TenHang` varchar(256) COLLATE utf8mb4_bin NOT NULL,
  `LoaiHang` varchar(256) COLLATE utf8mb4_bin NOT NULL,
  `SL` int(10) UNSIGNED NOT NULL,
  `DonGia` int(10) UNSIGNED NOT NULL,
  `TinhTrang` tinyint(1) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `hang_hoa`
--

INSERT INTO `hang_hoa` (`MaHang`, `TenHang`, `LoaiHang`, `SL`, `DonGia`, `TinhTrang`) VALUES
('HH0001', 'abc', 'xyz', 2, 0, 1),
('HH0002', '123412', 'ádfasdf', 12, 100000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `hoa_don`
--

CREATE TABLE `hoa_don` (
  `MaHD` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `MaPDP` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `MaNV` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `NgayLapHD` date NOT NULL,
  `TongTienThue` int(10) UNSIGNED NOT NULL,
  `TongTienDV` int(10) UNSIGNED NOT NULL,
  `Thue` tinyint(2) UNSIGNED NOT NULL,
  `TongCong` int(10) UNSIGNED NOT NULL,
  `TongTienKM` int(10) UNSIGNED NOT NULL,
  `TongPhaiTra` int(10) UNSIGNED NOT NULL,
  `TinhTrang` tinyint(1) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `hoa_don`
--

INSERT INTO `hoa_don` (`MaHD`, `MaPDP`, `MaNV`, `NgayLapHD`, `TongTienThue`, `TongTienDV`, `Thue`, `TongCong`, `TongTienKM`, `TongPhaiTra`, `TinhTrang`) VALUES
('HD0001', 'PDP0001', 'NV0001', '2021-12-16', 500000, 700000, 10, 1320000, 8000, 1312000, 1),
('HD0002', 'PDP0002', 'NV0001', '2021-12-10', 500000, 700000, 10, 1320000, 0, 1320000, 1),
('HD0003', 'PDP0003', 'NV0001', '2021-12-15', 500000, 700000, 10, 1320000, 0, 1320000, 1),
('HD0004', 'PDP0004', 'NV0003', '2021-12-12', 0, 0, 10, 0, 0, 0, 2);

-- --------------------------------------------------------

--
-- Table structure for table `khach_hang`
--

CREATE TABLE `khach_hang` (
  `MaKH` varchar(12) COLLATE utf8mb4_bin NOT NULL,
  `Ho` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `Ten` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `sdt` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `DiaChi` varchar(256) COLLATE utf8mb4_bin NOT NULL,
  `NgaySinh` date NOT NULL,
  `GioiTinh` tinyint(1) UNSIGNED NOT NULL,
  `TinhTrang` tinyint(1) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `khach_hang`
--

INSERT INTO `khach_hang` (`MaKH`, `Ho`, `Ten`, `sdt`, `DiaChi`, `NgaySinh`, `GioiTinh`, `TinhTrang`) VALUES
('012345678912', 'Nguyễn Văn', 'A', '0123456789', 'abcxyz', '2001-12-06', 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `khuyen_mai_dich_vu`
--

CREATE TABLE `khuyen_mai_dich_vu` (
  `MaKMDV` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `Ten` varchar(256) COLLATE utf8mb4_bin NOT NULL,
  `NgayBD` date NOT NULL,
  `NgayKT` date NOT NULL,
  `TinhTrang` tinyint(1) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `khuyen_mai_dich_vu`
--

INSERT INTO `khuyen_mai_dich_vu` (`MaKMDV`, `Ten`, `NgayBD`, `NgayKT`, `TinhTrang`) VALUES
('KMDV0001', 'Khuyến mãi 1', '2021-12-14', '2021-12-24', 1),
('KMDV0002', 'Khuyến mãi dịp tết', '2021-12-15', '2021-12-30', 1),
('null', 'null', '0000-00-00', '0000-00-00', 0);

-- --------------------------------------------------------

--
-- Table structure for table `khuyen_mai_phong`
--

CREATE TABLE `khuyen_mai_phong` (
  `MaKMPhong` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `Ten` varchar(256) COLLATE utf8mb4_bin NOT NULL,
  `NgayBD` date NOT NULL,
  `NgayKT` date NOT NULL,
  `TinhTrang` tinyint(1) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `khuyen_mai_phong`
--

INSERT INTO `khuyen_mai_phong` (`MaKMPhong`, `Ten`, `NgayBD`, `NgayKT`, `TinhTrang`) VALUES
('KMP0001', 'Khuyến mãi 2', '2021-12-14', '2021-12-23', 1),
('null', 'null', '0000-00-00', '0000-00-00', 0);

-- --------------------------------------------------------

--
-- Table structure for table `loai_phong`
--

CREATE TABLE `loai_phong` (
  `MaLoai` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `TenLoai` varchar(256) COLLATE utf8mb4_bin NOT NULL,
  `TinhTrang` tinyint(3) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `loai_phong`
--

INSERT INTO `loai_phong` (`MaLoai`, `TenLoai`, `TinhTrang`) VALUES
('LP0001', 'abc', 1),
('LP0002', 'xyz', 1),
('LP0003', 'tyu', 1),
('LP0004', 'uio', 1);

-- --------------------------------------------------------

--
-- Table structure for table `nhan_vien`
--

CREATE TABLE `nhan_vien` (
  `MaNV` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `Ho` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `Ten` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `sdt` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `NgaySinh` date NOT NULL,
  `GioiTinh` tinyint(1) UNSIGNED NOT NULL,
  `DiaChi` varchar(256) COLLATE utf8mb4_bin NOT NULL,
  `TienLuong` int(11) UNSIGNED NOT NULL,
  `TinhTrang` tinyint(1) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `nhan_vien`
--

INSERT INTO `nhan_vien` (`MaNV`, `Ho`, `Ten`, `sdt`, `NgaySinh`, `GioiTinh`, `DiaChi`, `TienLuong`, `TinhTrang`) VALUES
('NV0001', 'Nguyễn Văn Minh', 'Đức', '0368078796', '2001-04-14', 0, 'abcxyz', 20000000, 1),
('NV0002', 'Lê Thị Cẩm', 'Duyên', '0123456789', '2001-11-01', 1, 'xyz', 18000000, 1),
('NV0003', 'Nguyễn Huỳnh Thanh', 'Duy', '0123987654', '2001-05-13', 0, 'xyzasd', 18000000, 1),
('NV0004', 'Tô Phương', 'Dũng', '0893217654', '2001-08-17', 0, 'werty', 18000000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `nha_cung_cap`
--

CREATE TABLE `nha_cung_cap` (
  `MaNCC` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `TenNCC` varchar(256) COLLATE utf8mb4_bin NOT NULL,
  `DiaChi` varchar(256) COLLATE utf8mb4_bin NOT NULL,
  `sdt` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `TinhTrang` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `nha_cung_cap`
--

INSERT INTO `nha_cung_cap` (`MaNCC`, `TenNCC`, `DiaChi`, `sdt`, `TinhTrang`) VALUES
('NCC0001', 'abc', 'ádfgdsf', '0123456789', 1);

-- --------------------------------------------------------

--
-- Table structure for table `phieu_dat_phong`
--

CREATE TABLE `phieu_dat_phong` (
  `MaPDP` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `MaKH` varchar(12) COLLATE utf8mb4_bin NOT NULL,
  `MaNV` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `SLPhong` tinyint(2) UNSIGNED NOT NULL,
  `NgayLapPDP` date NOT NULL,
  `NgayThue` date NOT NULL,
  `NgayTra` date NOT NULL,
  `TongTienThue` int(10) UNSIGNED NOT NULL,
  `TongTienKM` int(10) UNSIGNED NOT NULL,
  `TinhTrang` tinyint(1) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `phieu_dat_phong`
--

INSERT INTO `phieu_dat_phong` (`MaPDP`, `MaKH`, `MaNV`, `SLPhong`, `NgayLapPDP`, `NgayThue`, `NgayTra`, `TongTienThue`, `TongTienKM`, `TinhTrang`) VALUES
('PDP0001', '012345678912', 'NV0001', 1, '2021-12-11', '2021-12-12', '2021-12-13', 500000, 0, 1),
('PDP0002', '012345678912', 'NV0002', 2, '2021-12-14', '2021-12-22', '2021-12-24', 0, 0, 1),
('PDP0003', '012345678912', 'NV0003', 2, '2021-12-14', '2021-12-16', '2021-12-15', 0, 0, 3),
('PDP0004', '012345678912', 'NV0001', 2, '2021-12-10', '2021-12-11', '2021-12-13', 0, 0, 2);

-- --------------------------------------------------------

--
-- Table structure for table `phieu_nhap_hang`
--

CREATE TABLE `phieu_nhap_hang` (
  `MaPNH` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `MaNV` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `MaNCC` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `NgayNhap` date NOT NULL,
  `TongTien` int(10) UNSIGNED NOT NULL,
  `TinhTrang` tinyint(1) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `phieu_nhap_hang`
--

INSERT INTO `phieu_nhap_hang` (`MaPNH`, `MaNV`, `MaNCC`, `NgayNhap`, `TongTien`, `TinhTrang`) VALUES
('PNH0001', 'NV0001', 'NCC0001', '2021-12-13', 500000, 1),
('PNH0002', 'NV0002', 'NCC0001', '2021-12-14', 0, 1),
('PNH0003', 'NV0003', 'NCC0001', '2021-12-14', 0, 1),
('PNH0004', 'NV0004', 'NCC0001', '2021-12-14', 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `phong`
--

CREATE TABLE `phong` (
  `MaPhong` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `MaLoai` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `Tang` tinyint(2) UNSIGNED NOT NULL,
  `SLNguoi` tinyint(2) UNSIGNED NOT NULL,
  `DonGia` int(10) UNSIGNED NOT NULL,
  `TinhTrang` tinyint(1) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `phong`
--

INSERT INTO `phong` (`MaPhong`, `MaLoai`, `Tang`, `SLNguoi`, `DonGia`, `TinhTrang`) VALUES
('PHONG01', 'LP0001', 1, 2, 2000000, 1),
('PHONG02', 'LP0001', 1, 2, 2000000, 1),
('PHONG03', 'LP0002', 1, 2, 2000000, 1),
('PHONG04', 'LP0003', 1, 2, 2000000, 1),
('PHONG05', 'LP0004', 1, 2, 2000000, 1),
('PHONG06', 'LP0001', 1, 2, 2000000, 1),
('PHONG07', 'LP0002', 1, 2, 2000000, 1),
('PHONG08', 'LP0004', 1, 2, 2000000, 1),
('PHONG09', 'LP0002', 2, 2, 2000000, 1),
('PHONG10', 'LP0004', 2, 2, 2000000, 1),
('PHONG11', 'LP0001', 2, 2, 2000000, 1),
('PHONG12', 'LP0003', 2, 2, 2000000, 1),
('PHONG13', 'LP0001', 2, 2, 2000000, 1),
('PHONG14', 'LP0002', 2, 2, 2000000, 1),
('PHONG15', 'LP0003', 2, 2, 2000000, 1),
('PHONG16', 'LP0004', 2, 2, 2000000, 1),
('PHONG17', 'LP0001', 3, 2, 2000000, 1),
('PHONG18', 'LP0002', 3, 2, 2000000, 1),
('PHONG19', 'LP0003', 3, 2, 2000000, 1),
('PHONG20', 'LP0002', 3, 2, 2000000, 1),
('PHONG21', 'LP0002', 3, 2, 2000000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `quyen_tk`
--

CREATE TABLE `quyen_tk` (
  `MaQuyen` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `TenQuyen` varchar(256) COLLATE utf8mb4_bin NOT NULL,
  `TinhTrang` tinyint(3) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `quyen_tk`
--

INSERT INTO `quyen_tk` (`MaQuyen`, `TenQuyen`, `TinhTrang`) VALUES
('LT', 'Lễ Tân', 1),
('QL', 'Quản lý', 1),
('TKH', 'Thủ kho', 1),
('TN', 'Thu ngân', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tai_khoan`
--

CREATE TABLE `tai_khoan` (
  `MaTK` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `MaNV` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `MaQuyen` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `Username` varchar(35) COLLATE utf8mb4_bin NOT NULL,
  `Password` varchar(32) COLLATE utf8mb4_bin NOT NULL,
  `TinhTrang` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `tai_khoan`
--

INSERT INTO `tai_khoan` (`MaTK`, `MaNV`, `MaQuyen`, `Username`, `Password`, `TinhTrang`) VALUES
('TK0001', 'NV0001', 'QL', 'admin', '96DVaFPJJxDJOsoTEYdLnA==', 1),
('TK0002', 'NV0002', 'LT', 'camduyen', '8Z4S9qgaA0kSefx97tc3qg==', 1),
('TK0003', 'NV0003', 'TN', 'thanhduy', '96DVaFPJJxDJOsoTEYdLnA', 1),
('TK0004', 'NV0004', 'TKH', 'phuongdung', '8Z4S9qgaA0kSefx97tc3qg==', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chi_tiet_hoa_don`
--
ALTER TABLE `chi_tiet_hoa_don`
  ADD KEY `MaHD` (`MaHD`),
  ADD KEY `MaDV` (`MaDV`),
  ADD KEY `MaKMDV` (`MaKMDV`);

--
-- Indexes for table `chi_tiet_km_dich_vu`
--
ALTER TABLE `chi_tiet_km_dich_vu`
  ADD KEY `MaKMDV` (`MaKMDV`),
  ADD KEY `MaDV` (`MaDV`);

--
-- Indexes for table `chi_tiet_km_phong`
--
ALTER TABLE `chi_tiet_km_phong`
  ADD KEY `MaKMPhong` (`MaKMPhong`),
  ADD KEY `MaPhong` (`MaPhong`);

--
-- Indexes for table `chi_tiet_phieu_dat_phong`
--
ALTER TABLE `chi_tiet_phieu_dat_phong`
  ADD KEY `MaPDP` (`MaPDP`),
  ADD KEY `MaPhong` (`MaPhong`),
  ADD KEY `MaKMPhong` (`MaKMPhong`);

--
-- Indexes for table `chi_tiet_phieu_nhap`
--
ALTER TABLE `chi_tiet_phieu_nhap`
  ADD KEY `MaPNH` (`MaPNH`),
  ADD KEY `MaHang` (`MaHang`);

--
-- Indexes for table `chi_tiet_quyen`
--
ALTER TABLE `chi_tiet_quyen`
  ADD KEY `MaChucNang` (`MaChucNang`),
  ADD KEY `MaQuyen` (`MaQuyen`);

--
-- Indexes for table `chuc_nang`
--
ALTER TABLE `chuc_nang`
  ADD PRIMARY KEY (`MaChucNang`);

--
-- Indexes for table `dich_vu`
--
ALTER TABLE `dich_vu`
  ADD PRIMARY KEY (`MaDV`);

--
-- Indexes for table `hang_hoa`
--
ALTER TABLE `hang_hoa`
  ADD PRIMARY KEY (`MaHang`);

--
-- Indexes for table `hoa_don`
--
ALTER TABLE `hoa_don`
  ADD PRIMARY KEY (`MaHD`),
  ADD KEY `MaPDP` (`MaPDP`),
  ADD KEY `MaNV` (`MaNV`);

--
-- Indexes for table `khach_hang`
--
ALTER TABLE `khach_hang`
  ADD PRIMARY KEY (`MaKH`);

--
-- Indexes for table `khuyen_mai_dich_vu`
--
ALTER TABLE `khuyen_mai_dich_vu`
  ADD PRIMARY KEY (`MaKMDV`);

--
-- Indexes for table `khuyen_mai_phong`
--
ALTER TABLE `khuyen_mai_phong`
  ADD PRIMARY KEY (`MaKMPhong`);

--
-- Indexes for table `loai_phong`
--
ALTER TABLE `loai_phong`
  ADD PRIMARY KEY (`MaLoai`);

--
-- Indexes for table `nhan_vien`
--
ALTER TABLE `nhan_vien`
  ADD PRIMARY KEY (`MaNV`);

--
-- Indexes for table `nha_cung_cap`
--
ALTER TABLE `nha_cung_cap`
  ADD PRIMARY KEY (`MaNCC`);

--
-- Indexes for table `phieu_dat_phong`
--
ALTER TABLE `phieu_dat_phong`
  ADD PRIMARY KEY (`MaPDP`),
  ADD KEY `MaKH` (`MaKH`),
  ADD KEY `MaNV` (`MaNV`);

--
-- Indexes for table `phieu_nhap_hang`
--
ALTER TABLE `phieu_nhap_hang`
  ADD PRIMARY KEY (`MaPNH`),
  ADD KEY `MaNV` (`MaNV`),
  ADD KEY `MaNCC` (`MaNCC`);

--
-- Indexes for table `phong`
--
ALTER TABLE `phong`
  ADD PRIMARY KEY (`MaPhong`),
  ADD KEY `MaLoai` (`MaLoai`);

--
-- Indexes for table `quyen_tk`
--
ALTER TABLE `quyen_tk`
  ADD PRIMARY KEY (`MaQuyen`);

--
-- Indexes for table `tai_khoan`
--
ALTER TABLE `tai_khoan`
  ADD PRIMARY KEY (`MaTK`),
  ADD KEY `MaNV` (`MaNV`),
  ADD KEY `MaQuyen` (`MaQuyen`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `chi_tiet_hoa_don`
--
ALTER TABLE `chi_tiet_hoa_don`
  ADD CONSTRAINT `chi_tiet_hoa_don_ibfk_1` FOREIGN KEY (`MaHD`) REFERENCES `hoa_don` (`MaHD`),
  ADD CONSTRAINT `chi_tiet_hoa_don_ibfk_2` FOREIGN KEY (`MaDV`) REFERENCES `dich_vu` (`MaDV`),
  ADD CONSTRAINT `chi_tiet_hoa_don_ibfk_3` FOREIGN KEY (`MaKMDV`) REFERENCES `khuyen_mai_dich_vu` (`MaKMDV`);

--
-- Constraints for table `chi_tiet_km_dich_vu`
--
ALTER TABLE `chi_tiet_km_dich_vu`
  ADD CONSTRAINT `chi_tiet_km_dich_vu_ibfk_1` FOREIGN KEY (`MaKMDV`) REFERENCES `khuyen_mai_dich_vu` (`MaKMDV`),
  ADD CONSTRAINT `chi_tiet_km_dich_vu_ibfk_2` FOREIGN KEY (`MaDV`) REFERENCES `dich_vu` (`MaDV`);

--
-- Constraints for table `chi_tiet_km_phong`
--
ALTER TABLE `chi_tiet_km_phong`
  ADD CONSTRAINT `chi_tiet_km_phong_ibfk_1` FOREIGN KEY (`MaKMPhong`) REFERENCES `khuyen_mai_phong` (`MaKMPhong`),
  ADD CONSTRAINT `chi_tiet_km_phong_ibfk_2` FOREIGN KEY (`MaPhong`) REFERENCES `phong` (`MaPhong`);

--
-- Constraints for table `chi_tiet_phieu_dat_phong`
--
ALTER TABLE `chi_tiet_phieu_dat_phong`
  ADD CONSTRAINT `chi_tiet_phieu_dat_phong_ibfk_1` FOREIGN KEY (`MaPDP`) REFERENCES `phieu_dat_phong` (`MaPDP`),
  ADD CONSTRAINT `chi_tiet_phieu_dat_phong_ibfk_2` FOREIGN KEY (`MaPhong`) REFERENCES `phong` (`MaPhong`),
  ADD CONSTRAINT `chi_tiet_phieu_dat_phong_ibfk_3` FOREIGN KEY (`MaKMPhong`) REFERENCES `khuyen_mai_phong` (`MaKMPhong`);

--
-- Constraints for table `chi_tiet_phieu_nhap`
--
ALTER TABLE `chi_tiet_phieu_nhap`
  ADD CONSTRAINT `chi_tiet_phieu_nhap_ibfk_1` FOREIGN KEY (`MaPNH`) REFERENCES `phieu_nhap_hang` (`MaPNH`),
  ADD CONSTRAINT `chi_tiet_phieu_nhap_ibfk_2` FOREIGN KEY (`MaHang`) REFERENCES `hang_hoa` (`MaHang`);

--
-- Constraints for table `chi_tiet_quyen`
--
ALTER TABLE `chi_tiet_quyen`
  ADD CONSTRAINT `chi_tiet_quyen_ibfk_1` FOREIGN KEY (`MaChucNang`) REFERENCES `chuc_nang` (`MaChucNang`),
  ADD CONSTRAINT `chi_tiet_quyen_ibfk_2` FOREIGN KEY (`MaQuyen`) REFERENCES `quyen_tk` (`MaQuyen`);

--
-- Constraints for table `hoa_don`
--
ALTER TABLE `hoa_don`
  ADD CONSTRAINT `hoa_don_ibfk_1` FOREIGN KEY (`MaPDP`) REFERENCES `phieu_dat_phong` (`MaPDP`),
  ADD CONSTRAINT `hoa_don_ibfk_2` FOREIGN KEY (`MaNV`) REFERENCES `nhan_vien` (`MaNV`);

--
-- Constraints for table `phieu_dat_phong`
--
ALTER TABLE `phieu_dat_phong`
  ADD CONSTRAINT `phieu_dat_phong_ibfk_1` FOREIGN KEY (`MaKH`) REFERENCES `khach_hang` (`MaKH`),
  ADD CONSTRAINT `phieu_dat_phong_ibfk_2` FOREIGN KEY (`MaNV`) REFERENCES `nhan_vien` (`MaNV`);

--
-- Constraints for table `phieu_nhap_hang`
--
ALTER TABLE `phieu_nhap_hang`
  ADD CONSTRAINT `phieu_nhap_hang_ibfk_1` FOREIGN KEY (`MaNV`) REFERENCES `nhan_vien` (`MaNV`),
  ADD CONSTRAINT `phieu_nhap_hang_ibfk_2` FOREIGN KEY (`MaNCC`) REFERENCES `nha_cung_cap` (`MaNCC`);

--
-- Constraints for table `phong`
--
ALTER TABLE `phong`
  ADD CONSTRAINT `phong_ibfk_1` FOREIGN KEY (`MaLoai`) REFERENCES `loai_phong` (`MaLoai`);

--
-- Constraints for table `tai_khoan`
--
ALTER TABLE `tai_khoan`
  ADD CONSTRAINT `tai_khoan_ibfk_1` FOREIGN KEY (`MaNV`) REFERENCES `nhan_vien` (`MaNV`),
  ADD CONSTRAINT `tai_khoan_ibfk_2` FOREIGN KEY (`MaQuyen`) REFERENCES `quyen_tk` (`MaQuyen`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
