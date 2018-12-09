public class Pencil {

    private Paper paper;
    private int durability;
    private int pointValue;
    private int length;


    public Pencil(Paper paper, int durability, int length) {
        this.paper = paper;
        this.durability = durability;
        this.pointValue = durability;
        this.length = length;
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


    public int getPointValue() {
        return pointValue;
    }


    public int getLength() {
        return length;
    }


    public void sharpen() {
        if(length != 0) {
            pointValue = durability;
            length--;
        }
    }


    public void erase(String text){
        StringBuilder print = paper.getPrint();
        int lastInd = print.lastIndexOf(text);
        for(int i = 0; i < text.length(); i++){
            print.setCharAt(lastInd + i, ' ');
        }

    }


}