package mx.tec.lab.repository;

import mx.tec.lab.entity.Dragon;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test
;import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class DragonRepositoryTest {
	@Resource
	private DragonRepository dragonRepository;
	
	@Test
	public void givenDragon_whenSave_thenRetrieveSame() {
		Dragon dragon = new Dragon(1, "Meraxes");
		dragonRepository.save(dragon);
		
		Dragon retrievedDragon = dragonRepository.findById(1L).orElse(null);
		assertEquals("Meraxes", retrievedDragon.getName());
	}
	
	@Test
	public void save_and_update() {
		Dragon dragon = new Dragon(2, "Vhagar");
		dragonRepository.save(dragon);
		
		dragon.setName("Balerion");
		dragonRepository.save(dragon);
		
		Dragon updatedDragon = dragonRepository.findById(2L).orElse(null);
		assertNotEquals("Vhagar", updatedDragon.getName());
		
	}
	
	@Test
	public void delete_dragon() {
		Dragon dragon = new Dragon(3, "Vhagar");
		dragonRepository.save(dragon);
		
		dragonRepository.delete(dragon);
		
		Dragon deletedDragon = dragonRepository.findById(3L).orElse(null);
		
		assertThat(deletedDragon).isNull();
	}
	
}
