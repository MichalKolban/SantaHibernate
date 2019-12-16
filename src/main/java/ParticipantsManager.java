import java.util.List;
import java.util.Random;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ParticipantsManager {

	ParticipantsDAO participantsDAO = new ParticipantsDAO();

	public Boolean isParticipantAlreadyExists(String fullName) {

		String firstName = "";
		String lastName = "";

		try {
			String[] tab = fullName.split(" ");
			firstName = tab[0];
			lastName = tab[1];

		} catch (Exception e) {
			System.out.println("Please enter name spacebar and last name");
			e.printStackTrace();
		}

		Boolean object = participantsDAO.isParticipantAlreadyExistsMANAGERDAO(firstName, lastName);

		return object;
	}

	public void saveParticipantString(String fullName) {
		
		String[] tab = fullName.split(" ");
		String firstName = tab[0].trim();
		String lastName = tab[1].trim();
		
		Transaction transaction = null;

		Participants participant = new Participants();

		participant.setFirstName(firstName);
		participant.setLastName(lastName);
		participant.setIsSantaAlready(false);

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			transaction = session.beginTransaction();

			session.save(participant);

			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	
	// wybierasz osobe
	// sprawdzamy czy Ty jestes w bazie
	// jezeli jesteœ to :
		// sprawdzamy czy nie jesteœ juz sant¹ 
		// losujemy Ci osobe
		// ustawiamy Ciebie ze jestes juz sant¹ 
		// ustawiamy juz wylosowan¹ osobê ze ktos juz j¹ ma
		// zapisac relacje w nowej tabeli
	
	// jezeli nie jestes ;
		// propozycja zapisu w bazie jako nowy uczestnik 
	
	public void drawAPerson(String fullName) {
		
		
		String[] tab = fullName.split(" ");
		String firstName = tab[0];
		String lastName = tab[1];
		
		// najpierw sprawdzamy czy jest taka osoba w bazie 
		// czu nie jest ju¿ sant¹ 
		
		boolean exists = participantsDAO.isParticipantAlreadyExistsMANAGERDAO(firstName, lastName);
		boolean isAlreadyASanta = participantsDAO.isPerticipantAlreadyASanta(firstName, lastName);
		
		// jezeli jest to losuje osobe
		// santa ustawiany jest w bazie jako osoba która juz by³a sant¹
		
		if(exists && isAlreadyASanta == false) {
			
			try {
			
			Participants santa = new Participants();
			
			// ustawiamy ¿e dana osoba jest ju¿ sant¹
			participantsDAO.setSantaInDatabase(firstName, lastName);
			
			// wybraæ osobê obdarowywan¹
			List<Participants> list = participantsDAO.getParticipants();
			
			for(Participants person : list) {
				
				if(person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
					santa = person;
				}
				
			}
			
			Participants recivingParticipant = getSingleChild(list, firstName, lastName);
			
			System.out.println("Secret santa is : " + firstName + " " + lastName);
			System.out.println("Revicing Participant is : " + recivingParticipant.getFirstName() + " " + recivingParticipant.getLastName());
			
			
			// zapisaæ powi¹zanie w nowej tabeli
			
			participantsDAO.saveSantaConnection(santa, recivingParticipant);
			
			
			System.out.println(" Klient jest w bazie ale nie jest Sant¹");
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else {
			
			System.out.println("NAH");
		}

			}
	
	
	public String participantToRemove(String toRemove) {

		String message = "";
		
		Boolean exists = isParticipantAlreadyExists(toRemove);
		
		String[] tab = toRemove.split(" ");
		
		String name = tab[0];
		String lastName = tab[1];
		
		if(exists) {
			
			participantsDAO.removeParticipant(name, lastName);
			System.out.println(toRemove + " is removed");
			
			message = toRemove + " is succesfully deleted from DB";
			
		} else {
			message = " something gone wrong x( ";
		}
		
		
		return message;
		
	}
	
//public Participants getGoodChild (String name, String lastName) {
//		
//		Participants goodChild = null;
//		try(Session session = HibernateUtil.getSessionFactory().openSession()){
//			
//			List<Participants> list = session.createQuery("FROM Participants", Participants.class).list();
//					
//					Participants choosenOne = getSingleChild(list, name, lastName);
//					
//					goodChild = choosenOne;
//					
//					System.out.println("GOOD boy / girl : " + choosenOne.getFirstName() + " " + choosenOne.getLastName());
//					
//				}
//		return goodChild;
//		
//	}
	
	private Participants getSingleChild(List<Participants> list, String name, String lastName) {

		Participants choosen = null;
		Random r = new Random();
		choosen = list.get(r.nextInt(list.size()));
		while (choosen.getFirstName().equals(name) && choosen.getLastName().equals(lastName)) {
			r = new Random();
			choosen = list.get(r.nextInt(list.size()));
		}
		return choosen;

	}

	
	
	
}
