public class Makanan extends ItemMenu implements Comparable<Makanan> {
    public Makanan(String kode, String nama, int harga) {
        super(kode, nama, harga);
    }

    @Override
    public int getPajak() {
        if (this.harga > 50.0) {
            return this.harga * 8 / 100;
        } else {
            return this.harga * 11 / 100;
        }
    }

    @Override
    public boolean setBanyak(int banyak) {
        if (banyak > 2) {
            return false;
        } else {
            this.banyak = banyak;
            return true;
        }
    }

    @Override
    public int compareTo(Makanan makanan) {
        return Integer.compare(this.harga, makanan.harga);
    }
}
