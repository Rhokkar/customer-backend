package at.fhv.jazzers.customerBackend.domain.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long customerIdInternal;

    @Embedded
    private CustomerId customerId;

    private String givenName;

    private String familyName;

    private String birthName;

    private String gender;

    private LocalDate birthDate;

    private int height;

    private String eyecolor;

    private String email;

    private String taxId;

    private String addressaddressCountry;

    private String addressaddressLocality;

    private String addresspostalCode;

    private String addressstreetAddress;

    private String addresshouseNumber;

    private String phoneNo;

    private String mobileNo;

    private String bankAccountbankcity;

    private String bankAccountbankbankCode;

    private String bankAccountbankdesc;

    private String bankAccountbankbic;

    private String bankAccountiban;

    private String creditCardnumber;

    private String creditCardtype;

    private String creditCardcvc;

    protected Customer() {

    }

    public Long customerIdInternal() {
        return customerIdInternal;
    }

    public CustomerId customerId() {
        return customerId;
    }

    public String givenName() {
        return givenName;
    }

    public String familyName() {
        return familyName;
    }

    public String birthName() {
        return birthName;
    }

    public String gender() {
        return gender;
    }

    public LocalDate birthDate() {
        return birthDate;
    }

    public int height() {
        return height;
    }

    public String eyecolor() {
        return eyecolor;
    }

    public String email() {
        return email;
    }

    public String taxId() {
        return taxId;
    }

    public String addressaddressCountry() {
        return addressaddressCountry;
    }

    public String addressaddressLocality() {
        return addressaddressLocality;
    }

    public String addresspostalCode() {
        return addresspostalCode;
    }

    public String addressstreetAddress() {
        return addressstreetAddress;
    }

    public String addresshouseNumber() {
        return addresshouseNumber;
    }

    public String phoneNo() {
        return phoneNo;
    }

    public String mobileNo() {
        return mobileNo;
    }

    public String bankAccountbankcity() {
        return bankAccountbankcity;
    }

    public String bankAccountbankbankCode() {
        return bankAccountbankbankCode;
    }

    public String bankAccountbankdesc() {
        return bankAccountbankdesc;
    }

    public String bankAccountbankbic() {
        return bankAccountbankbic;
    }

    public String bankAccountiban() {
        return bankAccountiban;
    }

    public String creditCardnumber() {
        return creditCardnumber;
    }

    public String creditCardtype() {
        return creditCardtype;
    }

    public String creditCardcvc() {
        return creditCardcvc;
    }
}