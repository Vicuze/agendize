package mx.agendize.api.v2.queues;

import java.util.List;

import mx.agendize.api.AgendizeException;
import mx.agendize.api.data.objects.FormDetails;
import mx.agendize.api.v2.queues.reference.Queue;
import mx.agendize.api.v2.scheduling.reference.Company;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

public class QueuesManagerTest {

	QueuesManager qm = null; 
	
	@Before
	public void setUp() throws Exception {
	qm = new QueuesManager("ff1187afe566ae685c9d838f22e709174509067b", "15bbc9261685ee8c5838fc1369082a77");
	}

	@Test
	public void testGetQueues() throws JSONException, AgendizeException {
		List<Queue> queues = qm.getQueues();
		for(Queue q: queues){
			System.out.println(q);
		}
	}

	@Test
	public void testCreateAndDelete() throws JSONException, AgendizeException {
		Queue q = new Queue(); 
		q.setAuthor("Victor");
		q.setCompany(new Company(440048, "test"));
		q.setForm(new FormDetails(3172697, "test"));
		q.setName("Queue created via API");
		q.setThankMessage("This is the thank you message");
		q.setWelcomeMessage("This is the welcome message");
		Queue createdQueue = qm.create(q);
		qm.delete(createdQueue.getId());
	}

	@Test
	public void testUpdate() throws JSONException, AgendizeException {
		List<Queue> queues = qm.getQueues();
		if(!queues.isEmpty()){
			String realName = queues.get(0).getName();
			queues.get(0).setName("tempName");
			qm.update(queues.get(0));
			queues.get(0).setName(realName);
			qm.update(queues.get(0));
		}
	}
}
