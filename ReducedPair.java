package mapReduce;

public class ReducedPair {
	ReducedKey key;
	int value;
	
	public ReducedPair(ReducedKey key, int value) {
		this.key = key;
		this.value = value;
	}
	
	public int getFirstKey() {
		return key.getFirst();
	}
	
	public int getSecondKey() {
		return key.getSecond();
	}
	
	public int getValue() {
		return value;
	}
}
