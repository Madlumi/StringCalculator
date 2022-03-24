import java.util.Scanner;

public class StringCalculator {


  public class logStub implements StringCalculator.Logger {
    @Override
    public void log(Integer number) {
    }
  }
  public StringCalculator(Logger logger) {
    this.logger = logger;
  }


  public static String[] welcomeText = {
          "Welcome to a string calculator!",
          "Enter <scalc> followed by your numbers within <'> separated by a <,> ,",
          "and they will be summed together!"

  };
  public static void main(String args[]){
    for(int i = 0 ; i < welcomeText.length; i++){
      System.out.println(welcomeText[i]);
    }

    StringCalculator sc= new StringCalculator(null);
    Scanner scan = new Scanner(System.in);

    while(true){
      String input = scan.nextLine();
      if(input.indexOf("scalc")!=-1){
        try {
          int res = sc.add(input.substring(
                  input.indexOf("'")+1,
                  input.indexOf("'")+1+ input.substring(input.indexOf("'")+1).indexOf("'")
          ));

          System.out.println("The result is " + res);
        } catch (Exception e){
          System.err.println(e);
        }


      }else if(input.length()<=0){
        return;
      }
    }
  }


  public interface Logger { public void log(Integer number); }


  private final Logger logger;

  //helper function to get next sepparator
  private int getNextSep(String s,String[] sepparators){
    //list of separators
    int next = -1;
    //loop through separatirs
    for(int i = 0; i < sepparators.length; i++){
      int n  = s.indexOf( sepparators[i] );
      //make sure to get the most imidiate next separator
      if (n != -1&&(n<next || next == -1)){
        //if(sepparators[i]=='\n')n+=1;
        next = n;

      }
    }
    return next;
  }
  private int getNextSepLen(String s,String[] sepparators){
    //list of separators
    int next = -1;
    //loop through separatirs
    for(int i = 0; i < sepparators.length; i++){
      int n  = s.indexOf( sepparators[i] );
      //make sure to get the most imidiate next separator
      if (n != -1&&(sepparators[i].length()>next || next == -1)){
        //if(sepparators[i]=='\n')n+=1;
        next = sepparators[i].length();

      }
    }
    return next;
  }



  public int add(String numbers) throws Exception {
    int sum = 0;
    String[] sepparators = new String[] {",","\n"};
    //check if custom seps:
    if(numbers.indexOf("//")!=-1){
      //load string
      String CustDelims = numbers.substring(numbers.indexOf("//")+2,  numbers.indexOf('\n'));
      if(CustDelims.indexOf("[") != -1){
        while(CustDelims.indexOf("[") != -1  && CustDelims.indexOf("]") != -1){
          String nSep = CustDelims.substring(1,CustDelims.indexOf("]"));
          CustDelims  = CustDelims.substring(CustDelims.indexOf("]")+1);

          String[] newSepArr = new String[sepparators.length+1];
          newSepArr[newSepArr.length-1]=nSep;
          System.arraycopy(sepparators, 0, newSepArr, 0, sepparators.length);
          sepparators=newSepArr;
        }
      }else {
        sepparators = new String[] {CustDelims,",","\n"};
      }
      //for(int i  = 0; i < sepparators.length; i++){System.err.println(sepparators[i]);}



      numbers=numbers.substring(numbers.indexOf('\n')+1);
    }

    //loop though all the numbers
    for(int i = 0; i < numbers.length();){
      //get length until next separator
      int untilNextSepparator = getNextSep( numbers.substring(i) ,sepparators);
      int sepLen = getNextSepLen( numbers.substring(i) ,sepparators);
      //check if there is last
      int j;
      if(untilNextSepparator>0){
        j = Integer.parseInt(numbers.substring(i,i+untilNextSepparator));
        i += untilNextSepparator+getNextSepLen( numbers.substring(i) ,sepparators);
      }else{
        j=Integer.parseInt(numbers.substring(i));
        i = numbers.length();
      }

      //check if < 0
      if(j<0){
        throw new Exception("Negatives not allowed - " + j);
      }else if(j>1000){
        logger.log(j);
      }
      //add to sum
      sum+=j;
    }
    return sum;
  }
}
