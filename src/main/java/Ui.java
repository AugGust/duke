import java.util.ArrayList;

public class Ui {
	
	public Ui() {
	}
	
	void drawLine()	{
		System.out.println("	____________________________________________________________");
	}
	
	public void welcome()	{
		System.out.println("	____________________________________________________________\r\n" + 
				"	Hello! I'm Duke\r\n" + 
				"	What can I do for you?\r\n" + 
				"	____________________________________________________________");
	}
	
	public void showLoadingError()	{
		System.out.println("	____________________________________________________________\r\n" + 
				"	Error: Unable to load save file!\r\n" + 
				"	____________________________________________________________");
	}
	
	public void bye()	{
		drawLine();
		System.out.println("	Bye. Hope to see you again soon!");
		drawLine();
	}
	
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
	
	public void taskDone(Task task)	{
		drawLine();
		System.out.println("	Nice! I've marked this task as done:");
		System.out.println("	" + task);
		drawLine();
	}
	
	public void foundList(ArrayList<Task> tasks)	{
		drawLine();
		System.out.println("     Here are the matching tasks in your list:");
		for (int i = 0; i < tasks.size(); i++) {
			System.out.print("     " + (i+1) + ".");
			System.out.println(tasks.get(i));
		}
		drawLine();
	}
	
	public void taskAdded(Task task, int count)	{
		drawLine();
		System.out.println("	Got it. I've added this task:");
		System.out.println("	" + task);
		System.out.println("	Now you have " + count + " tasks in the list.");
		drawLine();
	}
	
	public void taskDeleted(Task task, int count)	{
		drawLine();
		System.out.println("	Noted. I've removed this task:");
		System.out.println("	" + task);
		System.out.println("	Now you have " + count + " tasks in the list.");
		drawLine();
	}
	
}
