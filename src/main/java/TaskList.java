import java.util.ArrayList;

public class TaskList {
	private ArrayList<Task> list;
	
	/**
	 * @param tasks ArrayList of Tasks
	 */
	public TaskList(ArrayList<Task> tasks)	{
		this.list = tasks;
	}

	/**
	 * Creates empty TaskList
	 */
	public TaskList() {
		list = new ArrayList<Task>();
	}
	
	/**
	 * @return Count of Tasks
	 */
	public int size()	{
		return list.size();
	}
	
	/**
	 * @param index Index of task to retrieve
	 * @return Task object
	 * @throws DukeException Caught out of index error
	 */
	public Task get(int index) throws DukeException	{
		try {
			return list.get(index);
		} catch	(IndexOutOfBoundsException e)	{
			throw new DukeException("index");
		}
	}
	
	/**
	 * @param task New Task to add to Tasklist
	 */
	public void add(Task task)	{
		list.add(task);
	}
	
	/**
	 * @param index Index of Task to remove
	 * @throws DukeException Caught out of index error
	 */
	public void remove(int index) throws DukeException {
		try {
			list.remove(index);
		} catch	(IndexOutOfBoundsException e)	{
			throw new DukeException("index");
		}
	}
}
