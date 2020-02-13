package mapReduce;

import java.util.Scanner;
import java.util.ArrayList;

public class mapReduce {
	static int rowsInA;
	static int columnsInA;
	static int columnsInB;
	static int[][] a;
	static int[][] b;
	static ArrayList<Pair> pairArray;
	static ArrayList<ReducedPair> firstReducedArray;
	static ArrayList<ReducedPair> secondReducedArray;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.print("Enter number of rows in A: ");
		rowsInA = s.nextInt();
		System.out.print("Enter number of columns in A / rows in B: ");
		columnsInA = s.nextInt();
		System.out.print("Enter number of columns in B: ");
		columnsInB = s.nextInt();
		a = new int[rowsInA][columnsInA];
		b = new int[columnsInA][columnsInB];
		System.out.println("Enter matrix A");
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				a[i][j] = s.nextInt();
			}
		}
		System.out.println("Enter matrix B");
		for (int j = 0; j < b.length; j++) {
			for (int k = 0; k < b[0].length; k++) {
				b[j][k] = s.nextInt();
			}
		}
		s.close();

		firstMap();
		firstReduce();
		secondMap();
		secondReduce();
	}

	static void firstMap() {
		pairArray = new ArrayList<Pair>();
		for (int i = 0; i < rowsInA; i++) {
			for (int j = 0; j < columnsInA; j++) {
				Value value = new Value('A', i, a[i][j]);
				pairArray.add(new Pair(j, value));
			}
		}

		for (int j = 0; j < columnsInA; j++) {
			for (int k = 0; k < columnsInB; k++) {
				Value value = new Value('B', k, b[j][k]);
				pairArray.add(new Pair(j, value));
			}
		}

		System.out.println("First map results:");

		for (Pair pair : pairArray) {
			System.out.println("(" + pair.getKey() + "," + "(" + pair.getMtx() + "," + pair.getRef() + ","
					+ pair.getMtxValue() + "))");
		}
	}

	static void firstReduce() {
		ArrayList<Pair> arrayA;
		ArrayList<Pair> arrayB;
		firstReducedArray = new ArrayList<ReducedPair>();

		for (int j = 0; j < columnsInA; j++) {
			arrayA = new ArrayList<Pair>();
			arrayB = new ArrayList<Pair>();

			for (Pair pair : pairArray) {
				if (pair.getKey() == j && pair.getMtx() == 'A') {
					arrayA.add(pair);
				}
				if (pair.getKey() == j && pair.getMtx() == 'B') {
					arrayB.add(pair);
				}
			}

			for (Pair pairA : arrayA) {
				for (Pair pairB : arrayB) {
					ReducedKey key = new ReducedKey(pairA.getRef(), pairB.getRef());
					int value = pairA.getMtxValue() * pairB.getMtxValue();
					firstReducedArray.add(new ReducedPair(key, value));
				}
			}

		}
		System.out.println("First reduce results:");

		for (ReducedPair pair : firstReducedArray) {
			System.out
					.println("((" + pair.getFirstKey() + "," + pair.getSecondKey() + ")" + "," + pair.getValue() + ")");
		}
	}

	static void secondMap() {
		System.out.println("Second map results:");

		for (ReducedPair pair : firstReducedArray) {
			System.out
					.println("((" + pair.getFirstKey() + "," + pair.getSecondKey() + ")" + "," + pair.getValue() + ")");
		}
	}

	static void secondReduce() {
		secondReducedArray = new ArrayList<ReducedPair>();
		for (int i = 0; i < rowsInA; i++) {
			for (int k = 0; k < columnsInB; k++) {
				int sumValue = 0;
				for (ReducedPair pair : firstReducedArray) {
					if (pair.getFirstKey() == i && pair.getSecondKey() == k) {
						sumValue = sumValue + pair.getValue();
					}
				}
				ReducedKey key = new ReducedKey(i, k);
				secondReducedArray.add(new ReducedPair(key, sumValue));
			}
		}
		System.out.println("Second reduce results:");
		for (ReducedPair pair : secondReducedArray) {
			System.out
					.println("((" + pair.getFirstKey() + "," + pair.getSecondKey() + ")" + "," + pair.getValue() + ")");
		}
	}
}
