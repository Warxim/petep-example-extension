package connection;

import com.warxim.petep.core.connection.StringBasedConnectionManager;
import com.warxim.petep.helper.PetepHelper;

import java.util.UUID;

/**
 * Connection manager for example connections, uses random UUID as generated connection code.
 */
public class ExampleConnectionManager extends StringBasedConnectionManager {
    public ExampleConnectionManager(PetepHelper helper) {
        super(helper);
    }

    /**
     * Generates connection code.
     * @return Random UUID without "-" character
     */
    public String generateCode() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
