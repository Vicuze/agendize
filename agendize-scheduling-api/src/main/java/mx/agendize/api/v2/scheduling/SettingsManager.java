package mx.agendize.api.v2.scheduling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import mx.agendize.api.APIUtils;
import mx.agendize.api.AgendizeApiManager;
import mx.agendize.api.AgendizeException;
import mx.agendize.api.v2.scheduling.reference.AgendizeSchedulingObjectHelper;
import mx.agendize.api.v2.scheduling.reference.Setting;
import mx.agendize.api.v2.scheduling.reference.Setting.SchedulingMode;
import mx.agendize.api.v2.scheduling.reference.Setting.SettingGroup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class for settings management.
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class SettingsManager extends AgendizeApiManager {
	
	private static final String CLIENT_SMS_DASHBOARD_CHANGE = "clientSMSDashboardChange";
	private static final String CLIENT_EMAIL_DASHBOARD_CHANGE = "clientEmailDashboardChange";
	private static final String CLIENT_SMS_DASHBOARD_CANCEL = "clientSMSDashboardCancel";
	private static final String CLIENT_EMAIL_DASHBOARD_CANCEL = "clientEmailDashboardCancel";
	private static final String CLIENT_SMS_WIDGET_CHANGE = "clientSMSWidgetChange";
	private static final String CLIENT_EMAIL_WIDGET_CHANGE = "clientEmailWidgetChange";
	private static final String CLIENT_SMS_WIDGET_CANCEL = "clientSMSWidgetCancel";
	private static final String CLIENT_EMAIL_WIDGET_CANCEL = "clientEmailWidgetCancel";
	private static final String CLIENT_SMS_WIDGET_NEW = "clientSMSWidgetNew";
	private static final String CLIENT_EMAIL_WIDGET_NEW = "clientEmailWidgetNew";

	private static final String STAFF_SMS_DASHBOARD_CHANGE = "staffSMSDashboardChange";
	private static final String STAFF_EMAIL_DASHBOARD_CHANGE = "staffEmailDashboardChange";
	private static final String STAFF_SMS_DASHBOARD_CANCEL = "staffSMSDashboardCancel";
	private static final String STAFF_EMAIL_DASHBOARD_CANCEL = "staffEmailDashboardCancel";
	private static final String STAFF_SMS_WIDGET_CHANGE = "staffSMSWidgetChange";
	private static final String STAFF_EMAIL_WIDGET_CHANGE = "staffEmailWidgetChange";
	private static final String STAFF_SMS_WIDGET_CANCEL = "staffSMSWidgetCancel";
	private static final String STAFF_EMAIL_WIDGET_CANCEL = "staffEmailWidgetCancel";
	private static final String STAFF_SMS_WIDGET_NEW = "staffSMSWidgetNew";
	private static final String STAFF_EMAIL_WIDGET_NEW = "staffEmailWidgetNew";

	private static final String MANAGER_SMS_DASHBOARD_CHANGE = "managerSMSDashboardChange";
	private static final String MANAGER_EMAIL_DASHBOARD_CHANGE = "managerEmailDashboardChange";
	private static final String MANAGER_SMS_DASHBOARD_CANCEL = "managerSMSDashboardCancel";
	private static final String MANAGER_EMAIL_DASHBOARD_CANCEL = "managerEmailDashboardCancel";
	private static final String MANAGER_SMS_DECLINE = "managerSMSDecline";
	private static final String MANAGER_EMAIL_DECLINE = "managerEmailDecline";
	private static final String MANAGER_SMS_WIDGET_CHANGE = "managerSMSWidgetChange";
	private static final String MANAGER_EMAIL_WIDGET_CHANGE = "managerEmailWidgetChange";
	private static final String MANAGER_SMS_WIDGET_CANCEL = "managerSMSWidgetCancel";
	private static final String MANAGER_EMAIL_WIDGET_CANCEL = "managerEmailWidgetCancel";
	private static final String MANAGER_SMS_WIDGET_NEW = "managerSMSWidgetNew";
	private static final String MANAGER_EMAIL_WIDGET_NEW = "managerEmailWidgetNew";

	static final Logger logger = LogManager.getLogger(SettingsManager.class);

	private static final String GROUP = "group";

	public SettingsManager(String apiKey, String token) throws IOException {
		super(apiKey, token);
	}
	
	/**
	 * gets the settings for the company and the setting groups.
	 * @param companyId Company identifier.
	 * @param groups List of settings group identifiers. Group identifiers can be "notifications", "items", "contact", "rules", "widget". if null or empty, will return all the settings for the company. See <a herf="http://developers.agendize.com/v2/scheduling/reference/settings/index.jsp">http://developers.agendize.com/v2/scheduling/reference/settings/index.jsp</a>
	 * @return the list of settings.
	 * @throws IOException
	 */
	public List<Setting> getSettings(int companyId, List<SettingGroup> groups) throws IOException{
		logger.info("getSettings. Company id = " + companyId + "."+ ((groups!=null && !groups.isEmpty())?(" Groups = "+groups):""));
		List<Setting> result = new ArrayList<Setting>();
		if(groups!=null && !groups.isEmpty()){
			for (SettingGroup group: groups){
				result.addAll(getSettingsForGroup(companyId, group));
			}
		} else {
			result.addAll(getSettingsForGroup(companyId, null));
		}
		return result;
	}
	
	/**
	 * Gets all the settings for a given group. Groups can be "notifications", "items", "contact", "rules","widget". See <a href="http://developers.agendize.com/v2/scheduling/reference/settings/index.jsp">http://developers.agendize.com/v2/scheduling/reference/settings/index.jsp</a>
	 * @param companyId
	 * @param group
	 * @return all the settings for the specific group. 
	 * @throws IOException
	 */
	private List<Setting> getSettingsForGroup(int companyId, SettingGroup group) throws IOException{
		logger.info("getSettings. Company id = " + companyId + "."+ " Group = "+group.getCode());
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		if(group != null){
			properties.put(GROUP, group.getCode());
		}
		String settingsString = APIUtils.getRequest(SCHEDULING_API_COMPANIES_URL+"/"+companyId+"/settings", properties);
		JSONObject jsonObject = new JSONObject(settingsString);
		return AgendizeSchedulingObjectHelper.jsonArrayToSettingList(jsonObject.getJSONArray("items"));
	}
	
	/**
	 * Returns all the notification settings.
	 * @param companyId
	 * @return all the notification settings.
	 * @throws IOException
	 */
	public List<Setting> getNotificationSettings(int companyId) throws IOException{
		return getSettingsForGroup(companyId, SettingGroup.NOTIFICATIONS);
	}
	
	/**
	 * Saves or updates a setting for a company.
	 * @param companyId Company identifier.
	 * @param settings Settings to update. 
	 * @return The list of all company's Settings.
	 * @throws IOException
	 * @throws JSONException
	 * @throws AgendizeException 
	 */
	public List<Setting> update(int companyId, List<Setting> settings) throws IOException, JSONException, AgendizeException{
		logger.info("Update settings for company with id = " + companyId + ".");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		String urlString = SCHEDULING_API_COMPANIES_URL + "/" + companyId + "/settings";
		String json = AgendizeSchedulingObjectHelper.settingListToJSONObject(settings).toString();
		String settingsString = APIUtils.putRequest(urlString, json, properties);
		JSONObject jsonObject = new JSONObject(settingsString);
		List<Setting> result = AgendizeSchedulingObjectHelper.jsonArrayToSettingList(jsonObject.getJSONArray("items"));
		logger.info("Update succesful.");
		return result; 
	}
	
	/**
	 * Returns the scheduling mode for the company.
	 * @param companyId
	 * @return The scheduling mode of the company.
	 * @throws IOException
	 */
	public SchedulingMode getSchedulingMode(int companyId) throws IOException{
		SettingsManager settingsManager = new SettingsManager(apiKey, token);
		List<SettingGroup> groups = new ArrayList<SettingGroup>();
		groups.add(SettingGroup.ITEMS);
		List<Setting> settings = settingsManager.getSettings(companyId, groups);
		SchedulingMode result = null;
		for(Setting setting: settings){
			if(setting.getId().equals("scheduledItems")){
				result = SchedulingMode.get(setting.getValue());
			}
		}
		return result;
	}
	
	/**
	 * This will deactivate all the notifications. /!\ If you want to reactivate the notifications later, don't forget to save the current settings before;
	 * @param companyId
	 * @return updated settings
	 * @throws AgendizeException 
	 * @throws IOException 
	 * @throws JSONException 
	 */
	public List<Setting> deactivateNotifications(int companyId) throws JSONException, IOException, AgendizeException{
		List<Setting> settings = new ArrayList<Setting>();
		settings.add(new Setting(SettingGroup.NOTIFICATIONS, MANAGER_EMAIL_WIDGET_NEW, "false"));
		settings.add(new Setting(SettingGroup.NOTIFICATIONS, MANAGER_SMS_WIDGET_NEW, "false"));
		settings.add(new Setting(SettingGroup.NOTIFICATIONS, MANAGER_EMAIL_WIDGET_CANCEL, "false"));
		settings.add(new Setting(SettingGroup.NOTIFICATIONS, MANAGER_SMS_WIDGET_CANCEL, "false"));
		settings.add(new Setting(SettingGroup.NOTIFICATIONS, MANAGER_EMAIL_WIDGET_CHANGE, "false"));
		settings.add(new Setting(SettingGroup.NOTIFICATIONS, MANAGER_SMS_WIDGET_CHANGE, "false"));
		settings.add(new Setting(SettingGroup.NOTIFICATIONS, MANAGER_EMAIL_DECLINE, "false"));
		settings.add(new Setting(SettingGroup.NOTIFICATIONS, MANAGER_SMS_DECLINE, "false"));
		settings.add(new Setting(SettingGroup.NOTIFICATIONS, MANAGER_EMAIL_DASHBOARD_CANCEL, "false"));
		settings.add(new Setting(SettingGroup.NOTIFICATIONS, MANAGER_SMS_DASHBOARD_CANCEL, "false"));
		settings.add(new Setting(SettingGroup.NOTIFICATIONS, MANAGER_EMAIL_DASHBOARD_CHANGE, "false"));
		settings.add(new Setting(SettingGroup.NOTIFICATIONS, MANAGER_SMS_DASHBOARD_CHANGE, "false"));
		
		settings.add(new Setting(SettingGroup.NOTIFICATIONS, STAFF_EMAIL_WIDGET_NEW, "false"));
		settings.add(new Setting(SettingGroup.NOTIFICATIONS, STAFF_SMS_WIDGET_NEW, "false"));
		settings.add(new Setting(SettingGroup.NOTIFICATIONS, STAFF_EMAIL_WIDGET_CANCEL, "false"));
		settings.add(new Setting(SettingGroup.NOTIFICATIONS, STAFF_SMS_WIDGET_CANCEL, "false"));
		settings.add(new Setting(SettingGroup.NOTIFICATIONS, STAFF_EMAIL_WIDGET_CHANGE, "false"));
		settings.add(new Setting(SettingGroup.NOTIFICATIONS, STAFF_SMS_WIDGET_CHANGE, "false"));
		settings.add(new Setting(SettingGroup.NOTIFICATIONS, STAFF_EMAIL_DASHBOARD_CANCEL, "false"));
		settings.add(new Setting(SettingGroup.NOTIFICATIONS, STAFF_SMS_DASHBOARD_CANCEL, "false"));
		settings.add(new Setting(SettingGroup.NOTIFICATIONS, STAFF_EMAIL_DASHBOARD_CHANGE, "false"));
		settings.add(new Setting(SettingGroup.NOTIFICATIONS, STAFF_SMS_DASHBOARD_CHANGE, "false"));
		
		settings.add(new Setting(SettingGroup.NOTIFICATIONS, CLIENT_EMAIL_WIDGET_NEW, "false"));
		settings.add(new Setting(SettingGroup.NOTIFICATIONS, CLIENT_SMS_WIDGET_NEW, "false"));
		settings.add(new Setting(SettingGroup.NOTIFICATIONS, CLIENT_EMAIL_WIDGET_CANCEL, "false"));
		settings.add(new Setting(SettingGroup.NOTIFICATIONS, CLIENT_SMS_WIDGET_CANCEL, "false"));
		settings.add(new Setting(SettingGroup.NOTIFICATIONS, CLIENT_EMAIL_WIDGET_CHANGE, "false"));
		settings.add(new Setting(SettingGroup.NOTIFICATIONS, CLIENT_SMS_WIDGET_CHANGE, "false"));
		settings.add(new Setting(SettingGroup.NOTIFICATIONS, CLIENT_EMAIL_DASHBOARD_CANCEL, "false"));
		settings.add(new Setting(SettingGroup.NOTIFICATIONS, CLIENT_SMS_DASHBOARD_CANCEL, "false"));
		settings.add(new Setting(SettingGroup.NOTIFICATIONS, CLIENT_EMAIL_DASHBOARD_CHANGE, "false"));
		settings.add(new Setting(SettingGroup.NOTIFICATIONS, CLIENT_SMS_DASHBOARD_CHANGE, "false"));
		return update(companyId, settings);
	}
}
