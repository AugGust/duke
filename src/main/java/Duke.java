import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
	static Scanner sc;
	static ArrayList<Task> list;
	static Path saveFile = Paths.get("", "data");

	public static void main(String[] args) throws DukeException {
		list = new ArrayList<Task>();
		File file = new File(saveFile.toAbsolutePath().toString());
		if (!file.exists())
			file.mkdir();
		saveFile = Paths.get("", "data", "duke.txt");
		file = new File(saveFile.toAbsolutePath().toString());
		try {
			if (!file.createNewFile()) {
				// found previous settings
				String fileStr = Files.readString(saveFile);
				String[] entries = fileStr.split("\n");
				if (entries[0].length() != 0) {
					for (int i = 0; i < entries.length; i++) {
						String[] words = entries[i].split(" | ");
						if (words[0].equals("T")) {
							Todo temp = new Todo(words[4]);
							if (words[2].equals("1"))
								temp.markAsDone();
							list.add(temp);
						} else if (words[0].equals("E")) {
							System.out.println();
							Event temp = new Event(words[4], words[6]);
							if (words[2].equals("1"))
								temp.markAsDone();
							list.add(temp);
						} else {
							Deadline temp = new Deadline(words[4], words[6]);
							if (words[2].equals("1"))
								temp.markAsDone();
							list.add(temp);
						}
					}
				}
			} else {
				System.out.println("	Created new save file");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
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
			return;
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
		save();
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

		@Override
		public String toSave() {
			return "D | " + super.isDoneStr() + " | " + super.getDesc() + " | " + by;
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

		@Override
		public String toSave() {
			return "E | " + super.isDoneStr() + " | " + super.getDesc() + " | " + at;
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

		@Override
		public String toSave() {
			return "T | " + super.isDoneStr() + " | " + super.getDesc();
		}
	}

	public static void save() {
		String toWrite = "";
		for (int i = 0; i < list.size(); i++)
			toWrite += list.get(i).toSave() + "\n";
		try {
			Files.deleteIfExists(saveFile);
			Files.writeString(saveFile, toWrite, StandardOpenOption.CREATE);
		} catch (IOException e) {
			System.out.println("Error saving!");
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
