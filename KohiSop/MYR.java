public class MYR implements IMataUang {
    @Override
    public int tukarRupiah(int harga) { 
        return harga / 4; 
    }

    @Override
    public String getNama() {
        return  "MYR";
    }
}
