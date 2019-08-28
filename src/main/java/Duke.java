import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
	static Scanner sc;
	static ArrayList<Task> list;

	public static void main(String[] args) throws DukeException {
		/*
		 * String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" +
		 * "| | | | | | | |/ / _ \\\n" + "| |_| | |_| |   <  __/\n" +
		 * "|____/ \\__,_|_|\\_\\___|\n"; System.out.println("Hello from\n" + logo);
		 */
		list = new ArrayList<Task>();
		System.out.println("	____________________________________________________________");
		System.out.println("	Hello! I'm Duke");
		System.out.println("	What can I do for you?");
		System.out.println("	____________________________________________________________");
		sc = new Scanner(System.in);
		nextCommand();
		sc.close();
	}

	static void nextCommand() throws DukeException {
		String test = sc.nextLine();
		System.out.println("	____________________________________________________________\n	");
		if (test.equals("bye")) {
			System.out.println("	Bye. Hope to see you again soon!");
			System.out.println("	____________________________________________________________\n	");
		} else if (test.equals("list")) {
			for (int i = 0; i < list.size(); i++) {
				System.out.print("	" + (i + 1) + ". ");
				System.out.println(list.get(i));
			}
			endCommand();
		} else if (test.length() < 5) {
			throw new DukeException("wrong command");
		} else if (test.substring(0, 5).equals("done ")) {
			int index = Integer.parseInt(test.substring(5, test.length())) - 1;
			if (index + 1 > list.size())
				throw new DukeException("done index");
			list.get(index).markAsDone();
			System.out.println("	Nice! I've marked this task as done:");
			System.out.println("	" + list.get(index));
			endCommand();
		} else if (test.substring(0, 5).equals("todo ")) {
			if (test.length() <= 5)
				throw new DukeException("todo blank");
			list.add(new Todo(test.substring(5, test.length())));
			System.out.println("	Got it. I've added this task:");
			System.out.println("	" + list.get(list.size() - 1));
			showCount();
			endCommand();
		} else if (test.length() < 6) {
			throw new DukeException("wrong command");
		} else if (test.substring(0, 6).equals("event ")) {
			String[] words = test.substring(6, test.length()).split(" /at ");
			if (words.length < 2)
				throw new DukeException("event format");
			list.add(new Event(words[0], words[1]));
			System.out.println("	Got it. I've added this task:");
			System.out.println("	" + list.get(list.size() - 1));
			showCount();
			endCommand();
		} else if (test.length() < 9) {
			throw new DukeException("wrong command");
		} else if (test.substring(0, 9).equals("deadline ")) {
			String[] words = test.substring(9, test.length()).split(" /by ");
			if (words.length < 2)
				throw new DukeException("deadline format");
			list.add(new Deadline(words[0], words[1]));
			System.out.println("	Got it. I've added this task:");
			System.out.println("	" + list.get(list.size() - 1));
			showCount();
			endCommand();
		} else {
			throw new DukeException("wrong command");
		}
	}

	public static void showCount() {
		System.out.println("	Now you have " + list.size() + " tasks in the list.");
	}

	public static void endCommand() throws DukeException {
		System.out.println("	____________________________________________________________");
		nextCommand();
	}

	public static class Task {
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

		public String getDesc() {
			return desc;
		}

		@Override
		public String toString() {
			return "[" + getStatusIcon() + "] " + desc;
		}
	}

	public static class Deadline extends Task {
		protected String by;

		public Deadline(String description, String by) {
			super(description);
			this.by = by;
		}

		@Override
		public String toString() {
			return "[D]" + super.toString() + " (by: " + by + ")";
		}
	}

	public static class Event extends Task {
		protected String at;

		public Event(String description, String at) {
			super(description);
			this.at = at;
		}

		@Override
		public String toString() {
			return "[E]" + super.toString() + " (at: " + at + ")";
		}
	}

	public static class Todo extends Task {
		public Todo(String description) {
			super(description);
		}

		@Override
		public String toString() {
			return "[T]" + super.toString();
		}
	}

	public static class DukeException extends Exception {
		public DukeException(String message) throws DukeException {
			if (message.equals("wrong command"))
				System.out.println("	☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
			else if (message.equals("todo blank"))
				System.out.println("	☹ OOPS!!! The description of a todo cannot be empty.");
			else if (message.equals("event format"))
				System.out.println("	☹ OOPS!!! The format of event is wrong. Format: event <name> /at <time>");
			else if (message.equals("deadline format"))
				System.out.println("	☹ OOPS!!! The format of deadline is wrong. Format: deadline <name> /by <time>");
			else if (message.equals("done index"))
				System.out.println("	☹ OOPS!!! The number is out of range");
			endCommand();
		}
	}
}
