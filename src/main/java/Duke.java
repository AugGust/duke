import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
	private Storage storage;
	private TaskList list;
	private Ui ui;
	private Parser ps;
	private Scanner sc;
	// static Path saveFile = Paths.get("", "data", "duke.txt");

	public Duke(String filePath) {
		ui = new Ui();
		ps = new Parser();
		sc = new Scanner(System.in);
		storage = new Storage(filePath);
		try {
			list = new TaskList(storage.load());
		} catch (DukeException e) {
			ui.showLoadingError();
			list = new TaskList();
		}
	}

	public void run() {
		ui.welcome();
		nextCommand();
	}

	void nextCommand() {
		Command command = new Command();
		try {
			command = ps.parseCommand(sc.nextLine());
		} catch (DukeException e) {
			nextCommand();
		}

		switch (command.getType()) {
		case 0:
			ui.bye();
			return;
		case 1:
			ui.displayList(list);
			break;
		case 2:
			try {
				list.get(command.getIndex()).markAsDone();
				ui.taskDone(list.get(command.getIndex()));
			} catch (DukeException e) {
				break;
			}
			break;
		case 3:
			try {
				ArrayList<Task> foundList = new ArrayList<Task>();
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).toString().contains(command.getInput1()))
						foundList.add(list.get(i));
				}
				ui.foundList(foundList);
			} catch (DukeException e) {
				break;
			}
			break;
		case 4:
			Todo newTodo = new Todo(command.getInput1());
			list.add(newTodo);
			ui.taskAdded(newTodo, list.size());
			break;
		case 5:
			Event newEvent = new Event(command.getInput1(), command.getInput2());
			list.add(newEvent);
			ui.taskAdded(newEvent, list.size());
			break;
		case 6:
			try {
				Task toDelete = list.get(command.getIndex());
				list.remove(command.getIndex());
				ui.taskDeleted(toDelete, list.size());
			} catch (DukeException e) {
				break;
			}
			break;
		case 7:
			Deadline newDeadline = new Deadline(command.getInput1(), command.getInput2());
			list.add(newDeadline);
			ui.taskAdded(newDeadline, list.size());
			break;
		}
		

		storage.save(list);
		nextCommand();
	}

	void outOfIndex() {
		try {
			new DukeException("index");
		} catch (DukeException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Duke("data/duke.txt").run();
	}
}
