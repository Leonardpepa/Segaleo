package contact;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import resources.TextResources;

/* This class is used to read the data from the file 
 * that contains the frequent questions and answers
 * about the services*/
public class FAQReader {

	private ArrayList<FAQ> faqsList;

	// Initializing the list
	public FAQReader() {
		faqsList = new ArrayList<>();
		readFAQ();
	}

	private void readFAQ() {

		String line;
		String question;
		String answer;

		// File format: the # is used to separate the question from the answer
		String path = "./files/faqs/Q&A";
		String lang = TextResources.endpointPath;
		File qnaFile = new File(path + lang);
		try {
			FileReader reader = new FileReader(qnaFile);
			BufferedReader inputReader = new BufferedReader(reader);

			/*
			 * The reader reads each line, separates the question from the answer, adds it
			 * to the list
			 */
			line = inputReader.readLine();
			while (line != null) {
				// replacing the \n from the file with a line separator
				question = line.split("#")[0].replace("\\n", System.lineSeparator());
				answer = line.split("#")[1].replace("\\n", System.lineSeparator());
				faqsList.add(new FAQ(question, answer));
				line = inputReader.readLine();
			}
			inputReader.close();
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<FAQ> getFaqsList() {
		return faqsList;
	}

	public void setFaqsList(ArrayList<FAQ> faqsList) {
		this.faqsList = faqsList;
	}

}
