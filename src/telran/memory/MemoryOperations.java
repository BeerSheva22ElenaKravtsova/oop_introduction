package telran.memory;

public class MemoryOperations {

	public static int getMaxAvailableMemory() {
		int res = Integer.MAX_VALUE;
		int left = 0;
		int right = res;
		int middle = right / 2;
		byte ar[] = null;
		while (left <= right) {
			try {
				ar = new byte[middle*1024*1024];
				ar = null;
				left = middle + 1;
			} catch (Throwable e) {
				right = middle - 1;
			}
			middle = left + (right - left) / 2;
		}
		return right;
	}
}
