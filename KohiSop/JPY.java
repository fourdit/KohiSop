public class JPY implements IMataUang {
    @Override
    public int tukarRupiah(int harga) { 
        return harga * 10;
    }

    @Override
    public String getNama() {
        return "JPY";
    }
    
}