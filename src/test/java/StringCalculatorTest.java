import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
  public void testAddMisc(){
    assertEquals(100,sc.add("99,1"));
    assertEquals(0,sc.add("0,0"));
    assertEquals(0,sc.add("-100,100"));
    assertEquals(-100,sc.add("-100"));
    assertEquals(0,sc.add("0"));
  }
}
