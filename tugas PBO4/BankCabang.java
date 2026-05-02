class BankBRI extends Bank {
    @Override
    void sukuBunga() {
        System.out.println("Suku Bunga dari BRI adalah : 4%");
    }

    @Override
    void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        // Logika Overriding: bankTujuan diset otomatis menjadi "BRI"
        super.transferUang(jumlah, rekeningTujuan, "BRI");
    }
}

class BankBJB extends Bank {
    @Override
    void sukuBunga() {
        System.out.println("Suku Bunga dari BJB adalah : 4.5%");
    }

    @Override
    void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        // Logika Overriding: bankTujuan diset otomatis menjadi "BJB"
        super.transferUang(jumlah, rekeningTujuan, "BJB");
    }
}