import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class q9 {

    public static int findTotal(int[] numbers) {
        int result = 0;
        for (int i=0; i < numbers.length; i++) {
            result += numbers[i];
        }
        return result;
    }
    public static double findAverage(int[] numbers) {
        double total = (double) findTotal(numbers);
        return total / (double) numbers.length;
    }

    public static double findMedian(int[] numbers, double startIndex, double endIndex) {
        startIndex = (int) (startIndex);
        endIndex = (int) (endIndex + 0.5) - 1;
        int formula = (int) (endIndex - startIndex + 1);
        double median;
        int medianindex = (int) (startIndex + formula/2);
        if ((formula % 2) == 1) {
            median = numbers[medianindex];
        } else {
            median = findAverage(new int[] {numbers[medianindex-1], numbers[medianindex]});
        }
        return median;
    }

    public static void main(String[] args) {
        try {
            File file = new File("numbers.txt");
            Scanner scanner1 = new Scanner(file);
            int numberOfLines = 0;

            while (scanner1.hasNextLine()) {
                scanner1.nextLine();
                numberOfLines += 1;
            }
            scanner1.close();
            
            int[] array = new int[numberOfLines];
            Scanner scanner2 = new Scanner(file);
            int j = 0;
            while (scanner2.hasNextLine()) {
                array[j] = scanner2.nextInt();
                j += 1;
            }
            scanner2.close();

            int sum = findTotal(array);
            double average = findAverage(array);
            double median = findMedian(array, 0, array.length);
            double medianIndex = ((double)(array.length) - 1)/2;
            double q1 = findMedian(array, 0, medianIndex);
            double q3 = findMedian(array, medianIndex+1, array.length);
            
            System.out.println("The sum of the set of numbers is " + sum);
            System.out.println("The average of the set of numbers is " + average);
            System.out.println("The lower quartile of the set of numbers is " + q1);
            System.out.println("The median of the set of numbers is " + median);
            System.out.println("The upper quartile of the set of numbers is " + q3);
            
            int prev = array[0];
            Boolean seen = false;
            boolean anyNumberRepeats = false;
            System.out.println("Numbers that repeat:");
            for (int i=1; i<array.length; i++) {
                if (prev == array[i]) {
                    if (seen == false) {
                        System.out.println(array[i]);
                    }
                    seen = true;
                } else {
                    seen = false;
                }
                prev = array[i];
            }
            if (!anyNumberRepeats) {
                System.out.println("There are no numbers that repeat twice.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}