import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PencilTest {

    private Pencil defaultPencil;
    private Pencil softPencil;
    private Pencil durablePencil;
    private Pencil reallyShortPencil;
    private Paper paper;


    @Before
    public void setUp() {
        paper = new Paper();
        defaultPencil = new Pencil(paper, 1000, 30, 100);
        softPencil = new Pencil(paper, 10, 30, 100);
        durablePencil = new Pencil(paper, 200, 30, 100);
        reallyShortPencil = new Pencil(paper, 20, 2, 5);
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
        Pencil pencilWithDurability = new Pencil(paper, 200, 5, 3);
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


    @Test
    public void pencilRestoresPointValueAfterSharpening() {
        durablePencil.write("I am getting duller by the character.");
        assertEquals(168, durablePencil.getPointValue());
        durablePencil.sharpen();
        assertEquals(200, durablePencil.getPointValue());
    }


    @Test
    public void pencilGetsShorterAfterSharpening() {
        reallyShortPencil.write("blah blah blah");
        reallyShortPencil.sharpen();
        assertEquals(1, reallyShortPencil.getLength());
    }


    @Test
    public void pencilDoesNotSharpenAfterLengthIsZero() {
        reallyShortPencil.sharpen();
        reallyShortPencil.sharpen();

        reallyShortPencil.write("I am a short pencil.");
        reallyShortPencil.sharpen();

        assertEquals(0, reallyShortPencil.getLength());
        assertEquals(3, reallyShortPencil.getPointValue());
    }


    @Test
    public void pencilDoesNotWriteAfterLengthIsZeroAndHasADullPoint() {
        for(int i = 0; i < 3; i++){
            reallyShortPencil.write("Hello, there. ");
            reallyShortPencil.sharpen();
        }

        reallyShortPencil.write("Hello, there. ");
        assertEquals("Hello, there. Hello, there. Hello, there. Hello,        ", paper.read());
    }


    @Test
    public void pencilCanEraseASingleWord() {
        defaultPencil.write("yo");
        defaultPencil.erase("yo");
        assertEquals("  ", paper.read());
    }


    @Test
    public void pencilCanEraseSingleWordMultipleTimes() {
        defaultPencil.write("Show me how you do that.");
        defaultPencil.erase("how");
        assertEquals("Show me     you do that.", paper.read());
        defaultPencil.erase("how");
        assertEquals("S    me     you do that.", paper.read());
    }


    @Test
    public void pencilDoesNothingWhenTryingToEraseNonexistentWord() {
        defaultPencil.write("Welcome to my dojo.");
        defaultPencil.erase("banana");
        assertEquals("Welcome to my dojo.", paper.read());
    }


    @Test
    public void eraserDegradesOnNonWhitespaceCharacters() {
        reallyShortPencil.write("It's a beautiful day!");
        reallyShortPencil.erase("a");
        assertEquals("It's a beautiful d y!", paper.read());
        reallyShortPencil.erase("a");
        assertEquals("It's a be utiful d y!", paper.read());
        assertEquals(3, reallyShortPencil.getEraserIntegrity());
    }


    @Test
    public void pencilDoesNotEraseWhenEraserValueIsZero() {
        reallyShortPencil.write("What's up dog?!!!");
        reallyShortPencil.erase("'s");
        reallyShortPencil.erase("?");
        reallyShortPencil.erase("!!");
        assertEquals(0, reallyShortPencil.getEraserIntegrity());
        reallyShortPencil.erase("dog");
        assertEquals("What   up dog !  ", paper.read());
    }


    @Test
    public void pencilErasesTextOppositeOfHowItWasWritten() {
        reallyShortPencil.write("Khruangbin rox");
        reallyShortPencil.erase("Khruangbin");
        assertEquals("Khrua      rox", paper.read());
    }


    @Test
    public void pencilCanEditExistingText() {
        defaultPencil.write("Hola,     amigos");
        defaultPencil.edit("mis", 6);
        assertEquals("Hola, mis amigos", paper.read());
    }


    @Test
    public void pencilCanEditTextAtGivenIndex() {
        defaultPencil.write("aaaaa   aaaa");
        defaultPencil.edit("bbb", 5);
        assertEquals("aaaaabbbaaaa", paper.read());
    }


    @Test
    public void pencilDoesNotEditTextWithBadIndex() {
        defaultPencil.write("123");
        defaultPencil.edit("4567", 3);
        assertEquals("123", paper.read());
    }


    @Test
    public void pencilReplacesNonWhitespaceCharactersWithAtSymbol() {
        defaultPencil.write("One ring to rule them all.");
        defaultPencil.edit("ORANGE", 7);
        assertEquals("One rin@R@@G@ule them all.", paper.read());
    }


    @Test
    public void pencilAppendsRestOfReplacementWordIfLongerThanExistingPrint() {
        defaultPencil.write("My precious...");
        defaultPencil.edit("bicycle?", 12);
        assertEquals("My precious.@@cycle?", paper.read());
    }


    //Non-requirement tests

    @Test
    public void pencilCanChangePaper() {
          defaultPencil.write("Hahahaha that's so funny!");
          Paper newSheet = new Paper();
          defaultPencil.setPaper(newSheet);
          defaultPencil.write("This is serious.");
          assertEquals("Hahahaha that's so funny!", paper.read());
          assertEquals("This is serious.", defaultPencil.getPaper().read());
    }


    @Test
    public void pencilEraserIsCaseSensitive() {
        defaultPencil.write("Well, well, well. What do we have here?");
        defaultPencil.erase("well");
        defaultPencil.erase("well");
        defaultPencil.erase("well");
        assertEquals("Well,     ,     . What do we have here?", paper.read());
    }


    @Test
    public void pencilWillNotWriteCapitalLetterWithPointValueOne() {
        softPencil.write("WWWWaDdup?!?!?");
        assertEquals("WWWWa d       ", paper.read());
    }
}