import java.util.*;
import java.io.*;

public class SpiralMatrix
{
   public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(",");
        int rows = Integer.parseInt(input[0]);
        int columns = Integer.parseInt(input[1]);
        int[][] numbers = new int[rows][columns];
        
        for (int i = 0; i < rows; i++) {
            String line = sc.nextLine();
            String[] s = line.split(",");
            for (int j = 0; j < columns; j++)
                numbers[i][j] = Integer.parseInt(s[j]);
        }
        
        int fromTop = 0;
        int fromBottom = 0;
        int fromLeft = 0;
        int fromRight = 0;
        int numbersLeft = rows*columns;
        
        while (true) {
            for (int i = fromLeft; i < columns - fromRight; i++) {
                if (numbersLeft == 1)
                    System.out.print(numbers[fromTop][i]);
                else
                    System.out.print(numbers[fromTop][i] + ",");
                numbersLeft--;
            }
            fromTop++;
            if (numbersLeft == 0) break;
            
            for (int j = fromTop; j < rows - fromBottom; j++) {
                if (numbersLeft == 1)
                    System.out.print(numbers[j][columns - fromRight - 1]);
                else
                    System.out.print(numbers[j][columns - fromRight - 1] + ",");
                numbersLeft--;
            }
            fromRight++;
            if (numbersLeft == 0) break;
            
            for (int i = columns - fromRight - 1; i >= fromLeft; i--) {
                if (numbersLeft == 1)
                    System.out.print(numbers[rows - fromBottom - 1][i]);
                else
                    System.out.print(numbers[rows - fromBottom - 1][i] + ",");
                numbersLeft--;
            }
            fromBottom++;
            if (numbersLeft == 0) break;
            
            for (int j = rows - fromBottom - 1; j >= fromTop; j--) {
                if (numbersLeft == 1)
                    System.out.print(numbers[j][fromLeft]);
                else
                    System.out.print(numbers[j][fromLeft] + ",");
                numbersLeft--;
            }
            fromLeft++;
            if (numbersLeft == 0) break;
        }
        System.out.print("\n");
    }
}