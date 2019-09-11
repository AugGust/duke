import java.util.ArrayList;

public class Ui {
	
	public Ui() {
	}
	
	/**
	 * Draws a divider line in the console
	 */
	void drawLine()	{
		System.out.println("	____________________________________________________________");
	}
	
	/**
	 * Prints welcome message
	 */
	public void welcome()	{
		System.out.println("	____________________________________________________________\r\n" + 
				"	Hello! I'm Duke\r\n" + 
				"	What can I do for you?\r\n" + 
				"	____________________________________________________________");
	}
	
	/**
	 * Show to user that error occured during loading of previous TaskList
	 */
	public void showLoadingError()	{
		System.out.println("	____________________________________________________________\r\n" + 
				"	Error: Unable to load save file!\r\n" + 
				"	____________________________________________________________");
	}
	
	/**
	 * Prints goodbye message
	 */
	public void bye()	{
		drawLine();
		System.out.println("	Bye. Hope to see you again soon!");
		drawLine();
	}
	
	/**
	 * @param list Prints all Tasks in given TaskList
	 */
	public void displayList(TaskList list)	{
		drawLine();
		for (int i = 0; i < list.size(); i++) {
			System.out.print("	" + (i + 1) + ". ");
			try {
				System.out.println(list.get(i));
			} catch (DukeException e) {
				e.printStackTrace();
			}
		}
		drawLine();
	}
	
	/**
	 * Prints message indicating marking done of Task
	 * @param task Task that is just marked done
	 */
	public void taskDone(Task task)	{
		drawLine();
		System.out.println("	Nice! I've marked this task as done:");
		System.out.println("	" + task);
		drawLine();
	}
	
	/**
	 * Prints all task given as list as found based on search term
	 * @param tasks List of found tasks
	 */
	public void foundList(ArrayList<Task> tasks)	{
		drawLine();
		System.out.println("     Here are the matching tasks in your list:");
		for (int i = 0; i < tasks.size(); i++) {
			System.out.print("     " + (i+1) + ".");
			System.out.println(tasks.get(i));
		}
		drawLine();
	}
	
	/**
	 * Prints message indicating new task added and total number of tasks in list
	 * @param task Newly added task
	 * @param count Total number of tasks
	 */
	public void taskAdded(Task task, int count)	{
		drawLine();
		System.out.println("	Got it. I've added this task:");
		System.out.println("	" + task);
		System.out.println("	Now you have " + count + " tasks in the list.");
		drawLine();
	}
	
	/**
	 * Prints message indicating that task was deleted and total number of tasks in list
	 * @param task Deleted Task
	 * @param count Total number of tasks
	 */
	public void taskDeleted(Task task, int count)	{
		drawLine();
		System.out.println("	Noted. I've removed this task:");
		System.out.println("	" + task);
		System.out.println("	Now you have " + count + " tasks in the list.");
		drawLine();
	}
	
}
