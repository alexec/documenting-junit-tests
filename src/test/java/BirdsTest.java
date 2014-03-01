import org.junit.Test;

import static org.junit.Assert.assertEquals;

@Doc(
        title = "Birds API",
        description = "The Birds API is provided so that you can remotely vocalise birds."
)
public class BirdsTest {

    @Test
    @Doc(
            title = "Duck",
            description = "A duck makes a quacking noise."
    )
    public void whenADuckVocalisesItMakesAQuackNoise() throws Exception {
        assertEquals("Quack!", new Duck().vocalise());
    }

    @Test
    @Doc(
            title = "Pigeon",
            description = "A pigeon makes a cooing noise."
    )
    public void whenAPigeonVocalisesItMakesACooingNoise() throws Exception {
        assertEquals("Coo!", new Pigeon().vocalise());
    }
}
