import org.hibernate.Session;
import org.hibernate.Transaction;

public class ParticipantsManager {

	ParticipantsDAO particpantsDAO = new ParticipantsDAO();

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

		Boolean object = particpantsDAO.isParticipantAlreadyExistsMANAGERDAO(firstName, lastName);

		return object;
	}

	public void saveParticipantString(String fullName) {
		
		String[] tab = fullName.split(" ");
		String firstName = tab[0];
		String lastName = tab[1];
		
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
	
	
}
