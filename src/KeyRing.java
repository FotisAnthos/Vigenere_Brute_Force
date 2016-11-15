
public class KeyRing {
	private int i1, i2, i3, i4, i5, i6;
	private char[] alphabet;
	private String currentkey;

	public KeyRing() {
		this.i1 = 1;
		this.i2 = 1;
		this.i3 = 1;
		this.i4 = 1;
		this.i5 = 1;
		this.i6 = 1;
		this.alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	}

	public String getKey(){
		currentkey = getCharForNumber(i1) + getCharForNumber(i2) + getCharForNumber(i3) +
				getCharForNumber(i4) + getCharForNumber(i5) + getCharForNumber(i6);
		if(incrementKey() == 1) return "Done";
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
