package Model;

public class SituationJeu {
	private int Code;
	private String Exposition;
	private String Question;
	
	private Reponse[] Reponses;

	public String getDescription() {
		return Exposition;
	}

	public String getQuestion() {
		return Question;
	}

	public Reponse[] getReponses() {
		return Reponses;
	}
}
