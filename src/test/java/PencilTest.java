import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PencilTest {
    @Test
    public void pencilRecordsStringWhenWritten() {
        Pencil pencil = new Pencil();
        assertEquals("hello", pencil.write("hello"));
    }
}