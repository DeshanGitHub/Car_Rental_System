package lk.ijse.spring.entity;

import lk.ijse.spring.embeded.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
public class Employee {
    @Id
    private String id;
    private Name name;
    private String address;
    private String email;
    private String contactNum;
    private String jobRole;
    private String password;
    private String userName;
}
