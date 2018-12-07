import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PencilTest {

    Pencil pencil;
    Paper paper;

    @Before
    public void setUp() {
        paper = new Paper();
        pencil = new Pencil(paper);
    }

    
    @Test
    public void pencilRecordsStringWhenWritten() {
        pencil.write("hello");
        assertEquals("hello", paper.read());
    }

    
    @Test
    public void pencilRecordsAnyStringWhenWritten() {
        pencil.write("The quick brown fox jumped over the lazy dog.");
        assertEquals("The quick brown fox jumped over the lazy dog.", paper.read());
    }

    
    @Test
    public void pencilConcatenatesTextToRecord() {
        pencil.write("Hello. ");
        pencil.write("My name is Inigo Montoya. ");
        pencil.write("You killed my father. ");
        pencil.write("Prepare to die.");
        assertEquals("Hello. My name is Inigo Montoya. You killed my father. Prepare to die.", paper.read());
    }


    @Test
    public void pencilCanWriteNothing() {
        pencil.write("Winter is coming.");
        pencil.write(null);
        assertEquals("Winter is coming.", paper.read());
    }
}