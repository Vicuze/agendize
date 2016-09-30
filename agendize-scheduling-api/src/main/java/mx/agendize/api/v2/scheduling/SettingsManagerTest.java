package mx.agendize.api.v2.scheduling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mx.agendize.api.AgendizeException;
import mx.agendize.api.v2.scheduling.reference.Setting;
import mx.agendize.api.v2.scheduling.reference.Setting.SettingGroup;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

public class SettingsManagerTest {

	SettingsManager sm = null; 
	
	@Before
	public void setUp() throws Exception {
		//victor+api@agendize.com
		sm = new SettingsManager("92e0b65e5bb4e14b435528991e02a6c333d4478f", "a0b316705c87dd897f171bcbf949112b");
		//victor+testaz2@agendize.com
		//sm = new SettingsManager("ff1187afe566ae685c9d838f22e709174509067b", "15bbc9261685ee8c5838fc1369082a77");
	}

	@Test
	public void testDeactivateNotifications() throws JSONException, IOException, AgendizeException {
		sm.deactivateNotifications(1924513);
	}

	@Test
	public void testGetSettings() throws JSONException, IOException, AgendizeException {
		List<SettingGroup> groups = new ArrayList<SettingGroup>();
		groups.add(SettingGroup.WIDGET);
		sm.getSettings(1924513, groups);
	}

	@Test
	public void testUpdate() throws JSONException, IOException, AgendizeException {
		Setting s = new Setting(SettingGroup.WIDGET, "displayDuration", "false"); 
		List<Setting> settings = new ArrayList<Setting>(); 
		settings.add(s);
		sm.update(1924513, settings); 
	}
	
	 

}
