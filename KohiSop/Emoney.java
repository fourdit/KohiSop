public class Emoney implements IPembayaran {
    private int saldo;

    public Emoney(int saldo) {
        this.saldo = saldo;
    }

    public int getSaldo() {
        return saldo;
    }

    public int getBiayaAdmin() {
        return 20;
    }

    @Override
    public int hitungDiskon(int harga) {
        return harga * 7 / 100;
    }
}
