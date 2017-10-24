
public class KeyRing {
	private int i1, i2, i3, i4, i5, i6, h1, h2, h3;
	//private char[] alphabet;
	private String currentkey;

	public KeyRing() {
		this.h1 = 1;
		this.h2 = 11;
		this.h3 = 20;
		this.i2 = 1;
		this.i3 = 1;
		this.i4 = 1;
		this.i5 = 1;
		this.i6 = 1;
		//this.alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	}

	public String getKey(String name){
		int curr;
		if(name.contentEquals("h1")) {
			curr = h1;
			if(i2+i3+i4+i5+i6 == 45) h1++;
			if(h1 == 11) return "End";
		}
		else if(name.contentEquals("h2")) {
			curr = h2;
			if(i2+i3+i4+i5+i6 == 45) h2++;
			if(h2 == 20) return "End";
		}
		else if(name.contentEquals("h3")) {
			curr = h3;
			if(i2+i3+i4+i5+i6 == 45) h3++;
			if(h3 == 27) return "End";
		}
		else {
			System.out.println("KeyRing Failure-Not Recognising Helper");
			return null;
		}
		currentkey = getCharForNumber(curr) + getCharForNumber(i2) + getCharForNumber(i3) +
				getCharForNumber(i4) + getCharForNumber(i5) + getCharForNumber(i6);
		if(incrementKey() == 1) return "Done";//! to helpers
		return currentkey;
	}

	public int incrementKey(){
		if(currentkey == null) { 
			System.out.println("Increment key == null first check");
			return 1; 
		}

		i6++;
		if(i6>26){
			i6 = 1;
			i5++;
			if(i5>26){
				i5 = 1;
				i4++;
				if(i4>26){
					i4 = 1;
					i3++;
					if(i3>26){
						i3 = 1;
						i2++;
						if(i2>26){
							i2 = 1;
							i1++;
							if(i1>26){
								currentkey = null;
								return 1; //When last key is met

							}
						}

					}
				}
			}
		}
		return 0;

	}

	private String getCharForNumber(int i) {
		return i > 0 && i < 27 ? String.valueOf((char)(i + 'A' - 1)) : null;
	}

}
