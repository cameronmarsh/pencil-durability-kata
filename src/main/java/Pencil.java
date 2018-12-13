public class Pencil {

    private Paper paper;
    private int pointDurability;
    private int pointValue;
    private int length;
    private int eraserIntegrity;


    public Pencil(Paper paper, int pointDurability, int length, int eraserDurability) {
        this.paper = paper;
        this.pointDurability = pointDurability;
        this.pointValue = pointDurability;
        this.length = length;
        this.eraserIntegrity = eraserDurability;
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
            pointValue -= getDegredationValue(currChar);
        }

    }

    private int getDegredationValue(char character) {
        if (character >= 65 && character <= 90){ //if character is uppercase
            return 2;
        } else if (character > 32 && character < 127){
            //if character is not uppercase and not whitespace (i.e. lowercase, number, or punctuation)
            return 1;
        }

        return 0;
    }


    public int getPointValue() {
        return pointValue;
    }


    public int getLength() {
        return length;
    }


    public int getEraserIntegrity() {
        return eraserIntegrity;
    }


    public void sharpen() {
        if(length != 0) {
            pointValue = pointDurability;
            length--;
        }
    }


    public void erase(String text){
        StringBuilder print = paper.getPrint();

        int lastInd = print.lastIndexOf(text);
        if(lastInd == -1) { //the string has not been written
            return;
        }

        for(int i = text.length()-1; i >= 0; i--){
            if(eraserIntegrity == 0){
                return;
            }

            if(getDegredationValue(text.charAt(i)) > 0){
                print.setCharAt(lastInd + i, ' ');
                eraserIntegrity--;
            }
        }

    }


    public void edit(String replacement, int index) {
        if(index < 0 || index >= paper.read().length()){
            return;
        }

        StringBuilder print = paper.getPrint();
        for(int i = 0; i < replacement.length(); i++){
            if(index + i >= print.length()){ //if there are no characters to replace, append the rest of the string
                print.append(replacement.substring(i));
                return;
            }
            if(getDegredationValue(print.charAt(index + i)) > 0){ //if the character is not whitespace
                print.setCharAt(index + i, '@');
            } else {
                print.setCharAt(index + i, replacement.charAt(i));
            }
        }

    }
}