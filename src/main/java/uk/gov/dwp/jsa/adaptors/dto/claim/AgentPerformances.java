package uk.gov.dwp.jsa.adaptors.dto.claim;

import java.time.LocalDate;

public class AgentPerformances {

    private LocalDate startDate;
    private LocalDate endDate;
    private AgentPerformance[] agents;

    public AgentPerformances() {
    }

    public AgentPerformances(final LocalDate startDate, final LocalDate endDate, final AgentPerformance[] agents) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.agents = agents.clone();
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(final LocalDate date) {
        this.startDate = date;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(final LocalDate endDate) {
        this.endDate = endDate;
    }

    public AgentPerformance[] getAgents() {
        return agents != null ? agents.clone() : new AgentPerformance[] {};
    }

    public void setAgents(final AgentPerformance[] agents) {
        this.agents = agents.clone();
    }
}
