import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

    private static final String URL      = "jdbc:mysql://localhost:3306/toko_retail?useSSL=false&serverTimezone=UTC";
    private static final String USER     = "root";
    private static final String PASSWORD = "";

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Berhasil terhubung ke database toko_retail.\n");
            boolean jalan = true;

            while (jalan) {
                tampilMenu();
                System.out.print("Pilihan : ");
                String input = sc.nextLine().trim();

                switch (input) {
                    case "1": tampilSemuaData(conn); break;
                    case "2": tambahData(conn); break;
                    case "3": cariData(conn); break;
                    case "4": ubahData(conn); break;
                    case "5": hapusData(conn); break;
                    case "0":
                        jalan = false;
                        System.out.println("Terima kasih. Program selesai.");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid, silakan coba lagi.\n");
                }
            }
        } catch (SQLException e) {
            System.out.println("Gagal terhubung ke database: " + e.getMessage());
        }
    }

    private static void tampilMenu() {
        System.out.println("+-----------------------------------+");
        System.out.println("|          MENU TOKO RETAIL          |");
        System.out.println("+-----------------------------------+");
        System.out.println("  1. Tampil Semua Data");
        System.out.println("  2. Tambah Data");
        System.out.println("  3. Cari Data");
        System.out.println("  4. Ubah Data");
        System.out.println("  5. Hapus Data");
        System.out.println("  0. Keluar");
        System.out.println();
    }

    // ==== 1. TAMPIL SEMUA DATA ====
    private static void tampilSemuaData(Connection conn) {
        String sql = "SELECT * FROM barang ORDER BY id";
        System.out.println();
        printGaris();
        System.out.printf("| %-3s| %-8s| %-20s| %10s | %6s |%n", "#", "Kode", "Nama Barang", "Harga", "Stok");
        printGaris();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            int no = 1;
            boolean adaData = false;
            while (rs.next()) {
                adaData = true;
                System.out.printf("| %-3d| %-8s| %-20s| %10.0f | %6d |%n",
                        no++, rs.getString("kode"), rs.getString("nama_barang"),
                        rs.getDouble("harga"), rs.getInt("stok"));
            }
            printGaris();
            if (adaData) {
                System.out.println("Total: " + (no - 1) + " barang");
            } else {
                System.out.println("Belum ada data barang.");
            }
        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan saat mengambil data: " + e.getMessage());
        }
        System.out.println();
    }

    private static void printGaris() {
        System.out.println("+-----+---------+---------------------+------------+--------+");
    }

    // ==== 2. TAMBAH DATA ====
    private static void tambahData(Connection conn) {
        System.out.println("\n-- Tambah Data Barang --");
        try {
            System.out.print("Kode Barang   : ");
            String kode = sc.nextLine().trim();

            System.out.print("Nama Barang   : ");
            String nama = sc.nextLine().trim();

            System.out.print("Harga         : ");
            double harga = Double.parseDouble(sc.nextLine().trim());

            System.out.print("Stok          : ");
            int stok = Integer.parseInt(sc.nextLine().trim());

            String sql = "INSERT INTO barang (kode, nama_barang, harga, stok) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, kode);
                ps.setString(2, nama);
                ps.setDouble(3, harga);
                ps.setInt(4, stok);
                int rows = ps.executeUpdate();
                if (rows > 0) {
                    System.out.println("Data berhasil ditambahkan.\n");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Input harga/stok harus berupa angka.\n");
        } catch (SQLException e) {
            System.out.println("Gagal menambah data (kode mungkin sudah ada): " + e.getMessage() + "\n");
        }
    }

    // ==== 3. CARI DATA ====
    private static void cariData(Connection conn) {
        System.out.println("\n-- Cari Data Barang --");
        System.out.print("Masukkan kode atau nama barang : ");
        String keyword = sc.nextLine().trim();

        String sql = "SELECT * FROM barang WHERE kode LIKE ? OR nama_barang LIKE ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");

            try (ResultSet rs = ps.executeQuery()) {
                boolean ditemukan = false;
                printGaris();
                System.out.printf("| %-3s| %-8s| %-20s| %10s | %6s |%n", "#", "Kode", "Nama Barang", "Harga", "Stok");
                printGaris();
                int no = 1;
                while (rs.next()) {
                    ditemukan = true;
                    System.out.printf("| %-3d| %-8s| %-20s| %10.0f | %6d |%n",
                            no++, rs.getString("kode"), rs.getString("nama_barang"),
                            rs.getDouble("harga"), rs.getInt("stok"));
                }
                printGaris();
                if (!ditemukan) {
                    System.out.println("Data tidak ditemukan.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan saat mencari data: " + e.getMessage());
        }
        System.out.println();
    }

    // ==== 4. UBAH DATA ====
    private static void ubahData(Connection conn) {
        System.out.println("\n-- Ubah Data Barang --");
        System.out.print("Masukkan kode barang yang akan diubah : ");
        String kode = sc.nextLine().trim();

        String cekSql = "SELECT * FROM barang WHERE kode = ?";
        try (PreparedStatement cekPs = conn.prepareStatement(cekSql)) {
            cekPs.setString(1, kode);
            try (ResultSet rs = cekPs.executeQuery()) {
                if (!rs.next()) {
                    System.out.println("Data dengan kode " + kode + " tidak ditemukan.\n");
                    return;
                }
                System.out.println("Data ditemukan: " + rs.getString("nama_barang")
                        + " | Harga: " + rs.getDouble("harga")
                        + " | Stok: " + rs.getInt("stok"));
            }

            System.out.print("Nama Barang baru (kosongkan jika tidak diubah) : ");
            String nama = sc.nextLine().trim();
            System.out.print("Harga baru (kosongkan jika tidak diubah)       : ");
            String hargaStr = sc.nextLine().trim();
            System.out.print("Stok baru (kosongkan jika tidak diubah)        : ");
            String stokStr = sc.nextLine().trim();

            StringBuilder sql = new StringBuilder("UPDATE barang SET ");
            boolean adaField = false;
            if (!nama.isEmpty()) { sql.append("nama_barang = ?"); adaField = true; }
            if (!hargaStr.isEmpty()) { sql.append(adaField ? ", " : "").append("harga = ?"); adaField = true; }
            if (!stokStr.isEmpty()) { sql.append(adaField ? ", " : "").append("stok = ?"); adaField = true; }

            if (!adaField) {
                System.out.println("Tidak ada perubahan yang dilakukan.\n");
                return;
            }
            sql.append(" WHERE kode = ?");

            try (PreparedStatement ps = conn.prepareStatement(sql.toString())) {
                int idx = 1;
                if (!nama.isEmpty()) ps.setString(idx++, nama);
                if (!hargaStr.isEmpty()) ps.setDouble(idx++, Double.parseDouble(hargaStr));
                if (!stokStr.isEmpty()) ps.setInt(idx++, Integer.parseInt(stokStr));
                ps.setString(idx, kode);

                int rows = ps.executeUpdate();
                if (rows > 0) {
                    System.out.println("Data berhasil diubah.\n");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Input harga/stok harus berupa angka.\n");
        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan saat mengubah data: " + e.getMessage() + "\n");
        }
    }

    // ==== 5. HAPUS DATA ====
    private static void hapusData(Connection conn) {
        System.out.println("\n-- Hapus Data Barang --");
        System.out.print("Masukkan kode barang yang akan dihapus : ");
        String kode = sc.nextLine().trim();

        String cekSql = "SELECT * FROM barang WHERE kode = ?";
        try (PreparedStatement cekPs = conn.prepareStatement(cekSql)) {
            cekPs.setString(1, kode);
            try (ResultSet rs = cekPs.executeQuery()) {
                if (!rs.next()) {
                    System.out.println("Data dengan kode " + kode + " tidak ditemukan.\n");
                    return;
                }
            }

            System.out.print("Yakin ingin menghapus data ini? (y/n) : ");
            String konfirmasi = sc.nextLine().trim().toLowerCase();
            if (!konfirmasi.equals("y")) {
                System.out.println("Penghapusan dibatalkan.\n");
                return;
            }

            String sql = "DELETE FROM barang WHERE kode = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, kode);
                int rows = ps.executeUpdate();
                if (rows > 0) {
                    System.out.println("Data berhasil dihapus.\n");
                }
            }
        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan saat menghapus data: " + e.getMessage() + "\n");
        }
    }
}