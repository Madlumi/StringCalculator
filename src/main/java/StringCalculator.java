public class StringCalculator {
  public static void main(String args[]){
    System.out.println("test");
  }

  //helper function to get next sepparator
  private int getNextSep(String s){
    //list of separators
    char[] sepparators = {',','\n'};
    int next = -1;
    //loop through separatirs
    for(int i = 0; i < sepparators.length; i++){
      int n  = s.substring(i).indexOf(sepparators[i]);
      //make sure to get the most imidiate next separator
      if (n != -1&&(n<next || next == -1)){
        if(sepparators[i]=='\n')n+=1;
        next = n;
      }
    }
    return next;
  }
  
  public int add(String numbers){
    int sum = 0;

    //loop though all the numbers
    for(int i = 0; i < numbers.length();){
      //get length until next separator
      int untilNextSepparator = getNextSep( numbers.substring(i) );
      //check if there is a next separator, then add the number
      if(untilNextSepparator>0){
        sum+=Integer.parseInt(numbers.substring(i,i+untilNextSepparator));
        i += untilNextSepparator+1;
      }else{
        sum+=Integer.parseInt(numbers.substring(i));
        break;
      }
    }
    return sum;
  }
}
