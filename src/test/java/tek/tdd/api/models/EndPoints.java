package tek.tdd.api.models;

import lombok.Getter;

@Getter
public enum EndPoints {
    TOKEN("/api/token"),
    GET_ACCOUNT("/API/ACCOUNTS/GET-ACCOUNT"),
    ADD_PRIMARY_ACCOUNT("/api/accounts/add-primary-account"),
    GET_PRIMARY_ACCOUNT("/api/accounts/get-primary-account"),
    GET_ALL_PLAN_CODE("/api/plans/get-all-plan-code"),
    GET_USER_PROFILE("/api/user/profile");

    private String value;

    EndPoints(String value){
        this.value = value;
    }

}
