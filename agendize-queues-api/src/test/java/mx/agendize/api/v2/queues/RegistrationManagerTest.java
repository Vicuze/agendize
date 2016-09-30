package mx.agendize.api.v2.queues;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import mx.agendize.api.AgendizeException;
import mx.agendize.api.v2.queues.reference.Queue;
import mx.agendize.api.v2.queues.reference.QueueClient;
import mx.agendize.api.v2.queues.reference.Registration;
import mx.agendize.api.v2.queues.reference.Registration.RegistrationStatus;
import mx.agendize.api.v2.reference.Time;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

public class RegistrationManagerTest {

	RegistrationManager rm = null; 
	QueuesManager qm = null; 
	
	@Before
	public void setUp() throws Exception {
		rm = new RegistrationManager("ff1187afe566ae685c9d838f22e709174509067b", "15bbc9261685ee8c5838fc1369082a77");
		qm = new QueuesManager("ff1187afe566ae685c9d838f22e709174509067b", "15bbc9261685ee8c5838fc1369082a77");
	}

	@Test
	public void testGetRegistrations() throws JSONException, AgendizeException {
		List<Queue> queues = qm.getQueues(); 
		if(!queues.isEmpty()){
			List<Registration> registrations = rm.getRegistrations(queues.get(0).getId()); 
		}
	}

	@Test
	public void testGet() throws JSONException, AgendizeException, IOException {
		List<Queue> queues = qm.getQueues(); 
		if(!queues.isEmpty()){
			List<Registration> registrations = rm.getRegistrations(queues.get(0).getId()); 
			if(!registrations.isEmpty()){
				Registration reg = rm.get(queues.get(0).getId(), registrations.get(0).getId()); 
			}
		}
	}

	@Test
	public void testUpdate() throws JSONException, AgendizeException, IOException {
		List<Queue> queues = qm.getQueues(); 
		if(!queues.isEmpty()){
			List<Registration> registrations = rm.getRegistrations(queues.get(0).getId()); 
			if(!registrations.isEmpty()){
				RegistrationStatus oldStatus = registrations.get(0).getStatus(); 
				registrations.get(0).setStatus(RegistrationStatus.FINISHED);
				rm.update(queues.get(0).getId(), registrations.get(0));
				registrations.get(0).setStatus(RegistrationStatus.REGISTERED);
				rm.update(queues.get(0).getId(), registrations.get(0));
				registrations.get(0).setStatus(oldStatus);
				rm.update(queues.get(0).getId(), registrations.get(0));
			}
		}
	}

	@Test
	public void testCreateAndDelete() throws JSONException, AgendizeException, IOException {
		List<Queue> queues = qm.getQueues(); 
		if(!queues.isEmpty()){
			Time registered = new Time("America/Mexico_City", new Date());
			QueueClient client = new QueueClient("John", "Smith", "js@agendizedemo.com", null);
			Registration r = new Registration("vthivillier@agendize.com", queues.get(0), registered, client, RegistrationStatus.REGISTERED);
			Registration createdRegistration = rm.create(queues.get(0).getId(), r);
			
		}
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testNext() throws JSONException, AgendizeException {
		List<Queue> queues = qm.getQueues(); 
		if(!queues.isEmpty()){
			Registration r = rm.next(queues.get(0).getId());
		}
	}

}
