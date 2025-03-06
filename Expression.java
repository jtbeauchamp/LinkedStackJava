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


import java.util.ArrayList;

public class Expression {

    /**
     * Converts a given infix expression into a postfix expression
     * @param infixExpression the expression being converted
     * @return the newly made postfix expression
     */
    public static String[] convertToPostfix(String[] infixExpression){

        StackInterface<String> operatorStack = new LinkedStack<>(); //stack that holds the operators in the expression
        ArrayList<String> postfixExpression = new ArrayList<String>();  //postfix expression Array List that gets the tokens appended to it
        String[] result;    //the array that postfixExpression gets casted into and returned
        int index = 0;  //index of the infixExpression array

        while(index < infixExpression.length){ //loops until the index has reached the end of the infix expression length
            switch(infixExpression[index]){ //switch cases for the tokens that are considered as the loop iterates through the infix array

                case "^":   //pushes the power onto the stack
                operatorStack.push(infixExpression[index]);
                break;

                case "+": case "-": case"*": case"/":   //pops the token into the array list depending on the precedence of the item at the current index
                while(!operatorStack.isEmpty() && (precedence(infixExpression[index]) <= precedence(operatorStack.peek()))){
                    postfixExpression.add(operatorStack.peek());
                    operatorStack.pop();
                }
                operatorStack.push(infixExpression[index]);
                break;

                case "(":   //pushes an open paren onto stack
                operatorStack.push(infixExpression[index]);
                break;

                case ")":   //pops the stack adn appends it into the arraylist until an open paren is popped
                String topOperator = operatorStack.pop();
                while(!topOperator.equals("(") && !operatorStack.isEmpty()){
                    postfixExpression.add(topOperator);
                    topOperator = operatorStack.pop();
                }
                break;

                default:
                postfixExpression.add(infixExpression[index]);
                break;
            }
            index++;
        
            
        }

        while(!operatorStack.isEmpty()){    //while loop that pops any remaining tokens into the postfixExpression Array List
            String topOperator = operatorStack.pop();
            postfixExpression.add(topOperator);   
        } 

        result = new String[postfixExpression.size()];  //loops the Array List into an array of string that is returned
        for(int i = 0; i < postfixExpression.size(); i ++){
            result[i] = postfixExpression.get(i);
        }
        return result;
    }

    /**
     * Evaluates a postfix equation given to it and returns the solution
     * @param postfixExpression the expression that is evaluated
     * @return the solution to the equation
     */
    public static double evaluatePostfix(String[] postfixExpression){
        StackInterface<Double> valueStack = new LinkedStack<>();
        int index = 0;
        double operandOne, operandTwo;
        double solution;
        double result;
        while(index < postfixExpression.length){
            if(postfixExpression[index].isBlank()){
                index++;
            }
            else{ 
                switch(postfixExpression[index]){
                    case "+": case "-": case"*": case"/": case "^":
                    operandTwo = valueStack.pop();
                    operandOne = valueStack.pop();
                    result = getCalc(postfixExpression[index], operandOne, operandTwo);
                    valueStack.push(result);
                    break;

                    default:
                    try {
                        valueStack.push(Double.parseDouble(postfixExpression[index]));
                    } catch (Exception e) {
                        System.out.println(". \"" + postfixExpression[index] + "\" is not a valid token");
                    }
                    break;
                }
                index++;
            }
        }
        
        try {   //Pops the solution out of the stack, throws an exception if there are still items in the stack

            solution = valueStack.pop();    //solution that is popped out of the bag
            if(!valueStack.isEmpty()){  //throws exception if there is more items in the bag after solution is popped
                throw new RuntimeException();
            }
            return solution;    //returns the solution

        } catch (Exception RuntimeException) {
        }
        throw new RuntimeException("Invalid postfix expression. Could not evaluate.");
    } 

    /**
     * Private helper class that determines the precedence of each operator popped from the stack
     * @param operator string that is being evaluated for the operator function
     * @return
     */
    private static int precedence(String operator){
        int operatorPrecedence;
            switch (operator){
                case "^":
                operatorPrecedence = 3;
                return operatorPrecedence;

                case "*":
                case "/":
                operatorPrecedence = 2;
                return operatorPrecedence;

                case "+":
                case "-":
                operatorPrecedence = 1;
                return operatorPrecedence;

                case "(":
                operatorPrecedence = 0;
                return operatorPrecedence;

                default:
                throw new RuntimeException("Invalid Operator " + operator);
            }
    }

    /**
     * Private helper class that calculates depending on the two operands and the operators
     * @param operator operator that is used to determine which equation will be used
     * @param lhs the operand on the left side of the eqaution
     * @param rhs the operand on the right side of the equation
     * @return  the sum/difference/quotient/product of the equation being returned
     */
    private static double getCalc(String operator, double lhs, double rhs){
        String op = operator.toString();   //op represents operator in the switch case
        switch(op){
            case "*":
            return lhs * rhs;   //multiplication case

            case "/":
            return lhs / rhs;   //division case

            case "+":
            return lhs + rhs;   //addition case

            case "-":
            return lhs - rhs;   //subtraction case

            case "^":
            return Math.pow(lhs, rhs);  //power case
        }
        throw new RuntimeException("Invalid operator");
    }
}