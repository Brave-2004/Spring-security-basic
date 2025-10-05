package G50.pdp.controller;

import G50.pdp.dto.EmployeeDto;
import G50.pdp.model.Employee;
import G50.pdp.model.Group;
import G50.pdp.model.User;
import G50.pdp.server.DataSource;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.Authenticator;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {
    public final DataSource dataSource;

    @GetMapping(produces = "application/json")
    public List<EmployeeDto> getEmployees(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        EntityManager entityManager = dataSource.getEntityManager();

        List<Employee> employees = entityManager.createQuery("from Employee ",Employee.class).getResultList();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof User) {
            User principal = (User) authentication.getPrincipal();
            System.out.println(principal.getUsername());
        }
        System.out.println(authentication);

        return employees.stream().map(employeeDto->new EmployeeDto(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getGenderEnum(),
                employeeDto.getEmail(),
                employeeDto.getPhoneNumber(),
                employeeDto.getGroup() != null ? employeeDto.getGroup().getName() : null
        )).toList();
    }


    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        EntityManager entityManager = dataSource.getEntityManager();
        entityManager.getTransaction().begin();

        if (employee.getGroup() != null && employee.getGroup().getId() != null) {
            Group group = entityManager.find(Group.class, employee.getGroup().getId());
            employee.setGroup(group);
        }

        entityManager.persist(employee);
        entityManager.getTransaction().commit();
        return employee;
    }


    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable(name = "id") Integer id) {
        EntityManager entityManager = dataSource.getEntityManager();
        entityManager.getTransaction().begin();
        Employee employeeDto = entityManager.find(Employee.class, id);
        entityManager.remove(employeeDto);
        entityManager.getTransaction().commit();
    }

}
