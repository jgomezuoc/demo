package com.qindel.requests;

public abstract class AbstractRequest {


    /**
     * This method is used to clean and validate the request
     */
    public void cleanAndValidate() {
        this.preprocessAttributes();
        this.validate();
    }

    /**
     * This method is used to clean and apply the necesary changes to the request
     */
    protected abstract void preprocessAttributes();


    /**
     * This method is used to validate the request
     */
    protected abstract void validate();
}
