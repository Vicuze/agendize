package mx.agendize.api.v2.account.reference;

import mx.agendize.api.v2.reference.AccountPreferences;

public class ProfileSettings {

	private AccountPreferences preferences;

	public AccountPreferences getPreferences() {
		return preferences;
	}

	public void setPreferences(AccountPreferences preferences) {
		this.preferences = preferences;
	}
}
