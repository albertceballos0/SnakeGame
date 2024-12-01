package tests;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

// Include all test classes
@Suite
@SelectClasses({
    SnakeBoardTest.class,
    SnakeFoodTest.class,
    SnakeGameTest.class,
    SnakeObstacleTest.class,
    SnakePlayerTest.class
})
public class AllTests {
    // No methods are needed here; the annotations handle everything.
}