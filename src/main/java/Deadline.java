public class Deadline extends Task {
	protected String by;

	/**
	 * @param description description of Task
	 * @param by Date and time of deadline
	 */
	public Deadline(String description, String by) {
		super(description);
		this.by = by;
	}

	@Override
	public String toString() {
		return "[D]" + super.toString() + " (by: " + by + ")";
	}

	@Override
	public String toSave() {
		return "D | " + super.isDoneStr() + " | " + super.getDesc() + " | " + by;
	}
}