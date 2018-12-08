public class Paper {
    private StringBuilder print;

    public Paper() {
        print = new StringBuilder();
    }


    public void append(String text) {
        if(text != null) {
            print.append(text);
        }
    }


    public void append(Character character){
        if(character != null){
            print.append(character);
        }
    }


    public String read() {
        return print.toString();
    }
}
