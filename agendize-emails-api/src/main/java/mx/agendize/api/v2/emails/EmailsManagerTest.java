package mx.agendize.api.v2.emails;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mx.agendize.api.AgendizeException;
import mx.agendize.api.v2.clients.ClientsManager;
import mx.agendize.api.v2.emails.reference.EmailTemplate;
import mx.agendize.api.v2.emails.reference.MarketingEmail;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

public class EmailsManagerTest {

	EmailsManager em = null; 
	ClientsManager cm = null; 

	@Before
	public void setUp() throws Exception {
		//victor+api@agendize.com
		em = new EmailsManager("92e0b65e5bb4e14b435528991e02a6c333d4478f", "a0b316705c87dd897f171bcbf949112b");
		cm = new ClientsManager("92e0b65e5bb4e14b435528991e02a6c333d4478f", "a0b316705c87dd897f171bcbf949112b");
		//victor+testaz2@agendize.com
		//em = new EmailsManager("ff1187afe566ae685c9d838f22e709174509067b", "15bbc9261685ee8c5838fc1369082a77");
		//cm = new ClientsManager("ff1187afe566ae685c9d838f22e709174509067b", "15bbc9261685ee8c5838fc1369082a77");
	}

	@Test
	public void testGetEmailTemplates() throws IOException {
		List<EmailTemplate> emailTemplates = em.getEmailTemplates(); 
		for(EmailTemplate et: emailTemplates){
			//System.out.println(et.getName());
		}
	}

	@Test
	public void testGet() throws IOException {
		List<EmailTemplate> emailTemplates = em.getEmailTemplates(); 
		if(!emailTemplates.isEmpty()){
			//System.out.println(em.get(emailTemplates.get(0).getId()));
		}
	}

	@Test
	public void testCreateAndDelete() throws JSONException, AgendizeException {
		EmailTemplate et = new EmailTemplate("30% off", "<html><body><h1>30%off</h1></br/><br/><h2>We're happy to offer you 30% on all services!</h2></body></html>", "30% discount " + System.currentTimeMillis()); 
		EmailTemplate createdTemplate = em.create(et);
		//em.delete(createdTemplate.getId());
	}

	@Test
	public void testUpdate() throws IOException, AgendizeException {
		List<EmailTemplate> emailTemplates = em.getEmailTemplates(); 
		if(!emailTemplates.isEmpty()){
			EmailTemplate templateToUpdate = emailTemplates.get(0); 
			String oldName = templateToUpdate.getName(); 
			templateToUpdate.setName("Temp name");
			EmailTemplate updatedTemplate = em.update(templateToUpdate);
			updatedTemplate.setName(oldName);
			em.update(updatedTemplate);
		}
	}

	@Test
	public void testSend() throws IOException, AgendizeException {
		//List<Client> clients = cm.getClients(""); 
		List<EmailTemplate> emailTemplates = em.getEmailTemplates(); 
		List<Integer> recipients = new ArrayList<Integer>(); 
		MarketingEmail me = null; 
		if(!emailTemplates.isEmpty()){
			//if(clients != null && !clients.isEmpty()){
				//for(Client client : clients){
					recipients.add(697731);			
				//}
				me = new MarketingEmail(emailTemplates.get(0).getId(), recipients); 		
				em.send(me);
			//}
		}
	}

	@Test
	public void testSend2() throws IOException, AgendizeException {
		//List<Client> clients = cm.getClients(""); 
		String html = "<html><body><h1>30%off</h1></br/><br/><h2>We're happy to offer you 30% on all services!</h2></body></html>";
		String subject = "30% off!";
		//if(clients != null && !clients.isEmpty()){
			List<Integer> recipients = new ArrayList<Integer>(); 
			//for(Client client : clients){
				recipients.add(697731);			
			//}
			MarketingEmail me = new MarketingEmail(subject, html, recipients); 
			em.send(me);
		//}
	}

}
