import java.util.Scanner;

public class Duke {
	static Scanner sc;
	
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
    	System.out.println("	____________________________________________________________");
    	System.out.println("	Hello! I'm Duke");
    	System.out.println("	What can I do for you?");
    	System.out.println("	____________________________________________________________");
    	sc = new Scanner(System.in);
    	nextCommand();
    	sc.close();
    }
    
    static void nextCommand()	{
    	String test = sc.nextLine();
    	System.out.println("	____________________________________________________________\n	");
    	if (test.equals("bye"))	{
    		System.out.println("	Bye. Hope to see you again soon!");
    		System.out.println("	____________________________________________________________\n	");
    	}
    	else	{
    		System.out.println('	' + test);
        	System.out.println("	____________________________________________________________");
        	nextCommand();
    	}
    }
}
