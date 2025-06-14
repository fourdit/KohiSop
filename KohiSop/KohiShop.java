import java.util.Queue;
import java.util.Stack;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class KohiShop {
    private static Scanner prompt = new Scanner(System.in);
    private static Queue<Order> orderQueue = new LinkedList<Order>();
    private static ArrayList<Member> memberDB = new ArrayList<Member>();

    public static void main(String[] args) {
        
        while (true) {
            Order pesananBaru = buatOrder();

            pilihMenu(pesananBaru);
            
            pesananBaru.setMataUang(pilihMataUang());
            pesananBaru.setPembayaran(pilihPembayaran());
    
            pesananBaru.printKwitansi();

            tungguInput();

            orderQueue.add(pesananBaru);

            if (orderQueue.size() == 3) {
                prosesOrder();
            }
        }

    }

    private static void tungguInput() {
        System.out.print("Tekan <Enter> untuk lanjut ...");
        prompt.nextLine();
    }

    private static void prosesOrder() {
        S.clear();
        PriorityQueue<Makanan> antrianMakanan = new PriorityQueue<Makanan>();
        Stack<Minuman> antrianMinuman = new Stack<Minuman>();
        for (Order order : orderQueue) {
            for (ItemMenu item : order.getOrderedItems()) {
                if (item instanceof Makanan) {
                    antrianMakanan.add((Makanan)item);
                } else {
                    antrianMinuman.add((Minuman)item);
                }
            }
        }

        System.out.print("Antrian makanan di dapur: " + antrianMakanan.poll());
        for (Makanan item : antrianMakanan) {
            System.out.printf(", %s", item);
        }
        System.out.println();

        System.out.print("Antrian makanan di dapur: " + antrianMakanan.poll());
        for (Makanan item : antrianMakanan) {
            System.out.printf(", %s", item);
        }
        System.out.println();
        tungguInput();
    }

    private static Order buatOrder() {
        S.clear();
        Member pembeli = null;
        System.out.print("Masukkan nama: ");
        String namaPembeli = prompt.nextLine();
        for (Member member : memberDB) {
            if (member.getNama().equalsIgnoreCase(namaPembeli)) {
                pembeli = member;
            }
        }
        
        if (pembeli == null) {
            pembeli = new Member(namaPembeli);
            memberDB.add(pembeli);
        }

        Order orderBaru = new Order(pembeli);
        return orderBaru;
    }

    private static void pilihMenu(Order pesanan) {
        S.clear();
        while (true) {
            pesanan.printMenu();

            System.out.print("Masukkan kode menu (atau 'CC' untuk batalkan): ");
            String kode = prompt.nextLine().toUpperCase().trim();

            if (kode.equals("CC")) {
                System.out.println("Pesanan dibatalkan. Program dihentikan.");
                System.exit(0);
            }

            System.out.print("Masukkan jumlah porsi (default 1, 0/S = batal): ");
            String inputBanyak = prompt.nextLine().trim();

            if (inputBanyak.equals("S") || inputBanyak.equals("0")) {
                continue;
            }

            if (inputBanyak.isEmpty()) {
                inputBanyak = "1";
            }

            int banyakPorsi = 1;
            try {
                banyakPorsi = Integer.parseInt(inputBanyak);
            } catch (NumberFormatException e) {
                System.out.println("Input bukan bilangan. Membatalkan pemesanan item");
                continue;
            }

            boolean orderStatus = pesanan.tambahOrder(kode, banyakPorsi);
            if (!orderStatus) {
                System.out.println("Pesanan dibatalkan.");
                continue;
            }

            S.clear();
            System.out.println("\nPesanan saat ini:");
            pesanan.printOrder();

            System.out.print("Tambah item lagi? (y/N): ");
            String lanjut = prompt.nextLine().trim();
            if (lanjut.equalsIgnoreCase("N")) {
                break;
            }
        }
    }

    private static IMataUang pilihMataUang() {
        IMataUang mataUang = null;

        while (mataUang == null) {
            S.clear();
            System.out.println();
            System.out.println("Pilih metode pembayaran:");
            System.out.println("1. USD\n2. JPY\n3. MYR\n4. EUR");
            System.out.print("Masukkan pilihan (1-4): ");

            int pilihan = 0;
            try {
                pilihan = prompt.nextInt();
                prompt.nextLine();
            } catch (NumberFormatException e) {
                System.out.println("Input bukan bilangan. Membatalkan pemesanan item");
                continue;
            }
            
            switch (pilihan) {
                case 1:
                    mataUang = new USD();
                    break;
                case 2:
                    mataUang = new JPY();
                    break;
                case 3:
                    mataUang = new MYR();
                    break;
                case 4:
                    mataUang = new EUR();
                    break;
                default:
                    System.out.println("Input salah. Coba lagi");
                    break;
            }
        }

        return mataUang;
    }

    private static IPembayaran pilihPembayaran() {
        IPembayaran pembayaran = null;

        while (pembayaran == null) {
            S.clear();
            System.out.println();
            System.out.println("Pilih metode pembayaran:");
            System.out.println("1. Tunai\n2. Qris\n3. Emoney");
            System.out.print("Masukkan pilihan (1-3): ");
    
            int pilihan = 0;
            try {
                pilihan = prompt.nextInt();
                prompt.nextLine();
            } catch (NumberFormatException e) {
                System.out.println("Input bukan bilangan. Membatalkan pemesanan item");
                continue;
            }
            
            switch (pilihan) {
                case 1:
                    pembayaran = new Tunai();
                    break;
                case 2:
                    System.out.print("Masukkan saldo anda: ");
                    pembayaran = new Qris(prompt.nextInt());
                    break;
                case 3:
                    System.out.print("Masukkan saldo anda: ");
                    pembayaran = new Emoney(prompt.nextInt());
                    break;            
                default:
                    System.out.println("Input salah. Coba lagi");
                    break;
            }   
        }
        System.out.println();

        return pembayaran;
    }
}
