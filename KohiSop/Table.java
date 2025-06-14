public class Table {

    public String table = "";
    public int[][] width = {
            { 64 },
            { 37, 15 },
            { 8, 38, 12 },
            { 36, 10, 10 }
    };

    public void addEntry(String s1, int i1) {
        this.table += String.format(
            "%-" + width[1][0] + "s %-" + width[1][1] + "d\n", s1, i1);
    }

    public void addEntry(String s1, int i1, int i2) {
        this.table += String.format(
            "%-" + width[3][0] + "s %-" + width[3][1] + "d %" + width[3][2] + "d\n", 
            s1, i1, i2);
    }

    public void addEntry(String s1, String s2, int i1) {
        this.table += String.format(
            "%-" + width[2][0] + "s %-" + width[2][1] + "s %-" + width[2][2] + "d\n", 
            s1, s2, i1);
    }

    public void addHeader(String s1, String s2) {
        this.table += addSeparator();
        this.table += String.format(
            "%-" + width[1][0] + "s %-" + width[1][1] + "s\n", s1, s2);
        this.table += addSeparator();
    }

    public void addHeader(String s1, String s2, String s3) {
        this.table += addSeparator();
        this.table += String.format("%-" + width[2][0] + "s %-" + width[2][1] + "s %-" + width[2][2] + "s\n", s1,
                s2, s3); 
        this.table += addSeparator();
    }

    public String addSeparator() {
        String temp = "";
        for (int i = 0; i < this.width[0][0]; i++) {
            temp += "-";
        }
        temp += "\n";
        return temp;
    }

    public void printTable() {
        System.out.print(table);
        System.out.print(addSeparator());
    }

}