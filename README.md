[README.md](https://github.com/user-attachments/files/32371829/README.md)
# SauceDemo Selenium Automation (TestNG + Cucumber BDD)

Java + Selenium WebDriver automation for https://www.saucedemo.com/, using the
Page Object Model. The project now includes **two parallel test styles that
share the same Page Objects**:

1. **Plain TestNG tests** — `src/test/java/tests/` (LoginTest, InventoryTest,
   CartTest, CheckoutTest)
2. **Cucumber BDD tests** — Gherkin feature files + step definitions that call
   the exact same Page Object methods

## Prerequisites
- Java 11+
- Maven 3.6+
- Google Chrome installed (WebDriverManager downloads the matching driver automatically)

## Project structure
```
src/main/java/pages/            - Page Object classes (LoginPage, InventoryPage, CartPage,
                                   CheckoutStepOnePage, CheckoutStepTwoPage, CheckoutCompletePage)
src/test/java/tests/            - Plain TestNG test classes (BaseTest + 4 test classes)
src/test/java/stepdefinitions/  - Cucumber step definitions (LoginSteps, CartSteps,
                                   CheckoutSteps) + Hooks (browser setup/teardown) +
                                   TestContext (shared state injected via PicoContainer)
src/test/java/runners/          - CucumberTestRunner (TestNG runner that executes all
                                   .feature files)
src/test/resources/features/    - Gherkin feature files: login.feature, cart.feature,
                                   checkout.feature
src/test/resources/testng.xml   - Suite file wiring together both test styles
```

## How the BDD layer works
Each `.feature` file describes scenarios in plain English (Given/When/Then).
Cucumber matches each step's text to a method in `stepdefinitions/`, which in
turn calls the same Page Object methods used by the plain TestNG tests —
so there's no duplicate automation logic, only two ways of describing the
same test cases. `TestContext` is a shared object that Cucumber injects into
every step definition class for a scenario (via `cucumber-picocontainer`),
so `LoginSteps`, `CartSteps`, and `CheckoutSteps` can all read and update the
same WebDriver and page objects. `Hooks` opens the browser before each
scenario and closes it after.

## Run everything (TestNG tests + Cucumber scenarios)
```
mvn clean test
```

## Run only the Cucumber suite
```
mvn test -Dtest=runners.CucumberTestRunner
```

## Run only the plain TestNG suite
```
mvn test -Dtest=tests.LoginTest,tests.InventoryTest,tests.CartTest,tests.CheckoutTest
```

## View the Cucumber HTML report
After running, open `target/cucumber-reports/cucumber.html` in a browser.

## Run headless (e.g. in CI)
Uncomment the `--headless=new` line in `BaseTest.setUp()` (TestNG tests) and
`Hooks.setUp()` (Cucumber tests).

## CI/CD with Jenkins
A `Jenkinsfile` (declarative pipeline) is included at the project root. It
checks out the repo, builds with Maven, runs the full test suite, and
publishes TestNG + Cucumber HTML reports. Chrome runs headless automatically
when the `JENKINS_URL` environment variable is present (or when `HEADLESS=true`
is set), so no display server is needed on the agent. See the setup steps
below for configuring the Jenkins job itself.

## Notes
- Locators are based on the current SauceDemo DOM (`data-test` attributes and
  element IDs); re-verify if the site markup changes.
- `problem_user` and `error_user` are intentionally not used in the main suite
  since they have baked-in UI bugs; useful for a separate negative-testing class
  if you want to demonstrate handling known-broken behavior.
