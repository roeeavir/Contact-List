import java.util.Scanner;

/*Name: Roee Aviran
ID: 316492644 */

public class Main {

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		ContactList cList = new ContactList();
		try {
			loadMenu(cList, s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Exiting...");
		s.close();

	}

	public static void loadMenu(ContactList cList, Scanner s) throws Exception {
		boolean isFinished = false;
		while (!isFinished) {
			try {
				System.out.println("- - - - - - - - - - - - - - -\r\n" + "- - - Contact Creator : - - -\r\n"
						+ "- - - - - - - - - - - - - - -\r\n" + "1. Add/Update Contact\r\n" + "2. Remove Contact\r\n"
						+ "3. Save Contacts To File\r\n" + "4. Load Contacts From File\r\n"
						+ "5. Search Contact By Name\r\n" + "6. Print All Contacts\r\n"
						+ "- - - - - - - - - - - - - - -\r\n" + "Choose your option or any other key to EXIT.\r\n"
						+ "Your Option:");

				char i = s.next().charAt(0);
				s.nextLine();
				switch (i) {
				case '1':
					System.out.println("ADD/UPDATE CONTACT:");
					cList.addContact(new Contact(getFirstName(s), getLastName(s), getHomePhone(s), getMobilePhone(s)));
					break;
				case '2':
					System.out.println("REMOVE CONTACT:");
					cList.removeContact(getFirstName(s), getLastName(s));
					break;
				case '3':
					cList.saveToFile();
					break;
				case '4':
					cList.loadFromFile();
					break;
				case '5':
					System.out.println("SEARCH CONTACT:");
					int index = cList.searchByName(getFirstName(s), getLastName(s));
						if(index >=0)
							System.out.println(cList.get(index));
						else
							System.out.println("Contact Not Found!");
					break;
				case '6':
					System.out.println(cList);
					break;

				default:
					System.out.println("No option was chosen\n");
					isFinished = true;
					break;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}

	
	}

	public static String getFirstName(Scanner s) {
		System.out.println("Enter First Name:");
		return s.nextLine();
	}

	public static String getLastName(Scanner s) {
		System.out.println("Enter Last Name:");
		return s.nextLine();
	}

	public static String getHomePhone(Scanner s) {
		System.out.println("Enter Home Phone:");
		return s.nextLine();
	}

	public static String getMobilePhone(Scanner s) {
		System.out.println("Enter Mobile Phone:");
		return s.nextLine();
	}

}
