package com.personalproject.personalproject_1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.personalproject.personalproject_1.config.WebSecurityConfig;
import com.personalproject.personalproject_1.dto.TodoRequestDto;
import com.personalproject.personalproject_1.dto.TodoResponseDto;
import com.personalproject.personalproject_1.entity.Todo;
import com.personalproject.personalproject_1.entity.User;
import com.personalproject.personalproject_1.exception.TodoNotFoundException;
import com.personalproject.personalproject_1.impl.UserDetailsImpl;
import com.personalproject.personalproject_1.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        value = TodoController.class,
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ASSIGNABLE_TYPE,
                        classes = WebSecurityConfig.class
                )
        }
)
public class TodoControllerTest {
    private MockMvc mvc;

    private Principal mockPrincipal;
    private User testUser;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TodoService todoService;
    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity(new MockSpringSecurityFilter()))
                .alwaysDo(print())
                .build();
    }

    private void mockUserSetup() {
        // Mock 테스트 유져 생성
        String username = "jong1234";
        String password = "gyu1234";
        User testUser = new User(username, password);
        UserDetailsImpl testUserDetails = new UserDetailsImpl(testUser);
        mockPrincipal = new UsernamePasswordAuthenticationToken(testUserDetails, "", testUserDetails.getAuthorities());
    }

    @Test
    @DisplayName("Todo 생성")
    void createTodo() throws Exception {
        // given
        this.mockUserSetup();
        TodoRequestDto todoRequestDto = new TodoRequestDto();
        todoRequestDto.setTitle("test title");
        todoRequestDto.setContent("test content");
        String body = objectMapper.writeValueAsString(todoRequestDto);

        // when
        ResultActions actions  = mvc.perform(post("/api/todo")
                        .content(body).characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .principal(mockPrincipal));
        // then
        actions.andExpect(status().isCreated());
    }


    @DisplayName("Todo 조회 성공")
    @Test
    void getTodoSuccess() throws Exception {
        // given
        this.mockUserSetup();
        Long testId = 1L;
        given(todoService.getTodo(eq(testId))).willReturn(new TodoResponseDto());
        // when &t hen
        mvc.perform(get("/api/todo/{todoId}", testId)
                        .accept(MediaType.APPLICATION_JSON)
                        .principal(mockPrincipal))
                .andExpect(status().isOk());
    }
    @DisplayName("Todo 조회 실패 - 존재 하지 않는 todo id")
    @Test
    void getTodoFail() throws Exception {
        // given
        this.mockUserSetup();
        Long testId = 1L;
        given(todoService.getTodo(eq(testId))).willThrow(new TodoNotFoundException("해당 To do를 찾을수 없습니다."));
        // when &t hen
        mvc.perform(get("/api/todo/{todoId}",testId)
                        .accept(MediaType.APPLICATION_JSON)
                        .principal(mockPrincipal))
                .andExpect(jsonPath("$.errorMessage").value("해당 To do를 찾을수 없습니다."))
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(status().isNotFound());
    }

    @DisplayName("Todo 전체 조회")
    @Test
    void getTodoList() throws Exception {
        // given
        this.mockUserSetup();
        String userName1 = "Jong";
        String userName2 = "Gyu";
        Map<String,List<TodoResponseDto>> todoList = new HashMap<>();
        todoList.put(userName1, new ArrayList<>());
        todoList.put(userName2, new ArrayList<>());
        given(todoService.getTotalTodoList()).willReturn(todoList);

        // when & then
        mvc.perform(get("/api/todo/total")
                        .accept(MediaType.APPLICATION_JSON)
                        .principal(mockPrincipal))
                .andExpect(status().isOk());
    }

    @DisplayName("Todo 수정")
    @Test
    void updateTodo() throws Exception {
        // given
        this.mockUserSetup();
        TodoRequestDto todoRequestDto = new TodoRequestDto();
        Long todoId = 1L;
        todoRequestDto.setContent("수정합니다.");
        todoRequestDto.setTitle("수정");
        String body = objectMapper.writeValueAsString(todoRequestDto);
        given(todoService.updateTodo(eq(todoId),eq(todoRequestDto),any(User.class))).willReturn(new TodoResponseDto());

        // when & then
        mvc.perform(patch("/api/todo/{todoId}",todoId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body).characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON)
                        .principal(mockPrincipal))
                .andExpect(status().isOk());
    }
}
