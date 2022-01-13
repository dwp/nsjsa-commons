package uk.gov.dwp.jsa.adaptors.dto.claim;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AgentPerformancesTest {
    private AgentPerformances agentPerformances;
    private final LocalDate EXPECTED_START_DATE = LocalDate.now();
    private final LocalDate EXPECTED_END_DATE = EXPECTED_START_DATE.plusDays(3);
    private final AgentPerformance EXPECTED_AGENT = new AgentPerformance("Agent 1", 1, 1, 1, 1);
    private final AgentPerformance[] EXPECTED_AGENT_ARRAY = new AgentPerformance[] { EXPECTED_AGENT };
    private final AgentPerformance[] EXPECTED_NULL_ARRAY = new AgentPerformance[0];

    @Before
    public void setUp() {
        agentPerformances = new AgentPerformances();
    }

    @Test
    public void setStartDate() {
        agentPerformances.setStartDate(EXPECTED_START_DATE);
        assertThat(agentPerformances.getStartDate(), is(EXPECTED_START_DATE));
    }

    @Test
    public void setEndDate() {
        agentPerformances.setStartDate(EXPECTED_END_DATE);
        assertThat(agentPerformances.getStartDate(), is(EXPECTED_END_DATE));
    }

    @Test
    public void getAgents() {
        assertThat(agentPerformances.getAgents(), is(EXPECTED_NULL_ARRAY));
    }

    @Test
    public void setAgents() {
        agentPerformances.setAgents(EXPECTED_AGENT_ARRAY);
        assertThat(agentPerformances.getAgents(), is(EXPECTED_AGENT_ARRAY));
    }
}
