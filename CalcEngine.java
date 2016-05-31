/**
 * The main part of the calculator doing the calculations.
 * 
 * @author  David J. Barnes and Michael Kolling 
 * @version 2008.03.30
 */
public class CalcEngine
{
    // The calculator's state is maintained in three fields:

    // The current value (to be) shown in the display.
    
    protected String calcString;
    
    private Postfix pfx;
    

    /**
     * Create a CalcEngine.
     */
    public CalcEngine()
    {
        clear();
        pfx = new Postfix();
    }

    /**
     * @return The value that should currently be displayed
     * on the calculator display.
     */
    public String getDisplayValue()
    {
        return calcString;
    }

    /**
     * A number button was pressed.
     * Either start a new operand, or incorporate this number as
     * the least significant digit of an existing one.
     * @param number The number pressed on the calculator.
     */

    public void equals()
    {
        // This should completes the building of a second operand,
        // so ensure that we really have a left operand, an operator
        // and a right operand.
        
    }

    /**
     * The 'C' (clear) button was pressed.
     * Reset everything to a starting state.
     */
    public void clear()
    {
        calcString = "";
    }

    /**
     * @return The title of this calculation engine.
     */
    public String getTitle()
    {
        return "Java Calculator";
    }

    /**
     * @return The author of this engine.
     */
    public String getAuthor()
    {
        return "David J. Barnes and Michael Kolling";
    }

    /**
     * @return The version number of this engine.
     */
    public String getVersion()
    {
       return "Version 1.0";
    }

    public void calculateResult()
    {
    	calcString = "" + pfx.evaluate(pfx.infixToPostfix(calcString));
    }
    public void addToString(String c){
    	calcString += c;
    }
    /**
     * Apply an operator.
     * @param operator The operator to apply.
     */

    /**
     * Report an error in the sequence of keys that was pressed.
     */

}
