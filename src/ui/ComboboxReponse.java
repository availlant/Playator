package ui;

public class ComboboxReponse
{
    private String Libelle;
    private int Code;

    public String toString()
    {
        return getText();
    }

	public int getValue() {
		return Code;
	}

	public void setValue(int value) {
		Code = value;
	}

	public String getText() {
		return Libelle;
	}

	public void setText(String text) {
		Libelle = text;
	}
}