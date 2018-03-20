package sizeyunsuan;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack; 
/** 
 * ����ջ����������������� 
 * ������ջ��ʵ��������ȣ�һ��ջ����������Ҫ���������numStack,һ����������������ȷ�priStack 
 * 
 * �����㷨ʵ��˼·Ϊ���õ�ǰȡ�õ��������priStackջ��������Ƚ����ȼ��������ڣ�����Ϊ�������㣬����ջ���� 
 * �����ڣ���Ϊ�����ں��棬���Ի����㣬����ջ��Ԫ�س�ջ��ȡ�����������㣻 
 * ��С�ڣ���ͬ��ȡ��ջ��Ԫ�����㣬������������ջ���������ȼ�'(' > '*' = '/' > '+' = '-' > ')' 
 * 
 */ 
public class Main {  
 private Stack<Character> priStack = new Stack<Character>();// ������ջ  
 private Stack<Integer> numStack = new Stack<Integer>();;// ������ջ     
 
 /** 
  * ������Ҫ�������ַ��������ؼ�����(�˴���Ϊʱ�����⣬ʡ�ԺϷ�����֤) 
  * @param str ��Ҫ���м����ı��ʽ 
  * @return ������ 
  */ 
 public int caculate(String str) {  
  // 1.�ж�string������û�зǷ��ַ�  
  String temp;// ������ʱ��Ŷ�ȡ���ַ�  
  // 2.ѭ����ʼ�����ַ��������ַ��������꣬�ҷ���ջΪ��ʱ����������  
  StringBuffer tempNum = new StringBuffer();// ������ʱ��������ַ���(��Ϊ��λ��ʱ)  
  StringBuffer string = new StringBuffer().append(str);// �������棬���Ч��  
   
  while (string.length() != 0) {  
   temp = string.substring(0, 1);  
   string.delete(0, 1);  
   // �ж�temp����tempΪ������ʱ  
   if (!isNum(temp)) {  
    // 1.��ʱ��tempNum�ڼ�Ϊ��Ҫ����������ȡ������ѹջ���������tempNum  
    if (!"".equals(tempNum.toString())) {  
     // �����ʽ�ĵ�һ������Ϊ����  
     int num = Integer.parseInt(tempNum.toString());  
     numStack.push(num); 
     tempNum.delete(0, tempNum.length());  
    }  
    // �õ�ǰȡ�õ��������ջ��������Ƚ����ȼ��������ڣ�����Ϊ�������㣬����ջ���������ڣ���Ϊ�����ں��棬���Ի����㣬����ջ��Ԫ�س�ջ��ȡ�����������㣻  
    // ��С�ڣ���ͬ��ȡ��ջ��Ԫ�����㣬������������ջ��  
   
    // �жϵ�ǰ�������ջ��Ԫ�����ȼ���ȡ��Ԫ�أ����м���(��Ϊ���ȼ�����С��ջ��Ԫ�أ���С�ڵڶ���Ԫ�صȵȣ���Ҫ��ѭ���ж�)  
    while (!compare(temp.charAt(0)) && (!priStack.empty())) { 
     int a = (int) numStack.pop();// �ڶ���������  
     int b = (int) numStack.pop();// ��һ��������  
     char ope = priStack.pop();  
     int result = 0;// ������  
     switch (ope) {  
     // ����ǼӺŻ��߼��ţ���  
     case '+':  
      result = b + a;  
      // ������������������ջ  
      numStack.push(result);  
      break;  
     case '-':  	 
      result = b - a;  
      if(result < 0){
    	 return -1;
      }
      // ������������������ջ  
      numStack.push(result);  
      break;  
     case '*':  
      result = b * a;  
      // ������������������ջ  
      numStack.push(result);  
      break;  
     case '/':  
      if(b%a != 0){
    	  return -1;
      }
      result = b / a;// ������������������ջ  
      numStack.push(result);  
      break;  
     }  
   
    }  
    // �жϵ�ǰ�������ջ��Ԫ�����ȼ��� ����ߣ����ߵ���ƽ��������󣬽���ǰ�������ţ����������ջ  
    if (temp.charAt(0) != '#') {  
     priStack.push(new Character(temp.charAt(0)));  
     if (temp.charAt(0) == ')') {// ��ջ��Ϊ'('������ǰԪ��Ϊ')'ʱ�����������������꣬ȥ������  
      priStack.pop();  
      priStack.pop();  
     }  
    }  
   } else 
    // ��Ϊ�ǲ�����ʱ�����֣�  
    tempNum = tempNum.append(temp);// ����������һλ���ӵ��Զ���������(�����Ǹ�λ����ʱ��)  
  }  
  return numStack.pop();  
 }  
  
 
 /** 
  * �жϴ�����ַ��ǲ���0-9������ 
  * 
  * @param str 
  * ������ַ��� 
  * @return 
  */ 
 private boolean isNum(String temp) {  
  return temp.matches("[0-9]");  
 }  
   
 /** 
  * �Ƚϵ�ǰ��������ջ��Ԫ�ز��������ȼ��������ջ��Ԫ�����ȼ��ߣ��򷵻�true�����򷵻�false 
  * 
  * @param str ��Ҫ���бȽϵ��ַ� 
  * @return �ȽϽ�� true�����ջ��Ԫ�����ȼ��ߣ�false�����ջ��Ԫ�����ȼ��� 
  */ 
 private boolean compare(char str) {  
  if (priStack.empty()) {  
   // ��Ϊ��ʱ����Ȼ ��ǰ���ȼ���ͣ����ظ�  
   return true;  
  }  
  char last = (char) priStack.lastElement();  
  // ���ջ��Ϊ'('��Ȼ�����ȼ���ͣ�')'������Ϊջ����  
  if (last == '(') {  
   return true;  
  }  
  switch (str) {  
  case '#':  
   return false;// ������  
  case '(':  
   // '('���ȼ����,��Ȼ����true  
   return true;  
  case ')':  
   // ')'���ȼ���ͣ�  
   return false;  
  case '*': {  
   // '*/'���ȼ�ֻ��'+-'��  
   if (last == '+' || last == '-')  
    return true;  
   else 
    return false;  
  }  
  case '/': {  
   if (last == '+' || last == '-')  
    return true;  
   else 
    return false;  
  }  
   // '+-'Ϊ��ͣ�һֱ����false  
  case '+':  
   return false;  
  case '-':  
   return false;  
  }  
  return true;  
 }  
 
//��������ʽ
 private static String generateCal() {
   int symNum = (int)(Math.random()*3)+3;       //�������3-5֮��ķ�����
     
    //�������������� 
     char[] c ={'+','-','*','/'};   //����������ż�
     Random random=new Random();     
     for(int i = 0;i < symNum;i++){
    	 int index=random.nextInt(c.length); //�õ�һ������±�
    	 symbol[i]=c[index];
     } 
     
     //��������������� 
     for(int i = 0;i < symNum + 1;i++){
    	 number[i]=(int)(Math.random()*100)+1;; //�õ�һ������±�    	 
     }
    // System.out.println(Arrays.toString(number));    
     
     //�������ʽ
     String cal = "";int i;
     for( i = 0;i < symNum;i++)
     {
    	 cal = cal + String.valueOf(number[i]) + String.valueOf(symbol[i]);
     }
     cal = cal + String.valueOf(number[i]);
    // System.out.println (cal);
     return cal;
 }
 
 private static char[] symbol = new char[5];
 private static int[] number = new int[6];
 
 public static void main(String args[]) throws IOException {  
	// System.out.println("***������Ҫ�������ĵ���***");
     Scanner s =new Scanner (System.in);
     int n=s.nextInt();   //��������
     
     File fp=new File(".//result.txt");
     PrintWriter pfp= new PrintWriter(fp);
     pfp.print("201571030120\r\n");  //���ѧ��
    
     for(int i = 0;i < n;i++){
      String cal = generateCal();
      //��������ʽ
      Main operate = new Main();  
      int t = operate.caculate(cal+'#');  
      if(t == -1){
       i--;
      }
      else{
       System.out.println(cal+'='+t); 
       pfp.print(cal+'='+t+"\r\n");    	     
       }
     }
     pfp.close();    
 }    
}
