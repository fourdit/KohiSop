public class Minuman extends ItemMenu {
    public Minuman(String kode, String nama, int harga) {
        super(kode, nama, harga);
    }

    @Override
    public int getPajak() {
        if (this.harga < 50.0) {
            return 0;
        } else if (this.harga >= 50 && this.harga < 55) {
            return this.harga * 8 / 100;
        } else {
            return this.harga * 11 / 100;
        }
    }

    @Override
    public boolean setBanyak(int banyak) {
        if (banyak > 3) {
            return false;
        } else {
            this.banyak = banyak;
            return true;
        }
    }
}
