import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

public class SortUtilsJava {
	public static <T> void bubbleSort(T[] arr, Comparator<? super T> comparator) {

		final int n = arr.length;

		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (comparator.compare(arr[j], arr[j + 1]) > 0) {
					swap(arr, j, j + 1);
				}
			}
		}

	}

	public static <T> void swap(T[] arr, int i, int j) {
		final T temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static void main(String[] args) {
		var random = new Random();
		var items = IntStream.range(0, 10).mapToObj(i -> new Data(UUID.randomUUID(), random.nextInt(0, 100_000_000))).toArray(Data[]::new);
		System.out.println("Before: " + Arrays.toString(items));
		SortUtilsJava.bubbleSort(items, Comparator.comparing(Data::userId));
		System.out.println("Sorted: " + Arrays.toString(items));
	}

	public record Data(UUID id, Integer userId) {
	}
}
