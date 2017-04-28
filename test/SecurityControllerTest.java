import org.junit.Test;
import utils.StartupData;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;


public class SecurityControllerTest {

    @Test
    public void login() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                StartupData.load();
//                UserDto dto = Translate.to(UserDto.class).from(User.find.byId(1l));
//                User user = Translate.to(User.class).from(dto);
            }
        });
    }
//
//    @Test
//    public void login() {
//        running(fakeApplication(inMemoryDatabase()), new Runnable() {
//            public void run() {
//                DemoData.loadDemoData();
//
//                ObjectNode loginJson = Json.newObject();
//                loginJson.put("emailAddress", DemoData.user1.getEmailAddress());
//                loginJson.put("password", DemoData.user1.getPassword());
//
//                Result result = callAction(routes.ref.SecurityCtrl.login(), fakeRequest().withJsonBody(loginJson));
//
//                assertThat(status(result)).isEqualTo(OK);
//
//                JsonNode json = Json.parse(contentAsString(result));
//                assertThat(json.get("authToken")).isNotNull();
//            }
//        });
//    }
//
//    @Test
//    public void loginWithBadPassword() {
//        running(fakeApplication(inMemoryDatabase()), new Runnable() {
//            public void run() {
//                DemoData.loadDemoData();
//
//                ObjectNode loginJson = Json.newObject();
//                loginJson.put("emailAddress", DemoData.user1.getEmailAddress());
//                loginJson.put("password", DemoData.user1.getPassword().substring(1));
//
//                Result result = callAction(routes.ref.SecurityCtrl.login(), fakeRequest().withJsonBody(loginJson));
//
//                assertThat(status(result)).isEqualTo(UNAUTHORIZED);
//            }
//        });
//    }
//
//    @Test
//    public void loginWithBadUsername() {
//        running(fakeApplication(inMemoryDatabase()), new Runnable() {
//            public void run() {
//                DemoData.loadDemoData();
//
//                ObjectNode loginJson = Json.newObject();
//                loginJson.put("emailAddress", DemoData.user1.getEmailAddress().substring(1));
//                loginJson.put("password", DemoData.user1.getPassword());
//
//                Result result = callAction(routes.ref.SecurityCtrl.login(), fakeRequest().withJsonBody(loginJson));
//
//                assertThat(status(result)).isEqualTo(UNAUTHORIZED);
//            }
//        });
//    }
//
//    @Test
//    public void loginWithDifferentCaseUsername() {
//        running(fakeApplication(inMemoryDatabase()), new Runnable() {
//            public void run() {
//                DemoData.loadDemoData();
//
//                ObjectNode loginJson = Json.newObject();
//                loginJson.put("emailAddress", DemoData.user1.getEmailAddress().toUpperCase());
//                loginJson.put("password", DemoData.user1.getPassword());
//
//                Result result = callAction(routes.ref.SecurityCtrl.login(), fakeRequest().withJsonBody(loginJson));
//
//                assertThat(status(result)).isEqualTo(OK);
//            }
//        });
//    }
//
//    @Test
//    public void loginWithNullPassword() {
//        running(fakeApplication(inMemoryDatabase()), new Runnable() {
//            public void run() {
//                DemoData.loadDemoData();
//
//                ObjectNode loginJson = Json.newObject();
//                loginJson.put("emailAddress", DemoData.user1.getEmailAddress());
//
//                Result result = callAction(routes.ref.SecurityCtrl.login(), fakeRequest().withJsonBody(loginJson));
//
//                assertThat(status(result)).isEqualTo(BAD_REQUEST);
//            }
//        });
//    }
//
//    @Test
//    public void logout() {
//        running(fakeApplication(inMemoryDatabase()), new Runnable() {
//            public void run() {
//                DemoData.loadDemoData();
//
//                String authToken = DemoData.user1.createToken();
//
//                Result result = callAction(routes.ref.SecurityCtrl.logout(), fakeRequest().withHeader(SecurityCtrl.AUTH_TOKEN_HEADER, authToken));
//
//                assertThat(status(result)).isEqualTo(SEE_OTHER);
//            }
//        });
//    }

}
