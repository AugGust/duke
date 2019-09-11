public class Event extends Task {
	protected String at;

	/**
	 * @param description description of Task
	 * @param at Date and time of event
	 */
	public Event(String description, String at) {
		super(description);
		this.at = at;
	}

	@Override
	public String toString() {
		return "[E]" + super.toString() + " (at: " + at + ")";
	}

	@Override
	public String toSave() {
		return "E | " + super.isDoneStr() + " | " + super.getDesc() + " | " + at;
	}
}