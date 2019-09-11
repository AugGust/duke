import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Parser {
	
	public Parser() {
	}
	
	/**
	 * @param test Input String by user
	 * @return Command Object
	 * @throws DukeException To be caught for error handling
	 */
	public Command parseCommand(String test) throws DukeException	{
		if (test.equals("bye"))
			return new Command(0);
		else if (test.equals("list"))
			return new Command(1);
		else if (test.length() < 5)	{
			throw new DukeException("wrong command");
		}
		
		else if (test.substring(0, 5).equals("done ")) {
			int index = Integer.parseInt(test.substring(5, test.length())) - 1;
			return new Command(2, index);
		} 
		else if (test.substring(0, 5).equals("find "))	{
			String searchTerm = test.substring(5, test.length());
			return new Command(3, searchTerm);
		}
		else if (test.substring(0, 5).equals("todo ")) {
			if (test.length() <= 5)
				throw new DukeException("todo blank");
			return new Command(4, test.substring(5, test.length()));
		} else if (test.length() < 6) {
			throw new DukeException("wrong command");
		} 
		else if (test.substring(0, 6).equals("event ")) {
			String[] words = test.substring(6, test.length()).split(" /at ");
			if (words.length < 2)
				throw new DukeException("event format");
			else if (!checkDateTimeFormat(words[1]))
				throw new DukeException("event format");
			return new Command(5, words[0], formatDateTime(words[1]));
		} 
		else if (test.length() < 7) {
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
			return new Command(6, index);
		} else if (test.length() < 9) {
			throw new DukeException("wrong command");
		} else if (test.substring(0, 9).equals("deadline ")) {
			String[] words = test.substring(9, test.length()).split(" /by ");
			if (words.length < 2)
				throw new DukeException("deadline format");
			else if (!checkDateTimeFormat(words[1]))
				throw new DukeException("deadline format");
			return new Command(7, words[0], formatDateTime(words[1]));
		}
		else
			throw new DukeException("wrong command");
	}
	
	/**
	 * @param input Input String for DateTime. Correct Format: dd/MM/yyyy HHmm
	 * @return True/false, if String conforms to format
	 */
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

	/**
	 * @param input Input String for DateTime. Correct Format: dd/MM/yyyy HHmm
	 * @return Formatted, reader friendly date and time
	 */
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
}
