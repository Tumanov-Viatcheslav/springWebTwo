package alkaz.springwebtwo.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductTest {
    @Test
    public void testMakeOk(){
        Assertions.assertDoesNotThrow(()->new Product(1, "овсянка",70));
    }
    @Test
    public void testMakeBad(){
        Assertions.assertThrows(Exception.class,()->new Product(1, "овсянка",-70));
    }
}
