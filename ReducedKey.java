package mapReduce;

public class ReducedKey {
	int first;
	int second;
	
	public ReducedKey(int first, int second) {
		this.first = first;
		this.second = second;
	}
	
	public int getFirst() {
		return first;
	}
	
	public int getSecond() {
		return second;
	}
}
