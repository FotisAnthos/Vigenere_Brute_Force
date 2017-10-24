
public class Decryption {
	private String res;
	private String text;
	private String key;

	public Decryption(String text) {
		super();
		this.res = "";
		this.text = text.toUpperCase();;
	}

	public String decrypt(String key) {
        String res = "";
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c < 'A' || c > 'Z') continue;
            res += (char)((c - key.charAt(j) + 26) % 26 + 'A');
            j = ++j % key.length();
        }
        return res;
    }

}
