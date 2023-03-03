package lk.ijse.spring.entity;

import lk.ijse.spring.dto.NameDTO;
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
public class Customer {
    @Id
    private String cusId;
    private Name cusName;
    private String cusAddress;
    private String cusNicOrDlNum;
    private String cusContactNum;
    private String cusEmail;
    private String cusUserName;
    private String cusPassword;
    private String cusImgDescription;
    private String cusFrontImgPath;
    private String cusBackImgPath;
    private String cusStatus;
    private String cusStatusReason;
}
