import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PaperTest {

    Paper paper;

    @Before
    public void setUp() {
        paper = new Paper();
    }


    @Test
    public void canReadEmptyPage() {
        assertEquals("", paper.read());
    }


    @Test
    public void canReadNonEmptyPage() {
        paper.append("Hello, world!");
        assertEquals("Hello, world!", paper.read());
    }
}
