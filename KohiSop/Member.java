import java.util.Random;

public class Member {
    private String nama;
    private String id;
    private int poin;

    public Member(String nama) {
        this.nama = nama;
        this.id = generateId(); 
        this.poin = 0;
    }

    private String generateId() {
        String charGroup = "ABCDEF0123456789";
        Random random = new Random();
        StringBuilder id = new StringBuilder(6);

        for (int i = 0; i < 6; i++) {
            int charIndex = random.nextInt(charGroup.length());
            char randomChar = charGroup.charAt(charIndex);
            id.append(randomChar);
        }
        return id.toString();
    }

    public String getNama() {
        return nama;
    }

    public String getId() {
        return id;
    }

    public int getPoin() {
        return poin;
    }

    public void tambahPoin(int poinBaru) {
        this.poin += poinBaru;
    }

    public boolean hasCodeA() {
        return id.toUpperCase().contains("A");
    }

    public void kurangiPoin(int poinDigunakan) {
        if (poinDigunakan <= poin) {
            this.poin -= poinDigunakan;
        }
    }
}