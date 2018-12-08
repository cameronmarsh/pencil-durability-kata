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
        if(paper != null) {
            paper.append(text);
        }
    }
}
