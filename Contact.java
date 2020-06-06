import java.io.Serializable;

/*Name: Roee Aviran
ID: 316492644 */

public class Contact implements Comparable<Contact>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Variables
	private String fName;
	private String lName;
	private String hPhone;
	private String mPhone;

	public Contact(String fName, String lName, String hPhone, String mPhone) throws Exception {

		try {
			setfName(fName);
			setlName(lName);
			sethPhone(hPhone);
			setmPhone(mPhone);
			hasPhone();
		} catch (Exception e) {
			System.err.println(e.getMessage() + "\n");
			throw new Exception();
		}
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) throws Exception {
		if (fName == null)
			throw new Exception("First Name Cannot Be Null!");
		else if (fName.length() < 3)
			throw new Exception("First Name's Size Cannot Be Shorter Than 3!");
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) throws Exception {
		if (lName == null)
			throw new Exception("Last Name Cannot Be Null!");
		else if (lName.length() < 3)
			throw new Exception("Last Name's Size Cannot Be Shorter Than 3!");
		this.lName = lName;
	}

	public String gethPhone() {
		return hPhone;
	}

	public void sethPhone(String hPhone) throws Exception {
		if (hPhone.isEmpty())
			this.hPhone = hPhone;
		else if (checkPhoneNum(hPhone, 9)) {
			this.hPhone = hPhone;
		}
		else 
			throw new Exception("Invalid Home Phone Number (Must Contain 9 Digits Or More)!");
		
	}
	

	public String getmPhone() {
		return mPhone;
	}

	public void setmPhone(String mPhone) throws Exception {
		if (mPhone.isEmpty())
			this.mPhone = mPhone;
		else if (checkPhoneNum(mPhone, 10)) {
			this.mPhone = mPhone;
		}
		else 
			throw new Exception("Invalid Mobile Phone Number (Must Contain 10 Digits Or More!)!");
	}
	
	private boolean checkPhoneNum(String pNum, int size) {
		char[] nums = pNum.toCharArray();
		int counter = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] >= '0' && nums[i] <= '9') 
				counter++;
		}
		if (counter >= size) {
			return true;
		}
		return false;
	}
	
	private void hasPhone() throws Exception {
		if (mPhone.isEmpty() && hPhone.isEmpty())
			throw new Exception("At Least One Kind Of Phone Number Must Be Entered!");
	}

	@Override
	public int compareTo(Contact c) {
		if (fName.toUpperCase().compareTo(c.fName.toUpperCase()) == 0) {
			if (lName.toUpperCase().compareTo(c.lName.toUpperCase()) == 0) 
				return 0;
			return lName.toUpperCase().compareTo(c.lName.toUpperCase());
		}
		else 
			return fName.toUpperCase().compareTo(c.fName.toUpperCase());
	}


	@Override
	public String toString() {
		return "\t" + fName + " " + lName + " : " + hPhone + " " + mPhone;
	}

}
