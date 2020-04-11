import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.math.*;

public class PrimoMasGrande {
	static Scanner keyboard=new Scanner(System.in);
	public static void main(String[] args) throws FileNotFoundException {
		int tamano = 0;
		BigInteger number;
		BigInteger prime;
		long number2;
		long prime2;
		int length =0;
		
		
		long initialTime, finalTime;
		
		
		System.out.println("Size of the list of numbers:");
		tamano=keyboard.nextInt();
		String[] lines=readFileLines(tamano);

		for (int i=0; i<lines.length; i++) {
			length=lines[i].length();
			System.out.println(length);
			
			if(length<10) {
				number2= Long.parseLong(lines[i]);
				initialTime=System.currentTimeMillis();
				prime2=previousPrimeLong(number2);
				finalTime=System.currentTimeMillis();
				System.out.println(lines[i]+"\t"+prime2+"\t finding the previous prime took "+(finalTime-initialTime)+"ms");
				
			} else {
				number= new BigInteger(lines[i]);
				initialTime=System.currentTimeMillis();
				prime=fastPreviousPrimeBigInteger(number);
				finalTime=System.currentTimeMillis();
				System.out.println(lines[i]+"\t"+prime+"\t finding the previous prime took "+(finalTime-initialTime)+"ms");
			}
		
		}	
	}
	
	/* 
	 * *************************************** FOR LONG ***************************************
	 */
	
	/* 
	 * For Long
	 * Search of previous prime number with Trial Division algorithm
	 */
	public static long previousPrimeLong(long input) {
		boolean found = false;
		
		while (!found) {
			found = isPrimeLong(input);
			if (!found) input--;
		}
		return input;
	}
	/* 
	 * For Long
	 * Test if prime with Trial Division algorithm
	 */
	public static boolean isPrimeLong(long input) {
		boolean prime = true;

		if (input>2 && input%2==0) prime=false;
		
		long last = (long)Math.sqrt(input) + 1;
		for (long i=3; i<last ; i+=2) { 
		
			if (input%i == 0) prime=false;
		}
		return prime;
	} 
	
	/* 
	 * *************************************** FOR BIG INTEGER SLOW ***************************************
	 */
	/* 
	 * For BigInteger
	 * Slow version: double while
	 */
	public static BigInteger slowPreviousPrimeBigInteger(BigInteger input) {
		
		boolean prime = true;
		boolean found = false;
		BigInteger counter = new BigInteger(input.toString());
		BigInteger previous_prime = BigInteger.TWO;
		
		while (counter.compareTo(BigInteger.TWO) == 1 && !found) {
			prime = true;
			BigInteger counter2 = BigInteger.TWO;
			
			while (counter2.compareTo(counter) == -1 && prime) {
				BigInteger result = counter.mod(counter2);
				if (result.compareTo(BigInteger.ZERO) == 0) prime = false;
				counter2=counter2.add(BigInteger.ONE);
			}
			if (prime) {
				previous_prime = counter;
				found = true;
			}
			counter=counter.subtract(BigInteger.ONE);
		}
		return previous_prime;
	}
	
	/* 
	 * *************************************** FOR BIG INTEGER FAST ***************************************
	 * 
	 */		
	/* 
	 * For BigInteger
	 * Fast version: double while
	 * Problem: the FOR loop that we did with longs doesn't work with BigInteger, we use double while
	 */
	public static BigInteger fastPreviousPrimeBigInteger(BigInteger input) {
		boolean found = false;
		boolean prime;
		BigInteger counter = new BigInteger("3");
		BigInteger previous_prime = BigInteger.TWO;
		BigInteger i = input;
		
		if (input.compareTo(BigInteger.ONE) == 0) {
			found = true;
			previous_prime = BigInteger.ONE;
		}
		if (input.compareTo(BigInteger.TWO) == 0) {
			found = true;
			previous_prime = BigInteger.TWO;
		}
		
		while (i.compareTo(BigInteger.TWO) == 1 && !found) {
			prime = true;
			BigInteger div = i.mod(BigInteger.TWO);
			
			if (div.compareTo(BigInteger.ZERO) == 0) prime = false;
			
			BigInteger last = i.sqrt();
			
			while ((counter.compareTo(last) <= 0) && prime) {
				div = i.mod(counter);
				if (div.compareTo(BigInteger.ZERO) == 0) prime = false;
				counter = counter.add(BigInteger.TWO);
			}
			
			if (prime) {
				previous_prime = i;
				found = true;
			}
			i=i.subtract(BigInteger.ONE);
		}
		return (previous_prime);
	}
	
	/* 
	 * *************************************** METHODS TO WORK WITH FILES ***************************************
	 * 
	 */
	/* 
	 * Method that read the lines from the file
	 */
	private static String[] readFileLines(int numLines) throws FileNotFoundException {
		String[] result;
		if (numLines<0) numLines=0;
		result = new String[numLines];
		Scanner fichero = new Scanner(new File("Valors.csv"));
		for (int i=0; i<numLines; i++) {
			result[i]=fichero.nextLine();
		}
		fichero.close();
		return result;
	}
}
