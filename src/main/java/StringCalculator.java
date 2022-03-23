
public class StringCalculator {

  public StringCalculator(Logger logger) {
    this.logger = logger;
  }

  public static void main(String args[]){



  }


  public interface Logger { public void log(Integer number); }


  private final Logger logger;

  //helper function to get next sepparator
  private int getNextSep(String s,char[] sepparators){
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



  public int add(String numbers) throws Exception {
    int sum = 0;
    char[] sepparators;
    //check if custom seps:
    if(numbers.indexOf("//")!=-1){
      sepparators = new char[] {numbers.charAt(2),',','\n'};
      numbers=numbers.substring(numbers.indexOf('\n')+1);
    }else{
      //baseic seps
      sepparators = new char[] {',','\n'};
    }

    //loop though all the numbers
    for(int i = 0; i < numbers.length();){
      //get length until next separator
      int untilNextSepparator = getNextSep( numbers.substring(i) ,sepparators);
      //check if there is last
      int j;
      if(untilNextSepparator>0){
        j = Integer.parseInt(numbers.substring(i,i+untilNextSepparator));
        i += untilNextSepparator+1;
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
