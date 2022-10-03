package com.flight.qrpinger.service.sms.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class StrategyFactory {
    private Map<StrategyName, TextStrategy> strategies;

    @Autowired
    public StrategyFactory(Set<TextStrategy> strategySet) {
        createStrategy(strategySet);
    }

    public void createStrategy(Set<TextStrategy> strategySet) {
        this.strategies = new HashMap<StrategyName,TextStrategy>();
        strategySet.forEach(strategy -> this.strategies.put(strategy.getStrategyName(),strategy));
    }

    public TextStrategy findStrategy(String strategyName) {
        return strategies.get(strategyName);
    }

}
