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

    public String read() {
        return print.toString();
    }
}
