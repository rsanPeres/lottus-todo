import com.lottus.todo.core.controller.TodoController;
import com.lottus.todo.core.controller.request.TodoRequest;
import com.lottus.todo.core.domain.TodoEntity;
import com.lottus.todo.core.dto.TodoDto;
import com.lottus.todo.core.enums.PriorityEnum;
import com.lottus.todo.core.enums.StatusEnum;
import com.lottus.todo.core.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootTest(classes= TodoController.class)
@SpringJUnitConfig
public class TodoControllerTest {
    @MockBean
    private TodoService todoService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    public TodoControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUpdateTodo() throws Exception {
        UUID id = UUID.randomUUID();

        TodoRequest request = new TodoRequest(id, "titleTest",
                LocalDate.of(2024, 5, 10),
                LocalDate.of(2024, 5, 10),
                PriorityEnum.URGENTE, StatusEnum.EM_ANDAMENTO, true);

        TodoDto dto = new TodoDto(id, "titleTest",
                LocalDate.of(2024, 5, 10),
                LocalDate.of(2024, 5, 10),
                PriorityEnum.URGENTE, StatusEnum.FINALIZADO, false);

        when(todoService.update(request.toDto())).thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders.patch("/api/v1/todo", id)
                        .contentType("application/json")
                        .content("""
                    {
                      "id": "%s",
                      "title": "titleTest",
                      "dueDate": "10/05/2024",
                      "createdDate": "10/05/2024",
                      "priority": "URGENTE",
                      "status": "EM_ANDAMENTO",
                      "completed": true
                    }
                    """.formatted(id.toString()))
                        .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(dto.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priority").value(dto.getPriority().name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(dto.getStatus().name()));
    }

    @Test
    public void testGetTodoById() throws Exception {
        UUID id = UUID.randomUUID();
        TodoDto dto = new TodoDto(id, "titleTest",
                LocalDate.of(2024, 5, 10),
                LocalDate.of(2024, 5, 10),
                PriorityEnum.URGENTE, StatusEnum.FINALIZADO, false);
        when(todoService.getById(id)).thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/todo/{id}", id)
                .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(dto.getId().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(dto.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dueDate").value(dto.getDueDate().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.completed").value(dto.getStatus()));
    }

    @Test
    public void testGetAllTodo(){

    }

    @Test
    public void testDeleteTodo() throws Exception {
        UUID id = UUID.randomUUID();
        doNothing().when(todoService).delete(id);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/todo/{id}", id)
                        .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
