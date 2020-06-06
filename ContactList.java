import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

/*Name: Roee Aviran
ID: 316492644 */

public class ContactList {

	// Variables
	private ArrayList<Contact> list;
	private int numOfContacts;

	public ContactList() {
		this.list = new ArrayList<Contact>();
		this.numOfContacts = 0;
	}

	public void sortByName() {
		Collections.sort(list);
	}

	public int searchByName(String fName, String lName) throws Exception {
		sortByName();
		int left = 0, right = numOfContacts - 1, mid = 0;
		Contact temp = new Contact(fName, lName, "000000000", "");
		while (left <= right) {
			mid = (left + right)/2;
			if (list.get(mid).compareTo(temp) == 0)
				return mid;
			else if (list.get(mid).compareTo(temp) < 0) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return -mid-1;
	}

	public int addContact(Contact c) throws Exception {
		if (c == null)
			return -1;
		int index = searchByName(c.getfName(), c.getlName());
		if (index >= 0) {
			list.remove(list.get(index));
			list.add(c);
			System.out.println("Contact Updated!");
			return 0;
		} else {
			list.add(Math.abs(index+1), c);
			System.out.println("Contact Added!");
			numOfContacts++;
			return 1;
		}

	}

	public void removeContact(String fName, String lName) throws Exception {
		int index = searchByName(fName, lName);
		if (index >= 0) {
			list.remove(list.get(index));
			numOfContacts--;
			System.out.println("Contact Removed");
		}
		else
			System.out.println("Contact Not Found!");
	}

	@Override
	public String toString() {
		sortByName();
		String str = "Contacts:\n";
		String ch = "";
		for (Contact c : list) {
			if (!c.getfName().toUpperCase().substring(0, 1).equals(ch)) {
				ch = c.getfName().toUpperCase().substring(0, 1);
				str += ch.toUpperCase() + ":\n";
			}
			str += c + "\n";
		}
		if (numOfContacts == 0) {
			str += "List Is Empty.";
		}
		return str;
	}

	public void saveToFile() {
		try (ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream("contacts.obj")))) {
			if (numOfContacts == 0) {
				System.out.println("List Is Empty");
			}
			else {
				out.writeInt(numOfContacts);
				for (Contact c : list) {
					out.writeObject(c);
				}
				System.out.printf("Contacts Saved To File: %d\n", numOfContacts);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadFromFile() {
		try (ObjectInputStream in = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream("contacts.obj")))) {
			int index = 0;
			int temp = numOfContacts;
			int size = in.readInt();
			if (size == 0) {
				throw new Exception();
			}else {
				while (index < size) {
					addContact((Contact) in.readObject());
					index++;
				}
				System.out.printf("Contacts Loaded From File: Added: %d Updated: %d\n", 
						numOfContacts - temp, size - (numOfContacts - temp));
			}
		} catch (IOException e) {
			System.out.println("File Is Empty.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Contact get(int index) {
		if(index >= 0 && numOfContacts > 0 && numOfContacts > index)
			return list.get(index);
		System.out.println("Contact Not Found!");
		return null;
	}
}
