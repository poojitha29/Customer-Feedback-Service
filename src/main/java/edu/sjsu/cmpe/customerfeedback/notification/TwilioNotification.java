package edu.sjsu.cmpe.customerfeedback.notification;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;

public class TwilioNotification {
	
	public static final String ACCOUNT_SID = "ACd5e2e16d1907d227cfd80a940ed1fd20";
	  public static final String AUTH_TOKEN = "b5cfe6eb1f9fd82331f8a4be84db92fd";
	 
	  public void notify (String body, String recipient) throws TwilioRestException {
	    TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
	 
	    List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("Body", body));
	    params.add(new BasicNameValuePair("To", recipient));
	    params.add(new BasicNameValuePair("From", "+16508876684"));         
	     
	    if(!recipient.equals("")) {
			MessageFactory messageFactory = client.getAccount()
					.getMessageFactory();
			Message message = messageFactory.create(params);
			System.out.println(message.getSid());
		}
	  }
}
