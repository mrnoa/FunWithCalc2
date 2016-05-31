
public class HexCalcEngine extends CalcEngine {

	private int base;
	private int displayString;

	public HexCalcEngine() {
		setBase(16);
	}


    public void setBase(int base){
    	this.base = base;
    }

	public int getBase() {
		return base;
	}
}
