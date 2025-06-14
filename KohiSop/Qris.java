public class Qris implements IPembayaran {
    private int saldo;

    public Qris(int saldo) {
        this.saldo = saldo;
    }

    public int getSaldo() {
        return saldo;
    }

    @Override
    public int hitungDiskon(int harga) {
        return harga * 5 / 100;
    }
}

