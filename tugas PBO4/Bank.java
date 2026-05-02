class Bank {
    // 1) Transfer ke rekening lain
    void transferUang(int jumlah, String rekeningTujuan) {
        System.out.println("Transfer Rp" + jumlah + " ke rekening " + rekeningTujuan);
    }

    // 2) Transfer ke rekening lain di bank berbeda
    void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        System.out.println("Transfer Rp" + jumlah + " ke rekening " + rekeningTujuan + " (" + bankTujuan + ")");
    }

    // 3) Transfer dengan tambahan berita
    void transferUang(int jumlah, String rekeningTujuan, String bankTujuan, String berita) {
        System.out.println(
                "Transfer Rp" + jumlah + " ke " + bankTujuan + " [" + rekeningTujuan + "] berita: " + berita);
    }

    // 4) Mencetak suku bunga standar
    void sukuBunga() {
        System.out.println("Suku Bunga standar adalah 3%");
    }
}