public class Pencil {
    private Paper paper;
    private int durability;


    public Pencil() {

    }


    public Pencil(Paper paper) {
        this.paper = paper;
    }


    public Pencil(Paper paper, int durability) {
        this.paper = paper;
        this.durability = durability;
    }


    public void write(String text) {
        if (paper == null || text == null) {
            return;
        }

        paper.append(text);

        for (int i = 0; i < text.length(); i++) {
            char currChar = text.charAt(i);
            if (currChar >= 65 && currChar <= 90){
                durability -= 2;
            } else if (currChar > 32 && currChar < 127){
                durability--;
            }
        }

    }


    public int getDurability () {
        return durability;
    }
}