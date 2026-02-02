package org.example.assignment3.composite;

import org.example.assignment3.component.OrganizationComponent;

import java.util.ArrayList;
import java.util.List;

public class Department implements OrganizationComponent {

    private final String name;
    private final List<OrganizationComponent> children = new ArrayList<>();

    public Department(String name) {
        this.name = name;
    }

    // 单一方法调用：添加
    public void add(OrganizationComponent component) {
        children.add(component);
    }

    // 单一方法调用：移除
    public void remove(OrganizationComponent component) {
        children.remove(component);
    }

    @Override
    public double getTotalSalary() {
        double total = 0;
        for (OrganizationComponent c : children) {
            total += c.getTotalSalary();
        }
        return total;
    }

    @Override
    public void printXML(String indent) {
        System.out.println(indent + "<department name=\"" + name + "\">");

        for (OrganizationComponent c : children) {
            c.printXML(indent + "  ");
        }

        System.out.println(indent + "</department>");
    }
}
