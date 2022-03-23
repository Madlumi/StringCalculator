import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static  org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTest {

  public class logStub implements StringCalculator.Logger {
    @Override
    public void log(Integer number) {
    }
  }
  StringCalculator.Logger ml = mock(StringCalculator.Logger.class);
  StringCalculator sc;

  @BeforeEach
  public void initTests(){
    ml = mock(StringCalculator.Logger.class);
    sc= new StringCalculator(ml);
  }





  @Test
  public void testWelcome()throws Exception {
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(stream);

    PrintStream ops = System.out;

    System.setOut(ps);

    String[] args = {""};
    StringCalculator.main(args);

    System.setOut(ops);
    String comp = "";
    for(int i = 0; i < StringCalculator.welcomeText.length; i++){
      comp+=( StringCalculator.welcomeText[i] +"\r\n");
    }

    assertEquals(new String(stream.toByteArray()),comp);
  }




  @Test
  public void testAddNone()throws Exception {
    StringCalculator.Logger ml = mock(StringCalculator.Logger.class);
    StringCalculator sc= new StringCalculator(ml);
    assertEquals(0,sc.add(""));
  }
  @Test
  public void testAddOne()throws Exception {
    assertEquals(1, sc.add("1"));
  }
  @Test
  public void testAddTwo()throws Exception {
    assertEquals(3,sc.add("1,2"));
  }
  @Test
  public void testMany()throws Exception {
    assertEquals(15,sc.add("1,2,4,8"));
  }
  @Test
  public void testNewRow() throws Exception {
    assertEquals(3,sc.add("1\n2"));
  }

  @Test
  public void testNewRowAndComa()throws Exception {
    assertEquals(7,sc.add("1\n2,4"));
  }


  @Test
  public void testAddMiscNumbers() throws Exception {
    assertEquals(100,sc.add("99,1"));
    assertEquals(0,sc.add("0,0"));
    assertEquals(0,sc.add("0"));
  }

  @Test
  public void testCustomSeps() throws Exception {
    assertEquals(7,sc.add("//;\n1;2,4"));
  }
  @Test
  public void testNeg(){
    Throwable e = assertThrows(Exception.class, ()->{sc.add("-1");} );
    assertTrue(e.getMessage().contains("Negatives not allowed"));
  }

  @Test
  public void testTooBig()  throws Exception{
    assertEquals(1001,sc.add("1001"));
    verify(ml,times(1)).log(1001);
  }
  @Test
  public void testTooBig2()  throws Exception{
    assertEquals(2002,sc.add("1001,1001"));
    verify(ml,times(2)).log(1001);
  }
  @Test
  public void testTooBig3()  throws Exception{
    assertEquals(2001,sc.add("1001,1000"));
    verify(ml,times(1)).log(1001);
    verify(ml,times(0)).log(1000);
  }
  @Test
  public void testNotTooBig()  throws Exception{

    assertEquals(1000,sc.add("1000"));
    verify(ml,times(0)).log(1000);

  }
  @Test
  public void testNotTooBig2()  throws Exception{
    assertEquals(2000,sc.add("1000,1000"));
    verify(ml,times(0)).log(1000);

  }
}
