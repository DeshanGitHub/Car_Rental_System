package lk.ijse.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class EmployeeDTO {
    private String id;
    private NameDTO nameDTO;
    private String address;
    private String email;
    private String contactNum;
    private String jobRole;
    private String password;
    private String userName;


}
