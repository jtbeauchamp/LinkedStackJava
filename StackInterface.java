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

public interface StackInterface <T> {
    /**Adds an entry on the top of the stack
     * @param newEntry entry being added to the top of the stack */
    public void push(T newEntry);

    /**Removes the top entry of the stack
     * @return the entry being removed */
    public T pop();

    /**Retrieves the entry on the top of the stack
     * @return the entry at the top of the stack */
    public T peek();

    /**Checks if the stack is empty
     * @return true if the stack is empty, else false */
    public boolean isEmpty();

    /**Removes all entries in the stack */
    public void clear();
}