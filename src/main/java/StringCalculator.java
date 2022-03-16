public class StringCalculator {
  public static void main(String args[]){
    System.out.println("test");
  }

  public int add(String numbers){
    int sum = 0;
    //loop though all the numbers
    for(int i = 0; i < numbers.length();){
      //get length until next separator
      int untilNextSepparator = numbers.substring(i).indexOf(',');
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
