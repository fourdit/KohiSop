public class USD implements IMataUang {
    @Override
    public int tukarRupiah(int harga) { 
        return harga / 15;
    }

    @Override
    public String getNama() {
        return "USD";
    }
    
}