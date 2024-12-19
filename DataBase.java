
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// This is the database class which also contains the dataBaseArray. Here I am able to manage operations
// within the database including adding, removing, and finding records
public class DataBase {
	IndexArray IdArr, firstArr, lastArr;
	DataBaseRecord[] DataBaseArray;
	int size;
	Scanner scan = new Scanner(System.in);

//new database dataArray object to store array of DataBase records
	public DataBase() {
		DataBaseArray = new DataBaseRecord[100];
		size = 0;
		IdArr = new IndexArray();
		firstArr = new IndexArray();
		lastArr = new IndexArray();
		File data = new File("/Users/omarhabeel/Documents/data2.txt");

//here I set up a file input scanner to read in all the data from my input file. My eclipse was bugged
// so for some reason when I imported the file it kept failing to read it, so I just set the file
//path directly on my computer. I read the data in line by line, then remove excess whitespace, and
//then split the first and last name and ID number. I then read create the IndexRecord with the information
//unless the ID is already present in the database
		try (Scanner fileScanner = new Scanner(data)) {
			while (fileScanner.hasNextLine()) {
				String[] line = fileScanner.nextLine().trim().toLowerCase().split("\\s+");
				String last = line[0];
				String first = line[1];
				String ID = line[2];
				if (IdArr.search(ID) == -1) {
					addRecord(ID, first, last);
				} else {
					System.out.println("duplicate removed");
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void addRecord(String ID, String first, String last) {
		DataBaseRecord record = new DataBaseRecord(ID, first, last);
		addRecord(record);
		int where = this.size - 1;
		IdArr.addIndex(new IndexRecord(ID, where));
		firstArr.addIndex(new IndexRecord(first, where));
		lastArr.addIndex(new IndexRecord(last, where));
	}

//This is the actual method for adding records to the database where 3 parameters are present and are all
	// added to a record which is then added to the database. The index is also
	// grabbed here.

	public void addIt() {
		System.out.print("Enter ID: ");
		String ID = scan.nextLine();
		if (IdArr.search(ID) != -1) {
			System.out.println("ID already in use");
			return;
		}
		System.out.print("Enter first name: ");
		String first = scan.nextLine().toLowerCase();
		System.out.print("Enter last name: ");
		String last = scan.nextLine().toLowerCase();
		addRecord(ID, first, last);
	}

//The user inputs are taken and inputted into appropriate variables. This only happens if the ID isn't
//in use already.  I then set them to lowercase so that later when comparisons happen case isn't taken 
//into consideration. I then call addRecord to add it to the database.

	public void deleteIt() {
		System.out.print("Enter ID: ");
		String ID = scan.nextLine();
		int where = IdArr.search(ID);
		if (where != -1) {
			IdArr.deleteIndex(where);
			firstArr.deleteIndex(where);
			lastArr.deleteIndex(where);
			System.out.println("Deleted");
			size--;
		} else {
			System.out.println("Record not found");
			return;
		}
	}

//First the ID is searched for in the index Array and if the ID is found the index for the information
//in the record is removed. Then the size is decremented.

	public void findIt() {
		System.out.print("Enter ID: ");
		String ID = this.scan.nextLine();
		int index = this.IdArr.search(ID);
		if (index == -1) {
			System.out.println("Record not found");
			return;
		}
		System.out.println(this.getRecord(IdArr.search(ID)));
	}

//Uses the search method in the IndexArray class instead of the binarySearch method that was present
//in the ordered array. Now traverses the linked list

	private void printIt(boolean ascending, IndexArray arr) {
		if (ascending) {
			arr.iteratorInitFront();
			while (arr.hasNext()) {
				System.out.println(this.getRecord(arr.getNext()));
			}
		} else {
			arr.iteratorInitBack();
			while (arr.hasPrevious()) {
				System.out.println(this.getRecord(arr.getPrevious()));
			}
		}
	}

	// There is a boolean value of ascending or not inputted into the method, and if
	// it is ascending the
	// iterator is set to the front and prints the record forward. If ascending is
	// false, the iterator
	// is initialized to the back and decrements printing all the information out
	// backwards. The second
	// input taken by this method is the type of array that the user wants to print
	// whether that be first name,
	// last, or Id number
	public void ListByIDAscending() {
		this.printIt(true, IdArr);
	}

	public void ListByIDDescending() {
		this.printIt(false, IdArr);
	}

	public void ListByFirstAscending() {
		this.printIt(true, firstArr);
	}

	public void ListByFirstDescending() {
		this.printIt(false, firstArr);
	}

	public void ListByLastAscending() {
		this.printIt(true, lastArr);
	}

	public void ListByLastDescending() {
		this.printIt(false, lastArr);
	}

//These are for listing the records out and use the printIt method,and the two parameters vary based 
//on which array is to be printed and which direction. These only exist for the sake of adhering to the
//driver class as it would be much simpler to put these all in some kind of loop/if statement.

	public int getSize() {
		return size;
	}

	public void addRecord(DataBaseRecord record) {
		if (size < DataBaseArray.length) {
			DataBaseArray[size++] = record;
		}
	}

//takes a database record object as an arg and just adds a record to the end, while incrementing the size.

	public DataBaseRecord getRecord(int index) {
		return DataBaseArray[index];
	}
}

//An index is taken in as a param, and the databaserecord present at that index in the records array is 
//returned 
