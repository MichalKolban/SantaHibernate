import java.util.List;
import java.util.Random;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ParticipantsDAO {
	
	
	public void saveParticipantString(String firstName, String lastName) {
		
		Transaction transaction = null;
		
		Participants participant = new Participants();
		
		participant.setFirstName(firstName);
		participant.setLastName(lastName);
		participant.setIsSantaAlready(false);
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			
			transaction = session.beginTransaction();
			
			session.save(participant);
			
			transaction.commit();
			
		}catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	public Boolean isParticipantAlreadyExists(String fullName) {
		
		String firstName = "";
		String lastName = "";
		
		try {
			String[] tab = fullName.split(" ");
			firstName = tab[0];
			lastName = tab[1];
			
		}catch (Exception e) {
			System.out.println("Please enter name spacebar and last name");
			e.printStackTrace();
		}
		
	Transaction transaction = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			transaction = session.beginTransaction();

			try {

				Participants object = (Participants) session.createQuery(" FROM Participants p WHERE p.firstName = '"
						+ firstName + "' AND p.lastName = '" + lastName + "'").getSingleResult();

				if(object == null) {
					return true;
				}
				
				System.out.println(object.getFirstName() + " " + object.getLastName());

			} catch (Exception e) {
				System.out.println("No participant in database");
			}

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return false;
		
	}
	
	
	public Boolean isParticipantAlreadyExistsMANAGERDAO(String firstName, String lastName) {
		
	Transaction transaction = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			transaction = session.beginTransaction();

			try {

				Participants object = (Participants) session.createQuery(" FROM Participants p WHERE p.firstName = '"
						+ firstName + "' AND p.lastName = '" + lastName + "'").getSingleResult();

				if(object == null) {
					return true;
				}
				
				System.out.println(object.getFirstName() + " " + object.getLastName());

			} catch (Exception e) {
				System.out.println("No participant in database");
			}

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return false;
		
	}
	
	
	
	
	public List<Participants> getParticipants(){
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			return session.createQuery("from Participants", Participants.class).list();
		}
	}
	
	public Participants getSanta(String firstName, String lastName) {

		Participants santa = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			List<Participants> list = session.createQuery("from Participants", Participants.class).list();

			for (Participants person : list) {
				if(person.getIsSantaAlready().equals(true)) {
					System.out.println("PARTICIPANT IS ALREADY A SANTA !");
					return null;
				}else if (person.getLastName().equals(lastName) && person.getFirstName().equals(firstName)) {
					santa = person;
					setSantaInDatabase(santa);
					System.out.println(
							"SUCCES WE HAVE A " + person.getFirstName() + " " + person.getLastName() + " IN DATABASE !!!");
					
					break;
				}
			}
			if (santa == null) {
				System.out.println("NO USER WITH THIS CREDENTIALS IN BASE");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return santa;
	}
	
	private void setSantaInDatabase(Participants santa) {
		
		Transaction transaction = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()){

			transaction = session.beginTransaction();
			
			santa.setIsSantaAlready(true);
			
			session.update(santa);
			
			transaction.commit();
			
		}catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		
	}
	
	
	public Participants getGoodChild (String name, String lastName) {
		
		Participants goodChild = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			
			List<Participants> list = session.createQuery("FROM Participants", Participants.class).list();
					
					Participants choosenOne = getSingleChild(list, name, lastName);
					
					goodChild = choosenOne;
					
					System.out.println("GOOD boy / girl : " + choosenOne.getFirstName() + " " + choosenOne.getLastName());
					
				}
		return goodChild;
		
	}
	
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
	
	public Boolean isParticipantExistsObj(Participants participant) {
		
		List<Participants> list = getParticipants();
		String firstName = participant.getFirstName();
		String lastName = participant.getLastName();
		
		for(Participants l : list) {
			if(l.getFirstName().equals(firstName) && l.getLastName().equals(lastName)) {
				return true;
			}
		}
		return false;
	}

	public void saveSantaConnection(Participants santa, Participants goodChild) {
		
		if(santa != null && goodChild != null) {
		
		Transaction transaction = null;
		
		SantaConnections connections = new SantaConnections();
		
		String santaWholeName = santa.getFirstName() + " " + santa.getLastName();
		String childWholeName = goodChild.getFirstName() + " " + goodChild.getLastName();
		
		connections.setSantaWholeName(santaWholeName);
		connections.setGoodChild(childWholeName);
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			
			transaction = session.beginTransaction();
			
			session.save(connections);
			
			transaction.commit();
			
		}catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		}
		
	}
	
	
	
	
	
	
	
	
// 	==========================   WORKING NOT USIG FOR NOW ==============================
//	
//   public Boolean isParticipantExists(String name, String lastName) {
//		
//		List<Participants> list = getParticipants();
//		
//		if(list.contains(name) && list.contains(lastName)) {
//			return true;
//		}
//		return false;
//	}
	
//	public void saveParticipant(Participants participant) {
//		
//		Transaction transaction = null;
//		
//		try(Session session = HibernateUtil.getSessionFactory().openSession()){
//			
//			transaction = session.beginTransaction();
//			
//			session.save(participant);
//			
//			transaction.commit();
//			
//		}catch(Exception e) {
//			if(transaction != null) {
//				transaction.rollback();
//			}
//			e.printStackTrace();
//		}
//	}
	
	
	
//	================================  NOT WORKING FOR NOW  ================================
//
//	public String createNewParticipant(String name, String lastName){
//		
//		Participants participant = new Participants();
//		ParticipantsDAO participantsDAO = new ParticipantsDAO();
//		
//		participant.setFirstName(name);
//		participant.setLastName(lastName);
//		
//		if(participantsDAO.isParticipantExists(participant.getFirstName(), participant.getLastName())) {
//			participantsDAO.saveParticipant(participant);
//			return "New Participant : " + name + " " + lastName + " added!";
//		}
//		
//		return "Participant " + name + " " + lastName + " already exists!";
//	}
	
	
	
}
