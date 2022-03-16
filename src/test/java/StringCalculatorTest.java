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
  public void testAddTwo(){
    assertEquals(3,sc.add("1,2"));
  }
}
