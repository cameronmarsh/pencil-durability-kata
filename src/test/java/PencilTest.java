import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PencilTest {

    Pencil defaultPencil;
    Pencil softPencil;
    Pencil durablePencil;
    Paper paper;


    @Before
    public void setUp() {
        paper = new Paper();
        defaultPencil = new Pencil(paper, 1000);
        softPencil = new Pencil(paper, 10);
        durablePencil = new Pencil(paper, 200);
    }

    
    @Test
    public void pencilRecordsStringWhenWritten() {
        defaultPencil.write("hello");
        assertEquals("hello", paper.read());
    }

    
    @Test
    public void pencilRecordsAnyStringWhenWritten() {
        defaultPencil.write("The quick brown fox jumped over the lazy dog.");
        assertEquals("The quick brown fox jumped over the lazy dog.", paper.read());
    }

    
    @Test
    public void pencilConcatenatesTextToRecord() {
        defaultPencil.write("Hello. ");
        defaultPencil.write("My name is Inigo Montoya. ");
        defaultPencil.write("You killed my father. ");
        defaultPencil.write("Prepare to die.");
        assertEquals("Hello. My name is Inigo Montoya. You killed my father. Prepare to die.", paper.read());
    }


    @Test
    public void pencilCanWriteNothing() {
        defaultPencil.write("Winter is coming.");
        defaultPencil.write(null);
        assertEquals("Winter is coming.", paper.read());
    }


    @Test
    public void canProvidePointDurabilityToPencilOnInstantiation() {
        Pencil pencilWithDurability = new Pencil(paper, 200);
    }

    @Test
    public void pencilPointDegradesInValueWhenCharactersAreWritten() {
        durablePencil.write("Hello, world!");
        assertEquals(187, durablePencil.getDurability());
    }

    //    @Test
//    public void pencilWritesSpaceWhenPointIsDull() {
//        Pencil softLeadPencil = new Pencil(paper, 10);
//        softLeadPencil.write("Hello, world!");
//        assertEquals("Hello, wor   ", paper.read());
//    }
}