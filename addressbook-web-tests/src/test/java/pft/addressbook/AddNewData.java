package pft.addressbook;

public class AddNewData {
    private final String firstname;
    private final String middlename;
    private final String lastname;
    private final String nickname;
    private final String title;
    private final String company;
    private final String address;
    private final String homephone;
    private final String email;
    private final String homepage;
    private final String birthyear;
    private final String ayyear;
    private final String address2;
    private final String phone2;
    private final String notes2;

    public AddNewData(String firstname, String middlename, String lastname, String nickname, String title, String company, String address, String homephone, String email, String homepage, String birthyear, String ayyear, String address2, String phone2, String notes2) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.title = title;
        this.company = company;
        this.address = address;
        this.homephone = homephone;
        this.email = email;
        this.homepage = homepage;
        this.birthyear = birthyear;
        this.ayyear = ayyear;
        this.address2 = address2;
        this.phone2 = phone2;
        this.notes2 = notes2;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getHomephone() {
        return homephone;
    }

    public String getEmail() {
        return email;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getBirthyear() {
        return birthyear;
    }

    public String getAyyear() {
        return ayyear;
    }

    public String getAddress2() {
        return address2;
    }

    public String getPhone2() {
        return phone2;
    }

    public String getNotes2() {
        return notes2;
    }
}
