import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
	static Scanner sc;
	static ArrayList<Task> list;
	
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
    	list = new ArrayList<Task>();
    	System.out.println("	____________________________________________________________");
    	System.out.println("	Hello! I'm Duke");
    	System.out.println("	What can I do for you?");
    	System.out.println("	____________________________________________________________");
    	sc = new Scanner(System.in);
    	nextCommand();
    	sc.close();
    }
    
    //Level 1
    static void nextCommand()	{
    	String test = sc.nextLine();
    	System.out.println("	____________________________________________________________\n	");
    	if (test.equals("bye"))	{
    		System.out.println("	Bye. Hope to see you again soon!");
    		System.out.println("	____________________________________________________________\n	");
    	}
    	else if (test.equals("list"))	{
        	for (int i = 0; i < list.size(); i++)	{
        		System.out.print("	" + (i+1) + ". ");
        		System.out.println("	[" + list.get(i).getStatusIcon() + "] " + list.get(i).getTask());
        	}
    		System.out.println("	____________________________________________________________\n	");
    		nextCommand();
    	}
    	else if (test.substring(0, 4).equals("done")) {
    		int index = Integer.parseInt(test.substring(5, 6)) - 1;
    		list.get(index).markAsDone();
    		System.out.println("	Nice! I've marked this task as done:");
    		System.out.println("	[" + list.get(index).getStatusIcon() + "] " + list.get(index).getTask());
    		System.out.println("	____________________________________________________________");
    		nextCommand();
    	}
    	else	{
    		list.add(new Task(test));
    		System.out.println('	' + "added: " + test);
        	System.out.println("	____________________________________________________________");
        	nextCommand();
    	}
    }
    
    public static class Task {
    	protected String task;
    	protected boolean isDone;
    	
    	public Task(String task)	{
    		this.task = task;
    		this.isDone = false;
    	}
    	
    	public String getStatusIcon()	{
    		return (isDone ? "✓" : "✗");
    	}
    	
    	public void markAsDone()	{
    		this.isDone = true;
    	}
    	
    	public String getTask()	{
    		return this.task;
    	}
    }
}
