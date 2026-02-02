package org.example.assignment3.leaf;

import org.example.assignment3.component.OrganizationComponent;

public class Employee implements OrganizationComponent {

    private final String name;
    private final double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public double getTotalSalary() {
        return salary;
    }

    @Override
    public void printXML(String indent) {
        System.out.println(
                indent +
                        "<employee name=\"" + name +
                        "\" salary=\"" + salary + "\" />"
        );
    }
}
