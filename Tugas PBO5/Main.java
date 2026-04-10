import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //Input student
        System.out.println("Masukkan Nama Mahasiswa");
        String namaMhs = input.nextLine();
        System.out.println("Masukkan Alamat");
        String alamatMhs = input.nextLine();

        Student s = new Student(namaMhs, alamatMhs);

        //Tambah Data Nilai
        s.addCourseGrade("PBO", 85);
        s.addCourseGrade("Basis Data", 90);

        System.out.println(s.toString());
        s.printGrades();
        System.out.println("Rata-rata: " + s.getAverageGrade());
        System.out.println();

        //input teacher
        System.out.println("Masukkan Nama Dosen: ");
        String namaDosen = input.nextLine();
        System.out.println("Masukkan Alamat: ");
        String alamatDosen = input.nextLine();

        Teacher t = new Teacher(namaDosen, alamatDosen);

        t.addCourse("PBO");
        t.addCourse("AI");
        System.out.println(t.toString());
    }
}