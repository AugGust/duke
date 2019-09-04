public class DukeException extends Exception {
	public DukeException(String message) throws DukeException {
		drawLine();
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
		else if (message.equals("index"))
			System.out.println("	☹ OOPS!!! The number is out of range");
		else if (message.equals("delete format"))
			System.out.println("	☹ OOPS!!! Please enter the number of the task you want to delete");
		drawLine();
	}
	
	void drawLine()	{
		System.out.println("	____________________________________________________________");
	}
}