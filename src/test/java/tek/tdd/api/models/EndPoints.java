package tek.tdd.api.models;

public enum EndPoints {
    TOKEN("/api/token"),
    GET_ACCOUNT("/API/ACCOUNTS/GET-ACCOUNT"),
    GET_PRIMARY_ACCOUNT("/api/accounts/get-primary-account");

    private String value;

    EndPoints(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
