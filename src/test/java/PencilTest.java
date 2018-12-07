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
        assertEquals("Winter is coming!", pencil.write("Winter is coming!"));
    }
}