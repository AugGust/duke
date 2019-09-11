import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

/**
 * @author Jy
 *
 */
/**
 * @author Jy
 *
 */
public class Storage {
	String filePath;
	
	/**
	 * @param filePath File path for save data of Duke
	 */
	public Storage(String filePath) {
		this.filePath = filePath;
	}
	
	/**
	 * Attempts to load saved data of Duke
	 * @return ArrayList of Tasks
	 * @throws DukeException IOException during file access
	 */
	public ArrayList<Task> load() throws DukeException	{
		ArrayList<Task> loadedTasks = new ArrayList<Task>();
		
		File file = new File(filePath);
		File folder = new File(file.getParent());
		if (!folder.exists())
			folder.mkdir();
		
		try {
			if (!file.createNewFile()) {
				// found previous settings
				String fileStr = Files.readString(file.toPath());
				String[] entries = fileStr.split("\n");
				if (entries[0].length() != 0) {
					for (int i = 0; i < entries.length; i++) {
						String[] words = entries[i].split(" \\| ");
						if (words[0].equals("T")) {
							Todo temp = new Todo(words[2]);
							if (words[1].equals("1"))
								temp.markAsDone();
							loadedTasks.add(temp);
						} else if (words[0].equals("E")) {
							Event temp = new Event(words[2], words[3]);
							if (words[1].equals("1"))
								temp.markAsDone();
							loadedTasks.add(temp);
						} else {
							Deadline temp = new Deadline(words[2], words[3]);
							if (words[1].equals("1"))
								temp.markAsDone();
							loadedTasks.add(temp);
						}
					}
				}
			} else {
				System.out.println("	Created new save file");
			}
		} catch (IOException e1) {
			throw new DukeException("loading");
		}
		
		return loadedTasks;
	}
	
	/**
	 * @param list Saves given TaskList to save file location
	 */
	public void save(TaskList list)	{
		String toWrite = "";
		for (int i = 0; i < list.size(); i++)
			try {
				toWrite += list.get(i).toSave() + "\n";
			} catch (DukeException e) {
				e.printStackTrace();
			}
		try {
			Files.deleteIfExists(new File(filePath).toPath());
			Files.writeString(new File(filePath).toPath(), toWrite, StandardOpenOption.CREATE);
		} catch (IOException e) {
			System.out.println("Error saving!");
		}
	}
}
