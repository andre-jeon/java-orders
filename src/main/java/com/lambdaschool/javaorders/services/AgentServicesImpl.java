package com.lambdaschool.javaorders.services;

import com.lambdaschool.javaorders.models.Agent;
import com.lambdaschool.javaorders.repositories.AgentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "agentServices")
public class AgentServicesImpl implements AgentServices{
    @Autowired
    AgentsRepository agentrepos;

    @Override
    public Agent save(Agent agent) {
        return agentrepos.save(agent);
    }

    @Override
    public Agent findAgentById(long agentId) {
        return agentrepos.findById(agentId)
                .orElseThrow(() -> new EntityNotFoundException("Agent " + agentId + " Was not found!"));
    }
}
