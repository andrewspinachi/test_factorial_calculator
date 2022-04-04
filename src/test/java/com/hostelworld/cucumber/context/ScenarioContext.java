package com.hostelworld.cucumber.context;

import io.cucumber.messages.Messages;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ScenarioContext {

    private Messages.GherkinDocument.Feature.Scenario scenario;
    private Map<Context, Object> data = new HashMap<>();

    public void saveData(Context key, Object value) {
        this.data.put(key, value);
    }

    public Object getData(Context key) {
        return this.data.get(key);
    }

    public String getScenarioName() {
        return scenario.getName();
    }

    public void resetContext() {
        data.clear();
    }

}