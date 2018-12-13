/**
 * Class representing a graphite pencil object
 */
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


    /**
     * Appends the given string to the pencil's current paper
     * @param text string to append to the paper object
     */
    public void write(String text) {
        if (paper == null || text == null) {
            return;
        }

        for (int i = 0; i < text.length(); i++) {
            char currChar = text.charAt(i);

            if(pointValue <= 0 || pointValue < getDegredationValue(currChar)){
                paper.append(' ');
                continue;
            }

            paper.append(currChar);
            pointValue -= getDegredationValue(currChar);
        }

    }


    /**
     * Calculates the value to degrade a pencil's point after writing the given character.
     * @param character
     * @return 2 if character is uppercase, 1 if lowercase or punctuation, 0 if whitespace
     */
    private int getDegredationValue(char character) {
        if (character >= 65 && character <= 90){ //if character is uppercase
            return 2;
        } else if (character > 32 && character < 127){
            //if character is not uppercase and not whitespace (i.e. lowercase, number, or punctuation)
            return 1;
        }

        return 0;
    }



    /**
     * Reset the pencil's point value to it's maximum and decrease the pencil's length by a value of one
     */
    public void sharpen() {
        if(length != 0) {
            pointValue = pointDurability;
            length--;
        }
    }


    /**
     * Erase the last instance of the given word on the pencil's paper and replace with space characters. Note that the
     * string must match case.
     * @param text substring to erase from paper
     */
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


    /**
     * Replace part of written page with given string at given location. Adds '@' if overlapping with a non-whitespace
     * character
     * @param replacement substring to add in place of existing text
     * @param index starting place in the paper's text to replace
     */
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



    //Getter and setter functions

    public int getPointValue() {
        return pointValue;
    }


    public int getLength() {
        return length;
    }


    public int getEraserIntegrity() {
        return eraserIntegrity;
    }


    public Paper getPaper() {
        return paper;
    }


    public void setPaper(Paper paper) {
        this.paper = paper;
    }





}