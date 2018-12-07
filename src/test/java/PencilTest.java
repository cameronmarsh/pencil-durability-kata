import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PencilTest {
    @Test
    public void pencilRecordsStringWhenWritten() {
        Pencil pencil = new Pencil();
        assertEquals("hello", pencil.write("hello"));
    }

    @Test
    public void pencilRecordsAnyStringWhenWritten() {
        Pencil pencil = new Pencil();
        assertEquals("The quick brown fox jumped over the lazy dog.", pencil.write("The quick brown fox jumped over the lazy dog."));
    }

    @Test
    public void pencilConcatenatesTextToRecord() {
        Pencil pencil = new Pencil();
        pencil.write("Hello. ");
        pencil.write("My name is Inigo Montoya. ");
        pencil.write("You killed my father. ");
        assertEquals("Hello. My name is Inigo Montoya. You killed my father. Prepare to die.", pencil.write("Prepare to die."));
    }
}