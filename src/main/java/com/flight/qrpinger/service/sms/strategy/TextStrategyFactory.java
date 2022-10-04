package com.flight.qrpinger.service.sms.strategy;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Log
@Component
public class TextStrategyFactory {
    private Map<String, TextStrategy> strategies;

    @Autowired
    public TextStrategyFactory(Set<TextStrategy> strategySet) {
        log.info("StrategyFactory constructor");
        createStrategy(strategySet);
    }

    public void createStrategy(Set<TextStrategy> strategySet) {
        log.info("createStrategy");
        this.strategies = new HashMap<String,TextStrategy>();
        strategySet.forEach(strategy -> this.strategies.put(strategy.getStrategyName(),strategy));
        log.info("createStrategy strategies: "+strategies);
    }

    public TextStrategy findStrategy(String strategyName) {
        TextStrategy strategy = strategies.get(strategyName);
        log.info("findStrategy on "+strategyName+" returning "+strategy);
        return strategy;
    }

}
