package controller;

import org.junit.jupiter.api.BeforeEach;

public class TacticianTest {
    private Tactician tactician;

    @BeforeEach
    public void setUp(){
        tactician = new Tactician("Player0");
    }
}
