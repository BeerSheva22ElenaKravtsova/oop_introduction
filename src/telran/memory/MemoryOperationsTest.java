package telran.memory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MemoryOperationsTest {
	byte ar[];

	@Test
	void maxMemoryTest() {
		int maxMemory = MemoryOperations.getMaxAvailableMemory();
		ar = new byte[maxMemory];
		ar = null; // массив у которого нет ссылки, выделенная память становится мусором и гарбедж коллекшион ддолжен сработать
		
		//код обработки Exception
		boolean flException = false;
		try {
			ar = new byte[maxMemory + 1];
		} catch(Throwable e) {
			flException = true;
		}
		assertTrue(flException); // если флаг прошел - значит у нас ошибка тк я попадаю в Exception
	
	 
	}

}
