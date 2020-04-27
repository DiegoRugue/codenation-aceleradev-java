package br.com.codenation;

import java.util.*;
import java.util.stream.Collectors;

public class StatisticUtil {

	public static int average(int[] elements) {
		return (int) Arrays.stream(elements).average().getAsDouble();
	}

	public static int mode(int[] elements) {
		List<Median> medianList = new ArrayList<>();

		for (int element : elements) {
			Optional<Median> optionalMedian = medianList.stream()
					.filter(x -> x.getElement() == element)
					.findAny();

			if (optionalMedian.isPresent()) {
				optionalMedian.get().incrementQuantity();
			} else {
				medianList.add(new Median(element, 1));
			}
		}

		return medianList.stream()
				.max(Comparator.comparing(Median::getQuantity))
				.get()
				.getElement();
	}

	public static int median(int[] elements) {
		Arrays.sort(elements);

		boolean evenNumber = elements.length % 2 == 0;
		int middleIndex = (elements.length - 1) / 2;
		if (evenNumber) {
			return (elements[middleIndex] + elements[middleIndex + 1]) / 2;
		}
		return elements[middleIndex];
	}
}
