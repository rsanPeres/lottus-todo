import com.lottus.todo.core.domain.TodoEntity;
import com.lottus.todo.core.dto.TodoDto;
import com.lottus.todo.core.enums.PriorityEnum;
import com.lottus.todo.core.enums.StatusEnum;
import com.lottus.todo.core.repository.TodoRepository;
import com.lottus.todo.core.service.TodoService;
import com.lottus.todo.core.service.impl.TodoServiceImpl;
import jakarta.transaction.Transactional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest(classes= TodoServiceImpl.class)
@SpringJUnitConfig
@Rollback
public class TodoServiceImplTest {
    @Autowired
    private TodoService todoService;
    @MockBean
    private TodoRepository todoRepository;

    @Test
    public void testCreateTodo() {
        TodoDto todoDto = new TodoDto(null, "Test Title",
                LocalDate.now(), LocalDate.now(),
                PriorityEnum.URGENTE, StatusEnum.EM_ANDAMENTO, true);

        TodoDto createdTodo = todoService.create(todoDto);

        assertNotNull(createdTodo.getId());
        assertEquals("Test Title", createdTodo.getTitle());
        assertEquals(PriorityEnum.URGENTE, createdTodo.getPriority());
    }

    @Test
    public void testUpdateTodo() {
        TodoEntity entity = new TodoEntity(UUID.randomUUID(), "Old Title",
                LocalDate.now(), LocalDate.now(),
                PriorityEnum.MEDIA, StatusEnum.EM_ANDAMENTO, true);
        TodoEntity savedEntity = todoRepository.save(entity);

        TodoDto updatedTodoDto = new TodoDto(savedEntity.getId(), "Updated Title",
                LocalDate.now(), LocalDate.now(),
                PriorityEnum.URGENTE, StatusEnum.FINALIZADO, false);

        TodoDto updatedTodo = todoService.update(updatedTodoDto);

        assertEquals("Updated Title", updatedTodo.getTitle());
        assertEquals(PriorityEnum.URGENTE, updatedTodo.getPriority());
    }

    @Test
    public void testGetTodoById() {
        TodoEntity entity = new TodoEntity(UUID.randomUUID(), "Get Test",
                LocalDate.now(), LocalDate.now(),
                PriorityEnum.MEDIA, StatusEnum.EM_ANDAMENTO, true);
        TodoEntity savedEntity = todoRepository.save(entity);

        TodoDto foundTodo = todoService.getById(savedEntity.getId());

        assertNotNull(foundTodo);
        assertEquals(savedEntity.getId(), foundTodo.getId());
    }

    @Test
    public void testDeleteTodo() {
        TodoEntity entity = new TodoEntity(UUID.randomUUID(), "Delete Test",
                LocalDate.now(), LocalDate.now(),
                PriorityEnum.MEDIA, StatusEnum.EM_ANDAMENTO, true);
        TodoEntity savedEntity = todoRepository.save(entity);

        todoService.delete(savedEntity.getId());

        assertTrue(todoRepository.findById(savedEntity.getId()).isEmpty());
    }
}
