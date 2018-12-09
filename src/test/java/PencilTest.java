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
        assertEquals(187, durablePencil.getPointValue());
    }


    @Test
    public void pencilPointDoesNotDegradeOnWhitespaceChars() {
        softPencil.write("     \n\n\t\t\t\n      \r        ");
        assertEquals(10, softPencil.getPointValue());
    }


    @Test
    public void pencilDegradesByOneWhenPassedNumberOrPunctuation() {
        softPencil.write("12345!?;");
        assertEquals(2, softPencil.getPointValue());
    }


    @Test
    public void pencilWritesSpaceWhenPointIsDull() {
        softPencil.write("WOW! Amazing.");
        assertEquals("WOW! Am      ", paper.read());
    }


    @Test
    public void pencilCanWriteAfterSharpeningDullPoint() {
        softPencil.write("Hello, world! ");
        softPencil.sharpen();
        softPencil.write("Bye, now.");
        assertEquals("Hello, wor    Bye, now.", paper.read());
    }
}