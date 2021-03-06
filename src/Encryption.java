
public class Encryption {
	private String text;
	private String key;



	public Encryption(String text, String key) {
		this.text = text;
		this.key = key;
	}


	public String encrypt() {
		String res = "";
		text = text.toUpperCase();
		for (int i = 0, j = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (c < 'A' || c > 'Z') continue;
			res += (char)((c + key.charAt(j) - 2 * 'A') % 26 + 'A');
			j = ++j % key.length();
		}
		return res;
	}

}
