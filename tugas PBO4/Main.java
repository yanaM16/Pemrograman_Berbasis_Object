public class Main {
    public static void main(String[] args) {
        Bank bankUmum = new Bank();
        BankBRI bri = new BankBRI();
        BankBJB bjb = new BankBJB();

        System.out.println("=== Testing Method Overloading (Bank Umum) ===");
        bankUmum.sukuBunga();
        bankUmum.transferUang(500000, "930125048");
        bankUmum.transferUang(1000000, "118093681", "Mandiri");
        bankUmum.transferUang(200000, "749107592", "BRI", "Bayar Makan");

        System.out.println("\n=== Testing Method Overriding (BRI & BJB) ===");
        bri.sukuBunga();
        bri.transferUang(750000, "145720689", "Sembarang Bank");

        bjb.sukuBunga();
        bjb.transferUang(300000, "810472940", "Sembarang Bank");

        System.out.println("\n=== Bonus Challange: Biaya Transfer ===");
        hitungBiayaTransfer("BRI", "BJB", 50000);
        hitungBiayaTransfer("BRI", "BRI", 50000);
    }

    static void hitungBiayaTransfer(String dari, String ke, int jumlah) {
        int biaya = dari.equals(ke) ? 0 : 6500;
        System.out.println("Transfer dari " + dari + " ke " + ke + " dikenakan biaya " + biaya);
        System.out.println("Total yang ditarik dari saldo: Rp" + (jumlah + biaya));
    }
}