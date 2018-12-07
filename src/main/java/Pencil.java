public class Pencil {
    private Paper paper;

    public Pencil() {

    }

    public Pencil(Paper paper) {
        this.paper = paper;
    }

    public void write(String text) {
        if(paper != null) {
            paper.append(text);
        }
    }
}
