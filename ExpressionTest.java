//
//  Name:       Beauchamp, Joshua
//  Project:    3
//  Due:        October 23, 2023
//  Course:     cs-2400-02-f23
//
//  Description:
//              The program solves mathematic equations through the use of a stack. The stack is used to store
//              operands and operators contained by the equation. To accomplish this, it converts the equations to postfix
//              then evaluates the newly made postfix equation. Should the equation already be in postfix form, the program
//              evaluates the expression.
//

public class ExpressionTest {
    public static void main(String[] args) throws Exception{
        System.out.println("Expression by Joshua Beauchamp\n");

        //Tries to read input from the command line, throws exception if the input is not valid
        int index = 0;
        while (index < args.length){    //loops through each index in the command line array
            try{
                System.out.println(args[index]);    
                System.out.print("\t");

                String[] splitExpression = args[index].split(" ");  //splits the array as to not contain spaces

                String[] postFix = Expression.convertToPostfix(splitExpression);    //converts the newly split array into a postfix form array
                for(String s: postFix){     //the String s represents each string that is stored by the postfix array
                    System.out.print(s + " ");
                }
                System.out.println("= " + Expression.evaluatePostfix(postFix) + "\n");  //prints out the evaluated postfix solution
            }
            catch(Exception e){ //catches any exception thrown by main since the exceptions are caught by the methods
                System.out.println("\nInvalid expression. Can not evaluate.");
            }
                index++;    //increments to the next string
        }

        //Testing portion begins
        //Follows the same code as the one above
        System.out.println("Beginning the hardcoded tests section of the project");
        System.out.println("----------------------------------------------------\n");

        //Test 1
        //Tests if a letter as a number will result in an invalid token exception
        System.out.println("Testing \"1 + a * 3\" ");
        String[] testArray = {"1 + a * 3"};
        test(testArray);
        //end Test 1

        //Test 2
        //Tests if parenthesis will still allow the program to function correctly
        System.out.println("Testing \"( 1 + 2 ) * 2\"");
        String[] testArray2 = {"( 1 + 2 ) * 2"};
        test(testArray2);   //end test 2

        //Test 3
        //Tests if exponents will work as operators
        System.out.println("Testing \"2 ^ 3 ^ 4\"");
        String[] testArray3 = {"2 ^ 3 ^ 4"};
        test(testArray3);   //end test 3

        //Test 4
        //Tests if an invalid token as the operand will cause an error
        System.out.println("Testing \"2 @ 1\"");
        String[] testArray4 = {"2 @ 1"};
        test(testArray4);
        //end test 4

        //Test 5
        //Tests if a postfix expression will still be evaluated through both Expression class methods
        System.out.println("Testing \"2 2 2 ^ ^\"");
        String[] testArray5 = {"2 2 2 ^ ^"};
        test(testArray5);
        //end test 5
        
        //Test 6
        //Tests if doubles as input still work
        System.out.println("Testing \"7.0 - 2.0 * 3.0\"");
        String[] testArray6 = {"7.0 - 2.0 * 3.0"};
        test(testArray6);
        //end test 6

        //Test 7
        //Tests if an non-balanced expression is detected
        System.out.println("Testing \"1 + 2 * 3 -\"");
        String[] testArray7 = {"1 + 2 * 3 -"};
        test(testArray7);
        //end test7
    }

    /**
     * Method that runs the given array through the Expression class methods
     * and outputs the results to the console
     * @param array the given array that is being tested
     */
    public static void test(String[] array){
        int index = 0;
        System.out.println("-------------------------");
        while (index < array.length){
            try{
                System.out.println(array[index]);
                System.out.print("\t");

                String[] splitExpression = array[index].split(" ");

                String[] postfix = Expression.convertToPostfix(splitExpression);
                for(String s: postfix){
                    System.out.print(s + " ");
                }
                System.out.print("= ");
                System.out.printf("%.2f", Expression.evaluatePostfix(postfix));
                System.out.println("\n\n\n");
            }
            catch(Exception e){
                System.out.println("\nInvalid expression. Can not evaluate.\n\n\n");
            }
            index++;
        } 
    }
}   //end main