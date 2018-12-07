public class Pencil {
    private StringBuilder paper;

    public Pencil() {
        this.paper = new StringBuilder();
    }

    public String write(String text){
        paper.append(text);
        return paper.toString();
    }
}
