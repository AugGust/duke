public class Command {
	private int type;
	private int index;
	private String input1;
	private String input2;
	
	public Command() {
		super();
	}
	public Command(int type) {
		super();
		this.type = type;
	}
	public Command(int type, int index) {
		super();
		this.type = type;
		this.index = index;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public Command(int type, String input1, String input2) {
		super();
		this.type = type;
		this.input1 = input1;
		this.input2 = input2;
	}
	public Command(int type, String input1) {
		this.type = type;
		this.input1 = input1;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getInput1() {
		return input1;
	}
	public void setInput1(String input1) {
		this.input1 = input1;
	}
	public String getInput2() {
		return input2;
	}
	public void setInput2(String input2) {
		this.input2 = input2;
	}
	
	
}
