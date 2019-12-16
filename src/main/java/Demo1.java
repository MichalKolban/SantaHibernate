import java.util.List;
import java.util.Scanner;

public class Demo1 {

	public static void main(String[] args) {

		ParticipantsDAO participantsDAO = new ParticipantsDAO();

		ParticipantsManager manager = new ParticipantsManager();

		String fullName = "";

		String firstName = "";
		String lastName = "";

		String quit = "";

		do {

			Scanner sc = new Scanner(System.in);

			System.out.println("=====================");
			System.out.println("0 - to print options");

			int number = sc.nextInt();

			switch (number) {

			case 0:
				System.out.println("1 - show all participants");
				System.out.println("2 - save new participant in database");
				System.out.println("3 - draw a person who will receive a gift from you");
				System.out.println("4 - show connections");
				System.out.println("5 - delete participant");
				System.out.println("6 - update participant");
				
				System.out.println("9 - to exit");
				break;

			case 1:

				List<Participants> participantsList = participantsDAO.getParticipants();
				participantsList.forEach(s -> System.out.println(s.getFirstName() + " " + s.getLastName()));

				if (participantsList.isEmpty()) {
					System.out.println("List is empty");
				}

				break;

			case 2:


				sc = new Scanner(System.in);

				System.out.println("Enter your name and last name :");
				fullName = sc.nextLine();

				boolean isParticipantInDB = manager.isParticipantAlreadyExists(fullName);


				if (isParticipantInDB) {
					System.out.println("This participant already exists!");
				} else {
					manager.saveParticipantString(fullName);
					System.out.println(fullName + " is signed in Database");
				}

				break;

			case 3:
				sc = new Scanner(System.in);
				System.out.println("Enter your name and last name:");
				
				fullName = sc.nextLine();
				
				System.out.println(fullName);
				
				manager.drawAPerson(fullName);
				
				break;
				
			case 4:
				
				List<SantaConnections> list = participantsDAO.getSantaConnection();
				list.forEach(s -> System.out.println("Secret Santa is : " + s.getSantaName() + " " + s.getSantaLastName() + " | Reciving : " + s.getUserName() + " " + s.getUserLastName()));

				if (list.isEmpty()) {
					System.out.println("List is empty");
				}

				break;
			case 5:
				
				// usuñ participanta z bazy oraz z pozosta³ych
				sc = new Scanner(System.in);
				System.out.println("Enter name and last name participant to remove");
				String toRemove = sc.nextLine();
				
				String remove = manager.participantToRemove(toRemove);
				
				System.out.println(remove);
				
			case 9:

				System.out.println("Program shutting down...");
				quit = "yes";
				HibernateUtil.getSessionFactory().close();
				break;

			default:

				System.out.println("Try again");

			}

		} while (quit != "yes");

	}

}
