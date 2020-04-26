package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.List;

public class DesafioApplication {
	private final static int INITIAL_VALUE = 1;
	private final static int MIN_VALUE = 0;
	private final static int MAX_VALUE = 350;

	public static List<Integer> fibonacci() {
		List<Integer> listFibonacci = new ArrayList<>();
		listFibonacci.add(MIN_VALUE);
		listFibonacci.add(INITIAL_VALUE);
		int currentNumber = INITIAL_VALUE;

		while (currentNumber > MIN_VALUE && currentNumber < MAX_VALUE) {
			int lastPosition = listFibonacci.get(listFibonacci.size() - 1);
			int lastOnePosition = listFibonacci.get(listFibonacci.size() - 2);

			currentNumber = lastPosition + lastOnePosition;

			listFibonacci.add(currentNumber);
		}

		return listFibonacci;
	}

	public static Boolean isFibonacci(int number) {
		return fibonacci().contains(number);
	}
}
