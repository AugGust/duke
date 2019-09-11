public class Task {
	protected String desc;
	protected boolean isDone;

	/**
	 * @param desc  description of Task
	 */
	public Task(String desc) {
		this.desc = desc;
		this.isDone = false;
	}
	
	/**
	 * @return Tick or cross icon based on isDone state of Task
	 */
	public String getStatusIcon() {
		return (isDone ? "✓" : "✗");
	}

	/**
	 * Sets isDone to true
	 */
	public void markAsDone() {
		this.isDone = true;
	}

	/**
	 * @return String value "1" or "0" based on isDone. For save formatting
	 */
	public String isDoneStr() {
		return (isDone ? "1" : "0");
	}

	public String getDesc() {
		return desc;
	}

	/**
	 * Formats Task to readable friendly format
	 */
	@Override
	public String toString() {
		return "[" + getStatusIcon() + "] " + desc;
	}

	/**
	 * @return Save friendly format of Task
	 */
	public String toSave() {
		return desc;
	}
}