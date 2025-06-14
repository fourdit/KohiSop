abstract class ItemMenu {
    protected String kode;
    protected String nama;
    protected int harga;
    protected int banyak;

    public ItemMenu(String kode, String nama, int harga) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
    }

    public String getKode() {
        return kode;
    }

    public String getNama() {
        return nama;
    }

    public int getHarga() {
        return harga;
    }

    abstract public int getPajak();

    public int getHargaJual() {
        return harga + getPajak();
    }

    public int getBanyak() {
        return banyak;
    }

    @Override
    public String toString() {
        return String.format("%s x%d", this.nama, this.banyak);
    }

    abstract public boolean setBanyak(int banyak);
}