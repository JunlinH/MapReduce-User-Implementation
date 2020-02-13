package mapReduce;

public class Pair {
	int key;
	Value value;
	
	public Pair(int key, Value value) {
		this.key = key;
		this.value = value;
	}
	
	public int getKey() {
		return key;
	}
	
	public char getMtx() {
		return value.matrix;
	}
	
	public int getRef() {
		return value.ref;
	}
	
	public int getMtxValue() {
		return value.matrixValue;
	}

}
