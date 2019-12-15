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
				System.out.println("3 - enter your name to pick secretSanta");
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

				// check if this persona already exists ?

				sc = new Scanner(System.in);

				System.out.println("Enter your name and last name :");
				fullName = sc.nextLine();

				boolean isParticipantInDB = manager.isParticipantAlreadyExists(fullName);

				// if not save to DB

				if (isParticipantInDB) {
					System.out.println("This participant already exists!");
				} else {
//					participantsDAO.saveParticipantString(firstName, lastName);
					manager.saveParticipantString(fullName);
					System.out.println(fullName + " is signed in Database");
				}

				break;

			case 3:

				System.out.println("Enter your name:");
				firstName = sc.next();
				System.out.println("Enter your lastName");
				lastName = sc.next();

				Participants santa = participantsDAO.getSanta(firstName, lastName);

				if (santa != null) {
					System.out.println("SANTA NAME : " + santa.getFirstName() + " " + santa.getLastName());
				} else {
					System.out.println(firstName + " " + lastName + " is already a Santa !");

					// once again pick connection with santa and goodChild from DB

					break;

				}

				Participants goodChild = participantsDAO.getGoodChild(firstName, lastName);
				System.out.println(goodChild.getFirstName() + " " + goodChild.getLastName());

				System.out.println("SANTA " + santa.getFirstName() + " " + santa.getLastName() + "/t" + " Good child : "
						+ goodChild.getFirstName() + " " + goodChild.getLastName());

				break;

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
