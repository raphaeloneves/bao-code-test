package pt.raphaelneves.bao.test.codingchallenge.integrations.connectors;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import pt.raphaelneves.bao.test.codingchallenge.CodingChallengeApplication;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = CodingChallengeApplication.class)
public class DataElementGroupConnectorTest {

    @Mock
    private DataElementGroupConnector connector;

}