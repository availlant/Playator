package soap.client.notused;

import java.util.HashMap;
import javax.swing.SwingWorker;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import callback.ICallback;

public abstract class SoapClientBase {
	public abstract SOAPMessage call(String method, HashMap<String, String> parameters) throws SOAPException;

	public void callAsync(final String method, final HashMap<String, String> parameters, final ICallback<SOAPMessage> callback) {
		SwingWorker<Void, Void> sw = new SwingWorker<Void, Void>() {

			private SOAPMessage result;

			@Override
			protected Void doInBackground() {
				try {
					result = call(method, parameters);
				} catch (SOAPException e) {
					
				}
				
				return null;
			}
			
			@Override
			protected void done() {	
				callback.OnFinish(result);
			}
		};
		
		sw.execute();
	}
}
