public class Task {
	protected String desc;
	protected boolean isDone;

	public Task(String desc) {
		this.desc = desc;
		this.isDone = false;
	}

	public String getStatusIcon() {
		return (isDone ? "✓" : "✗");
	}

	public void markAsDone() {
		this.isDone = true;
	}

	public String isDoneStr() {
		return (isDone ? "1" : "0");
	}

	public String getDesc() {
		return desc;
	}

	@Override
	public String toString() {
		return "[" + getStatusIcon() + "] " + desc;
	}

	public String toSave() {
		return desc;
	}
}