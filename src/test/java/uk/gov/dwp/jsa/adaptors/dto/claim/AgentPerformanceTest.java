package uk.gov.dwp.jsa.adaptors.dto.claim;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AgentPerformanceTest {
    private final String AGENT = "Agent 1";
    private final int EXPECTED_SUCCESS = 1;
    private final int EXPECTED_FAIL = 1;
    private final int EXPECTED_WITHDRAWN = 1;
    private final int EXPECTED_TOTAL = 1;

    private AgentPerformance agentPerformance;

    @Before
    public void setUp() {
        agentPerformance = new AgentPerformance("Agent 0", 0, 0, 0, 0);
    }

    @Test
    public void setAgent() {
        agentPerformance.setAgent(AGENT);
        assertThat(agentPerformance.getAgent(), is(AGENT));
    }

    @Test
    public void setSuccess() {
        agentPerformance.setSuccess(EXPECTED_SUCCESS);
        assertThat(agentPerformance.getSuccess(), is(EXPECTED_SUCCESS));
    }

    @Test
    public void setFail() {
        agentPerformance.setFail(EXPECTED_FAIL);
        assertThat(agentPerformance.getFail(), is(EXPECTED_FAIL));
    }

    @Test
    public void setWithdrawn() {
        agentPerformance.setWithdrawn(EXPECTED_WITHDRAWN);
        assertThat(agentPerformance.getWithdrawn(), is(EXPECTED_WITHDRAWN));
    }

    @Test
    public void setTotal() {
        agentPerformance.setWithdrawn(EXPECTED_TOTAL);
        assertThat(agentPerformance.getWithdrawn(), is(EXPECTED_TOTAL));
    }

    @Test
    public void csvHeaders() {
        assertThat(AgentPerformance.csvHeaders(), is("Agent, Success, Fail, Withdrawn, Total"));
    }
}
