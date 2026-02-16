/* CS207 Cryptogram Project - Iteration 1 - Group 25 2026 */
package org.group25;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class AppTest {
  @Test
  void addUserTest() {
    App app = new App();
    app.getAppData().addUser("test");
    assertNotNull(app.getAppData().getUsers());
  }
}
