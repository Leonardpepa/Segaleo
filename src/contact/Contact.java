package contact;

import java.util.ArrayList;

public class Contact {

	private SocialMedia social;
	private ArrayList<FAQ> faqs;
	private FAQReader reader;

	// private Message message;


	public Contact() {
		this.reader = new FAQReader();
		this.social = new SocialMedia();
		this.faqs = reader.getFaqsList();
	}

	public SocialMedia getSocial() {
		return social;
	}

	public void setSocial(SocialMedia social) {
		this.social = social;
	}

	public ArrayList<FAQ> getFaqs() {
		return faqs;
	}

	public void setFaqs(ArrayList<FAQ> faqs) {
		this.faqs = faqs;
	}

}
