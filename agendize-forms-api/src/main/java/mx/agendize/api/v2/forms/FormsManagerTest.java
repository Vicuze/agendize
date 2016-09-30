package mx.agendize.api.v2.forms;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mx.agendize.api.AgendizeException;
import mx.agendize.api.v2.forms.reference.Field;
import mx.agendize.api.v2.forms.reference.Form;
import mx.agendize.api.v2.forms.reference.FormResult;
import mx.agendize.api.v2.forms.reference.Field.FormFieldType;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

public class FormsManagerTest {

	FormsManager fm = null; 

	@Before
	public void setUp() throws Exception {
		//victor+api@agendize.com
		fm = new FormsManager("92e0b65e5bb4e14b435528991e02a6c333d4478f", "a0b316705c87dd897f171bcbf949112b");
		//victor+testaz2@agendize.com
		//fm = new FormsManager("ff1187afe566ae685c9d838f22e709174509067b", "15bbc9261685ee8c5838fc1369082a77");
	}

	@Test
	public void testGetForms() throws JSONException, AgendizeException {
		List<Form> forms = fm.getForms(); 
	}

	@Test
	public void testGetInt() throws JSONException, AgendizeException {
		List<Form> forms = fm.getForms(); 
		if(!forms.isEmpty()){
			Form f = fm.get(forms.get(0).getId()); 
		}
	}

	@Test
	public void testCreateAndDelete() throws AgendizeException {
		Form f = new Form(); 
		List<Field> fields = new ArrayList<Field>(); 
		Field fi = new Field(FormFieldType.INPUT, "fieldLabel", true, 0, "fieldKey", null); 
		fields.add(fi);
		f.setFields(fields);
		f.setName("Mon formulaire créé par API " + System.currentTimeMillis());
		f.setTitle("Titre de mon formulaire créé par API " + System.currentTimeMillis());
		f.setSubmitMessage("Coucou");
		Form createdForm = fm.create(f);
		fm.delete(createdForm.getId());
	}


	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetResults() throws JSONException, AgendizeException, IOException {
		List<Form> forms = fm.getForms(); 
		if(!forms.isEmpty()){
			List<FormResult> formResults = fm.getResults(forms.get(0).getId()); 
		}
	}

	@Test
	public void testGetIntInt() throws IOException, JSONException, AgendizeException {
		List<Form> forms = fm.getForms(); 
		if(!forms.isEmpty()){
			List<FormResult> formResults = fm.getResults(forms.get(0).getId()); 
			if(!formResults.isEmpty()){
				FormResult formResult = fm.get(formResults.get(0).getId(), forms.get(0).getId()); 
			}
		}
	}

}
