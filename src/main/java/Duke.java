import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
						String[] words = entries[i].split(" \\| ");
						if (words[0].equals("T")) {
							Todo temp = new Todo(words[2]);
							if (words[1].equals("1"))
								temp.markAsDone();
							list.add(temp);
						} else if (words[0].equals("E")) {
							Event temp = new Event(words[2], words[3]);
							if (words[1].equals("1"))
								temp.markAsDone();
							list.add(temp);
						} else {
							Deadline temp = new Deadline(words[2], words[3]);
							if (words[1].equals("1"))
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
			else if (!checkDateTimeFormat(words[1]))
				throw new DukeException("event format");
			list.add(new Event(words[0], formatDateTime(words[1])));
			System.out.println("	Got it. I've added this task:");
			System.out.println("	" + list.get(list.size() - 1));
			showCount();
			endCommand();
		} else if (test.length() < 7) {
			throw new DukeException("wrong command");
		} else if (test.substring(0, 7).equals("delete ")) {
			if (test.length() <= 7)
				throw new DukeException("delete format");
			String number = test.split(" ")[1];
			int index = 0;
			try {
				index = Integer.parseInt(number) - 1;
			} catch (NumberFormatException e) {
				throw new DukeException("delete format");
			}
			Task toDelete;
			try {
				toDelete = list.get(index);
				System.out.println("	Noted. I've removed this task:");
				System.out.println("	" + toDelete);
				list.remove(index);
				showCount();
				endCommand();
			} catch (IndexOutOfBoundsException e) {
				throw new DukeException("out of bounds");
			}
		} else if (test.length() < 9) {
			throw new DukeException("wrong command");
		} else if (test.substring(0, 9).equals("deadline ")) {
			String[] words = test.substring(9, test.length()).split(" /by ");
			if (words.length < 2)
				throw new DukeException("deadline format");
			else if (!checkDateTimeFormat(words[1]))
				throw new DukeException("deadline format");
			list.add(new Deadline(words[0], formatDateTime(words[1])));
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

	public static boolean checkDateTimeFormat(String input) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
		sdf.setLenient(false);
		try {
			sdf.parse(input);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	public static String formatDateTime(String input) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
		String formatted = "";
		try {
			Date date = sdf.parse(input);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			String hour = "" + cal.get(Calendar.HOUR_OF_DAY);
			if (hour.length() == 1)
				hour = "0" + hour;
			String min = "" + cal.get(Calendar.MINUTE);
			if (min.length() == 1)
				min = "0" + min;
			formatted = cal.get(Calendar.DAY_OF_MONTH) + " "
					+ new DateFormatSymbols().getMonths()[cal.get(Calendar.MONTH)] + " " + cal.get(Calendar.YEAR) + " "
					+ hour + min + "H";
			return formatted;
		} catch (ParseException e) {
			return formatted;
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
				System.out.println(
						"	☹ OOPS!!! The format of event is wrong. Format: event <name> /at <dd/MM/yyyy HHmm>, e.g. 02/12/2019 1800");
			else if (message.equals("deadline format"))
				System.out.println(
						"	☹ OOPS!!! The format of deadline is wrong. Format: deadline <name> /by <dd/MM/yyyy HHmm>, e.g. 02/12/2019 1800");
			else if (message.equals("done index"))
				System.out.println("	☹ OOPS!!! The number is out of range");
			else if (message.equals("delete format"))
				System.out.println("	☹ OOPS!!! Please enter the number of the task you want to delete");
			else if (message.equals("out of bounds"))
				System.out.println("	☹ OOPS!!! The number is too high!");
			endCommand();
		}
	}
}
