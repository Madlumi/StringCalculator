import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTest {

  StringCalculator sc= new StringCalculator();

  @Test
  public void testAddNone(){
    assertEquals(0,sc.add(""));
  }
  @Test
  public void testAddOne(){
    assertEquals(1, sc.add("1"));
  }
  @Test
  public void testAddTwo(){
    assertEquals(3,sc.add("1,2"));
  }
  @Test
  public void testMany(){
    assertEquals(15,sc.add("1,2,4,8"));
  }
  @Test
  public void testNewRow(){
    assertEquals(3,sc.add("1\n2"));
  }

  @Test
  public void testNewRowAndComa(){
    assertEquals(7,sc.add("1\n2,4"));
  }


  @Test
  public void testAddMiscNumbers(){
    assertEquals(100,sc.add("99,1"));
    assertEquals(0,sc.add("0,0"));
    assertEquals(0,sc.add("0"));
  }

  @Test
  public void testCustomSeps(){
    assertEquals(7,sc.add("//;\n1;2,4"));
  }
  @Test
  public void testNeg(){
    Throwable e = assertThrows(Exception.class, ()->{sc.add("-1");} );
    assertTrue(e.getMessage().contains("Negatives not allowed"));


  }
}
