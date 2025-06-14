import java.util.ArrayList;

public class Order {

    private ArrayList<ItemMenu> menu = new ArrayList<>();
    private ArrayList<ItemMenu> order = new ArrayList<>();
    private int banyakOrderMinuman;
    private int banyakOrderMakanan;
    private IPembayaran pembayaran;
    private IMataUang mataUang;
    private Member orderer;

    public Order(Member orderer) {
        this.orderer = orderer;

        menu.add(new Minuman("A1", "Caffe Latte", 46));
        menu.add(new Minuman("A2", "Cappucino", 46));
        menu.add(new Minuman("E1", "Caffe Americano", 37));
        menu.add(new Minuman("E2", "Caffe Mocha", 55));
        menu.add(new Minuman("E3", "Caramel Machiato", 59));
        menu.add(new Minuman("E4", "Asian Dolche Latte", 55));
        menu.add(new Minuman("E5", "Double Shots Iced Shaken Espresso", 50));
        menu.add(new Minuman("B1", "Freshly Brewed Coffee", 23));
        menu.add(new Minuman("B2", "Vanilla Sweet Cream Cold Brew", 50));
        menu.add(new Minuman("B3", "Cold Brew", 44));
        
        menu.add(new Makanan("M1", "Pertemania Pizza", 112));
        menu.add(new Makanan("M2", "Mie Rebus Super Mario", 35));
        menu.add(new Makanan("M3", "Ayam Bakar Goreng Rebus Spesial", 72));
        menu.add(new Makanan("M4", "Soto Kambing Iga Guling", 124));
        menu.add(new Makanan("S1", "Singkong Bakar A La Carte", 37));
        menu.add(new Makanan("S2", "Ubi Cilembu Bakar Arang", 58));
        menu.add(new Makanan("S3", "Tempe Mendoan", 18));
        menu.add(new Makanan("S4", "Tahu Bakso Extra Telur", 28));
    }

    public Member getOrderer() {
        return orderer;
    }

    public ArrayList<ItemMenu> getOrderedItems() {
        return order;
    }

    public void setPembayaran(IPembayaran pembayaran) {
        this.pembayaran = pembayaran;
    }

    public void setMataUang(IMataUang mataUang) {
        this.mataUang = mataUang;
    }

    public void printMenu() {
        Table displayMenu = new Table();

        displayMenu.addHeader("Kode", "Minuman", "Harga (Rp)");
        for (ItemMenu item : menu) {
            if (item instanceof Minuman) {
                displayMenu.addEntry(item.getKode(), item.getNama(), item.getHargaJual());
            }
        }

        displayMenu.addHeader("Kode", "Makanan", "Harga (Rp)");
        for (ItemMenu item : menu) {
            if (item instanceof Makanan) {
                displayMenu.addEntry(item.getKode(), item.getNama(), item.getHargaJual());
            }
        }
        displayMenu.printTable();
    }

    public void printOrder() {
        Table displayOrder = new Table();

        displayOrder.addHeader("Kode", "Minuman", "Harga (Rp)");
        for (ItemMenu item : order) {
            if (item instanceof Minuman) {
                displayOrder.addEntry(item.getKode(), item.getBanyak() + "x " + item.getNama(), item.getHargaJual());
            }
        }

        displayOrder.addHeader("Kode", "Makanan", "Harga (Rp)");
        for (ItemMenu item : order) {
            if (item instanceof Makanan) {
                displayOrder.addEntry(item.getKode(), item.getBanyak() + "x " + item.getNama(), item.getHargaJual());
            }
        }
        displayOrder.printTable();
    }

    public boolean tambahOrder(String kode, int banyak) {
        ItemMenu selectedItem = null;
        for (ItemMenu item : menu) {
            if (item.getKode().equalsIgnoreCase(kode)) {
                selectedItem = item;
                break;
            }
        }

        if (selectedItem == null) {
            System.out.println("Kode menu tidak ditemukan. Silakan coba lagi.");
            return false;
        }

        if (!selectedItem.setBanyak(banyak)) {
            System.out.println("Jumlah porsi melebihi batas maksimum item ini.");
            return false;
        }

        if ((selectedItem instanceof Makanan && banyakOrderMakanan >= 5) || (selectedItem instanceof Minuman && banyakOrderMinuman >= 5)) {
            System.out.println("Maaf. Anda sudah mencapai maksimum banyak item menu yang bisa anda pesan.");
            return false;
        }

        order.add(selectedItem);

        if (selectedItem instanceof Minuman) banyakOrderMinuman++;
        else banyakOrderMakanan++;

        return true;
    }
    
    
        public void printKwitansi() {
       
        System.out.println("\n============================================================================");
        System.out.println("                        KWITANSI PEMBAYARAN - KOHISOP                         ");
        System.out.println("============================================================================\n");
    
        System.out.println("------------------------------ DETAIL MEMBER -------------------------------");
        System.out.printf("%-63s %s\n", "Nama Member", orderer.getNama());
        System.out.printf("%-63s %s\n", "Kode Member", orderer.getId());
        System.out.printf("%-63s %d\n", "Poin Sebelumnya", orderer.getPoin());
        System.out.println("----------------------------------------------------------------------------\n");

        int totalMakanan = 0;
        int totalMinuman = 0;
        int totalPajakMinuman = 0;
        int totalPajakMakanan = 0;

        System.out.println("\n\n>>> Daftar Minuman");
        System.out.println("----------------------------------------------------------------------------");
        System.out.printf(" %-5s %-33s %10s %7s %12s\n", "Kode", "Minuman", "Qty", "Harga", "Pajak");
        System.out.println("----------------------------------------------------------------------------");

        for (ItemMenu item : order) {
            if (item instanceof Minuman) {
                int qty = item.getBanyak();
                int harga = item.getHarga();
                int pajak = orderer.hasCodeA() ? 0 : item.getPajak(); 

                System.out.printf(" %-5s %-40s %-4d  Rp %-9d Rp %-11d   \n\n",
                    item.getKode(), item.getNama(), qty, harga, pajak);

                totalMinuman += harga * qty;
                totalPajakMinuman += pajak * qty;
            }
        }

        System.out.println("\n>>> Daftar Makanan");
        System.out.println("----------------------------------------------------------------------------");
        System.out.printf(" %-4s %-33s %10s %7s %12s      \n", "Kode", "Makanan", "Qty", "Harga", "Pajak");
        System.out.println("----------------------------------------------------------------------------");

        for (ItemMenu item : order) {
            if (item instanceof Makanan) {
                int qty = item.getBanyak();
                int harga = item.getHarga();
                int pajak = orderer.hasCodeA() ? 0 : item.getPajak();

                System.out.printf(" %-5s %-40s %-4d  Rp %-9d Rp %-11d   \n\n",
                    item.getKode(), item.getNama(), qty, harga, pajak);

                totalMakanan += harga * qty;
                totalPajakMakanan += pajak * qty;
            }
        }

        System.out.println("\n------------------------------ RINCIAN HARGA -------------------------------");
        System.out.printf("%-63s Rp %-11d\n", "Subtotal minuman (tanpa pajak)", totalMinuman);
        System.out.printf("%-63s Rp %-11d\n", "Pajak minuman", totalPajakMinuman);
        System.out.printf("%-63s Rp %-11d\n", "Subtotal minuman (dengan pajak)", totalMinuman + totalPajakMinuman);
        System.out.printf("%-63s Rp %-11d\n", "Subtotal makanan (sudah termasuk pajak)", totalMakanan + totalPajakMakanan);
    
        int totalTagihanAwal = totalMakanan + totalPajakMakanan + totalMinuman + totalPajakMinuman;
        System.out.println();
        System.out.printf("%-63s Rp %-11d\n", "TOTAL sebelum diskon & admin", totalTagihanAwal);
    
        int diskon = pembayaran.hitungDiskon(totalTagihanAwal);
        int admin = 0;
    
        if (pembayaran instanceof Qris) {
            System.out.printf("%-63s Rp %-11d\n", "Diskon QRIS (5%)", diskon);
        } else if (pembayaran instanceof Emoney) {
            admin = ((Emoney) pembayaran).getBiayaAdmin();
            System.out.printf("%-63s Rp %-11d\n", "Diskon eMoney (7%)", diskon);
            System.out.printf("%-63s Rp %-11d\n", "Biaya Admin eMoney", admin);
        }

        int diskonAdmin = Math.min((orderer.getPoin() * 2), admin);
        int poinYangDigunakan = (admin - diskonAdmin) / 2;
        orderer.kurangiPoin(poinYangDigunakan);
        System.out.printf("%-63s Rp %-11d\n", "Diskon biaya admin", diskonAdmin);
    
        orderer.tambahPoin(totalTagihanAwal / 10);
        System.out.printf("%-63s    %-11d\n", "Poin tersisa", orderer.getPoin());

        int totalAkhir = totalTagihanAwal - diskon + admin;
        System.out.printf("%-63s Rp %-11d\n", "TOTAL setelah diskon & admin", totalAkhir);

        int konversiTotalAwal = mataUang.tukarRupiah(totalTagihanAwal);
        int konversiTotalAkhir = mataUang.tukarRupiah(totalAkhir);
    
        String namaMataUang = mataUang.getNama();
        System.out.println("\n---------------------------- KONVERSI MATA UANG -------------------------");
        System.out.printf("%-63s %s %-11d\n", "Total SEBELUM diskon & admin (" + namaMataUang + ")", namaMataUang, konversiTotalAwal);
        System.out.printf("%-63s %s %-11d\n", "Total SESUDAH diskon & admin (" + namaMataUang + ")", namaMataUang, konversiTotalAkhir);
    
        System.out.println("\n============================================================================");
        System.out.println("                    Terima kasih! Silakan datang kembali.                     ");
        System.out.println("============================================================================\n");

        banyakOrderMinuman = 0;
        banyakOrderMakanan = 0;
    }
    
}
