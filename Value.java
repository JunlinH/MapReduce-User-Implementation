package mapReduce;

public class Value {
    char matrix;
    int ref;
    int matrixValue;
    
    public Value(char matrix, int ref, int matrixValue) {
    	this.matrix = matrix;
    	this.ref = ref;
    	this.matrixValue = matrixValue;
    }
    
    public char getMatrixType() {
    	return matrix;
    }
    
    public int getRef() {
    	return ref;
    }
    
    public int getMatrixValue() {
    	return matrixValue;
    }

}
