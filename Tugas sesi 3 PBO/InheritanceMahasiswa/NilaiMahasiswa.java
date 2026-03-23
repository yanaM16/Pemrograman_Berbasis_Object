class NilaiMahasiswa extends Mahasiswa {
    String grade;

    NilaiMahasiswa(String nim, String nama, int nilai) {
        super(nim, nama, nilai);
        this.grade = hitungGrade();
    }

    String hitungGrade() {
        if (nilai < 0 || nilai > 100) {
            return "Input nilai anda salah";
        } else if (nilai >= 80) {
            return "A";
        } else if (nilai >= 70) {
            return "B";
        } else if (nilai >= 60) {
            return "C";
        } else if (nilai >= 50) {
            return "D";
        } else {
            return "E";
        }
    }

    boolean isLulus() {
        return grade.equals("A") || grade.equals("B") || grade.equals("C");
    }

    void tampilkan() {
        System.out.println("NIM  : " + nim);
        System.out.println("Nama : " + nama);
        System.out.println("Nilai: " + nilai);
        System.out.println("Grade: " + grade);
        System.out.println("=====================================");
    }
}