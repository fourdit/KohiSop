public class EUR implements IMataUang {
    @Override
    public int tukarRupiah(int harga) { 
        return harga / 14; 
    }

    @Override
    public String getNama() {
        return "EUR";
    }
    
}