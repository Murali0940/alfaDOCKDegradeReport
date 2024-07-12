package basemethods;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

public class MailWithAttachment extends BaseMethods {

	BaseMethods bm;

	public void sendDegradeReport() throws EmailException {

		System.out.println("Mail sending started ");
		MultiPartEmail email = new MultiPartEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("murali@a-tkg.co.in", "M1u2r3a4l5i6"));
		email.setSSLOnConnect(true);
		email.setFrom("murali@a-tkg.co.in");
		email.setSubject("AlfaDOCK Automation Report");
		email.setMsg("Dear sir,\r\n" + "Please find the attachment below");
		EmailAttachment attachment = new EmailAttachment();
		System.out.println("Attachment done in mail ");
		attachment.setPath("F:\\ScreenShotsAndExtentReport\\ExtentReport\\DegradeTestReport.html");
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		email.addTo("murali@a-tkg.co.in");

		email.attach(attachment);
		email.send();
		System.out.println("Mail sent");
		System.out.println("-----------------------------------------------------");

	}

}
