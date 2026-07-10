package streamapi;


import jdk.dynalink.linker.support.Guards;

import java.util.*;
import java.util.stream.Collectors;

public class StreamApiPractice {
    public void employeeExample(){
        Employee e1=new Employee(12,"Rak");
        Employee e2=new Employee(2,"Raj");
        Employee e3=new Employee(2,"Raje");
        List<Employee> input = new ArrayList<>();
        input.add(e1);
        input.add(e2);
        input.add(e3);
       input.stream()
               .sorted(Comparator.comparingInt(Employee::getId).reversed())
               .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        StreamApiPractice streamApiPractice=new StreamApiPractice();
        streamApiPractice.employeeExample();
    }

}
