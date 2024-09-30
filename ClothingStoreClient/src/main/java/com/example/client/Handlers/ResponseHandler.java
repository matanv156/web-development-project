package com.example.client.Handlers;

import com.example.client.Models.Employee;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class ResponseHandler {
    static Gson gson = new Gson();

    public static Employee getEmployeeFromRequest(JsonElement element) {
        return gson.fromJson(element, Employee.class);
    }

    public static List<Employee> getAllEmployeesFromRequest(JsonElement element) {
        Type employeeListType = new TypeToken<List<Employee>>() {}.getType();
        return gson.fromJson(element, employeeListType);
    }
}
