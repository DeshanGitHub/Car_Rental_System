package lk.ijse.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class CustomerDTO {
    private String cusId;
    private NameDTO cusName;
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
