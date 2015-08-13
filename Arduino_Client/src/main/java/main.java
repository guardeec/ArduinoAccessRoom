import Stages.EndpointStructure.endpoint;
import Stages.InitializationStructure.initialisation;
import Stages.WorkStructure.work;

/**
 * Created by guardeec on 18.05.15.
 */
public class main {
    public static void main(String args[]) {
        initialisation.start();
        work.start();
        endpoint.start();
    }

}

