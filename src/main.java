public class main {

	public static void main(String[] args) {

		String key = "TURING"; //
		String plaintext = "CONGRATULATIONSYOUSUCEEDINDECRYPTINGTHISMESSAGEITWASNOTTOOHARDAFTERALLKEEPUPTHEGOODWORKANDSPENDMORETIMEWITHCRYPTOOLANDSTUDYCAREFULLYTHEAVAILABLEBOOKSANDDONOTFORGETTHATHEBIGGESTBOOKISINTHEINTERNET";
		Encryption e = new Encryption(plaintext, key);
		String enc = e.encrypt();
		System.out.println("Cyphertext:\n" + enc);
		System.out.println("-------------------\n");
		VigenereBF bf = new VigenereBF(enc, plaintext);
		bf.decode();

	}
}
