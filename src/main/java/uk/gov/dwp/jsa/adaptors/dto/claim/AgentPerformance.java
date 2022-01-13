package uk.gov.dwp.jsa.adaptors.dto.claim;

import com.opencsv.bean.CsvBindByPosition;

public class AgentPerformance {

    private static final int AGENT_MI_COLUMN_INDEX_AGENT = 0;
    private static final int AGENT_MI_COLUMN_INDEX_SUCCESS = 1;
    private static final int AGENT_MI_COLUMN_INDEX_FAIL = 2;
    private static final int AGENT_MI_COLUMN_INDEX_WITHDRAWN = 3;
    private static final int AGENT_MI_COLUMN_INDEX_TOTAL = 4;

    @CsvBindByPosition(position = AGENT_MI_COLUMN_INDEX_AGENT)
    private String agent;
    @CsvBindByPosition(position = AGENT_MI_COLUMN_INDEX_SUCCESS)
    private int success;
    @CsvBindByPosition(position = AGENT_MI_COLUMN_INDEX_FAIL)
    private int fail;
    @CsvBindByPosition(position = AGENT_MI_COLUMN_INDEX_WITHDRAWN)
    private int withdrawn;
    @CsvBindByPosition(position = AGENT_MI_COLUMN_INDEX_TOTAL)
    private int total;

    public AgentPerformance() {
    }

    public AgentPerformance(
            final String agent, final int success, final int fail, final int withdrawn, final int total
    ) {
        this.agent = agent;
        this.success = success;
        this.fail = fail;
        this.withdrawn = withdrawn;
        this.total = total;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(final String agent) {
        this.agent = agent;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(final int success) {
        this.success = success;
    }

    public int getFail() {
        return fail;
    }

    public void setFail(final int fail) {
        this.fail = fail;
    }

    public int getWithdrawn() {
        return withdrawn;
    }

    public void setWithdrawn(final int withdrawn) {
        this.withdrawn = withdrawn;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(final int total) {
        this.total = total;
    }

    public static String csvHeaders() {
        return "Agent, Success, Fail, Withdrawn, Total";
    }
}
