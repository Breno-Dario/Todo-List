package com.dariopontes.todo_list;

import com.dariopontes.todo_list.entity.Todo;
import org.junit.jupiter.api.Test;
import org.mockito.exceptions.misusing.InvalidUseOfMatchersException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoListApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testeCreateTodoSeccess() {
      var todo = new Todo("todo 1", "desct todo 1", false, 1);


	  webTestClient
			  .post()
			  .uri("/todos")
			  .bodyValue(todo)
			  .exchange()
			  .expectStatus().isOk()
			  .expectBody()
			  .jsonPath("$").isArray()
			  .jsonPath("$.length()").isEqualTo(1)
			  .jsonPath("$[0].nome").isEqualTo(todo.getNome())
			  .jsonPath("$[0].descricao").isEqualTo(todo.getDescricao())
			  .jsonPath("$[0].realizado").isEqualTo(todo.isRealizado())
			  .jsonPath("$[0].prioridade").isEqualTo(todo.getPrioridade());


	}

	@Test
	void testeCreateTodoFailure() {
		webTestClient
				.post()
				.uri("/todos")
				.bodyValue(new Todo("", "", false, 1))
				.exchange()
				.expectStatus().isBadRequest();
	}


}
