package mx.agendize.api.v2.scheduling;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import mx.agendize.api.APIUtils;
import mx.agendize.api.AgendizeApiManager;
import mx.agendize.api.AgendizeException;
import mx.agendize.api.v2.reference.Time;
import mx.agendize.api.v2.scheduling.reference.AgendizeSchedulingObjectHelper;
import mx.agendize.api.v2.scheduling.reference.Appointment;
import mx.agendize.api.v2.scheduling.reference.Client;
import mx.agendize.api.v2.scheduling.reference.Company;
import mx.agendize.api.v2.scheduling.reference.Resource;
import mx.agendize.api.v2.scheduling.reference.Service;
import mx.agendize.api.v2.scheduling.reference.Setting;
import mx.agendize.api.v2.scheduling.reference.Staff;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class for Companies management.
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class CompaniesManager extends AgendizeApiManager{
	
	static final Logger logger = LogManager.getLogger(CompaniesManager.class);
	
	/**
	 * @param apiKey API Key. No API Key? <a target="_blank" href="http://app.agendize.com/account#app" >Get one here</a>
	 * @param token SSO token. See <a target="_blank" href="http://developers.agendize.com/en/p/authentication" >http://developers.agendize.com/en/p/authentication</a>
	 * @throws IOException in case the API key or SSO token are not valid
	 */
	public CompaniesManager(String apiKey, String token) throws IOException {
		super(apiKey, token);
	}

	/**
	 * Gives the list of companies for an agendize account
	 * @return The list of company objects.
	 * @throws IOException
	 * @throws JSONException
	 */
	public List<Company> getCompanies() throws IOException {
		logger.info("getCompanies.");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
        String companiesString = APIUtils.getRequest(SCHEDULING_API_COMPANIES_URL, properties);
		JSONObject jsonObject = new JSONObject(companiesString);
		List<Company> result = AgendizeSchedulingObjectHelper.jsonArrayToCompanyList(jsonObject.getJSONArray("items"));
		logger.info(result.size() + " companies found.");
		return result;
	}
	
	/**
	 * Returns company object given the company identifier
	 * @param id Company identifier.
	 * @return The company object.
	 * @throws JSONException
	 * @throws IOException
	 */
	public Company get(int id) throws JSONException, IOException{
		logger.info("Get company with id = " + id + ".");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
        String companyString = APIUtils.getRequest(SCHEDULING_API_COMPANIES_URL+"/"+id, properties);
        Company result = null;
        if(companyString != null && !"".equals(companyString)){
        	result = AgendizeSchedulingObjectHelper.jsonObjectToCompany(new JSONObject(companyString));
        }
		if(result != null){
			logger.info("Company found.");
		} else {
			logger.info("No company found for this id.");
		}
		return result;
	}
	
	/**
	 * Creates a company. 
	 * @param company the company to create. 
	 * @return The company created.
	 * @throws JSONException
	 * @throws AgendizeException 
	 */
	public Company create(Company company) throws JSONException, AgendizeException{
		logger.info("Create new company.");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		String json = AgendizeSchedulingObjectHelper.companyToJSONObject(company).toString();
		String companyString = APIUtils.postRequest(SCHEDULING_API_COMPANIES_URL, json, properties);
		JSONObject jsonObject = new JSONObject(companyString);
		Company result = AgendizeSchedulingObjectHelper.jsonObjectToCompany(jsonObject);
		logger.info("Company created succesfully. id = " + result.getId());
        return result;
	}
	
	/**
	 * Fills the company schedule with random appointments. Only works for service & staff mode. This method will deactivate the notifications before creating all the appointments, to avoid spam. Notification will be reseted to their original configuration after the appointments creation.
	 * @param company The company. 
	 * @param startDate the start date. format: yyyy-MM-dd. No appointments will be created before today, so giving a past date is useless.
	 * @param numberOfWeeks Number of weeks for which you want to fill the calendar with appointments.
	 * @param numberOfAppointmentPerDay Number of appointment per days you want to create.
	 * @param createClients <p>If true, random clients will be created.</p>
	 * <p> If false, existing clients will be used. /!\ In that case, real e-mails will be sent!</p>
	 * @param locale for the names of the created clients. Spanish, French and English implemented. Default: English.
	 * @throws JSONException
	 * @throws ParseException
	 * @throws AgendizeException 
	 * @throws IOException 
	 */
	public List<Appointment> fillAppointments(Company company, String startDate, int numberOfWeeks, 
			int numberOfAppointmentPerDay, boolean createClients, Locale locale) throws JSONException, ParseException, AgendizeException, IOException{

		AppointmentsManager appointmentManager = null;
		ServicesManager servicesManager = null;
		SettingsManager settingsManager = null;
		
		appointmentManager = new AppointmentsManager(apiKey, token);
		servicesManager = new ServicesManager(apiKey, token); 
		settingsManager = new SettingsManager(apiKey, token); 

		/* Lest's save the notification settings, we're going to deactivate them while we create the appointments, to prevent spamming */
		List<Setting> originalNotifSettings =  settingsManager.getNotificationSettings(company.getId());
		/* Deactivation of notifications */
		settingsManager.deactivateNotifications(company.getId());
		
		List<Appointment> result = new ArrayList<Appointment>();
		
		try{
			//Calcul de la date de fin
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(startDate));
			cal.add(Calendar.DATE, numberOfWeeks*7);
			Date endDate = cal.getTime();
			
			// First get the service list
			List<Service> services = servicesManager.getServices(company.getId());
	
			int numberOfClients = 30;
			
			// Second get (or generate) Client list
			List<Client> clients = new ArrayList<Client>();
			if(createClients){
				List<String> maleFirstNames;
				List<String> femaleFirstNames;
				List<String> lastNames;
				//TODO separate male/female & add gender
				if(locale.getLanguage().equals(new Locale("es").getLanguage())){
					maleFirstNames = Arrays.asList("Bernardo","Camilo","Gabriel","H&#233;ctor","Jorge","Quentin","Roberto","Sebasti&#225;n","Tiago","Ulises","Walter","Xavier","Yannick");
					femaleFirstNames = Arrays.asList("Angelica","Diana","Elisa","Fernanda","In&#233;s","Katya","Lucia","Maria","Natalia","Olga","Pamela","Valentina");
					lastNames = Arrays.asList("Acosta","Blanco","Cruz","D&#237;az","Escobar","Flores","G&#243;mez","Hern&#225;ndez", "Jim&#233;nez","L&#243;pez","Mart&#237;nez","Navarrete","Ortiz", "P&#233;rez","Quiroga","Reyes", "Torres","Vargas","Y&#225;&#241;ez","Z&#250;&#241;iga");
				} else if(locale.getLanguage().equals(new Locale("fr").getLanguage())){
					maleFirstNames = Arrays.asList("Bernard","Camille","Gabriel","Hubert","Jean","Olivier","Quentin","Robert","Sebastien","Thomas","Ulysse","Valentin","Xavier","Yannick");
					femaleFirstNames = Arrays.asList("Angelique","Camille","Diane","Elise","Françoise","Ines","Lucie","Marie","Natalie","Pauline");
					lastNames = Arrays.asList("Andre","Bernard","Chevalier","Dupont","Fournier","Girard","Henry", "Lefebre","Martin","Nicolas","Petit","Roux","Simon","Thomas","Vincent","Wagner");
				} else if(locale.getLanguage().equals(new Locale("de").getLanguage())){
					maleFirstNames = Arrays.asList("Ben","Jonas","Leon","Elias","Finn","Paul","Luis","Lukas","Felix","Maximilian","Moritz");
					femaleFirstNames = Arrays.asList("Mia","Emma","Hannah","Sofia","Emilia", "Lina","Marie","Lena","Mila","Lea","Leonie","Johanna");
					lastNames = Arrays.asList("Müller","Schmidt","Schneider","Fischer","Weber","Meyer","Henry", "Wagner","Becker","Nicolas","Schulz","Hoffmann","Schäfer","Koch","Bauer","Richter");
				} else {
					maleFirstNames = Arrays.asList("Carmelo","Greg","Herb","Jamison","Quentin","Roberto","Tony","Ulrich","Winford","Xavier","Yann");
					femaleFirstNames = Arrays.asList("Abbie","Delana","Emilie","Felicia","Ida","Kathlene","Luci","Meredith","Natacha","Oprah","Paris","Valerie");
					lastNames = Arrays.asList("Arguelles","Bal","Costantino","Deans","Ehret","Farlow","Giltner","Hanning","Ikemoto","Jefferson","Kelley","Lasiter","Marsh","Nester","Ozborne","Peltier","Quinnan","Rubino","Samms","Trentham","Ubermann","Visser","Watts","Xiao","Yasutomi","Zona");
				}
				
				String suffix = "@example.com";
				if(locale.getLanguage().equals(new Locale("es").getLanguage())){
					suffix = "@ejemplo.com";
				} else if(locale.getLanguage().equals(new Locale("fr").getLanguage())){
					suffix = "@exemple.fr";
				} else if(locale.getLanguage().equals(new Locale("de").getLanguage())){
					suffix = "@beispiel.de";
				}
				// Let's create the client list
				for(int i = 0; i<numberOfClients; i++){
					String firstName = null;
					String gender; 
					//TODO add pics
					if(i%2 == 0){
						firstName = maleFirstNames.get((int)(Math.random()*maleFirstNames.size()));
						gender = "male"; 
					} else {
						femaleFirstNames.get((int)(Math.random()*femaleFirstNames.size()));
						gender = "female"; 
					}
					String lastName = lastNames.get((int)(Math.random()*lastNames.size()));
					String number = "+1"; 
					for(int j=0;j<10;j++){
						number = number.concat(String.valueOf((int)(Math.random()*10)));
					}
					if(locale.getLanguage().equals(new Locale("es").getLanguage())){
						number = "+521"; 
						for(int j=0;j<10;j++){
							number = number.concat(String.valueOf((int)(Math.random()*10)));
						}
					} else if(locale.getLanguage().equals(new Locale("fr").getLanguage())){
						number = "+33"; 
						for(int j=0;j<9;j++){
							number = number.concat(String.valueOf((int)(Math.random()*10)));
						}
					}
	
					Client c = new Client(firstName, lastName, firstName.toLowerCase()+"."+lastName.toLowerCase()+suffix);
					c.setGender(gender);
					c.setPhone(number);
					clients.add(c);
				}
			} else {
				// Get the existing client list
				ClientsManager cm = new ClientsManager(apiKey, token);
				clients = cm.getClients("");
			}
			
			/* Create the appointments */
			Boolean cont = Boolean.TRUE; 
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(startDate));
			int clientsCount=0;
			
			while (cont){ // This loop creates all the appointments for 1 day and increments the date
				int appointmentsCreatedForTheDay = 0;
				boolean slotsAvailableToday = false; // = true if there are any slot available today. If not, we can't add appointments
				for(Service s: services){
					List<Time> freeSlots = appointmentManager.getFreeSlots(company.getId(), sdf.format(c.getTime()), sdf.format(c.getTime()), s.getId(), null);
					if(!freeSlots.isEmpty()){
						slotsAvailableToday=true; 
					}
				}
				while (appointmentsCreatedForTheDay<numberOfAppointmentPerDay && slotsAvailableToday){//TODO Cas à gérer: pas de services assignés à des staffs
					// Let's check if there are free slots this day.
					// Let's take a random service
					Service s = services.get((int)(Math.random()*services.size()));
					// And a random staff for this service
					if(s.getStaff() != null){
						Staff st = s.getStaff().get((int)(Math.random()*s.getStaff().size()));
						// Let's find the free slots for this config
						List<Time> freeSlots = appointmentManager.getFreeSlots(company.getId(), sdf.format(c.getTime()), sdf.format(c.getTime()), s.getId(), st.getId());
						// And create an appointment in a random free slot.
						if(!freeSlots.isEmpty()){
							Time slot = freeSlots.get((int)(Math.random()*freeSlots.size()));
							Appointment app = new Appointment(company, s, null, st, clients.get(clientsCount), slot, null, null);
							if(++clientsCount>=clients.size()){
								clientsCount= 0;
							}
							Appointment createdAppointment = appointmentManager.create(app);
							result.add(createdAppointment);
							appointmentsCreatedForTheDay++;
						}
					}
				}
				c.add(Calendar.DATE, 1);
				if(!c.getTime().before(endDate)){
					cont=false;
				}
			}
		} finally {
			/* Re-activation of notifications. */
			settingsManager.update(company.getId(), originalNotifSettings);
		}
		return result;
	}
	
	public List<Appointment> fillAppointments_resources(Company company, String startDate, int numberOfWeeks, 
			int numberOfAppointmentPerDay, boolean createClients, Locale locale) throws JSONException, ParseException, AgendizeException, IOException{

		AppointmentsManager appointmentManager = null;
		ResourcesManager resourcesManager = null;
		SettingsManager settingsManager = null;
		
		appointmentManager = new AppointmentsManager(apiKey, token);
		resourcesManager = new ResourcesManager(apiKey, token); 
		settingsManager = new SettingsManager(apiKey, token); 
		
		/* Lest's save the notification settings, we're going to deactivate them while we create the appointments, to prevent spamming */
		List<Setting> originalNotifSettings =  settingsManager.getNotificationSettings(company.getId());
		/* Deactivation of notifications */
		settingsManager.deactivateNotifications(company.getId());
		
		List<Appointment> result = new ArrayList<Appointment>();
		
		try{
		
			//Calcul de la date de fin
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(startDate));
			cal.add(Calendar.DATE, numberOfWeeks*7);
			Date endDate = cal.getTime();
			
			// First get the service list
			List<Resource> resources = resourcesManager.getResources(company.getId());
	
			int numberOfClients = 30;
			
			// Second get (or generate) Client list
			List<Client> clients = new ArrayList<Client>();
			if(createClients){
				List<String> firstNames;
				List<String> lastNames;
				if(locale.getLanguage().equals(new Locale("es").getLanguage())){
					firstNames = Arrays.asList("Angelica","Bernardo","Camilo","Diana","Elisa","Fernanda","Gabriel","H&#233;ctor","In&#233;s", "Jorge","Katya","Lucia","Maria","Natalia","Olga","Pamela","Quentin", "Roberto","Sebasti&#225;n","Tiago","Ulises","Valentina","Walter","Xavier","Yannick");
					lastNames = Arrays.asList("Acosta","Blanco","Cruz","D&#237;az","Escobar","Flores","G&#243;mez","Hern&#225;ndez", "Jim&#233;nez","L&#243;pez","Mart&#237;nez","Navarrete","Ortiz", "P&#233;rez","Quiroga","Reyes", "Torres","Vargas","Y&#225;&#241;ez","Z&#250;&#241;iga");
				} else if(locale.getLanguage().equals(new Locale("fr").getLanguage())){
					firstNames = Arrays.asList("Angelique","Bernard","Camille","Diane","Elise","Françoise","Gabriel","Hubert","Ines", "Jean", "Lucie","Marie","Natalie","Olivier","Pauline","Quentin", "Robert","Sebastien","Thomas","Ulysse","Valentin","Xavier","Yannick");
					lastNames = Arrays.asList("Andre","Bernard","Chevalier","Dupont","Fournier","Girard","Henry", "Lefebre","Martin","Nicolas","Petit","Roux","Simon","Thomas","Vincent","Wagner");
				} else {
					firstNames = Arrays.asList("Abbie","Bev","Carmelo","Delana","Emilie","Felicia","Greg","Herb","Ida","Jamison","Kathlene","Luci","Meredith","Natacha","Oprah","Paris","Quentin","Roberto","Sam","Tony","Ulrich","Valerie","Winford","Xavier","Yann","Zoraida");
					lastNames = Arrays.asList("Arguelles","Bal","Costantino","Deans","Ehret","Farlow","Giltner","Hanning","Ikemoto","Jefferson","Kelley","Lasiter","Marsh","Nester","Ozborne","Peltier","Quinnan","Rubino","Samms","Trentham","Ubermann","Visser","Watts","Xiao","Yasutomi","Zona");
				}
				// Let's create the client list
				for(int i = 0; i<numberOfClients; i++){
					String firstName = firstNames.get((int)(Math.random()*firstNames.size()));
					String lastName = lastNames.get((int)(Math.random()*lastNames.size()));
					clients.add(new Client(firstName, lastName, firstName.toLowerCase()+"."+lastName.toLowerCase()+"@acme.com"));
				}
			} else {
				// Get the existing client list
				ClientsManager cm = new ClientsManager(apiKey, token);
				clients = cm.getClients("");
			}
			
			/* Create the appointments */
			Boolean cont = Boolean.TRUE; 
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(startDate));
			int clientsCount=0;
			
			while (cont){ // This loop creates all the appointments for 1 day and increments the date
				int appointmentsCreatedForTheDay = 0;
				boolean slotsAvailableToday = false; // = true if there are any slot available today. If not, we can't add appointments
				for(Resource r: resources){
					List<Time> freeSlots = appointmentManager.getFreeSlots_resourceMode(company.getId(), sdf.format(c.getTime()), sdf.format(c.getTime()), r.getId(), null);
					if(!freeSlots.isEmpty()){
						slotsAvailableToday=true; 
					}
				}
				while (appointmentsCreatedForTheDay<numberOfAppointmentPerDay && slotsAvailableToday){
					// Let's check if there are free slots this day.
					// Let's take a random resource
					Resource r = resources.get((int)(Math.random()*resources.size()));
					// Let's find the free slots for this resource
					List<Time> freeSlots = appointmentManager.getFreeSlots_resourceMode(company.getId(), sdf.format(c.getTime()), sdf.format(c.getTime()), r.getId(), null);
					// And create an appointment in a random free slot.
					if(!freeSlots.isEmpty()){
						Time slot = freeSlots.get((int)(Math.random()*freeSlots.size()));
						Appointment app = new Appointment(company, null, r, null, clients.get(clientsCount), slot, null, null);
						if(++clientsCount>=clients.size()){
							clientsCount= 0;
						}
						Appointment createdAppointment = appointmentManager.create(app);
						result.add(createdAppointment);
						appointmentsCreatedForTheDay++;
					}
				}
				c.add(Calendar.DATE, 1);
				if(!c.getTime().before(endDate)){
					cont=false;
				}
			}
		} finally {
			/* Re-activation of notifications. */
			settingsManager.update(company.getId(), originalNotifSettings);
		}
		return result;
	}
	
	/**
	 * Updates a company
	 * @param company Company to update. Must have an id.
	 * @return udpated company.
	 * @throws AgendizeException
	 */
	public Company update(Company company) throws AgendizeException {
		logger.info("Update company with id = " + company.getId() + ".");
		if(company.getId()==null){
			throw new AgendizeException("The company object must have an id to be updated.");
		}
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		String urlString = SCHEDULING_API_COMPANIES_URL + "/" + company.getId();
		String json = AgendizeSchedulingObjectHelper.companyToJSONObject(company).toString();
		//System.out.println(json);
		String companyString = APIUtils.putRequest(urlString, json, properties);
		JSONObject companyJson =  new JSONObject(companyString);
		return AgendizeSchedulingObjectHelper.jsonObjectToCompany(companyJson);
	}

	/**
	 * Deletes a company. /!\ Will delete all the staff, services, resources, appointments of the company.
	 * @param companyId identifier of the company to delete.
	 * @throws AgendizeException 
	 */
	public void delete(int companyId) throws AgendizeException {
		logger.info("Delete company with id = " + companyId + ".");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		APIUtils.deleteRequest(SCHEDULING_API_COMPANIES_URL+"/"+companyId, properties);
	}

	public List<String> getCompanyIds()  {
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
        String companiesString = null;
		try {
			companiesString = APIUtils.getRequest(SCHEDULING_API_COMPANIES_URL, properties);
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<String> result = null;
		if(companiesString != null && !"".equals(companiesString)){
			JSONObject jsonObject = new JSONObject(companiesString);
			result = new ArrayList<String>();
			for(int j= 0; j<jsonObject.getJSONArray("items").length(); j++){
				JSONObject company = (JSONObject) jsonObject.getJSONArray("items").get(j);
	    		result.add(company.getString("id"));
	    	}
		}
    	return result;
	}
}
