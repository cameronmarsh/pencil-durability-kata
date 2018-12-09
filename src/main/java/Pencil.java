public class Pencil {
    private Paper paper;
    private int durability;
    private int pointValue;


    public Pencil() {

    }


    public Pencil(Paper paper) {
        this.paper = paper;
    }


    public Pencil(Paper paper, int durability) {
        this.paper = paper;
        this.durability = durability;
        this.pointValue = durability;
    }


    public void write(String text) {
        if (paper == null || text == null) {
            return;
        }

        for (int i = 0; i < text.length(); i++) {
            if(pointValue <= 0){
                paper.append(' ');
                continue;
            }

            char currChar = text.charAt(i);
            paper.append(currChar);
            if (currChar >= 65 && currChar <= 90){ //if character is uppercase
                pointValue -= 2;
            } else if (currChar > 32 && currChar < 127){
                //if character is not uppercase and not whitespace (i.e. lowercase, number, or punctuation)
                pointValue--;
            }
        }

    }


    public int getDurability () {
        return durability;
    }


    public int getPointValue() {
        return pointValue;
    }


    public void sharpen() {
        pointValue = durability;
    }
}