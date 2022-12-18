package telran.memory;

public class MemoryOperations {

	/**
	 * пытаться выделить памяти на определенный размер если не удается - я от
	 * размера отнимаю 1 сначала max int больше нельзя тк оператор new принимает
	 * только int
	 * 
	 * @return
	 */
	public static int getMaxAvailableMemory() {
		int res = Integer.MAX_VALUE;
		boolean running = true;// это флаг
		byte ar[] = null;
		while (running) {
			try {
				ar = new byte[res];
				running = false;
				
			// только если удачное выделение памяти
			// здесь идем вправо
			} catch (Throwable e) {
				res /= 2;
				
				int left = 0;
				int right = Integer.MAX_VALUE;
				int middle = right / 2;
				while (left <= right && ar[middle] != res) {
					if (res < ar[middle]) {
						right = middle - 1;
					} else {
						left = middle + 1;
					}
					middle = (left + right) / 2;
				}
				return left > right ? -1 : middle;
				
				
				//память от 0 до мах значения
				// разделить пополам
				//если мах не проходит - идем влево уменьшаем
				//если удачное выделение памяти - надо уйти справо
				//это бинарный алгоритм
				// написать этот метод с помощью бинарного алгоритма
				//здесь идем влево пока лево не будет больше права
			}
		}
		return res;	
	}
	
	
	public static int binarySearch(byte[] ar, int res) {
		ar = new byte[res];
		int left = 0;
		int right = Integer.MAX_VALUE;
		int middle = right / 2;
		while (left <= right && ar[middle] != res) {
			if (res < ar[middle]) {
				right = middle - 1;
			} else {
				left = middle + 1;
			}
			middle = (left + right) / 2;
		}
		return left > right ? -1 : middle;
	}
}


//2 задание изменить canvas чтобы прошли тесты
