package policy.management.exception.error;

public enum ErrorEnum {

    NOT_FOUND_EXCEPTION(1, "POLICY NOT FOUND", "Policy does not exist"),
    DATE_CONVERSION_EXCEPTION(2, "DATE CONVERSION EXCEPTION", "Something went wrong while converting a date"),
    INVALID_DATE_EXCEPTION(3, "INVALID DATE", "The provided date is invalid, it must be greater or equal to the current date"),
    INVALID_NUMBER_OF_INSURED_PEOPLE(4, "INVALID INSURED PEOPLE NUMBER", "The number of insured people can not be 0"),
    CONSTRAINT_VALIDATION_EXCEPTION(5, "CONSTRAINT EXCEPTION", "A constraint has been violated"),
    UNEXPECTED_EXCEPTION(6, "SOMETHING WENT WRONG", "Sorry for the inconvenience, please contact an administrator or try later");

    public final int code;
    public final String title;
    public final String description;

    ErrorEnum(int code, String title, String description) {
        this.code = code;
        this.title = title;
        this.description = description;
    }

}
