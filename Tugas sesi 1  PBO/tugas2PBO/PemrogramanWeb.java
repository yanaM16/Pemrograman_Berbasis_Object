import javax.swing.JOptionPane;

public class PemrogramanWeb {

    private String materi;

    public void inputMateri() {
        materi = JOptionPane.showInputDialog("Anda sedang belajar apa?");

    }

    public void tampilkanHasil(){
        JOptionPane.showMessageDialog(null, "Belajar " + materi + " sangat mudah");
    }

    public static void main(String[] args) {
        PemrogramanWeb obj = new PemrogramanWeb();

        obj.inputMateri();
        obj.tampilkanHasil();
    }
}