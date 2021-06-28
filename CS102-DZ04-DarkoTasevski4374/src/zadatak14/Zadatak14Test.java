package zadatak14;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Zadatak14Test {
    @Test
    public void zadatak14Test(){
        int result = Zadatak14.ukloniCifruX(512, 1);
        assertEquals("intX nije 52!", 52, result);

        int result1 = Zadatak14.ukloniCifruX(612, 1);
        assertEquals("intX nije 62!", 62, result1);

        int result2 = Zadatak14.ukloniCifruX(128, 8);
        assertEquals("intX nije 12!", 12, result2);

        int result3 = Zadatak14.ukloniCifruX(516, 5);
        assertEquals("intX nije 16!", 16, result3);

        int result4 = Zadatak14.ukloniCifruX(224, 2);
        assertEquals("intX nije 4!", 4, result4);

        int result5 = Zadatak14.ukloniCifruX(-24, 2);
        assertEquals("intX nije -24!", -24, result5);

        int result6 = Zadatak14.ukloniCifruX(224, 22);
        assertEquals("intX nije 224!", 224, result6);
    }
}
