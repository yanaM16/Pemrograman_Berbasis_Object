import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {
    public static void main(String[] args) {
        String csvFile = "D:\\Tugas Sesi 11 PBO\\students.csv";
        String line;
        String csvSplitBy = ",";
        int indeks = 0;
        int jumlahData = 0; // counter baris data (tanpa header)

        System.out.println("NIM, NAMA, UMUR, PRODI");
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                indeks++;
                if (indeks > 1) { // skip header
                    String[] student = line.split(csvSplitBy);
                    System.out.println(student[0] + ", " + student[1] + ", " + student[2] + ", " + student[3]);
                    jumlahData++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Jumlah baris data (tanpa header): " + jumlahData);
    }
}