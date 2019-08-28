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
        		System.out.println(list.get(i));
        	}
        	endCommand();
    	}
    	else if (test.length() < 4)	{
    		list.add(new Task(test));
    		System.out.println('	' + "added: " + test);
        	endCommand();
    	}
    	else if (test.substring(0, 4).equals("done")) {
    		int index = Integer.parseInt(test.substring(5, 6)) - 1;
    		list.get(index).markAsDone();
    		System.out.println("	Nice! I've marked this task as done:");
    		System.out.println("	" + list.get(index));
    		endCommand();
    	}
    	else if (test.substring(0, 4).equals("todo"))	{
    		list.add(new Todo(test.substring(5, test.length())));
    		System.out.println("	Got it. I've added this task:");
    		System.out.println("	" + list.get(list.size() - 1));
    		showCount();
    		endCommand();
    	}
    	else if (test.length() < 5)	{
    		list.add(new Task(test));
    		System.out.println('	' + "added: " + test);
        	endCommand();
    	}
    	else if (test.substring(0, 5).equals("event"))	{
    		String[] words = test.substring(6, test.length()).split(" /at ");
    		list.add(new Event(words[0], words[1]));
    		System.out.println("	Got it. I've added this task:");
    		System.out.println("	" + list.get(list.size() - 1));
    		showCount();
    		endCommand();
    	}
    	else if (test.length() < 8)	{
    		list.add(new Task(test));
    		System.out.println('	' + "added: " + test);
        	endCommand();
    	}
    	else if (test.substring(0, 8).equals("deadline"))	{
    		String[] words = test.substring(9, test.length()).split(" /by ");
    		list.add(new Deadline(words[0], words[1]));
    		System.out.println("	Got it. I've added this task:");
    		System.out.println("	" + list.get(list.size() - 1));
    		showCount();
    		endCommand();
    	}
    	else	{
    		list.add(new Task(test));
    		System.out.println('	' + "added: " + test);
        	endCommand();
    	}
    }
    
    public static void showCount()	{
    	System.out.println("	Now you have " + list.size() + " tasks in the list.");
    }
    
    public static void endCommand()	{
    	System.out.println("	____________________________________________________________");
    	nextCommand();
    }
    
    public static class Task {
    	protected String desc;
    	protected boolean isDone;
    	
    	public Task(String desc)	{
    		this.desc = desc;
    		this.isDone = false;
    	}
    	
    	public String getStatusIcon()	{
    		return (isDone ? "✓" : "✗");
    	}
    	
    	public void markAsDone()	{
    		this.isDone = true;
    	}
    	
    	public String getDesc()	{
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
}
