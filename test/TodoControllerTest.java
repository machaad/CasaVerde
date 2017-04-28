import static org.fest.assertions.Assertions.assertThat;

public class TodoControllerTest {
//
//    @Test
//    public void getAllTodosNoAuthToken() {
//        running(fakeApplication(inMemoryDatabase()), new Runnable() {
//            public void run() {
//                DemoData.loadDemoData();
//
//                Result result = callAction(routes.ref.TodoCtrl.getAllTodos());
//                assertThat(status(result)).isEqualTo(UNAUTHORIZED);
//                assertThat(contentAsString(result)).doesNotContain(DemoData.todo1_1.value);
//            }
//        });
//    }
//
//    @Test
//    public void getAllTodosInvalidAuthToken() {
//        running(fakeApplication(inMemoryDatabase()), new Runnable() {
//            public void run() {
//                DemoData.loadDemoData();
//
//                Result result = callAction(routes.ref.TodoCtrl.getAllTodos(), fakeRequest().withHeader(SecurityCtrl.AUTH_TOKEN_HEADER, "wrong"));
//                assertThat(status(result)).isEqualTo(UNAUTHORIZED);
//                assertThat(contentAsString(result)).doesNotContain(DemoData.todo1_1.value);
//            }
//        });
//    }
//
//
//    @Test
//    public void getAllTodosForUser1() {
//        running(fakeApplication(inMemoryDatabase()), new Runnable() {
//            public void run() {
//                DemoData.loadDemoData();
//
//                String authToken = DemoData.user1.createToken();
//
//                Result result = callAction(routes.ref.TodoCtrl.getAllTodos(), fakeRequest().withHeader(SecurityCtrl.AUTH_TOKEN_HEADER, authToken));
//
//                assertThat(status(result)).isEqualTo(OK);
//                assertThat(contentAsString(result)).contains(DemoData.todo1_1.value);
//            }
//        });
//    }
//
//    @Test
//    public void addTodo() {
//        running(fakeApplication(inMemoryDatabase()), new Runnable() {
//            public void run() {
//                DemoData.loadDemoData();
//
//                String authToken = DemoData.user1.createToken();
//
//                ObjectNode todoJson = Json.newObject();
//                todoJson.put("value", "make it work");
//
//                FakeRequest fakeRequest = fakeRequest()
//                        .withHeader(SecurityCtrl.AUTH_TOKEN_HEADER, authToken)
//                        .withJsonBody(todoJson);
//
//                Result result = callAction(routes.ref.TodoCtrl.createTodo(), fakeRequest);
//
//                assertThat(status(result)).isEqualTo(OK);
//
//                Todo todo = Json.fromJson(Json.parse(contentAsString(result)), Todo.class);
//                assertThat(todo.id).isNotNull();
//                assertThat(todo.value).isEqualTo("make it work");
//                assertThat(todo.user).isNull(); // this should not be serialized
//            }
//        });
//    }
//
//    @Test
//    public void addTodoNoValue() {
//        running(fakeApplication(inMemoryDatabase()), new Runnable() {
//            public void run() {
//                DemoData.loadDemoData();
//
//                String authToken = DemoData.user1.createToken();
//
//                FakeRequest fakeRequest = fakeRequest()
//                        .withHeader(SecurityCtrl.AUTH_TOKEN_HEADER, authToken);
//
//                Result result = callAction(routes.ref.TodoCtrl.createTodo(), fakeRequest);
//
//                assertThat(status(result)).isEqualTo(BAD_REQUEST);
//            }
//        });
//    }
//
//    @Test
//    public void addTodoUnauthorized() {
//        running(fakeApplication(inMemoryDatabase()), new Runnable() {
//            public void run() {
//                DemoData.loadDemoData();
//
//                Result result = callAction(routes.ref.TodoCtrl.createTodo(), fakeRequest());
//
//                assertThat(status(result)).isEqualTo(UNAUTHORIZED);
//            }
//        });
//    }

}
