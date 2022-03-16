public class StringCalculator {
  public static void main(String args[]){
    System.out.println("test");
  }

  public int add(String numbers){
    System.out.println(numbers.length());
    int sum = 0;
    for(int i = 0; i < numbers.length();){
      int n = numbers.substring(i).indexOf(',');
      if(n>0){
        sum+=Integer.parseInt(numbers.substring(i,i+n));
        i += n+1;
      }else{
        sum+=Integer.parseInt(numbers.substring(i));
        break;
      }
    }
    return sum;
  }
}
