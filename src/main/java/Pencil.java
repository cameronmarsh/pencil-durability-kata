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
            if (currChar >= 65 && currChar <= 90){ //if character is uppercase
                durability -= 2;
            } else if (currChar > 32 && currChar < 127){
                //if character is not uppercase and not whitespace (i.e. lowercase, number, or punctuation)
                durability--;
            }
        }

    }


    public int getDurability () {
        return durability;
    }
}