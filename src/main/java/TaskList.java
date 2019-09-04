import java.util.ArrayList;

public class TaskList {
	private ArrayList<Task> list;
	
	public TaskList(ArrayList<Task> tasks)	{
		this.list = tasks;
	}

	public TaskList() {
		list = new ArrayList<Task>();
	}
	
	public int size()	{
		return list.size();
	}
	
	public Task get(int index) throws DukeException	{
		try {
			return list.get(index);
		} catch	(IndexOutOfBoundsException e)	{
			throw new DukeException("index");
		}
	}
	
	public void add(Task task)	{
		list.add(task);
	}
	
	public void remove(int index) throws DukeException {
		try {
			list.remove(index);
		} catch	(IndexOutOfBoundsException e)	{
			throw new DukeException("index");
		}
	}
}
