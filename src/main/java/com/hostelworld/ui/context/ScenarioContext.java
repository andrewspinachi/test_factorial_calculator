package com.hostelworld.ui.context;

import io.cucumber.java.Scenario;
import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ScenarioContext {

    @Getter
    @Setter
    private Scenario scenario;

    private Map<Context, Object> data = new HashMap<>();

    public void saveData(Context key, Object value) {
        this.data.put(key, value);
    }

    public Object getData(Context key) {
        return this.data.get(key);
    }

    public void resetContext() {
        data.clear();
    }
}