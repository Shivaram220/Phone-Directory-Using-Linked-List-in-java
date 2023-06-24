import java.util.Scanner;

public class PhoneDirectory{
    public class NodeContact {
        String name;
        long phoneNumber;
        NodeContact next;

        NodeContact(String name, long phoneNumber) {
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.next = null;
        }
    }

    public static NodeContact head;
    public static NodeContact tail;

    // Add a contact to the directory in sorted order
    public void addContact(String name, long phoneNumber) {
        NodeContact newContact = new NodeContact(name, phoneNumber);

        // if (head == null || name.compareToIgnoreCase(head.name) < 0) {
        //     newContact.next = head;
        //     head = newContact;
        if(head == null){
            head = tail = newContact;
        }
        else if(name.compareToIgnoreCase(head.name) < 0){
            newContact.next = head;
            head = newContact;
        }
        else {
            NodeContact current = head;
            NodeContact previous = null;

            while (current != null && name.compareToIgnoreCase(current.name) > 0) {
                previous = current;
                current = current.next;
            }

            newContact.next = current;
            previous.next = newContact;
            if(current == null){
                tail = newContact;
            }
        }
    }

    // Search for a contact by name
    public void searchContact(String name) {
        NodeContact temp = head;
        boolean found = false;

        while (temp != null) {
            if (temp.name.equalsIgnoreCase(name)) {
                System.out.println("Contact found:");
                System.out.println("Name: " + temp.name);
                System.out.println("Phone Number: " + temp.phoneNumber);
                found = true;
                break;
            }
            temp = temp.next;
        }

        if (!found) {
            System.out.println("Contact not found.");
        }
    }

    // Display all contacts in the directory
    public void displayContacts() {
        if (head == null) {
            System.out.println("Phone directory is empty.");
        } else {
            System.out.println("Phone directory contacts:");
            NodeContact temp = head;
            while (temp != null) {
                System.out.println("Name: " + temp.name);
                System.out.println("Phone Number: " + temp.phoneNumber);
                temp = temp.next;
            }
        }
    }

    public void deleteContact(String name) {
        if (head == null) {
            System.out.println("Phone directory is empty No contact to delete.");
            return;
        }

        if (head.name.equalsIgnoreCase(name)) {
            System.out.print(head.name+" ");
            head = head.next;
            System.out.println("Contact deleted successfully.");
            return;
        }

        NodeContact current = head;
        NodeContact previous = null;
        boolean found = false;

        while (current != null) {
            if (current.name.equalsIgnoreCase(name)) {
                found = true;
                break;
            }
            previous = current;
            current = current.next;
        }

        if (found) {
            if(current.next == null){
                previous.next = current.next;
                System.out.println("Contact deleted successfully.");
                tail = previous;
                return;
            }
            previous.next = current.next;
            System.out.println("Contact deleted successfully.");
        } else {
            System.out.println("Contact not found.");
        }
    }
    public void deleteAll(){
        if(head == null){
            System.out.println("No contacts to delete");
        }
        else{
            head = tail = null;
            System.out.println("All contacts are deleted");
        }
    }
    public static void main(String[] args) {
        PhoneDirectory phoneDirectory = new PhoneDirectory();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nPhone Directory Menu:");
            System.out.println("1. Add Contact");
            System.out.println("2. Search Contact");
            System.out.println("3. Display Contacts");
            System.out.println("4. Delete Contact");
            System.out.println("5. Delete all");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter contact name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter phone number: ");
                    long phoneNumber = sc.nextLong();
                    phoneDirectory.addContact(name, phoneNumber);
                    System.out.println("Contact added successfully.");
                    break;
                case 2:
                    System.out.print("Enter contact name to search: ");
                    String searchName = sc.nextLine();
                    phoneDirectory.searchContact(searchName);
                    break;
                case 3:
                    phoneDirectory.displayContacts();
                    break;
                case 4:
                    System.out.print("Enter contact name to delete: ");
                    String delContact = sc.nextLine();
                    phoneDirectory.deleteContact(delContact);
                    break;
                case 5:
                    phoneDirectory.deleteAll();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
                
            }
            
            
        }
    }
}
