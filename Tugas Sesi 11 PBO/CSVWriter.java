import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CSVWriter {
    public static void main(String[] args) {
        String csvFile = "D:\\Tugas Sesi 11 PBO\\students.csv";
        Scanner scanner = new Scanner(System.in);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile))) {
            char lanjut;

            do {
                System.out.print("Masukkan NIM   : ");
                String nim = scanner.nextLine();

                System.out.print("Masukkan Nama  : ");
                String nama = scanner.nextLine();

                System.out.print("Masukkan Umur  : ");
                String umur = scanner.nextLine();

                System.out.print("Masukkan Prodi : ");
                String prodi = scanner.nextLine();

                // Gabungkan jadi satu baris CSV
                String line = nim + "," + nama + "," + umur + "," + prodi;
                bw.write(line);
                bw.newLine();

                System.out.print("Tambah data lagi? (y/n): ");
                lanjut = scanner.nextLine().charAt(0);

            } while (lanjut == 'y' || lanjut == 'Y');

            System.out.println("Data berhasil disimpan ke " + csvFile);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}